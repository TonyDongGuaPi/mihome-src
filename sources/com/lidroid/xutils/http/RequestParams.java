package com.lidroid.xutils.http;

import android.text.TextUtils;
import com.lidroid.xutils.http.client.entity.BodyParamsEntity;
import com.lidroid.xutils.http.client.multipart.HttpMultipartMode;
import com.lidroid.xutils.http.client.multipart.MultipartEntity;
import com.lidroid.xutils.http.client.multipart.content.ContentBody;
import com.lidroid.xutils.http.client.multipart.content.FileBody;
import com.lidroid.xutils.http.client.multipart.content.InputStreamBody;
import com.lidroid.xutils.http.client.multipart.content.StringBody;
import com.lidroid.xutils.task.Priority;
import com.lidroid.xutils.util.LogUtils;
import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;

public class RequestParams {

    /* renamed from: a  reason: collision with root package name */
    private String f6333a = "UTF-8";
    private List<HeaderItem> b;
    private List<NameValuePair> c;
    private HttpEntity d;
    private List<NameValuePair> e;
    private HashMap<String, ContentBody> f;
    private Priority g;

    public RequestParams() {
    }

    public RequestParams(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.f6333a = str;
        }
    }

    public Priority a() {
        return this.g;
    }

    public void a(Priority priority) {
        this.g = priority;
    }

    public String b() {
        return this.f6333a;
    }

    public void a(String str) {
        b("Content-Type", str);
    }

    public void a(Header header) {
        if (this.b == null) {
            this.b = new ArrayList();
        }
        this.b.add(new HeaderItem(header));
    }

    public void a(String str, String str2) {
        if (this.b == null) {
            this.b = new ArrayList();
        }
        this.b.add(new HeaderItem(str, str2));
    }

    public void a(List<Header> list) {
        if (this.b == null) {
            this.b = new ArrayList();
        }
        for (Header headerItem : list) {
            this.b.add(new HeaderItem(headerItem));
        }
    }

    public void b(Header header) {
        if (this.b == null) {
            this.b = new ArrayList();
        }
        this.b.add(new HeaderItem(header, true));
    }

    public void b(String str, String str2) {
        if (this.b == null) {
            this.b = new ArrayList();
        }
        this.b.add(new HeaderItem(str, str2, true));
    }

    public void b(List<Header> list) {
        if (this.b == null) {
            this.b = new ArrayList();
        }
        for (Header headerItem : list) {
            this.b.add(new HeaderItem(headerItem, true));
        }
    }

    public void c(String str, String str2) {
        if (this.c == null) {
            this.c = new ArrayList();
        }
        this.c.add(new BasicNameValuePair(str, str2));
    }

    public void a(NameValuePair nameValuePair) {
        if (this.c == null) {
            this.c = new ArrayList();
        }
        this.c.add(nameValuePair);
    }

    public void c(List<NameValuePair> list) {
        if (this.c == null) {
            this.c = new ArrayList();
        }
        if (list != null && list.size() > 0) {
            for (NameValuePair add : list) {
                this.c.add(add);
            }
        }
    }

    public void d(String str, String str2) {
        if (this.e == null) {
            this.e = new ArrayList();
        }
        this.e.add(new BasicNameValuePair(str, str2));
    }

    public void b(NameValuePair nameValuePair) {
        if (this.e == null) {
            this.e = new ArrayList();
        }
        this.e.add(nameValuePair);
    }

    public void d(List<NameValuePair> list) {
        if (this.e == null) {
            this.e = new ArrayList();
        }
        if (list != null && list.size() > 0) {
            for (NameValuePair add : list) {
                this.e.add(add);
            }
        }
    }

    public void a(String str, File file) {
        if (this.f == null) {
            this.f = new HashMap<>();
        }
        this.f.put(str, new FileBody(file));
    }

    public void a(String str, File file, String str2) {
        if (this.f == null) {
            this.f = new HashMap<>();
        }
        this.f.put(str, new FileBody(file, str2));
    }

    public void a(String str, File file, String str2, String str3) {
        if (this.f == null) {
            this.f = new HashMap<>();
        }
        this.f.put(str, new FileBody(file, str2, str3));
    }

    public void a(String str, File file, String str2, String str3, String str4) {
        if (this.f == null) {
            this.f = new HashMap<>();
        }
        this.f.put(str, new FileBody(file, str2, str3, str4));
    }

    public void a(String str, InputStream inputStream, long j) {
        if (this.f == null) {
            this.f = new HashMap<>();
        }
        this.f.put(str, new InputStreamBody(inputStream, j));
    }

    public void a(String str, InputStream inputStream, long j, String str2) {
        if (this.f == null) {
            this.f = new HashMap<>();
        }
        this.f.put(str, new InputStreamBody(inputStream, j, str2));
    }

    public void a(String str, InputStream inputStream, long j, String str2, String str3) {
        if (this.f == null) {
            this.f = new HashMap<>();
        }
        this.f.put(str, new InputStreamBody(inputStream, j, str2, str3));
    }

    public void a(HttpEntity httpEntity) {
        this.d = httpEntity;
        if (this.e != null) {
            this.e.clear();
            this.e = null;
        }
        if (this.f != null) {
            this.f.clear();
            this.f = null;
        }
    }

    public HttpEntity c() {
        if (this.d != null) {
            return this.d;
        }
        if (this.f != null && !this.f.isEmpty()) {
            MultipartEntity multipartEntity = new MultipartEntity(HttpMultipartMode.STRICT, (String) null, Charset.forName(this.f6333a));
            if (this.e != null && !this.e.isEmpty()) {
                for (NameValuePair next : this.e) {
                    try {
                        multipartEntity.a(next.getName(), (ContentBody) new StringBody(next.getValue()));
                    } catch (UnsupportedEncodingException e2) {
                        LogUtils.b(e2.getMessage(), e2);
                    }
                }
            }
            for (Map.Entry next2 : this.f.entrySet()) {
                multipartEntity.a((String) next2.getKey(), (ContentBody) next2.getValue());
            }
            return multipartEntity;
        } else if (this.e == null || this.e.isEmpty()) {
            return null;
        } else {
            return new BodyParamsEntity(this.e, this.f6333a);
        }
    }

    public List<NameValuePair> d() {
        return this.c;
    }

    public List<HeaderItem> e() {
        return this.b;
    }

    public class HeaderItem {

        /* renamed from: a  reason: collision with root package name */
        public final boolean f6334a;
        public final Header b;

        public HeaderItem(Header header) {
            this.f6334a = false;
            this.b = header;
        }

        public HeaderItem(Header header, boolean z) {
            this.f6334a = z;
            this.b = header;
        }

        public HeaderItem(String str, String str2) {
            this.f6334a = false;
            this.b = new BasicHeader(str, str2);
        }

        public HeaderItem(String str, String str2, boolean z) {
            this.f6334a = z;
            this.b = new BasicHeader(str, str2);
        }
    }
}
