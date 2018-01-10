package com.chimpim.toolbox.config.impl;

import com.chimpim.toolbox.config.TypeConverter;

public class DoubleConverter implements TypeConverter<Double> {
    @Override
    public Double convert(String value) throws IllegalArgumentException {
        return Double.parseDouble(value);
    }
}
