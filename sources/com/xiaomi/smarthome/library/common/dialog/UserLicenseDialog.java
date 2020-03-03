package com.xiaomi.smarthome.library.common.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.api.DeviceApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.page.PluginLicenseActivity;
import com.xiaomi.smarthome.library.common.widget.nestscroll.UIUtils;
import com.xiaomi.smarthome.miio.LanguageUtil;
import com.xiaomi.smarthome.stat.STAT;
import java.util.Locale;

public class UserLicenseDialog {

    /* renamed from: a  reason: collision with root package name */
    public static final String f18599a = "enable_privacy_setting";
    /* access modifiers changed from: private */
    public String b;
    /* access modifiers changed from: private */
    public String c;
    /* access modifiers changed from: private */
    public String d;
    /* access modifiers changed from: private */
    public Spanned e;
    /* access modifiers changed from: private */
    public Spanned f;
    /* access modifiers changed from: private */
    public String g;
    /* access modifiers changed from: private */
    public String h;
    /* access modifiers changed from: private */
    public String i;
    /* access modifiers changed from: private */
    public String j;
    private TextView k;
    /* access modifiers changed from: private */
    public Context l;
    /* access modifiers changed from: private */
    public View.OnClickListener m;
    /* access modifiers changed from: private */
    public View n;
    /* access modifiers changed from: private */
    public LicenseDialog o;
    /* access modifiers changed from: private */
    public String p;
    /* access modifiers changed from: private */
    public int q;
    /* access modifiers changed from: private */
    public int r;
    /* access modifiers changed from: private */
    public Intent s;

    private class NoLineClickSpan extends ClickableSpan {
        public void onClick(View view) {
        }

        private NoLineClickSpan() {
        }

        public void updateDrawState(TextPaint textPaint) {
            textPaint.setColor(Color.parseColor("#527acc"));
            textPaint.setUnderlineText(false);
        }
    }

    public void a() {
        if (this.o == null) {
            this.o = new LicenseDialog(this.l);
            this.o.setCancelable(false);
            this.o.show();
        }
    }

    private class LicenseDialog extends Dialog {
        public LicenseDialog(Context context) {
            super(context);
        }

        /* access modifiers changed from: protected */
        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            View unused = UserLicenseDialog.this.n = LayoutInflater.from(UserLicenseDialog.this.l.getApplicationContext()).inflate(R.layout.dialog_user_license, (ViewGroup) null);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2, 80);
            layoutParams.setMargins(UIUtils.a(8), UIUtils.a(8), UIUtils.a(8), UIUtils.a(8));
            UserLicenseDialog.this.n.setLayoutParams(layoutParams);
            UserLicenseDialog.this.b();
            setContentView(UserLicenseDialog.this.n);
            Window window = getWindow();
            if (window != null) {
                View findViewById = findViewById(16908310);
                if (findViewById != null) {
                    findViewById.setVisibility(8);
                }
                window.setGravity(80);
                window.setBackgroundDrawable(new InsetDrawable(new ColorDrawable(0), UIUtils.a(8), UIUtils.a(8), UIUtils.a(8), UIUtils.a(8)));
                WindowManager.LayoutParams attributes = window.getAttributes();
                attributes.width = -1;
                attributes.height = -2;
                window.setAttributes(attributes);
            }
        }
    }

    /* access modifiers changed from: private */
    public void b() {
        if (TextUtils.isEmpty(this.c) || TextUtils.isEmpty(this.d)) {
            final String str = TextUtils.isEmpty(this.c) ? this.d : this.c;
            String trim = str.trim();
            if (TextUtils.isEmpty(this.c)) {
                Spanned spanned = this.f;
            } else {
                Spanned spanned2 = this.e;
            }
            this.k = (TextView) this.n.findViewById(R.id.user_license_title);
            String a2 = LanguageUtil.a((Locale) null, (int) R.string.have_read, trim.trim());
            int indexOf = a2.indexOf(trim);
            int length = trim.length() + indexOf;
            SpannableString spannableString = new SpannableString(a2);
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#666666")), 0, indexOf, 33);
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#666666")), length, a2.length(), 33);
            spannableString.setSpan(new NoLineClickSpan() {
                public void onClick(View view) {
                    Intent intent = new Intent(UserLicenseDialog.this.l, PluginLicenseActivity.class);
                    if (!TextUtils.isEmpty(UserLicenseDialog.this.j)) {
                        intent.putExtra(PluginLicenseActivity.LICENSE_HTML_CONTENT, UserLicenseDialog.this.j);
                    } else if (!TextUtils.isEmpty(UserLicenseDialog.this.h)) {
                        intent.putExtra(PluginLicenseActivity.LICENSE_URI, UserLicenseDialog.this.h);
                    } else {
                        intent.putExtra(PluginLicenseActivity.LICENSE_CONTENT, UserLicenseDialog.this.f);
                    }
                    intent.putExtra("title", str);
                    UserLicenseDialog.this.l.startActivity(intent);
                }
            }, indexOf, length, 33);
            this.k.setMovementMethod(LinkMovementMethod.getInstance());
            this.k.setText(spannableString);
        } else {
            String trim2 = this.c.trim();
            String trim3 = this.d.trim();
            this.k = (TextView) this.n.findViewById(R.id.user_license_title);
            String a3 = LanguageUtil.a((Locale) null, (int) R.string.have_read_and, trim2, trim3);
            SpannableString spannableString2 = new SpannableString(a3);
            int indexOf2 = a3.indexOf(trim2);
            int length2 = trim2.length() + indexOf2;
            int indexOf3 = a3.indexOf(trim3);
            int length3 = trim3.length() + indexOf3;
            spannableString2.setSpan(new ForegroundColorSpan(Color.parseColor("#666666")), 0, indexOf2, 33);
            spannableString2.setSpan(new ForegroundColorSpan(Color.parseColor("#666666")), length2, indexOf3, 33);
            spannableString2.setSpan(new ForegroundColorSpan(Color.parseColor("#666666")), length3, a3.length(), 33);
            spannableString2.setSpan(new NoLineClickSpan() {
                public void onClick(View view) {
                    Intent intent = new Intent(UserLicenseDialog.this.l, PluginLicenseActivity.class);
                    intent.putExtra("title", UserLicenseDialog.this.c);
                    if (!TextUtils.isEmpty(UserLicenseDialog.this.i)) {
                        intent.putExtra(PluginLicenseActivity.LICENSE_HTML_CONTENT, UserLicenseDialog.this.i);
                    } else if (!TextUtils.isEmpty(UserLicenseDialog.this.g)) {
                        intent.putExtra(PluginLicenseActivity.LICENSE_URI, UserLicenseDialog.this.g);
                    } else if (UserLicenseDialog.this.q > 0) {
                        intent.putExtra(PluginLicenseActivity.LICENSE_HTML_CONTENT_RES, UserLicenseDialog.this.q);
                    } else {
                        intent.putExtra(PluginLicenseActivity.LICENSE_CONTENT, UserLicenseDialog.this.e);
                    }
                    UserLicenseDialog.this.l.startActivity(intent);
                }
            }, indexOf2, length2, 33);
            spannableString2.setSpan(new NoLineClickSpan() {
                public void onClick(View view) {
                    Intent intent = new Intent(UserLicenseDialog.this.l, PluginLicenseActivity.class);
                    intent.putExtra("title", UserLicenseDialog.this.d);
                    if (!TextUtils.isEmpty(UserLicenseDialog.this.j)) {
                        intent.putExtra(PluginLicenseActivity.LICENSE_HTML_CONTENT, UserLicenseDialog.this.j);
                    } else if (!TextUtils.isEmpty(UserLicenseDialog.this.h)) {
                        intent.putExtra(PluginLicenseActivity.LICENSE_URI, UserLicenseDialog.this.h);
                    } else if (UserLicenseDialog.this.r > 0) {
                        intent.putExtra(PluginLicenseActivity.LICENSE_HTML_CONTENT_RES, UserLicenseDialog.this.r);
                    } else {
                        intent.putExtra(PluginLicenseActivity.LICENSE_CONTENT, UserLicenseDialog.this.f);
                    }
                    UserLicenseDialog.this.l.startActivity(intent);
                }
            }, indexOf3, length3, 33);
            this.k.setMovementMethod(LinkMovementMethod.getInstance());
            this.k.setText(spannableString2);
        }
        Button button = (Button) this.n.findViewById(R.id.cancel);
        final Button button2 = (Button) this.n.findViewById(R.id.agree);
        button.setText(LanguageUtil.a((Locale) null, (int) R.string.cancel));
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                UserLicenseDialog.this.o.dismiss();
                ((Activity) UserLicenseDialog.this.l).finish();
            }
        });
        button2.setText(LanguageUtil.a((Locale) null, (int) R.string.agree_go_on));
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (UserLicenseDialog.this.p != null) {
                    DeviceApi.getInstance().setUserLicenseConfig(UserLicenseDialog.this.l, UserLicenseDialog.this.p, "accept", (AsyncCallback<String, Error>) null);
                }
                UserLicenseDialog.this.o.dismiss();
                if (UserLicenseDialog.this.m != null) {
                    UserLicenseDialog.this.m.onClick(button2);
                }
                boolean z = false;
                if (UserLicenseDialog.this.s != null) {
                    z = UserLicenseDialog.this.s.getBooleanExtra("enable_privacy_setting", false);
                }
                if (z) {
                    UserExpPlanDialog.a(UserLicenseDialog.this.l, UserLicenseDialog.this.p, UserLicenseDialog.this.s);
                    STAT.i.e("", 1);
                    return;
                }
                STAT.i.e("", 2);
            }
        });
    }

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        private String f18605a;
        private String b;
        private String c;
        private Spanned d;
        private Spanned e;
        private String f;
        private String g;
        private String h;
        private String i;
        private int j;
        private int k;
        private Context l;
        private View.OnClickListener m;
        private String n;
        private Intent o;

        public Builder a(int i2) {
            this.j = i2;
            return this;
        }

        public Builder b(int i2) {
            this.k = i2;
            return this;
        }

        public Builder(Context context) {
            this.l = context;
        }

        public Builder a(String str) {
            this.f18605a = str;
            return this;
        }

        @Deprecated
        public Builder b(String str) {
            if (TextUtils.isEmpty(str)) {
                return this;
            }
            this.b = this.l.getApplicationContext().getString(R.string.dialog_license_title);
            return this;
        }

        public Builder a(Spanned spanned) {
            this.d = spanned;
            return this;
        }

        public Builder a(Intent intent) {
            this.o = intent;
            return this;
        }

        @Deprecated
        public Builder c(String str) {
            this.c = this.l.getApplicationContext().getString(R.string.dialog_privacy_title);
            return this;
        }

        public Builder b(Spanned spanned) {
            this.e = spanned;
            return this;
        }

        public Builder a(View.OnClickListener onClickListener) {
            this.m = onClickListener;
            return this;
        }

        public Builder d(String str) {
            this.n = str;
            return this;
        }

        public Builder e(String str) {
            this.f = str;
            return this;
        }

        public Builder f(String str) {
            this.g = str;
            return this;
        }

        public Builder g(String str) {
            this.h = str;
            return this;
        }

        public Builder h(String str) {
            this.i = str;
            return this;
        }

        public UserLicenseDialog a() {
            UserLicenseDialog userLicenseDialog = new UserLicenseDialog();
            Context unused = userLicenseDialog.l = this.l;
            String unused2 = userLicenseDialog.c = this.b;
            Spanned unused3 = userLicenseDialog.e = this.d;
            String unused4 = userLicenseDialog.d = this.c;
            Spanned unused5 = userLicenseDialog.f = this.e;
            String unused6 = userLicenseDialog.b = this.f18605a;
            View.OnClickListener unused7 = userLicenseDialog.m = this.m;
            String unused8 = userLicenseDialog.p = this.n;
            Intent unused9 = userLicenseDialog.s = this.o;
            String unused10 = userLicenseDialog.g = this.f;
            String unused11 = userLicenseDialog.h = this.g;
            String unused12 = userLicenseDialog.i = this.h;
            String unused13 = userLicenseDialog.j = this.i;
            int unused14 = userLicenseDialog.q = this.j;
            int unused15 = userLicenseDialog.r = this.k;
            return userLicenseDialog;
        }
    }
}
