package com.vladium.emma.rt;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Deprecated
public final class RT {
    private RT() {
    }

    public static void a(File file, boolean z, boolean z2) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file, z);
        try {
            fileOutputStream.write(org.jacoco.agent.rt.RT.a().a(false));
        } finally {
            fileOutputStream.close();
        }
    }

    public static synchronized void a(File file, boolean z) throws IOException {
        synchronized (RT.class) {
            a(file, true, z);
        }
    }
}
