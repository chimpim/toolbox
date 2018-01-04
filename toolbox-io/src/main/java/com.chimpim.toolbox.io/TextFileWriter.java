package com.chimpim.toolbox.io;

import java.io.*;


public class TextFileWriter implements Closeable {
    private static final String DEFAULT_ENCODING = "utf-8";
    private BufferedWriter writer;

    public TextFileWriter(String fileName) throws IOException {
        this(fileName, DEFAULT_ENCODING);
    }

    public TextFileWriter(String fileName, String encoding) throws IOException {
        this(new File(fileName), encoding, false);
    }

    public TextFileWriter(String fileName, String encoding, boolean append) throws IOException {
        this(new File(fileName), encoding, append);
    }

    public TextFileWriter(File file) throws IOException {
        this(file, DEFAULT_ENCODING, false);
    }

    public TextFileWriter(File file, String encoding) throws IOException {
        this(file, encoding, false);
    }

    public TextFileWriter(File file, boolean append) throws IOException {
        this(file, DEFAULT_ENCODING, append);
    }

    public TextFileWriter(File file, String encoding, boolean append) throws IOException {
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, append), encoding));
    }

    public void write(String str) throws IOException {
        writer.write(str);
    }


    public void writeLine(String str) throws IOException {
        writer.write(str);
        writer.newLine();
    }

    public void flush() throws IOException {
        writer.flush();
    }

    public void append(CharSequence csq) throws IOException {
        writer.append(csq);
    }

    @Override
    public void close() throws IOException {
        if (writer != null) {
            writer.close();
        }
    }
}
