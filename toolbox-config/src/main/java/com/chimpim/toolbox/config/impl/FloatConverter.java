package com.chimpim.toolbox.config.impl;

import com.chimpim.toolbox.config.TypeConverter;

public class FloatConverter implements TypeConverter<Float> {
    @Override
    public Float convert(String value) throws IllegalArgumentException {
        return Float.parseFloat(value);
    }
}
