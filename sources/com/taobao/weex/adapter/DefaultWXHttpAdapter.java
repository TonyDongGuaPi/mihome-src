package com.taobao.weex.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.taobao.weex.adapter.IWXHttpAdapter;
import com.taobao.weex.common.WXRequest;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.http.client.methods.HttpPut;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class DefaultWXHttpAdapter implements IWXHttpAdapter {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private static final IEventReporterDelegate DEFAULT_DELEGATE = new NOPEventReportDelegate((AnonymousClass1) null);
    private ExecutorService mExecutorService;

    public interface IEventReporterDelegate {
        void httpExchangeFailed(IOException iOException);

        InputStream interpretResponseStream(@Nullable InputStream inputStream);

        void postConnect();

        void preConnect(HttpURLConnection httpURLConnection, @Nullable String str);
    }

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-1595832085974530288L, "com/taobao/weex/adapter/DefaultWXHttpAdapter", 62);
        $jacocoData = a2;
        return a2;
    }

    public DefaultWXHttpAdapter() {
        $jacocoInit()[0] = true;
    }

    static /* synthetic */ HttpURLConnection access$100(DefaultWXHttpAdapter defaultWXHttpAdapter, WXRequest wXRequest, IWXHttpAdapter.OnHttpListener onHttpListener) throws IOException {
        boolean[] $jacocoInit = $jacocoInit();
        HttpURLConnection openConnection = defaultWXHttpAdapter.openConnection(wXRequest, onHttpListener);
        $jacocoInit[58] = true;
        return openConnection;
    }

    static /* synthetic */ byte[] access$200(DefaultWXHttpAdapter defaultWXHttpAdapter, InputStream inputStream, IWXHttpAdapter.OnHttpListener onHttpListener) throws IOException {
        boolean[] $jacocoInit = $jacocoInit();
        byte[] readInputStreamAsBytes = defaultWXHttpAdapter.readInputStreamAsBytes(inputStream, onHttpListener);
        $jacocoInit[59] = true;
        return readInputStreamAsBytes;
    }

    static /* synthetic */ String access$300(DefaultWXHttpAdapter defaultWXHttpAdapter, InputStream inputStream, IWXHttpAdapter.OnHttpListener onHttpListener) throws IOException {
        boolean[] $jacocoInit = $jacocoInit();
        String readInputStream = defaultWXHttpAdapter.readInputStream(inputStream, onHttpListener);
        $jacocoInit[60] = true;
        return readInputStream;
    }

    static {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[61] = true;
    }

    private void execute(Runnable runnable) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mExecutorService != null) {
            $jacocoInit[1] = true;
        } else {
            $jacocoInit[2] = true;
            this.mExecutorService = Executors.newFixedThreadPool(3);
            $jacocoInit[3] = true;
        }
        this.mExecutorService.execute(runnable);
        $jacocoInit[4] = true;
    }

    public void sendRequest(final WXRequest wXRequest, final IWXHttpAdapter.OnHttpListener onHttpListener) {
        boolean[] $jacocoInit = $jacocoInit();
        if (onHttpListener == null) {
            $jacocoInit[5] = true;
        } else {
            $jacocoInit[6] = true;
            onHttpListener.onHttpStart();
            $jacocoInit[7] = true;
        }
        execute(new Runnable(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ DefaultWXHttpAdapter this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(5667669114671951567L, "com/taobao/weex/adapter/DefaultWXHttpAdapter$1", 42);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r2;
                $jacocoInit[0] = true;
            }

            /* JADX WARNING: Removed duplicated region for block: B:23:0x00e0 A[Catch:{ IOException | IllegalArgumentException -> 0x00f7, Throwable -> 0x013e }] */
            /* JADX WARNING: Removed duplicated region for block: B:24:0x00e5 A[Catch:{ IOException | IllegalArgumentException -> 0x00f7, Throwable -> 0x013e }] */
            /* JADX WARNING: Removed duplicated region for block: B:42:0x014d  */
            /* JADX WARNING: Removed duplicated region for block: B:43:0x0152  */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r10 = this;
                    boolean[] r0 = $jacocoInit()
                    com.taobao.weex.WXSDKManager r1 = com.taobao.weex.WXSDKManager.getInstance()
                    java.util.Map r1 = r1.getAllInstanceMap()
                    com.taobao.weex.common.WXRequest r2 = r4
                    java.lang.String r2 = r2.instanceId
                    java.lang.Object r1 = r1.get(r2)
                    com.taobao.weex.WXSDKInstance r1 = (com.taobao.weex.WXSDKInstance) r1
                    r2 = 1
                    r0[r2] = r2
                    if (r1 != 0) goto L_0x001f
                    r3 = 2
                    r0[r3] = r2
                    goto L_0x0036
                L_0x001f:
                    boolean r3 = r1.isDestroy()
                    if (r3 == 0) goto L_0x0029
                    r3 = 3
                    r0[r3] = r2
                    goto L_0x0036
                L_0x0029:
                    r3 = 4
                    r0[r3] = r2
                    com.taobao.weex.performance.WXInstanceApm r3 = r1.getApmForInstance()
                    r3.actionNetRequest()
                    r3 = 5
                    r0[r3] = r2
                L_0x0036:
                    r3 = 6
                    r0[r3] = r2
                    com.taobao.weex.common.WXResponse r3 = new com.taobao.weex.common.WXResponse
                    r3.<init>()
                    r4 = 7
                    r0[r4] = r2
                    com.taobao.weex.adapter.DefaultWXHttpAdapter r4 = r10.this$0
                    com.taobao.weex.adapter.DefaultWXHttpAdapter$IEventReporterDelegate r4 = r4.getEventReporterDelegate()
                    r5 = 8
                    r6 = 0
                    r0[r5] = r2     // Catch:{ IOException | IllegalArgumentException -> 0x00f7 }
                    com.taobao.weex.adapter.DefaultWXHttpAdapter r5 = r10.this$0     // Catch:{ IOException | IllegalArgumentException -> 0x00f7 }
                    com.taobao.weex.common.WXRequest r7 = r4     // Catch:{ IOException | IllegalArgumentException -> 0x00f7 }
                    com.taobao.weex.adapter.IWXHttpAdapter$OnHttpListener r8 = r5     // Catch:{ IOException | IllegalArgumentException -> 0x00f7 }
                    java.net.HttpURLConnection r5 = com.taobao.weex.adapter.DefaultWXHttpAdapter.access$100(r5, r7, r8)     // Catch:{ IOException | IllegalArgumentException -> 0x00f7 }
                    r7 = 9
                    r0[r7] = r2     // Catch:{ IOException | IllegalArgumentException -> 0x00f7 }
                    com.taobao.weex.common.WXRequest r7 = r4     // Catch:{ IOException | IllegalArgumentException -> 0x00f7 }
                    java.lang.String r7 = r7.body     // Catch:{ IOException | IllegalArgumentException -> 0x00f7 }
                    r4.preConnect(r5, r7)     // Catch:{ IOException | IllegalArgumentException -> 0x00f7 }
                    r7 = 10
                    r0[r7] = r2     // Catch:{ IOException | IllegalArgumentException -> 0x00f7 }
                    java.util.Map r7 = r5.getHeaderFields()     // Catch:{ IOException | IllegalArgumentException -> 0x00f7 }
                    r8 = 11
                    r0[r8] = r2     // Catch:{ IOException | IllegalArgumentException -> 0x00f7 }
                    int r8 = r5.getResponseCode()     // Catch:{ IOException | IllegalArgumentException -> 0x00f7 }
                    com.taobao.weex.adapter.IWXHttpAdapter$OnHttpListener r9 = r5     // Catch:{ IOException | IllegalArgumentException -> 0x00f7 }
                    if (r9 != 0) goto L_0x007a
                    r7 = 12
                    r0[r7] = r2     // Catch:{ IOException | IllegalArgumentException -> 0x00f7 }
                    goto L_0x0087
                L_0x007a:
                    r9 = 13
                    r0[r9] = r2     // Catch:{ IOException | IllegalArgumentException -> 0x00f7 }
                    com.taobao.weex.adapter.IWXHttpAdapter$OnHttpListener r9 = r5     // Catch:{ IOException | IllegalArgumentException -> 0x00f7 }
                    r9.onHeadersReceived(r8, r7)     // Catch:{ IOException | IllegalArgumentException -> 0x00f7 }
                    r7 = 14
                    r0[r7] = r2     // Catch:{ IOException | IllegalArgumentException -> 0x00f7 }
                L_0x0087:
                    r4.postConnect()     // Catch:{ IOException | IllegalArgumentException -> 0x00f7 }
                    r7 = 15
                    r0[r7] = r2     // Catch:{ IOException | IllegalArgumentException -> 0x00f7 }
                    java.lang.String r7 = java.lang.String.valueOf(r8)     // Catch:{ IOException | IllegalArgumentException -> 0x00f7 }
                    r3.statusCode = r7     // Catch:{ IOException | IllegalArgumentException -> 0x00f7 }
                    r7 = 200(0xc8, float:2.8E-43)
                    if (r8 >= r7) goto L_0x009d
                    r7 = 16
                    r0[r7] = r2     // Catch:{ IOException | IllegalArgumentException -> 0x00f7 }
                    goto L_0x00a5
                L_0x009d:
                    r7 = 299(0x12b, float:4.19E-43)
                    if (r8 <= r7) goto L_0x00b9
                    r7 = 17
                    r0[r7] = r2     // Catch:{ IOException | IllegalArgumentException -> 0x00f7 }
                L_0x00a5:
                    com.taobao.weex.adapter.DefaultWXHttpAdapter r7 = r10.this$0     // Catch:{ IOException | IllegalArgumentException -> 0x00f7 }
                    java.io.InputStream r5 = r5.getErrorStream()     // Catch:{ IOException | IllegalArgumentException -> 0x00f7 }
                    com.taobao.weex.adapter.IWXHttpAdapter$OnHttpListener r8 = r5     // Catch:{ IOException | IllegalArgumentException -> 0x00f7 }
                    java.lang.String r5 = com.taobao.weex.adapter.DefaultWXHttpAdapter.access$300(r7, r5, r8)     // Catch:{ IOException | IllegalArgumentException -> 0x00f7 }
                    r3.errorMsg = r5     // Catch:{ IOException | IllegalArgumentException -> 0x00f7 }
                    r5 = 22
                    r0[r5] = r2     // Catch:{ IOException | IllegalArgumentException -> 0x00f7 }
                    r5 = 0
                    goto L_0x00dc
                L_0x00b9:
                    r7 = 18
                    r0[r7] = r2     // Catch:{ IOException | IllegalArgumentException -> 0x00f7 }
                    java.io.InputStream r5 = r5.getInputStream()     // Catch:{ IOException | IllegalArgumentException -> 0x00f7 }
                    r7 = 19
                    r0[r7] = r2     // Catch:{ IOException | IllegalArgumentException -> 0x00f7 }
                    java.io.InputStream r5 = r4.interpretResponseStream(r5)     // Catch:{ IOException | IllegalArgumentException -> 0x00f7 }
                    r7 = 20
                    r0[r7] = r2     // Catch:{ IOException | IllegalArgumentException -> 0x00f7 }
                    com.taobao.weex.adapter.DefaultWXHttpAdapter r7 = r10.this$0     // Catch:{ IOException | IllegalArgumentException -> 0x00f7 }
                    com.taobao.weex.adapter.IWXHttpAdapter$OnHttpListener r8 = r5     // Catch:{ IOException | IllegalArgumentException -> 0x00f7 }
                    byte[] r5 = com.taobao.weex.adapter.DefaultWXHttpAdapter.access$200(r7, r5, r8)     // Catch:{ IOException | IllegalArgumentException -> 0x00f7 }
                    r3.originalData = r5     // Catch:{ IOException | IllegalArgumentException -> 0x00f7 }
                    r5 = 21
                    r0[r5] = r2     // Catch:{ IOException | IllegalArgumentException -> 0x00f7 }
                    r5 = 1
                L_0x00dc:
                    com.taobao.weex.adapter.IWXHttpAdapter$OnHttpListener r7 = r5     // Catch:{ IOException | IllegalArgumentException -> 0x00f7 }
                    if (r7 != 0) goto L_0x00e5
                    r7 = 23
                    r0[r7] = r2     // Catch:{ IOException | IllegalArgumentException -> 0x00f7 }
                    goto L_0x00f2
                L_0x00e5:
                    r7 = 24
                    r0[r7] = r2     // Catch:{ IOException | IllegalArgumentException -> 0x00f7 }
                    com.taobao.weex.adapter.IWXHttpAdapter$OnHttpListener r7 = r5     // Catch:{ IOException | IllegalArgumentException -> 0x00f7 }
                    r7.onHttpFinish(r3)     // Catch:{ IOException | IllegalArgumentException -> 0x00f7 }
                    r7 = 25
                    r0[r7] = r2     // Catch:{ IOException | IllegalArgumentException -> 0x00f7 }
                L_0x00f2:
                    r3 = 26
                    r0[r3] = r2
                    goto L_0x014b
                L_0x00f7:
                    r5 = move-exception
                    r7 = 27
                    r0[r7] = r2
                    r5.printStackTrace()
                    java.lang.String r7 = "-1"
                    r3.statusCode = r7
                    java.lang.String r7 = "-1"
                    r3.errorCode = r7
                    r7 = 28
                    r0[r7] = r2
                    java.lang.String r7 = r5.getMessage()
                    r3.errorMsg = r7
                    com.taobao.weex.adapter.IWXHttpAdapter$OnHttpListener r7 = r5
                    if (r7 != 0) goto L_0x011a
                    r3 = 29
                    r0[r3] = r2
                    goto L_0x0127
                L_0x011a:
                    r7 = 30
                    r0[r7] = r2
                    com.taobao.weex.adapter.IWXHttpAdapter$OnHttpListener r7 = r5
                    r7.onHttpFinish(r3)
                    r3 = 31
                    r0[r3] = r2
                L_0x0127:
                    boolean r3 = r5 instanceof java.io.IOException
                    if (r3 != 0) goto L_0x0130
                    r3 = 32
                    r0[r3] = r2
                    goto L_0x014a
                L_0x0130:
                    r3 = 33
                    r0[r3] = r2     // Catch:{ Throwable -> 0x013e }
                    java.io.IOException r5 = (java.io.IOException) r5     // Catch:{ Throwable -> 0x013e }
                    r4.httpExchangeFailed(r5)     // Catch:{ Throwable -> 0x013e }
                    r3 = 34
                    r0[r3] = r2
                    goto L_0x014a
                L_0x013e:
                    r3 = move-exception
                    r4 = 35
                    r0[r4] = r2
                    r3.printStackTrace()
                    r3 = 36
                    r0[r3] = r2
                L_0x014a:
                    r5 = 0
                L_0x014b:
                    if (r1 != 0) goto L_0x0152
                    r1 = 37
                    r0[r1] = r2
                    goto L_0x016d
                L_0x0152:
                    boolean r3 = r1.isDestroy()
                    if (r3 == 0) goto L_0x015d
                    r1 = 38
                    r0[r1] = r2
                    goto L_0x016d
                L_0x015d:
                    r3 = 39
                    r0[r3] = r2
                    com.taobao.weex.performance.WXInstanceApm r1 = r1.getApmForInstance()
                    r3 = 0
                    r1.actionNetResult(r5, r3)
                    r1 = 40
                    r0[r1] = r2
                L_0x016d:
                    r1 = 41
                    r0[r1] = r2
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.adapter.DefaultWXHttpAdapter.AnonymousClass1.run():void");
            }
        });
        $jacocoInit[8] = true;
    }

    private HttpURLConnection openConnection(WXRequest wXRequest, IWXHttpAdapter.OnHttpListener onHttpListener) throws IOException {
        boolean[] $jacocoInit = $jacocoInit();
        URL url = new URL(wXRequest.url);
        $jacocoInit[9] = true;
        HttpURLConnection createConnection = createConnection(url);
        $jacocoInit[10] = true;
        createConnection.setConnectTimeout(wXRequest.timeoutMs);
        $jacocoInit[11] = true;
        createConnection.setReadTimeout(wXRequest.timeoutMs);
        $jacocoInit[12] = true;
        createConnection.setUseCaches(false);
        $jacocoInit[13] = true;
        createConnection.setDoInput(true);
        if (wXRequest.paramMap == null) {
            $jacocoInit[14] = true;
        } else {
            $jacocoInit[15] = true;
            Set<String> keySet = wXRequest.paramMap.keySet();
            $jacocoInit[16] = true;
            $jacocoInit[17] = true;
            for (String next : keySet) {
                $jacocoInit[19] = true;
                createConnection.addRequestProperty(next, wXRequest.paramMap.get(next));
                $jacocoInit[20] = true;
            }
            $jacocoInit[18] = true;
        }
        if ("POST".equals(wXRequest.method)) {
            $jacocoInit[21] = true;
        } else if (HttpPut.METHOD_NAME.equals(wXRequest.method)) {
            $jacocoInit[22] = true;
        } else if ("PATCH".equals(wXRequest.method)) {
            $jacocoInit[23] = true;
        } else {
            if (!TextUtils.isEmpty(wXRequest.method)) {
                $jacocoInit[35] = true;
                createConnection.setRequestMethod(wXRequest.method);
                $jacocoInit[36] = true;
            } else {
                createConnection.setRequestMethod("GET");
                $jacocoInit[37] = true;
            }
            $jacocoInit[38] = true;
            return createConnection;
        }
        createConnection.setRequestMethod(wXRequest.method);
        if (wXRequest.body == null) {
            $jacocoInit[24] = true;
        } else {
            if (onHttpListener == null) {
                $jacocoInit[25] = true;
            } else {
                $jacocoInit[26] = true;
                onHttpListener.onHttpUploadProgress(0);
                $jacocoInit[27] = true;
            }
            createConnection.setDoOutput(true);
            $jacocoInit[28] = true;
            DataOutputStream dataOutputStream = new DataOutputStream(createConnection.getOutputStream());
            $jacocoInit[29] = true;
            dataOutputStream.write(wXRequest.body.getBytes());
            $jacocoInit[30] = true;
            dataOutputStream.close();
            if (onHttpListener == null) {
                $jacocoInit[31] = true;
            } else {
                $jacocoInit[32] = true;
                onHttpListener.onHttpUploadProgress(100);
                $jacocoInit[33] = true;
            }
            $jacocoInit[34] = true;
        }
        $jacocoInit[38] = true;
        return createConnection;
    }

    private byte[] readInputStreamAsBytes(InputStream inputStream, IWXHttpAdapter.OnHttpListener onHttpListener) throws IOException {
        boolean[] $jacocoInit = $jacocoInit();
        if (inputStream == null) {
            $jacocoInit[39] = true;
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[2048];
        $jacocoInit[40] = true;
        int i = 0;
        while (true) {
            int read = inputStream.read(bArr, 0, bArr.length);
            if (read != -1) {
                $jacocoInit[41] = true;
                byteArrayOutputStream.write(bArr, 0, read);
                i += read;
                if (onHttpListener == null) {
                    $jacocoInit[42] = true;
                } else {
                    $jacocoInit[43] = true;
                    onHttpListener.onHttpResponseProgress(i);
                    $jacocoInit[44] = true;
                }
            } else {
                byteArrayOutputStream.flush();
                $jacocoInit[45] = true;
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                $jacocoInit[46] = true;
                return byteArray;
            }
        }
    }

    private String readInputStream(InputStream inputStream, IWXHttpAdapter.OnHttpListener onHttpListener) throws IOException {
        boolean[] $jacocoInit = $jacocoInit();
        if (inputStream == null) {
            $jacocoInit[47] = true;
            return null;
        }
        StringBuilder sb = new StringBuilder();
        $jacocoInit[48] = true;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        char[] cArr = new char[2048];
        $jacocoInit[49] = true;
        while (true) {
            int read = bufferedReader.read(cArr);
            if (read != -1) {
                $jacocoInit[50] = true;
                sb.append(cArr, 0, read);
                if (onHttpListener == null) {
                    $jacocoInit[51] = true;
                } else {
                    $jacocoInit[52] = true;
                    onHttpListener.onHttpResponseProgress(sb.length());
                    $jacocoInit[53] = true;
                }
            } else {
                bufferedReader.close();
                $jacocoInit[54] = true;
                String sb2 = sb.toString();
                $jacocoInit[55] = true;
                return sb2;
            }
        }
    }

    /* access modifiers changed from: protected */
    public HttpURLConnection createConnection(URL url) throws IOException {
        boolean[] $jacocoInit = $jacocoInit();
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        $jacocoInit[56] = true;
        return httpURLConnection;
    }

    @NonNull
    public IEventReporterDelegate getEventReporterDelegate() {
        boolean[] $jacocoInit = $jacocoInit();
        IEventReporterDelegate iEventReporterDelegate = DEFAULT_DELEGATE;
        $jacocoInit[57] = true;
        return iEventReporterDelegate;
    }

    private static class NOPEventReportDelegate implements IEventReporterDelegate {
        private static transient /* synthetic */ boolean[] $jacocoData;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(36233739104682756L, "com/taobao/weex/adapter/DefaultWXHttpAdapter$NOPEventReportDelegate", 6);
            $jacocoData = a2;
            return a2;
        }

        private NOPEventReportDelegate() {
            $jacocoInit()[0] = true;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ NOPEventReportDelegate(AnonymousClass1 r3) {
            this();
            boolean[] $jacocoInit = $jacocoInit();
            $jacocoInit[5] = true;
        }

        public void preConnect(HttpURLConnection httpURLConnection, @Nullable String str) {
            $jacocoInit()[1] = true;
        }

        public void postConnect() {
            $jacocoInit()[2] = true;
        }

        public InputStream interpretResponseStream(@Nullable InputStream inputStream) {
            $jacocoInit()[3] = true;
            return inputStream;
        }

        public void httpExchangeFailed(IOException iOException) {
            $jacocoInit()[4] = true;
        }
    }
}
