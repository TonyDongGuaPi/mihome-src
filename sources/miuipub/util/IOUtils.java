package miuipub.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import miuipub.util.Pools;

public class IOUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final int f3018a = 4096;
    private static final ThreadLocal<SoftReference<byte[]>> b = new ThreadLocal<>();
    private static final ThreadLocal<SoftReference<char[]>> c = new ThreadLocal<>();
    private static final Pools.Pool<ByteArrayOutputStream> d = Pools.b(new Pools.Manager<ByteArrayOutputStream>() {
        /* renamed from: a */
        public ByteArrayOutputStream b() {
            return new ByteArrayOutputStream();
        }

        public void a(ByteArrayOutputStream byteArrayOutputStream) {
            byteArrayOutputStream.reset();
        }
    }, 2);
    private static final Pools.Pool<CharArrayWriter> e = Pools.b(new Pools.Manager<CharArrayWriter>() {
        /* renamed from: a */
        public CharArrayWriter b() {
            return new CharArrayWriter();
        }

        public void a(CharArrayWriter charArrayWriter) {
            charArrayWriter.reset();
        }
    }, 2);
    private static final Pools.Pool<StringWriter> f = Pools.b(new Pools.Manager<StringWriter>() {
        /* renamed from: a */
        public StringWriter b() {
            return new StringWriter();
        }

        public void a(StringWriter stringWriter) {
            stringWriter.getBuffer().setLength(0);
        }
    }, 2);
    private static final String g;

    static {
        StringWriter b2 = f.b();
        PrintWriter printWriter = new PrintWriter(b2);
        printWriter.println();
        printWriter.flush();
        g = b2.toString();
        printWriter.close();
        f.b(b2);
    }

    protected IOUtils() throws InstantiationException {
        throw new InstantiationException("Cannot instantiate utility class");
    }

    public static void a(Reader reader) {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException unused) {
            }
        }
    }

    public static void a(Writer writer) {
        if (writer != null) {
            try {
                writer.close();
            } catch (IOException unused) {
            }
        }
    }

    public static void a(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException unused) {
            }
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:1|2|3|4|6) */
    /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0005 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(java.io.OutputStream r0) {
        /*
            if (r0 == 0) goto L_0x0008
            r0.flush()     // Catch:{ IOException -> 0x0005 }
        L_0x0005:
            r0.close()     // Catch:{ IOException -> 0x0008 }
        L_0x0008:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: miuipub.util.IOUtils.a(java.io.OutputStream):void");
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    public static byte[] b(InputStream inputStream) throws IOException {
        ByteArrayOutputStream b2 = d.b();
        a(inputStream, (OutputStream) b2);
        byte[] byteArray = b2.toByteArray();
        d.b(b2);
        return byteArray;
    }

    public static byte[] b(Reader reader) throws IOException {
        ByteArrayOutputStream b2 = d.b();
        a(reader, (OutputStream) b2);
        byte[] byteArray = b2.toByteArray();
        d.b(b2);
        return byteArray;
    }

    public static byte[] a(Reader reader, String str) throws IOException {
        ByteArrayOutputStream b2 = d.b();
        a(reader, (OutputStream) b2, str);
        byte[] byteArray = b2.toByteArray();
        d.b(b2);
        return byteArray;
    }

    public static char[] c(InputStream inputStream) throws IOException {
        CharArrayWriter b2 = e.b();
        a(inputStream, (Writer) b2);
        char[] charArray = b2.toCharArray();
        e.b(b2);
        return charArray;
    }

    public static char[] a(InputStream inputStream, String str) throws IOException {
        CharArrayWriter b2 = e.b();
        a(inputStream, (Writer) b2, str);
        char[] charArray = b2.toCharArray();
        e.b(b2);
        return charArray;
    }

    public static char[] c(Reader reader) throws IOException {
        CharArrayWriter b2 = e.b();
        a(reader, (Writer) b2);
        char[] charArray = b2.toCharArray();
        e.b(b2);
        return charArray;
    }

    public static String d(InputStream inputStream) throws IOException {
        StringWriter b2 = f.b();
        a(inputStream, (Writer) b2);
        String stringWriter = b2.toString();
        f.b(b2);
        return stringWriter;
    }

    public static String b(InputStream inputStream, String str) throws IOException {
        StringWriter b2 = f.b();
        a(inputStream, (Writer) b2, str);
        String stringWriter = b2.toString();
        f.b(b2);
        return stringWriter;
    }

    public static String d(Reader reader) throws IOException {
        StringWriter b2 = f.b();
        a(reader, (Writer) b2);
        String stringWriter = b2.toString();
        f.b(b2);
        return stringWriter;
    }

    public static List<String> e(InputStream inputStream) throws IOException {
        return e((Reader) new InputStreamReader(inputStream));
    }

    public static List<String> c(InputStream inputStream, String str) throws IOException {
        return e((Reader) (str == null || str.length() == 0) ? new InputStreamReader(inputStream) : new InputStreamReader(inputStream, str));
    }

    public static List<String> e(Reader reader) throws IOException {
        BufferedReader bufferedReader = reader instanceof BufferedReader ? (BufferedReader) reader : new BufferedReader(reader);
        ArrayList arrayList = new ArrayList();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                return arrayList;
            }
            arrayList.add(readLine);
        }
    }

    public static InputStream a(String str) {
        return new ByteArrayInputStream(str.getBytes());
    }

    public static InputStream a(String str, String str2) throws UnsupportedEncodingException {
        return new ByteArrayInputStream((str2 == null || str2.length() == 0) ? str.getBytes() : str.getBytes(str2));
    }

    public static void a(OutputStream outputStream, byte[] bArr) throws IOException {
        if (bArr != null) {
            outputStream.write(bArr);
        }
    }

    public static void a(Writer writer, byte[] bArr) throws IOException {
        if (bArr != null) {
            writer.write(new String(bArr));
        }
    }

    public static void a(Writer writer, byte[] bArr, String str) throws IOException {
        if (bArr != null) {
            writer.write((str == null || str.length() == 0) ? new String(bArr) : new String(bArr, str));
        }
    }

    public static void a(Writer writer, char[] cArr) throws IOException {
        if (cArr != null) {
            writer.write(cArr);
        }
    }

    public static void a(OutputStream outputStream, char[] cArr) throws IOException {
        if (cArr != null) {
            outputStream.write(new String(cArr).getBytes());
        }
    }

    public static void a(OutputStream outputStream, char[] cArr, String str) throws IOException {
        byte[] bArr;
        if (cArr != null) {
            if (str == null || str.length() == 0) {
                bArr = new String(cArr).getBytes();
            } else {
                bArr = new String(cArr).getBytes(str);
            }
            outputStream.write(bArr);
        }
    }

    public static void a(Writer writer, String str) throws IOException {
        if (str != null) {
            writer.write(str);
        }
    }

    public static void a(OutputStream outputStream, String str) throws IOException {
        if (str != null) {
            outputStream.write(str.getBytes());
        }
    }

    public static void a(OutputStream outputStream, String str, String str2) throws IOException {
        if (str != null) {
            outputStream.write((str2 == null || str2.length() == 0) ? str.getBytes() : str.getBytes(str2));
        }
    }

    public static void a(OutputStream outputStream, Collection<Object> collection, String str) throws IOException {
        if (collection != null) {
            if (str == null) {
                str = g;
            }
            for (Object next : collection) {
                if (next != null) {
                    outputStream.write(next.toString().getBytes());
                }
                outputStream.write(str.getBytes());
            }
        }
    }

    public static void a(OutputStream outputStream, Collection<Object> collection, String str, String str2) throws IOException {
        if (collection != null) {
            if (str == null) {
                str = g;
            }
            for (Object next : collection) {
                if (next != null) {
                    outputStream.write(next.toString().getBytes(str2));
                }
                outputStream.write(str.getBytes(str2));
            }
        }
    }

    public static void a(Writer writer, Collection<Object> collection, String str) throws IOException {
        if (collection != null) {
            if (str == null) {
                str = g;
            }
            for (Object next : collection) {
                if (next != null) {
                    writer.write(next.toString());
                }
                writer.write(str);
            }
        }
    }

    public static long a(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] a2 = a();
        long j = 0;
        while (true) {
            int read = inputStream.read(a2);
            if (read != -1) {
                outputStream.write(a2, 0, read);
                j += (long) read;
            } else {
                outputStream.flush();
                return j;
            }
        }
    }

    public static void a(InputStream inputStream, Writer writer) throws IOException {
        a((Reader) new InputStreamReader(inputStream), writer);
    }

    public static void a(InputStream inputStream, Writer writer, String str) throws IOException {
        a((Reader) (str == null || str.length() == 0) ? new InputStreamReader(inputStream) : new InputStreamReader(inputStream, str), writer);
    }

    public static void a(Reader reader, OutputStream outputStream) throws IOException {
        a(reader, (Writer) new OutputStreamWriter(outputStream));
    }

    public static void a(Reader reader, OutputStream outputStream, String str) throws IOException {
        a(reader, (Writer) (str == null || str.length() == 0) ? new OutputStreamWriter(outputStream) : new OutputStreamWriter(outputStream, str));
    }

    public static long a(Reader reader, Writer writer) throws IOException {
        char[] b2 = b();
        long j = 0;
        while (true) {
            int read = reader.read(b2);
            if (read != -1) {
                writer.write(b2, 0, read);
                j += (long) read;
            } else {
                writer.flush();
                return j;
            }
        }
    }

    private static byte[] a() {
        SoftReference softReference = b.get();
        byte[] bArr = softReference != null ? (byte[]) softReference.get() : null;
        if (bArr != null) {
            return bArr;
        }
        byte[] bArr2 = new byte[4096];
        b.set(new SoftReference(bArr2));
        return bArr2;
    }

    private static char[] b() {
        SoftReference softReference = c.get();
        char[] cArr = softReference != null ? (char[]) softReference.get() : null;
        if (cArr != null) {
            return cArr;
        }
        char[] cArr2 = new char[4096];
        c.set(new SoftReference(cArr2));
        return cArr2;
    }
}
