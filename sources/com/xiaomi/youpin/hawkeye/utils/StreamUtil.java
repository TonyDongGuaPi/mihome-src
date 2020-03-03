package com.xiaomi.youpin.hawkeye.utils;

import android.util.Pair;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StreamUtil {
    public static void a(InputStream inputStream, OutputStream outputStream, byte[] bArr) throws IOException {
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }

    public static void a(Closeable closeable, boolean z) throws IOException {
        if (closeable == null) {
            return;
        }
        if (z) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            closeable.close();
        }
    }

    public static ArrayList<Pair<String, String>> a(Map<String, List<String>> map) {
        ArrayList<Pair<String, String>> arrayList = new ArrayList<>();
        for (Map.Entry next : map.entrySet()) {
            for (String str : (List) next.getValue()) {
                if (next.getKey() != null) {
                    arrayList.add(Pair.create(next.getKey(), str));
                }
            }
        }
        return arrayList;
    }
}
