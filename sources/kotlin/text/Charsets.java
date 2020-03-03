package kotlin.text;

import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\u00048G¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\u00048G¢\u0006\u0006\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u000e\u001a\u00020\u00048G¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u000bR\u0010\u0010\u0010\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lkotlin/text/Charsets;", "", "()V", "ISO_8859_1", "Ljava/nio/charset/Charset;", "US_ASCII", "UTF_16", "UTF_16BE", "UTF_16LE", "UTF_32", "UTF32", "()Ljava/nio/charset/Charset;", "UTF_32BE", "UTF32_BE", "UTF_32LE", "UTF32_LE", "UTF_8", "utf_32", "utf_32be", "utf_32le", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
public final class Charsets {
    @NotNull
    @JvmField

    /* renamed from: a  reason: collision with root package name */
    public static final Charset f2900a;
    @NotNull
    @JvmField
    public static final Charset b;
    @NotNull
    @JvmField
    public static final Charset c;
    @NotNull
    @JvmField
    public static final Charset d;
    @NotNull
    @JvmField
    public static final Charset e;
    @NotNull
    @JvmField
    public static final Charset f;
    public static final Charsets g = new Charsets();
    private static Charset h;
    private static Charset i;
    private static Charset j;

    static {
        Charset forName = Charset.forName("UTF-8");
        Intrinsics.b(forName, "Charset.forName(\"UTF-8\")");
        f2900a = forName;
        Charset forName2 = Charset.forName("UTF-16");
        Intrinsics.b(forName2, "Charset.forName(\"UTF-16\")");
        b = forName2;
        Charset forName3 = Charset.forName("UTF-16BE");
        Intrinsics.b(forName3, "Charset.forName(\"UTF-16BE\")");
        c = forName3;
        Charset forName4 = Charset.forName("UTF-16LE");
        Intrinsics.b(forName4, "Charset.forName(\"UTF-16LE\")");
        d = forName4;
        Charset forName5 = Charset.forName("US-ASCII");
        Intrinsics.b(forName5, "Charset.forName(\"US-ASCII\")");
        e = forName5;
        Charset forName6 = Charset.forName("ISO-8859-1");
        Intrinsics.b(forName6, "Charset.forName(\"ISO-8859-1\")");
        f = forName6;
    }

    private Charsets() {
    }

    @NotNull
    @JvmName(name = "UTF32")
    public final Charset a() {
        Charset charset = h;
        if (charset != null) {
            return charset;
        }
        Charsets charsets = this;
        Charset forName = Charset.forName("UTF-32");
        Intrinsics.b(forName, "Charset.forName(\"UTF-32\")");
        h = forName;
        return forName;
    }

    @NotNull
    @JvmName(name = "UTF32_LE")
    public final Charset b() {
        Charset charset = i;
        if (charset != null) {
            return charset;
        }
        Charsets charsets = this;
        Charset forName = Charset.forName("UTF-32LE");
        Intrinsics.b(forName, "Charset.forName(\"UTF-32LE\")");
        i = forName;
        return forName;
    }

    @NotNull
    @JvmName(name = "UTF32_BE")
    public final Charset c() {
        Charset charset = j;
        if (charset != null) {
            return charset;
        }
        Charsets charsets = this;
        Charset forName = Charset.forName("UTF-32BE");
        Intrinsics.b(forName, "Charset.forName(\"UTF-32BE\")");
        j = forName;
        return forName;
    }
}
