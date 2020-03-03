package com.xiaomi.phonenum.http;

import java.io.IOException;

public interface HttpClient {
    Response a(Request request) throws IOException;
}
