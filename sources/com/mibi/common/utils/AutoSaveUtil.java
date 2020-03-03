package com.mibi.common.utils;

import android.os.Bundle;
import android.util.Log;
import com.mibi.common.decorator.AutoSave;
import java.lang.reflect.Field;

public class AutoSaveUtil {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7596a = "AutoSaveUtil";
    private static final String b = "AUTO_SAVE_FIELD_";

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x007d A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(java.lang.Object r8, android.os.Bundle r9) {
        /*
            boolean r0 = r8 instanceof com.mibi.common.decorator.AutoSave
            if (r0 == 0) goto L_0x0081
            if (r9 != 0) goto L_0x0008
            goto L_0x0081
        L_0x0008:
            java.lang.Class r0 = r8.getClass()
            java.lang.reflect.Field[] r0 = r0.getDeclaredFields()
            int r1 = r0.length
            r2 = 0
        L_0x0012:
            if (r2 >= r1) goto L_0x0080
            r3 = r0[r2]
            java.lang.Class<com.mibi.common.decorator.AutoSave$AutoSavable> r4 = com.mibi.common.decorator.AutoSave.AutoSavable.class
            boolean r4 = r3.isAnnotationPresent(r4)
            if (r4 == 0) goto L_0x007d
            r4 = 1
            r3.setAccessible(r4)
            java.lang.String r4 = r3.getName()
            r5 = 0
            java.lang.Object r3 = r3.get(r8)     // Catch:{ IllegalAccessException -> 0x0035, IllegalArgumentException -> 0x002c }
            goto L_0x003e
        L_0x002c:
            r3 = move-exception
            java.lang.String r6 = "AutoSaveUtil"
            java.lang.String r7 = "get field value failed"
            android.util.Log.d(r6, r7, r3)
            goto L_0x003d
        L_0x0035:
            r3 = move-exception
            java.lang.String r6 = "AutoSaveUtil"
            java.lang.String r7 = "couldn't access field value"
            android.util.Log.d(r6, r7, r3)
        L_0x003d:
            r3 = r5
        L_0x003e:
            if (r3 == 0) goto L_0x007d
            java.lang.Class<java.io.Serializable> r5 = java.io.Serializable.class
            boolean r5 = r5.isInstance(r3)
            if (r5 == 0) goto L_0x005f
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "AUTO_SAVE_FIELD_"
            r5.append(r6)
            r5.append(r4)
            java.lang.String r4 = r5.toString()
            java.io.Serializable r3 = (java.io.Serializable) r3
            r9.putSerializable(r4, r3)
            goto L_0x007d
        L_0x005f:
            java.lang.Class<android.os.Parcelable> r5 = android.os.Parcelable.class
            boolean r5 = r5.isInstance(r3)
            if (r5 == 0) goto L_0x007d
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "AUTO_SAVE_FIELD_"
            r5.append(r6)
            r5.append(r4)
            java.lang.String r4 = r5.toString()
            android.os.Parcelable r3 = (android.os.Parcelable) r3
            r9.putParcelable(r4, r3)
        L_0x007d:
            int r2 = r2 + 1
            goto L_0x0012
        L_0x0080:
            return
        L_0x0081:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mibi.common.utils.AutoSaveUtil.a(java.lang.Object, android.os.Bundle):void");
    }

    public static void b(Object obj, Bundle bundle) {
        if ((obj instanceof AutoSave) && bundle != null) {
            for (Field field : obj.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(AutoSave.AutoSavable.class)) {
                    field.setAccessible(true);
                    try {
                        field.set(obj, bundle.get(b + field.getName()));
                    } catch (IllegalAccessException e) {
                        Log.d(f7596a, "couldn't access field value", e);
                    } catch (IllegalArgumentException e2) {
                        Log.d(f7596a, "get field value failed", e2);
                    }
                }
            }
        }
    }
}
