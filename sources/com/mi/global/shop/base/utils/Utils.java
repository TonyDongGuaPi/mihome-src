package com.mi.global.shop.base.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Base64;
import com.coloros.mcssdk.c.a;
import com.mi.log.LogUtil;
import com.mi.util.Utils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Utils {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static final String f5681a = "Utils";

    public static final class Network extends Utils.Network {
    }

    public static final class Preference extends Utils.Preference {
        public static void a(Context context, String str, boolean z) {
            SharedPreferences defaultSharedPreferences;
            if (context != null && (defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)) != null && !defaultSharedPreferences.contains(str)) {
                setBooleanPref(context, str, z);
            }
        }
    }

    public static final class Files {
        public static long a(File file) {
            long j;
            File[] listFiles = file.listFiles();
            long j2 = 0;
            if (listFiles == null || listFiles.length == 0) {
                return 0;
            }
            for (int i = 0; i < listFiles.length; i++) {
                if (listFiles[i].isDirectory()) {
                    j = a(listFiles[i]);
                } else {
                    j = listFiles[i].length();
                }
                j2 += j;
            }
            return j2;
        }

        public static boolean a(String str, String str2) {
            LogUtil.b(Utils.f5681a, String.format("zipFile:%s, targetDir:%s", new Object[]{str, str2}));
            if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
                return false;
            }
            if (!str2.endsWith("/")) {
                str2 = str2 + "/";
            }
            try {
                ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(new FileInputStream(str)));
                byte[] bArr = new byte[4096];
                while (true) {
                    ZipEntry nextEntry = zipInputStream.getNextEntry();
                    if (nextEntry != null) {
                        String name = nextEntry.getName();
                        File file = new File(str2 + name);
                        if (!name.endsWith("/")) {
                            File file2 = new File(file.getParent());
                            if (!file2.exists() || !file2.isDirectory()) {
                                file2.mkdirs();
                            }
                            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file), 4096);
                            while (true) {
                                int read = zipInputStream.read(bArr, 0, 4096);
                                if (read == -1) {
                                    break;
                                }
                                bufferedOutputStream.write(bArr, 0, read);
                            }
                            bufferedOutputStream.flush();
                            bufferedOutputStream.close();
                        }
                    } else {
                        zipInputStream.close();
                        return true;
                    }
                }
            } catch (IOException unused) {
                return false;
            }
        }

        public static boolean b(File file) {
            if (file == null || !file.exists()) {
                return false;
            }
            if (!file.isDirectory()) {
                return file.delete();
            }
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File b : listFiles) {
                    if (!b(b)) {
                        return false;
                    }
                }
            }
            file.delete();
            return true;
        }

        public static boolean a(AssetManager assetManager, String str, String str2) {
            try {
                String[] list = assetManager.list(str);
                new File(str2).mkdirs();
                boolean z = true;
                for (String str3 : list) {
                    if (str3.contains(".")) {
                        z &= b(assetManager, str + "/" + str3, str2 + "/" + str3);
                    } else {
                        z &= a(assetManager, str + "/" + str3, str2 + "/" + str3);
                    }
                }
                return z;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        private static boolean b(AssetManager assetManager, String str, String str2) {
            try {
                InputStream open = assetManager.open(str);
                new File(str2).createNewFile();
                FileOutputStream fileOutputStream = new FileOutputStream(str2);
                a(open, (OutputStream) fileOutputStream);
                open.close();
                fileOutputStream.flush();
                fileOutputStream.close();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        private static void a(InputStream inputStream, OutputStream outputStream) throws IOException {
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read != -1) {
                    outputStream.write(bArr, 0, read);
                } else {
                    return;
                }
            }
        }
    }

    public static int a(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            return 0;
        }
    }

    public static String b(String str) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec("1431ed34249f13de".getBytes("utf-8"), a.b);
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS7Padding");
            instance.init(2, secretKeySpec, d("4def5eca70e41551"));
            try {
                return new String(instance.doFinal(Base64.decode(str, 0)), "utf-8");
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception e2) {
            System.out.println(e2.toString());
            return null;
        }
    }

    public static String c(String str) {
        byte[] bArr;
        try {
            bArr = str.getBytes("UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            bArr = null;
        }
        return new String(a(bArr, "1431ed34249f13de", "4def5eca70e41551"));
    }

    public static byte[] a(byte[] bArr, String str, String str2) {
        try {
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS7Padding");
            instance.init(1, new SecretKeySpec(str.getBytes(), a.b), new IvParameterSpec(str2.getBytes()));
            return Base64.encode(instance.doFinal(bArr), 0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static IvParameterSpec d(String str) {
        byte[] bArr;
        if (str == null) {
            str = "";
        }
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append(str);
        while (stringBuffer.length() < 16) {
            stringBuffer.append("0");
        }
        if (stringBuffer.length() > 16) {
            stringBuffer.setLength(16);
        }
        try {
            bArr = stringBuffer.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            bArr = null;
        }
        return new IvParameterSpec(bArr);
    }
}
