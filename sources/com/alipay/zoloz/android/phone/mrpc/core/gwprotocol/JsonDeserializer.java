package com.alipay.zoloz.android.phone.mrpc.core.gwprotocol;

import android.util.Log;
import com.alipay.sdk.util.l;
import com.alipay.zoloz.b.a;
import com.alipay.zoloz.mobile.a.a.b;
import java.lang.reflect.Type;
import org.json.JSONObject;

public class JsonDeserializer extends AbstractDeserializer {
    public JsonDeserializer(Type type, byte[] bArr) {
        super(type, bArr);
    }

    public Object parser() {
        String str;
        try {
            String str2 = new String(this.mData);
            Log.v("HttpCaller", "threadid = " + Thread.currentThread().getId() + "; rpc response:  " + str2);
            JSONObject jSONObject = new JSONObject(str2);
            int i = jSONObject.getInt(l.f1135a);
            if (i != 1000) {
                throw new b(Integer.valueOf(i), jSONObject.optString("tips"));
            } else if (this.mType == String.class) {
                return jSONObject.optString("result");
            } else {
                return a.a(jSONObject.optString("result"), this.mType);
            }
        } catch (Exception e) {
            if (("response  =" + new String(this.mData) + ":" + e) == null) {
                str = "";
            } else {
                str = e.getMessage();
            }
            throw new b((Integer) 10, str);
        }
    }
}
