package com.xiaomi.smarthome.device.bluetooth.utils;

import android.text.TextUtils;
import com.xiaomi.smarthome.application.SHApplication;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ConfigUtils {
    public static File a(String str) {
        File externalFilesDir = SHApplication.getAppContext().getExternalFilesDir("config");
        if (externalFilesDir.exists() && externalFilesDir.isFile()) {
            externalFilesDir.delete();
        }
        if (externalFilesDir.exists() || externalFilesDir.mkdirs()) {
            return new File(externalFilesDir, str);
        }
        return null;
    }

    public static BufferedReader a(File file) {
        if (file == null) {
            return null;
        }
        try {
            return new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String[] a(BufferedReader bufferedReader, String str) {
        String str2 = null;
        while (true) {
            try {
                str2 = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (str2 == null) {
                return null;
            }
            if (!TextUtils.isEmpty(str2) && !str2.startsWith("#")) {
                return str2.split(str);
            }
        }
    }
}
