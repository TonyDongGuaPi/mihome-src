package com.mi.global.shop.util;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import com.xiaomi.smarthome.download.Downloads;

public class MediaUriUtils {
    public static Uri a(Context context, String str) {
        Uri uri;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (a(str)) {
            return Uri.parse(str);
        }
        Uri uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        Cursor query = context.getContentResolver().query(uri2, new String[]{"_id"}, "_data=? ", new String[]{str}, (String) null);
        if (query == null || !query.moveToFirst()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(Downloads._DATA, str);
            uri = context.getContentResolver().insert(uri2, contentValues);
        } else {
            uri = ContentUris.withAppendedId(uri2, query.getLong(query.getColumnIndex("_id")));
        }
        if (query != null) {
            query.close();
        }
        return uri;
    }

    public static boolean a(String str) {
        return str.startsWith("content://") || str.startsWith("file://");
    }
}
