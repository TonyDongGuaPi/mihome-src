package com.yanzhenjie.permission.checker;

import android.os.Environment;
import java.io.File;

class StorageWriteTest implements PermissionTest {
    StorageWriteTest() {
    }

    public boolean a() throws Throwable {
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        if (!externalStorageDirectory.exists() || !externalStorageDirectory.canWrite()) {
            return false;
        }
        File file = new File(externalStorageDirectory, "ANDROID.PERMISSION.TEST");
        if (file.exists()) {
            return file.delete();
        }
        return file.createNewFile();
    }
}
