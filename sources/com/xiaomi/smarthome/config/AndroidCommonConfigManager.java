package com.xiaomi.smarthome.config;

import android.support.annotation.NonNull;
import com.miui.tsmclient.net.TSMAuthContants;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.config.model.DeviceRoomConfig;
import com.xiaomi.smarthome.config.model.LvMiSupportLocalInfo;
import com.xiaomi.smarthome.config.model.SupportBleGatewayFirmwareVersion;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.device.authorization.page.DeviceAuthMasterListActivity;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.library.http.Error;
import com.xiaomi.smarthome.library.http.HttpApi;
import com.xiaomi.smarthome.library.http.Request;
import com.xiaomi.smarthome.library.http.async.AsyncHandler;
import com.xiaomi.smarthome.mibrain.model.MiBrainConfigInfo;
import com.xiaomi.smarthome.setting.ServerRouteUtil;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import okhttp3.Call;
import okhttp3.Response;
import org.json.JSONObject;

public class AndroidCommonConfigManager {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static final String f13927a = "AndroidCommonConfigManager";
    private static volatile AndroidCommonConfigManager j = null;
    private static final int l = 5000;
    /* access modifiers changed from: private */
    public List<MiBrainConfigInfo> b = new ArrayList();
    /* access modifiers changed from: private */
    public List<MiBrainConfigInfo> c = new ArrayList();
    /* access modifiers changed from: private */
    public List<MiBrainConfigInfo> d = new ArrayList();
    /* access modifiers changed from: private */
    public List<LvMiSupportLocalInfo> e = new ArrayList();
    /* access modifiers changed from: private */
    public List<SupportBleGatewayFirmwareVersion> f = new ArrayList();
    /* access modifiers changed from: private */
    public boolean g = false;
    /* access modifiers changed from: private */
    public boolean h = false;
    /* access modifiers changed from: private */
    public DeviceRoomConfig i;
    /* access modifiers changed from: private */
    public int k;
    /* access modifiers changed from: private */
    public AtomicBoolean m = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public long n = 0;

    private AndroidCommonConfigManager() {
    }

    public static AndroidCommonConfigManager a() {
        if (j == null) {
            synchronized (AndroidCommonConfigManager.class) {
                if (j == null) {
                    j = new AndroidCommonConfigManager();
                }
            }
        }
        return j;
    }

    public List<MiBrainConfigInfo> b() {
        return this.b;
    }

    public List<MiBrainConfigInfo> c() {
        return this.c;
    }

    public List<MiBrainConfigInfo> d() {
        return this.d;
    }

    public List<LvMiSupportLocalInfo> e() {
        return this.e;
    }

    public List<SupportBleGatewayFirmwareVersion> f() {
        return this.f;
    }

    public boolean g() {
        return this.g;
    }

    public boolean h() {
        return this.h;
    }

    public void i() {
        if (Math.abs(System.currentTimeMillis() - this.n) >= 5000 && !this.m.getAndSet(true)) {
            ArrayList arrayList = new ArrayList();
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(TSMAuthContants.PARAM_LANGUAGE, "zh_CN");
                StringBuilder sb = new StringBuilder();
                sb.append("android_common_config");
                sb.append(GlobalSetting.E ? "_preview" : "");
                jSONObject.put("name", sb.toString());
                jSONObject.put("version", "1");
            } catch (Exception unused) {
                this.m.set(false);
            }
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            Request request = null;
            try {
                request = new Request.Builder().a("GET").b(a(jSONObject)).a();
            } catch (UnsupportedEncodingException e2) {
                this.m.set(false);
                e2.printStackTrace();
            }
            if (request == null) {
                this.m.set(false);
            } else {
                HttpApi.a(request, (AsyncHandler) new AsyncHandler() {
                    public void onFailure(Error error, Exception exc, Response response) {
                    }

                    public void onSuccess(Object obj, Response response) {
                    }

                    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v10, resolved type: java.lang.Object} */
                    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v17, resolved type: org.json.JSONObject} */
                    /* JADX WARNING: Multi-variable type inference failed */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void processResponse(okhttp3.Response r7) {
                        /*
                            r6 = this;
                            r0 = 0
                            okhttp3.ResponseBody r7 = r7.body()     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            java.lang.String r7 = r7.string()     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            r1.<init>(r7)     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            java.lang.String r7 = "result"
                            boolean r7 = r1.isNull(r7)     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            if (r7 == 0) goto L_0x0020
                            com.xiaomi.smarthome.config.AndroidCommonConfigManager r7 = com.xiaomi.smarthome.config.AndroidCommonConfigManager.this     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            java.util.concurrent.atomic.AtomicBoolean r7 = r7.m     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            r7.set(r0)     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            return
                        L_0x0020:
                            java.lang.String r7 = "result"
                            org.json.JSONObject r7 = r1.optJSONObject(r7)     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            java.lang.String r1 = com.xiaomi.smarthome.config.AndroidCommonConfigManager.f13927a     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            r2.<init>()     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            java.lang.String r3 = "getRemoteConfig  result"
                            r2.append(r3)     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            java.lang.String r3 = r7.toString()     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            r2.append(r3)     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            com.xiaomi.smarthome.miio.Miio.b(r1, r2)     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            java.lang.String r1 = "content"
                            boolean r1 = r7.isNull(r1)     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            if (r1 == 0) goto L_0x0054
                            com.xiaomi.smarthome.config.AndroidCommonConfigManager r7 = com.xiaomi.smarthome.config.AndroidCommonConfigManager.this     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            java.util.concurrent.atomic.AtomicBoolean r7 = r7.m     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            r7.set(r0)     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            return
                        L_0x0054:
                            r1 = 0
                            java.lang.String r2 = "content"
                            java.lang.Object r7 = r7.get(r2)     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            boolean r2 = r7 instanceof org.json.JSONObject     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            if (r2 == 0) goto L_0x0063
                            r1 = r7
                            org.json.JSONObject r1 = (org.json.JSONObject) r1     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            goto L_0x006e
                        L_0x0063:
                            boolean r2 = r7 instanceof java.lang.String     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            if (r2 == 0) goto L_0x006e
                            org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            java.lang.String r7 = (java.lang.String) r7     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            r1.<init>(r7)     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                        L_0x006e:
                            java.lang.String r7 = "android_audio_brain_config"
                            boolean r7 = r1.isNull(r7)     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            if (r7 != 0) goto L_0x0087
                            java.lang.String r7 = "android_audio_brain_config"
                            org.json.JSONArray r7 = r1.optJSONArray(r7)     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            if (r7 == 0) goto L_0x0087
                            com.xiaomi.smarthome.config.AndroidCommonConfigManager r2 = com.xiaomi.smarthome.config.AndroidCommonConfigManager.this     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            java.util.List r7 = com.xiaomi.smarthome.mibrain.model.MiBrainConfigInfo.a(r7)     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            java.util.List unused = r2.b = r7     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                        L_0x0087:
                            java.lang.String r7 = "android_audio_brain_setting_config"
                            boolean r7 = r1.isNull(r7)     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            if (r7 != 0) goto L_0x00a0
                            java.lang.String r7 = "android_audio_brain_setting_config"
                            org.json.JSONArray r7 = r1.optJSONArray(r7)     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            if (r7 == 0) goto L_0x00a0
                            com.xiaomi.smarthome.config.AndroidCommonConfigManager r2 = com.xiaomi.smarthome.config.AndroidCommonConfigManager.this     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            java.util.List r7 = com.xiaomi.smarthome.mibrain.model.MiBrainConfigInfo.a(r7)     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            java.util.List unused = r2.c = r7     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                        L_0x00a0:
                            java.lang.String r7 = "android_audio_micro_config"
                            boolean r7 = r1.isNull(r7)     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            if (r7 != 0) goto L_0x00b9
                            java.lang.String r7 = "android_audio_micro_config"
                            org.json.JSONArray r7 = r1.optJSONArray(r7)     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            if (r7 == 0) goto L_0x00b9
                            com.xiaomi.smarthome.config.AndroidCommonConfigManager r2 = com.xiaomi.smarthome.config.AndroidCommonConfigManager.this     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            java.util.List r7 = com.xiaomi.smarthome.mibrain.model.MiBrainConfigInfo.a(r7)     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            java.util.List unused = r2.d = r7     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                        L_0x00b9:
                            java.lang.String r7 = "lumi_device_support_local"
                            boolean r7 = r1.isNull(r7)     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            if (r7 != 0) goto L_0x00d2
                            java.lang.String r7 = "lumi_device_support_local"
                            org.json.JSONArray r7 = r1.optJSONArray(r7)     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            if (r7 == 0) goto L_0x00d2
                            com.xiaomi.smarthome.config.AndroidCommonConfigManager r2 = com.xiaomi.smarthome.config.AndroidCommonConfigManager.this     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            java.util.List r7 = com.xiaomi.smarthome.config.model.LvMiSupportLocalInfo.a(r7)     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            java.util.List unused = r2.e = r7     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                        L_0x00d2:
                            java.lang.String r7 = "support_ble_gatway_firmware_version_config"
                            boolean r7 = r1.isNull(r7)     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            if (r7 != 0) goto L_0x00f7
                            java.lang.String r7 = "support_ble_gatway_firmware_version_config"
                            org.json.JSONArray r7 = r1.optJSONArray(r7)     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            if (r7 == 0) goto L_0x00ec
                            com.xiaomi.smarthome.config.AndroidCommonConfigManager r2 = com.xiaomi.smarthome.config.AndroidCommonConfigManager.this     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            java.util.List r7 = com.xiaomi.smarthome.config.model.SupportBleGatewayFirmwareVersion.a(r7)     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            java.util.List unused = r2.f = r7     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            goto L_0x0101
                        L_0x00ec:
                            com.xiaomi.smarthome.config.AndroidCommonConfigManager r7 = com.xiaomi.smarthome.config.AndroidCommonConfigManager.this     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            r2.<init>()     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            java.util.List unused = r7.f = r2     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            goto L_0x0101
                        L_0x00f7:
                            com.xiaomi.smarthome.config.AndroidCommonConfigManager r7 = com.xiaomi.smarthome.config.AndroidCommonConfigManager.this     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            r2.<init>()     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            java.util.List unused = r7.f = r2     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                        L_0x0101:
                            java.lang.String r7 = "android_mini_program_switch"
                            boolean r7 = r1.isNull(r7)     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            if (r7 != 0) goto L_0x0123
                            java.lang.String r7 = "android_mini_program_switch"
                            org.json.JSONObject r7 = r1.optJSONObject(r7)     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            if (r7 == 0) goto L_0x011d
                            com.xiaomi.smarthome.config.AndroidCommonConfigManager r2 = com.xiaomi.smarthome.config.AndroidCommonConfigManager.this     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            java.lang.String r3 = "status"
                            boolean r7 = r7.optBoolean(r3)     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            boolean unused = r2.g = r7     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            goto L_0x0128
                        L_0x011d:
                            com.xiaomi.smarthome.config.AndroidCommonConfigManager r7 = com.xiaomi.smarthome.config.AndroidCommonConfigManager.this     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            boolean unused = r7.g = r0     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            goto L_0x0128
                        L_0x0123:
                            com.xiaomi.smarthome.config.AndroidCommonConfigManager r7 = com.xiaomi.smarthome.config.AndroidCommonConfigManager.this     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            boolean unused = r7.g = r0     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                        L_0x0128:
                            java.lang.String r7 = "main_device_room_tab_config"
                            boolean r7 = r1.isNull(r7)     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            r2 = 3
                            r3 = 2
                            if (r7 != 0) goto L_0x015c
                            java.lang.String r7 = "main_device_room_tab_config"
                            org.json.JSONObject r7 = r1.optJSONObject(r7)     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            if (r7 == 0) goto L_0x0151
                            java.lang.String r4 = "click_icon_op"
                            int r4 = r7.optInt(r4, r3)     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            java.lang.String r5 = "icon_cnt"
                            int r7 = r7.optInt(r5, r2)     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            com.xiaomi.smarthome.config.AndroidCommonConfigManager r2 = com.xiaomi.smarthome.config.AndroidCommonConfigManager.this     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            com.xiaomi.smarthome.config.model.DeviceRoomConfig r5 = new com.xiaomi.smarthome.config.model.DeviceRoomConfig     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            r5.<init>(r4, r7)     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            com.xiaomi.smarthome.config.model.DeviceRoomConfig unused = r2.i = r5     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            goto L_0x0166
                        L_0x0151:
                            com.xiaomi.smarthome.config.AndroidCommonConfigManager r7 = com.xiaomi.smarthome.config.AndroidCommonConfigManager.this     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            com.xiaomi.smarthome.config.model.DeviceRoomConfig r4 = new com.xiaomi.smarthome.config.model.DeviceRoomConfig     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            r4.<init>(r3, r2)     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            com.xiaomi.smarthome.config.model.DeviceRoomConfig unused = r7.i = r4     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            goto L_0x0166
                        L_0x015c:
                            com.xiaomi.smarthome.config.AndroidCommonConfigManager r7 = com.xiaomi.smarthome.config.AndroidCommonConfigManager.this     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            com.xiaomi.smarthome.config.model.DeviceRoomConfig r4 = new com.xiaomi.smarthome.config.model.DeviceRoomConfig     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            r4.<init>(r3, r2)     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            com.xiaomi.smarthome.config.model.DeviceRoomConfig unused = r7.i = r4     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                        L_0x0166:
                            com.xiaomi.smarthome.config.AndroidCommonConfigManager r7 = com.xiaomi.smarthome.config.AndroidCommonConfigManager.this     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            java.lang.String r2 = "enable_scene_operation"
                            int r2 = r1.optInt(r2, r0)     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            r4 = 1
                            if (r2 != r4) goto L_0x0172
                            goto L_0x0173
                        L_0x0172:
                            r4 = 0
                        L_0x0173:
                            boolean unused = r7.h = r4     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            java.lang.String r7 = "voice_device_auth_mask_models"
                            boolean r7 = r1.has(r7)     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            if (r7 == 0) goto L_0x0197
                            java.lang.String r7 = "voice_device_auth_mask_models"
                            org.json.JSONArray r7 = r1.optJSONArray(r7)     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            r2 = 0
                        L_0x0185:
                            int r4 = r7.length()     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            if (r2 >= r4) goto L_0x0197
                            java.util.Set<java.lang.String> r4 = com.xiaomi.smarthome.device.authorization.page.DeviceAuthMasterListActivity.denyModels     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            java.lang.String r5 = r7.optString(r2)     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            r4.add(r5)     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            int r2 = r2 + 1
                            goto L_0x0185
                        L_0x0197:
                            com.xiaomi.smarthome.config.AndroidCommonConfigManager r7 = com.xiaomi.smarthome.config.AndroidCommonConfigManager.this     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            java.lang.String r2 = "family_add_device_redpoint_count"
                            int r1 = r1.optInt(r2, r3)     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            int unused = r7.k = r1     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            com.xiaomi.smarthome.config.AndroidCommonConfigManager r7 = com.xiaomi.smarthome.config.AndroidCommonConfigManager.this     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            java.util.concurrent.atomic.AtomicBoolean r7 = r7.m     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            r7.set(r0)     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            com.xiaomi.smarthome.config.AndroidCommonConfigManager r7 = com.xiaomi.smarthome.config.AndroidCommonConfigManager.this     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            long r1 = java.lang.System.currentTimeMillis()     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            long unused = r7.n = r1     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            java.lang.String r7 = com.xiaomi.smarthome.config.AndroidCommonConfigManager.f13927a     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            r1.<init>()     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            java.lang.String r2 = "getRemoteConfig  miBrainHelpConfigInfoList.size:"
                            r1.append(r2)     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            com.xiaomi.smarthome.config.AndroidCommonConfigManager r2 = com.xiaomi.smarthome.config.AndroidCommonConfigManager.this     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            java.util.List r2 = r2.b     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            int r2 = r2.size()     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            r1.append(r2)     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            java.lang.String r2 = "   miBrainSettingShowLaboratoryList.size:  "
                            r1.append(r2)     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            com.xiaomi.smarthome.config.AndroidCommonConfigManager r2 = com.xiaomi.smarthome.config.AndroidCommonConfigManager.this     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            java.util.List r2 = r2.c     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            int r2 = r2.size()     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            r1.append(r2)     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            java.lang.String r2 = "   mMicroHelpConfigInfoList.size:  "
                            r1.append(r2)     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            com.xiaomi.smarthome.config.AndroidCommonConfigManager r2 = com.xiaomi.smarthome.config.AndroidCommonConfigManager.this     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            java.util.List r2 = r2.d     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            int r2 = r2.size()     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            r1.append(r2)     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            java.lang.String r2 = "   isShowMiniProgram:  "
                            r1.append(r2)     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            com.xiaomi.smarthome.config.AndroidCommonConfigManager r2 = com.xiaomi.smarthome.config.AndroidCommonConfigManager.this     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            boolean r2 = r2.g     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            r1.append(r2)     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            java.lang.String r1 = r1.toString()     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            com.xiaomi.smarthome.miio.Miio.b(r7, r1)     // Catch:{ IOException -> 0x0225, JSONException -> 0x0217, Exception -> 0x0209 }
                            goto L_0x0232
                        L_0x0209:
                            r7 = move-exception
                            com.xiaomi.smarthome.config.AndroidCommonConfigManager r1 = com.xiaomi.smarthome.config.AndroidCommonConfigManager.this
                            java.util.concurrent.atomic.AtomicBoolean r1 = r1.m
                            r1.set(r0)
                            r7.printStackTrace()
                            goto L_0x0232
                        L_0x0217:
                            r7 = move-exception
                            com.xiaomi.smarthome.config.AndroidCommonConfigManager r1 = com.xiaomi.smarthome.config.AndroidCommonConfigManager.this
                            java.util.concurrent.atomic.AtomicBoolean r1 = r1.m
                            r1.set(r0)
                            r7.printStackTrace()
                            goto L_0x0232
                        L_0x0225:
                            r7 = move-exception
                            com.xiaomi.smarthome.config.AndroidCommonConfigManager r1 = com.xiaomi.smarthome.config.AndroidCommonConfigManager.this
                            java.util.concurrent.atomic.AtomicBoolean r1 = r1.m
                            r1.set(r0)
                            r7.printStackTrace()
                        L_0x0232:
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.config.AndroidCommonConfigManager.AnonymousClass1.processResponse(okhttp3.Response):void");
                    }

                    public void processFailure(Call call, IOException iOException) {
                        AndroidCommonConfigManager.this.m.set(false);
                        long unused = AndroidCommonConfigManager.this.n = System.currentTimeMillis();
                        DeviceAuthMasterListActivity.addLocalDeny();
                    }
                });
            }
        }
    }

    @NonNull
    private String a(JSONObject jSONObject) throws UnsupportedEncodingException {
        return ServerRouteUtil.a(SHApplication.getAppContext()) + "/app/service/getappconfig?data=" + URLEncoder.encode(jSONObject.toString(), "UTF-8");
    }

    public DeviceRoomConfig j() {
        return this.i;
    }

    public int k() {
        return this.k;
    }
}
