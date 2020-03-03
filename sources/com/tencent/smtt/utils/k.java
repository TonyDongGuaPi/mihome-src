package com.tencent.smtt.utils;

import com.tencent.smtt.utils.j;
import java.io.InputStream;
import java.util.zip.ZipEntry;

final class k implements j.b {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ String f9210a;

    k(String str) {
        this.f9210a = str;
    }

    public boolean a(InputStream inputStream, ZipEntry zipEntry, String str) {
        try {
            return j.b(inputStream, zipEntry, this.f9210a, str);
        } catch (Exception e) {
            throw new Exception("copyFileIfChanged Exception", e);
        }
    }
}
