package com.xiaomi.youpin.common.cache;

import com.xiaomi.youpin.common.util.CleanUtils;
import com.xiaomi.youpin.common.util.FileIOUtils;
import com.xiaomi.youpin.log.LogUtils;
import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class FileCache implements ICache<String> {

    /* renamed from: a  reason: collision with root package name */
    public static final String f23229a = "FileCache";
    File b;
    String c;
    int d = 0;

    public void d() {
    }

    public FileCache(String str, int i) {
        this.c = str;
        this.d = i;
        this.b = new File(this.c);
        this.b.mkdirs();
    }

    public String e() {
        return this.c;
    }

    public File a(String str) {
        File f = f(str);
        if (!f.exists() || !f.isFile()) {
            return null;
        }
        return f;
    }

    public Set<String> a() {
        HashSet hashSet = new HashSet();
        File[] listFiles = this.b.listFiles();
        if (listFiles != null) {
            for (int i = 0; i < listFiles.length; i++) {
                if (listFiles[i].isFile()) {
                    hashSet.add(listFiles[i].getName());
                }
            }
        }
        return hashSet;
    }

    /* renamed from: e */
    public String d(String str) {
        File a2 = a(str);
        if (a2 == null) {
            return null;
        }
        a(a2);
        return FileIOUtils.b(a2);
    }

    public boolean b(String str) {
        return a(str) != null;
    }

    public boolean a(String str, String str2) {
        return FileIOUtils.a(f(str), str2);
    }

    public void b() {
        String str = f23229a;
        LogUtils.d(str, "clear:" + this.c);
        CleanUtils.c(this.c);
    }

    public void c() {
        File[] listFiles;
        if (this.d > 0 && (listFiles = this.b.listFiles()) != null && listFiles.length > this.d) {
            new Thread() {
                public void run() {
                    File[] listFiles;
                    String str = FileCache.f23229a;
                    LogUtils.d(str, "trim:" + FileCache.this.c);
                    if (FileCache.this.d > 0 && (listFiles = FileCache.this.b.listFiles()) != null && listFiles.length > FileCache.this.d) {
                        Arrays.sort(listFiles, new FileComparator());
                        int length = listFiles.length - FileCache.this.d;
                        for (int i = 0; i < length; i++) {
                            listFiles[i].delete();
                            String str2 = FileCache.f23229a;
                            LogUtils.d(str2, "trim delete:" + listFiles[i].getAbsolutePath());
                        }
                    }
                }
            }.start();
        }
    }

    public boolean c(String str) {
        File f = f(str);
        String str2 = f23229a;
        LogUtils.d(str2, "remove:" + str);
        return f.delete();
    }

    public File f(String str) {
        return new File(this.c, str);
    }

    public void a(File file) {
        if (file != null && file.exists()) {
            file.setLastModified(System.currentTimeMillis());
        }
    }

    static class FileComparator implements Comparator<File> {
        FileComparator() {
        }

        /* renamed from: a */
        public int compare(File file, File file2) {
            return (int) (file.lastModified() - file2.lastModified());
        }
    }

    public void a(String str, byte[] bArr) {
        FileIOUtils.a(f(str), bArr);
    }

    public byte[] g(String str) {
        File a2 = a(str);
        if (a2 == null) {
            return null;
        }
        a(a2);
        return FileIOUtils.c(a2);
    }

    public void a(String str, InputStream inputStream) {
        FileIOUtils.a(f(str), inputStream);
    }
}
