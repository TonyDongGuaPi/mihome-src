package com.mi.global.bbs.utils;

import android.content.res.Configuration;
import android.telephony.TelephonyManager;
import android.webkit.CookieManager;
import com.mi.global.bbs.BBSApplication;
import com.mi.global.bbs.manager.Region;
import com.mi.global.bbs.model.LocalModel;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.bbs.utils.Utils;
import com.mi.log.LogUtil;
import com.miui.tsmclient.util.StringUtils;
import com.taobao.weex.annotation.JSMethod;
import com.xiaomi.miot.support.monitor.core.BaseInfo;
import com.xiaomi.smarthome.framework.api.model.AreaPropInfo;
import com.xiaomi.stat.d;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;

public class LocaleHelper {
    public static String APP_LOCALE = null;
    public static String APP_LOCAL_NAME = null;
    public static final String ARAB_LANG = "ar";
    public static final String ARAB_LOCAL = "arab";
    public static final String ARAB_NAME = "العالم العربي ";
    public static final String[] COUNTRIES_ARAB = {"ps", "jo", "sy", "sa", "iq", "ye", "kw", "qa", "bh", "om", "dz", "ma", BaseInfo.KEY_THREAD_NAME, "ly", AreaPropInfo.j, "eg", "mr"};
    public static final String[][] COUNTRIES_MAP = {new String[]{"in", "en", "India"}, new String[]{"id", "id", "Indonesia"}, new String[]{Region.RU, Region.RU, "Россия"}, new String[]{"sg", "en", "Singapore"}, new String[]{"vn", "vi", "Việt Nam"}, new String[]{d.U, d.U, "Polska"}, new String[]{"ua", "uk", "Україна"}, new String[]{"mx", "mx", "México"}, new String[]{"th", "th", "ประเทศไทย"}, new String[]{d.ad, "en", "United Arab Emirates"}, new String[]{"us", "en", "United States"}, new String[]{ARAB_LOCAL, "ar", "الوطن العربي"}, new String[]{"tw", "zh", "台灣"}, new String[]{"hk", "zh", "香港"}, new String[]{"oc", "en", "Other countries"}, new String[]{d.u, d.u, "España"}, new String[]{"ph", "en", "Philippines"}, new String[]{"fr", "fr", "Français"}, new String[]{"it", "it", "Italia"}, new String[]{"bd", "en", "Bangladesh"}, new String[]{"gb", "en", "United Kingdom"}};
    public static final String TAG = "applocale";
    public static final List<String> europeSites = Arrays.asList(new String[]{d.u, d.U, "it", "fr", "gb"});

    public static void initLocaleAndLanguage() {
        APP_LOCALE = Utils.Preference.getStringPref(BBSApplication.getInstance(), "pref_locale", (String) null);
        LanguageHelper.APP_LANG = Utils.Preference.getStringPref(BBSApplication.getInstance(), "pref_lang", (String) null);
        APP_LOCAL_NAME = Utils.Preference.getStringPref(BBSApplication.getInstance(), Constants.App.PREF_LOCALE_NAME, (String) null);
        if (APP_LOCALE == null || LanguageHelper.APP_LANG == null) {
            detectLocale();
            if (APP_LOCALE == null || LanguageHelper.APP_LANG == null) {
                APP_LOCALE = "in";
                LanguageHelper.APP_LANG = "en";
                APP_LOCAL_NAME = "India";
            }
            Utils.Preference.setStringPref(BBSApplication.getInstance(), "pref_locale", APP_LOCALE);
            Utils.Preference.setStringPref(BBSApplication.getInstance(), "pref_lang", LanguageHelper.APP_LANG);
            Utils.Preference.setStringPref(BBSApplication.getInstance(), Constants.App.PREF_LOCALE_NAME, APP_LOCAL_NAME);
        }
    }

    public static String getCurrentLocal() {
        String str = null;
        try {
            str = (String) Class.forName("miui.os.Build").getMethod("getRegion", new Class[0]).invoke((Object) null, new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str == null ? BBSApplication.getInstance().getResources().getConfiguration().locale.getCountry() : str;
    }

    private static void detectLocale() {
        String currentLocal = getCurrentLocal();
        for (String[] strArr : COUNTRIES_MAP) {
            if (strArr[0].equalsIgnoreCase(currentLocal)) {
                APP_LOCALE = strArr[0];
                LanguageHelper.APP_LANG = strArr[1];
                APP_LOCAL_NAME = strArr[2];
            }
        }
        for (String str : COUNTRIES_ARAB) {
            if (str.equalsIgnoreCase(currentLocal)) {
                APP_LOCALE = str;
                LanguageHelper.APP_LANG = "ar";
                APP_LOCAL_NAME = ARAB_NAME;
            }
        }
        if (APP_LOCALE == null || LanguageHelper.APP_LANG == null) {
            currentLocal = ((TelephonyManager) BBSApplication.getInstance().getSystemService("phone")).getSimCountryIso();
            for (String[] strArr2 : COUNTRIES_MAP) {
                if (strArr2[0].equalsIgnoreCase(currentLocal)) {
                    APP_LOCALE = strArr2[0];
                    LanguageHelper.APP_LANG = strArr2[1];
                    APP_LOCAL_NAME = strArr2[2];
                }
            }
            for (String str2 : COUNTRIES_ARAB) {
                if (str2.equalsIgnoreCase(currentLocal)) {
                    APP_LOCALE = str2;
                    LanguageHelper.APP_LANG = "ar";
                    APP_LOCAL_NAME = ARAB_NAME;
                }
            }
        }
        Utils.Preference.setStringPref(BBSApplication.getInstance(), Constants.App.PREF_LOCALE_ORIGIN, currentLocal);
    }

    public static void setLocale() {
        try {
            Locale locale = new Locale(LanguageHelper.APP_LANG, APP_LOCALE);
            Configuration configuration = BBSApplication.getInstance().getBaseContext().getResources().getConfiguration();
            Locale.setDefault(locale);
            configuration.locale = locale;
            LogUtil.b("applocale", " set locale to :" + locale.toString());
            BBSApplication.getInstance().getBaseContext().getResources().updateConfiguration(configuration, BBSApplication.getInstance().getBaseContext().getResources().getDisplayMetrics());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String[][] getCountriesMap() {
        String[][] strArr;
        try {
            JSONArray jSONArray = new JSONArray(Utils.Preference.getStringPref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_OPEN_COUNTRY, ""));
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                String string = jSONArray.getString(i);
                for (String[] strArr2 : COUNTRIES_MAP) {
                    if (strArr2[0].equalsIgnoreCase(string)) {
                        LocalModel localModel = new LocalModel();
                        localModel.local = strArr2[0];
                        localModel.lang = strArr2[1];
                        localModel.name = strArr2[2];
                        arrayList.add(localModel);
                    }
                }
            }
            strArr = (String[][]) Array.newInstance(String.class, new int[]{arrayList.size(), 3});
            int i2 = 0;
            while (i2 < arrayList.size()) {
                try {
                    LocalModel localModel2 = (LocalModel) arrayList.get(i2);
                    strArr[i2][0] = localModel2.local;
                    strArr[i2][1] = localModel2.lang;
                    strArr[i2][2] = localModel2.name;
                    i2++;
                } catch (Exception e) {
                    e = e;
                    e.printStackTrace();
                    return strArr;
                }
            }
        } catch (Exception e2) {
            e = e2;
            strArr = COUNTRIES_MAP;
            e.printStackTrace();
            return strArr;
        }
        return strArr;
    }

    public static boolean switchLocale(int i) {
        String[][] countriesMap = getCountriesMap();
        if (countriesMap == null || i >= COUNTRIES_MAP.length) {
            return false;
        }
        if (countriesMap[i][0].equals(APP_LOCALE) && countriesMap[i][1].equals(LanguageHelper.APP_LANG)) {
            return false;
        }
        try {
            CookieManager.getInstance().removeAllCookie();
        } catch (Exception e) {
            e.printStackTrace();
        }
        APP_LOCALE = countriesMap[i][0];
        LanguageHelper.APP_LANG = countriesMap[i][1];
        APP_LOCAL_NAME = countriesMap[i][2];
        Utils.Preference.setStringPref(BBSApplication.getInstance(), "pref_locale", APP_LOCALE);
        Utils.Preference.setStringPref(BBSApplication.getInstance(), "pref_lang", LanguageHelper.APP_LANG);
        Utils.Preference.setStringPref(BBSApplication.getInstance(), Constants.App.PREF_LOCALE_NAME, APP_LOCAL_NAME);
        return true;
    }

    public static String getLocaleLang() {
        if (isIndonesia()) {
            return "in_ID";
        }
        return LanguageHelper.APP_LANG + JSMethod.NOT_SET + APP_LOCALE.toUpperCase();
    }

    public static String getCurrencyFormat(String str) {
        String str2 = "#,###.00";
        if (APP_LOCALE.equalsIgnoreCase("in")) {
            str2 = "#,###";
        } else if (APP_LOCALE.equalsIgnoreCase("tw")) {
            str2 = "#,### ";
        }
        return new DecimalFormat(str2).format(Double.parseDouble(str));
    }

    public static String getDateTimeFormat() {
        return isIndia() ? "dd MMM, KK:mm a" : "yyyy-MM-dd HH:mm";
    }

    public static String getDateTime(Long l) {
        String format = new SimpleDateFormat(getDateTimeFormat()).format(new Date(l.longValue()));
        return isIndia() ? format.replace("pm", "PM").replace("am", "AM") : format;
    }

    public static String getOnlyDateFormat() {
        return isIndia() ? "dd MMM" : StringUtils.EXPECT_DATE_FORMAT;
    }

    public static String getOnlyDateStr(Long l) {
        String format = new SimpleDateFormat(getOnlyDateFormat()).format(new Date(l.longValue()));
        return isIndia() ? format.replace("pm", "PM").replace("am", "AM") : format;
    }

    public static boolean isIndia() {
        return "in".equalsIgnoreCase(APP_LOCALE);
    }

    public static boolean isVietnam() {
        return "vn".equalsIgnoreCase(APP_LOCALE);
    }

    public static boolean isTaiwan() {
        return "tw".equalsIgnoreCase(APP_LOCALE);
    }

    public static boolean isIndonesia() {
        return "id".equalsIgnoreCase(APP_LOCALE);
    }

    public static boolean isAr() {
        return ARAB_LOCAL.equalsIgnoreCase(APP_LOCALE);
    }

    public static boolean isEs() {
        return d.u.equalsIgnoreCase(APP_LOCALE);
    }

    public static boolean isPl() {
        return d.U.equalsIgnoreCase(APP_LOCALE);
    }

    public static boolean isIT() {
        return "it".equalsIgnoreCase(APP_LOCALE);
    }

    public static boolean isRu() {
        return Region.RU.equalsIgnoreCase(APP_LOCALE);
    }

    public static boolean isUa() {
        return "ua".equalsIgnoreCase(APP_LOCALE);
    }

    public static boolean isFr() {
        return "fr".equalsIgnoreCase(APP_LOCALE);
    }

    public static boolean isBD() {
        return "bd".equalsIgnoreCase(APP_LOCALE);
    }

    public static boolean isGB() {
        return "gb".equalsIgnoreCase(APP_LOCALE);
    }

    public static boolean hasColumn() {
        return APP_LOCALE.equalsIgnoreCase("id") || APP_LOCALE.equalsIgnoreCase("in") || APP_LOCALE.equalsIgnoreCase("vn") || APP_LOCALE.equalsIgnoreCase(Region.RU) || APP_LOCALE.equalsIgnoreCase("ua") || APP_LOCALE.equalsIgnoreCase("mx") || APP_LOCALE.equalsIgnoreCase(d.u);
    }

    public static String getLocalCookie() {
        String str = APP_LOCALE;
        for (String equalsIgnoreCase : COUNTRIES_ARAB) {
            if (APP_LOCALE.equalsIgnoreCase(equalsIgnoreCase)) {
                str = ARAB_LOCAL;
            }
        }
        return str;
    }

    public static boolean isEruope() {
        return europeSites.contains(getLocalCookie());
    }
}
