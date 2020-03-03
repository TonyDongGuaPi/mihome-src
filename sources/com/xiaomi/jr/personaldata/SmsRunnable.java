package com.xiaomi.jr.personaldata;

import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.provider.Telephony;
import com.xiaomi.jr.common.utils.MifiLog;
import com.xiaomi.jr.personaldata.CollectRunnable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SmsRunnable extends CollectRunnable {
    private static final long c = 2592000000L;
    private static final long d = 31104000000L;
    private static Filter e;

    public interface Filter {
        boolean a(String str);
    }

    /* access modifiers changed from: package-private */
    public int b() {
        return 3;
    }

    /* access modifiers changed from: package-private */
    public String c() {
        return "sms";
    }

    /* access modifiers changed from: package-private */
    public long d() {
        return 2592000000L;
    }

    /* access modifiers changed from: package-private */
    public long e() {
        return d;
    }

    SmsRunnable(Context context) {
        super(context);
    }

    public static void a(Filter filter) {
        e = filter;
    }

    /* access modifiers changed from: package-private */
    public String[] a() {
        return new String[]{"android.permission.READ_SMS"};
    }

    /* access modifiers changed from: package-private */
    public CollectRunnable.CollectResult a(long j, long j2) throws Exception {
        long j3 = j2;
        if (Build.VERSION.SDK_INT < 19) {
            return null;
        }
        int i = 0;
        Cursor query = f().getContentResolver().query(Telephony.Sms.CONTENT_URI, new String[]{"address", "body", "date"}, "date>" + j + " and " + "date" + "<=" + j3, (String[]) null, "date asc");
        if (query == null) {
            return null;
        }
        if (query.getCount() == 0) {
            query.close();
            return null;
        }
        CollectRunnable.CollectResult collectResult = new CollectRunnable.CollectResult();
        JSONArray jSONArray = new JSONArray();
        long j4 = 0;
        while (true) {
            if (!query.moveToNext()) {
                break;
            }
            String string = query.getString(i);
            if (string != null && (e == null || e.a(string))) {
                String string2 = query.getString(1);
                long j5 = query.getLong(2);
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("address", string);
                    jSONObject.put("body", string2);
                    jSONObject.put("date", j5);
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
                j4 += (long) (jSONObject.toString().length() + 1);
                if (j4 <= 10000) {
                    collectResult.b = j5;
                    jSONArray.put(jSONObject);
                    i = 0;
                } else if (jSONArray.length() == 0) {
                    return null;
                }
            }
        }
        if (!query.moveToNext()) {
            collectResult.b = j3;
        }
        query.close();
        collectResult.f11002a = jSONArray.toString();
        MifiLog.c("TestData", "collected " + c() + " count=" + jSONArray.length() + " size=" + j4);
        return collectResult;
    }
}
