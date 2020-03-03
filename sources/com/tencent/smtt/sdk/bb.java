package com.tencent.smtt.sdk;

import com.tencent.tinker.loader.shareutil.ShareConstants;
import java.io.File;
import java.io.FileFilter;

final class bb implements FileFilter {
    bb() {
    }

    public boolean accept(File file) {
        return !file.getName().endsWith(ShareConstants.w);
    }
}
