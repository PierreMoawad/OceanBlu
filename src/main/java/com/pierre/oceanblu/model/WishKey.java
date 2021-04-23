package com.pierre.oceanblu.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class WishKey implements Serializable {

    @Column(name = "user_id")
    private Long userId;
    @Column(name = "product_id")
    private Long productId;
}
