package org.apache.commons.lang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class LocaleUtils {

    /* renamed from: a  reason: collision with root package name */
    private static List f3368a;
    private static Set b;
    private static final Map c = Collections.synchronizedMap(new HashMap());
    private static final Map d = Collections.synchronizedMap(new HashMap());

    public static Locale a(String str) {
        if (str == null) {
            return null;
        }
        int length = str.length();
        if (length == 2 || length == 5 || length >= 7) {
            char charAt = str.charAt(0);
            char charAt2 = str.charAt(1);
            if (charAt < 'a' || charAt > 'z' || charAt2 < 'a' || charAt2 > 'z') {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Invalid locale format: ");
                stringBuffer.append(str);
                throw new IllegalArgumentException(stringBuffer.toString());
            } else if (length == 2) {
                return new Locale(str, "");
            } else {
                if (str.charAt(2) == '_') {
                    char charAt3 = str.charAt(3);
                    if (charAt3 == '_') {
                        return new Locale(str.substring(0, 2), "", str.substring(4));
                    }
                    char charAt4 = str.charAt(4);
                    if (charAt3 < 'A' || charAt3 > 'Z' || charAt4 < 'A' || charAt4 > 'Z') {
                        StringBuffer stringBuffer2 = new StringBuffer();
                        stringBuffer2.append("Invalid locale format: ");
                        stringBuffer2.append(str);
                        throw new IllegalArgumentException(stringBuffer2.toString());
                    } else if (length == 5) {
                        return new Locale(str.substring(0, 2), str.substring(3, 5));
                    } else {
                        if (str.charAt(5) == '_') {
                            return new Locale(str.substring(0, 2), str.substring(3, 5), str.substring(6));
                        }
                        StringBuffer stringBuffer3 = new StringBuffer();
                        stringBuffer3.append("Invalid locale format: ");
                        stringBuffer3.append(str);
                        throw new IllegalArgumentException(stringBuffer3.toString());
                    }
                } else {
                    StringBuffer stringBuffer4 = new StringBuffer();
                    stringBuffer4.append("Invalid locale format: ");
                    stringBuffer4.append(str);
                    throw new IllegalArgumentException(stringBuffer4.toString());
                }
            }
        } else {
            StringBuffer stringBuffer5 = new StringBuffer();
            stringBuffer5.append("Invalid locale format: ");
            stringBuffer5.append(str);
            throw new IllegalArgumentException(stringBuffer5.toString());
        }
    }

    public static List a(Locale locale) {
        return a(locale, locale);
    }

    public static List a(Locale locale, Locale locale2) {
        ArrayList arrayList = new ArrayList(4);
        if (locale != null) {
            arrayList.add(locale);
            if (locale.getVariant().length() > 0) {
                arrayList.add(new Locale(locale.getLanguage(), locale.getCountry()));
            }
            if (locale.getCountry().length() > 0) {
                arrayList.add(new Locale(locale.getLanguage(), ""));
            }
            if (!arrayList.contains(locale2)) {
                arrayList.add(locale2);
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    public static List a() {
        if (f3368a == null) {
            c();
        }
        return f3368a;
    }

    private static synchronized void c() {
        synchronized (LocaleUtils.class) {
            if (f3368a == null) {
                f3368a = Collections.unmodifiableList(Arrays.asList(Locale.getAvailableLocales()));
            }
        }
    }

    public static Set b() {
        if (b == null) {
            d();
        }
        return b;
    }

    private static synchronized void d() {
        synchronized (LocaleUtils.class) {
            if (b == null) {
                b = Collections.unmodifiableSet(new HashSet(a()));
            }
        }
    }

    public static boolean b(Locale locale) {
        return a().contains(locale);
    }

    public static List b(String str) {
        List list = (List) c.get(str);
        if (list == null) {
            if (str != null) {
                ArrayList arrayList = new ArrayList();
                List a2 = a();
                for (int i = 0; i < a2.size(); i++) {
                    Locale locale = (Locale) a2.get(i);
                    if (str.equals(locale.getCountry()) && locale.getVariant().length() == 0) {
                        arrayList.add(locale);
                    }
                }
                list = Collections.unmodifiableList(arrayList);
            } else {
                list = Collections.EMPTY_LIST;
            }
            c.put(str, list);
        }
        return list;
    }

    public static List c(String str) {
        List list = (List) d.get(str);
        if (list == null) {
            if (str != null) {
                ArrayList arrayList = new ArrayList();
                List a2 = a();
                for (int i = 0; i < a2.size(); i++) {
                    Locale locale = (Locale) a2.get(i);
                    if (str.equals(locale.getLanguage()) && locale.getCountry().length() != 0 && locale.getVariant().length() == 0) {
                        arrayList.add(locale);
                    }
                }
                list = Collections.unmodifiableList(arrayList);
            } else {
                list = Collections.EMPTY_LIST;
            }
            d.put(str, list);
        }
        return list;
    }
}
