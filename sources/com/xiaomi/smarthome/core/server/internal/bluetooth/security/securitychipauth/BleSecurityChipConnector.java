package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth;

import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import com.xiaomi.smarthome.core.server.bluetooth.IBleChannelReader;
import com.xiaomi.smarthome.core.server.bluetooth.IBleChannelWriter;
import com.xiaomi.smarthome.core.server.bluetooth.IBleResponse;
import com.xiaomi.smarthome.core.server.internal.bluetooth.channel.ChannelManager;
import com.xiaomi.smarthome.core.server.internal.bluetooth.channel.SecureAuthChannelManager;
import com.xiaomi.smarthome.core.server.internal.bluetooth.model.BluetoothCache;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.BleSecurityConnector;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.DeviceApi;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.IBleDeviceLauncher;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.library.bluetooth.BluetoothConstants;
import com.xiaomi.smarthome.library.bluetooth.connect.BleConnectManager;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleNotifyResponse;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleReadResponse;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleResponse;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.common.util.ByteUtils;
import java.util.UUID;
import util.VersionUtils;

public abstract class BleSecurityChipConnector extends BleSecurityConnector {
    private static final int f = 569;
    private IBleChannelWriter e = SecureAuthChannelManager.e().b(e(), this.h);
    /* access modifiers changed from: private */
    public Handler g = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            if (message.what == BleSecurityChipConnector.f && BleSecurityChipConnector.this.i != null) {
                BleSecurityChipConnector.this.i.a(0, null);
                BleNotifyResponse unused = BleSecurityChipConnector.this.i = null;
            }
        }
    };
    private IBleChannelReader h = new IBleChannelReader() {
        public IBinder asBinder() {
            return null;
        }

        public void onRead(String str, byte[] bArr, int i) throws RemoteException {
            BleSecurityChipConnector.this.a(bArr, i);
        }
    };
    /* access modifiers changed from: private */
    public BleNotifyResponse i = null;

    /* access modifiers changed from: protected */
    public void a(Message message) {
    }

    /* access modifiers changed from: protected */
    public abstract void a(byte[] bArr, int i2);

    /* access modifiers changed from: protected */
    public boolean k() {
        return false;
    }

    protected BleSecurityChipConnector(IBleDeviceLauncher iBleDeviceLauncher) {
        super(iBleDeviceLauncher);
    }

    private void l() {
        SecureAuthChannelManager.e().a(e(), this.h);
    }

    /* access modifiers changed from: package-private */
    public boolean a(byte[] bArr, int i2, IBleResponse iBleResponse) {
        try {
            this.e.write(bArr, i2, iBleResponse);
            return true;
        } catch (RemoteException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    /* access modifiers changed from: private */
    public void m() {
        a(e(), (BleResponse<String>) new BleResponse<String>() {
            public void a(int i, String str) {
                boolean a2 = VersionUtils.a(BleSecurityChipConnector.this.k() ? 5 : 1, str);
                BluetoothLog.a("before sendA4, check version , isMeshDevice %s, firmware version %s, need send A4 result is %s", String.valueOf(BleSecurityChipConnector.this.k()), str, String.valueOf(a2));
                if (a2) {
                    BluetoothLog.d("connector start send A4");
                    BleSecurityChipConnector.this.g.sendEmptyMessageDelayed(BleSecurityChipConnector.f, 5000);
                    SecureAuthChannelManager.e().c(BleSecurityChipConnector.this.e());
                    return;
                }
                BluetoothLog.d("at send A4 step, check no need to send A4");
                BleSecurityChipConnector.this.h();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void h() {
        BluetoothLog.d("receive A4 result");
        this.g.removeMessages(f);
        if (this.i != null) {
            this.i.a(0, null);
            this.i = null;
        }
    }

    /* access modifiers changed from: protected */
    public void c() {
        l();
    }

    /* access modifiers changed from: protected */
    public void a(UUID uuid, UUID uuid2, byte[] bArr) {
        ChannelManager.BleChannel f2;
        if (BluetoothConstants.i.equals(uuid) && BluetoothConstants.S.equals(uuid2) && (f2 = SecureAuthChannelManager.e().f(e())) != null) {
            f2.a(bArr);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(String str) {
        Bundle bundle = new Bundle();
        bundle.putString(BluetoothConstants.D, str);
        this.f14295a.b(-33, bundle);
    }

    /* access modifiers changed from: package-private */
    public void b(final BleNotifyResponse bleNotifyResponse) {
        BleConnectManager.a().a(e(), BluetoothConstants.i, BluetoothConstants.S, (BleNotifyResponse) new BleNotifyResponse() {
            public void a(int i, Void voidR) {
                if (i == 0) {
                    BleNotifyResponse unused = BleSecurityChipConnector.this.i = bleNotifyResponse;
                    BleSecurityChipConnector.this.m();
                    return;
                }
                bleNotifyResponse.a(i, null);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void a(String str, final BleResponse<String> bleResponse) {
        BleConnectManager.a().a(str, BluetoothConstants.i, BluetoothConstants.J, (BleReadResponse) new BleReadResponse() {
            public void a(int i, byte[] bArr) {
                if (i != 0 || ByteUtils.e(bArr)) {
                    bleResponse.a(-1, "");
                    return;
                }
                int i2 = 0;
                int i3 = 0;
                while (i2 < bArr.length && bArr[i2] != 0) {
                    i3++;
                    i2++;
                }
                if (i3 == 0) {
                    bleResponse.a(-1, "");
                    return;
                }
                byte[] bArr2 = new byte[i3];
                for (int i4 = 0; i4 < i3; i4++) {
                    bArr2[i4] = bArr[i4];
                }
                BluetoothCache.p(BleSecurityChipConnector.this.e(), new String(bArr2));
                bleResponse.a(0, new String(bArr2));
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void a(String str, final String str2, final AsyncCallback<Void, Error> asyncCallback) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            a(str, (BleResponse<String>) new BleResponse<String>() {
                public void a(int i, String str) {
                    if (i == 0 && !TextUtils.isEmpty(str)) {
                        DeviceApi.c(str2, str, new AsyncCallback<Void, Error>() {
                            /* renamed from: a */
                            public void onSuccess(Void voidR) {
                                if (asyncCallback != null) {
                                    asyncCallback.onSuccess(null);
                                }
                            }

                            public void onFailure(Error error) {
                                if (asyncCallback != null) {
                                    asyncCallback.onFailure(error);
                                }
                            }
                        });
                    } else if (asyncCallback != null) {
                        asyncCallback.onFailure(new Error(-1, "read version failed"));
                    }
                }
            });
        } else if (asyncCallback != null) {
            asyncCallback.onFailure(new Error(-1, "mac or did is null"));
        }
    }
}
