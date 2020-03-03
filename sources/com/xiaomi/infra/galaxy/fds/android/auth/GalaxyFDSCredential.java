package com.xiaomi.infra.galaxy.fds.android.auth;

import com.xiaomi.infra.galaxy.fds.android.exception.GalaxyFDSClientException;
import org.apache.http.client.methods.HttpRequestBase;

public interface GalaxyFDSCredential {
    String a(String str);

    void a(HttpRequestBase httpRequestBase) throws GalaxyFDSClientException;
}
