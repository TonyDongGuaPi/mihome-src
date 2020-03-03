package org.mp4parser.boxes.iso14496.part12;

import org.mp4parser.Box;
import org.mp4parser.support.AbstractContainerBox;
import org.mp4parser.tools.Path;

public class SampleTableBox extends AbstractContainerBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3883a = "stbl";
    private SampleToChunkBox b;

    public SampleTableBox() {
        super("stbl");
    }

    public SampleDescriptionBox d() {
        return (SampleDescriptionBox) Path.a((AbstractContainerBox) this, "stsd");
    }

    public SampleSizeBox e() {
        return (SampleSizeBox) Path.a((AbstractContainerBox) this, SampleSizeBox.f3882a);
    }

    public SampleToChunkBox f() {
        return (SampleToChunkBox) Path.a((AbstractContainerBox) this, SampleToChunkBox.f3884a);
    }

    public ChunkOffsetBox g() {
        for (Box next : a()) {
            if (next instanceof ChunkOffsetBox) {
                return (ChunkOffsetBox) next;
            }
        }
        return null;
    }

    public TimeToSampleBox h() {
        return (TimeToSampleBox) Path.a((AbstractContainerBox) this, "stts");
    }

    public SyncSampleBox i() {
        return (SyncSampleBox) Path.a((AbstractContainerBox) this, SyncSampleBox.f3897a);
    }

    public CompositionTimeToSample j() {
        return (CompositionTimeToSample) Path.a((AbstractContainerBox) this, CompositionTimeToSample.f3836a);
    }

    public SampleDependencyTypeBox k() {
        return (SampleDependencyTypeBox) Path.a((AbstractContainerBox) this, SampleDependencyTypeBox.f3878a);
    }
}
