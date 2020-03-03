package com.mi.global.bbs.utils;

import android.content.Context;
import com.mi.util.ApkUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ChannelUtil extends com.mi.util.ChannelUtil {
    private static final String[] CHANNELS = {"GOOGLEPLAY", "MSITE", "OTA"};

    public static void init() {
        if (sChannelUtil == null) {
            sChannelUtil = new ChannelUtil();
        }
    }

    private ChannelUtil() {
        this.channels = CHANNELS;
    }

    public static ChannelUtil getInstance() {
        return (ChannelUtil) sChannelUtil;
    }

    public static String getApkMD5(Context context) {
        String str = null;
        try {
            ZipFile zipFile = new ZipFile(ApkUtils.c(context, context.getPackageName()));
            ZipEntry entry = zipFile.getEntry("META-INF/md5.txt");
            if (entry != null) {
                String readLine = new BufferedReader(new InputStreamReader(zipFile.getInputStream(entry))).readLine();
                try {
                    System.out.println(readLine);
                    return readLine;
                } catch (IOException e) {
                    IOException iOException = e;
                    str = readLine;
                    e = iOException;
                }
            }
        } catch (IOException e2) {
            e = e2;
            e.printStackTrace();
            return str;
        }
        return str;
    }
}
