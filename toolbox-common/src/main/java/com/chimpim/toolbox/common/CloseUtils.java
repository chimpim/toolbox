package com.chimpim.toolbox.common;

import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.Method;

public class CloseUtils {

    public static void close(Closeable... closeables) {
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void close(AutoCloseable... closeables) {
        for (AutoCloseable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void close(Object... closeables) {
        for (Object closeable : closeables) {
            if (closeable != null) {
                try {
                    Method close = closeable.getClass().getMethod("close");
                    close.invoke(closeable);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
