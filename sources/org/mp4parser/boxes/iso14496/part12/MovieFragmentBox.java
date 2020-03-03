package org.mp4parser.boxes.iso14496.part12;

import java.util.ArrayList;
import java.util.List;
import org.mp4parser.Container;
import org.mp4parser.boxes.iso14496.part12.SampleDependencyTypeBox;
import org.mp4parser.support.AbstractContainerBox;
import org.mp4parser.tools.Path;

public class MovieFragmentBox extends AbstractContainerBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3866a = "moof";

    public MovieFragmentBox() {
        super(f3866a);
    }

    public List<Long> a(SampleDependencyTypeBox sampleDependencyTypeBox) {
        ArrayList arrayList = new ArrayList();
        long j = 1;
        for (SampleDependencyTypeBox.Entry b : sampleDependencyTypeBox.e()) {
            if (b.b() == 2) {
                arrayList.add(Long.valueOf(j));
            }
            j++;
        }
        return arrayList;
    }

    public int d() {
        return a(TrackFragmentBox.class, false).size();
    }

    public long[] e() {
        List<TrackFragmentBox> a2 = a(TrackFragmentBox.class, false);
        long[] jArr = new long[a2.size()];
        for (int i = 0; i < a2.size(); i++) {
            jArr[i] = a2.get(i).d().j();
        }
        return jArr;
    }

    public List<TrackFragmentHeaderBox> f() {
        return Path.b((Container) this, TrackFragmentHeaderBox.f3904a);
    }

    public List<TrackRunBox> g() {
        return a(TrackRunBox.class, true);
    }
}
