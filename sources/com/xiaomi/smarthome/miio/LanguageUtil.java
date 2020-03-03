package com.xiaomi.smarthome.miio;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.widget.TextView;
import com.mi.global.bbs.manager.Region;
import com.miui.tsmclient.entity.CardInfo;
import com.org.smarthome_activity.R;
import com.payu.custombrowser.util.CBConstant;
import com.xiaomi.miot.support.monitor.core.BaseInfo;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.process.ProcessUtil;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.library.common.util.SharePrefsManager;
import com.xiaomi.smarthome.library.common.util.StringUtil;
import com.xiaomi.smarthome.notishortcut.SmartNotiApi;
import com.xiaomi.stat.a.l;
import com.xiaomi.stat.d;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class LanguageUtil {
    public static Locale A = new Locale("vi", "VN");
    public static Locale B = new Locale("th", "TH");
    public static Locale C = new Locale("ar", "AE");
    public static final String D = "default_country";
    public static final String E = "default_language";
    public static Locale F = new Locale("in", "ID");
    public static Locale G = new Locale("en", ServerCompact.i);
    public static Locale H = new Locale("pt", "BR");
    public static Locale I = new Locale(BaseInfo.KEY_TIME_RECORD, "TR");
    public static Locale J = new Locale("nl", "NL");
    public static Set<Locale> K = new HashSet();

    /* renamed from: a  reason: collision with root package name */
    public static final String f1553a = "action_locale_changed";
    public static final String b = "language_setting";
    public static final int c = 0;
    public static final int d = 1;
    public static final int e = 2;
    public static final int f = 3;
    public static final int g = 4;
    public static final int h = 5;
    public static final int i = 6;
    public static final int j = 7;
    public static final int k = 8;
    public static final int l = 9;
    public static final int m = 10;
    public static final int n = 11;
    public static final int o = 12;
    public static final int p = 13;
    public static final int q = 14;
    public static final int r = 15;
    public static final int s = 16;
    public static final int t = 17;
    public static final int u = 18;
    public static Locale v = new Locale("zh", ServerCompact.b);
    public static Locale w = new Locale("ko", "KR");
    public static Locale x = new Locale(d.u, ServerCompact.f);
    public static Locale y = new Locale(Region.RU, ServerCompact.d);
    public static Locale z = new Locale(d.U, "PL");

    static {
        K.add(new Locale(l.a.x, "AD"));
        K.add(new Locale(l.a.x, ServerCompact.e));
        K.add(new Locale(l.a.x, ServerCompact.f));
        K.add(new Locale(l.a.x, ServerCompact.h));
        K.add(new Locale("sq", "AL"));
        K.add(new Locale("sq", "MK"));
        K.add(new Locale("de", "AT"));
        K.add(new Locale("sv", "AX"));
        K.add(new Locale("sv", "SE"));
        K.add(new Locale("sv", "FI"));
        K.add(new Locale("hr", "BA"));
        K.add(new Locale(d.Y, "BA"));
        K.add(new Locale("nl", "BE"));
        K.add(new Locale("fr", "BE"));
        K.add(new Locale("de", "BE"));
        K.add(new Locale("en", "BE"));
        K.add(new Locale("bg", "BG"));
        K.add(new Locale("be", "BY"));
        K.add(new Locale("de", "CH"));
        K.add(new Locale("fr", "CH"));
        K.add(new Locale("it", "CH"));
        K.add(new Locale("el", "CY"));
        K.add(new Locale(d.v, "CZ"));
        K.add(new Locale("da", "DK"));
        K.add(new Locale("et", "EE"));
        K.add(new Locale("fi", "FI"));
        K.add(new Locale(d.B, "FO"));
        K.add(new Locale("fr", "GF"));
        K.add(new Locale("da", "GL"));
        K.add(new Locale("kl", "GL"));
        K.add(new Locale("el", "GR"));
        K.add(new Locale("hr", "HR"));
        K.add(new Locale("hu", "HU"));
        K.add(new Locale("ga", "IE"));
        K.add(new Locale("en", "IE"));
        K.add(new Locale("en", "IM"));
        K.add(new Locale("is", "IS"));
        K.add(new Locale("en", "JE"));
        K.add(new Locale("de", "LI"));
        K.add(new Locale(d.T, "LT"));
        K.add(new Locale("fr", "LU"));
        K.add(new Locale("de", "LU"));
        K.add(new Locale("lv", "LV"));
        K.add(new Locale("fr", "MC"));
        K.add(new Locale("ro", "MD"));
        K.add(new Locale(Region.RU, "MD"));
        K.add(new Locale(d.Y, "ME"));
        K.add(new Locale("mk", "MK"));
        K.add(new Locale("mt", "MT"));
        K.add(new Locale("no", Constants.ae));
        K.add(new Locale(CBConstant.NB, Constants.ae));
        K.add(new Locale("nn", Constants.ae));
        K.add(new Locale("ro", "RO"));
        K.add(new Locale(d.Y, "RS"));
        K.add(new Locale("sl", "SI"));
        K.add(new Locale(CBConstant.NB, "SJ"));
        K.add(new Locale(d.af, "SK"));
        K.add(new Locale("it", "SM"));
        K.add(new Locale("uk", "UA"));
        K.add(new Locale("it", "VA"));
        K.add(new Locale("en", "GG"));
        K.add(new Locale("en", ServerCompact.i));
        K.add(new Locale("pt", "PT"));
    }

    public static String a() {
        Locale locale;
        Resources resources = CommonApplication.getAppContext().getResources();
        Locale d2 = ServerCompact.d(CommonApplication.getAppContext());
        if (Build.VERSION.SDK_INT >= 24) {
            locale = Resources.getSystem().getConfiguration().getLocales().get(0);
        } else {
            locale = Locale.getDefault();
        }
        if (SharePrefsManager.b(CommonApplication.getAppContext(), "language_setting", CardInfo.KEY_IS_DEFAULT, true)) {
            return resources.getString(R.string.settings_language_default);
        }
        if (a(G, d2) && K.contains(locale)) {
            return resources.getString(R.string.settings_language_us);
        }
        if (d2 == null) {
            return resources.getString(R.string.settings_language_default);
        }
        if (a(d2, Locale.CHINA)) {
            return resources.getString(R.string.settings_language_zh);
        }
        if (a(d2, Locale.TRADITIONAL_CHINESE)) {
            return resources.getString(R.string.settings_language_zh_rtw);
        }
        if (a(d2, v)) {
            return resources.getString(R.string.settings_language_zh_hk);
        }
        if (a(d2, w)) {
            return resources.getString(R.string.settings_language_ko);
        }
        if (a(d2, x)) {
            return resources.getString(R.string.settings_language_es);
        }
        if (a(d2, y)) {
            return resources.getString(R.string.settings_language_ru);
        }
        if (a(d2, Locale.US)) {
            return resources.getString(R.string.settings_language_us);
        }
        if (a(d2, Locale.FRANCE)) {
            return resources.getString(R.string.settings_language_fr);
        }
        if (a(d2, Locale.ITALY)) {
            return resources.getString(R.string.settings_language_it);
        }
        if (a(d2, Locale.GERMANY)) {
            return resources.getString(R.string.settings_language_de);
        }
        if (a(d2, F)) {
            return resources.getString(R.string.settings_language_id);
        }
        if (a(d2, Locale.JAPAN)) {
            return resources.getString(R.string.settings_language_ja);
        }
        if (a(d2, z)) {
            return resources.getString(R.string.settings_language_pl);
        }
        if (a(d2, B)) {
            return resources.getString(R.string.settings_language_th);
        }
        if (a(d2, A)) {
            return resources.getString(R.string.settings_language_vi);
        }
        if (a(d2, J)) {
            return resources.getString(R.string.settings_language_nl);
        }
        if (a(d2, H)) {
            return resources.getString(R.string.settings_language_pt);
        }
        if (a(d2, I)) {
            return resources.getString(R.string.settings_language_tr);
        }
        return resources.getString(R.string.settings_language_default);
    }

    public static Locale a(int i2, AsyncCallback<Void, Error> asyncCallback) {
        Locale locale;
        if (Build.VERSION.SDK_INT >= 24) {
            locale = Resources.getSystem().getConfiguration().getLocales().get(0);
        } else {
            locale = Locale.getDefault();
        }
        SharePrefsManager.a(CommonApplication.getAppContext(), "language_setting", CardInfo.KEY_IS_DEFAULT, false);
        switch (i2) {
            case 0:
                CoreApi.a().a((Locale) null, asyncCallback);
                a(locale);
                SharePrefsManager.a(CommonApplication.getAppContext(), "language_setting", CardInfo.KEY_IS_DEFAULT, true);
                break;
            case 1:
                CoreApi.a().a(Locale.CHINA, asyncCallback);
                a(Locale.CHINA);
                locale = Locale.CHINA;
                break;
            case 2:
                CoreApi.a().a(Locale.TRADITIONAL_CHINESE, asyncCallback);
                a(Locale.TRADITIONAL_CHINESE);
                locale = Locale.TRADITIONAL_CHINESE;
                break;
            case 3:
                CoreApi.a().a(Locale.US, asyncCallback);
                a(Locale.US);
                locale = Locale.US;
                break;
            case 4:
                CoreApi.a().a(v, asyncCallback);
                a(v);
                locale = v;
                break;
            case 5:
                CoreApi.a().a(w, asyncCallback);
                a(w);
                locale = w;
                break;
            case 6:
                CoreApi.a().a(x, asyncCallback);
                a(x);
                locale = x;
                break;
            case 7:
                CoreApi.a().a(y, asyncCallback);
                a(y);
                locale = y;
                break;
            case 8:
                CoreApi.a().a(Locale.FRANCE, asyncCallback);
                a(Locale.FRANCE);
                locale = Locale.FRANCE;
                break;
            case 9:
                CoreApi.a().a(Locale.ITALY, asyncCallback);
                a(Locale.ITALY);
                locale = Locale.ITALY;
                break;
            case 10:
                CoreApi.a().a(Locale.GERMANY, asyncCallback);
                a(Locale.GERMANY);
                locale = Locale.GERMANY;
                break;
            case 11:
                CoreApi.a().a(F, asyncCallback);
                a(F);
                locale = F;
                break;
            case 12:
                CoreApi.a().a(z, asyncCallback);
                a(z);
                locale = z;
                break;
            case 13:
                CoreApi.a().a(A, asyncCallback);
                a(A);
                locale = A;
                break;
            case 14:
                CoreApi.a().a(Locale.JAPAN, asyncCallback);
                a(Locale.JAPAN);
                locale = Locale.JAPAN;
                break;
            case 15:
                CoreApi.a().a(B, asyncCallback);
                a(B);
                locale = B;
                break;
            case 16:
                CoreApi.a().a(J, asyncCallback);
                a(J);
                locale = J;
                break;
            case 17:
                CoreApi.a().a(H, asyncCallback);
                a(H);
                locale = H;
                break;
            case 18:
                CoreApi.a().a(I, asyncCallback);
                a(I);
                locale = I;
                break;
            default:
                CoreApi.a().a((Locale) null, asyncCallback);
                a(locale);
                SharePrefsManager.a(CommonApplication.getAppContext(), "language_setting", CardInfo.KEY_IS_DEFAULT, true);
                break;
        }
        SmartNotiApi.a(CommonApplication.getAppContext()).a(locale);
        return locale;
    }

    public static void a(Locale locale) {
        if (!K.contains(b()) || !a(G, CommonApplication.getAppContext().getResources().getConfiguration().locale)) {
            boolean b2 = SharePrefsManager.b(CommonApplication.getAppContext(), "language_setting", CardInfo.KEY_IS_DEFAULT, true);
            if (a(G, CommonApplication.getAppContext().getResources().getConfiguration().locale) && !b2) {
                b(Locale.US);
            } else if (!a(locale, CommonApplication.getAppContext().getResources().getConfiguration().locale)) {
                b(locale);
            }
        }
    }

    public static Locale b() {
        if (Build.VERSION.SDK_INT >= 24) {
            return Resources.getSystem().getConfiguration().getLocales().get(0);
        }
        return Locale.getDefault();
    }

    public static void b(Locale locale) {
        Context appContext = CommonApplication.getAppContext();
        if (appContext != null) {
            boolean b2 = SharePrefsManager.b(appContext, "language_setting", CardInfo.KEY_IS_DEFAULT, true);
            if (K.contains(b())) {
                if (b2) {
                    locale = G;
                } else if (a(Locale.US, locale)) {
                    locale = G;
                }
            } else if (a(G, locale)) {
                if (b2) {
                    locale = b();
                } else {
                    locale = Locale.US;
                }
            }
            try {
                Resources resources = appContext.getResources();
                Configuration configuration = resources.getConfiguration();
                DisplayMetrics displayMetrics = resources.getDisplayMetrics();
                configuration.locale = locale;
                resources.updateConfiguration(configuration, displayMetrics);
                if (ProcessUtil.b(appContext)) {
                    SmartHomeDeviceManager.a().s();
                }
            } catch (Exception unused) {
            }
            LocalBroadcastManager.getInstance(appContext).sendBroadcast(new Intent(f1553a));
        }
    }

    public static void c() {
        Locale c2 = ServerCompact.c(CommonApplication.getAppContext());
        if (c2 != null) {
            b(c2);
        }
    }

    public static void a(Configuration configuration) {
        Locale c2 = ServerCompact.c(CommonApplication.getAppContext());
        if (c2 == null) {
            b(configuration.locale);
        } else {
            b(c2);
        }
    }

    public static Context a(Context context, Locale locale) {
        Configuration configuration = context.getResources().getConfiguration();
        if (Build.VERSION.SDK_INT < 17) {
            return context;
        }
        try {
            configuration.setLocale(locale);
            return context.createConfigurationContext(configuration);
        } catch (Exception unused) {
            return context;
        }
    }

    public static Context a(Context context) {
        Context appContext = CommonApplication.getAppContext();
        if (appContext == null) {
            appContext = context;
        }
        if (Build.VERSION.SDK_INT < 17) {
            return context;
        }
        Locale locale = null;
        if (CoreApi.a().l()) {
            locale = CoreApi.a().I();
        } else if (appContext != null) {
            locale = ServerCompact.c(appContext);
        }
        if (locale == null) {
            return context;
        }
        boolean b2 = SharePrefsManager.b(appContext, "language_setting", CardInfo.KEY_IS_DEFAULT, true);
        if (K.contains(b())) {
            if (b2) {
                locale = G;
            } else if (a(Locale.US, locale)) {
                locale = G;
            }
        } else if (a(G, locale)) {
            if (b2) {
                locale = b();
            } else {
                locale = Locale.US;
            }
        }
        return a(context, locale);
    }

    public static boolean a(Locale locale, Locale locale2) {
        if (locale == locale2) {
            return true;
        }
        if (locale == null || locale2 == null || !locale.getLanguage().equalsIgnoreCase(locale2.getLanguage()) || !locale.getCountry().equalsIgnoreCase(locale2.getCountry())) {
            return false;
        }
        return true;
    }

    public static String a(Activity activity, int i2) {
        try {
            return StringUtil.a(activity, ServerCompact.c(activity.getApplicationContext())).getString(i2);
        } catch (Exception e2) {
            LogUtil.b("LanguageUtil", e2.getMessage());
            return CommonApplication.getAppContext().getString(i2);
        }
    }

    public static boolean a(Activity activity, int i2, TextView textView) {
        return StringUtil.a(activity, ServerCompact.c(activity.getApplicationContext()), i2, textView);
    }

    public static String a(Activity activity) {
        return c(ServerCompact.c(activity.getApplicationContext()));
    }

    public static DateFormat b(Activity activity) {
        return SimpleDateFormat.getDateTimeInstance(3, 3, ServerCompact.c(activity.getApplicationContext()));
    }

    public static String c(Locale locale) {
        return a(locale, "/");
    }

    public static String a(Locale locale, String str) {
        if (TextUtils.isEmpty(str)) {
            str = "/";
        }
        if (a(locale, Locale.CHINA) || a(locale, Locale.TRADITIONAL_CHINESE) || a(locale, v)) {
            return "yyyy年MM月dd日";
        }
        if (a(locale, w)) {
            return "yyyy년MM월dd일";
        }
        if (a(locale, x) || a(locale, A) || a(locale, F) || a(locale, y) || a(locale, I) || a(locale, J) || a(locale, H) || a(locale, Locale.FRANCE)) {
            return d.s + str + "MM" + str + "yyyy";
        }
        return "MM" + str + d.s + str + "yyyy";
    }

    public static Resources a(Resources resources, Locale locale) {
        if (locale == null) {
            return resources;
        }
        try {
            Configuration configuration = new Configuration(resources.getConfiguration());
            configuration.locale = locale;
            return new Resources(resources.getAssets(), resources.getDisplayMetrics(), configuration);
        } catch (Exception unused) {
            return resources;
        }
    }

    public static String a(Locale locale, int i2) {
        if (locale == null) {
            locale = ServerCompact.c(CommonApplication.getAppContext());
        }
        if (locale == null) {
            return CommonApplication.getAppContext().getResources().getString(i2);
        }
        try {
            return a(CommonApplication.getAppContext().getResources(), locale).getString(i2);
        } catch (Exception unused) {
            return CommonApplication.getAppContext().getResources().getString(i2);
        }
    }

    public static String a(Locale locale, int i2, Object... objArr) {
        if (locale == null) {
            locale = ServerCompact.c(CommonApplication.getAppContext());
        }
        if (locale == null) {
            return CommonApplication.getAppContext().getResources().getString(i2, objArr);
        }
        try {
            return a(CommonApplication.getAppContext().getResources(), locale).getString(i2, objArr);
        } catch (Exception unused) {
            return CommonApplication.getAppContext().getResources().getString(i2, objArr);
        }
    }

    public static String a(Locale locale, int i2, int i3, Object... objArr) {
        if (locale == null) {
            locale = ServerCompact.c(CommonApplication.getAppContext());
        }
        if (locale == null) {
            return CommonApplication.getAppContext().getResources().getQuantityString(i2, i3, objArr);
        }
        try {
            return a(CommonApplication.getAppContext().getResources(), locale).getQuantityString(i2, i3, objArr);
        } catch (Exception unused) {
            return CommonApplication.getAppContext().getResources().getQuantityString(i2, i3, objArr);
        }
    }

    public static String a(String str, String str2) {
        String str3;
        Locale c2 = ServerCompact.c(CommonApplication.getAppContext());
        Locale b2 = b();
        if (a(c2, Locale.CHINA)) {
            str3 = "zh";
        } else if (a(c2, Locale.TRADITIONAL_CHINESE)) {
            str3 = "tw";
        } else if (a(c2, v)) {
            str3 = "hk";
        } else if (a(c2, w)) {
            str3 = "ko";
        } else if (a(c2, x)) {
            str3 = d.u;
        } else if (a(c2, y)) {
            str3 = Region.RU;
        } else if (a(c2, Locale.US)) {
            str3 = K.contains(b2) ? "en-gb" : "us";
        } else if (a(c2, Locale.FRANCE)) {
            str3 = "fr";
        } else if (a(c2, Locale.ITALY)) {
            str3 = "it";
        } else if (a(c2, Locale.GERMANY)) {
            str3 = "de";
        } else if (a(c2, F)) {
            str3 = "id";
        } else if (a(c2, z)) {
            str3 = d.U;
        } else if (a(c2, A)) {
            str3 = "vi";
        } else if (a(c2, B)) {
            str3 = "th";
        } else if (a(c2, Locale.JAPAN)) {
            str3 = "ja";
        } else if (a(c2, G)) {
            str3 = "en-gb";
        } else if (a(c2, J)) {
            str3 = "nl";
        } else if (a(c2, I)) {
            str3 = BaseInfo.KEY_TIME_RECORD;
        } else if (a(c2, H)) {
            str3 = "pt-br";
        } else {
            str3 = K.contains(b2) ? "en-gb" : "us";
        }
        try {
            AssetManager assets = CommonApplication.getAppContext().getAssets();
            assets.open(str + "/" + str3 + "/" + str2);
        } catch (IOException unused) {
            str3 = "us";
        }
        return str + "/" + str3 + "/" + str2;
    }

    public static String d() {
        return a("location", "cityWeather.json");
    }
}
