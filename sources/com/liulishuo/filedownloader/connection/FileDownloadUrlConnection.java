package com.liulishuo.filedownloader.connection;

import com.liulishuo.filedownloader.util.FileDownloadHelper;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class FileDownloadUrlConnection implements FileDownloadConnection {
    protected URLConnection c;

    public boolean a(String str, long j) {
        return false;
    }

    public void f() {
    }

    public FileDownloadUrlConnection(String str, Configuration configuration) throws IOException {
        this(new URL(str), configuration);
    }

    public FileDownloadUrlConnection(URL url, Configuration configuration) throws IOException {
        if (configuration == null || configuration.f6414a == null) {
            this.c = url.openConnection();
        } else {
            this.c = url.openConnection(configuration.f6414a);
        }
        if (configuration != null) {
            if (configuration.b != null) {
                this.c.setReadTimeout(configuration.b.intValue());
            }
            if (configuration.c != null) {
                this.c.setConnectTimeout(configuration.c.intValue());
            }
        }
    }

    public FileDownloadUrlConnection(String str) throws IOException {
        this(str, (Configuration) null);
    }

    public void a(String str, String str2) {
        this.c.addRequestProperty(str, str2);
    }

    public InputStream a() throws IOException {
        return this.c.getInputStream();
    }

    public Map<String, List<String>> b() {
        return this.c.getRequestProperties();
    }

    public Map<String, List<String>> c() {
        return this.c.getHeaderFields();
    }

    public String a(String str) {
        return this.c.getHeaderField(str);
    }

    public void d() throws IOException {
        this.c.connect();
    }

    public int e() throws IOException {
        if (this.c instanceof HttpURLConnection) {
            return ((HttpURLConnection) this.c).getResponseCode();
        }
        return 0;
    }

    public static class Creator implements FileDownloadHelper.ConnectionCreator {

        /* renamed from: a  reason: collision with root package name */
        private final Configuration f6415a;

        public Creator() {
            this((Configuration) null);
        }

        public Creator(Configuration configuration) {
            this.f6415a = configuration;
        }

        /* access modifiers changed from: package-private */
        public FileDownloadConnection a(URL url) throws IOException {
            return new FileDownloadUrlConnection(url, this.f6415a);
        }

        public FileDownloadConnection a(String str) throws IOException {
            return new FileDownloadUrlConnection(str, this.f6415a);
        }
    }

    public static class Configuration {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public Proxy f6414a;
        /* access modifiers changed from: private */
        public Integer b;
        /* access modifiers changed from: private */
        public Integer c;

        public Configuration a(Proxy proxy) {
            this.f6414a = proxy;
            return this;
        }

        public Configuration a(int i) {
            this.b = Integer.valueOf(i);
            return this;
        }

        public Configuration b(int i) {
            this.c = Integer.valueOf(i);
            return this;
        }
    }
}
