package com.lidroid.xutils.http.client.util;

import android.text.TextUtils;
import com.lidroid.xutils.util.LogUtils;
import com.taobao.weex.el.parse.Operators;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.conn.util.InetAddressUtils;
import org.apache.http.message.BasicNameValuePair;

public class URIBuilder {

    /* renamed from: a  reason: collision with root package name */
    private String f6354a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private int g;
    private String h;
    private String i;
    private String j;
    private List<NameValuePair> k;
    private String l;
    private String m;

    public URIBuilder() {
        this.g = -1;
    }

    public URIBuilder(String str) {
        try {
            a(new URI(str));
        } catch (URISyntaxException e2) {
            LogUtils.b(e2.getMessage(), e2);
        }
    }

    public URIBuilder(URI uri) {
        a(uri);
    }

    private void a(URI uri) {
        this.f6354a = uri.getScheme();
        this.b = uri.getRawSchemeSpecificPart();
        this.c = uri.getRawAuthority();
        this.f = uri.getHost();
        this.g = uri.getPort();
        this.e = uri.getRawUserInfo();
        this.d = uri.getUserInfo();
        this.i = uri.getRawPath();
        this.h = uri.getPath();
        this.j = uri.getRawQuery();
        this.k = g(uri.getRawQuery());
        this.m = uri.getRawFragment();
        this.l = uri.getFragment();
    }

    private List<NameValuePair> g(String str) {
        if (!TextUtils.isEmpty(str)) {
            return URLEncodedUtils.a(str);
        }
        return null;
    }

    public URI a(Charset charset) throws URISyntaxException {
        return new URI(b(charset));
    }

    private String b(Charset charset) {
        StringBuilder sb = new StringBuilder();
        if (this.f6354a != null) {
            sb.append(this.f6354a);
            sb.append(Operators.CONDITION_IF_MIDDLE);
        }
        if (this.b != null) {
            sb.append(this.b);
        } else {
            if (this.c != null) {
                sb.append("//");
                sb.append(this.c);
            } else if (this.f != null) {
                sb.append("//");
                if (this.e != null) {
                    sb.append(this.e);
                    sb.append("@");
                } else if (this.d != null) {
                    sb.append(a(this.d, charset));
                    sb.append("@");
                }
                if (InetAddressUtils.isIPv6Address(this.f)) {
                    sb.append(Operators.ARRAY_START_STR);
                    sb.append(this.f);
                    sb.append(Operators.ARRAY_END_STR);
                } else {
                    sb.append(this.f);
                }
                if (this.g >= 0) {
                    sb.append(":");
                    sb.append(this.g);
                }
            }
            if (this.i != null) {
                sb.append(h(this.i));
            } else if (this.h != null) {
                sb.append(b(h(this.h), charset));
            }
            if (this.j != null) {
                sb.append("?");
                sb.append(this.j);
            } else if (this.k != null) {
                sb.append("?");
                sb.append(a(this.k, charset));
            }
        }
        if (this.m != null) {
            sb.append("#");
            sb.append(this.m);
        } else if (this.l != null) {
            sb.append("#");
            sb.append(c(this.l, charset));
        }
        return sb.toString();
    }

    private String a(String str, Charset charset) {
        return URLEncodedUtils.a(str, charset);
    }

    private String b(String str, Charset charset) {
        return URLEncodedUtils.c(str, charset).replace("+", "20%");
    }

    private String a(List<NameValuePair> list, Charset charset) {
        return URLEncodedUtils.a((Iterable<? extends NameValuePair>) list, charset);
    }

    private String c(String str, Charset charset) {
        return URLEncodedUtils.b(str, charset);
    }

    public URIBuilder a(String str) {
        this.f6354a = str;
        return this;
    }

    public URIBuilder b(String str) {
        this.d = str;
        this.b = null;
        this.c = null;
        this.e = null;
        return this;
    }

    public URIBuilder a(String str, String str2) {
        return b(String.valueOf(str) + Operators.CONDITION_IF_MIDDLE + str2);
    }

    public URIBuilder c(String str) {
        this.f = str;
        this.b = null;
        this.c = null;
        return this;
    }

    public URIBuilder a(int i2) {
        if (i2 < 0) {
            i2 = -1;
        }
        this.g = i2;
        this.b = null;
        this.c = null;
        return this;
    }

    public URIBuilder d(String str) {
        this.h = str;
        this.b = null;
        this.i = null;
        return this;
    }

    public URIBuilder e(String str) {
        this.k = g(str);
        this.j = null;
        this.b = null;
        return this;
    }

    public URIBuilder b(String str, String str2) {
        if (this.k == null) {
            this.k = new ArrayList();
        }
        this.k.add(new BasicNameValuePair(str, str2));
        this.j = null;
        this.b = null;
        return this;
    }

    public URIBuilder c(String str, String str2) {
        if (this.k == null) {
            this.k = new ArrayList();
        }
        if (!this.k.isEmpty()) {
            Iterator<NameValuePair> it = this.k.iterator();
            while (it.hasNext()) {
                if (it.next().getName().equals(str)) {
                    it.remove();
                }
            }
        }
        this.k.add(new BasicNameValuePair(str, str2));
        this.j = null;
        this.b = null;
        return this;
    }

    public URIBuilder f(String str) {
        this.l = str;
        this.m = null;
        return this;
    }

    public String a() {
        return this.f6354a;
    }

    public String b() {
        return this.d;
    }

    public String c() {
        return this.f;
    }

    public int d() {
        return this.g;
    }

    public String e() {
        return this.h;
    }

    public List<NameValuePair> f() {
        if (this.k != null) {
            return new ArrayList(this.k);
        }
        return new ArrayList();
    }

    public String g() {
        return this.l;
    }

    private static String h(String str) {
        if (str == null) {
            return null;
        }
        int i2 = 0;
        while (i2 < str.length() && str.charAt(i2) == '/') {
            i2++;
        }
        return i2 > 1 ? str.substring(i2 - 1) : str;
    }
}
