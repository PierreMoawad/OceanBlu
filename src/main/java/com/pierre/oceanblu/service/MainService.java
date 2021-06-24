package com.pierre.oceanblu.service;

import com.pierre.oceanblu.exception.IllegalImageSizeException;
import com.pierre.oceanblu.exception.IllegalPurchaseStatusException;
import com.pierre.oceanblu.exception.IllegalRatingFormTypeException;
import com.pierre.oceanblu.model.form.PurchaseProductsForm;
import com.pierre.oceanblu.model.Rating;
import com.pierre.oceanblu.model.Transaction;
import com.pierre.oceanblu.model.Product;
import com.pierre.oceanblu.model.User;
import com.pierre.oceanblu.model.form.RatingForm;
import com.pierre.oceanblu.repository.RatingRepository;
import com.pierre.oceanblu.repository.TransactionRepository;
import com.pierre.oceanblu.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.unit.DataSize;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static com.pierre.oceanblu.model.Transaction.*;
import static com.pierre.oceanblu.model.Transaction.Code.*;

@Service
@RequiredArgsConstructor
public class MainService {

    @Value("${spring.servlet.multipart.max-file-size}")
    private DataSize maxUploadSize;

    private final TransactionRepository transactionRepository;
    private final ProductRepository productRepository;
    private final RatingRepository ratingRepository;

    public List<Transaction> listAllTransactions() {

        return transactionRepository.findAll();
    }

    public Transaction getTransactionByID(Long id) {

        return transactionRepository.getById(id);
    }

    public void saveTransaction(Transaction transaction) {

        transactionRepository.save(transaction);
    }

    public void purchaseProduct(PurchaseProductsForm form, User user) throws IOException {

        Product productToBeSaved;

        switch (form.getStatus()) {

            case EXISTING_PRODUCT:

                productToBeSaved = productRepository.getById(form.getExistingProductID());
                break;

            case NEW_PRODUCT:

                if (form.getNewProductImage().getSize() > maxUploadSize.toBytes()) {

                    throw new IllegalImageSizeException(form.getNewProductImage().getSize(), maxUploadSize.toBytes());
                }
                else {

                    productToBeSaved = form.getNewProduct();
                    productToBeSaved.setImage(form.getNewProductImage().getBytes());
                }
                break;

            default: throw new IllegalPurchaseStatusException(form.getStatus());
        }

        productToBeSaved.addQuantity(form.getQuantity());
        productRepository.save(productToBeSaved);
        transactionRepository.save(builder()
                .product(productToBeSaved)
                .user(user)
                .code(IN)
                .items(form.getQuantity())
                .currentItems(productToBeSaved.getQuantity())
                .currentValue(productToBeSaved.getQuantity() * productToBeSaved.getPrice())
                .build());
    }

    public List<Product> listAllProducts() {

        return productRepository.findAll();
    }

    public Product getProductByID(Long id) {

        return productRepository.getById(id);
    }

    public void sellProduct(Long id, User user, Integer quantity) {

        Product product = productRepository.getById(id);
        product.removeQuantity(quantity);
        productRepository.save(product);
        transactionRepository.save(builder()
                .product(product)
                .user(user)
                .code(OUT)
                .items(quantity)
                .currentItems(product.getQuantity())
                .currentValue(product.getQuantity() * product.getPrice())
                .build());
    }

    public void saveRating(RatingForm form) {

        Transaction transaction = transactionRepository.getById(form.getTransactionId());
        Rating rating;

        switch (form.getType()) {

            case ONLY_REVIEW:

                rating  = transaction.getRating();

                if (rating != null) {

                    rating.setReviewLeft(true);
                    rating.setReviewTitle(form.getReviewTitle());
                    rating.setReviewBody(form.getReviewBody());
                }
                break;

            case RATE_AND_REVIEW:

                rating = new Rating();
                rating.setRate(form.getRate());
                rating.setReviewLeft(form.isReviewLeft());

                if (form.isReviewLeft()) {

                    rating.setReviewTitle(form.getReviewTitle());
                    rating.setReviewBody(form.getReviewBody());
                }
                break;

            default: throw new IllegalRatingFormTypeException(form.getType());
        }

        transaction.setRating(rating);
        ratingRepository.save(Objects.requireNonNull(rating));
        transactionRepository.save(transaction);
    }
}