package cn.com.xm.bt.profile.b;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import cn.com.xm.bt.a.a;
import cn.com.xm.bt.c.c;
import cn.com.xm.bt.c.d;
import java.security.MessageDigest;
import java.util.UUID;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public abstract class b {

    /* renamed from: a  reason: collision with root package name */
    public static final UUID f580a = c.a("FEE0");
    protected static final UUID b = c.a("FEE1");
    private cn.com.xm.bt.c.b c = null;

    protected b(cn.com.xm.bt.c.b bVar) {
        this.c = bVar;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x000b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(java.lang.Object r3, int r4) {
        /*
            r2 = this;
            if (r3 != 0) goto L_0x0003
            return
        L_0x0003:
            monitor-enter(r3)
            long r0 = (long) r4
            r3.wait(r0)     // Catch:{ Exception -> 0x000b }
            goto L_0x000b
        L_0x0009:
            r4 = move-exception
            goto L_0x000d
        L_0x000b:
            monitor-exit(r3)     // Catch:{ all -> 0x0009 }
            return
        L_0x000d:
            monitor-exit(r3)     // Catch:{ all -> 0x0009 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.com.xm.bt.profile.b.b.a(java.lang.Object, int):void");
    }

    /* access modifiers changed from: protected */
    public final void a(Object obj) {
        if (obj != null) {
            synchronized (obj) {
                obj.notify();
            }
        }
    }

    /* access modifiers changed from: protected */
    public final BluetoothGattService a(UUID uuid) {
        if (this.c == null) {
            return null;
        }
        return this.c.a(uuid);
    }

    /* access modifiers changed from: protected */
    public final boolean a(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr) {
        return this.c != null && this.c.b(bluetoothGattCharacteristic, bArr);
    }

    /* access modifiers changed from: protected */
    public final boolean a(BluetoothGattCharacteristic bluetoothGattCharacteristic, d.b bVar) {
        return this.c != null && this.c.a(bluetoothGattCharacteristic, bVar);
    }

    /* access modifiers changed from: protected */
    public final boolean a(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return this.c != null && this.c.b(bluetoothGattCharacteristic);
    }

    /* access modifiers changed from: protected */
    public byte[] c(String str) {
        try {
            return MessageDigest.getInstance("MD5").digest(str.getBytes());
        } catch (Exception e) {
            a.b("HMBaseProfile", "Exception:" + e.getMessage());
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public byte[] b(byte[] bArr, byte[] bArr2) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, com.coloros.mcssdk.c.a.b);
            Cipher instance = Cipher.getInstance("AES/ECB/NoPadding");
            instance.init(1, secretKeySpec);
            byte[] doFinal = instance.doFinal(bArr);
            a.b("HMBaseProfile", "chip value:" + c.a(doFinal));
            return doFinal;
        } catch (Exception e) {
            a.b("HMBaseProfile", "Exception:" + e.getMessage());
            return null;
        }
    }

    public BluetoothDevice g() {
        if (this.c == null) {
            return null;
        }
        return this.c.d();
    }
}
