package com.xiaomi.jr.appbase.utils;

import android.content.Context;
import android.content.DialogInterface;

public class DialogHelper {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10322a = "DialogHelper";

    public static void a(Context context, String str, String str2) {
        a(context, str, str2, true, (String) null, (String) null, (DialogInterface.OnClickListener) null, (DialogInterface.OnClickListener) null, "web_dialog");
    }

    /* JADX WARNING: type inference failed for: r1v3, types: [android.view.View] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(android.content.Context r4, java.lang.String r5, java.lang.String r6, boolean r7, java.lang.String r8, java.lang.String r9, android.content.DialogInterface.OnClickListener r10, android.content.DialogInterface.OnClickListener r11, java.lang.String r12) {
        /*
            android.view.LayoutInflater r0 = android.view.LayoutInflater.from(r4)
            int r1 = com.xiaomi.jr.appbase.R.layout.dialog_common
            r2 = 0
            r3 = 0
            android.view.View r0 = r0.inflate(r1, r2, r3)
            boolean r1 = android.text.TextUtils.isEmpty(r6)
            if (r1 != 0) goto L_0x002d
            int r1 = com.xiaomi.jr.appbase.R.id.message_textview
            android.view.View r1 = r0.findViewById(r1)
            r2 = r1
            android.widget.TextView r2 = (android.widget.TextView) r2
            android.text.method.MovementMethod r1 = android.text.method.ScrollingMovementMethod.getInstance()
            r2.setMovementMethod(r1)
            java.lang.String r1 = "<br>"
            java.lang.String r3 = "\n"
            java.lang.String r6 = r6.replace(r1, r3)
            com.xiaomi.jr.richtext.RichTextRender.a((android.widget.TextView) r2, (java.lang.String) r6)
        L_0x002d:
            boolean r6 = android.text.TextUtils.isEmpty(r8)
            if (r6 == 0) goto L_0x0039
            int r6 = com.xiaomi.jr.appbase.R.string.dialog_confirm_text_i_know
            java.lang.String r8 = r4.getString(r6)
        L_0x0039:
            com.miui.supportlite.app.AlertDialog$Builder r6 = new com.miui.supportlite.app.AlertDialog$Builder
            r6.<init>(r4)
            com.miui.supportlite.app.AlertDialog$Builder r5 = r6.a((java.lang.CharSequence) r5)
            com.miui.supportlite.app.AlertDialog$Builder r5 = r5.a((java.lang.CharSequence) r8, (android.content.DialogInterface.OnClickListener) r10)
            com.miui.supportlite.app.AlertDialog$Builder r5 = r5.b((java.lang.CharSequence) r9, (android.content.DialogInterface.OnClickListener) r11)
            com.miui.supportlite.app.AlertDialog$Builder r5 = r5.a((boolean) r7)
            com.miui.supportlite.app.AlertDialog$Builder r5 = r5.a((android.view.View) r0)
            com.miui.supportlite.app.AlertDialog r5 = r5.a()
            if (r2 == 0) goto L_0x005b
            com.xiaomi.jr.richtext.ViewContextHelper.a(r2, r5)
        L_0x005b:
            com.xiaomi.jr.dialog.DialogManager.a((android.support.v4.app.DialogFragment) r5, (android.content.Context) r4, (java.lang.String) r12)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.jr.appbase.utils.DialogHelper.a(android.content.Context, java.lang.String, java.lang.String, boolean, java.lang.String, java.lang.String, android.content.DialogInterface$OnClickListener, android.content.DialogInterface$OnClickListener, java.lang.String):void");
    }
}
