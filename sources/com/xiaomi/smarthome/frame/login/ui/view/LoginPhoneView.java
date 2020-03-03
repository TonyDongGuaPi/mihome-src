package com.xiaomi.smarthome.frame.login.ui.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.frame.baseui.ToastManager;
import com.xiaomi.smarthome.frame.login.ui.LoginPhoneActivity;
import com.xiaomi.smarthome.frame.login.util.LoginUtil;
import com.xiaomi.smarthome.framework.permission.PermissionHelper;
import com.xiaomi.smarthome.library.common.network.NetworkUtils;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.yanzhenjie.permission.Action;
import java.lang.ref.WeakReference;
import java.util.List;

public class LoginPhoneView extends LinearLayout {

    /* renamed from: a  reason: collision with root package name */
    private static final int f16313a = 11;
    private int b;
    /* access modifiers changed from: private */
    public ViewFlipper c;
    private LocalPhoneView d;
    /* access modifiers changed from: private */
    public LoginInputView e;
    /* access modifiers changed from: private */
    public View f;
    /* access modifiers changed from: private */
    public LoginInputView g;
    /* access modifiers changed from: private */
    public TextView h;
    /* access modifiers changed from: private */
    public TextView i;
    /* access modifiers changed from: private */
    public boolean j;
    /* access modifiers changed from: private */
    public WeakReference<LoginPhoneActivity> k;
    private CountDownTimer l;
    private TextWatcher m;
    private TextWatcher n;

    public LoginPhoneView(Context context) {
        this(context, (AttributeSet) null);
    }

    public LoginPhoneView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LoginPhoneView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.b = 6;
        this.j = false;
        this.l = new CountDownTimer(60000, 1000) {
            public void onTick(long j) {
                LoginPhoneView.this.h.setText(LoginPhoneView.this.getContext().getString(R.string.login_verify_code_second, new Object[]{Long.valueOf(j / 1000)}));
            }

            public void onFinish() {
                LoginPhoneView.this.h.setText(R.string.login_verify_code_again);
                LoginPhoneView.this.h.setEnabled(LoginPhoneView.this.c());
                boolean unused = LoginPhoneView.this.j = false;
            }
        };
        this.m = new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                boolean z = false;
                boolean z2 = LoginPhoneView.this.getPhone().length() == 11;
                boolean z3 = LoginPhoneView.this.getSmsCode().length() > 0;
                TextView access$500 = LoginPhoneView.this.i;
                if (z2 && z3) {
                    z = true;
                }
                access$500.setEnabled(z);
                LoginPhoneView.this.h.setEnabled(z2);
            }
        };
        this.n = new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                boolean z = false;
                boolean z2 = LoginPhoneView.this.getSmsCode().length() > 0;
                if (LoginPhoneView.this.c.getDisplayedChild() == 0) {
                    LoginPhoneView.this.i.setEnabled(z2);
                } else if (LoginPhoneView.this.c.getDisplayedChild() == 1) {
                    boolean z3 = LoginPhoneView.this.getPhone().length() == 11;
                    TextView access$500 = LoginPhoneView.this.i;
                    if (z3 && z2) {
                        z = true;
                    }
                    access$500.setEnabled(z);
                }
            }
        };
        a();
    }

    private void a() {
        LayoutInflater.from(getContext()).inflate(R.layout.smarthome_login_phone_input, this, true);
        this.c = (ViewFlipper) findViewById(R.id.login_first_input);
        this.d = (LocalPhoneView) findViewById(R.id.login_sms_code_local_phone);
        this.e = (LoginInputView) findViewById(R.id.login_phone_input_phone);
        this.f = findViewById(R.id.login_sms_code_input_group);
        this.g = (LoginInputView) findViewById(R.id.login_sms_code_input);
        this.h = (TextView) findViewById(R.id.login_sms_code_get);
        this.i = (TextView) findViewById(R.id.login_phone_sure);
        setOrientation(1);
        int d2 = DisplayUtils.d(getContext(), 13.0f);
        setPadding(d2, 0, d2, 0);
        this.c.setDisplayedChild(1);
        this.c.setInAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.login_fade_in));
        this.c.setOutAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.login_fade_out));
        this.e.setClearClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LoginPhoneView.this.b();
            }
        });
        this.e.getEditText().addTextChangedListener(this.m);
        this.g.getEditText().addTextChangedListener(this.n);
    }

    /* access modifiers changed from: private */
    public void b() {
        this.g.getEditText().setText("");
        if (!this.j) {
            this.h.setText(getContext().getString(R.string.login_phone_get_sms_code));
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.j = false;
        this.l.cancel();
    }

    public void showLocalPhoneNew(String str, String str2, String str3, final OnSmsCodeLoginListener onSmsCodeLoginListener) {
        a(str, str2, str3, onSmsCodeLoginListener);
        this.d.setTag(getContext().getString(R.string.login_phone_newer));
        this.f.setVisibility(8);
        this.i.setText(R.string.login_home_phone_register);
        this.i.setEnabled(true);
        this.i.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (onSmsCodeLoginListener != null) {
                    onSmsCodeLoginListener.a();
                }
            }
        });
    }

    public void showLocalPhoneOld(String str, String str2, String str3, final OnSmsCodeLoginListener onSmsCodeLoginListener) {
        a(str, str2, str3, onSmsCodeLoginListener);
        this.d.setTag("");
        this.f.setVisibility(0);
        b();
        this.h.setEnabled(true);
        this.h.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!LoginPhoneView.this.j) {
                    if (LoginPhoneView.this.k == null || LoginPhoneView.this.k.get() == null) {
                        ToastManager.a().a((int) R.string.login_phone_error_other);
                    } else {
                        PermissionHelper.f((Activity) LoginPhoneView.this.k.get(), true, new Action() {
                            public void onAction(List<String> list) {
                                LoginPhoneView.this.g.requestFocus();
                                if (!NetworkUtils.a()) {
                                    ToastManager.a().a((int) R.string.login_verify_code_network_unavailable);
                                } else if (onSmsCodeLoginListener != null) {
                                    onSmsCodeLoginListener.a("", "");
                                }
                            }
                        });
                    }
                }
            }
        });
        this.i.setEnabled(false);
        this.i.setText(R.string.login_home_phone_old_ensure);
        this.i.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!NetworkUtils.a()) {
                    ToastManager.a().a((int) R.string.login_network_not_available);
                } else if (onSmsCodeLoginListener != null) {
                    onSmsCodeLoginListener.a(LoginPhoneView.this.getSmsCode());
                }
            }
        });
    }

    public void setSMSCodeLength(int i2) {
        this.b = i2;
        this.g.getEditText().setFilters(new InputFilter[]{new InputFilter.LengthFilter(i2)});
    }

    public void showPhoneSmsCodeLogin(final OnSmsCodeLoginListener onSmsCodeLoginListener) {
        if (this.c.getDisplayedChild() != 1) {
            this.c.setDisplayedChild(1);
        }
        this.e.requestFocus();
        this.f.setVisibility(0);
        b();
        this.h.setEnabled(false);
        this.h.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!LoginPhoneView.this.j) {
                    if (LoginPhoneView.this.k == null || LoginPhoneView.this.k.get() == null) {
                        ToastManager.a().a((int) R.string.login_phone_error_other);
                    } else {
                        PermissionHelper.f((Activity) LoginPhoneView.this.k.get(), true, new Action() {
                            public void onAction(List<String> list) {
                                LoginPhoneView.this.g.requestFocus();
                                if (onSmsCodeLoginListener != null) {
                                    onSmsCodeLoginListener.a(LoginPhoneView.this.getPhone(), "", "");
                                }
                            }
                        });
                    }
                }
            }
        });
        this.i.setEnabled(false);
        this.i.setText(R.string.login_home_phone_old_ensure);
        this.i.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (onSmsCodeLoginListener != null) {
                    onSmsCodeLoginListener.b(LoginPhoneView.this.getPhone(), LoginPhoneView.this.getSmsCode());
                }
            }
        });
    }

    public void showSmsCodeSendSuccess(int i2) {
        this.b = i2;
        this.j = true;
        this.l.start();
        if (i2 < 4) {
            i2 = 4;
        }
        this.g.setInputMaxLength(i2);
    }

    private void a(String str, String str2, String str3, final OnSmsCodeLoginListener onSmsCodeLoginListener) {
        final ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{0, this.f.getMeasuredHeight()});
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                ViewGroup.LayoutParams layoutParams = LoginPhoneView.this.f.getLayoutParams();
                layoutParams.height = intValue;
                LoginPhoneView.this.f.setLayoutParams(layoutParams);
            }
        });
        ofInt.addListener(new Animator.AnimatorListener() {
            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
            }

            public void onAnimationEnd(Animator animator) {
                LoginUtil.a(LoginPhoneView.this.getContext(), (View) LoginPhoneView.this.e.getEditText());
            }
        });
        ofInt.setDuration(300);
        this.c.setDisplayedChild(0);
        this.d.setPhone(str, str2, str3);
        this.d.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                boolean z = LoginPhoneView.this.f.getVisibility() != 0;
                LoginPhoneView.this.showPhoneSmsCodeLogin(onSmsCodeLoginListener);
                if (z) {
                    ofInt.start();
                } else {
                    LoginUtil.a(LoginPhoneView.this.getContext(), (View) LoginPhoneView.this.e.getEditText());
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public boolean c() {
        if (this.c.getDisplayedChild() == 0 || getPhone().length() == 11) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public String getPhone() {
        return this.e.getEditText().getText().toString().trim();
    }

    /* access modifiers changed from: private */
    public String getSmsCode() {
        return this.g.getEditText().getText().toString().trim();
    }

    public void setSmsCode(String str) {
        this.g.requestFocus();
        this.g.getEditText().setText(str);
        this.g.getEditText().setSelection(str.length());
    }

    public void setActivity(LoginPhoneActivity loginPhoneActivity) {
        this.k = new WeakReference<>(loginPhoneActivity);
    }
}
