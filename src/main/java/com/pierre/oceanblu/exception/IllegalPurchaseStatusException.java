package com.pierre.oceanblu.exception;

import static com.pierre.oceanblu.model.form.PurchaseProductsForm.*;

public class IllegalPurchaseStatusException extends IllegalArgumentException {

    public IllegalPurchaseStatusException(Status status) {

        super("Parameter 'status' has an illegal value: " + status.toString());
    }
}
