package com.xiaomi.phonenum.http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import com.xiaomi.phonenum.bean.HttpError;
import com.xiaomi.phonenum.phone.PhoneInfoManager;
import com.xiaomi.phonenum.phone.PhoneUtil;
import com.xiaomi.phonenum.utils.Logger;
import com.xiaomi.phonenum.utils.LoggerManager;
import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;

public class UrlConnHttpFactory extends HttpFactory {

    /* renamed from: a  reason: collision with root package name */
    private static CookieManager f12563a = new CookieManager();
    /* access modifiers changed from: private */
    public PhoneUtil b;
    /* access modifiers changed from: private */
    public Logger c = LoggerManager.a();
    /* access modifiers changed from: private */
    public Context d;

    static {
        f12563a.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
    }

    public UrlConnHttpFactory(Context context) {
        this.d = context;
        this.b = PhoneInfoManager.a(context);
        CookieHandler.setDefault(f12563a);
    }

    public HttpClient a(HttpClientConfig httpClientConfig) {
        return new HttpUrlConnClient(httpClientConfig);
    }

    private class HttpUrlConnClient implements HttpClient {
        private static final String b = "HttpUrlConnClient";
        private HttpClientConfig c;

        private HttpUrlConnClient(HttpClientConfig httpClientConfig) {
            this.c = httpClientConfig;
        }

        public Response a(Request request) throws IOException {
            Network network;
            long uptimeMillis = SystemClock.uptimeMillis();
            if (this.c.e >= 0) {
                if (!UrlConnHttpFactory.this.b.b(this.c.e)) {
                    return HttpError.DATA_NOT_ENABLED.result();
                }
                if (!UrlConnHttpFactory.this.b.b()) {
                    if (!UrlConnHttpFactory.this.b.a("android.permission.CHANGE_NETWORK_STATE")) {
                        return HttpError.NO_CHANGE_NETWORK_STATE_PERMISSION.result();
                    }
                    network = a();
                    if (network == null) {
                        return HttpError.CELLULAR_NETWORK_NOT_AVAILABLE.result();
                    }
                    return a(request, a(request.f12559a, network)).a(SystemClock.uptimeMillis() - uptimeMillis).a();
                }
            }
            network = null;
            return a(request, a(request.f12559a, network)).a(SystemClock.uptimeMillis() - uptimeMillis).a();
        }

        /* JADX WARNING: Removed duplicated region for block: B:34:0x00e1 A[SYNTHETIC, Splitter:B:34:0x00e1] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private com.xiaomi.phonenum.http.Response.Builder a(com.xiaomi.phonenum.http.Request r5, java.net.HttpURLConnection r6) throws java.io.IOException {
            /*
                r4 = this;
                com.xiaomi.phonenum.http.HttpClientConfig r0 = r4.c     // Catch:{ all -> 0x00e9 }
                long r0 = r0.f12557a     // Catch:{ all -> 0x00e9 }
                int r0 = (int) r0     // Catch:{ all -> 0x00e9 }
                r6.setConnectTimeout(r0)     // Catch:{ all -> 0x00e9 }
                com.xiaomi.phonenum.http.HttpClientConfig r0 = r4.c     // Catch:{ all -> 0x00e9 }
                long r0 = r0.b     // Catch:{ all -> 0x00e9 }
                int r0 = (int) r0     // Catch:{ all -> 0x00e9 }
                r6.setReadTimeout(r0)     // Catch:{ all -> 0x00e9 }
                java.util.Map<java.lang.String, java.lang.String> r0 = r5.d     // Catch:{ all -> 0x00e9 }
                if (r0 == 0) goto L_0x0021
                r0 = 1
                r6.setDoInput(r0)     // Catch:{ all -> 0x00e9 }
                r6.setDoOutput(r0)     // Catch:{ all -> 0x00e9 }
                java.lang.String r0 = "POST"
                r6.setRequestMethod(r0)     // Catch:{ all -> 0x00e9 }
                goto L_0x0026
            L_0x0021:
                java.lang.String r0 = "GET"
                r6.setRequestMethod(r0)     // Catch:{ all -> 0x00e9 }
            L_0x0026:
                boolean r0 = r5.e     // Catch:{ all -> 0x00e9 }
                r6.setInstanceFollowRedirects(r0)     // Catch:{ all -> 0x00e9 }
                java.util.Map<java.lang.String, java.lang.String> r0 = r5.c     // Catch:{ all -> 0x00e9 }
                if (r0 == 0) goto L_0x0055
                java.util.Map<java.lang.String, java.lang.String> r0 = r5.c     // Catch:{ all -> 0x00e9 }
                java.util.Set r0 = r0.entrySet()     // Catch:{ all -> 0x00e9 }
                java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x00e9 }
            L_0x0039:
                boolean r1 = r0.hasNext()     // Catch:{ all -> 0x00e9 }
                if (r1 == 0) goto L_0x0055
                java.lang.Object r1 = r0.next()     // Catch:{ all -> 0x00e9 }
                java.util.Map$Entry r1 = (java.util.Map.Entry) r1     // Catch:{ all -> 0x00e9 }
                java.lang.Object r2 = r1.getKey()     // Catch:{ all -> 0x00e9 }
                java.lang.String r2 = (java.lang.String) r2     // Catch:{ all -> 0x00e9 }
                java.lang.Object r1 = r1.getValue()     // Catch:{ all -> 0x00e9 }
                java.lang.String r1 = (java.lang.String) r1     // Catch:{ all -> 0x00e9 }
                r6.setRequestProperty(r2, r1)     // Catch:{ all -> 0x00e9 }
                goto L_0x0039
            L_0x0055:
                r6.connect()     // Catch:{ all -> 0x00e9 }
                java.util.Map<java.lang.String, java.lang.String> r0 = r5.d     // Catch:{ all -> 0x00e9 }
                if (r0 == 0) goto L_0x007e
                java.io.OutputStream r0 = r6.getOutputStream()     // Catch:{ all -> 0x00e9 }
                java.io.BufferedWriter r1 = new java.io.BufferedWriter     // Catch:{ all -> 0x00e9 }
                java.io.OutputStreamWriter r2 = new java.io.OutputStreamWriter     // Catch:{ all -> 0x00e9 }
                java.lang.String r3 = "UTF-8"
                r2.<init>(r0, r3)     // Catch:{ all -> 0x00e9 }
                r1.<init>(r2)     // Catch:{ all -> 0x00e9 }
                java.util.Map<java.lang.String, java.lang.String> r5 = r5.d     // Catch:{ all -> 0x00e9 }
                java.lang.String r5 = com.xiaomi.phonenum.utils.MapUtil.a((java.util.Map<java.lang.String, java.lang.String>) r5)     // Catch:{ all -> 0x00e9 }
                r1.write(r5)     // Catch:{ all -> 0x00e9 }
                r1.flush()     // Catch:{ all -> 0x00e9 }
                r1.close()     // Catch:{ all -> 0x00e9 }
                r0.close()     // Catch:{ all -> 0x00e9 }
            L_0x007e:
                int r5 = r6.getResponseCode()     // Catch:{ all -> 0x00e9 }
                com.xiaomi.phonenum.http.Response$Builder r0 = new com.xiaomi.phonenum.http.Response$Builder     // Catch:{ all -> 0x00e9 }
                r0.<init>()     // Catch:{ all -> 0x00e9 }
                com.xiaomi.phonenum.http.Response$Builder r0 = r0.a((int) r5)     // Catch:{ all -> 0x00e9 }
                java.lang.String r1 = "Location"
                java.lang.String r1 = r6.getHeaderField(r1)     // Catch:{ all -> 0x00e9 }
                com.xiaomi.phonenum.http.Response$Builder r0 = r0.c(r1)     // Catch:{ all -> 0x00e9 }
                java.lang.String r1 = "Set-Cookie"
                java.lang.String r1 = r6.getHeaderField(r1)     // Catch:{ all -> 0x00e9 }
                com.xiaomi.phonenum.http.Response$Builder r0 = r0.b(r1)     // Catch:{ all -> 0x00e9 }
                java.util.Map r1 = r6.getHeaderFields()     // Catch:{ all -> 0x00e9 }
                com.xiaomi.phonenum.http.Response$Builder r0 = r0.a((java.util.Map<java.lang.String, java.util.List<java.lang.String>>) r1)     // Catch:{ all -> 0x00e9 }
                r1 = 200(0xc8, float:2.8E-43)
                if (r5 != r1) goto L_0x00e5
                r5 = 0
                java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ all -> 0x00dc }
                java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ all -> 0x00dc }
                java.io.InputStream r3 = r6.getInputStream()     // Catch:{ all -> 0x00dc }
                r2.<init>(r3)     // Catch:{ all -> 0x00dc }
                r3 = 1024(0x400, float:1.435E-42)
                r1.<init>(r2, r3)     // Catch:{ all -> 0x00dc }
                java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x00da }
                r5.<init>()     // Catch:{ all -> 0x00da }
            L_0x00c1:
                java.lang.String r2 = r1.readLine()     // Catch:{ all -> 0x00da }
                if (r2 == 0) goto L_0x00cb
                r5.append(r2)     // Catch:{ all -> 0x00da }
                goto L_0x00c1
            L_0x00cb:
                java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x00da }
                com.xiaomi.phonenum.http.Response$Builder r5 = r0.a((java.lang.String) r5)     // Catch:{ all -> 0x00da }
                r1.close()     // Catch:{ all -> 0x00e9 }
                r6.disconnect()
                return r5
            L_0x00da:
                r5 = move-exception
                goto L_0x00df
            L_0x00dc:
                r0 = move-exception
                r1 = r5
                r5 = r0
            L_0x00df:
                if (r1 == 0) goto L_0x00e4
                r1.close()     // Catch:{ all -> 0x00e9 }
            L_0x00e4:
                throw r5     // Catch:{ all -> 0x00e9 }
            L_0x00e5:
                r6.disconnect()
                return r0
            L_0x00e9:
                r5 = move-exception
                r6.disconnect()
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.phonenum.http.UrlConnHttpFactory.HttpUrlConnClient.a(com.xiaomi.phonenum.http.Request, java.net.HttpURLConnection):com.xiaomi.phonenum.http.Response$Builder");
        }

        private HttpURLConnection a(String str, Network network) throws IOException {
            if (network == null || Build.VERSION.SDK_INT < 21) {
                return (HttpURLConnection) new URL(str).openConnection();
            }
            return (HttpURLConnection) network.openConnection(new URL(str));
        }

        private Network a() {
            try {
                if (Build.VERSION.SDK_INT >= 21) {
                    return a(UrlConnHttpFactory.this.d, this.c.d, this.c.e);
                }
                return null;
            } catch (InterruptedException e) {
                UrlConnHttpFactory.this.c.a(b, "waitForCellular", e);
                return null;
            } catch (TimeoutException e2) {
                Logger c2 = UrlConnHttpFactory.this.c;
                c2.a(b, "waitForCellular Timeout " + this.c.d, e2);
                return null;
            }
        }

        @RequiresApi(api = 21)
        private Network a(Context context, long j, int i) throws InterruptedException, TimeoutException {
            final ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            final AtomicReference atomicReference = new AtomicReference((Object) null);
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            connectivityManager.requestNetwork(new NetworkRequest.Builder().addCapability(12).addTransportType(0).setNetworkSpecifier(String.valueOf(i)).build(), new ConnectivityManager.NetworkCallback() {
                public void onAvailable(Network network) {
                    NetworkInfo networkInfo = connectivityManager.getNetworkInfo(network);
                    if (networkInfo != null && networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                        atomicReference.set(network);
                        countDownLatch.countDown();
                    }
                }
            });
            if (countDownLatch.await(j, TimeUnit.MILLISECONDS)) {
                return (Network) atomicReference.get();
            }
            throw new TimeoutException();
        }
    }
}
