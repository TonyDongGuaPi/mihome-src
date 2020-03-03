package com.xiaomi.jr.idcardverifier.utils;

import android.content.Context;
import android.text.TextUtils;
import com.megvii.idcardquality.bean.IDCardAttr;
import com.xiaomi.jr.common.utils.Client;
import com.xiaomi.jr.common.utils.MifiHostsUtils;
import com.xiaomi.jr.common.utils.NetworkUtils;
import com.xiaomi.jr.idcardverifier.IDCardVerifyActivity;
import com.xiaomi.jr.idcardverifier.R;
import com.xiaomi.jr.idcardverifier.http.SimpleHttpRequest;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class VerifyStatUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10877a = (MifiHostsUtils.c("https://api.jr.mi.com/") + "images/stat.gif");
    private static Context b;
    private static Map<String, String> c;
    private static Map<String, Long> d = new HashMap();

    public static void a(Context context, Map<String, String> map) {
        b = context.getApplicationContext();
        c = map;
    }

    public static void a(int i) {
        if (b != null) {
            a(b, R.string.stat_category_idcard_verifier, i, c);
        }
    }

    public static void a(int i, Map<String, String> map) {
        if (b != null) {
            if (map == null) {
                map = c;
            } else {
                map.putAll(c);
            }
            a(b, R.string.stat_category_idcard_verifier, i, map);
        }
    }

    public static void a(int i, IDCardAttr.IDCardSide iDCardSide) {
        if (b != null) {
            String b2 = b(i, iDCardSide);
            if (!TextUtils.isEmpty(b2)) {
                a(b, b.getString(R.string.stat_category_idcard_verifier), b2, c);
            }
        }
    }

    public static void a(int i, IDCardVerifyActivity.State state) {
        if (b != null) {
            String c2 = c(i, state);
            if (!TextUtils.isEmpty(c2)) {
                a(b, b.getString(R.string.stat_category_idcard_verifier), c2, c);
            }
        }
    }

    public static void b(int i, IDCardVerifyActivity.State state) {
        if (b != null) {
            String a2 = a(state);
            String b2 = b(state);
            if (!TextUtils.isEmpty(a2) && !TextUtils.isEmpty(b2)) {
                a(b, b.getString(R.string.stat_category_idcard_verifier), b.getString(i, new Object[]{a2, b2}), c);
            }
        }
    }

    public static void a(int i, IDCardVerifyActivity.State state, IDCardAttr.IDCardSide iDCardSide) {
        if (iDCardSide != null && b != null) {
            String a2 = a(state);
            if (!TextUtils.isEmpty(a2)) {
                String string = b.getString(R.string.stat_category_idcard_verifier);
                a(b, string, b.getString(i, new Object[]{a2, b.getString(iDCardSide == IDCardAttr.IDCardSide.IDCARD_SIDE_FRONT ? R.string.stat_value_id_card_side_front : R.string.stat_value_id_card_side_back)}), c);
            }
        }
    }

    public static String b(int i, IDCardAttr.IDCardSide iDCardSide) {
        if (iDCardSide == null || b == null) {
            return null;
        }
        return b.getString(i, new Object[]{b.getString(iDCardSide == IDCardAttr.IDCardSide.IDCARD_SIDE_FRONT ? R.string.stat_value_id_card_side_front : R.string.stat_value_id_card_side_back)});
    }

    public static String c(int i, IDCardVerifyActivity.State state) {
        if (b != null) {
            String a2 = a(state);
            if (!TextUtils.isEmpty(a2)) {
                return b.getString(i, new Object[]{a2});
            }
        }
        return null;
    }

    public static void a(String str) {
        if (!TextUtils.isEmpty(str) && b != null) {
            d.put(str, Long.valueOf(System.currentTimeMillis()));
        }
    }

    public static void b(String str) {
        if (!TextUtils.isEmpty(str) && b != null && d.containsKey(str)) {
            long currentTimeMillis = System.currentTimeMillis() - d.get(str).longValue();
            HashMap hashMap = new HashMap();
            hashMap.put("duration", String.format("%.1f", new Object[]{Float.valueOf((((float) currentTimeMillis) * 1.0f) / 1000.0f)}));
            hashMap.putAll(c);
            a(b, b.getString(R.string.stat_category_idcard_verifier), str, (Map<String, String>) hashMap);
            d.remove(str);
        }
    }

    public static void c(String str) {
        if (!TextUtils.isEmpty(str) && b != null) {
            d.remove(str);
        }
    }

    private static String a(IDCardVerifyActivity.State state) {
        if (state == null || b == null) {
            return null;
        }
        switch (state) {
            case SCAN_FRONT_SIDE_ONGOING:
            case SCAN_BACK_SIDE_ONGOING:
            case UPLOAD_SCAN_FRONT_SIDE_ONGOING:
            case UPLOAD_SCAN_BACK_SIDE_ONGOING:
            case UPLOAD_SCAN_FRONT_SIDE_SUCCESS:
            case UPLOAD_SCAN_BACK_SIDE_SUCCESS:
                return b.getString(R.string.stat_value_state_scan);
            case PICK_FRONT_SIDE_PREVIEW:
            case PICK_BACK_SIDE_PREVIEW:
            case UPLOAD_AND_VERIFY_PICK_PHOTO_ONGOING:
                return b.getString(R.string.stat_value_state_pick);
            case CAPTURE_FRONT_SIDE_ONGOING:
            case CAPTURE_BACK_SIDE_ONGOING:
            case CAPTURE_FRONT_SIDE_COMPLETE:
            case CAPTURE_BACK_SIDE_COMPLETE:
            case UPLOAD_CAPTURE_FRONT_SIDE_ONGOING:
            case UPLOAD_CAPTURE_BACK_SIDE_ONGOING:
            case UPLOAD_CAPTURE_FRONT_SIDE_SUCCESS:
            case UPLOAD_CAPTURE_BACK_SIDE_SUCCESS:
                return b.getString(R.string.stat_value_state_capture);
            default:
                return null;
        }
    }

    private static String b(IDCardVerifyActivity.State state) {
        if (state == null && b == null) {
            return null;
        }
        switch (state) {
            case SCAN_FRONT_SIDE_ONGOING:
            case UPLOAD_SCAN_FRONT_SIDE_ONGOING:
            case UPLOAD_SCAN_FRONT_SIDE_SUCCESS:
            case PICK_FRONT_SIDE_PREVIEW:
            case CAPTURE_FRONT_SIDE_ONGOING:
            case CAPTURE_FRONT_SIDE_COMPLETE:
            case UPLOAD_CAPTURE_FRONT_SIDE_ONGOING:
            case UPLOAD_CAPTURE_FRONT_SIDE_SUCCESS:
                return b.getString(R.string.stat_value_id_card_side_front);
            case SCAN_BACK_SIDE_ONGOING:
            case UPLOAD_SCAN_BACK_SIDE_ONGOING:
            case UPLOAD_SCAN_BACK_SIDE_SUCCESS:
            case PICK_BACK_SIDE_PREVIEW:
            case UPLOAD_AND_VERIFY_PICK_PHOTO_ONGOING:
            case CAPTURE_BACK_SIDE_ONGOING:
            case CAPTURE_BACK_SIDE_COMPLETE:
            case UPLOAD_CAPTURE_BACK_SIDE_ONGOING:
            case UPLOAD_CAPTURE_BACK_SIDE_SUCCESS:
                return b.getString(R.string.stat_value_id_card_side_back);
            default:
                return null;
        }
    }

    private static void a(Context context, int i, int i2, Map<String, String> map) {
        a(context, context.getString(i), context.getString(i2), map);
    }

    private static void a(Context context, String str, String str2, Map<String, String> map) {
        HashMap hashMap = new HashMap(VerifyUtils.a(context));
        if (map != null) {
            hashMap.putAll(map);
        }
        hashMap.put("deviceIdMd5", Client.c(context));
        hashMap.put(IjkMediaPlayer.OnNativeInvokeListener.ARG_IP, NetworkUtils.a(true));
        hashMap.put("networkType", NetworkUtils.d(context));
        hashMap.put("packageName", context.getPackageName());
        hashMap.put("productType", str);
        hashMap.put("pageTitle", str2);
        hashMap.put("t", String.valueOf(System.currentTimeMillis()));
        JSONObject jSONObject = new JSONObject(hashMap);
        HashMap hashMap2 = new HashMap();
        hashMap2.put("data", jSONObject.toString());
        SimpleHttpRequest.a(f10877a, hashMap2, (SimpleHttpRequest.Listener) null);
    }
}
