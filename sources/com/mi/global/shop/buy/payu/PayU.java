package com.mi.global.shop.buy.payu;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import com.alipay.sdk.sys.a;
import com.coloros.mcssdk.mode.CommandMessage;
import com.mi.global.shop.buy.EMIfragment;
import com.mi.global.shop.buy.OrderdetailFragment;
import com.mi.global.shop.buy.model.EMIBank;
import com.payu.sdk.Constants;
import com.payu.sdk.Params;
import com.payu.sdk.Payment;
import com.payu.sdk.exceptions.HashException;
import com.payu.sdk.exceptions.MissingParameterException;
import com.xiaomi.accountsdk.service.DeviceInfoResult;
import com.xiaomi.stat.d;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

public class PayU {
    public static final String A = "instrument_id";
    public static final String B = "var1";
    public static final String C = "var2";
    public static final String D = "var3";
    public static final String E = "var4";
    public static final String F = "var5";
    public static final String G = "var6";
    public static final String H = "var7";
    public static final String I = "var8";
    public static final String J = "var9";
    public static final String K = "VISA";
    public static final String L = "LASER";
    public static final String M = "DISCOVER";
    public static final String N = "MAES";
    public static final String O = "MAST";
    public static final String P = "AMEX";
    public static final String Q = "DINR";
    public static final String R = "JCB";
    public static final String S = "SMAE";
    public static JSONObject T = null;
    public static JSONObject U = null;
    public static JSONObject V = null;
    public static List<EMIBank> W = null;
    public static JSONArray X = null;
    public static JSONArray Y = null;
    public static JSONArray Z = null;

    /* renamed from: a  reason: collision with root package name */
    public static String f6891a = null;
    private static final int aC = 3;
    private static final String aD = "2.4";
    private static final String[] aE = {l, m, n, o};
    private static final String[] aF = {p};
    private static final String[] aG = {j};
    public static JSONArray aa = null;
    public static JSONArray ab = null;
    public static JSONArray ac = null;
    public static JSONObject ad = null;
    public static ArrayList<OrderdetailFragment.PaymentMethod> ae = new ArrayList<>();
    public static HashMap<String, Integer> af = null;
    public static JSONObject ag = null;
    public static String ah = null;
    public static String ai = null;
    public static String aj = null;
    public static String ak = null;
    public static String al = null;
    public static String am = null;
    public static String an = null;
    public static String ao = null;
    public static String ap = null;
    public static String aq = null;
    public static String ar = null;
    public static PaymentMode[] as = null;
    public static ProgressDialog at = null;
    public static long au = 0;
    public static boolean av = false;
    public static Set<String> aw = new HashSet();
    static final Map<PaymentMode, String> ax = new HashMap();
    private static final String ay = "PayU";
    private static PayU az = null;
    public static final String b = "amount";
    public static final String c = "txnid";
    public static final String d = "productinfo";
    public static final String e = "surl";
    public static final String f = "furl";
    public static final String g = "key";
    public static final String h = "email";
    public static final String i = "firstname";
    public static final String j = "bankcode";
    public static final String k = "mode";
    public static final String l = "ccnum";
    public static final String m = "ccexpmon";
    public static final String n = "ccexpyr";
    public static final String o = "ccname";
    public static final String p = "ccvv";
    public static final String q = "user_credentials";
    public static final String r = "offer_key";
    public static final String s = "drop_category";
    public static final String t = "enforce_paymethod";
    public static final String u = "payment_options";
    public static final String v = "store_card";
    public static final String w = "device_type";
    public static final String x = "1";
    public static final String y = "showCustom";
    public static final String z = "instrument_type";
    private Activity aA;
    private String aB;

    public enum PaymentMode {
        CC,
        DC,
        NB,
        EMI,
        PAYU_MONEY,
        STORED_CARDS,
        CASH,
        UPI
    }

    public static String b() {
        return aD;
    }

    public static int c() {
        return 3;
    }

    static {
        ax.put(PaymentMode.CC, "Credit Card");
        ax.put(PaymentMode.DC, "Debit Card");
        ax.put(PaymentMode.NB, "Net Banking");
        ax.put(PaymentMode.EMI, EMIfragment.f6830a);
        ax.put(PaymentMode.PAYU_MONEY, "PayUMoney");
        ax.put(PaymentMode.STORED_CARDS, "Stored Cards");
        ax.put(PaymentMode.CASH, "Cash Card");
    }

    public static void a() {
        az = null;
    }

    private PayU(Activity activity, String str) {
        this.aB = str;
        this.aA = activity;
    }

    public static synchronized PayU a(Activity activity) {
        PayU payU;
        synchronized (PayU.class) {
            az = null;
            try {
                Bundle bundle = activity.getPackageManager().getApplicationInfo(activity.getPackageName(), 128).metaData;
                az = new PayU(activity, (String) null);
            } catch (PackageManager.NameNotFoundException e2) {
                Log.e(ay, "Failed to load meta-data, NameNotFound: " + e2.getMessage());
            } catch (NullPointerException e3) {
                Log.e(ay, "Failed to load meta-data, NullPointer: " + e3.getMessage());
            }
            payU = az;
        }
        return payU;
    }

    public Intent a(double d2, HashMap<String, String> hashMap, PaymentMode[] paymentModeArr) {
        Intent intent = new Intent();
        intent.putExtra("amount", d2);
        if (hashMap == null) {
            return intent;
        }
        for (String next : hashMap.keySet()) {
            intent.putExtra(next, hashMap.get(next));
        }
        intent.putExtra("key", f6891a);
        if (hashMap.containsKey(s)) {
            ah = hashMap.get(s).replaceAll("\\s+", "");
        }
        if (hashMap.containsKey(t)) {
            ai = hashMap.get(t);
        }
        if (hashMap.containsKey(q)) {
            aj = hashMap.get(q);
        }
        if (paymentModeArr != null) {
            int[] iArr = new int[paymentModeArr.length];
            int length = paymentModeArr.length;
            int i2 = 0;
            int i3 = 0;
            while (i2 < length) {
                iArr[i3] = paymentModeArr[i2].ordinal();
                i2++;
                i3++;
            }
            intent.putExtra("payment_options", iArr);
        }
        return intent;
    }

    public String a(Payment payment, Params params) throws MissingParameterException, HashException {
        params.put(d.aa, payment.getMode().toString());
        params.put("device_type", "1");
        params.put(A, Settings.Secure.getString(this.aA.getContentResolver(), DeviceInfoResult.BUNDLE_KEY_ANDROID_ID));
        params.put(z, "Manufacturer: " + Build.MANUFACTURER + " Model: " + Build.MODEL + "  Product: " + Build.PRODUCT);
        switch (payment.getMode()) {
            case CC:
                params.put(j, "CC");
                a(aF, params);
                if (params.get("store_card_token").length() <= 1) {
                    if (params.get(p).length() >= 3) {
                        a(aE, params);
                        break;
                    } else {
                        params.put(p, "123");
                        params.put(m, "12");
                        params.put(n, "2090");
                        break;
                    }
                }
                break;
            case NB:
                a(aG, params);
                break;
            case PAYU_MONEY:
                params.put(j, "payuw");
                params.put(d.aa, "wallet");
                break;
            case EMI:
                a(aE, params);
                a(aF, params);
                break;
            case UPI:
                params.put(d.aa, "UPI");
                params.put(t, params.get(j));
                break;
        }
        new StringBuffer();
        String str = "";
        try {
            for (String str2 : params.keySet()) {
                if (!str2.contentEquals("surl")) {
                    if (!str2.contentEquals("furl")) {
                        if (str2.contentEquals("amount")) {
                            DecimalFormat decimalFormat = new DecimalFormat("#.00");
                            str = str + str2 + "=" + decimalFormat.format(Double.valueOf(params.get(str2))) + a.b;
                        } else {
                            str = str + str2 + "=" + params.get(str2) + a.b;
                        }
                    }
                }
                str = str + str2 + "=" + URLEncoder.encode(params.get(str2), "UTF-8") + a.b;
            }
            return str + "hash=" + ak;
        } catch (NoSuchAlgorithmException unused) {
            throw new HashException();
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    private void a(String[] strArr, Params params) throws MissingParameterException {
        int length = strArr.length;
        int i2 = 0;
        while (i2 < length) {
            String str = strArr[i2];
            if (params.containsKey(str)) {
                i2++;
            } else {
                throw new MissingParameterException("Parameter " + str + " is missing");
            }
        }
        if (ak == null) {
            throw new MissingParameterException("Parameter Hash is missing");
        }
    }

    public List<NameValuePair> a(String str, HashMap<String, String> hashMap) throws NoSuchAlgorithmException {
        new StringBuilder();
        ArrayList arrayList = new ArrayList(4);
        arrayList.add(new BasicNameValuePair("key", f6891a));
        arrayList.add(new BasicNameValuePair(CommandMessage.COMMAND, str));
        for (int i2 = 1; i2 <= hashMap.size(); i2++) {
            arrayList.add(new BasicNameValuePair("var" + i2, String.valueOf(hashMap.get("var" + i2))));
        }
        if (str.contentEquals(Constants.GET_IBIBO_CODES)) {
            arrayList.add(new BasicNameValuePair("hash", ap));
        } else if (str.contentEquals(Constants.SAVE_USER_CARD)) {
            arrayList.add(new BasicNameValuePair("hash", ao));
        } else if (str.contentEquals(Constants.EDIT_USER_CARD)) {
            arrayList.add(new BasicNameValuePair("hash", an));
        } else if (str.contentEquals(Constants.DELETE_USER_CARD)) {
            arrayList.add(new BasicNameValuePair("hash", al));
        } else if (str.contentEquals(Constants.GET_USER_CARDS)) {
            arrayList.add(new BasicNameValuePair("hash", am));
        } else if (str.contentEquals(Constants.PAYMENT_RELATED_DETAILS)) {
            arrayList.add(new BasicNameValuePair("hash", ar));
        } else if (str.contentEquals(Constants.GET_VAS)) {
            arrayList.add(new BasicNameValuePair("hash", aq));
        }
        arrayList.add(new BasicNameValuePair("device_type", "1"));
        return arrayList;
    }
}
