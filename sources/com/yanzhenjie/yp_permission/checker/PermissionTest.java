package com.yanzhenjie.yp_permission.checker;

import android.database.Cursor;

interface PermissionTest {
    boolean a() throws Throwable;

    public static class CursorTest {
        public static void a(Cursor cursor) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                int type = cursor.getType(0);
                if (type != 0 && type != 4) {
                    cursor.getString(0);
                }
            }
        }
    }
}
