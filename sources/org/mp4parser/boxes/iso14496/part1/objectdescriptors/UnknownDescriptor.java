package org.mp4parser.boxes.iso14496.part1.objectdescriptors;

import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.logging.Logger;

public class UnknownDescriptor extends BaseDescriptor {

    /* renamed from: a  reason: collision with root package name */
    private static Logger f3832a = Logger.getLogger(UnknownDescriptor.class.getName());
    private ByteBuffer b;

    public void a(ByteBuffer byteBuffer) throws IOException {
        this.b = byteBuffer.slice();
    }

    public String toString() {
        return "UnknownDescriptor" + "{tag=" + this.Z + ", sizeOfInstance=" + this.aa + ", data=" + this.b + Operators.BLOCK_END;
    }

    public ByteBuffer b() {
        throw new RuntimeException("sdjlhfl");
    }

    /* access modifiers changed from: package-private */
    public int a() {
        throw new RuntimeException("sdjlhfl");
    }
}
