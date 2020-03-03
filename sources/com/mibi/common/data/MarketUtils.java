package com.mibi.common.data;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.exoplayer2.C;
import miuipub.app.AlertDialog;

public class MarketUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7526a = "MarketUtils";
    private static final String b = "market://details";
    private static final String c = "http://app.mi.com";

    public interface InstallPromtListener {
        void a();

        void b();
    }

    public static boolean a(Context context, String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 0);
        } catch (Exception unused) {
            packageInfo = null;
        }
        return packageInfo != null;
    }

    public static void a(final Context context, String str, final String str2, final InstallPromtListener installPromtListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.b((CharSequence) str);
        builder.a(17039370, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                MarketUtils.b(context, str2);
                if (installPromtListener != null) {
                    installPromtListener.a();
                }
            }
        });
        builder.b(17039360, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                if (installPromtListener != null) {
                    installPromtListener.b();
                }
            }
        });
        builder.c();
    }

    public static boolean b(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return false;
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        if (a(context, "com.xiaomi.market")) {
            intent.setData(Uri.parse(b).buildUpon().appendQueryParameter("id", str).appendQueryParameter("back", "true").build());
            intent.setPackage("com.xiaomi.market");
        } else {
            Uri parse = Uri.parse(c);
            intent.addCategory("android.intent.category.BROWSABLE");
            intent.setData(parse.buildUpon().appendQueryParameter("id", str).build());
        }
        if (!(context instanceof Activity)) {
            intent.addFlags(C.ENCODING_PCM_MU_LAW);
        }
        try {
            context.startActivity(intent);
            return true;
        } catch (Exception e) {
            Log.e(f7526a, "Utils openDetailInMarket exception ", e);
            return false;
        }
    }
}
