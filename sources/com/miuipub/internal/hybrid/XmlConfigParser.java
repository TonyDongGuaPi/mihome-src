package com.miuipub.internal.hybrid;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.xmlpull.v1.XmlPullParserException;

public class XmlConfigParser implements ConfigParser {

    /* renamed from: a  reason: collision with root package name */
    private static final String f8269a = "com.miui.sdk.hybrid.config";
    private static final String b = "miui_hybrid_config";
    private static final String c = "widget";
    private static final String d = "content";
    private static final String e = "feature";
    private static final String f = "param";
    private static final String g = "preference";
    private static final String h = "access";
    private static final String i = "src";
    private static final String j = "origin";
    private static final String k = "subdomains";
    private static final String l = "name";
    private static final String m = "value";
    private static final String n = "signature";
    private static final String o = "timestamp";
    private static final String p = "vendor";
    private XmlResourceParser q;

    private Config a(Config config, Map<String, Object> map) {
        return config;
    }

    private XmlConfigParser(XmlResourceParser xmlResourceParser) {
        this.q = xmlResourceParser;
    }

    public static XmlConfigParser a(Context context) throws HybridException {
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            int i2 = bundle != null ? bundle.getInt(f8269a) : 0;
            if (i2 == 0) {
                i2 = context.getResources().getIdentifier(b, "xml", context.getPackageName());
            }
            return a(context, i2);
        } catch (PackageManager.NameNotFoundException e2) {
            throw new HybridException(201, e2.getMessage());
        }
    }

    public static XmlConfigParser a(Context context, int i2) throws HybridException {
        try {
            return a(context.getResources().getXml(i2));
        } catch (Resources.NotFoundException e2) {
            throw new HybridException(201, e2.getMessage());
        }
    }

    public static XmlConfigParser a(XmlResourceParser xmlResourceParser) {
        return new XmlConfigParser(xmlResourceParser);
    }

    public Config a(Map<String, Object> map) throws HybridException {
        if (map == null) {
            map = new HashMap<>();
        }
        Config config = new Config();
        if (this.q != null) {
            try {
                XmlResourceParser xmlResourceParser = this.q;
                while (true) {
                    int next = xmlResourceParser.next();
                    if (next == 2 || next == 1) {
                    }
                }
                if (c.equals(xmlResourceParser.getName())) {
                    a(config, xmlResourceParser);
                }
                this.q.close();
            } catch (XmlPullParserException e2) {
                throw new HybridException(201, e2.getMessage());
            } catch (IOException e3) {
                throw new HybridException(201, e3.getMessage());
            } catch (Throwable th) {
                this.q.close();
                throw th;
            }
        }
        return a(config, map);
    }

    private void a(Config config, XmlResourceParser xmlResourceParser) throws XmlPullParserException, IOException {
        int depth = xmlResourceParser.getDepth();
        while (true) {
            int next = xmlResourceParser.next();
            if (next == 1) {
                return;
            }
            if (next == 3 && xmlResourceParser.getDepth() <= depth) {
                return;
            }
            if (!(next == 3 || next == 4)) {
                String name = xmlResourceParser.getName();
                if ("content".equals(name)) {
                    b(config, xmlResourceParser);
                } else if ("feature".equals(name)) {
                    c(config, xmlResourceParser);
                } else if (g.equals(name)) {
                    d(config, xmlResourceParser);
                } else if (h.equals(name)) {
                    e(config, xmlResourceParser);
                }
            }
        }
    }

    private void b(Config config, XmlResourceParser xmlResourceParser) {
        config.b(xmlResourceParser.getAttributeValue((String) null, "src"));
    }

    private void c(Config config, XmlResourceParser xmlResourceParser) throws XmlPullParserException, IOException {
        Feature feature = new Feature();
        feature.a(xmlResourceParser.getAttributeValue((String) null, "name"));
        int depth = xmlResourceParser.getDepth();
        while (true) {
            int next = xmlResourceParser.next();
            if (next == 1 || (next == 3 && xmlResourceParser.getDepth() <= depth)) {
                config.a(feature);
            } else if (!(next == 3 || next == 4 || !"param".equals(xmlResourceParser.getName()))) {
                a(feature, xmlResourceParser);
            }
        }
        config.a(feature);
    }

    private void a(Feature feature, XmlResourceParser xmlResourceParser) throws XmlPullParserException, IOException {
        feature.a(xmlResourceParser.getAttributeValue((String) null, "name").toLowerCase(), xmlResourceParser.getAttributeValue((String) null, "value"));
    }

    private void d(Config config, XmlResourceParser xmlResourceParser) {
        String lowerCase = xmlResourceParser.getAttributeValue((String) null, "name").toLowerCase();
        String attributeValue = xmlResourceParser.getAttributeValue((String) null, "value");
        if ("signature".equals(lowerCase)) {
            a(config).a(attributeValue);
        } else if ("timestamp".equals(lowerCase)) {
            a(config).a(Long.parseLong(attributeValue));
        } else if (p.equals(lowerCase)) {
            config.a(attributeValue);
        } else {
            config.a(lowerCase, attributeValue);
        }
    }

    private Security a(Config config) {
        Security a2 = config.a();
        if (a2 != null) {
            return a2;
        }
        Security security = new Security();
        config.a(security);
        return security;
    }

    private void e(Config config, XmlResourceParser xmlResourceParser) {
        Permission permission = new Permission();
        permission.a(xmlResourceParser.getAttributeValue((String) null, "origin"));
        permission.a(xmlResourceParser.getAttributeBooleanValue((String) null, k, false));
        permission.b(false);
        config.a(permission);
    }
}
