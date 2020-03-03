package com.xiaomi.smarthome.core.server.internal.bluetooth.security;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.xiaomi.smarthome.core.server.internal.bluetooth.BluetoothApi;
import com.xiaomi.smarthome.frame.log.BluetoothMyLogger;
import com.xiaomi.smarthome.library.bluetooth.BleConnectOptions;
import com.xiaomi.smarthome.library.bluetooth.BluetoothConstants;
import com.xiaomi.smarthome.library.bluetooth.channel.Channel;
import com.xiaomi.smarthome.library.bluetooth.connect.BleConnectManager;
import com.xiaomi.smarthome.library.bluetooth.connect.Code;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleConnectResponse;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleNotifyResponse;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothUtils;
import java.util.UUID;

public abstract class BleSecurityConnector implements ISecurityConnect {

    /* renamed from: a  reason: collision with root package name */
    protected IBleDeviceLauncher f14295a;
    protected BleConnectResponse b;
    protected Bundle c;
    protected final Handler d = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            BleSecurityConnector.this.a(message);
        }
    };
    private boolean e;
    private ConnectReceiver f;
    private final BleConnectResponse g = new BleConnectResponse() {
        public void a(int i, Bundle bundle) {
            BleSecurityConnector.this.f14295a.a(i, bundle);
            if (i == 0) {
                if (bundle != null) {
                    BleSecurityConnector.this.c.putAll(bundle);
                }
                BleSecurityConnector.this.a(bundle);
                BleSecurityConnector.this.k();
                BleSecurityConnector.this.j();
                return;
            }
            BleSecurityConnector.this.a(i, true);
        }
    };

    /* access modifiers changed from: protected */
    public void a(Bundle bundle) {
    }

    /* access modifiers changed from: protected */
    public abstract void a(Message message);

    /* access modifiers changed from: protected */
    public abstract void a(UUID uuid, UUID uuid2, byte[] bArr);

    /* access modifiers changed from: protected */
    public void c() {
    }

    /* access modifiers changed from: protected */
    public void h() {
    }

    /* access modifiers changed from: protected */
    public byte[] i() {
        return null;
    }

    /* access modifiers changed from: protected */
    public abstract void j();

    protected BleSecurityConnector(IBleDeviceLauncher iBleDeviceLauncher) {
        this.f14295a = iBleDeviceLauncher;
        if (iBleDeviceLauncher != null) {
            this.c = new Bundle();
            return;
        }
        throw new NullPointerException("launcher should not be null");
    }

    public void a(BleConnectOptions bleConnectOptions, BleConnectResponse bleConnectResponse) {
        this.b = bleConnectResponse;
        BluetoothApi.a(e(), bleConnectOptions, this.g);
    }

    public void a() {
        BleConnectManager.a().a(this.f14295a.a());
    }

    /* access modifiers changed from: protected */
    public void b() {
        this.e = true;
    }

    /* access modifiers changed from: protected */
    public boolean d() {
        return this.e;
    }

    /* access modifiers changed from: protected */
    public String e() {
        return this.f14295a.a();
    }

    /* access modifiers changed from: protected */
    public int f() {
        return this.f14295a.b();
    }

    /* access modifiers changed from: protected */
    public byte[] g() {
        return this.f14295a.c();
    }

    /* access modifiers changed from: protected */
    public void a(final BleNotifyResponse bleNotifyResponse) {
        BleConnectManager.a().a(e(), BluetoothConstants.i, BluetoothConstants.M, (BleNotifyResponse) new BleNotifyResponse() {
            public void a(int i, Void voidR) {
                bleNotifyResponse.a(i, null);
            }
        });
    }

    /* access modifiers changed from: private */
    public void k() {
        if (this.f == null) {
            this.f = new ConnectReceiver();
            IntentFilter intentFilter = new IntentFilter("com.xiaomi.smarthome.bluetooth.character_changed");
            intentFilter.addAction("com.xiaomi.smarthome.bluetooth.connect_status_changed");
            intentFilter.addAction(Channel.b);
            BluetoothUtils.a((BroadcastReceiver) this.f, intentFilter);
        }
    }

    private void l() {
        if (this.f != null) {
            BluetoothUtils.a((BroadcastReceiver) this.f);
            this.f = null;
        }
    }

    /* access modifiers changed from: protected */
    public void a(int i) {
        BluetoothMyLogger.d(String.format("%s.dispatchResult code = %s", new Object[]{getClass().getSimpleName(), Code.a(i)}));
        a(i, false);
    }

    /* access modifiers changed from: private */
    public void a(final int i, final boolean z) {
        this.d.post(new Runnable() {
            public void run() {
                BleSecurityConnector.this.b(i, z);
            }
        });
    }

    /* access modifiers changed from: private */
    public void b(int i, boolean z) {
        if (d()) {
            i = -2;
        }
        boolean z2 = i == 0;
        l();
        c();
        if (this.b != null) {
            if (z2) {
                this.c.putByteArray("key_token", i());
            }
            this.c.putBoolean(BluetoothConstants.ad, z);
            this.b.a(i, this.c);
        }
        this.d.removeCallbacksAndMessages((Object) null);
        this.b = null;
    }

    private class ConnectReceiver extends BroadcastReceiver {
        private ConnectReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                String action = intent.getAction();
                if ("com.xiaomi.smarthome.bluetooth.character_changed".equalsIgnoreCase(action)) {
                    if (BleSecurityConnector.this.e().equalsIgnoreCase(intent.getStringExtra("key_device_address"))) {
                        UUID uuid = (UUID) intent.getSerializableExtra("key_service_uuid");
                        UUID uuid2 = (UUID) intent.getSerializableExtra("key_character_uuid");
                        byte[] byteArrayExtra = intent.getByteArrayExtra("key_character_value");
                        if (uuid != null && uuid2 != null) {
                            BleSecurityConnector.this.a(uuid, uuid2, byteArrayExtra);
                        }
                    }
                } else if ("com.xiaomi.smarthome.bluetooth.connect_status_changed".equalsIgnoreCase(action)) {
                    if (BleSecurityConnector.this.e().equalsIgnoreCase(intent.getStringExtra("key_device_address")) && intent.getIntExtra("key_connect_status", 5) == 32) {
                        BleSecurityConnector.this.a(-32);
                    }
                } else if (Channel.b.equals(action)) {
                    BleSecurityConnector.this.h();
                }
            }
        }
    }
}
