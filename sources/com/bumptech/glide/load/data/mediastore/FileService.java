package com.bumptech.glide.load.data.mediastore;

import java.io.File;

class FileService {
    FileService() {
    }

    public boolean a(File file) {
        return file.exists();
    }

    public long b(File file) {
        return file.length();
    }

    public File a(String str) {
        return new File(str);
    }
}
