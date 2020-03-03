package com.tencent.smtt.sdk;

import java.io.File;
import java.io.FileFilter;

class av implements FileFilter {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ am f9149a;

    av(am amVar) {
        this.f9149a = amVar;
    }

    public boolean accept(File file) {
        return file.getName().endsWith(".jar");
    }
}
