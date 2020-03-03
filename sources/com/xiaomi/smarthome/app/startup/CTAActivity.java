package com.xiaomi.smarthome.app.startup;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.LocalBroadcastManager;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.miui.tsmclient.entity.CardInfo;
import com.xiaomi.smarthome.OpenExternalBrowserCompat;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.server.internal.globaldynamicsetting.GlobalDynamicSettingConst;
import com.xiaomi.smarthome.core.server.internal.globaldynamicsetting.GlobalDynamicSettingManager;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.server_compact.ServerBean;
import com.xiaomi.smarthome.international.SelectServerUtils;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.util.SharePrefsManager;
import com.xiaomi.smarthome.library.common.util.TextViewUtils;
import com.xiaomi.smarthome.miio.LanguageUtil;
import com.xiaomi.smarthome.miio.activity.UserLicense;
import java.util.Locale;

public class CTAActivity extends Activity {

    /* renamed from: a  reason: collision with root package name */
    private ServerBean f13701a;
    Button mCancel;
    Context mContext;
    TextView mDisclaimContent;
    View mEmpty;
    Button mOK;
    ImageView mRememberView;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        DisplayUtils.e(this);
        super.onCreate(bundle);
        this.f13701a = SelectServerUtils.b();
        StartupUtils.a(getWindow());
        if (Build.VERSION.SDK_INT != 26 || !DisplayUtils.d(this)) {
            setRequestedOrientation(1);
        }
        this.mContext = this;
        setContentView(R.layout.cta_activity);
        this.mEmpty = findViewById(R.id.empty);
        this.mEmpty.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CTAActivity.this.c();
            }
        });
        this.mDisclaimContent = (TextView) findViewById(R.id.disclaim_content);
        String string = getString(R.string.disclaimers_content);
        string.indexOf("#start#");
        string.indexOf("#end#");
        "#start#".length();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append(string.replace("#start#", "").replace("#end#", ""));
        this.mDisclaimContent.setText(spannableStringBuilder);
        this.mDisclaimContent.setMovementMethod(LinkMovementMethod.getInstance());
        a();
        this.mCancel = (Button) findViewById(R.id.cancel);
        this.mCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CTAActivity.this.c();
            }
        });
        this.mOK = (Button) findViewById(R.id.ok);
        this.mOK.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CTAActivity.this.b();
            }
        });
    }

    private void a() {
        TextView textView = (TextView) findViewById(R.id.disclaim_content1);
        textView.setVisibility(0);
        String string = getString(R.string.disclaimers_join_user_exp_plan);
        int indexOf = string.indexOf("#start#");
        int indexOf2 = string.indexOf("#end#") - "#start#".length();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        String replace = string.replace("#start#", "").replace("#end#", "");
        int indexOf3 = replace.indexOf("#start1#");
        int indexOf4 = replace.indexOf("#end1#") - "#start1#".length();
        spannableStringBuilder.append(replace.replace("#start1#", "").replace("#end1#", ""));
        AnonymousClass4 r1 = new ClickableSpan() {
            public void onClick(View view) {
                OpenExternalBrowserCompat.a(CTAActivity.this, UserLicense.getUrlByTpye(UserLicense.TYPE_USER_LICENSE));
            }

            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setColor(Color.parseColor("#FF527ACC"));
                textPaint.setUnderlineText(false);
            }
        };
        AnonymousClass5 r7 = new ClickableSpan() {
            public void onClick(View view) {
                OpenExternalBrowserCompat.a(CTAActivity.this, UserLicense.getUrlByTpye(UserLicense.TYPE_USER_PRIVACY));
            }

            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setColor(Color.parseColor("#FF527ACC"));
                textPaint.setUnderlineText(false);
            }
        };
        if (indexOf >= 0 && indexOf2 > 0) {
            try {
                spannableStringBuilder.setSpan(r1, indexOf, indexOf2, 33);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (indexOf3 >= 0 && indexOf4 > 0) {
            try {
                spannableStringBuilder.setSpan(r7, indexOf3, indexOf4, 33);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        textView.setText(spannableStringBuilder);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        TextViewUtils.a();
        SHApplication.getGlobalHandler().post(new Runnable() {
            public void run() {
                CoreApi.a().n();
            }
        });
    }

    public void onBackPressed() {
        try {
            c();
            super.onBackPressed();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public void b() {
        CoreApi.a().a(this.mContext, (CoreApi.IsCoreReadyCallback) new CoreApi.IsCoreReadyCallback() {
            public void onCoreReady() {
                CoreApi.a().a(false, true, (AsyncCallback<Void, Error>) null);
            }
        });
        View decorView = getWindow().getDecorView();
        if (decorView != null) {
            decorView.setVisibility(8);
        }
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            public void run() {
                CTAActivity.this.finish();
                CTAActivity.this.overridePendingTransition(0, 0);
            }
        }, 50);
        startActivity(new Intent(this, CTAActivity2rd.class));
        overridePendingTransition(0, 0);
    }

    /* access modifiers changed from: private */
    public void c() {
        finish();
        overridePendingTransition(0, 0);
        LocalBroadcastManager instance = LocalBroadcastManager.getInstance(this.mContext);
        Intent intent = new Intent(CTAHelper.f13727a);
        intent.putExtra("param_key", 2);
        instance.sendBroadcast(intent);
    }

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context context) {
        if (Build.VERSION.SDK_INT >= 17) {
            Locale a2 = a(SHApplication.getAppContext());
            boolean b = SharePrefsManager.b(SHApplication.getAppContext(), "language_setting", CardInfo.KEY_IS_DEFAULT, true);
            if (LanguageUtil.K.contains(LanguageUtil.b())) {
                if (b) {
                    a2 = LanguageUtil.G;
                } else if (LanguageUtil.a(Locale.US, a2)) {
                    a2 = LanguageUtil.G;
                }
            } else if (LanguageUtil.a(LanguageUtil.G, a2)) {
                a2 = b ? LanguageUtil.b() : Locale.US;
            }
            context = LanguageUtil.a(context, a2);
        }
        super.attachBaseContext(context);
    }

    private Locale a(Context context) {
        if (context == null) {
            return LanguageUtil.b();
        }
        Locale locale = null;
        SharedPreferences sharedPreferences = context.getSharedPreferences(GlobalDynamicSettingManager.f14576a, 0);
        String b = SharePrefsManager.b(sharedPreferences, GlobalDynamicSettingConst.g, "");
        String b2 = SharePrefsManager.b(sharedPreferences, GlobalDynamicSettingConst.h, "");
        if (!TextUtils.isEmpty(b) && !TextUtils.isEmpty(b2)) {
            locale = new Locale(b, b2);
        }
        return locale == null ? LanguageUtil.b() : locale;
    }
}
