package miuipub.net.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

public class HttpRequestParams {

    /* renamed from: a  reason: collision with root package name */
    public static String f2978a = "UTF-8";
    private String b;
    private ConcurrentHashMap<String, Object> c;
    private boolean d;

    public HttpRequestParams() {
        this.b = f2978a;
        this.c = new ConcurrentHashMap<>();
        this.d = false;
    }

    public HttpRequestParams(Object... objArr) {
        this();
        int length = objArr.length;
        if (length % 2 == 0) {
            int i = 0;
            while (i < length) {
                if (objArr[i] instanceof String) {
                    String str = objArr[i];
                    String str2 = objArr[i + 1];
                    if (str2 instanceof String) {
                        a(str, str2);
                    } else if (str2 instanceof File) {
                        a(str, (File) str2);
                    } else if (str2 instanceof List) {
                        a(str, (List<String>) (List) str2);
                    } else {
                        throw new IllegalArgumentException("Unknown argument type : " + str2);
                    }
                    i += 2;
                } else {
                    throw new IllegalArgumentException("Unknown argument name : " + objArr[i]);
                }
            }
            return;
        }
        throw new IllegalArgumentException("Supplied argument must be even");
    }

    public HttpRequestParams a(String str) {
        if (str == null) {
            str = f2978a;
        }
        this.b = str;
        return this;
    }

    public HttpRequestParams a(String str, String str2) {
        if (!(str == null || str2 == null)) {
            this.c.put(str, str2);
        }
        return this;
    }

    public HttpRequestParams a(String str, File file) {
        if (!(str == null || file == null)) {
            try {
                this.c.put(str, new FileWrapper(new FileInputStream(file), file.length(), file.getName(), (String) null));
                this.d = true;
            } catch (FileNotFoundException e) {
                throw new IllegalArgumentException(e.getMessage(), e);
            }
        }
        return this;
    }

    public HttpRequestParams a(String str, File file, String str2) {
        if (!(str == null || file == null)) {
            try {
                this.c.put(str, new FileWrapper(new FileInputStream(file), file.length(), file.getName(), str2));
                this.d = true;
            } catch (FileNotFoundException e) {
                throw new IllegalArgumentException(e.getMessage(), e);
            }
        }
        return this;
    }

    public HttpRequestParams a(String str, List<String> list) {
        if (!(str == null || list == null || list.size() <= 0)) {
            this.c.put(str, list);
        }
        return this;
    }

    public HttpRequestParams a(String str, InputStream inputStream, long j, String str2) {
        if (!(str == null || inputStream == null || j < 0)) {
            this.c.put(str, new FileWrapper(inputStream, j, str2, (String) null));
            this.d = true;
        }
        return this;
    }

    public HttpRequestParams a(String str, InputStream inputStream, long j, String str2, String str3) {
        if (!(str == null || inputStream == null || j < 0)) {
            this.c.put(str, new FileWrapper(inputStream, j, str2, str3));
            this.d = true;
        }
        return this;
    }

    public HttpRequestParams a(Map<String, String> map) {
        for (Map.Entry next : map.entrySet()) {
            a((String) next.getKey(), (String) next.getValue());
        }
        return this;
    }

    public HttpRequestParams b(String str) {
        this.c.remove(str);
        return this;
    }

    public String a() {
        return URLEncodedUtils.format(c(), this.b);
    }

    /* access modifiers changed from: package-private */
    public HttpEntity b() {
        try {
            if (this.d) {
                return new SimpleMultipartEntity(this.b, this.c);
            }
            return new UrlEncodedFormEntity(c(), this.b);
        } catch (IOException unused) {
            return null;
        }
    }

    private List<BasicNameValuePair> c() {
        LinkedList linkedList = new LinkedList();
        for (Map.Entry next : this.c.entrySet()) {
            Object value = next.getValue();
            if (value instanceof String) {
                linkedList.add(new BasicNameValuePair((String) next.getKey(), (String) value));
            } else if (value instanceof List) {
                for (String basicNameValuePair : (List) value) {
                    linkedList.add(new BasicNameValuePair((String) next.getKey(), basicNameValuePair));
                }
            }
        }
        return linkedList;
    }

    static class FileWrapper {

        /* renamed from: a  reason: collision with root package name */
        public InputStream f2979a;
        public long b;
        public String c;
        public String d;

        public FileWrapper(InputStream inputStream, long j, String str, String str2) {
            this.f2979a = inputStream;
            this.b = j;
            this.c = str == null ? "nofilename" : str;
            this.d = str2;
        }
    }
}
