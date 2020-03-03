package com.xiaomi.smarthome.core.server.internal.bluetooth.mesh;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import com.xiaomi.smarthome.core.server.bluetooth.IBleChannelReader;
import com.xiaomi.smarthome.core.server.bluetooth.IBleChannelWriter;
import com.xiaomi.smarthome.core.server.bluetooth.IBleMeshUpgradeResponse;
import com.xiaomi.smarthome.core.server.bluetooth.IBleResponse;
import com.xiaomi.smarthome.core.server.internal.bluetooth.channel.ChannelManager;
import com.xiaomi.smarthome.core.server.internal.bluetooth.channel.MeshDfuChannelManager;
import com.xiaomi.smarthome.frame.log.BluetoothMyLogger;
import com.xiaomi.smarthome.library.bluetooth.BluetoothConstants;
import com.xiaomi.smarthome.library.bluetooth.OTAErrorCode;
import com.xiaomi.smarthome.library.bluetooth.channel.CRC32;
import com.xiaomi.smarthome.library.bluetooth.connect.BleConnectManager;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleNotifyResponse;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleWriteResponse;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothUtils;
import com.xiaomi.smarthome.library.common.util.ByteUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.UUID;

public final class MeshDfuManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f14185a = "MeshDfuManager";
    private static final int b = 20000;
    private static final int c = 12289;
    private static final int d = 12290;
    private static final int e = 12291;
    private static volatile MeshDfuManager f;
    /* access modifiers changed from: private */
    public boolean g = false;
    /* access modifiers changed from: private */
    public String h;
    private String i;
    /* access modifiers changed from: private */
    public IBleMeshUpgradeResponse j;
    private HandlerThread k = new HandlerThread("MeshDfuManager-Thread");
    /* access modifiers changed from: private */
    public DfuHandler l;
    /* access modifiers changed from: private */
    public SendCommandRequest m;
    /* access modifiers changed from: private */
    public TransferFragmentCallback n;
    /* access modifiers changed from: private */
    public MeshFileLoader o;
    /* access modifiers changed from: private */
    public int p = 1;
    /* access modifiers changed from: private */
    public String q;
    private BroadcastReceiver r = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (TextUtils.equals(action, "com.xiaomi.smarthome.bluetooth.connect_status_changed")) {
                String stringExtra = intent.getStringExtra("key_device_address");
                if (MeshDfuManager.this.h != null && MeshDfuManager.this.h.equalsIgnoreCase(stringExtra) && intent.getIntExtra("key_connect_status", 5) == 32 && MeshDfuManager.this.j != null) {
                    if (MeshDfuManager.this.g) {
                        MeshDfuManager.this.a(-101, "BLE DISCONNECT");
                    } else {
                        MeshDfuManager.this.a(-105, " BLE disconnect");
                    }
                }
            } else if (TextUtils.equals(action, "com.xiaomi.smarthome.bluetooth.character_changed")) {
                String stringExtra2 = intent.getStringExtra("key_device_address");
                if (MeshDfuManager.this.h != null && MeshDfuManager.this.h.equalsIgnoreCase(stringExtra2)) {
                    UUID uuid = (UUID) intent.getSerializableExtra("key_service_uuid");
                    UUID uuid2 = (UUID) intent.getSerializableExtra("key_character_uuid");
                    byte[] byteArrayExtra = intent.getByteArrayExtra("key_character_value");
                    if (uuid != null && uuid2 != null) {
                        MeshDfuManager.this.a(uuid, uuid2, byteArrayExtra);
                    }
                }
            }
        }
    };
    private IBleChannelWriter s;
    private IBleChannelReader t = new IBleChannelReader() {
        public IBinder asBinder() {
            return null;
        }

        public void onRead(String str, byte[] bArr, int i) throws RemoteException {
        }
    };

    private interface SendCommandCallback {
        void a(int i, String str);

        void a(byte[] bArr);
    }

    private interface TransferFragmentCallback {
        void a(int i, int i2);

        void a(int i, String str);
    }

    private int c(int i2) {
        switch (i2) {
            case 1:
                return OTAErrorCode.y;
            case 2:
                return OTAErrorCode.x;
            case 3:
                return OTAErrorCode.z;
            case 4:
                return OTAErrorCode.A;
            case 5:
                return OTAErrorCode.B;
            case 6:
                return OTAErrorCode.C;
            default:
                return OTAErrorCode.D;
        }
    }

    /* access modifiers changed from: private */
    public int d(int i2) {
        switch (i2) {
            case -3:
                return OTAErrorCode.v;
            case -2:
                return OTAErrorCode.u;
            default:
                return OTAErrorCode.w;
        }
    }

    private MeshDfuManager() {
        this.k.start();
        this.l = new DfuHandler(this.k.getLooper());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.xiaomi.smarthome.bluetooth.character_changed");
        intentFilter.addAction("com.xiaomi.smarthome.bluetooth.connect_status_changed");
        BluetoothUtils.a(this.r, intentFilter);
    }

    public static MeshDfuManager a() {
        if (f == null) {
            synchronized (MeshDfuManager.class) {
                if (f == null) {
                    f = new MeshDfuManager();
                }
            }
        }
        return f;
    }

    public boolean b() {
        return this.g;
    }

    public void a(String str, String str2, String str3, IBleMeshUpgradeResponse iBleMeshUpgradeResponse) {
        BluetoothLog.c(String.format("MeshDfuManager startUpgrade mac = %s, filePath = %s", new Object[]{str2, str3}));
        if (iBleMeshUpgradeResponse == null) {
            BluetoothMyLogger.e("MeshDfuManager startUpdate failed, callback is null");
        } else if (TextUtils.isEmpty(str2)) {
            BluetoothMyLogger.e("MeshDfuManager startUpdate failed, mac is null");
            a(iBleMeshUpgradeResponse, -102, "mac is null");
        } else if (this.g) {
            BluetoothMyLogger.e("MeshDfuManager startUpdate failed, has another updating");
            a(iBleMeshUpgradeResponse, -103, "has another updating");
        } else if (!BluetoothUtils.c(str2)) {
            BluetoothMyLogger.e("MeshDfuManager startUpdate failed, device is not connected");
            a(iBleMeshUpgradeResponse, -100, "device is not connected");
        } else if (!b(str3)) {
            BluetoothMyLogger.e("MeshDfuManager startUpdate failed, file is not exist : " + str3);
            a(iBleMeshUpgradeResponse, -104, "file is not exist");
        } else {
            this.g = true;
            this.h = str2;
            this.i = str3;
            this.j = iBleMeshUpgradeResponse;
            this.q = str;
            this.s = MeshDfuChannelManager.e().b(str2, this.t);
            d();
        }
    }

    public void a(String str) {
        BluetoothMyLogger.d(String.format("MeshDfuManager cancelUpgrade mac = %s", new Object[]{BluetoothMyLogger.a(str)}));
        if (this.g && TextUtils.equals(str, this.h)) {
            ChannelManager.BleChannel f2 = MeshDfuChannelManager.e().f(this.h);
            if (f2 != null) {
                f2.f();
            }
            a(2, "cancel upgrade");
        }
    }

    private void a(IBleMeshUpgradeResponse iBleMeshUpgradeResponse, int i2, String str) {
        if (iBleMeshUpgradeResponse != null) {
            try {
                iBleMeshUpgradeResponse.onResponse(i2, str);
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }
    }

    private boolean b(String str) {
        File file = new File(str);
        return file.exists() && file.length() > 0;
    }

    private void d() {
        BleConnectManager.a().a(this.h, BluetoothConstants.i, BluetoothConstants.T, (BleNotifyResponse) new BleNotifyResponse() {
            public void a(int i, Void voidR) {
                if (i == 0) {
                    MeshDfuManager.this.c();
                } else {
                    MeshDfuManager.this.a(-107, "open command notify failed");
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void c() {
        BleConnectManager.a().a(this.h, BluetoothConstants.i, BluetoothConstants.U, (BleNotifyResponse) new BleNotifyResponse() {
            public void a(int i, Void voidR) {
                if (i == 0) {
                    try {
                        if (MeshDfuManager.this.j == null || !MeshDfuManager.this.j.isMeshDevice() || MeshDfuManager.this.a("1.4.0", MeshDfuManager.this.q) <= 0) {
                            MeshDfuManager.this.e();
                            return;
                        }
                        BluetoothLog.a("mesh device skip protocol version,current version is %s", MeshDfuManager.this.q);
                        int unused = MeshDfuManager.this.p = -1;
                        MeshDfuManager.this.f();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                } else {
                    MeshDfuManager.this.a(-108, "open data notify failed");
                }
            }
        });
    }

    public int a(String str, String str2) {
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            return 0;
        }
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        if (TextUtils.isEmpty(str2)) {
            return 1;
        }
        String[] split = str.split("[._]");
        String[] split2 = str2.split("[._]");
        int min = Math.min(split.length, split2.length);
        int i2 = 0;
        while (i2 < min) {
            try {
                int parseInt = Integer.parseInt(split[i2]);
                int parseInt2 = Integer.parseInt(split2[i2]);
                if (parseInt != parseInt2) {
                    return parseInt - parseInt2;
                }
                i2++;
            } catch (Exception e2) {
                BluetoothLog.a((Throwable) e2);
                return 0;
            }
        }
        return split.length - split2.length;
    }

    /* access modifiers changed from: private */
    public void e() {
        a(1, (byte[]) null, (SendCommandCallback) new SendCommandCallback() {
            public void a(byte[] bArr) {
                if (bArr == null || bArr.length < 2) {
                    MeshDfuManager.this.a((int) OTAErrorCode.r, "get fragment size failed");
                    return;
                }
                short s = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN).getShort();
                BluetoothLog.a("get Protocol version %d", Integer.valueOf(s));
                int unused = MeshDfuManager.this.p = s;
                if (s <= 2) {
                    MeshDfuManager.this.f();
                    return;
                }
                MeshDfuManager meshDfuManager = MeshDfuManager.this;
                meshDfuManager.a(3, "should update app , current app not suit protocol version " + s);
            }

            public void a(int i, String str) {
                MeshDfuManager.this.a((int) OTAErrorCode.r, str);
            }
        });
    }

    /* access modifiers changed from: private */
    public void f() {
        a(2, (byte[]) null, (SendCommandCallback) new SendCommandCallback() {
            public void a(byte[] bArr) {
                if (bArr == null || bArr.length < 2) {
                    MeshDfuManager.this.a((int) OTAErrorCode.t, "get fragment size failed");
                    return;
                }
                short s = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN).getShort();
                if (s <= 0) {
                    MeshDfuManager.this.a((int) OTAErrorCode.t, "fragment size is 0");
                } else if (MeshDfuManager.this.p == 2) {
                    MeshDfuManager.this.a((int) s);
                } else {
                    MeshDfuManager.this.b((int) s);
                }
            }

            public void a(int i, String str) {
                MeshDfuManager.this.a((int) OTAErrorCode.t, str);
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(final int i2) {
        a(3, (byte[]) null, (SendCommandCallback) new SendCommandCallback() {
            public void a(byte[] bArr) {
                if (bArr == null || bArr.length < 2) {
                    MeshDfuManager.this.a((int) OTAErrorCode.s, "get last fragment index failed");
                    return;
                }
                ByteBuffer order = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
                short s = order.getShort();
                int i = order.getInt();
                ByteBuffer order2 = ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN);
                order2.putInt(i);
                BluetoothLog.d("remote hex = " + ByteUtils.d(order2.array()));
                if (s == 0) {
                    BluetoothLog.d("getLastFragmentIndex =0");
                    MeshDfuManager.this.b(i2);
                    return;
                }
                boolean a2 = MeshDfuManager.this.a(i2, (int) s, i);
                BluetoothLog.d("getLastFragmentIndex,check result=" + a2);
                if (a2) {
                    MeshDfuManager.this.a(i2, (int) s);
                } else {
                    MeshDfuManager.this.b(i2);
                }
            }

            public void a(int i, String str) {
                MeshDfuManager.this.a((int) OTAErrorCode.s, str);
            }
        });
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x007a A[SYNTHETIC, Splitter:B:28:0x007a] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0085 A[SYNTHETIC, Splitter:B:34:0x0085] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(int r11, int r12, int r13) {
        /*
            r10 = this;
            java.lang.String r0 = r10.i
            boolean r0 = r10.b((java.lang.String) r0)
            r1 = 0
            if (r0 != 0) goto L_0x000a
            return r1
        L_0x000a:
            r0 = 0
            java.io.File r2 = new java.io.File     // Catch:{ FileNotFoundException -> 0x0074 }
            java.lang.String r3 = r10.i     // Catch:{ FileNotFoundException -> 0x0074 }
            r2.<init>(r3)     // Catch:{ FileNotFoundException -> 0x0074 }
            long r3 = r2.length()     // Catch:{ FileNotFoundException -> 0x0074 }
            int r5 = r11 * r12
            long r5 = (long) r5     // Catch:{ FileNotFoundException -> 0x0074 }
            java.lang.String r7 = "check last fragment Index,fragmentSize =%d, lastIndex =%d,fileSize = %d,crc32=%d"
            r8 = 4
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch:{ FileNotFoundException -> 0x0074 }
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)     // Catch:{ FileNotFoundException -> 0x0074 }
            r8[r1] = r11     // Catch:{ FileNotFoundException -> 0x0074 }
            java.lang.Integer r11 = java.lang.Integer.valueOf(r12)     // Catch:{ FileNotFoundException -> 0x0074 }
            r12 = 1
            r8[r12] = r11     // Catch:{ FileNotFoundException -> 0x0074 }
            r11 = 2
            java.lang.Long r9 = java.lang.Long.valueOf(r3)     // Catch:{ FileNotFoundException -> 0x0074 }
            r8[r11] = r9     // Catch:{ FileNotFoundException -> 0x0074 }
            r11 = 3
            java.lang.Integer r9 = java.lang.Integer.valueOf(r13)     // Catch:{ FileNotFoundException -> 0x0074 }
            r8[r11] = r9     // Catch:{ FileNotFoundException -> 0x0074 }
            com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog.a((java.lang.String) r7, (java.lang.Object[]) r8)     // Catch:{ FileNotFoundException -> 0x0074 }
            int r11 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r11 <= 0) goto L_0x0041
            goto L_0x0042
        L_0x0041:
            r3 = r5
        L_0x0042:
            java.io.FileInputStream r11 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x0074 }
            r11.<init>(r2)     // Catch:{ FileNotFoundException -> 0x0074 }
            int r0 = a((java.io.InputStream) r11, (long) r3)     // Catch:{ FileNotFoundException -> 0x006f, all -> 0x006c }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x006f, all -> 0x006c }
            r2.<init>()     // Catch:{ FileNotFoundException -> 0x006f, all -> 0x006c }
            java.lang.String r3 = "check local CRC32="
            r2.append(r3)     // Catch:{ FileNotFoundException -> 0x006f, all -> 0x006c }
            r2.append(r0)     // Catch:{ FileNotFoundException -> 0x006f, all -> 0x006c }
            java.lang.String r2 = r2.toString()     // Catch:{ FileNotFoundException -> 0x006f, all -> 0x006c }
            com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog.d(r2)     // Catch:{ FileNotFoundException -> 0x006f, all -> 0x006c }
            if (r13 != r0) goto L_0x0062
            goto L_0x0063
        L_0x0062:
            r12 = 0
        L_0x0063:
            r11.close()     // Catch:{ IOException -> 0x0067 }
            goto L_0x006b
        L_0x0067:
            r11 = move-exception
            r11.printStackTrace()
        L_0x006b:
            return r12
        L_0x006c:
            r12 = move-exception
            r0 = r11
            goto L_0x0083
        L_0x006f:
            r12 = move-exception
            r0 = r11
            goto L_0x0075
        L_0x0072:
            r12 = move-exception
            goto L_0x0083
        L_0x0074:
            r12 = move-exception
        L_0x0075:
            r12.printStackTrace()     // Catch:{ all -> 0x0072 }
            if (r0 == 0) goto L_0x0082
            r0.close()     // Catch:{ IOException -> 0x007e }
            goto L_0x0082
        L_0x007e:
            r11 = move-exception
            r11.printStackTrace()
        L_0x0082:
            return r1
        L_0x0083:
            if (r0 == 0) goto L_0x008d
            r0.close()     // Catch:{ IOException -> 0x0089 }
            goto L_0x008d
        L_0x0089:
            r11 = move-exception
            r11.printStackTrace()
        L_0x008d:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.core.server.internal.bluetooth.mesh.MeshDfuManager.a(int, int, int):boolean");
    }

    public static int a(InputStream inputStream, long j2) {
        int i2;
        IOException e2;
        byte[] bArr = new byte[2048];
        long j3 = j2;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            boolean z = true;
            i3++;
            try {
                int read = inputStream.read(bArr);
                if (read == 0) {
                    return i4;
                }
                long j4 = (long) read;
                if (j4 < j3) {
                    j3 -= j4;
                    z = false;
                } else {
                    read = (int) j3;
                }
                i2 = CRC32.a(bArr, read, i4);
                try {
                    BluetoothLog.d("length =" + read);
                    ByteBuffer order = ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN);
                    order.putInt(i2);
                    if (z) {
                        BluetoothLog.d("index =" + i3 + " ,crc32 hex =" + ByteUtils.d(order.array()));
                        return i2;
                    }
                    i4 = i2;
                } catch (IOException e3) {
                    e2 = e3;
                    e2.printStackTrace();
                    i4 = i2;
                }
            } catch (IOException e4) {
                i2 = i4;
                e2 = e4;
                e2.printStackTrace();
                i4 = i2;
            }
        }
    }

    /* access modifiers changed from: private */
    public void b(int i2) {
        BluetoothLog.c("MeshDfuManager sendData fragmentSize = " + i2);
        if (!new File(this.i).exists()) {
            a(-104, "file don't exist");
            return;
        }
        this.o = new MeshFileLoader(this.i, i2);
        g();
    }

    /* access modifiers changed from: private */
    public void a(int i2, int i3) {
        BluetoothLog.a("send data fragment size = %d,last fragment index =%d", Integer.valueOf(i2), Integer.valueOf(i3));
        if (!new File(this.i).exists()) {
            a(-104, "file don't exist");
            return;
        }
        this.o = new MeshFileLoader(this.i, i2);
        this.o.a(i3);
        g();
    }

    /* access modifiers changed from: private */
    public void g() {
        if (this.o != null) {
            if (this.o.c()) {
                h();
                return;
            }
            byte[] a2 = this.o.a();
            if (a2 == null) {
                BluetoothMyLogger.e("MeshDfuManager sendFragmentData buffer is null");
                a(-1, "read file error");
                return;
            }
            this.l.removeMessages(12290);
            this.l.sendEmptyMessage(12290);
            StringBuilder sb = new StringBuilder();
            sb.append("MeshDfuManager sendFragmentData fragment index = ");
            sb.append(this.o.f14196a - 1);
            BluetoothLog.c(sb.toString());
            a(a2, (TransferFragmentCallback) new TransferFragmentCallback() {
                public void a(int i, int i2) {
                    MeshDfuManager.this.l.sendEmptyMessage(12291);
                }

                public void a(int i, String str) {
                    MeshDfuManager.this.a(i, str);
                }
            });
        }
    }

    private void h() {
        BluetoothLog.c("MeshDfuManager switchFirmware");
        a(4, ByteUtils.a(1), (SendCommandCallback) new SendCommandCallback() {
            public void a(byte[] bArr) {
                if (MeshDfuManager.this.j != null) {
                    try {
                        MeshDfuManager.this.j.onProgress(100);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                MeshDfuManager.this.i();
            }

            public void a(int i, String str) {
                MeshDfuManager meshDfuManager = MeshDfuManager.this;
                meshDfuManager.a(i, str + ":switch firmware fail");
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(UUID uuid, UUID uuid2, byte[] bArr) {
        ChannelManager.BleChannel f2;
        if (!BluetoothConstants.i.equals(uuid)) {
            return;
        }
        if (BluetoothConstants.T.equals(uuid2)) {
            if (bArr != null && bArr.length > 0) {
                switch (bArr[0]) {
                    case 1:
                        a(new DfuCommandCompleteEvent(bArr));
                        return;
                    case 2:
                        a(new DfuTransferCompleteEvent(bArr));
                        return;
                    default:
                        return;
                }
            }
        } else if (BluetoothConstants.U.equals(uuid2) && (f2 = MeshDfuChannelManager.e().f(this.h)) != null) {
            f2.a(bArr);
        }
    }

    private void a(DfuCommandCompleteEvent dfuCommandCompleteEvent) {
        if (this.m == null || this.m.f14197a != dfuCommandCompleteEvent.c()) {
            BluetoothMyLogger.d(String.format("MeshDfuManager processCommandEvent (%s) error, can't find callback", new Object[]{dfuCommandCompleteEvent.toString()}));
            return;
        }
        this.l.removeMessages(12289);
        if (this.m.b == null) {
            return;
        }
        if (dfuCommandCompleteEvent.a() == 0) {
            this.m.b.a(dfuCommandCompleteEvent.d());
            return;
        }
        this.m.b.a(c(dfuCommandCompleteEvent.a()), (String) null);
        BluetoothMyLogger.d(String.format("MeshDfuManager processCommandEvent (%s) error, status failed", new Object[]{dfuCommandCompleteEvent.toString()}));
    }

    private void a(DfuTransferCompleteEvent dfuTransferCompleteEvent) {
        if (this.n == null) {
            BluetoothMyLogger.d(String.format("MeshDfuManager processTransferEvent (%s) error, can't find callback", new Object[]{dfuTransferCompleteEvent.toString()}));
        } else if (dfuTransferCompleteEvent.a() == 0) {
            this.n.a(dfuTransferCompleteEvent.a(), dfuTransferCompleteEvent.c());
        } else {
            this.n.a(c(dfuTransferCompleteEvent.a()), (String) null);
            BluetoothMyLogger.d(String.format("MeshDfuManager processTransferEvent (%s) error, status failed", new Object[]{dfuTransferCompleteEvent.toString()}));
        }
    }

    private void a(int i2, byte[] bArr, final SendCommandCallback sendCommandCallback) {
        try {
            DfuCommand dfuCommand = new DfuCommand(i2, bArr);
            this.m = new SendCommandRequest(i2, sendCommandCallback);
            this.l.sendEmptyMessageDelayed(12289, 20000);
            BleConnectManager.a().a(this.h, BluetoothConstants.i, BluetoothConstants.T, dfuCommand.a(), (BleWriteResponse) new BleWriteResponse() {
                public void a(int i, Void voidR) {
                    if (i != 0) {
                        SendCommandRequest unused = MeshDfuManager.this.m = null;
                        MeshDfuManager.this.l.removeMessages(12289);
                        sendCommandCallback.a(i, "send command failed");
                    }
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
            sendCommandCallback.a(-11, "params exceed max size");
        }
    }

    private void a(byte[] bArr, final TransferFragmentCallback transferFragmentCallback) {
        if (this.s == null) {
            transferFragmentCallback.a(-1, "channel write is null");
            return;
        }
        try {
            this.n = transferFragmentCallback;
            this.s.write(bArr, 0, new IBleResponse() {
                public IBinder asBinder() {
                    return null;
                }

                public void onResponse(int i, Bundle bundle) throws RemoteException {
                    if (i != 0) {
                        TransferFragmentCallback unused = MeshDfuManager.this.n = null;
                        transferFragmentCallback.a(MeshDfuManager.this.d(i), "channel write failed");
                    }
                }
            });
        } catch (RemoteException e2) {
            e2.printStackTrace();
            transferFragmentCallback.a(-1, "channel write exception");
        }
    }

    /* access modifiers changed from: private */
    public void a(int i2, String str) {
        BluetoothMyLogger.d(String.format("MeshDfuManager onUpgradeFailed errorCode = %d, errorMsg = %s", new Object[]{Integer.valueOf(i2), str}));
        a(this.j, i2, str);
        j();
    }

    /* access modifiers changed from: private */
    public void i() {
        BluetoothMyLogger.d("MeshDfuManager onUpgradeSuccess ");
        a(this.j, 0, (String) null);
        j();
    }

    private void j() {
        if (this.s != null) {
            MeshDfuChannelManager.e().a(this.h, this.t);
            this.s = null;
        }
        if (this.o != null) {
            this.o.d();
            this.o = null;
        }
        this.l.removeMessages(12289);
        this.l.removeMessages(12290);
        this.m = null;
        this.n = null;
        this.j = null;
        this.g = false;
        this.h = null;
        this.i = null;
    }

    private class DfuHandler extends Handler {
        public DfuHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 12289:
                    if (!(MeshDfuManager.this.m == null || MeshDfuManager.this.m.b == null)) {
                        MeshDfuManager.this.m.b.a(-7, "send command timeout");
                    }
                    SendCommandRequest unused = MeshDfuManager.this.m = null;
                    return;
                case 12290:
                    if (MeshDfuManager.this.j != null) {
                        try {
                            MeshDfuManager.this.j.onProgress(MeshDfuManager.this.o.b());
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                        MeshDfuManager.this.l.sendEmptyMessageDelayed(12290, 400);
                        return;
                    }
                    return;
                case 12291:
                    MeshDfuManager.this.g();
                    return;
                default:
                    return;
            }
        }
    }

    private class MeshFileLoader {

        /* renamed from: a  reason: collision with root package name */
        short f14196a = 1;
        private long c;
        private long d = 0;
        private int e;
        private byte[] f;
        private int g = 0;
        private int h = 0;
        private byte[] i;
        private FileInputStream j;
        private boolean k = false;

        public MeshFileLoader(String str, int i2) {
            this.e = i2;
            this.f = new byte[(i2 * 10)];
            this.i = new byte[(i2 + 2)];
            File file = new File(str);
            this.c = file.length();
            try {
                this.j = new FileInputStream(file);
            } catch (FileNotFoundException e2) {
                e2.printStackTrace();
            }
        }

        public boolean a(int i2) {
            long j2 = (long) (this.e * i2);
            if (j2 > this.c) {
                j2 = this.c;
            }
            try {
                if (this.j.skip(j2) != j2) {
                    return false;
                }
                this.f14196a = (short) (((short) i2) + 1);
                this.d = j2;
                return true;
            } catch (IOException e2) {
                e2.printStackTrace();
                return false;
            }
        }

        public byte[] a() {
            byte[] bArr;
            if (this.k) {
                BluetoothLog.c("MeshDfuManager getFragmentBuffer has send complete");
                return null;
            } else if (this.j == null) {
                BluetoothMyLogger.d("MeshDfuManager getFragmentBuffer fis is null");
                return null;
            } else if (this.h >= this.g && !e()) {
                BluetoothMyLogger.d("MeshDfuManager loadFromFile failed");
                return null;
            } else if (this.g == 0 || this.g == -1) {
                BluetoothLog.c("MeshDfuManager getFragmentBuffer fileBufferCount is " + this.g);
                return null;
            } else {
                if (this.g - this.h >= this.e) {
                    System.arraycopy(this.f, this.h, this.i, 2, this.e);
                    bArr = this.i;
                    this.d += (long) this.e;
                    this.h += this.e;
                } else {
                    bArr = new byte[((this.g - this.h) + 2)];
                    System.arraycopy(this.f, this.h, bArr, 2, this.g - this.h);
                    this.d += (long) (this.g - this.h);
                    this.h = this.g;
                }
                byte[] a2 = ByteUtils.a(this.f14196a);
                bArr[0] = a2[0];
                bArr[1] = a2[1];
                this.f14196a = (short) (this.f14196a + 1);
                if (this.d >= this.c) {
                    this.k = true;
                }
                return bArr;
            }
        }

        public int b() {
            long j2 = (this.d * 100) / this.c;
            if (j2 > 100) {
                j2 = 100;
            }
            return (int) j2;
        }

        private boolean e() {
            boolean z;
            try {
                this.g = this.j.read(this.f);
                z = true;
            } catch (IOException e2) {
                e2.printStackTrace();
                z = false;
            }
            this.h = 0;
            return z;
        }

        public boolean c() {
            return this.k;
        }

        /* access modifiers changed from: package-private */
        public void d() {
            if (this.j != null) {
                try {
                    this.j.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                this.j = null;
            }
            this.f = null;
            this.i = null;
        }
    }

    private class SendCommandRequest {

        /* renamed from: a  reason: collision with root package name */
        int f14197a;
        SendCommandCallback b;

        public SendCommandRequest(int i, SendCommandCallback sendCommandCallback) {
            this.f14197a = i;
            this.b = sendCommandCallback;
        }
    }
}
