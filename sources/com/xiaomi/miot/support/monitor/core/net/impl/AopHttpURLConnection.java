package com.xiaomi.miot.support.monitor.core.net.impl;

import com.xiaomi.miot.support.monitor.MiotMonitorManager;
import com.xiaomi.miot.support.monitor.core.net.NetInfo;
import com.xiaomi.miot.support.monitor.core.net.i.IStreamCompleteListener;
import com.xiaomi.miot.support.monitor.utils.AndroidUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.security.Permission;
import java.util.List;
import java.util.Map;

public class AopHttpURLConnection extends HttpURLConnection implements IStreamCompleteListener {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11469a = "AopHttpURLConnection";
    private final HttpURLConnection b;
    private NetInfo c;

    private void a(Exception exc) {
    }

    public AopHttpURLConnection(HttpURLConnection httpURLConnection) {
        super(httpURLConnection.getURL());
        this.b = httpURLConnection;
    }

    private void a(NetInfo netInfo, HttpURLConnection httpURLConnection) {
        int contentLength = httpURLConnection.getContentLength();
        if (contentLength >= 0) {
            netInfo.setReceivedBytes((long) contentLength);
        }
        try {
            netInfo.setStatusCode(httpURLConnection.getResponseCode());
        } catch (IOException | NullPointerException unused) {
        }
    }

    private NetInfo a() {
        if (this.c == null) {
            this.c = new NetInfo();
            this.c.setURL(this.b.getURL().toString());
            this.c.setBackType(AndroidUtils.a(MiotMonitorManager.a().h()) ? 1 : 2);
        }
        return this.c;
    }

    private void a(NetInfo netInfo) {
        netInfo.end();
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
        a();
        try {
            this.b.connect();
        } catch (IOException e) {
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
        a();
        try {
            Object content = this.b.getContent();
            int contentLength = this.b.getContentLength();
            if (contentLength >= 0) {
                NetInfo a2 = a();
                a2.setReceivedBytes((long) contentLength);
                a(a2);
            }
            return content;
        } catch (IOException e) {
            throw e;
        }
    }

    public Object getContent(Class[] clsArr) throws IOException {
        a();
        try {
            Object content = this.b.getContent(clsArr);
            a(a(), this.b);
            return content;
        } catch (IOException e) {
            throw e;
        }
    }

    public String getContentEncoding() {
        a();
        String contentEncoding = this.b.getContentEncoding();
        a(a(), this.b);
        return contentEncoding;
    }

    public int getContentLength() {
        a();
        int contentLength = this.b.getContentLength();
        a(a(), this.b);
        return contentLength;
    }

    public String getContentType() {
        a();
        String contentType = this.b.getContentType();
        a(a(), this.b);
        return contentType;
    }

    public long getDate() {
        a();
        long date = this.b.getDate();
        a(a(), this.b);
        return date;
    }

    public InputStream getErrorStream() {
        a();
        try {
            return new AopInputStream(this.b.getErrorStream());
        } catch (Exception unused) {
            return this.b.getErrorStream();
        }
    }

    public long getHeaderFieldDate(String str, long j) {
        a();
        long headerFieldDate = this.b.getHeaderFieldDate(str, j);
        a(a(), this.b);
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
        a();
        try {
            int responseCode = this.b.getResponseCode();
            a(a(), this.b);
            return responseCode;
        } catch (IOException e) {
            throw e;
        }
    }

    public String getResponseMessage() throws IOException {
        a();
        try {
            String responseMessage = this.b.getResponseMessage();
            a(a(), this.b);
            return responseMessage;
        } catch (IOException e) {
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
        a();
        try {
            this.b.setRequestMethod(str);
        } catch (ProtocolException e) {
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
        a();
        long expiration = this.b.getExpiration();
        a(a(), this.b);
        return expiration;
    }

    public String getHeaderField(int i) {
        a();
        String headerField = this.b.getHeaderField(i);
        a(a(), this.b);
        return headerField;
    }

    public String getHeaderField(String str) {
        a();
        String headerField = this.b.getHeaderField(str);
        a(a(), this.b);
        return headerField;
    }

    public int getHeaderFieldInt(String str, int i) {
        a();
        int headerFieldInt = this.b.getHeaderFieldInt(str, i);
        a(a(), this.b);
        return headerFieldInt;
    }

    public String getHeaderFieldKey(int i) {
        a();
        String headerFieldKey = this.b.getHeaderFieldKey(i);
        a(a(), this.b);
        return headerFieldKey;
    }

    public Map<String, List<String>> getHeaderFields() {
        a();
        Map<String, List<String>> headerFields = this.b.getHeaderFields();
        a(a(), this.b);
        return headerFields;
    }

    public long getIfModifiedSince() {
        a();
        long ifModifiedSince = this.b.getIfModifiedSince();
        a(a(), this.b);
        return ifModifiedSince;
    }

    public InputStream getInputStream() throws IOException {
        NetInfo a2 = a();
        try {
            AopInputStream aopInputStream = new AopInputStream(this.b.getInputStream());
            a(a2, this.b);
            aopInputStream.a(this);
            return aopInputStream;
        } catch (IOException e) {
            throw e;
        }
    }

    public long getLastModified() {
        a();
        long lastModified = this.b.getLastModified();
        a(a(), this.b);
        return lastModified;
    }

    public OutputStream getOutputStream() throws IOException {
        a();
        try {
            AopOutputStream aopOutputStream = new AopOutputStream(this.b.getOutputStream());
            aopOutputStream.a(this);
            return aopOutputStream;
        } catch (IOException e) {
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

    public void a(long j) {
        this.c.setReceivedBytes(j);
        this.c.end();
    }

    public void c(long j) {
        this.c.setReceivedBytes(j);
        this.c.end();
    }

    public void b(long j) {
        this.c.setSendBytes(j);
        this.c.end();
    }

    public void d(long j) {
        this.c.setSendBytes(j);
        this.c.end();
    }
}
