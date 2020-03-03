package com.xiaomi.infra.galaxy.fds.android.auth;

import com.alipay.sdk.sys.a;
import com.google.gson.Gson;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.infra.galaxy.fds.android.exception.GalaxyFDSClientException;
import com.xiaomi.infra.galaxy.fds.android.model.StorageAccessToken;
import com.xiaomi.youpin.login.api.stat.LoginType;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import kotlin.text.Typography;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;

public class OAuthCredential implements GalaxyFDSCredential {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10134a = "STORAGE_ACCESS_TOKEN";
    private static final String b = "APP_ID";
    private static final String c = "OAUTH_APPID";
    private static final String d = "OAUTH_ACCESS_TOKEN";
    private static final String e = "OAUTH_PROVIDER";
    private static final String f = "OAUTH_MAC_KEY";
    private static final String g = "OAUTH_MAC_ALGORITHM";
    private final String h = LoginType.k;
    private final String i;
    private final StorageAccessToken j;

    public OAuthCredential(String str, String str2, String str3, String str4, String str5, String str6, String str7) throws GalaxyFDSClientException {
        this.i = str2;
        this.j = a(str, str2, str3, str4, str5, str6, str7);
    }

    private StorageAccessToken a(String str, String str2, String str3, String str4, String str5, String str6, String str7) throws GalaxyFDSClientException {
        try {
            DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(str + "/?" + f10134a + a.b + b + "=" + str2 + a.b + c + "=" + str3 + a.b + d + "=" + str4 + a.b + e + "=" + str5 + a.b + g + "=" + str7 + a.b + f + "=" + str6);
            httpGet.setHeader("Authorization", LoginType.k);
            HttpResponse execute = defaultHttpClient.execute(httpGet);
            if (execute.getStatusLine().getStatusCode() == 200) {
                InputStream content = execute.getEntity().getContent();
                StorageAccessToken storageAccessToken = (StorageAccessToken) new Gson().fromJson((Reader) new InputStreamReader(content), StorageAccessToken.class);
                content.close();
                return storageAccessToken;
            }
            throw new GalaxyFDSClientException("Failed to get the storage access token from FDS server. URI:" + httpGet.getURI().toString() + ".Reason:" + execute.getStatusLine().toString());
        } catch (IOException e2) {
            throw new GalaxyFDSClientException("Failed to get the storage access token", e2);
        }
    }

    public void a(HttpRequestBase httpRequestBase) {
        httpRequestBase.setHeader("Authorization", LoginType.k);
    }

    public String a(String str) {
        StringBuilder sb = new StringBuilder(str);
        if (str.indexOf(63) == -1) {
            sb.append(Operators.CONDITION_IF);
        } else {
            sb.append(Typography.c);
        }
        sb.append(b);
        sb.append('=');
        sb.append(this.i);
        sb.append(Typography.c);
        sb.append(f10134a);
        sb.append('=');
        sb.append(this.j.a());
        return sb.toString();
    }
}
