package com.xiaomi.youpin.common.util;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresPermission;
import android.support.v4.content.FileProvider;
import com.google.android.exoplayer2.C;
import com.xiaomi.youpin.share.ShareObject;
import java.io.File;
import org.jacoco.agent.rt.internal_8ff85ea.core.runtime.AgentOptions;

public final class IntentUtils {
    private IntentUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static Intent a(String str) {
        return a(h(str), false);
    }

    public static Intent a(File file) {
        return a(file, false);
    }

    public static Intent a(String str, boolean z) {
        return a(h(str), z);
    }

    public static Intent a(File file, boolean z) {
        Uri uri;
        if (file == null) {
            return null;
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        if (Build.VERSION.SDK_INT < 24) {
            uri = Uri.fromFile(file);
        } else {
            intent.setFlags(1);
            uri = FileProvider.getUriForFile(Utils.a(), Utils.a().getPackageName() + ".utilcode.provider", file);
        }
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        return a(intent, z);
    }

    @Deprecated
    public static Intent a(String str, String str2) {
        return a(h(str), str2, false);
    }

    @Deprecated
    public static Intent a(File file, String str) {
        return a(file, str, false);
    }

    @Deprecated
    public static Intent a(String str, String str2, boolean z) {
        return a(h(str), str2, z);
    }

    @Deprecated
    public static Intent a(File file, String str, boolean z) {
        Uri uri;
        if (file == null) {
            return null;
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        if (Build.VERSION.SDK_INT < 24) {
            uri = Uri.fromFile(file);
        } else {
            intent.setFlags(1);
            uri = FileProvider.getUriForFile(Utils.a(), str, file);
        }
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        return a(intent, z);
    }

    public static Intent b(String str) {
        return b(str, false);
    }

    public static Intent b(String str, boolean z) {
        Intent intent = new Intent("android.intent.action.DELETE");
        intent.setData(Uri.parse("package:" + str));
        return a(intent, z);
    }

    public static Intent c(String str) {
        return c(str, false);
    }

    public static Intent c(String str, boolean z) {
        Intent launchIntentForPackage = Utils.a().getPackageManager().getLaunchIntentForPackage(str);
        if (launchIntentForPackage == null) {
            return null;
        }
        return a(launchIntentForPackage, z);
    }

    public static Intent d(String str) {
        return d(str, false);
    }

    public static Intent d(String str, boolean z) {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.parse("package:" + str));
        return a(intent, z);
    }

    public static Intent e(String str) {
        return e(str, false);
    }

    public static Intent e(String str, boolean z) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.TEXT", str);
        return a(intent, z);
    }

    public static Intent b(String str, String str2) {
        return b(str, str2, false);
    }

    public static Intent b(String str, String str2, boolean z) {
        if (str2 == null || str2.length() == 0) {
            return null;
        }
        return a(str, new File(str2), z);
    }

    public static Intent a(String str, File file) {
        return a(str, file, false);
    }

    public static Intent a(String str, File file, boolean z) {
        if (file == null || !file.isFile()) {
            return a(str, Uri.fromFile(file), z);
        }
        return null;
    }

    public static Intent a(String str, Uri uri) {
        return a(str, uri, false);
    }

    public static Intent a(String str, Uri uri, boolean z) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.TEXT", str);
        intent.putExtra("android.intent.extra.STREAM", uri);
        intent.setType(ShareObject.d);
        return a(intent, z);
    }

    public static Intent c(String str, String str2) {
        return a(str, str2, (Bundle) null, false);
    }

    public static Intent c(String str, String str2, boolean z) {
        return a(str, str2, (Bundle) null, z);
    }

    public static Intent a(String str, String str2, Bundle bundle) {
        return a(str, str2, bundle, false);
    }

    public static Intent a(String str, String str2, Bundle bundle, boolean z) {
        Intent intent = new Intent("android.intent.action.VIEW");
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        intent.setComponent(new ComponentName(str, str2));
        return a(intent, z);
    }

    public static Intent a() {
        return a(false);
    }

    public static Intent a(boolean z) {
        Intent intent = new Intent("android.intent.action.ACTION_REQUEST_SHUTDOWN");
        intent.putExtra("android.intent.extra.KEY_CONFIRM", false);
        return a(intent, z);
    }

    public static Intent f(String str) {
        return f(str, false);
    }

    public static Intent f(String str, boolean z) {
        return a(new Intent("android.intent.action.DIAL", Uri.parse("tel:" + str)), z);
    }

    @RequiresPermission("android.permission.CALL_PHONE")
    public static Intent g(String str) {
        return g(str, false);
    }

    @RequiresPermission("android.permission.CALL_PHONE")
    public static Intent g(String str, boolean z) {
        return a(new Intent("android.intent.action.CALL", Uri.parse("tel:" + str)), z);
    }

    public static Intent d(String str, String str2) {
        return d(str, str2, false);
    }

    public static Intent d(String str, String str2, boolean z) {
        Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:" + str));
        intent.putExtra("sms_body", str2);
        return a(intent, z);
    }

    public static Intent a(Uri uri) {
        return a(uri, false);
    }

    public static Intent a(Uri uri, boolean z) {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(AgentOptions.k, uri);
        intent.addFlags(1);
        return a(intent, z);
    }

    private static Intent a(Intent intent, boolean z) {
        return z ? intent.addFlags(C.ENCODING_PCM_MU_LAW) : intent;
    }

    private static File h(String str) {
        if (i(str)) {
            return null;
        }
        return new File(str);
    }

    private static boolean i(String str) {
        if (str == null) {
            return true;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
