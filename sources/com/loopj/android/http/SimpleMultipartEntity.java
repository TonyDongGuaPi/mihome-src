package com.loopj.android.http;

import android.text.TextUtils;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.commons.cli.HelpFormatter;

class SimpleMultipartEntity implements HttpEntity {
    /* access modifiers changed from: private */
    public static final byte[] CR_LF = "\r\n".getBytes();
    private static final String LOG_TAG = "SimpleMultipartEntity";
    private static final char[] MULTIPART_CHARS = "-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private static final String STR_CR_LF = "\r\n";
    /* access modifiers changed from: private */
    public static final byte[] TRANSFER_ENCODING_BINARY = "Content-Transfer-Encoding: binary\r\n".getBytes();
    private final String boundary;
    private final byte[] boundaryEnd;
    /* access modifiers changed from: private */
    public final byte[] boundaryLine;
    private long bytesWritten;
    private final List<FilePart> fileParts = new ArrayList();
    private boolean isRepeatable;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final ResponseHandlerInterface progressHandler;
    private long totalSize;

    private String normalizeContentType(String str) {
        return str == null ? "application/octet-stream" : str;
    }

    public Header getContentEncoding() {
        return null;
    }

    public boolean isChunked() {
        return false;
    }

    public boolean isStreaming() {
        return false;
    }

    public SimpleMultipartEntity(ResponseHandlerInterface responseHandlerInterface) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 30; i++) {
            sb.append(MULTIPART_CHARS[random.nextInt(MULTIPART_CHARS.length)]);
        }
        this.boundary = sb.toString();
        this.boundaryLine = (HelpFormatter.f + this.boundary + "\r\n").getBytes();
        this.boundaryEnd = (HelpFormatter.f + this.boundary + HelpFormatter.f + "\r\n").getBytes();
        this.progressHandler = responseHandlerInterface;
    }

    public void addPart(String str, String str2, String str3) {
        try {
            this.out.write(this.boundaryLine);
            this.out.write(createContentDisposition(str));
            this.out.write(createContentType(str3));
            this.out.write(CR_LF);
            this.out.write(str2.getBytes());
            this.out.write(CR_LF);
        } catch (IOException e) {
            AsyncHttpClient.log.e(LOG_TAG, "addPart ByteArrayOutputStream exception", e);
        }
    }

    public void addPartWithCharset(String str, String str2, String str3) {
        if (str3 == null) {
            str3 = "UTF-8";
        }
        addPart(str, str2, "text/plain; charset=" + str3);
    }

    public void addPart(String str, String str2) {
        addPartWithCharset(str, str2, (String) null);
    }

    public void addPart(String str, File file) {
        addPart(str, file, (String) null);
    }

    public void addPart(String str, File file, String str2) {
        this.fileParts.add(new FilePart(str, file, normalizeContentType(str2)));
    }

    public void addPart(String str, File file, String str2, String str3) {
        this.fileParts.add(new FilePart(str, file, normalizeContentType(str2), str3));
    }

    public void addPart(String str, String str2, InputStream inputStream, String str3) throws IOException {
        this.out.write(this.boundaryLine);
        this.out.write(createContentDisposition(str, str2));
        this.out.write(createContentType(str3));
        this.out.write(TRANSFER_ENCODING_BINARY);
        this.out.write(CR_LF);
        byte[] bArr = new byte[4096];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                this.out.write(bArr, 0, read);
            } else {
                this.out.write(CR_LF);
                this.out.flush();
                return;
            }
        }
    }

    /* access modifiers changed from: private */
    public byte[] createContentType(String str) {
        return ("Content-Type: " + normalizeContentType(str) + "\r\n").getBytes();
    }

    private byte[] createContentDisposition(String str) {
        return ("Content-Disposition: form-data; name=\"" + str + "\"" + "\r\n").getBytes();
    }

    /* access modifiers changed from: private */
    public byte[] createContentDisposition(String str, String str2) {
        return ("Content-Disposition: form-data; name=\"" + str + "\"" + "; filename=\"" + str2 + "\"" + "\r\n").getBytes();
    }

    /* access modifiers changed from: private */
    public void updateProgress(long j) {
        this.bytesWritten += j;
        this.progressHandler.sendProgressMessage(this.bytesWritten, this.totalSize);
    }

    public long getContentLength() {
        long size = (long) this.out.size();
        for (FilePart totalLength : this.fileParts) {
            long totalLength2 = totalLength.getTotalLength();
            if (totalLength2 < 0) {
                return -1;
            }
            size += totalLength2;
        }
        return size + ((long) this.boundaryEnd.length);
    }

    public Header getContentType() {
        return new BasicHeader("Content-Type", "multipart/form-data; boundary=" + this.boundary);
    }

    public void setIsRepeatable(boolean z) {
        this.isRepeatable = z;
    }

    public boolean isRepeatable() {
        return this.isRepeatable;
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        this.bytesWritten = 0;
        this.totalSize = (long) ((int) getContentLength());
        this.out.writeTo(outputStream);
        updateProgress((long) this.out.size());
        for (FilePart writeTo : this.fileParts) {
            writeTo.writeTo(outputStream);
        }
        outputStream.write(this.boundaryEnd);
        updateProgress((long) this.boundaryEnd.length);
    }

    public void consumeContent() throws IOException, UnsupportedOperationException {
        if (isStreaming()) {
            throw new UnsupportedOperationException("Streaming entity does not implement #consumeContent()");
        }
    }

    public InputStream getContent() throws IOException, UnsupportedOperationException {
        throw new UnsupportedOperationException("getContent() is not supported. Use writeTo() instead.");
    }

    private class FilePart {
        public final File file;
        public final byte[] header;

        public FilePart(String str, File file2, String str2, String str3) {
            this.header = createHeader(str, TextUtils.isEmpty(str3) ? file2.getName() : str3, str2);
            this.file = file2;
        }

        public FilePart(String str, File file2, String str2) {
            this.header = createHeader(str, file2.getName(), str2);
            this.file = file2;
        }

        private byte[] createHeader(String str, String str2, String str3) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                byteArrayOutputStream.write(SimpleMultipartEntity.this.boundaryLine);
                byteArrayOutputStream.write(SimpleMultipartEntity.this.createContentDisposition(str, str2));
                byteArrayOutputStream.write(SimpleMultipartEntity.this.createContentType(str3));
                byteArrayOutputStream.write(SimpleMultipartEntity.TRANSFER_ENCODING_BINARY);
                byteArrayOutputStream.write(SimpleMultipartEntity.CR_LF);
            } catch (IOException e) {
                AsyncHttpClient.log.e(SimpleMultipartEntity.LOG_TAG, "createHeader ByteArrayOutputStream exception", e);
            }
            return byteArrayOutputStream.toByteArray();
        }

        public long getTotalLength() {
            return ((long) this.header.length) + this.file.length() + ((long) SimpleMultipartEntity.CR_LF.length);
        }

        public void writeTo(OutputStream outputStream) throws IOException {
            outputStream.write(this.header);
            SimpleMultipartEntity.this.updateProgress((long) this.header.length);
            FileInputStream fileInputStream = new FileInputStream(this.file);
            byte[] bArr = new byte[4096];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read != -1) {
                    outputStream.write(bArr, 0, read);
                    SimpleMultipartEntity.this.updateProgress((long) read);
                } else {
                    outputStream.write(SimpleMultipartEntity.CR_LF);
                    SimpleMultipartEntity.this.updateProgress((long) SimpleMultipartEntity.CR_LF.length);
                    outputStream.flush();
                    AsyncHttpClient.silentCloseInputStream(fileInputStream);
                    return;
                }
            }
        }
    }
}
