package com.chimpim.toolbox.io;

import java.io.*;
import java.nio.charset.Charset;


public class TextFileWriter implements Closeable {
    private static final Charset DEFAULT_CHARSET = Charset.forName("utf-8");
    private BufferedWriter writer;

    public TextFileWriter(String fileName) throws IOException {
        this(fileName, DEFAULT_CHARSET);
    }

    public TextFileWriter(String fileName, Charset charset) throws IOException {
        this(new File(fileName), charset, false);
    }

    public TextFileWriter(String fileName, Charset charset, boolean append) throws IOException {
        this(new File(fileName), charset, append);
    }

    public TextFileWriter(File file) throws IOException {
        this(file, DEFAULT_CHARSET, false);
    }

    public TextFileWriter(File file, Charset charset) throws IOException {
        this(file, charset, false);
    }

    public TextFileWriter(File file, boolean append) throws IOException {
        this(file, DEFAULT_CHARSET, append);
    }

    public TextFileWriter(File file, Charset charset, boolean append) throws IOException {
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, append), charset));
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
