package com.ximalaya.ting.android.opensdk.httputil;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.ximalaya.ting.android.opensdk.player.service.XmPlayerConfig;
import com.ximalaya.ting.android.player.IDomainServerIpCallback;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HttpDNSUtilForOpenSDK {

    private static class DNSHostIP {
        private static List<DNSHostIP> c;

        /* renamed from: a  reason: collision with root package name */
        private String[] f1991a;
        private int b;

        private DNSHostIP(String[] strArr) {
            this.b = 0;
            if (strArr != null && strArr.length > 1) {
                this.f1991a = strArr;
            }
        }

        public static List<DNSHostIP> a() {
            if (c == null) {
                c = new CopyOnWriteArrayList<DNSHostIP>() {
                    {
                        add(new DNSHostIP(new String[]{"fdfs.xmcdn.com", "adudio1.ws.xmcdn.com"}));
                        add(new DNSHostIP(new String[]{"audio.xmcdn.com", "audio2.xmcdn.com"}));
                        add(new DNSHostIP(new String[]{"download.xmcdn.com", "download2.xmcdn.com"}));
                        add(new DNSHostIP(new String[]{"api.ximalaya.com", "114.80.138.114"}));
                        add(new DNSHostIP(new String[]{"activity.ximalaya.com", "180.153.255.9"}));
                        add(new DNSHostIP(new String[]{"live.xmcdn.com", "112.65.220.26", "112.65.220.28"}));
                    }
                };
            }
            return c;
        }

        public static String a(String str) {
            for (DNSHostIP next : a()) {
                if (next.f1991a != null && Arrays.binarySearch(next.f1991a, str) >= 0) {
                    return next.f1991a[next.b];
                }
            }
            return null;
        }

        public static String b(String str) {
            int i;
            for (DNSHostIP next : a()) {
                if (next.f1991a != null && Arrays.binarySearch(next.f1991a, str) >= 0) {
                    if (next.b == next.f1991a.length - 1) {
                        i = 0;
                    } else {
                        i = next.b + 1;
                    }
                    next.b = i;
                    return next.f1991a[i];
                }
            }
            return null;
        }

        public static boolean c(String str) {
            Iterator<DNSHostIP> it = a().iterator();
            while (true) {
                if (!it.hasNext()) {
                    return false;
                }
                DNSHostIP next = it.next();
                if (next.f1991a != null) {
                    for (String equals : next.f1991a) {
                        if (equals.equals(str)) {
                            return true;
                        }
                    }
                    continue;
                }
            }
        }

        public static String[] d(String str) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            for (DNSHostIP next : a()) {
                if (next.f1991a != null && next.f1991a[0].equals(str)) {
                    return (String[]) Arrays.copyOfRange(next.f1991a, 1, next.f1991a.length);
                }
            }
            return null;
        }
    }

    public static IDomainServerIpCallback a(Context context) {
        if (!XmPlayerConfig.a(context).j()) {
            return null;
        }
        return new IDomainServerIpCallback() {
            public void a(String str, String str2, String str3) {
            }

            public String b(String str) {
                return null;
            }

            public String[][] a(String str) {
                if (TextUtils.isEmpty(str)) {
                    return null;
                }
                String host = Uri.parse(str).getHost();
                if (!DNSHostIP.c(host)) {
                    return null;
                }
                String[] d = DNSHostIP.d(host);
                if (d == null) {
                    return null;
                }
                String[][] strArr = (String[][]) Array.newInstance(String.class, new int[]{d.length + 1, 2});
                strArr[0] = new String[]{str, host};
                for (int i = 1; i < strArr.length; i++) {
                    int i2 = i - 1;
                    strArr[i] = new String[]{str.replaceFirst(host, d[i2]), d[i2]};
                }
                return strArr;
            }
        };
    }

    public static Interceptor a() {
        return new Interceptor() {
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Throwable th;
                Response response;
                Request request = chain.request();
                HttpUrl url = request.url();
                String httpUrl = url.toString();
                String host = url.host();
                try {
                    Request.Builder newBuilder = request.newBuilder();
                    String a2 = DNSHostIP.a(host);
                    if (!TextUtils.isEmpty(a2)) {
                        newBuilder.url(httpUrl.replaceFirst(host, a2));
                    } else {
                        newBuilder.url(url);
                    }
                    response = chain.proceed(newBuilder.build());
                    th = null;
                } catch (Throwable th2) {
                    th = th2;
                    response = null;
                }
                if ((response == null || (!response.isSuccessful() && response.code() != 401)) && DNSHostIP.c(host)) {
                    try {
                        Request.Builder newBuilder2 = request.newBuilder();
                        String b = DNSHostIP.b(host);
                        if (!TextUtils.isEmpty(b)) {
                            newBuilder2.url(httpUrl.replaceFirst(host, b));
                        } else {
                            newBuilder2.url(httpUrl);
                        }
                        Response proceed = chain.proceed(newBuilder2.header("Host", host).build());
                        if (proceed != null) {
                            return proceed;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        if (response != null) {
                            response.close();
                        }
                        response = null;
                    }
                }
                if (response != null) {
                    return response;
                }
                if (th != null) {
                    throw new IOException(th.toString() + "  URL:" + httpUrl);
                }
                throw new IOException("application interceptor returned null  URL:" + httpUrl);
            }
        };
    }
}
