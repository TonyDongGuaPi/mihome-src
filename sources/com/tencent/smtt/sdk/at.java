package com.tencent.smtt.sdk;

import com.tencent.tinker.loader.shareutil.ShareConstants;
import java.io.File;
import java.io.FileFilter;

class at implements FileFilter {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ am f9147a;

    at(am amVar) {
        this.f9147a = amVar;
    }

    public boolean accept(File file) {
        return !file.getName().endsWith(ShareConstants.w) && !file.getName().endsWith(".jar_is_first_load_dex_flag_file");
    }
}
