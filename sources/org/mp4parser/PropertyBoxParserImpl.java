package org.mp4parser;

import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.mp4parser.tools.Hex;

public class PropertyBoxParserImpl extends AbstractBoxParser {
    static Properties b;
    static String[] c = new String[0];
    Properties d;
    Pattern e = Pattern.compile("(.*)\\((.*?)\\)");
    StringBuilder f = new StringBuilder();
    ThreadLocal<String> g = new ThreadLocal<>();
    ThreadLocal<String[]> h = new ThreadLocal<>();

    public PropertyBoxParserImpl(String... strArr) {
        InputStream openStream;
        if (b != null) {
            this.d = new Properties(b);
            return;
        }
        InputStream resourceAsStream = getClass().getResourceAsStream("/isoparser-default.properties");
        try {
            this.d = new Properties();
            try {
                this.d.load(resourceAsStream);
                ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
                Enumeration<URL> resources = (contextClassLoader == null ? ClassLoader.getSystemClassLoader() : contextClassLoader).getResources("isoparser-custom.properties");
                while (resources.hasMoreElements()) {
                    openStream = resources.nextElement().openStream();
                    this.d.load(openStream);
                    openStream.close();
                }
                for (String resourceAsStream2 : strArr) {
                    this.d.load(getClass().getResourceAsStream(resourceAsStream2));
                }
                b = this.d;
                try {
                    resourceAsStream.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            } catch (IOException e3) {
                throw new RuntimeException(e3);
            } catch (Throwable th) {
                openStream.close();
                throw th;
            }
        } catch (Throwable th2) {
            try {
                resourceAsStream.close();
            } catch (IOException e4) {
                e4.printStackTrace();
            }
            throw th2;
        }
    }

    public PropertyBoxParserImpl(Properties properties) {
        this.d = properties;
    }

    public ParsableBox a(String str, byte[] bArr, String str2) {
        b(str, bArr, str2);
        String[] strArr = this.h.get();
        try {
            Class<?> cls = Class.forName(this.g.get());
            if (strArr.length <= 0) {
                return (ParsableBox) cls.newInstance();
            }
            Class[] clsArr = new Class[strArr.length];
            Object[] objArr = new Object[strArr.length];
            for (int i = 0; i < strArr.length; i++) {
                if ("userType".equals(strArr[i])) {
                    objArr[i] = bArr;
                    clsArr[i] = byte[].class;
                } else if ("type".equals(strArr[i])) {
                    objArr[i] = str;
                    clsArr[i] = String.class;
                } else if ("parent".equals(strArr[i])) {
                    objArr[i] = str2;
                    clsArr[i] = String.class;
                } else {
                    throw new InternalError("No such param: " + strArr[i]);
                }
            }
            return (ParsableBox) cls.getConstructor(clsArr).newInstance(objArr);
        } catch (ClassNotFoundException e2) {
            throw new RuntimeException(e2);
        } catch (InstantiationException e3) {
            throw new RuntimeException(e3);
        } catch (IllegalAccessException e4) {
            throw new RuntimeException(e4);
        } catch (InvocationTargetException e5) {
            throw new RuntimeException(e5);
        } catch (NoSuchMethodException e6) {
            throw new RuntimeException(e6);
        }
    }

    public void b(String str, byte[] bArr, String str2) {
        String str3;
        if (bArr == null) {
            str3 = this.d.getProperty(str);
            if (str3 == null) {
                StringBuilder sb = this.f;
                sb.append(str2);
                sb.append('-');
                sb.append(str);
                String sb2 = sb.toString();
                this.f.setLength(0);
                str3 = this.d.getProperty(sb2);
            }
        } else if ("uuid".equals(str)) {
            Properties properties = this.d;
            str3 = properties.getProperty("uuid[" + Hex.a(bArr).toUpperCase() + Operators.ARRAY_END_STR);
            if (str3 == null) {
                Properties properties2 = this.d;
                str3 = properties2.getProperty(String.valueOf(str2) + "-uuid[" + Hex.a(bArr).toUpperCase() + Operators.ARRAY_END_STR);
            }
            if (str3 == null) {
                str3 = this.d.getProperty("uuid");
            }
        } else {
            throw new RuntimeException("we have a userType but no uuid box type. Something's wrong");
        }
        if (str3 == null) {
            str3 = this.d.getProperty("default");
        }
        if (str3 == null) {
            throw new RuntimeException("No box object found for " + str);
        } else if (!str3.endsWith(Operators.BRACKET_END_STR)) {
            this.h.set(c);
            this.g.set(str3);
        } else {
            Matcher matcher = this.e.matcher(str3);
            if (matcher.matches()) {
                this.g.set(matcher.group(1));
                if (matcher.group(2).length() == 0) {
                    this.h.set(c);
                } else {
                    this.h.set(matcher.group(2).length() > 0 ? matcher.group(2).split(",") : new String[0]);
                }
            } else {
                throw new RuntimeException("Cannot work with that constructor: " + str3);
            }
        }
    }
}
