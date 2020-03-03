package com.xiaomi.smarthome.core.server;

import android.os.MemoryFile;
import android.os.ParcelFileDescriptor;
import java.io.FileDescriptor;
import java.io.IOException;
import java.lang.reflect.Method;

public class MemoryFileHelper {
    public static MemoryFile a(String str, int i) {
        try {
            return new MemoryFile(str, i);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static MemoryFile a(ParcelFileDescriptor parcelFileDescriptor, int i, int i2) {
        if (parcelFileDescriptor != null) {
            return a(parcelFileDescriptor.getFileDescriptor(), i, i2);
        }
        throw new IllegalArgumentException("ParcelFileDescriptor 不能为空");
    }

    public static MemoryFile a(FileDescriptor fileDescriptor, int i, int i2) {
        MemoryFile memoryFile;
        try {
            memoryFile = new MemoryFile("tem", 1);
            try {
                memoryFile.close();
                Method[] declaredMethods = MemoryFile.class.getDeclaredMethods();
                Method method = null;
                int i3 = 0;
                while (declaredMethods != null && i3 < declaredMethods.length) {
                    if (declaredMethods[i3].getName().equals("native_mmap")) {
                        method = declaredMethods[i3];
                    }
                    i3++;
                }
                ReflectUtils.a("android.os.MemoryFile", (Object) memoryFile, "mFD", (Object) fileDescriptor);
                ReflectUtils.a("android.os.MemoryFile", (Object) memoryFile, "mLength", (Object) Integer.valueOf(i));
                ReflectUtils.a("android.os.MemoryFile", (Object) memoryFile, "mAddress", ReflectUtils.a((Object) null, method, fileDescriptor, Integer.valueOf(i), Integer.valueOf(i2)));
            } catch (Exception e) {
                e = e;
                e.printStackTrace();
                return memoryFile;
            }
        } catch (Exception e2) {
            e = e2;
            memoryFile = null;
            e.printStackTrace();
            return memoryFile;
        }
        return memoryFile;
    }

    public static ParcelFileDescriptor a(MemoryFile memoryFile) {
        if (memoryFile != null) {
            return (ParcelFileDescriptor) ReflectUtils.a("android.os.ParcelFileDescriptor", b(memoryFile));
        }
        throw new IllegalArgumentException("memoryFile 不能为空");
    }

    public static FileDescriptor b(MemoryFile memoryFile) {
        if (memoryFile != null) {
            return (FileDescriptor) ReflectUtils.a("android.os.MemoryFile", (Object) memoryFile, "getFileDescriptor", new Object[0]);
        }
        throw new IllegalArgumentException("memoryFile 不能为空");
    }
}
