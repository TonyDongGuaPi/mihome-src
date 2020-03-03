package com.xiaomi.jr.mipay.pay.verifier;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.miui.supportlite.app.AlertDialog;
import com.xiaomi.jr.common.app.ActivityChecker;
import com.xiaomi.jr.common.utils.Utils;
import com.xiaomi.jr.deeplink.DeeplinkUtils;
import com.xiaomi.jr.mipay.common.MipayConstants;
import com.xiaomi.jr.mipay.common.MipayManager;
import com.xiaomi.jr.mipay.common.http.HostManager;
import com.xiaomi.jr.mipay.common.http.MipayHttpCallback;
import com.xiaomi.jr.mipay.common.model.ProcessInfo;
import com.xiaomi.jr.mipay.common.util.PermissionHelper;
import com.xiaomi.jr.mipay.pay.verifier.PayPassVerifier;
import com.xiaomi.jr.mipay.pay.verifier.component.PasswordEditText;
import com.xiaomi.jr.mipay.pay.verifier.model.VerifyResult;
import com.xiaomi.jr.mipay.safekeyboard.SafeKeyboard;
import com.xiaomi.jr.mipay.safekeyboard.SafeKeyboardManager;
import com.xiaomi.jr.mipay.safekeyboard.SafeKeyboardView;
import com.xiaomi.jr.permission.Request;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class VerifierActivity extends BaseActivity {
    private static Map<String, Integer> j;

    /* renamed from: a  reason: collision with root package name */
    private SafeKeyboardView f10961a;
    private TextView b;
    /* access modifiers changed from: private */
    public PasswordEditText c;
    private Button d;
    private String e;
    /* access modifiers changed from: private */
    public VerifyResult f;
    private PasswordEditText.PasswordInputListener g = new PasswordEditText.PasswordInputListener() {
        public final void onPassInputFinish(boolean z) {
            VerifierActivity.this.a(z);
        }
    };
    private View.OnClickListener h = new View.OnClickListener() {
        public void onClick(View view) {
            PermissionHelper.a(VerifierActivity.this, new Request.Callback() {
                public void a(String str) {
                }

                public /* synthetic */ void b() {
                    Request.Callback.CC.$default$b(this);
                }

                public void a() {
                    String password = VerifierActivity.this.c.getPassword();
                    if (!TextUtils.isEmpty(password)) {
                        VerifierActivity.this.a(password);
                    } else {
                        Utils.a(VerifierActivity.this.getApplicationContext(), R.string.jr_mipay_password_error);
                    }
                }
            });
        }
    };
    private boolean i;

    /* access modifiers changed from: private */
    public /* synthetic */ void a(boolean z) {
        c();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.jr_mipay_paypass_verifier_activity_verifier);
        this.e = getIntent().getStringExtra(MipayConstants.e);
        if (!TextUtils.isEmpty(this.e)) {
            this.b = (TextView) findViewById(R.id.check_password_summary);
            this.c = (PasswordEditText) findViewById(R.id.check_password_edit);
            this.d = (Button) findViewById(R.id.button);
            this.f10961a = SafeKeyboardManager.a((Activity) this, SafeKeyboard.c);
            SafeKeyboardManager.a(this.f10961a);
            a();
            return;
        }
        throw new IllegalStateException("ProcessType is null");
    }

    private void a() {
        Integer num = j.get(this.e);
        if (num == null) {
            num = Integer.valueOf(R.string.jr_mipay_check_password_summary);
        }
        this.b.setText(num.intValue());
        SafeKeyboardManager.a((View) this.c, this.f10961a);
        c();
        this.c.setPassInputListener(this.g);
        this.d.setOnClickListener(this.h);
        this.f10961a.setOnKeyEventListener(new SafeKeyboardView.OnKeyEventListener() {
            public final void onKeyEvent(int i) {
                VerifierActivity.this.a(i);
            }
        });
        this.c.requestFocus();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(int i2) {
        if (i2 == -1) {
            d();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        SafeKeyboardManager.a((View) this.c);
        if (this.i) {
            b();
        }
        super.onDestroy();
    }

    public void finish() {
        super.finish();
        this.i = true;
    }

    private void b() {
        PayPassVerifier.VerifyPasswordListener verifyPasswordListener;
        WeakReference<PayPassVerifier.VerifyPasswordListener> c2 = PayPassVerifier.c();
        if (c2 != null && (verifyPasswordListener = (PayPassVerifier.VerifyPasswordListener) c2.get()) != null) {
            if (this.f == null) {
                verifyPasswordListener.a();
            } else {
                verifyPasswordListener.a(this.f);
            }
        }
    }

    private void c() {
        this.d.setEnabled(this.c.isPassInputFinish());
    }

    /* access modifiers changed from: private */
    public void a(final String str) {
        MipayManager.b().a(this.e).enqueue(new MipayHttpCallback<ProcessInfo>(this) {
            public void a(ProcessInfo processInfo) {
                VerifierManager.a().a(processInfo.b, str, false).enqueue(new MipayHttpCallback<VerifyResult>(VerifierActivity.this) {
                    public void a(VerifyResult verifyResult) {
                        VerifyResult unused = VerifierActivity.this.f = verifyResult;
                        VerifierActivity.this.finish();
                    }

                    public void a(int i, String str, VerifyResult verifyResult, Throwable th) {
                        if (i == 2000004) {
                            VerifierActivity.this.showProcessErrorDialog();
                        } else if (i == 2010003) {
                            VerifierActivity.this.a(verifyResult.b, verifyResult.c, verifyResult.f10971a);
                        }
                    }
                });
            }
        });
    }

    private void d() {
        DeeplinkUtils.openDeeplink(this, getString(R.string.jr_mipay_find_password), HostManager.a("resetPwd/resetPage"));
        this.c.clearPassword();
    }

    /* access modifiers changed from: private */
    public void a(String str, String str2, boolean z) {
        if (ActivityChecker.a((Activity) this)) {
            AlertDialog.Builder a2 = new AlertDialog.Builder(this).a((CharSequence) str).b((CharSequence) str2).b(R.string.jr_mipay_pass_err_find, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public final void onClick(DialogInterface dialogInterface, int i) {
                    VerifierActivity.this.c(dialogInterface, i);
                }
            }).a((DialogInterface.OnDismissListener) new DialogInterface.OnDismissListener() {
                public final void onDismiss(DialogInterface dialogInterface) {
                    VerifierActivity.this.a(dialogInterface);
                }
            });
            if (z) {
                a2.a(R.string.jr_mipay_pass_err_reinput, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        VerifierActivity.this.b(dialogInterface, i);
                    }
                });
            }
            Utils.a((DialogFragment) a2.a(), getSupportFragmentManager(), "passErr");
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void c(DialogInterface dialogInterface, int i2) {
        d();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(DialogInterface dialogInterface) {
        this.c.clearPassword();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(DialogInterface dialogInterface, int i2) {
        this.c.clearPassword();
    }

    /* access modifiers changed from: protected */
    public void showProcessErrorDialog() {
        if (ActivityChecker.a((Activity) this)) {
            Utils.a((DialogFragment) new AlertDialog.Builder(this).a(R.string.jr_mipay_process_expired).a(17039370, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public final void onClick(DialogInterface dialogInterface, int i) {
                    VerifierActivity.this.a(dialogInterface, i);
                }
            }).a(false).a(), getSupportFragmentManager(), "process error");
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(DialogInterface dialogInterface, int i2) {
        finish();
    }

    static {
        HashMap hashMap = new HashMap();
        hashMap.put(PayPassVerifier.c, Integer.valueOf(R.string.jr_mipay_check_password_unbind_summary));
        hashMap.put(PayPassVerifier.f, Integer.valueOf(R.string.jr_mipay_ring_check_password_to_bind));
        hashMap.put(PayPassVerifier.g, Integer.valueOf(R.string.jr_mipay_ring_check_password_to_unbind));
        hashMap.put(PayPassVerifier.h, Integer.valueOf(R.string.jr_mipay_check_password_summary));
        hashMap.put(PayPassVerifier.d, Integer.valueOf(R.string.jr_mipay_check_password_modify_password_summary));
        j = Collections.unmodifiableMap(hashMap);
    }
}
