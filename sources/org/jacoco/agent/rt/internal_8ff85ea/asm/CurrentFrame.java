package org.jacoco.agent.rt.internal_8ff85ea.asm;

class CurrentFrame extends Frame {
    CurrentFrame() {
    }

    /* access modifiers changed from: package-private */
    public void a(int i, int i2, ClassWriter classWriter, Item item) {
        super.a(i, i2, classWriter, item);
        Frame frame = new Frame();
        a(classWriter, frame, 0);
        a(frame);
        this.x.q = 0;
    }
}
