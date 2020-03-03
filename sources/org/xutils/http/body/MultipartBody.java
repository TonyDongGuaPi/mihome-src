package org.xutils.http.body;

import android.text.TextUtils;
import com.alipay.sdk.util.i;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.commons.cli.HelpFormatter;
import org.xutils.common.Callback;
import org.xutils.common.util.IOUtil;
import org.xutils.common.util.KeyValue;
import org.xutils.http.ProgressHandler;

public class MultipartBody implements ProgressBody {

    /* renamed from: a  reason: collision with root package name */
    private static byte[] f10778a = "--------7da3d81520810".getBytes();
    private static byte[] b = "\r\n".getBytes();
    private static byte[] c = HelpFormatter.f.getBytes();
    private byte[] d;
    private String e;
    private String f = "UTF-8";
    private List<KeyValue> g;
    private long h = 0;
    private long i = 0;
    private ProgressHandler j;

    public MultipartBody(List<KeyValue> list, String str) {
        if (!TextUtils.isEmpty(str)) {
            this.f = str;
        }
        this.g = list;
        c();
        CounterOutputStream counterOutputStream = new CounterOutputStream();
        try {
            a((OutputStream) counterOutputStream);
            this.h = counterOutputStream.f10779a.get();
        } catch (IOException unused) {
            this.h = -1;
        }
    }

    public void a(ProgressHandler progressHandler) {
        this.j = progressHandler;
    }

    private void c() {
        String hexString = Double.toHexString(Math.random() * 65535.0d);
        this.d = hexString.getBytes();
        this.e = "multipart/form-data; boundary=" + new String(f10778a) + hexString;
    }

    public long b() {
        return this.h;
    }

    public void a(String str) {
        int indexOf = this.e.indexOf(i.b);
        this.e = "multipart/" + str + this.e.substring(indexOf);
    }

    public String a() {
        return this.e;
    }

    public void a(OutputStream outputStream) throws IOException {
        if (this.j == null || this.j.a(this.h, this.i, true)) {
            for (KeyValue next : this.g) {
                String str = next.f4233a;
                Object obj = next.b;
                if (!TextUtils.isEmpty(str) && obj != null) {
                    a(outputStream, str, obj);
                }
            }
            a(outputStream, c, f10778a, this.d, c);
            outputStream.flush();
            if (this.j != null) {
                this.j.a(this.h, this.h, true);
                return;
            }
            return;
        }
        throw new Callback.CancelledException("upload stopped!");
    }

    private void a(OutputStream outputStream, String str, Object obj) throws IOException {
        String str2;
        byte[] bArr;
        a(outputStream, c, f10778a, this.d);
        String str3 = "";
        if (obj instanceof BodyItemWrapper) {
            BodyItemWrapper bodyItemWrapper = (BodyItemWrapper) obj;
            Object a2 = bodyItemWrapper.a();
            String b2 = bodyItemWrapper.b();
            str2 = bodyItemWrapper.c();
            obj = a2;
            str3 = b2;
        } else {
            str2 = null;
        }
        if (obj instanceof File) {
            File file = (File) obj;
            if (TextUtils.isEmpty(str3)) {
                str3 = file.getName();
            }
            if (TextUtils.isEmpty(str2)) {
                str2 = FileBody.a(file);
            }
            a(outputStream, a(str, str3, this.f));
            a(outputStream, a(obj, str2, this.f));
            a(outputStream, new byte[0][]);
            a(outputStream, file);
            a(outputStream, new byte[0][]);
            return;
        }
        a(outputStream, a(str, str3, this.f));
        a(outputStream, a(obj, str2, this.f));
        a(outputStream, new byte[0][]);
        if (obj instanceof InputStream) {
            a(outputStream, (InputStream) obj);
            a(outputStream, new byte[0][]);
            return;
        }
        if (obj instanceof byte[]) {
            bArr = (byte[]) obj;
        } else {
            bArr = String.valueOf(obj).getBytes(this.f);
        }
        a(outputStream, bArr);
        this.i += (long) bArr.length;
        if (this.j != null && !this.j.a(this.h, this.i, false)) {
            throw new Callback.CancelledException("upload stopped!");
        }
    }

    private void a(OutputStream outputStream, byte[]... bArr) throws IOException {
        if (bArr != null) {
            for (byte[] write : bArr) {
                outputStream.write(write);
            }
        }
        outputStream.write(b);
    }

    private void a(OutputStream outputStream, File file) throws IOException {
        if (outputStream instanceof CounterOutputStream) {
            ((CounterOutputStream) outputStream).a(file);
        } else {
            a(outputStream, (InputStream) new FileInputStream(file));
        }
    }

    private void a(OutputStream outputStream, InputStream inputStream) throws IOException {
        if (outputStream instanceof CounterOutputStream) {
            ((CounterOutputStream) outputStream).a(inputStream);
            return;
        }
        try {
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read >= 0) {
                    outputStream.write(bArr, 0, read);
                    this.i += (long) read;
                    if (this.j != null) {
                        if (!this.j.a(this.h, this.i, false)) {
                            throw new Callback.CancelledException("upload stopped!");
                        }
                    }
                } else {
                    return;
                }
            }
        } finally {
            IOUtil.a((Closeable) inputStream);
        }
    }

    private static byte[] a(String str, String str2, String str3) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder("Content-Disposition: form-data");
        sb.append("; name=\"");
        sb.append(str.replace("\"", "\\\""));
        sb.append("\"");
        if (!TextUtils.isEmpty(str2)) {
            sb.append("; filename=\"");
            sb.append(str2.replace("\"", "\\\""));
            sb.append("\"");
        }
        return sb.toString().getBytes(str3);
    }

    private static byte[] a(Object obj, String str, String str2) throws UnsupportedEncodingException {
        String str3;
        StringBuilder sb = new StringBuilder("Content-Type: ");
        if (!TextUtils.isEmpty(str)) {
            str3 = str.replaceFirst("\\/jpg$", "/jpeg");
        } else if (obj instanceof String) {
            str3 = "text/plain; charset=" + str2;
        } else {
            str3 = "application/octet-stream";
        }
        sb.append(str3);
        return sb.toString().getBytes(str2);
    }

    private class CounterOutputStream extends OutputStream {

        /* renamed from: a  reason: collision with root package name */
        final AtomicLong f10779a = new AtomicLong(0);

        public CounterOutputStream() {
        }

        public void a(File file) {
            if (this.f10779a.get() != -1) {
                this.f10779a.addAndGet(file.length());
            }
        }

        public void a(InputStream inputStream) {
            if (this.f10779a.get() != -1) {
                long a2 = InputStreamBody.a(inputStream);
                if (a2 > 0) {
                    this.f10779a.addAndGet(a2);
                } else {
                    this.f10779a.set(-1);
                }
            }
        }

        public void write(int i) throws IOException {
            if (this.f10779a.get() != -1) {
                this.f10779a.incrementAndGet();
            }
        }

        public void write(byte[] bArr) throws IOException {
            if (this.f10779a.get() != -1) {
                this.f10779a.addAndGet((long) bArr.length);
            }
        }

        public void write(byte[] bArr, int i, int i2) throws IOException {
            if (this.f10779a.get() != -1) {
                this.f10779a.addAndGet((long) i2);
            }
        }
    }
}
