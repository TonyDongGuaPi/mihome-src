package com.xiaomi.smarthome.library.common.util;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import com.taobao.weex.annotation.JSMethod;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.framework.permission.PermissionHelper;
import com.yanzhenjie.permission.Action;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ImageSaveHelper {

    /* renamed from: a  reason: collision with root package name */
    public static final String f18682a = ("SmartHome" + File.separator + "images" + File.separator + "screenshot" + File.separator);
    public static final String b;
    static SimpleDateFormat c = new SimpleDateFormat("yyyy_MM_dd");

    public interface ImageSaveCallback {
        void a();

        void a(int i);
    }

    static {
        StringBuilder sb = new StringBuilder();
        sb.append(Environment.getExternalStorageDirectory().getAbsolutePath());
        sb.append(File.separator);
        sb.append(f18682a);
        b = sb.toString();
    }

    public static void a(Activity activity, String str, boolean z) {
        if (activity != null && !activity.isFinishing()) {
            if ((Build.VERSION.SDK_INT < 17 || !activity.isDestroyed()) && !TextUtils.isEmpty(str)) {
                a(activity, str, b + a(), z, true);
            }
        }
    }

    public static void a(Activity activity, String str, String str2, boolean z, boolean z2) {
        final Activity activity2 = activity;
        final String str3 = str;
        final String str4 = str2;
        final boolean z3 = z;
        final boolean z4 = z2;
        if (PermissionHelper.g(activity, true, new Action() {
            public void onAction(List<String> list) {
                ImageSaveHelper.c(activity2, str3, str4, z3, z4);
            }
        })) {
            c(activity, str, str2, z, z2);
        }
    }

    /* access modifiers changed from: private */
    public static void c(Activity activity, final String str, final String str2, final boolean z, final boolean z2) {
        AsyncTaskUtils.a(new AsyncTask<Object, Object, Object>() {
            /* access modifiers changed from: protected */
            public Object doInBackground(Object... objArr) {
                File file;
                try {
                    File file2 = new File(str);
                    if (!file2.exists()) {
                        ToastUtil.a((int) R.string.image_not_exist);
                        return null;
                    } else if (file2.length() > CommonUtils.e()) {
                        ToastUtil.a((int) R.string.space_not_enable);
                        return null;
                    } else {
                        File file3 = new File(str2);
                        if (!file3.exists()) {
                            File parentFile = file3.getParentFile();
                            if (!parentFile.exists()) {
                                parentFile.mkdirs();
                            }
                        } else if (!z2) {
                            ToastUtil.a((int) R.string.image_already_exist);
                            return null;
                        } else {
                            int i = 0;
                            while (true) {
                                i++;
                                file = new File(file3.getAbsolutePath() + JSMethod.NOT_SET + i);
                                if (!file.exists()) {
                                    break;
                                }
                                file3 = file;
                            }
                            file3 = file;
                        }
                        try {
                            CommonUtils.a(file2, file3);
                            if (z) {
                                ImageSaveHelper.a(file3);
                            }
                            ToastUtil.a((int) R.string.image_saved);
                        } catch (IOException e) {
                            e.printStackTrace();
                            ToastUtil.a((int) R.string.save_fail);
                        }
                        return null;
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    ToastUtil.a((int) R.string.save_fail);
                }
            }
        }, new Object[0]);
    }

    public static void a(File file) throws FileNotFoundException {
        MediaStore.Images.Media.insertImage(SHApplication.getAppContext().getContentResolver(), file.getAbsolutePath(), "", (String) null);
        SHApplication.getAppContext().sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(file)));
    }

    public static String a() {
        Date date = new Date();
        return c.format(date) + JSMethod.NOT_SET + ((int) Math.floor(Math.random() * 1.0E7d));
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x006c A[SYNTHETIC, Splitter:B:33:0x006c] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0076 A[SYNTHETIC, Splitter:B:39:0x0076] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0081 A[SYNTHETIC, Splitter:B:44:0x0081] */
    /* JADX WARNING: Removed duplicated region for block: B:50:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:51:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:36:0x0071=Splitter:B:36:0x0071, B:30:0x0067=Splitter:B:30:0x0067} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(android.graphics.Bitmap r2, java.lang.String r3) {
        /*
            if (r2 != 0) goto L_0x0003
            return
        L_0x0003:
            boolean r0 = android.text.TextUtils.isEmpty(r3)
            if (r0 == 0) goto L_0x0023
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r0 = b
            r3.append(r0)
            java.lang.String r0 = a()
            r3.append(r0)
            java.lang.String r0 = ".jpg"
            r3.append(r0)
            java.lang.String r3 = r3.toString()
        L_0x0023:
            java.io.File r0 = new java.io.File
            r0.<init>(r3)
            boolean r3 = r0.exists()
            if (r3 == 0) goto L_0x003a
            r0.delete()
            r0.createNewFile()     // Catch:{ IOException -> 0x0035 }
            goto L_0x0047
        L_0x0035:
            r3 = move-exception
            r3.printStackTrace()
            goto L_0x0047
        L_0x003a:
            java.io.File r3 = r0.getParentFile()
            boolean r1 = r3.exists()
            if (r1 != 0) goto L_0x0047
            r3.mkdirs()
        L_0x0047:
            r3 = 0
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException -> 0x0070, IOException -> 0x0066 }
            r1.<init>(r0)     // Catch:{ FileNotFoundException -> 0x0070, IOException -> 0x0066 }
            android.graphics.Bitmap$CompressFormat r3 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch:{ FileNotFoundException -> 0x0061, IOException -> 0x005e, all -> 0x005b }
            r0 = 90
            r2.compress(r3, r0, r1)     // Catch:{ FileNotFoundException -> 0x0061, IOException -> 0x005e, all -> 0x005b }
            r1.flush()     // Catch:{ FileNotFoundException -> 0x0061, IOException -> 0x005e, all -> 0x005b }
            r1.close()     // Catch:{ IOException -> 0x007a }
            goto L_0x007e
        L_0x005b:
            r2 = move-exception
            r3 = r1
            goto L_0x007f
        L_0x005e:
            r2 = move-exception
            r3 = r1
            goto L_0x0067
        L_0x0061:
            r2 = move-exception
            r3 = r1
            goto L_0x0071
        L_0x0064:
            r2 = move-exception
            goto L_0x007f
        L_0x0066:
            r2 = move-exception
        L_0x0067:
            r2.printStackTrace()     // Catch:{ all -> 0x0064 }
            if (r3 == 0) goto L_0x007e
            r3.close()     // Catch:{ IOException -> 0x007a }
            goto L_0x007e
        L_0x0070:
            r2 = move-exception
        L_0x0071:
            r2.printStackTrace()     // Catch:{ all -> 0x0064 }
            if (r3 == 0) goto L_0x007e
            r3.close()     // Catch:{ IOException -> 0x007a }
            goto L_0x007e
        L_0x007a:
            r2 = move-exception
            r2.printStackTrace()
        L_0x007e:
            return
        L_0x007f:
            if (r3 == 0) goto L_0x0089
            r3.close()     // Catch:{ IOException -> 0x0085 }
            goto L_0x0089
        L_0x0085:
            r3 = move-exception
            r3.printStackTrace()
        L_0x0089:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.library.common.util.ImageSaveHelper.a(android.graphics.Bitmap, java.lang.String):void");
    }
}
