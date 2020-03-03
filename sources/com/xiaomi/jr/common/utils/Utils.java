package com.xiaomi.jr.common.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;
import com.alipay.mobile.common.logging.api.LogCategory;
import com.xiaomi.jr.annotation.anr.ANRProcessor;
import com.xiaomi.jr.common.os.SystemProperties;
import com.xiaomi.youpin.share.model.ShareChannel;
import com.ximalaya.ting.android.opensdk.constants.PreferenceConstantsInOpenSdk;
import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;

public class Utils {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1423a = "Utils";
    private static int b;

    /* access modifiers changed from: private */
    @SuppressLint({"MissingPermission"})
    public static final Location g(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService("location");
        Location lastKnownLocation = locationManager.isProviderEnabled("gps") ? locationManager.getLastKnownLocation("gps") : null;
        return (lastKnownLocation != null || !locationManager.isProviderEnabled(LogCategory.CATEGORY_NETWORK)) ? lastKnownLocation : locationManager.getLastKnownLocation(LogCategory.CATEGORY_NETWORK);
    }

    public static int a(Context context, Uri uri) {
        try {
            return b(context, uri);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private static int b(Context context, Uri uri) throws FileNotFoundException {
        int i;
        String authority = uri.getAuthority();
        if (!TextUtils.isEmpty(authority)) {
            try {
                Resources resourcesForApplication = context.getPackageManager().getResourcesForApplication(authority);
                List<String> pathSegments = uri.getPathSegments();
                if (pathSegments != null) {
                    int size = pathSegments.size();
                    if (size == 1) {
                        try {
                            i = Integer.parseInt(pathSegments.get(0));
                        } catch (NumberFormatException unused) {
                            throw new FileNotFoundException("Single path segment is not a resource ID: " + uri);
                        }
                    } else if (size == 2) {
                        i = resourcesForApplication.getIdentifier(pathSegments.get(1), pathSegments.get(0), authority);
                    } else {
                        throw new FileNotFoundException("More than two path segments: " + uri);
                    }
                    if (i != 0) {
                        return i;
                    }
                    throw new FileNotFoundException("No resource found for: " + uri);
                }
                throw new FileNotFoundException("No path: " + uri);
            } catch (PackageManager.NameNotFoundException unused2) {
                throw new FileNotFoundException("No package found for authority: " + uri);
            }
        } else {
            throw new FileNotFoundException("No authority: " + uri);
        }
    }

    public static int b(Context context) {
        if (b == 0) {
            try {
                b = context.getResources().getDimensionPixelSize(context.getResources().getIdentifier(PreferenceConstantsInOpenSdk.B, "dimen", "android"));
            } catch (Exception unused) {
            }
        }
        return b;
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String c(Context context) {
        ClipData.Item itemAt;
        ClipData primaryClip = ((ClipboardManager) context.getSystemService(ShareChannel.d)).getPrimaryClip();
        if (primaryClip == null || primaryClip.getItemCount() <= 0 || (itemAt = primaryClip.getItemAt(0)) == null || itemAt.getText() == null) {
            return null;
        }
        return itemAt.getText().toString();
    }

    public static void a(Context context, String str) {
        ((ClipboardManager) context.getSystemService(ShareChannel.d)).setPrimaryClip(ClipData.newPlainText("text", str));
    }

    private static boolean d() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

    public static void a() {
        if (!d()) {
            throw new IllegalStateException("Should be on main thread.");
        }
    }

    public static void b() {
        if (d()) {
            throw new IllegalStateException("Should NOT be on main thread.");
        }
    }

    public static boolean c() {
        return SystemProperties.a("ro.debuggable", 0) == 1;
    }

    public static int d(Context context) {
        return e(context).x;
    }

    public static Point e(Context context) {
        Point point = new Point();
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        if (Build.VERSION.SDK_INT >= 17) {
            defaultDisplay.getRealSize(point);
        } else {
            try {
                point.set(((Integer) Display.class.getMethod("getRawWidth", new Class[0]).invoke(defaultDisplay, new Object[0])).intValue(), ((Integer) Display.class.getMethod("getRawHeight", new Class[0]).invoke(defaultDisplay, new Object[0])).intValue());
            } catch (Exception e) {
                defaultDisplay.getSize(point);
                e.printStackTrace();
            }
        }
        return point;
    }

    public static Location a(final Context context) {
        return (Location) ANRProcessor.a(new Callable<Object>() {
            public Object call() throws Exception {
                return Utils.g(context);
            }
        }, 4500);
    }

    public static void a(Context context, int i) {
        b(context, context.getString(i));
    }

    public static void a(Context context, int i, int i2) {
        a(context, context.getString(i), i2);
    }

    public static void b(Context context, String str) {
        a(context, str, 1);
    }

    public static void a(Context context, String str, int i) {
        if (d() || Looper.myLooper() != null) {
            Toast.makeText(context, str, i).show();
        } else {
            new Handler(Looper.getMainLooper()).post(new Runnable(context, str, i) {
                private final /* synthetic */ Context f$0;
                private final /* synthetic */ String f$1;
                private final /* synthetic */ int f$2;

                {
                    this.f$0 = r1;
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    Toast.makeText(this.f$0, this.f$1, this.f$2).show();
                }
            });
        }
    }

    public static void a(DialogFragment dialogFragment, FragmentManager fragmentManager, String str) {
        if (fragmentManager.isStateSaved()) {
            MifiLog.d("Utils", "The dialog should be shown before state saved.");
        } else {
            dialogFragment.show(fragmentManager, str);
        }
    }

    public static void a(Context context, Intent intent) {
        if (TextUtils.isEmpty(intent.getStringExtra("from")) && (context instanceof Activity)) {
            Intent intent2 = ((Activity) context).getIntent();
            String stringExtra = intent2.getStringExtra("from");
            if (TextUtils.isEmpty(stringExtra)) {
                String stringExtra2 = intent2.getStringExtra("url");
                if (!TextUtils.isEmpty(stringExtra2)) {
                    stringExtra = UrlUtils.b(stringExtra2, "from");
                }
            }
            if (!TextUtils.isEmpty(stringExtra)) {
                intent.putExtra("from", stringExtra);
            }
        }
    }
}
