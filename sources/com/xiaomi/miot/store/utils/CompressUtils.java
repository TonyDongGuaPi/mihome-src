package com.xiaomi.miot.store.utils;

import android.support.annotation.NonNull;
import com.xiaomi.youpin.log.LogUtils;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;

public class CompressUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11411a = "CompressUtils";

    public static boolean a(@NonNull File file, @NonNull File file2) {
        ZipFile zipFile = null;
        try {
            ZipFile zipFile2 = new ZipFile(file);
            try {
                Enumeration<? extends ZipEntry> entries = zipFile2.entries();
                while (entries.hasMoreElements()) {
                    ZipEntry zipEntry = (ZipEntry) entries.nextElement();
                    File file3 = new File(file2, zipEntry.getName());
                    if (zipEntry.isDirectory()) {
                        file3.mkdirs();
                    } else {
                        file3.getParentFile().mkdirs();
                        InputStream inputStream = zipFile2.getInputStream(zipEntry);
                        FileOutputStream fileOutputStream = new FileOutputStream(file3);
                        IOUtils.a(inputStream, (OutputStream) fileOutputStream);
                        IOUtils.a((Closeable) inputStream);
                        IOUtils.a((Closeable) fileOutputStream);
                    }
                }
                IOUtils.a((Closeable) zipFile2);
                return true;
            } catch (IOException e) {
                e = e;
                zipFile = zipFile2;
                try {
                    LogUtils.e(f11411a, "unzip failed", e);
                    IOUtils.a((Closeable) zipFile);
                    return false;
                } catch (Throwable th) {
                    th = th;
                    zipFile2 = zipFile;
                    IOUtils.a((Closeable) zipFile2);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                IOUtils.a((Closeable) zipFile2);
                throw th;
            }
        } catch (IOException e2) {
            e = e2;
            LogUtils.e(f11411a, "unzip failed", e);
            IOUtils.a((Closeable) zipFile);
            return false;
        }
    }

    public static boolean b(@NonNull File file, @NonNull File file2) {
        TarArchiveInputStream tarArchiveInputStream = null;
        try {
            TarArchiveInputStream tarArchiveInputStream2 = new TarArchiveInputStream(new FileInputStream(file));
            while (true) {
                try {
                    TarArchiveEntry e = tarArchiveInputStream2.e();
                    if (e != null) {
                        File file3 = new File(file2, e.getName());
                        if (e.isDirectory()) {
                            file3.mkdirs();
                        } else {
                            file3.createNewFile();
                            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file3));
                            IOUtils.a((InputStream) tarArchiveInputStream2, (OutputStream) bufferedOutputStream);
                            IOUtils.a((Closeable) bufferedOutputStream);
                        }
                    } else {
                        IOUtils.a((Closeable) tarArchiveInputStream2);
                        return true;
                    }
                } catch (Exception e2) {
                    e = e2;
                    tarArchiveInputStream = tarArchiveInputStream2;
                    try {
                        LogUtils.e(f11411a, "untar failed", e);
                        IOUtils.a((Closeable) tarArchiveInputStream);
                        return false;
                    } catch (Throwable th) {
                        th = th;
                        tarArchiveInputStream2 = tarArchiveInputStream;
                        IOUtils.a((Closeable) tarArchiveInputStream2);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    IOUtils.a((Closeable) tarArchiveInputStream2);
                    throw th;
                }
            }
        } catch (Exception e3) {
            e = e3;
            LogUtils.e(f11411a, "untar failed", e);
            IOUtils.a((Closeable) tarArchiveInputStream);
            return false;
        }
    }
}
