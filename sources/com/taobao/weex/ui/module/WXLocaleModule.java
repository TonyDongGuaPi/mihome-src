package com.taobao.weex.ui.module;

import android.app.Application;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.text.TextUtils;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXLocaleModule extends WXModule {
    private static transient /* synthetic */ boolean[] $jacocoData;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(6919302257669398628L, "com/taobao/weex/ui/module/WXLocaleModule", 32);
        $jacocoData = a2;
        return a2;
    }

    public WXLocaleModule() {
        $jacocoInit()[0] = true;
    }

    @JSMethod(uiThread = false)
    public String getLanguageSync() {
        boolean[] $jacocoInit = $jacocoInit();
        String languageImpl = getLanguageImpl();
        $jacocoInit[1] = true;
        return languageImpl;
    }

    @JSMethod(uiThread = false)
    public void getLanguage(JSCallback jSCallback) {
        boolean[] $jacocoInit = $jacocoInit();
        jSCallback.invoke(getLanguageImpl());
        $jacocoInit[2] = true;
    }

    private String getLanguageImpl() {
        Locale locale;
        boolean[] $jacocoInit = $jacocoInit();
        if (Build.VERSION.SDK_INT >= 24) {
            $jacocoInit[3] = true;
            locale = LocaleList.getDefault().get(0);
            $jacocoInit[4] = true;
        } else {
            locale = Locale.getDefault();
            $jacocoInit[5] = true;
        }
        String str = locale.getLanguage() + "-" + locale.getCountry();
        $jacocoInit[6] = true;
        return str;
    }

    @JSMethod(uiThread = false)
    public List<String> getLanguages() {
        boolean[] $jacocoInit = $jacocoInit();
        String[] split = getLanguageTags().split(",");
        $jacocoInit[7] = true;
        List<String> asList = Arrays.asList(split);
        $jacocoInit[8] = true;
        return asList;
    }

    @JSMethod(uiThread = false)
    public void getLanguages(JSCallback jSCallback) {
        boolean[] $jacocoInit = $jacocoInit();
        jSCallback.invoke(getLanguageTags().split(","));
        $jacocoInit[9] = true;
    }

    private String getLanguageTags() {
        boolean[] $jacocoInit = $jacocoInit();
        Application application = WXEnvironment.getApplication();
        if (application == null) {
            $jacocoInit[10] = true;
        } else {
            $jacocoInit[11] = true;
            Resources resources = application.getResources();
            if (resources == null) {
                $jacocoInit[12] = true;
            } else {
                $jacocoInit[13] = true;
                Configuration configuration = resources.getConfiguration();
                if (configuration == null) {
                    $jacocoInit[14] = true;
                } else if (Build.VERSION.SDK_INT >= 24) {
                    $jacocoInit[15] = true;
                    LocaleList locales = configuration.getLocales();
                    $jacocoInit[16] = true;
                    String languageTags = locales.toLanguageTags();
                    $jacocoInit[17] = true;
                    return languageTags;
                } else {
                    Locale locale = configuration.locale;
                    if (locale == null) {
                        $jacocoInit[18] = true;
                    } else {
                        $jacocoInit[19] = true;
                        String languageTag = toLanguageTag(locale);
                        $jacocoInit[20] = true;
                        return languageTag;
                    }
                }
            }
        }
        $jacocoInit[21] = true;
        return "";
    }

    private String toLanguageTag(Locale locale) {
        boolean[] $jacocoInit = $jacocoInit();
        if (Build.VERSION.SDK_INT >= 21) {
            $jacocoInit[22] = true;
            String languageTag = locale.toLanguageTag();
            $jacocoInit[23] = true;
            return languageTag;
        }
        StringBuilder sb = new StringBuilder();
        $jacocoInit[24] = true;
        String language = locale.getLanguage();
        $jacocoInit[25] = true;
        String country = locale.getCountry();
        $jacocoInit[26] = true;
        sb.append(language);
        $jacocoInit[27] = true;
        if (TextUtils.isEmpty(country)) {
            $jacocoInit[28] = true;
        } else {
            $jacocoInit[29] = true;
            sb.append("-");
            sb.append(country);
            $jacocoInit[30] = true;
        }
        String sb2 = sb.toString();
        $jacocoInit[31] = true;
        return sb2;
    }
}
