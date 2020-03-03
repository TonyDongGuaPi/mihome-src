package org.jacoco.agent.rt.internal_8ff85ea.core.internal.flow;

import java.util.ArrayList;
import java.util.List;
import org.jacoco.agent.rt.internal_8ff85ea.asm.MethodVisitor;
import org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes;
import org.jacoco.agent.rt.internal_8ff85ea.asm.commons.AnalyzerAdapter;

class FrameSnapshot implements IFrame {

    /* renamed from: a  reason: collision with root package name */
    private static final FrameSnapshot f3621a = new FrameSnapshot((Object[]) null, (Object[]) null);
    private final Object[] b;
    private final Object[] c;

    private FrameSnapshot(Object[] objArr, Object[] objArr2) {
        this.b = objArr;
        this.c = objArr2;
    }

    static IFrame a(AnalyzerAdapter analyzerAdapter, int i) {
        if (analyzerAdapter == null || analyzerAdapter.c == null) {
            return f3621a;
        }
        return new FrameSnapshot(a(analyzerAdapter.c, 0), a(analyzerAdapter.d, i));
    }

    private static Object[] a(List<Object> list, int i) {
        ArrayList arrayList = new ArrayList(list);
        int size = list.size() - i;
        arrayList.subList(size, list.size()).clear();
        while (true) {
            size--;
            if (size < 0) {
                return arrayList.toArray();
            }
            Object obj = list.get(size);
            if (obj == Opcodes.af || obj == Opcodes.ae) {
                arrayList.remove(size + 1);
            }
        }
    }

    public void a(MethodVisitor methodVisitor) {
        if (this.b != null) {
            methodVisitor.a(-1, this.b.length, this.b, this.c.length, this.c);
        }
    }
}
