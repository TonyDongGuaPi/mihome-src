package com.android.internal.http.multipart;

import java.io.IOException;
import java.io.InputStream;

public interface PartSource {
    long a();

    String b();

    InputStream c() throws IOException;
}
