package com.xiaomi.smarthome.smartconfig;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NetworkDetector {

    /* renamed from: a  reason: collision with root package name */
    private static NetworkDetector f22299a = null;
    private static final int d = 1;
    private static final int e = 2;
    private static final int f = 4;
    /* access modifiers changed from: private */
    public HashMap<String, Long> b = new HashMap<>();
    /* access modifiers changed from: private */
    public List<String> c = new ArrayList();
    /* access modifiers changed from: private */
    public boolean g = false;
    /* access modifiers changed from: private */
    public String h;
    /* access modifiers changed from: private */
    public ThreadHandler i;
    private HandlerThread j = null;
    /* access modifiers changed from: private */
    public int k = 0;
    /* access modifiers changed from: private */
    public String l;
    /* access modifiers changed from: private */
    public List<Thread> m = new ArrayList();

    public void a(String str) {
    }

    public void b() {
    }

    public void c() {
    }

    class ThreadHandler extends Handler {
        public ThreadHandler(Looper looper) {
            super(looper);
        }

        /* JADX WARNING: Removed duplicated region for block: B:65:0x01a9 A[LOOP:4: B:63:0x01a3->B:65:0x01a9, LOOP_END] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void handleMessage(android.os.Message r10) {
            /*
                r9 = this;
                int r0 = r10.what
                r1 = 0
                switch(r0) {
                    case 1: goto L_0x00c3;
                    case 2: goto L_0x0008;
                    default: goto L_0x0006;
                }
            L_0x0006:
                goto L_0x01b3
            L_0x0008:
                java.lang.Object r10 = r10.obj
                java.lang.ref.WeakReference r10 = (java.lang.ref.WeakReference) r10
                if (r10 != 0) goto L_0x0010
                goto L_0x01b3
            L_0x0010:
                java.lang.Object r10 = r10.get()
                com.xiaomi.smarthome.smartconfig.NetworkDetector$DetectThread r10 = (com.xiaomi.smarthome.smartconfig.NetworkDetector.DetectThread) r10
                if (r10 != 0) goto L_0x001a
                goto L_0x01b3
            L_0x001a:
                com.xiaomi.smarthome.smartconfig.NetworkDetector r0 = com.xiaomi.smarthome.smartconfig.NetworkDetector.this
                java.util.HashMap r0 = r0.b
                java.lang.String r2 = r10.f22300a
                long r3 = r10.b
                java.lang.Long r3 = java.lang.Long.valueOf(r3)
                r0.put(r2, r3)
                com.xiaomi.smarthome.smartconfig.NetworkDetector r0 = com.xiaomi.smarthome.smartconfig.NetworkDetector.this
                int r0 = r0.k
                com.xiaomi.smarthome.smartconfig.NetworkDetector r2 = com.xiaomi.smarthome.smartconfig.NetworkDetector.this
                java.util.HashMap r2 = r2.b
                int r2 = r2.size()
                if (r0 != r2) goto L_0x009f
                org.json.JSONArray r5 = new org.json.JSONArray
                r5.<init>()
                com.xiaomi.smarthome.smartconfig.NetworkDetector r10 = com.xiaomi.smarthome.smartconfig.NetworkDetector.this
                java.util.HashMap r10 = r10.b
                java.util.Set r10 = r10.keySet()
                java.util.Iterator r10 = r10.iterator()
            L_0x0050:
                boolean r0 = r10.hasNext()
                if (r0 == 0) goto L_0x0081
                java.lang.Object r0 = r10.next()
                java.lang.String r0 = (java.lang.String) r0
                org.json.JSONObject r1 = new org.json.JSONObject
                r1.<init>()
                java.lang.String r2 = "cost"
                com.xiaomi.smarthome.smartconfig.NetworkDetector r3 = com.xiaomi.smarthome.smartconfig.NetworkDetector.this     // Catch:{ JSONException -> 0x007c }
                java.util.HashMap r3 = r3.b     // Catch:{ JSONException -> 0x007c }
                java.lang.Object r3 = r3.get(r0)     // Catch:{ JSONException -> 0x007c }
                r1.put(r2, r3)     // Catch:{ JSONException -> 0x007c }
                org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ JSONException -> 0x007c }
                r2.<init>()     // Catch:{ JSONException -> 0x007c }
                r2.put(r0, r1)     // Catch:{ JSONException -> 0x007c }
                r5.put(r2)     // Catch:{ JSONException -> 0x007c }
                goto L_0x0050
            L_0x007c:
                r0 = move-exception
                r0.printStackTrace()
                goto L_0x0050
            L_0x0081:
                com.xiaomi.smarthome.device.api.DeviceApi r3 = com.xiaomi.smarthome.device.api.DeviceApi.getInstance()
                android.content.Context r4 = com.xiaomi.smarthome.application.SHApplication.getAppContext()
                com.xiaomi.smarthome.smartconfig.NetworkDetector r10 = com.xiaomi.smarthome.smartconfig.NetworkDetector.this
                java.lang.String r6 = r10.l
                com.xiaomi.smarthome.smartconfig.NetworkDetector r10 = com.xiaomi.smarthome.smartconfig.NetworkDetector.this
                java.lang.String r7 = r10.h
                com.xiaomi.smarthome.smartconfig.NetworkDetector$ThreadHandler$1 r8 = new com.xiaomi.smarthome.smartconfig.NetworkDetector$ThreadHandler$1
                r8.<init>()
                r3.reportIpDetectResult(r4, r5, r6, r7, r8)
                goto L_0x01b3
            L_0x009f:
                com.xiaomi.smarthome.smartconfig.NetworkDetector r0 = com.xiaomi.smarthome.smartconfig.NetworkDetector.this
                java.util.List r0 = r0.c
                int r0 = r0.size()
                if (r0 <= 0) goto L_0x01b3
                monitor-enter(r10)
                com.xiaomi.smarthome.smartconfig.NetworkDetector r0 = com.xiaomi.smarthome.smartconfig.NetworkDetector.this     // Catch:{ all -> 0x00c0 }
                java.util.List r0 = r0.c     // Catch:{ all -> 0x00c0 }
                java.lang.Object r0 = r0.remove(r1)     // Catch:{ all -> 0x00c0 }
                java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x00c0 }
                r10.f22300a = r0     // Catch:{ all -> 0x00c0 }
                r10.notify()     // Catch:{ all -> 0x00c0 }
                monitor-exit(r10)     // Catch:{ all -> 0x00c0 }
                goto L_0x01b3
            L_0x00c0:
                r0 = move-exception
                monitor-exit(r10)     // Catch:{ all -> 0x00c0 }
                throw r0
            L_0x00c3:
                java.lang.Object r10 = r10.obj
                org.json.JSONObject r10 = (org.json.JSONObject) r10
                if (r10 != 0) goto L_0x00cb
                goto L_0x01b3
            L_0x00cb:
                java.lang.String r0 = "ips"
                org.json.JSONArray r0 = r10.optJSONArray(r0)
                if (r0 != 0) goto L_0x00d5
                goto L_0x01b3
            L_0x00d5:
                r2 = 0
            L_0x00d6:
                int r3 = r0.length()
                r4 = 0
                if (r2 >= r3) goto L_0x0100
                java.lang.String r3 = r0.getString(r2)     // Catch:{ JSONException -> 0x00f9 }
                com.xiaomi.smarthome.smartconfig.NetworkDetector r6 = com.xiaomi.smarthome.smartconfig.NetworkDetector.this     // Catch:{ JSONException -> 0x00f9 }
                java.util.HashMap r6 = r6.b     // Catch:{ JSONException -> 0x00f9 }
                java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ JSONException -> 0x00f9 }
                r6.put(r3, r4)     // Catch:{ JSONException -> 0x00f9 }
                com.xiaomi.smarthome.smartconfig.NetworkDetector r4 = com.xiaomi.smarthome.smartconfig.NetworkDetector.this     // Catch:{ JSONException -> 0x00f9 }
                java.util.List r4 = r4.c     // Catch:{ JSONException -> 0x00f9 }
                r4.add(r3)     // Catch:{ JSONException -> 0x00f9 }
                goto L_0x00fd
            L_0x00f9:
                r3 = move-exception
                r3.printStackTrace()
            L_0x00fd:
                int r2 = r2 + 1
                goto L_0x00d6
            L_0x0100:
                java.lang.String r0 = "testIps"
                boolean r0 = r10.has(r0)
                if (r0 == 0) goto L_0x0137
                java.lang.String r0 = "testIps"
                org.json.JSONArray r0 = r10.optJSONArray(r0)
                r2 = 0
            L_0x010f:
                int r3 = r0.length()
                if (r2 >= r3) goto L_0x0137
                java.lang.String r3 = r0.getString(r2)     // Catch:{ JSONException -> 0x0130 }
                com.xiaomi.smarthome.smartconfig.NetworkDetector r6 = com.xiaomi.smarthome.smartconfig.NetworkDetector.this     // Catch:{ JSONException -> 0x0130 }
                java.util.HashMap r6 = r6.b     // Catch:{ JSONException -> 0x0130 }
                java.lang.Long r7 = java.lang.Long.valueOf(r4)     // Catch:{ JSONException -> 0x0130 }
                r6.put(r3, r7)     // Catch:{ JSONException -> 0x0130 }
                com.xiaomi.smarthome.smartconfig.NetworkDetector r6 = com.xiaomi.smarthome.smartconfig.NetworkDetector.this     // Catch:{ JSONException -> 0x0130 }
                java.util.List r6 = r6.c     // Catch:{ JSONException -> 0x0130 }
                r6.add(r3)     // Catch:{ JSONException -> 0x0130 }
                goto L_0x0134
            L_0x0130:
                r3 = move-exception
                r3.printStackTrace()
            L_0x0134:
                int r2 = r2 + 1
                goto L_0x010f
            L_0x0137:
                java.lang.String r0 = "sign"
                boolean r0 = r10.has(r0)
                if (r0 == 0) goto L_0x014a
                com.xiaomi.smarthome.smartconfig.NetworkDetector r0 = com.xiaomi.smarthome.smartconfig.NetworkDetector.this
                java.lang.String r2 = "sign"
                java.lang.String r10 = r10.optString(r2)
                java.lang.String unused = r0.l = r10
            L_0x014a:
                com.xiaomi.smarthome.smartconfig.NetworkDetector r10 = com.xiaomi.smarthome.smartconfig.NetworkDetector.this
                com.xiaomi.smarthome.smartconfig.NetworkDetector r0 = com.xiaomi.smarthome.smartconfig.NetworkDetector.this
                java.util.HashMap r0 = r0.b
                int r0 = r0.size()
                int unused = r10.k = r0
                com.xiaomi.smarthome.smartconfig.NetworkDetector r10 = com.xiaomi.smarthome.smartconfig.NetworkDetector.this
                java.util.HashMap r10 = r10.b
                r10.clear()
                r10 = 0
            L_0x0163:
                r0 = 4
                if (r10 >= r0) goto L_0x0199
                com.xiaomi.smarthome.smartconfig.NetworkDetector r0 = com.xiaomi.smarthome.smartconfig.NetworkDetector.this     // Catch:{ Exception -> 0x0195 }
                java.util.List r0 = r0.c     // Catch:{ Exception -> 0x0195 }
                int r0 = r0.size()     // Catch:{ Exception -> 0x0195 }
                if (r0 <= 0) goto L_0x0199
                com.xiaomi.smarthome.smartconfig.NetworkDetector r0 = com.xiaomi.smarthome.smartconfig.NetworkDetector.this     // Catch:{ Exception -> 0x0195 }
                java.util.List r0 = r0.m     // Catch:{ Exception -> 0x0195 }
                com.xiaomi.smarthome.smartconfig.NetworkDetector$DetectThread r2 = new com.xiaomi.smarthome.smartconfig.NetworkDetector$DetectThread     // Catch:{ Exception -> 0x0195 }
                com.xiaomi.smarthome.smartconfig.NetworkDetector r3 = com.xiaomi.smarthome.smartconfig.NetworkDetector.this     // Catch:{ Exception -> 0x0195 }
                java.util.List r3 = r3.c     // Catch:{ Exception -> 0x0195 }
                java.lang.Object r3 = r3.remove(r1)     // Catch:{ Exception -> 0x0195 }
                java.lang.String r3 = (java.lang.String) r3     // Catch:{ Exception -> 0x0195 }
                com.xiaomi.smarthome.smartconfig.NetworkDetector r4 = com.xiaomi.smarthome.smartconfig.NetworkDetector.this     // Catch:{ Exception -> 0x0195 }
                com.xiaomi.smarthome.smartconfig.NetworkDetector$ThreadHandler r4 = r4.i     // Catch:{ Exception -> 0x0195 }
                r2.<init>(r3, r4)     // Catch:{ Exception -> 0x0195 }
                r0.add(r2)     // Catch:{ Exception -> 0x0195 }
                int r10 = r10 + 1
                goto L_0x0163
            L_0x0195:
                r10 = move-exception
                r10.printStackTrace()
            L_0x0199:
                com.xiaomi.smarthome.smartconfig.NetworkDetector r10 = com.xiaomi.smarthome.smartconfig.NetworkDetector.this
                java.util.List r10 = r10.m
                java.util.Iterator r10 = r10.iterator()
            L_0x01a3:
                boolean r0 = r10.hasNext()
                if (r0 == 0) goto L_0x01b3
                java.lang.Object r0 = r10.next()
                java.lang.Thread r0 = (java.lang.Thread) r0
                r0.start()
                goto L_0x01a3
            L_0x01b3:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.smartconfig.NetworkDetector.ThreadHandler.handleMessage(android.os.Message):void");
        }
    }

    public static NetworkDetector a() {
        if (f22299a == null) {
            synchronized (NetworkDetector.class) {
                if (f22299a == null) {
                    f22299a = new NetworkDetector();
                }
            }
        }
        return f22299a;
    }

    private NetworkDetector() {
    }

    /* access modifiers changed from: private */
    public void d() {
        for (Thread next : this.m) {
            ((DetectThread) next).c = false;
            next.interrupt();
        }
        this.m.clear();
    }

    /* access modifiers changed from: private */
    public static long c(String str) {
        String[] split = str.split(":");
        if (split.length != 3) {
            return -1;
        }
        if (split[2].equals("u")) {
            return a(split[0], split[1]);
        }
        return b(split[0], split[1]);
    }

    static class DetectThread extends Thread {

        /* renamed from: a  reason: collision with root package name */
        public String f22300a;
        public long b;
        public volatile boolean c = true;
        private WeakReference<ThreadHandler> d;

        DetectThread(String str, ThreadHandler threadHandler) {
            this.f22300a = str;
            this.d = new WeakReference<>(threadHandler);
        }

        /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r3 = this;
            L_0x0000:
                boolean r0 = r3.c
                if (r0 != 0) goto L_0x0005
                goto L_0x0035
            L_0x0005:
                java.lang.ref.WeakReference<com.xiaomi.smarthome.smartconfig.NetworkDetector$ThreadHandler> r0 = r3.d
                java.lang.Object r0 = r0.get()
                com.xiaomi.smarthome.smartconfig.NetworkDetector$ThreadHandler r0 = (com.xiaomi.smarthome.smartconfig.NetworkDetector.ThreadHandler) r0
                if (r0 != 0) goto L_0x0010
                goto L_0x0035
            L_0x0010:
                java.lang.String r1 = r3.f22300a
                long r1 = com.xiaomi.smarthome.smartconfig.NetworkDetector.c((java.lang.String) r1)
                r3.b = r1
                r1 = 2
                java.lang.ref.WeakReference r2 = new java.lang.ref.WeakReference
                r2.<init>(r3)
                android.os.Message r1 = android.os.Message.obtain(r0, r1, r2)
                r0.sendMessage(r1)
                monitor-enter(r3)
                r3.wait()     // Catch:{ InterruptedException -> 0x002d }
                monitor-exit(r3)     // Catch:{ all -> 0x002b }
                goto L_0x0000
            L_0x002b:
                r0 = move-exception
                goto L_0x0036
            L_0x002d:
                r0 = move-exception
                r0.printStackTrace()     // Catch:{ all -> 0x002b }
                java.lang.Thread.interrupted()     // Catch:{ all -> 0x002b }
                monitor-exit(r3)     // Catch:{ all -> 0x002b }
            L_0x0035:
                return
            L_0x0036:
                monitor-exit(r3)     // Catch:{ all -> 0x002b }
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.smartconfig.NetworkDetector.DetectThread.run():void");
        }
    }

    private static long a(String str, String str2) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            DatagramSocket datagramSocket = new DatagramSocket();
            byte[] bArr = {49, Framer.ENTER_FRAME_PREFIX, 0, 32, -1, -1, -1, -1, -1, -1, -1, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
            byte[] bArr2 = new byte[32];
            DatagramPacket datagramPacket = new DatagramPacket(bArr, bArr.length, new InetSocketAddress(str, Integer.valueOf(str2).intValue()));
            DatagramPacket datagramPacket2 = new DatagramPacket(bArr2, bArr2.length);
            datagramSocket.setSoTimeout(10000);
            datagramSocket.send(datagramPacket);
            datagramSocket.receive(datagramPacket2);
            byte b2 = bArr2[12];
            byte b3 = bArr2[13];
            byte b4 = bArr2[14];
            byte b5 = bArr2[15];
            int currentTimeMillis2 = (int) (System.currentTimeMillis() - currentTimeMillis);
            if (currentTimeMillis2 > 0) {
                return (long) currentTimeMillis2;
            }
            return -1;
        } catch (IOException | IllegalArgumentException | SocketException | UnknownHostException unused) {
            return -1;
        }
    }

    private static long b(String str, String str2) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(str, Integer.valueOf(str2).intValue()), 10000);
            if (!socket.isConnected()) {
                return -1;
            }
            socket.close();
            int currentTimeMillis2 = (int) (System.currentTimeMillis() - currentTimeMillis);
            if (currentTimeMillis2 > 0) {
                return (long) currentTimeMillis2;
            }
            return -1;
        } catch (IOException | SocketException unused) {
            return -1;
        }
    }
}
