package com.xiaomi.jr.mipay.codepay.access;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.DialogFragment;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import com.miui.supportlite.app.AlertDialog;
import com.xiaomi.jr.common.utils.PreferenceUtils;
import com.xiaomi.jr.common.utils.Utils;
import com.xiaomi.jr.dialog.DialogManager;
import com.xiaomi.jr.mipay.codepay.R;
import com.xiaomi.jr.mipay.codepay.access.AccessManager;

public class MipayLicense {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10884a = "key_user_confirm";
    private static final String b = "https://mipay.com/res/doc/eula/miwallet.html";

    public static boolean a(Context context) {
        return PreferenceUtils.e(context, "codepay", "key_user_confirm");
    }

    public static void a(Activity activity, AccessManager.Callback callback) {
        AlertDialog a2 = new AlertDialog.Builder(activity).a(R.string.jr_mipay_license_title).b(a(activity)).b(17039360, (DialogInterface.OnClickListener) null).a(R.string.jr_mipay_agree, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener(activity, callback) {
            private final /* synthetic */ Activity f$0;
            private final /* synthetic */ AccessManager.Callback f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            public final void onClick(DialogInterface dialogInterface, int i) {
                MipayLicense.a(this.f$0, this.f$1, dialogInterface, i);
            }
        }).a(false).a((DialogInterface.OnShowListener) $$Lambda$MipayLicense$SPxJkkzBSAEaJxPzsiDuz6mc0c.INSTANCE).a();
        a2.a((AlertDialog.TextUpdater) $$Lambda$MipayLicense$_lDer2Q8wPNMF6JcqV1c49O1b_I.INSTANCE);
        DialogManager.a((DialogFragment) a2, activity.getApplicationContext(), "wallet_license");
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void a(Activity activity, AccessManager.Callback callback, DialogInterface dialogInterface, int i) {
        PreferenceUtils.a((Context) activity, "codepay", "key_user_confirm", true);
        AccessManager.a((Context) activity, callback);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void a(DialogInterface dialogInterface) {
        AlertDialog alertDialog = (AlertDialog) dialogInterface;
        alertDialog.setCancelable(false);
        alertDialog.b().setMovementMethod(LinkMovementMethod.getInstance());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void a(TextView textView, CharSequence charSequence) {
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setText(charSequence);
    }

    private static CharSequence a(final Activity activity) {
        String string = activity.getString(R.string.jr_mipay_license_message_span);
        String string2 = activity.getString(R.string.jr_mipay_license_message, new Object[]{string});
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(string2);
        int indexOf = string2.indexOf(string);
        spannableStringBuilder.setSpan(new ClickableSpan() {
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.addCategory("android.intent.category.BROWSABLE");
                intent.setData(Uri.parse(MipayLicense.b.trim()));
                Utils.a((Context) activity, intent);
                activity.startActivity(intent);
            }

            public void updateDrawState(TextPaint textPaint) {
                textPaint.setUnderlineText(true);
                textPaint.setColor(activity.getResources().getColor(17170435));
            }
        }, indexOf, string.length() + indexOf, 33);
        return spannableStringBuilder;
    }
}
