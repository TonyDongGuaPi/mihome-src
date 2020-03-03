package kotlin.io;

import com.xiaomi.miot.support.monitor.core.tasks.MiotApmTask;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.internal.InlineOnly;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000X\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u0017\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\b\u001a\u0017\u0010\u0000\u001a\u00020\u0005*\u00020\u00062\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\b\u001a\u001c\u0010\u0007\u001a\u00020\b*\u00020\u00022\u0006\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u001a\u001e\u0010\n\u001a\u00020\u000b*\u00020\u00022\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000b0\r\u001a\u0010\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0010*\u00020\u0001\u001a\n\u0010\u0011\u001a\u00020\u0012*\u00020\u0013\u001a\u0010\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0015*\u00020\u0002\u001a\n\u0010\u0016\u001a\u00020\u000e*\u00020\u0002\u001a\u0017\u0010\u0016\u001a\u00020\u000e*\u00020\u00132\b\b\u0002\u0010\u0017\u001a\u00020\u0018H\b\u001a\r\u0010\u0019\u001a\u00020\u001a*\u00020\u000eH\b\u001a5\u0010\u001b\u001a\u0002H\u001c\"\u0004\b\u0000\u0010\u001c*\u00020\u00022\u0018\u0010\u001d\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u0010\u0012\u0004\u0012\u0002H\u001c0\rH\bø\u0001\u0000¢\u0006\u0002\u0010\u001f\u0002\b\n\u0006\b\u0011(\u001e0\u0001¨\u0006 "}, d2 = {"buffered", "Ljava/io/BufferedReader;", "Ljava/io/Reader;", "bufferSize", "", "Ljava/io/BufferedWriter;", "Ljava/io/Writer;", "copyTo", "", "out", "forEachLine", "", "action", "Lkotlin/Function1;", "", "lineSequence", "Lkotlin/sequences/Sequence;", "readBytes", "", "Ljava/net/URL;", "readLines", "", "readText", "charset", "Ljava/nio/charset/Charset;", "reader", "Ljava/io/StringReader;", "useLines", "T", "block", "Requires newer compiler version to be inlined correctly.", "(Ljava/io/Reader;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "kotlin-stdlib"}, k = 2, mv = {1, 1, 13})
@JvmName(name = "TextStreamsKt")
public final class TextStreamsKt {
    @InlineOnly
    private static final BufferedReader a(@NotNull Reader reader, int i) {
        return reader instanceof BufferedReader ? (BufferedReader) reader : new BufferedReader(reader, i);
    }

    @InlineOnly
    private static final BufferedWriter a(@NotNull Writer writer, int i) {
        return writer instanceof BufferedWriter ? (BufferedWriter) writer : new BufferedWriter(writer, i);
    }

    @NotNull
    public static final List<String> a(@NotNull Reader reader) {
        Intrinsics.f(reader, "receiver$0");
        ArrayList arrayList = new ArrayList();
        a(reader, (Function1<? super String, Unit>) new TextStreamsKt$readLines$1(arrayList));
        return arrayList;
    }

    public static final <T> T b(@NotNull Reader reader, @NotNull Function1<? super Sequence<String>, ? extends T> function1) {
        Throwable th;
        Intrinsics.f(reader, "receiver$0");
        Intrinsics.f(function1, MiotApmTask.j);
        Closeable bufferedReader = reader instanceof BufferedReader ? (BufferedReader) reader : new BufferedReader(reader, 8192);
        Throwable th2 = null;
        try {
            T invoke = function1.invoke(a((BufferedReader) bufferedReader));
            InlineMarker.b(1);
            if (PlatformImplementationsKt.a(1, 1, 0)) {
                CloseableKt.a(bufferedReader, th2);
            } else {
                bufferedReader.close();
            }
            InlineMarker.c(1);
            return invoke;
        } catch (Throwable unused) {
        }
        InlineMarker.c(1);
        throw th;
    }

    @InlineOnly
    private static final StringReader a(@NotNull String str) {
        return new StringReader(str);
    }

    @NotNull
    public static final Sequence<String> a(@NotNull BufferedReader bufferedReader) {
        Intrinsics.f(bufferedReader, "receiver$0");
        return SequencesKt.d(new LinesSequence(bufferedReader));
    }

    @NotNull
    public static final String b(@NotNull Reader reader) {
        Intrinsics.f(reader, "receiver$0");
        StringWriter stringWriter = new StringWriter();
        a(reader, stringWriter, 0, 2, (Object) null);
        String stringWriter2 = stringWriter.toString();
        Intrinsics.b(stringWriter2, "buffer.toString()");
        return stringWriter2;
    }

    public static /* synthetic */ long a(Reader reader, Writer writer, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 8192;
        }
        return a(reader, writer, i);
    }

    public static final long a(@NotNull Reader reader, @NotNull Writer writer, int i) {
        Intrinsics.f(reader, "receiver$0");
        Intrinsics.f(writer, "out");
        char[] cArr = new char[i];
        int read = reader.read(cArr);
        long j = 0;
        while (read >= 0) {
            writer.write(cArr, 0, read);
            j += (long) read;
            read = reader.read(cArr);
        }
        return j;
    }

    @InlineOnly
    private static final String a(@NotNull URL url, Charset charset) {
        return new String(a(url), charset);
    }

    @InlineOnly
    static /* synthetic */ String a(URL url, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.f2900a;
        }
        return new String(a(url), charset);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0025, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x001e, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0022, code lost:
        kotlin.io.CloseableKt.a(r3, r0);
     */
    @org.jetbrains.annotations.NotNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final byte[] a(@org.jetbrains.annotations.NotNull java.net.URL r3) {
        /*
            java.lang.String r0 = "receiver$0"
            kotlin.jvm.internal.Intrinsics.f(r3, r0)
            java.io.InputStream r3 = r3.openStream()
            java.io.Closeable r3 = (java.io.Closeable) r3
            r0 = 0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            r1 = r3
            java.io.InputStream r1 = (java.io.InputStream) r1     // Catch:{ Throwable -> 0x0020 }
            java.lang.String r2 = "it"
            kotlin.jvm.internal.Intrinsics.b(r1, r2)     // Catch:{ Throwable -> 0x0020 }
            byte[] r1 = kotlin.io.ByteStreamsKt.a((java.io.InputStream) r1)     // Catch:{ Throwable -> 0x0020 }
            kotlin.io.CloseableKt.a((java.io.Closeable) r3, (java.lang.Throwable) r0)
            return r1
        L_0x001e:
            r1 = move-exception
            goto L_0x0022
        L_0x0020:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x001e }
        L_0x0022:
            kotlin.io.CloseableKt.a((java.io.Closeable) r3, (java.lang.Throwable) r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.TextStreamsKt.a(java.net.URL):byte[]");
    }

    @InlineOnly
    static /* synthetic */ BufferedReader a(Reader reader, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 8192;
        }
        return reader instanceof BufferedReader ? (BufferedReader) reader : new BufferedReader(reader, i);
    }

    @InlineOnly
    static /* synthetic */ BufferedWriter a(Writer writer, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 8192;
        }
        return writer instanceof BufferedWriter ? (BufferedWriter) writer : new BufferedWriter(writer, i);
    }

    public static final void a(@NotNull Reader reader, @NotNull Function1<? super String, Unit> function1) {
        Throwable th;
        Intrinsics.f(reader, "receiver$0");
        Intrinsics.f(function1, "action");
        Closeable bufferedReader = reader instanceof BufferedReader ? (BufferedReader) reader : new BufferedReader(reader, 8192);
        Throwable th2 = null;
        try {
            Iterator<String> a2 = a((BufferedReader) bufferedReader).a();
            while (a2.hasNext()) {
                function1.invoke(a2.next());
            }
            Unit unit = Unit.f2693a;
            CloseableKt.a(bufferedReader, th2);
        } catch (Throwable th3) {
            CloseableKt.a(bufferedReader, th);
            throw th3;
        }
    }
}