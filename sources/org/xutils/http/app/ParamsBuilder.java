package org.xutils.http.app;

import javax.net.ssl.SSLSocketFactory;
import org.xutils.http.RequestParams;
import org.xutils.http.annotation.HttpRequest;

public interface ParamsBuilder {
    String a(RequestParams requestParams, HttpRequest httpRequest);

    String a(RequestParams requestParams, String[] strArr);

    SSLSocketFactory a();

    void a(RequestParams requestParams);

    void b(RequestParams requestParams, String[] strArr);
}
