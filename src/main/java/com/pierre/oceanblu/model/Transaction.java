package com.pierre.oceanblu.model;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rating_id", referencedColumnName = "id")
    @Nullable
    private Rating rating;
    @Builder.Default
    @Column(columnDefinition = "DATETIME")
    private final LocalDateTime date = LocalDateTime.now();
    @Enumerated(EnumType.STRING)
    @Column(length = 8)
    private Code code;
    private Integer items;
    @Column(name = "current_items")
    private Integer currentItems;
    @Column(name = "current_value")
    private Float currentValue;

    public String getOnlyDate() {

        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public String getFormattedDateTime() {

        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss"));
    }

    public void setProduct(Product product) {

        Product oldProduct = this.product;

        if (Objects.equals(product, oldProduct))
            return;

        this.product = product;

        if (oldProduct!=null)
            oldProduct.removeTransaction(this);

        if (product!=null)
            product.addTransaction(this);
    }

    public void setUser(User user) {

        User oldUser = this.user;

        if (Objects.equals(user, oldUser))
            return;

        this.user = user;

        if (oldUser!=null)
            oldUser.removeTransaction(this);

        if (user!=null)
            user.addTransaction(this);
    }

    public void setRating(Rating rating) {

        Rating oldRating = this.rating;

        if (Objects.equals(rating, oldRating))
            return;

        this.rating = rating;

        if (oldRating!=null)
            oldRating.setTransaction(this);

        if (rating!=null)
            rating.setTransaction(this);
    }

    public void setCode(Code code) {

        this.code = code;
    }

    public void setItems(Integer items) {

        this.items = items;
    }

    public void setCurrentItems(Integer currentItems) {

        this.currentItems = currentItems;
    }

    public void setCurrentValue(Float currentValue) {

        this.currentValue = currentValue;
    }

    @Override
    public String toString() {

        return "Stock " + id + ": [product = " + product.getName() + ", price = " + product.getPrice() +
               ", user = " + user.getName() + ", date = " + date + ", code = " + code + ", items = " + items +
               ", current items = " + currentItems + ", current value = " + currentValue + "]";
    }

    public enum Code {

        IN, OUT
    }
}
