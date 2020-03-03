package com.xiaomi.smarthome.framework.page.verify.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.hardware.biometrics.BiometricPrompt;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.Handler;
import android.os.Vibrator;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.hannto.printservice.hanntoprintservice.entity.PrinterParmater;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.page.verify.callback.OnFingerPrintVerifyCallback;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import javax.crypto.Cipher;

@TargetApi(23)
public class FingerPrintOpenVerifyDialog extends FingerprintManager.AuthenticationCallback {

    /* renamed from: a  reason: collision with root package name */
    private static final long[] f17080a = {100, 500};
    private static final String b = "FingerPrintOpenVerifyDialog";
    private final boolean c;
    private FingerprintManager d;
    private Context e;
    private MLAlertDialog f;
    /* access modifiers changed from: private */
    public DialogInterface.OnClickListener g;
    /* access modifiers changed from: private */
    public OnFingerPrintVerifyCallback h;
    private FingerprintManager.CryptoObject i;
    private CancellationSignal j;
    private boolean k = true;
    private DialogInterface.OnClickListener l = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialogInterface, int i) {
            if (FingerPrintOpenVerifyDialog.this.h != null) {
                FingerPrintOpenVerifyDialog.this.h.a((CharSequence) PrinterParmater.q);
            }
        }
    };
    private DialogInterface.OnClickListener m = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialogInterface, int i) {
            if (FingerPrintOpenVerifyDialog.this.g != null) {
                FingerPrintOpenVerifyDialog.this.g.onClick(dialogInterface, i);
            }
        }
    };

    public FingerPrintOpenVerifyDialog(Context context, FingerprintManager.CryptoObject cryptoObject, OnFingerPrintVerifyCallback onFingerPrintVerifyCallback, boolean z) {
        this.e = context;
        this.i = cryptoObject;
        this.h = onFingerPrintVerifyCallback;
        this.c = z;
        this.d = (FingerprintManager) this.e.getSystemService(FingerprintManager.class);
    }

    public void a(DialogInterface.OnClickListener onClickListener) {
        this.g = onClickListener;
    }

    public void a(String str) {
        BiometricPrompt.CryptoObject cryptoObject;
        if (this.k) {
            this.k = false;
            this.j = new CancellationSignal();
            if (Build.VERSION.SDK_INT >= 28) {
                BiometricPrompt.Builder negativeButton = new BiometricPrompt.Builder(this.e).setTitle(this.e.getString(R.string.device_more_fingerprint_title)).setDescription("").setNegativeButton(this.e.getString(R.string.cancel), this.e.getMainExecutor(), this.l);
                if (this.c) {
                    try {
                        Method method = negativeButton.getClass().getMethod("setPositiveButton", new Class[]{CharSequence.class, Executor.class, DialogInterface.OnClickListener.class});
                        method.setAccessible(true);
                        method.invoke(negativeButton, new Object[]{this.e.getString(R.string.device_more_fingerprint_input_pw), this.e.getMainExecutor(), this.m});
                    } catch (Exception e2) {
                        negativeButton.setNegativeButton(this.e.getString(R.string.device_more_fingerprint_input_pw), this.e.getMainExecutor(), this.m);
                        LogUtil.b(b, Log.getStackTraceString(e2));
                    }
                }
                BiometricPrompt build = negativeButton.build();
                if (this.i.getCipher() != null) {
                    cryptoObject = new BiometricPrompt.CryptoObject(this.i.getCipher());
                } else if (this.i.getSignature() != null) {
                    cryptoObject = new BiometricPrompt.CryptoObject(this.i.getSignature());
                } else {
                    cryptoObject = new BiometricPrompt.CryptoObject(this.i.getMac());
                }
                build.authenticate(cryptoObject, this.j, this.e.getMainExecutor(), new BiometricPrompt.AuthenticationCallback() {
                    public void onAuthenticationError(int i, CharSequence charSequence) {
                        FingerPrintOpenVerifyDialog.this.onAuthenticationError(i, charSequence);
                    }

                    public void onAuthenticationHelp(int i, CharSequence charSequence) {
                        FingerPrintOpenVerifyDialog.this.onAuthenticationHelp(i, charSequence);
                    }

                    public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult authenticationResult) {
                        FingerPrintOpenVerifyDialog.this.a(authenticationResult.getCryptoObject().getCipher());
                    }

                    public void onAuthenticationFailed() {
                        FingerPrintOpenVerifyDialog.this.onAuthenticationFailed();
                    }
                });
                return;
            }
            if (this.f == null) {
                ImageView imageView = new ImageView(this.e);
                imageView.setLayoutParams(new LinearLayout.LayoutParams(-1, -2, 17.0f));
                imageView.setImageResource(R.drawable.xiaomi_sm_fingerprint_demo);
                MLAlertDialog.Builder b2 = new MLAlertDialog.Builder(this.e).a((CharSequence) this.e.getString(R.string.device_more_fingerprint_title)).b((int) R.string.cancel, this.l).b((View) imageView);
                if (this.c) {
                    b2.a((CharSequence) this.e.getString(R.string.device_more_fingerprint_input_pw), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (FingerPrintOpenVerifyDialog.this.g != null) {
                                FingerPrintOpenVerifyDialog.this.g.onClick(dialogInterface, i);
                            }
                        }
                    });
                }
                b2.a(false);
                this.f = b2.b();
            }
            if (!this.f.isShowing()) {
                this.f.show();
            }
            this.f.setTitle(str);
            this.d.authenticate(this.i, this.j, 0, this, (Handler) null);
        }
    }

    public void a() {
        if (!this.k) {
            this.k = true;
            this.j.cancel();
            this.j = null;
        }
    }

    public void onAuthenticationError(int i2, CharSequence charSequence) {
        if (this.h == null) {
            return;
        }
        if (i2 == 5) {
            this.h.a();
        } else if (i2 != 7) {
            this.h.a(charSequence);
        } else {
            this.h.b(charSequence);
        }
    }

    public void onAuthenticationHelp(int i2, CharSequence charSequence) {
        if (!this.k && this.f != null && !TextUtils.isEmpty(charSequence)) {
            this.f.setTitle(charSequence);
        }
    }

    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult authenticationResult) {
        a(authenticationResult.getCryptoObject().getCipher());
    }

    /* access modifiers changed from: private */
    public void a(Cipher cipher) {
        if (!this.k && this.h != null) {
            ToastUtil.a((CharSequence) this.e.getString(R.string.device_more_verify_success));
            this.h.a(cipher);
        }
    }

    public void onAuthenticationFailed() {
        if (!this.k && this.f != null) {
            this.f.setTitle(this.e.getString(R.string.device_more_fingerprint_error));
            Vibrator vibrator = (Vibrator) this.e.getSystemService("vibrator");
            if (vibrator != null) {
                vibrator.vibrate(f17080a, -1);
            }
        }
    }

    public void b() {
        a();
        if (this.f != null) {
            this.f.dismiss();
        }
    }
}
