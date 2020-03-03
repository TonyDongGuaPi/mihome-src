package com.xiaomi.jr.http;

import android.app.Activity;
import android.support.v4.app.Fragment;
import com.xiaomi.jr.common.utils.MifiLog;
import com.xiaomi.jr.http.model.MiFiResponse;
import retrofit2.Call;

public abstract class MifiHttpCallback<T extends MiFiResponse> extends HttpCallback<T> {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10815a = "MifiHttpCallback";

    public MifiHttpCallback(Fragment fragment) {
        super(fragment);
    }

    public MifiHttpCallback(Activity activity) {
        super(activity);
    }

    /* access modifiers changed from: protected */
    public void a(Call<T> call, T t) {
        if (t.c()) {
            a(t);
        } else {
            a(t.a(), t.b(), t, new Throwable());
        }
    }

    public void a(int i, String str, T t, Throwable th) {
        MifiLog.e(f10815a, "request failure: code=" + i + ", error=" + str + ", result=" + t);
    }
}
