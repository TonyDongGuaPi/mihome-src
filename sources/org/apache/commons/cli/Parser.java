package org.apache.commons.cli;

import com.mobikwik.sdk.lib.Constants;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ListIterator;
import java.util.Properties;

public abstract class Parser implements CommandLineParser {

    /* renamed from: a  reason: collision with root package name */
    protected CommandLine f3195a;
    private Options b;
    private List c;

    /* access modifiers changed from: protected */
    public abstract String[] a(Options options, String[] strArr, boolean z);

    /* access modifiers changed from: protected */
    public void a(Options options) {
        this.b = options;
        this.c = new ArrayList(options.getRequiredOptions());
    }

    /* access modifiers changed from: protected */
    public Options a() {
        return this.b;
    }

    /* access modifiers changed from: protected */
    public List b() {
        return this.c;
    }

    public CommandLine a(Options options, String[] strArr) throws ParseException {
        return a(options, strArr, (Properties) null, false);
    }

    public CommandLine a(Options options, String[] strArr, Properties properties) throws ParseException {
        return a(options, strArr, properties, false);
    }

    public CommandLine b(Options options, String[] strArr, boolean z) throws ParseException {
        return a(options, strArr, (Properties) null, z);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0082, code lost:
        if (r7 != false) goto L_0x004c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0087 A[LOOP:2: B:27:0x0087->B:39:0x0087, LOOP_START] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0037 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.commons.cli.CommandLine a(org.apache.commons.cli.Options r4, java.lang.String[] r5, java.util.Properties r6, boolean r7) throws org.apache.commons.cli.ParseException {
        /*
            r3 = this;
            java.util.List r0 = r4.helpOptions()
            java.util.Iterator r0 = r0.iterator()
        L_0x0008:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0018
            java.lang.Object r1 = r0.next()
            org.apache.commons.cli.Option r1 = (org.apache.commons.cli.Option) r1
            r1.clearValues()
            goto L_0x0008
        L_0x0018:
            r3.a((org.apache.commons.cli.Options) r4)
            org.apache.commons.cli.CommandLine r4 = new org.apache.commons.cli.CommandLine
            r4.<init>()
            r3.f3195a = r4
            r4 = 0
            if (r5 != 0) goto L_0x0027
            java.lang.String[] r5 = new java.lang.String[r4]
        L_0x0027:
            org.apache.commons.cli.Options r0 = r3.a()
            java.lang.String[] r5 = r3.a((org.apache.commons.cli.Options) r0, (java.lang.String[]) r5, (boolean) r7)
            java.util.List r5 = java.util.Arrays.asList(r5)
            java.util.ListIterator r5 = r5.listIterator()
        L_0x0037:
            boolean r0 = r5.hasNext()
            if (r0 == 0) goto L_0x00a1
            java.lang.Object r0 = r5.next()
            java.lang.String r0 = (java.lang.String) r0
            java.lang.String r1 = "--"
            boolean r1 = r1.equals(r0)
            r2 = 1
            if (r1 == 0) goto L_0x004e
        L_0x004c:
            r4 = 1
            goto L_0x0085
        L_0x004e:
            java.lang.String r1 = "-"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x005f
            if (r7 == 0) goto L_0x0059
            goto L_0x004c
        L_0x0059:
            org.apache.commons.cli.CommandLine r1 = r3.f3195a
            r1.addArg(r0)
            goto L_0x0085
        L_0x005f:
            java.lang.String r1 = "-"
            boolean r1 = r0.startsWith(r1)
            if (r1 == 0) goto L_0x007d
            if (r7 == 0) goto L_0x0079
            org.apache.commons.cli.Options r1 = r3.a()
            boolean r1 = r1.hasOption(r0)
            if (r1 != 0) goto L_0x0079
            org.apache.commons.cli.CommandLine r4 = r3.f3195a
            r4.addArg(r0)
            goto L_0x004c
        L_0x0079:
            r3.a((java.lang.String) r0, (java.util.ListIterator) r5)
            goto L_0x0085
        L_0x007d:
            org.apache.commons.cli.CommandLine r1 = r3.f3195a
            r1.addArg(r0)
            if (r7 == 0) goto L_0x0085
            goto L_0x004c
        L_0x0085:
            if (r4 == 0) goto L_0x0037
        L_0x0087:
            boolean r0 = r5.hasNext()
            if (r0 == 0) goto L_0x0037
            java.lang.Object r0 = r5.next()
            java.lang.String r0 = (java.lang.String) r0
            java.lang.String r1 = "--"
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto L_0x0087
            org.apache.commons.cli.CommandLine r1 = r3.f3195a
            r1.addArg(r0)
            goto L_0x0087
        L_0x00a1:
            r3.a((java.util.Properties) r6)
            r3.c()
            org.apache.commons.cli.CommandLine r4 = r3.f3195a
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.cli.Parser.a(org.apache.commons.cli.Options, java.lang.String[], java.util.Properties, boolean):org.apache.commons.cli.CommandLine");
    }

    /* access modifiers changed from: protected */
    public void a(Properties properties) {
        if (properties != null) {
            Enumeration<?> propertyNames = properties.propertyNames();
            while (propertyNames.hasMoreElements()) {
                String obj = propertyNames.nextElement().toString();
                if (!this.f3195a.hasOption(obj)) {
                    Option option = a().getOption(obj);
                    String property = properties.getProperty(obj);
                    if (option.hasArg()) {
                        if (option.getValues() == null || option.getValues().length == 0) {
                            try {
                                option.addValueForProcessing(property);
                            } catch (RuntimeException unused) {
                            }
                        }
                    } else if (!Constants.YES.equalsIgnoreCase(property) && !"true".equalsIgnoreCase(property) && !"1".equalsIgnoreCase(property)) {
                        return;
                    }
                    this.f3195a.addOption(option);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void c() throws MissingOptionException {
        if (!b().isEmpty()) {
            throw new MissingOptionException(b());
        }
    }

    public void a(Option option, ListIterator listIterator) throws ParseException {
        while (true) {
            if (!listIterator.hasNext()) {
                break;
            }
            String str = (String) listIterator.next();
            if (a().hasOption(str) && str.startsWith("-")) {
                listIterator.previous();
                break;
            } else {
                try {
                    option.addValueForProcessing(Util.b(str));
                } catch (RuntimeException unused) {
                    listIterator.previous();
                }
            }
        }
        if (option.getValues() == null && !option.hasOptionalArg()) {
            throw new MissingArgumentException(option);
        }
    }

    /* access modifiers changed from: protected */
    public void a(String str, ListIterator listIterator) throws ParseException {
        if (a().hasOption(str)) {
            Option option = (Option) a().getOption(str).clone();
            if (option.isRequired()) {
                b().remove(option.getKey());
            }
            if (a().getOptionGroup(option) != null) {
                OptionGroup optionGroup = a().getOptionGroup(option);
                if (optionGroup.isRequired()) {
                    b().remove(optionGroup);
                }
                optionGroup.setSelected(option);
            }
            if (option.hasArg()) {
                a(option, listIterator);
            }
            this.f3195a.addOption(option);
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Unrecognized option: ");
        stringBuffer.append(str);
        throw new UnrecognizedOptionException(stringBuffer.toString(), str);
    }
}
