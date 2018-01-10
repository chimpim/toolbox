package com.chimpim.toolbox.config.impl;

import com.chimpim.toolbox.config.TypeConverter;

public class StringConverter implements TypeConverter<String> {
    @Override
    public String convert(String value) throws IllegalArgumentException {
        return value;
    }
}
