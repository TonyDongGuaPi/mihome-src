package com.mi.global.shop.buy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.mi.global.shop.R;
import com.mi.global.shop.activity.WebActivity;
import com.mi.global.shop.newmodel.user.exchangecoupon.coupon.NewOTExResult;
import com.mi.global.shop.request.SimpleCallback;
import com.mi.global.shop.request.SimpleJsonRequest;
import com.mi.global.shop.ui.BaseFragment;
import com.mi.global.shop.util.ConnectionHelper;
import com.mi.util.MiToast;
import com.mi.util.RequestQueueUtil;
import in.cashify.otex.ExchangeError;
import in.cashify.otex.ExchangeManager;
import in.cashify.otex.ExchangeSetup;
import in.cashify.otex.OTEx;
import in.cashify.otex.SetupError;
import java.util.HashMap;
import java.util.Map;

public class OTExFragment extends BaseFragment implements ExchangeManager.OnExchangeCallback {

    /* renamed from: a  reason: collision with root package name */
    public static final String f6842a = "OTExFragment";
    private DefaultRetryPolicy b = new DefaultRetryPolicy(20000, 1, 1.0f);
    private View c;
    private ExchangeSetup d;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.c = layoutInflater.inflate(R.layout.shop_fragment_otex, viewGroup, false);
        return this.c;
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.d = new ExchangeSetup();
        this.d.a(122001);
        OTEx.a(getChildFragmentManager(), (LinearLayout) view.findViewById(R.id.layout_group), f6842a, this.d);
    }

    public void a(String str, String str2) {
        String str3 = f6842a;
        Log.d(str3, "-----onRegistrationRequest-----" + str + "---" + str2);
        c(str, str2);
    }

    public void b(String str, String str2) {
        String str3 = f6842a;
        Log.d(str3, "onQuoteRequest" + str + "---" + str2);
        d(str, str2);
    }

    public void a(SetupError setupError) {
        String str = f6842a;
        Log.d(str, "onExchangeSetup" + setupError.toString());
        a(setupError.b());
    }

    public void a(int i, String str, String str2) {
        String str3 = f6842a;
        Log.d(str3, "onTestComplete" + i + "---" + str + "---" + str2);
    }

    public void a(String str) {
        String str2 = f6842a;
        Log.d(str2, "onTestStart" + str);
    }

    /* renamed from: com.mi.global.shop.buy.OTExFragment$6  reason: invalid class name */
    static /* synthetic */ class AnonymousClass6 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f6848a = new int[ExchangeError.Kind.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|(3:15|16|18)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(18:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|18) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0040 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x004b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0056 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                in.cashify.otex.ExchangeError$Kind[] r0 = in.cashify.otex.ExchangeError.Kind.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f6848a = r0
                int[] r0 = f6848a     // Catch:{ NoSuchFieldError -> 0x0014 }
                in.cashify.otex.ExchangeError$Kind r1 = in.cashify.otex.ExchangeError.Kind.ROOTED_DEVICE_NOT_SUPPORTED     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = f6848a     // Catch:{ NoSuchFieldError -> 0x001f }
                in.cashify.otex.ExchangeError$Kind r1 = in.cashify.otex.ExchangeError.Kind.EMULATOR_NOT_SUPPORTED     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = f6848a     // Catch:{ NoSuchFieldError -> 0x002a }
                in.cashify.otex.ExchangeError$Kind r1 = in.cashify.otex.ExchangeError.Kind.DEVICE_NOT_SUPPORTED     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = f6848a     // Catch:{ NoSuchFieldError -> 0x0035 }
                in.cashify.otex.ExchangeError$Kind r1 = in.cashify.otex.ExchangeError.Kind.DEVICE_NOT_ELIGIBLE     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = f6848a     // Catch:{ NoSuchFieldError -> 0x0040 }
                in.cashify.otex.ExchangeError$Kind r1 = in.cashify.otex.ExchangeError.Kind.EXCHANGE_NOT_VALID     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r0 = f6848a     // Catch:{ NoSuchFieldError -> 0x004b }
                in.cashify.otex.ExchangeError$Kind r1 = in.cashify.otex.ExchangeError.Kind.SERVER_ERROR     // Catch:{ NoSuchFieldError -> 0x004b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                int[] r0 = f6848a     // Catch:{ NoSuchFieldError -> 0x0056 }
                in.cashify.otex.ExchangeError$Kind r1 = in.cashify.otex.ExchangeError.Kind.INVALID_PIN_CODE     // Catch:{ NoSuchFieldError -> 0x0056 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0056 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0056 }
            L_0x0056:
                int[] r0 = f6848a     // Catch:{ NoSuchFieldError -> 0x0062 }
                in.cashify.otex.ExchangeError$Kind r1 = in.cashify.otex.ExchangeError.Kind.PERMISSION_NOT_GRANTED     // Catch:{ NoSuchFieldError -> 0x0062 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0062 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0062 }
            L_0x0062:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mi.global.shop.buy.OTExFragment.AnonymousClass6.<clinit>():void");
        }
    }

    private void a(ExchangeError.Kind kind) {
        int i = AnonymousClass6.f6848a[kind.ordinal()];
        b("");
    }

    private void c(String str, String str2) {
        final String str3 = str;
        final String str4 = str2;
        AnonymousClass3 r0 = new StringRequest(1, ConnectionHelper.bj(), new Response.Listener<String>() {
            /* renamed from: a */
            public void onResponse(String str) {
                ExchangeManager exchangeManager = (ExchangeManager) OTExFragment.this.getChildFragmentManager().findFragmentByTag(OTExFragment.f6842a);
                if (exchangeManager != null) {
                    exchangeManager.a(str);
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                OTExFragment.this.b("");
            }
        }) {
            /* access modifiers changed from: protected */
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap hashMap = new HashMap(2);
                hashMap.put("_k", str3);
                hashMap.put("_d", str4);
                return hashMap;
            }
        };
        r0.setTag(f6842a);
        RequestQueueUtil.a().add(r0);
    }

    private void d(String str, String str2) {
        final String str3 = str;
        final String str4 = str2;
        AnonymousClass5 r0 = new SimpleJsonRequest(ConnectionHelper.bk(), NewOTExResult.class, (Map) null, new SimpleCallback<NewOTExResult>() {
            public void a(NewOTExResult newOTExResult) {
                if (newOTExResult != null) {
                    OTExFragment.this.b(newOTExResult.quote_id);
                } else {
                    OTExFragment.this.b("");
                }
            }

            public void a(String str) {
                OTExFragment.this.b("");
            }
        }) {
            /* access modifiers changed from: protected */
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap hashMap = new HashMap(2);
                hashMap.put("_k", str3);
                hashMap.put("_d", str4);
                return hashMap;
            }

            public Map<String, String> getHeaders() throws AuthFailureError {
                return new HashMap(1);
            }
        };
        r0.setTag(f6842a);
        r0.setRetryPolicy(this.b);
        RequestQueueUtil.a().add(r0);
    }

    /* access modifiers changed from: private */
    public void b(String str) {
        if (e()) {
            OTEx.a(getChildFragmentManager(), f6842a);
            Intent intent = new Intent(getActivity(), WebActivity.class);
            if (!TextUtils.isEmpty(str)) {
                intent.putExtra("url", ConnectionHelper.bl() + "qid=" + str);
            } else {
                intent.putExtra("url", ConnectionHelper.bn());
                MiToast.a((Context) getActivity(), (CharSequence) getString(R.string.user_exchange_sdk_fail), 1);
            }
            startActivity(intent);
            getActivity().finish();
        }
    }
}
