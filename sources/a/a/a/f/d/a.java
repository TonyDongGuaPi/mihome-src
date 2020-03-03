package a.a.a.f.d;

import android.content.Context;
import android.support.annotation.Nullable;
import in.cashify.otex.ExchangeSetup;
import org.json.JSONObject;

public class a extends a.a.a.f.b {

    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public final Context f405a;

        public b(Context context) {
            this.f405a = context;
        }

        @Nullable
        public a a(ExchangeSetup exchangeSetup, a.a.a.a aVar) {
            try {
                JSONObject jSONObject = new JSONObject();
                if (exchangeSetup != null) {
                    exchangeSetup.a(jSONObject);
                }
                if (aVar != null) {
                    aVar.a(jSONObject);
                }
                return new a(this.f405a, jSONObject.toString());
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
