package kotlin.text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u000b\u001a!\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0002\b\u0004\u001a\u0011\u0010\u0005\u001a\u00020\u0006*\u00020\u0002H\u0002¢\u0006\u0002\b\u0007\u001a\u0014\u0010\b\u001a\u00020\u0002*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u001aJ\u0010\t\u001a\u00020\u0002*\b\u0012\u0004\u0012\u00020\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00062\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u00012\u0014\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001H\b¢\u0006\u0002\b\u000e\u001a\u0014\u0010\u000f\u001a\u00020\u0002*\u00020\u00022\b\b\u0002\u0010\u0010\u001a\u00020\u0002\u001a\u001e\u0010\u0011\u001a\u00020\u0002*\u00020\u00022\b\b\u0002\u0010\u0010\u001a\u00020\u00022\b\b\u0002\u0010\u0012\u001a\u00020\u0002\u001a\n\u0010\u0013\u001a\u00020\u0002*\u00020\u0002\u001a\u0014\u0010\u0014\u001a\u00020\u0002*\u00020\u00022\b\b\u0002\u0010\u0012\u001a\u00020\u0002¨\u0006\u0015"}, d2 = {"getIndentFunction", "Lkotlin/Function1;", "", "indent", "getIndentFunction$StringsKt__IndentKt", "indentWidth", "", "indentWidth$StringsKt__IndentKt", "prependIndent", "reindent", "", "resultSizeEstimate", "indentAddFunction", "indentCutFunction", "reindent$StringsKt__IndentKt", "replaceIndent", "newIndent", "replaceIndentByMargin", "marginPrefix", "trimIndent", "trimMargin", "kotlin-stdlib"}, k = 5, mv = {1, 1, 13}, xi = 1, xs = "kotlin/text/StringsKt")
class StringsKt__IndentKt {
    @NotNull
    public static /* synthetic */ String a(String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str2 = "|";
        }
        return StringsKt.a(str, str2);
    }

    @NotNull
    public static final String a(@NotNull String str, @NotNull String str2) {
        Intrinsics.f(str, "receiver$0");
        Intrinsics.f(str2, "marginPrefix");
        return StringsKt.a(str, "", str2);
    }

    @NotNull
    public static /* synthetic */ String a(String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str2 = "";
        }
        if ((i & 2) != 0) {
            str3 = "|";
        }
        return StringsKt.a(str, str2, str3);
    }

    @NotNull
    public static final String a(@NotNull String str, @NotNull String str2, @NotNull String str3) {
        int i;
        String invoke;
        String str4 = str;
        String str5 = str3;
        Intrinsics.f(str4, "receiver$0");
        Intrinsics.f(str2, "newIndent");
        Intrinsics.f(str5, "marginPrefix");
        if (!StringsKt.a((CharSequence) str5)) {
            List<String> i2 = StringsKt.i(str4);
            int length = str.length() + (str2.length() * i2.size());
            Function1<String, String> c = c(str2);
            int a2 = CollectionsKt.a(i2);
            Collection arrayList = new ArrayList();
            int i3 = 0;
            for (Object next : i2) {
                int i4 = i3 + 1;
                if (i3 < 0) {
                    CollectionsKt.b();
                }
                String str6 = (String) next;
                String str7 = null;
                if ((i3 == 0 || i3 == a2) && StringsKt.a((CharSequence) str6)) {
                    str6 = null;
                } else {
                    CharSequence charSequence = str6;
                    int length2 = charSequence.length();
                    int i5 = 0;
                    while (true) {
                        if (i5 >= length2) {
                            i = -1;
                            break;
                        } else if (!CharsKt.a(charSequence.charAt(i5))) {
                            i = i5;
                            break;
                        } else {
                            i5++;
                        }
                    }
                    if (i != -1) {
                        int i6 = i;
                        if (StringsKt.a(str6, str3, i, false, 4, (Object) null)) {
                            int length3 = i6 + str3.length();
                            if (str6 != null) {
                                str7 = str6.substring(length3);
                                Intrinsics.b(str7, "(this as java.lang.String).substring(startIndex)");
                            } else {
                                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                            }
                        }
                    }
                    if (!(str7 == null || (invoke = c.invoke(str7)) == null)) {
                        str6 = invoke;
                    }
                }
                if (str6 != null) {
                    arrayList.add(str6);
                }
                i3 = i4;
            }
            String sb = ((StringBuilder) CollectionsKt.a((List) arrayList, new StringBuilder(length), "\n", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 124, (Object) null)).toString();
            Intrinsics.b(sb, "mapIndexedNotNull { inde…\"\\n\")\n        .toString()");
            return sb;
        }
        throw new IllegalArgumentException("marginPrefix must be non-blank string.".toString());
    }

    @NotNull
    public static final String a(@NotNull String str) {
        Intrinsics.f(str, "receiver$0");
        return StringsKt.b(str, "");
    }

    @NotNull
    public static /* synthetic */ String b(String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str2 = "";
        }
        return StringsKt.b(str, str2);
    }

    @NotNull
    public static final String b(@NotNull String str, @NotNull String str2) {
        String invoke;
        String str3 = str;
        Intrinsics.f(str3, "receiver$0");
        Intrinsics.f(str2, "newIndent");
        List<String> i = StringsKt.i(str3);
        Iterable iterable = i;
        Collection arrayList = new ArrayList();
        for (Object next : iterable) {
            if (!StringsKt.a((CharSequence) (String) next)) {
                arrayList.add(next);
            }
        }
        Iterable<String> iterable2 = (List) arrayList;
        Collection arrayList2 = new ArrayList(CollectionsKt.a(iterable2, 10));
        for (String b : iterable2) {
            arrayList2.add(Integer.valueOf(b(b)));
        }
        Integer num = (Integer) CollectionsKt.E((List) arrayList2);
        int i2 = 0;
        int intValue = num != null ? num.intValue() : 0;
        int length = str.length() + (str2.length() * i.size());
        Function1<String, String> c = c(str2);
        int a2 = CollectionsKt.a(i);
        Collection arrayList3 = new ArrayList();
        for (Object next2 : iterable) {
            int i3 = i2 + 1;
            if (i2 < 0) {
                CollectionsKt.b();
            }
            String str4 = (String) next2;
            if ((i2 == 0 || i2 == a2) && StringsKt.a((CharSequence) str4)) {
                str4 = null;
            } else {
                String f = StringsKt.f(str4, intValue);
                if (!(f == null || (invoke = c.invoke(f)) == null)) {
                    str4 = invoke;
                }
            }
            if (str4 != null) {
                arrayList3.add(str4);
            }
            i2 = i3;
        }
        String sb = ((StringBuilder) CollectionsKt.a((List) arrayList3, new StringBuilder(length), "\n", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 124, (Object) null)).toString();
        Intrinsics.b(sb, "mapIndexedNotNull { inde…\"\\n\")\n        .toString()");
        return sb;
    }

    @NotNull
    public static /* synthetic */ String c(String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str2 = "    ";
        }
        return StringsKt.c(str, str2);
    }

    @NotNull
    public static final String c(@NotNull String str, @NotNull String str2) {
        Intrinsics.f(str, "receiver$0");
        Intrinsics.f(str2, "indent");
        return SequencesKt.a(SequencesKt.u(StringsKt.h(str), new StringsKt__IndentKt$prependIndent$1(str2)), "\n", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
    }

    private static final int b(@NotNull String str) {
        CharSequence charSequence = str;
        int length = charSequence.length();
        int i = 0;
        while (true) {
            if (i >= length) {
                i = -1;
                break;
            } else if (!CharsKt.a(charSequence.charAt(i))) {
                break;
            } else {
                i++;
            }
        }
        return i == -1 ? str.length() : i;
    }

    private static final Function1<String, String> c(String str) {
        if (str.length() == 0) {
            return StringsKt__IndentKt$getIndentFunction$1.INSTANCE;
        }
        return new StringsKt__IndentKt$getIndentFunction$2(str);
    }

    private static final String a(@NotNull List<String> list, int i, Function1<? super String, String> function1, Function1<? super String, String> function12) {
        String invoke;
        int a2 = CollectionsKt.a(list);
        Collection arrayList = new ArrayList();
        int i2 = 0;
        for (Object next : list) {
            int i3 = i2 + 1;
            if (i2 < 0) {
                if (PlatformImplementationsKt.a(1, 3, 0)) {
                    CollectionsKt.b();
                } else {
                    throw new ArithmeticException("Index overflow has happened.");
                }
            }
            String str = (String) next;
            if ((i2 == 0 || i2 == a2) && StringsKt.a((CharSequence) str)) {
                str = null;
            } else {
                String invoke2 = function12.invoke(str);
                if (!(invoke2 == null || (invoke = function1.invoke(invoke2)) == null)) {
                    str = invoke;
                }
            }
            if (str != null) {
                arrayList.add(str);
            }
            i2 = i3;
        }
        String sb = ((StringBuilder) CollectionsKt.a((List) arrayList, new StringBuilder(i), "\n", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 124, (Object) null)).toString();
        Intrinsics.b(sb, "mapIndexedNotNull { inde…\"\\n\")\n        .toString()");
        return sb;
    }
}
