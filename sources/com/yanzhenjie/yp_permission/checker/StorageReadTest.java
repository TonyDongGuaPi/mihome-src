package com.yanzhenjie.yp_permission.checker;

import android.os.Environment;
import java.io.File;

class StorageReadTest implements PermissionTest {
    StorageReadTest() {
    }

    public boolean a() throws Throwable {
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        if (!externalStorageDirectory.exists() || !externalStorageDirectory.canRead()) {
            return false;
        }
        long lastModified = externalStorageDirectory.lastModified();
        String[] list = externalStorageDirectory.list();
        if (lastModified <= 0 || list == null) {
            return false;
        }
        return true;
    }
}
