package miuipub.net.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import miuipub.net.http.HttpRequestParams;
import miuipub.util.IOUtils;
import org.apache.commons.cli.HelpFormatter;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.message.BasicHeader;

class SimpleMultipartEntity implements HttpEntity {

    /* renamed from: a  reason: collision with root package name */
    private static final char[] f2984a = "-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private String b;
    private String c;
    private ArrayList<Object> d;
    private long e;

    public Header getContentEncoding() {
        return null;
    }

    public boolean isChunked() {
        return false;
    }

    public boolean isRepeatable() {
        return false;
    }

    public boolean isStreaming() {
        return false;
    }

    public SimpleMultipartEntity(String str, Map<String, Object> map) throws IOException {
        StringBuilder sb = new StringBuilder();
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < 30; i++) {
            sb.append(f2984a[random.nextInt(f2984a.length)]);
        }
        this.b = sb.toString();
        this.c = str;
        this.d = new ArrayList<>();
        this.e = 0;
        byte[] bytes = ("\r\n--" + this.b + "\r\n").getBytes(this.c);
        byte[] bytes2 = ("\r\n--" + this.b + "--\r\n").getBytes(this.c);
        a(HelpFormatter.f + this.b + "\r\n");
        for (Map.Entry next : map.entrySet()) {
            Object value = next.getValue();
            if (value instanceof String) {
                a((String) next.getKey(), (String) value);
            } else if (value instanceof List) {
                List list = (List) value;
                String str2 = (String) next.getKey();
                for (int i2 = 0; i2 < list.size() - 1; i2++) {
                    a(str2, (String) list.get(i2));
                    a(bytes);
                }
                a(str2, (String) list.get(list.size() - 1));
            } else if (!(value instanceof HttpRequestParams.FileWrapper)) {
                throw new IOException("Unexpected parameters " + ((String) next.getKey()) + ":" + next.getValue());
            }
            a(bytes);
        }
        for (Map.Entry next2 : map.entrySet()) {
            Object value2 = next2.getValue();
            if (value2 instanceof HttpRequestParams.FileWrapper) {
                a((String) next2.getKey(), (HttpRequestParams.FileWrapper) value2);
                a(bytes);
            }
        }
        this.d.set(this.d.size() - 1, bytes2);
        this.e += 2;
    }

    private void a(String str, String str2) {
        try {
            a("Content-Disposition: form-data; name=\"" + str + "\"\r\n\r\n");
            a(str2);
        } catch (UnsupportedEncodingException unused) {
        }
    }

    private void a(String str, HttpRequestParams.FileWrapper fileWrapper) {
        String str2;
        try {
            a("Content-Disposition: form-data; name=\"" + str + "\"; filename=\"" + fileWrapper.c + "\"\r\n");
            if (fileWrapper.d != null) {
                if (fileWrapper.d.length() != 0) {
                    str2 = fileWrapper.d;
                    a("Content-Type: " + str2 + "\r\n");
                    a("Content-Transfer-Encoding: binary\r\n\r\n");
                    a(fileWrapper.f2979a, fileWrapper.b);
                }
            }
            str2 = "application/octet-stream";
            a("Content-Type: " + str2 + "\r\n");
            a("Content-Transfer-Encoding: binary\r\n\r\n");
            a(fileWrapper.f2979a, fileWrapper.b);
        } catch (UnsupportedEncodingException unused) {
        }
    }

    private void a(String str) throws UnsupportedEncodingException {
        a(str.getBytes(this.c));
    }

    private void a(byte[] bArr) {
        this.d.add(bArr);
        this.e += (long) bArr.length;
    }

    private void a(InputStream inputStream, long j) {
        this.d.add(inputStream);
        this.e += j;
    }

    public long getContentLength() {
        return this.e;
    }

    public Header getContentType() {
        return new BasicHeader("Content-Type", "multipart/form-data; boundary=" + this.b);
    }

    public InputStream getContent() throws IOException, IllegalStateException {
        return new EntityStream(this.d);
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        IOUtils.a(getContent(), outputStream);
    }

    public void consumeContent() throws IOException {
        Iterator<Object> it = this.d.iterator();
        while (it.hasNext()) {
            Object next = it.next();
            if (next instanceof InputStream) {
                IOUtils.a((InputStream) next);
            }
        }
        this.d.clear();
    }

    private static class EntityStream extends InputStream {

        /* renamed from: a  reason: collision with root package name */
        List<Object> f2985a;
        int b = 0;
        int c = 0;

        public EntityStream(List<Object> list) {
            this.f2985a = list;
        }

        public int read(byte[] bArr) throws IOException {
            return read(bArr, 0, bArr.length);
        }

        public int read(byte[] bArr, int i, int i2) throws IOException {
            int i3 = 0;
            while (true) {
                if (this.b >= this.f2985a.size() && i2 != 0) {
                    break;
                }
                Object obj = this.f2985a.get(this.b);
                if (obj instanceof byte[]) {
                    byte[] bArr2 = (byte[]) obj;
                    if (bArr2.length == this.c) {
                        this.b++;
                        this.c = 0;
                    } else {
                        int min = Math.min(bArr2.length - this.c, i2);
                        System.arraycopy(bArr2, this.c, bArr, i, min);
                        this.c += min;
                        i += min;
                        i2 -= min;
                        i3 += min;
                        if (bArr2.length == this.c) {
                            this.b++;
                            this.c = 0;
                        }
                    }
                } else if (obj instanceof InputStream) {
                    int read = ((InputStream) obj).read(bArr, i, i2);
                    if (read == 0) {
                        break;
                    } else if (read == -1) {
                        this.b++;
                        this.c = 0;
                    } else {
                        i += read;
                        i2 -= read;
                        i3 += read;
                    }
                } else {
                    throw new IOException("Unexpected value");
                }
            }
            if (i3 == 0 && this.b == this.f2985a.size()) {
                return -1;
            }
            return i3;
        }

        public int read() throws IOException {
            int i = -1;
            while (this.b < this.f2985a.size()) {
                Object obj = this.f2985a.get(this.b);
                if (obj instanceof byte[]) {
                    byte[] bArr = (byte[]) obj;
                    this.c++;
                    if (this.c < bArr.length) {
                        return bArr[this.c];
                    }
                    this.b++;
                    this.c = 0;
                } else if (obj instanceof InputStream) {
                    i = ((InputStream) obj).read();
                    if (i >= 0) {
                        return i;
                    }
                    this.b++;
                    this.c = 0;
                } else {
                    throw new IOException("Unexpected value");
                }
            }
            return i;
        }
    }
}
