package org.apache.commons.cli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class PosixParser extends Parser {
    private List b = new ArrayList();
    private boolean c;
    private Option d;
    private Options e;

    private void d() {
        this.c = false;
        this.b.clear();
    }

    /* access modifiers changed from: protected */
    public String[] a(Options options, String[] strArr, boolean z) {
        String str;
        d();
        this.e = options;
        Iterator it = Arrays.asList(strArr).iterator();
        while (it.hasNext()) {
            String str2 = (String) it.next();
            if (str2.startsWith(HelpFormatter.f)) {
                int indexOf = str2.indexOf(61);
                if (indexOf == -1) {
                    str = str2;
                } else {
                    str = str2.substring(0, indexOf);
                }
                if (!options.hasOption(str)) {
                    b(str2, z);
                } else {
                    this.d = options.getOption(str);
                    this.b.add(str);
                    if (indexOf != -1) {
                        this.b.add(str2.substring(indexOf + 1));
                    }
                }
            } else if ("-".equals(str2)) {
                this.b.add(str2);
            } else if (!str2.startsWith("-")) {
                b(str2, z);
            } else if (str2.length() == 2 || options.hasOption(str2)) {
                c(str2, z);
            } else {
                a(str2, z);
            }
            a(it);
        }
        return (String[]) this.b.toArray(new String[this.b.size()]);
    }

    private void a(Iterator it) {
        if (this.c) {
            while (it.hasNext()) {
                this.b.add(it.next());
            }
        }
    }

    private void b(String str, boolean z) {
        if (z && (this.d == null || !this.d.hasArg())) {
            this.c = true;
            this.b.add(HelpFormatter.f);
        }
        this.b.add(str);
    }

    private void c(String str, boolean z) {
        if (z && !this.e.hasOption(str)) {
            this.c = true;
        }
        if (this.e.hasOption(str)) {
            this.d = this.e.getOption(str);
        }
        this.b.add(str);
    }

    /* access modifiers changed from: protected */
    public void a(String str, boolean z) {
        int i;
        int i2 = 1;
        while (i2 < str.length()) {
            String valueOf = String.valueOf(str.charAt(i2));
            if (this.e.hasOption(valueOf)) {
                List list = this.b;
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("-");
                stringBuffer.append(valueOf);
                list.add(stringBuffer.toString());
                this.d = this.e.getOption(valueOf);
                if (!this.d.hasArg() || str.length() == (i = i2 + 1)) {
                    i2++;
                } else {
                    this.b.add(str.substring(i));
                    return;
                }
            } else if (z) {
                b(str.substring(i2), true);
                return;
            } else {
                this.b.add(str);
                return;
            }
        }
    }
}
