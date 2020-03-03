package org.jacoco.agent.rt.internal_8ff85ea.core.internal.flow;

public class Instruction {

    /* renamed from: a  reason: collision with root package name */
    private final int f3622a;
    private int b = 0;
    private int c = 0;
    private Instruction d;

    public Instruction(int i) {
        this.f3622a = i;
    }

    public void a() {
        this.b++;
    }

    public void a(Instruction instruction) {
        this.d = instruction;
        instruction.a();
    }

    public void b() {
        Instruction instruction = this;
        while (instruction != null) {
            int i = instruction.c;
            instruction.c = i + 1;
            if (i == 0) {
                instruction = instruction.d;
            } else {
                return;
            }
        }
    }

    public int c() {
        return this.f3622a;
    }

    public int d() {
        return this.b;
    }

    public int e() {
        return this.c;
    }
}
