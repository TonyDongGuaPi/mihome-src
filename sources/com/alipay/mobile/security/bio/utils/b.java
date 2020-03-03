package com.alipay.mobile.security.bio.utils;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Pattern;

class b implements FileFilter {
    b() {
    }

    public boolean accept(File file) {
        return Pattern.matches("cpu[0-9]+", file.getName());
    }
}
