package com.yanzhenjie.permission.checker;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.provider.VoicemailContract;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;

class AddVoicemailTest implements PermissionTest {

    /* renamed from: a  reason: collision with root package name */
    private ContentResolver f2406a;

    AddVoicemailTest(Context context) {
        this.f2406a = context.getContentResolver();
    }

    @RequiresApi(api = 14)
    public boolean a() throws Throwable {
        try {
            Uri uri = VoicemailContract.Voicemails.CONTENT_URI;
            ContentValues contentValues = new ContentValues();
            contentValues.put("date", Long.valueOf(System.currentTimeMillis()));
            contentValues.put("number", "1");
            contentValues.put("duration", 1);
            contentValues.put("source_package", "permission");
            contentValues.put("source_data", "permission");
            contentValues.put("is_read", 0);
            long parseId = ContentUris.parseId(this.f2406a.insert(uri, contentValues));
            if (this.f2406a.delete(uri, "_id=?", new String[]{Long.toString(parseId)}) > 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            String message = e.getMessage();
            if (!TextUtils.isEmpty(message)) {
                return true ^ message.toLowerCase().contains("add_voicemail");
            }
            return false;
        }
    }
}
