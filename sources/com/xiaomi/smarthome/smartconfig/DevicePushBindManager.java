package com.xiaomi.smarthome.smartconfig;

import android.app.DownloadManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.exoplayer2.C;
import com.mi.global.bbs.utils.Constants;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.net.Crypto;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.core.server.internal.plugin.util.FileUtils;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.miui.FindDeviceDialogActivity;
import com.xiaomi.smarthome.printer.DeviceImageApi;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public enum DevicePushBindManager {
    instance;
    
    private static final String BLACK_LIST = "black_list";
    public static final String EXTRA_BINDWIFI = "bind_aiot_wifi";
    public static final String TAG = "DevicePushBindManager";
    private Map<String, PushBindEntity> cacheDevice;
    private boolean mHomeShow;
    private List<OnScanListener> mListener;
    private boolean mNeedScan;

    public interface OnScanListener {
        void a(String str, PushBindEntity pushBindEntity);

        void b(String str, PushBindEntity pushBindEntity);
    }

    public void checkDeviceWifi() {
        if (!CoreApi.a().D()) {
            CoreApi.a().a(SHApplication.getAppContext(), (CoreApi.IsCoreReadyCallback) new CoreApi.IsCoreReadyCallback() {
                /* access modifiers changed from: private */
                public static /* synthetic */ JSONObject a(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }

                public void onCoreReady() {
                    CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/aiot/get_iot_devices").b((List<KeyValuePair>) new ArrayList()).a(), $$Lambda$DevicePushBindManager$1$fkmOZmfzlZk4_jVu4N5lN6LWzKg.INSTANCE, Crypto.RC4, new AsyncCallback<JSONObject, Error>() {
                        /* renamed from: a */
                        public void onSuccess(final JSONObject jSONObject) {
                            Log.i(DevicePushBindManager.TAG, "checkDeviceWifi onSuccess:" + jSONObject);
                            CommonApplication.getGlobalWorkerHandler().post(new Runnable() {
                                public void run() {
                                    try {
                                        JSONArray optJSONArray = jSONObject.optJSONArray("result");
                                        JSONObject[] jSONObjectArr = null;
                                        if (optJSONArray != null && optJSONArray.length() > 0) {
                                            jSONObjectArr = new JSONObject[optJSONArray.length()];
                                            for (int i = 0; i < jSONObjectArr.length; i++) {
                                                jSONObjectArr[i] = optJSONArray.optJSONObject(i);
                                            }
                                        }
                                        DevicePushBindManager.this.notifyFindDevice(jSONObjectArr);
                                    } catch (Exception e) {
                                        Log.e(DevicePushBindManager.TAG, "checkDeviceWifi onSuccess Exception", e);
                                    }
                                }
                            });
                        }

                        public void onFailure(Error error) {
                            Log.i(DevicePushBindManager.TAG, "checkDeviceWifi onFailure:" + error);
                        }
                    });
                }
            });
        }
    }

    public void notifyFindDevice(JSONObject... jSONObjectArr) {
        HashMap hashMap = new HashMap();
        if (jSONObjectArr == null) {
            notifyFindChange(hashMap);
            Log.i(TAG, "notifyFindDevice no data");
            return;
        }
        ArrayList arrayList = new ArrayList();
        Set<String> stringSet = SHApplication.getAppContext().getSharedPreferences(TAG, 0).getStringSet(BLACK_LIST, (Set) null);
        for (JSONObject jSONObject : jSONObjectArr) {
            String optString = jSONObject.optString("bssid");
            PushBindEntity a2 = PushBindEntity.a(jSONObject);
            if (a2 != null && (stringSet == null || !stringSet.contains(optString))) {
                hashMap.put(optString, a2);
            }
        }
        notifyFindChange(hashMap);
        for (Map.Entry entry : hashMap.entrySet()) {
            String str = (String) entry.getKey();
            PushBindEntity pushBindEntity = this.cacheDevice.get(str);
            if (pushBindEntity == null || (!pushBindEntity.c() && pushBindEntity.e() && !pushBindEntity.d())) {
                PushBindEntity pushBindEntity2 = (PushBindEntity) entry.getValue();
                this.cacheDevice.put(str, pushBindEntity2);
                if (arrayList.size() == 0) {
                    arrayList.add(pushBindEntity2);
                }
                if (pushBindEntity == null) {
                    dispatchListener(true, str, pushBindEntity2);
                }
            }
        }
        requestImage(arrayList);
        Log.i(TAG, "notifyFindDevice entities:" + arrayList + " JSONObject:" + Arrays.toString(jSONObjectArr));
    }

    private void notifyFindChange(Map<String, PushBindEntity> map) {
        for (Map.Entry next : this.cacheDevice.entrySet()) {
            String str = (String) next.getKey();
            PushBindEntity pushBindEntity = (PushBindEntity) next.getValue();
            boolean containsKey = map.containsKey(str);
            if ((containsKey && !pushBindEntity.e()) || (!containsKey && pushBindEntity.e())) {
                pushBindEntity.a(containsKey);
                dispatchListener(containsKey, str, pushBindEntity);
            }
        }
    }

    private void requestImage(final ArrayList<PushBindEntity> arrayList) {
        if (arrayList.size() != 0) {
            if (arrayList.size() == 1) {
                DeviceImageApi.a(arrayList.get(0).f22312a.o(), new AsyncCallback<DeviceImageApi.DeviceImageEntity, Error>() {
                    /* renamed from: a */
                    public void onSuccess(DeviceImageApi.DeviceImageEntity deviceImageEntity) {
                        if (!TextUtils.isEmpty(deviceImageEntity.b)) {
                            DevicePushBindManager.this.startCacheVideoFile(deviceImageEntity.b);
                        }
                        DevicePushBindManager.this.startFindDevice(arrayList);
                    }

                    public void onFailure(Error error) {
                        DevicePushBindManager.this.startFindDevice(arrayList);
                        Log.e(DevicePushBindManager.TAG, "get_product_config error:" + error);
                    }
                });
            } else {
                startFindDevice(arrayList);
            }
        }
    }

    private void dispatchListener(boolean z, String str, PushBindEntity pushBindEntity) {
        if (pushBindEntity == null) {
            LogUtilGrey.a(TAG, "dispatchListener bssid is null," + str);
            return;
        }
        LogUtilGrey.a(TAG, "dispatchListener bssid:" + str + " find:" + z);
        for (int size = this.mListener.size() + -1; size >= 0; size--) {
            if (z) {
                this.mListener.get(size).a(str, pushBindEntity);
            } else {
                this.mListener.get(size).b(str, pushBindEntity);
            }
        }
    }

    /* access modifiers changed from: private */
    public void startCacheVideoFile(String str) {
        if (!FileUtils.b(SHApplication.getAppContext(), str).exists()) {
            try {
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse("https://static.home.mi.com/app/image/get/file/" + str));
                request.setDestinationInExternalFilesDir(SHApplication.getAppContext(), Environment.DIRECTORY_MOVIES, str);
                request.setNotificationVisibility(2);
                request.setAllowedNetworkTypes(2);
                ((DownloadManager) SHApplication.getAppContext().getSystemService(Constants.TitleMenu.MENU_DOWNLOAD)).enqueue(request);
            } catch (Exception e) {
                Log.e(TAG, "startCacheVideoFile", e);
            }
        }
    }

    /* access modifiers changed from: private */
    public void startFindDevice(ArrayList<PushBindEntity> arrayList) {
        SHApplication application = SHApplication.getApplication();
        Log.i(TAG, "startFindActivity appForeground:" + this.mHomeShow);
        if (isHomeShow()) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                PushBindEntity pushBindEntity = arrayList.get(size);
                if (pushBindEntity.c() || !pushBindEntity.e() || pushBindEntity.d()) {
                    arrayList.remove(size);
                } else {
                    pushBindEntity.a();
                }
            }
            if (arrayList.size() > 0) {
                Intent intent = new Intent(SHApplication.getAppContext(), FindDeviceDialogActivity.class);
                intent.addFlags(536870912);
                intent.addFlags(4194304);
                intent.addFlags(C.ENCODING_PCM_MU_LAW);
                intent.putParcelableArrayListExtra(EXTRA_BINDWIFI, arrayList);
                intent.putExtra("strategy_id", 16);
                application.startActivity(intent);
                return;
            }
            Log.i(TAG, "receive push all showed:" + arrayList);
            return;
        }
        Log.i(TAG, "receive push background json:" + arrayList);
    }

    public void onResume() {
        this.mHomeShow = true;
        if (this.mNeedScan) {
            checkDeviceWifi();
            this.mNeedScan = false;
        }
    }

    public void needScan() {
        this.mNeedScan = true;
    }

    public void onPause() {
        this.mHomeShow = false;
    }

    public boolean isHomeShow() {
        return this.mHomeShow;
    }

    public void bind(String str) {
        if (str != null) {
            String upperCase = str.toUpperCase(Locale.ENGLISH);
            String lowerCase = str.toLowerCase(Locale.ENGLISH);
            PushBindEntity pushBindEntity = this.cacheDevice.get(lowerCase);
            if (pushBindEntity == null) {
                pushBindEntity = this.cacheDevice.get(upperCase);
            }
            if (pushBindEntity != null) {
                pushBindEntity.b();
            }
            dispatchListener(false, lowerCase, pushBindEntity);
        }
    }

    public void clear() {
        this.cacheDevice.clear();
    }

    public Map<String, PushBindEntity> getCache() {
        HashMap hashMap = new HashMap();
        for (Map.Entry next : this.cacheDevice.entrySet()) {
            if (!((PushBindEntity) next.getValue()).d() && ((PushBindEntity) next.getValue()).e()) {
                hashMap.put(next.getKey(), next.getValue());
            }
        }
        return hashMap;
    }

    public void registScanListener(OnScanListener onScanListener) {
        if (onScanListener != null && !this.mListener.contains(onScanListener)) {
            this.mListener.add(onScanListener);
        }
    }

    public void unregistScanListener(OnScanListener onScanListener) {
        this.mListener.remove(onScanListener);
    }

    public void addBlackList(final PushBindEntity pushBindEntity) {
        if (pushBindEntity != null) {
            SHApplication.getGlobalWorkerHandler().post(new Runnable() {
                public void run() {
                    SharedPreferences sharedPreferences = SHApplication.getAppContext().getSharedPreferences(DevicePushBindManager.TAG, 0);
                    Set stringSet = sharedPreferences.getStringSet(DevicePushBindManager.BLACK_LIST, (Set) null);
                    if (stringSet == null) {
                        stringSet = new HashSet();
                    }
                    stringSet.add(pushBindEntity.d);
                    sharedPreferences.edit().putStringSet(DevicePushBindManager.BLACK_LIST, stringSet).apply();
                }
            });
        }
    }
}
