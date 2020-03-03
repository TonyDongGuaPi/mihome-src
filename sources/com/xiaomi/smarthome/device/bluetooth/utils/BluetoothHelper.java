package com.xiaomi.smarthome.device.bluetooth.utils;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.bluetooth.Response;
import com.xiaomi.smarthome.core.entity.bluetooth.SearchRequest;
import com.xiaomi.smarthome.core.entity.device.BtDevice;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.core.server.bluetooth.IBleResponse;
import com.xiaomi.smarthome.core.server.bluetooth.SearchResponse;
import com.xiaomi.smarthome.core.server.internal.bluetooth.BluetoothResponse;
import com.xiaomi.smarthome.core.server.internal.bluetooth.IBluetoothService;
import com.xiaomi.smarthome.core.server.internal.bluetooth.LocalSearchResponse;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.ISecureConnectHandler;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.SecureConnectOptions;
import com.xiaomi.smarthome.device.BleDevice;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceUtils;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.device.bluetooth.BLEDeviceManager;
import com.xiaomi.smarthome.device.bluetooth.BleCacheUtils;
import com.xiaomi.smarthome.device.bluetooth.MiioBtSearchResponse;
import com.xiaomi.smarthome.device.bluetooth.security.BLECipher;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.log.BluetoothMyLogger;
import com.xiaomi.smarthome.frame.plugin.PluginApi;
import com.xiaomi.smarthome.frame.plugin.RunningProcess;
import com.xiaomi.smarthome.library.bluetooth.BleConnectOptions;
import com.xiaomi.smarthome.library.bluetooth.BluetoothContextManager;
import com.xiaomi.smarthome.library.bluetooth.IBleSecureConnectResponse;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothUtils;
import com.xiaomi.smarthome.library.common.util.ListUtils;
import com.xiaomi.smarthome.library.common.util.XMStringUtils;
import com.xiaomi.smarthome.miio.MiioManager;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import util.VersionUtils;

public class BluetoothHelper extends BluetoothContextManager {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static ExecutorService f15282a = Executors.newSingleThreadExecutor();

    public static boolean a() {
        return false;
    }

    public static boolean a(int i) {
        return i >= 0 && i <= 2;
    }

    public static int a(List<BleDevice> list) {
        if (ListUtils.a(list)) {
            return 0;
        }
        ArrayList arrayList = new ArrayList();
        for (BleDevice next : list) {
            if (!arrayList.contains(next.model)) {
                arrayList.add(next.model);
            }
        }
        return arrayList.size();
    }

    public static void a(BleDevice bleDevice) {
        PluginRecord d;
        if (bleDevice != null && (d = CoreApi.a().d(bleDevice.model)) != null && d.l()) {
            Intent intent = new Intent();
            intent.putExtra("mac", bleDevice.mac);
            PluginApi.getInstance().sendMessage(CommonApplication.getAppContext(), d, 9, intent, (DeviceStat) null, (RunningProcess) null, false, (PluginApi.SendMessageCallback) null);
        }
    }

    public static int b(int i) {
        return a(CoreApi.a().b(i));
    }

    public static int a(String str) {
        PluginRecord d = CoreApi.a().d(str);
        if (d != null) {
            return d.c().y();
        }
        return 0;
    }

    public static boolean c(int i) {
        int b = b(i);
        return b == 1 || b == 2;
    }

    public static int a(String str, String str2) {
        return DeviceUtils.a(str, str2);
    }

    public static boolean a(int i, String str) {
        return VersionUtils.a(i, str);
    }

    public static void a(String str, int i, Bundle bundle) {
        Intent intent = new Intent(str);
        intent.putExtra("extra_code", i);
        if (bundle == null) {
            bundle = new Bundle();
        }
        intent.putExtras(bundle);
        BluetoothUtils.a(intent);
    }

    public static void a(String str, BluetoothResponse bluetoothResponse) {
        Bundle bundle = new Bundle();
        bundle.putString(IBluetoothService.Z, str);
        CoreApi.a().a((String) null, 37, bundle, (IBleResponse) bluetoothResponse);
    }

    public static void b() {
        CoreApi.a().W();
    }

    public static void a(SearchRequest searchRequest, SearchResponse searchResponse) {
        CoreApi.a().a(searchRequest, searchResponse);
    }

    public static boolean b(String str, String str2) {
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            return true;
        }
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        if (str.equalsIgnoreCase(str2)) {
            return true;
        }
        PluginRecord d = CoreApi.a().d(str);
        PluginRecord d2 = CoreApi.a().d(str2);
        if (d == null || d.c() == null || d2 == null || d2.c() == null) {
            return false;
        }
        int d3 = d.c().d();
        int N = d.c().N();
        int d4 = d2.c().d();
        if (d3 == d2.c().N() || N == d4) {
            return true;
        }
        return false;
    }

    public static void b(String str) {
        CoreApi.a().a(str, 31, (Bundle) null, (IBleResponse) null);
    }

    public static void c() {
        CoreApi.a().a((String) null, 32, (Bundle) null, (IBleResponse) null);
    }

    public static void a(final SearchRequest searchRequest, final MiioBtSearchResponse miioBtSearchResponse) {
        if (searchRequest == null || miioBtSearchResponse == null) {
            BluetoothMyLogger.c(String.format("searchMiioBluetoothDevice request or resposne null", new Object[0]));
        } else {
            CoreApi.a().a(CommonApplication.getAppContext(), (CoreApi.IsCoreReadyCallback) new CoreApi.IsCoreReadyCallback() {
                public void onCoreReady() {
                    CoreApi.a().a(searchRequest, (LocalSearchResponse) new LocalSearchResponse() {
                        public void a() {
                            miioBtSearchResponse.a();
                        }

                        public void a(final BtDevice btDevice) {
                            BluetoothHelper.f15282a.submit(new Runnable() {
                                public void run() {
                                    final BleDevice a2 = BleDevice.a(btDevice);
                                    CommonApplication.getGlobalHandler().post(new Runnable() {
                                        public void run() {
                                            miioBtSearchResponse.a(a2);
                                        }
                                    });
                                }
                            });
                        }

                        public void b() {
                            miioBtSearchResponse.b();
                        }

                        public void c() {
                            miioBtSearchResponse.c();
                        }
                    });
                }
            });
        }
    }

    public static void a(String str, Response.BleConnectResponse bleConnectResponse) {
        a(str, new BleConnectOptions.Builder().a(1).c(35000).b(2).d(20000).a(), bleConnectResponse);
    }

    public static void a(String str, BleConnectOptions bleConnectOptions, final Response.BleConnectResponse bleConnectResponse) {
        a(str, bleConnectOptions, (IBleSecureConnectResponse) new IBleSecureConnectResponse() {
            public void a(int i, Bundle bundle) {
            }

            public void b(int i, Bundle bundle) {
            }

            public void c(int i, Bundle bundle) {
            }

            public void d(int i, Bundle bundle) {
                if (bleConnectResponse != null) {
                    bleConnectResponse.onResponse(i, bundle);
                }
            }
        });
    }

    public static ISecureConnectHandler a(final String str, BleConnectOptions bleConnectOptions, final IBleSecureConnectResponse iBleSecureConnectResponse) {
        return CoreApi.a().a(str, new SecureConnectOptions.Builder().a(bleConnectOptions).a(), (IBleSecureConnectResponse) new IBleSecureConnectResponse() {
            public void a(int i, Bundle bundle) {
                if (iBleSecureConnectResponse != null) {
                    iBleSecureConnectResponse.a(i, bundle);
                }
            }

            public void b(int i, Bundle bundle) {
                if (iBleSecureConnectResponse != null) {
                    iBleSecureConnectResponse.b(i, bundle);
                }
            }

            public void c(int i, Bundle bundle) {
                if (iBleSecureConnectResponse != null) {
                    iBleSecureConnectResponse.c(i, bundle);
                }
            }

            public void d(int i, Bundle bundle) {
                if (i == 0) {
                    Intent intent = new Intent("action.online.status.changed");
                    intent.putExtra("extra_mac", str);
                    intent.putExtra("extra_online_status", 80);
                    BluetoothUtils.a(intent);
                }
                if (iBleSecureConnectResponse != null) {
                    iBleSecureConnectResponse.d(i, bundle);
                }
            }
        });
    }

    public static void b(String str, final Response.BleConnectResponse bleConnectResponse) {
        b(str, new BleConnectOptions.Builder().a(1).c(35000).b(2).d(20000).a(), new IBleSecureConnectResponse() {
            public void a(int i, Bundle bundle) {
            }

            public void b(int i, Bundle bundle) {
            }

            public void c(int i, Bundle bundle) {
            }

            public void d(int i, Bundle bundle) {
                if (bleConnectResponse != null) {
                    bleConnectResponse.onResponse(i, bundle);
                }
            }
        });
    }

    public static ISecureConnectHandler b(final String str, BleConnectOptions bleConnectOptions, final IBleSecureConnectResponse iBleSecureConnectResponse) {
        return CoreApi.a().c(str, new SecureConnectOptions.Builder().a(bleConnectOptions).a(), new IBleSecureConnectResponse() {
            public void a(int i, Bundle bundle) {
                if (iBleSecureConnectResponse != null) {
                    iBleSecureConnectResponse.a(i, bundle);
                }
            }

            public void b(int i, Bundle bundle) {
                if (iBleSecureConnectResponse != null) {
                    iBleSecureConnectResponse.b(i, bundle);
                }
            }

            public void c(int i, Bundle bundle) {
                if (iBleSecureConnectResponse != null) {
                    iBleSecureConnectResponse.c(i, bundle);
                }
            }

            public void d(int i, Bundle bundle) {
                if (i == 0) {
                    Intent intent = new Intent("action.online.status.changed");
                    intent.putExtra("extra_mac", str);
                    intent.putExtra("extra_online_status", 80);
                    BluetoothUtils.a(intent);
                }
                if (iBleSecureConnectResponse != null) {
                    iBleSecureConnectResponse.d(i, bundle);
                }
            }
        });
    }

    public static void c(String str, final Response.BleConnectResponse bleConnectResponse) {
        c(str, new BleConnectOptions.Builder().a(1).c(35000).b(2).d(20000).a(), new IBleSecureConnectResponse() {
            public void a(int i, Bundle bundle) {
            }

            public void b(int i, Bundle bundle) {
            }

            public void c(int i, Bundle bundle) {
            }

            public void d(int i, Bundle bundle) {
                if (bleConnectResponse != null) {
                    bleConnectResponse.onResponse(i, bundle);
                }
            }
        });
    }

    public static ISecureConnectHandler c(final String str, BleConnectOptions bleConnectOptions, final IBleSecureConnectResponse iBleSecureConnectResponse) {
        return CoreApi.a().b(str, new SecureConnectOptions.Builder().a(bleConnectOptions).a(), (IBleSecureConnectResponse) new IBleSecureConnectResponse() {
            public void a(int i, Bundle bundle) {
                if (iBleSecureConnectResponse != null) {
                    iBleSecureConnectResponse.a(i, bundle);
                }
            }

            public void b(int i, Bundle bundle) {
                if (iBleSecureConnectResponse != null) {
                    iBleSecureConnectResponse.b(i, bundle);
                }
            }

            public void c(int i, Bundle bundle) {
                if (iBleSecureConnectResponse != null) {
                    iBleSecureConnectResponse.c(i, bundle);
                }
            }

            public void d(int i, Bundle bundle) {
                if (i == 0) {
                    Intent intent = new Intent("action.online.status.changed");
                    intent.putExtra("extra_mac", str);
                    intent.putExtra("extra_online_status", 80);
                    BluetoothUtils.a(intent);
                }
                if (iBleSecureConnectResponse != null) {
                    iBleSecureConnectResponse.d(i, bundle);
                }
            }
        });
    }

    public static ISecureConnectHandler d(final String str, BleConnectOptions bleConnectOptions, final IBleSecureConnectResponse iBleSecureConnectResponse) {
        return CoreApi.a().d(str, new SecureConnectOptions.Builder().a(bleConnectOptions).a(), new IBleSecureConnectResponse() {
            public void a(int i, Bundle bundle) {
                if (iBleSecureConnectResponse != null) {
                    iBleSecureConnectResponse.a(i, bundle);
                }
            }

            public void b(int i, Bundle bundle) {
                if (iBleSecureConnectResponse != null) {
                    iBleSecureConnectResponse.b(i, bundle);
                }
            }

            public void c(int i, Bundle bundle) {
                if (iBleSecureConnectResponse != null) {
                    iBleSecureConnectResponse.c(i, bundle);
                }
            }

            public void d(int i, Bundle bundle) {
                if (i == 0) {
                    Intent intent = new Intent("action.online.status.changed");
                    intent.putExtra("extra_mac", str);
                    intent.putExtra("extra_online_status", 80);
                    BluetoothUtils.a(intent);
                }
                if (iBleSecureConnectResponse != null) {
                    iBleSecureConnectResponse.d(i, bundle);
                }
            }
        });
    }

    public static void a(String str, String str2, String str3, final Response.BleConnectResponse bleConnectResponse) {
        a(str, str2, str3, new BleConnectOptions.Builder().a(2).c(35000).b(2).d(20000).a(), (IBleSecureConnectResponse) new IBleSecureConnectResponse() {
            public void a(int i, Bundle bundle) {
            }

            public void b(int i, Bundle bundle) {
            }

            public void c(int i, Bundle bundle) {
            }

            public void d(int i, Bundle bundle) {
                if (bleConnectResponse != null) {
                    bleConnectResponse.onResponse(i, bundle);
                }
            }
        });
    }

    public static ISecureConnectHandler a(final String str, String str2, String str3, BleConnectOptions bleConnectOptions, final IBleSecureConnectResponse iBleSecureConnectResponse) {
        return CoreApi.a().a(str, str2, str3, new SecureConnectOptions.Builder().a(bleConnectOptions).a(), (IBleSecureConnectResponse) new IBleSecureConnectResponse() {
            public void a(int i, Bundle bundle) {
                if (iBleSecureConnectResponse != null) {
                    iBleSecureConnectResponse.a(i, bundle);
                }
            }

            public void b(int i, Bundle bundle) {
                if (iBleSecureConnectResponse != null) {
                    iBleSecureConnectResponse.b(i, bundle);
                }
            }

            public void c(int i, Bundle bundle) {
                if (iBleSecureConnectResponse != null) {
                    iBleSecureConnectResponse.c(i, bundle);
                }
            }

            public void d(int i, Bundle bundle) {
                if (i == 0) {
                    Intent intent = new Intent("action.online.status.changed");
                    intent.putExtra("extra_mac", str);
                    intent.putExtra("extra_online_status", 80);
                    BluetoothUtils.a(intent);
                }
                if (iBleSecureConnectResponse != null) {
                    iBleSecureConnectResponse.d(i, bundle);
                }
            }
        });
    }

    public static ISecureConnectHandler a(final String str, String str2, String str3, int i, final IBleSecureConnectResponse iBleSecureConnectResponse) {
        return CoreApi.a().a(str, str2, str3, i, new SecureConnectOptions.Builder().a(new BleConnectOptions.Builder().a(1).c(35000).b(2).d(20000).a()).a(), (IBleSecureConnectResponse) new IBleSecureConnectResponse() {
            public void a(int i, Bundle bundle) {
                if (iBleSecureConnectResponse != null) {
                    iBleSecureConnectResponse.a(i, bundle);
                }
            }

            public void b(int i, Bundle bundle) {
                if (iBleSecureConnectResponse != null) {
                    iBleSecureConnectResponse.b(i, bundle);
                }
            }

            public void c(int i, Bundle bundle) {
                if (iBleSecureConnectResponse != null) {
                    iBleSecureConnectResponse.c(i, bundle);
                }
            }

            public void d(int i, Bundle bundle) {
                if (i == 0) {
                    Intent intent = new Intent("action.online.status.changed");
                    intent.putExtra("extra_mac", str);
                    intent.putExtra("extra_online_status", 80);
                    BluetoothUtils.a(intent);
                }
                if (iBleSecureConnectResponse != null) {
                    iBleSecureConnectResponse.d(i, bundle);
                }
            }
        });
    }

    public static void d(String str, final Response.BleConnectResponse bleConnectResponse) {
        e(str, new BleConnectOptions.Builder().a(1).c(35000).b(2).d(20000).a(), new IBleSecureConnectResponse() {
            public void a(int i, Bundle bundle) {
            }

            public void b(int i, Bundle bundle) {
            }

            public void c(int i, Bundle bundle) {
            }

            public void d(int i, Bundle bundle) {
                if (bleConnectResponse != null) {
                    bleConnectResponse.onResponse(i, bundle);
                }
            }
        });
    }

    public static ISecureConnectHandler e(final String str, BleConnectOptions bleConnectOptions, final IBleSecureConnectResponse iBleSecureConnectResponse) {
        return CoreApi.a().f(str, new SecureConnectOptions.Builder().a(bleConnectOptions).a(), new IBleSecureConnectResponse() {
            public void a(int i, Bundle bundle) {
                if (iBleSecureConnectResponse != null) {
                    iBleSecureConnectResponse.a(i, bundle);
                }
            }

            public void b(int i, Bundle bundle) {
                if (iBleSecureConnectResponse != null) {
                    iBleSecureConnectResponse.b(i, bundle);
                }
            }

            public void c(int i, Bundle bundle) {
                if (iBleSecureConnectResponse != null) {
                    iBleSecureConnectResponse.c(i, bundle);
                }
            }

            public void d(int i, Bundle bundle) {
                if (i == 0) {
                    Intent intent = new Intent("action.online.status.changed");
                    intent.putExtra("extra_mac", str);
                    intent.putExtra("extra_online_status", 80);
                    intent.putExtra("IS_STANDARD_AUTH_DEVICE", true);
                    BluetoothUtils.a(intent);
                }
                if (iBleSecureConnectResponse != null) {
                    iBleSecureConnectResponse.d(i, bundle);
                }
            }
        });
    }

    public static ISecureConnectHandler f(final String str, BleConnectOptions bleConnectOptions, final IBleSecureConnectResponse iBleSecureConnectResponse) {
        return CoreApi.a().e(str, new SecureConnectOptions.Builder().a(bleConnectOptions).a(), new IBleSecureConnectResponse() {
            public void a(int i, Bundle bundle) {
                if (iBleSecureConnectResponse != null) {
                    iBleSecureConnectResponse.a(i, bundle);
                }
            }

            public void b(int i, Bundle bundle) {
                if (iBleSecureConnectResponse != null) {
                    iBleSecureConnectResponse.b(i, bundle);
                }
            }

            public void c(int i, Bundle bundle) {
                if (iBleSecureConnectResponse != null) {
                    iBleSecureConnectResponse.c(i, bundle);
                }
            }

            public void d(int i, Bundle bundle) {
                if (i == 0) {
                    Intent intent = new Intent("action.online.status.changed");
                    intent.putExtra("extra_mac", str);
                    intent.putExtra("extra_online_status", 80);
                    intent.putExtra("IS_STANDARD_AUTH_DEVICE", true);
                    BluetoothUtils.a(intent);
                }
                if (iBleSecureConnectResponse != null) {
                    iBleSecureConnectResponse.d(i, bundle);
                }
            }
        });
    }

    public static int c(String str) {
        BleDevice d;
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        int h = BleCacheUtils.h(str);
        if (h > 0) {
            return h;
        }
        String j = BleCacheUtils.j(str);
        if (TextUtils.isEmpty(j) && (d = BLEDeviceManager.d(str)) != null) {
            j = d.model;
        }
        return !TextUtils.isEmpty(j) ? CoreApi.a().e(j) : h;
    }

    public static void a(String str, int i, Bundle bundle, Response.BleCallResponse bleCallResponse) {
        switch (i) {
            case 1:
                BleCacheUtils.x(str);
                return;
            case 2:
                BLEDeviceManager.a(BLEDeviceManager.d(str));
                return;
            case 3:
                CoreApi.a().a(str, 40, (Bundle) null, (IBleResponse) null);
                return;
            default:
                return;
        }
    }

    public static void c(String str, final String str2) {
        BluetoothLog.e(String.format("renameBleDevice for %s, name = %s", new Object[]{str, str2}));
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            final BleDevice d = BLEDeviceManager.d(str);
            if (d != null && !TextUtils.isEmpty(d.did)) {
                MiioManager.a().a((Device) d, str2, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                    /* renamed from: a */
                    public void onSuccess(Void voidR) {
                        BluetoothLog.c(String.format("onSuccess", new Object[0]));
                        d.name = str2;
                    }

                    public void onFailure(Error error) {
                        BluetoothLog.c(String.format("onFailure %s", new Object[]{error}));
                    }
                });
            }
            BleCacheUtils.a(str, str2);
            BLEDeviceManager.b(true);
        }
    }

    public static void d(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            BluetoothLog.e(String.format("setBleDeviceSubtitle for %s, subtitle = %s", new Object[]{str, str2}));
            BleCacheUtils.e(str, str2);
            BLEDeviceManager.b(true);
        }
    }

    public static void d(final String str) {
        if (!CommonApplication.isCurrentHotStart()) {
            CommonApplication.getGlobalHandler().postDelayed(new Runnable() {
                public void run() {
                    Fresco.getImagePipeline().prefetchToDiskCache(ImageRequestBuilder.newBuilderWithSource(Uri.parse(str)).setResizeOptions(new ResizeOptions(540, 960)).build(), (Object) null);
                }
            }, 1000);
        }
    }

    public static void e(String str, String str2) {
        BleDevice d;
        BluetoothLog.c(String.format("notifyDeviceBinded: mac = %s, token = %s", new Object[]{XMStringUtils.i(str), XMStringUtils.i(str2)}));
        if (!TextUtils.isEmpty(str) && (d = BLEDeviceManager.d(str)) != null) {
            if (!TextUtils.isEmpty(str2)) {
                BleCacheUtils.h(str, str2);
            }
            if (!d.k() && d.i()) {
                SmartHomeDeviceManager.a().p(str);
                BLEDeviceManager.b(true);
            }
        }
    }

    public static byte[] a(String str, int i) {
        return BLECipher.a(BleCacheUtils.b(str), i);
    }

    public static byte[] b(String str, int i) {
        return BLECipher.b(BleCacheUtils.b(str), i);
    }

    public static void a(String str, int i, final Response.BleResponse<Integer> bleResponse) {
        Bundle bundle = new Bundle();
        bundle.putInt(IBluetoothService.S, i);
        CoreApi.a().a(str, 14, bundle, (IBleResponse) new BluetoothResponse() {
            public void onResponse(int i, Bundle bundle) throws RemoteException {
                bleResponse.onResponse(i, Integer.valueOf(bundle.getInt(IBluetoothService.S, 23)));
            }
        });
    }
}
