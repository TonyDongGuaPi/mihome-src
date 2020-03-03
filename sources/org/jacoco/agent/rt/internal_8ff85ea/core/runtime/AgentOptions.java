package org.jacoco.agent.rt.internal_8ff85ea.core.runtime;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

public final class AgentOptions {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3630a = "destfile";
    public static final String b = "jacoco.exec";
    public static final String c = "append";
    public static final String d = "includes";
    public static final String e = "excludes";
    public static final String f = "exclclassloader";
    public static final String g = "inclbootstrapclasses";
    public static final String h = "inclnolocationclasses";
    public static final String i = "sessionid";
    public static final String j = "dumponexit";
    public static final String k = "output";
    public static final String l = "address";
    public static final String m = null;
    public static final String n = "port";
    public static final int o = 6300;
    public static final String p = "classdumpdir";
    public static final String q = "jmx";
    private static final Pattern r = Pattern.compile(",(?=[a-zA-Z0-9_\\-]+=)");
    private static final Collection<String> s = Arrays.asList(new String[]{f3630a, c, d, e, f, g, h, i, j, k, "address", "port", p, q});
    private final Map<String, String> t;

    public enum OutputMode {
        file,
        tcpserver,
        tcpclient,
        none
    }

    public AgentOptions() {
        this.t = new HashMap();
    }

    public AgentOptions(String str) {
        this();
        if (str != null && str.length() > 0) {
            String[] split = r.split(str);
            int length = split.length;
            int i2 = 0;
            while (i2 < length) {
                String str2 = split[i2];
                int indexOf = str2.indexOf(61);
                if (indexOf != -1) {
                    String substring = str2.substring(0, indexOf);
                    if (s.contains(substring)) {
                        a(substring, str2.substring(indexOf + 1));
                        i2++;
                    } else {
                        throw new IllegalArgumentException(String.format("Unknown agent option \"%s\".", new Object[]{substring}));
                    }
                } else {
                    throw new IllegalArgumentException(String.format("Invalid agent option syntax \"%s\".", new Object[]{str}));
                }
            }
            o();
        }
    }

    public AgentOptions(Properties properties) {
        this();
        for (String next : s) {
            String property = properties.getProperty(next);
            if (property != null) {
                a(next, property);
            }
        }
    }

    private void o() {
        b(j());
        l();
    }

    private void b(int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException("port must be positive");
        }
    }

    public String a() {
        return b(f3630a, b);
    }

    public void a(String str) {
        a(f3630a, str);
    }

    public boolean b() {
        return b(c, true);
    }

    public void a(boolean z) {
        a(c, z);
    }

    public String c() {
        return b(d, "*");
    }

    public void b(String str) {
        a(d, str);
    }

    public String d() {
        return b(e, "");
    }

    public void c(String str) {
        a(e, str);
    }

    public String e() {
        return b(f, "sun.reflect.DelegatingClassLoader");
    }

    public void d(String str) {
        a(f, str);
    }

    public boolean f() {
        return b(g, false);
    }

    public void b(boolean z) {
        a(g, z);
    }

    public boolean g() {
        return b(h, false);
    }

    public void c(boolean z) {
        a(h, z);
    }

    public String h() {
        return b(i, (String) null);
    }

    public void e(String str) {
        a(i, str);
    }

    public boolean i() {
        return b(j, true);
    }

    public void d(boolean z) {
        a(j, z);
    }

    public int j() {
        return b("port", (int) o);
    }

    public void a(int i2) {
        b(i2);
        a("port", i2);
    }

    public String k() {
        return b("address", m);
    }

    public void f(String str) {
        a("address", str);
    }

    public OutputMode l() {
        String str = this.t.get(k);
        return str == null ? OutputMode.file : OutputMode.valueOf(str);
    }

    public void g(String str) {
        a(OutputMode.valueOf(str));
    }

    public void a(OutputMode outputMode) {
        a(k, outputMode.name());
    }

    public String m() {
        return b(p, (String) null);
    }

    public void h(String str) {
        a(p, str);
    }

    public boolean n() {
        return b(q, false);
    }

    public void e(boolean z) {
        a(q, z);
    }

    private void a(String str, int i2) {
        a(str, Integer.toString(i2));
    }

    private void a(String str, boolean z) {
        a(str, Boolean.toString(z));
    }

    private void a(String str, String str2) {
        this.t.put(str, str2);
    }

    private String b(String str, String str2) {
        String str3 = this.t.get(str);
        return str3 == null ? str2 : str3;
    }

    private boolean b(String str, boolean z) {
        String str2 = this.t.get(str);
        return str2 == null ? z : Boolean.parseBoolean(str2);
    }

    private int b(String str, int i2) {
        String str2 = this.t.get(str);
        return str2 == null ? i2 : Integer.parseInt(str2);
    }

    public String a(File file) {
        return String.format("-javaagent:%s=%s", new Object[]{file, this});
    }

    public String b(File file) {
        return CommandLineSupport.a(a(file));
    }

    public String a(String str, File file) {
        List<String> b2 = CommandLineSupport.b(str);
        String format = String.format("-javaagent:%s", new Object[]{file});
        Iterator<String> it = b2.iterator();
        while (it.hasNext()) {
            if (it.next().startsWith(format)) {
                it.remove();
            }
        }
        b2.add(0, a(file));
        return CommandLineSupport.a(b2);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String next : s) {
            String str = this.t.get(next);
            if (str != null) {
                if (sb.length() > 0) {
                    sb.append(',');
                }
                sb.append(next);
                sb.append('=');
                sb.append(str);
            }
        }
        return sb.toString();
    }
}
