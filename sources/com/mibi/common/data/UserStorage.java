package com.mibi.common.data;

import android.content.Context;
import android.content.SharedPreferences;
import com.taobao.weex.annotation.JSMethod;
import java.io.File;

public class UserStorage {

    /* renamed from: a  reason: collision with root package name */
    private Context f7548a;
    private String b;
    private StorageDir c;

    private UserStorage(Context context, String str) {
        this.f7548a = context.getApplicationContext();
        this.b = str;
        File filesDir = context.getFilesDir();
        this.c = new StorageDir(filesDir, "users" + File.separator + str);
    }

    public static UserStorage a(Context context, String str) {
        return new UserStorage(context, str);
    }

    public StorageDir a() {
        return this.c;
    }

    public StorageDir a(String str) {
        return new StorageDir((File) this.c, str);
    }

    public SharedPreferences b() {
        return this.f7548a.getSharedPreferences(this.b, 0);
    }

    public SharedPreferences b(String str) {
        Context context = this.f7548a;
        return context.getSharedPreferences(this.b + JSMethod.NOT_SET + str, 0);
    }
}
