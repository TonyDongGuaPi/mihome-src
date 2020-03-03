package org.apache.commons.cli;

import com.taobao.weex.el.parse.Operators;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class HelpFormatter {

    /* renamed from: a  reason: collision with root package name */
    public static final int f3193a = 74;
    public static final int b = 1;
    public static final int c = 3;
    public static final String d = "usage: ";
    public static final String e = "-";
    public static final String f = "--";
    public static final String g = "arg";
    public int h = 74;
    public int i = 1;
    public int j = 3;
    public String k = d;
    public String l = System.getProperty("line.separator");
    public String m = "-";
    public String n = f;
    public String o = "arg";
    protected Comparator p = new OptionComparator((AnonymousClass1) null);

    /* renamed from: org.apache.commons.cli.HelpFormatter$1  reason: invalid class name */
    class AnonymousClass1 {
    }

    public void a(int i2) {
        this.h = i2;
    }

    public int a() {
        return this.h;
    }

    public void b(int i2) {
        this.i = i2;
    }

    public int b() {
        return this.i;
    }

    public void c(int i2) {
        this.j = i2;
    }

    public int c() {
        return this.j;
    }

    public void a(String str) {
        this.k = str;
    }

    public String d() {
        return this.k;
    }

    public void b(String str) {
        this.l = str;
    }

    public String e() {
        return this.l;
    }

    public void c(String str) {
        this.m = str;
    }

    public String f() {
        return this.m;
    }

    public void d(String str) {
        this.n = str;
    }

    public String g() {
        return this.n;
    }

    public void e(String str) {
        this.o = str;
    }

    public String h() {
        return this.o;
    }

    public Comparator i() {
        return this.p;
    }

    public void a(Comparator comparator) {
        if (comparator == null) {
            this.p = new OptionComparator((AnonymousClass1) null);
        } else {
            this.p = comparator;
        }
    }

    public void a(String str, Options options) {
        a(this.h, str, (String) null, options, (String) null, false);
    }

    public void a(String str, Options options, boolean z) {
        a(this.h, str, (String) null, options, (String) null, z);
    }

    public void a(String str, String str2, Options options, String str3) {
        a(str, str2, options, str3, false);
    }

    public void a(String str, String str2, Options options, String str3, boolean z) {
        a(this.h, str, str2, options, str3, z);
    }

    public void a(int i2, String str, String str2, Options options, String str3) {
        a(i2, str, str2, options, str3, false);
    }

    public void a(int i2, String str, String str2, Options options, String str3, boolean z) {
        PrintWriter printWriter = new PrintWriter(System.out);
        a(printWriter, i2, str, str2, options, this.i, this.j, str3, z);
        printWriter.flush();
    }

    public void a(PrintWriter printWriter, int i2, String str, String str2, Options options, int i3, int i4, String str3) {
        a(printWriter, i2, str, str2, options, i3, i4, str3, false);
    }

    public void a(PrintWriter printWriter, int i2, String str, String str2, Options options, int i3, int i4, String str3, boolean z) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("cmdLineSyntax not provided");
        }
        if (z) {
            a(printWriter, i2, str, options);
        } else {
            a(printWriter, i2, str);
        }
        if (str2 != null && str2.trim().length() > 0) {
            b(printWriter, i2, str2);
        }
        a(printWriter, i2, options, i3, i4);
        if (str3 != null && str3.trim().length() > 0) {
            b(printWriter, i2, str3);
        }
    }

    public void a(PrintWriter printWriter, int i2, String str, Options options) {
        StringBuffer stringBuffer = new StringBuffer(this.k);
        stringBuffer.append(str);
        stringBuffer.append(" ");
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList(options.getOptions());
        Collections.sort(arrayList2, i());
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            Option option = (Option) it.next();
            OptionGroup optionGroup = options.getOptionGroup(option);
            if (optionGroup == null) {
                a(stringBuffer, option, option.isRequired());
            } else if (!arrayList.contains(optionGroup)) {
                arrayList.add(optionGroup);
                a(stringBuffer, optionGroup);
            }
            if (it.hasNext()) {
                stringBuffer.append(" ");
            }
        }
        a(printWriter, i2, stringBuffer.toString().indexOf(32) + 1, stringBuffer.toString());
    }

    private void a(StringBuffer stringBuffer, OptionGroup optionGroup) {
        if (!optionGroup.isRequired()) {
            stringBuffer.append(Operators.ARRAY_START_STR);
        }
        ArrayList arrayList = new ArrayList(optionGroup.getOptions());
        Collections.sort(arrayList, i());
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            a(stringBuffer, (Option) it.next(), true);
            if (it.hasNext()) {
                stringBuffer.append(" | ");
            }
        }
        if (!optionGroup.isRequired()) {
            stringBuffer.append(Operators.ARRAY_END_STR);
        }
    }

    private static void a(StringBuffer stringBuffer, Option option, boolean z) {
        if (!z) {
            stringBuffer.append(Operators.ARRAY_START_STR);
        }
        if (option.getOpt() != null) {
            stringBuffer.append("-");
            stringBuffer.append(option.getOpt());
        } else {
            stringBuffer.append(f);
            stringBuffer.append(option.getLongOpt());
        }
        if (option.hasArg() && option.hasArgName()) {
            stringBuffer.append(" <");
            stringBuffer.append(option.getArgName());
            stringBuffer.append(">");
        }
        if (!z) {
            stringBuffer.append(Operators.ARRAY_END_STR);
        }
    }

    public void a(PrintWriter printWriter, int i2, String str) {
        int length = this.k.length() + str.indexOf(32) + 1;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.k);
        stringBuffer.append(str);
        a(printWriter, i2, length, stringBuffer.toString());
    }

    public void a(PrintWriter printWriter, int i2, Options options, int i3, int i4) {
        StringBuffer stringBuffer = new StringBuffer();
        a(stringBuffer, i2, options, i3, i4);
        printWriter.println(stringBuffer.toString());
    }

    public void b(PrintWriter printWriter, int i2, String str) {
        a(printWriter, i2, 0, str);
    }

    public void a(PrintWriter printWriter, int i2, int i3, String str) {
        StringBuffer stringBuffer = new StringBuffer(str.length());
        a(stringBuffer, i2, i3, str);
        printWriter.println(stringBuffer.toString());
    }

    /* access modifiers changed from: protected */
    public StringBuffer a(StringBuffer stringBuffer, int i2, Options options, int i3, int i4) {
        String d2 = d(i3);
        String d3 = d(i4);
        ArrayList arrayList = new ArrayList();
        List<Option> helpOptions = options.helpOptions();
        Collections.sort(helpOptions, i());
        int i5 = 0;
        int i6 = 0;
        for (Option option : helpOptions) {
            StringBuffer stringBuffer2 = new StringBuffer(8);
            if (option.getOpt() == null) {
                stringBuffer2.append(d2);
                StringBuffer stringBuffer3 = new StringBuffer();
                stringBuffer3.append("   ");
                stringBuffer3.append(this.n);
                stringBuffer2.append(stringBuffer3.toString());
                stringBuffer2.append(option.getLongOpt());
            } else {
                stringBuffer2.append(d2);
                stringBuffer2.append(this.m);
                stringBuffer2.append(option.getOpt());
                if (option.hasLongOpt()) {
                    stringBuffer2.append(',');
                    stringBuffer2.append(this.n);
                    stringBuffer2.append(option.getLongOpt());
                }
            }
            if (option.hasArg()) {
                if (option.hasArgName()) {
                    stringBuffer2.append(" <");
                    stringBuffer2.append(option.getArgName());
                    stringBuffer2.append(">");
                } else {
                    stringBuffer2.append(' ');
                }
            }
            arrayList.add(stringBuffer2);
            if (stringBuffer2.length() > i6) {
                i6 = stringBuffer2.length();
            }
        }
        Iterator it = helpOptions.iterator();
        while (it.hasNext()) {
            Option option2 = (Option) it.next();
            int i7 = i5 + 1;
            StringBuffer stringBuffer4 = new StringBuffer(arrayList.get(i5).toString());
            if (stringBuffer4.length() < i6) {
                stringBuffer4.append(d(i6 - stringBuffer4.length()));
            }
            stringBuffer4.append(d3);
            int i8 = i6 + i4;
            if (option2.getDescription() != null) {
                stringBuffer4.append(option2.getDescription());
            }
            a(stringBuffer, i2, i8, stringBuffer4.toString());
            if (it.hasNext()) {
                stringBuffer.append(this.l);
            }
            i5 = i7;
        }
        return stringBuffer;
    }

    /* access modifiers changed from: protected */
    public StringBuffer a(StringBuffer stringBuffer, int i2, int i3, String str) {
        int a2 = a(str, i2, 0);
        if (a2 == -1) {
            stringBuffer.append(f(str));
            return stringBuffer;
        }
        stringBuffer.append(f(str.substring(0, a2)));
        stringBuffer.append(this.l);
        if (i3 >= i2) {
            i3 = 1;
        }
        String d2 = d(i3);
        while (true) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(d2);
            stringBuffer2.append(str.substring(a2).trim());
            str = stringBuffer2.toString();
            a2 = a(str, i2, 0);
            if (a2 == -1) {
                stringBuffer.append(str);
                return stringBuffer;
            }
            if (str.length() > i2 && a2 == i3 - 1) {
                a2 = i2;
            }
            stringBuffer.append(f(str.substring(0, a2)));
            stringBuffer.append(this.l);
        }
    }

    /* access modifiers changed from: protected */
    public int a(String str, int i2, int i3) {
        int indexOf = str.indexOf(10, i3);
        if ((indexOf != -1 && indexOf <= i2) || ((indexOf = str.indexOf(9, i3)) != -1 && indexOf <= i2)) {
            return indexOf + 1;
        }
        int i4 = i2 + i3;
        if (i4 >= str.length()) {
            return -1;
        }
        int i5 = i4;
        while (i5 >= i3 && (r5 = str.charAt(i5)) != ' ' && r5 != 10 && r5 != 13) {
            i5--;
        }
        if (i5 > i3) {
            return i5;
        }
        while (i4 <= str.length() && (r9 = str.charAt(i4)) != ' ' && r9 != 10 && r9 != 13) {
            i4++;
        }
        if (i4 == str.length()) {
            return -1;
        }
        return i4;
    }

    /* access modifiers changed from: protected */
    public String d(int i2) {
        StringBuffer stringBuffer = new StringBuffer(i2);
        for (int i3 = 0; i3 < i2; i3++) {
            stringBuffer.append(' ');
        }
        return stringBuffer.toString();
    }

    /* access modifiers changed from: protected */
    public String f(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        int length = str.length();
        while (length > 0 && Character.isWhitespace(str.charAt(length - 1))) {
            length--;
        }
        return str.substring(0, length);
    }

    private static class OptionComparator implements Comparator {
        private OptionComparator() {
        }

        OptionComparator(AnonymousClass1 r1) {
            this();
        }

        public int compare(Object obj, Object obj2) {
            return ((Option) obj).getKey().compareToIgnoreCase(((Option) obj2).getKey());
        }
    }
}
