package com.pierre.oceanblu.model.form;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RatingForm {

    private Type type;
    private Long transactionId;
    private Integer rate;
    private boolean reviewLeft;
    private String reviewTitle;
    private String reviewBody;

    public RatingForm(Long transactionId) {

        this.transactionId = transactionId;
    }

    public enum Type {

        ONLY_REVIEW, RATE_AND_REVIEW, TEST_CASE_VALUE
    }
}
