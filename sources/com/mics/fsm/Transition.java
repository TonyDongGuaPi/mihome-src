package com.mics.fsm;

import com.mics.util.Logger;
import java.util.ArrayList;
import java.util.List;

public class Transition {

    /* renamed from: a  reason: collision with root package name */
    private List<Value> f7759a = new ArrayList();
    private int b = 0;
    private int c = 0;
    private int d = 0;

    Transition() {
    }

    /* access modifiers changed from: private */
    public void a(Value value) {
        for (Value equals : this.f7759a) {
            if (equals.equals(value)) {
                throw new IllegalArgumentException("禁止相同的source、event指向不同的target！");
            }
        }
        this.f7759a.add(value);
        int length = String.valueOf(value.f7761a).length();
        int length2 = String.valueOf(value.b).length();
        int length3 = String.valueOf(value.c).length();
        if (length > this.b) {
            this.b = length;
        }
        if (length2 > this.c) {
            this.c = length2;
        }
        if (length3 > this.d) {
            this.d = length3;
        }
    }

    public External a() {
        return new External(this);
    }

    public void b() {
        StringBuilder sb = new StringBuilder();
        int i = ((((this.b + this.c) + this.d) + 15) + 4) - 2;
        for (int i2 = 0; i2 < i; i2++) {
            sb.append("-");
        }
        Logger.a("┌" + sb.toString() + "┐", new Object[0]);
        Logger.a("|%-" + (this.b + 5) + "s|%-" + (this.d + 5) + "s|%-" + (this.c + 5) + "s|", "Source", "Event", "Target");
        StringBuilder sb2 = new StringBuilder();
        sb2.append("├");
        sb2.append(sb.toString());
        sb2.append("┤");
        Logger.a(sb2.toString(), new Object[0]);
        for (Value next : this.f7759a) {
            Logger.a("|%-" + (this.b + 5) + "s|%-" + (this.d + 5) + "s|%-" + (this.c + 5) + "s|", String.valueOf(next.f7761a), String.valueOf(next.c), String.valueOf(next.b));
        }
        Logger.a("└" + sb.toString() + "┘", new Object[0]);
    }

    /* access modifiers changed from: package-private */
    public Enum a(Enum enumR, Enum enumR2) {
        for (Value next : this.f7759a) {
            if (next.a(enumR, enumR2)) {
                return next.b;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public boolean b(Enum enumR, Enum enumR2) {
        return a(enumR, enumR2) != null;
    }

    private static class Value {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public Enum f7761a;
        /* access modifiers changed from: private */
        public Enum b;
        /* access modifiers changed from: private */
        public Enum c;

        Value() {
        }

        /* access modifiers changed from: package-private */
        public boolean a() {
            return this.f7761a == null || this.b == null || this.c == null;
        }

        public boolean equals(Object obj) {
            if (super.equals(obj)) {
                return true;
            }
            if (!(obj instanceof Value)) {
                return false;
            }
            Value value = (Value) obj;
            if (value.f7761a == this.f7761a && value.c == this.c) {
                return true;
            }
            return false;
        }

        /* access modifiers changed from: package-private */
        public boolean a(Enum enumR, Enum enumR2) {
            return enumR == this.f7761a && enumR2 == this.c;
        }
    }

    public static class External {

        /* renamed from: a  reason: collision with root package name */
        private Transition f7760a;
        private Value b = new Value();

        External(Transition transition) {
            this.f7760a = transition;
        }

        public External a(Enum enumR) {
            Enum unused = this.b.f7761a = enumR;
            return this;
        }

        public External b(Enum enumR) {
            Enum unused = this.b.b = enumR;
            return this;
        }

        public External c(Enum enumR) {
            Enum unused = this.b.c = enumR;
            return this;
        }

        public Transition a() {
            if (!this.b.a()) {
                this.f7760a.a(this.b);
                return this.f7760a;
            }
            throw new IllegalArgumentException("source、target、event任何一项都不能为空");
        }
    }
}
