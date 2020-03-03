package com.liulishuo.filedownloader.connection;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface FileDownloadConnection {

    /* renamed from: a  reason: collision with root package name */
    public static final int f6413a = 0;
    public static final int b = 1;

    InputStream a() throws IOException;

    String a(String str);

    void a(String str, String str2);

    boolean a(String str, long j);

    Map<String, List<String>> b();

    Map<String, List<String>> c();

    void d() throws IOException;

    int e() throws IOException;

    void f();
}
