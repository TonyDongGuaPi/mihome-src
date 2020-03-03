package com.xiaomi.jr.mipay.common.http;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.Toast;
import com.google.gson.Gson;
import com.xiaomi.jr.common.utils.MifiLog;
import com.xiaomi.jr.http.HttpCallback;
import com.xiaomi.jr.mipay.common.model.MipayResponse;
import java.util.Locale;
import retrofit2.Call;

public abstract class MipayHttpCallback<T extends MipayResponse> extends HttpCallback<T> {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10943a = "MipayHttpCallback";

    public MipayHttpCallback(Fragment fragment) {
        super(fragment);
    }

    public MipayHttpCallback(Activity activity) {
        super(activity);
    }

    /* access modifiers changed from: protected */
    public void a(Call<T> call, T t) {
        if (t.j == 200) {
            a(t);
            return;
        }
        ExceptionHandler.a(t.j, t.k);
        a(t.j, t.k, t, (Throwable) null);
        MifiLog.e(f10943a, "error occurs with 200 for " + call.request().toString() + ", result: " + new Gson().toJson((Object) t));
    }

    public void a(int i, String str, T t, Throwable th) {
        Context context;
        if (this.d == null || this.d.get() == null) {
            context = (this.e == null || this.e.get() == null) ? null : ((Activity) this.e.get()).getApplicationContext();
        } else {
            context = ((Fragment) this.d.get()).getActivity().getApplicationContext();
        }
        if (context != null) {
            Toast.makeText(context, String.format(Locale.getDefault(), "%s [%d]", new Object[]{str, Integer.valueOf(i)}), 1).show();
        }
    }
}
