package com.chimpim.toolbox.config;

public class Config {
    @Property(name = "config.boolean")
    private boolean _boolean;
    @Property(name = "config.int")
    private int _int;
    @Property(name = "config.integer")
    private Integer _integer;

    @Override
    public String toString() {
        return "Config{" +
                "_boolean=" + _boolean +
                ", _int=" + _int +
                ", _integer=" + _integer +
                '}';
    }
}
