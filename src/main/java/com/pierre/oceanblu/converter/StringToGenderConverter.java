package com.pierre.oceanblu.converter;

import org.springframework.core.convert.converter.Converter;

import static com.pierre.oceanblu.model.User.*;

public class StringToGenderConverter implements Converter<String, Gender> {

    @Override
    public Gender convert(String value) {

        return Gender.valueOf(value.toUpperCase());
    }
}
