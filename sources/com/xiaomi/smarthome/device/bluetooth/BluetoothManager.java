package com.xiaomi.smarthome.device.bluetooth;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.mi.global.bbs.utils.Constants;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.router.miio.miioplugin.PluginServiceManager;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.bluetooth.Response;
import com.xiaomi.smarthome.bluetooth.XmBluetoothDevice;
import com.xiaomi.smarthome.bluetooth.XmBluetoothManager;
import com.xiaomi.smarthome.core.entity.bluetooth.SearchRequest;
import com.xiaomi.smarthome.core.entity.bluetooth.SearchResult;
import com.xiaomi.smarthome.core.server.bluetooth.IBleResponse;
import com.xiaomi.smarthome.core.server.bluetooth.SearchResponse;
import com.xiaomi.smarthome.core.server.internal.bluetooth.BluetoothResponse;
import com.xiaomi.smarthome.core.server.internal.bluetooth.BtConstants;
import com.xiaomi.smarthome.core.server.internal.bluetooth.IBluetoothService;
import com.xiaomi.smarthome.device.BleDevice;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.device.api.IBleCallback;
import com.xiaomi.smarthome.device.bluetooth.security.BLECipher;
import com.xiaomi.smarthome.device.bluetooth.utils.BluetoothConstants;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.log.BluetoothMyLogger;
import com.xiaomi.smarthome.frame.plugin.host.PluginBluetoothManagerHostApi;
import com.xiaomi.smarthome.framework.plugin.rn.RNRuntime;
import com.xiaomi.smarthome.library.bluetooth.BleConnectOptions;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothUtils;
import com.xiaomi.smarthome.library.common.util.ByteUtils;
import com.xiaomi.smarthome.library.crypto.MD5Util;
import com.xiaomi.smarthome.stat.STAT;
import java.util.UUID;

public class BluetoothManager extends PluginBluetoothManagerHostApi {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public Handler f1514a = new Handler(Looper.getMainLooper());

    public boolean isAutoReconnect(String str) {
        return false;
    }

    public boolean setAutoReconnect(String str, boolean z) {
        return false;
    }

    public void a(String str, BleConnectOptions bleConnectOptions, final Response.BleConnectResponse bleConnectResponse) {
        if (!TextUtils.isEmpty(str)) {
            Bundle bundle = new Bundle();
            if (bleConnectOptions != null) {
                bundle.putParcelable(BtConstants.d, bleConnectOptions);
            }
            CoreApi.a().a(str, 1, bundle, (IBleResponse) new BluetoothResponse() {
                public void onResponse(int i, Bundle bundle) throws RemoteException {
                    if (bleConnectResponse != null) {
                        if (bundle != null) {
                            bundle.setClassLoader(bleConnectResponse.getClass().getClassLoader());
                        }
                        bleConnectResponse.onResponse(i, bundle);
                    }
                }
            });
        }
    }

    public void connect(String str, Response.BleConnectResponse bleConnectResponse) {
        a(str, (BleConnectOptions) null, bleConnectResponse);
    }

    public void disconnect(String str) {
        CoreApi.a().a(str, 2, (Bundle) null, (IBleResponse) null);
    }

    public void read(String str, UUID uuid, UUID uuid2, Response.BleReadResponse bleReadResponse) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(IBluetoothService.N, uuid);
        bundle.putSerializable(IBluetoothService.O, uuid2);
        final String str2 = str;
        final UUID uuid3 = uuid;
        final UUID uuid4 = uuid2;
        final Response.BleReadResponse bleReadResponse2 = bleReadResponse;
        CoreApi.a().a(str, 3, bundle, (IBleResponse) new BluetoothResponse() {
            public void onResponse(int i, Bundle bundle) throws RemoteException {
                byte[] byteArray = bundle.getByteArray(IBluetoothService.P);
                BluetoothManager.this.a(str2, i, "read", uuid3, uuid4);
                bleReadResponse2.onResponse(i, byteArray);
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(String str, int i, String str2, UUID uuid, UUID uuid2) {
        String a2 = a(str);
        if (!TextUtils.isEmpty(a2)) {
            int connectStatus = getConnectStatus(str);
            if (i == 0) {
                STAT.i.a(a2, str2);
            } else if (2 == connectStatus) {
                STAT.i.a(a2, str2, i, uuid, uuid2);
            }
        }
    }

    public static String a(String str) {
        DeviceStat f = RNRuntime.a().f();
        if (f != null) {
            return f.model;
        }
        BleDevice d = BLEDeviceManager.d(str);
        if (d != null) {
            return d.model;
        }
        return null;
    }

    public void write(String str, UUID uuid, UUID uuid2, byte[] bArr, Response.BleWriteResponse bleWriteResponse) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(IBluetoothService.N, uuid);
        bundle.putSerializable(IBluetoothService.O, uuid2);
        bundle.putByteArray(IBluetoothService.P, bArr);
        final Response.BleWriteResponse bleWriteResponse2 = bleWriteResponse;
        final String str2 = str;
        final UUID uuid3 = uuid;
        final UUID uuid4 = uuid2;
        CoreApi.a().a(str, 4, bundle, (IBleResponse) new BluetoothResponse() {
            public void onResponse(int i, Bundle bundle) throws RemoteException {
                if (bleWriteResponse2 != null) {
                    BluetoothManager.this.a(str2, i, Constants.TitleMenu.MENU_WRITE, uuid3, uuid4);
                    bleWriteResponse2.onResponse(i, null);
                }
            }
        });
    }

    public void notify(String str, UUID uuid, UUID uuid2, final Response.BleNotifyResponse bleNotifyResponse) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(IBluetoothService.N, uuid);
        bundle.putSerializable(IBluetoothService.O, uuid2);
        CoreApi.a().a(str, 6, bundle, (IBleResponse) new BluetoothResponse() {
            public void onResponse(int i, Bundle bundle) throws RemoteException {
                if (bleNotifyResponse != null) {
                    bleNotifyResponse.onResponse(i, null);
                }
            }
        });
    }

    public void unnotify(String str, UUID uuid, UUID uuid2) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(IBluetoothService.N, uuid);
        bundle.putSerializable(IBluetoothService.O, uuid2);
        CoreApi.a().a(str, 7, bundle, (IBleResponse) null);
    }

    public void indication(String str, UUID uuid, UUID uuid2, final Response.BleNotifyResponse bleNotifyResponse) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(IBluetoothService.N, uuid);
        bundle.putSerializable(IBluetoothService.O, uuid2);
        CoreApi.a().a(str, 15, bundle, (IBleResponse) new BluetoothResponse() {
            public void onResponse(int i, Bundle bundle) throws RemoteException {
                if (bleNotifyResponse != null) {
                    bleNotifyResponse.onResponse(i, null);
                }
            }
        });
    }

    public void unindication(String str, UUID uuid, UUID uuid2) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(IBluetoothService.N, uuid);
        bundle.putSerializable(IBluetoothService.O, uuid2);
        CoreApi.a().a(str, 16, bundle, (IBleResponse) null);
    }

    public void readRemoteRssi(String str, final Response.BleReadRssiResponse bleReadRssiResponse) {
        CoreApi.a().a(str, 10, (Bundle) null, (IBleResponse) new BluetoothResponse() {
            public void onResponse(int i, Bundle bundle) throws RemoteException {
                bleReadRssiResponse.onResponse(i, Integer.valueOf(bundle.getInt(IBluetoothService.R, 0)));
            }
        });
    }

    public void openBluetoothSilently() {
        SHApplication.getGlobalWorkerHandler().post(new Runnable() {
            public void run() {
                BluetoothUtils.k();
            }
        });
    }

    public void openBluetooth(Context context) {
        BluetoothUtils.a(context);
    }

    public boolean isBluetoothOpen() {
        return BluetoothUtils.b();
    }

    public void deviceRename(String str, String str2) {
        try {
            if (PluginServiceManager.a().b() != null) {
                PluginServiceManager.a().b().renameBluetoothDevice(str, str2);
            }
        } catch (RemoteException unused) {
        }
    }

    public void bindDevice(String str) {
        CoreApi.a().a(str, 33, (Bundle) null, (IBleResponse) null);
    }

    public void unBindDevice(String str) {
        CoreApi.a().a(str, 34, (Bundle) null, (IBleResponse) null);
    }

    public void secureConnect(String str, final Response.BleConnectResponse bleConnectResponse) {
        try {
            if (PluginServiceManager.a().b() != null) {
                PluginServiceManager.a().b().secureConnect(str, new IBleCallback.Stub() {
                    public void onResponse(final int i, final Bundle bundle) throws RemoteException {
                        if (bleConnectResponse != null) {
                            BluetoothManager.this.f1514a.post(new Runnable() {
                                public void run() {
                                    try {
                                        if (bundle != null) {
                                            bundle.setClassLoader(bleConnectResponse.getClass().getClassLoader());
                                        }
                                        bleConnectResponse.onResponse(i, bundle);
                                    } catch (Throwable th) {
                                        BluetoothLog.a(th);
                                    }
                                }
                            });
                        }
                    }
                });
            }
        } catch (RemoteException unused) {
        }
    }

    public void call(String str, int i, Bundle bundle, final Response.BleCallResponse bleCallResponse) {
        try {
            if (PluginServiceManager.a().b() != null) {
                PluginServiceManager.a().b().callBleApi(str, i, bundle, new IBleCallback.Stub() {
                    public void onResponse(int i, Bundle bundle) throws RemoteException {
                        if (bleCallResponse != null) {
                            bleCallResponse.onResponse(i, bundle);
                        }
                    }
                });
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void disconnect(String str, long j) {
        BluetoothMyLogger.f("disconnect " + BluetoothMyLogger.a(str) + ", " + j);
        Bundle bundle = new Bundle();
        bundle.putLong(IBluetoothService.ab, j);
        CoreApi.a().a(str, 2, bundle, (IBleResponse) null);
    }

    public void refreshDeviceStatus(final String str, long j, final Response.BleDeviceStatusResponse bleDeviceStatusResponse) {
        if (!TextUtils.isEmpty(str) && bleDeviceStatusResponse != null) {
            Bundle bundle = new Bundle();
            bundle.putLong(IBluetoothService.X, j);
            CoreApi.a().a(str, 35, bundle, (IBleResponse) new BluetoothResponse() {
                public void onResponse(int i, Bundle bundle) throws RemoteException {
                    int i2 = bundle.getInt(IBluetoothService.W, 48);
                    Intent intent = new Intent("action.online.status.changed");
                    intent.putExtra("extra_mac", str);
                    intent.putExtra("extra_online_status", i2);
                    BluetoothUtils.a(intent);
                    if (bleDeviceStatusResponse != null) {
                        bleDeviceStatusResponse.onResponse(i, Integer.valueOf(i2));
                    }
                }
            });
        }
    }

    public void startScan(int i, int i2, final XmBluetoothManager.BluetoothSearchResponse bluetoothSearchResponse) {
        if (i > 0 && bluetoothSearchResponse != null) {
            Log.i("startScan", "BM startScan");
            SearchRequest.Builder builder = new SearchRequest.Builder();
            if (i2 == 1) {
                builder.a(i);
            } else if (i2 == 0) {
                builder.b(i);
            } else {
                return;
            }
            CoreApi.a().a(builder.a(), (SearchResponse) new SearchResponse.Stub() {
                public void onSearchStarted() throws RemoteException {
                    BluetoothLog.e("app onSearchStarted");
                    bluetoothSearchResponse.onSearchStarted();
                }

                public void onDeviceFounded(SearchResult searchResult) throws RemoteException {
                    BluetoothLog.e("app onDeviceFounded");
                    XmBluetoothDevice xmBluetoothDevice = new XmBluetoothDevice();
                    xmBluetoothDevice.device = searchResult.f13969a;
                    xmBluetoothDevice.deviceType = searchResult.d;
                    xmBluetoothDevice.name = searchResult.e;
                    xmBluetoothDevice.rssi = searchResult.b;
                    xmBluetoothDevice.scanRecord = searchResult.c;
                    bluetoothSearchResponse.onDeviceFounded(xmBluetoothDevice);
                }

                public void onSearchStopped() throws RemoteException {
                    BluetoothLog.e("app onSearchStopped");
                    bluetoothSearchResponse.onSearchStopped();
                }

                public void onSearchCanceled() throws RemoteException {
                    BluetoothLog.e("app onSearchCanceled");
                    bluetoothSearchResponse.onSearchCanceled();
                }
            });
        }
    }

    public void stopScan() {
        Log.i("stopScan", "BM stop");
        CoreApi.a().W();
    }

    public void registerMediaButtonReceiver(String str) {
        if (!TextUtils.isEmpty(str) && PluginServiceManager.a().b() != null) {
            try {
                PluginServiceManager.a().b().registerMediaButtonReceiver(str);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public String a() {
        if (PluginServiceManager.a().b() == null) {
            return null;
        }
        try {
            return PluginServiceManager.a().b().getMediaButtonModel();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void unRegisterMediaButtonReceiver(String str) {
        if (!TextUtils.isEmpty(str) && PluginServiceManager.a().b() != null) {
            try {
                PluginServiceManager.a().b().unRegisterMediaButtonReceiver(str);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void removeToken(String str) {
        if (!TextUtils.isEmpty(str)) {
            BluetoothMyLogger.d(String.format("removeToken for %s", new Object[]{BluetoothMyLogger.a(str)}));
            call(str, 1, (Bundle) null, (Response.BleCallResponse) null);
        }
    }

    public void writeNoRsp(String str, UUID uuid, UUID uuid2, byte[] bArr, final Response.BleWriteResponse bleWriteResponse) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(IBluetoothService.N, uuid);
        bundle.putSerializable(IBluetoothService.O, uuid2);
        bundle.putByteArray(IBluetoothService.P, bArr);
        CoreApi.a().a(str, 5, bundle, (IBleResponse) new BluetoothResponse() {
            public void onResponse(int i, Bundle bundle) throws RemoteException {
                if (bleWriteResponse != null) {
                    bleWriteResponse.onResponse(i, null);
                }
            }
        });
    }

    public int getConnectStatus(String str) {
        return BluetoothUtils.d(str);
    }

    public void startLeScan(int i, UUID[] uuidArr, final XmBluetoothManager.BluetoothSearchResponse bluetoothSearchResponse) {
        if (i > 0 && bluetoothSearchResponse != null) {
            Log.i("startScan", "BM startLeScan");
            SearchRequest.Builder builder = new SearchRequest.Builder();
            builder.a(i, uuidArr);
            CoreApi.a().a(builder.a(), (SearchResponse) new SearchResponse.Stub() {
                public void onSearchStarted() throws RemoteException {
                    BluetoothLog.e("app onSearchStarted");
                    bluetoothSearchResponse.onSearchStarted();
                }

                public void onDeviceFounded(SearchResult searchResult) throws RemoteException {
                    BluetoothLog.e("app onDeviceFounded");
                    XmBluetoothDevice xmBluetoothDevice = new XmBluetoothDevice();
                    xmBluetoothDevice.device = searchResult.f13969a;
                    xmBluetoothDevice.deviceType = searchResult.d;
                    xmBluetoothDevice.name = searchResult.e;
                    xmBluetoothDevice.rssi = searchResult.b;
                    xmBluetoothDevice.scanRecord = searchResult.c;
                    bluetoothSearchResponse.onDeviceFounded(xmBluetoothDevice);
                }

                public void onSearchStopped() throws RemoteException {
                    BluetoothLog.e("app onSearchStopped");
                    bluetoothSearchResponse.onSearchStopped();
                }

                public void onSearchCanceled() throws RemoteException {
                    BluetoothLog.e("app onSearchCanceled");
                    bluetoothSearchResponse.onSearchCanceled();
                }
            });
        }
    }

    public void getBluetoothFirmwareVersion(final String str, final Response.BleReadFirmwareVersionResponse bleReadFirmwareVersionResponse) {
        if (bleReadFirmwareVersionResponse != null && !TextUtils.isEmpty(str)) {
            XmBluetoothManager.getInstance().read(str, BluetoothConstants.w, BluetoothConstants.y, new Response.BleReadResponse() {
                /* renamed from: a */
                public void onResponse(int i, byte[] bArr) {
                    if (i != 0 || ByteUtils.e(bArr)) {
                        bleReadFirmwareVersionResponse.onResponse(-1, "");
                        return;
                    }
                    bleReadFirmwareVersionResponse.onResponse(0, new String(ByteUtils.b(BLECipher.a(BleCacheUtils.q(str), bArr), (byte) 0)));
                }
            });
        }
    }

    public boolean setAlertConfigs(String str, int i, boolean z) {
        return CoreApi.a().a(str, i, z);
    }

    public void writeBlock(String str, byte[] bArr, final Response.BleWriteResponse bleWriteResponse) {
        Bundle bundle = new Bundle();
        bundle.putByteArray(IBluetoothService.P, bArr);
        CoreApi.a().a(str, 11, bundle, (IBleResponse) new BluetoothResponse() {
            public void onResponse(int i, Bundle bundle) throws RemoteException {
                if (bleWriteResponse != null) {
                    bleWriteResponse.onResponse(i, null);
                }
            }
        });
    }

    public void registerBlockListener(String str, final Response.BleReadResponse bleReadResponse) {
        CoreApi.a().a(str, 12, (Bundle) null, (IBleResponse) new BluetoothResponse() {
            public void onResponse(int i, Bundle bundle) throws RemoteException {
                byte[] byteArray = bundle.getByteArray(IBluetoothService.P);
                if (bleReadResponse != null) {
                    bleReadResponse.onResponse(i, byteArray);
                }
            }
        });
    }

    public void unregisterBlockListener(String str) {
        CoreApi.a().a(str, 13, (Bundle) null, (IBleResponse) null);
    }

    public void securityChipConnect(String str, final Response.BleConnectResponse bleConnectResponse) {
        try {
            if (PluginServiceManager.a().b() != null) {
                PluginServiceManager.a().b().securityChipConnect(str, new IBleCallback.Stub() {
                    public void onResponse(final int i, final Bundle bundle) throws RemoteException {
                        if (bleConnectResponse != null) {
                            BluetoothManager.this.f1514a.post(new Runnable() {
                                public void run() {
                                    try {
                                        if (bundle != null) {
                                            bundle.setClassLoader(bleConnectResponse.getClass().getClassLoader());
                                        }
                                        bleConnectResponse.onResponse(i, bundle);
                                    } catch (Throwable th) {
                                        BluetoothLog.a(th);
                                    }
                                }
                            });
                        }
                    }
                });
            }
        } catch (RemoteException unused) {
        }
    }

    public void securityChipEncrypt(String str, byte[] bArr, final Response.BleReadResponse bleReadResponse) {
        Bundle bundle = new Bundle();
        bundle.putByteArray(IBluetoothService.P, bArr);
        CoreApi.a().a(str, 43, bundle, (IBleResponse) new BluetoothResponse() {
            public void onResponse(int i, Bundle bundle) throws RemoteException {
                if (bleReadResponse != null) {
                    bleReadResponse.onResponse(i, bundle.getByteArray(IBluetoothService.P));
                }
            }
        });
    }

    public void securityChipDecrypt(String str, byte[] bArr, final Response.BleReadResponse bleReadResponse) {
        Bundle bundle = new Bundle();
        bundle.putByteArray(IBluetoothService.P, bArr);
        CoreApi.a().a(str, 44, bundle, (IBleResponse) new BluetoothResponse() {
            public void onResponse(int i, Bundle bundle) throws RemoteException {
                if (bleReadResponse != null) {
                    bleReadResponse.onResponse(i, bundle.getByteArray(IBluetoothService.P));
                }
            }
        });
    }

    public void securityChipSharedDeviceConnect(String str, final Response.BleConnectResponse bleConnectResponse) {
        try {
            if (PluginServiceManager.a().b() != null) {
                PluginServiceManager.a().b().securityChipSharedDeviceConnect(str, new IBleCallback.Stub() {
                    public void onResponse(final int i, final Bundle bundle) throws RemoteException {
                        if (bleConnectResponse != null) {
                            BluetoothManager.this.f1514a.post(new Runnable() {
                                public void run() {
                                    try {
                                        if (bundle != null) {
                                            bundle.setClassLoader(bleConnectResponse.getClass().getClassLoader());
                                        }
                                        bleConnectResponse.onResponse(i, bundle);
                                    } catch (Throwable th) {
                                        BluetoothLog.a(th);
                                    }
                                }
                            });
                        }
                    }
                });
            }
        } catch (RemoteException unused) {
        }
    }

    public void isBleGatewayConnected(String str, final Response.BleResponse<Void> bleResponse) {
        CoreApi.a().a(str, 46, (Bundle) null, (IBleResponse) new BluetoothResponse() {
            public void onResponse(int i, Bundle bundle) throws RemoteException {
                if (bleResponse != null) {
                    bleResponse.onResponse(i, null);
                }
            }
        });
    }

    public void registerCharacterChanged(String str, UUID uuid, UUID uuid2, final Response.BleWriteResponse bleWriteResponse) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(IBluetoothService.N, uuid);
        bundle.putSerializable(IBluetoothService.O, uuid2);
        CoreApi.a().a(str, 41, bundle, (IBleResponse) new BluetoothResponse() {
            public void onResponse(int i, Bundle bundle) throws RemoteException {
                if (bleWriteResponse != null) {
                    bleWriteResponse.onResponse(i, null);
                }
            }
        });
    }

    public void unregisterCharacterChanged(String str, UUID uuid, UUID uuid2) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(IBluetoothService.N, uuid);
        bundle.putSerializable(IBluetoothService.O, uuid2);
        CoreApi.a().a(str, 42, bundle, (IBleResponse) null);
    }

    public boolean isSecurityChipSharedKeyValid(String str) {
        String v = BleCacheUtils.v(str);
        if (!TextUtils.isEmpty(v) && !TextUtils.equals(v, "-17973521")) {
            return true;
        }
        BluetoothMyLogger.d("isSecurityChipSharedKeyValid = inValid(" + v + Operators.BRACKET_END_STR);
        return false;
    }

    public void securityChipOperate(String str, int i, final Response.BleReadResponse bleReadResponse) {
        Bundle bundle = new Bundle();
        bundle.putInt(IBluetoothService.V, i);
        CoreApi.a().a(str, 45, bundle, (IBleResponse) new BluetoothResponse() {
            public void onResponse(int i, Bundle bundle) throws RemoteException {
                if (bleReadResponse != null) {
                    bleReadResponse.onResponse(i, bundle.getByteArray(IBluetoothService.P));
                }
            }
        });
    }

    public String getTokenMd5(String str) {
        String p = BleCacheUtils.p(str);
        if (TextUtils.isEmpty(p)) {
            return "";
        }
        return MD5Util.a(p);
    }

    public void getOneTimePassword(String str, int i, int i2, final Response.BleResponseV2<int[]> bleResponseV2) {
        Bundle bundle = new Bundle();
        bundle.putInt(IBluetoothService.ae, i);
        bundle.putInt(IBluetoothService.af, i2);
        CoreApi.a().a(str, 47, bundle, (IBleResponse) new BluetoothResponse() {
            public void onResponse(int i, Bundle bundle) throws RemoteException {
                int[] iArr;
                if (bleResponseV2 != null) {
                    String str = null;
                    if (bundle != null) {
                        str = bundle.getString(IBluetoothService.ah);
                        iArr = bundle.getIntArray(IBluetoothService.Q);
                    } else {
                        iArr = null;
                    }
                    bleResponseV2.onResponse(i, str, iArr);
                }
            }
        });
    }

    public void getOneTimePasswordWithDelayTime(String str, int i, int i2, long j, final Response.BleResponseV2<int[]> bleResponseV2) {
        Bundle bundle = new Bundle();
        bundle.putInt(IBluetoothService.ae, i);
        bundle.putInt(IBluetoothService.af, i2);
        bundle.putLong(IBluetoothService.ag, j);
        CoreApi.a().a(str, 47, bundle, (IBleResponse) new BluetoothResponse() {
            public void onResponse(int i, Bundle bundle) throws RemoteException {
                int[] iArr;
                if (bleResponseV2 != null) {
                    String str = null;
                    if (bundle != null) {
                        str = bundle.getString(IBluetoothService.ah);
                        iArr = bundle.getIntArray(IBluetoothService.Q);
                    } else {
                        iArr = null;
                    }
                    bleResponseV2.onResponse(i, str, iArr);
                }
            }
        });
    }

    public void isBleGatewayExistInDeviceList(final Response.BleResponse<Bundle> bleResponse) {
        try {
            if (PluginServiceManager.a().b() != null) {
                PluginServiceManager.a().b().isBleGatewayExistInDeviceList(new IBleCallback.Stub() {
                    public void onResponse(final int i, final Bundle bundle) throws RemoteException {
                        if (bleResponse != null) {
                            BluetoothManager.this.f1514a.post(new Runnable() {
                                public void run() {
                                    try {
                                        if (bundle != null) {
                                            bundle.setClassLoader(bleResponse.getClass().getClassLoader());
                                        }
                                        bleResponse.onResponse(i, bundle);
                                    } catch (Throwable th) {
                                        BluetoothLog.a(th);
                                    }
                                }
                            });
                        }
                    }
                });
            }
        } catch (RemoteException unused) {
        }
    }

    public void bleMeshConnect(String str, String str2, final Response.BleConnectResponse bleConnectResponse) {
        try {
            if (PluginServiceManager.a().b() != null) {
                PluginServiceManager.a().b().bleMeshConnect(str, str2, new IBleCallback.Stub() {
                    public void onResponse(final int i, final Bundle bundle) throws RemoteException {
                        if (bleConnectResponse != null) {
                            BluetoothManager.this.f1514a.post(new Runnable() {
                                public void run() {
                                    try {
                                        if (bundle != null) {
                                            bundle.setClassLoader(bleConnectResponse.getClass().getClassLoader());
                                        }
                                        bleConnectResponse.onResponse(i, bundle);
                                    } catch (Throwable th) {
                                        BluetoothLog.a(th);
                                    }
                                }
                            });
                        }
                    }
                });
            }
        } catch (RemoteException unused) {
        }
    }

    public void startBleMeshUpgrade(String str, String str2, String str3, String str4, Response.BleUpgradeResponse bleUpgradeResponse) {
        CoreApi.a().a(str, str2, str3, str4, bleUpgradeResponse);
    }

    public void cancelBleMeshUpgrade(String str) {
        CoreApi.a().h(str);
    }

    public void getBleMeshFirmwareVersion(String str, final Response.BleReadFirmwareVersionResponse bleReadFirmwareVersionResponse) {
        if (bleReadFirmwareVersionResponse != null && !TextUtils.isEmpty(str)) {
            CoreApi.a().a(str, 48, new Bundle(), (IBleResponse) new BluetoothResponse() {
                public void onResponse(int i, Bundle bundle) throws RemoteException {
                    bleReadFirmwareVersionResponse.onResponse(i, bundle.getString(IBluetoothService.V));
                }
            });
        }
    }

    public void miotBleEncrypt(String str, byte[] bArr, final Response.BleReadResponse bleReadResponse) {
        Bundle bundle = new Bundle();
        bundle.putByteArray(IBluetoothService.P, bArr);
        CoreApi.a().a(str, 49, bundle, (IBleResponse) new BluetoothResponse() {
            public void onResponse(int i, Bundle bundle) throws RemoteException {
                if (bleReadResponse != null) {
                    byte[] bArr = null;
                    if (bundle != null) {
                        bArr = bundle.getByteArray(IBluetoothService.P);
                    }
                    bleReadResponse.onResponse(i, bArr);
                }
            }
        });
    }

    public void miotBleDecrypt(String str, byte[] bArr, final Response.BleReadResponse bleReadResponse) {
        Bundle bundle = new Bundle();
        bundle.putByteArray(IBluetoothService.P, bArr);
        CoreApi.a().a(str, 50, bundle, (IBleResponse) new BluetoothResponse() {
            public void onResponse(int i, Bundle bundle) throws RemoteException {
                if (bleReadResponse != null) {
                    byte[] bArr = null;
                    if (bundle != null) {
                        bArr = bundle.getByteArray(IBluetoothService.P);
                    }
                    bleReadResponse.onResponse(i, bArr);
                }
            }
        });
    }

    public void bleStandardAuthConnect(String str, final Response.BleConnectResponse bleConnectResponse) {
        try {
            if (PluginServiceManager.a().b() != null) {
                PluginServiceManager.a().b().bleStandardAuthConnect(str, new IBleCallback.Stub() {
                    public void onResponse(final int i, final Bundle bundle) throws RemoteException {
                        if (bleConnectResponse != null) {
                            BluetoothManager.this.f1514a.post(new Runnable() {
                                public void run() {
                                    try {
                                        if (bundle != null) {
                                            bundle.setClassLoader(bleConnectResponse.getClass().getClassLoader());
                                        }
                                        bleConnectResponse.onResponse(i, bundle);
                                    } catch (Throwable th) {
                                        BluetoothLog.a(th);
                                    }
                                }
                            });
                        }
                    }
                });
            }
        } catch (RemoteException unused) {
        }
    }

    public void getBleStandardAuthFirmwareVersion(String str, final Response.BleReadFirmwareVersionResponse bleReadFirmwareVersionResponse) {
        if (bleReadFirmwareVersionResponse != null && !TextUtils.isEmpty(str)) {
            CoreApi.a().a(str, 53, new Bundle(), (IBleResponse) new BluetoothResponse() {
                public void onResponse(int i, Bundle bundle) throws RemoteException {
                    bleReadFirmwareVersionResponse.onResponse(i, bundle.getString(IBluetoothService.V));
                }
            });
        }
    }

    public void bleStandardAuthEncrypt(String str, byte[] bArr, final Response.BleReadResponse bleReadResponse) {
        Bundle bundle = new Bundle();
        bundle.putByteArray(IBluetoothService.P, bArr);
        CoreApi.a().a(str, 43, bundle, (IBleResponse) new BluetoothResponse() {
            public void onResponse(int i, Bundle bundle) throws RemoteException {
                if (bleReadResponse != null) {
                    bleReadResponse.onResponse(i, bundle.getByteArray(IBluetoothService.P));
                }
            }
        });
    }

    public void bleStandardAuthDecrypt(String str, byte[] bArr, final Response.BleReadResponse bleReadResponse) {
        Bundle bundle = new Bundle();
        bundle.putByteArray(IBluetoothService.P, bArr);
        CoreApi.a().a(str, 44, bundle, (IBleResponse) new BluetoothResponse() {
            public void onResponse(int i, Bundle bundle) throws RemoteException {
                if (bleReadResponse != null) {
                    bleReadResponse.onResponse(i, bundle.getByteArray(IBluetoothService.P));
                }
            }
        });
    }

    public void isBleCharacterExist(String str, UUID uuid, UUID uuid2, final Response.BleResponse<Void> bleResponse) {
        if (getConnectStatus(str) == 2) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(IBluetoothService.N, uuid);
            bundle.putSerializable(IBluetoothService.O, uuid2);
            CoreApi.a().a(str, 54, bundle, (IBleResponse) new BluetoothResponse() {
                public void onResponse(int i, Bundle bundle) throws RemoteException {
                    if (bleResponse != null) {
                        bleResponse.onResponse(i, null);
                    }
                }
            });
        } else if (bleResponse != null) {
            bleResponse.onResponse(-8, null);
        }
    }

    public byte[] miotBleEncryptSync(String str, byte[] bArr) {
        return CoreApi.a().a(str, bArr);
    }

    public byte[] miotBleDecryptSync(String str, byte[] bArr) {
        return CoreApi.a().b(str, bArr);
    }
}
