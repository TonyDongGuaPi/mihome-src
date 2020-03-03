package com.lidroid.xutils.http.client.entity;

import com.lidroid.xutils.http.client.util.URLEncodedUtils;
import com.lidroid.xutils.util.LogUtils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.message.BasicNameValuePair;

public class BodyParamsEntity extends AbstractHttpEntity implements Cloneable {

    /* renamed from: a  reason: collision with root package name */
    protected byte[] f6342a;
    private boolean b;
    private String c;
    private List<NameValuePair> d;

    public boolean isRepeatable() {
        return true;
    }

    public boolean isStreaming() {
        return false;
    }

    public BodyParamsEntity() {
        this((String) null);
    }

    public BodyParamsEntity(String str) {
        this.b = true;
        this.c = "UTF-8";
        if (str != null) {
            this.c = str;
        }
        setContentType("application/x-www-form-urlencoded");
        this.d = new ArrayList();
    }

    public BodyParamsEntity(List<NameValuePair> list) {
        this(list, (String) null);
    }

    public BodyParamsEntity(List<NameValuePair> list, String str) {
        this.b = true;
        this.c = "UTF-8";
        if (str != null) {
            this.c = str;
        }
        setContentType("application/x-www-form-urlencoded");
        this.d = list;
        a();
    }

    public BodyParamsEntity a(String str, String str2) {
        this.d.add(new BasicNameValuePair(str, str2));
        this.b = true;
        return this;
    }

    public BodyParamsEntity a(List<NameValuePair> list) {
        this.d.addAll(list);
        this.b = true;
        return this;
    }

    private void a() {
        if (this.b) {
            try {
                this.f6342a = URLEncodedUtils.a((List<? extends NameValuePair>) this.d, this.c).getBytes(this.c);
            } catch (UnsupportedEncodingException e) {
                LogUtils.b(e.getMessage(), e);
            }
            this.b = false;
        }
    }

    public long getContentLength() {
        a();
        return (long) this.f6342a.length;
    }

    public InputStream getContent() throws IOException {
        a();
        return new ByteArrayInputStream(this.f6342a);
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        if (outputStream != null) {
            a();
            outputStream.write(this.f6342a);
            outputStream.flush();
            return;
        }
        throw new IllegalArgumentException("Output stream may not be null");
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
