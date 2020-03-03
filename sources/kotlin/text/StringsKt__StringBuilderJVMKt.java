package kotlin.text;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\f\n\u0002\u0010\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\u0010\u0005\n\u0002\u0010\u0019\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\u0010\n\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u001a\u0012\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u0002\u001a\u001d\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\b\u001a\u001f\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0005H\b\u001a\u0012\u0010\u0000\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u0007\u001a\u001f\u0010\u0000\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\b\u0010\u0003\u001a\u0004\u0018\u00010\bH\b\u001a\u001f\u0010\u0000\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\b\u0010\u0003\u001a\u0004\u0018\u00010\tH\b\u001a\u001d\u0010\u0000\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\u0006\u0010\u0003\u001a\u00020\nH\b\u001a\u001d\u0010\u0000\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\u0006\u0010\u0003\u001a\u00020\u000bH\b\u001a\u001d\u0010\u0000\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\u0006\u0010\u0003\u001a\u00020\u0004H\b\u001a\u001d\u0010\u0000\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\u0006\u0010\u0003\u001a\u00020\fH\b\u001a\u001f\u0010\u0000\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\b\u0010\u0003\u001a\u0004\u0018\u00010\u0005H\b\u001a\u001d\u0010\u0000\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\u0006\u0010\u0003\u001a\u00020\rH\b\u001a\u001d\u0010\u0000\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\u0006\u0010\u0003\u001a\u00020\u000eH\b\u001a\u001d\u0010\u0000\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\u0006\u0010\u0003\u001a\u00020\u000fH\b\u001a\u001d\u0010\u0000\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\u0006\u0010\u0003\u001a\u00020\u0010H\b\u001a\u001d\u0010\u0000\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\u0006\u0010\u0003\u001a\u00020\u0011H\b\u001a\u001f\u0010\u0000\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\b\u0010\u0003\u001a\u0004\u0018\u00010\u0012H\b\u001a%\u0010\u0000\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\u000e\u0010\u0003\u001a\n\u0018\u00010\u0006j\u0004\u0018\u0001`\u0007H\b\u001a\u0014\u0010\u0013\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u0007H\u0007\u001a!\u0010\u0014\u001a\u00020\u0015*\u00060\u0006j\u0002`\u00072\u0006\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u0004H\n¨\u0006\u0017"}, d2 = {"appendln", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "value", "", "", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "Ljava/lang/StringBuffer;", "", "", "", "", "", "", "", "", "", "", "clear", "set", "", "index", "kotlin-stdlib"}, k = 5, mv = {1, 1, 13}, xi = 1, xs = "kotlin/text/StringsKt")
class StringsKt__StringBuilderJVMKt extends StringsKt__RegexExtensionsKt {
    @InlineOnly
    private static final void a(@NotNull StringBuilder sb, int i, char c) {
        Intrinsics.f(sb, "receiver$0");
        sb.setCharAt(i, c);
    }

    @NotNull
    @SinceKotlin(version = "1.3")
    public static final StringBuilder a(@NotNull StringBuilder sb) {
        Intrinsics.f(sb, "receiver$0");
        sb.setLength(0);
        return sb;
    }

    @NotNull
    public static final Appendable a(@NotNull Appendable appendable) {
        Intrinsics.f(appendable, "receiver$0");
        Appendable append = appendable.append(SystemProperties.f2912a);
        Intrinsics.b(append, "append(SystemProperties.LINE_SEPARATOR)");
        return append;
    }

    @InlineOnly
    private static final Appendable a(@NotNull Appendable appendable, CharSequence charSequence) {
        Appendable append = appendable.append(charSequence);
        Intrinsics.b(append, "append(value)");
        return StringsKt.a(append);
    }

    @InlineOnly
    private static final Appendable a(@NotNull Appendable appendable, char c) {
        Appendable append = appendable.append(c);
        Intrinsics.b(append, "append(value)");
        return StringsKt.a(append);
    }

    @NotNull
    public static final StringBuilder b(@NotNull StringBuilder sb) {
        Intrinsics.f(sb, "receiver$0");
        sb.append(SystemProperties.f2912a);
        Intrinsics.b(sb, "append(SystemProperties.LINE_SEPARATOR)");
        return sb;
    }

    @InlineOnly
    private static final StringBuilder a(@NotNull StringBuilder sb, StringBuffer stringBuffer) {
        sb.append(stringBuffer);
        Intrinsics.b(sb, "append(value)");
        return StringsKt.b(sb);
    }

    @InlineOnly
    private static final StringBuilder a(@NotNull StringBuilder sb, CharSequence charSequence) {
        sb.append(charSequence);
        Intrinsics.b(sb, "append(value)");
        return StringsKt.b(sb);
    }

    @InlineOnly
    private static final StringBuilder a(@NotNull StringBuilder sb, String str) {
        sb.append(str);
        Intrinsics.b(sb, "append(value)");
        return StringsKt.b(sb);
    }

    @InlineOnly
    private static final StringBuilder a(@NotNull StringBuilder sb, Object obj) {
        sb.append(obj);
        Intrinsics.b(sb, "append(value)");
        return StringsKt.b(sb);
    }

    @InlineOnly
    private static final StringBuilder a(@NotNull StringBuilder sb, StringBuilder sb2) {
        sb.append(sb2);
        Intrinsics.b(sb, "append(value)");
        return StringsKt.b(sb);
    }

    @InlineOnly
    private static final StringBuilder a(@NotNull StringBuilder sb, char[] cArr) {
        sb.append(cArr);
        Intrinsics.b(sb, "append(value)");
        return StringsKt.b(sb);
    }

    @InlineOnly
    private static final StringBuilder a(@NotNull StringBuilder sb, char c) {
        sb.append(c);
        Intrinsics.b(sb, "append(value)");
        return StringsKt.b(sb);
    }

    @InlineOnly
    private static final StringBuilder a(@NotNull StringBuilder sb, boolean z) {
        sb.append(z);
        Intrinsics.b(sb, "append(value)");
        return StringsKt.b(sb);
    }

    @InlineOnly
    private static final StringBuilder a(@NotNull StringBuilder sb, int i) {
        sb.append(i);
        Intrinsics.b(sb, "append(value)");
        return StringsKt.b(sb);
    }

    @InlineOnly
    private static final StringBuilder a(@NotNull StringBuilder sb, short s) {
        sb.append(s);
        Intrinsics.b(sb, "append(value.toInt())");
        return StringsKt.b(sb);
    }

    @InlineOnly
    private static final StringBuilder a(@NotNull StringBuilder sb, byte b) {
        sb.append(b);
        Intrinsics.b(sb, "append(value.toInt())");
        return StringsKt.b(sb);
    }

    @InlineOnly
    private static final StringBuilder a(@NotNull StringBuilder sb, long j) {
        sb.append(j);
        Intrinsics.b(sb, "append(value)");
        return StringsKt.b(sb);
    }

    @InlineOnly
    private static final StringBuilder a(@NotNull StringBuilder sb, float f) {
        sb.append(f);
        Intrinsics.b(sb, "append(value)");
        return StringsKt.b(sb);
    }

    @InlineOnly
    private static final StringBuilder a(@NotNull StringBuilder sb, double d) {
        sb.append(d);
        Intrinsics.b(sb, "append(value)");
        return StringsKt.b(sb);
    }
}
