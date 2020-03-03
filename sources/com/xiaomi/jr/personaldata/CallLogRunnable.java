package com.xiaomi.jr.personaldata;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.provider.CallLog;
import com.xiaomi.jr.common.utils.MifiLog;
import com.xiaomi.jr.personaldata.CollectRunnable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CallLogRunnable extends CollectRunnable {
    private static final long c = 2592000000L;
    private static final long d = 31104000000L;

    /* access modifiers changed from: package-private */
    public int b() {
        return 2;
    }

    /* access modifiers changed from: package-private */
    public String c() {
        return "calllog";
    }

    /* access modifiers changed from: package-private */
    public long d() {
        return 2592000000L;
    }

    /* access modifiers changed from: package-private */
    public long e() {
        return d;
    }

    CallLogRunnable(Context context) {
        super(context);
    }

    /* access modifiers changed from: package-private */
    public String[] a() {
        return new String[]{"android.permission.READ_CALL_LOG"};
    }

    /* access modifiers changed from: protected */
    @SuppressLint({"MissingPermission"})
    public CollectRunnable.CollectResult a(long j, long j2) throws Exception {
        long j3 = j2;
        int i = 0;
        int i2 = 2;
        Cursor query = f().getContentResolver().query(CallLog.Calls.CONTENT_URI, new String[]{"number", "type", "date", "duration"}, "date>" + j + " and " + "date" + "<=" + j3, (String[]) null, "date asc");
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
            int i3 = query.getInt(1);
            long j5 = query.getLong(i2);
            CollectRunnable.CollectResult collectResult2 = collectResult;
            long j6 = query.getLong(3);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("number", string);
                jSONObject.put("type", i3);
                jSONObject.put("date", j5);
                jSONObject.put("duration", j6);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            j4 += (long) (jSONObject.toString().length() + 1);
            if (j4 <= 10000) {
                collectResult = collectResult2;
                collectResult.b = j5;
                jSONArray.put(jSONObject);
                i = 0;
                i2 = 2;
            } else if (jSONArray.length() == 0) {
                return null;
            } else {
                collectResult = collectResult2;
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
