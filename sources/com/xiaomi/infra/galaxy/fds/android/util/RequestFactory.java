package com.xiaomi.infra.galaxy.fds.android.util;

import com.xiaomi.infra.galaxy.fds.android.auth.GalaxyFDSCredential;
import com.xiaomi.infra.galaxy.fds.android.exception.GalaxyFDSClientException;
import com.xiaomi.infra.galaxy.fds.model.HttpMethod;
import java.util.Date;
import java.util.Map;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;

public class RequestFactory {
    public static HttpUriRequest a(String str, GalaxyFDSCredential galaxyFDSCredential, HttpMethod httpMethod, Map<String, String> map) throws GalaxyFDSClientException {
        HttpRequestBase httpRequestBase;
        String a2 = galaxyFDSCredential.a(str);
        switch (httpMethod) {
            case GET:
                httpRequestBase = new HttpGet(a2);
                break;
            case PUT:
                httpRequestBase = new HttpPut(a2);
                break;
            case POST:
                httpRequestBase = new HttpPost(a2);
                break;
            case DELETE:
                httpRequestBase = new HttpDelete(a2);
                break;
            case HEAD:
                httpRequestBase = new HttpHead(a2);
                break;
            default:
                httpRequestBase = null;
                break;
        }
        if (httpRequestBase != null) {
            if (map != null) {
                map.remove("Content-Length");
                map.remove("Content-Length".toLowerCase());
                for (Map.Entry next : map.entrySet()) {
                    httpRequestBase.addHeader((String) next.getKey(), (String) next.getValue());
                }
            }
            httpRequestBase.addHeader("Date", Util.a(new Date()));
            galaxyFDSCredential.a(httpRequestBase);
        }
        return httpRequestBase;
    }
}
