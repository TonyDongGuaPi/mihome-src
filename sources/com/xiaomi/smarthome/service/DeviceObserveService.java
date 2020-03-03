package com.xiaomi.smarthome.service;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.DownloadManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.usage.UsageEvents;
import android.app.usage.UsageStatsManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import com.coloros.mcssdk.PushManager;
import com.google.android.exoplayer2.C;
import com.mi.global.bbs.utils.Constants;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.SmartHomeConfig;
import com.xiaomi.smarthome.SmartHomeMainActivity;
import com.xiaomi.smarthome.app.startup.StartupCheckList;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.net.Crypto;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.core.server.internal.plugin.NotificationChannelCreator;
import com.xiaomi.smarthome.core.server.internal.plugin.util.FileUtils;
import com.xiaomi.smarthome.device.ApDeviceManager;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.JsonParser;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.location.LocationPermissionDialogHelper;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.library.common.util.MessageHandlerThread;
import com.xiaomi.smarthome.library.common.util.PreferenceUtils;
import com.xiaomi.smarthome.library.common.util.SharePrefsManager;
import com.xiaomi.smarthome.miui.FindDeviceDialogActivity;
import com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider;
import com.xiaomi.smarthome.smartconfig.stage.ConfigStage;
import com.xiaomi.smarthome.stat.STAT;
import com.xiaomi.smarthome.wificonfig.WifiDeviceFinder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DeviceObserveService extends BroadcastReceiver {
    public static final String FIND_NEW_DEVICE_DIALOG = "find_new_device_dialog";
    public static final String SHORT_URL = "https://static.home.mi.com/app/image/get/file/";

    /* renamed from: a  reason: collision with root package name */
    private static final String f22047a = "DeviceObserveService";
    private static final int b = 1;
    private static final int c = 2;
    private static final int d = 3;
    private static final int e = 4;
    private static final String f = "notified_device";
    private static final String g = "ignore_device";
    private static final int h = 3600000;
    private static final String i = "\\w+-\\w+-\\w+_mi(?:ap|bt|dev)\\w{4}";
    private static DeviceObserveService t;
    private HashSet<String> j = null;
    private HashMap<String, Long> k = null;
    private HashSet<String> l = new HashSet<>();
    private WifiManager m;
    private DownloadManager n;
    private NotificationManager o;
    private HandlerThread p;
    private Handler q = null;
    /* access modifiers changed from: private */
    public boolean r = true;
    private boolean s = false;

    public void onCreate() {
    }

    public static DeviceObserveService getInstance() {
        if (t == null) {
            t = new DeviceObserveService();
            t.onCreate();
        }
        return t;
    }

    public void onStartCommand() {
        if (a()) {
            Home m2 = HomeManager.a().m();
            if (m2 == null || !m2.p()) {
                Log.e(f22047a, "home is shared");
                return;
            }
            Handler handler = this.q;
            if (handler != null) {
                handler.sendEmptyMessage(1);
                return;
            }
            return;
        }
        Log.e(f22047a, "cta not passed");
    }

    private boolean a() {
        if (!StartupCheckList.b()) {
            return false;
        }
        if (this.q != null) {
            return true;
        }
        this.m = (WifiManager) SHApplication.getAppContext().getApplicationContext().getSystemService("wifi");
        this.o = (NotificationManager) SHApplication.getAppContext().getSystemService(PushManager.MESSAGE_TYPE_NOTI);
        this.n = (DownloadManager) SHApplication.getAppContext().getSystemService(Constants.TitleMenu.MENU_DOWNLOAD);
        this.s = a(SHApplication.getAppContext());
        if (this.p != null) {
            return true;
        }
        this.p = new MessageHandlerThread("device_observer");
        this.p.start();
        this.q = new Handler(this.p.getLooper()) {
            public void handleMessage(Message message) {
                boolean z = false;
                switch (message.what) {
                    case 1:
                        DeviceObserveService.this.e();
                        return;
                    case 2:
                        DeviceObserveService.this.a((String) message.obj);
                        return;
                    case 3:
                        boolean unused = DeviceObserveService.this.r = false;
                        return;
                    case 4:
                        Log.e(DeviceObserveService.f22047a, "on msg smart config stop");
                        DeviceObserveService deviceObserveService = DeviceObserveService.this;
                        String str = (String) message.obj;
                        if (message.arg1 == 0) {
                            z = true;
                        }
                        deviceObserveService.a(str, z);
                        return;
                    default:
                        return;
                }
            }
        };
        return true;
    }

    public void onIgnoreSSID(String str) {
        Handler handler;
        if (a() && (handler = this.q) != null) {
            handler.sendMessage(this.q.obtainMessage(2, str));
        }
    }

    public void onStartSmartConfig(String str) {
        Handler handler;
        if (a() && (handler = this.q) != null) {
            handler.sendMessage(this.q.obtainMessage(3, str));
        }
    }

    public void onFinishSmartConfig(String str, boolean z) {
        Handler handler;
        if (a() && (handler = this.q) != null) {
            handler.sendMessage(this.q.obtainMessage(4, z ^ true ? 1 : 0, 0, str));
        }
    }

    private void b() {
        if (this.k == null) {
            this.j = new HashSet<>();
            this.k = new HashMap<>();
            String a2 = PreferenceUtils.a(f, "");
            String a3 = PreferenceUtils.a(g, "");
            try {
                JSONObject jSONObject = new JSONObject(a2);
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    this.k.put(next, Long.valueOf(jSONObject.getLong(next)));
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            try {
                JSONArray jSONArray = new JSONArray(a3);
                f();
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    this.j.add(jSONArray.optString(i2));
                }
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
    }

    public String getLauncherTopApp(Context context) {
        if (Build.VERSION.SDK_INT < 21) {
            return "";
        }
        long currentTimeMillis = System.currentTimeMillis();
        String str = "";
        UsageEvents.Event event = new UsageEvents.Event();
        UsageEvents queryEvents = ((UsageStatsManager) context.getSystemService("usagestats")).queryEvents(currentTimeMillis - 120000, currentTimeMillis);
        while (queryEvents.hasNextEvent()) {
            queryEvents.getNextEvent(event);
            if (event.getEventType() == 1) {
                str = event.getPackageName();
            }
        }
        return !TextUtils.isEmpty(str) ? str : "";
    }

    private void c() {
        JSONObject jSONObject = new JSONObject();
        for (String next : this.k.keySet()) {
            try {
                jSONObject.put(next, this.k.get(next));
            } catch (JSONException unused) {
            }
        }
        PreferenceUtils.b(f, jSONObject.toString());
    }

    private void d() {
        JSONArray jSONArray = new JSONArray();
        f();
        Iterator<String> it = this.j.iterator();
        while (it.hasNext()) {
            jSONArray.put(it.next());
        }
        PreferenceUtils.b(g, jSONArray.toString());
    }

    /* access modifiers changed from: private */
    public void e() {
        Log.e(f22047a, "start process scan result");
        if (!this.r) {
            Log.e(f22047a, "doing smart config");
        }
        if (SharePrefsManager.b(SHApplication.getAppContext(), SmartHomeConfig.i, SmartHomeConfig.m, false) || SHApplication.getForegroundActivityCount() != 0) {
            b();
            if (LocationPermissionDialogHelper.a((Activity) null, false, R.string.open_location_permission)) {
                try {
                    List<ScanResult> scanResults = this.m.getScanResults();
                    if (scanResults != null) {
                        Log.e(f22047a, "scan result size - " + scanResults.size());
                        ArrayList arrayList = new ArrayList();
                        f();
                        for (ScanResult next : scanResults) {
                            if (Pattern.matches(i, next.SSID) && !this.j.contains(next.SSID)) {
                                arrayList.add(next);
                            }
                        }
                        if (arrayList.size() > 0) {
                            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                                final ScanResult scanResult = (ScanResult) arrayList.get(i2);
                                if (DeviceFactory.e(scanResult)) {
                                    Intent intent = new Intent(ApDeviceManager.b);
                                    ArrayList arrayList2 = new ArrayList();
                                    arrayList2.add(scanResult);
                                    intent.putParcelableArrayListExtra(ApDeviceManager.c, arrayList2);
                                    LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(intent);
                                }
                                if (this.q != null) {
                                    this.q.postDelayed(new Runnable() {
                                        public void run() {
                                            DeviceObserveService.this.a(scanResult);
                                        }
                                    }, (long) (i2 * 3000));
                                }
                            }
                            return;
                        }
                        Log.e(f22047a, "find no result");
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        } else {
            Log.e(f22047a, "disable auto discovery");
        }
    }

    private void f() {
        if (this.j == null) {
            this.j = new HashSet<>();
        }
    }

    private void g() {
        if (this.l == null) {
            this.l = new HashSet<>();
        }
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        this.r = true;
        synchronized (this) {
            f();
            this.j.add(str);
        }
        d();
        e();
    }

    /* access modifiers changed from: private */
    public void a(String str, boolean z) {
        this.r = true;
        synchronized (this) {
            g();
            this.l.add(str);
        }
        e();
    }

    /* access modifiers changed from: private */
    public void a(final ScanResult scanResult) {
        CoreApi.a().a(SHApplication.getAppContext(), (CoreApi.IsCoreReadyCallback) new CoreApi.IsCoreReadyCallback() {
            public void onCoreReady() {
                ArrayList arrayList = new ArrayList();
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("models", DeviceFactory.i(scanResult.SSID));
                    jSONObject.put("device", Build.DEVICE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                arrayList.add(new KeyValuePair("data", jSONObject.toString()));
                CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/public/get_product_config").b((List<KeyValuePair>) arrayList).a(), new JsonParser<String>() {
                    /* renamed from: a */
                    public String parse(JSONObject jSONObject) throws JSONException {
                        return jSONObject.toString();
                    }
                }, Crypto.NONE, new AsyncCallback<String, Error>() {
                    /* renamed from: a */
                    public void onSuccess(String str) {
                        String str2;
                        try {
                            JSONArray optJSONArray = new JSONObject(str).optJSONArray("configs");
                            int i = 0;
                            while (i < optJSONArray.length()) {
                                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                                String optString = optJSONObject.optString("model");
                                String optString2 = optJSONObject.optString("name");
                                int optInt = optJSONObject.optInt("wifi_rssi");
                                if (Build.PRODUCT.equals("gemini") || Build.PRODUCT.equals("lithium")) {
                                    optInt += 5;
                                }
                                if (scanResult.level >= optInt || SmartHomeMainActivity.mIsActivityResumed) {
                                    JSONObject optJSONObject2 = optJSONObject.optJSONObject("neg_screen");
                                    if (optJSONObject2 != null) {
                                        String optString3 = optJSONObject2.optString("short_480");
                                        String optString4 = optJSONObject2.optString("neg_480");
                                        String optString5 = optJSONObject2.optString("short_video");
                                        String optString6 = optJSONObject2.optString("neg_video");
                                        if (!TextUtils.isEmpty(optString5)) {
                                            long unused = DeviceObserveService.this.b(optString5);
                                        }
                                        DeviceObserveService deviceObserveService = DeviceObserveService.this;
                                        if (TextUtils.isEmpty(optString3)) {
                                            str2 = optString4;
                                        } else {
                                            str2 = "https://static.home.mi.com/app/image/get/file/" + optString3;
                                        }
                                        if (!TextUtils.isEmpty(optString5)) {
                                            optString6 = "https://static.home.mi.com/app/image/get/file/" + optString5;
                                        }
                                        deviceObserveService.a(optString2, optString, str2, optString6, scanResult);
                                    }
                                    i++;
                                } else {
                                    return;
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    public void onFailure(Error error) {
                        Log.e("FindDevice", "ERROR");
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public long b(String str) {
        if (FileUtils.b(SHApplication.getAppContext(), str).exists()) {
            return -1;
        }
        try {
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse("https://static.home.mi.com/app/image/get/file/" + str));
            request.setDestinationInExternalFilesDir(SHApplication.getAppContext(), Environment.DIRECTORY_MOVIES, str);
            request.setNotificationVisibility(2);
            request.setAllowedNetworkTypes(2);
            return this.n.enqueue(request);
        } catch (Exception e2) {
            e2.printStackTrace();
            return -2;
        }
    }

    /* access modifiers changed from: private */
    public void a(String str, String str2, String str3, String str4, ScanResult scanResult) {
        boolean z;
        String str5 = str;
        String str6 = str2;
        ScanResult scanResult2 = scanResult;
        if (!SharePrefsManager.b(SHApplication.getAppContext(), SmartHomeConfig.i, SmartHomeConfig.m, false) && SHApplication.getForegroundActivityCount() == 0) {
            Log.e(f22047a, "disable auto discovery");
        } else if (this.o != null) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(SHApplication.getAppContext());
            builder.setSmallIcon(R.drawable.notify_icon);
            builder.setContentTitle(SHApplication.getAppContext().getResources().getString(R.string.wifi_scan_new_device_title));
            builder.setWhen(System.currentTimeMillis());
            builder.setAutoCancel(true);
            Intent a2 = ConfigStage.a(SHApplication.getAppContext(), scanResult2, str6, (String) null, (String) null);
            if (a2 != null) {
                a2.putExtra("category", 1);
            }
            PendingIntent activity = PendingIntent.getActivity(SHApplication.getAppContext(), scanResult2.SSID.hashCode(), a2, 134217728);
            StringBuilder sb = new StringBuilder();
            sb.append("current - ");
            sb.append(scanResult2.frequency);
            sb.append(", ");
            sb.append(FindDeviceDialogActivity.sResumed);
            sb.append(", ");
            sb.append(SHApplication.getForegroundActivityCount() == 0);
            sb.append(", ");
            sb.append(SmartHomeMainActivity.mIsActivityResumed);
            sb.append(" enableScan:");
            sb.append(this.r);
            sb.append("  SSID");
            sb.append(scanResult2.SSID);
            Log.e(f22047a, sb.toString());
            if (SHApplication.getForegroundActivityCount() == 0 || SmartHomeMainActivity.mIsActivityResumed) {
                if (this.k.containsKey(scanResult2.SSID) && System.currentTimeMillis() - this.k.get(scanResult2.SSID).longValue() < 3600000) {
                    Log.e(f22047a, "mNotifiedSSIDs containsKey  last show time:" + this.k.get(scanResult2.SSID));
                    return;
                } else if (FindDeviceDialogActivity.sResumed) {
                    Log.e(f22047a, "FindDeviceDialogActivity.sResumed");
                    return;
                } else if (!this.r) {
                    Log.e(f22047a, "enableScan is false");
                    return;
                } else {
                    if (DeviceFactory.e(scanResult)) {
                        Iterator<ScanResult> it = ApDeviceManager.a().c().iterator();
                        while (true) {
                            if (it.hasNext()) {
                                if (it.next().SSID.equals(scanResult2.SSID)) {
                                    z = true;
                                    break;
                                }
                            } else {
                                z = false;
                                break;
                            }
                        }
                        if (!z) {
                            Log.e(f22047a, "getDiscoveredUnconnectDevice not find " + scanResult2.SSID);
                            return;
                        }
                    }
                    Intent intent = new Intent(SHApplication.getAppContext(), FindDeviceDialogActivity.class);
                    if (SHApplication.getApplication().isAppForeground()) {
                        intent.addFlags(8388608);
                    }
                    intent.addFlags(536870912);
                    intent.addFlags(4194304);
                    intent.addFlags(C.ENCODING_PCM_MU_LAW);
                    intent.putExtra("find_device", scanResult2);
                    intent.putExtra("device_name", str5);
                    intent.putExtra("model", str6);
                    intent.putExtra("image_url", str3);
                    intent.putExtra("video_url", str4);
                    intent.putExtra(SmartConfigDataProvider.N, SHApplication.getForegroundActivityCount() == 0);
                    intent.putExtra("timestamp", System.currentTimeMillis());
                    builder.setFullScreenIntent(PendingIntent.getActivity(SHApplication.getAppContext(), R.string.app_name, intent, 134217728), true);
                    String quantityString = SHApplication.getAppContext().getResources().getQuantityString(R.plurals.wifi_scan_new_device, 1, new Object[]{1, str5});
                    builder.setContentTitle(SHApplication.getAppContext().getResources().getString(R.string.wifi_scan_new_device_title));
                    builder.setContentText(quantityString);
                    builder.setContentIntent(activity);
                    builder.setPriority(1);
                    int hashCode = scanResult2.SSID.hashCode();
                    if (Build.VERSION.SDK_INT >= 26) {
                        builder.setChannelId(NotificationChannelCreator.b(this.o));
                    }
                    this.o.notify(hashCode, builder.build());
                    synchronized (this) {
                        g();
                        this.l.remove(scanResult2.SSID);
                        this.k.put(scanResult2.SSID, Long.valueOf(System.currentTimeMillis()));
                    }
                    Log.e(f22047a, "start notify - " + hashCode);
                    STAT.e.b(str6);
                    c();
                }
            }
            WifiDeviceFinder.d().b(scanResult2);
        }
    }

    private boolean a(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return false;
        }
        String packageName = context.getPackageName();
        for (ActivityManager.RunningAppProcessInfo next : runningAppProcesses) {
            if (next.importance == 100 && next.processName.equals(packageName)) {
                return true;
            }
        }
        return false;
    }

    public boolean isValid(ScanResult scanResult) {
        synchronized (this) {
            if (this.j != null && this.j.contains(scanResult.SSID)) {
                return false;
            }
            if (this.l == null || !this.l.contains(scanResult.SSID)) {
                return true;
            }
            return false;
        }
    }

    public void onReceive(Context context, Intent intent) {
        getInstance().onStartCommand();
    }

    public void notifiyNotified(String str) {
        if (this.k != null) {
            this.k.put(str, Long.valueOf(System.currentTimeMillis()));
            c();
        }
    }
}
