package com.xiaomi.smarthome.frame.login.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Vibrator;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xiaomi.smarthome.frame.baseui.ToastManager;
import com.xiaomi.smarthome.frame.login.ui.view.VerifyCodeInputItem;
import com.xiaomi.smarthome.frame.login.util.LoginUtil;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.sdk.R;

public class VerifyCodeInputView extends LinearLayout {

    /* renamed from: a  reason: collision with root package name */
    private static final long[] f16338a = {100, 500};
    public int VERIFY_CODE_NUMBER;
    /* access modifiers changed from: private */
    public String b;
    private VerifyCodeInputItem[] c;
    /* access modifiers changed from: private */
    public OnCodeInputFinishListener d;
    private LayoutInflater e;
    /* access modifiers changed from: private */
    public boolean f;
    /* access modifiers changed from: private */
    public boolean g;

    public interface OnCodeInputFinishListener {
        void a(String str);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return true;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return true;
    }

    public VerifyCodeInputView(Context context) {
        this(context, (AttributeSet) null);
    }

    public VerifyCodeInputView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public VerifyCodeInputView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.VERIFY_CODE_NUMBER = 4;
        this.b = "";
        this.f = false;
        this.g = false;
        this.e = LayoutInflater.from(context);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.VerifyCodeInputView);
            this.VERIFY_CODE_NUMBER = obtainStyledAttributes.getInteger(0, 4);
            obtainStyledAttributes.recycle();
        }
        this.c = new VerifyCodeInputItem[this.VERIFY_CODE_NUMBER];
        init();
    }

    public void init() {
        setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        setOrientation(0);
        for (int i = 0; i < this.VERIFY_CODE_NUMBER; i++) {
            a(i);
        }
        this.c[0].requestFocus();
    }

    private void a(int i) {
        VerifyCodeInputItem verifyCodeInputItem = (VerifyCodeInputItem) this.e.inflate(com.xiaomi.smarthome.R.layout.pincode_edit_text_view, (ViewGroup) null);
        this.c[i] = verifyCodeInputItem;
        addView(verifyCodeInputItem, new LinearLayout.LayoutParams(DisplayUtils.a(getContext(), 40.0f), DisplayUtils.a(getContext(), 50.0f)));
        verifyCodeInputItem.setOnCodeChangeListener(new VerifyCodeInputItem.OnCodeChangeListener() {
            public void a(int i) {
                if (!VerifyCodeInputView.this.g) {
                    VerifyCodeInputView.this.b(i);
                }
            }

            public void a() {
                VerifyCodeInputView.this.a();
            }
        });
        if (i == this.VERIFY_CODE_NUMBER - 1) {
            verifyCodeInputItem.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                    if (i != 6) {
                        return false;
                    }
                    if (VerifyCodeInputView.this.b.length() != VerifyCodeInputView.this.VERIFY_CODE_NUMBER || VerifyCodeInputView.this.d == null) {
                        return true;
                    }
                    VerifyCodeInputView.this.d.a(VerifyCodeInputView.this.b);
                    return true;
                }
            });
        } else {
            verifyCodeInputItem.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                    if (i != 6) {
                        return false;
                    }
                    ToastManager.a().a(VerifyCodeInputView.this.getContext().getString(com.xiaomi.smarthome.R.string.login_verify_code_not_full, new Object[]{Integer.valueOf(VerifyCodeInputView.this.VERIFY_CODE_NUMBER)}));
                    return true;
                }
            });
        }
        if (i != this.VERIFY_CODE_NUMBER - 1) {
            View view = new View(getContext());
            view.setLayoutParams(new LinearLayout.LayoutParams(0, -1, 1.0f));
            addView(view);
        }
    }

    public void setCode(String str) {
        if (!TextUtils.isEmpty(str) && str.length() == this.VERIFY_CODE_NUMBER) {
            this.g = true;
            int i = 0;
            while (i < this.VERIFY_CODE_NUMBER) {
                int i2 = i + 1;
                this.c[i].showCode(Integer.valueOf(str.substring(i, i2)).intValue());
                i = i2;
            }
            this.b = str;
            this.c[this.b.length() - 1].requestFocus();
            LoginUtil.a(getContext(), (View) this.c[this.b.length() - 1]);
            if (this.d != null) {
                this.d.a(this.b);
            }
            this.g = false;
        }
    }

    /* access modifiers changed from: private */
    public void b(int i) {
        if (this.b.length() < this.VERIFY_CODE_NUMBER) {
            this.b += i;
        }
        if (this.b.length() != this.VERIFY_CODE_NUMBER) {
            this.c[this.b.length()].requestFocus();
            LoginUtil.a(getContext(), (View) this.c[this.b.length()]);
        } else if (this.d != null) {
            this.d.a(this.b);
        }
    }

    /* access modifiers changed from: private */
    public void a() {
        if (!this.f) {
            this.f = true;
            if (TextUtils.isEmpty(this.b)) {
                this.f = false;
                return;
            }
            if (this.b.length() == 1) {
                this.c[0].clearPin();
                this.c[0].requestFocus();
                LoginUtil.a(getContext(), (View) this.c[0]);
                this.b = "";
            } else {
                this.b = this.b.substring(0, this.b.length() - 1);
                if (this.b.length() == this.VERIFY_CODE_NUMBER - 1) {
                    this.c[this.VERIFY_CODE_NUMBER - 1].clearPin();
                } else {
                    this.c[this.b.length()].clearPin();
                    this.c[this.b.length()].requestFocus();
                    LoginUtil.a(getContext(), (View) this.c[this.b.length()]);
                }
            }
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    boolean unused = VerifyCodeInputView.this.f = false;
                }
            }, 200);
        }
    }

    @Deprecated
    public String add(int i) {
        if (this.b.length() < this.VERIFY_CODE_NUMBER) {
            this.b += i;
        }
        this.c[this.b.length() - 1].showCode(i);
        if (this.b.length() != this.VERIFY_CODE_NUMBER) {
            this.c[this.b.length()].requestFocus();
        } else if (this.d != null) {
            this.d.a(this.b);
        }
        return this.b;
    }

    @Deprecated
    public String delete() {
        if (TextUtils.isEmpty(this.b)) {
            return "";
        }
        this.c[this.b.length() - 1].clearPin();
        this.c[this.b.length() - 1].requestFocus();
        if (this.b.length() == 1) {
            this.b = "";
        } else {
            this.b = this.b.substring(0, this.b.length() - 1);
        }
        return this.b;
    }

    public void reset() {
        for (VerifyCodeInputItem clearPin : this.c) {
            clearPin.clearPin();
        }
        this.c[0].requestFocus();
        LoginUtil.a(getContext(), (View) this.c[0]);
        this.b = "";
    }

    public void showError() {
        startAnimation(AnimationUtils.loadAnimation(getContext(), com.xiaomi.smarthome.R.anim.shake));
        ((Vibrator) getContext().getSystemService("vibrator")).vibrate(f16338a, -1);
    }

    public String getVerifyCode() {
        return this.b;
    }

    public void setOnCodeInputFinishListener(OnCodeInputFinishListener onCodeInputFinishListener) {
        this.d = onCodeInputFinishListener;
    }
}
