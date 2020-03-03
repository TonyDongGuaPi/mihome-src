package org.mp4parser;

import com.alipay.sdk.util.i;
import com.taobao.weex.el.parse.Operators;
import java.io.EOFException;
import java.io.IOException;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BasicContainer implements Container {

    /* renamed from: a  reason: collision with root package name */
    private List<Box> f3732a = new ArrayList();

    public BasicContainer() {
    }

    public BasicContainer(List<Box> list) {
        this.f3732a = list;
    }

    public List<Box> a() {
        return this.f3732a;
    }

    public void a(List<? extends Box> list) {
        this.f3732a = new ArrayList(list);
    }

    /* access modifiers changed from: protected */
    public long b() {
        long j = 0;
        for (int i = 0; i < a().size(); i++) {
            j += this.f3732a.get(i).c();
        }
        return j;
    }

    public <T extends Box> List<T> a(Class<T> cls) {
        ArrayList arrayList = null;
        Box box = null;
        for (Box next : a()) {
            if (cls.isInstance(next)) {
                if (box == null) {
                    box = next;
                } else {
                    if (arrayList == null) {
                        arrayList = new ArrayList(2);
                        arrayList.add(box);
                    }
                    arrayList.add(next);
                }
            }
        }
        if (arrayList != null) {
            return arrayList;
        }
        if (box != null) {
            return Collections.singletonList(box);
        }
        return Collections.emptyList();
    }

    public <T extends Box> List<T> a(Class<T> cls, boolean z) {
        ArrayList arrayList = new ArrayList(2);
        List<Box> a2 = a();
        for (int i = 0; i < a2.size(); i++) {
            Box box = a2.get(i);
            if (cls.isInstance(box)) {
                arrayList.add(box);
            }
            if (z && (box instanceof Container)) {
                arrayList.addAll(((Container) box).a(cls, z));
            }
        }
        return arrayList;
    }

    public void a(Box box) {
        if (box != null) {
            this.f3732a = new ArrayList(a());
            this.f3732a.add(box);
        }
    }

    public void a(ReadableByteChannel readableByteChannel, long j, BoxParser boxParser) throws IOException {
        long j2 = 0;
        while (true) {
            if (j < 0 || j2 < j) {
                try {
                    ParsableBox a2 = boxParser.a(readableByteChannel, this instanceof ParsableBox ? ((ParsableBox) this).ae_() : null);
                    this.f3732a.add(a2);
                    j2 += a2.c();
                } catch (EOFException e) {
                    if (j >= 0) {
                        throw e;
                    }
                    return;
                }
            } else {
                return;
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(Operators.ARRAY_START_STR);
        for (int i = 0; i < this.f3732a.size(); i++) {
            if (i > 0) {
                sb.append(i.b);
            }
            sb.append(this.f3732a.get(i).toString());
        }
        sb.append(Operators.ARRAY_END_STR);
        return sb.toString();
    }

    public final void a(WritableByteChannel writableByteChannel) throws IOException {
        for (Box b : a()) {
            b.b(writableByteChannel);
        }
    }
}
