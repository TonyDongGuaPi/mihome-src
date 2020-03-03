package org.mp4parser.aspectj.internal.lang.reflect;

import org.mp4parser.aspectj.lang.reflect.AjType;
import org.mp4parser.aspectj.lang.reflect.InterTypeDeclaration;

public class InterTypeDeclarationImpl implements InterTypeDeclaration {

    /* renamed from: a  reason: collision with root package name */
    protected String f3744a;
    private AjType<?> b;
    private AjType<?> c;
    private int d;

    public InterTypeDeclarationImpl(AjType<?> ajType, String str, int i) {
        this.b = ajType;
        this.f3744a = str;
        this.d = i;
        try {
            this.c = (AjType) StringToType.b(str, ajType.e());
        } catch (ClassNotFoundException unused) {
        }
    }

    public InterTypeDeclarationImpl(AjType<?> ajType, AjType<?> ajType2, int i) {
        this.b = ajType;
        this.c = ajType2;
        this.f3744a = ajType2.a();
        this.d = i;
    }

    public AjType<?> d() {
        return this.b;
    }

    public AjType<?> e() throws ClassNotFoundException {
        if (this.c != null) {
            return this.c;
        }
        throw new ClassNotFoundException(this.f3744a);
    }

    public int f() {
        return this.d;
    }
}
