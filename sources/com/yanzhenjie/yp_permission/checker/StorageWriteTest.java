package com.yanzhenjie.yp_permission.checker;

import android.os.Environment;
import com.alipay.android.phone.a.a.a;
import java.io.File;

class StorageWriteTest implements PermissionTest {
    StorageWriteTest() {
    }

    public boolean a() throws Throwable {
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        if (!externalStorageDirectory.exists() || !externalStorageDirectory.canWrite()) {
            return false;
        }
        File file = new File(externalStorageDirectory, a.f813a);
        if (file.exists() && file.isFile() && !file.delete()) {
            return false;
        }
        if (!file.exists() && !file.mkdirs()) {
            return false;
        }
        File file2 = new File(file, "ANDROID.PERMISSION.TEST");
        if (file2.exists()) {
            return file2.delete();
        }
        return file2.createNewFile();
    }
}
