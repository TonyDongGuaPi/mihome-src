package com.xiaomi.smarthome.device;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.SmartHomeConfig;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.SharePrefsManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class VirtualDeviceManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f15005a = "yeelink.light.virtual";
    Dialog b = null;

    /* access modifiers changed from: package-private */
    public void a(Activity activity, String str) {
    }

    public static boolean a(String str) {
        try {
            List<ModelGroupInfo> a2 = a(new JSONObject(str));
            if (a2 == null) {
                return false;
            }
            if (a2.size() == 0) {
                return false;
            }
            SmartHomeDeviceHelper.a().e(a2);
            return true;
        } catch (JSONException unused) {
            return true;
        }
    }

    public static List<ModelGroupInfo> a(JSONObject jSONObject) {
        JSONArray optJSONArray = jSONObject.optJSONArray("result");
        if (optJSONArray == null || optJSONArray.length() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            ModelGroupInfo modelGroupInfo = new ModelGroupInfo();
            modelGroupInfo.d = optJSONObject.optString("virtualModel");
            modelGroupInfo.b = optJSONObject.optString(MibiConstants.fg);
            modelGroupInfo.f14912a = optJSONObject.optString("name");
            modelGroupInfo.c = null;
            JSONArray optJSONArray2 = optJSONObject.optJSONArray("models");
            if (optJSONArray2 != null && optJSONArray2.length() > 0) {
                modelGroupInfo.c = new String[optJSONArray2.length()];
                for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                    modelGroupInfo.c[i2] = optJSONArray2.optString(i2);
                }
            }
            arrayList.add(modelGroupInfo);
        }
        return arrayList;
    }

    public void a(final BaseActivity baseActivity) {
        if (baseActivity != null) {
            String c = SharePrefsManager.c(SHApplication.getAppContext(), SmartHomeConfig.e, SmartHomeConfig.f, (String) null);
            if (TextUtils.isEmpty(c)) {
                baseActivity.mHandler.postDelayed(new Runnable() {
                    public void run() {
                        VirtualDeviceManager.this.a((Activity) baseActivity, SharePrefsManager.c(SHApplication.getAppContext(), SmartHomeConfig.e, SmartHomeConfig.f, (String) null));
                    }
                }, 1000);
            } else {
                a((Activity) baseActivity, c);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(final Activity activity, final ModelGroupInfo modelGroupInfo) {
        if (activity != null && !activity.isFinishing() && modelGroupInfo != null && !CoreApi.a().D() && !SharePrefsManager.b(SHApplication.getAppContext(), SmartHomeConfig.e, "showed_group_tip", false)) {
            if (this.b == null || !this.b.isShowing()) {
                if (this.b == null) {
                    MLAlertDialog.Builder builder = new MLAlertDialog.Builder(activity);
                    builder.a(false);
                    builder.a((int) R.string.dialog_title_create_yeelight_group);
                    builder.a((int) R.string.dialog_button_create_group, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            SharePrefsManager.a(SHApplication.getAppContext(), SmartHomeConfig.e, "showed_group_tip", true);
                            String[] a2 = SmartHomeDeviceHelper.a().a(modelGroupInfo);
                            Intent intent = new Intent(activity, ModifyGroupActivity.class);
                            intent.putExtra("group_device_ids", a2);
                            intent.putExtra("group_model", "yeelink.light.virtual");
                            activity.startActivity(intent);
                        }
                    });
                    builder.b((int) R.string.know_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            SharePrefsManager.a(SHApplication.getAppContext(), SmartHomeConfig.e, "showed_group_tip", true);
                        }
                    });
                    this.b = builder.b();
                }
                this.b.show();
            }
        }
    }

    public static void a() {
        SHApplication.getGlobalWorkerHandler().post(new Runnable() {
            /* JADX WARNING: Code restructure failed: missing block: B:12:0x006c, code lost:
                if (r3.equals(com.xiaomi.smarthome.device.VirtualDeviceManager.b()) == false) goto L_0x0070;
             */
            /* JADX WARNING: Removed duplicated region for block: B:9:0x004f  */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r7 = this;
                    android.content.Context r0 = com.xiaomi.smarthome.application.SHApplication.getAppContext()
                    java.lang.String r1 = "prefs_virtual_device"
                    java.lang.String r2 = "virtual_device_info_key"
                    r3 = 0
                    java.lang.String r0 = com.xiaomi.smarthome.library.common.util.SharePrefsManager.c(r0, r1, r2, r3)
                    boolean r1 = android.text.TextUtils.isEmpty(r0)
                    r2 = 1
                    if (r1 == 0) goto L_0x0015
                    goto L_0x0070
                L_0x0015:
                    android.content.Context r1 = com.xiaomi.smarthome.application.SHApplication.getAppContext()
                    java.lang.String r3 = "prefs_virtual_device"
                    java.lang.String r4 = "virtual_device_last_check_key"
                    r5 = 0
                    long r3 = com.xiaomi.smarthome.library.common.util.SharePrefsManager.a((android.content.Context) r1, (java.lang.String) r3, (java.lang.String) r4, (long) r5)
                    int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                    if (r1 > 0) goto L_0x0029
                L_0x0027:
                    r1 = 1
                    goto L_0x004d
                L_0x0029:
                    java.text.SimpleDateFormat r1 = new java.text.SimpleDateFormat
                    java.lang.String r5 = "yyyy-MM-dd"
                    r1.<init>(r5)
                    java.util.Date r5 = new java.util.Date
                    r5.<init>(r3)
                    java.lang.String r3 = r1.format(r5)
                    long r4 = java.lang.System.currentTimeMillis()
                    java.util.Date r6 = new java.util.Date
                    r6.<init>(r4)
                    java.lang.String r1 = r1.format(r6)
                    boolean r1 = r3.equals(r1)
                    if (r1 == 0) goto L_0x0027
                    r1 = 0
                L_0x004d:
                    if (r1 != 0) goto L_0x006f
                    android.content.Context r3 = com.xiaomi.smarthome.application.SHApplication.getAppContext()
                    java.lang.String r4 = "prefs_virtual_device"
                    java.lang.String r5 = "virtual_device_last_locale_key"
                    java.lang.String r6 = ""
                    java.lang.String r3 = com.xiaomi.smarthome.library.common.util.SharePrefsManager.c(r3, r4, r5, r6)
                    boolean r4 = android.text.TextUtils.isEmpty(r3)
                    if (r4 == 0) goto L_0x0064
                    goto L_0x0070
                L_0x0064:
                    java.lang.String r4 = com.xiaomi.smarthome.device.VirtualDeviceManager.c()
                    boolean r3 = r3.equals(r4)
                    if (r3 != 0) goto L_0x006f
                    goto L_0x0070
                L_0x006f:
                    r2 = r1
                L_0x0070:
                    if (r2 == 0) goto L_0x0083
                    com.xiaomi.smarthome.device.api.DeviceApi r0 = com.xiaomi.smarthome.device.api.DeviceApi.getInstance()
                    android.content.Context r1 = com.xiaomi.smarthome.application.SHApplication.getAppContext()
                    com.xiaomi.smarthome.device.VirtualDeviceManager$4$1 r2 = new com.xiaomi.smarthome.device.VirtualDeviceManager$4$1
                    r2.<init>()
                    r0.getModelGroupInfoJson(r1, r2)
                    goto L_0x0086
                L_0x0083:
                    com.xiaomi.smarthome.device.VirtualDeviceManager.a((java.lang.String) r0)
                L_0x0086:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.device.VirtualDeviceManager.AnonymousClass4.run():void");
            }
        });
    }

    /* access modifiers changed from: private */
    public static String c() {
        Locale I = CoreApi.a().I();
        if (I == null) {
            I = Locale.getDefault();
        }
        if (I == null) {
            return "";
        }
        return I.getCountry() + "-" + I.getLanguage();
    }
}
