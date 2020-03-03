package com.xiaomi.smarthome.fastvideo;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class IOUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final char f15883a = '/';
    public static final char b = '\\';
    public static final char c = File.separatorChar;
    public static final String d = "\n";
    public static final String e = "\r\n";
    public static final String f;
    private static final int g = 4096;

    static {
        StringWriter stringWriter = new StringWriter(4);
        new PrintWriter(stringWriter).println();
        f = stringWriter.toString();
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

    public static void a(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException unused) {
            }
        }
    }

    public static byte[] b(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        a(inputStream, (OutputStream) byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] b(Reader reader) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        a(reader, (OutputStream) byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] a(Reader reader, String str) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        a(reader, (OutputStream) byteArrayOutputStream, str);
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] a(String str) throws IOException {
        return str.getBytes();
    }

    public static char[] c(InputStream inputStream) throws IOException {
        CharArrayWriter charArrayWriter = new CharArrayWriter();
        a(inputStream, (Writer) charArrayWriter);
        return charArrayWriter.toCharArray();
    }

    public static char[] a(InputStream inputStream, String str) throws IOException {
        CharArrayWriter charArrayWriter = new CharArrayWriter();
        a(inputStream, (Writer) charArrayWriter, str);
        return charArrayWriter.toCharArray();
    }

    public static char[] c(Reader reader) throws IOException {
        CharArrayWriter charArrayWriter = new CharArrayWriter();
        a(reader, (Writer) charArrayWriter);
        return charArrayWriter.toCharArray();
    }

    public static String d(InputStream inputStream) throws IOException {
        StringWriter stringWriter = new StringWriter();
        a(inputStream, (Writer) stringWriter);
        return stringWriter.toString();
    }

    public static String b(InputStream inputStream, String str) throws IOException {
        StringWriter stringWriter = new StringWriter();
        a(inputStream, (Writer) stringWriter, str);
        return stringWriter.toString();
    }

    public static String d(Reader reader) throws IOException {
        StringWriter stringWriter = new StringWriter();
        a(reader, (Writer) stringWriter);
        return stringWriter.toString();
    }

    public static String a(byte[] bArr) throws IOException {
        return new String(bArr);
    }

    public static String a(byte[] bArr, String str) throws IOException {
        if (str == null) {
            return new String(bArr);
        }
        return new String(bArr, str);
    }

    public static List e(InputStream inputStream) throws IOException {
        return e((Reader) new InputStreamReader(inputStream));
    }

    public static List c(InputStream inputStream, String str) throws IOException {
        if (str == null) {
            return e(inputStream);
        }
        return e((Reader) new InputStreamReader(inputStream, str));
    }

    public static List e(Reader reader) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(reader);
        ArrayList arrayList = new ArrayList();
        for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
            arrayList.add(readLine);
        }
        return arrayList;
    }

    public static InputStream b(String str) {
        return new ByteArrayInputStream(str.getBytes());
    }

    public static InputStream a(String str, String str2) throws IOException {
        return new ByteArrayInputStream(str2 != null ? str.getBytes(str2) : str.getBytes());
    }

    public static void a(byte[] bArr, OutputStream outputStream) throws IOException {
        if (bArr != null) {
            outputStream.write(bArr);
        }
    }

    public static void a(byte[] bArr, Writer writer) throws IOException {
        if (bArr != null) {
            writer.write(new String(bArr));
        }
    }

    public static void a(byte[] bArr, Writer writer, String str) throws IOException {
        if (bArr == null) {
            return;
        }
        if (str == null) {
            a(bArr, writer);
        } else {
            writer.write(new String(bArr, str));
        }
    }

    public static void a(char[] cArr, Writer writer) throws IOException {
        if (cArr != null) {
            writer.write(cArr);
        }
    }

    public static void a(char[] cArr, OutputStream outputStream) throws IOException {
        if (cArr != null) {
            outputStream.write(new String(cArr).getBytes());
        }
    }

    public static void a(char[] cArr, OutputStream outputStream, String str) throws IOException {
        if (cArr == null) {
            return;
        }
        if (str == null) {
            a(cArr, outputStream);
        } else {
            outputStream.write(new String(cArr).getBytes(str));
        }
    }

    public static void a(String str, Writer writer) throws IOException {
        if (str != null) {
            writer.write(str);
        }
    }

    public static void a(String str, OutputStream outputStream) throws IOException {
        if (str != null) {
            outputStream.write(str.getBytes());
        }
    }

    public static void a(String str, OutputStream outputStream, String str2) throws IOException {
        if (str == null) {
            return;
        }
        if (str2 == null) {
            a(str, outputStream);
        } else {
            outputStream.write(str.getBytes(str2));
        }
    }

    public static void a(StringBuffer stringBuffer, Writer writer) throws IOException {
        if (stringBuffer != null) {
            writer.write(stringBuffer.toString());
        }
    }

    public static void a(StringBuffer stringBuffer, OutputStream outputStream) throws IOException {
        if (stringBuffer != null) {
            outputStream.write(stringBuffer.toString().getBytes());
        }
    }

    public static void a(StringBuffer stringBuffer, OutputStream outputStream, String str) throws IOException {
        if (stringBuffer == null) {
            return;
        }
        if (str == null) {
            a(stringBuffer, outputStream);
        } else {
            outputStream.write(stringBuffer.toString().getBytes(str));
        }
    }

    public static void a(Collection collection, String str, OutputStream outputStream) throws IOException {
        if (collection != null) {
            if (str == null) {
                str = f;
            }
            for (Object next : collection) {
                if (next != null) {
                    outputStream.write(next.toString().getBytes());
                }
                outputStream.write(str.getBytes());
            }
        }
    }

    public static void a(Collection collection, String str, OutputStream outputStream, String str2) throws IOException {
        if (str2 == null) {
            a(collection, str, outputStream);
        } else if (collection != null) {
            if (str == null) {
                str = f;
            }
            for (Object next : collection) {
                if (next != null) {
                    outputStream.write(next.toString().getBytes(str2));
                }
                outputStream.write(str.getBytes(str2));
            }
        }
    }

    public static void a(Collection collection, String str, Writer writer) throws IOException {
        if (collection != null) {
            if (str == null) {
                str = f;
            }
            for (Object next : collection) {
                if (next != null) {
                    writer.write(next.toString());
                }
                writer.write(str);
            }
        }
    }

    public static int a(InputStream inputStream, OutputStream outputStream) throws IOException {
        long b2 = b(inputStream, outputStream);
        if (b2 > 2147483647L) {
            return -1;
        }
        return (int) b2;
    }

    public static long b(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[4096];
        long j = 0;
        while (true) {
            int read = inputStream.read(bArr);
            if (-1 == read) {
                return j;
            }
            outputStream.write(bArr, 0, read);
            j += (long) read;
        }
    }

    public static void a(InputStream inputStream, Writer writer) throws IOException {
        a((Reader) new InputStreamReader(inputStream), writer);
    }

    public static void a(InputStream inputStream, Writer writer, String str) throws IOException {
        if (str == null) {
            a(inputStream, writer);
        } else {
            a((Reader) new InputStreamReader(inputStream, str), writer);
        }
    }

    public static int a(Reader reader, Writer writer) throws IOException {
        long b2 = b(reader, writer);
        if (b2 > 2147483647L) {
            return -1;
        }
        return (int) b2;
    }

    public static long b(Reader reader, Writer writer) throws IOException {
        char[] cArr = new char[4096];
        long j = 0;
        while (true) {
            int read = reader.read(cArr);
            if (-1 == read) {
                return j;
            }
            writer.write(cArr, 0, read);
            j += (long) read;
        }
    }

    public static void a(Reader reader, OutputStream outputStream) throws IOException {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        a(reader, (Writer) outputStreamWriter);
        outputStreamWriter.flush();
    }

    public static void a(Reader reader, OutputStream outputStream, String str) throws IOException {
        if (str == null) {
            a(reader, outputStream);
            return;
        }
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, str);
        a(reader, (Writer) outputStreamWriter);
        outputStreamWriter.flush();
    }

    public static boolean a(InputStream inputStream, InputStream inputStream2) throws IOException {
        if (!(inputStream instanceof BufferedInputStream)) {
            inputStream = new BufferedInputStream(inputStream);
        }
        if (!(inputStream2 instanceof BufferedInputStream)) {
            inputStream2 = new BufferedInputStream(inputStream2);
        }
        for (int read = inputStream.read(); -1 != read; read = inputStream.read()) {
            if (read != inputStream2.read()) {
                return false;
            }
        }
        if (inputStream2.read() == -1) {
            return true;
        }
        return false;
    }

    public static boolean a(Reader reader, Reader reader2) throws IOException {
        if (!(reader instanceof BufferedReader)) {
            reader = new BufferedReader(reader);
        }
        if (!(reader2 instanceof BufferedReader)) {
            reader2 = new BufferedReader(reader2);
        }
        for (int read = reader.read(); -1 != read; read = reader.read()) {
            if (read != reader2.read()) {
                return false;
            }
        }
        if (reader2.read() == -1) {
            return true;
        }
        return false;
    }
}
