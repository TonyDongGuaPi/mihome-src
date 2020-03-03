package com.xiaomi.smarthome.miio.camera.face.util;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.xiaomi.smarthome.framework.log.LogUtil;

public class GlobalProvider extends ContentProvider {
    public static final Uri AUTHORITY_URI = Uri.parse("content://com.xiaomi.smarthome.mijiacamera.GlobalProvider");
    public static final Uri CONTENT_URI = AUTHORITY_URI;
    public static final String PARAM_KEY = "key";
    public static final String PARAM_VALUE = "value";
    public static final String TAG = "GlobalProvider";
    private SharedPreferences mStore;

    @Nullable
    public String getType(@NonNull Uri uri) {
        return "";
    }

    public static Cursor query(Context context, String... strArr) {
        return context.getContentResolver().query(CONTENT_URI, strArr, (String) null, (String[]) null, (String) null);
    }

    public static int delete(Context context, String str) {
        return context.getContentResolver().delete(CONTENT_URI, str, (String[]) null);
    }

    public static String getString(Context context, String str) {
        return getString(context, str, (String) null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0018, code lost:
        if (android.text.TextUtils.isEmpty(r3) != false) goto L_0x001a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getString(android.content.Context r2, java.lang.String r3, java.lang.String r4) {
        /*
            r0 = 1
            java.lang.String[] r0 = new java.lang.String[r0]
            r1 = 0
            r0[r1] = r3
            android.database.Cursor r2 = query(r2, r0)
            boolean r3 = r2.moveToNext()
            if (r3 == 0) goto L_0x001a
            java.lang.String r3 = r2.getString(r1)
            boolean r0 = android.text.TextUtils.isEmpty(r3)
            if (r0 == 0) goto L_0x001b
        L_0x001a:
            r3 = r4
        L_0x001b:
            r2.close()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.miio.camera.face.util.GlobalProvider.getString(android.content.Context, java.lang.String, java.lang.String):java.lang.String");
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x002b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int getInt(android.content.Context r3, java.lang.String r4, int r5) {
        /*
            java.lang.String r0 = TAG     // Catch:{ Exception -> 0x0045 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0045 }
            r1.<init>()     // Catch:{ Exception -> 0x0045 }
            java.lang.String r2 = "getInt defValue="
            r1.append(r2)     // Catch:{ Exception -> 0x0045 }
            r1.append(r5)     // Catch:{ Exception -> 0x0045 }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x0045 }
            com.xiaomi.smarthome.framework.log.LogUtil.a((java.lang.String) r0, (java.lang.String) r1)     // Catch:{ Exception -> 0x0045 }
            r0 = 1
            java.lang.String[] r0 = new java.lang.String[r0]     // Catch:{ Exception -> 0x0045 }
            r1 = 0
            r0[r1] = r4     // Catch:{ Exception -> 0x0045 }
            android.database.Cursor r3 = query(r3, r0)     // Catch:{ Exception -> 0x0045 }
            boolean r4 = r3.moveToNext()     // Catch:{ Exception -> 0x0045 }
            if (r4 == 0) goto L_0x002b
            int r4 = r3.getInt(r1)     // Catch:{ Exception -> 0x002b }
            r5 = r4
        L_0x002b:
            r3.close()     // Catch:{ Exception -> 0x0045 }
            java.lang.String r3 = TAG     // Catch:{ Exception -> 0x0045 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0045 }
            r4.<init>()     // Catch:{ Exception -> 0x0045 }
            java.lang.String r0 = "getInt ret="
            r4.append(r0)     // Catch:{ Exception -> 0x0045 }
            r4.append(r5)     // Catch:{ Exception -> 0x0045 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x0045 }
            com.xiaomi.smarthome.framework.log.LogUtil.a((java.lang.String) r3, (java.lang.String) r4)     // Catch:{ Exception -> 0x0045 }
            goto L_0x004f
        L_0x0045:
            r3 = move-exception
            java.lang.String r4 = TAG
            java.lang.String r3 = r3.toString()
            com.xiaomi.smarthome.framework.log.LogUtil.b(r4, r3)
        L_0x004f:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.miio.camera.face.util.GlobalProvider.getInt(android.content.Context, java.lang.String, int):int");
    }

    public static Uri save(Context context, ContentValues contentValues) {
        return context.getContentResolver().insert(CONTENT_URI, contentValues);
    }

    public static Uri save(Context context, String str, String str2) {
        try {
            ContentValues contentValues = new ContentValues(1);
            contentValues.put(str, str2);
            return save(context, contentValues);
        } catch (Exception e) {
            LogUtil.b(TAG, e.toString());
            return null;
        }
    }

    public static Uri save(Context context, String str, int i) {
        try {
            ContentValues contentValues = new ContentValues(1);
            contentValues.put(str, Integer.valueOf(i));
            return save(context, contentValues);
        } catch (Exception e) {
            LogUtil.b(TAG, e.toString());
            return null;
        }
    }

    public static int remove(Context context, String str) {
        return delete(context, str);
    }

    public boolean onCreate() {
        LogUtil.a(TAG, "onCreate mijia.camera.global.sp");
        Context context = getContext();
        context.getClass();
        this.mStore = context.getSharedPreferences("mijia.camera.global.sp", 0);
        return true;
    }

    @Nullable
    public Cursor query(@NonNull Uri uri, @Nullable String[] strArr, @Nullable String str, @Nullable String[] strArr2, @Nullable String str2) {
        int length = strArr == null ? 0 : strArr.length;
        String str3 = null;
        if (length > 0) {
            String[] strArr3 = new String[length];
            for (int i = 0; i < length; i++) {
                strArr3[i] = getValue(strArr[i], (String) null);
                String str4 = TAG;
                LogUtil.a(str4, "query values=" + strArr3[i]);
            }
            return createCursor(strArr, strArr3);
        }
        String queryParameter = uri.getQueryParameter("key");
        String str5 = TAG;
        LogUtil.a(str5, "query key=" + queryParameter);
        if (!TextUtils.isEmpty(queryParameter)) {
            str3 = getValue(queryParameter, (String) null);
        }
        String str6 = TAG;
        LogUtil.a(str6, "query value=" + str3);
        return createSingleCursor(queryParameter, str3);
    }

    /* access modifiers changed from: protected */
    public Cursor createSingleCursor(String str, String str2) {
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{str}, 1);
        if (!TextUtils.isEmpty(str2)) {
            matrixCursor.addRow(new Object[]{str2});
        }
        return matrixCursor;
    }

    /* access modifiers changed from: protected */
    public Cursor createCursor(String[] strArr, String[] strArr2) {
        MatrixCursor matrixCursor = new MatrixCursor(strArr, 1);
        for (String str : strArr2) {
            if (!TextUtils.isEmpty(str)) {
                matrixCursor.addRow(new Object[]{str});
            }
        }
        return matrixCursor;
    }

    @Nullable
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        if (contentValues == null || contentValues.size() <= 0) {
            String queryParameter = uri.getQueryParameter("key");
            String queryParameter2 = uri.getQueryParameter("value");
            if (TextUtils.isEmpty(queryParameter)) {
                return null;
            }
            save(queryParameter, queryParameter2);
            return null;
        }
        save(contentValues);
        return null;
    }

    public int delete(@NonNull Uri uri, @Nullable String str, @Nullable String[] strArr) {
        String str2 = TAG;
        LogUtil.a(str2, "delete selection=" + str);
        if (str == null) {
            str = uri.getQueryParameter("key");
        }
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        remove(str);
        return 1;
    }

    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String str, @Nullable String[] strArr) {
        if (contentValues == null || contentValues.size() <= 0) {
            String queryParameter = uri.getQueryParameter("key");
            String queryParameter2 = uri.getQueryParameter("value");
            if (TextUtils.isEmpty(queryParameter)) {
                return 0;
            }
            save(queryParameter, queryParameter2);
            return 1;
        }
        save(contentValues);
        return contentValues.size();
    }

    /* access modifiers changed from: protected */
    public String getValue(String str, String str2) {
        return this.mStore.getString(str, str2);
    }

    /* access modifiers changed from: protected */
    public void save(ContentValues contentValues) {
        SharedPreferences.Editor edit = this.mStore.edit();
        for (String next : contentValues.keySet()) {
            String asString = contentValues.getAsString(next);
            if (!TextUtils.isEmpty(next)) {
                if (asString != null) {
                    edit.putString(next, asString);
                } else {
                    edit.remove(next);
                }
            }
        }
        edit.apply();
    }

    /* access modifiers changed from: protected */
    public void save(String str, String str2) {
        SharedPreferences.Editor edit = this.mStore.edit();
        if (str2 != null) {
            edit.putString(str, str2);
        } else {
            edit.remove(str);
        }
        edit.apply();
    }

    /* access modifiers changed from: protected */
    public void remove(String str) {
        SharedPreferences.Editor edit = this.mStore.edit();
        edit.remove(str);
        edit.apply();
    }
}
