package com.chimpim.toolbox.config.impl;

import com.chimpim.toolbox.config.TypeConverter;

public class BooleanConverter implements TypeConverter<Boolean> {
    @Override
    public Boolean convert(String value) throws IllegalArgumentException {
        return Boolean.parseBoolean(value);
    }
}
