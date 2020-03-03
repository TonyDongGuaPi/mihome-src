package com.xiaomi.smarthome.camera.activity.timelapse;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.Utils.FileUtil;
import com.mijia.debug.SDKLog;
import java.io.File;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DownLoadTimeLapseDemo {
    public static final int DOWNLOADFILESUCCESS = 100;
    private static final String TAG = "DownLoadTimeLapseDemo";
    private static DownLoadTimeLapseDemo mInstance;
    private OkHttpClient client = new OkHttpClient();
    private Context mContext;
    private String mDid;
    private Handler mHandler;

    private DownLoadTimeLapseDemo(Context context, String str, Handler handler) {
        this.mContext = context;
        this.mDid = str;
        this.mHandler = handler;
        SDKLog.b(TAG, "DownLoadTimeLapseDemo mDid=" + this.mDid);
    }

    public static DownLoadTimeLapseDemo getInstance(Context context, String str, Handler handler) {
        if (mInstance == null) {
            synchronized (DownLoadTimeLapseDemo.class) {
                if (mInstance == null) {
                    mInstance = new DownLoadTimeLapseDemo(context, str, handler);
                }
            }
        }
        return mInstance;
    }

    public void downLoadFile() {
        SDKLog.b(TAG, "downLoadFile 开始下载");
        this.client.newCall(new Request.Builder().url("https://cnbj2.fds.api.xiaomi.com/firmwarehualai/timelaps_demo.gif").build()).enqueue(new Callback() {
            public void onFailure(Call call, IOException iOException) {
                SDKLog.b(DownLoadTimeLapseDemo.TAG, "下载失败");
            }

            public void onResponse(Call call, Response response) throws IOException {
                if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
                    SDKLog.b(DownLoadTimeLapseDemo.TAG, "Main Thread");
                } else {
                    SDKLog.b(DownLoadTimeLapseDemo.TAG, "Not Main Thread");
                }
                if (response.isSuccessful()) {
                    DownLoadTimeLapseDemo.this.writeFile(response);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00c3 A[SYNTHETIC, Splitter:B:32:0x00c3] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00cb A[Catch:{ IOException -> 0x00c7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00d7 A[SYNTHETIC, Splitter:B:42:0x00d7] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00df A[Catch:{ IOException -> 0x00db }] */
    /* JADX WARNING: Removed duplicated region for block: B:52:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void writeFile(okhttp3.Response r9) {
        /*
            r8 = this;
            r0 = 4096(0x1000, float:5.74E-42)
            byte[] r0 = new byte[r0]
            java.lang.String r1 = r8.getDownloadFilePath()
            java.lang.String r2 = "DownLoadTimeLapseDemo"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "writeFile path="
            r3.append(r4)
            r3.append(r1)
            java.lang.String r3 = r3.toString()
            com.mijia.debug.SDKLog.b(r2, r3)
            r2 = 0
            okhttp3.ResponseBody r3 = r9.body()     // Catch:{ Exception -> 0x00bc, all -> 0x00b8 }
            java.io.InputStream r3 = r3.byteStream()     // Catch:{ Exception -> 0x00bc, all -> 0x00b8 }
            java.io.File r4 = new java.io.File     // Catch:{ Exception -> 0x00b4, all -> 0x00b1 }
            r4.<init>(r1)     // Catch:{ Exception -> 0x00b4, all -> 0x00b1 }
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x00b4, all -> 0x00b1 }
            r1.<init>(r4)     // Catch:{ Exception -> 0x00b4, all -> 0x00b1 }
            okhttp3.ResponseBody r9 = r9.body()     // Catch:{ Exception -> 0x00af, all -> 0x00ad }
            long r4 = r9.contentLength()     // Catch:{ Exception -> 0x00af, all -> 0x00ad }
            java.lang.String r9 = "DownLoadTimeLapseDemo"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00af, all -> 0x00ad }
            r2.<init>()     // Catch:{ Exception -> 0x00af, all -> 0x00ad }
            java.lang.String r6 = "writeFile fileSize="
            r2.append(r6)     // Catch:{ Exception -> 0x00af, all -> 0x00ad }
            r2.append(r4)     // Catch:{ Exception -> 0x00af, all -> 0x00ad }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x00af, all -> 0x00ad }
            com.mijia.debug.SDKLog.b(r9, r2)     // Catch:{ Exception -> 0x00af, all -> 0x00ad }
            java.lang.String r9 = "DownLoadTimeLapseDemo"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00af, all -> 0x00ad }
            r2.<init>()     // Catch:{ Exception -> 0x00af, all -> 0x00ad }
            java.lang.String r6 = "writeFile fileSize="
            r2.append(r6)     // Catch:{ Exception -> 0x00af, all -> 0x00ad }
            java.lang.String r4 = com.Utils.FileUtil.a((long) r4)     // Catch:{ Exception -> 0x00af, all -> 0x00ad }
            r2.append(r4)     // Catch:{ Exception -> 0x00af, all -> 0x00ad }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x00af, all -> 0x00ad }
            com.mijia.debug.SDKLog.b(r9, r2)     // Catch:{ Exception -> 0x00af, all -> 0x00ad }
            r4 = 0
        L_0x006b:
            int r9 = r3.read(r0)     // Catch:{ Exception -> 0x00af, all -> 0x00ad }
            r2 = -1
            if (r9 == r2) goto L_0x008f
            r2 = 0
            r1.write(r0, r2, r9)     // Catch:{ Exception -> 0x00af, all -> 0x00ad }
            long r6 = (long) r9     // Catch:{ Exception -> 0x00af, all -> 0x00ad }
            long r4 = r4 + r6
            java.lang.String r9 = "DownLoadTimeLapseDemo"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00af, all -> 0x00ad }
            r2.<init>()     // Catch:{ Exception -> 0x00af, all -> 0x00ad }
            java.lang.String r6 = "download current------>sum="
            r2.append(r6)     // Catch:{ Exception -> 0x00af, all -> 0x00ad }
            r2.append(r4)     // Catch:{ Exception -> 0x00af, all -> 0x00ad }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x00af, all -> 0x00ad }
            com.mijia.debug.SDKLog.b(r9, r2)     // Catch:{ Exception -> 0x00af, all -> 0x00ad }
            goto L_0x006b
        L_0x008f:
            r1.flush()     // Catch:{ Exception -> 0x00af, all -> 0x00ad }
            android.os.Handler r9 = r8.mHandler     // Catch:{ Exception -> 0x00af, all -> 0x00ad }
            if (r9 == 0) goto L_0x009d
            android.os.Handler r9 = r8.mHandler     // Catch:{ Exception -> 0x00af, all -> 0x00ad }
            r0 = 100
            r9.sendEmptyMessage(r0)     // Catch:{ Exception -> 0x00af, all -> 0x00ad }
        L_0x009d:
            java.lang.String r9 = "DownLoadTimeLapseDemo"
            java.lang.String r0 = "下载成功"
            com.mijia.debug.SDKLog.b(r9, r0)     // Catch:{ Exception -> 0x00af, all -> 0x00ad }
            if (r3 == 0) goto L_0x00a9
            r3.close()     // Catch:{ IOException -> 0x00c7 }
        L_0x00a9:
            r1.close()     // Catch:{ IOException -> 0x00c7 }
            goto L_0x00d2
        L_0x00ad:
            r9 = move-exception
            goto L_0x00d5
        L_0x00af:
            r9 = move-exception
            goto L_0x00b6
        L_0x00b1:
            r9 = move-exception
            r1 = r2
            goto L_0x00d5
        L_0x00b4:
            r9 = move-exception
            r1 = r2
        L_0x00b6:
            r2 = r3
            goto L_0x00be
        L_0x00b8:
            r9 = move-exception
            r1 = r2
            r3 = r1
            goto L_0x00d5
        L_0x00bc:
            r9 = move-exception
            r1 = r2
        L_0x00be:
            r9.printStackTrace()     // Catch:{ all -> 0x00d3 }
            if (r2 == 0) goto L_0x00c9
            r2.close()     // Catch:{ IOException -> 0x00c7 }
            goto L_0x00c9
        L_0x00c7:
            r9 = move-exception
            goto L_0x00cf
        L_0x00c9:
            if (r1 == 0) goto L_0x00d2
            r1.close()     // Catch:{ IOException -> 0x00c7 }
            goto L_0x00d2
        L_0x00cf:
            r9.printStackTrace()
        L_0x00d2:
            return
        L_0x00d3:
            r9 = move-exception
            r3 = r2
        L_0x00d5:
            if (r3 == 0) goto L_0x00dd
            r3.close()     // Catch:{ IOException -> 0x00db }
            goto L_0x00dd
        L_0x00db:
            r0 = move-exception
            goto L_0x00e3
        L_0x00dd:
            if (r1 == 0) goto L_0x00e6
            r1.close()     // Catch:{ IOException -> 0x00db }
            goto L_0x00e6
        L_0x00e3:
            r0.printStackTrace()
        L_0x00e6:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.camera.activity.timelapse.DownLoadTimeLapseDemo.writeFile(okhttp3.Response):void");
    }

    private String getDownloadFilePath() {
        String d = FileUtil.d();
        if (TextUtils.isEmpty(d)) {
            return d;
        }
        File file = new File(d);
        if (!file.exists()) {
            file.mkdirs();
        }
        return d + File.separator + "timelaps_demo.gif";
    }
}
