package com.chimpim.toolbox.config;

import org.junit.Test;

import java.net.URL;

import static org.junit.Assert.*;

public class PropertyLoaderTest {
    @Test
    public void load() throws Exception {
        URL resource = this.getClass().getResource("/config.properties");

        Config config = PropertyLoader.getDefault().load(Config.class, resource.getFile());
        System.out.println(config);
    }

}