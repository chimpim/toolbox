package com.chimpim.toolbox.config.impl;

import com.chimpim.toolbox.config.TypeConverter;

public class LongConverter implements TypeConverter<Long> {
    @Override
    public Long convert(String value) throws IllegalArgumentException {
        return Long.parseLong(value);
    }
}
