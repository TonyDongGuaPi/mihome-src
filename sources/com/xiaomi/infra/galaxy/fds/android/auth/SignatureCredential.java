package com.xiaomi.infra.galaxy.fds.android.auth;

import com.google.common.collect.LinkedListMultimap;
import com.xiaomi.infra.galaxy.fds.android.exception.GalaxyFDSClientException;
import com.xiaomi.infra.galaxy.fds.auth.signature.SignAlgorithm;
import com.xiaomi.infra.galaxy.fds.auth.signature.Signer;
import com.xiaomi.infra.galaxy.fds.model.HttpMethod;
import java.net.URI;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import org.apache.http.Header;
import org.apache.http.client.methods.HttpRequestBase;

public class SignatureCredential implements GalaxyFDSCredential {

    /* renamed from: a  reason: collision with root package name */
    private static final ThreadLocal<SimpleDateFormat> f10136a = new ThreadLocal<SimpleDateFormat>() {
        /* access modifiers changed from: protected */
        /* renamed from: a */
        public SimpleDateFormat initialValue() {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            return simpleDateFormat;
        }
    };
    private final String b;
    private final String c;

    public String a(String str) {
        return str;
    }

    public SignatureCredential(String str, String str2) {
        this.b = str;
        this.c = str2;
    }

    public void a(HttpRequestBase httpRequestBase) throws GalaxyFDSClientException {
        httpRequestBase.setHeader("date", f10136a.get().format(new Date()));
        try {
            URI uri = httpRequestBase.getURI();
            LinkedListMultimap create = LinkedListMultimap.create();
            for (Header header : httpRequestBase.getAllHeaders()) {
                create.put(header.getName(), header.getValue());
            }
            httpRequestBase.setHeader("Authorization", Signer.a(HttpMethod.valueOf(httpRequestBase.getMethod()), uri, create, this.b, this.c, SignAlgorithm.HmacSHA1));
        } catch (NoSuchAlgorithmException e) {
            throw new GalaxyFDSClientException("Fail to get signature for request:" + httpRequestBase, e);
        } catch (InvalidKeyException e2) {
            throw new GalaxyFDSClientException("Fail to get signature for request:" + httpRequestBase, e2);
        }
    }
}
