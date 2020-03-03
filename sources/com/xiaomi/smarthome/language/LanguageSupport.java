package com.xiaomi.smarthome.language;

import com.mi.global.bbs.manager.Region;
import com.xiaomi.miot.support.monitor.core.BaseInfo;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.miio.LanguageUtil;
import com.xiaomi.stat.d;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LanguageSupport {
    public static List<LanguageModel> a() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new LanguageModel((Locale) null, 0, R.string.settings_language_default, ""));
        arrayList.add(new LanguageModel(Locale.CHINA, 1, R.string.settings_language_zh, "zh"));
        arrayList.add(new LanguageModel(Locale.TRADITIONAL_CHINESE, 2, R.string.settings_language_zh_rtw, "tw"));
        arrayList.add(new LanguageModel(LanguageUtil.v, 4, R.string.settings_language_zh_hk, "hk"));
        arrayList.add(new LanguageModel(Locale.US, 3, R.string.settings_language_us, "us"));
        arrayList.add(new LanguageModel(LanguageUtil.x, 6, R.string.settings_language_es, d.u));
        arrayList.add(new LanguageModel(LanguageUtil.y, 7, R.string.settings_language_ru, Region.RU));
        arrayList.add(new LanguageModel(LanguageUtil.w, 5, R.string.settings_language_ko, "ko"));
        arrayList.add(new LanguageModel(Locale.ITALY, 9, R.string.settings_language_it, "it"));
        arrayList.add(new LanguageModel(Locale.FRANCE, 8, R.string.settings_language_fr, "fr"));
        arrayList.add(new LanguageModel(Locale.GERMANY, 10, R.string.settings_language_de, "de"));
        arrayList.add(new LanguageModel(LanguageUtil.F, 11, R.string.settings_language_id, "id"));
        arrayList.add(new LanguageModel(LanguageUtil.z, 12, R.string.settings_language_pl, d.U));
        arrayList.add(new LanguageModel(LanguageUtil.A, 13, R.string.settings_language_vi, "vi"));
        arrayList.add(new LanguageModel(Locale.JAPAN, 14, R.string.settings_language_ja, "ja"));
        arrayList.add(new LanguageModel(LanguageUtil.B, 15, R.string.settings_language_th, "th"));
        arrayList.add(new LanguageModel(LanguageUtil.J, 16, R.string.settings_language_nl, "nl"));
        arrayList.add(new LanguageModel(LanguageUtil.H, 17, R.string.settings_language_pt, "pt_BR"));
        arrayList.add(new LanguageModel(LanguageUtil.I, 18, R.string.settings_language_tr, BaseInfo.KEY_TIME_RECORD));
        return arrayList;
    }

    public static String b() {
        Locale c = ServerCompact.c(SHApplication.getAppContext());
        if (c == null) {
            return "us";
        }
        for (LanguageModel next : a()) {
            if (LanguageUtil.a(c, next.f18427a)) {
                return next.b;
            }
        }
        return "us";
    }
}
