package org.wltea.analyzer.cfg;

import com.alipay.sdk.util.i;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Properties;

public class DefaultConfig implements Configuration {

    /* renamed from: a  reason: collision with root package name */
    private static final String f4193a = "org/wltea/analyzer/dic/main2012.dic";
    private static final String b = "org/wltea/analyzer/dic/quantifier.dic";
    private static final String c = "IKAnalyzer.cfg.xml";
    private static final String d = "ext_dict";
    private static final String e = "ext_stopwords";
    private Properties f = new Properties();
    private boolean g;

    public String b() {
        return f4193a;
    }

    public String c() {
        return b;
    }

    public static Configuration f() {
        return new DefaultConfig();
    }

    private DefaultConfig() {
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(c);
        if (resourceAsStream != null) {
            try {
                this.f.loadFromXML(resourceAsStream);
            } catch (InvalidPropertiesFormatException e2) {
                e2.printStackTrace();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        }
    }

    public boolean a() {
        return this.g;
    }

    public void a(boolean z) {
        this.g = z;
    }

    public List<String> d() {
        String[] split;
        ArrayList arrayList = new ArrayList(2);
        String property = this.f.getProperty(d);
        if (!(property == null || (split = property.split(i.b)) == null)) {
            for (String str : split) {
                if (str != null && !"".equals(str.trim())) {
                    arrayList.add(str.trim());
                }
            }
        }
        return arrayList;
    }

    public List<String> e() {
        String[] split;
        ArrayList arrayList = new ArrayList(2);
        String property = this.f.getProperty(e);
        if (!(property == null || (split = property.split(i.b)) == null)) {
            for (String str : split) {
                if (str != null && !"".equals(str.trim())) {
                    arrayList.add(str.trim());
                }
            }
        }
        return arrayList;
    }
}
