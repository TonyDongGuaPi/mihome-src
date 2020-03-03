package org.mp4parser;

import com.taobao.weex.el.parse.Operators;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import org.mp4parser.boxes.iso14496.part12.MovieBox;
import org.mp4parser.support.DoNotParseDetail;
import org.mp4parser.support.Logger;

@DoNotParseDetail
public class IsoFile extends BasicContainer implements Closeable {

    /* renamed from: a  reason: collision with root package name */
    private static Logger f3733a = Logger.a(IsoFile.class);
    private ReadableByteChannel b;

    public IsoFile(String str) throws IOException {
        this(new FileInputStream(str).getChannel(), new PropertyBoxParserImpl(new String[0]));
    }

    public IsoFile(File file) throws IOException {
        this(new FileInputStream(file).getChannel(), new PropertyBoxParserImpl(new String[0]));
    }

    public IsoFile(ReadableByteChannel readableByteChannel) throws IOException {
        this(readableByteChannel, new PropertyBoxParserImpl(new String[0]));
    }

    public IsoFile(ReadableByteChannel readableByteChannel, BoxParser boxParser) throws IOException {
        this.b = readableByteChannel;
        a(readableByteChannel, -1, boxParser);
    }

    public static byte[] a(String str) {
        byte[] bArr = new byte[4];
        if (str != null) {
            for (int i = 0; i < Math.min(4, str.length()); i++) {
                bArr[i] = (byte) str.charAt(i);
            }
        }
        return bArr;
    }

    public static String a(byte[] bArr) {
        byte[] bArr2 = new byte[4];
        if (bArr != null) {
            System.arraycopy(bArr, 0, bArr2, 0, Math.min(bArr.length, 4));
        }
        try {
            return new String(bArr2, "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            throw new Error("Required character encoding is missing", e);
        }
    }

    public long c() {
        return b();
    }

    public MovieBox d() {
        for (Box next : a()) {
            if (next instanceof MovieBox) {
                return (MovieBox) next;
            }
        }
        return null;
    }

    public void b(WritableByteChannel writableByteChannel) throws IOException {
        a(writableByteChannel);
    }

    public void close() throws IOException {
        this.b.close();
    }

    public String toString() {
        return "model(" + this.b.toString() + Operators.BRACKET_END_STR;
    }
}
