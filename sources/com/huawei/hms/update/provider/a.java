package com.huawei.hms.update.provider;

import android.content.Context;
import android.net.Uri;
import com.xiaomi.smarthome.fastvideo.IOUtils;
import java.io.File;
import java.io.IOException;

class a {

    /* renamed from: a  reason: collision with root package name */
    private Context f5935a;
    private String b;

    a() {
    }

    public void a(Context context) {
        com.huawei.hms.c.a.a(context, "context nust not be null.");
        this.f5935a = context;
    }

    public File a(String str) {
        String a2 = a();
        if (a2 == null) {
            return null;
        }
        return b(new File(a2, str));
    }

    private String a() {
        String str;
        Context context = (Context) com.huawei.hms.c.a.b(this.f5935a, "mContext is null, call setContext first.");
        synchronized (this) {
            if (this.b == null) {
                if (context.getExternalCacheDir() != null) {
                    this.b = a(context.getExternalCacheDir());
                } else {
                    this.b = a(context.getFilesDir());
                }
            }
            str = this.b;
        }
        return str;
    }

    public Uri a(File file, String str) {
        String b2;
        String a2 = a(file);
        if (a2 == null || (b2 = b(a2)) == null) {
            return null;
        }
        return new Uri.Builder().scheme("content").authority(str).encodedPath(b2).build();
    }

    private String b(String str) {
        int i;
        String a2 = a();
        if (a2 == null || !str.startsWith(a2)) {
            return null;
        }
        if (a2.endsWith("/")) {
            i = a2.length();
        } else {
            i = a2.length() + 1;
        }
        return Uri.encode("ContentUriHelper") + IOUtils.f15883a + str.substring(i);
    }

    public File a(Uri uri) {
        String c;
        String encodedPath = uri.getEncodedPath();
        if (encodedPath == null || (c = c(encodedPath)) == null) {
            return null;
        }
        return b(new File(c));
    }

    private String c(String str) {
        int indexOf;
        String a2;
        String a3 = a();
        if (a3 != null && (indexOf = str.indexOf(47, 1)) >= 0 && "ContentUriHelper".equals(Uri.decode(str.substring(1, indexOf))) && (a2 = a(new File(a3, Uri.decode(str.substring(indexOf + 1))))) != null && a2.startsWith(a3)) {
            return a2;
        }
        return null;
    }

    private static String a(File file) {
        if (file == null) {
            return null;
        }
        try {
            return file.getCanonicalPath();
        } catch (IOException unused) {
            return null;
        }
    }

    private static File b(File file) {
        if (file == null) {
            return null;
        }
        try {
            return file.getCanonicalFile();
        } catch (IOException unused) {
            return null;
        }
    }
}
