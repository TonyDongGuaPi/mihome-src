package com.mi.global.shop.request;

import android.content.Context;
import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.mi.global.shop.R;
import com.mi.global.shop.ShopApp;
import com.mi.global.shop.locale.LocaleHelper;
import com.mi.global.shop.newmodel.BaseResult;
import com.mi.global.shop.newmodel.usercenter.NewUserInfoResult;
import com.mi.multimonitor.CrashReport;
import com.mi.util.MiToast;

public abstract class SimpleCallback<T extends BaseResult> implements Response.ErrorListener, Response.Listener<T> {
    public abstract void a(T t);

    /* renamed from: b */
    public void onResponse(T t) {
        if (t == null) {
            a(ShopApp.g().getString(R.string.shop_service_unavailiable));
            return;
        }
        if (t.errno == 20001 && !(t instanceof NewUserInfoResult) && !ShopApp.h.f.equals("community_sdk")) {
            ShopApp.d();
        }
        if (t.errno != 0) {
            a(t.errmsg);
        } else {
            a(t);
        }
    }

    public void onErrorResponse(VolleyError volleyError) {
        if (volleyError instanceof NetworkError) {
            a(ShopApp.g().getString(R.string.shop_network_unavaliable));
        } else if (volleyError instanceof ServerError) {
            a(ShopApp.g().getString(R.string.shop_service_unavailiable));
        } else if (volleyError instanceof TimeoutError) {
            a(ShopApp.g().getString(R.string.shop_service_unavailiable));
        } else if (volleyError instanceof ParseError) {
            a(ShopApp.g().getString(R.string.shop_service_unavailiable));
            if (!LocaleHelper.q()) {
                CrashReport.postCrash(Thread.currentThread(), (Throwable) volleyError);
            }
        } else {
            a(ShopApp.g().getString(R.string.shop_service_unavailiable));
        }
        if (ShopApp.j()) {
            volleyError.printStackTrace();
        }
    }

    public void a(String str) {
        MiToast.a((Context) ShopApp.g(), (CharSequence) str, 0);
    }
}
