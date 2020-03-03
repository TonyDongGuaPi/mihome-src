package com.xiaomi.infrared.utils;

import android.text.TextUtils;
import com.xiaomi.infrared.bean.IRKeyValue;
import com.xiaomi.smarthome.frame.core.CoreApi;
import java.util.Locale;
import org.json.JSONObject;

public class CommUtil {
    public static boolean a() {
        Locale I = CoreApi.a().I();
        if (I == null) {
            I = Locale.getDefault();
        }
        return TextUtils.equals(Locale.SIMPLIFIED_CHINESE.getCountry(), I.getCountry()) && TextUtils.equals(Locale.SIMPLIFIED_CHINESE.getLanguage(), I.getLanguage());
    }

    public static String a(IRKeyValue iRKeyValue) {
        String e = iRKeyValue.e();
        String d = iRKeyValue.d();
        if (TextUtils.isEmpty(e)) {
            return d;
        }
        if (TextUtils.isEmpty(d)) {
            return e;
        }
        return a() ? d : e;
    }

    public static boolean b() {
        String c = c();
        if (c != null) {
            return c.trim().equals("zh-CN") || c.trim().equals("zh-TW");
        }
        return false;
    }

    private static String c() {
        Locale locale = Locale.getDefault();
        String language = locale.getLanguage();
        String lowerCase = locale.getCountry().toLowerCase();
        if ("zh".equals(language)) {
            if ("cn".equals(lowerCase)) {
                return "zh-CN";
            }
            if ("tw".equals(lowerCase)) {
                return "zh-TW";
            }
            return language;
        } else if (!"pt".equals(language)) {
            return language;
        } else {
            if (TtmlNode.TAG_BR.equals(lowerCase)) {
                return "pt-BR";
            }
            return "pt".equals(lowerCase) ? "pt-PT" : language;
        }
    }

    public static String a(JSONObject jSONObject, String str) {
        Object opt = jSONObject.opt(str);
        return (opt == JSONObject.NULL || opt == null) ? "" : String.valueOf(opt);
    }
}
