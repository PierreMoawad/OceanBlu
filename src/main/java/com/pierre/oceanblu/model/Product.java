package com.pierre.oceanblu.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static com.pierre.oceanblu.model.Transaction.*;

@Entity
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private Collection<Transaction> transactions;
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    @Nullable
    private Set<Wish> wishlist;
    private String name;
    private String company;
    private Float price;
    private Integer quantity;
    @Lob
    private byte[] image;
    private String description;

    public Product() {

        this.quantity = 0;
    }

    public Long getId() {

        return id;
    }

    public Collection<Transaction> getTransactions() {

        return new ArrayList<>(transactions);
    }

    public String getName() {

        return name;
    }

    public String getCompany() {

        return company;
    }

    public Float getPrice() {

        return price;
    }

    public Integer getQuantity() {

        return quantity;
    }

    public byte[] getImage() {

        return image;
    }

    public String getDescription() {

        return description;
    }

    public Collection<Rating> getRatingsWithReviews() {

        return new ArrayList<>(transactions).stream()
                .map(Transaction::getRating)
                .filter(Objects::nonNull)
                .filter(Rating::isReviewLeft)
                .collect(Collectors.toList());
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addTransaction(Transaction transaction) {

        if (transactions.contains(transaction))
            return;

        transactions.add(transaction);
        transaction.setProduct(this);
    }

    public void removeTransaction(Transaction transaction) {

        if (!transactions.contains(transaction))
            return;

        transactions.remove(transaction);
        transaction.setProduct(null);
    }

    public void addQuantity(Integer quantity) {

        this.quantity += quantity;
    }

    public void removeQuantity(Integer quantity) {

        this.quantity -= quantity;
    }

    public String generateBase64Image() {

        return Base64.encodeBase64String(image);
    }

    public Float calculateRating() {

        Collection<Rating> ratings = transactions.stream()
                .filter(t -> t.getCode().equals(Code.OUT))
                .map(Transaction::getRating)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        if (!ratings.isEmpty()) {

            return BigDecimal.valueOf(
                    ratings.stream()
                            .mapToDouble(Rating::getRate)
                            .average()
                            .orElse(Double.NaN))
                    .setScale(2, RoundingMode.HALF_UP)
                    .floatValue();
        }
        else {

            return null;
        }
    }

    @Override
    public String toString() {

        return "Product " + id + ": [name = " + name + ", company = " + company + ", price = " + price +
               ", quantity = " + quantity +
               ", description = " + description.substring(0, Math.min(description.length(), 10)) + "]";
    }
}