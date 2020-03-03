package com.mi.global.shop.util;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.shop.model.SyncModel;
import com.mi.global.shop.util.Utils;
import com.mi.log.LogUtil;
import com.mi.util.SignUtils;
import com.sina.weibo.sdk.statistic.LogBuilder;
import java.io.File;
import java.util.HashMap;
import org.json.JSONObject;

public class SkinUtil {
    public static final String A = "KEY_ACCOUNT_AWAITING_PAYMENT_ICON";
    public static final String B = "KEY_ACCOUNT_SHIPPING_ICON";
    public static final String C = "KEY_ACCOUNT_RETURNS_ICON";
    public static final String D = "KEY_TAB_STORE_REGISTE";
    public static final String E = "KEY_TAB_STORE_STAR_PRODUCTS";
    public static final String F = "KEY_TAB_STORE_STAR_ACCESSORIES";
    public static final String G = "KEY_FESTIVAL_TITLE_GIF_BG";
    public static final String H = "KEY_FESTIVAL_PULL_BG";
    public static final String I = "KEY_FESTIVAL_PULL_GIF_ITEM";
    public static final String J = "1";
    public static final String K = "header_title_bg.jpg";
    public static final String L = "header_title_icon.png";
    public static final String M = "header_cart_icon.png";
    public static final String N = "bottom_tab_bg";
    public static final String O = "icon_main_home_normal";
    public static final String P = "icon_main_home_selected";
    public static final String Q = "icon_main_category_normal";
    public static final String R = "icon_main_category_selected";
    public static final String S = "icon_main_discover_normal";
    public static final String T = "icon_main_discover_selected";
    public static final String U = "icon_main_account_normal";
    public static final String V = "icon_main_account_selected";
    public static final String W = "icon_main_service_normal";
    public static final String X = "icon_main_service_selected";
    public static final String Y = "account_info_bg.jpg";
    public static final String Z = "account_india_3x_info_bg.png";

    /* renamed from: a  reason: collision with root package name */
    public static final String f7106a = "pref_skin_download_id";
    public static final String aa = "icon_order_awaiting_payment.png";
    public static final String ab = "icon_order_shipping.png";
    public static final String ac = "icon_order_returns.png";
    public static final String ad = "icon_home_register.jpg";
    public static final String ae = "icon_home_star_products.jpg";
    public static final String af = "icon_home_star_accessories.jpg";
    public static final String ag = "festival_title_bg.gif";
    public static final String ah = "festival_pull_bg.jpg";
    public static final String ai = "festival_pull_item.gif";
    public static String aj = null;
    public static String ak = null;
    public static boolean al = false;
    public static int am = 0;
    public static SkinListener an = null;
    /* access modifiers changed from: private */
    public static final String ao = "SkinUtil";
    private static final String[] ap = {".gif", ".png", ".jpg"};
    public static final String b = "pref_skin_download_url";
    public static final String c = "pref_skin_theme";
    public static final String d = "pref_skin_md5";
    public static final String e = "pref_skin_switch";
    public static final String f = "pref_skin_completed";
    public static final String g = "pref_skin_start_time";
    public static final String h = "pref_skin_end_time";
    public static HashMap<String, String> i = new HashMap<>();
    public static final String j = "KEY_HEADER_STATUSBAR";
    public static final String k = "KEY_HEADER_TITLE_BG";
    public static final String l = "KEY_HEADER_TITLE_ICON";
    public static final String m = "KEY_HEADER_CART_ICON";
    public static final String n = "KEY_TAB_BG";
    public static final String o = "KEY_TAB_STORE_NORMAL";
    public static final String p = "KEY_TAB_STORE_SELECTED";
    public static final String q = "KEY_TAB_PRODUCTS_NORMAL";
    public static final String r = "KEY_TAB_PRODUCTS_SELECTED";
    public static final String s = "KEY_TAB_DISCOVER_NORMAL";
    public static final String t = "KEY_TAB_DISCOVER_SELECTED";
    public static final String u = "KEY_TAB_ACCOUNT_NORMAL";
    public static final String v = "KEY_TAB_ACCOUNT_SELECTED";
    public static final String w = "KEY_TAB_SERVICE_NORMAL";
    public static final String x = "KEY_TAB_SERVICE_SELECTED";
    public static final String y = "KEY_ACCOUNT_INFO_BG";
    public static final String z = "KEY_ACCOUNT_INDIA_3X_INFO_BG";

    public interface SkinListener {
        void a();
    }

    public static void a(Context context) {
        al = false;
        i.clear();
        try {
            File externalFilesDir = context.getExternalFilesDir((String) null);
            if (externalFilesDir != null) {
                aj = externalFilesDir.getAbsolutePath() + "/skin/";
                File file = new File(aj);
                if (!file.exists()) {
                    file.mkdir();
                }
                boolean booleanPref = Utils.Preference.getBooleanPref(context, e, false);
                boolean booleanPref2 = Utils.Preference.getBooleanPref(context, f, false);
                if (booleanPref && booleanPref2) {
                    long longPref = Utils.Preference.getLongPref(context, g, 0);
                    long longPref2 = Utils.Preference.getLongPref(context, h, 0);
                    long currentTimeMillis = System.currentTimeMillis() / 1000;
                    String str = ao;
                    LogUtil.b(str, "now:" + currentTimeMillis + "###end:" + longPref2);
                    if (currentTimeMillis >= longPref && currentTimeMillis < longPref2) {
                        al = true;
                    }
                }
                if (al) {
                    ak = String.format("%s%s/", new Object[]{aj, Utils.Preference.getStringPref(context, c, "")});
                    HashMap<String, String> hashMap = i;
                    hashMap.put(j, ak + "1");
                    HashMap<String, String> hashMap2 = i;
                    hashMap2.put(k, ak + K);
                    HashMap<String, String> hashMap3 = i;
                    hashMap3.put(l, ak + L);
                    HashMap<String, String> hashMap4 = i;
                    hashMap4.put(m, ak + M);
                    HashMap<String, String> hashMap5 = i;
                    hashMap5.put(n, ak + N);
                    HashMap<String, String> hashMap6 = i;
                    hashMap6.put(o, ak + O);
                    HashMap<String, String> hashMap7 = i;
                    hashMap7.put(p, ak + P);
                    HashMap<String, String> hashMap8 = i;
                    hashMap8.put(q, ak + Q);
                    HashMap<String, String> hashMap9 = i;
                    hashMap9.put(r, ak + R);
                    HashMap<String, String> hashMap10 = i;
                    hashMap10.put(s, ak + S);
                    HashMap<String, String> hashMap11 = i;
                    hashMap11.put(t, ak + T);
                    HashMap<String, String> hashMap12 = i;
                    hashMap12.put(u, ak + U);
                    HashMap<String, String> hashMap13 = i;
                    hashMap13.put(v, ak + V);
                    HashMap<String, String> hashMap14 = i;
                    hashMap14.put(w, ak + W);
                    HashMap<String, String> hashMap15 = i;
                    hashMap15.put(x, ak + X);
                    HashMap<String, String> hashMap16 = i;
                    hashMap16.put(y, ak + Y);
                    HashMap<String, String> hashMap17 = i;
                    hashMap17.put(z, ak + Z);
                    HashMap<String, String> hashMap18 = i;
                    hashMap18.put(A, ak + aa);
                    HashMap<String, String> hashMap19 = i;
                    hashMap19.put(B, ak + ab);
                    HashMap<String, String> hashMap20 = i;
                    hashMap20.put(C, ak + ac);
                    HashMap<String, String> hashMap21 = i;
                    hashMap21.put(D, ak + ad);
                    HashMap<String, String> hashMap22 = i;
                    hashMap22.put(E, ak + ae);
                    HashMap<String, String> hashMap23 = i;
                    hashMap23.put(F, ak + af);
                    HashMap<String, String> hashMap24 = i;
                    hashMap24.put(G, ak + ag);
                    HashMap<String, String> hashMap25 = i;
                    hashMap25.put(H, ak + ah);
                    HashMap<String, String> hashMap26 = i;
                    hashMap26.put(I, ak + ai);
                }
            }
        } catch (Exception unused) {
        }
    }

    public static String a(String str) {
        int lastIndexOf;
        if (i == null) {
            return "";
        }
        String str2 = i.get(str);
        if (TextUtils.isEmpty(str2) || (lastIndexOf = str2.lastIndexOf("/")) == -1) {
            return "";
        }
        if (str2.indexOf(46, lastIndexOf) != -1) {
            return !b(str2) ? "" : str2;
        }
        for (String str3 : ap) {
            if (b(str2 + str3)) {
                return str2 + str3;
            }
        }
        return "";
    }

    public static boolean b(String str) {
        return new File(str).exists();
    }

    public static Uri c(String str) {
        if (i == null) {
            return null;
        }
        String str2 = i.get(str);
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        File file = new File(str2);
        if (file.exists()) {
            return Uri.fromFile(file);
        }
        return null;
    }

    public static void a(Context context, SkinListener skinListener) {
        JSONObject optJSONObject;
        JSONObject optJSONObject2;
        if (!TextUtils.isEmpty(aj) && SyncModel.response != null && (optJSONObject = SyncModel.response.optJSONObject("data")) != null && (optJSONObject2 = optJSONObject.optJSONObject("resurface")) != null) {
            String optString = optJSONObject2.optString("md5");
            String optString2 = optJSONObject2.optString("url");
            String optString3 = optJSONObject2.optString("theme");
            am = optJSONObject2.optInt("duration");
            long optLong = optJSONObject2.optLong(LogBuilder.j);
            if (System.currentTimeMillis() / 1000 <= optLong) {
                Utils.Preference.setBooleanPref(context, e, optJSONObject2.optBoolean("switch"));
                Utils.Preference.setLongPref(context, g, Long.valueOf(optJSONObject2.optLong(LogBuilder.i)));
                Utils.Preference.setLongPref(context, h, Long.valueOf(optLong));
                Utils.Preference.setStringPref(context, b, optString2);
                Utils.Preference.setStringPref(context, c, optString3);
                if (!Utils.Preference.getStringPref(context, d, "").equals(optString)) {
                    Utils.Preference.setStringPref(context, d, optString);
                    if (TextUtils.isEmpty(optString2) || TextUtils.isEmpty(optString) || TextUtils.isEmpty(optString3)) {
                        Utils.Preference.setBooleanPref(context, e, false);
                        return;
                    }
                    a(context, optString2, optString3);
                    an = skinListener;
                }
            }
        }
    }

    public static void a(Context context, String str, String str2) {
        Utils.Preference.setBooleanPref(context, f, false);
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Constants.TitleMenu.MENU_DOWNLOAD);
        DownloadManager.Request visibleInDownloadsUi = new DownloadManager.Request(Uri.parse(str.trim())).setNotificationVisibility(2).setVisibleInDownloadsUi(false);
        try {
            visibleInDownloadsUi.getClass().getDeclaredMethod("setFileSize", new Class[]{Long.class}).invoke(visibleInDownloadsUi, new Object[]{500});
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            File file = new File(String.format("%s%s.zip", new Object[]{aj, str2}));
            Uri fromFile = Uri.fromFile(file);
            if (file.exists()) {
                file.delete();
            }
            visibleInDownloadsUi.setDestinationUri(fromFile);
            Utils.Preference.setLongPref(context, f7106a, Long.valueOf(downloadManager.enqueue(visibleInDownloadsUi)));
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    public static class DownloadCompletedReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            if (com.xiaomi.smarthome.download.DownloadManager.D.equals(intent.getAction())) {
                long longPref = Utils.Preference.getLongPref(context, SkinUtil.f7106a, 0);
                if (longPref == intent.getLongExtra(com.xiaomi.smarthome.download.DownloadManager.G, 0)) {
                    a(context, longPref);
                }
            }
        }

        private void a(Context context, long j) {
            DownloadManager.Query query = new DownloadManager.Query();
            query.setFilterById(new long[]{j});
            Cursor query2 = ((DownloadManager) context.getSystemService(Constants.TitleMenu.MENU_DOWNLOAD)).query(query);
            if (query2 == null) {
                a(context);
                return;
            }
            if (query2.moveToFirst()) {
                int i = query2.getInt(query2.getColumnIndex("status"));
                int columnIndex = query2.getColumnIndex("local_uri");
                if (i == 8) {
                    LogUtil.b(SkinUtil.ao, "skin download success");
                    a(context, query2.getString(columnIndex));
                } else if (i == 16) {
                    LogUtil.b(SkinUtil.ao, "skin download fail");
                    a(context);
                }
            }
            query2.close();
        }

        private void a(Context context) {
            SkinUtil.an = null;
            Utils.Preference.setBooleanPref(context, SkinUtil.e, false);
            Utils.Preference.setBooleanPref(context, SkinUtil.f, false);
            Utils.Preference.setLongPref(context, SkinUtil.g, 0L);
            Utils.Preference.setLongPref(context, SkinUtil.h, 0L);
            Utils.Preference.setStringPref(context, SkinUtil.b, "");
            Utils.Preference.setStringPref(context, SkinUtil.c, "");
            Utils.Preference.setStringPref(context, SkinUtil.d, "");
        }

        private void a(Context context, String str) {
            String stringPref = Utils.Preference.getStringPref(context, SkinUtil.d, "");
            if (TextUtils.isEmpty(stringPref) || TextUtils.isEmpty(str)) {
                a(context);
                return;
            }
            String substring = str.substring(str.lastIndexOf(47) + 1);
            if (SignUtils.a(SkinUtil.aj + substring, stringPref)) {
                LogUtil.b(SkinUtil.ao, "skin check md5 success");
                File file = new File(String.format("%s%s/", new Object[]{SkinUtil.aj, Utils.Preference.getStringPref(context, SkinUtil.c, "")}));
                if (file.exists()) {
                    Utils.Files.b(file);
                }
                file.mkdir();
                if (Utils.Files.b(SkinUtil.aj + substring, file.getAbsolutePath())) {
                    LogUtil.b(SkinUtil.ao, "skin unzip success");
                    Utils.Preference.setBooleanPref(context, SkinUtil.f, true);
                    SkinUtil.a(context);
                    if (SkinUtil.an != null) {
                        SkinUtil.an.a();
                        return;
                    }
                    return;
                }
                LogUtil.b(SkinUtil.ao, "skin unzip fail");
            }
            LogUtil.b(SkinUtil.ao, "skin check md5 fail");
            a(context);
        }
    }
}
