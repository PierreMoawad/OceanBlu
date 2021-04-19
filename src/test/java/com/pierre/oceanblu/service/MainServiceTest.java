package com.pierre.oceanblu.service;

import com.pierre.oceanblu.exception.IllegalImageSizeException;
import com.pierre.oceanblu.exception.IllegalPurchaseStatusException;
import com.pierre.oceanblu.exception.IllegalRatingFormTypeException;
import com.pierre.oceanblu.model.Product;
import com.pierre.oceanblu.model.Rating;
import com.pierre.oceanblu.model.Transaction;
import com.pierre.oceanblu.model.User;
import com.pierre.oceanblu.model.form.PurchaseProductsForm;
import com.pierre.oceanblu.model.form.RatingForm;
import com.pierre.oceanblu.model.form.RatingForm.Type;
import com.pierre.oceanblu.repository.ProductRepository;
import com.pierre.oceanblu.repository.RatingRepository;
import com.pierre.oceanblu.repository.TransactionRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static com.pierre.oceanblu.model.Transaction.Code.*;
import static com.pierre.oceanblu.model.User.Gender.*;
import static com.pierre.oceanblu.model.User.Role.*;
import static com.pierre.oceanblu.model.form.PurchaseProductsForm.*;
import static com.pierre.oceanblu.model.form.PurchaseProductsForm.Status.*;
import static com.pierre.oceanblu.model.form.PurchaseProductsForm.Status.TEST_CASE_VALUE;
import static com.pierre.oceanblu.model.form.RatingForm.Type.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class MainServiceTest {

    private final TransactionRepository transactionRepositoryMock = mock(TransactionRepository.class);
    private final ProductRepository productRepositoryMock = mock(ProductRepository.class);
    private final RatingRepository ratingRepositoryMock = mock(RatingRepository.class);

    private MainService mainService;

    @Before
    public void before() {

        mainService = new MainService(
                transactionRepositoryMock,
                productRepositoryMock,
                ratingRepositoryMock
        );

        ReflectionTestUtils.setField(
                mainService,
                "maxUploadSize",
                DataSize.ofKilobytes(64),
                DataSize.class
        );
    }

    @Test
    public void ListAllTransactionsTest() {

        List<Transaction> actualTransactions = Arrays.asList(
                Transaction.builder()
                        .id(1L)
                        .product(Product.builder()
                                .id(1000L)
                                .name("iPhone X")
                                .company("Apple")
                                .price(950F)
                                .quantity(14)
                                .description("Nice Phone!")
                                .build())
                        .date(LocalDateTime.of(2021, 2, 12, 3, 27, 16))
                        .code(OUT)
                        .items(2)
                        .user(User.builder()
                                .id(1L)
                                .firstName("John")
                                .lastName("Doe")
                                .username("j.doe")
                                .password("ol1124#")
                                .role(USER)
                                .gender(MALE)
                                .build())
                        .rating(Rating.builder()
                                .id(1L)
                                .rate(4)
                                .reviewLeft(false)
                                .build())
                        .currentItems(12)
                        .currentValue(11400F)
                        .build(),

                Transaction.builder()
                        .id(2L)
                        .product(Product.builder()
                                .id(1001L)
                                .name("Galaxy S9")
                                .company("Samsung")
                                .price(1000F)
                                .quantity(3)
                                .description("Some specs...")
                                .build())
                        .date(LocalDateTime.of(2021, 3, 4, 12, 5, 37))
                        .code(IN)
                        .items(12)
                        .user(User.builder()
                                .id(2L)
                                .firstName("Caroline")
                                .lastName("Thomson")
                                .username("admin")
                                .password("aDmIn*/8558/*")
                                .role(ADMIN)
                                .gender(FEMALE)
                                .build())
                        .currentItems(15)
                        .currentValue(15000F)
                        .build()
        );

        when(transactionRepositoryMock.findAll()).thenReturn(actualTransactions);

        List<Transaction> expectedTransactions = mainService.listAllTransactions();

        assertThat(actualTransactions).isEqualTo(expectedTransactions);
    }

    @Test
    public void getTransactionByIDTest() {

        final Long actualTransactionID = 1L;

        Transaction actualTransaction = Transaction.builder()
                .id(actualTransactionID)
                .product(Product.builder()
                        .id(1000L)
                        .name("iPhone X")
                        .company("Apple")
                        .price(950F)
                        .quantity(14)
                        .description("Nice Phone!")
                        .build())
                .date(LocalDateTime.of(2021, 2, 12, 3, 27, 16))
                .code(OUT)
                .items(2)
                .user(User.builder()
                        .id(1L)
                        .firstName("John")
                        .lastName("Doe")
                        .username("j.doe")
                        .password("ol1124#")
                        .role(USER)
                        .gender(MALE)
                        .build())
                .rating(Rating.builder()
                        .id(1L)
                        .rate(4)
                        .reviewLeft(false)
                        .build())
                .currentItems(12)
                .currentValue(11400F)
                .build();

        when(transactionRepositoryMock.getById(actualTransactionID)).thenReturn(actualTransaction);

        Transaction expectedTransaction = mainService.getTransactionByID(actualTransactionID);

        assertThat(actualTransaction).isEqualTo(expectedTransaction);
    }

    @Test
    public void saveTransactionTest() {

        Transaction transaction = Transaction.builder()
                .id(1L)
                .product(Product.builder()
                        .id(1000L)
                        .name("iPhone X")
                        .company("Apple")
                        .price(950F)
                        .quantity(14)
                        .description("Nice Phone!")
                        .build())
                .date(LocalDateTime.of(2021, 2, 12, 3, 27, 16))
                .code(OUT)
                .items(2)
                .user(User.builder()
                        .id(1L)
                        .firstName("John")
                        .lastName("Doe")
                        .username("j.doe")
                        .password("ol1124#")
                        .role(USER)
                        .gender(MALE)
                        .build())
                .rating(Rating.builder()
                        .id(1L)
                        .rate(4)
                        .reviewLeft(false)
                        .build())
                .currentItems(12)
                .currentValue(11400F)
                .build();

        when(transactionRepositoryMock.save(transaction)).thenReturn(transaction);

        mainService.saveTransaction(transaction);

        verify(transactionRepositoryMock, times(1)).save(transaction);
    }

    //////////////////////////////////////////
    //  Tests for purchaseProduct() method  //
    //////////////////////////////////////////

    // Test if method getById() is called when the product is existing in DB
    @Test
    public void getByIdCalledForExistingProductTest() throws IOException {

        final Long existingProductID = 1000L;

        PurchaseProductsForm form = PurchaseProductsForm.builder()
                .status(EXISTING_PRODUCT)
                .existingProductID(existingProductID)
                .newProduct(null)
                .newProductImage(null)
                .quantity(5)
                .build();

        Product existingProduct = Product.builder()
                .id(existingProductID)
                .name("iPhone X")
                .company("Apple")
                .price(950F)
                .quantity(14)
                .description("Nice Phone!")
                .build();

        User user = User.builder()
                .id(2L)
                .firstName("Caroline")
                .lastName("Thomson")
                .username("admin")
                .password("aDmIn*/8558/*")
                .role(ADMIN)
                .gender(FEMALE)
                .build();

        when(productRepositoryMock.getById(existingProductID)).thenReturn(existingProduct);

        mainService.purchaseProduct(form, user);

        verify(productRepositoryMock, times(1)).getById(existingProductID);
    }

    // Test if method getById() is never called when the product is new
    @Test
    public void getByIdNeverCalledForNewProductTest() throws IOException {

        final Long newProductID = 1001L;

        Product newProduct = Product.builder()
                .id(newProductID)
                .name("iPhone X")
                .company("Apple")
                .price(950F)
                .quantity(14)
                .description("Nice Phone!")
                .build();

        PurchaseProductsForm form = PurchaseProductsForm.builder()
                .status(NEW_PRODUCT)
                .existingProductID(null)
                .newProduct(newProduct)
                .newProductImage(new MockMultipartFile("image.jpg", new byte[]{1, 1, 1}))
                .quantity(5)
                .build();

        User user = User.builder()
                .id(2L)
                .firstName("Caroline")
                .lastName("Thomson")
                .username("admin")
                .password("aDmIn*/8558/*")
                .role(ADMIN)
                .gender(FEMALE)
                .build();

        mainService.purchaseProduct(form, user);

        verify(productRepositoryMock, times(0)).getById(newProductID);
    }

    // Test if newProductImage is added to productToBeSaved when product is new
    @Test
    public void newProductImageAddedToProductToBeSavedTest() throws IOException {

        final MultipartFile newProductImage = new MockMultipartFile("image.jpg", new byte[]{1, 1, 1});

        Product newProduct = Product.builder()
                .id(1001L)
                .name("iPhone X")
                .company("Apple")
                .price(950F)
                .quantity(14)
                .description("Nice Phone!")
                .build();

        PurchaseProductsForm form = PurchaseProductsForm.builder()
                .status(NEW_PRODUCT)
                .existingProductID(null)
                .newProduct(newProduct)
                .newProductImage(newProductImage)
                .quantity(5)
                .build();

        User user = User.builder()
                .id(2L)
                .firstName("Caroline")
                .lastName("Thomson")
                .username("admin")
                .password("aDmIn*/8558/*")
                .role(ADMIN)
                .gender(FEMALE)
                .build();

        when(productRepositoryMock.save(newProduct)).thenReturn(newProduct);

        mainService.purchaseProduct(form, user);
        byte[] actualImage = newProductImage.getBytes();
        byte[] expectedImage = productRepositoryMock.save(newProduct).getImage();

        assertThat(actualImage).isEqualTo(expectedImage);
    }

    // Test if IllegalImageSizeException is thrown when image size exceeds max
    @Test
    public void IllegalImageSizeExceptionThrownTest() {

        Product newProduct = Product.builder()
                .id(1001L)
                .name("iPhone X")
                .company("Apple")
                .price(950F)
                .quantity(14)
                .description("Nice Phone!")
                .build();

        PurchaseProductsForm form = PurchaseProductsForm.builder()
                .status(NEW_PRODUCT)
                .existingProductID(null)
                .newProduct(newProduct)
                .newProductImage(new MockMultipartFile("image.jpg", new byte[71680]))
                .quantity(5)
                .build();

        User user = User.builder()
                .id(2L)
                .firstName("Caroline")
                .lastName("Thomson")
                .username("admin")
                .password("aDmIn*/8558/*")
                .role(ADMIN)
                .gender(FEMALE)
                .build();

        assertThatExceptionOfType(IllegalImageSizeException.class)
                .isThrownBy(() -> mainService.purchaseProduct(form, user))
                .withMessage("Uploaded image size 70KB exceeds maximum 64KB");
    }

    // Test if IllegalPurchaseStatusException is thrown for illegal status value
    @Test
    public void IllegalPurchaseStatusExceptionThrownTest() {

        final Status illegalStatusValue = TEST_CASE_VALUE;

        Product newProduct = Product.builder()
                .id(1001L)
                .name("iPhone X")
                .company("Apple")
                .price(950F)
                .quantity(14)
                .description("Nice Phone!")
                .build();

        PurchaseProductsForm form = PurchaseProductsForm.builder()
                .status(illegalStatusValue)
                .existingProductID(null)
                .newProduct(newProduct)
                .newProductImage(new MockMultipartFile("image.jpg", new byte[]{1, 1, 1}))
                .quantity(5)
                .build();

        User user = User.builder()
                .id(2L)
                .firstName("Caroline")
                .lastName("Thomson")
                .username("admin")
                .password("aDmIn*/8558/*")
                .role(ADMIN)
                .gender(FEMALE)
                .build();

        assertThatExceptionOfType(IllegalPurchaseStatusException.class)
                .isThrownBy(() -> mainService.purchaseProduct(form, user))
                .withMessage("Parameter 'status' has an illegal value: " + illegalStatusValue);
    }

    // Test if quantity is correctly added to productToBeSaved
    @Test
    public void quantityAddedToProductTest() throws IOException {

        final Long existingProductID = 1000L;
        final Integer purchasedQuantity = 5;
        final Integer existingQuantity = 15;
        final Integer actualQuantity = purchasedQuantity + existingQuantity;

        PurchaseProductsForm form = PurchaseProductsForm.builder()
                .status(EXISTING_PRODUCT)
                .existingProductID(existingProductID)
                .newProduct(null)
                .newProductImage(null)
                .quantity(purchasedQuantity)
                .build();

        Product existingProduct = Product.builder()
                .id(existingProductID)
                .name("iPhone X")
                .company("Apple")
                .price(950F)
                .quantity(existingQuantity)
                .description("Nice Phone!")
                .build();

        User user = User.builder()
                .id(2L)
                .firstName("Caroline")
                .lastName("Thomson")
                .username("admin")
                .password("aDmIn*/8558/*")
                .role(ADMIN)
                .gender(FEMALE)
                .build();

        when(productRepositoryMock.getById(existingProductID)).thenReturn(existingProduct);
        when(productRepositoryMock.save(existingProduct)).thenReturn(existingProduct);

        mainService.purchaseProduct(form, user);
        Integer expectedQuantity = productRepositoryMock.save(existingProduct).getQuantity();

        assertThat(actualQuantity).isEqualTo(expectedQuantity);
    }

    // End of tests for purchaseProduct() ///////////////

    @Test
    public void listAllProductsTest() {

        List<Product> actualProducts = Arrays.asList(
                Product.builder()
                        .id(1000L)
                        .name("iPhone X")
                        .company("Apple")
                        .price(950F)
                        .quantity(14)
                        .description("Nice Phone!")
                        .build(),

                Product.builder()
                        .id(1001L)
                        .name("Galaxy S9")
                        .company("Samsung")
                        .price(1000F)
                        .quantity(3)
                        .description("Some specs...")
                        .build()
        );

        when(productRepositoryMock.findAll()).thenReturn(actualProducts);

        List<Product> expectedProducts = mainService.listAllProducts();

        assertThat(actualProducts).isEqualTo(expectedProducts);
    }

    @Test
    public void getProductByIDTest() {

        final Long actualProductID = 1000L;

        Product actualProduct = Product.builder()
                .id(actualProductID)
                .name("iPhone X")
                .company("Apple")
                .price(950F)
                .quantity(14)
                .description("Nice Phone!")
                .build();

        when(productRepositoryMock.getById(actualProductID)).thenReturn(actualProduct);

        Product expectedProduct = mainService.getProductByID(actualProductID);

        assertThat(actualProduct).isEqualTo(expectedProduct);
    }

    //////////////////////////////////////
    //  Tests for sellProduct() method  //
    //////////////////////////////////////

    // Test if getById() is called by sellProduct()
    @Test
    public void getByIdCalledBySellProductTest() {

        final Long productID = 1000L;
        final Integer quantity = 3;

        Product product = Product.builder()
                .id(productID)
                .name("iPhone X")
                .company("Apple")
                .price(950F)
                .quantity(14)
                .description("Nice Phone!")
                .build();

        User user = User.builder()
                .id(2L)
                .firstName("Caroline")
                .lastName("Thomson")
                .username("admin")
                .password("aDmIn*/8558/*")
                .role(ADMIN)
                .gender(FEMALE)
                .build();

        when(productRepositoryMock.getById(productID)).thenReturn(product);

        mainService.sellProduct(productID, user, quantity);

        verify(productRepositoryMock, times(1)).getById(productID);
    }

    // Test if quantity is correctly subtracted from product
    @Test
    public void quantitySubtractedFromProductTest() {

        final Long productID = 1000L;
        final Integer productQuantity = 14;
        final Integer soldQuantity = 3;
        final Integer actualQuantity = productQuantity - soldQuantity;

        Product product = Product.builder()
                .id(productID)
                .name("iPhone X")
                .company("Apple")
                .price(950F)
                .quantity(productQuantity)
                .description("Nice Phone!")
                .build();

        User user = User.builder()
                .id(2L)
                .firstName("Caroline")
                .lastName("Thomson")
                .username("admin")
                .password("aDmIn*/8558/*")
                .role(ADMIN)
                .gender(FEMALE)
                .build();

        when(productRepositoryMock.getById(productID)).thenReturn(product);
        when(productRepositoryMock.save(product)).thenReturn(product);

        mainService.sellProduct(productID, user, soldQuantity);
        Integer expectedQuantity = product.getQuantity();

        assertThat(actualQuantity).isEqualTo(expectedQuantity);
    }

    // Test if save() is called by sellProduct()
    @Test
    public void saveCalledBySellProductTest() {

        final Long productID = 1000L;
        final Integer quantity = 3;

        Product product = Product.builder()
                .id(productID)
                .name("iPhone X")
                .company("Apple")
                .price(950F)
                .quantity(14)
                .description("Nice Phone!")
                .build();

        User user = User.builder()
                .id(2L)
                .firstName("Caroline")
                .lastName("Thomson")
                .username("admin")
                .password("aDmIn*/8558/*")
                .role(ADMIN)
                .gender(FEMALE)
                .build();

        when(productRepositoryMock.getById(productID)).thenReturn(product);
        when(productRepositoryMock.save(product)).thenReturn(product);

        mainService.sellProduct(productID, user, quantity);

        verify(productRepositoryMock, times(1)).save(product);
    }

    // End of tests for sellProduct() ///////////////

    /////////////////////////////////////
    //  Tests for saveRating() method  //
    /////////////////////////////////////

    // Test if getById() is called by saveRating()
    @Test
    public void getByIdCalledBySaveRatingTest() {

        final Long transactionID = 1L;

        Rating rating = Rating.builder()
                .id(1L)
                .rate(5)
                .reviewLeft(false)
                .build();

        Transaction transaction = Transaction.builder()
                .id(transactionID)
                .product(Product.builder()
                        .id(1001L)
                        .name("Galaxy S9")
                        .company("Samsung")
                        .price(1000F)
                        .quantity(3)
                        .description("Some specs...")
                        .build())
                .date(LocalDateTime.of(2021, 3, 4, 12, 5, 37))
                .code(IN)
                .items(12)
                .user(User.builder()
                        .id(2L)
                        .firstName("Caroline")
                        .lastName("Thomson")
                        .username("admin")
                        .password("aDmIn*/8558/*")
                        .role(ADMIN)
                        .gender(FEMALE)
                        .build())
                .rating(rating)
                .currentItems(15)
                .currentValue(15000F)
                .build();

        RatingForm form = RatingForm.builder()
                .type(ONLY_REVIEW)
                .transactionId(transactionID)
                .reviewTitle("title")
                .reviewBody("body")
                .build();

        when(transactionRepositoryMock.getById(transactionID)).thenReturn(transaction);

        mainService.saveRating(form);

        verify(transactionRepositoryMock, times(1)).getById(transactionID);
    }

    // Test if RatingRepository.save() is called by saveRating()
    @Test
    public void ratingRepositorySaveCalledBySaveRatingTest() {

        final Long transactionID = 1L;

        Rating rating = Rating.builder()
                .id(1L)
                .rate(5)
                .reviewLeft(false)
                .build();

        Transaction transaction = Transaction.builder()
                .id(transactionID)
                .product(Product.builder()
                        .id(1001L)
                        .name("Galaxy S9")
                        .company("Samsung")
                        .price(1000F)
                        .quantity(3)
                        .description("Some specs...")
                        .build())
                .date(LocalDateTime.of(2021, 3, 4, 12, 5, 37))
                .code(IN)
                .items(12)
                .user(User.builder()
                        .id(2L)
                        .firstName("Caroline")
                        .lastName("Thomson")
                        .username("admin")
                        .password("aDmIn*/8558/*")
                        .role(ADMIN)
                        .gender(FEMALE)
                        .build())
                .rating(rating)
                .currentItems(15)
                .currentValue(15000F)
                .build();

        RatingForm form = RatingForm.builder()
                .type(ONLY_REVIEW)
                .transactionId(transactionID)
                .reviewTitle("title")
                .reviewBody("body")
                .build();

        when(transactionRepositoryMock.getById(transactionID)).thenReturn(transaction);
        when(ratingRepositoryMock.save(rating)).thenReturn(rating);

        mainService.saveRating(form);

        verify(ratingRepositoryMock, times(1)).save(rating);
    }

    // Test if TransactionRepository.save() is called by saveRating()
    @Test
    public void transactionRepositorySaveCalledBySaveRatingTest() {

        final Long transactionID = 1L;

        Rating rating = Rating.builder()
                .id(1L)
                .rate(5)
                .reviewLeft(false)
                .build();

        Transaction transaction = Transaction.builder()
                .id(transactionID)
                .product(Product.builder()
                        .id(1001L)
                        .name("Galaxy S9")
                        .company("Samsung")
                        .price(1000F)
                        .quantity(3)
                        .description("Some specs...")
                        .build())
                .date(LocalDateTime.of(2021, 3, 4, 12, 5, 37))
                .code(IN)
                .items(12)
                .user(User.builder()
                        .id(2L)
                        .firstName("Caroline")
                        .lastName("Thomson")
                        .username("admin")
                        .password("aDmIn*/8558/*")
                        .role(ADMIN)
                        .gender(FEMALE)
                        .build())
                .rating(rating)
                .currentItems(15)
                .currentValue(15000F)
                .build();

        RatingForm form = RatingForm.builder()
                .type(ONLY_REVIEW)
                .transactionId(transactionID)
                .reviewTitle("title")
                .reviewBody("body")
                .build();

        when(transactionRepositoryMock.getById(transactionID)).thenReturn(transaction);
        when(ratingRepositoryMock.save(rating)).thenReturn(rating);
        when(transactionRepositoryMock.save(transaction)).thenReturn(transaction);

        mainService.saveRating(form);

        verify(transactionRepositoryMock, times(1)).save(transaction);
    }

    // Test if reviewLeft value changes from false to true when adding only review
    @Test
    public void reviewLeftValueChangesTest() {

        final Long transactionID = 1L;
        final boolean actualReviewLeft = false;

        Rating rating = Rating.builder()
                .id(1L)
                .rate(5)
                .reviewLeft(actualReviewLeft)
                .build();

        Transaction transaction = Transaction.builder()
                .id(transactionID)
                .product(Product.builder()
                        .id(1001L)
                        .name("Galaxy S9")
                        .company("Samsung")
                        .price(1000F)
                        .quantity(3)
                        .description("Some specs...")
                        .build())
                .date(LocalDateTime.of(2021, 3, 4, 12, 5, 37))
                .code(IN)
                .items(12)
                .user(User.builder()
                        .id(2L)
                        .firstName("Caroline")
                        .lastName("Thomson")
                        .username("admin")
                        .password("aDmIn*/8558/*")
                        .role(ADMIN)
                        .gender(FEMALE)
                        .build())
                .rating(rating)
                .currentItems(15)
                .currentValue(15000F)
                .build();

        RatingForm form = RatingForm.builder()
                .type(ONLY_REVIEW)
                .transactionId(transactionID)
                .reviewTitle("title")
                .reviewBody("body")
                .build();

        when(transactionRepositoryMock.getById(transactionID)).thenReturn(transaction);
        when(ratingRepositoryMock.save(rating)).thenReturn(rating);

        mainService.saveRating(form);
        boolean expectedReviewLeft = ratingRepositoryMock.save(rating).isReviewLeft();

        assertThat(actualReviewLeft).isNotEqualTo(expectedReviewLeft);
    }

    // Test if new review title and body are saved to rating when adding only review
    @Test
    public void titleAndBodySavedTest() {

        final Long transactionID = 1L;
        final String actualTitle = "title";
        final String actualBody = "body";

        Rating rating = Rating.builder()
                .id(1L)
                .rate(5)
                .reviewLeft(false)
                .build();

        Transaction transaction = Transaction.builder()
                .id(transactionID)
                .product(Product.builder()
                        .id(1001L)
                        .name("Galaxy S9")
                        .company("Samsung")
                        .price(1000F)
                        .quantity(3)
                        .description("Some specs...")
                        .build())
                .date(LocalDateTime.of(2021, 3, 4, 12, 5, 37))
                .code(IN)
                .items(12)
                .user(User.builder()
                        .id(2L)
                        .firstName("Caroline")
                        .lastName("Thomson")
                        .username("admin")
                        .password("aDmIn*/8558/*")
                        .role(ADMIN)
                        .gender(FEMALE)
                        .build())
                .rating(rating)
                .currentItems(15)
                .currentValue(15000F)
                .build();

        RatingForm form = RatingForm.builder()
                .type(ONLY_REVIEW)
                .transactionId(transactionID)
                .reviewTitle(actualTitle)
                .reviewBody(actualBody)
                .build();

        when(transactionRepositoryMock.getById(transactionID)).thenReturn(transaction);
        when(ratingRepositoryMock.save(rating)).thenReturn(rating);

        mainService.saveRating(form);
        String expectedTitle = ratingRepositoryMock.save(rating).getReviewTitle();
        String expectedBody = ratingRepositoryMock.save(rating).getReviewBody();

        assertThat(actualTitle).isEqualTo(expectedTitle);
        assertThat(actualBody).isEqualTo(expectedBody);
    }

    // Test if rate and reviewLeft are saved to rating when adding rate and review
    @Test
    public void rateAndReviewLeftSavedTest() {

        final Long transactionID = 1L;
        final Integer actualRate = 5;
        final boolean actualReviewLeft = false;

        Rating rating = Rating.builder()
                .id(1L)
                .build();

        Transaction transaction = Transaction.builder()
                .id(transactionID)
                .product(Product.builder()
                        .id(1001L)
                        .name("Galaxy S9")
                        .company("Samsung")
                        .price(1000F)
                        .quantity(3)
                        .description("Some specs...")
                        .build())
                .date(LocalDateTime.of(2021, 3, 4, 12, 5, 37))
                .code(IN)
                .items(12)
                .user(User.builder()
                        .id(2L)
                        .firstName("Caroline")
                        .lastName("Thomson")
                        .username("admin")
                        .password("aDmIn*/8558/*")
                        .role(ADMIN)
                        .gender(FEMALE)
                        .build())
                .rating(rating)
                .currentItems(15)
                .currentValue(15000F)
                .build();

        RatingForm form = RatingForm.builder()
                .type(RATE_AND_REVIEW)
                .transactionId(transactionID)
                .rate(actualRate)
                .reviewLeft(actualReviewLeft)
                .build();

        when(transactionRepositoryMock.getById(transactionID)).thenReturn(transaction);
        when(ratingRepositoryMock.save(rating)).thenReturn(rating);
        when(transactionRepositoryMock.save(transaction)).thenReturn(transaction);

        mainService.saveRating(form);
        Integer expectedRate = transactionRepositoryMock.save(transaction).getRating().getRate();
        boolean expectedReviewLeft = transactionRepositoryMock.save(transaction).getRating().isReviewLeft();

        assertThat(actualRate).isEqualTo(expectedRate);
        assertThat(actualReviewLeft).isEqualTo(expectedReviewLeft);
    }

    // Test if title and body are null in rating when adding rate and review and reviewLeft = false
    @Test
    public void bodyAndTileAreNullTest() {

        final Long transactionID = 1L;

        Rating rating = Rating.builder()
                .id(1L)
                .build();

        Transaction transaction = Transaction.builder()
                .id(transactionID)
                .product(Product.builder()
                        .id(1001L)
                        .name("Galaxy S9")
                        .company("Samsung")
                        .price(1000F)
                        .quantity(3)
                        .description("Some specs...")
                        .build())
                .date(LocalDateTime.of(2021, 3, 4, 12, 5, 37))
                .code(IN)
                .items(12)
                .user(User.builder()
                        .id(2L)
                        .firstName("Caroline")
                        .lastName("Thomson")
                        .username("admin")
                        .password("aDmIn*/8558/*")
                        .role(ADMIN)
                        .gender(FEMALE)
                        .build())
                .rating(rating)
                .currentItems(15)
                .currentValue(15000F)
                .build();

        RatingForm form = RatingForm.builder()
                .type(RATE_AND_REVIEW)
                .transactionId(transactionID)
                .rate(5)
                .reviewLeft(false)
                .reviewTitle("title")
                .reviewBody("body")
                .build();

        when(transactionRepositoryMock.getById(transactionID)).thenReturn(transaction);
        when(ratingRepositoryMock.save(rating)).thenReturn(rating);
        when(transactionRepositoryMock.save(transaction)).thenReturn(transaction);

        mainService.saveRating(form);
        String expectedTitle = transactionRepositoryMock.save(transaction).getRating().getReviewTitle();
        String expectedBody = transactionRepositoryMock.save(transaction).getRating().getReviewBody();

        assertNull(expectedTitle);
        assertNull(expectedBody);
    }

    // Test if IllegalRatingFormTypeException is thrown for invalid type value
    @Test
    public void illegalRatingFormTypeExceptionThrownTest() {

        final Long transactionID = 1L;
        final Type illegalType = Type.TEST_CASE_VALUE;

        RatingForm form = RatingForm.builder()
                .type(illegalType)
                .transactionId(transactionID)
                .rate(5)
                .reviewLeft(false)
                .reviewTitle("title")
                .reviewBody("body")
                .build();

        assertThatExceptionOfType(IllegalRatingFormTypeException.class)
                .isThrownBy(() -> mainService.saveRating(form))
                .withMessage("Parameter 'type' has an illegal value: " + illegalType);
    }

    // End of tests for saveRating() ///////////////
}
