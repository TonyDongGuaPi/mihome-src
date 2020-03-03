package org.xutils.http.cookie;

import android.text.TextUtils;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.youpin.login.api.LoginErrorCode;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import org.xutils.DbManager;
import org.xutils.common.util.LogUtil;
import org.xutils.db.Selector;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.db.table.DbModel;

public enum DbCookieStore implements CookieStore {
    INSTANCE;
    
    private static final int LIMIT_COUNT = 5000;
    private static final long TRIM_TIME_SPAN = 1000;
    /* access modifiers changed from: private */
    public final DbManager db;
    /* access modifiers changed from: private */
    public long lastTrimTime;
    private final Executor trimExecutor;

    public void add(URI uri, HttpCookie httpCookie) {
        if (httpCookie != null) {
            try {
                this.db.d((Object) new CookieEntity(getEffectiveURI(uri), httpCookie));
            } catch (Throwable th) {
                LogUtil.b(th.getMessage(), th);
            }
            trimSize();
        }
    }

    public List<HttpCookie> get(URI uri) {
        if (uri != null) {
            URI effectiveURI = getEffectiveURI(uri);
            ArrayList arrayList = new ArrayList();
            try {
                Selector<CookieEntity> d = this.db.d(CookieEntity.class);
                WhereBuilder a2 = WhereBuilder.a();
                String host = effectiveURI.getHost();
                if (!TextUtils.isEmpty(host)) {
                    WhereBuilder a3 = WhereBuilder.a("domain", "=", host);
                    WhereBuilder c = a3.c("domain", "=", "." + host);
                    int indexOf = host.indexOf(".");
                    int lastIndexOf = host.lastIndexOf(".");
                    if (indexOf > 0 && lastIndexOf > indexOf) {
                        String substring = host.substring(indexOf, host.length());
                        if (!TextUtils.isEmpty(substring)) {
                            c.c("domain", "=", substring);
                        }
                    }
                    a2.a(c);
                }
                String path = effectiveURI.getPath();
                if (!TextUtils.isEmpty(path)) {
                    WhereBuilder c2 = WhereBuilder.a("path", "=", path).c("path", "=", "/").c("path", "=", (Object) null);
                    int lastIndexOf2 = path.lastIndexOf("/");
                    while (lastIndexOf2 > 0) {
                        path = path.substring(0, lastIndexOf2);
                        c2.c("path", "=", path);
                        lastIndexOf2 = path.lastIndexOf("/");
                    }
                    a2.a(c2);
                }
                a2.c("uri", "=", effectiveURI.toString());
                List<CookieEntity> g = d.a(a2).g();
                if (g != null) {
                    for (CookieEntity next : g) {
                        if (!next.d()) {
                            arrayList.add(next.a());
                        }
                    }
                }
            } catch (Throwable th) {
                LogUtil.b(th.getMessage(), th);
            }
            return arrayList;
        }
        throw new NullPointerException("uri is null");
    }

    public List<HttpCookie> getCookies() {
        ArrayList arrayList = new ArrayList();
        try {
            List<CookieEntity> c = this.db.c(CookieEntity.class);
            if (c != null) {
                for (CookieEntity next : c) {
                    if (!next.d()) {
                        arrayList.add(next.a());
                    }
                }
            }
        } catch (Throwable th) {
            LogUtil.b(th.getMessage(), th);
        }
        return arrayList;
    }

    public List<URI> getURIs() {
        String a2;
        ArrayList arrayList = new ArrayList();
        try {
            List<DbModel> c = this.db.d(CookieEntity.class).a("uri").c();
            if (c != null) {
                for (DbModel a3 : c) {
                    a2 = a3.a("uri");
                    if (!TextUtils.isEmpty(a2)) {
                        arrayList.add(new URI(a2));
                    }
                }
            }
        } catch (Throwable th) {
            LogUtil.b(th.getMessage(), th);
        }
        return arrayList;
    }

    public boolean remove(URI uri, HttpCookie httpCookie) {
        if (httpCookie == null) {
            return true;
        }
        try {
            WhereBuilder a2 = WhereBuilder.a("name", "=", httpCookie.getName());
            String domain = httpCookie.getDomain();
            if (!TextUtils.isEmpty(domain)) {
                a2.b("domain", "=", domain);
            }
            String path = httpCookie.getPath();
            if (!TextUtils.isEmpty(path)) {
                if (path.length() > 1 && path.endsWith("/")) {
                    path = path.substring(0, path.length() - 1);
                }
                a2.b("path", "=", path);
            }
            this.db.a((Class<?>) CookieEntity.class, a2);
            return true;
        } catch (Throwable th) {
            LogUtil.b(th.getMessage(), th);
            return false;
        }
    }

    public boolean removeAll() {
        try {
            this.db.a((Class<?>) CookieEntity.class);
            return true;
        } catch (Throwable th) {
            LogUtil.b(th.getMessage(), th);
            return true;
        }
    }

    private void trimSize() {
        this.trimExecutor.execute(new Runnable() {
            public void run() {
                List<CookieEntity> g;
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - DbCookieStore.this.lastTrimTime >= 1000) {
                    long unused = DbCookieStore.this.lastTrimTime = currentTimeMillis;
                    try {
                        DbCookieStore.this.db.a((Class<?>) CookieEntity.class, WhereBuilder.a("expiry", "<", Long.valueOf(System.currentTimeMillis())).b("expiry", Operators.NOT_EQUAL2, -1L));
                    } catch (Throwable th) {
                        LogUtil.b(th.getMessage(), th);
                    }
                    try {
                        int h = (int) DbCookieStore.this.db.d(CookieEntity.class).h();
                        if (h > 5010 && (g = DbCookieStore.this.db.d(CookieEntity.class).a("expiry", Operators.NOT_EQUAL2, -1L).a("expiry", false).a(h + LoginErrorCode.I).g()) != null) {
                            DbCookieStore.this.db.e((Object) g);
                        }
                    } catch (Throwable th2) {
                        LogUtil.b(th2.getMessage(), th2);
                    }
                }
            }
        });
    }

    private URI getEffectiveURI(URI uri) {
        try {
            return new URI("http", uri.getHost(), uri.getPath(), (String) null, (String) null);
        } catch (Throwable unused) {
            return uri;
        }
    }
}
