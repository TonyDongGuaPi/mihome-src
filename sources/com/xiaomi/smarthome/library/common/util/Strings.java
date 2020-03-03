package com.xiaomi.smarthome.library.common.util;

import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Strings {

    /* renamed from: a  reason: collision with root package name */
    private static final String f18737a = "common/Strings";

    public static List<String> a(String str, String str2) {
        if (str == null || str.length() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Collections.addAll(arrayList, str.split(str2));
        return arrayList;
    }

    public static String a(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[512];
        while (true) {
            try {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            } catch (IOException e) {
                Log.e(f18737a, e.toString());
            }
        }
        return byteArrayOutputStream.toString();
    }

    public static boolean a(String str) {
        return str == null || str.length() == 0;
    }
}
