package com.loopj.android.http;

import android.content.Context;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public abstract class FileAsyncHttpResponseHandler extends AsyncHttpResponseHandler {
    private static final String LOG_TAG = "FileAsyncHttpRH";
    protected final boolean append;
    protected final File file;
    protected File frontendFile;
    protected final boolean renameIfExists;

    public abstract void onFailure(int i, Header[] headerArr, Throwable th, File file2);

    public abstract void onSuccess(int i, Header[] headerArr, File file2);

    public FileAsyncHttpResponseHandler(File file2) {
        this(file2, false);
    }

    public FileAsyncHttpResponseHandler(File file2, boolean z) {
        this(file2, z, false);
    }

    public FileAsyncHttpResponseHandler(File file2, boolean z, boolean z2) {
        this(file2, z, z2, false);
    }

    public FileAsyncHttpResponseHandler(File file2, boolean z, boolean z2, boolean z3) {
        super(z3);
        Utils.asserts(file2 != null, "File passed into FileAsyncHttpResponseHandler constructor must not be null");
        if (!file2.isDirectory() && !file2.getParentFile().isDirectory()) {
            Utils.asserts(file2.getParentFile().mkdirs(), "Cannot create parent directories for requested File location");
        }
        if (file2.isDirectory() && !file2.mkdirs()) {
            AsyncHttpClient.log.d(LOG_TAG, "Cannot create directories for requested Directory location, might not be a problem");
        }
        this.file = file2;
        this.append = z;
        this.renameIfExists = z2;
    }

    public FileAsyncHttpResponseHandler(Context context) {
        this.file = getTemporaryFile(context);
        this.append = false;
        this.renameIfExists = false;
    }

    public boolean deleteTargetFile() {
        return getTargetFile() != null && getTargetFile().delete();
    }

    /* access modifiers changed from: protected */
    public File getTemporaryFile(Context context) {
        Utils.asserts(context != null, "Tried creating temporary file without having Context");
        try {
            return File.createTempFile("temp_", "_handled", context.getCacheDir());
        } catch (IOException e) {
            AsyncHttpClient.log.e(LOG_TAG, "Cannot create temporary file", e);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public File getOriginalFile() {
        Utils.asserts(this.file != null, "Target file is null, fatal!");
        return this.file;
    }

    public File getTargetFile() {
        if (this.frontendFile == null) {
            this.frontendFile = getOriginalFile().isDirectory() ? getTargetFileByParsingURL() : getOriginalFile();
        }
        return this.frontendFile;
    }

    /* access modifiers changed from: protected */
    public File getTargetFileByParsingURL() {
        String str;
        Utils.asserts(getOriginalFile().isDirectory(), "Target file is not a directory, cannot proceed");
        Utils.asserts(getRequestURI() != null, "RequestURI is null, cannot proceed");
        String uri = getRequestURI().toString();
        String substring = uri.substring(uri.lastIndexOf(47) + 1, uri.length());
        File file2 = new File(getOriginalFile(), substring);
        if (!file2.exists() || !this.renameIfExists) {
            return file2;
        }
        if (!substring.contains(".")) {
            str = substring + " (%d)";
        } else {
            str = substring.substring(0, substring.lastIndexOf(46)) + " (%d)" + substring.substring(substring.lastIndexOf(46), substring.length());
        }
        int i = 0;
        while (true) {
            File file3 = new File(getOriginalFile(), String.format(str, new Object[]{Integer.valueOf(i)}));
            if (!file3.exists()) {
                return file3;
            }
            i++;
        }
    }

    public final void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th) {
        onFailure(i, headerArr, th, getTargetFile());
    }

    public final void onSuccess(int i, Header[] headerArr, byte[] bArr) {
        onSuccess(i, headerArr, getTargetFile());
    }

    /* access modifiers changed from: protected */
    public byte[] getResponseData(HttpEntity httpEntity) throws IOException {
        if (httpEntity == null) {
            return null;
        }
        InputStream content = httpEntity.getContent();
        long contentLength = httpEntity.getContentLength();
        FileOutputStream fileOutputStream = new FileOutputStream(getTargetFile(), this.append);
        if (content == null) {
            return null;
        }
        try {
            byte[] bArr = new byte[4096];
            int i = 0;
            while (true) {
                int read = content.read(bArr);
                if (read != -1 && !Thread.currentThread().isInterrupted()) {
                    i += read;
                    fileOutputStream.write(bArr, 0, read);
                    sendProgressMessage((long) i, contentLength);
                }
            }
            return null;
        } finally {
            AsyncHttpClient.silentCloseInputStream(content);
            fileOutputStream.flush();
            AsyncHttpClient.silentCloseOutputStream(fileOutputStream);
        }
    }
}
