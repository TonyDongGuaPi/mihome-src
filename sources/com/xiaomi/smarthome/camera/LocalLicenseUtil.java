package com.xiaomi.smarthome.camera;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import com.google.zxing.client.result.ExpandedProductParsedResult;
import com.mijia.camera.Utils.Util;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.frame.login.util.ServerUtil;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.framework.page.PluginLicenseActivity;
import org.cybergarage.http.HTTP;

public class LocalLicenseUtil {

    public static class LocalLicense {
        public int mLicense;
        public String mPlan;
        public int mPrivacy;

        public LocalLicense(int i, int i2, String str) {
            this.mLicense = i2;
            this.mPlan = str;
            this.mPrivacy = i;
        }
    }

    public static class UrlLicense {
        public String mLicense;
        public String mPlan;
        public String mPrivacy;

        public UrlLicense(String str, String str2, String str3) {
            this.mLicense = str2;
            this.mPlan = str3;
            this.mPrivacy = str;
        }
    }

    public static LocalLicense getV3LocalLicense(Resources resources) {
        String b = ServerUtil.b();
        String language = resources.getConfiguration().locale.getLanguage();
        int g = Util.g(resources, R.raw.camera_v3_ua_en);
        int g2 = Util.g(resources, R.raw.camera_v3_privacy_en);
        String f = Util.f(resources, R.raw.camera_plan);
        if ("SA".equalsIgnoreCase(b) || "AE".equalsIgnoreCase(b) || "EG".equalsIgnoreCase(b) || "MA".equalsIgnoreCase(b)) {
            if ("en".equalsIgnoreCase(language)) {
                g = Util.g(resources, R.raw.camera_v3_ua_en);
                g2 = Util.g(resources, R.raw.camera_v3_privacy_en);
            } else {
                g = Util.g(resources, R.raw.camera_v3_ua_ar);
                g2 = Util.g(resources, R.raw.camera_v3_privacy_ar);
            }
        } else if ("BY".equalsIgnoreCase(b)) {
            if ("en".equalsIgnoreCase(language)) {
                g = Util.g(resources, R.raw.camera_v3_ua_en);
                g2 = Util.g(resources, R.raw.camera_v3_privacy_en);
            } else {
                g = Util.g(resources, R.raw.camera_v3_ua_br);
                g2 = Util.g(resources, R.raw.camera_v3_privacy_br);
            }
        } else if ("DE".equalsIgnoreCase(b)) {
            if ("en".equalsIgnoreCase(language)) {
                g = Util.g(resources, R.raw.camera_v3_ua_en);
                g2 = Util.g(resources, R.raw.camera_v3_privacy_en);
            } else {
                g = Util.g(resources, R.raw.camera_v3_ua_de);
                g2 = Util.g(resources, R.raw.camera_v3_privacy_de);
            }
        } else if ("GR".equalsIgnoreCase(b)) {
            if ("en".equalsIgnoreCase(language)) {
                g = Util.g(resources, R.raw.camera_v3_ua_en);
                g2 = Util.g(resources, R.raw.camera_v3_privacy_en);
            } else {
                g = Util.g(resources, R.raw.camera_v3_ua_el);
                g2 = Util.g(resources, R.raw.camera_v3_privacy_el);
            }
        } else if (ServerCompact.f.equalsIgnoreCase(b)) {
            if ("en".equalsIgnoreCase(language)) {
                g = Util.g(resources, R.raw.camera_v3_ua_en);
                g2 = Util.g(resources, R.raw.camera_v3_privacy_en);
            } else {
                g = Util.g(resources, R.raw.camera_v3_ua_es);
                g2 = Util.g(resources, R.raw.camera_v3_privacy_es);
            }
        } else if ("AR".equalsIgnoreCase(b) || "PE".equalsIgnoreCase(b) || "CL".equalsIgnoreCase(b) || "CO".equalsIgnoreCase(b) || HTTP.MX.equalsIgnoreCase(b)) {
            if ("en".equalsIgnoreCase(language)) {
                g = Util.g(resources, R.raw.camera_v3_ua_en);
                g2 = Util.g(resources, R.raw.camera_v3_privacy_en);
            } else {
                g = Util.g(resources, R.raw.camera_v3_ua_es_la);
                g2 = Util.g(resources, R.raw.camera_v3_privacy_es_la);
            }
        } else if (ServerCompact.e.equalsIgnoreCase(b)) {
            if ("en".equalsIgnoreCase(language)) {
                g = Util.g(resources, R.raw.camera_v3_ua_en);
                g2 = Util.g(resources, R.raw.camera_v3_privacy_en);
            } else {
                g = Util.g(resources, R.raw.camera_v3_ua_fr);
                g2 = Util.g(resources, R.raw.camera_v3_privacy_fr);
            }
        } else if ("IL".equalsIgnoreCase(b)) {
            if ("en".equalsIgnoreCase(language)) {
                g = Util.g(resources, R.raw.camera_v3_ua_en);
                g2 = Util.g(resources, R.raw.camera_v3_privacy_en);
            } else {
                g = Util.g(resources, R.raw.camera_v3_ua_he);
                g2 = Util.g(resources, R.raw.camera_v3_privacy_he);
            }
        } else if (ServerCompact.b.equalsIgnoreCase(b)) {
            if ("en".equalsIgnoreCase(language)) {
                g = Util.g(resources, R.raw.camera_v3_ua_en);
                g2 = Util.g(resources, R.raw.camera_v3_privacy_en);
            } else {
                g = Util.g(resources, R.raw.camera_v3_ua_hk);
                g2 = Util.g(resources, R.raw.camera_v3_privacy_hk);
            }
        } else if ("ID".equalsIgnoreCase(b)) {
            if ("en".equalsIgnoreCase(language)) {
                g = Util.g(resources, R.raw.camera_v3_ua_en);
                g2 = Util.g(resources, R.raw.camera_v3_privacy_en);
            } else {
                g = Util.g(resources, R.raw.camera_v3_ua_id);
                g2 = Util.g(resources, R.raw.camera_v3_privacy_id);
            }
        } else if (ServerCompact.h.equalsIgnoreCase(b)) {
            if ("en".equalsIgnoreCase(language)) {
                g = Util.g(resources, R.raw.camera_v3_ua_en);
                g2 = Util.g(resources, R.raw.camera_v3_privacy_en);
            } else {
                g = Util.g(resources, R.raw.camera_v3_ua_it);
                g2 = Util.g(resources, R.raw.camera_v3_privacy_it);
            }
        } else if ("KR".equalsIgnoreCase(b)) {
            if ("en".equalsIgnoreCase(language)) {
                g = Util.g(resources, R.raw.camera_v3_ua_en_kr);
                g2 = Util.g(resources, R.raw.camera_v3_privacy_en_kr);
            } else {
                g = Util.g(resources, R.raw.camera_v3_ua_kr);
                g2 = Util.g(resources, R.raw.camera_v3_privacy_kr);
            }
        } else if ("MY".equalsIgnoreCase(b)) {
            if ("en".equalsIgnoreCase(language)) {
                g = Util.g(resources, R.raw.camera_v3_ua_en);
                g2 = Util.g(resources, R.raw.camera_v3_privacy_en);
            } else {
                g = Util.g(resources, R.raw.camera_v3_ua_ms);
                g2 = Util.g(resources, R.raw.camera_v3_privacy_ms);
            }
        } else if ("PL".equalsIgnoreCase(b)) {
            if ("en".equalsIgnoreCase(language)) {
                g = Util.g(resources, R.raw.camera_v3_ua_en);
                g2 = Util.g(resources, R.raw.camera_v3_privacy_en);
            } else {
                g = Util.g(resources, R.raw.camera_v3_ua_pl);
                g2 = Util.g(resources, R.raw.camera_v3_privacy_pl);
            }
        } else if ("PT".equalsIgnoreCase(b)) {
            if ("en".equalsIgnoreCase(language)) {
                g = Util.g(resources, R.raw.camera_v3_ua_en);
                g2 = Util.g(resources, R.raw.camera_v3_privacy_en);
            } else {
                g = Util.g(resources, R.raw.camera_v3_ua_pt);
                g2 = Util.g(resources, R.raw.camera_v3_privacy_pt);
            }
        } else if (ServerCompact.d.equalsIgnoreCase(b)) {
            if ("en".equalsIgnoreCase(language)) {
                g = Util.g(resources, R.raw.camera_v3_ua_en);
                g2 = Util.g(resources, R.raw.camera_v3_privacy_en);
            } else {
                g = Util.g(resources, R.raw.camera_v3_ua_ru);
                g2 = Util.g(resources, R.raw.camera_v3_privacy_ru);
            }
        } else if ("BR".equalsIgnoreCase(b)) {
            if ("en".equalsIgnoreCase(language)) {
                g = Util.g(resources, R.raw.camera_v3_ua_en);
                g2 = Util.g(resources, R.raw.camera_v3_privacy_en);
            } else {
                g = Util.g(resources, R.raw.camera_v3_ua_pt_br);
                g2 = Util.g(resources, R.raw.camera_v3_privacy_pt_br);
            }
        } else if ("TH".equalsIgnoreCase(b)) {
            if ("en".equalsIgnoreCase(language)) {
                g = Util.g(resources, R.raw.camera_v3_ua_en);
                g2 = Util.g(resources, R.raw.camera_v3_privacy_en);
            } else {
                g = Util.g(resources, R.raw.camera_v3_ua_th);
                g2 = Util.g(resources, R.raw.camera_v3_privacy_th);
            }
        } else if ("TR".equalsIgnoreCase(b)) {
            if ("en".equalsIgnoreCase(language)) {
                g = Util.g(resources, R.raw.camera_v3_ua_en);
                g2 = Util.g(resources, R.raw.camera_v3_privacy_en);
            } else {
                g = Util.g(resources, R.raw.camera_v3_ua_tr);
                g2 = Util.g(resources, R.raw.camera_v3_privacy_tr);
            }
        } else if (ServerCompact.f1531a.equalsIgnoreCase(b)) {
            if ("en".equalsIgnoreCase(language)) {
                g = Util.g(resources, R.raw.camera_v3_ua_en);
                g2 = Util.g(resources, R.raw.camera_v3_privacy_en);
            } else {
                g = Util.g(resources, R.raw.camera_v3_ua_tw);
                g2 = Util.g(resources, R.raw.camera_v3_privacy_tw);
            }
        } else if ("UA".equalsIgnoreCase(b)) {
            if ("en".equalsIgnoreCase(language)) {
                g = Util.g(resources, R.raw.camera_v3_ua_en);
                g2 = Util.g(resources, R.raw.camera_v3_privacy_en);
            } else {
                g = Util.g(resources, R.raw.camera_v3_ua_uk);
                g2 = Util.g(resources, R.raw.camera_v3_privacy_uk);
            }
        } else if ("US".equalsIgnoreCase(b)) {
            g = Util.g(resources, R.raw.camera_v3_ua_us);
            g2 = Util.g(resources, R.raw.camera_v3_privacy_us);
        } else if ("CN".equalsIgnoreCase(b)) {
            if ("en".equalsIgnoreCase(language)) {
                g = Util.g(resources, R.raw.camera_v3_ua_en_ml);
                g2 = Util.g(resources, R.raw.camera_v3_privacy_en_ml);
            } else {
                g = Util.g(resources, R.raw.camera_v3_license_cn);
                g2 = Util.g(resources, R.raw.camera_v3_privacy_cn);
            }
        }
        return new LocalLicense(g2, g, f);
    }

    public static LocalLicense getV3UpgradeLicense(Resources resources) {
        String b = ServerUtil.b();
        String language = resources.getConfiguration().locale.getLanguage();
        int g = Util.g(resources, R.raw.camera_v3_upgrade_license_en);
        int g2 = Util.g(resources, R.raw.camera_v3_upgrade_privacy_en);
        String f = Util.f(resources, R.raw.camera_v3_upgrade_plan);
        if ("CN".equalsIgnoreCase(b) && !"en".equalsIgnoreCase(language)) {
            g = Util.g(resources, R.raw.camera_v3_upgrade_license_cn);
            g2 = Util.g(resources, R.raw.camera_v3_upgrade_privacy_cn);
        }
        return new LocalLicense(g2, g, f);
    }

    public static LocalLicense getV4LocalLicense(Resources resources) {
        Resources resources2 = resources;
        String b = ServerUtil.b();
        String language = resources.getConfiguration().locale.getLanguage();
        int g = Util.g(resources2, R.raw.camera_v4_license_en);
        int g2 = Util.g(resources2, R.raw.camera_v4_privacy_en);
        if ("SA".equalsIgnoreCase(b) || "AE".equalsIgnoreCase(b) || "EG".equalsIgnoreCase(b) || "MA".equalsIgnoreCase(b) || "QA".equalsIgnoreCase(b) || "OM".equalsIgnoreCase(b) || "IQ".equalsIgnoreCase(b) || ExpandedProductParsedResult.POUND.equalsIgnoreCase(b) || "JO".equalsIgnoreCase(b) || "BH".equalsIgnoreCase(b) || "KW".equalsIgnoreCase(b)) {
            if ("en".equalsIgnoreCase(language)) {
                g = Util.g(resources2, R.raw.camera_v4_license_en);
                g2 = Util.g(resources2, R.raw.camera_v4_privacy_en);
            } else {
                g = Util.g(resources2, R.raw.camera_v4_license_ar);
                g2 = Util.g(resources2, R.raw.camera_v4_privacy_ar);
            }
        } else if ("BY".equalsIgnoreCase(b)) {
            if ("en".equalsIgnoreCase(language)) {
                g = Util.g(resources2, R.raw.camera_v4_license_en);
                g2 = Util.g(resources2, R.raw.camera_v4_privacy_en);
            } else {
                g = Util.g(resources2, R.raw.camera_v4_license_be);
                g2 = Util.g(resources2, R.raw.camera_v4_privacy_be);
            }
        } else if ("DE".equalsIgnoreCase(b) || "AT".equalsIgnoreCase(b)) {
            if ("en".equalsIgnoreCase(language)) {
                g = Util.g(resources2, R.raw.camera_v4_license_en);
                g2 = Util.g(resources2, R.raw.camera_v4_privacy_en);
            } else {
                g = Util.g(resources2, R.raw.camera_v4_license_de);
                g2 = Util.g(resources2, R.raw.camera_v4_privacy_de);
            }
        } else if ("GR".equalsIgnoreCase(b)) {
            if ("en".equalsIgnoreCase(language)) {
                g = Util.g(resources2, R.raw.camera_v4_license_en);
                g2 = Util.g(resources2, R.raw.camera_v4_privacy_en);
            } else {
                g = Util.g(resources2, R.raw.camera_v4_license_el);
                g2 = Util.g(resources2, R.raw.camera_v4_privacy_el);
            }
        } else if (ServerCompact.f.equalsIgnoreCase(b)) {
            if ("en".equalsIgnoreCase(language)) {
                g = Util.g(resources2, R.raw.camera_v4_license_en);
                g2 = Util.g(resources2, R.raw.camera_v4_privacy_en);
            } else {
                g = Util.g(resources2, R.raw.camera_v4_license_es);
                g2 = Util.g(resources2, R.raw.camera_v4_privacy_es);
            }
        } else if (ServerCompact.e.equalsIgnoreCase(b) || "NG".equalsIgnoreCase(b)) {
            if ("en".equalsIgnoreCase(language)) {
                g = Util.g(resources2, R.raw.camera_v4_license_en);
                g2 = Util.g(resources2, R.raw.camera_v4_privacy_en);
            } else {
                g = Util.g(resources2, R.raw.camera_v4_license_fr);
                g2 = Util.g(resources2, R.raw.camera_v4_privacy_fr);
            }
        } else if ("IL".equalsIgnoreCase(b)) {
            if ("en".equalsIgnoreCase(language)) {
                g = Util.g(resources2, R.raw.camera_v4_license_en);
                g2 = Util.g(resources2, R.raw.camera_v4_privacy_en);
            } else {
                g = Util.g(resources2, R.raw.camera_v4_license_he);
                g2 = Util.g(resources2, R.raw.camera_v4_privacy_he);
            }
        } else if (ServerCompact.b.equalsIgnoreCase(b)) {
            if ("en".equalsIgnoreCase(language)) {
                g = Util.g(resources2, R.raw.camera_v4_license_en);
                g2 = Util.g(resources2, R.raw.camera_v4_privacy_en);
            } else {
                g = Util.g(resources2, R.raw.camera_v4_license_hk);
                g2 = Util.g(resources2, R.raw.camera_v4_privacy_hk);
            }
        } else if ("ID".equalsIgnoreCase(b)) {
            if ("en".equalsIgnoreCase(language)) {
                g = Util.g(resources2, R.raw.camera_v4_license_en);
                g2 = Util.g(resources2, R.raw.camera_v4_privacy_en);
            } else {
                g = Util.g(resources2, R.raw.camera_v4_license_id);
                g2 = Util.g(resources2, R.raw.camera_v4_privacy_id);
            }
        } else if (ServerCompact.h.equalsIgnoreCase(b)) {
            if ("en".equalsIgnoreCase(language)) {
                g = Util.g(resources2, R.raw.camera_v4_license_en);
                g2 = Util.g(resources2, R.raw.camera_v4_privacy_en);
            } else {
                g = Util.g(resources2, R.raw.camera_v4_license_it);
                g2 = Util.g(resources2, R.raw.camera_v4_privacy_it);
            }
        } else if ("KR".equalsIgnoreCase(b)) {
            g = Util.g(resources2, R.raw.camera_v4_license_ko);
            g2 = Util.g(resources2, R.raw.camera_v4_privacy_ko);
        } else if ("PL".equalsIgnoreCase(b)) {
            if ("en".equalsIgnoreCase(language)) {
                g = Util.g(resources2, R.raw.camera_v4_license_en);
                g2 = Util.g(resources2, R.raw.camera_v4_privacy_en);
            } else {
                g = Util.g(resources2, R.raw.camera_v4_license_pl);
                g2 = Util.g(resources2, R.raw.camera_v4_privacy_pl);
            }
        } else if ("PT".equalsIgnoreCase(b)) {
            if ("en".equalsIgnoreCase(language)) {
                g = Util.g(resources2, R.raw.camera_v4_license_en);
                g2 = Util.g(resources2, R.raw.camera_v4_privacy_en);
            } else {
                g = Util.g(resources2, R.raw.camera_v4_license_pt);
                g2 = Util.g(resources2, R.raw.camera_v4_privacy_pt);
            }
        } else if (ServerCompact.d.equalsIgnoreCase(b)) {
            if ("en".equalsIgnoreCase(language)) {
                g = Util.g(resources2, R.raw.camera_v4_license_en);
                g2 = Util.g(resources2, R.raw.camera_v4_privacy_en);
            } else {
                g = Util.g(resources2, R.raw.camera_v4_license_ru);
                g2 = Util.g(resources2, R.raw.camera_v4_privacy_ru);
            }
        } else if ("TH".equalsIgnoreCase(b)) {
            if ("en".equalsIgnoreCase(language)) {
                g = Util.g(resources2, R.raw.camera_v4_license_en);
                g2 = Util.g(resources2, R.raw.camera_v4_privacy_en);
            } else {
                g = Util.g(resources2, R.raw.camera_v4_license_th);
                g2 = Util.g(resources2, R.raw.camera_v4_privacy_th);
            }
        } else if ("TR".equalsIgnoreCase(b)) {
            if ("en".equalsIgnoreCase(language)) {
                g = Util.g(resources2, R.raw.camera_v4_license_en);
                g2 = Util.g(resources2, R.raw.camera_v4_privacy_en);
            } else {
                g = Util.g(resources2, R.raw.camera_v4_license_tr);
                g2 = Util.g(resources2, R.raw.camera_v4_privacy_tr);
            }
        } else if (ServerCompact.f1531a.equalsIgnoreCase(b)) {
            if ("en".equalsIgnoreCase(language)) {
                g = Util.g(resources2, R.raw.camera_v4_license_en);
                g2 = Util.g(resources2, R.raw.camera_v4_privacy_en);
            } else {
                g = Util.g(resources2, R.raw.camera_v4_license_tw);
                g2 = Util.g(resources2, R.raw.camera_v4_privacy_tw);
            }
        } else if ("UA".equalsIgnoreCase(b)) {
            if ("en".equalsIgnoreCase(language)) {
                g = Util.g(resources2, R.raw.camera_v4_license_en);
                g2 = Util.g(resources2, R.raw.camera_v4_privacy_en);
            } else {
                g = Util.g(resources2, R.raw.camera_v4_license_uk);
                g2 = Util.g(resources2, R.raw.camera_v4_privacy_uk);
            }
        } else if ("US".equalsIgnoreCase(b)) {
            g = Util.g(resources2, R.raw.camera_v4_license_us);
            g2 = Util.g(resources2, R.raw.camera_v4_privacy_us);
        } else if ("CN".equalsIgnoreCase(b)) {
            if ("en".equalsIgnoreCase(language)) {
                g = Util.g(resources2, R.raw.camera_v4_license_en_ml);
                g2 = Util.g(resources2, R.raw.camera_v4_privacy_en_ml);
            } else {
                g = Util.g(resources2, R.raw.camera_v4_license_cn);
                g2 = Util.g(resources2, R.raw.camera_v4_privacy_cn);
            }
        } else if ("NL".equalsIgnoreCase(b)) {
            if ("en".equalsIgnoreCase(language)) {
                g = Util.g(resources2, R.raw.camera_v4_license_en);
                g2 = Util.g(resources2, R.raw.camera_v4_privacy_en);
            } else {
                g = Util.g(resources2, R.raw.camera_v4_license_nl);
                g2 = Util.g(resources2, R.raw.camera_v4_privacy_nl);
            }
        } else if ("CZ".equalsIgnoreCase(b)) {
            if ("en".equalsIgnoreCase(language)) {
                g = Util.g(resources2, R.raw.camera_v4_license_en);
                g2 = Util.g(resources2, R.raw.camera_v4_privacy_en);
            } else {
                g = Util.g(resources2, R.raw.camera_v4_license_cz);
                g2 = Util.g(resources2, R.raw.camera_v4_privacy_cz);
            }
        } else if ("SK".equalsIgnoreCase(b)) {
            if ("en".equalsIgnoreCase(language)) {
                g = Util.g(resources2, R.raw.camera_v4_license_en);
                g2 = Util.g(resources2, R.raw.camera_v4_privacy_en);
            } else {
                g = Util.g(resources2, R.raw.camera_v4_license_sk);
                g2 = Util.g(resources2, R.raw.camera_v4_privacy_sk);
            }
        } else if ("SK".equalsIgnoreCase(b)) {
            if ("en".equalsIgnoreCase(language)) {
                g = Util.g(resources2, R.raw.camera_v4_license_en);
                g2 = Util.g(resources2, R.raw.camera_v4_privacy_en);
            } else {
                g = Util.g(resources2, R.raw.camera_v4_license_sk);
                g2 = Util.g(resources2, R.raw.camera_v4_privacy_sk);
            }
        } else if ("MM".equalsIgnoreCase(b)) {
            if ("en".equalsIgnoreCase(language)) {
                g = Util.g(resources2, R.raw.camera_v4_license_en);
                g2 = Util.g(resources2, R.raw.camera_v4_privacy_en);
            } else {
                g = Util.g(resources2, R.raw.camera_v4_license_my);
                g2 = Util.g(resources2, R.raw.camera_v4_privacy_my);
            }
        } else if ("VN".equalsIgnoreCase(b)) {
            if ("en".equalsIgnoreCase(language)) {
                g = Util.g(resources2, R.raw.camera_v4_license_en);
                g2 = Util.g(resources2, R.raw.camera_v4_privacy_en);
            } else {
                g = Util.g(resources2, R.raw.camera_v4_license_vi);
                g2 = Util.g(resources2, R.raw.camera_v4_privacy_vi);
            }
        } else if ("HR".equalsIgnoreCase(b)) {
            if ("en".equalsIgnoreCase(language)) {
                g = Util.g(resources2, R.raw.camera_v4_license_en);
                g2 = Util.g(resources2, R.raw.camera_v4_privacy_en);
            } else {
                g = Util.g(resources2, R.raw.camera_v4_license_hr);
                g2 = Util.g(resources2, R.raw.camera_v4_privacy_hr);
            }
        } else if ("CH".equalsIgnoreCase(b)) {
            if ("en".equalsIgnoreCase(language)) {
                g = Util.g(resources2, R.raw.camera_v4_license_en);
                g2 = Util.g(resources2, R.raw.camera_v4_privacy_en);
            } else if ("de".equalsIgnoreCase(language)) {
                g = Util.g(resources2, R.raw.camera_v4_license_de);
                g2 = Util.g(resources2, R.raw.camera_v4_privacy_de);
            } else if ("it".equalsIgnoreCase(language)) {
                g = Util.g(resources2, R.raw.camera_v4_license_it);
                g2 = Util.g(resources2, R.raw.camera_v4_privacy_it);
            } else {
                g = Util.g(resources2, R.raw.camera_v4_license_fr);
                g2 = Util.g(resources2, R.raw.camera_v4_privacy_fr);
            }
        } else if ("BE".equalsIgnoreCase(b)) {
            if ("en".equalsIgnoreCase(language)) {
                g = Util.g(resources2, R.raw.camera_v4_license_en);
                g2 = Util.g(resources2, R.raw.camera_v4_privacy_en);
            } else if ("nl".equalsIgnoreCase(language)) {
                g = Util.g(resources2, R.raw.camera_v4_license_nl);
                g2 = Util.g(resources2, R.raw.camera_v4_privacy_nl);
            } else {
                g = Util.g(resources2, R.raw.camera_v4_license_fr);
                g2 = Util.g(resources2, R.raw.camera_v4_privacy_fr);
            }
        } else if ("lu".equalsIgnoreCase(b)) {
            if ("en".equalsIgnoreCase(language)) {
                g = Util.g(resources2, R.raw.camera_v4_license_en);
                g2 = Util.g(resources2, R.raw.camera_v4_privacy_en);
            } else if ("de".equalsIgnoreCase(language)) {
                g = Util.g(resources2, R.raw.camera_v4_license_de);
                g2 = Util.g(resources2, R.raw.camera_v4_privacy_de);
            } else {
                g = Util.g(resources2, R.raw.camera_v4_license_fr);
                g2 = Util.g(resources2, R.raw.camera_v4_privacy_fr);
            }
        }
        LocalLicense localLicense = new LocalLicense(g2, g, (String) null);
        if ("CN".equalsIgnoreCase(b)) {
            localLicense.mPlan = Util.f(resources2, R.raw.camera_v4_plan);
        }
        return localLicense;
    }

    public static void jumpToV3PrivacyPage(Context context, boolean z) {
        LocalLicense v4LocalLicense = z ? getV4LocalLicense(context.getResources()) : getV3LocalLicense(context.getResources());
        Intent intent = new Intent(context, PluginLicenseActivity.class);
        intent.putExtra("title", context.getString(R.string.privacy_title));
        intent.putExtra(PluginLicenseActivity.LICENSE_HTML_CONTENT_RES, v4LocalLicense.mPrivacy);
        context.startActivity(intent);
    }

    public static void jumpToV3UpgradePrivacyPage(Context context) {
        LocalLicense v3UpgradeLicense = getV3UpgradeLicense(context.getResources());
        Intent intent = new Intent(context, PluginLicenseActivity.class);
        intent.putExtra("title", context.getString(R.string.privacy_title));
        intent.putExtra(PluginLicenseActivity.LICENSE_HTML_CONTENT_RES, v3UpgradeLicense.mPrivacy);
        context.startActivity(intent);
    }
}
