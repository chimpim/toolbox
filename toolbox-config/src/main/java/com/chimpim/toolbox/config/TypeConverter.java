package com.chimpim.toolbox.config;

public interface TypeConverter<T> {
    T convert(String value) throws IllegalArgumentException;
}
