package cn.jiajixin.nuwa.util;

import android.content.Context;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class AssetUtils {
    public static String a(Context context, String str, File file) throws IOException {
        File file2 = new File(file, str);
        if (!file2.exists()) {
            InputStream open = context.getAssets().open(str);
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            a(open, fileOutputStream);
            open.close();
            fileOutputStream.close();
        }
        return file2.getAbsolutePath();
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
