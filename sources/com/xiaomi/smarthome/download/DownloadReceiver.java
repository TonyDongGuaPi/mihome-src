package com.xiaomi.smarthome.download;

import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import com.google.android.exoplayer2.C;
import java.io.File;

public class DownloadReceiver extends BroadcastReceiver {
    SystemFacade mSystemFacade = null;

    public void onReceive(Context context, Intent intent) {
        if (this.mSystemFacade == null) {
            this.mSystemFacade = new RealSystemFacade(context);
        }
        String action = intent.getAction();
        Log.v(Constants.f15548a, "action: " + action);
        if (action.equals(MiWFDownloadManager.H)) {
            a(context);
        } else if (action.equals(MiWFDownloadManager.I)) {
            b(context);
        } else if (action.equals(Constants.h)) {
            a(context);
        } else if (action.equals(Constants.i) || action.equals(Constants.j) || action.equals(Constants.k)) {
            a(context, intent);
        }
    }

    private void a(Context context, Intent intent) {
        Uri data = intent.getData();
        String action = intent.getAction();
        if (action.equals(Constants.i)) {
            Log.v(Constants.f15548a, "Receiver open for " + data);
        } else if (action.equals(Constants.j)) {
            Log.v(Constants.f15548a, "Receiver list for " + data);
        } else {
            Log.v(Constants.f15548a, "Receiver hide for " + data);
        }
        Cursor query = context.getContentResolver().query(data, (String[]) null, (String) null, (String[]) null, (String) null);
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    if (action.equals(Constants.i)) {
                        a(context, query);
                        a(context, data, query);
                    } else if (action.equals(Constants.j)) {
                        a(intent, query);
                    } else {
                        a(context, data, query);
                    }
                    query.close();
                }
            } finally {
                query.close();
            }
        }
    }

    private void a(Context context, Uri uri, Cursor cursor) {
        this.mSystemFacade.a(ContentUris.parseId(uri));
        int i = cursor.getInt(cursor.getColumnIndexOrThrow("status"));
        int i2 = cursor.getInt(cursor.getColumnIndexOrThrow("visibility"));
        if (Downloads.isStatusCompleted(i) && i2 == 1) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("visibility", 0);
            context.getContentResolver().update(uri, contentValues, (String) null, (String[]) null);
        }
    }

    private void a(Context context, Cursor cursor) {
        String string = cursor.getString(cursor.getColumnIndexOrThrow(Downloads._DATA));
        String string2 = cursor.getString(cursor.getColumnIndexOrThrow(Downloads.COLUMN_MIME_TYPE));
        Uri parse = Uri.parse(string);
        if (parse.getScheme() == null) {
            parse = Uri.fromFile(new File(string));
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(parse, string2);
        intent.setFlags(C.ENCODING_PCM_MU_LAW);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Log.d(Constants.f15548a, "no activity for " + string2, e);
        }
    }

    private void a(Intent intent, Cursor cursor) {
        Intent intent2;
        String string = cursor.getString(cursor.getColumnIndexOrThrow(Downloads.COLUMN_NOTIFICATION_PACKAGE));
        if (string != null) {
            String string2 = cursor.getString(cursor.getColumnIndexOrThrow(Downloads.COLUMN_NOTIFICATION_CLASS));
            if (cursor.getInt(cursor.getColumnIndex(Downloads.COLUMN_IS_PUBLIC_API)) != 0) {
                intent2 = new Intent("android.intent.action.DOWNLOAD_NOTIFICATION_CLICKED");
                intent2.setPackage(string);
            } else if (string2 != null) {
                Intent intent3 = new Intent("android.intent.action.DOWNLOAD_NOTIFICATION_CLICKED");
                intent3.setClassName(string, string2);
                if (intent.getBooleanExtra("multiple", true)) {
                    intent3.setData(Downloads.CONTENT_URI);
                } else {
                    intent3.setData(ContentUris.withAppendedId(Downloads.CONTENT_URI, cursor.getLong(cursor.getColumnIndexOrThrow("_id"))));
                }
                intent2 = intent3;
            } else {
                return;
            }
            this.mSystemFacade.a(intent2);
        }
    }

    private void a(Context context) {
        context.startService(new Intent(context, DownloadService.class));
    }

    private void b(Context context) {
        context.stopService(new Intent(context, DownloadService.class));
    }
}
