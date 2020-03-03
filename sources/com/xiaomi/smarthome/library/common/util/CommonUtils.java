package com.xiaomi.smarthome.library.common.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Binder;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.os.RemoteException;
import android.os.StatFs;
import android.provider.Settings;
import android.support.v4.content.PermissionChecker;
import android.support.v4.util.LruCache;
import android.telephony.TelephonyManager;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.alipay.sdk.util.i;
import com.mi.global.shop.webview.WebViewHelper;
import com.taobao.weex.common.Constants;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.passport.ui.internal.PassportUI;
import com.xiaomi.router.miio.miioplugin.PluginServiceManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.ModelGroupInfo;
import com.xiaomi.smarthome.device.SmartHomeDeviceHelper;
import com.xiaomi.smarthome.download.Constants;
import com.xiaomi.smarthome.download.Downloads;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.process.ProcessUtil;
import com.xiaomi.smarthome.frame.server_compact.ServerBean;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.framework.log.MyLog;
import com.xiaomi.smarthome.library.commonapi.SystemApi;
import com.xiaomi.smarthome.library.deviceId.DeviceIdCompat;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import miui.os.SystemProperties;
import miuipub.reflect.Field;

public abstract class CommonUtils {
    private static final String[] A = {"qwertyuiop", "asdfghjkl", "zxcvbnm"};
    private static long B = 0;
    private static Random C = null;
    private static final String D = "%s_%d.%s";
    private static String E = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    private static Pattern F = Pattern.compile(E);

    /* renamed from: a  reason: collision with root package name */
    public static final Pattern f18653a = Pattern.compile("miid:[1-9]{1}[0-9]{0,}");
    public static final Pattern b = Pattern.compile("msgto://[1-9]{1}[0-9]{0,}.*");
    public static final Pattern c = Pattern.compile("puttxt://[1-9]{1}[0-9]{0,}.*");
    public static final Pattern d = Pattern.compile("@(.+?)<([1-9]{1}[0-9]{0,})>");
    public static final Pattern e = Pattern.compile("@<a href=\"friend://([1-9]{1}[0-9]{0,})\">(.+?)(</a>)");
    public static final String f = "男";
    public static final String g = "女";
    public static final String h = "3gwap";
    public static final String i = "3gnet";
    public static final String j = "wifi";
    public static final String k = "#777";
    public static final int l = 1;
    public static final int m = 2;
    public static final int n = 3;
    public static final int o = 1000;
    public static final int p = 60000;
    public static final int q = 3600000;
    public static final int r = 86400000;
    public static final int s = 800;
    public static final int t = 480;
    public static final int u = 1;
    public static final int v = 2;
    public static final int w = 150;
    public static final int x = 320;
    private static final String y = "%s%cuuid=%s";
    private static final int z = 1024;

    public static void a(boolean z2) {
    }

    public static boolean a(char c2) {
        return (c2 >= 'a' && c2 <= 'z') || (c2 >= 'A' && c2 <= 'Z');
    }

    public static boolean p(Context context) {
        return true;
    }

    public static void a(ListView listView) {
        a(listView, 0, 0, 100);
    }

    @SuppressLint({"NewApi"})
    public static void a(ListView listView, int i2, int i3, int i4) {
        if (Build.VERSION.SDK_INT >= 11) {
            listView.smoothScrollToPositionFromTop(i2, i3, i4);
        } else {
            listView.setSelectionFromTop(i2, i3);
        }
    }

    public static void a(String str, Activity activity) {
        a(str, activity, (Runnable) null, (Runnable) null);
    }

    public static void a(final String str, final Activity activity, final Runnable runnable, final Runnable runnable2) {
        activity.runOnUiThread(new Runnable() {
            public void run() {
                if (activity.isFinishing()) {
                    MyLog.e("Activity 已经结束，不要再显示对话框");
                    return;
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setMessage(str);
                builder.setPositiveButton(activity.getString(R.string.ok_button), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (runnable != null) {
                            runnable.run();
                        }
                    }
                });
                if (runnable2 != null) {
                    builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            runnable2.run();
                        }
                    });
                    builder.setCancelable(true);
                    builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        public void onCancel(DialogInterface dialogInterface) {
                            runnable2.run();
                        }
                    });
                } else {
                    builder.setCancelable(false);
                }
                builder.create().show();
            }
        });
    }

    public static boolean a(String str) {
        if (str == null) {
            return false;
        }
        try {
            new URL(str);
            return true;
        } catch (MalformedURLException unused) {
            return false;
        }
    }

    protected static URL b(String str) {
        if (str == null) {
            return null;
        }
        try {
            return new URL(str);
        } catch (MalformedURLException unused) {
            return null;
        }
    }

    public static String a(InputStream inputStream) {
        StringWriter stringWriter = new StringWriter();
        char[] cArr = new char[1024];
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            while (true) {
                int read = bufferedReader.read(cArr);
                if (read == -1) {
                    return stringWriter.toString();
                }
                stringWriter.write(cArr, 0, read);
            }
        } catch (IOException e2) {
            MyLog.a((Throwable) e2);
            return "";
        }
    }

    public static void a(SQLiteDatabase sQLiteDatabase, String str, String[] strArr) {
        String str2 = "CREATE TABLE " + str + Operators.BRACKET_START_STR + "_id" + " INTEGER  PRIMARY KEY ,";
        for (int i2 = 0; i2 < strArr.length - 1; i2 += 2) {
            if (i2 != 0) {
                str2 = str2 + ",";
            }
            str2 = str2 + strArr[i2] + " " + strArr[i2 + 1];
        }
        sQLiteDatabase.execSQL(str2 + ");");
    }

    public static byte[] c(String str) {
        try {
            return str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return str.getBytes();
        }
    }

    public static String[] a(List<String> list) {
        String[] strArr = new String[list.size()];
        list.toArray(strArr);
        return strArr;
    }

    public static long[] b(List<Long> list) {
        long[] jArr = new long[list.size()];
        for (int i2 = 0; i2 < list.size(); i2++) {
            jArr[i2] = list.get(i2).longValue();
        }
        return jArr;
    }

    public static int[] c(List<Integer> list) {
        int[] iArr = new int[list.size()];
        for (int i2 = 0; i2 < list.size(); i2++) {
            iArr[i2] = list.get(i2).intValue();
        }
        return iArr;
    }

    public static void d(String str) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        printWriter.println(str);
        printWriter.println(String.format("Current thread id (%s); thread name (%s)", new Object[]{Long.valueOf(Thread.currentThread().getId()), Thread.currentThread().getName()}));
        new Throwable("Call stack").printStackTrace(printWriter);
        MyLog.e(stringWriter.toString());
    }

    public static void a(Cursor cursor) {
        MyLog.e("Print out the cursor info");
        MyLog.e(String.format("Cursor.count = %d", new Object[]{Integer.valueOf(cursor.getCount())}));
        String[] columnNames = cursor.getColumnNames();
        MyLog.e("Columns");
        MyLog.a((Object[]) columnNames);
        if (cursor.moveToFirst()) {
            int i2 = 0;
            do {
                MyLog.e(String.format("Row %d", new Object[]{Integer.valueOf(i2)}));
                StringWriter stringWriter = new StringWriter();
                PrintWriter printWriter = new PrintWriter(stringWriter);
                for (int i3 = 0; i3 < cursor.getColumnCount(); i3++) {
                    printWriter.print(cursor.getColumnName(i3));
                    printWriter.print("=");
                    printWriter.print(cursor.getString(i3));
                    printWriter.println();
                }
                MyLog.e(stringWriter.toString());
                i2++;
            } while (cursor.moveToNext());
        }
    }

    public static String e(String str) {
        return str.replace(PassportUI.CHINA_COUNTRY_CODE, "");
    }

    public static String a() {
        if ("samsung".equalsIgnoreCase(Build.BRAND)) {
            return ",";
        }
        return (TextUtils.isEmpty(Build.MODEL) || !Build.MODEL.toUpperCase().contains("OMS")) ? i.b : ",";
    }

    public static void a(final Activity activity, String str) {
        AnonymousClass2 r0 = new Runnable() {
            public void run() {
                activity.finish();
            }
        };
        a(str, activity, (Runnable) r0, (Runnable) r0);
    }

    public static boolean b() {
        return Environment.getExternalStorageState().equals("removed");
    }

    public static boolean c() {
        return e() <= 102400;
    }

    public static boolean d() {
        return !SystemUtils.a() && !c() && !b();
    }

    public static long e() {
        if (SystemUtils.a()) {
            return 0;
        }
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        return ((long) statFs.getBlockSize()) * (((long) statFs.getAvailableBlocks()) - 4);
    }

    public static boolean a(Context context) {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || 1 != activeNetworkInfo.getType()) {
            return false;
        }
        return true;
    }

    public static String b(Context context) {
        NetworkInfo activeNetworkInfo;
        if (a(context)) {
            return "wifi";
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null) {
            return "";
        }
        return activeNetworkInfo.getExtraInfo();
    }

    public static boolean a(Bitmap bitmap, String str) {
        return a(bitmap, str, Bitmap.CompressFormat.PNG, 100);
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0032 A[SYNTHETIC, Splitter:B:22:0x0032] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x003e A[SYNTHETIC, Splitter:B:27:0x003e] */
    /* JADX WARNING: Removed duplicated region for block: B:33:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(android.graphics.Bitmap r2, java.lang.String r3, android.graphics.Bitmap.CompressFormat r4, int r5) {
        /*
            r0 = 0
            f((java.lang.String) r3)     // Catch:{ IOException -> 0x002c }
            java.io.File r1 = new java.io.File     // Catch:{ IOException -> 0x002c }
            r1.<init>(r3)     // Catch:{ IOException -> 0x002c }
            boolean r3 = r1.exists()     // Catch:{ IOException -> 0x002c }
            if (r3 != 0) goto L_0x0012
            r1.createNewFile()     // Catch:{ IOException -> 0x002c }
        L_0x0012:
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x002c }
            r3.<init>(r1)     // Catch:{ IOException -> 0x002c }
            boolean r2 = r2.compress(r4, r5, r3)     // Catch:{ IOException -> 0x0027, all -> 0x0024 }
            r3.close()     // Catch:{ IOException -> 0x001f }
            goto L_0x0023
        L_0x001f:
            r3 = move-exception
            com.xiaomi.smarthome.framework.log.MyLog.a((java.lang.Throwable) r3)
        L_0x0023:
            return r2
        L_0x0024:
            r2 = move-exception
            r0 = r3
            goto L_0x003c
        L_0x0027:
            r2 = move-exception
            r0 = r3
            goto L_0x002d
        L_0x002a:
            r2 = move-exception
            goto L_0x003c
        L_0x002c:
            r2 = move-exception
        L_0x002d:
            com.xiaomi.smarthome.framework.log.MyLog.a((java.lang.Throwable) r2)     // Catch:{ all -> 0x002a }
            if (r0 == 0) goto L_0x003a
            r0.close()     // Catch:{ IOException -> 0x0036 }
            goto L_0x003a
        L_0x0036:
            r2 = move-exception
            com.xiaomi.smarthome.framework.log.MyLog.a((java.lang.Throwable) r2)
        L_0x003a:
            r2 = 0
            return r2
        L_0x003c:
            if (r0 == 0) goto L_0x0046
            r0.close()     // Catch:{ IOException -> 0x0042 }
            goto L_0x0046
        L_0x0042:
            r3 = move-exception
            com.xiaomi.smarthome.framework.log.MyLog.a((java.lang.Throwable) r3)
        L_0x0046:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.library.common.util.CommonUtils.a(android.graphics.Bitmap, java.lang.String, android.graphics.Bitmap$CompressFormat, int):boolean");
    }

    public static boolean a(String str, File file, int i2) throws IOException {
        Rect rect;
        Bitmap b2 = b(str, i2, i2);
        Bitmap createBitmap = Bitmap.createBitmap(i2, i2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        int width = b2.getWidth();
        int height = b2.getHeight();
        Paint paint = new Paint();
        if (width > height) {
            rect = new Rect((width - height) / 2, 0, (width + height) / 2, height);
        } else {
            rect = new Rect(0, (height - width) / 2, width, (height + width) / 2);
        }
        canvas.drawBitmap(b2, rect, new Rect(0, 0, i2, i2), paint);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        boolean compress = createBitmap.compress(Bitmap.CompressFormat.PNG, 0, fileOutputStream);
        fileOutputStream.close();
        return compress;
    }

    public static boolean a(String str, File file, int i2, int i3, Bitmap.CompressFormat compressFormat) throws IOException {
        Rect rect;
        Bitmap b2 = b(str, i2, i3);
        Bitmap createBitmap = Bitmap.createBitmap(i2, i3, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        int width = b2.getWidth();
        int height = b2.getHeight();
        Paint paint = new Paint();
        if (width > height) {
            rect = new Rect((width - height) / 2, 0, (width + height) / 2, height);
        } else {
            rect = new Rect(0, (height - width) / 2, width, (height + width) / 2);
        }
        canvas.drawBitmap(b2, rect, new Rect(0, 0, i2, i3), paint);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        boolean compress = createBitmap.compress(compressFormat, 0, fileOutputStream);
        fileOutputStream.close();
        return compress;
    }

    public static Bitmap a(Bitmap bitmap, float f2, int i2, int i3) {
        Rect rect;
        if (bitmap == null) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(i2, i3, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Paint paint = new Paint();
        if (width > height) {
            rect = new Rect((width - height) / 2, 0, (width + height) / 2, height);
        } else {
            rect = new Rect(0, (height - width) / 2, width, (height + width) / 2);
        }
        Rect rect2 = new Rect(0, 0, i3, i3);
        RectF rectF = new RectF(rect2);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-12434878);
        canvas.drawRoundRect(rectF, f2, f2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect2, paint);
        bitmap.recycle();
        return createBitmap;
    }

    public static Bitmap a(Bitmap bitmap, int i2, int i3, Bitmap.Config config) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width < i2 && height < i2) {
            return bitmap;
        }
        Rect rect = new Rect(0, 0, width, height);
        Rect rect2 = new Rect(0, 0, i2, i3);
        Bitmap createBitmap = Bitmap.createBitmap(i2, i3, config);
        new Canvas(createBitmap).drawBitmap(bitmap, rect, rect2, new Paint());
        return createBitmap;
    }

    public static Bitmap b(Bitmap bitmap, int i2, int i3, Bitmap.Config config) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width < i2 && height < i2) {
            return bitmap;
        }
        double d2 = (double) width;
        double d3 = (double) i2;
        Double.isNaN(d2);
        Double.isNaN(d3);
        double d4 = d2 / d3;
        double d5 = (double) height;
        double d6 = (double) i3;
        Double.isNaN(d5);
        Double.isNaN(d6);
        double d7 = d5 / d6;
        if (d4 > d7) {
            d7 = d4;
        }
        Double.isNaN(d2);
        Double.isNaN(d5);
        return a(bitmap, (int) (d2 / d7), (int) (d5 / d7), config);
    }

    public static void a(String str, int i2, int i3) {
        Bitmap bitmap;
        Bitmap decodeFile = BitmapFactory.decodeFile(str);
        if (i2 <= 0 || i2 <= 0 || (decodeFile.getWidth() <= i2 && decodeFile.getHeight() <= i2)) {
            bitmap = decodeFile;
        } else {
            bitmap = b(decodeFile, i2, i3, Bitmap.Config.ARGB_8888);
            if (bitmap != decodeFile) {
                decodeFile.recycle();
            }
        }
        a(bitmap, str);
        bitmap.recycle();
    }

    public static Bitmap b(String str, int i2, int i3) {
        return a(str, i2, i3, false);
    }

    public static Bitmap a(String str, int i2, int i3, boolean z2) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            int i4 = 1;
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(str, options);
            int i5 = options.outWidth;
            int i6 = options.outHeight;
            while (i5 > i2 && i6 > i3) {
                i4++;
                i5 = options.outWidth / i4;
                i6 = options.outHeight / i4;
            }
            options.inSampleSize = i4;
            options.inJustDecodeBounds = false;
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            return BitmapFactory.decodeFile(str, options);
        } catch (OutOfMemoryError e2) {
            if (!z2) {
                MyLog.a((Throwable) e2);
                return null;
            }
            throw e2;
        }
    }

    public static Bitmap a(int i2, int i3, int i4, Context context) {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            int i5 = 1;
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(context.getResources(), i2, options);
            int i6 = options.outWidth;
            int i7 = options.outHeight;
            while (true) {
                if (i6 <= i3) {
                    if (i7 <= i4) {
                        options.inSampleSize = i5;
                        options.inJustDecodeBounds = false;
                        options.inPreferredConfig = Bitmap.Config.RGB_565;
                        return BitmapFactory.decodeResource(context.getResources(), i2, options);
                    }
                }
                i5++;
                i6 = options.outWidth / i5;
                i7 = options.outHeight / i5;
            }
        } catch (OutOfMemoryError e2) {
            MyLog.a((Throwable) e2);
            return null;
        }
    }

    public static Bitmap a(String str, int i2) {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            int i3 = 1;
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(str, options);
            int i4 = options.outWidth;
            int i5 = options.outHeight;
            while ((i4 * i4) + (i5 * i5) > i2 * i2) {
                i3++;
                i4 = options.outWidth / i3;
                i5 = options.outHeight / i3;
            }
            options.inSampleSize = i3;
            options.inJustDecodeBounds = false;
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            return BitmapFactory.decodeFile(str, options);
        } catch (OutOfMemoryError e2) {
            MyLog.a((Throwable) e2);
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r6.inSampleSize = r9;
        r6.inJustDecodeBounds = false;
        r1 = android.graphics.BitmapFactory.decodeFile(r0, r6);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Bitmap c(java.lang.String r17, int r18, int r19) throws java.io.IOException {
        /*
            r0 = r17
            r1 = r18
            r2 = r19
            r3 = 0
            r4 = 0
            r5 = 1
            if (r1 <= 0) goto L_0x0044
            if (r2 <= 0) goto L_0x0044
            android.graphics.BitmapFactory$Options r6 = new android.graphics.BitmapFactory$Options     // Catch:{ OutOfMemoryError -> 0x0083 }
            r6.<init>()     // Catch:{ OutOfMemoryError -> 0x0083 }
            r6.inJustDecodeBounds = r5     // Catch:{ OutOfMemoryError -> 0x0083 }
            android.graphics.BitmapFactory.decodeFile(r0, r6)     // Catch:{ OutOfMemoryError -> 0x0083 }
            int r7 = r6.outWidth     // Catch:{ OutOfMemoryError -> 0x0083 }
            int r8 = r6.outHeight     // Catch:{ OutOfMemoryError -> 0x0083 }
            r9 = 1
        L_0x001c:
            double r10 = (double) r7
            double r12 = (double) r1
            r14 = 4609434218613702656(0x3ff8000000000000, double:1.5)
            java.lang.Double.isNaN(r12)
            double r12 = r12 * r14
            int r16 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r16 <= 0) goto L_0x003b
            double r10 = (double) r8
            double r12 = (double) r2
            java.lang.Double.isNaN(r12)
            double r12 = r12 * r14
            int r14 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r14 <= 0) goto L_0x003b
            int r9 = r9 << 1
            int r7 = r7 >> 1
            int r8 = r8 >> 1
            goto L_0x001c
        L_0x003b:
            r6.inSampleSize = r9     // Catch:{ OutOfMemoryError -> 0x0083 }
            r6.inJustDecodeBounds = r3     // Catch:{ OutOfMemoryError -> 0x0083 }
            android.graphics.Bitmap r1 = android.graphics.BitmapFactory.decodeFile(r0, r6)     // Catch:{ OutOfMemoryError -> 0x0083 }
            goto L_0x0048
        L_0x0044:
            android.graphics.Bitmap r1 = android.graphics.BitmapFactory.decodeFile(r0, r4)     // Catch:{ OutOfMemoryError -> 0x0083 }
        L_0x0048:
            if (r1 != 0) goto L_0x004b
            return r4
        L_0x004b:
            android.media.ExifInterface r2 = new android.media.ExifInterface     // Catch:{ Exception -> 0x005c }
            r2.<init>(r0)     // Catch:{ Exception -> 0x005c }
            java.lang.String r0 = "Orientation"
            int r0 = r2.getAttributeInt(r0, r5)     // Catch:{ Exception -> 0x005c }
            float r0 = com.xiaomi.smarthome.library.common.util.ImageExifUtils.b(r0)     // Catch:{ Exception -> 0x005c }
            int r3 = (int) r0
            goto L_0x0064
        L_0x005c:
            r0 = move-exception
            java.lang.String r0 = r0.getMessage()     // Catch:{ OutOfMemoryError -> 0x0083 }
            com.xiaomi.smarthome.framework.log.MyLog.f(r0)     // Catch:{ OutOfMemoryError -> 0x0083 }
        L_0x0064:
            android.graphics.Matrix r11 = new android.graphics.Matrix     // Catch:{ OutOfMemoryError -> 0x0083 }
            r11.<init>()     // Catch:{ OutOfMemoryError -> 0x0083 }
            float r0 = (float) r3     // Catch:{ OutOfMemoryError -> 0x0083 }
            r11.postRotate(r0)     // Catch:{ OutOfMemoryError -> 0x0083 }
            r7 = 0
            r8 = 0
            int r9 = r1.getWidth()     // Catch:{ OutOfMemoryError -> 0x0083 }
            int r10 = r1.getHeight()     // Catch:{ OutOfMemoryError -> 0x0083 }
            r12 = 1
            r6 = r1
            android.graphics.Bitmap r0 = android.graphics.Bitmap.createBitmap(r6, r7, r8, r9, r10, r11, r12)     // Catch:{ OutOfMemoryError -> 0x0083 }
            if (r0 == r1) goto L_0x0082
            r1.recycle()     // Catch:{ OutOfMemoryError -> 0x0083 }
        L_0x0082:
            return r0
        L_0x0083:
            java.io.IOException r0 = new java.io.IOException
            java.lang.String r1 = "decode file out of memory"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.library.common.util.CommonUtils.c(java.lang.String, int, int):android.graphics.Bitmap");
    }

    public static boolean a(Bitmap bitmap, String str, Rect rect, Paint paint) {
        try {
            return a(bitmap, BitmapFactory.decodeFile(str), rect, paint);
        } catch (OutOfMemoryError e2) {
            MyLog.a((Throwable) e2);
            return false;
        }
    }

    public static boolean a(Bitmap bitmap, Bitmap bitmap2, Rect rect, Paint paint) {
        if (bitmap2 == null) {
            return false;
        }
        try {
            new Canvas(bitmap).drawBitmap(bitmap2, new Rect(0, 0, bitmap2.getWidth(), bitmap2.getHeight()), rect, paint);
            return true;
        } catch (OutOfMemoryError e2) {
            MyLog.a((Throwable) e2);
            return false;
        }
    }

    public static boolean a(Bitmap bitmap, Bitmap bitmap2, Paint paint) {
        return a(bitmap, bitmap2, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()), paint);
    }

    public static final class TitleAndListBitmaps {

        /* renamed from: a  reason: collision with root package name */
        private final Bitmap f18662a;
        private final Bitmap b;

        public TitleAndListBitmaps(Bitmap bitmap, Bitmap bitmap2) {
            this.f18662a = bitmap;
            this.b = bitmap2;
        }

        public Bitmap a() {
            return this.f18662a;
        }

        public Bitmap b() {
            return this.b;
        }
    }

    public static boolean a(Bitmap bitmap, Bitmap bitmap2, Rect rect) {
        return a(bitmap, bitmap2, rect, new Paint());
    }

    public static boolean a(Bitmap bitmap, Bitmap bitmap2) {
        return a(bitmap, bitmap2, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()));
    }

    public static void f(String str) {
        File file = new File(str.substring(0, str.lastIndexOf("/")));
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public static String a(int i2) {
        return "<" + i2 + ">";
    }

    public static String g(String str) {
        return "<" + str + ">";
    }

    public static boolean a(Context context, String str) {
        return context.getPackageManager().queryIntentActivities(new Intent(str), 65536).size() > 0;
    }

    public static boolean a(Context context, Intent intent) {
        return context.getPackageManager().queryIntentActivities(intent, 65536).size() > 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x003e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(java.io.File r3, java.io.File r4) throws java.io.IOException {
        /*
            java.lang.String r0 = r3.getAbsolutePath()
            java.lang.String r1 = r4.getAbsolutePath()
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x000f
            return
        L_0x000f:
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ all -> 0x0035 }
            r1.<init>(r3)     // Catch:{ all -> 0x0035 }
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ all -> 0x0033 }
            r3.<init>(r4)     // Catch:{ all -> 0x0033 }
            r4 = 1024(0x400, float:1.435E-42)
            byte[] r4 = new byte[r4]     // Catch:{ all -> 0x0030 }
        L_0x001e:
            int r0 = r1.read(r4)     // Catch:{ all -> 0x0030 }
            if (r0 < 0) goto L_0x0029
            r2 = 0
            r3.write(r4, r2, r0)     // Catch:{ all -> 0x0030 }
            goto L_0x001e
        L_0x0029:
            r1.close()
            r3.close()
            return
        L_0x0030:
            r4 = move-exception
            r0 = r3
            goto L_0x0037
        L_0x0033:
            r4 = move-exception
            goto L_0x0037
        L_0x0035:
            r4 = move-exception
            r1 = r0
        L_0x0037:
            if (r1 == 0) goto L_0x003c
            r1.close()
        L_0x003c:
            if (r0 == 0) goto L_0x0041
            r0.close()
        L_0x0041:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.library.common.util.CommonUtils.a(java.io.File, java.io.File):void");
    }

    public static void b(Context context, String str) {
        context.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(new File(str))));
    }

    public static boolean c(Context context) {
        return ((TelephonyManager) context.getSystemService("phone")).getSimState() == 5;
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x0071 A[SYNTHETIC, Splitter:B:38:0x0071] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x007a A[Catch:{ IOException -> 0x007d }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean b(java.lang.String r5, int r6) throws java.io.IOException {
        /*
            java.io.File r0 = new java.io.File
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r5)
            java.lang.String r2 = ".temp"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            r1 = 80
            r2 = 1
            r3 = 0
            if (r2 != r6) goto L_0x0026
            android.graphics.Bitmap r6 = android.graphics.BitmapFactory.decodeFile(r5)     // Catch:{ all -> 0x0023 }
            r1 = 50
            goto L_0x002e
        L_0x0023:
            r5 = move-exception
            r4 = r3
            goto L_0x006f
        L_0x0026:
            r6 = 480(0x1e0, float:6.73E-43)
            r4 = 800(0x320, float:1.121E-42)
            android.graphics.Bitmap r6 = c(r5, r6, r4)     // Catch:{ all -> 0x0023 }
        L_0x002e:
            if (r6 != 0) goto L_0x003b
            r5 = 0
            boolean r6 = r0.exists()     // Catch:{ IOException -> 0x003a }
            if (r6 == 0) goto L_0x003a
            r0.delete()     // Catch:{ IOException -> 0x003a }
        L_0x003a:
            return r5
        L_0x003b:
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch:{ all -> 0x0023 }
            r4.<init>(r0)     // Catch:{ all -> 0x0023 }
            android.graphics.Bitmap$CompressFormat r3 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch:{ all -> 0x006e }
            r6.compress(r3, r1, r4)     // Catch:{ all -> 0x006e }
            r4.close()     // Catch:{ all -> 0x0069 }
            r6.recycle()     // Catch:{ all -> 0x006e }
            java.io.File r6 = new java.io.File     // Catch:{ all -> 0x006e }
            r6.<init>(r5)     // Catch:{ all -> 0x006e }
            boolean r5 = r6.exists()     // Catch:{ all -> 0x006e }
            if (r5 == 0) goto L_0x0059
            r6.delete()     // Catch:{ all -> 0x006e }
        L_0x0059:
            r0.renameTo(r6)     // Catch:{ all -> 0x006e }
            r4.close()     // Catch:{ IOException -> 0x0068 }
            boolean r5 = r0.exists()     // Catch:{ IOException -> 0x0068 }
            if (r5 == 0) goto L_0x0068
            r0.delete()     // Catch:{ IOException -> 0x0068 }
        L_0x0068:
            return r2
        L_0x0069:
            r5 = move-exception
            r6.recycle()     // Catch:{ all -> 0x006e }
            throw r5     // Catch:{ all -> 0x006e }
        L_0x006e:
            r5 = move-exception
        L_0x006f:
            if (r4 == 0) goto L_0x0074
            r4.close()     // Catch:{ IOException -> 0x007d }
        L_0x0074:
            boolean r6 = r0.exists()     // Catch:{ IOException -> 0x007d }
            if (r6 == 0) goto L_0x007d
            r0.delete()     // Catch:{ IOException -> 0x007d }
        L_0x007d:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.library.common.util.CommonUtils.b(java.lang.String, int):boolean");
    }

    public static void a(ZipOutputStream zipOutputStream, File file, String str, FileFilter fileFilter) {
        File[] fileArr;
        String str2;
        if (str == null) {
            str = "";
        }
        try {
            if (file.isDirectory()) {
                if (fileFilter != null) {
                    fileArr = file.listFiles(fileFilter);
                } else {
                    fileArr = file.listFiles();
                }
                zipOutputStream.putNextEntry(new ZipEntry(str + File.separator));
                if (TextUtils.isEmpty(str)) {
                    str2 = "";
                } else {
                    str2 = str + File.separator;
                }
                for (File file2 : fileArr) {
                    a(zipOutputStream, file2, str2 + file2.getName(), (FileFilter) null);
                }
                return;
            }
            if (!TextUtils.isEmpty(str)) {
                zipOutputStream.putNextEntry(new ZipEntry(URLEncoder.encode(str)));
            } else {
                Date date = new Date();
                zipOutputStream.putNextEntry(new ZipEntry(String.valueOf(date.getTime()) + Constants.n));
            }
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read != -1) {
                    zipOutputStream.write(bArr, 0, read);
                } else {
                    fileInputStream.close();
                    return;
                }
            }
        } catch (IOException e2) {
            MyLog.f("zipFiction failed with exception:" + e2.toString());
        }
    }

    public static void a(TextView textView, String str, String str2, int i2) {
        a(textView, (CharSequence) str, (CharSequence[]) new String[]{str2}, i2, false);
    }

    public static void a(TextView textView, CharSequence charSequence, CharSequence charSequence2, int i2) {
        a(textView, charSequence, new CharSequence[]{charSequence2}, i2, false);
    }

    public static void a(TextView textView, CharSequence charSequence, CharSequence[] charSequenceArr, int i2, boolean z2) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(charSequence);
        for (CharSequence charSequence2 : charSequenceArr) {
            int i3 = 0;
            while (true) {
                int a2 = a(charSequence, charSequence2, i3, z2);
                if (a2 <= -1) {
                    break;
                }
                spannableStringBuilder.setSpan(new ForegroundColorSpan(i2), a2, charSequence2.length() + a2, 33);
                i3 = a2 + charSequence2.length();
            }
        }
        textView.setText(spannableStringBuilder);
    }

    private static int a(CharSequence charSequence, CharSequence charSequence2, int i2, boolean z2) {
        while (i2 < charSequence.length()) {
            int i3 = 0;
            while (i3 < charSequence2.length() && (r1 = i2 + i3) < charSequence.length() && a(charSequence.charAt(r1), charSequence2.charAt(i3), z2)) {
                if (i3 == charSequence2.length() - 1) {
                    return i2;
                }
                i3++;
            }
            i2++;
        }
        return -1;
    }

    private static boolean a(char c2, char c3, boolean z2) {
        return z2 ? c2 == c3 || Character.toLowerCase(c2) == Character.toLowerCase(c3) : c2 == c3;
    }

    public static CharSequence a(String str, String str2, final View.OnClickListener onClickListener, final boolean z2, final int i2) {
        int indexOf = str.indexOf(str2);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
        if (indexOf >= 0) {
            spannableStringBuilder.setSpan(new ClickableSpan() {
                public void onClick(View view) {
                    onClickListener.onClick(view);
                }
            }, indexOf, str2.length() + indexOf, 33);
            spannableStringBuilder.setSpan(new CharacterStyle() {
                public void updateDrawState(TextPaint textPaint) {
                    textPaint.setUnderlineText(z2);
                    textPaint.setColor(SHApplication.getAppContext().getResources().getColor(i2));
                }
            }, indexOf, str2.length() + indexOf, 33);
        }
        return spannableStringBuilder;
    }

    public static boolean a(String str, String str2) {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            return false;
        }
        if (!str2.endsWith("/")) {
            str2 = str2 + "/";
        }
        try {
            ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(new FileInputStream(str)));
            byte[] bArr = new byte[4096];
            while (true) {
                ZipEntry nextEntry = zipInputStream.getNextEntry();
                if (nextEntry != null) {
                    String name = nextEntry.getName();
                    File file = new File(str2 + name);
                    if (!name.endsWith("/")) {
                        File file2 = new File(file.getParent());
                        if (!file2.exists() || !file2.isDirectory()) {
                            file2.mkdirs();
                            a(file2);
                        }
                        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file), 4096);
                        while (true) {
                            int read = zipInputStream.read(bArr, 0, 4096);
                            if (read == -1) {
                                break;
                            }
                            bufferedOutputStream.write(bArr, 0, read);
                        }
                        bufferedOutputStream.flush();
                        bufferedOutputStream.close();
                    }
                } else {
                    zipInputStream.close();
                    return true;
                }
            }
        } catch (IOException e2) {
            MyLog.a("解压缩失败！！！", (Throwable) e2);
            return false;
        }
    }

    public static void a(File file) {
        File file2 = new File(file, ".nomedia");
        if (!file2.exists() || !file2.isFile()) {
            try {
                file2.createNewFile();
            } catch (IOException e2) {
                MyLog.a((Throwable) e2);
            }
        }
    }

    public static String a(File file, String str) {
        File file2 = new File(file, str);
        if (!file2.exists()) {
            return file2.getAbsolutePath();
        }
        int lastIndexOf = str.lastIndexOf(46);
        String str2 = "";
        if (lastIndexOf > 0) {
            String substring = str.substring(0, lastIndexOf);
            str2 = str.substring(lastIndexOf + 1);
            str = substring;
        }
        int i2 = 1;
        while (true) {
            File file3 = new File(file, String.format(D, new Object[]{str, Integer.valueOf(i2), str2}));
            if (!file3.exists()) {
                return file3.getAbsolutePath();
            }
            i2++;
        }
    }

    public static boolean f() {
        return !TextUtils.isEmpty(Build.PRODUCT) && Build.PRODUCT.contains("lephone");
    }

    public static String d(Context context) {
        String a2 = PreferenceUtils.a(context, "country", "");
        if (TextUtils.isEmpty(a2)) {
            a2 = g(context);
            if (a2 == null) {
                a2 = "";
            }
            if (!TextUtils.isEmpty(a2)) {
                PreferenceUtils.b(context, "country", a2);
            }
        }
        return a2.toUpperCase();
    }

    public static boolean e(Context context) {
        String d2 = d(context);
        return "CN".equalsIgnoreCase(d2) || ServerCompact.f1531a.equalsIgnoreCase(d2) || ServerCompact.b.equalsIgnoreCase(d2);
    }

    public static boolean f(Context context) {
        return "CN".equalsIgnoreCase(d(context));
    }

    public static String g(Context context) {
        return ((TelephonyManager) context.getSystemService("phone")).getSimCountryIso();
    }

    public static boolean h(Context context) {
        String simOperator = ((TelephonyManager) context.getSystemService("phone")).getSimOperator();
        return "46000".equals(simOperator) || "46002".equals(simOperator) || "46007".equals(simOperator);
    }

    public static boolean i(Context context) {
        return "46001".equals(((TelephonyManager) context.getSystemService("phone")).getSimOperator());
    }

    public static boolean j(Context context) {
        return "46003".equals(((TelephonyManager) context.getSystemService("phone")).getSimOperator());
    }

    public static boolean k(Context context) {
        return "CN".equalsIgnoreCase(g(context));
    }

    public static boolean l(Context context) {
        return Locale.CHINA.toString().equalsIgnoreCase(Locale.getDefault().toString()) || Locale.CHINESE.toString().equalsIgnoreCase(Locale.getDefault().toString());
    }

    public static boolean g() {
        return l(SHApplication.getAppContext()) || Locale.TAIWAN.toString().equalsIgnoreCase(Locale.getDefault().toString()) || "zh_HK".equalsIgnoreCase(Locale.getDefault().toString()) || Locale.TRADITIONAL_CHINESE.toString().equalsIgnoreCase(Locale.getDefault().toString());
    }

    public static boolean h() {
        return "Desire HD".equals(Build.MODEL);
    }

    public static boolean m(Context context) {
        try {
            return context.getPackageManager().getPackageInfo("com.miui.cloudservice", 16384) != null;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static <T> boolean a(ArrayList<T> arrayList, ArrayList<T> arrayList2) {
        if (arrayList == null || arrayList2 == null || arrayList.isEmpty() || arrayList2.isEmpty()) {
            return false;
        }
        Iterator<T> it = arrayList.iterator();
        while (it.hasNext()) {
            if (arrayList2.contains(it.next())) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x002e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int b(java.io.File r4) throws java.io.IOException {
        /*
            r0 = 0
            java.util.zip.CheckedInputStream r1 = new java.util.zip.CheckedInputStream     // Catch:{ all -> 0x002b }
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ all -> 0x002b }
            r2.<init>(r4)     // Catch:{ all -> 0x002b }
            java.util.zip.CRC32 r4 = new java.util.zip.CRC32     // Catch:{ all -> 0x002b }
            r4.<init>()     // Catch:{ all -> 0x002b }
            r1.<init>(r2, r4)     // Catch:{ all -> 0x002b }
            r4 = 1024(0x400, float:1.435E-42)
            byte[] r4 = new byte[r4]     // Catch:{ all -> 0x0028 }
        L_0x0014:
            int r0 = r1.read(r4)     // Catch:{ all -> 0x0028 }
            if (r0 < 0) goto L_0x001b
            goto L_0x0014
        L_0x001b:
            java.util.zip.Checksum r4 = r1.getChecksum()     // Catch:{ all -> 0x0028 }
            long r2 = r4.getValue()     // Catch:{ all -> 0x0028 }
            int r4 = (int) r2
            r1.close()
            return r4
        L_0x0028:
            r4 = move-exception
            r0 = r1
            goto L_0x002c
        L_0x002b:
            r4 = move-exception
        L_0x002c:
            if (r0 == 0) goto L_0x0031
            r0.close()
        L_0x0031:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.library.common.util.CommonUtils.b(java.io.File):int");
    }

    public static String a(Context context, String str, String str2) {
        String str3;
        byte[] bArr = new byte[8192];
        try {
            InputStream open = context.getResources().getAssets().open(str);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                int read = open.read(bArr);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
            open.close();
            str3 = byteArrayOutputStream.toString();
            try {
                byteArrayOutputStream.close();
            } catch (IOException unused) {
            }
        } catch (IOException unused2) {
            str3 = "";
            MyLog.f("getFromAssets 读取文件错误" + str);
            return str3;
        }
        return str3;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x003e A[SYNTHETIC, Splitter:B:20:0x003e] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x005a A[SYNTHETIC, Splitter:B:27:0x005a] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0068 A[SYNTHETIC, Splitter:B:33:0x0068] */
    /* JADX WARNING: Removed duplicated region for block: B:40:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:41:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:24:0x0044=Splitter:B:24:0x0044, B:17:0x0028=Splitter:B:17:0x0028} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Bitmap c(android.content.Context r4, java.lang.String r5) {
        /*
            r0 = 0
            android.content.res.Resources r4 = r4.getResources()     // Catch:{ IOException -> 0x0042, OutOfMemoryError -> 0x0026, all -> 0x0023 }
            android.content.res.AssetManager r4 = r4.getAssets()     // Catch:{ IOException -> 0x0042, OutOfMemoryError -> 0x0026, all -> 0x0023 }
            java.io.InputStream r4 = r4.open(r5)     // Catch:{ IOException -> 0x0042, OutOfMemoryError -> 0x0026, all -> 0x0023 }
            android.graphics.Bitmap r1 = android.graphics.BitmapFactory.decodeStream(r4)     // Catch:{ IOException -> 0x0021, OutOfMemoryError -> 0x001f }
            if (r4 == 0) goto L_0x001d
            r4.close()     // Catch:{ IOException -> 0x0017 }
            goto L_0x001d
        L_0x0017:
            r4 = move-exception
            java.lang.String r5 = "Failed to close InputStream when getting bitmap from assets."
            com.xiaomi.smarthome.framework.log.MyLog.a((java.lang.String) r5, (java.lang.Throwable) r4)
        L_0x001d:
            r0 = r1
            goto L_0x0064
        L_0x001f:
            r1 = move-exception
            goto L_0x0028
        L_0x0021:
            r1 = move-exception
            goto L_0x0044
        L_0x0023:
            r5 = move-exception
            r4 = r0
            goto L_0x0066
        L_0x0026:
            r1 = move-exception
            r4 = r0
        L_0x0028:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0065 }
            r2.<init>()     // Catch:{ all -> 0x0065 }
            java.lang.String r3 = "getBitmapFromAssets "
            r2.append(r3)     // Catch:{ all -> 0x0065 }
            r2.append(r5)     // Catch:{ all -> 0x0065 }
            java.lang.String r5 = r2.toString()     // Catch:{ all -> 0x0065 }
            com.xiaomi.smarthome.framework.log.MyLog.a((java.lang.String) r5, (java.lang.Throwable) r1)     // Catch:{ all -> 0x0065 }
            if (r4 == 0) goto L_0x0064
            r4.close()     // Catch:{ IOException -> 0x005e }
            goto L_0x0064
        L_0x0042:
            r1 = move-exception
            r4 = r0
        L_0x0044:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0065 }
            r2.<init>()     // Catch:{ all -> 0x0065 }
            java.lang.String r3 = "getBitmapFromAssets "
            r2.append(r3)     // Catch:{ all -> 0x0065 }
            r2.append(r5)     // Catch:{ all -> 0x0065 }
            java.lang.String r5 = r2.toString()     // Catch:{ all -> 0x0065 }
            com.xiaomi.smarthome.framework.log.MyLog.a((java.lang.String) r5, (java.lang.Throwable) r1)     // Catch:{ all -> 0x0065 }
            if (r4 == 0) goto L_0x0064
            r4.close()     // Catch:{ IOException -> 0x005e }
            goto L_0x0064
        L_0x005e:
            r4 = move-exception
            java.lang.String r5 = "Failed to close InputStream when getting bitmap from assets."
            com.xiaomi.smarthome.framework.log.MyLog.a((java.lang.String) r5, (java.lang.Throwable) r4)
        L_0x0064:
            return r0
        L_0x0065:
            r5 = move-exception
        L_0x0066:
            if (r4 == 0) goto L_0x0072
            r4.close()     // Catch:{ IOException -> 0x006c }
            goto L_0x0072
        L_0x006c:
            r4 = move-exception
            java.lang.String r0 = "Failed to close InputStream when getting bitmap from assets."
            com.xiaomi.smarthome.framework.log.MyLog.a((java.lang.String) r0, (java.lang.Throwable) r4)
        L_0x0072:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.library.common.util.CommonUtils.c(android.content.Context, java.lang.String):android.graphics.Bitmap");
    }

    public static boolean i() {
        return Build.MODEL.contains("mione") || Build.MODEL.contains("MI-ONE");
    }

    public static boolean j() {
        return Build.MODEL.contains("ZTE");
    }

    public static Bitmap a(Bitmap bitmap) {
        return a(bitmap, 7.0f);
    }

    public static Bitmap a(Bitmap bitmap, float f2) {
        Rect rect;
        if (bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        if (width > height) {
            rect = new Rect((width - height) / 2, 0, (width + height) / 2, height);
        } else {
            rect = new Rect(0, (height - width) / 2, width, (width + height) / 2);
        }
        Rect rect2 = new Rect(0, 0, width, height);
        RectF rectF = new RectF(rect2);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-12434878);
        canvas.drawRoundRect(rectF, f2, f2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect2, paint);
        bitmap.recycle();
        return createBitmap;
    }

    public static String[] a(Context context, Uri uri) {
        if (uri.getScheme().equalsIgnoreCase("file")) {
            String path = uri.getPath();
            return new String[]{path, h(path)};
        }
        Cursor query = context.getContentResolver().query(uri, new String[]{Downloads._DATA, "mime_type"}, (String) null, (String[]) null, (String) null);
        if (query == null) {
            return null;
        }
        try {
            query.moveToFirst();
            return new String[]{query.getString(0), query.getString(1)};
        } finally {
            if (query != null) {
                query.close();
            }
        }
    }

    public static String h(String str) {
        String str2;
        int lastIndexOf = str.lastIndexOf(46);
        if (lastIndexOf < 0) {
            str2 = "";
        } else {
            str2 = str.substring(lastIndexOf + 1);
        }
        return "image/" + str2;
    }

    public static void a(Context context, Intent intent, String str, int i2, boolean z2) {
        Intent intent2 = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        intent2.putExtra("android.intent.extra.shortcut.INTENT", intent);
        intent2.putExtra("android.intent.extra.shortcut.NAME", str);
        intent2.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(context, i2));
        intent2.putExtra("duplicate", z2);
        context.sendBroadcast(intent2);
    }

    public static boolean n(Context context) {
        return ((WifiManager) context.getSystemService("wifi")).isWifiEnabled();
    }

    public static String o(Context context) {
        WifiInfo connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
        if (connectionInfo == null) {
            return null;
        }
        String ssid = connectionInfo.getSSID();
        return (ssid == null || !ssid.startsWith("\"") || !ssid.endsWith("\"")) ? ssid : ssid.substring(1, ssid.length() - 1);
    }

    public static byte[] i(String str) throws NoSuchAlgorithmException, IOException {
        MessageDigest instance = MessageDigest.getInstance("SHA1");
        FileInputStream fileInputStream = new FileInputStream(new File(str));
        byte[] bArr = new byte[4096];
        while (true) {
            int read = fileInputStream.read(bArr);
            if (read == -1) {
                return instance.digest();
            }
            instance.update(bArr, 0, read);
        }
    }

    public static boolean j(String str) {
        return F.matcher(str).matches();
    }

    public static boolean d(Context context, String str) {
        try {
            context.getPackageManager().getPackageInfo(str, 0);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static void a(Activity activity, double d2, double d3, String str, String str2) {
        boolean d4 = d((Context) activity, WebViewHelper.b);
        StringBuilder sb = new StringBuilder();
        sb.append("http://maps.google.com/maps?q=loc:");
        sb.append(d2);
        sb.append(",");
        sb.append(d3);
        if (d4) {
            sb.append(Operators.BRACKET_START_STR);
            sb.append(str);
            sb.append("@");
            sb.append(str2);
            sb.append(Operators.BRACKET_END_STR);
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(sb.toString()));
            intent.setClassName(WebViewHelper.b, "com.google.android.maps.MapsActivity");
            activity.startActivity(intent);
            return;
        }
        activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(sb.toString())));
    }

    public static void c(File file) {
        MyLog.e("deleteDirs filePath = " + file.getAbsolutePath());
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null && listFiles.length > 0) {
                for (File file2 : listFiles) {
                    if (file2.isFile()) {
                        file2.delete();
                    } else {
                        c(file2);
                    }
                }
            }
            file.delete();
        }
    }

    public static <T> T[] a(Class<T> cls, int i2) {
        return (Object[]) Array.newInstance(cls, i2);
    }

    public static <T> void a(T[] tArr, int i2, int i3, T[] tArr2) {
        System.arraycopy(tArr, i2, tArr2, 0, (i3 + i2) - i2);
    }

    public static <T> void a(T[] tArr, Collection<T> collection) {
        Collections.addAll(collection, tArr);
    }

    public static <T> T[] a(Class<T> cls, Collection<T> collection) {
        return collection.toArray(a(cls, 0));
    }

    public static <T> T[] a(Class<T> cls, T[] tArr, int i2, int i3) {
        T[] a2 = a(cls, i3);
        a(tArr, i2, i3, a2);
        return a2;
    }

    public static int[] a(int[] iArr, int i2, int i3) {
        int[] iArr2 = new int[i3];
        System.arraycopy(iArr, i2, iArr2, 0, (i3 + i2) - i2);
        return iArr2;
    }

    public static <T> ArrayList<T> a(T[] tArr) {
        ArrayList<T> arrayList = new ArrayList<>(tArr.length);
        Collections.addAll(arrayList, tArr);
        return arrayList;
    }

    public static Bitmap a(Bitmap bitmap, int i2) {
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        RectF rectF = new RectF(rect);
        float f2 = (float) i2;
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-12434878);
        canvas.drawRoundRect(rectF, f2, f2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return createBitmap;
    }

    public static Bitmap a(Bitmap bitmap, int i2, boolean z2, boolean z3, boolean z4, boolean z5) {
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        RectF rectF = new RectF(rect);
        float f2 = (float) i2;
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-12434878);
        canvas.drawRoundRect(rectF, f2, f2, paint);
        if (z2) {
            canvas.drawRect(0.0f, 0.0f, (float) (bitmap.getWidth() / 2), (float) (bitmap.getHeight() / 2), paint);
        }
        if (z3) {
            canvas.drawRect((float) (bitmap.getWidth() / 2), 0.0f, (float) bitmap.getWidth(), (float) (bitmap.getHeight() / 2), paint);
        }
        if (z4) {
            canvas.drawRect(0.0f, (float) (bitmap.getHeight() / 2), (float) (bitmap.getWidth() / 2), (float) bitmap.getHeight(), paint);
        }
        if (z5) {
            canvas.drawRect((float) (bitmap.getWidth() / 2), (float) (bitmap.getHeight() / 2), (float) bitmap.getWidth(), (float) bitmap.getHeight(), paint);
        }
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return createBitmap;
    }

    public static String c(String str, int i2) {
        return a(str, i2, true);
    }

    public static String a(String str, int i2, boolean z2) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("?thumb=");
        sb.append(String.valueOf(i2));
        sb.append("x");
        sb.append(String.valueOf(i2));
        sb.append(z2 ? "&scale=auto" : "");
        return sb.toString();
    }

    public static int q(Context context) {
        try {
            Log.d("NativeCrash", "1111-hhh-111");
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            Log.d("NativeCrash", "1111-hhh-222");
            Log.d("NativeCrash", "1111-hhh-444");
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e2) {
            Log.d("NativeCrash", "1111-hhh-333");
            MyLog.f("cannot find package" + e2);
            return -1;
        }
    }

    public static long d(String str, int i2) {
        String[] split = str.split("\\.");
        int i3 = 1;
        long j2 = 0;
        for (int length = split.length - 1; length >= 0; length--) {
            j2 += (long) (Integer.valueOf(split[length]).intValue() * i3);
            i3 *= i2;
        }
        return j2;
    }

    public static Bitmap a(Context context, LruCache<String, Bitmap> lruCache, String str, int i2) {
        return a(context, lruCache, str, i2, Bitmap.Config.ARGB_8888);
    }

    public static Bitmap a(Context context, LruCache<String, Bitmap> lruCache, String str, int i2, Bitmap.Config config) {
        Bitmap bitmap = lruCache == null ? null : lruCache.get(str);
        if (bitmap != null) {
            return bitmap;
        }
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 1;
            options.inJustDecodeBounds = false;
            options.inPreferredConfig = config;
            Bitmap decodeResource = BitmapFactory.decodeResource(context.getResources(), i2, options);
            if (!(lruCache == null || decodeResource == null)) {
                try {
                    lruCache.put(str, decodeResource);
                } catch (OutOfMemoryError e2) {
                    e = e2;
                    bitmap = decodeResource;
                }
            }
            return decodeResource;
        } catch (OutOfMemoryError e3) {
            e = e3;
            MyLog.a((Throwable) e);
            return bitmap;
        }
    }

    public static BitmapDrawable b(Context context, LruCache<String, Bitmap> lruCache, String str, int i2) {
        return b(context, lruCache, str, i2, Bitmap.Config.ARGB_8888);
    }

    public static BitmapDrawable b(Context context, LruCache<String, Bitmap> lruCache, String str, int i2, Bitmap.Config config) {
        Bitmap a2 = a(context, lruCache, str, i2, config);
        if (a2 != null) {
            return new BitmapDrawable(a2);
        }
        return null;
    }

    public static void a(Activity activity) {
        int i2;
        int i3 = activity.getResources().getConfiguration().orientation;
        int orientation = activity.getWindowManager().getDefaultDisplay().getOrientation();
        int i4 = 8;
        if (Build.VERSION.SDK_INT <= 8) {
            i2 = 1;
            i4 = 0;
        } else {
            i2 = 9;
        }
        if (orientation == 0 || orientation == 1) {
            if (i3 == 1) {
                activity.setRequestedOrientation(1);
            } else if (i3 == 2) {
                activity.setRequestedOrientation(0);
            }
        } else if (orientation != 2 && orientation != 3) {
        } else {
            if (i3 == 1) {
                activity.setRequestedOrientation(i2);
            } else if (i3 == 2) {
                activity.setRequestedOrientation(i4);
            }
        }
    }

    public static void b(Cursor cursor) {
        if (cursor != null) {
            try {
                cursor.close();
            } catch (Throwable th) {
                MyLog.a("CommonUtils.closeQuietly ", th);
            }
        }
    }

    public static Activity b(Activity activity) {
        while (activity.getParent() != null) {
            activity = activity.getParent();
        }
        return activity;
    }

    public static boolean a(byte[] bArr) {
        return bArr == null || bArr.length == 0;
    }

    public static byte[] a(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[(bArr.length + bArr2.length)];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    public static void a(int i2, TextView textView, TextView textView2, boolean z2) {
        String str;
        double d2 = (double) i2;
        int i3 = i2 / 1000000;
        if (i3 > 0) {
            str = SHApplication.getAppContext().getString(R.string.m_s);
        } else if (i2 / 1000 > 0) {
            str = SHApplication.getAppContext().getString(R.string.k_s);
        } else {
            str = SHApplication.getAppContext().getString(R.string.k_s);
            Double.isNaN(d2);
            d2 /= 1000.0d;
        }
        if (i3 >= 1000) {
            MyLog.f("it`s impossible, speed must be some errors " + i2);
        }
        while (d2 >= 1000.0d) {
            d2 /= 1000.0d;
        }
        textView.setText(a(d2).format(d2));
        if (z2) {
            str = str.toUpperCase();
        }
        textView2.setText(str);
    }

    public static void a(int i2, TextView textView, TextView textView2) {
        a(i2, textView, textView2, false);
    }

    public static void a(int i2, LinearLayout linearLayout, TextView textView, boolean z2) {
        String str;
        double d2 = (double) i2;
        int i3 = i2 / 1000000;
        if (i3 > 0) {
            str = SHApplication.getAppContext().getString(R.string.m_s);
        } else if (i2 / 1000 > 0) {
            str = SHApplication.getAppContext().getString(R.string.k_s);
        } else {
            str = SHApplication.getAppContext().getString(R.string.k_s);
            Double.isNaN(d2);
            d2 /= 1000.0d;
        }
        if (i3 >= 1000) {
            MyLog.f("it`s impossible, speed must be some errors " + i2);
        }
        while (d2 >= 1000.0d) {
            d2 /= 1000.0d;
        }
        String format = a(d2).format(d2);
        linearLayout.removeAllViews();
        for (int i4 = 0; i4 < format.length(); i4++) {
            ImageView imageView = new ImageView(linearLayout.getContext());
            imageView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
            switch (format.charAt(i4)) {
                case '.':
                    imageView.setImageResource(R.drawable.frame_ziti_dian);
                    break;
                case '0':
                    imageView.setImageResource(R.drawable.frame_ziti_0);
                    break;
                case '1':
                    imageView.setImageResource(R.drawable.frame_ziti_1);
                    break;
                case '2':
                    imageView.setImageResource(R.drawable.frame_ziti_2);
                    break;
                case '3':
                    imageView.setImageResource(R.drawable.frame_ziti_3);
                    break;
                case '4':
                    imageView.setImageResource(R.drawable.frame_ziti_4);
                    break;
                case '5':
                    imageView.setImageResource(R.drawable.frame_ziti_5);
                    break;
                case '6':
                    imageView.setImageResource(R.drawable.frame_ziti_6);
                    break;
                case '7':
                    imageView.setImageResource(R.drawable.frame_ziti_7);
                    break;
                case '8':
                    imageView.setImageResource(R.drawable.frame_ziti_8);
                    break;
                case '9':
                    imageView.setImageResource(R.drawable.frame_ziti_9);
                    break;
            }
            linearLayout.addView(imageView);
        }
        if (z2) {
            str = str.toUpperCase();
        }
        textView.setText(str);
    }

    public static void a(int i2, LinearLayout linearLayout, TextView textView) {
        a(i2, linearLayout, textView, false);
    }

    private static DecimalFormat a(double d2) {
        DecimalFormat decimalFormat = new DecimalFormat("###");
        if (d2 >= 100.0d) {
            return new DecimalFormat("###");
        }
        if (d2 >= 10.0d) {
            return new DecimalFormat("##.0");
        }
        return d2 >= 0.0d ? new DecimalFormat("0.00") : decimalFormat;
    }

    public static void a(long j2, TextView textView, TextView textView2) {
        String str;
        float f2;
        String str2;
        DecimalFormat decimalFormat = new DecimalFormat("###");
        if (j2 < 1024) {
            f2 = (float) j2;
            if (f2 >= 1000.0f) {
                f2 /= 1024.0f;
                str = "MB";
                decimalFormat = a((double) f2);
            } else {
                str = "KB";
            }
        } else {
            float f3 = ((float) j2) / 1024.0f;
            if (f3 < 1024.0f) {
                if (f3 >= 1000.0f) {
                    f3 /= 1024.0f;
                    str = ServerCompact.i;
                } else {
                    str = "MB";
                }
                decimalFormat = a((double) f2);
            } else {
                f2 = f3 / 1024.0f;
                if (f2 < 1024.0f) {
                    if (f2 >= 1000.0f) {
                        f2 /= 1024.0f;
                        str2 = "TB";
                    } else {
                        str2 = ServerCompact.i;
                    }
                    decimalFormat = a((double) f2);
                } else {
                    f2 /= 1024.0f;
                    str = "TB";
                    decimalFormat = a((double) f2);
                }
            }
        }
        textView.setText(decimalFormat.format((double) f2));
        textView2.setText(str);
    }

    public static String a(long j2) {
        String str;
        float f2;
        if (j2 < 1024) {
            f2 = (float) j2;
            str = Field.b;
        } else {
            f2 = ((float) j2) / 1024.0f;
            if (f2 < 1024.0f) {
                str = "KB";
            } else {
                f2 /= 1024.0f;
                if (f2 < 1024.0f) {
                    str = "MB";
                } else {
                    f2 /= 1024.0f;
                    str = ServerCompact.i;
                }
            }
        }
        if (str.equals(Field.b)) {
            return String.valueOf(j2) + str;
        }
        DecimalFormat decimalFormat = new DecimalFormat("####.#");
        return decimalFormat.format((double) f2) + str;
    }

    public static String b(long j2) {
        String str;
        float f2;
        if (j2 < 1024) {
            f2 = (float) j2;
            str = "B/S";
        } else {
            f2 = ((float) j2) / 1024.0f;
            if (f2 < 1024.0f) {
                str = "KB/S";
            } else {
                f2 /= 1024.0f;
                if (f2 < 1024.0f) {
                    str = "MB/S";
                } else {
                    f2 /= 1024.0f;
                    str = "GB/S";
                }
            }
        }
        if (str.equals(Field.b)) {
            return String.valueOf(j2) + str;
        }
        DecimalFormat decimalFormat = new DecimalFormat("####.#");
        return decimalFormat.format((double) f2) + str;
    }

    public static String[] c(long j2) {
        String str;
        float f2;
        if (j2 < 1024) {
            f2 = (float) j2;
            str = "B/S";
        } else {
            f2 = ((float) j2) / 1024.0f;
            if (f2 < 1024.0f) {
                str = "KB/S";
            } else {
                f2 /= 1024.0f;
                if (f2 < 1024.0f) {
                    str = "MB/S";
                } else {
                    f2 /= 1024.0f;
                    str = "GB/S";
                }
            }
        }
        if (str.equals(Field.b)) {
            return new String[]{String.valueOf(j2), str};
        }
        return new String[]{new DecimalFormat("####.#").format((double) f2), str};
    }

    public static float a(Context context, float f2) {
        return TypedValue.applyDimension(1, f2, context.getResources().getDisplayMetrics());
    }

    public static void b(int i2) {
        e(XMStringUtils.a(SHApplication.getAppContext(), i2), 0);
    }

    public static void k(String str) {
        e(str, 0);
    }

    public static void a(int i2, int i3) {
        e(XMStringUtils.a(SHApplication.getAppContext(), i2), i3);
    }

    public static void e(final String str, final int i2) {
        if (!TextUtils.isEmpty(str)) {
            SHApplication.getGlobalHandler().post(new Runnable() {
                public void run() {
                    Toast.makeText(SHApplication.getApplication(), str, i2).show();
                }
            });
        }
    }

    public static <T> List<T> a(Class<?> cls, String str) {
        boolean z2;
        ArrayList arrayList = new ArrayList();
        Class[] declaredClasses = cls.getDeclaredClasses();
        if (declaredClasses == null) {
            return arrayList;
        }
        for (Class cls2 : declaredClasses) {
            Class[] interfaces = cls2.getInterfaces();
            if (interfaces != null) {
                int length = interfaces.length;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    } else if (interfaces[i2].getSimpleName().equals(str)) {
                        z2 = true;
                        break;
                    } else {
                        i2++;
                    }
                }
            }
            z2 = false;
            if (z2) {
                try {
                    Constructor declaredConstructor = cls2.getDeclaredConstructor(new Class[0]);
                    declaredConstructor.setAccessible(true);
                    arrayList.add(declaredConstructor.newInstance(new Object[0]));
                } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException unused) {
                }
            }
        }
        return arrayList;
    }

    public static boolean k() {
        long currentTimeMillis = System.currentTimeMillis();
        boolean z2 = currentTimeMillis - B < 500;
        B = currentTimeMillis;
        return z2;
    }

    public static double l() {
        if (C == null) {
            C = new Random();
            C.setSeed(System.currentTimeMillis());
        }
        return C.nextDouble();
    }

    private static ComponentName a(ActivityManager activityManager, ActivityManager.RunningAppProcessInfo runningAppProcessInfo) {
        if (runningAppProcessInfo == null) {
            return null;
        }
        for (ActivityManager.RunningTaskInfo next : activityManager.getRunningTasks(9999)) {
            if (next.baseActivity.getPackageName().equals(runningAppProcessInfo.processName)) {
                return next.topActivity;
            }
        }
        return null;
    }

    public static boolean r(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (Build.VERSION.SDK_INT < 21) {
            String packageName = context.getPackageName();
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
            if (runningAppProcesses == null) {
                return false;
            }
            for (ActivityManager.RunningAppProcessInfo next : runningAppProcesses) {
                if (next.processName.startsWith(packageName) && (next.importance == 100 || next.importance == 200)) {
                    Log.e("CommonUtils", "Process:" + next.processName);
                    return true;
                }
            }
            return false;
        }
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses2 = activityManager.getRunningAppProcesses();
        List<ActivityManager.RunningTaskInfo> runningTasks = activityManager.getRunningTasks(1);
        if (runningTasks == null || runningTasks.size() == 0 || activityManager.getRunningTasks(1).get(0) == null || runningAppProcesses2 == null) {
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo next2 : runningAppProcesses2) {
            if (next2.processName.equals("com.xiaomi.smarthome")) {
                try {
                    if (((Integer) ActivityManager.RunningAppProcessInfo.class.getField("processState").get(next2)).intValue() == 6) {
                        return true;
                    }
                } catch (NoSuchFieldException e2) {
                    e2.printStackTrace();
                } catch (IllegalAccessException e3) {
                    e3.printStackTrace();
                }
            }
        }
        return false;
    }

    public static boolean c(Activity activity) {
        String str = "";
        List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) activity.getSystemService("activity")).getRunningTasks(1);
        if (runningTasks != null) {
            str = runningTasks.get(0).topActivity.getClassName();
        }
        return str.equals(activity.getClass().getName());
    }

    public static void s(Context context) {
        Log.e("CommonUtils", "killProcess before");
        try {
            if (!r(context)) {
                if (ProcessUtil.e(context)) {
                    int i2 = 0;
                    if (PluginServiceManager.a().b() != null) {
                        try {
                            i2 = PluginServiceManager.a().b().getMainProcessId();
                        } catch (RemoteException unused) {
                        }
                    }
                    if (i2 > 0) {
                        Log.e("CommonUtils", "killMainProcess:" + i2);
                        Runtime runtime = Runtime.getRuntime();
                        runtime.exec("kill " + i2);
                    }
                }
                Log.e("CommonUtils", "killProcess:" + ProcessUtil.f1529a);
                Runtime runtime2 = Runtime.getRuntime();
                runtime2.exec("kill " + Process.myPid());
            }
        } catch (IOException unused2) {
        }
    }

    public static Uri c(int i2) {
        return SystemUtils.a(i2);
    }

    public static boolean a(Device device) {
        List<ModelGroupInfo> l2 = SmartHomeDeviceHelper.a().l();
        if (l2 == null || l2.size() == 0) {
            return false;
        }
        for (int i2 = 0; i2 < l2.size(); i2++) {
            ModelGroupInfo modelGroupInfo = l2.get(i2);
            if (!(modelGroupInfo == null || modelGroupInfo.c == null || modelGroupInfo.c.length == 0)) {
                for (String equals : modelGroupInfo.c) {
                    if (TextUtils.equals(equals, device.model)) {
                        return true;
                    }
                }
                continue;
            }
        }
        return false;
    }

    public static boolean m() {
        return TextUtils.equals(Constants.Name.STABLE, r());
    }

    public static boolean n() {
        return TextUtils.equals("alpha", r());
    }

    public static boolean o() {
        if (!SystemApi.c()) {
            return false;
        }
        try {
            return SystemProperties.get("ro.product.mod_device", "").contains("_global");
        } catch (Throwable th) {
            th.printStackTrace();
            return true;
        }
    }

    public static boolean p() {
        if (!SystemApi.c()) {
            return false;
        }
        try {
            String str = SystemProperties.get("ro.miui.ui.version.name", "");
            if (str.isEmpty() || str.length() < 2 || Integer.valueOf(str.substring(1)).intValue() < 10) {
                return false;
            }
            return true;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public static String q() {
        try {
            return SystemProperties.get("ro.miui.region", "CN");
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    public static String r() {
        if (!SystemApi.c()) {
            return "";
        }
        try {
            if (miui.os.Build.IS_CTS_BUILD) {
                return "cts";
            }
            if (miui.os.Build.IS_ALPHA_BUILD) {
                return "alpha";
            }
            if (miui.os.Build.IS_DEVELOPMENT_VERSION) {
                return "dev";
            }
            if (miui.os.Build.IS_STABLE_VERSION) {
                return Constants.Name.STABLE;
            }
            return "";
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    public static int b(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return 0;
        }
        String replaceAll = str.replaceAll(" ", "");
        String replaceAll2 = str2.replaceAll(" ", "");
        String[] split = replaceAll.split("\\.");
        String[] split2 = replaceAll2.split("\\.");
        String[] strArr = new String[split.length];
        String[] strArr2 = new String[split2.length];
        for (int i2 = 0; i2 < split.length; i2++) {
            strArr[i2] = split[i2].replaceAll("[a-zA-Z]+", "");
        }
        for (int i3 = 0; i3 < split2.length; i3++) {
            strArr2[i3] = split2[i3].replaceAll("[a-zA-Z]+", "");
        }
        for (int i4 = 0; i4 < Math.min(strArr.length, strArr2.length); i4++) {
            String str3 = strArr[i4];
            String str4 = strArr2[i4];
            if (TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4)) {
                return -2;
            }
            int parseInt = Integer.parseInt(str3);
            int parseInt2 = Integer.parseInt(str4);
            if (parseInt != parseInt2) {
                if (parseInt > parseInt2) {
                    return 1;
                }
                if (parseInt < parseInt2) {
                    return -1;
                }
            }
        }
        if (strArr.length < strArr2.length) {
            return -1;
        }
        if (strArr.length > strArr2.length) {
            return 1;
        }
        return 0;
    }

    public static boolean s() {
        try {
            if (SHApplication.getAppContext().getPackageManager().getPackageInfo("com.xiaomi.mihomemanager", 0) != null) {
                return true;
            }
            return false;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static boolean e(Context context, String str) {
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        }
        if (context.getApplicationInfo().targetSdkVersion < 23) {
            if (PermissionChecker.checkPermission(context, str, Binder.getCallingPid(), Binder.getCallingUid(), context.getPackageName()) == 0) {
                return true;
            }
            return false;
        } else if (context.checkSelfPermission(str) == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean d(Activity activity) {
        if (Build.VERSION.SDK_INT >= 17 && Settings.Global.getInt(activity.getContentResolver(), "force_fsg_nav_bar", 0) != 0) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 17) {
            Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
            Point point = new Point();
            Point point2 = new Point();
            defaultDisplay.getSize(point);
            defaultDisplay.getRealSize(point2);
            if (point2.y != point.y) {
                return true;
            }
            return false;
        }
        boolean hasPermanentMenuKey = ViewConfiguration.get(activity).hasPermanentMenuKey();
        boolean deviceHasKey = KeyCharacterMap.deviceHasKey(4);
        if (hasPermanentMenuKey || deviceHasKey) {
            return false;
        }
        return true;
    }

    public static int t() {
        if (SystemApi.c()) {
            try {
                return Settings.Secure.getInt(SHApplication.getAppContext().getContentResolver(), "upload_log_pref", 1);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return 0;
    }

    public static String t(Context context) {
        PackageManager packageManager = context.getPackageManager();
        boolean z2 = false;
        try {
            if (((String) packageManager.getPackageInfo("com.android.vending", 1).applicationInfo.loadLabel(packageManager)) != null) {
                return "com.android.vending";
            }
            return "";
        } catch (Exception unused) {
            try {
                if (((String) packageManager.getPackageInfo("com.google.market", 1).applicationInfo.loadLabel(packageManager)) != null) {
                    z2 = true;
                }
                return z2 ? "com.google.market" : "";
            } catch (Exception unused2) {
                return "";
            }
        }
    }

    public static String l(String str) {
        ServerBean F2 = CoreApi.a().F();
        String H = CoreApi.a().H();
        if (F2 == null || ServerCompact.c(F2)) {
            if (!TextUtils.isEmpty(H) && !H.equalsIgnoreCase("release") && H.equalsIgnoreCase("preview")) {
                str = "pv." + str;
            }
        } else if (TextUtils.isEmpty(H) || H.equalsIgnoreCase("release") || !H.equalsIgnoreCase("preview")) {
            str = F2.f1530a + "." + str;
        } else {
            str = "pv." + F2.f1530a + "." + str;
        }
        return "https://" + str;
    }

    public static boolean a(List<? extends Object> list, List<? extends Object> list2) {
        if (list == null || list.isEmpty() || list2 == null || list2.isEmpty()) {
            return false;
        }
        for (int i2 = 0; i2 < list2.size(); i2++) {
            Object obj = list2.get(i2);
            if (obj != null && list.contains(obj)) {
                return true;
            }
        }
        return false;
    }

    public static String u(Context context) {
        if (context == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        try {
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
            sb.append("availMem:" + (memoryInfo.availMem / 1024) + " kb\n");
            sb.append("threshold:" + (memoryInfo.threshold / 1024) + " kb\n");
            sb.append("totalMem:" + (memoryInfo.totalMem / 1024) + " kb\n");
            sb.append("lowMemory:" + memoryInfo.lowMemory + "\n");
            return sb.toString();
        } catch (Exception e2) {
            e2.printStackTrace();
            return e2.getMessage();
        }
    }

    @SuppressLint({"MissingPermission"})
    private static String v(Context context) {
        return DeviceIdCompat.a(context);
    }
}
