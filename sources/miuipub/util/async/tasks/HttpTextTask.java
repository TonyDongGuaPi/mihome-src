package miuipub.util.async.tasks;

import java.nio.charset.Charset;
import java.util.Map;
import miuipub.net.http.HttpResponse;
import miuipub.net.http.HttpSession;
import miuipub.util.IOUtils;
import miuipub.util.async.tasks.HttpTask;

public abstract class HttpTextTask<T> extends HttpTask<T> {
    public HttpTextTask(String str) {
        this((HttpSession) null, HttpTask.Method.Get, str, (Map<String, String>) null);
    }

    public HttpTextTask(HttpSession httpSession, String str) {
        this(httpSession, HttpTask.Method.Get, str, (Map<String, String>) null);
    }

    public HttpTextTask(HttpSession httpSession, HttpTask.Method method, String str, Map<String, String> map) {
        super(httpSession, method, str, map);
    }

    /* access modifiers changed from: protected */
    public final String i() throws Exception {
        String str;
        HttpResponse j = j();
        String e = j.e();
        if (e == null || e.length() <= 0) {
            byte[] b = IOUtils.b(j.b());
            str = new String(b, Charset.forName(a(b)));
        } else {
            str = IOUtils.b(j.b(), e.toUpperCase());
        }
        j.g();
        return str;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003a, code lost:
        r4 = r4.toUpperCase();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.lang.String a(byte[] r4) {
        /*
            java.lang.String r0 = new java.lang.String
            int r1 = r4.length
            r2 = 500(0x1f4, float:7.0E-43)
            if (r1 <= r2) goto L_0x0008
            goto L_0x0009
        L_0x0008:
            int r2 = r4.length
        L_0x0009:
            java.lang.String r1 = "UTF-8"
            java.nio.charset.Charset r1 = java.nio.charset.Charset.forName(r1)
            r3 = 0
            r0.<init>(r4, r3, r2, r1)
            java.lang.String r4 = r0.toUpperCase()
            java.lang.String r0 = "CONTENT-TYPE"
            int r0 = r4.indexOf(r0)
            if (r0 < 0) goto L_0x0037
            r0 = 10
            int r1 = r4.lastIndexOf(r0)
            if (r1 >= 0) goto L_0x0028
            r1 = 0
        L_0x0028:
            int r0 = r4.indexOf(r0)
            if (r0 >= 0) goto L_0x0032
            int r0 = r4.length()
        L_0x0032:
            java.lang.String r4 = r4.substring(r1, r0)
            goto L_0x0038
        L_0x0037:
            r4 = 0
        L_0x0038:
            if (r4 == 0) goto L_0x006e
            java.lang.String r4 = r4.toUpperCase()
            java.lang.String r0 = "CHARSET="
            int r0 = r4.indexOf(r0)
            if (r0 < 0) goto L_0x006e
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            int r0 = r0 + 8
        L_0x004d:
            int r2 = r4.length()
            if (r0 >= r2) goto L_0x0069
            char r2 = r4.charAt(r0)
            r3 = 39
            if (r2 == r3) goto L_0x0063
            r3 = 34
            if (r2 == r3) goto L_0x0063
            r3 = 32
            if (r2 != r3) goto L_0x0066
        L_0x0063:
            r1.append(r2)
        L_0x0066:
            int r0 = r0 + 1
            goto L_0x004d
        L_0x0069:
            java.lang.String r4 = r1.toString()
            return r4
        L_0x006e:
            java.lang.String r4 = "UTF-8"
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: miuipub.util.async.tasks.HttpTextTask.a(byte[]):java.lang.String");
    }
}
