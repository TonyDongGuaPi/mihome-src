package com.xiaomi.smarthome.library.bluetooth.connect;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.xiaomi.smarthome.library.bluetooth.BluetoothConstants;
import com.xiaomi.smarthome.library.bluetooth.BluetoothContextManager;
import com.xiaomi.smarthome.library.bluetooth.Constants;
import com.xiaomi.smarthome.library.bluetooth.connect.listener.GattResponseListener;
import com.xiaomi.smarthome.library.bluetooth.connect.listener.ReadCharacterListener;
import com.xiaomi.smarthome.library.bluetooth.connect.listener.ReadRssiListener;
import com.xiaomi.smarthome.library.bluetooth.connect.listener.RequestMtuListener;
import com.xiaomi.smarthome.library.bluetooth.connect.listener.ServiceDiscoverListener;
import com.xiaomi.smarthome.library.bluetooth.connect.listener.WriteCharacterListener;
import com.xiaomi.smarthome.library.bluetooth.connect.listener.WriteDescriptorListener;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleResponse;
import com.xiaomi.smarthome.library.bluetooth.proxy.ProxyBulk;
import com.xiaomi.smarthome.library.bluetooth.proxy.ProxyInterceptor;
import com.xiaomi.smarthome.library.bluetooth.proxy.ProxyUtils;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothUtils;
import com.xiaomi.smarthome.library.common.util.ByteUtils;
import com.xiaomi.smarthome.library.common.util.Version;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@TargetApi(18)
public class BleConnectWorker implements Handler.Callback, IBleConnectWorker, IBluetoothGattResponse, RuntimeChecker, ProxyInterceptor {

    /* renamed from: a  reason: collision with root package name */
    private static final int f18511a = 288;
    private static final int b = 289;
    private static final boolean c = (!BluetoothContextManager.o());
    private BluetoothGatt d;
    private BluetoothDevice e;
    /* access modifiers changed from: private */
    public GattResponseListener f;
    private Handler g;
    private volatile int h;
    private BleGattProfile i;
    private Map<UUID, Map<UUID, BluetoothGattCharacteristic>> j;
    private IBluetoothGattResponse k;
    private RuntimeChecker l;
    private final Object m = new Object();

    private boolean b(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return true;
    }

    public BleConnectWorker(String str, RuntimeChecker runtimeChecker) {
        BluetoothAdapter e2 = BluetoothUtils.e();
        if (e2 != null) {
            this.e = e2.getRemoteDevice(str);
            this.l = runtimeChecker;
            this.g = new Handler(Looper.myLooper(), this);
            this.j = new HashMap();
            this.k = (IBluetoothGattResponse) ProxyUtils.a((Object) this, (Class<?>) IBluetoothGattResponse.class, (ProxyInterceptor) this);
            return;
        }
        throw new IllegalStateException("ble adapter null");
    }

    private void i() {
        if (c) {
            BluetoothLog.c(String.format("refreshServiceProfile for %s", new Object[]{this.e.getAddress()}));
        }
        if (this.d != null) {
            List<BluetoothGattService> services = this.d.getServices();
            HashMap hashMap = new HashMap();
            for (BluetoothGattService next : services) {
                UUID uuid = next.getUuid();
                Map map = (Map) hashMap.get(uuid);
                if (map == null) {
                    if (c) {
                        BluetoothLog.c("Service: " + uuid);
                    }
                    map = new HashMap();
                    hashMap.put(next.getUuid(), map);
                }
                for (BluetoothGattCharacteristic next2 : next.getCharacteristics()) {
                    UUID uuid2 = next2.getUuid();
                    if (c) {
                        BluetoothLog.c("character: uuid = " + uuid2);
                    }
                    map.put(next2.getUuid(), next2);
                }
            }
            this.j.clear();
            this.j.putAll(hashMap);
            this.i = new BleGattProfile(this.j);
        } else if (c) {
            BluetoothLog.b(String.format("ble gatt null", new Object[0]));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r0 = r2.j.get(r3);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.bluetooth.BluetoothGattCharacteristic b(java.util.UUID r3, java.util.UUID r4) {
        /*
            r2 = this;
            if (r3 == 0) goto L_0x0015
            if (r4 == 0) goto L_0x0015
            java.util.Map<java.util.UUID, java.util.Map<java.util.UUID, android.bluetooth.BluetoothGattCharacteristic>> r0 = r2.j
            java.lang.Object r0 = r0.get(r3)
            java.util.Map r0 = (java.util.Map) r0
            if (r0 == 0) goto L_0x0015
            java.lang.Object r0 = r0.get(r4)
            android.bluetooth.BluetoothGattCharacteristic r0 = (android.bluetooth.BluetoothGattCharacteristic) r0
            goto L_0x0016
        L_0x0015:
            r0 = 0
        L_0x0016:
            if (r0 != 0) goto L_0x0028
            android.bluetooth.BluetoothGatt r1 = r2.d
            if (r1 == 0) goto L_0x0028
            android.bluetooth.BluetoothGatt r1 = r2.d
            android.bluetooth.BluetoothGattService r3 = r1.getService(r3)
            if (r3 == 0) goto L_0x0028
            android.bluetooth.BluetoothGattCharacteristic r0 = r3.getCharacteristic(r4)
        L_0x0028:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.library.bluetooth.connect.BleConnectWorker.b(java.util.UUID, java.util.UUID):android.bluetooth.BluetoothGattCharacteristic");
    }

    /* access modifiers changed from: private */
    public void e(int i2) {
        if (c) {
            BluetoothLog.c(String.format("setConnectStatus status = %s", new Object[]{Constants.a(i2)}));
        }
        this.h = i2;
    }

    public void a(int i2, int i3) {
        c();
        if (c) {
            BluetoothLog.c(String.format("onConnectionStateChange for %s: status = %d, newState = %d", new Object[]{this.e.getAddress(), Integer.valueOf(i2), Integer.valueOf(i3)}));
        }
        if (i2 == 0 && i3 == 2) {
            e(2);
            if (this.e.getBondState() == 12) {
                BluetoothLog.d("Waiting 1600 ms for a possible Service Changed indication...");
                b(1600);
            }
            if (this.f != null) {
                this.f.a(true);
                return;
            }
            return;
        }
        k();
    }

    public void a(int i2) {
        c();
        if (c) {
            BluetoothLog.c(String.format("onServicesDiscovered for %s: status = %d", new Object[]{this.e.getAddress(), Integer.valueOf(i2)}));
        }
        if (i2 == 0) {
            e(19);
            f(16);
            i();
        }
        if (this.f != null && (this.f instanceof ServiceDiscoverListener)) {
            ((ServiceDiscoverListener) this.f).a(i2, this.i);
        }
    }

    public void a(BluetoothGattCharacteristic bluetoothGattCharacteristic, int i2, byte[] bArr) {
        c();
        if (c) {
            BluetoothLog.c(String.format("onCharacteristicRead for %s: status = %d, service = 0x%s, character = 0x%s, value = %s", new Object[]{this.e.getAddress(), Integer.valueOf(i2), bluetoothGattCharacteristic.getService().getUuid(), bluetoothGattCharacteristic.getUuid(), ByteUtils.d(bArr)}));
        }
        if (this.f != null && (this.f instanceof ReadCharacterListener)) {
            ((ReadCharacterListener) this.f).a(bluetoothGattCharacteristic, i2, bArr);
        }
    }

    public void b(BluetoothGattCharacteristic bluetoothGattCharacteristic, int i2, byte[] bArr) {
        c();
        if (c) {
            BluetoothLog.c(String.format("onCharacteristicWrite for %s: status = %d, service = 0x%s, character = 0x%s, value = %s", new Object[]{this.e.getAddress(), Integer.valueOf(i2), bluetoothGattCharacteristic.getService().getUuid(), bluetoothGattCharacteristic.getUuid(), ByteUtils.d(bArr)}));
        }
        if (this.f != null && (this.f instanceof WriteCharacterListener)) {
            ((WriteCharacterListener) this.f).a(bluetoothGattCharacteristic, i2, bArr);
        }
        a(bluetoothGattCharacteristic.getService().getUuid(), bluetoothGattCharacteristic.getUuid(), bArr, i2);
    }

    public void a(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr) {
        c();
        if (c) {
            BluetoothLog.c(String.format("onCharacteristicChanged for %s: value = %s, service = 0x%s, character = 0x%s", new Object[]{this.e.getAddress(), ByteUtils.d(bArr), bluetoothGattCharacteristic.getService().getUuid(), bluetoothGattCharacteristic.getUuid()}));
        }
        c(bluetoothGattCharacteristic.getService().getUuid(), bluetoothGattCharacteristic.getUuid(), bArr);
    }

    public void a(BluetoothGattDescriptor bluetoothGattDescriptor, int i2) {
        c();
        if (c) {
            BluetoothLog.c(String.format("onDescriptorWrite for %s: status = %d, service = 0x%s, character = 0x%s, descriptor = 0x%s", new Object[]{this.e.getAddress(), Integer.valueOf(i2), bluetoothGattDescriptor.getCharacteristic().getService().getUuid(), bluetoothGattDescriptor.getCharacteristic().getUuid(), bluetoothGattDescriptor.getUuid()}));
        }
        if (this.f != null && (this.f instanceof WriteDescriptorListener)) {
            ((WriteDescriptorListener) this.f).a(bluetoothGattDescriptor, i2);
        }
    }

    public void b(int i2, int i3) {
        c();
        if (c) {
            BluetoothLog.c(String.format("onReadRemoteRssi for %s, rssi = %d, status = %d", new Object[]{this.e.getAddress(), Integer.valueOf(i2), Integer.valueOf(i3)}));
        }
        if (this.f != null && (this.f instanceof ReadRssiListener)) {
            ((ReadRssiListener) this.f).a(i2, i3);
        }
    }

    public void c(int i2, int i3) {
        c();
        if (c) {
            BluetoothLog.c(String.format("onMtuChanged for %s, mtu = %d, status = %d", new Object[]{this.e.getAddress(), Integer.valueOf(i2), Integer.valueOf(i3)}));
        }
        if (this.f != null && (this.f instanceof RequestMtuListener)) {
            ((RequestMtuListener) this.f).a(i2, i3);
        }
    }

    /* access modifiers changed from: private */
    public void f(int i2) {
        Intent intent = new Intent("com.xiaomi.smarthome.bluetooth.connect_status_changed");
        intent.putExtra("key_device_address", this.e.getAddress());
        intent.putExtra("key_connect_status", i2);
        BluetoothUtils.a(intent);
    }

    private void a(UUID uuid, UUID uuid2, byte[] bArr, int i2) {
        Intent intent = new Intent("com.xiaomi.smarthome.bluetooth.character_write");
        intent.putExtra("key_device_address", this.e.getAddress());
        intent.putExtra("key_service_uuid", uuid);
        intent.putExtra("key_character_uuid", uuid2);
        intent.putExtra("key_character_value", bArr);
        intent.putExtra("key_character_write_status", i2);
        BluetoothUtils.a(intent);
    }

    private void c(UUID uuid, UUID uuid2, byte[] bArr) {
        Intent intent = new Intent("com.xiaomi.smarthome.bluetooth.character_changed");
        intent.putExtra("key_device_address", this.e.getAddress());
        intent.putExtra("key_service_uuid", uuid);
        intent.putExtra("key_character_uuid", uuid2);
        intent.putExtra("key_character_value", bArr);
        BluetoothUtils.a(intent);
    }

    public boolean a() {
        c();
        if (c) {
            BluetoothLog.c(String.format("openGatt for %s", new Object[]{j()}));
        }
        if (this.d != null) {
            if (c) {
                BluetoothLog.b(String.format("Previous gatt not closed", new Object[0]));
            }
            return true;
        }
        Context n = BluetoothUtils.n();
        BluetoothGattResponse bluetoothGattResponse = new BluetoothGattResponse(this.k);
        this.e = BluetoothUtils.e().getRemoteDevice(j());
        if (Version.a()) {
            this.d = this.e.connectGatt(n, false, bluetoothGattResponse, 2);
        } else {
            this.d = this.e.connectGatt(n, false, bluetoothGattResponse);
        }
        if (this.d != null) {
            return true;
        }
        if (c) {
            BluetoothLog.b(String.format("openGatt failed: connectGatt return null!", new Object[0]));
        }
        return false;
    }

    private String j() {
        return this.e.getAddress();
    }

    public void b() {
        c();
        if (c) {
            BluetoothLog.c(String.format("closeGatt for %s", new Object[]{j()}));
        }
        if (this.d != null) {
            if (c) {
                BluetoothLog.c("Disconnecting...");
            }
            this.g.sendEmptyMessageDelayed(289, 2000);
            this.d.disconnect();
            return;
        }
        if (this.f != null) {
            this.f.a(false);
        }
        e(0);
        f(32);
    }

    private void k() {
        if (c) {
            BluetoothLog.c(String.format("releaseAfterDisconnected for %s", new Object[]{j()}));
        }
        this.g.removeMessages(289);
        if (this.d != null) {
            f();
            if (this.d != null) {
                if (c) {
                    BluetoothLog.c("Closing...");
                }
                this.d.close();
            }
            this.d = null;
            this.g.postDelayed(new Runnable() {
                public void run() {
                    if (BleConnectWorker.this.f != null) {
                        BleConnectWorker.this.f.a(false);
                    }
                    BleConnectWorker.this.e(0);
                    BleConnectWorker.this.f(32);
                }
            }, 600);
        } else if (c) {
            BluetoothLog.c(String.format("releaseAfterDisconnected for %s failed, mBluetoothGatt is null", new Object[]{j()}));
        }
    }

    /* access modifiers changed from: protected */
    public void b(int i2) {
        synchronized (this.m) {
            try {
                this.m.wait((long) i2);
            } catch (InterruptedException unused) {
                BluetoothLog.d("Sleeping interrupted");
            }
        }
    }

    public boolean d() {
        c();
        if (c) {
            BluetoothLog.c(String.format("discoverService for %s", new Object[]{j()}));
        }
        if (this.d == null) {
            if (c) {
                BluetoothLog.b(String.format("discoverService but gatt is null!", new Object[0]));
            }
            return false;
        } else if (this.d.discoverServices()) {
            return true;
        } else {
            if (c) {
                BluetoothLog.b(String.format("discoverServices failed", new Object[0]));
            }
            return false;
        }
    }

    public int e() {
        c();
        return this.h;
    }

    public void a(GattResponseListener gattResponseListener) {
        c();
        this.f = gattResponseListener;
    }

    public void b(GattResponseListener gattResponseListener) {
        c();
        if (this.f == gattResponseListener) {
            this.f = null;
        }
    }

    public boolean f() {
        if (c) {
            BluetoothLog.c(String.format("refreshDeviceCache for %s", new Object[]{j()}));
        }
        c();
        if (this.d == null) {
            if (c) {
                BluetoothLog.b(String.format("ble gatt null", new Object[0]));
            }
            return false;
        } else if (BluetoothUtils.a(this.d)) {
            return true;
        } else {
            if (c) {
                BluetoothLog.b(String.format("refreshDeviceCache failed", new Object[0]));
            }
            return false;
        }
    }

    public boolean a(UUID uuid, UUID uuid2) {
        if (c) {
            BluetoothLog.c(String.format("readCharacteristic for %s: service = 0x%s, character = 0x%s", new Object[]{this.e.getAddress(), uuid, uuid2}));
        }
        c();
        BluetoothGattCharacteristic b2 = b(uuid, uuid2);
        if (b2 == null) {
            if (c) {
                BluetoothLog.b(String.format("characteristic not exist!", new Object[0]));
            }
            return false;
        } else if (!a(b2)) {
            if (c) {
                BluetoothLog.b(String.format("characteristic not readable!", new Object[0]));
            }
            return false;
        } else if (this.d == null) {
            if (c) {
                BluetoothLog.b(String.format("ble gatt null", new Object[0]));
            }
            return false;
        } else if (this.d.readCharacteristic(b2)) {
            return true;
        } else {
            if (c) {
                BluetoothLog.b(String.format("readCharacteristic failed", new Object[0]));
            }
            return false;
        }
    }

    public boolean a(UUID uuid, UUID uuid2, byte[] bArr) {
        if (c) {
            BluetoothLog.c(String.format("writeCharacteristic for %s: service = 0x%s, character = 0x%s, value = 0x%s", new Object[]{this.e.getAddress(), uuid, uuid2, ByteUtils.d(bArr)}));
        }
        c();
        BluetoothGattCharacteristic b2 = b(uuid, uuid2);
        if (b2 == null) {
            if (c) {
                BluetoothLog.b(String.format("characteristic not exist!", new Object[0]));
            }
            return false;
        } else if (!b(b2)) {
            if (c) {
                BluetoothLog.b(String.format("characteristic not writable!", new Object[0]));
            }
            return false;
        } else if (this.d == null) {
            if (c) {
                BluetoothLog.b(String.format("ble gatt null", new Object[0]));
            }
            return false;
        } else {
            if (bArr == null) {
                bArr = ByteUtils.b;
            }
            b2.setValue(bArr);
            if (this.d.writeCharacteristic(b2)) {
                return true;
            }
            if (c) {
                BluetoothLog.b(String.format("writeCharacteristic failed", new Object[0]));
            }
            return false;
        }
    }

    public boolean b(UUID uuid, UUID uuid2, byte[] bArr) {
        if (c) {
            BluetoothLog.c(String.format("writeCharacteristicWithNoRsp for %s: service = 0x%s, character = 0x%s, value = 0x%s", new Object[]{this.e.getAddress(), uuid, uuid2, ByteUtils.d(bArr)}));
        }
        c();
        BluetoothGattCharacteristic b2 = b(uuid, uuid2);
        if (b2 == null) {
            if (c) {
                BluetoothLog.b(String.format("characteristic not exist!", new Object[0]));
            }
            return false;
        } else if (!c(b2)) {
            if (c) {
                BluetoothLog.b(String.format("characteristic not norsp writable!", new Object[0]));
            }
            return false;
        } else if (this.d == null) {
            if (c) {
                BluetoothLog.b(String.format("ble gatt null", new Object[0]));
            }
            return false;
        } else {
            if (bArr == null) {
                bArr = ByteUtils.b;
            }
            b2.setValue(bArr);
            b2.setWriteType(1);
            if (this.d.writeCharacteristic(b2)) {
                return true;
            }
            if (c) {
                BluetoothLog.b(String.format("writeCharacteristic failed", new Object[0]));
            }
            return false;
        }
    }

    public boolean a(UUID uuid, UUID uuid2, boolean z) {
        c();
        if (c) {
            BluetoothLog.c(String.format("setCharacteristicNotification for %s, service = %s, character = %s, enable = %b", new Object[]{j(), uuid, uuid2, Boolean.valueOf(z)}));
        }
        BluetoothGattCharacteristic b2 = b(uuid, uuid2);
        if (b2 == null) {
            if (c) {
                BluetoothLog.b(String.format("characteristic not exist!", new Object[0]));
            }
            return false;
        } else if (!d(b2)) {
            if (c) {
                BluetoothLog.b(String.format("characteristic not notifyable!", new Object[0]));
            }
            return false;
        } else if (this.d == null) {
            if (c) {
                BluetoothLog.b(String.format("ble gatt null", new Object[0]));
            }
            return false;
        } else if (!this.d.setCharacteristicNotification(b2, z)) {
            if (c) {
                BluetoothLog.b(String.format("setCharacteristicNotification failed", new Object[0]));
            }
            return false;
        } else {
            BluetoothGattDescriptor descriptor = b2.getDescriptor(BluetoothConstants.A);
            if (descriptor == null) {
                if (c) {
                    BluetoothLog.b(String.format("getDescriptor for notify null!", new Object[0]));
                }
                return false;
            }
            if (!descriptor.setValue(z ? BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE : BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE)) {
                if (c) {
                    BluetoothLog.b(String.format("setValue for notify descriptor failed!", new Object[0]));
                }
                return false;
            } else if (this.d.writeDescriptor(descriptor)) {
                return true;
            } else {
                if (c) {
                    BluetoothLog.b(String.format("writeDescriptor for notify failed", new Object[0]));
                }
                return false;
            }
        }
    }

    public boolean b(UUID uuid, UUID uuid2, boolean z) {
        c();
        if (c) {
            BluetoothLog.c(String.format("setCharacteristicIndication for %s, service = %s, character = %s, enable = %b", new Object[]{j(), uuid, uuid2, Boolean.valueOf(z)}));
        }
        BluetoothGattCharacteristic b2 = b(uuid, uuid2);
        if (b2 == null) {
            if (c) {
                BluetoothLog.b(String.format("characteristic not exist!", new Object[0]));
            }
            return false;
        } else if (!e(b2)) {
            if (c) {
                BluetoothLog.b(String.format("characteristic not indicatable!", new Object[0]));
            }
            return false;
        } else if (this.d == null) {
            if (c) {
                BluetoothLog.b(String.format("ble gatt null", new Object[0]));
            }
            return false;
        } else if (!this.d.setCharacteristicNotification(b2, z)) {
            if (c) {
                BluetoothLog.b(String.format("setCharacteristicIndication failed", new Object[0]));
            }
            return false;
        } else {
            BluetoothGattDescriptor descriptor = b2.getDescriptor(BluetoothConstants.A);
            if (descriptor == null) {
                if (c) {
                    BluetoothLog.b(String.format("getDescriptor for indicate null!", new Object[0]));
                }
                return false;
            }
            if (!descriptor.setValue(z ? BluetoothGattDescriptor.ENABLE_INDICATION_VALUE : BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE)) {
                if (c) {
                    BluetoothLog.b(String.format("setValue for indicate descriptor failed!", new Object[0]));
                }
                return false;
            } else if (this.d.writeDescriptor(descriptor)) {
                return true;
            } else {
                if (c) {
                    BluetoothLog.b(String.format("writeDescriptor for indicate failed", new Object[0]));
                }
                return false;
            }
        }
    }

    public boolean g() {
        c();
        if (c) {
            BluetoothLog.c(String.format("readRemoteRssi for %s", new Object[]{j()}));
        }
        if (this.d == null) {
            if (c) {
                BluetoothLog.b(String.format("ble gatt null", new Object[0]));
            }
            return false;
        } else if (this.d.readRemoteRssi()) {
            return true;
        } else {
            if (c) {
                BluetoothLog.b(String.format("readRemoteRssi failed", new Object[0]));
            }
            return false;
        }
    }

    public boolean c(int i2) {
        if (c) {
            BluetoothLog.c(String.format("requestConnectionPriority for %s: connectionPriority = %d", new Object[]{j(), Integer.valueOf(i2)}));
        }
        if (this.d == null) {
            if (c) {
                BluetoothLog.b(String.format("ble gatt null", new Object[0]));
            }
            return false;
        } else if (i2 < 0 || i2 > 2) {
            if (c) {
                BluetoothLog.b(String.format("connectionPriority not within valid range", new Object[0]));
            }
            return false;
        } else if (Build.VERSION.SDK_INT < 21) {
            if (c) {
                BluetoothLog.b(String.format("requestConnectionPriority only support from android 5.0", new Object[0]));
            }
            return false;
        } else if (this.d.requestConnectionPriority(i2)) {
            return true;
        } else {
            if (c) {
                BluetoothLog.b(String.format("requestConnectionPriority failed", new Object[0]));
            }
            return false;
        }
    }

    public boolean d(int i2) {
        c();
        if (c) {
            BluetoothLog.c(String.format("requestMtu for %s, mtu = %d", new Object[]{j(), Integer.valueOf(i2)}));
        }
        if (this.d == null) {
            if (c) {
                BluetoothLog.b(String.format("ble gatt null", new Object[0]));
            }
            return false;
        } else if (Build.VERSION.SDK_INT < 21) {
            if (c) {
                BluetoothLog.b(String.format("requestMtu only support from android 5.0", new Object[0]));
            }
            return false;
        } else if (this.d.requestMtu(i2)) {
            return true;
        } else {
            if (c) {
                BluetoothLog.b(String.format("requestMtu failed", new Object[0]));
            }
            return false;
        }
    }

    public void a(UUID uuid, UUID uuid2, BleResponse<Void> bleResponse) {
        if (bleResponse != null) {
            if (this.d == null || this.j == null) {
                bleResponse.a(-8, null);
            } else if (b(uuid, uuid2) != null) {
                bleResponse.a(0, null);
            } else {
                bleResponse.a(-1, null);
            }
        }
    }

    private boolean a(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return (bluetoothGattCharacteristic == null || (bluetoothGattCharacteristic.getProperties() & 2) == 0) ? false : true;
    }

    private boolean c(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return (bluetoothGattCharacteristic == null || (bluetoothGattCharacteristic.getProperties() & 4) == 0) ? false : true;
    }

    private boolean d(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return (bluetoothGattCharacteristic == null || (bluetoothGattCharacteristic.getProperties() & 16) == 0) ? false : true;
    }

    private boolean e(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return (bluetoothGattCharacteristic == null || (bluetoothGattCharacteristic.getProperties() & 32) == 0) ? false : true;
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 288:
                ProxyBulk.a(message.obj);
                return true;
            case 289:
                BluetoothLog.d("disconnect timeout");
                k();
                return true;
            default:
                return true;
        }
    }

    public boolean a(Object obj, Method method, Object[] objArr) {
        this.g.obtainMessage(288, new ProxyBulk(obj, method, objArr)).sendToTarget();
        return true;
    }

    public void c() {
        this.l.c();
    }

    public BleGattProfile h() {
        return this.i;
    }
}
