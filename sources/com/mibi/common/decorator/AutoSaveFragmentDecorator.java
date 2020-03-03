package com.mibi.common.decorator;

import android.os.Bundle;
import com.mibi.common.base.FragmentDecoratorAdapter;
import com.mibi.common.decorator.AutoSave;
import java.lang.reflect.Field;

public class AutoSaveFragmentDecorator extends FragmentDecoratorAdapter {
    private static final String c = "AUTO_SAVE_FIELD_";

    public void a(Bundle bundle) {
        super.a(bundle);
        if (bundle != null) {
            for (Field field : this.f7457a.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(AutoSave.AutoSavable.class)) {
                    field.setAccessible(true);
                    Object obj = bundle.get(c + field.getName());
                    if (obj != null) {
                        try {
                            field.set(this.f7457a, obj);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (IllegalArgumentException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0074 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void c(android.os.Bundle r8) {
        /*
            r7 = this;
            super.c(r8)
            com.mibi.common.base.DecoratableFragment r0 = r7.f7457a
            java.lang.Class r0 = r0.getClass()
            java.lang.reflect.Field[] r0 = r0.getDeclaredFields()
            int r1 = r0.length
            r2 = 0
        L_0x000f:
            if (r2 >= r1) goto L_0x0077
            r3 = r0[r2]
            java.lang.Class<com.mibi.common.decorator.AutoSave$AutoSavable> r4 = com.mibi.common.decorator.AutoSave.AutoSavable.class
            boolean r4 = r3.isAnnotationPresent(r4)
            if (r4 == 0) goto L_0x0074
            r4 = 1
            r3.setAccessible(r4)
            java.lang.String r4 = r3.getName()
            r5 = 0
            com.mibi.common.base.DecoratableFragment r6 = r7.f7457a     // Catch:{ IllegalAccessException -> 0x0030, IllegalArgumentException -> 0x002b }
            java.lang.Object r3 = r3.get(r6)     // Catch:{ IllegalAccessException -> 0x0030, IllegalArgumentException -> 0x002b }
            goto L_0x0035
        L_0x002b:
            r3 = move-exception
            r3.printStackTrace()
            goto L_0x0034
        L_0x0030:
            r3 = move-exception
            r3.printStackTrace()
        L_0x0034:
            r3 = r5
        L_0x0035:
            if (r3 == 0) goto L_0x0074
            java.lang.Class<java.io.Serializable> r5 = java.io.Serializable.class
            boolean r5 = r5.isInstance(r3)
            if (r5 == 0) goto L_0x0056
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "AUTO_SAVE_FIELD_"
            r5.append(r6)
            r5.append(r4)
            java.lang.String r4 = r5.toString()
            java.io.Serializable r3 = (java.io.Serializable) r3
            r8.putSerializable(r4, r3)
            goto L_0x0074
        L_0x0056:
            java.lang.Class<android.os.Parcelable> r5 = android.os.Parcelable.class
            boolean r5 = r5.isInstance(r3)
            if (r5 == 0) goto L_0x0074
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "AUTO_SAVE_FIELD_"
            r5.append(r6)
            r5.append(r4)
            java.lang.String r4 = r5.toString()
            android.os.Parcelable r3 = (android.os.Parcelable) r3
            r8.putParcelable(r4, r3)
        L_0x0074:
            int r2 = r2 + 1
            goto L_0x000f
        L_0x0077:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mibi.common.decorator.AutoSaveFragmentDecorator.c(android.os.Bundle):void");
    }
}
