package com.tencent.smtt.sdk;

import java.io.File;
import java.io.FileFilter;

class as implements FileFilter {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ aq f9146a;

    as(aq aqVar) {
        this.f9146a = aqVar;
    }

    public boolean accept(File file) {
        return file.getName().endsWith("tbs.conf");
    }
}
