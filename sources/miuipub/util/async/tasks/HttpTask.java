package miuipub.util.async.tasks;

import com.alipay.sdk.sys.a;
import java.util.Map;
import miuipub.net.http.HttpRequestParams;
import miuipub.net.http.HttpResponse;
import miuipub.net.http.HttpSession;
import miuipub.util.async.Task;

public abstract class HttpTask<T> extends Task<T> {

    /* renamed from: a  reason: collision with root package name */
    private final HttpSession f3042a;
    private final Method b;
    private final String c;
    private final HttpRequestParams d;
    private HttpSession.ProgressListener e;

    public enum Method {
        Get,
        Post
    }

    public HttpTask(String str) {
        this((HttpSession) null, Method.Get, str, (Map<String, String>) null);
    }

    public HttpTask(HttpSession httpSession, String str) {
        this(httpSession, Method.Get, str, (Map<String, String>) null);
    }

    public HttpTask(HttpSession httpSession, Method method, String str, Map<String, String> map) {
        String str2;
        this.f3042a = httpSession == null ? HttpSession.a() : httpSession;
        this.b = method;
        if (method == Method.Get) {
            if (map == null || map.size() <= 0) {
                this.c = str;
            } else {
                HttpRequestParams httpRequestParams = new HttpRequestParams(map);
                if (str.indexOf(63) >= 0) {
                    str2 = str + a.b + httpRequestParams.a();
                } else {
                    str2 = str + "?" + httpRequestParams.a();
                }
                this.c = str2;
            }
            this.d = null;
            return;
        }
        this.c = str;
        this.d = new HttpRequestParams();
        this.d.a(map);
    }

    public String d() {
        return this.c;
    }

    /* access modifiers changed from: protected */
    public final HttpResponse j() throws Exception {
        if (this.e == null) {
            this.e = new HttpSession.ProgressListener() {
                public void a(long j, long j2) {
                    if (j > 2147483647L) {
                        long j3 = (long) ((int) (((float) (j * 2147483647L)) / 9.223372E18f));
                        j = j3;
                        j2 = (long) ((int) (((float) (2147483647L * j3)) / 9.223372E18f));
                    }
                    HttpTask.this.a((int) j, (int) j2);
                }
            };
        }
        switch (this.b) {
            case Get:
                return this.f3042a.a(this.c, (Map<String, String>) null, this.d, this.e);
            case Post:
                return this.f3042a.b(this.c, (Map<String, String>) null, this.d, this.e);
            default:
                return null;
        }
    }
}
