package org.mp4parser.support;

import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.NoAspectBoundException;
import org.mp4parser.aspectj.lang.annotation.Aspect;
import org.mp4parser.aspectj.lang.annotation.Before;

@Aspect
public class RequiresParseDetailAspect {

    /* renamed from: a  reason: collision with root package name */
    public static final RequiresParseDetailAspect f4106a = null;
    private static Throwable b;

    static {
        try {
            c();
        } catch (Throwable th) {
            b = th;
        }
    }

    public static RequiresParseDetailAspect a() {
        if (f4106a != null) {
            return f4106a;
        }
        throw new NoAspectBoundException("org.mp4parser.support.RequiresParseDetailAspect", b);
    }

    public static boolean b() {
        return f4106a != null;
    }

    private static void c() {
        f4106a = new RequiresParseDetailAspect();
    }

    @Before("this(org.mp4parser.support.AbstractBox) && ((execution(public * * (..)) && !( execution(* parseDetails()) || execution(* getNumOfBytesToFirstChild()) || execution(* getType()) || execution(* isParsed()) || execution(* getHeader(*)) || execution(* parse()) || execution(* getBox(*)) || execution(* getSize()) || execution(* getOffset()) || execution(* setOffset(*)) || execution(* parseDetails()) || execution(* _parseDetails(*)) || execution(* parse(*,*,*,*)) || execution(* getIsoFile()) || execution(* getParent()) || execution(* setParent(*)) || execution(* getUserType()) || execution(* setUserType(*))) && !@annotation(org.mp4parser.support.DoNotParseDetail)) || @annotation(org.mp4parser.support.ParseDetail))")
    public void a(JoinPoint joinPoint) {
        if (!(joinPoint.d() instanceof AbstractBox)) {
            throw new RuntimeException("Only methods in subclasses of " + AbstractBox.class.getName() + " can  be annotated with ParseDetail");
        } else if (!((AbstractBox) joinPoint.d()).x()) {
            ((AbstractBox) joinPoint.d()).w();
        }
    }
}
