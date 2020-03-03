package com.imi.fastjson.parser;

import com.alibaba.fastjson.parser.JSONLexer;
import com.imi.fastjson.JSON;
import com.imi.fastjson.JSONException;
import com.imi.fastjson.util.Base64;
import com.imi.fastjson.util.IOUtils;
import java.io.CharArrayReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.lang.ref.SoftReference;

public final class JSONReaderScanner extends JSONLexer {
    public static final int u = 8192;
    private static final ThreadLocal<SoftReference<char[]>> v = new ThreadLocal<>();
    private Reader w;
    private char[] x;
    private int y;

    public JSONReaderScanner(String str) {
        this(str, JSON.DEFAULT_PARSER_FEATURE);
    }

    public JSONReaderScanner(String str, int i) {
        this((Reader) new StringReader(str), i);
    }

    public JSONReaderScanner(char[] cArr, int i) {
        this(cArr, i, JSON.DEFAULT_PARSER_FEATURE);
    }

    public JSONReaderScanner(Reader reader) {
        this(reader, JSON.DEFAULT_PARSER_FEATURE);
    }

    public JSONReaderScanner(Reader reader, int i) {
        this.w = reader;
        this.d = i;
        SoftReference softReference = v.get();
        if (softReference != null) {
            this.x = (char[]) softReference.get();
            v.set((Object) null);
        }
        if (this.x == null) {
            this.x = new char[8192];
        }
        try {
            this.y = reader.read(this.x);
            this.f = -1;
            n();
            if (this.e == 65279) {
                n();
            }
        } catch (IOException e) {
            throw new JSONException(e.getMessage(), e);
        }
    }

    public JSONReaderScanner(char[] cArr, int i, int i2) {
        this((Reader) new CharArrayReader(cArr, 0, i), i2);
    }

    public final char c(int i) {
        if (i >= this.y) {
            if (this.y != -1) {
                int i2 = this.y - this.f;
                if (i2 > 0) {
                    System.arraycopy(this.x, this.f, this.x, 0, i2);
                }
                try {
                    this.y = this.w.read(this.x, i2, this.x.length - i2);
                    if (this.y == 0) {
                        throw new JSONException("illegal stat, textLength is zero");
                    } else if (this.y == -1) {
                        return JSONLexer.EOI;
                    } else {
                        this.y += i2;
                        i -= this.f;
                        this.j -= this.f;
                        this.f = 0;
                    }
                } catch (IOException e) {
                    throw new JSONException(e.getMessage(), e);
                }
            } else if (i < this.i) {
                return this.x[i];
            } else {
                return JSONLexer.EOI;
            }
        }
        return this.x[i];
    }

    public final String a(int i, int i2, int i3, SymbolTable symbolTable) {
        return symbolTable.a(this.x, i, i2, i3);
    }

    public final char n() {
        int i = this.f + 1;
        this.f = i;
        if (i >= this.y) {
            if (this.y == -1) {
                return JSONLexer.EOI;
            }
            if (this.i > 0) {
                if (this.b == 4) {
                    System.arraycopy(this.x, this.x.length - this.i, this.x, 0, this.i);
                    this.j = this.i - 1;
                } else {
                    System.arraycopy(this.x, this.y - this.i, this.x, 0, this.i);
                    this.j = 0;
                }
            }
            i = this.i;
            this.f = i;
            try {
                this.y = this.w.read(this.x, this.f, this.x.length - this.f);
                if (this.y == 0) {
                    throw new JSONException("illegal stat, textLength is zero");
                } else if (this.y == -1) {
                    this.e = JSONLexer.EOI;
                    return JSONLexer.EOI;
                } else {
                    this.y += this.f;
                }
            } catch (IOException e) {
                throw new JSONException(e.getMessage(), e);
            }
        }
        char c = this.x[i];
        this.e = c;
        return c;
    }

    /* access modifiers changed from: protected */
    public final void a(int i, int i2, char[] cArr) {
        System.arraycopy(this.x, i, cArr, 0, i2);
    }

    public byte[] s() {
        return Base64.a(this.x, this.j + 1, this.i);
    }

    /* access modifiers changed from: protected */
    public final void a(int i, char[] cArr, int i2, int i3) {
        System.arraycopy(this.x, i, cArr, i2, i3);
    }

    public final String z() {
        if (!this.k) {
            return new String(this.x, this.j + 1, this.i);
        }
        return new String(this.h, 0, this.i);
    }

    public final String k() {
        char c = c((this.j + this.i) - 1);
        int i = this.i;
        if (c == 'L' || c == 'S' || c == 'B' || c == 'F' || c == 'D') {
            i--;
        }
        return new String(this.x, this.j, i);
    }

    public void close() {
        super.close();
        v.set(new SoftReference(this.x));
        this.x = null;
        IOUtils.a((Closeable) this.w);
    }

    public boolean l() {
        if (this.y == -1 || this.f == this.x.length) {
            return true;
        }
        return this.e == 26 && this.f + 1 == this.x.length;
    }
}
