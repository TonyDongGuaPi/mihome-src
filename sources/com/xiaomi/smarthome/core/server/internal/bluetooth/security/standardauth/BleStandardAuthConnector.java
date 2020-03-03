package com.xiaomi.smarthome.core.server.internal.bluetooth.security.standardauth;

import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import com.xiaomi.smarthome.core.server.bluetooth.IBleChannelReader;
import com.xiaomi.smarthome.core.server.bluetooth.IBleChannelWriter;
import com.xiaomi.smarthome.core.server.bluetooth.IBleResponse;
import com.xiaomi.smarthome.core.server.internal.bluetooth.channel.StandardAuthChannelManager;
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
import com.xiaomi.smarthome.library.common.util.ByteUtils;
import java.util.UUID;

public abstract class BleStandardAuthConnector extends BleSecurityConnector {
    private IBleChannelWriter e = StandardAuthChannelManager.e().b(e(), this.f);
    private IBleChannelReader f = new IBleChannelReader() {
        public IBinder asBinder() {
            return null;
        }

        public void onRead(String str, byte[] bArr, int i) throws RemoteException {
            BleStandardAuthConnector.this.a(bArr, i);
        }
    };

    /* access modifiers changed from: protected */
    public void a(Message message) {
    }

    /* access modifiers changed from: protected */
    public void a(UUID uuid, UUID uuid2, byte[] bArr) {
    }

    /* access modifiers changed from: protected */
    public abstract void a(byte[] bArr, int i);

    protected BleStandardAuthConnector(IBleDeviceLauncher iBleDeviceLauncher) {
        super(iBleDeviceLauncher);
    }

    private void k() {
        StandardAuthChannelManager.e().a(e(), this.f);
    }

    /* access modifiers changed from: package-private */
    public boolean a(byte[] bArr, int i, IBleResponse iBleResponse) {
        try {
            this.e.write(bArr, i, iBleResponse);
            return true;
        } catch (RemoteException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public void c() {
        k();
    }

    /* access modifiers changed from: package-private */
    public void b(final BleNotifyResponse bleNotifyResponse) {
        BleConnectManager.a().a(e(), BluetoothConstants.i, BluetoothConstants.V, (BleNotifyResponse) new BleNotifyResponse() {
            public void a(int i, Void voidR) {
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
                        BluetoothCache.p(BleStandardAuthConnector.this.e(), str);
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
