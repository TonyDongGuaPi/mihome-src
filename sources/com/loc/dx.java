package com.loc;

import com.mobikwik.sdk.lib.Constants;
import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;
import kotlin.text.Typography;
import org.xmlpull.v1.XmlSerializer;

final class dx implements XmlSerializer {

    /* renamed from: a  reason: collision with root package name */
    private static final String[] f6570a = {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, "&quot;", null, null, null, "&amp;", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, "&lt;", null, "&gt;", null};
    private final char[] b = new char[8192];
    private int c;
    private Writer d;
    private OutputStream e;
    private CharsetEncoder f;
    private ByteBuffer g = ByteBuffer.allocate(8192);
    private boolean h;

    dx() {
    }

    private void a() throws IOException {
        int position = this.g.position();
        if (position > 0) {
            this.g.flip();
            this.e.write(this.g.array(), 0, position);
            this.g.clear();
        }
    }

    private void a(char c2) throws IOException {
        int i = this.c;
        if (i >= 8191) {
            flush();
            i = this.c;
        }
        this.b[i] = c2;
        this.c = i + 1;
    }

    private void a(String str) throws IOException {
        a(str, 0, str.length());
    }

    private void a(String str, int i, int i2) throws IOException {
        if (i2 > 8192) {
            int i3 = i2 + i;
            while (i < i3) {
                int i4 = i + 8192;
                a(str, i, i4 < i3 ? 8192 : i3 - i);
                i = i4;
            }
            return;
        }
        int i5 = this.c;
        if (i5 + i2 > 8192) {
            flush();
            i5 = this.c;
        }
        str.getChars(i, i + i2, this.b, i5);
        this.c = i5 + i2;
    }

    private void a(char[] cArr, int i, int i2) throws IOException {
        if (i2 > 8192) {
            int i3 = i2 + i;
            while (i < i3) {
                int i4 = i + 8192;
                a(cArr, i, i4 < i3 ? 8192 : i3 - i);
                i = i4;
            }
            return;
        }
        int i5 = this.c;
        if (i5 + i2 > 8192) {
            flush();
            i5 = this.c;
        }
        System.arraycopy(cArr, i, this.b, i5, i2);
        this.c = i5 + i2;
    }

    private void b(String str) throws IOException {
        String str2;
        int length = str.length();
        char length2 = (char) f6570a.length;
        String[] strArr = f6570a;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            char charAt = str.charAt(i);
            if (charAt < length2 && (str2 = strArr[charAt]) != null) {
                if (i2 < i) {
                    a(str, i2, i - i2);
                }
                i2 = i + 1;
                a(str2);
            }
            i++;
        }
        if (i2 < i) {
            a(str, i2, i - i2);
        }
    }

    public final XmlSerializer attribute(String str, String str2, String str3) throws IOException, IllegalArgumentException, IllegalStateException {
        a(' ');
        if (str != null) {
            a(str);
            a((char) Operators.CONDITION_IF_MIDDLE);
        }
        a(str2);
        a("=\"");
        b(str3);
        a('\"');
        return this;
    }

    public final void cdsect(String str) throws IOException, IllegalArgumentException, IllegalStateException {
        throw new UnsupportedOperationException();
    }

    public final void comment(String str) throws IOException, IllegalArgumentException, IllegalStateException {
        throw new UnsupportedOperationException();
    }

    public final void docdecl(String str) throws IOException, IllegalArgumentException, IllegalStateException {
        throw new UnsupportedOperationException();
    }

    public final void endDocument() throws IOException, IllegalArgumentException, IllegalStateException {
        flush();
    }

    public final XmlSerializer endTag(String str, String str2) throws IOException, IllegalArgumentException, IllegalStateException {
        String str3;
        if (this.h) {
            str3 = " />\n";
        } else {
            a("</");
            if (str != null) {
                a(str);
                a((char) Operators.CONDITION_IF_MIDDLE);
            }
            a(str2);
            str3 = ">\n";
        }
        a(str3);
        this.h = false;
        return this;
    }

    public final void entityRef(String str) throws IOException, IllegalArgumentException, IllegalStateException {
        throw new UnsupportedOperationException();
    }

    public final void flush() throws IOException {
        if (this.c > 0) {
            if (this.e != null) {
                CharBuffer wrap = CharBuffer.wrap(this.b, 0, this.c);
                CharsetEncoder charsetEncoder = this.f;
                ByteBuffer byteBuffer = this.g;
                while (true) {
                    CoderResult encode = charsetEncoder.encode(wrap, byteBuffer, true);
                    if (!encode.isError()) {
                        if (!encode.isOverflow()) {
                            a();
                            this.e.flush();
                            break;
                        }
                        a();
                        charsetEncoder = this.f;
                        byteBuffer = this.g;
                    } else {
                        throw new IOException(encode.toString());
                    }
                }
            } else {
                this.d.write(this.b, 0, this.c);
                this.d.flush();
            }
            this.c = 0;
        }
    }

    public final int getDepth() {
        throw new UnsupportedOperationException();
    }

    public final boolean getFeature(String str) {
        throw new UnsupportedOperationException();
    }

    public final String getName() {
        throw new UnsupportedOperationException();
    }

    public final String getNamespace() {
        throw new UnsupportedOperationException();
    }

    public final String getPrefix(String str, boolean z) throws IllegalArgumentException {
        throw new UnsupportedOperationException();
    }

    public final Object getProperty(String str) {
        throw new UnsupportedOperationException();
    }

    public final void ignorableWhitespace(String str) throws IOException, IllegalArgumentException, IllegalStateException {
        throw new UnsupportedOperationException();
    }

    public final void processingInstruction(String str) throws IOException, IllegalArgumentException, IllegalStateException {
        throw new UnsupportedOperationException();
    }

    public final void setFeature(String str, boolean z) throws IllegalArgumentException, IllegalStateException {
        if (!str.equals("http://xmlpull.org/v1/doc/features.html#indent-output")) {
            throw new UnsupportedOperationException();
        }
    }

    public final void setOutput(OutputStream outputStream, String str) throws IOException, IllegalArgumentException, IllegalStateException {
        if (outputStream != null) {
            try {
                this.f = Charset.forName(str).newEncoder();
                this.e = outputStream;
            } catch (IllegalCharsetNameException e2) {
                throw ((UnsupportedEncodingException) new UnsupportedEncodingException(str).initCause(e2));
            } catch (UnsupportedCharsetException e3) {
                throw ((UnsupportedEncodingException) new UnsupportedEncodingException(str).initCause(e3));
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    public final void setOutput(Writer writer) throws IOException, IllegalArgumentException, IllegalStateException {
        this.d = writer;
    }

    public final void setPrefix(String str, String str2) throws IOException, IllegalArgumentException, IllegalStateException {
        throw new UnsupportedOperationException();
    }

    public final void setProperty(String str, Object obj) throws IllegalArgumentException, IllegalStateException {
        throw new UnsupportedOperationException();
    }

    public final void startDocument(String str, Boolean bool) throws IOException, IllegalArgumentException, IllegalStateException {
        StringBuilder sb = new StringBuilder("<?xml version='1.0' encoding='utf-8' standalone='");
        sb.append(bool.booleanValue() ? Constants.YES : "no");
        sb.append("' ?>\n");
        a(sb.toString());
    }

    public final XmlSerializer startTag(String str, String str2) throws IOException, IllegalArgumentException, IllegalStateException {
        if (this.h) {
            a(">\n");
        }
        a((char) Typography.d);
        if (str != null) {
            a(str);
            a((char) Operators.CONDITION_IF_MIDDLE);
        }
        a(str2);
        this.h = true;
        return this;
    }

    public final XmlSerializer text(String str) throws IOException, IllegalArgumentException, IllegalStateException {
        if (this.h) {
            a(">");
            this.h = false;
        }
        b(str);
        return this;
    }

    public final XmlSerializer text(char[] cArr, int i, int i2) throws IOException, IllegalArgumentException, IllegalStateException {
        String str;
        if (this.h) {
            a(">");
            this.h = false;
        }
        char length = (char) f6570a.length;
        String[] strArr = f6570a;
        int i3 = i2 + i;
        int i4 = i;
        while (i < i3) {
            char c2 = cArr[i];
            if (c2 < length && (str = strArr[c2]) != null) {
                if (i4 < i) {
                    a(cArr, i4, i - i4);
                }
                i4 = i + 1;
                a(str);
            }
            i++;
        }
        if (i4 < i) {
            a(cArr, i4, i - i4);
        }
        return this;
    }
}
