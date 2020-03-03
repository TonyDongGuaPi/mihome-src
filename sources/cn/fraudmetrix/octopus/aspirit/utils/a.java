package cn.fraudmetrix.octopus.aspirit.utils;

import cn.fraudmetrix.octopus.aspirit.main.OctopusManager;
import com.tencent.smtt.sdk.CookieManager;
import java.io.File;

public class a {

    /* renamed from: a  reason: collision with root package name */
    private static final a f651a = new a();

    private a() {
    }

    public static synchronized a a() {
        a aVar;
        synchronized (a.class) {
            aVar = f651a;
        }
        return aVar;
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x004c A[SYNTHETIC, Splitter:B:30:0x004c] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0056 A[SYNTHETIC, Splitter:B:35:0x0056] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0062 A[SYNTHETIC, Splitter:B:43:0x0062] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x006c A[SYNTHETIC, Splitter:B:48:0x006c] */
    /* JADX WARNING: Removed duplicated region for block: B:54:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(java.io.File r10, java.io.File r11) {
        /*
            r9 = this;
            r0 = 0
            boolean r1 = r11.exists()     // Catch:{ IOException -> 0x005f, all -> 0x0048 }
            if (r1 == 0) goto L_0x000a
            r11.delete()     // Catch:{ IOException -> 0x005f, all -> 0x0048 }
        L_0x000a:
            r11.createNewFile()     // Catch:{ IOException -> 0x005f, all -> 0x0048 }
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ IOException -> 0x005f, all -> 0x0048 }
            r1.<init>(r10)     // Catch:{ IOException -> 0x005f, all -> 0x0048 }
            java.nio.channels.FileChannel r10 = r1.getChannel()     // Catch:{ IOException -> 0x005f, all -> 0x0048 }
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0045, all -> 0x003f }
            r1.<init>(r11)     // Catch:{ IOException -> 0x0045, all -> 0x003f }
            java.nio.channels.FileChannel r11 = r1.getChannel()     // Catch:{ IOException -> 0x0045, all -> 0x003f }
            r3 = 0
            long r5 = r10.size()     // Catch:{ IOException -> 0x0046, all -> 0x003a }
            r2 = r10
            r7 = r11
            r2.transferTo(r3, r5, r7)     // Catch:{ IOException -> 0x0046, all -> 0x003a }
            if (r10 == 0) goto L_0x0034
            r10.close()     // Catch:{ IOException -> 0x0030 }
            goto L_0x0034
        L_0x0030:
            r10 = move-exception
            r10.printStackTrace()
        L_0x0034:
            if (r11 == 0) goto L_0x0074
            r11.close()     // Catch:{ IOException -> 0x0070 }
            goto L_0x0074
        L_0x003a:
            r0 = move-exception
            r8 = r0
            r0 = r10
            r10 = r8
            goto L_0x004a
        L_0x003f:
            r11 = move-exception
            r8 = r0
            r0 = r10
            r10 = r11
            r11 = r8
            goto L_0x004a
        L_0x0045:
            r11 = r0
        L_0x0046:
            r0 = r10
            goto L_0x0060
        L_0x0048:
            r10 = move-exception
            r11 = r0
        L_0x004a:
            if (r0 == 0) goto L_0x0054
            r0.close()     // Catch:{ IOException -> 0x0050 }
            goto L_0x0054
        L_0x0050:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0054:
            if (r11 == 0) goto L_0x005e
            r11.close()     // Catch:{ IOException -> 0x005a }
            goto L_0x005e
        L_0x005a:
            r11 = move-exception
            r11.printStackTrace()
        L_0x005e:
            throw r10
        L_0x005f:
            r11 = r0
        L_0x0060:
            if (r0 == 0) goto L_0x006a
            r0.close()     // Catch:{ IOException -> 0x0066 }
            goto L_0x006a
        L_0x0066:
            r10 = move-exception
            r10.printStackTrace()
        L_0x006a:
            if (r11 == 0) goto L_0x0074
            r11.close()     // Catch:{ IOException -> 0x0070 }
            goto L_0x0074
        L_0x0070:
            r10 = move-exception
            r10.printStackTrace()
        L_0x0074:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.fraudmetrix.octopus.aspirit.utils.a.a(java.io.File, java.io.File):void");
    }

    private File d() {
        return OctopusManager.b().c().getDir("webview", 0);
    }

    private File e() {
        return new File(d().getPath(), "Cookies");
    }

    private File f() {
        return new File(d().getPath(), "Cookies.bak");
    }

    private void g() {
        CookieManager.getInstance().removeAllCookie();
    }

    public String a(String str) {
        return CookieManager.getInstance().getCookie(str);
    }

    public void b() {
        g();
        a(f(), e());
    }

    public void c() {
        a(e(), f());
        g();
    }
}
