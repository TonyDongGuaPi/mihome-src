package com.mi.global.shop.base.request;

import android.content.Context;
import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.mi.global.shop.base.ApplicationAgent;
import com.mi.global.shop.base.BaseResult;
import com.mi.global.shop.base.R;
import com.mi.util.MiToast;

public abstract class SimpleCallback<T extends BaseResult> implements Response.ErrorListener, Response.Listener<T> {
    public abstract void success(T t);

    public void onResponse(T t) {
        if (t == null) {
            error(ApplicationAgent.f5586a.getString(R.string.service_unavailiable));
        } else if (t.errno != 0) {
            error(t.errmsg);
        } else {
            try {
                success(t);
            } catch (Exception e) {
                error(e.toString());
            }
        }
    }

    public void onErrorResponse(VolleyError volleyError) {
        volleyError.printStackTrace();
        if (volleyError instanceof NetworkError) {
            error(ApplicationAgent.f5586a.getString(R.string.network_unavaliable));
        } else if (volleyError instanceof ServerError) {
            error(ApplicationAgent.f5586a.getString(R.string.service_unavailiable));
        } else if (volleyError instanceof TimeoutError) {
            error(ApplicationAgent.f5586a.getString(R.string.service_unavailiable));
        } else if (volleyError instanceof ParseError) {
            error(ApplicationAgent.f5586a.getString(R.string.service_unavailiable));
        } else {
            error(ApplicationAgent.f5586a.getString(R.string.service_unavailiable));
        }
        if (ApplicationAgent.b) {
            volleyError.printStackTrace();
        }
    }

    public void error(String str) {
        MiToast.a((Context) ApplicationAgent.f5586a, (CharSequence) str, 0);
    }
}
