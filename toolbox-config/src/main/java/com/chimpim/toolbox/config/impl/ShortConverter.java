package com.chimpim.toolbox.config.impl;

import com.chimpim.toolbox.config.TypeConverter;

public class ShortConverter implements TypeConverter<Short> {
    @Override
    public Short convert(String value) throws IllegalArgumentException {
        return Short.parseShort(value);
    }
}
