package com.tencent.tinker.loader.shareutil;

import android.util.Log;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileLock;

public class ShareFileLockHelper implements Closeable {

    /* renamed from: a  reason: collision with root package name */
    public static final int f9256a = 3;
    public static final int b = 10;
    private static final String c = "Tinker.FileLockHelper";
    private final FileOutputStream d;
    private final FileLock e;

    private ShareFileLockHelper(File file) throws IOException {
        this.d = new FileOutputStream(file);
        FileLock fileLock = null;
        e = null;
        int i = 0;
        while (true) {
            if (i >= 3) {
                break;
            }
            i++;
            try {
                FileLock lock = this.d.getChannel().lock();
                if (lock != null) {
                    fileLock = lock;
                    break;
                }
                try {
                    Thread.sleep(10);
                    fileLock = lock;
                } catch (Exception e2) {
                    e = e2;
                    fileLock = lock;
                    Log.e(c, "getInfoLock Thread failed time:10");
                }
            } catch (Exception e3) {
                e = e3;
                Log.e(c, "getInfoLock Thread failed time:10");
            }
        }
        if (fileLock != null) {
            this.e = fileLock;
            return;
        }
        throw new IOException("Tinker Exception:FileLockHelper lock file failed: " + file.getAbsolutePath(), e);
    }

    public static ShareFileLockHelper a(File file) throws IOException {
        return new ShareFileLockHelper(file);
    }

    public void close() throws IOException {
        try {
            if (this.e != null) {
                this.e.release();
            }
        } finally {
            if (this.d != null) {
                this.d.close();
            }
        }
    }
}
