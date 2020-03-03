package com.tencent.smtt.utils;

import com.tencent.smtt.utils.j;
import java.io.File;

final class l implements j.a {
    l() {
    }

    public boolean a(File file, File file2) {
        return file.length() == file2.length() && file.lastModified() == file2.lastModified();
    }
}
