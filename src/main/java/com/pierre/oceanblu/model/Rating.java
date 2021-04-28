package com.pierre.oceanblu.model;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(mappedBy = "rating", fetch = FetchType.LAZY)
    @Nullable
    private Transaction transaction;
    private Integer rate;
    @Column(name = "review_left")
    private boolean reviewLeft;
    @Column(name = "review_title")
    @Nullable
    private String reviewTitle;
    @Column(name = "review_body")
    @Nullable
    private String reviewBody;

    public void setTransaction(Transaction transaction) {

        Transaction oldTransaction = this.transaction;

        if (Objects.equals(transaction, oldTransaction))
            return;

        this.transaction = transaction;

        if (oldTransaction != null)
            oldTransaction.setRating(null);

        if (transaction != null)
            transaction.setRating(this);
    }

    public void setRate(Integer rate) {

        this.rate = rate;
    }

    public void setReviewLeft(boolean reviewLeft) {

        this.reviewLeft = reviewLeft;
    }

    public void setReviewTitle(String title) {

        this.reviewTitle = title;
    }

    public void setReviewBody(String body) {

        this.reviewBody = body;
    }

    @Override
    public String toString() {

        return "Rating " + id + ": [rate = " + rate +
               ", reviewLeft = " + reviewLeft + (reviewLeft ?
                ", review: [title = " + reviewTitle + ", body = " +
                Objects.requireNonNull(reviewBody).substring(0, Math.min(reviewBody.length(), 10)) + "]]" : "]");
    }
}
