package com.xiaomi.smarthome.library.common.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Looper;
import com.alipay.sdk.sys.a;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.Parser;
import com.xiaomi.smarthome.frame.ErrorCode;
import com.xiaomi.smarthome.framework.api.AsyncResponseCallback;
import com.xiaomi.smarthome.library.apache.http.NameValuePair;
import com.xiaomi.smarthome.library.common.util.XMStringUtils;
import com.xiaomi.smarthome.library.crypto.Base64Coder;
import com.xiaomi.smarthome.library.http.ClientUtil;
import com.xiaomi.smarthome.library.http.Error;
import com.xiaomi.smarthome.library.http.HttpApi;
import com.xiaomi.smarthome.library.http.Request2;
import com.xiaomi.smarthome.library.http.async.AsyncHandler;
import com.xiaomi.smarthome.library.http.async.TextAsyncHandler;
import com.xiaomi.smarthome.library.http.sync.TextSyncHandler;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import org.aspectj.runtime.internal.AroundClosure;
import org.json.JSONException;

public class NetworkUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final int f18637a = 1;
    public static final int b = 2;
    public static final int c = 3;
    public static String d = (e + f + g);
    private static String e = "8007236f-";
    private static String f = "a2d6-4847-ac83-";
    private static String g = "c49395ad6d65";
    private static OkHttpClient h = ClientUtil.a();
    private static HashMap<String, String> i = new HashMap<>();

    public class AjcClosure1 extends AroundClosure {
        public AjcClosure1(Object[] objArr) {
            super(objArr);
        }

        public Object run(Object[] objArr) {
            return NetworkUtils.a((URL) this.state[0]);
        }
    }

    public class AjcClosure3 extends AroundClosure {
        public AjcClosure3(Object[] objArr) {
            super(objArr);
        }

        public Object run(Object[] objArr) {
            return NetworkUtils.b((URL) this.state[0]);
        }
    }

    public class AjcClosure5 extends AroundClosure {
        public AjcClosure5(Object[] objArr) {
            super(objArr);
        }

        public Object run(Object[] objArr) {
            return NetworkUtils.c((URL) this.state[0]);
        }
    }

    public class AjcClosure7 extends AroundClosure {
        public AjcClosure7(Object[] objArr) {
            super(objArr);
        }

        public Object run(Object[] objArr) {
            return NetworkUtils.d((URL) this.state[0]);
        }
    }

    public interface JSONParser<T> {
        T a(String str) throws JSONException;
    }

    public interface OnDownloadProgress {
        void a();

        void a(long j, long j2);

        void a(String str);

        void b();
    }

    public static <T> void a(String str, String str2, List<NameValuePair> list, final JSONParser<T> jSONParser, final AsyncResponseCallback<T> asyncResponseCallback) {
        Request2 a2 = new Request2.Builder().a(str2).b(str).a(list).a();
        if (Looper.myLooper() == null) {
            try {
                String str3 = (String) HttpApi.a(h, a2, new TextSyncHandler());
                T t = null;
                if (jSONParser != null) {
                    try {
                        t = jSONParser.a(str3);
                    } catch (Exception unused) {
                        if (asyncResponseCallback != null) {
                            asyncResponseCallback.a(ErrorCode.INVALID.getCode());
                            return;
                        }
                        return;
                    }
                }
                if (asyncResponseCallback != null) {
                    asyncResponseCallback.a(t);
                }
            } catch (Exception e2) {
                if (asyncResponseCallback != null) {
                    asyncResponseCallback.a(ErrorCode.INVALID.getCode(), e2.getMessage());
                }
            }
        } else {
            try {
                HttpApi.a(h, a2, (AsyncHandler) new TextAsyncHandler() {
                    /* renamed from: a */
                    public void onSuccess(String str, Response response) {
                        Object obj = null;
                        try {
                            if (jSONParser != null) {
                                obj = jSONParser.a(str);
                            }
                            if (asyncResponseCallback != null) {
                                asyncResponseCallback.a(obj);
                            }
                        } catch (Exception e) {
                            if (asyncResponseCallback != null) {
                                asyncResponseCallback.a(ErrorCode.INVALID.getCode(), e.getMessage());
                            }
                        }
                    }

                    public void onFailure(Error error, Exception exc, Response response) {
                        if (asyncResponseCallback != null) {
                            asyncResponseCallback.a(ErrorCode.INVALID.getCode());
                        }
                    }
                });
            } catch (Exception e3) {
                if (asyncResponseCallback != null) {
                    asyncResponseCallback.a(ErrorCode.INVALID.getCode(), e3.getMessage());
                }
            }
        }
    }

    public static <T> void a(String str, String str2, List<NameValuePair> list, final Callback<T> callback, final Parser<T> parser) {
        a(str, str2, list, new JSONParser<T>() {
            public T a(String str) throws JSONException {
                if (parser != null) {
                    return parser.parse(str);
                }
                return null;
            }
        }, new AsyncResponseCallback<T>() {
            public void a(T t) {
                if (callback != null) {
                    callback.onSuccess(t);
                }
            }

            public void a(int i) {
                if (callback != null) {
                    callback.onFailure(i, "");
                }
            }

            public void a(int i, Object obj) {
                if (callback != null) {
                    callback.onFailure(i, obj == null ? "" : obj.toString());
                }
            }
        });
    }

    public static String a(List<NameValuePair> list) {
        Collections.sort(list, new Comparator<NameValuePair>() {
            /* renamed from: a */
            public int compare(NameValuePair nameValuePair, NameValuePair nameValuePair2) {
                return nameValuePair.a().compareTo(nameValuePair2.a());
            }
        });
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (NameValuePair next : list) {
            if (!z) {
                sb.append(a.b);
            }
            sb.append(next.a());
            sb.append("=");
            sb.append(next.b());
            z = false;
        }
        sb.append(a.b);
        sb.append(e);
        sb.append(f);
        sb.append(g);
        return XMStringUtils.d(Base64Coder.a(XMStringUtils.e(sb.toString())));
    }

    public static synchronized String a(Context context) {
        synchronized (NetworkUtils.class) {
        }
        return "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.7; rv:20.0) Gecko/20100101 Firefox/20.0";
    }

    public static void a(HttpURLConnection httpURLConnection) {
        httpURLConnection.setConnectTimeout(10000);
        httpURLConnection.setReadTimeout(15000);
    }

    /* JADX WARNING: Removed duplicated region for block: B:49:0x01c9 A[SYNTHETIC, Splitter:B:49:0x01c9] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x01d4  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0203 A[SYNTHETIC, Splitter:B:60:0x0203] */
    /* JADX WARNING: Removed duplicated region for block: B:69:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] a(java.lang.String r13) {
        /*
            long r0 = java.lang.System.currentTimeMillis()
            r2 = 1
            r3 = 0
            r4 = 0
            java.net.URL r6 = new java.net.URL     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            r6.<init>(r13)     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            java.lang.String r7 = r6.getHost()     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            java.net.HttpURLConnection.setFollowRedirects(r2)     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            android.content.Context r8 = com.xiaomi.smarthome.application.CommonApplication.getAppContext()     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            boolean r8 = com.xiaomi.smarthome.library.common.network.Network.c((android.content.Context) r8)     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            if (r8 == 0) goto L_0x0037
            java.lang.String r6 = com.xiaomi.smarthome.library.common.network.Network.a((java.net.URL) r6)     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            java.net.URL r8 = new java.net.URL     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            r8.<init>(r6)     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            com.xiaomi.miot.support.monitor.aop.trace.TraceNetTrafficMonitor r6 = com.xiaomi.miot.support.monitor.aop.trace.TraceNetTrafficMonitor.b()     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            java.net.URLConnection r6 = r6.b(r8)     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            java.net.HttpURLConnection r6 = (java.net.HttpURLConnection) r6     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            java.lang.String r8 = "X-Online-Host"
            r6.setRequestProperty(r8, r7)     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            goto L_0x0041
        L_0x0037:
            com.xiaomi.miot.support.monitor.aop.trace.TraceNetTrafficMonitor r7 = com.xiaomi.miot.support.monitor.aop.trace.TraceNetTrafficMonitor.b()     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            java.net.URLConnection r6 = r7.b(r6)     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            java.net.HttpURLConnection r6 = (java.net.HttpURLConnection) r6     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
        L_0x0041:
            java.lang.String r7 = "User-Agent"
            android.content.Context r8 = com.xiaomi.smarthome.application.CommonApplication.getAppContext()     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            java.lang.String r8 = a((android.content.Context) r8)     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            r6.setRequestProperty(r7, r8)     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            java.lang.String r7 = "Connection"
            java.lang.String r8 = "Keep-Alive"
            r6.setRequestProperty(r7, r8)     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            java.util.HashMap<java.lang.String, java.lang.String> r7 = i     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            java.util.Set r7 = r7.entrySet()     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            java.util.Iterator r7 = r7.iterator()     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
        L_0x005f:
            boolean r8 = r7.hasNext()     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            if (r8 == 0) goto L_0x007b
            java.lang.Object r8 = r7.next()     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            java.util.Map$Entry r8 = (java.util.Map.Entry) r8     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            java.lang.Object r9 = r8.getKey()     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            java.lang.Object r8 = r8.getValue()     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            java.lang.String r8 = (java.lang.String) r8     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            r6.setRequestProperty(r9, r8)     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            goto L_0x005f
        L_0x007b:
            a((java.net.HttpURLConnection) r6)     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            r6.connect()     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            int r7 = r6.getResponseCode()     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            r8.<init>()     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            java.lang.String r9 = "the response code is "
            r8.append(r9)     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            java.lang.String r9 = "content-length"
            r10 = -1
            int r9 = r6.getHeaderFieldInt(r9, r10)     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            r8.append(r9)     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            java.lang.String r9 = ", connected in "
            r8.append(r9)     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            long r11 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            r9 = 0
            long r11 = r11 - r0
            r8.append(r11)     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            java.lang.String r8 = r8.toString()     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            com.xiaomi.smarthome.framework.log.MyLog.c(r8)     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            if (r7 != r10) goto L_0x00da
            long r6 = java.lang.System.currentTimeMillis()
            long r6 = r6 - r0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "http downloadFile to "
            r0.append(r1)
            r0.append(r13)
            java.lang.String r13 = " cost "
            r0.append(r13)
            r0.append(r6)
            java.lang.String r13 = "ms, download size = "
            r0.append(r13)
            r0.append(r4)
            java.lang.String r13 = r0.toString()
            com.xiaomi.smarthome.framework.log.MyLog.c(r13)
            return r3
        L_0x00da:
            int r7 = r6.getContentLength()     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            com.xiaomi.smarthome.library.common.network.Network$DoneHandlerInputStream r8 = new com.xiaomi.smarthome.library.common.network.Network$DoneHandlerInputStream     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            java.io.InputStream r6 = r6.getInputStream()     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            r8.<init>(r6)     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0176 }
            r6.<init>()     // Catch:{ Exception -> 0x0176 }
            java.lang.String r9 = "content bytes "
            r6.append(r9)     // Catch:{ Exception -> 0x0176 }
            r6.append(r7)     // Catch:{ Exception -> 0x0176 }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x0176 }
            com.xiaomi.smarthome.framework.log.MyLog.c(r6)     // Catch:{ Exception -> 0x0176 }
            if (r7 >= 0) goto L_0x012f
            long r6 = java.lang.System.currentTimeMillis()
            long r6 = r6 - r0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "http downloadFile to "
            r0.append(r1)
            r0.append(r13)
            java.lang.String r13 = " cost "
            r0.append(r13)
            r0.append(r6)
            java.lang.String r13 = "ms, download size = "
            r0.append(r13)
            r0.append(r4)
            java.lang.String r13 = r0.toString()
            com.xiaomi.smarthome.framework.log.MyLog.c(r13)
            r8.close()     // Catch:{ IOException -> 0x012a }
            goto L_0x012e
        L_0x012a:
            r13 = move-exception
            com.xiaomi.smarthome.framework.log.MyLog.a((java.lang.Throwable) r13)
        L_0x012e:
            return r3
        L_0x012f:
            byte[] r6 = new byte[r7]     // Catch:{ Exception -> 0x0176 }
        L_0x0131:
            long r11 = (long) r7
            int r9 = (r4 > r11 ? 1 : (r4 == r11 ? 0 : -1))
            if (r9 >= 0) goto L_0x0144
            int r9 = (int) r4
            long r11 = r11 - r4
            int r11 = (int) r11
            int r9 = r8.read(r6, r9, r11)     // Catch:{ Exception -> 0x0142 }
            if (r9 == r10) goto L_0x0144
            long r11 = (long) r9
            long r4 = r4 + r11
            goto L_0x0131
        L_0x0142:
            r2 = move-exception
            goto L_0x017e
        L_0x0144:
            long r9 = java.lang.System.currentTimeMillis()
            long r9 = r9 - r0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "http downloadFile to "
            r0.append(r1)
            r0.append(r13)
            java.lang.String r13 = " cost "
            r0.append(r13)
            r0.append(r9)
            java.lang.String r13 = "ms, download size = "
            r0.append(r13)
            r0.append(r4)
            java.lang.String r13 = r0.toString()
            com.xiaomi.smarthome.framework.log.MyLog.c(r13)
            r8.close()     // Catch:{ IOException -> 0x0171 }
            goto L_0x01d2
        L_0x0171:
            r13 = move-exception
            com.xiaomi.smarthome.framework.log.MyLog.a((java.lang.Throwable) r13)
            goto L_0x01d2
        L_0x0176:
            r2 = move-exception
            r6 = r3
            goto L_0x017e
        L_0x0179:
            r2 = move-exception
            goto L_0x01d8
        L_0x017b:
            r2 = move-exception
            r6 = r3
            r8 = r6
        L_0x017e:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x01d6 }
            r7.<init>()     // Catch:{ all -> 0x01d6 }
            java.lang.String r9 = "error to call url:"
            r7.append(r9)     // Catch:{ all -> 0x01d6 }
            r7.append(r13)     // Catch:{ all -> 0x01d6 }
            java.lang.String r9 = " error:"
            r7.append(r9)     // Catch:{ all -> 0x01d6 }
            java.lang.String r9 = r2.getMessage()     // Catch:{ all -> 0x01d6 }
            r7.append(r9)     // Catch:{ all -> 0x01d6 }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x01d6 }
            com.xiaomi.smarthome.framework.log.MyLog.a((java.lang.String) r7, (java.lang.Throwable) r2)     // Catch:{ all -> 0x01d6 }
            long r9 = java.lang.System.currentTimeMillis()
            long r9 = r9 - r0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "http downloadFile to "
            r0.append(r1)
            r0.append(r13)
            java.lang.String r13 = " cost "
            r0.append(r13)
            r0.append(r9)
            java.lang.String r13 = "ms, download size = "
            r0.append(r13)
            r0.append(r4)
            java.lang.String r13 = r0.toString()
            com.xiaomi.smarthome.framework.log.MyLog.c(r13)
            if (r8 == 0) goto L_0x01d1
            r8.close()     // Catch:{ IOException -> 0x01cd }
            goto L_0x01d1
        L_0x01cd:
            r13 = move-exception
            com.xiaomi.smarthome.framework.log.MyLog.a((java.lang.Throwable) r13)
        L_0x01d1:
            r2 = 0
        L_0x01d2:
            if (r2 == 0) goto L_0x01d5
            r3 = r6
        L_0x01d5:
            return r3
        L_0x01d6:
            r2 = move-exception
            r3 = r8
        L_0x01d8:
            long r6 = java.lang.System.currentTimeMillis()
            long r6 = r6 - r0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "http downloadFile to "
            r0.append(r1)
            r0.append(r13)
            java.lang.String r13 = " cost "
            r0.append(r13)
            r0.append(r6)
            java.lang.String r13 = "ms, download size = "
            r0.append(r13)
            r0.append(r4)
            java.lang.String r13 = r0.toString()
            com.xiaomi.smarthome.framework.log.MyLog.c(r13)
            if (r3 == 0) goto L_0x020b
            r3.close()     // Catch:{ IOException -> 0x0207 }
            goto L_0x020b
        L_0x0207:
            r13 = move-exception
            com.xiaomi.smarthome.framework.log.MyLog.a((java.lang.Throwable) r13)
        L_0x020b:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.library.common.network.NetworkUtils.a(java.lang.String):byte[]");
    }

    static final URLConnection a(URL url) {
        return url.openConnection();
    }

    static final URLConnection b(URL url) {
        return url.openConnection();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0045, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x023c, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x023d, code lost:
        r20 = r7;
        r8 = r24;
        r5 = r0;
        r9 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x0244, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x0245, code lost:
        r20 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0046, code lost:
        r2 = r0;
        r20 = r7;
        r5 = null;
        r19 = null;
        r8 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x024f, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x0250, code lost:
        r20 = r7;
        r8 = r24;
        r5 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x0256, code lost:
        r7 = null;
        r11 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0050, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0051, code lost:
        r5 = r0;
        r20 = r7;
        r7 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x012b, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x012c, code lost:
        r5 = r0;
        r20 = r7;
        r9 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0130, code lost:
        r7 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x016b, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x016c, code lost:
        r5 = r0;
        r20 = r7;
        r9 = 0;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0045 A[ExcHandler: all (r0v46 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:8:0x002c] */
    /* JADX WARNING: Removed duplicated region for block: B:117:0x0244 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:5:0x0019] */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x02aa A[SYNTHETIC, Splitter:B:128:0x02aa] */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x02b5 A[SYNTHETIC, Splitter:B:133:0x02b5] */
    /* JADX WARNING: Removed duplicated region for block: B:139:0x02c1  */
    /* JADX WARNING: Removed duplicated region for block: B:144:0x02d2  */
    /* JADX WARNING: Removed duplicated region for block: B:145:0x02d4  */
    /* JADX WARNING: Removed duplicated region for block: B:153:0x030d A[SYNTHETIC, Splitter:B:153:0x030d] */
    /* JADX WARNING: Removed duplicated region for block: B:158:0x0318 A[SYNTHETIC, Splitter:B:158:0x0318] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00f6 A[SYNTHETIC, Splitter:B:37:0x00f6] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0133  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.xiaomi.smarthome.library.common.network.NetworkUtils.DownloadResponse a(android.content.Context r22, java.lang.String r23, java.io.File r24, com.xiaomi.smarthome.library.common.network.NetworkUtils.OnDownloadProgress r25, boolean r26, boolean r27) {
        /*
            r1 = r23
            r2 = r25
            boolean r3 = com.xiaomi.smarthome.library.common.util.SystemUtils.a()
            r6 = 0
            if (r3 != 0) goto L_0x0322
            boolean r3 = com.xiaomi.smarthome.library.common.network.Network.e((android.content.Context) r22)
            if (r3 != 0) goto L_0x0013
            goto L_0x0322
        L_0x0013:
            long r7 = java.lang.System.currentTimeMillis()
            r9 = 0
            java.net.URL r3 = new java.net.URL     // Catch:{ IOException -> 0x024f, all -> 0x0244 }
            r3.<init>(r1)     // Catch:{ IOException -> 0x024f, all -> 0x0244 }
            java.lang.String r11 = r3.getHost()     // Catch:{ IOException -> 0x024f, all -> 0x0244 }
            r12 = 1
            java.net.HttpURLConnection.setFollowRedirects(r12)     // Catch:{ IOException -> 0x024f, all -> 0x0244 }
            boolean r13 = com.xiaomi.smarthome.library.common.network.Network.c((android.content.Context) r22)     // Catch:{ IOException -> 0x024f, all -> 0x0244 }
            if (r13 == 0) goto L_0x005c
            java.lang.String r3 = com.xiaomi.smarthome.library.common.network.Network.a((java.net.URL) r3)     // Catch:{ IOException -> 0x0050, all -> 0x0045 }
            java.net.URL r13 = new java.net.URL     // Catch:{ IOException -> 0x0050, all -> 0x0045 }
            r13.<init>(r3)     // Catch:{ IOException -> 0x0050, all -> 0x0045 }
            com.xiaomi.miot.support.monitor.aop.trace.TraceNetTrafficMonitor r3 = com.xiaomi.miot.support.monitor.aop.trace.TraceNetTrafficMonitor.b()     // Catch:{ IOException -> 0x0050, all -> 0x0045 }
            java.net.URLConnection r3 = r3.b(r13)     // Catch:{ IOException -> 0x0050, all -> 0x0045 }
            java.net.HttpURLConnection r3 = (java.net.HttpURLConnection) r3     // Catch:{ IOException -> 0x0050, all -> 0x0045 }
            java.lang.String r13 = "X-Online-Host"
            r3.setRequestProperty(r13, r11)     // Catch:{ IOException -> 0x0050, all -> 0x0045 }
            goto L_0x0066
        L_0x0045:
            r0 = move-exception
            r2 = r0
            r20 = r7
            r5 = 0
            r19 = 0
            r8 = r24
            goto L_0x02dd
        L_0x0050:
            r0 = move-exception
            r5 = r0
            r20 = r7
            r7 = 0
        L_0x0055:
            r11 = 0
        L_0x0056:
            r19 = 0
            r8 = r24
            goto L_0x025a
        L_0x005c:
            com.xiaomi.miot.support.monitor.aop.trace.TraceNetTrafficMonitor r11 = com.xiaomi.miot.support.monitor.aop.trace.TraceNetTrafficMonitor.b()     // Catch:{ IOException -> 0x024f, all -> 0x0244 }
            java.net.URLConnection r3 = r11.b(r3)     // Catch:{ IOException -> 0x024f, all -> 0x0244 }
            java.net.HttpURLConnection r3 = (java.net.HttpURLConnection) r3     // Catch:{ IOException -> 0x024f, all -> 0x0244 }
        L_0x0066:
            java.lang.String r11 = "User-Agent"
            java.lang.String r13 = a((android.content.Context) r22)     // Catch:{ IOException -> 0x024f, all -> 0x0244 }
            r3.setRequestProperty(r11, r13)     // Catch:{ IOException -> 0x024f, all -> 0x0244 }
            java.lang.String r11 = "Connection"
            java.lang.String r13 = "Keep-Alive"
            r3.setRequestProperty(r11, r13)     // Catch:{ IOException -> 0x024f, all -> 0x0244 }
            long r13 = r24.length()     // Catch:{ IOException -> 0x024f, all -> 0x0244 }
            if (r27 == 0) goto L_0x00c3
            int r11 = (r13 > r9 ? 1 : (r13 == r9 ? 0 : -1))
            if (r11 == 0) goto L_0x00bf
            java.lang.String r11 = "Range"
            java.lang.String r15 = "bytes=%d-"
            java.lang.Object[] r9 = new java.lang.Object[r12]     // Catch:{ IOException -> 0x00b7, all -> 0x0045 }
            java.lang.Long r10 = java.lang.Long.valueOf(r13)     // Catch:{ IOException -> 0x00b7, all -> 0x0045 }
            r9[r6] = r10     // Catch:{ IOException -> 0x00b7, all -> 0x0045 }
            java.lang.String r9 = java.lang.String.format(r15, r9)     // Catch:{ IOException -> 0x00b7, all -> 0x0045 }
            r3.setRequestProperty(r11, r9)     // Catch:{ IOException -> 0x00b7, all -> 0x0045 }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00b7, all -> 0x0045 }
            r9.<init>()     // Catch:{ IOException -> 0x00b7, all -> 0x0045 }
            java.lang.String r10 = "Range:"
            r9.append(r10)     // Catch:{ IOException -> 0x00b7, all -> 0x0045 }
            java.lang.String r10 = "bytes=%d-"
            java.lang.Object[] r11 = new java.lang.Object[r12]     // Catch:{ IOException -> 0x00b7, all -> 0x0045 }
            java.lang.Long r15 = java.lang.Long.valueOf(r13)     // Catch:{ IOException -> 0x00b7, all -> 0x0045 }
            r11[r6] = r15     // Catch:{ IOException -> 0x00b7, all -> 0x0045 }
            java.lang.String r10 = java.lang.String.format(r10, r11)     // Catch:{ IOException -> 0x00b7, all -> 0x0045 }
            r9.append(r10)     // Catch:{ IOException -> 0x00b7, all -> 0x0045 }
            java.lang.String r9 = r9.toString()     // Catch:{ IOException -> 0x00b7, all -> 0x0045 }
            com.xiaomi.smarthome.framework.log.MyLog.c(r9)     // Catch:{ IOException -> 0x00b7, all -> 0x0045 }
            r9 = r13
            goto L_0x00c6
        L_0x00b7:
            r0 = move-exception
            r5 = r0
            r20 = r7
            r7 = 0
            r9 = 0
            goto L_0x0055
        L_0x00bf:
            r9 = r13
        L_0x00c0:
            r13 = 0
            goto L_0x00c6
        L_0x00c3:
            r9 = 0
            goto L_0x00c0
        L_0x00c6:
            a((java.net.HttpURLConnection) r3)     // Catch:{ IOException -> 0x023c, all -> 0x0244 }
            r3.connect()     // Catch:{ IOException -> 0x023c, all -> 0x0244 }
            int r11 = r3.getResponseCode()     // Catch:{ IOException -> 0x023c, all -> 0x0244 }
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0233, all -> 0x0244 }
            r15.<init>()     // Catch:{ IOException -> 0x0233, all -> 0x0244 }
            java.lang.String r12 = "the response code is "
            r15.append(r12)     // Catch:{ IOException -> 0x0233, all -> 0x0244 }
            r15.append(r11)     // Catch:{ IOException -> 0x0233, all -> 0x0244 }
            java.lang.String r12 = ", connected in "
            r15.append(r12)     // Catch:{ IOException -> 0x0233, all -> 0x0244 }
            long r17 = java.lang.System.currentTimeMillis()     // Catch:{ IOException -> 0x0233, all -> 0x0244 }
            r12 = 0
            long r4 = r17 - r7
            r15.append(r4)     // Catch:{ IOException -> 0x0233, all -> 0x0244 }
            java.lang.String r4 = r15.toString()     // Catch:{ IOException -> 0x0233, all -> 0x0244 }
            com.xiaomi.smarthome.framework.log.MyLog.c(r4)     // Catch:{ IOException -> 0x0233, all -> 0x0244 }
            r4 = -1
            if (r11 != r4) goto L_0x0133
            com.xiaomi.smarthome.library.common.network.NetworkUtils$DownloadResponse r0 = new com.xiaomi.smarthome.library.common.network.NetworkUtils$DownloadResponse     // Catch:{ IOException -> 0x012b, all -> 0x0045 }
            r3 = 0
            r5 = 2
            r0.<init>(r4, r5, r6, r3)     // Catch:{ IOException -> 0x012b, all -> 0x0045 }
            long r2 = java.lang.System.currentTimeMillis()
            long r2 = r2 - r7
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "http downloadFile to "
            r4.append(r5)
            r4.append(r1)
            java.lang.String r1 = " cost "
            r4.append(r1)
            r4.append(r2)
            java.lang.String r1 = "ms, total size = "
            r4.append(r1)
            long r1 = r24.length()
            r4.append(r1)
            java.lang.String r1 = r4.toString()
            com.xiaomi.smarthome.framework.log.MyLog.c(r1)
            return r0
        L_0x012b:
            r0 = move-exception
            r5 = r0
            r20 = r7
            r9 = r13
        L_0x0130:
            r7 = 0
            goto L_0x0056
        L_0x0133:
            if (r27 == 0) goto L_0x0171
            r15 = 0
            int r5 = (r9 > r15 ? 1 : (r9 == r15 ? 0 : -1))
            if (r5 <= 0) goto L_0x0171
            r5 = 206(0xce, float:2.89E-43)
            if (r11 == r5) goto L_0x0171
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x012b, all -> 0x0045 }
            r0.<init>()     // Catch:{ IOException -> 0x012b, all -> 0x0045 }
            java.lang.String r5 = "expected response code is 206 while actual code is "
            r0.append(r5)     // Catch:{ IOException -> 0x012b, all -> 0x0045 }
            r0.append(r11)     // Catch:{ IOException -> 0x012b, all -> 0x0045 }
            java.lang.String r5 = " give up append"
            r0.append(r5)     // Catch:{ IOException -> 0x012b, all -> 0x0045 }
            java.lang.String r0 = r0.toString()     // Catch:{ IOException -> 0x012b, all -> 0x0045 }
            com.xiaomi.smarthome.framework.log.MyLog.a((java.lang.String) r0)     // Catch:{ IOException -> 0x012b, all -> 0x0045 }
            boolean r0 = r24.exists()     // Catch:{ IOException -> 0x016b, all -> 0x0045 }
            if (r0 == 0) goto L_0x0167
            boolean r0 = r24.isFile()     // Catch:{ IOException -> 0x016b, all -> 0x0045 }
            if (r0 == 0) goto L_0x0167
            com.xiaomi.smarthome.library.file.FileUtil.b((java.io.File) r24)     // Catch:{ IOException -> 0x016b, all -> 0x0045 }
        L_0x0167:
            r9 = r15
            r13 = r9
            r0 = 0
            goto L_0x0173
        L_0x016b:
            r0 = move-exception
            r5 = r0
            r20 = r7
            r9 = r15
            goto L_0x0130
        L_0x0171:
            r0 = r27
        L_0x0173:
            if (r0 == 0) goto L_0x017c
            int r5 = r3.getContentLength()     // Catch:{ IOException -> 0x012b, all -> 0x0045 }
            int r12 = (int) r9
            int r5 = r5 + r12
            goto L_0x0180
        L_0x017c:
            int r5 = r3.getContentLength()     // Catch:{ IOException -> 0x0233, all -> 0x0244 }
        L_0x0180:
            if (r2 == 0) goto L_0x018f
            r20 = r7
            long r6 = (long) r5
            r2.a(r9, r6)     // Catch:{ IOException -> 0x018c, all -> 0x0189 }
            goto L_0x0191
        L_0x0189:
            r0 = move-exception
            goto L_0x0247
        L_0x018c:
            r0 = move-exception
            goto L_0x0236
        L_0x018f:
            r20 = r7
        L_0x0191:
            com.xiaomi.smarthome.library.common.network.Network$DoneHandlerInputStream r6 = new com.xiaomi.smarthome.library.common.network.Network$DoneHandlerInputStream     // Catch:{ IOException -> 0x018c, all -> 0x0189 }
            java.io.InputStream r3 = r3.getInputStream()     // Catch:{ IOException -> 0x018c, all -> 0x0189 }
            r6.<init>(r3)     // Catch:{ IOException -> 0x018c, all -> 0x0189 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x022a, all -> 0x0221 }
            r3.<init>()     // Catch:{ IOException -> 0x022a, all -> 0x0221 }
            java.lang.String r7 = "content bytes "
            r3.append(r7)     // Catch:{ IOException -> 0x022a, all -> 0x0221 }
            r3.append(r5)     // Catch:{ IOException -> 0x022a, all -> 0x0221 }
            java.lang.String r3 = r3.toString()     // Catch:{ IOException -> 0x022a, all -> 0x0221 }
            com.xiaomi.smarthome.framework.log.MyLog.c(r3)     // Catch:{ IOException -> 0x022a, all -> 0x0221 }
            r3 = 10240(0x2800, float:1.4349E-41)
            byte[] r3 = new byte[r3]     // Catch:{ IOException -> 0x022a, all -> 0x0221 }
            java.io.FileOutputStream r7 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x022a, all -> 0x0221 }
            r8 = r24
            r7.<init>(r8, r0)     // Catch:{ IOException -> 0x021f, all -> 0x021d }
            r9 = r13
        L_0x01ba:
            int r0 = r6.read(r3)     // Catch:{ IOException -> 0x0218, all -> 0x0212 }
            if (r0 == r4) goto L_0x01cd
            r13 = 0
            r7.write(r3, r13, r0)     // Catch:{ IOException -> 0x0218, all -> 0x0212 }
            long r13 = (long) r0     // Catch:{ IOException -> 0x0218, all -> 0x0212 }
            long r9 = r9 + r13
            if (r2 == 0) goto L_0x01ba
            long r13 = (long) r5     // Catch:{ IOException -> 0x0218, all -> 0x0212 }
            r2.a(r9, r13)     // Catch:{ IOException -> 0x0218, all -> 0x0212 }
            goto L_0x01ba
        L_0x01cd:
            long r3 = java.lang.System.currentTimeMillis()
            long r3 = r3 - r20
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r5 = "http downloadFile to "
            r0.append(r5)
            r0.append(r1)
            java.lang.String r1 = " cost "
            r0.append(r1)
            r0.append(r3)
            java.lang.String r1 = "ms, total size = "
            r0.append(r1)
            long r3 = r24.length()
            r0.append(r3)
            java.lang.String r0 = r0.toString()
            com.xiaomi.smarthome.framework.log.MyLog.c(r0)
            r6.close()     // Catch:{ IOException -> 0x01ff }
            goto L_0x0204
        L_0x01ff:
            r0 = move-exception
            r1 = r0
            com.xiaomi.smarthome.framework.log.MyLog.a((java.lang.Throwable) r1)
        L_0x0204:
            r7.close()     // Catch:{ IOException -> 0x0209 }
            r6 = 1
            goto L_0x020f
        L_0x0209:
            r0 = move-exception
            r1 = r0
            com.xiaomi.smarthome.framework.log.MyLog.a((java.lang.Throwable) r1)
            r6 = 0
        L_0x020f:
            r5 = 0
            goto L_0x02bf
        L_0x0212:
            r0 = move-exception
            r2 = r0
            r19 = r6
            goto L_0x02dc
        L_0x0218:
            r0 = move-exception
            r5 = r0
            r19 = r6
            goto L_0x025a
        L_0x021d:
            r0 = move-exception
            goto L_0x0224
        L_0x021f:
            r0 = move-exception
            goto L_0x022d
        L_0x0221:
            r0 = move-exception
            r8 = r24
        L_0x0224:
            r2 = r0
            r19 = r6
            r5 = 0
            goto L_0x02dd
        L_0x022a:
            r0 = move-exception
            r8 = r24
        L_0x022d:
            r5 = r0
            r19 = r6
            r9 = r13
            r7 = 0
            goto L_0x025a
        L_0x0233:
            r0 = move-exception
            r20 = r7
        L_0x0236:
            r8 = r24
            r5 = r0
            r9 = r13
            r7 = 0
            goto L_0x0258
        L_0x023c:
            r0 = move-exception
            r20 = r7
            r8 = r24
            r5 = r0
            r9 = r13
            goto L_0x0256
        L_0x0244:
            r0 = move-exception
            r20 = r7
        L_0x0247:
            r8 = r24
            r2 = r0
            r5 = 0
            r19 = 0
            goto L_0x02dd
        L_0x024f:
            r0 = move-exception
            r20 = r7
            r15 = r9
            r8 = r24
            r5 = r0
        L_0x0256:
            r7 = 0
            r11 = 0
        L_0x0258:
            r19 = 0
        L_0x025a:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x02da }
            r0.<init>()     // Catch:{ all -> 0x02da }
            java.lang.String r3 = "error to call url:"
            r0.append(r3)     // Catch:{ all -> 0x02da }
            r0.append(r1)     // Catch:{ all -> 0x02da }
            java.lang.String r3 = " error:"
            r0.append(r3)     // Catch:{ all -> 0x02da }
            java.lang.String r3 = r5.getMessage()     // Catch:{ all -> 0x02da }
            r0.append(r3)     // Catch:{ all -> 0x02da }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x02da }
            com.xiaomi.smarthome.framework.log.MyLog.a((java.lang.String) r0, (java.lang.Throwable) r5)     // Catch:{ all -> 0x02da }
            long r3 = java.lang.System.currentTimeMillis()
            long r3 = r3 - r20
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r6 = "http downloadFile to "
            r0.append(r6)
            r0.append(r1)
            java.lang.String r1 = " cost "
            r0.append(r1)
            r0.append(r3)
            java.lang.String r1 = "ms, total size = "
            r0.append(r1)
            long r3 = r24.length()
            r0.append(r3)
            java.lang.String r0 = r0.toString()
            com.xiaomi.smarthome.framework.log.MyLog.c(r0)
            if (r19 == 0) goto L_0x02b3
            r19.close()     // Catch:{ IOException -> 0x02ae }
            goto L_0x02b3
        L_0x02ae:
            r0 = move-exception
            r1 = r0
            com.xiaomi.smarthome.framework.log.MyLog.a((java.lang.Throwable) r1)
        L_0x02b3:
            if (r7 == 0) goto L_0x02be
            r7.close()     // Catch:{ IOException -> 0x02b9 }
            goto L_0x02be
        L_0x02b9:
            r0 = move-exception
            r1 = r0
            com.xiaomi.smarthome.framework.log.MyLog.a((java.lang.Throwable) r1)
        L_0x02be:
            r6 = 0
        L_0x02bf:
            if (r2 == 0) goto L_0x02ce
            if (r6 != 0) goto L_0x02c7
            r25.b()
            goto L_0x02ce
        L_0x02c7:
            java.lang.String r0 = r24.getAbsolutePath()
            r2.a(r0)
        L_0x02ce:
            com.xiaomi.smarthome.library.common.network.NetworkUtils$DownloadResponse r0 = new com.xiaomi.smarthome.library.common.network.NetworkUtils$DownloadResponse
            if (r6 == 0) goto L_0x02d4
            r4 = 3
            goto L_0x02d5
        L_0x02d4:
            r4 = 2
        L_0x02d5:
            int r1 = (int) r9
            r0.<init>(r11, r4, r1, r5)
            return r0
        L_0x02da:
            r0 = move-exception
            r2 = r0
        L_0x02dc:
            r5 = r7
        L_0x02dd:
            long r3 = java.lang.System.currentTimeMillis()
            long r3 = r3 - r20
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r6 = "http downloadFile to "
            r0.append(r6)
            r0.append(r1)
            java.lang.String r1 = " cost "
            r0.append(r1)
            r0.append(r3)
            java.lang.String r1 = "ms, total size = "
            r0.append(r1)
            long r3 = r24.length()
            r0.append(r3)
            java.lang.String r0 = r0.toString()
            com.xiaomi.smarthome.framework.log.MyLog.c(r0)
            if (r19 == 0) goto L_0x0316
            r19.close()     // Catch:{ IOException -> 0x0311 }
            goto L_0x0316
        L_0x0311:
            r0 = move-exception
            r1 = r0
            com.xiaomi.smarthome.framework.log.MyLog.a((java.lang.Throwable) r1)
        L_0x0316:
            if (r5 == 0) goto L_0x0321
            r5.close()     // Catch:{ IOException -> 0x031c }
            goto L_0x0321
        L_0x031c:
            r0 = move-exception
            r1 = r0
            com.xiaomi.smarthome.framework.log.MyLog.a((java.lang.Throwable) r1)
        L_0x0321:
            throw r2
        L_0x0322:
            if (r2 == 0) goto L_0x0327
            r25.b()
        L_0x0327:
            com.xiaomi.smarthome.library.common.network.NetworkUtils$DownloadResponse r0 = new com.xiaomi.smarthome.library.common.network.NetworkUtils$DownloadResponse
            r1 = 0
            r2 = 0
            r3 = 2
            r0.<init>(r1, r3, r1, r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.library.common.network.NetworkUtils.a(android.content.Context, java.lang.String, java.io.File, com.xiaomi.smarthome.library.common.network.NetworkUtils$OnDownloadProgress, boolean, boolean):com.xiaomi.smarthome.library.common.network.NetworkUtils$DownloadResponse");
    }

    static final URLConnection c(URL url) {
        return url.openConnection();
    }

    static final URLConnection d(URL url) {
        return url.openConnection();
    }

    public static boolean a() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) CommonApplication.getAppContext().getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable();
    }

    public static boolean b() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) CommonApplication.getAppContext().getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || activeNetworkInfo.getType() != 1) {
            return false;
        }
        return true;
    }

    public static boolean c() {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) CommonApplication.getAppContext().getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
                return false;
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0021 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(byte[] r4) {
        /*
            r0 = 0
            byte r1 = r4[r0]
            r2 = 1
            byte r4 = r4[r2]
            r3 = -84
            if (r1 == r3) goto L_0x0014
            r3 = -64
            if (r1 == r3) goto L_0x001d
            r4 = 10
            if (r1 == r4) goto L_0x0013
            goto L_0x0021
        L_0x0013:
            return r2
        L_0x0014:
            r1 = 16
            if (r4 < r1) goto L_0x001d
            r1 = 31
            if (r4 > r1) goto L_0x001d
            return r2
        L_0x001d:
            r1 = -88
            if (r4 == r1) goto L_0x0022
        L_0x0021:
            return r0
        L_0x0022:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.library.common.network.NetworkUtils.a(byte[]):boolean");
    }

    public static class DownloadResponse {

        /* renamed from: a  reason: collision with root package name */
        public int f18641a;
        public int b;
        public int c;
        public Exception d;

        public DownloadResponse(int i, int i2, int i3, Exception exc) {
            this.f18641a = i;
            this.b = i2;
            this.c = i3;
            this.d = exc;
        }
    }

    public static class DownloadTask extends AsyncTask<Object, Long, DownloadResponse> {

        /* renamed from: a  reason: collision with root package name */
        String f18642a;
        File b;
        Context c;
        DownloadListener d;

        public interface DownloadListener {
            void a();

            void b();
        }

        public DownloadTask(Context context, String str, File file, DownloadListener downloadListener) {
            this.f18642a = str;
            this.b = file;
            this.c = context;
            this.d = downloadListener;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public DownloadResponse doInBackground(Object... objArr) {
            if (isCancelled()) {
                return null;
            }
            try {
                return NetworkUtils.a(this.c, this.f18642a, this.b, (OnDownloadProgress) null, false, false);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(DownloadResponse downloadResponse) {
            if (downloadResponse == null || downloadResponse.b != 3) {
                if (this.d != null) {
                    this.d.b();
                }
            } else if (this.d != null) {
                this.d.a();
            }
        }
    }
}
