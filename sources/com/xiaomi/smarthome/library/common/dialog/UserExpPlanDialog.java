package com.xiaomi.smarthome.library.common.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.google.android.exoplayer2.C;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.frame.plugin.host.PluginHostApi;
import com.xiaomi.smarthome.library.common.widget.nestscroll.UIUtils;
import com.xiaomi.smarthome.miio.LanguageUtil;
import com.xiaomi.smarthome.miio.page.usrexpplan.UsrExpPlanActivity;
import java.util.Locale;

public class UserExpPlanDialog {
    public static void a(Context context, String str, Intent intent) {
        LicenseDialog licenseDialog = new LicenseDialog(str, intent, context);
        licenseDialog.setCancelable(false);
        licenseDialog.show();
    }

    static class LicenseDialog extends Dialog {

        /* renamed from: a  reason: collision with root package name */
        Intent f18595a;
        /* access modifiers changed from: private */
        public String b;

        public LicenseDialog(String str, Intent intent, @NonNull Context context) {
            super(context);
            this.b = str;
            this.f18595a = intent;
        }

        /* access modifiers changed from: protected */
        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            View inflate = LayoutInflater.from(getContext().getApplicationContext()).inflate(R.layout.dialog_user_exp_plan, (ViewGroup) null);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2, 80);
            layoutParams.setMargins(UIUtils.a(8), UIUtils.a(8), UIUtils.a(8), UIUtils.a(8));
            inflate.setLayoutParams(layoutParams);
            setContentView(inflate);
            a();
            Window window = getWindow();
            if (window != null) {
                window.setGravity(80);
                window.setBackgroundDrawable(new InsetDrawable(new ColorDrawable(0), UIUtils.a(8), UIUtils.a(8), UIUtils.a(8), UIUtils.a(8)));
                WindowManager.LayoutParams attributes = window.getAttributes();
                attributes.width = -1;
                attributes.height = -2;
                window.setAttributes(attributes);
            }
        }

        private void a() {
            int i;
            int i2;
            TextView textView = (TextView) findViewById(R.id.join_usr_exp_plan_tv);
            String stringExtra = this.f18595a == null ? null : this.f18595a.getStringExtra("usr_exp_plan_tips");
            AnonymousClass1 r3 = new ClickableSpan() {
                public void onClick(View view) {
                    Intent intent = new Intent(LicenseDialog.this.getContext().getApplicationContext(), UsrExpPlanActivity.class);
                    if (LicenseDialog.this.f18595a != null) {
                        intent.putExtras(LicenseDialog.this.f18595a);
                    }
                    intent.addFlags(C.ENCODING_PCM_MU_LAW);
                    LicenseDialog.this.getContext().getApplicationContext().startActivity(intent);
                }

                public void updateDrawState(TextPaint textPaint) {
                    super.updateDrawState(textPaint);
                    textPaint.setColor(Color.parseColor("#33aaff"));
                    textPaint.setUnderlineText(false);
                }
            };
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            int i3 = 0;
            if (TextUtils.isEmpty(stringExtra)) {
                String a2 = LanguageUtil.a((Locale) null, (int) R.string.disclaim_join_user_exp_plan);
                i2 = a2.indexOf("#start#");
                i = a2.indexOf("#end#") - "#start#".length();
                stringExtra = a2.replace("#start#", "").replace("#end#", "");
            } else {
                i2 = this.f18595a.getIntExtra("usr_exp_plan_start", 0);
                i = this.f18595a.getIntExtra("usr_exp_plan_end", stringExtra.length());
                if (i2 < 0) {
                    i2 = 0;
                }
                if (i > stringExtra.length()) {
                    i = stringExtra.length();
                }
            }
            if (i2 >= i) {
                i2 = 0;
            } else {
                i3 = i;
            }
            spannableStringBuilder.append(stringExtra);
            if (i2 >= 0 && i3 > 0) {
                try {
                    spannableStringBuilder.setSpan(r3, i2, i3, 33);
                } catch (Exception unused) {
                }
            }
            textView.setText(spannableStringBuilder);
            textView.setMovementMethod(LinkMovementMethod.getInstance());
            Button button = (Button) findViewById(R.id.cancel);
            Button button2 = (Button) findViewById(R.id.agree);
            button.setText(LanguageUtil.a((Locale) null, (int) R.string.cancel));
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    PluginHostApi.instance().setUsrExpPlanEnabled(LicenseDialog.this.b, false);
                    LicenseDialog.this.dismiss();
                }
            });
            button2.setText(LanguageUtil.a((Locale) null, (int) R.string.agree_go_on));
            button2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    PluginHostApi.instance().setUsrExpPlanEnabled(LicenseDialog.this.b, true);
                    LicenseDialog.this.dismiss();
                }
            });
        }
    }
}
