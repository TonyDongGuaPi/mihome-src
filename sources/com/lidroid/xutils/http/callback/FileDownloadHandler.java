package com.lidroid.xutils.http.callback;

import android.text.TextUtils;
import com.lidroid.xutils.util.IOUtils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.http.HttpEntity;

public class FileDownloadHandler {
    public File a(HttpEntity httpEntity, RequestCallBackHandler requestCallBackHandler, String str, boolean z, String str2) throws IOException {
        BufferedInputStream bufferedInputStream;
        FileOutputStream fileOutputStream;
        String str3 = str;
        String str4 = str2;
        BufferedOutputStream bufferedOutputStream = null;
        if (httpEntity == null || TextUtils.isEmpty(str)) {
            return null;
        }
        File file = new File(str3);
        if (!file.exists()) {
            File parentFile = file.getParentFile();
            if (parentFile.exists() || parentFile.mkdirs()) {
                file.createNewFile();
            }
        }
        long j = 0;
        if (z) {
            try {
                j = file.length();
                fileOutputStream = new FileOutputStream(str3, true);
            } catch (Throwable th) {
                th = th;
                bufferedInputStream = null;
                IOUtils.a((Closeable) bufferedInputStream);
                IOUtils.a((Closeable) bufferedOutputStream);
                throw th;
            }
        } else {
            fileOutputStream = new FileOutputStream(str3);
        }
        long j2 = j;
        long contentLength = httpEntity.getContentLength() + j2;
        bufferedInputStream = new BufferedInputStream(httpEntity.getContent());
        try {
            BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(fileOutputStream);
            if (requestCallBackHandler != null) {
                try {
                    if (!requestCallBackHandler.a(contentLength, j2, true)) {
                        IOUtils.a((Closeable) bufferedInputStream);
                        IOUtils.a((Closeable) bufferedOutputStream2);
                        return file;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    bufferedOutputStream = bufferedOutputStream2;
                    IOUtils.a((Closeable) bufferedInputStream);
                    IOUtils.a((Closeable) bufferedOutputStream);
                    throw th;
                }
            }
            byte[] bArr = new byte[4096];
            while (true) {
                long j3 = j2;
                int read = bufferedInputStream.read(bArr);
                if (read == -1) {
                    bufferedOutputStream2.flush();
                    if (requestCallBackHandler != null) {
                        requestCallBackHandler.a(contentLength, j3, true);
                    }
                    IOUtils.a((Closeable) bufferedInputStream);
                    IOUtils.a((Closeable) bufferedOutputStream2);
                    if (!file.exists() || TextUtils.isEmpty(str2)) {
                        return file;
                    }
                    File file2 = new File(file.getParent(), str4);
                    while (file2.exists()) {
                        file2 = new File(file.getParent(), String.valueOf(System.currentTimeMillis()) + str4);
                    }
                    return file.renameTo(file2) ? file2 : file;
                }
                bufferedOutputStream2.write(bArr, 0, read);
                j2 = j3 + ((long) read);
                if (requestCallBackHandler != null && !requestCallBackHandler.a(contentLength, j2, false)) {
                    IOUtils.a((Closeable) bufferedInputStream);
                    IOUtils.a((Closeable) bufferedOutputStream2);
                    return file;
                }
            }
        } catch (Throwable th3) {
            th = th3;
            IOUtils.a((Closeable) bufferedInputStream);
            IOUtils.a((Closeable) bufferedOutputStream);
            throw th;
        }
    }
}
