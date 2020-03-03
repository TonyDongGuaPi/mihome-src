package com.xiaomi.smarthome.smartconfig.step;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Message;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.library.common.widget.ResizeLayout;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider;
import com.xiaomi.smarthome.smartconfig.step.SmartConfigStep;
import java.util.regex.Pattern;

public class WifiExtApPasswdInputStep extends SmartConfigStep {

    /* renamed from: a  reason: collision with root package name */
    private static final int f22706a = 32;
    private static final int b = 63;
    private String c;
    private WifiManager d;
    private ScanResult e;
    /* access modifiers changed from: private */
    public boolean f = true;
    /* access modifiers changed from: private */
    public ObjectAnimator g;
    /* access modifiers changed from: private */
    public ObjectAnimator h;
    /* access modifiers changed from: private */
    public LayoutChangeListener i;
    /* access modifiers changed from: private */
    public int j;
    /* access modifiers changed from: private */
    public int k;
    @BindView(2131428347)
    RelativeLayout mContentView;
    @BindView(2131429685)
    ImageView mDeviceIcon;
    @BindView(2131430975)
    TextView mDeviceInfo;
    @BindView(2131429706)
    ResizeLayout mHeadView;
    @BindView(2131431711)
    ImageView mIvPwdShow;
    @BindView(2131431178)
    Button mNextBtn;
    @BindView(2131434005)
    EditText mPasswordEditor;
    @BindView(2131430969)
    ImageView mReturnBtn;
    @BindView(2131432919)
    View mTitleBar;
    @BindView(2131430720)
    EditText mWifiChooser;
    @BindView(2131434010)
    View mWifiPassContainer;

    public void a(Message message) {
    }

    public void c() {
    }

    public void d() {
    }

    public void e() {
    }

    public SmartConfigStep.Step f() {
        return null;
    }

    public void a(Context context) {
        ((Activity) context).getWindow().setSoftInputMode(16);
        a(context, R.layout.smart_config_ap_passwd_input_step);
        TitleBarUtil.a(this.mTitleBar);
        this.mTitleBar.setBackgroundColor(Color.parseColor("#00000000"));
        TitleBarUtil.b((Activity) this.af);
        this.mReturnBtn.setImageResource(R.drawable.left_arrow_icon1);
        this.mDeviceInfo.setTextColor(-1);
        this.c = (String) SmartConfigDataProvider.a().a("device_model");
        PluginRecord d2 = CoreApi.a().d(this.c);
        if (d2 != null) {
            this.mDeviceInfo.setText(d2.p());
        }
        this.d = (WifiManager) context.getSystemService("wifi");
        this.mNextBtn.setText(R.string.next_button);
        this.mReturnBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WifiExtApPasswdInputStep.this.d_(false);
            }
        });
        this.mPasswordEditor.setVisibility(0);
        this.mNextBtn.setEnabled(false);
        SmartConfigDataProvider.a().b(SmartConfigDataProvider.p);
        this.i = new LayoutChangeListener(context);
        this.mContentView.getViewTreeObserver().addOnGlobalLayoutListener(this.i);
        this.mNextBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                InputMethodManager inputMethodManager = (InputMethodManager) WifiExtApPasswdInputStep.this.af.getSystemService("input_method");
                if (inputMethodManager.isActive() && (WifiExtApPasswdInputStep.this.af instanceof Activity) && ((Activity) WifiExtApPasswdInputStep.this.af).getCurrentFocus() != null) {
                    inputMethodManager.hideSoftInputFromWindow(((Activity) WifiExtApPasswdInputStep.this.af).getCurrentFocus().getWindowToken(), 2);
                }
                String obj = WifiExtApPasswdInputStep.this.mWifiChooser.getText().toString();
                String obj2 = WifiExtApPasswdInputStep.this.mPasswordEditor.getText().toString();
                if (obj.getBytes().length > 32) {
                    Toast.makeText(WifiExtApPasswdInputStep.this.af, WifiExtApPasswdInputStep.this.af.getString(R.string.dianlimao_ssid_toolong), 0).show();
                } else if (obj2.getBytes().length > 63) {
                    Toast.makeText(WifiExtApPasswdInputStep.this.af, WifiExtApPasswdInputStep.this.af.getString(R.string.dianlimao_pwd_toolong), 0).show();
                } else {
                    SmartConfigDataProvider.a().b(SmartConfigDataProvider.d, obj.trim());
                    SmartConfigDataProvider.a().b(SmartConfigDataProvider.e, obj2.trim());
                    WifiExtApPasswdInputStep.this.D();
                }
            }
        });
        this.mWifiChooser.setFilters(new InputFilter[]{new InputFilter.LengthFilter(32)});
        this.mPasswordEditor.setText("");
        this.mPasswordEditor.setFilters(new InputFilter[]{new InputFilter.LengthFilter(63)});
        this.mWifiChooser.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                WifiExtApPasswdInputStep.this.b();
            }
        });
        this.mPasswordEditor.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                String charSequence2 = charSequence.toString();
                String replaceAll = Pattern.compile("[一-龥]").matcher(charSequence2).replaceAll("");
                if (!replaceAll.equals(charSequence2)) {
                    WifiExtApPasswdInputStep.this.mPasswordEditor.setText(replaceAll);
                    WifiExtApPasswdInputStep.this.mPasswordEditor.setSelection(replaceAll.length());
                }
            }

            public void afterTextChanged(Editable editable) {
                WifiExtApPasswdInputStep.this.b();
            }
        });
        this.mIvPwdShow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int selectionStart = WifiExtApPasswdInputStep.this.mPasswordEditor.getSelectionStart();
                if (WifiExtApPasswdInputStep.this.mPasswordEditor.getInputType() != 144) {
                    WifiExtApPasswdInputStep.this.mPasswordEditor.setInputType(144);
                    WifiExtApPasswdInputStep.this.mIvPwdShow.setImageResource(R.drawable.pwd_show_icon);
                } else {
                    WifiExtApPasswdInputStep.this.mPasswordEditor.setInputType(129);
                    WifiExtApPasswdInputStep.this.mIvPwdShow.setImageResource(R.drawable.pwd_hide_icon);
                }
                WifiExtApPasswdInputStep.this.mPasswordEditor.setSelection(selectionStart);
            }
        });
    }

    /* access modifiers changed from: private */
    public void b() {
        String trim = this.mWifiChooser.getText().toString().trim();
        String trim2 = this.mPasswordEditor.getText().toString().trim();
        if (TextUtils.isEmpty(trim2) || trim2.length() < 8 || TextUtils.isEmpty(trim)) {
            this.mNextBtn.setEnabled(false);
        } else {
            this.mNextBtn.setEnabled(true);
        }
    }

    private class LayoutChangeListener implements ViewTreeObserver.OnGlobalLayoutListener {
        private Context b;

        LayoutChangeListener(Context context) {
            this.b = context;
        }

        public void onGlobalLayout() {
            if (WifiExtApPasswdInputStep.this.k == 0) {
                int unused = WifiExtApPasswdInputStep.this.j = WifiExtApPasswdInputStep.this.mTitleBar.getHeight();
                int unused2 = WifiExtApPasswdInputStep.this.k = WifiExtApPasswdInputStep.this.mHeadView.getHeight();
                WifiExtApPasswdInputStep.this.c(this.b);
                WifiExtApPasswdInputStep.this.d(this.b);
                return;
            }
            Rect rect = new Rect();
            View decorView = ((Activity) this.b).getWindow().getDecorView();
            decorView.getWindowVisibleDisplayFrame(rect);
            int i = rect.bottom - rect.top;
            int[] iArr = new int[2];
            WifiExtApPasswdInputStep.this.mPasswordEditor.getLocationInWindow(iArr);
            if (iArr[1] + WifiExtApPasswdInputStep.this.mPasswordEditor.getHeight() > i && WifiExtApPasswdInputStep.this.f && !WifiExtApPasswdInputStep.this.h.isRunning()) {
                if (WifiExtApPasswdInputStep.this.g.isRunning()) {
                    WifiExtApPasswdInputStep.this.g.end();
                }
                WifiExtApPasswdInputStep.this.h.start();
                boolean unused3 = WifiExtApPasswdInputStep.this.f = false;
                if (Build.VERSION.SDK_INT < 16) {
                    WifiExtApPasswdInputStep.this.mContentView.getViewTreeObserver().removeGlobalOnLayoutListener(WifiExtApPasswdInputStep.this.i);
                } else {
                    WifiExtApPasswdInputStep.this.mContentView.getViewTreeObserver().removeOnGlobalLayoutListener(WifiExtApPasswdInputStep.this.i);
                }
            } else if (decorView.getHeight() - i < 200 && !WifiExtApPasswdInputStep.this.f && !WifiExtApPasswdInputStep.this.g.isRunning()) {
                if (WifiExtApPasswdInputStep.this.h.isRunning()) {
                    WifiExtApPasswdInputStep.this.g.end();
                }
                WifiExtApPasswdInputStep.this.g.start();
                boolean unused4 = WifiExtApPasswdInputStep.this.f = true;
                if (Build.VERSION.SDK_INT < 16) {
                    WifiExtApPasswdInputStep.this.mContentView.getViewTreeObserver().removeGlobalOnLayoutListener(WifiExtApPasswdInputStep.this.i);
                } else {
                    WifiExtApPasswdInputStep.this.mContentView.getViewTreeObserver().removeOnGlobalLayoutListener(WifiExtApPasswdInputStep.this.i);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void c(final Context context) {
        this.g = ObjectAnimator.ofInt(this.mHeadView, "height", new int[]{this.j, this.k}).setDuration(300);
        this.g.setInterpolator(new AccelerateDecelerateInterpolator());
        this.g.addListener(new Animator.AnimatorListener() {
            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
                WifiExtApPasswdInputStep.this.mTitleBar.setBackgroundColor(Color.parseColor("#00000000"));
                WifiExtApPasswdInputStep.this.mDeviceInfo.setTextColor(-1);
                WifiExtApPasswdInputStep.this.mReturnBtn.setImageDrawable(context.getResources().getDrawable(R.drawable.left_arrow_icon1));
            }

            public void onAnimationEnd(Animator animator) {
                TitleBarUtil.b((Activity) context);
                WifiExtApPasswdInputStep.this.mContentView.getViewTreeObserver().addOnGlobalLayoutListener(WifiExtApPasswdInputStep.this.i);
            }
        });
    }

    /* access modifiers changed from: private */
    public void d(final Context context) {
        this.h = ObjectAnimator.ofInt(this.mHeadView, "height", new int[]{this.k, this.j}).setDuration(300);
        this.h.setInterpolator(new AccelerateDecelerateInterpolator());
        this.h.addListener(new Animator.AnimatorListener() {
            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
            }

            public void onAnimationEnd(Animator animator) {
                WifiExtApPasswdInputStep.this.mTitleBar.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.common_title_bar_bg));
                WifiExtApPasswdInputStep.this.mDeviceInfo.setTextColor(context.getResources().getColor(R.color.class_V));
                WifiExtApPasswdInputStep.this.mReturnBtn.setImageDrawable(context.getResources().getDrawable(R.drawable.common_title_bar_return));
                TitleBarUtil.a((Activity) context);
                WifiExtApPasswdInputStep.this.mContentView.getViewTreeObserver().addOnGlobalLayoutListener(WifiExtApPasswdInputStep.this.i);
            }
        });
    }

    public boolean a() {
        d_(false);
        return true;
    }
}
