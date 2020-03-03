package com.tencent.smtt.sdk;

import android.os.Build;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import java.io.File;
import java.io.FileFilter;

final class ao implements FileFilter {
    ao() {
    }

    public boolean accept(File file) {
        String name = file.getName();
        if (name != null && !name.endsWith(".jar_is_first_load_dex_flag_file")) {
            return Build.VERSION.SDK_INT < 21 || !name.endsWith(ShareConstants.w);
        }
        return false;
    }
}
