package org.apache.commons.lang.enums;

import com.taobao.weex.el.parse.Operators;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.StringUtils;

public abstract class Enum implements Serializable, Comparable {

    /* renamed from: a  reason: collision with root package name */
    private static final Map f3384a = Collections.unmodifiableMap(new HashMap(0));
    private static Map b = new WeakHashMap();
    static Class class$org$apache$commons$lang$enums$Enum = null;
    static Class class$org$apache$commons$lang$enums$ValuedEnum = null;
    private static final long serialVersionUID = -487045951170455942L;
    private final transient int c;
    private final String iName;
    protected transient String iToString = null;

    private static class Entry {

        /* renamed from: a  reason: collision with root package name */
        final Map f3385a = new HashMap();
        final Map b = Collections.unmodifiableMap(this.f3385a);
        final List c = new ArrayList(25);
        final List d = Collections.unmodifiableList(this.c);

        protected Entry() {
        }
    }

    protected Enum(String str) {
        a(str);
        this.iName = str;
        this.c = getEnumClass().hashCode() + 7 + (str.hashCode() * 3);
    }

    private void a(String str) {
        Class cls;
        Entry entry;
        Class cls2;
        Class cls3;
        if (!StringUtils.a(str)) {
            Class enumClass = getEnumClass();
            if (enumClass != null) {
                Class cls4 = getClass();
                boolean z = false;
                while (true) {
                    if (cls4 == null) {
                        break;
                    }
                    if (class$org$apache$commons$lang$enums$Enum == null) {
                        cls2 = class$("org.apache.commons.lang.enums.Enum");
                        class$org$apache$commons$lang$enums$Enum = cls2;
                    } else {
                        cls2 = class$org$apache$commons$lang$enums$Enum;
                    }
                    if (cls4 == cls2) {
                        break;
                    }
                    if (class$org$apache$commons$lang$enums$ValuedEnum == null) {
                        cls3 = class$("org.apache.commons.lang.enums.ValuedEnum");
                        class$org$apache$commons$lang$enums$ValuedEnum = cls3;
                    } else {
                        cls3 = class$org$apache$commons$lang$enums$ValuedEnum;
                    }
                    if (cls4 == cls3) {
                        break;
                    } else if (cls4 == enumClass) {
                        z = true;
                        break;
                    } else {
                        cls4 = cls4.getSuperclass();
                    }
                }
                if (z) {
                    if (class$org$apache$commons$lang$enums$Enum == null) {
                        cls = class$("org.apache.commons.lang.enums.Enum");
                        class$org$apache$commons$lang$enums$Enum = cls;
                    } else {
                        cls = class$org$apache$commons$lang$enums$Enum;
                    }
                    synchronized (cls) {
                        entry = (Entry) b.get(enumClass);
                        if (entry == null) {
                            entry = b(enumClass);
                            WeakHashMap weakHashMap = new WeakHashMap();
                            weakHashMap.putAll(b);
                            weakHashMap.put(enumClass, entry);
                            b = weakHashMap;
                        }
                    }
                    if (!entry.f3385a.containsKey(str)) {
                        entry.f3385a.put(str, this);
                        entry.c.add(this);
                        return;
                    }
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("The Enum name must be unique, '");
                    stringBuffer.append(str);
                    stringBuffer.append("' has already been added");
                    throw new IllegalArgumentException(stringBuffer.toString());
                }
                throw new IllegalArgumentException("getEnumClass() must return a superclass of this class");
            }
            throw new IllegalArgumentException("getEnumClass() must not be null");
        }
        throw new IllegalArgumentException("The Enum name must not be empty or null");
    }

    static Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    /* access modifiers changed from: protected */
    public Object readResolve() {
        Entry entry = (Entry) b.get(getEnumClass());
        if (entry == null) {
            return null;
        }
        return entry.f3385a.get(getName());
    }

    protected static Enum getEnum(Class cls, String str) {
        Entry a2 = a(cls);
        if (a2 == null) {
            return null;
        }
        return (Enum) a2.f3385a.get(str);
    }

    protected static Map getEnumMap(Class cls) {
        Entry a2 = a(cls);
        if (a2 == null) {
            return f3384a;
        }
        return a2.b;
    }

    protected static List getEnumList(Class cls) {
        Entry a2 = a(cls);
        if (a2 == null) {
            return Collections.EMPTY_LIST;
        }
        return a2.d;
    }

    protected static Iterator iterator(Class cls) {
        return getEnumList(cls).iterator();
    }

    private static Entry a(Class cls) {
        Class cls2;
        if (cls != null) {
            if (class$org$apache$commons$lang$enums$Enum == null) {
                cls2 = class$("org.apache.commons.lang.enums.Enum");
                class$org$apache$commons$lang$enums$Enum = cls2;
            } else {
                cls2 = class$org$apache$commons$lang$enums$Enum;
            }
            if (cls2.isAssignableFrom(cls)) {
                Entry entry = (Entry) b.get(cls);
                if (entry == null) {
                    try {
                        Class.forName(cls.getName(), true, cls.getClassLoader());
                        return (Entry) b.get(cls);
                    } catch (Exception unused) {
                    }
                }
                return entry;
            }
            throw new IllegalArgumentException("The Class must be a subclass of Enum");
        }
        throw new IllegalArgumentException("The Enum Class must not be null");
    }

    private static Entry b(Class cls) {
        Class cls2;
        Class cls3;
        Entry entry = new Entry();
        Class superclass = cls.getSuperclass();
        while (true) {
            if (superclass == null) {
                break;
            }
            if (class$org$apache$commons$lang$enums$Enum == null) {
                cls2 = class$("org.apache.commons.lang.enums.Enum");
                class$org$apache$commons$lang$enums$Enum = cls2;
            } else {
                cls2 = class$org$apache$commons$lang$enums$Enum;
            }
            if (superclass == cls2) {
                break;
            }
            if (class$org$apache$commons$lang$enums$ValuedEnum == null) {
                cls3 = class$("org.apache.commons.lang.enums.ValuedEnum");
                class$org$apache$commons$lang$enums$ValuedEnum = cls3;
            } else {
                cls3 = class$org$apache$commons$lang$enums$ValuedEnum;
            }
            if (superclass == cls3) {
                break;
            }
            Entry entry2 = (Entry) b.get(superclass);
            if (entry2 != null) {
                entry.c.addAll(entry2.c);
                entry.f3385a.putAll(entry2.f3385a);
                break;
            }
            superclass = superclass.getSuperclass();
        }
        return entry;
    }

    public final String getName() {
        return this.iName;
    }

    public Class getEnumClass() {
        return getClass();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj.getClass() == getClass()) {
            return this.iName.equals(((Enum) obj).iName);
        }
        if (!obj.getClass().getName().equals(getClass().getName())) {
            return false;
        }
        return this.iName.equals(a(obj));
    }

    public final int hashCode() {
        return this.c;
    }

    public int compareTo(Object obj) {
        if (obj == this) {
            return 0;
        }
        if (obj.getClass() == getClass()) {
            return this.iName.compareTo(((Enum) obj).iName);
        }
        if (obj.getClass().getName().equals(getClass().getName())) {
            return this.iName.compareTo(a(obj));
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Different enum class '");
        stringBuffer.append(ClassUtils.a((Class) obj.getClass()));
        stringBuffer.append("'");
        throw new ClassCastException(stringBuffer.toString());
    }

    private String a(Object obj) {
        try {
            return (String) obj.getClass().getMethod("getName", (Class[]) null).invoke(obj, (Object[]) null);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            throw new IllegalStateException("This should not happen");
        }
    }

    public String toString() {
        if (this.iToString == null) {
            String a2 = ClassUtils.a(getEnumClass());
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(a2);
            stringBuffer.append(Operators.ARRAY_START_STR);
            stringBuffer.append(getName());
            stringBuffer.append(Operators.ARRAY_END_STR);
            this.iToString = stringBuffer.toString();
        }
        return this.iToString;
    }
}
