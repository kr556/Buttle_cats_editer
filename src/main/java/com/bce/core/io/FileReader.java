package com.bce.core.io;

import java.io.File;
import java.io.InputStream;

public class FileReader {
    public FileReader() {}

    public InputStream read(String fileName) {
        return ImageReader.class.getClassLoader().getResourceAsStream(fileName);
    }
}
