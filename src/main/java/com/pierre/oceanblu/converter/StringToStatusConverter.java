package com.pierre.oceanblu.converter;

import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;

import static com.pierre.oceanblu.model.form.PurchaseProductsForm.*;

public class StringToStatusConverter implements Converter<String, Status> {

    @Override
    public Status convert(String value) {

        List<Integer> capitalIndices = getCapitalIndices(value);

        if (!capitalIndices.isEmpty()) {

            for (Integer index : capitalIndices) {

                value = value.substring(0, index) + "_" + value.substring(index);
            }
        }
        return Status.valueOf(value.toUpperCase());
    }

    private List<Integer> getCapitalIndices(String value) {

        List<Integer> capitalIndices = new ArrayList<>();

        for (int i = 0; i < value.length(); i++) {

            if (Character.isUpperCase(value.charAt(i))) {

                capitalIndices.add(i);
            }
        }
        return capitalIndices;
    }
}