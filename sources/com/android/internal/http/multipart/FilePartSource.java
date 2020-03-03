package com.android.internal.http.multipart;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class FilePartSource implements PartSource {
    public FilePartSource(File file) throws FileNotFoundException {
        throw new RuntimeException("Stub!");
    }

    public FilePartSource(String str, File file) throws FileNotFoundException {
        throw new RuntimeException("Stub!");
    }

    public long a() {
        throw new RuntimeException("Stub!");
    }

    public String b() {
        throw new RuntimeException("Stub!");
    }

    public InputStream c() throws IOException {
        throw new RuntimeException("Stub!");
    }
}
