package com.xiaomi.jr.web.sms;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.jr.common.utils.MifiLog;
import com.xiaomi.jr.web.R;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;

public class SMSMonitor {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11073a = "MiFiSMSMonitor";
    private static volatile SMSReceiver b;

    public interface SmsVerificationCodeListener {
        void onReceive(String str);
    }

    public static void a(Context context, String str, SmsVerificationCodeListener smsVerificationCodeListener) {
        if (b == null) {
            b = new SMSReceiver();
        }
        b.start(context, str, smsVerificationCodeListener);
    }

    public static void a(Context context) {
        if (b != null) {
            b.stop(context);
            b = null;
        }
    }

    static String a(Context context, String str, String str2) {
        String[] strArr;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (TextUtils.isEmpty(str2)) {
            strArr = new String[]{context.getString(R.string.verification_code_prefix) + "[0-9]{6}\\D"};
        } else {
            strArr = a(str2);
        }
        if (strArr == null) {
            return null;
        }
        for (String compile : strArr) {
            Matcher matcher = Pattern.compile(compile).matcher(str);
            if (matcher.find()) {
                return matcher.group(0).replaceAll("[^0-9]", "");
            }
        }
        return null;
    }

    private static String[] a(String str) {
        String[] strArr = null;
        try {
            JSONArray jSONArray = new JSONArray(str);
            String[] strArr2 = new String[jSONArray.length()];
            int i = 0;
            while (i < strArr2.length) {
                try {
                    strArr2[i] = jSONArray.getString(i);
                    i++;
                } catch (JSONException e) {
                    JSONException jSONException = e;
                    strArr = strArr2;
                    e = jSONException;
                    MifiLog.e(f11073a, "buildPatternRuleArray: " + e);
                    return strArr;
                }
            }
            return strArr2;
        } catch (JSONException e2) {
            e = e2;
            MifiLog.e(f11073a, "buildPatternRuleArray: " + e);
            return strArr;
        }
    }
}
