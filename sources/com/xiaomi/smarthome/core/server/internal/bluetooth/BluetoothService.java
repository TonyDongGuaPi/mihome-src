package com.xiaomi.smarthome.core.server.internal.bluetooth;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.core.entity.bluetooth.SearchRequest;
import com.xiaomi.smarthome.core.entity.device.BtDevice;
import com.xiaomi.smarthome.core.server.CoreManager;
import com.xiaomi.smarthome.core.server.CoreService;
import com.xiaomi.smarthome.core.server.bluetooth.IBleMeshUpgradeResponse;
import com.xiaomi.smarthome.core.server.bluetooth.IBleResponse;
import com.xiaomi.smarthome.core.server.bluetooth.SearchResponse;
import com.xiaomi.smarthome.core.server.internal.bluetooth.channel.IPCChannelManager;
import com.xiaomi.smarthome.core.server.internal.bluetooth.channel.StandardAuthChannelManager;
import com.xiaomi.smarthome.core.server.internal.bluetooth.mesh.MeshDfuManager;
import com.xiaomi.smarthome.core.server.internal.bluetooth.model.BluetoothCache;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.DeviceApi;
import com.xiaomi.smarthome.core.server.internal.device.BluetoothDeviceSearch;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.HostSetting;
import com.xiaomi.smarthome.library.bluetooth.BleConnectOptions;
import com.xiaomi.smarthome.library.bluetooth.BluetoothContextManager;
import com.xiaomi.smarthome.library.bluetooth.connect.BleGattProfile;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import java.util.UUID;

public class BluetoothService implements IBluetoothService {
    private static volatile IBluetoothService ai;
    /* access modifiers changed from: private */
    public static Context aj;
    private final BroadcastReceiver ak = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                String action = intent.getAction();
                String stringExtra = intent.getStringExtra(CoreManager.b);
                BluetoothLog.c(String.format("BluetoothService onReceive %s, processName = %s", new Object[]{action, stringExtra}));
                if (CoreManager.f14039a.equals(action) && TextUtils.equals(stringExtra, context.getPackageName())) {
                    BluetoothLog.c(String.format("App process died", new Object[0]));
                    BluetoothService.this.b();
                }
            }
        }
    };

    private BluetoothService() {
        aj = CoreService.getAppContext();
        BluetoothContextManager.a(aj, HostSetting.h);
        CommonApplication.getThreadExecutor().submit(new Runnable() {
            public void run() {
                BluetoothLogger.a(BluetoothService.aj);
            }
        });
        BluetoothCache.a();
        MiuiSDKHelper.a();
        IPCChannelManager.e().f();
        StandardAuthChannelManager.e().f();
        d();
    }

    private void d() {
        LocalBroadcastManager.getInstance(aj).registerReceiver(this.ak, new IntentFilter(CoreManager.f14039a));
    }

    public static IBluetoothService a() {
        if (ai == null) {
            synchronized (BluetoothService.class) {
                if (ai == null) {
                    ai = new BluetoothService();
                }
            }
        }
        return ai;
    }

    public void a(SearchRequest searchRequest, SearchResponse searchResponse) {
        BluetoothApi.a(searchRequest, searchResponse);
    }

    public void b() {
        BluetoothDeviceSearch.a().c();
    }

    public void a(String str, int i, Bundle bundle, IBleResponse iBleResponse) {
        switch (i) {
            case 1:
                BluetoothApi.a(str, bundle != null ? (BleConnectOptions) bundle.getParcelable(BtConstants.d) : null, iBleResponse);
                return;
            case 2:
                long j = 0;
                if (bundle != null) {
                    j = bundle.getLong(IBluetoothService.ab, 0);
                }
                BluetoothApi.a(str, j);
                return;
            case 3:
                BluetoothApi.a(str, (UUID) bundle.getSerializable(IBluetoothService.N), (UUID) bundle.getSerializable(IBluetoothService.O), iBleResponse);
                return;
            case 4:
                BluetoothApi.a(str, (UUID) bundle.getSerializable(IBluetoothService.N), (UUID) bundle.getSerializable(IBluetoothService.O), bundle.getByteArray(IBluetoothService.P), iBleResponse);
                return;
            case 5:
                BluetoothApi.b(str, (UUID) bundle.getSerializable(IBluetoothService.N), (UUID) bundle.getSerializable(IBluetoothService.O), bundle.getByteArray(IBluetoothService.P), iBleResponse);
                return;
            case 6:
                BluetoothApi.b(str, (UUID) bundle.getSerializable(IBluetoothService.N), (UUID) bundle.getSerializable(IBluetoothService.O), iBleResponse);
                return;
            case 7:
                BluetoothApi.a(str, (UUID) bundle.getSerializable(IBluetoothService.N), (UUID) bundle.getSerializable(IBluetoothService.O));
                return;
            default:
                switch (i) {
                    case 10:
                        BluetoothApi.c(str, iBleResponse);
                        return;
                    case 11:
                        BluetoothApi.c(str, bundle.getByteArray(IBluetoothService.P), iBleResponse);
                        return;
                    case 12:
                        BluetoothApi.b(str, iBleResponse);
                        return;
                    case 13:
                        BluetoothApi.a(str);
                        return;
                    case 14:
                        BluetoothApi.b(str, bundle.getInt(IBluetoothService.S), iBleResponse);
                        return;
                    case 15:
                        BluetoothApi.c(str, (UUID) bundle.getSerializable(IBluetoothService.N), (UUID) bundle.getSerializable(IBluetoothService.O), iBleResponse);
                        return;
                    case 16:
                        BluetoothApi.b(str, (UUID) bundle.getSerializable(IBluetoothService.N), (UUID) bundle.getSerializable(IBluetoothService.O));
                        return;
                    default:
                        switch (i) {
                            case 30:
                                BluetoothApi.a();
                                return;
                            case 31:
                                BluetoothApi.e(str);
                                return;
                            case 32:
                                BluetoothApi.e();
                                return;
                            case 33:
                                BluetoothApi.c(str);
                                return;
                            case 34:
                                BluetoothApi.d(str);
                                return;
                            case 35:
                                BluetoothApi.a(str, bundle.getLong(IBluetoothService.X), iBleResponse);
                                return;
                            case 36:
                                BluetoothApi.a(bundle.getString(IBluetoothService.T, ""), bundle.getString(IBluetoothService.V, ""));
                                return;
                            case 37:
                                BluetoothApi.f(bundle.getString(IBluetoothService.Z, ""), iBleResponse);
                                return;
                            default:
                                switch (i) {
                                    case 40:
                                        BluetoothApi.b(str);
                                        return;
                                    case 41:
                                        BluetoothApi.d(str, (UUID) bundle.getSerializable(IBluetoothService.N), (UUID) bundle.getSerializable(IBluetoothService.O), iBleResponse);
                                        return;
                                    case 42:
                                        BluetoothApi.c(str, (UUID) bundle.getSerializable(IBluetoothService.N), (UUID) bundle.getSerializable(IBluetoothService.O));
                                        return;
                                    case 43:
                                        BluetoothApi.a(str, bundle.getByteArray(IBluetoothService.P), iBleResponse);
                                        return;
                                    case 44:
                                        BluetoothApi.b(str, bundle.getByteArray(IBluetoothService.P), iBleResponse);
                                        return;
                                    case 45:
                                        BluetoothApi.a(str, bundle.getInt(IBluetoothService.V), iBleResponse);
                                        return;
                                    case 46:
                                        BluetoothApi.a(str, iBleResponse);
                                        return;
                                    case 47:
                                        BluetoothApi.a(str, bundle.getInt(IBluetoothService.ae), bundle.getInt(IBluetoothService.af), bundle.getLong(IBluetoothService.ag, -1), iBleResponse);
                                        return;
                                    case 48:
                                        BluetoothApi.d(str, iBleResponse);
                                        return;
                                    case 49:
                                        BluetoothApi.d(str, bundle.getByteArray(IBluetoothService.P), iBleResponse);
                                        return;
                                    case 50:
                                        BluetoothApi.e(str, bundle.getByteArray(IBluetoothService.P), iBleResponse);
                                        return;
                                    case 51:
                                        BluetoothApi.f(str, bundle.getByteArray(IBluetoothService.P), iBleResponse);
                                        return;
                                    case 52:
                                        BluetoothApi.g(str, bundle.getByteArray(IBluetoothService.P), iBleResponse);
                                        return;
                                    case 53:
                                        BluetoothApi.e(str, iBleResponse);
                                        return;
                                    case 54:
                                        BluetoothApi.e(str, (UUID) bundle.getSerializable(IBluetoothService.N), (UUID) bundle.getSerializable(IBluetoothService.O), iBleResponse);
                                        return;
                                    default:
                                        BluetoothLog.f(String.format("callBluetoothApi unknown code %d", new Object[]{Integer.valueOf(i)}));
                                        return;
                                }
                        }
                }
        }
    }

    public void a(String str, int i, Bundle bundle) {
        switch (i) {
            case 1:
                bundle.putString("extra.result", BluetoothCache.a(str));
                return;
            case 2:
                bundle.putString("extra.result", BluetoothCache.f(str));
                return;
            case 3:
                bundle.putString("extra.result", BluetoothCache.i(str));
                return;
            case 4:
                bundle.putString("extra.result", BluetoothCache.j(str));
                return;
            case 5:
                bundle.putInt("extra.result", BluetoothCache.n(str));
                return;
            case 6:
                bundle.putString("extra.result", BluetoothCache.o(str));
                return;
            case 7:
                bundle.putString("extra.result", BluetoothCache.o(str, bundle.getString(IBluetoothService.T)));
                return;
            case 8:
                bundle.putByteArray("extra.result", BluetoothCache.l(str));
                return;
            case 9:
                bundle.putParcelable("extra.result", BluetoothCache.z(str));
                return;
            case 10:
                bundle.putString("extra.result", BluetoothCache.d(str));
                return;
            case 11:
                bundle.putInt("extra.result", BluetoothCache.g(str));
                return;
            case 12:
                bundle.putInt("extra.result", BluetoothCache.m(str));
                return;
            case 13:
                bundle.putString("extra.result", BluetoothCache.c(str));
                return;
            case 14:
                bundle.putInt("extra.result", BluetoothCache.h(str));
                return;
            case 15:
                bundle.putInt("extra.result", BluetoothCache.e(str));
                return;
            case 16:
                bundle.putString("extra.result", BluetoothCache.b(str));
                return;
            case 17:
                bundle.putString("extra.result", BluetoothCache.k(str));
                return;
            case 18:
                bundle.putString("extra.result", BluetoothCache.v(str));
                return;
            case 19:
                bundle.putString("extra.result", BluetoothCache.q(str));
                return;
            case 20:
                bundle.putString("extra.result", BluetoothCache.x(str));
                return;
            case 21:
                bundle.putString("extra.result", BluetoothCache.s(str));
                return;
            case 22:
                bundle.putInt("extra.result", BluetoothCache.t(str));
                return;
            case 23:
                bundle.putBoolean("extra.result", BluetoothCache.u(str));
                return;
            case 24:
                bundle.putString("extra.result", BluetoothCache.y(str));
                return;
            default:
                switch (i) {
                    case 100:
                        bundle.putParcelableArrayList(IBluetoothService.ac, BluetoothApi.b());
                        return;
                    case 101:
                        bundle.putParcelableArrayList(IBluetoothService.ac, BluetoothApi.c());
                        return;
                    case 102:
                        bundle.putParcelableArrayList(IBluetoothService.ac, BluetoothApi.d());
                        return;
                    default:
                        BluetoothLog.f(String.format("getBluetoothCache unknown code %d", new Object[]{Integer.valueOf(i)}));
                        return;
                }
        }
    }

    public void b(String str, int i, Bundle bundle) {
        switch (i) {
            case 1:
                BluetoothCache.a(str, bundle.getString(IBluetoothService.V, ""));
                return;
            case 2:
                BluetoothCache.e(str, bundle.getString(IBluetoothService.V, ""));
                return;
            case 3:
                BluetoothCache.f(str, bundle.getString(IBluetoothService.V, ""));
                return;
            case 4:
                BluetoothCache.g(str, bundle.getString(IBluetoothService.V, ""));
                return;
            case 5:
                BluetoothCache.d(str, bundle.getInt(IBluetoothService.V, 0));
                return;
            case 6:
                BluetoothCache.i(str, bundle.getString(IBluetoothService.V, ""));
                return;
            case 7:
                String string = bundle.getString(IBluetoothService.T);
                if (!TextUtils.isEmpty(string)) {
                    BluetoothCache.a(str, string, bundle.getString(IBluetoothService.V, ""));
                    return;
                }
                return;
            case 8:
                BluetoothCache.a(str, bundle.getByteArray(IBluetoothService.V));
                return;
            case 9:
                BluetoothCache.a(str, (BleGattProfile) bundle.getParcelable(IBluetoothService.V));
                return;
            case 10:
                BluetoothCache.d(str, bundle.getString(IBluetoothService.V, ""));
                return;
            case 12:
                BluetoothCache.e(str, bundle.getInt(IBluetoothService.V, 0));
                return;
            case 13:
                BluetoothCache.c(str, bundle.getString(IBluetoothService.V, ""));
                return;
            case 14:
                BluetoothCache.c(str, bundle.getInt(IBluetoothService.V, -60));
                return;
            case 15:
                BluetoothCache.a(str, bundle.getInt(IBluetoothService.V, 0));
                return;
            case 16:
                BluetoothCache.b(str, bundle.getString(IBluetoothService.V, ""));
                return;
            case 17:
                throw new UnsupportedOperationException();
            case 18:
                BluetoothCache.l(str, bundle.getString(IBluetoothService.V, ""));
                return;
            case 19:
                BluetoothCache.j(str, bundle.getString(IBluetoothService.V, ""));
                return;
            case 20:
                BluetoothCache.m(str, bundle.getString(IBluetoothService.V, ""));
                return;
            case 21:
                BluetoothCache.k(str, bundle.getString(IBluetoothService.V, ""));
                return;
            case 22:
                BluetoothCache.f(str, bundle.getInt(IBluetoothService.V, 0));
                return;
            case 23:
                BluetoothCache.a(str, bundle.getBoolean(IBluetoothService.V, true));
                return;
            default:
                BluetoothLog.f(String.format("setBluetoothCache unknown code %d", new Object[]{Integer.valueOf(i)}));
                return;
        }
    }

    public void a(SearchRequest searchRequest, final IBleResponse iBleResponse) {
        BluetoothDeviceSearch.a().a(searchRequest, (LocalSearchResponse) new LocalSearchResponse() {
            public void a() {
                try {
                    iBleResponse.onResponse(BtConstants.t, (Bundle) null);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            public void a(BtDevice btDevice) {
                try {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("extra.device", btDevice);
                    iBleResponse.onResponse(BtConstants.w, bundle);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            public void b() {
                try {
                    iBleResponse.onResponse(BtConstants.v, (Bundle) null);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            public void c() {
                try {
                    iBleResponse.onResponse(BtConstants.u, (Bundle) null);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void a(String str, final String str2, final String str3, String str4, final IBleMeshUpgradeResponse iBleMeshUpgradeResponse) {
        MeshDfuManager.a().a(str3, str, str4, (IBleMeshUpgradeResponse) new IBleMeshUpgradeResponse() {
            public IBinder asBinder() {
                return null;
            }

            public void onProgress(int i) throws RemoteException {
                if (iBleMeshUpgradeResponse != null) {
                    iBleMeshUpgradeResponse.onProgress(i);
                }
            }

            public void onResponse(int i, String str) throws RemoteException {
                if (iBleMeshUpgradeResponse != null) {
                    iBleMeshUpgradeResponse.onResponse(i, str);
                }
                if (i == 0 && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
                    DeviceApi.a(str2, str3, "", "", "", "", (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                        /* renamed from: a */
                        public void onSuccess(Void voidR) {
                        }

                        public void onFailure(Error error) {
                        }
                    });
                }
            }

            public boolean isMeshDevice() throws RemoteException {
                if (iBleMeshUpgradeResponse == null) {
                    return false;
                }
                return iBleMeshUpgradeResponse.isMeshDevice();
            }
        });
    }

    public void a(String str) {
        MeshDfuManager.a().a(str);
    }

    public byte[] a(String str, byte[] bArr) {
        return BluetoothApi.a(str, bArr);
    }

    public byte[] b(String str, byte[] bArr) {
        return BluetoothApi.b(str, bArr);
    }
}
