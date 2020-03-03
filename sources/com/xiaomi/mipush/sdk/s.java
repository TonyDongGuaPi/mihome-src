package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.y;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class s {

    /* renamed from: a  reason: collision with root package name */
    private static volatile s f11563a;
    private static final Object b = new Object();
    private Context c;

    private s(Context context) {
        this.c = context;
    }

    public static s a(Context context) {
        if (f11563a == null) {
            synchronized (s.class) {
                if (f11563a == null) {
                    f11563a = new s(context);
                }
            }
        }
        return f11563a;
    }

    private File a(String str) {
        File file = new File(this.c.getFilesDir() + "/crash");
        if (!file.exists()) {
            file.mkdirs();
            return null;
        }
        File[] listFiles = file.listFiles();
        for (int i = 0; i < listFiles.length; i++) {
            if (listFiles[i].isFile() && listFiles[i].getName().startsWith(str)) {
                return listFiles[i];
            }
        }
        return null;
    }

    public String a(File file) {
        if (file == null) {
            return "";
        }
        String[] split = file.getName().split(":");
        return split.length != 2 ? "" : split[0];
    }

    public ArrayList<File> a() {
        ArrayList<File> arrayList = new ArrayList<>();
        File file = new File(this.c.getFilesDir() + "/crash");
        if (!file.exists()) {
            file.mkdirs();
            return arrayList;
        }
        File[] listFiles = file.listFiles();
        for (int i = 0; i < listFiles.length; i++) {
            String[] split = listFiles[i].getName().split(":");
            if (split.length >= 2 && Integer.parseInt(split[1]) >= 1 && listFiles[i].isFile()) {
                arrayList.add(listFiles[i]);
            }
        }
        return arrayList;
    }

    public void a(String str, String str2) {
        if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str)) {
            synchronized (b) {
                File a2 = a(str2);
                if (a2 != null) {
                    String[] split = a2.getName().split(":");
                    if (split.length >= 2) {
                        a2.renameTo(new File(this.c.getFilesDir() + "/crash" + "/" + str2 + ":" + String.valueOf(Integer.parseInt(split[1]) + 1)));
                    }
                } else {
                    FileOutputStream fileOutputStream = null;
                    try {
                        FileOutputStream fileOutputStream2 = new FileOutputStream(new File(this.c.getFilesDir() + "/crash" + "/" + str2 + ":" + "1"));
                        try {
                            fileOutputStream2.write(str.getBytes());
                            fileOutputStream2.flush();
                            y.a((Closeable) fileOutputStream2);
                        } catch (Exception e) {
                            e = e;
                            fileOutputStream = fileOutputStream2;
                            try {
                                b.a((Throwable) e);
                                y.a((Closeable) fileOutputStream);
                            } catch (Throwable th) {
                                th = th;
                                y.a((Closeable) fileOutputStream);
                                throw th;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            fileOutputStream = fileOutputStream2;
                            y.a((Closeable) fileOutputStream);
                            throw th;
                        }
                    } catch (Exception e2) {
                        e = e2;
                        b.a((Throwable) e);
                        y.a((Closeable) fileOutputStream);
                    }
                }
            }
        }
    }
}
