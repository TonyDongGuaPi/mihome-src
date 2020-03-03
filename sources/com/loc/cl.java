package com.loc;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import com.coloros.mcssdk.mode.CommandMessage;

public final class cl {
    public static int a(Context context, String str, String str2) {
        try {
            return context.getSharedPreferences(str, 0).getInt(str2, -1);
        } catch (Throwable unused) {
            return -1;
        }
    }

    public static void a(Context context, String str, String str2, int i) {
        try {
            SharedPreferences.Editor edit = context.getSharedPreferences(str, 0).edit();
            edit.putString(CommandMessage.COMMAND, str2);
            edit.putInt("version", i);
            a(edit);
        } catch (Throwable unused) {
        }
    }

    public static void a(Context context, String str, String str2, String str3) {
        try {
            SharedPreferences.Editor edit = context.getSharedPreferences(str, 0).edit();
            edit.putString(str2, str3);
            a(edit);
        } catch (Throwable unused) {
        }
    }

    @SuppressLint({"NewApi"})
    private static void a(final SharedPreferences.Editor editor) {
        if (editor != null) {
            if (Build.VERSION.SDK_INT >= 9) {
                editor.apply();
                return;
            }
            try {
                new AsyncTask<Void, Void, Void>() {
                    private Void a() {
                        try {
                            if (editor == null) {
                                return null;
                            }
                            editor.commit();
                            return null;
                        } catch (Throwable unused) {
                            return null;
                        }
                    }

                    /* access modifiers changed from: protected */
                    public final /* synthetic */ Object doInBackground(Object[] objArr) {
                        return a();
                    }
                }.execute(new Void[]{null, null, null});
            } catch (Throwable unused) {
            }
        }
    }

    public static String b(Context context, String str, String str2) {
        try {
            return context.getSharedPreferences(str, 0).getString(str2, (String) null);
        } catch (Throwable unused) {
            return null;
        }
    }
}
