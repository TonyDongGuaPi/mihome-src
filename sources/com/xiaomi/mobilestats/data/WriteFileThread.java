package com.xiaomi.mobilestats.data;

import android.content.Context;
import java.io.File;
import java.io.FileOutputStream;

public class WriteFileThread extends Thread {
    private String cC;
    private String cE = "\n";
    private byte[] data;

    public WriteFileThread(Context context, String str, byte[] bArr) {
        this.cC = str;
        this.data = bArr;
    }

    private void c(byte[] bArr) {
        try {
            File file = new File(this.cC);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(this.cC, true);
            if (bArr != null && bArr.length > 0) {
                fileOutputStream.write(bArr);
                fileOutputStream.write(this.cE.getBytes());
            }
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        super.run();
        c(this.data);
    }
}
