package com.chimpim.toolbox.io;

import java.io.*;
import java.nio.charset.Charset;

public class TextFileReader implements Closeable {
    private static final Charset DEFAULT_CHARSET = Charset.forName("utf-8");

    private BufferedReader reader;

    public TextFileReader(String fileName) throws IOException {
        this(fileName, DEFAULT_CHARSET);
    }

    public TextFileReader(String fileName, Charset charset) throws IOException {
        this(new File(fileName), charset);
    }

    public TextFileReader(File file, Charset charset) throws IOException {
        reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
    }


    public String readAll() throws IOException {
        char[] buf = new char[1024];
        int len;
        StringBuilder sb = new StringBuilder();
        while ((len = reader.read(buf)) != -1) {
            sb.append(buf, 0, len);
        }
        return sb.toString();
    }


    public String readLine() throws IOException {
        return reader.readLine();
    }


    @Override
    public void close() throws IOException {
        if (reader != null) {
            reader.close();
        }
    }
}