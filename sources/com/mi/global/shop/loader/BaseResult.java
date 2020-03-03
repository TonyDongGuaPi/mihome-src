package com.mi.global.shop.loader;

import android.text.TextUtils;
import com.mi.global.shop.R;
import com.mi.global.shop.ShopApp;
import com.mi.multimonitor.Request;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public abstract class BaseResult {
    private static Map<ResultStatus, Integer> c = new HashMap();

    /* renamed from: a  reason: collision with root package name */
    public boolean f6930a;
    public String b;
    private ResultStatus d = ResultStatus.OK;
    private boolean e;
    private String f;

    public enum ResultStatus {
        NETWROK_ERROR,
        SERVICE_ERROR,
        DATA_ERROR,
        AUTH_ERROR,
        OK,
        CLOSED
    }

    public abstract BaseResult a();

    /* access modifiers changed from: protected */
    public abstract int b();

    static {
        c.put(ResultStatus.NETWROK_ERROR, Integer.valueOf(R.string.shop_network_unavaliable));
        c.put(ResultStatus.SERVICE_ERROR, Integer.valueOf(R.string.shop_service_unavailiable));
        c.put(ResultStatus.DATA_ERROR, Integer.valueOf(R.string.shop_data_error));
        c.put(ResultStatus.AUTH_ERROR, Integer.valueOf(R.string.shop_auth_error));
    }

    public static int a(ResultStatus resultStatus) {
        return c.get(resultStatus).intValue();
    }

    public ResultStatus c() {
        return this.d;
    }

    public void b(ResultStatus resultStatus) {
        this.d = resultStatus;
    }

    public void a(JSONObject jSONObject) {
        this.e = true;
        this.f6930a = false;
        this.f = ShopApp.g().getResources().getString(R.string.shop_data_error);
        if (jSONObject != null) {
            if (jSONObject.optInt(Request.RESULT_CODE_KEY) == 0) {
                this.e = false;
                return;
            }
            String optString = jSONObject.optString("errmsg");
            if (!TextUtils.isEmpty(optString)) {
                this.f = optString;
            }
            JSONObject optJSONObject = jSONObject.optJSONObject("data");
            if (optJSONObject != null) {
                this.b = optJSONObject.optString("close_link");
                this.f6930a = !TextUtils.isEmpty(this.b);
            }
        }
    }

    public boolean d() {
        return this.e;
    }

    public String e() {
        return this.f;
    }
}
