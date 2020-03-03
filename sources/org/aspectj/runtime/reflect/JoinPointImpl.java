package org.aspectj.runtime.reflect;

import com.taobao.weex.el.parse.Operators;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.SourceLocation;
import org.aspectj.runtime.internal.AroundClosure;

class JoinPointImpl implements ProceedingJoinPoint {
    Object n;
    Object o;
    Object[] p;
    JoinPoint.StaticPart q;
    private AroundClosure r;

    static class StaticPartImpl implements JoinPoint.StaticPart {

        /* renamed from: a  reason: collision with root package name */
        String f3471a;
        Signature b;
        SourceLocation c;
        private int d;

        public StaticPartImpl(int i, String str, Signature signature, SourceLocation sourceLocation) {
            this.f3471a = str;
            this.b = signature;
            this.c = sourceLocation;
            this.d = i;
        }

        public int d() {
            return this.d;
        }

        public String c() {
            return this.f3471a;
        }

        public Signature a() {
            return this.b;
        }

        public SourceLocation b() {
            return this.c;
        }

        /* access modifiers changed from: package-private */
        public String a(StringMaker stringMaker) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(stringMaker.a(c()));
            stringBuffer.append(Operators.BRACKET_START_STR);
            stringBuffer.append(((SignatureImpl) a()).b(stringMaker));
            stringBuffer.append(Operators.BRACKET_END_STR);
            return stringBuffer.toString();
        }

        public final String toString() {
            return a(StringMaker.k);
        }

        public final String e() {
            return a(StringMaker.j);
        }

        public final String f() {
            return a(StringMaker.l);
        }
    }

    static class EnclosingStaticPartImpl extends StaticPartImpl implements JoinPoint.EnclosingStaticPart {
        public EnclosingStaticPartImpl(int i, String str, Signature signature, SourceLocation sourceLocation) {
            super(i, str, signature, sourceLocation);
        }
    }

    public JoinPointImpl(JoinPoint.StaticPart staticPart, Object obj, Object obj2, Object[] objArr) {
        this.q = staticPart;
        this.n = obj;
        this.o = obj2;
        this.p = objArr;
    }

    public Object c() {
        return this.n;
    }

    public Object d() {
        return this.o;
    }

    public Object[] e() {
        if (this.p == null) {
            this.p = new Object[0];
        }
        Object[] objArr = new Object[this.p.length];
        System.arraycopy(this.p, 0, objArr, 0, this.p.length);
        return objArr;
    }

    public JoinPoint.StaticPart i() {
        return this.q;
    }

    public String h() {
        return this.q.c();
    }

    public Signature f() {
        return this.q.a();
    }

    public SourceLocation g() {
        return this.q.b();
    }

    public final String toString() {
        return this.q.toString();
    }

    public final String a() {
        return this.q.e();
    }

    public final String b() {
        return this.q.f();
    }

    public void a(AroundClosure aroundClosure) {
        this.r = aroundClosure;
    }

    public Object j() throws Throwable {
        if (this.r == null) {
            return null;
        }
        return this.r.run(this.r.getState());
    }

    public Object a(Object[] objArr) throws Throwable {
        if (this.r == null) {
            return null;
        }
        int flags = this.r.getFlags();
        int i = 1048576 & flags;
        int i2 = 1;
        boolean z = (65536 & flags) != 0;
        int i3 = (flags & 4096) != 0 ? 1 : 0;
        int i4 = (flags & 256) != 0 ? 1 : 0;
        boolean z2 = (flags & 16) != 0;
        boolean z3 = (flags & 1) != 0;
        Object[] state = this.r.getState();
        int i5 = i3 + 0 + ((!z2 || z) ? 0 : 1);
        if (i3 == 0 || i4 == 0) {
            i2 = 0;
        } else {
            state[0] = objArr[0];
        }
        if (z2 && z3) {
            if (z) {
                i2 = i4 + 1;
                state[0] = objArr[i4];
            } else {
                i2 = i3 + 1;
                state[i3] = objArr[i3];
            }
        }
        for (int i6 = i2; i6 < objArr.length; i6++) {
            state[(i6 - i2) + i5] = objArr[i6];
        }
        return this.r.run(state);
    }
}