package com.xiaomi.infra.galaxy.fds.android.model;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

public class FDSObject implements Closeable {

    /* renamed from: a  reason: collision with root package name */
    private final String f10137a;
    private final String b;
    private ObjectMetadata c;
    private InputStream d;

    public FDSObject(String str, String str2) {
        this.b = str;
        this.f10137a = str2;
    }

    public String a() {
        return this.f10137a;
    }

    public String b() {
        return this.b;
    }

    public ObjectMetadata c() {
        return this.c;
    }

    public void a(ObjectMetadata objectMetadata) {
        this.c = objectMetadata;
    }

    public InputStream d() {
        return this.d;
    }

    public void a(InputStream inputStream) {
        this.d = inputStream;
    }

    public void close() {
        if (this.d != null) {
            try {
                this.d.close();
            } catch (IOException unused) {
            }
        }
    }
}
