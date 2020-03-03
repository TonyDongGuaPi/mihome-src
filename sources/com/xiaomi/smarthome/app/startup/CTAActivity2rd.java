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
import com.xiaomi.smarthome.frame.AppUsrExpPlanUtil;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.server_compact.ServerBean;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.library.common.util.CommonUtils;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.util.SharePrefsManager;
import com.xiaomi.smarthome.library.common.util.TextViewUtils;
import com.xiaomi.smarthome.miio.LanguageUtil;
import com.xiaomi.smarthome.miio.activity.UserLicense;
import com.xiaomi.smarthome.miio.page.usrexpplan.UsrExpPlanActivity;
import java.util.Locale;

public class CTAActivity2rd extends Activity {
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
        StartupUtils.a(getWindow());
        if (Build.VERSION.SDK_INT != 26 || !DisplayUtils.d(this)) {
            setRequestedOrientation(1);
        }
        this.mContext = this;
        setContentView(R.layout.cta_activity);
        this.mEmpty = findViewById(R.id.empty);
        this.mEmpty.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CTAActivity2rd.this.d();
            }
        });
        this.mDisclaimContent = (TextView) findViewById(R.id.disclaim_content);
        if (!ServerCompact.e((ServerBean) null)) {
            ((TextView) findViewById(R.id.title)).setText(R.string.usr_exp_plan);
            this.mDisclaimContent.setVisibility(0);
            String string = getString(R.string.disclaim_join_user_exp_plan);
            int indexOf = string.indexOf("#start#");
            int indexOf2 = string.indexOf("#end#") - "#start#".length();
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            spannableStringBuilder.append(string.replace("#start#", "").replace("#end#", ""));
            AnonymousClass2 r0 = new ClickableSpan() {
                public void onClick(View view) {
                    CTAActivity2rd.this.startActivity(new Intent(CTAActivity2rd.this, UsrExpPlanActivity.class));
                }

                public void updateDrawState(TextPaint textPaint) {
                    super.updateDrawState(textPaint);
                    textPaint.setColor(Color.parseColor("#FF527ACC"));
                    textPaint.setUnderlineText(false);
                }
            };
            if (indexOf > 0 && indexOf2 > 0) {
                try {
                    spannableStringBuilder.setSpan(r0, indexOf, indexOf2, 33);
                } catch (Exception unused) {
                }
            }
            this.mDisclaimContent.setText(spannableStringBuilder);
            this.mDisclaimContent.setMovementMethod(LinkMovementMethod.getInstance());
        } else {
            ((TextView) findViewById(R.id.title)).setText(R.string.disclaim_title);
            this.mDisclaimContent.setVisibility(8);
        }
        b();
        if (ServerCompact.e((ServerBean) null)) {
            findViewById(R.id.disclaim_content1).setVisibility(8);
            a();
        }
        findViewById(R.id.remember).setVisibility(8);
        findViewById(R.id.remember3).setVisibility(8);
        findViewById(R.id.remember2).setVisibility(8);
        this.mCancel = (Button) findViewById(R.id.cancel);
        this.mCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CTAActivity2rd.this.d();
            }
        });
        this.mOK = (Button) findViewById(R.id.ok);
        this.mOK.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CTAActivity2rd.this.c();
            }
        });
    }

    private void a() {
        findViewById(R.id.disclaim_content1).setVisibility(0);
        findViewById(R.id.remember_container).setVisibility(8);
        findViewById(R.id.remember_container2).setVisibility(0);
        findViewById(R.id.remember_container3).setVisibility(0);
        final ImageView imageView = (ImageView) findViewById(R.id.remember2);
        final ImageView imageView2 = (ImageView) findViewById(R.id.remember3);
        imageView.setSelected(true);
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                imageView.setSelected(!imageView.isSelected());
                if (!imageView2.isSelected() || !imageView.isSelected()) {
                    CTAActivity2rd.this.mOK.setEnabled(false);
                    CTAActivity2rd.this.mOK.setTextColor(CTAActivity2rd.this.getResources().getColor(R.color.std_list_subtitle));
                    return;
                }
                CTAActivity2rd.this.mOK.setEnabled(true);
                CTAActivity2rd.this.mOK.setTextColor(CTAActivity2rd.this.getResources().getColor(R.color.class_text_17));
            }
        });
        String string = getString(R.string.disclaim_join_user_exp_plan2);
        int indexOf = string.indexOf("#start#");
        int indexOf2 = string.indexOf("#end#") - "#start#".length();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append(string.replace("#start#", "").replace("#end#", ""));
        AnonymousClass6 r3 = new ClickableSpan() {
            public void onClick(View view) {
                OpenExternalBrowserCompat.a(CTAActivity2rd.this, UserLicense.getUrlByTpye(UserLicense.TYPE_USER_LICENSE));
            }

            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setColor(Color.parseColor("#FF527ACC"));
                textPaint.setUnderlineText(false);
            }
        };
        if (indexOf >= 0 && indexOf2 > 0) {
            try {
                spannableStringBuilder.setSpan(r3, indexOf, indexOf2, 33);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        TextView textView = (TextView) findViewById(R.id.join_usr_exp_plan_tv2);
        textView.setText(spannableStringBuilder);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        imageView2.setSelected(true);
        imageView2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                imageView2.setSelected(!imageView2.isSelected());
                if (!imageView2.isSelected() || !imageView.isSelected()) {
                    CTAActivity2rd.this.mOK.setEnabled(false);
                    CTAActivity2rd.this.mOK.setTextColor(CTAActivity2rd.this.getResources().getColor(R.color.std_list_subtitle));
                    return;
                }
                CTAActivity2rd.this.mOK.setEnabled(true);
                CTAActivity2rd.this.mOK.setTextColor(CTAActivity2rd.this.getResources().getColor(R.color.class_text_17));
            }
        });
        String string2 = getString(R.string.disclaim_join_user_exp_plan3);
        int indexOf3 = string2.indexOf("#start#");
        int indexOf4 = string2.indexOf("#end#") - "#start#".length();
        SpannableStringBuilder spannableStringBuilder2 = new SpannableStringBuilder();
        spannableStringBuilder2.append(string2.replace("#start#", "").replace("#end#", ""));
        AnonymousClass8 r0 = new ClickableSpan() {
            public void onClick(View view) {
                OpenExternalBrowserCompat.a(CTAActivity2rd.this, UserLicense.getUrlByTpye(UserLicense.TYPE_USER_PRIVACY));
            }

            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setColor(Color.parseColor("#FF527ACC"));
                textPaint.setUnderlineText(false);
            }
        };
        if (indexOf3 >= 0 && indexOf4 > 0) {
            try {
                spannableStringBuilder2.setSpan(r0, indexOf3, indexOf4, 33);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        TextView textView2 = (TextView) findViewById(R.id.join_usr_exp_plan_tv3);
        textView2.setText(spannableStringBuilder2);
        textView2.setMovementMethod(LinkMovementMethod.getInstance());
        this.mRememberView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CTAActivity2rd.this.mRememberView.setSelected(!CTAActivity2rd.this.mRememberView.isSelected());
            }
        });
        this.mRememberView.setSelected(true);
        String string3 = getString(R.string.disclaim_join_user_exp_plan1);
        int indexOf5 = string3.indexOf("#start#");
        int indexOf6 = string3.indexOf("#end#") - "#start#".length();
        SpannableStringBuilder spannableStringBuilder3 = new SpannableStringBuilder();
        spannableStringBuilder3.append(string3.replace("#start#", "").replace("#end#", ""));
        AnonymousClass10 r02 = new ClickableSpan() {
            public void onClick(View view) {
                CTAActivity2rd.this.startActivity(new Intent(CTAActivity2rd.this.mContext, UsrExpPlanActivity.class));
            }

            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setColor(Color.parseColor("#FF527ACC"));
                textPaint.setUnderlineText(false);
            }
        };
        if (indexOf5 >= 0 && indexOf6 > 0) {
            try {
                spannableStringBuilder3.setSpan(r02, indexOf5, indexOf6, 33);
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
    }

    private void b() {
        this.mRememberView = (ImageView) findViewById(R.id.remember);
        int t = CommonUtils.t();
        View findViewById = findViewById(R.id.remember_container);
        if (t == 1) {
            findViewById.setVisibility(8);
            this.mRememberView.setSelected(true);
        } else {
            findViewById.setVisibility(8);
            findViewById.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (CTAActivity2rd.this.mRememberView.isSelected()) {
                        CTAActivity2rd.this.mRememberView.setSelected(false);
                    } else {
                        CTAActivity2rd.this.mRememberView.setSelected(true);
                    }
                }
            });
        }
        this.mRememberView.setSelected(true);
        this.mRememberView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CTAActivity2rd.this.mRememberView.setSelected(!CTAActivity2rd.this.mRememberView.isSelected());
            }
        });
        String string = getString(R.string.disclaim_join_user_exp_plan);
        int indexOf = string.indexOf("#start#");
        int indexOf2 = string.indexOf("#end#") - "#start#".length();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append(string.replace("#start#", "").replace("#end#", ""));
        AnonymousClass13 r0 = new ClickableSpan() {
            public void onClick(View view) {
                CTAActivity2rd.this.startActivity(new Intent(CTAActivity2rd.this.mContext, UsrExpPlanActivity.class));
            }

            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setColor(Color.parseColor("#FF527ACC"));
                textPaint.setUnderlineText(false);
            }
        };
        if (indexOf > 0 && indexOf2 > 0) {
            try {
                spannableStringBuilder.setSpan(r0, indexOf, indexOf2, 33);
            } catch (Exception unused) {
            }
        }
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
            d();
            super.onBackPressed();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public void c() {
        CoreApi.a().a(this.mContext, (CoreApi.IsCoreReadyCallback) new CoreApi.IsCoreReadyCallback() {
            public void onCoreReady() {
                CoreApi.a().a(false, true, (AsyncCallback<Void, Error>) null);
            }
        });
        AppUsrExpPlanUtil.a(getApplicationContext(), true);
        View decorView = getWindow().getDecorView();
        if (decorView != null) {
            decorView.setVisibility(8);
        }
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            public void run() {
                CTAActivity2rd.this.finish();
                CTAActivity2rd.this.overridePendingTransition(0, 0);
            }
        }, 50);
        LocalBroadcastManager instance = LocalBroadcastManager.getInstance(this.mContext);
        Intent intent = new Intent(CTAHelper.f13727a);
        intent.putExtra("param_key", 1);
        instance.sendBroadcast(intent);
    }

    /* access modifiers changed from: private */
    public void d() {
        AppUsrExpPlanUtil.a(getApplicationContext(), false);
        View decorView = getWindow().getDecorView();
        if (decorView != null) {
            decorView.setVisibility(8);
        }
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            public void run() {
                CTAActivity2rd.this.finish();
                CTAActivity2rd.this.overridePendingTransition(0, 0);
            }
        }, 50);
        overridePendingTransition(0, 0);
        LocalBroadcastManager instance = LocalBroadcastManager.getInstance(this.mContext);
        Intent intent = new Intent(CTAHelper.f13727a);
        intent.putExtra("param_key", 1);
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
