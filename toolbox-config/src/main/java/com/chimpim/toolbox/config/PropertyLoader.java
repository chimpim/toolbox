package com.chimpim.toolbox.config;


import com.chimpim.toolbox.config.impl.*;
import org.jetbrains.annotations.NotNull;

import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertyLoader {
    private Map<Type, TypeConverter> mTypeConverters;

    private static class DefaultHolder {
        private static PropertyLoader instance = new PropertyLoader();
    }

    public static PropertyLoader getDefault() {
        return DefaultHolder.instance;
    }

    public PropertyLoader() {
        mTypeConverters = new HashMap<>(16);
        // boolean
        BooleanConverter booleanConverter = new BooleanConverter();
        mTypeConverters.put(boolean.class, booleanConverter);
        mTypeConverters.put(Boolean.class, booleanConverter);
        // short
        ShortConverter shortConverter = new ShortConverter();
        mTypeConverters.put(short.class, shortConverter);
        mTypeConverters.put(Short.class, shortConverter);
        // int
        IntegerConverter integerConverter = new IntegerConverter();
        mTypeConverters.put(int.class, integerConverter);
        mTypeConverters.put(Integer.class, integerConverter);
        // long
        LongConverter longConverter = new LongConverter();
        mTypeConverters.put(long.class, longConverter);
        mTypeConverters.put(Long.class, longConverter);
        // float
        FloatConverter floatConverter = new FloatConverter();
        mTypeConverters.put(float.class, floatConverter);
        mTypeConverters.put(Float.class, floatConverter);
        // double
        DoubleConverter doubleConverter = new DoubleConverter();
        mTypeConverters.put(double.class, doubleConverter);
        mTypeConverters.put(Double.class, doubleConverter);
        // String
        StringConverter stringConverter = new StringConverter();
        mTypeConverters.put(String.class, stringConverter);
    }

    public <T> PropertyLoader registerTypeConverter(@NotNull Class<T> type, @NotNull TypeConverter<T> converter) {
        mTypeConverters.put(type, converter);
        return this;
    }

    public <T> PropertyLoader removeTypeConverter(@NotNull Class<T> type) {
        mTypeConverters.remove(type);
        return this;
    }

    public <T> T load(@NotNull Class<T> clazz, @NotNull String file) throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream(file));
        T instance = clazz.newInstance();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            Property property = field.getAnnotation(Property.class);
            if (property == null) continue;
            String name = property.name();
            Type type = field.getType();
            TypeConverter converter = mTypeConverters.get(type);
            if (converter == null) continue;
            String value = properties.getProperty(name, null);
            try {
                Object newValue = converter.convert(value);
                field.setAccessible(true);
                field.set(instance, newValue);
            } catch (IllegalArgumentException e) {
                System.err.println(e.toString());
            }
        }
        return instance;
    }
}
