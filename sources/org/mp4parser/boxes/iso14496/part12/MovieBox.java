package org.mp4parser.boxes.iso14496.part12;

import java.util.List;
import org.mp4parser.support.AbstractContainerBox;
import org.mp4parser.tools.Path;

public class MovieBox extends AbstractContainerBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3863a = "moov";

    public MovieBox() {
        super("moov");
    }

    public int d() {
        return a(TrackBox.class).size();
    }

    public long[] e() {
        List<TrackBox> a2 = a(TrackBox.class);
        long[] jArr = new long[a2.size()];
        for (int i = 0; i < a2.size(); i++) {
            jArr[i] = a2.get(i).d().g();
        }
        return jArr;
    }

    public MovieHeaderBox f() {
        return (MovieHeaderBox) Path.a((AbstractContainerBox) this, "mvhd");
    }
}
