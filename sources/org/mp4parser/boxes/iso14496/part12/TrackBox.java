package org.mp4parser.boxes.iso14496.part12;

import java.util.List;
import org.mp4parser.Box;
import org.mp4parser.support.AbstractContainerBox;
import org.mp4parser.tools.Path;

public class TrackBox extends AbstractContainerBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3900a = "trak";
    private SampleTableBox b;

    public TrackBox() {
        super("trak");
    }

    public TrackHeaderBox d() {
        return (TrackHeaderBox) Path.a((AbstractContainerBox) this, "tkhd[0]");
    }

    public SampleTableBox e() {
        MediaInformationBox d;
        if (this.b != null) {
            return this.b;
        }
        MediaBox f = f();
        if (f == null || (d = f.d()) == null) {
            return null;
        }
        this.b = d.d();
        return this.b;
    }

    public MediaBox f() {
        return (MediaBox) Path.a((AbstractContainerBox) this, "mdia[0]");
    }

    public void a(List<? extends Box> list) {
        super.a(list);
        this.b = null;
    }
}
