package com.xiaomi.jr.mipay.codepay.presenter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xiaomi.jr.common.utils.PreferenceUtils;
import com.xiaomi.jr.http.MifiHttpCallback;
import com.xiaomi.jr.http.model.MiFiResponse;
import com.xiaomi.jr.mipay.codepay.R;
import com.xiaomi.jr.mipay.codepay.api.ApiManager;
import com.xiaomi.jr.mipay.codepay.data.CodePayConfirmParams;
import com.xiaomi.jr.mipay.codepay.data.PayCode;
import com.xiaomi.jr.mipay.codepay.data.PayResult;
import com.xiaomi.jr.mipay.codepay.data.PayResultShowResult;
import com.xiaomi.jr.mipay.codepay.data.PayType;
import com.xiaomi.jr.mipay.codepay.presenter.CodePayContract;
import com.xiaomi.jr.mipay.codepay.ui.CodePayFragment;
import com.xiaomi.jr.mipay.codepay.util.CodePayConstants;
import com.xiaomi.jr.mipay.codepay.util.CodePayUtils;
import com.xiaomi.jr.mipay.common.MipayManager;
import com.xiaomi.jr.mipay.common.http.MipayHttpCallback;
import com.xiaomi.jr.mipay.common.model.ProcessInfo;
import com.xiaomi.jr.mipay.common.util.MipayClient;
import com.xiaomi.jr.mipay.pay.verifier.PayPassVerifier;
import java.util.Iterator;
import java.util.List;

public class CodePayPresenter extends Presenter implements CodePayContract.Presenter {

    /* renamed from: a  reason: collision with root package name */
    public static final int f10901a = -1;
    public static final int b = 0;
    private static final int d = 0;
    private static final int e = 1001;
    private static final int f = 1002;
    private static final int g = 1000;
    private static final int h = 60000;
    private CodePayContract.View i;
    /* access modifiers changed from: private */
    public String j;
    /* access modifiers changed from: private */
    public boolean k;
    /* access modifiers changed from: private */
    public PayType l;
    /* access modifiers changed from: private */
    public List<PayType> m;
    /* access modifiers changed from: private */
    public boolean n;
    /* access modifiers changed from: private */
    public boolean o;
    private boolean p;
    private boolean q;
    private Handler r = new Handler() {
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1001:
                    if (!CodePayPresenter.this.o && CodePayPresenter.this.l != null) {
                        CodePayPresenter.this.a(CodePayPresenter.this.l.mAuthCode);
                        return;
                    }
                    return;
                case 1002:
                    if (!CodePayPresenter.this.o) {
                        CodePayPresenter.this.l();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };

    public CodePayPresenter(CodePayContract.View view) {
        this.i = view;
    }

    public CodePayContract.View f() {
        return this.i;
    }

    public void d() {
        this.c = ((CodePayFragment) this.i).getActivity().getApplicationContext();
        this.n = false;
        a(false);
        a();
    }

    public void a(boolean z) {
        f().a(true);
        if (!z) {
            a(true, true);
        }
        l();
    }

    public void a() {
        ApiManager.b().a().enqueue(new MifiHttpCallback<MiFiResponse<Integer>>((CodePayFragment) f()) {
            public void a(MiFiResponse<Integer> miFiResponse) {
                CodePayPresenter.this.a(false, false);
                Integer d = miFiResponse.d();
                boolean z = true;
                if (d == null || !(d.intValue() == 1 || d.intValue() == 2)) {
                    z = false;
                }
                CodePayPresenter.this.f().b(z);
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(boolean z, boolean z2) {
        if (z2) {
            f().a(0, true);
            return;
        }
        if (z) {
            this.p = true;
        } else {
            this.q = true;
        }
        if (this.p && this.q) {
            f().a(0, false);
        }
    }

    public void b() {
        PreferenceUtils.b(k(), "codepay", CodePayConstants.l);
    }

    public PayType c() {
        return this.l;
    }

    public List<PayType> e() {
        return this.m;
    }

    public void a(int i2, Bundle bundle) {
        if (-1 == i2) {
            PayType payType = (PayType) bundle.getSerializable(CodePayConstants.k);
            if (TextUtils.equals("BINDCARD", payType.mPayType)) {
                f().c(false);
            } else {
                a(payType);
            }
        }
    }

    public void b(int i2, Bundle bundle) {
        int i3 = bundle.getInt("bind_success");
        if (!this.k || i3 == 1) {
            a(true);
        } else {
            f().b();
        }
    }

    public void a(int i2) {
        if (this.n) {
            n();
        } else if (-1 == i2) {
            PreferenceUtils.a(k(), "codepay", CodePayConstants.l, this.j);
            a(true);
        } else if (i2 == 0) {
            f().b();
        }
    }

    /* access modifiers changed from: private */
    public void a(PayType payType) {
        if (this.l != payType && payType != null) {
            this.l = payType;
            f().a(this.l);
        }
    }

    /* access modifiers changed from: private */
    public void l() {
        ApiManager.a().a(MipayClient.c(this.c).i(), PreferenceUtils.d(k(), "codepay", CodePayConstants.l)).enqueue(new MipayHttpCallback<PayCode>((CodePayFragment) this.i) {
            public void a(PayCode payCode) {
                boolean unused = CodePayPresenter.this.k = payCode.b;
                if (CodePayPresenter.this.k) {
                    CodePayPresenter.this.f().c(true);
                    return;
                }
                List unused2 = CodePayPresenter.this.m = payCode.d;
                int i = CodePayPresenter.this.l != null ? CodePayPresenter.this.l.mPayTypeId : payCode.c;
                CodePayPresenter.this.a(true, false);
                CodePayPresenter.this.a(a(i));
                boolean unused3 = CodePayPresenter.this.n = true;
                CodePayPresenter.this.n();
            }

            public void a(int i, String str, PayCode payCode, Throwable th) {
                if (i == 3000004) {
                    String unused = CodePayPresenter.this.j = payCode.f10893a;
                    CodePayPresenter.this.m();
                    return;
                }
                CodePayPresenter.this.a(true, false);
                if (CodePayPresenter.this.n) {
                    str = CodePayPresenter.this.k().getString(R.string.jr_mipay_refresh_failed);
                }
                CodePayPresenter.this.f().a(i, str, th);
                CodePayPresenter.this.g();
            }

            private PayType a(int i) {
                if (CodePayPresenter.this.m == null || CodePayPresenter.this.m.isEmpty()) {
                    return null;
                }
                if (i != -1) {
                    for (PayType payType : CodePayPresenter.this.m) {
                        if (i == payType.mPayTypeId) {
                            return payType;
                        }
                    }
                }
                Iterator it = CodePayPresenter.this.m.iterator();
                int i2 = 0;
                int i3 = 0;
                while (it.hasNext() && !((PayType) it.next()).mAvailable) {
                    i3++;
                }
                List f = CodePayPresenter.this.m;
                if (i3 < CodePayPresenter.this.m.size()) {
                    i2 = i3;
                }
                return (PayType) f.get(i2);
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        ApiManager.a().a(str, MipayClient.c(this.c).i(), PreferenceUtils.d(k(), "codepay", CodePayConstants.l)).enqueue(new MipayHttpCallback<PayResult>((CodePayFragment) this.i) {
            public void a(PayResult payResult) {
                if (payResult.f10894a == 1) {
                    CodePayPresenter.this.a((CodePayConfirmParams) ((PayResult) new Gson().fromJson(payResult.e(), new TypeToken<PayResult<CodePayConfirmParams>>() {
                    }.getType())).b);
                } else if (payResult.f10894a == 2) {
                    PayResult payResult2 = (PayResult) new Gson().fromJson(payResult.e(), new TypeToken<PayResult<PayResultShowResult>>() {
                    }.getType());
                    if (TextUtils.equals(((PayResultShowResult) payResult2.b).f10895a, "WAIT_PAY")) {
                        CodePayPresenter.this.n();
                    } else {
                        CodePayUtils.a((CodePayFragment) CodePayPresenter.this.f(), new Gson().toJson((Object) payResult2.b));
                    }
                } else {
                    CodePayPresenter.this.n();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(final CodePayConfirmParams codePayConfirmParams) {
        MipayManager.b().a(PayPassVerifier.k).enqueue(new MipayHttpCallback<ProcessInfo>((CodePayFragment) this.i) {
            public void a(ProcessInfo processInfo) {
                CodePayPresenter.this.f().a(processInfo.b, codePayConfirmParams);
            }

            public void a(int i, String str, ProcessInfo processInfo, Throwable th) {
                super.a(i, str, processInfo, th);
                CodePayPresenter.this.n();
            }
        });
    }

    /* access modifiers changed from: private */
    public void m() {
        g();
        f().a();
    }

    /* access modifiers changed from: private */
    public void n() {
        if (this.n && !this.o) {
            if (!this.r.hasMessages(1001)) {
                this.r.sendEmptyMessageDelayed(1001, 1000);
            }
            if (!this.r.hasMessages(1002)) {
                this.r.sendEmptyMessageDelayed(1002, 60000);
            }
        }
    }

    public void g() {
        this.r.removeMessages(1001);
        this.r.removeMessages(1002);
    }

    public void h() {
        super.h();
        this.o = false;
        if (this.n) {
            l();
            n();
        }
    }

    public void i() {
        super.i();
        this.o = true;
        g();
    }

    public void a(int i2, int i3, Intent intent) {
        super.a(i2, i3, intent);
        if (i2 == 1004) {
            Bundle bundle = new Bundle();
            if (intent != null) {
                bundle = intent.getExtras();
            }
            b(i3, bundle);
        } else if (i2 == 1001) {
            a(i3, intent != null ? intent.getExtras() : null);
        } else if (i2 == 1003) {
            ((CodePayFragment) this.i).getActivity().setResult(i3);
            ((CodePayFragment) this.i).d();
        }
    }

    public void j() {
        this.r.removeMessages(1002);
        this.r.removeMessages(1001);
    }
}
