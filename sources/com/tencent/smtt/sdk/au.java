package com.tencent.smtt.sdk;

import java.io.File;
import java.io.FileFilter;

class au implements FileFilter {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ am f9148a;

    au(am amVar) {
        this.f9148a = amVar;
    }

    public boolean accept(File file) {
        return file.getName().endsWith(".jar");
    }
}
