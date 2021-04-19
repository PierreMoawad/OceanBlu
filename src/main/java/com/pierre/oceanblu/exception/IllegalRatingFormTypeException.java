package com.pierre.oceanblu.exception;

import static com.pierre.oceanblu.model.form.RatingForm.*;

public class IllegalRatingFormTypeException extends IllegalArgumentException {

    public IllegalRatingFormTypeException(Type type) {

        super("Parameter 'type' has an illegal value: " + type.toString());
    }
}
