package org.mp4parser.aspectj.internal.lang.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import org.mp4parser.aspectj.lang.reflect.AjType;
import org.mp4parser.aspectj.lang.reflect.AjTypeSystem;
import org.mp4parser.aspectj.lang.reflect.InterTypeFieldDeclaration;

public class InterTypeFieldDeclarationImpl extends InterTypeDeclarationImpl implements InterTypeFieldDeclaration {
    private String b;
    private AjType<?> c;
    private Type d;

    public InterTypeFieldDeclarationImpl(AjType<?> ajType, String str, int i, String str2, AjType<?> ajType2, Type type) {
        super(ajType, str, i);
        this.b = str2;
        this.c = ajType2;
        this.d = type;
    }

    public InterTypeFieldDeclarationImpl(AjType<?> ajType, AjType<?> ajType2, Field field) {
        super(ajType, ajType2, field.getModifiers());
        this.b = field.getName();
        this.c = AjTypeSystem.a(field.getType());
        Type genericType = field.getGenericType();
        if (genericType instanceof Class) {
            this.d = AjTypeSystem.a((Class) genericType);
        } else {
            this.d = genericType;
        }
    }

    public String a() {
        return this.b;
    }

    public AjType<?> b() {
        return this.c;
    }

    public Type c() {
        return this.d;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(Modifier.toString(f()));
        stringBuffer.append(" ");
        stringBuffer.append(b().toString());
        stringBuffer.append(" ");
        stringBuffer.append(this.f3744a);
        stringBuffer.append(".");
        stringBuffer.append(a());
        return stringBuffer.toString();
    }
}
