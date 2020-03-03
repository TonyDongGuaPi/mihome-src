package com.mics.fsm;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class StateMachine {

    /* renamed from: a  reason: collision with root package name */
    private StateMachineConfig f7758a;
    private Transition b = new Transition();
    private Enum c;
    private Map<Enum, List<WeakReference<Processor>>> d;

    public interface Processor {
        void a(Object... objArr);

        void d();
    }

    private StateMachine() {
    }

    private void b() {
        this.f7758a.a(this);
        this.f7758a.a(this.b);
    }

    public static StateMachine a(StateMachineConfig stateMachineConfig) {
        StateMachine stateMachine = new StateMachine();
        stateMachine.f7758a = stateMachineConfig;
        stateMachine.b();
        return stateMachine;
    }

    public void a(Enum enumR) {
        this.c = enumR;
    }

    public void a(Enum enumR, Object... objArr) {
        List<WeakReference<Processor>> b2;
        if (this.b.b(this.c, enumR)) {
            Enum a2 = this.b.a(this.c, enumR);
            if (!(a2 == this.c || (b2 = b(this.c)) == null)) {
                for (WeakReference next : b2) {
                    if (next.get() != null) {
                        ((Processor) next.get()).d();
                    }
                }
            }
            this.c = a2;
            List<WeakReference<Processor>> b3 = b(this.c);
            if (b3 != null) {
                for (WeakReference next2 : b3) {
                    if (next2.get() != null) {
                        ((Processor) next2.get()).a(objArr);
                    }
                }
            }
        }
    }

    public Enum a() {
        return this.c;
    }

    public void a(Enum enumR, Processor processor) {
        c();
        List b2 = b(enumR);
        if (b2 == null) {
            b2 = new ArrayList();
            this.d.put(enumR, b2);
        }
        b2.add(new WeakReference(processor));
    }

    private synchronized List<WeakReference<Processor>> b(Enum enumR) {
        c();
        if (this.d.get(enumR) == null) {
            return null;
        }
        List<WeakReference<Processor>> list = this.d.get(enumR);
        Iterator<WeakReference<Processor>> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().get() == null) {
                it.remove();
            }
        }
        return list;
    }

    private void c() {
        if (this.d == null) {
            this.d = new HashMap();
        }
    }
}
