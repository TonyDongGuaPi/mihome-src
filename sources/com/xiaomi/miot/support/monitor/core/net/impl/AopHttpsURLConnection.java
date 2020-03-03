package com.xiaomi.miot.support.monitor.core.net.impl;

import com.xiaomi.miot.support.monitor.MiotMonitorManager;
import com.xiaomi.miot.support.monitor.core.net.NetInfo;
import com.xiaomi.miot.support.monitor.core.net.i.IStreamCompleteListener;
import com.xiaomi.miot.support.monitor.utils.AndroidUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ProtocolException;
import java.net.URL;
import java.security.Permission;
import java.security.Principal;
import java.security.cert.Certificate;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocketFactory;

public class AopHttpsURLConnection extends HttpsURLConnection implements IStreamCompleteListener {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11470a = "AopHttpsURLConnection";
    private HttpsURLConnection b;
    private NetInfo c;

    private void a(Exception exc) {
    }

    public AopHttpsURLConnection(HttpsURLConnection httpsURLConnection) {
        super(httpsURLConnection.getURL());
        this.b = httpsURLConnection;
    }

    public String getCipherSuite() {
        return this.b.getCipherSuite();
    }

    public Certificate[] getLocalCertificates() {
        return this.b.getLocalCertificates();
    }

    public Certificate[] getServerCertificates() throws SSLPeerUnverifiedException {
        try {
            return this.b.getServerCertificates();
        } catch (SSLPeerUnverifiedException e) {
            a((Exception) e);
            throw e;
        }
    }

    public void addRequestProperty(String str, String str2) {
        this.b.addRequestProperty(str, str2);
    }

    public void disconnect() {
        a(this.c);
        this.b.disconnect();
    }

    public boolean usingProxy() {
        return this.b.usingProxy();
    }

    public void connect() throws IOException {
        b();
        try {
            this.b.connect();
        } catch (IOException e) {
            a((Exception) e);
            throw e;
        }
    }

    public boolean getAllowUserInteraction() {
        return this.b.getAllowUserInteraction();
    }

    public int getConnectTimeout() {
        return this.b.getConnectTimeout();
    }

    public Object getContent() throws IOException {
        b();
        try {
            Object content = this.b.getContent();
            int contentLength = this.b.getContentLength();
            if (contentLength >= 0) {
                NetInfo b2 = b();
                b2.setReceivedBytes((long) contentLength);
                a(b2);
            }
            return content;
        } catch (IOException e) {
            a((Exception) e);
            throw e;
        }
    }

    public Object getContent(Class[] clsArr) throws IOException {
        b();
        try {
            Object content = this.b.getContent(clsArr);
            a();
            return content;
        } catch (IOException e) {
            a((Exception) e);
            throw e;
        }
    }

    public String getContentEncoding() {
        b();
        String contentEncoding = this.b.getContentEncoding();
        a();
        return contentEncoding;
    }

    public int getContentLength() {
        b();
        int contentLength = this.b.getContentLength();
        a();
        return contentLength;
    }

    public String getContentType() {
        b();
        String contentType = this.b.getContentType();
        a();
        return contentType;
    }

    public long getDate() {
        b();
        long date = this.b.getDate();
        a();
        return date;
    }

    public InputStream getErrorStream() {
        b();
        try {
            return new AopInputStream(this.b.getErrorStream());
        } catch (Exception unused) {
            return this.b.getErrorStream();
        }
    }

    public long getHeaderFieldDate(String str, long j) {
        b();
        long headerFieldDate = this.b.getHeaderFieldDate(str, j);
        a();
        return headerFieldDate;
    }

    public boolean getInstanceFollowRedirects() {
        return this.b.getInstanceFollowRedirects();
    }

    public Permission getPermission() throws IOException {
        return this.b.getPermission();
    }

    public String getRequestMethod() {
        return this.b.getRequestMethod();
    }

    public int getResponseCode() throws IOException {
        b();
        try {
            int responseCode = this.b.getResponseCode();
            a();
            return responseCode;
        } catch (IOException e) {
            a((Exception) e);
            throw e;
        }
    }

    public String getResponseMessage() throws IOException {
        b();
        try {
            String responseMessage = this.b.getResponseMessage();
            a();
            return responseMessage;
        } catch (IOException e) {
            a((Exception) e);
            throw e;
        }
    }

    public void setChunkedStreamingMode(int i) {
        this.b.setChunkedStreamingMode(i);
    }

    public void setFixedLengthStreamingMode(int i) {
        this.b.setFixedLengthStreamingMode(i);
    }

    public void setInstanceFollowRedirects(boolean z) {
        this.b.setInstanceFollowRedirects(z);
    }

    public void setRequestMethod(String str) throws ProtocolException {
        try {
            this.b.setRequestMethod(str);
        } catch (ProtocolException e) {
            a((Exception) e);
            throw e;
        }
    }

    public boolean getDefaultUseCaches() {
        return this.b.getDefaultUseCaches();
    }

    public boolean getDoInput() {
        return this.b.getDoInput();
    }

    public boolean getDoOutput() {
        return this.b.getDoOutput();
    }

    public long getExpiration() {
        b();
        long expiration = this.b.getExpiration();
        a();
        return expiration;
    }

    public String getHeaderField(int i) {
        b();
        String headerField = this.b.getHeaderField(i);
        a();
        return headerField;
    }

    public String getHeaderField(String str) {
        b();
        String headerField = this.b.getHeaderField(str);
        a();
        return headerField;
    }

    public int getHeaderFieldInt(String str, int i) {
        b();
        int headerFieldInt = this.b.getHeaderFieldInt(str, i);
        a();
        return headerFieldInt;
    }

    public String getHeaderFieldKey(int i) {
        b();
        String headerFieldKey = this.b.getHeaderFieldKey(i);
        a();
        return headerFieldKey;
    }

    public Map<String, List<String>> getHeaderFields() {
        b();
        Map<String, List<String>> headerFields = this.b.getHeaderFields();
        a();
        return headerFields;
    }

    public long getIfModifiedSince() {
        b();
        long ifModifiedSince = this.b.getIfModifiedSince();
        a();
        return ifModifiedSince;
    }

    public InputStream getInputStream() throws IOException {
        NetInfo b2 = b();
        try {
            AopInputStream aopInputStream = new AopInputStream(this.b.getInputStream());
            a(b2, this.b);
            aopInputStream.a(this);
            return aopInputStream;
        } catch (IOException e) {
            a((Exception) e);
            throw e;
        }
    }

    public long getLastModified() {
        b();
        long lastModified = this.b.getLastModified();
        a();
        return lastModified;
    }

    public OutputStream getOutputStream() throws IOException {
        b();
        try {
            AopOutputStream aopOutputStream = new AopOutputStream(this.b.getOutputStream());
            aopOutputStream.a(this);
            return aopOutputStream;
        } catch (IOException e) {
            a((Exception) e);
            throw e;
        }
    }

    public int getReadTimeout() {
        return this.b.getReadTimeout();
    }

    public Map<String, List<String>> getRequestProperties() {
        return this.b.getRequestProperties();
    }

    public String getRequestProperty(String str) {
        return this.b.getRequestProperty(str);
    }

    public URL getURL() {
        return this.b.getURL();
    }

    public boolean getUseCaches() {
        return this.b.getUseCaches();
    }

    public void setAllowUserInteraction(boolean z) {
        this.b.setAllowUserInteraction(z);
    }

    public void setConnectTimeout(int i) {
        this.b.setConnectTimeout(i);
    }

    public void setDefaultUseCaches(boolean z) {
        this.b.setDefaultUseCaches(z);
    }

    public void setDoInput(boolean z) {
        this.b.setDoInput(z);
    }

    public void setDoOutput(boolean z) {
        this.b.setDoOutput(z);
    }

    public void setIfModifiedSince(long j) {
        this.b.setIfModifiedSince(j);
    }

    public void setReadTimeout(int i) {
        this.b.setReadTimeout(i);
    }

    public void setRequestProperty(String str, String str2) {
        this.b.setRequestProperty(str, str2);
    }

    public void setUseCaches(boolean z) {
        this.b.setUseCaches(z);
    }

    public String toString() {
        if (this.b == null) {
            return "this connection object is null";
        }
        return this.b.toString();
    }

    public Principal getPeerPrincipal() throws SSLPeerUnverifiedException {
        return this.b.getPeerPrincipal();
    }

    public Principal getLocalPrincipal() {
        return this.b.getLocalPrincipal();
    }

    public void setHostnameVerifier(HostnameVerifier hostnameVerifier) {
        this.b.setHostnameVerifier(hostnameVerifier);
    }

    public HostnameVerifier getHostnameVerifier() {
        return this.b.getHostnameVerifier();
    }

    public void setSSLSocketFactory(SSLSocketFactory sSLSocketFactory) {
        this.b.setSSLSocketFactory(sSLSocketFactory);
    }

    public SSLSocketFactory getSSLSocketFactory() {
        return this.b.getSSLSocketFactory();
    }

    private void a() {
        a(b(), this.b);
    }

    private NetInfo b() {
        if (this.c == null) {
            this.c = new NetInfo();
            this.c.setURL(this.b.getURL().toString());
            this.c.setStartTime(System.currentTimeMillis());
            this.c.setBackType(AndroidUtils.a(MiotMonitorManager.a().h()) ? 1 : 2);
        }
        return this.c;
    }

    private void a(NetInfo netInfo) {
        netInfo.end();
    }

    private void a(NetInfo netInfo, HttpsURLConnection httpsURLConnection) {
        int contentLength = httpsURLConnection.getContentLength();
        if (contentLength >= 0) {
            netInfo.setReceivedBytes((long) contentLength);
        }
        try {
            netInfo.setStatusCode(httpsURLConnection.getResponseCode());
        } catch (IOException | NullPointerException unused) {
        }
    }

    public void a(long j) {
        this.c.setReceivedBytes(j);
        if (this.c.startTime > 0) {
            this.c.setCostTime(System.currentTimeMillis() - this.c.startTime);
        }
        this.c.end();
    }

    public void c(long j) {
        this.c.setReceivedBytes(j);
        if (this.c.startTime > 0) {
            this.c.setCostTime(System.currentTimeMillis() - this.c.startTime);
        }
        this.c.end();
    }

    public void b(long j) {
        this.c.setSendBytes(j);
        if (this.c.startTime > 0) {
            this.c.setCostTime(System.currentTimeMillis() - this.c.startTime);
        }
        this.c.end();
    }

    public void d(long j) {
        this.c.setSendBytes(j);
        if (this.c.startTime > 0) {
            this.c.setCostTime(System.currentTimeMillis() - this.c.startTime);
        }
        this.c.end();
    }
}
