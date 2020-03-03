package com.xiaomi.smarthome.wificonfig;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.xiaomi.smarthome.framework.log.MyLog;
import com.xiaomi.smarthome.library.common.util.MessageHandlerThread;
import com.xiaomi.smarthome.miio.Miio;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class NetworkTest {

    /* renamed from: a  reason: collision with root package name */
    public static final int f22906a = 0;
    public static final int b = 1;
    public static final int c = 2;
    private static final String d = "ot.io.mi.com";
    private static NetworkTest e = null;
    private static final int g = 1;
    private static final int h = 2;
    private static final int i = 5;
    private static final int j = 3;
    private static final int k = 4;
    private static final int l = 3;
    private static final int m = 2;
    private static final int n = 1;
    private InetAddress[] f;
    /* access modifiers changed from: private */
    public int o = 0;
    /* access modifiers changed from: private */
    public int p = 0;
    /* access modifiers changed from: private */
    public int q = 0;
    private int r = 0;
    /* access modifiers changed from: private */
    public ThreadHandler s;
    private HandlerThread t = new MessageHandlerThread("process_network2");
    /* access modifiers changed from: private */
    public Listener u;
    private Handler v = new Handler() {
        public void handleMessage(Message message) {
            switch (message.what) {
                case 3:
                    if (NetworkTest.this.u != null) {
                        NetworkTest.this.u.a();
                        return;
                    }
                    return;
                case 4:
                    if (NetworkTest.this.u != null) {
                        NetworkTest.this.u.a(message.arg1);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };

    public interface Listener {
        void a();

        void a(int i);
    }

    public void a(Listener listener) {
        this.u = listener;
    }

    public void a() {
        this.u = null;
    }

    public void b() {
        this.o = 0;
        this.p = 0;
        this.q = 0;
        this.r = 0;
        this.s = new ThreadHandler(this.t.getLooper());
        this.s.sendEmptyMessageDelayed(1, 1000);
    }

    class ThreadHandler extends Handler {
        public ThreadHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            int i = message.what;
            if (i != 5) {
                switch (i) {
                    case 1:
                        NetworkTest.this.f();
                        return;
                    case 2:
                        if (NetworkTest.this.o < 3 && NetworkTest.this.q < 2) {
                            NetworkTest.this.d();
                            return;
                        } else if (NetworkTest.this.o < 3) {
                            NetworkTest.this.s.sendEmptyMessage(5);
                            MyLog.f("send package to ot error");
                            return;
                        } else if (NetworkTest.this.o - NetworkTest.this.p > 1) {
                            NetworkTest.this.a(true, 3, 0);
                            return;
                        } else {
                            NetworkTest.this.s.sendEmptyMessage(5);
                            MyLog.f("send package to ot error");
                            return;
                        }
                    default:
                        return;
                }
            } else if (NetworkTest.this.e()) {
                NetworkTest.this.a(true, 3, 0);
            } else {
                NetworkTest.this.a(false, 4, 2);
            }
        }
    }

    public static NetworkTest c() {
        if (e == null) {
            e = new NetworkTest();
        }
        return e;
    }

    public NetworkTest() {
        this.t.start();
    }

    public void d() {
        int i2 = 0;
        while (i2 < this.f.length) {
            try {
                DatagramSocket datagramSocket = new DatagramSocket();
                byte[] bArr = {49, Framer.ENTER_FRAME_PREFIX, 0, 32, -1, -1, -1, -1, -1, -1, -1, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
                byte[] bArr2 = new byte[32];
                DatagramPacket datagramPacket = new DatagramPacket(bArr, bArr.length, this.f[i2], 8053);
                DatagramPacket datagramPacket2 = new DatagramPacket(bArr2, bArr2.length);
                datagramSocket.setSoTimeout(1000);
                datagramSocket.send(datagramPacket);
                datagramSocket.receive(datagramPacket2);
                byte b2 = bArr2[12];
                byte b3 = bArr2[13];
                byte b4 = bArr2[14];
                byte b5 = bArr2[15];
                this.o++;
                this.q = 0;
                a(true, 2, 0);
                return;
            } catch (SocketException unused) {
                Miio.g("mHost  = " + this.f[i2]);
            } catch (IOException | UnknownHostException unused2) {
            }
        }
        this.o++;
        this.p++;
        this.q++;
        Log.e("OT test", "mTotalFaildCount  = " + this.p);
        a(true, 2, 0);
        return;
        i2++;
    }

    /* access modifiers changed from: package-private */
    public boolean e() {
        int i2 = 0;
        while (i2 < this.f.length) {
            try {
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress(this.f[i2], 80), 2000);
                if (socket.isConnected()) {
                    socket.close();
                    return true;
                }
                i2++;
            } catch (IOException | SocketException unused) {
            }
        }
        MyLog.f("tcp 80 error");
        return false;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0032, code lost:
        if (r7.r < 1) goto L_0x005e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x005c, code lost:
        if (r7.r < 1) goto L_0x005e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x005e, code lost:
        r7.r++;
        r7.s.sendEmptyMessageDelayed(1, 1000);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0069, code lost:
        com.xiaomi.smarthome.framework.log.MyLog.f("dns resolve error");
        a(false, 2, 1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void f() {
        /*
            r7 = this;
            r0 = 1000(0x3e8, double:4.94E-321)
            r2 = 2
            r3 = 0
            r4 = 1
            java.lang.String r5 = "ot.io.mi.com"
            java.net.InetAddress[] r5 = java.net.InetAddress.getAllByName(r5)     // Catch:{ UnknownHostException -> 0x0056, all -> 0x0035 }
            r7.f = r5     // Catch:{ UnknownHostException -> 0x0056, all -> 0x0035 }
            java.net.InetAddress[] r5 = r7.f     // Catch:{ UnknownHostException -> 0x0056, all -> 0x0035 }
            if (r5 == 0) goto L_0x002c
            r5 = 0
        L_0x0012:
            java.net.InetAddress[] r6 = r7.f     // Catch:{ UnknownHostException -> 0x0056, all -> 0x0035 }
            int r6 = r6.length     // Catch:{ UnknownHostException -> 0x0056, all -> 0x0035 }
            if (r5 >= r6) goto L_0x002c
            java.net.InetAddress[] r6 = r7.f     // Catch:{ UnknownHostException -> 0x0056, all -> 0x0035 }
            r6 = r6[r5]     // Catch:{ UnknownHostException -> 0x0056, all -> 0x0035 }
            byte[] r6 = r6.getAddress()     // Catch:{ UnknownHostException -> 0x0056, all -> 0x0035 }
            boolean r6 = com.xiaomi.smarthome.library.common.network.NetworkUtils.a((byte[]) r6)     // Catch:{ UnknownHostException -> 0x0056, all -> 0x0035 }
            if (r6 == 0) goto L_0x0029
            r5 = 0
            r7.f = r5     // Catch:{ UnknownHostException -> 0x0056, all -> 0x0035 }
            goto L_0x002c
        L_0x0029:
            int r5 = r5 + 1
            goto L_0x0012
        L_0x002c:
            java.net.InetAddress[] r5 = r7.f
            if (r5 != 0) goto L_0x0072
            int r5 = r7.r
            if (r5 >= r4) goto L_0x0069
            goto L_0x005e
        L_0x0035:
            r5 = move-exception
            java.net.InetAddress[] r6 = r7.f
            if (r6 != 0) goto L_0x0052
            int r6 = r7.r
            if (r6 >= r4) goto L_0x0049
            int r2 = r7.r
            int r2 = r2 + r4
            r7.r = r2
            com.xiaomi.smarthome.wificonfig.NetworkTest$ThreadHandler r2 = r7.s
            r2.sendEmptyMessageDelayed(r4, r0)
            goto L_0x0055
        L_0x0049:
            java.lang.String r0 = "dns resolve error"
            com.xiaomi.smarthome.framework.log.MyLog.f(r0)
            r7.a(r3, r2, r4)
            goto L_0x0055
        L_0x0052:
            r7.a(r4, r2, r3)
        L_0x0055:
            throw r5
        L_0x0056:
            java.net.InetAddress[] r5 = r7.f
            if (r5 != 0) goto L_0x0072
            int r5 = r7.r
            if (r5 >= r4) goto L_0x0069
        L_0x005e:
            int r2 = r7.r
            int r2 = r2 + r4
            r7.r = r2
            com.xiaomi.smarthome.wificonfig.NetworkTest$ThreadHandler r2 = r7.s
            r2.sendEmptyMessageDelayed(r4, r0)
            goto L_0x0075
        L_0x0069:
            java.lang.String r0 = "dns resolve error"
            com.xiaomi.smarthome.framework.log.MyLog.f(r0)
            r7.a(r3, r2, r4)
            goto L_0x0075
        L_0x0072:
            r7.a(r4, r2, r3)
        L_0x0075:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.wificonfig.NetworkTest.f():void");
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z, int i2, int i3) {
        if (!z) {
            Message obtainMessage = this.v.obtainMessage();
            obtainMessage.what = 4;
            obtainMessage.arg1 = i3;
            this.v.sendMessage(obtainMessage);
        } else if (i2 == 3) {
            this.v.sendEmptyMessage(i2);
        } else {
            this.s.sendEmptyMessage(i2);
        }
    }

    public void g() {
        if (this.s != null) {
            this.s.removeMessages(1);
            this.s.removeMessages(2);
        }
    }
}
