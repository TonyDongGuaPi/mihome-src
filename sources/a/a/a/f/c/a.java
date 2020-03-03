package a.a.a.f.c;

import android.content.Context;
import android.support.annotation.Nullable;
import in.cashify.otex.ExchangeSetup;
import java.util.Map;
import org.json.JSONObject;

public class a extends a.a.a.f.b {

    public static class b {
        @Nullable
        public a a(Context context, ExchangeSetup exchangeSetup, a.a.a.a aVar, Map<String, a.a.a.b> map) {
            try {
                JSONObject jSONObject = new JSONObject();
                JSONObject jSONObject2 = new JSONObject();
                if (exchangeSetup != null) {
                    exchangeSetup.a(jSONObject2);
                }
                if (aVar != null) {
                    aVar.a(jSONObject2);
                }
                jSONObject.put("di", jSONObject2);
                JSONObject jSONObject3 = new JSONObject();
                for (String next : map.keySet()) {
                    jSONObject3.put(next, map.get(next).b());
                }
                jSONObject.put("dr", jSONObject3);
                jSONObject.put("ts", System.currentTimeMillis());
                return new a(context, jSONObject.toString());
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public a(Context context, String str) {
        super(context, str);
    }
}
