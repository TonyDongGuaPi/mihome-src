package org.mp4parser.aspectj.lang.reflect;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

public interface AjType<T> extends AnnotatedElement, Type {
    InterTypeFieldDeclaration[] A();

    InterTypeFieldDeclaration[] B();

    DeclareErrorOrWarning[] C();

    DeclareParents[] D();

    DeclareSoft[] E();

    DeclareAnnotation[] F();

    DeclarePrecedence[] G();

    T[] H();

    TypeVariable<Class<T>>[] I();

    boolean J();

    boolean K();

    boolean L();

    boolean M();

    boolean N();

    boolean O();

    boolean P();

    boolean Q();

    boolean R();

    String a();

    Constructor a(AjType<?>... ajTypeArr) throws NoSuchMethodException;

    Field a(String str) throws NoSuchFieldException;

    Method a(String str, AjType<?>... ajTypeArr) throws NoSuchMethodException;

    InterTypeConstructorDeclaration a(AjType<?> ajType, AjType<?>... ajTypeArr) throws NoSuchMethodException;

    InterTypeFieldDeclaration a(String str, AjType<?> ajType) throws NoSuchFieldException;

    InterTypeMethodDeclaration a(String str, AjType<?> ajType, AjType<?>... ajTypeArr) throws NoSuchMethodException;

    boolean a(Object obj);

    Advice[] a(AdviceKind... adviceKindArr);

    Package b();

    Constructor b(AjType<?>... ajTypeArr) throws NoSuchMethodException;

    Field b(String str) throws NoSuchFieldException;

    Method b(String str, AjType<?>... ajTypeArr) throws NoSuchMethodException;

    InterTypeConstructorDeclaration b(AjType<?> ajType, AjType<?>... ajTypeArr) throws NoSuchMethodException;

    InterTypeFieldDeclaration b(String str, AjType<?> ajType) throws NoSuchFieldException;

    InterTypeMethodDeclaration b(String str, AjType<?> ajType, AjType<?>... ajTypeArr) throws NoSuchMethodException;

    Advice[] b(AdviceKind... adviceKindArr);

    Pointcut c(String str) throws NoSuchPointcutException;

    AjType<?>[] c();

    int d();

    Pointcut d(String str) throws NoSuchPointcutException;

    Class<T> e();

    Advice e(String str) throws NoSuchAdviceException;

    Advice f(String str) throws NoSuchAdviceException;

    AjType<?> f();

    Type g();

    Method h();

    Constructor i();

    AjType<?> j();

    AjType<?> k();

    PerClause l();

    AjType<?>[] m();

    AjType<?>[] n();

    Constructor[] o();

    Constructor[] p();

    Field[] q();

    Field[] r();

    Method[] s();

    Method[] t();

    Pointcut[] u();

    Pointcut[] v();

    InterTypeMethodDeclaration[] w();

    InterTypeMethodDeclaration[] x();

    InterTypeConstructorDeclaration[] y();

    InterTypeConstructorDeclaration[] z();
}
