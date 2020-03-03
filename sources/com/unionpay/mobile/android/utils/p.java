package com.unionpay.mobile.android.utils;

import android.os.Environment;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public final class p {
    public static boolean a(byte[] bArr) {
        File file = new File(Environment.getExternalStorageDirectory(), "UPPay");
        file.mkdir();
        File file2 = new File(file, "UPPayPluginEx.apk");
        try {
            file2.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            fileOutputStream.write(bArr);
            fileOutputStream.close();
            return true;
        } catch (IOException e) {
            k.a("uppay", "write2file error!!!!");
            e.printStackTrace();
            return false;
        }
    }
}
