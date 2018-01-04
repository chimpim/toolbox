package com.chimpim.toolbox.io;

import java.io.*;

public class TextFileReader implements Closeable {
    private BufferedReader reader;

    public TextFileReader(String fileName) throws IOException {
        this(fileName, "utf-8");
    }

    public TextFileReader(String fileName, String encoding) throws IOException {
        this(new File(fileName), encoding);
    }

    public TextFileReader(File file, String encoding) throws IOException {
        reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding));
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