package com.xiaomi.smarthome.miio.page.smartlife;

import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.family.api.RemoteFamilyApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.api.UserConfig;
import com.xiaomi.smarthome.framework.api.UserConfigApi;
import com.xiaomi.smarthome.library.common.util.AsyncTaskUtils;
import com.xiaomi.smarthome.library.common.util.SharePrefsManager;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SmartLifeItem implements Parcelable {
    public static final Parcelable.Creator<SmartLifeItem> CREATOR = new Parcelable.Creator<SmartLifeItem>() {
        /* renamed from: a */
        public SmartLifeItem createFromParcel(Parcel parcel) {
            return new SmartLifeItem(parcel);
        }

        /* renamed from: a */
        public SmartLifeItem[] newArray(int i) {
            return new SmartLifeItem[i];
        }
    };
    public static final int c = 1;
    public static final int d = 2;
    public static final int e = 3;
    public static final int f = 4;
    public static final int g = 5;
    public static final int h = 6;
    public static final int i = 7;
    public static final int j = 8;
    public static final int k = 9;
    public static final int l = 10;
    public static final int m = 11;
    public static final int n = 12;
    public static final int o = 13;
    public static final int p = 14;
    public static final int q = 15;
    public static final int r = 16;
    public static final int s = 17;
    public static final int t = 18;
    public static final int u = 19;

    /* renamed from: a  reason: collision with root package name */
    public int f19933a;
    public String b;

    public static int a(int i2) {
        switch (i2) {
            case 1:
                return R.string.device_airpurifier;
            case 2:
                return R.string.device_airconditioner;
            case 3:
                return R.string.device_cold_fan;
            case 4:
                return R.string.device_dvd;
            case 5:
                return R.string.device_egg_cooker;
            case 6:
                return R.string.device_elec_heating;
            case 7:
                return R.string.device_electrickettle;
            case 8:
                return R.string.device_fan;
            case 9:
                return R.string.device_heater;
            case 10:
                return R.string.device_humidifier;
            case 11:
                return R.string.device_juicer;
            case 12:
                return R.string.device_light;
            case 13:
                return R.string.device_microwaveoven;
            case 14:
                return R.string.device_cooker;
            case 15:
                return R.string.device_socket;
            case 16:
                return R.string.device_sound;
            case 17:
                return R.string.device_stb;
            case 18:
                return R.string.device_tv;
            case 19:
                return R.string.device_water_purifier;
            default:
                return 0;
        }
    }

    public static int b(int i2) {
        switch (i2) {
            case 1:
                return R.drawable.std_lifeline_icon_aircleaner;
            case 2:
                return R.drawable.std_lifeline_icon_airconditioner;
            case 3:
                return R.drawable.std_lifeline_icon_coldfan;
            case 4:
                return R.drawable.std_lifeline_icon_dvd;
            case 5:
                return R.drawable.std_lifeline_icon_eggcooker;
            case 6:
                return R.drawable.std_lifeline_icon_electricheating;
            case 7:
                return R.drawable.std_lifeline_icon_electrickettle;
            case 8:
                return R.drawable.std_lifeline_icon_fan;
            case 9:
                return R.drawable.std_lifeline_icon_heater;
            case 10:
                return R.drawable.std_lifeline_icon_humidifier;
            case 11:
                return R.drawable.std_lifeline_icon_juicer;
            case 12:
                return R.drawable.std_lifeline_icon_light;
            case 13:
                return R.drawable.std_lifeline_icon_microwaveoven;
            case 14:
                return R.drawable.std_lifeline_icon_ricecooker;
            case 15:
                return R.drawable.std_lifeline_icon_socket;
            case 16:
                return R.drawable.std_lifeline_icon_sound;
            case 17:
                return R.drawable.std_lifeline_icon_stb;
            case 18:
                return R.drawable.std_lifeline_icon_tv;
            case 19:
                return R.drawable.std_lifeline_icon_waterpurifier;
            default:
                return 0;
        }
    }

    public static String c(int i2) {
        return "";
    }

    public int describeContents() {
        return 0;
    }

    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("key", this.f19933a);
            jSONObject.put("name", this.b);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public static SmartLifeItem a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        SmartLifeItem smartLifeItem = new SmartLifeItem();
        smartLifeItem.f19933a = jSONObject.optInt("key");
        smartLifeItem.b = jSONObject.optString("name");
        return smartLifeItem;
    }

    public SmartLifeItem(Parcel parcel) {
        this.f19933a = parcel.readInt();
        this.b = parcel.readString();
    }

    public SmartLifeItem() {
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.f19933a);
        parcel.writeString(this.b);
    }

    public static String a(Resources resources, int i2) {
        int a2 = a(i2);
        if (a2 == 0) {
            return "";
        }
        return resources.getString(R.string.smart_life_intro_tip4, new Object[]{resources.getString(a2)});
    }

    public static int[] d(int i2) {
        switch (i2) {
            case 1:
                return new int[]{R.string.smart_life_intro_tip1};
            case 2:
                return new int[]{R.string.smart_life_intro_tip2};
            case 3:
                return new int[]{R.string.smart_life_intro_tip1};
            case 4:
                return new int[]{R.string.smart_life_intro_tip2};
            case 5:
                return new int[]{R.string.smart_life_intro_tip1};
            case 6:
                return new int[]{R.string.smart_life_intro_tip1};
            case 7:
                return new int[]{R.string.smart_life_intro_tip1};
            case 8:
                return new int[]{R.string.smart_life_intro_tip1};
            case 9:
                return new int[]{R.string.smart_life_intro_tip1};
            case 10:
                return new int[]{R.string.smart_life_intro_tip1};
            case 11:
                return new int[]{R.string.smart_life_intro_tip1};
            case 12:
                return new int[]{R.string.smart_life_intro_tip1};
            case 13:
                return new int[]{R.string.smart_life_intro_tip1};
            case 14:
                return new int[]{R.string.smart_life_intro_tip1};
            case 15:
                return new int[]{R.string.smart_life_intro_tip1};
            case 16:
                return new int[]{R.string.smart_life_intro_tip1};
            case 17:
                return new int[]{R.string.smart_life_intro_tip1, R.string.smart_life_intro_tip2};
            case 18:
                return new int[]{R.string.smart_life_intro_tip1, R.string.smart_life_intro_tip2};
            case 19:
                return new int[]{R.string.smart_life_intro_tip1};
            default:
                return null;
        }
    }

    public static void b() {
        final String c2 = SharePrefsManager.c(SHApplication.getAppContext(), "smart_life", "smart_life_not_login_devices", "");
        if (!TextUtils.isEmpty(c2)) {
            AsyncTaskUtils.a(new AsyncTask<Void, Void, Void>() {
                /* access modifiers changed from: protected */
                /* renamed from: a */
                public Void doInBackground(Void... voidArr) {
                    try {
                        JSONArray jSONArray = new JSONArray(c2);
                        UserConfig userConfig = new UserConfig();
                        userConfig.B = 0;
                        userConfig.C = "3";
                        userConfig.D = new ArrayList<>();
                        long currentTimeMillis = System.currentTimeMillis();
                        ArrayList<UserConfig.UserConfigAttr> arrayList = userConfig.D;
                        arrayList.add(new UserConfig.UserConfigAttr("time", "" + currentTimeMillis));
                        userConfig.D.add(new UserConfig.UserConfigAttr("value", jSONArray.toString()));
                        UserConfigApi.a().a(SHApplication.getAppContext(), userConfig, (AsyncCallback<Void, Error>) null);
                        SharePrefsManager.a(SHApplication.getAppContext(), "smart_life", "smart_life_not_login_devices", "");
                        return null;
                    } catch (JSONException unused) {
                        return null;
                    }
                }
            }, new Void[0]);
        }
    }

    public static void a(final Context context, String str, final AsyncCallback<String, Error> asyncCallback) {
        RemoteFamilyApi.a().l(context, str, new AsyncCallback<String, Error>() {
            /* renamed from: a */
            public void onSuccess(String str) {
                SharePrefsManager.a(context, "smart_life", "smart_life_pictures", str);
                if (asyncCallback != null) {
                    asyncCallback.onSuccess(str);
                }
            }

            public void onFailure(Error error) {
                if (asyncCallback != null) {
                    asyncCallback.onFailure(error);
                }
            }
        });
    }
}
