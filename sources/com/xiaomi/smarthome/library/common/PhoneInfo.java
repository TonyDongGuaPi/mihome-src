package com.xiaomi.smarthome.library.common;

import android.os.AsyncTask;
import com.xiaomi.smarthome.library.common.util.AsyncTaskUtils;
import java.io.BufferedReader;
import java.io.FileReader;

public class PhoneInfo {

    /* renamed from: a  reason: collision with root package name */
    public static volatile boolean f18560a = false;

    public static void a() {
        AsyncTaskUtils.a(new AsyncTask<Void, Void, Void>() {
            /* access modifiers changed from: protected */
            /* renamed from: a */
            public Void doInBackground(Void... voidArr) {
                PhoneInfo.b();
                return null;
            }
        }, new Void[0]);
    }

    static void b() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/cpuinfo"));
            if (bufferedReader.readLine().contains("aarch64")) {
                f18560a = true;
            }
            bufferedReader.close();
        } catch (Exception unused) {
        }
    }
}
