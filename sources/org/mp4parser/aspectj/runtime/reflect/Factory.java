package org.mp4parser.aspectj.runtime.reflect;

import java.util.Hashtable;
import java.util.StringTokenizer;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.lang.reflect.AdviceSignature;
import org.mp4parser.aspectj.lang.reflect.CatchClauseSignature;
import org.mp4parser.aspectj.lang.reflect.ConstructorSignature;
import org.mp4parser.aspectj.lang.reflect.FieldSignature;
import org.mp4parser.aspectj.lang.reflect.InitializerSignature;
import org.mp4parser.aspectj.lang.reflect.LockSignature;
import org.mp4parser.aspectj.lang.reflect.MethodSignature;
import org.mp4parser.aspectj.lang.reflect.SourceLocation;
import org.mp4parser.aspectj.lang.reflect.UnlockSignature;
import org.mp4parser.aspectj.runtime.reflect.JoinPointImpl;

public final class Factory {
    static Hashtable e = new Hashtable();
    static Class f;
    private static Object[] g = new Object[0];

    /* renamed from: a  reason: collision with root package name */
    Class f3771a;
    ClassLoader b;
    String c;
    int d = 0;

    static {
        e.put("void", Void.TYPE);
        e.put("boolean", Boolean.TYPE);
        e.put("byte", Byte.TYPE);
        e.put("char", Character.TYPE);
        e.put("short", Short.TYPE);
        e.put("int", Integer.TYPE);
        e.put("long", Long.TYPE);
        e.put("float", Float.TYPE);
        e.put("double", Double.TYPE);
    }

    static Class a(String str, ClassLoader classLoader) {
        if (str.equals("*")) {
            return null;
        }
        Class cls = (Class) e.get(str);
        if (cls != null) {
            return cls;
        }
        if (classLoader != null) {
            return Class.forName(str, false, classLoader);
        }
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException unused) {
            if (f != null) {
                return f;
            }
            Class i = i("java.lang.ClassNotFoundException");
            f = i;
            return i;
        }
    }

    static Class i(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e2) {
            throw new NoClassDefFoundError(e2.getMessage());
        }
    }

    public Factory(String str, Class cls) {
        this.c = str;
        this.f3771a = cls;
        this.b = cls.getClassLoader();
    }

    public JoinPoint.StaticPart a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i) {
        MethodSignature a2 = a(str2, str3, str4, str5, str6, str7, str8);
        int i2 = this.d;
        this.d = i2 + 1;
        String str9 = str;
        return new JoinPointImpl.StaticPartImpl(i2, str, a2, a(i, -1));
    }

    public JoinPoint.StaticPart a(String str, String str2, String str3, String str4, String str5, String str6, String str7, int i) {
        MethodSignature a2 = a(str2, str3, str4, str5, str6, "", str7);
        int i2 = this.d;
        this.d = i2 + 1;
        String str8 = str;
        return new JoinPointImpl.StaticPartImpl(i2, str, a2, a(i, -1));
    }

    public JoinPoint.StaticPart a(String str, Signature signature, SourceLocation sourceLocation) {
        int i = this.d;
        this.d = i + 1;
        return new JoinPointImpl.StaticPartImpl(i, str, signature, sourceLocation);
    }

    public JoinPoint.StaticPart a(String str, Signature signature, int i, int i2) {
        int i3 = this.d;
        this.d = i3 + 1;
        return new JoinPointImpl.StaticPartImpl(i3, str, signature, a(i, i2));
    }

    public JoinPoint.StaticPart a(String str, Signature signature, int i) {
        int i2 = this.d;
        this.d = i2 + 1;
        return new JoinPointImpl.StaticPartImpl(i2, str, signature, a(i, -1));
    }

    public JoinPoint.EnclosingStaticPart b(String str, Signature signature, SourceLocation sourceLocation) {
        int i = this.d;
        this.d = i + 1;
        return new JoinPointImpl.EnclosingStaticPartImpl(i, str, signature, sourceLocation);
    }

    public JoinPoint.EnclosingStaticPart b(String str, Signature signature, int i, int i2) {
        int i3 = this.d;
        this.d = i3 + 1;
        return new JoinPointImpl.EnclosingStaticPartImpl(i3, str, signature, a(i, i2));
    }

    public JoinPoint.EnclosingStaticPart b(String str, Signature signature, int i) {
        int i2 = this.d;
        this.d = i2 + 1;
        return new JoinPointImpl.EnclosingStaticPartImpl(i2, str, signature, a(i, -1));
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v0, resolved type: org.mp4parser.aspectj.runtime.reflect.ConstructorSignatureImpl} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: org.mp4parser.aspectj.runtime.reflect.ConstructorSignatureImpl} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v9, resolved type: org.mp4parser.aspectj.runtime.reflect.MethodSignatureImpl} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v3, resolved type: org.mp4parser.aspectj.runtime.reflect.ConstructorSignatureImpl} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.mp4parser.aspectj.lang.JoinPoint.StaticPart a(java.lang.reflect.Member r9) {
        /*
            boolean r0 = r9 instanceof java.lang.reflect.Method
            if (r0 == 0) goto L_0x002f
            java.lang.reflect.Method r9 = (java.lang.reflect.Method) r9
            org.mp4parser.aspectj.runtime.reflect.MethodSignatureImpl r8 = new org.mp4parser.aspectj.runtime.reflect.MethodSignatureImpl
            int r1 = r9.getModifiers()
            java.lang.String r2 = r9.getName()
            java.lang.Class r3 = r9.getDeclaringClass()
            java.lang.Class[] r4 = r9.getParameterTypes()
            java.lang.Class[] r0 = r9.getParameterTypes()
            int r0 = r0.length
            java.lang.String[] r5 = new java.lang.String[r0]
            java.lang.Class[] r6 = r9.getExceptionTypes()
            java.lang.Class r7 = r9.getReturnType()
            r0 = r8
            r0.<init>(r1, r2, r3, r4, r5, r6, r7)
            java.lang.String r9 = "method-execution"
            r6 = r8
            goto L_0x0054
        L_0x002f:
            boolean r0 = r9 instanceof java.lang.reflect.Constructor
            if (r0 == 0) goto L_0x005c
            java.lang.reflect.Constructor r9 = (java.lang.reflect.Constructor) r9
            org.mp4parser.aspectj.runtime.reflect.ConstructorSignatureImpl r6 = new org.mp4parser.aspectj.runtime.reflect.ConstructorSignatureImpl
            int r1 = r9.getModifiers()
            java.lang.Class r2 = r9.getDeclaringClass()
            java.lang.Class[] r3 = r9.getParameterTypes()
            java.lang.Class[] r0 = r9.getParameterTypes()
            int r0 = r0.length
            java.lang.String[] r4 = new java.lang.String[r0]
            java.lang.Class[] r5 = r9.getExceptionTypes()
            r0 = r6
            r0.<init>(r1, r2, r3, r4, r5)
            java.lang.String r9 = "constructor-execution"
        L_0x0054:
            org.mp4parser.aspectj.runtime.reflect.JoinPointImpl$EnclosingStaticPartImpl r0 = new org.mp4parser.aspectj.runtime.reflect.JoinPointImpl$EnclosingStaticPartImpl
            r1 = -1
            r2 = 0
            r0.<init>(r1, r9, r6, r2)
            return r0
        L_0x005c:
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "member must be either a method or constructor"
            r9.<init>(r0)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mp4parser.aspectj.runtime.reflect.Factory.a(java.lang.reflect.Member):org.mp4parser.aspectj.lang.JoinPoint$StaticPart");
    }

    public static JoinPoint a(JoinPoint.StaticPart staticPart, Object obj, Object obj2) {
        return new JoinPointImpl(staticPart, obj, obj2, g);
    }

    public static JoinPoint a(JoinPoint.StaticPart staticPart, Object obj, Object obj2, Object obj3) {
        return new JoinPointImpl(staticPart, obj, obj2, new Object[]{obj3});
    }

    public static JoinPoint a(JoinPoint.StaticPart staticPart, Object obj, Object obj2, Object obj3, Object obj4) {
        return new JoinPointImpl(staticPart, obj, obj2, new Object[]{obj3, obj4});
    }

    public static JoinPoint a(JoinPoint.StaticPart staticPart, Object obj, Object obj2, Object[] objArr) {
        return new JoinPointImpl(staticPart, obj, obj2, objArr);
    }

    public MethodSignature a(String str) {
        MethodSignatureImpl methodSignatureImpl = new MethodSignatureImpl(str);
        methodSignatureImpl.a(this.b);
        return methodSignatureImpl;
    }

    public MethodSignature a(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        String str8 = str;
        int parseInt = Integer.parseInt(str, 16);
        String str9 = str3;
        Class a2 = a(str3, this.b);
        String str10 = str4;
        StringTokenizer stringTokenizer = new StringTokenizer(str4, ":");
        int countTokens = stringTokenizer.countTokens();
        Class[] clsArr = new Class[countTokens];
        for (int i = 0; i < countTokens; i++) {
            clsArr[i] = a(stringTokenizer.nextToken(), this.b);
        }
        StringTokenizer stringTokenizer2 = new StringTokenizer(str5, ":");
        int countTokens2 = stringTokenizer2.countTokens();
        String[] strArr = new String[countTokens2];
        for (int i2 = 0; i2 < countTokens2; i2++) {
            strArr[i2] = stringTokenizer2.nextToken();
        }
        StringTokenizer stringTokenizer3 = new StringTokenizer(str6, ":");
        int countTokens3 = stringTokenizer3.countTokens();
        Class[] clsArr2 = new Class[countTokens3];
        for (int i3 = 0; i3 < countTokens3; i3++) {
            clsArr2[i3] = a(stringTokenizer3.nextToken(), this.b);
        }
        return new MethodSignatureImpl(parseInt, str2, a2, clsArr, strArr, clsArr2, a(str7, this.b));
    }

    public MethodSignature a(int i, String str, Class cls, Class[] clsArr, String[] strArr, Class[] clsArr2, Class cls2) {
        MethodSignatureImpl methodSignatureImpl = new MethodSignatureImpl(i, str, cls, clsArr, strArr, clsArr2, cls2);
        methodSignatureImpl.a(this.b);
        return methodSignatureImpl;
    }

    public ConstructorSignature b(String str) {
        ConstructorSignatureImpl constructorSignatureImpl = new ConstructorSignatureImpl(str);
        constructorSignatureImpl.a(this.b);
        return constructorSignatureImpl;
    }

    public ConstructorSignature a(String str, String str2, String str3, String str4, String str5) {
        int parseInt = Integer.parseInt(str, 16);
        Class a2 = a(str2, this.b);
        StringTokenizer stringTokenizer = new StringTokenizer(str3, ":");
        int countTokens = stringTokenizer.countTokens();
        Class[] clsArr = new Class[countTokens];
        for (int i = 0; i < countTokens; i++) {
            clsArr[i] = a(stringTokenizer.nextToken(), this.b);
        }
        StringTokenizer stringTokenizer2 = new StringTokenizer(str4, ":");
        int countTokens2 = stringTokenizer2.countTokens();
        String[] strArr = new String[countTokens2];
        for (int i2 = 0; i2 < countTokens2; i2++) {
            strArr[i2] = stringTokenizer2.nextToken();
        }
        StringTokenizer stringTokenizer3 = new StringTokenizer(str5, ":");
        int countTokens3 = stringTokenizer3.countTokens();
        Class[] clsArr2 = new Class[countTokens3];
        for (int i3 = 0; i3 < countTokens3; i3++) {
            clsArr2[i3] = a(stringTokenizer3.nextToken(), this.b);
        }
        ConstructorSignatureImpl constructorSignatureImpl = new ConstructorSignatureImpl(parseInt, a2, clsArr, strArr, clsArr2);
        constructorSignatureImpl.a(this.b);
        return constructorSignatureImpl;
    }

    public ConstructorSignature a(int i, Class cls, Class[] clsArr, String[] strArr, Class[] clsArr2) {
        ConstructorSignatureImpl constructorSignatureImpl = new ConstructorSignatureImpl(i, cls, clsArr, strArr, clsArr2);
        constructorSignatureImpl.a(this.b);
        return constructorSignatureImpl;
    }

    public FieldSignature c(String str) {
        FieldSignatureImpl fieldSignatureImpl = new FieldSignatureImpl(str);
        fieldSignatureImpl.a(this.b);
        return fieldSignatureImpl;
    }

    public FieldSignature a(String str, String str2, String str3, String str4) {
        FieldSignatureImpl fieldSignatureImpl = new FieldSignatureImpl(Integer.parseInt(str, 16), str2, a(str3, this.b), a(str4, this.b));
        fieldSignatureImpl.a(this.b);
        return fieldSignatureImpl;
    }

    public FieldSignature a(int i, String str, Class cls, Class cls2) {
        FieldSignatureImpl fieldSignatureImpl = new FieldSignatureImpl(i, str, cls, cls2);
        fieldSignatureImpl.a(this.b);
        return fieldSignatureImpl;
    }

    public AdviceSignature d(String str) {
        AdviceSignatureImpl adviceSignatureImpl = new AdviceSignatureImpl(str);
        adviceSignatureImpl.a(this.b);
        return adviceSignatureImpl;
    }

    public AdviceSignature b(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        String str8 = str;
        int parseInt = Integer.parseInt(str, 16);
        String str9 = str3;
        Class a2 = a(str3, this.b);
        String str10 = str4;
        StringTokenizer stringTokenizer = new StringTokenizer(str4, ":");
        int countTokens = stringTokenizer.countTokens();
        Class[] clsArr = new Class[countTokens];
        for (int i = 0; i < countTokens; i++) {
            clsArr[i] = a(stringTokenizer.nextToken(), this.b);
        }
        StringTokenizer stringTokenizer2 = new StringTokenizer(str5, ":");
        int countTokens2 = stringTokenizer2.countTokens();
        String[] strArr = new String[countTokens2];
        for (int i2 = 0; i2 < countTokens2; i2++) {
            strArr[i2] = stringTokenizer2.nextToken();
        }
        StringTokenizer stringTokenizer3 = new StringTokenizer(str6, ":");
        int countTokens3 = stringTokenizer3.countTokens();
        Class[] clsArr2 = new Class[countTokens3];
        for (int i3 = 0; i3 < countTokens3; i3++) {
            clsArr2[i3] = a(stringTokenizer3.nextToken(), this.b);
        }
        String str11 = str2;
        AdviceSignatureImpl adviceSignatureImpl = new AdviceSignatureImpl(parseInt, str11, a2, clsArr, strArr, clsArr2, a(str7, this.b));
        adviceSignatureImpl.a(this.b);
        return adviceSignatureImpl;
    }

    public AdviceSignature b(int i, String str, Class cls, Class[] clsArr, String[] strArr, Class[] clsArr2, Class cls2) {
        AdviceSignatureImpl adviceSignatureImpl = new AdviceSignatureImpl(i, str, cls, clsArr, strArr, clsArr2, cls2);
        adviceSignatureImpl.a(this.b);
        return adviceSignatureImpl;
    }

    public InitializerSignature e(String str) {
        InitializerSignatureImpl initializerSignatureImpl = new InitializerSignatureImpl(str);
        initializerSignatureImpl.a(this.b);
        return initializerSignatureImpl;
    }

    public InitializerSignature a(String str, String str2) {
        InitializerSignatureImpl initializerSignatureImpl = new InitializerSignatureImpl(Integer.parseInt(str, 16), a(str2, this.b));
        initializerSignatureImpl.a(this.b);
        return initializerSignatureImpl;
    }

    public InitializerSignature a(int i, Class cls) {
        InitializerSignatureImpl initializerSignatureImpl = new InitializerSignatureImpl(i, cls);
        initializerSignatureImpl.a(this.b);
        return initializerSignatureImpl;
    }

    public CatchClauseSignature f(String str) {
        CatchClauseSignatureImpl catchClauseSignatureImpl = new CatchClauseSignatureImpl(str);
        catchClauseSignatureImpl.a(this.b);
        return catchClauseSignatureImpl;
    }

    public CatchClauseSignature a(String str, String str2, String str3) {
        CatchClauseSignatureImpl catchClauseSignatureImpl = new CatchClauseSignatureImpl(a(str, this.b), a(new StringTokenizer(str2, ":").nextToken(), this.b), new StringTokenizer(str3, ":").nextToken());
        catchClauseSignatureImpl.a(this.b);
        return catchClauseSignatureImpl;
    }

    public CatchClauseSignature a(Class cls, Class cls2, String str) {
        CatchClauseSignatureImpl catchClauseSignatureImpl = new CatchClauseSignatureImpl(cls, cls2, str);
        catchClauseSignatureImpl.a(this.b);
        return catchClauseSignatureImpl;
    }

    public LockSignature g(String str) {
        LockSignatureImpl lockSignatureImpl = new LockSignatureImpl(str);
        lockSignatureImpl.a(this.b);
        return lockSignatureImpl;
    }

    public LockSignature a() {
        LockSignatureImpl lockSignatureImpl = new LockSignatureImpl(a("Ljava/lang/Object;", this.b));
        lockSignatureImpl.a(this.b);
        return lockSignatureImpl;
    }

    public LockSignature a(Class cls) {
        LockSignatureImpl lockSignatureImpl = new LockSignatureImpl(cls);
        lockSignatureImpl.a(this.b);
        return lockSignatureImpl;
    }

    public UnlockSignature h(String str) {
        UnlockSignatureImpl unlockSignatureImpl = new UnlockSignatureImpl(str);
        unlockSignatureImpl.a(this.b);
        return unlockSignatureImpl;
    }

    public UnlockSignature b() {
        UnlockSignatureImpl unlockSignatureImpl = new UnlockSignatureImpl(a("Ljava/lang/Object;", this.b));
        unlockSignatureImpl.a(this.b);
        return unlockSignatureImpl;
    }

    public UnlockSignature b(Class cls) {
        UnlockSignatureImpl unlockSignatureImpl = new UnlockSignatureImpl(cls);
        unlockSignatureImpl.a(this.b);
        return unlockSignatureImpl;
    }

    public SourceLocation a(int i, int i2) {
        return new SourceLocationImpl(this.f3771a, this.c, i);
    }
}
