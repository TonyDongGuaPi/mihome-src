package com.xiaomi.smarthome.library.apache.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface HttpEntity {
    void a(OutputStream outputStream) throws IOException;

    boolean a();

    boolean b();

    long c();

    Header d();

    Header e();

    InputStream f() throws IOException, IllegalStateException;

    boolean g();

    void h() throws IOException;
}
