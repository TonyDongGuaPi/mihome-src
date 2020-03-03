package com.xiaomi.payment.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.mibi.common.base.StepActivity;
import com.mibi.common.component.IntentSpan;
import com.mibi.common.data.Utils;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.data.UserEnterUtils;
import com.xiaomi.payment.platform.R;
import miuipub.os.SystemProperties;

public class MibiLicenseActivity extends StepActivity {
    public static final String KEY_APP_ENABLE = "key_app_enable";
    public static final String KEY_USER_CONFIRM = "key_user_confirm";

    /* renamed from: a  reason: collision with root package name */
    private static final String f12443a = "default";

    /* access modifiers changed from: protected */
    public void doCreate(Bundle bundle) {
        super.doCreate(bundle);
        String string = getString(R.string.mibi_license_title);
        String string2 = getString(R.string.mibi_normal_license_name);
        String string3 = getString(R.string.mibi_privacy_license_name);
        String string4 = getString(R.string.mibi_all_license_message, new Object[]{string3, string2});
        String b = MibiConstants.b(MibiConstants.bw);
        SpannableStringBuilder a2 = a(a(new SpannableStringBuilder(string4), string4, string2, b), string4, string3, String.format(MibiConstants.bx, new Object[]{SystemProperties.a("ro.miui.region", "default"), getResources().getConfiguration().locale.toString()}));
        View inflate = LayoutInflater.from(this).inflate(R.layout.mibi_license_message, (ViewGroup) null);
        TextView textView = (TextView) inflate.findViewById(R.id.message);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setTextColor(getResources().getColor(R.color.mibi_text_color_black));
        textView.setText(a2);
        new AlertDialog.Builder(this).setTitle(string).setView(inflate).setNegativeButton(17039360, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                MibiLicenseActivity.this.setResult(0);
                dialogInterface.dismiss();
                MibiLicenseActivity.this.finish();
            }
        }).setPositiveButton(R.string.mibi_btn_agree, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Utils.b((Context) MibiLicenseActivity.this, MibiLicenseActivity.KEY_USER_CONFIRM, true);
                Utils.b((Context) MibiLicenseActivity.this, MibiLicenseActivity.KEY_APP_ENABLE, true);
                UserEnterUtils.a(MibiLicenseActivity.this.getApplicationContext(), true);
                MibiLicenseActivity.this.setResult(-1);
                dialogInterface.dismiss();
                MibiLicenseActivity.this.finish();
            }
        }).show().setCancelable(false);
    }

    private SpannableStringBuilder a(SpannableStringBuilder spannableStringBuilder, String str, String str2, String str3) {
        int indexOf = str.indexOf(str2);
        int length = str2.length() + indexOf;
        IntentSpan intentSpan = new IntentSpan(this, str2, str3);
        intentSpan.a(new IntentSpan.OnClickListener() {
            public void onClick(String str, String str2) {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.addCategory("android.intent.category.BROWSABLE");
                intent.setData(Uri.parse(str.trim()));
                MibiLicenseActivity.this.startActivity(intent);
            }
        });
        spannableStringBuilder.setSpan(intentSpan, indexOf, length, 33);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.mibi_text_color_blue)), indexOf, length, 33);
        return spannableStringBuilder;
    }

    /* access modifiers changed from: protected */
    public void doPreCreate(Bundle bundle) {
        super.doPreCreate(bundle);
        a();
    }

    private void a() {
        if (Utils.b()) {
            setRequestedOrientation(-1);
        }
    }

    public static boolean needShowLicense(Context context) {
        return !UserEnterUtils.a(context);
    }

    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }
}
