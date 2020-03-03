package org.xutils.http.body;

import java.io.IOException;
import java.io.OutputStream;

public interface RequestBody {
    String a();

    void a(OutputStream outputStream) throws IOException;

    void a(String str);

    long b();
}
