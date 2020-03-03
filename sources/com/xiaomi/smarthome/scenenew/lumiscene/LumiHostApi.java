package com.xiaomi.smarthome.scenenew.lumiscene;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.xiaomi.plugin.core.XmPluginPackage;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.device.api.Parser;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.framework.log.LogUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class LumiHostApi {

    /* renamed from: a  reason: collision with root package name */
    private static final String f21955a = "LumiHostApi";
    /* access modifiers changed from: private */
    public static Handler b = new Handler(Looper.getMainLooper());

    public static List<LmBaseDevice> a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        List<DeviceStat> subDeviceByParentDid = XmPluginHostApi.instance().getSubDeviceByParentDid(str);
        if (subDeviceByParentDid == null || subDeviceByParentDid.size() == 0) {
            return null;
        }
        for (DeviceStat lmBaseDevice : subDeviceByParentDid) {
            arrayList.add(new LmBaseDevice(lmBaseDevice));
        }
        return arrayList;
    }

    public static void a(XmPluginPackage xmPluginPackage, String str, Object obj, JSONObject jSONObject) {
        XmPluginHostApi.instance().addRecord(xmPluginPackage, str, obj, jSONObject);
    }

    public static <T> void a(DeviceRequest deviceRequest, Callback<T> callback, Parser<T> parser) {
        switch (deviceRequest.e) {
            case 0:
                LogUtil.a("lumiscene", deviceRequest.f + ":" + deviceRequest.b() + ":" + deviceRequest.c());
                a(deviceRequest.b(), deviceRequest.f, deviceRequest.k, callback, parser);
                return;
            case 1:
                LogUtil.a("lumiscene", deviceRequest.g + ":" + deviceRequest.b() + ":" + deviceRequest.a());
                if (deviceRequest.j != null) {
                    XmPluginHostApi.instance().callMethod(deviceRequest.a(), deviceRequest.g, deviceRequest.j, a(callback), a(parser));
                    return;
                }
                if (deviceRequest.i != null) {
                    LogUtil.a("lumiscene", deviceRequest.i.toString());
                }
                XmPluginHostApi.instance().callMethod(deviceRequest.a(), deviceRequest.g, deviceRequest.i, a(callback), a(parser));
                return;
            default:
                callback.onFailure(-1, "request type not found");
                return;
        }
    }

    public static <T> void a(String str, String str2, String str3, Callback<T> callback, Parser<T> parser) {
        XmPluginHostApi.instance().callSmartHomeApi(str, str2, str3, a(callback), a(parser));
    }

    public static Locale a(Context context) {
        Locale settingLocale = XmPluginHostApi.instance().getSettingLocale();
        return settingLocale == null ? context.getResources().getConfiguration().locale : settingLocale;
    }

    public static String b(Context context) {
        Locale a2 = a(context);
        return a2.getLanguage() + "-" + a2.getCountry();
    }

    private static <T> Callback<T> a(final Callback<T> callback) {
        if (callback == null) {
            return null;
        }
        return new Callback<T>() {
            public void onSuccess(T t) {
                LumiHostApi.b.post(new Runnable(t) {
                    private final /* synthetic */ Object f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        Callback.this.onSuccess(this.f$1);
                    }
                });
            }

            public void onFailure(int i, String str) {
                LumiHostApi.b.post(new Runnable(i, str) {
                    private final /* synthetic */ int f$1;
                    private final /* synthetic */ String f$2;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                    }

                    public final void run() {
                        Callback.this.onFailure(this.f$1, this.f$2);
                    }
                });
            }
        };
    }

    private static <T> Parser<T> a(Parser<T> parser) {
        if (parser == null) {
            return null;
        }
        return new Parser() {
            public final Object parse(String str) {
                return LumiHostApi.a(Parser.this, str);
            }
        };
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Object a(Parser parser, String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            if (parser != null) {
                return parser.parse(str);
            }
            return null;
        } else if (str.contains("\"result\":")) {
            String optString = new JSONObject(str).optString("result", (String) null);
            if (parser != null) {
                return optString != null ? parser.parse(optString) : parser.parse(str);
            }
            return null;
        } else if (parser != null) {
            return parser.parse(str);
        } else {
            return null;
        }
    }
}
