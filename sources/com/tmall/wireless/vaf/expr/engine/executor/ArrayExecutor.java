package com.tmall.wireless.vaf.expr.engine.executor;

import android.util.Log;
import com.tmall.wireless.vaf.expr.engine.DataManager;
import com.tmall.wireless.vaf.expr.engine.data.Data;
import com.tmall.wireless.vaf.expr.engine.data.Value;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ArrayExecutor extends ArithExecutor {
    private static final String j = "ArrayExecutor_TMTEST";

    public int a(Object obj) {
        int a2 = super.a(obj);
        Set<Object> b = b();
        if (b != null) {
            int i = -1;
            if (this.g > 0) {
                i = this.r.f();
            }
            Value c = c();
            if (c == null) {
                Log.e(j, "param is null");
                return a2;
            } else if (a(i, this.r.d(), c, b)) {
                return 1;
            } else {
                Log.e(j, "call array failed");
                return a2;
            }
        } else {
            Log.e(j, "execute findObject failed");
            return a2;
        }
    }

    /* access modifiers changed from: protected */
    public boolean a(int i, int i2, Value value, Set<Object> set) {
        JSONArray jSONArray;
        String a2 = this.p.a(i);
        Object c = value.c();
        if (c instanceof Integer) {
            boolean z = true;
            int intValue = ((Integer) c).intValue();
            for (Object next : set) {
                if (next instanceof DataManager) {
                    jSONArray = (JSONArray) this.t.a(a2);
                } else if (next instanceof JSONObject) {
                    jSONArray = ((JSONObject) next).optJSONArray(a2);
                } else if (next instanceof JSONArray) {
                    jSONArray = (JSONArray) next;
                } else {
                    Log.e(j, "error object:" + next);
                    return false;
                }
                try {
                    Object obj = jSONArray.get(intValue);
                    Data a3 = this.s.a(i2);
                    if (obj == null) {
                        a3.a();
                    } else if (!a3.a(obj)) {
                        Log.e(j, "call set return value failed:" + obj);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(j, "set value failed");
                    z = false;
                }
            }
            return z;
        }
        Log.e(j, "param not integer");
        return false;
    }

    /* access modifiers changed from: protected */
    public Value c() {
        byte d = this.r.d();
        Data a2 = a((int) d);
        if (a2 != null) {
            return a2.f;
        }
        Log.e(j, "read param failed:" + d);
        return null;
    }
}
