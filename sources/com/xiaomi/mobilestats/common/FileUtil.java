package com.xiaomi.mobilestats.common;

import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileUtil {
    public static boolean checkOrCreateFolder(String str) {
        try {
            File file = new File(str.toString());
            if (file.exists()) {
                return true;
            }
            file.mkdirs();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteFile(String str) {
        File file = new File(str);
        if (file.exists()) {
            return file.delete();
        }
        return true;
    }

    public static void deleteFloder(File file) {
        if (file != null) {
            try {
                if (file.isFile()) {
                    file.delete();
                } else if (file.isDirectory()) {
                    File[] listFiles = file.listFiles();
                    if (listFiles != null) {
                        if (listFiles.length != 0) {
                            for (File deleteFloder : listFiles) {
                                deleteFloder(deleteFloder);
                            }
                            file.delete();
                            return;
                        }
                    }
                    file.delete();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static File[] getFiles(String str) {
        File[] listFiles;
        if (StrUtil.isEmpty(str)) {
            return null;
        }
        try {
            File file = new File(str);
            if (!file.exists() || !file.isDirectory() || (listFiles = file.listFiles()) == null || listFiles.length <= 0) {
                return null;
            }
            return listFiles;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String readFile(String str) {
        String str2;
        try {
            File file = new File(str);
            if (!file.exists()) {
                return "";
            }
            FileInputStream fileInputStream = new FileInputStream(file);
            StringBuffer stringBuffer = new StringBuffer();
            byte[] bArr = new byte[1014];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                stringBuffer.append(new String(bArr, 0, read));
            }
            str2 = stringBuffer.toString();
            try {
                fileInputStream.close();
            } catch (Exception e) {
                e = e;
            }
            return str2;
        } catch (Exception e2) {
            e = e2;
            str2 = "";
            e.printStackTrace();
            return str2;
        }
    }

    public static void writeCrashMD5List(String str, String str2) {
        try {
            File file = new File(str);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(str, false);
            if (!TextUtils.isEmpty(str2)) {
                fileOutputStream.write(str2.getBytes());
            }
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeLogToFile(String str, byte[] bArr, boolean z) {
        try {
            File file = new File(str);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(str, true);
            if (bArr != null && bArr.length > 0) {
                fileOutputStream.write(bArr);
                if (z) {
                    fileOutputStream.write("\n".getBytes());
                }
            }
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
