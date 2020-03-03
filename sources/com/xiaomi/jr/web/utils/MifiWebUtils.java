package com.xiaomi.jr.web.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.text.Html;
import android.text.TextUtils;
import android.util.Base64;
import android.webkit.CookieManager;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebStorage;
import android.webkit.WebView;
import com.facebook.common.util.UriUtil;
import com.xiaomi.jr.account.XiaomiAccountManager;
import com.xiaomi.jr.appbase.utils.AppConstants;
import com.xiaomi.jr.common.utils.FileUtils;
import com.xiaomi.jr.common.utils.MifiLog;
import com.xiaomi.jr.common.utils.UrlUtils;
import com.xiaomi.jr.common.utils.Utils;
import java.io.File;
import java.util.regex.Pattern;

public class MifiWebUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11080a = "MifiWebUtils";
    private static final String b = "data";
    private static final String c = "data:image/.*;base64,";
    private static final String d = ".*<[a-z][\\s\\S]*>.*";
    private static final Pattern e = Pattern.compile(d);

    public static boolean a() {
        return MifiLog.f1417a;
    }

    public static void a(WebView webView) {
        webView.removeJavascriptInterface("searchBoxJavaBridge_");
        webView.removeJavascriptInterface("accessibility");
        webView.removeJavascriptInterface("accessibilityTraversal");
        WebSettings settings = webView.getSettings();
        settings.setAllowFileAccess(false);
        settings.setAllowFileAccessFromFileURLs(false);
        settings.setAllowUniversalAccessFromFileURLs(false);
        settings.setSavePassword(false);
    }

    public static boolean a(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return false;
        }
        Uri parse = Uri.parse(str);
        Uri parse2 = Uri.parse(str2);
        String path = parse.getPath();
        if (TextUtils.isEmpty(path)) {
            path = "/";
        }
        String path2 = parse2.getPath();
        if (TextUtils.isEmpty(path2)) {
            path2 = "/";
        }
        if (!TextUtils.equals(parse.getHost(), parse2.getHost()) || !TextUtils.equals(path, path2)) {
            return false;
        }
        return true;
    }

    public static boolean a(String str) {
        return e.matcher(str).matches();
    }

    public static CharSequence a(@NonNull Context context, String str) {
        return Html.fromHtml(str, new Html.ImageGetter(context) {
            private final /* synthetic */ Context f$0;

            {
                this.f$0 = r1;
            }

            public final Drawable getDrawable(String str) {
                return MifiWebUtils.b(this.f$0, str);
            }
        }, (Html.TagHandler) null);
    }

    /* access modifiers changed from: private */
    public static Drawable b(Context context, String str) {
        Bitmap decodeFile;
        Uri parse = Uri.parse(str);
        String scheme = parse.getScheme();
        Resources resources = context.getResources();
        Drawable drawable = null;
        if (TextUtils.equals(scheme, UriUtil.QUALIFIED_RESOURCE_SCHEME)) {
            int a2 = Utils.a(context, parse);
            if (a2 != 0) {
                drawable = resources.getDrawable(a2);
            }
            if (drawable != null) {
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            }
            return drawable;
        }
        if (TextUtils.equals(scheme, "data")) {
            try {
                byte[] decode = Base64.decode(str.replaceAll(c, ""), 2);
                Bitmap decodeByteArray = BitmapFactory.decodeByteArray(decode, 0, decode.length);
                if (decodeByteArray != null) {
                    BitmapDrawable bitmapDrawable = new BitmapDrawable(resources, decodeByteArray);
                    bitmapDrawable.setBounds(0, 0, bitmapDrawable.getIntrinsicWidth(), bitmapDrawable.getIntrinsicHeight());
                    return bitmapDrawable;
                }
            } catch (Exception e2) {
                MifiLog.e(f11080a, "can not get image data - " + e2.toString());
            }
        } else if (TextUtils.equals(scheme, "file") && (decodeFile = BitmapFactory.decodeFile(str)) != null) {
            BitmapDrawable bitmapDrawable2 = new BitmapDrawable(resources, decodeFile);
            bitmapDrawable2.setBounds(0, 0, bitmapDrawable2.getIntrinsicWidth(), bitmapDrawable2.getIntrinsicHeight());
            return bitmapDrawable2;
        }
        return null;
    }

    public static String b(String str) {
        if (XiaomiAccountManager.a().d() && f(str)) {
            return UrlUtils.a(str, "cUserId", XiaomiAccountManager.g());
        }
        return str;
    }

    private static boolean f(@NonNull String str) {
        String host = Uri.parse(str).getHost();
        if (TextUtils.isEmpty(host)) {
            return false;
        }
        if (host.endsWith("mi.com") || host.endsWith("xiaomi.com") || host.endsWith("mipay.com")) {
            return true;
        }
        return false;
    }

    public static boolean c(String str) {
        Uri parse = Uri.parse(str);
        if (parse.isOpaque()) {
            return false;
        }
        return parse.getBooleanQueryParameter(AppConstants.g, false);
    }

    public static boolean d(String str) {
        return UrlUtils.a(str, "zoomSupported", false);
    }

    public static boolean e(String str) {
        return UrlUtils.a(str, "_shield", false);
    }

    public static void a(Context context) {
        WebStorage.getInstance().deleteAllData();
        new WebView(context).clearCache(true);
        if (Build.VERSION.SDK_INT >= 21) {
            CookieManager.getInstance().removeAllCookies((ValueCallback) null);
            WebView.clearClientCertPreferences((Runnable) null);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            FileUtils.a(new File(context.getFilesDir().getParent() + "/app_webview"));
            return;
        }
        String[] databaseList = context.databaseList();
        if (databaseList != null) {
            for (String str : databaseList) {
                if (str.startsWith("webview")) {
                    context.deleteDatabase(str);
                }
            }
        }
    }
}
