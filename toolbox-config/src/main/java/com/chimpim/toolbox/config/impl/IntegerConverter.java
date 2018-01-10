package com.chimpim.toolbox.config.impl;

import com.chimpim.toolbox.config.TypeConverter;

public class IntegerConverter implements TypeConverter<Integer> {
    @Override
    public Integer convert(String value) throws IllegalArgumentException {
        return Integer.parseInt(value);
    }
}
