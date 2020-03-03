package com.android.vending.expansion.zipfile;

import android.content.Context;
import android.os.Environment;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class APKExpansionSupport {

    /* renamed from: a  reason: collision with root package name */
    private static final String f4756a = "/Android/obb/";

    static String[] a(Context context, int i, int i2) {
        String packageName = context.getPackageName();
        Vector vector = new Vector();
        if (Environment.getExternalStorageState().equals("mounted")) {
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            File file = new File(externalStorageDirectory.toString() + f4756a + packageName);
            if (file.exists()) {
                if (i > 0) {
                    String str = file + File.separator + "main." + i + "." + packageName + ".obb";
                    if (new File(str).isFile()) {
                        vector.add(str);
                    }
                }
                if (i2 > 0) {
                    String str2 = file + File.separator + "patch." + i2 + "." + packageName + ".obb";
                    if (new File(str2).isFile()) {
                        vector.add(str2);
                    }
                }
            }
        }
        String[] strArr = new String[vector.size()];
        vector.toArray(strArr);
        return strArr;
    }

    public static ZipResourceFile a(String[] strArr) throws IOException {
        ZipResourceFile zipResourceFile = null;
        for (String str : strArr) {
            if (zipResourceFile == null) {
                zipResourceFile = new ZipResourceFile(str);
            } else {
                zipResourceFile.d(str);
            }
        }
        return zipResourceFile;
    }

    public static ZipResourceFile b(Context context, int i, int i2) throws IOException {
        return a(a(context, i, i2));
    }
}
