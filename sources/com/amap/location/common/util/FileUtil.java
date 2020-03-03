package com.amap.location.common.util;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    /* renamed from: a  reason: collision with root package name */
    private static final String f4593a = "amaplocation";
    private static volatile String b;

    public static String a(Context context) {
        if (b != null) {
            return b;
        }
        if (a()) {
            try {
                File file = new File(context.getExternalFilesDir((String) null), f4593a);
                if (file.exists() || file.mkdirs()) {
                    b = file.getAbsolutePath();
                }
            } catch (Exception unused) {
                return null;
            }
        }
        return b;
    }

    public static boolean a() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    public static boolean a(File file) {
        if (!file.exists() && !file.mkdirs()) {
            return false;
        }
        b = file.getAbsolutePath();
        return true;
    }

    @Deprecated
    public static boolean a(String str, File file) {
        return a(str, file, true);
    }

    public static boolean a(String str, File file, boolean z) {
        if (file == null) {
            return false;
        }
        FileWriter fileWriter = null;
        try {
            FileWriter fileWriter2 = new FileWriter(file.getAbsolutePath(), z);
            try {
                fileWriter2.write(str);
                e.a((Closeable) fileWriter2);
                return true;
            } catch (Exception e) {
                e = e;
                fileWriter = fileWriter2;
                try {
                    e.printStackTrace();
                    e.a((Closeable) fileWriter);
                    return false;
                } catch (Throwable th) {
                    th = th;
                    e.a((Closeable) fileWriter);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                fileWriter = fileWriter2;
                e.a((Closeable) fileWriter);
                throw th;
            }
        } catch (Exception e2) {
            e = e2;
            e.printStackTrace();
            e.a((Closeable) fileWriter);
            return false;
        }
    }

    public static boolean a(byte[] bArr, File file, boolean z) {
        if (file == null) {
            return false;
        }
        FileOutputStream fileOutputStream = null;
        try {
            FileOutputStream fileOutputStream2 = new FileOutputStream(file.getAbsolutePath(), z);
            try {
                fileOutputStream2.write(bArr);
                e.a((Closeable) fileOutputStream2);
                return true;
            } catch (Exception e) {
                e = e;
                fileOutputStream = fileOutputStream2;
                try {
                    e.printStackTrace();
                    e.a((Closeable) fileOutputStream);
                    return false;
                } catch (Throwable th) {
                    th = th;
                    e.a((Closeable) fileOutputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream = fileOutputStream2;
                e.a((Closeable) fileOutputStream);
                throw th;
            }
        } catch (Exception e2) {
            e = e2;
            e.printStackTrace();
            e.a((Closeable) fileOutputStream);
            return false;
        }
    }

    public static long b(Context context) {
        return f(context.getFilesDir());
    }

    public static boolean b(File file) {
        if (!file.exists()) {
            return true;
        }
        if (file.isFile()) {
            return file.delete();
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(file);
        while (arrayList2.size() > 0) {
            File file2 = (File) arrayList2.remove(0);
            arrayList.add(file2);
            File[] listFiles = file2.listFiles();
            if (listFiles != null && listFiles.length > 0) {
                for (File file3 : listFiles) {
                    if (file3.isDirectory()) {
                        arrayList2.add(file3);
                    } else if (file3.exists() && !file3.delete()) {
                        return false;
                    }
                }
                continue;
            }
        }
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            File file4 = (File) arrayList.get(size);
            if (file4.exists() && !file4.delete()) {
                return false;
            }
        }
        return true;
    }

    public static long c(Context context) {
        if (!a()) {
            return 0;
        }
        return f(context.getExternalFilesDir((String) null));
    }

    public static byte[] c(File file) {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                byte[] a2 = e.a((InputStream) fileInputStream);
                e.a((Closeable) fileInputStream);
                return a2;
            } catch (IOException e) {
                e = e;
                try {
                    e.printStackTrace();
                    e.a((Closeable) fileInputStream);
                    return null;
                } catch (Throwable th) {
                    th = th;
                    fileInputStream2 = fileInputStream;
                    e.a((Closeable) fileInputStream2);
                    throw th;
                }
            }
        } catch (IOException e2) {
            e = e2;
            fileInputStream = null;
            e.printStackTrace();
            e.a((Closeable) fileInputStream);
            return null;
        } catch (Throwable th2) {
            th = th2;
            e.a((Closeable) fileInputStream2);
            throw th;
        }
    }

    public static String d(File file) {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                String b2 = e.b(fileInputStream);
                e.a((Closeable) fileInputStream);
                return b2;
            } catch (IOException e) {
                e = e;
                try {
                    e.printStackTrace();
                    e.a((Closeable) fileInputStream);
                    return null;
                } catch (Throwable th) {
                    th = th;
                    fileInputStream2 = fileInputStream;
                    e.a((Closeable) fileInputStream2);
                    throw th;
                }
            }
        } catch (IOException e2) {
            e = e2;
            fileInputStream = null;
            e.printStackTrace();
            e.a((Closeable) fileInputStream);
            return null;
        } catch (Throwable th2) {
            th = th2;
            e.a((Closeable) fileInputStream2);
            throw th;
        }
    }

    public static List<String> e(File file) {
        ArrayList arrayList = new ArrayList();
        BufferedReader bufferedReader = null;
        if (file != null) {
            try {
                if (file.exists()) {
                    BufferedReader bufferedReader2 = new BufferedReader(new FileReader(file));
                    while (true) {
                        try {
                            String readLine = bufferedReader2.readLine();
                            if (readLine == null) {
                                break;
                            }
                            arrayList.add(readLine);
                        } catch (IOException unused) {
                        } catch (Throwable th) {
                            th = th;
                            bufferedReader = bufferedReader2;
                            e.a((Closeable) bufferedReader);
                            throw th;
                        }
                    }
                    bufferedReader = bufferedReader2;
                }
            } catch (IOException unused2) {
            } catch (Throwable th2) {
                th = th2;
                e.a((Closeable) bufferedReader);
                throw th;
            }
        }
        e.a((Closeable) bufferedReader);
        return arrayList;
    }

    private static long f(File file) {
        try {
            StatFs statFs = new StatFs(file.getAbsolutePath());
            if (Build.VERSION.SDK_INT >= 18) {
                return statFs.getBlockSizeLong() * statFs.getAvailableBlocksLong();
            }
            return ((long) statFs.getBlockSize()) * ((long) statFs.getAvailableBlocks());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Deprecated
    private static boolean g(File file) {
        if (file == null || !file.exists()) {
            return true;
        }
        if (file.isFile()) {
            return file.delete();
        }
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (File b2 : listFiles) {
                if (!b(b2)) {
                    return false;
                }
            }
        }
        return file.delete();
    }
}
