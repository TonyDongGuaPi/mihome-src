package com.xiaomi.smarthome.camera.activity.timelapse;

import android.content.Context;
import android.os.Handler;
import com.mijia.debug.SDKLog;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class TCPClient {
    private static final int CONNECTTIMEOUT = 50000;
    public static final int DOWNLOAD_FAILED = 1000000;
    public static final int GET_TIMELAPSE_PHOTOGRAPHY_FILE_CANCLE = 1000005;
    public static final int GET_TIMELAPSE_PHOTOGRAPHY_FILE_FAILE = 1000004;
    public static final int GET_TIMELAPSE_PHOTOGRAPHY_FILE_PROGRESS = 1000002;
    public static final int GET_TIMELAPSE_PHOTOGRAPHY_FILE_SUCCESS = 1000003;
    public static final int SOCKET_CONNECTTIMEOUT = 99999;
    private static final String TAG = "TimelapsePhotographPlayActivity";
    public static final int TIMELAPSE_PHOTOGRAPHY_FILE_SIZE = 1000001;
    private static TCPClient instance;
    private Context context;
    private int fileLength;
    private boolean flag = true;
    /* access modifiers changed from: private */
    public InputStream inputStream;
    /* access modifiers changed from: private */
    public OutputStream outputStream;
    BufferedReader reader;
    /* access modifiers changed from: private */
    public Socket socket;
    /* access modifiers changed from: private */
    public InputStreamReader streamReader;
    private Thread thread;

    private TCPClient(Context context2) {
        this.context = context2;
    }

    public static TCPClient getInstance(Context context2) {
        if (instance == null) {
            instance = new TCPClient(context2);
        }
        return instance;
    }

    public void createClient(String str, int i, Handler handler, String str2, long j) {
        final String str3 = str;
        final int i2 = i;
        final Handler handler2 = handler;
        final String str4 = str2;
        final long j2 = j;
        this.thread = new Thread(new Runnable() {
            public void run() {
                try {
                    Socket unused = TCPClient.this.socket = new Socket();
                    TCPClient.this.socket.connect(new InetSocketAddress(str3, i2), 50000);
                    SDKLog.b(TCPClient.TAG, "判断客户端和服务器是否连接成功=" + TCPClient.this.socket.isConnected());
                    TCPClient.this.socket.setKeepAlive(true);
                    InputStream unused2 = TCPClient.this.inputStream = TCPClient.this.socket.getInputStream();
                    InputStreamReader unused3 = TCPClient.this.streamReader = new InputStreamReader(TCPClient.this.socket.getInputStream());
                    TCPClient.this.reader = new BufferedReader(TCPClient.this.streamReader);
                    OutputStream unused4 = TCPClient.this.outputStream = TCPClient.this.socket.getOutputStream();
                    TCPClient.this.recieveData(handler2, str4, j2);
                } catch (SocketTimeoutException e) {
                    SDKLog.b(TCPClient.TAG, "socket TimeOut!");
                    e.printStackTrace();
                    SDKLog.b(TCPClient.TAG, "e1=" + e.getMessage());
                    handler2.sendEmptyMessage(TCPClient.SOCKET_CONNECTTIMEOUT);
                } catch (IOException e2) {
                    e2.printStackTrace();
                    SDKLog.b(TCPClient.TAG, "e=" + e2.getMessage());
                    handler2.sendEmptyMessage(1000000);
                } catch (Throwable th) {
                    TCPClient.this.closeStream();
                    throw th;
                }
                TCPClient.this.closeStream();
            }
        });
        this.thread.start();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0180 A[SYNTHETIC, Splitter:B:50:0x0180] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0186 A[Catch:{ IOException -> 0x018f, all -> 0x018d }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void recieveData(android.os.Handler r8, java.lang.String r9, long r10) {
        /*
            r7 = this;
            java.lang.String r0 = "TimelapsePhotographPlayActivity"
            java.lang.String r1 = "客户端启动成功。并接收数据"
            com.mijia.debug.SDKLog.b(r0, r1)
            r0 = 1000004(0xf4244, float:1.401304E-39)
            java.io.BufferedReader r1 = r7.reader     // Catch:{ IOException -> 0x018f }
            java.lang.String r1 = r1.readLine()     // Catch:{ IOException -> 0x018f }
            r2 = 1
            if (r1 == 0) goto L_0x0041
            java.lang.String r3 = ":"
            boolean r3 = r1.contains(r3)     // Catch:{ IOException -> 0x018f }
            if (r3 == 0) goto L_0x0041
            java.lang.String r3 = ":"
            java.lang.String[] r3 = r1.split(r3)     // Catch:{ IOException -> 0x018f }
            r3 = r3[r2]     // Catch:{ IOException -> 0x018f }
            int r3 = java.lang.Integer.parseInt(r3)     // Catch:{ IOException -> 0x018f }
            r7.fileLength = r3     // Catch:{ IOException -> 0x018f }
            java.lang.String r3 = "TimelapsePhotographPlayActivity"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x018f }
            r4.<init>()     // Catch:{ IOException -> 0x018f }
            java.lang.String r5 = "延时摄影的长度="
            r4.append(r5)     // Catch:{ IOException -> 0x018f }
            int r5 = r7.fileLength     // Catch:{ IOException -> 0x018f }
            r4.append(r5)     // Catch:{ IOException -> 0x018f }
            java.lang.String r4 = r4.toString()     // Catch:{ IOException -> 0x018f }
            com.mijia.debug.SDKLog.b(r3, r4)     // Catch:{ IOException -> 0x018f }
        L_0x0041:
            java.lang.String r3 = "TimelapsePhotographPlayActivity"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x018f }
            r4.<init>()     // Catch:{ IOException -> 0x018f }
            java.lang.String r5 = "socket a="
            r4.append(r5)     // Catch:{ IOException -> 0x018f }
            r4.append(r1)     // Catch:{ IOException -> 0x018f }
            java.lang.String r1 = r4.toString()     // Catch:{ IOException -> 0x018f }
            com.mijia.debug.SDKLog.b(r3, r1)     // Catch:{ IOException -> 0x018f }
            android.os.Message r1 = r8.obtainMessage()     // Catch:{ IOException -> 0x018f }
            r3 = 1000001(0xf4241, float:1.4013E-39)
            r1.what = r3     // Catch:{ IOException -> 0x018f }
            int r3 = r7.fileLength     // Catch:{ IOException -> 0x018f }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ IOException -> 0x018f }
            r1.obj = r3     // Catch:{ IOException -> 0x018f }
            r8.sendMessage(r1)     // Catch:{ IOException -> 0x018f }
            java.io.OutputStream r1 = r7.outputStream     // Catch:{ IOException -> 0x018f }
            if (r1 == 0) goto L_0x007a
            java.io.OutputStream r1 = r7.outputStream     // Catch:{ IOException -> 0x018f }
            java.lang.String r3 = "ok"
            byte[] r3 = r3.getBytes()     // Catch:{ IOException -> 0x018f }
            r1.write(r3)     // Catch:{ IOException -> 0x018f }
        L_0x007a:
            r1 = 0
            if (r9 != 0) goto L_0x009f
            android.os.Message r9 = r8.obtainMessage()     // Catch:{ Exception -> 0x009c }
            r9.what = r0     // Catch:{ Exception -> 0x009c }
            r9.arg1 = r2     // Catch:{ Exception -> 0x009c }
            r8.sendMessage(r9)     // Catch:{ Exception -> 0x009c }
            r7.closeStream()     // Catch:{ Exception -> 0x009c }
            java.lang.String r9 = "TimelapsePhotographPlayActivity"
            java.lang.String r10 = "filepath==null"
            com.mijia.debug.SDKLog.b(r9, r10)     // Catch:{ Exception -> 0x009c }
            r7.closeStream()     // Catch:{ IOException -> 0x018f }
            r7.closeStream()
            return
        L_0x0099:
            r9 = move-exception
            goto L_0x0184
        L_0x009c:
            r9 = move-exception
            goto L_0x015e
        L_0x009f:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x009c }
            r2.<init>()     // Catch:{ Exception -> 0x009c }
            r2.append(r9)     // Catch:{ Exception -> 0x009c }
            r2.append(r10)     // Catch:{ Exception -> 0x009c }
            java.lang.String r9 = ".h265"
            r2.append(r9)     // Catch:{ Exception -> 0x009c }
            java.lang.String r9 = r2.toString()     // Catch:{ Exception -> 0x009c }
            java.lang.String r10 = "TimelapsePhotographPlayActivity"
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x009c }
            r11.<init>()     // Catch:{ Exception -> 0x009c }
            java.lang.String r2 = "储存延时摄影的路径="
            r11.append(r2)     // Catch:{ Exception -> 0x009c }
            r11.append(r9)     // Catch:{ Exception -> 0x009c }
            java.lang.String r11 = r11.toString()     // Catch:{ Exception -> 0x009c }
            com.mijia.debug.SDKLog.b(r10, r11)     // Catch:{ Exception -> 0x009c }
            java.io.File r10 = new java.io.File     // Catch:{ Exception -> 0x009c }
            r10.<init>(r9)     // Catch:{ Exception -> 0x009c }
            boolean r11 = r10.exists()     // Catch:{ Exception -> 0x009c }
            if (r11 == 0) goto L_0x00d7
            r10.delete()     // Catch:{ Exception -> 0x009c }
        L_0x00d7:
            r10.createNewFile()     // Catch:{ Exception -> 0x009c }
            java.io.FileOutputStream r11 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x009c }
            r11.<init>(r10)     // Catch:{ Exception -> 0x009c }
            r10 = 2048(0x800, float:2.87E-42)
            byte[] r10 = new byte[r10]     // Catch:{ Exception -> 0x015c, all -> 0x0159 }
            r2 = 0
            r3 = 0
        L_0x00e5:
            int r4 = r7.fileLength     // Catch:{ Exception -> 0x015c, all -> 0x0159 }
            if (r3 == r4) goto L_0x0137
            java.lang.String r4 = "TimelapsePhotographPlayActivity"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x015c, all -> 0x0159 }
            r5.<init>()     // Catch:{ Exception -> 0x015c, all -> 0x0159 }
            java.lang.String r6 = "flag="
            r5.append(r6)     // Catch:{ Exception -> 0x015c, all -> 0x0159 }
            boolean r6 = r7.flag     // Catch:{ Exception -> 0x015c, all -> 0x0159 }
            r5.append(r6)     // Catch:{ Exception -> 0x015c, all -> 0x0159 }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x015c, all -> 0x0159 }
            com.mijia.debug.SDKLog.b(r4, r5)     // Catch:{ Exception -> 0x015c, all -> 0x0159 }
            boolean r4 = r7.flag     // Catch:{ Exception -> 0x015c, all -> 0x0159 }
            if (r4 != 0) goto L_0x011e
            r11.close()     // Catch:{ Exception -> 0x015c, all -> 0x0159 }
            r7.closeStream()     // Catch:{ Exception -> 0x015c, all -> 0x0159 }
            r8.removeCallbacksAndMessages(r1)     // Catch:{ Exception -> 0x015c, all -> 0x0159 }
            r9 = 1000005(0xf4245, float:1.401305E-39)
            r8.sendEmptyMessage(r9)     // Catch:{ Exception -> 0x015c, all -> 0x0159 }
            r11.close()     // Catch:{ IOException -> 0x018f }
            r7.closeStream()     // Catch:{ IOException -> 0x018f }
            r7.closeStream()
            return
        L_0x011e:
            java.io.InputStream r4 = r7.inputStream     // Catch:{ Exception -> 0x015c, all -> 0x0159 }
            int r4 = r4.read(r10)     // Catch:{ Exception -> 0x015c, all -> 0x0159 }
            r11.write(r10, r2, r4)     // Catch:{ Exception -> 0x015c, all -> 0x0159 }
            int r3 = r3 + r4
            android.os.Message r4 = r8.obtainMessage()     // Catch:{ Exception -> 0x015c, all -> 0x0159 }
            r5 = 1000002(0xf4242, float:1.401301E-39)
            r4.what = r5     // Catch:{ Exception -> 0x015c, all -> 0x0159 }
            r4.arg1 = r3     // Catch:{ Exception -> 0x015c, all -> 0x0159 }
            r8.sendMessage(r4)     // Catch:{ Exception -> 0x015c, all -> 0x0159 }
            goto L_0x00e5
        L_0x0137:
            java.lang.String r10 = "TimelapsePhotographPlayActivity"
            java.lang.String r1 = "发送了下载成功的消息...."
            com.mijia.debug.SDKLog.b(r10, r1)     // Catch:{ Exception -> 0x015c, all -> 0x0159 }
            r11.close()     // Catch:{ Exception -> 0x015c, all -> 0x0159 }
            r7.closeStream()     // Catch:{ Exception -> 0x015c, all -> 0x0159 }
            android.os.Message r10 = r8.obtainMessage()     // Catch:{ Exception -> 0x015c, all -> 0x0159 }
            r1 = 1000003(0xf4243, float:1.401303E-39)
            r10.what = r1     // Catch:{ Exception -> 0x015c, all -> 0x0159 }
            r10.obj = r9     // Catch:{ Exception -> 0x015c, all -> 0x0159 }
            r8.sendMessage(r10)     // Catch:{ Exception -> 0x015c, all -> 0x0159 }
            r11.close()     // Catch:{ IOException -> 0x018f }
        L_0x0155:
            r7.closeStream()     // Catch:{ IOException -> 0x018f }
            goto L_0x01b0
        L_0x0159:
            r9 = move-exception
            r1 = r11
            goto L_0x0184
        L_0x015c:
            r9 = move-exception
            r1 = r11
        L_0x015e:
            r9.printStackTrace()     // Catch:{ all -> 0x0099 }
            r8.sendEmptyMessage(r0)     // Catch:{ all -> 0x0099 }
            java.lang.String r10 = "TimelapsePhotographPlayActivity"
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x0099 }
            r11.<init>()     // Catch:{ all -> 0x0099 }
            java.lang.String r2 = "e="
            r11.append(r2)     // Catch:{ all -> 0x0099 }
            java.lang.String r9 = r9.getMessage()     // Catch:{ all -> 0x0099 }
            r11.append(r9)     // Catch:{ all -> 0x0099 }
            java.lang.String r9 = r11.toString()     // Catch:{ all -> 0x0099 }
            com.mijia.debug.SDKLog.b(r10, r9)     // Catch:{ all -> 0x0099 }
            if (r1 == 0) goto L_0x0155
            r1.close()     // Catch:{ IOException -> 0x018f }
            goto L_0x0155
        L_0x0184:
            if (r1 == 0) goto L_0x0189
            r1.close()     // Catch:{ IOException -> 0x018f }
        L_0x0189:
            r7.closeStream()     // Catch:{ IOException -> 0x018f }
            throw r9     // Catch:{ IOException -> 0x018f }
        L_0x018d:
            r8 = move-exception
            goto L_0x01b4
        L_0x018f:
            r9 = move-exception
            r9.printStackTrace()     // Catch:{ all -> 0x018d }
            r8.sendEmptyMessage(r0)     // Catch:{ all -> 0x018d }
            java.lang.String r8 = "TimelapsePhotographPlayActivity"
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ all -> 0x018d }
            r10.<init>()     // Catch:{ all -> 0x018d }
            java.lang.String r11 = "e="
            r10.append(r11)     // Catch:{ all -> 0x018d }
            java.lang.String r9 = r9.getMessage()     // Catch:{ all -> 0x018d }
            r10.append(r9)     // Catch:{ all -> 0x018d }
            java.lang.String r9 = r10.toString()     // Catch:{ all -> 0x018d }
            com.mijia.debug.SDKLog.b(r8, r9)     // Catch:{ all -> 0x018d }
        L_0x01b0:
            r7.closeStream()
            return
        L_0x01b4:
            r7.closeStream()
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.camera.activity.timelapse.TCPClient.recieveData(android.os.Handler, java.lang.String, long):void");
    }

    /* access modifiers changed from: private */
    public void closeStream() {
        try {
            if (this.inputStream != null) {
                this.inputStream.close();
            }
            if (this.outputStream != null) {
                this.outputStream.close();
            }
            if (this.reader != null) {
                this.reader.close();
            }
            if (this.streamReader != null) {
                this.streamReader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setFlag(boolean z) {
        this.flag = z;
    }

    public void closeSocket() {
        SDKLog.b(TAG, "closeSocket。。。。");
        if (this.socket != null) {
            try {
                this.socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
