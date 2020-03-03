package com.alipay.deviceid.module.x;

import com.alipay.deviceid.module.rpc.mrpc.core.RpcException;
import com.alipay.sdk.util.l;
import java.lang.reflect.Type;
import org.json.JSONObject;

public final class bs extends bp {
    public bs(Type type, byte[] bArr) {
        super(type, bArr);
    }

    public final Object a() {
        try {
            String str = new String(this.b);
            StringBuilder sb = new StringBuilder("threadid = ");
            sb.append(Thread.currentThread().getId());
            sb.append("; rpc response:  ");
            sb.append(str);
            JSONObject jSONObject = new JSONObject(str);
            int i = jSONObject.getInt(l.f1135a);
            if (i != 1000) {
                throw new RpcException(Integer.valueOf(i), jSONObject.optString("tips"));
            } else if (this.f896a == String.class) {
                return jSONObject.optString("result");
            } else {
                return ah.a(jSONObject.optString("result"), this.f896a);
            }
        } catch (Exception e) {
            StringBuilder sb2 = new StringBuilder("response  =");
            sb2.append(new String(this.b));
            sb2.append(":");
            sb2.append(e);
            throw new RpcException((Integer) 10, sb2.toString() == null ? "" : e.getMessage());
        }
    }
}
