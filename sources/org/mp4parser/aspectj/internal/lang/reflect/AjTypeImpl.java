package org.mp4parser.aspectj.internal.lang.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import org.mp4parser.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
import org.mp4parser.aspectj.internal.lang.annotation.ajcDeclareEoW;
import org.mp4parser.aspectj.internal.lang.annotation.ajcDeclareParents;
import org.mp4parser.aspectj.internal.lang.annotation.ajcDeclarePrecedence;
import org.mp4parser.aspectj.internal.lang.annotation.ajcDeclareSoft;
import org.mp4parser.aspectj.internal.lang.annotation.ajcITD;
import org.mp4parser.aspectj.internal.lang.annotation.ajcPrivileged;
import org.mp4parser.aspectj.lang.annotation.After;
import org.mp4parser.aspectj.lang.annotation.AfterReturning;
import org.mp4parser.aspectj.lang.annotation.AfterThrowing;
import org.mp4parser.aspectj.lang.annotation.Around;
import org.mp4parser.aspectj.lang.annotation.Aspect;
import org.mp4parser.aspectj.lang.annotation.Before;
import org.mp4parser.aspectj.lang.annotation.DeclareError;
import org.mp4parser.aspectj.lang.annotation.DeclareParents;
import org.mp4parser.aspectj.lang.annotation.DeclareWarning;
import org.mp4parser.aspectj.lang.reflect.Advice;
import org.mp4parser.aspectj.lang.reflect.AdviceKind;
import org.mp4parser.aspectj.lang.reflect.AjType;
import org.mp4parser.aspectj.lang.reflect.AjTypeSystem;
import org.mp4parser.aspectj.lang.reflect.DeclareAnnotation;
import org.mp4parser.aspectj.lang.reflect.DeclareErrorOrWarning;
import org.mp4parser.aspectj.lang.reflect.DeclarePrecedence;
import org.mp4parser.aspectj.lang.reflect.DeclareSoft;
import org.mp4parser.aspectj.lang.reflect.InterTypeConstructorDeclaration;
import org.mp4parser.aspectj.lang.reflect.InterTypeFieldDeclaration;
import org.mp4parser.aspectj.lang.reflect.InterTypeMethodDeclaration;
import org.mp4parser.aspectj.lang.reflect.NoSuchAdviceException;
import org.mp4parser.aspectj.lang.reflect.NoSuchPointcutException;
import org.mp4parser.aspectj.lang.reflect.PerClause;
import org.mp4parser.aspectj.lang.reflect.PerClauseKind;
import org.mp4parser.aspectj.lang.reflect.Pointcut;

public class AjTypeImpl<T> implements AjType<T> {

    /* renamed from: a  reason: collision with root package name */
    private static final String f3737a = "ajc$";
    private Class<T> b;
    private Pointcut[] c = null;
    private Pointcut[] d = null;
    private Advice[] e = null;
    private Advice[] f = null;
    private InterTypeMethodDeclaration[] g = null;
    private InterTypeMethodDeclaration[] h = null;
    private InterTypeFieldDeclaration[] i = null;
    private InterTypeFieldDeclaration[] j = null;
    private InterTypeConstructorDeclaration[] k = null;
    private InterTypeConstructorDeclaration[] l = null;

    private void b(List<InterTypeFieldDeclaration> list, boolean z) {
    }

    public AjTypeImpl(Class<T> cls) {
        this.b = cls;
    }

    public String a() {
        return this.b.getName();
    }

    public Package b() {
        return this.b.getPackage();
    }

    public AjType<?>[] c() {
        return a((Class<?>[]) this.b.getInterfaces());
    }

    public int d() {
        return this.b.getModifiers();
    }

    public Class<T> e() {
        return this.b;
    }

    public AjType<? super T> f() {
        Class<? super T> superclass = this.b.getSuperclass();
        if (superclass == null) {
            return null;
        }
        return new AjTypeImpl(superclass);
    }

    public Type g() {
        return this.b.getGenericSuperclass();
    }

    public Method h() {
        return this.b.getEnclosingMethod();
    }

    public Constructor i() {
        return this.b.getEnclosingConstructor();
    }

    public AjType<?> j() {
        Class<?> enclosingClass = this.b.getEnclosingClass();
        if (enclosingClass != null) {
            return new AjTypeImpl(enclosingClass);
        }
        return null;
    }

    public AjType<?> k() {
        Class<?> declaringClass = this.b.getDeclaringClass();
        if (declaringClass != null) {
            return new AjTypeImpl(declaringClass);
        }
        return null;
    }

    public PerClause l() {
        if (!P()) {
            return null;
        }
        String value = ((Aspect) this.b.getAnnotation(Aspect.class)).value();
        if (value.equals("")) {
            if (f().P()) {
                return f().l();
            }
            return new PerClauseImpl(PerClauseKind.SINGLETON);
        } else if (value.startsWith("perthis(")) {
            return new PointcutBasedPerClauseImpl(PerClauseKind.PERTHIS, value.substring("perthis(".length(), value.length() - 1));
        } else {
            if (value.startsWith("pertarget(")) {
                return new PointcutBasedPerClauseImpl(PerClauseKind.PERTARGET, value.substring("pertarget(".length(), value.length() - 1));
            }
            if (value.startsWith("percflow(")) {
                return new PointcutBasedPerClauseImpl(PerClauseKind.PERCFLOW, value.substring("percflow(".length(), value.length() - 1));
            }
            if (value.startsWith("percflowbelow(")) {
                return new PointcutBasedPerClauseImpl(PerClauseKind.PERCFLOWBELOW, value.substring("percflowbelow(".length(), value.length() - 1));
            }
            if (value.startsWith("pertypewithin")) {
                return new TypePatternBasedPerClauseImpl(PerClauseKind.PERTYPEWITHIN, value.substring("pertypewithin(".length(), value.length() - 1));
            }
            throw new IllegalStateException("Per-clause not recognized: " + value);
        }
    }

    public boolean isAnnotationPresent(Class<? extends Annotation> cls) {
        return this.b.isAnnotationPresent(cls);
    }

    public <A extends Annotation> A getAnnotation(Class<A> cls) {
        return this.b.getAnnotation(cls);
    }

    public Annotation[] getAnnotations() {
        return this.b.getAnnotations();
    }

    public Annotation[] getDeclaredAnnotations() {
        return this.b.getDeclaredAnnotations();
    }

    public AjType<?>[] m() {
        return a((Class<?>[]) this.b.getClasses());
    }

    public AjType<?>[] n() {
        return a((Class<?>[]) this.b.getDeclaredClasses());
    }

    public Constructor a(AjType<?>... ajTypeArr) throws NoSuchMethodException {
        return this.b.getConstructor(c(ajTypeArr));
    }

    public Constructor[] o() {
        return this.b.getConstructors();
    }

    public Constructor b(AjType<?>... ajTypeArr) throws NoSuchMethodException {
        return this.b.getDeclaredConstructor(c(ajTypeArr));
    }

    public Constructor[] p() {
        return this.b.getDeclaredConstructors();
    }

    public Field a(String str) throws NoSuchFieldException {
        Field declaredField = this.b.getDeclaredField(str);
        if (!declaredField.getName().startsWith(f3737a)) {
            return declaredField;
        }
        throw new NoSuchFieldException(str);
    }

    public Field[] q() {
        Field[] declaredFields = this.b.getDeclaredFields();
        ArrayList arrayList = new ArrayList();
        for (Field field : declaredFields) {
            if (!field.getName().startsWith(f3737a) && !field.isAnnotationPresent(DeclareWarning.class) && !field.isAnnotationPresent(DeclareError.class)) {
                arrayList.add(field);
            }
        }
        Field[] fieldArr = new Field[arrayList.size()];
        arrayList.toArray(fieldArr);
        return fieldArr;
    }

    public Field b(String str) throws NoSuchFieldException {
        Field field = this.b.getField(str);
        if (!field.getName().startsWith(f3737a)) {
            return field;
        }
        throw new NoSuchFieldException(str);
    }

    public Field[] r() {
        Field[] fields = this.b.getFields();
        ArrayList arrayList = new ArrayList();
        for (Field field : fields) {
            if (!field.getName().startsWith(f3737a) && !field.isAnnotationPresent(DeclareWarning.class) && !field.isAnnotationPresent(DeclareError.class)) {
                arrayList.add(field);
            }
        }
        Field[] fieldArr = new Field[arrayList.size()];
        arrayList.toArray(fieldArr);
        return fieldArr;
    }

    public Method a(String str, AjType<?>... ajTypeArr) throws NoSuchMethodException {
        Method declaredMethod = this.b.getDeclaredMethod(str, c(ajTypeArr));
        if (a(declaredMethod)) {
            return declaredMethod;
        }
        throw new NoSuchMethodException(str);
    }

    public Method b(String str, AjType<?>... ajTypeArr) throws NoSuchMethodException {
        Method method = this.b.getMethod(str, c(ajTypeArr));
        if (a(method)) {
            return method;
        }
        throw new NoSuchMethodException(str);
    }

    public Method[] s() {
        Method[] declaredMethods = this.b.getDeclaredMethods();
        ArrayList arrayList = new ArrayList();
        for (Method method : declaredMethods) {
            if (a(method)) {
                arrayList.add(method);
            }
        }
        Method[] methodArr = new Method[arrayList.size()];
        arrayList.toArray(methodArr);
        return methodArr;
    }

    public Method[] t() {
        Method[] methods = this.b.getMethods();
        ArrayList arrayList = new ArrayList();
        for (Method method : methods) {
            if (a(method)) {
                arrayList.add(method);
            }
        }
        Method[] methodArr = new Method[arrayList.size()];
        arrayList.toArray(methodArr);
        return methodArr;
    }

    private boolean a(Method method) {
        if (method.getName().startsWith(f3737a)) {
            return false;
        }
        if (method.getAnnotations().length == 0) {
            return true;
        }
        if (!method.isAnnotationPresent(org.mp4parser.aspectj.lang.annotation.Pointcut.class) && !method.isAnnotationPresent(Before.class) && !method.isAnnotationPresent(After.class) && !method.isAnnotationPresent(AfterReturning.class) && !method.isAnnotationPresent(AfterThrowing.class) && !method.isAnnotationPresent(Around.class)) {
            return true;
        }
        return false;
    }

    public Pointcut c(String str) throws NoSuchPointcutException {
        for (Pointcut pointcut : u()) {
            if (pointcut.b().equals(str)) {
                return pointcut;
            }
        }
        throw new NoSuchPointcutException(str);
    }

    public Pointcut d(String str) throws NoSuchPointcutException {
        for (Pointcut pointcut : v()) {
            if (pointcut.b().equals(str)) {
                return pointcut;
            }
        }
        throw new NoSuchPointcutException(str);
    }

    public Pointcut[] u() {
        if (this.c != null) {
            return this.c;
        }
        ArrayList arrayList = new ArrayList();
        for (Method b2 : this.b.getDeclaredMethods()) {
            Pointcut b3 = b(b2);
            if (b3 != null) {
                arrayList.add(b3);
            }
        }
        Pointcut[] pointcutArr = new Pointcut[arrayList.size()];
        arrayList.toArray(pointcutArr);
        this.c = pointcutArr;
        return pointcutArr;
    }

    public Pointcut[] v() {
        if (this.d != null) {
            return this.d;
        }
        ArrayList arrayList = new ArrayList();
        for (Method b2 : this.b.getMethods()) {
            Pointcut b3 = b(b2);
            if (b3 != null) {
                arrayList.add(b3);
            }
        }
        Pointcut[] pointcutArr = new Pointcut[arrayList.size()];
        arrayList.toArray(pointcutArr);
        this.d = pointcutArr;
        return pointcutArr;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0016, code lost:
        r1 = r1.substring(r1.indexOf(com.alibaba.android.arouter.utils.Consts.c) + 2, r1.length());
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.mp4parser.aspectj.lang.reflect.Pointcut b(java.lang.reflect.Method r9) {
        /*
            r8 = this;
            java.lang.Class<org.mp4parser.aspectj.lang.annotation.Pointcut> r0 = org.mp4parser.aspectj.lang.annotation.Pointcut.class
            java.lang.annotation.Annotation r0 = r9.getAnnotation(r0)
            org.mp4parser.aspectj.lang.annotation.Pointcut r0 = (org.mp4parser.aspectj.lang.annotation.Pointcut) r0
            if (r0 == 0) goto L_0x004d
            java.lang.String r1 = r9.getName()
            java.lang.String r2 = "ajc$"
            boolean r2 = r1.startsWith(r2)
            if (r2 == 0) goto L_0x0034
            java.lang.String r2 = "$$"
            int r2 = r1.indexOf(r2)
            int r2 = r2 + 2
            int r3 = r1.length()
            java.lang.String r1 = r1.substring(r2, r3)
            java.lang.String r2 = "$"
            int r2 = r1.indexOf(r2)
            r3 = -1
            if (r2 == r3) goto L_0x0034
            r3 = 0
            java.lang.String r1 = r1.substring(r3, r2)
        L_0x0034:
            r3 = r1
            org.mp4parser.aspectj.internal.lang.reflect.PointcutImpl r1 = new org.mp4parser.aspectj.internal.lang.reflect.PointcutImpl
            java.lang.String r4 = r0.value()
            java.lang.Class r2 = r9.getDeclaringClass()
            org.mp4parser.aspectj.lang.reflect.AjType r6 = org.mp4parser.aspectj.lang.reflect.AjTypeSystem.a(r2)
            java.lang.String r7 = r0.argNames()
            r2 = r1
            r5 = r9
            r2.<init>(r3, r4, r5, r6, r7)
            return r1
        L_0x004d:
            r9 = 0
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mp4parser.aspectj.internal.lang.reflect.AjTypeImpl.b(java.lang.reflect.Method):org.mp4parser.aspectj.lang.reflect.Pointcut");
    }

    public Advice[] a(AdviceKind... adviceKindArr) {
        EnumSet<E> enumSet;
        if (adviceKindArr.length == 0) {
            enumSet = EnumSet.allOf(AdviceKind.class);
        } else {
            EnumSet<E> noneOf = EnumSet.noneOf(AdviceKind.class);
            noneOf.addAll(Arrays.asList(adviceKindArr));
            enumSet = noneOf;
        }
        return a((Set) enumSet);
    }

    public Advice[] b(AdviceKind... adviceKindArr) {
        EnumSet<E> enumSet;
        if (adviceKindArr.length == 0) {
            enumSet = EnumSet.allOf(AdviceKind.class);
        } else {
            EnumSet<E> noneOf = EnumSet.noneOf(AdviceKind.class);
            noneOf.addAll(Arrays.asList(adviceKindArr));
            enumSet = noneOf;
        }
        return b((Set) enumSet);
    }

    private Advice[] a(Set set) {
        if (this.e == null) {
            S();
        }
        ArrayList arrayList = new ArrayList();
        for (Advice advice : this.e) {
            if (set.contains(advice.e())) {
                arrayList.add(advice);
            }
        }
        Advice[] adviceArr = new Advice[arrayList.size()];
        arrayList.toArray(adviceArr);
        return adviceArr;
    }

    private void S() {
        Method[] declaredMethods = this.b.getDeclaredMethods();
        ArrayList arrayList = new ArrayList();
        for (Method c2 : declaredMethods) {
            Advice c3 = c(c2);
            if (c3 != null) {
                arrayList.add(c3);
            }
        }
        this.e = new Advice[arrayList.size()];
        arrayList.toArray(this.e);
    }

    private Advice[] b(Set set) {
        if (this.f == null) {
            T();
        }
        ArrayList arrayList = new ArrayList();
        for (Advice advice : this.f) {
            if (set.contains(advice.e())) {
                arrayList.add(advice);
            }
        }
        Advice[] adviceArr = new Advice[arrayList.size()];
        arrayList.toArray(adviceArr);
        return adviceArr;
    }

    private void T() {
        Method[] methods = this.b.getMethods();
        ArrayList arrayList = new ArrayList();
        for (Method c2 : methods) {
            Advice c3 = c(c2);
            if (c3 != null) {
                arrayList.add(c3);
            }
        }
        this.f = new Advice[arrayList.size()];
        arrayList.toArray(this.f);
    }

    public Advice e(String str) throws NoSuchAdviceException {
        if (!str.equals("")) {
            if (this.f == null) {
                T();
            }
            for (Advice advice : this.f) {
                if (advice.f().equals(str)) {
                    return advice;
                }
            }
            throw new NoSuchAdviceException(str);
        }
        throw new IllegalArgumentException("use getAdvice(AdviceType...) instead for un-named advice");
    }

    public Advice f(String str) throws NoSuchAdviceException {
        if (!str.equals("")) {
            if (this.e == null) {
                S();
            }
            for (Advice advice : this.e) {
                if (advice.f().equals(str)) {
                    return advice;
                }
            }
            throw new NoSuchAdviceException(str);
        }
        throw new IllegalArgumentException("use getAdvice(AdviceType...) instead for un-named advice");
    }

    private Advice c(Method method) {
        if (method.getAnnotations().length == 0) {
            return null;
        }
        Before before = (Before) method.getAnnotation(Before.class);
        if (before != null) {
            return new AdviceImpl(method, before.value(), AdviceKind.BEFORE);
        }
        After after = (After) method.getAnnotation(After.class);
        if (after != null) {
            return new AdviceImpl(method, after.value(), AdviceKind.AFTER);
        }
        AfterReturning afterReturning = (AfterReturning) method.getAnnotation(AfterReturning.class);
        if (afterReturning != null) {
            String pointcut = afterReturning.pointcut();
            if (pointcut.equals("")) {
                pointcut = afterReturning.value();
            }
            return new AdviceImpl(method, pointcut, AdviceKind.AFTER_RETURNING, afterReturning.returning());
        }
        AfterThrowing afterThrowing = (AfterThrowing) method.getAnnotation(AfterThrowing.class);
        if (afterThrowing != null) {
            String pointcut2 = afterThrowing.pointcut();
            if (pointcut2 == null) {
                pointcut2 = afterThrowing.value();
            }
            return new AdviceImpl(method, pointcut2, AdviceKind.AFTER_THROWING, afterThrowing.throwing());
        }
        Around around = (Around) method.getAnnotation(Around.class);
        if (around != null) {
            return new AdviceImpl(method, around.value(), AdviceKind.AROUND);
        }
        return null;
    }

    public InterTypeMethodDeclaration a(String str, AjType<?> ajType, AjType<?>... ajTypeArr) throws NoSuchMethodException {
        for (InterTypeMethodDeclaration interTypeMethodDeclaration : w()) {
            try {
                if (interTypeMethodDeclaration.a().equals(str)) {
                    if (interTypeMethodDeclaration.e().equals(ajType)) {
                        AjType[] g2 = interTypeMethodDeclaration.g();
                        if (g2.length == ajTypeArr.length) {
                            int i2 = 0;
                            while (i2 < g2.length) {
                                if (g2[i2].equals(ajTypeArr[i2])) {
                                    i2++;
                                }
                            }
                            return interTypeMethodDeclaration;
                        }
                        continue;
                    } else {
                        continue;
                    }
                }
            } catch (ClassNotFoundException unused) {
            }
        }
        throw new NoSuchMethodException(str);
    }

    public InterTypeMethodDeclaration[] w() {
        if (this.g == null) {
            ArrayList arrayList = new ArrayList();
            for (Method method : this.b.getDeclaredMethods()) {
                if (method.getName().contains("ajc$interMethodDispatch1$") && method.isAnnotationPresent(ajcITD.class)) {
                    ajcITD ajcitd = (ajcITD) method.getAnnotation(ajcITD.class);
                    arrayList.add(new InterTypeMethodDeclarationImpl(this, ajcitd.targetType(), ajcitd.modifiers(), ajcitd.name(), method));
                }
            }
            a((List<InterTypeMethodDeclaration>) arrayList, false);
            this.g = new InterTypeMethodDeclaration[arrayList.size()];
            arrayList.toArray(this.g);
        }
        return this.g;
    }

    public InterTypeMethodDeclaration b(String str, AjType<?> ajType, AjType<?>... ajTypeArr) throws NoSuchMethodException {
        for (InterTypeMethodDeclaration interTypeMethodDeclaration : x()) {
            try {
                if (interTypeMethodDeclaration.a().equals(str)) {
                    if (interTypeMethodDeclaration.e().equals(ajType)) {
                        AjType[] g2 = interTypeMethodDeclaration.g();
                        if (g2.length == ajTypeArr.length) {
                            int i2 = 0;
                            while (i2 < g2.length) {
                                if (g2[i2].equals(ajTypeArr[i2])) {
                                    i2++;
                                }
                            }
                            return interTypeMethodDeclaration;
                        }
                        continue;
                    } else {
                        continue;
                    }
                }
            } catch (ClassNotFoundException unused) {
            }
        }
        throw new NoSuchMethodException(str);
    }

    public InterTypeMethodDeclaration[] x() {
        if (this.h == null) {
            ArrayList arrayList = new ArrayList();
            for (Method method : this.b.getDeclaredMethods()) {
                if (method.getName().contains("ajc$interMethod$") && method.isAnnotationPresent(ajcITD.class)) {
                    ajcITD ajcitd = (ajcITD) method.getAnnotation(ajcITD.class);
                    if (Modifier.isPublic(ajcitd.modifiers())) {
                        arrayList.add(new InterTypeMethodDeclarationImpl(this, ajcitd.targetType(), ajcitd.modifiers(), ajcitd.name(), method));
                    }
                }
            }
            a((List<InterTypeMethodDeclaration>) arrayList, true);
            this.h = new InterTypeMethodDeclaration[arrayList.size()];
            arrayList.toArray(this.h);
        }
        return this.h;
    }

    private void a(List<InterTypeMethodDeclaration> list, boolean z) {
        if (P()) {
            for (Field field : this.b.getDeclaredFields()) {
                if (field.getType().isInterface() && field.isAnnotationPresent(DeclareParents.class)) {
                    Class cls = DeclareParents.class;
                    if (((DeclareParents) field.getAnnotation(cls)).defaultImpl() != cls) {
                        for (Method method : field.getType().getDeclaredMethods()) {
                            if (Modifier.isPublic(method.getModifiers()) || !z) {
                                list.add(new InterTypeMethodDeclarationImpl(this, AjTypeSystem.a(field.getType()), method, 1));
                            }
                        }
                    }
                }
            }
        }
    }

    public InterTypeConstructorDeclaration a(AjType<?> ajType, AjType<?>... ajTypeArr) throws NoSuchMethodException {
        for (InterTypeConstructorDeclaration interTypeConstructorDeclaration : y()) {
            try {
                if (interTypeConstructorDeclaration.e().equals(ajType)) {
                    AjType[] a2 = interTypeConstructorDeclaration.a();
                    if (a2.length == ajTypeArr.length) {
                        int i2 = 0;
                        while (i2 < a2.length) {
                            if (a2[i2].equals(ajTypeArr[i2])) {
                                i2++;
                            }
                        }
                        return interTypeConstructorDeclaration;
                    }
                    continue;
                } else {
                    continue;
                }
            } catch (ClassNotFoundException unused) {
            }
        }
        throw new NoSuchMethodException();
    }

    public InterTypeConstructorDeclaration[] y() {
        if (this.l == null) {
            ArrayList arrayList = new ArrayList();
            for (Method method : this.b.getDeclaredMethods()) {
                if (method.getName().contains("ajc$postInterConstructor") && method.isAnnotationPresent(ajcITD.class)) {
                    ajcITD ajcitd = (ajcITD) method.getAnnotation(ajcITD.class);
                    arrayList.add(new InterTypeConstructorDeclarationImpl(this, ajcitd.targetType(), ajcitd.modifiers(), method));
                }
            }
            this.l = new InterTypeConstructorDeclaration[arrayList.size()];
            arrayList.toArray(this.l);
        }
        return this.l;
    }

    public InterTypeConstructorDeclaration b(AjType<?> ajType, AjType<?>... ajTypeArr) throws NoSuchMethodException {
        for (InterTypeConstructorDeclaration interTypeConstructorDeclaration : z()) {
            try {
                if (interTypeConstructorDeclaration.e().equals(ajType)) {
                    AjType[] a2 = interTypeConstructorDeclaration.a();
                    if (a2.length == ajTypeArr.length) {
                        int i2 = 0;
                        while (i2 < a2.length) {
                            if (a2[i2].equals(ajTypeArr[i2])) {
                                i2++;
                            }
                        }
                        return interTypeConstructorDeclaration;
                    }
                    continue;
                } else {
                    continue;
                }
            } catch (ClassNotFoundException unused) {
            }
        }
        throw new NoSuchMethodException();
    }

    public InterTypeConstructorDeclaration[] z() {
        if (this.k == null) {
            ArrayList arrayList = new ArrayList();
            for (Method method : this.b.getMethods()) {
                if (method.getName().contains("ajc$postInterConstructor") && method.isAnnotationPresent(ajcITD.class)) {
                    ajcITD ajcitd = (ajcITD) method.getAnnotation(ajcITD.class);
                    if (Modifier.isPublic(ajcitd.modifiers())) {
                        arrayList.add(new InterTypeConstructorDeclarationImpl(this, ajcitd.targetType(), ajcitd.modifiers(), method));
                    }
                }
            }
            this.k = new InterTypeConstructorDeclaration[arrayList.size()];
            arrayList.toArray(this.k);
        }
        return this.k;
    }

    public InterTypeFieldDeclaration a(String str, AjType<?> ajType) throws NoSuchFieldException {
        for (InterTypeFieldDeclaration interTypeFieldDeclaration : A()) {
            if (interTypeFieldDeclaration.a().equals(str)) {
                try {
                    if (interTypeFieldDeclaration.e().equals(ajType)) {
                        return interTypeFieldDeclaration;
                    }
                } catch (ClassNotFoundException unused) {
                    continue;
                }
            }
        }
        throw new NoSuchFieldException(str);
    }

    public InterTypeFieldDeclaration[] A() {
        ArrayList arrayList = new ArrayList();
        if (this.i == null) {
            for (Method method : this.b.getDeclaredMethods()) {
                if (method.isAnnotationPresent(ajcITD.class) && method.getName().contains("ajc$interFieldInit")) {
                    ajcITD ajcitd = (ajcITD) method.getAnnotation(ajcITD.class);
                    try {
                        Method declaredMethod = this.b.getDeclaredMethod(method.getName().replace("FieldInit", "FieldGetDispatch"), method.getParameterTypes());
                        arrayList.add(new InterTypeFieldDeclarationImpl(this, ajcitd.targetType(), ajcitd.modifiers(), ajcitd.name(), AjTypeSystem.a(declaredMethod.getReturnType()), declaredMethod.getGenericReturnType()));
                    } catch (NoSuchMethodException unused) {
                        throw new IllegalStateException("Can't find field get dispatch method for " + method.getName());
                    }
                }
            }
            b((List<InterTypeFieldDeclaration>) arrayList, false);
            this.i = new InterTypeFieldDeclaration[arrayList.size()];
            arrayList.toArray(this.i);
        }
        return this.i;
    }

    public InterTypeFieldDeclaration b(String str, AjType<?> ajType) throws NoSuchFieldException {
        for (InterTypeFieldDeclaration interTypeFieldDeclaration : B()) {
            if (interTypeFieldDeclaration.a().equals(str)) {
                try {
                    if (interTypeFieldDeclaration.e().equals(ajType)) {
                        return interTypeFieldDeclaration;
                    }
                } catch (ClassNotFoundException unused) {
                    continue;
                }
            }
        }
        throw new NoSuchFieldException(str);
    }

    public InterTypeFieldDeclaration[] B() {
        ArrayList arrayList = new ArrayList();
        if (this.j == null) {
            for (Method method : this.b.getMethods()) {
                if (method.isAnnotationPresent(ajcITD.class)) {
                    ajcITD ajcitd = (ajcITD) method.getAnnotation(ajcITD.class);
                    if (method.getName().contains("ajc$interFieldInit") && Modifier.isPublic(ajcitd.modifiers())) {
                        try {
                            Method declaredMethod = method.getDeclaringClass().getDeclaredMethod(method.getName().replace("FieldInit", "FieldGetDispatch"), method.getParameterTypes());
                            arrayList.add(new InterTypeFieldDeclarationImpl(this, ajcitd.targetType(), ajcitd.modifiers(), ajcitd.name(), AjTypeSystem.a(declaredMethod.getReturnType()), declaredMethod.getGenericReturnType()));
                        } catch (NoSuchMethodException unused) {
                            throw new IllegalStateException("Can't find field get dispatch method for " + method.getName());
                        }
                    }
                }
            }
            b((List<InterTypeFieldDeclaration>) arrayList, true);
            this.j = new InterTypeFieldDeclaration[arrayList.size()];
            arrayList.toArray(this.j);
        }
        return this.j;
    }

    public DeclareErrorOrWarning[] C() {
        ArrayList arrayList = new ArrayList();
        for (Field field : this.b.getDeclaredFields()) {
            try {
                if (field.isAnnotationPresent(DeclareWarning.class)) {
                    DeclareWarning declareWarning = (DeclareWarning) field.getAnnotation(DeclareWarning.class);
                    if (Modifier.isPublic(field.getModifiers()) && Modifier.isStatic(field.getModifiers())) {
                        arrayList.add(new DeclareErrorOrWarningImpl(declareWarning.value(), (String) field.get((Object) null), false, this));
                    }
                } else if (field.isAnnotationPresent(DeclareError.class)) {
                    DeclareError declareError = (DeclareError) field.getAnnotation(DeclareError.class);
                    if (Modifier.isPublic(field.getModifiers()) && Modifier.isStatic(field.getModifiers())) {
                        arrayList.add(new DeclareErrorOrWarningImpl(declareError.value(), (String) field.get((Object) null), true, this));
                    }
                }
            } catch (IllegalAccessException | IllegalArgumentException unused) {
            }
        }
        for (Method method : this.b.getDeclaredMethods()) {
            if (method.isAnnotationPresent(ajcDeclareEoW.class)) {
                ajcDeclareEoW ajcdeclareeow = (ajcDeclareEoW) method.getAnnotation(ajcDeclareEoW.class);
                arrayList.add(new DeclareErrorOrWarningImpl(ajcdeclareeow.pointcut(), ajcdeclareeow.message(), ajcdeclareeow.isError(), this));
            }
        }
        DeclareErrorOrWarning[] declareErrorOrWarningArr = new DeclareErrorOrWarning[arrayList.size()];
        arrayList.toArray(declareErrorOrWarningArr);
        return declareErrorOrWarningArr;
    }

    public org.mp4parser.aspectj.lang.reflect.DeclareParents[] D() {
        ArrayList arrayList = new ArrayList();
        for (Method method : this.b.getDeclaredMethods()) {
            if (method.isAnnotationPresent(ajcDeclareParents.class)) {
                ajcDeclareParents ajcdeclareparents = (ajcDeclareParents) method.getAnnotation(ajcDeclareParents.class);
                arrayList.add(new DeclareParentsImpl(ajcdeclareparents.targetTypePattern(), ajcdeclareparents.parentTypes(), ajcdeclareparents.isExtends(), this));
            }
        }
        a((List<org.mp4parser.aspectj.lang.reflect.DeclareParents>) arrayList);
        if (f().P()) {
            arrayList.addAll(Arrays.asList(f().D()));
        }
        org.mp4parser.aspectj.lang.reflect.DeclareParents[] declareParentsArr = new org.mp4parser.aspectj.lang.reflect.DeclareParents[arrayList.size()];
        arrayList.toArray(declareParentsArr);
        return declareParentsArr;
    }

    private void a(List<org.mp4parser.aspectj.lang.reflect.DeclareParents> list) {
        for (Field field : this.b.getDeclaredFields()) {
            if (field.isAnnotationPresent(DeclareParents.class) && field.getType().isInterface()) {
                list.add(new DeclareParentsImpl(((DeclareParents) field.getAnnotation(DeclareParents.class)).value(), field.getType().getName(), false, this));
            }
        }
    }

    public DeclareSoft[] E() {
        ArrayList arrayList = new ArrayList();
        for (Method method : this.b.getDeclaredMethods()) {
            if (method.isAnnotationPresent(ajcDeclareSoft.class)) {
                ajcDeclareSoft ajcdeclaresoft = (ajcDeclareSoft) method.getAnnotation(ajcDeclareSoft.class);
                arrayList.add(new DeclareSoftImpl(this, ajcdeclaresoft.pointcut(), ajcdeclaresoft.exceptionType()));
            }
        }
        if (f().P()) {
            arrayList.addAll(Arrays.asList(f().E()));
        }
        DeclareSoft[] declareSoftArr = new DeclareSoft[arrayList.size()];
        arrayList.toArray(declareSoftArr);
        return declareSoftArr;
    }

    public DeclareAnnotation[] F() {
        Annotation annotation;
        ArrayList arrayList = new ArrayList();
        for (Method method : this.b.getDeclaredMethods()) {
            if (method.isAnnotationPresent(ajcDeclareAnnotation.class)) {
                ajcDeclareAnnotation ajcdeclareannotation = (ajcDeclareAnnotation) method.getAnnotation(ajcDeclareAnnotation.class);
                Annotation[] annotations = method.getAnnotations();
                int length = annotations.length;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        annotation = null;
                        break;
                    }
                    Annotation annotation2 = annotations[i2];
                    if (annotation2.annotationType() != ajcDeclareAnnotation.class) {
                        annotation = annotation2;
                        break;
                    }
                    i2++;
                }
                arrayList.add(new DeclareAnnotationImpl(this, ajcdeclareannotation.kind(), ajcdeclareannotation.pattern(), annotation, ajcdeclareannotation.annotation()));
            }
        }
        if (f().P()) {
            arrayList.addAll(Arrays.asList(f().F()));
        }
        DeclareAnnotation[] declareAnnotationArr = new DeclareAnnotation[arrayList.size()];
        arrayList.toArray(declareAnnotationArr);
        return declareAnnotationArr;
    }

    public DeclarePrecedence[] G() {
        ArrayList arrayList = new ArrayList();
        if (this.b.isAnnotationPresent(org.mp4parser.aspectj.lang.annotation.DeclarePrecedence.class)) {
            arrayList.add(new DeclarePrecedenceImpl(((org.mp4parser.aspectj.lang.annotation.DeclarePrecedence) this.b.getAnnotation(org.mp4parser.aspectj.lang.annotation.DeclarePrecedence.class)).value(), this));
        }
        for (Method method : this.b.getDeclaredMethods()) {
            if (method.isAnnotationPresent(ajcDeclarePrecedence.class)) {
                arrayList.add(new DeclarePrecedenceImpl(((ajcDeclarePrecedence) method.getAnnotation(ajcDeclarePrecedence.class)).value(), this));
            }
        }
        if (f().P()) {
            arrayList.addAll(Arrays.asList(f().G()));
        }
        DeclarePrecedence[] declarePrecedenceArr = new DeclarePrecedence[arrayList.size()];
        arrayList.toArray(declarePrecedenceArr);
        return declarePrecedenceArr;
    }

    public T[] H() {
        return this.b.getEnumConstants();
    }

    public TypeVariable<Class<T>>[] I() {
        return this.b.getTypeParameters();
    }

    public boolean J() {
        return this.b.isEnum();
    }

    public boolean a(Object obj) {
        return this.b.isInstance(obj);
    }

    public boolean K() {
        return this.b.isInterface();
    }

    public boolean L() {
        return this.b.isLocalClass() && !P();
    }

    public boolean M() {
        return this.b.isMemberClass() && !P();
    }

    public boolean N() {
        return this.b.isArray();
    }

    public boolean O() {
        return this.b.isPrimitive();
    }

    public boolean P() {
        return this.b.getAnnotation(Aspect.class) != null;
    }

    public boolean Q() {
        return this.b.isMemberClass() && P();
    }

    public boolean R() {
        return P() && this.b.isAnnotationPresent(ajcPrivileged.class);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AjTypeImpl)) {
            return false;
        }
        return ((AjTypeImpl) obj).b.equals(this.b);
    }

    public int hashCode() {
        return this.b.hashCode();
    }

    private AjType<?>[] a(Class<?>[] clsArr) {
        AjType<?>[] ajTypeArr = new AjType[clsArr.length];
        for (int i2 = 0; i2 < ajTypeArr.length; i2++) {
            ajTypeArr[i2] = AjTypeSystem.a(clsArr[i2]);
        }
        return ajTypeArr;
    }

    private Class<?>[] c(AjType<?>[] ajTypeArr) {
        Class<?>[] clsArr = new Class[ajTypeArr.length];
        for (int i2 = 0; i2 < clsArr.length; i2++) {
            clsArr[i2] = ajTypeArr[i2].e();
        }
        return clsArr;
    }

    public String toString() {
        return a();
    }
}
