package com.tencent.smtt.sdk;

import com.tencent.tinker.loader.shareutil.ShareConstants;
import java.io.File;
import java.io.FileFilter;

class ar implements FileFilter {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ aq f9145a;

    ar(aq aqVar) {
        this.f9145a = aqVar;
    }

    public boolean accept(File file) {
        return file.getName().endsWith(ShareConstants.w);
    }
}
