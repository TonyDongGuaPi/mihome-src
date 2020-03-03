package org.mp4parser.boxes.iso14496.part1.objectdescriptors;

import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import org.mp4parser.tools.IsoTypeReader;

public abstract class InitialObjectDescriptor extends ObjectDescriptorBase {

    /* renamed from: a  reason: collision with root package name */
    int f3828a;
    int b;
    int c;
    String d;
    int e;
    int f;
    int g;
    int h;
    int i;
    List<ESDescriptor> j = new ArrayList();
    List<ExtensionDescriptor> k = new ArrayList();
    List<BaseDescriptor> l = new ArrayList();
    private int m;

    public void a(ByteBuffer byteBuffer) throws IOException {
        int i2;
        int d2 = IsoTypeReader.d(byteBuffer);
        this.m = (65472 & d2) >> 6;
        this.f3828a = (d2 & 63) >> 5;
        this.b = (d2 & 31) >> 4;
        int l2 = l() - 2;
        if (this.f3828a == 1) {
            this.c = IsoTypeReader.f(byteBuffer);
            this.d = IsoTypeReader.a(byteBuffer, this.c);
            i2 = l2 - (this.c + 1);
        } else {
            this.e = IsoTypeReader.f(byteBuffer);
            this.f = IsoTypeReader.f(byteBuffer);
            this.g = IsoTypeReader.f(byteBuffer);
            this.h = IsoTypeReader.f(byteBuffer);
            this.i = IsoTypeReader.f(byteBuffer);
            i2 = l2 - 5;
            if (i2 > 2) {
                BaseDescriptor a2 = ObjectDescriptorFactory.a(-1, byteBuffer);
                i2 -= a2.l();
                if (a2 instanceof ESDescriptor) {
                    this.j.add((ESDescriptor) a2);
                } else {
                    this.l.add(a2);
                }
            }
        }
        if (i2 > 2) {
            BaseDescriptor a3 = ObjectDescriptorFactory.a(-1, byteBuffer);
            if (a3 instanceof ExtensionDescriptor) {
                this.k.add((ExtensionDescriptor) a3);
            } else {
                this.l.add(a3);
            }
        }
    }

    public String toString() {
        return "InitialObjectDescriptor" + "{objectDescriptorId=" + this.m + ", urlFlag=" + this.f3828a + ", includeInlineProfileLevelFlag=" + this.b + ", urlLength=" + this.c + ", urlString='" + this.d + Operators.SINGLE_QUOTE + ", oDProfileLevelIndication=" + this.e + ", sceneProfileLevelIndication=" + this.f + ", audioProfileLevelIndication=" + this.g + ", visualProfileLevelIndication=" + this.h + ", graphicsProfileLevelIndication=" + this.i + ", esDescriptors=" + this.j + ", extensionDescriptors=" + this.k + ", unknownDescriptors=" + this.l + Operators.BLOCK_END;
    }
}
