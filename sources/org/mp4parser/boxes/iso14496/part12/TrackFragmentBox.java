package org.mp4parser.boxes.iso14496.part12;

import org.mp4parser.Box;
import org.mp4parser.support.AbstractContainerBox;
import org.mp4parser.support.DoNotParseDetail;

public class TrackFragmentBox extends AbstractContainerBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3903a = "traf";

    public TrackFragmentBox() {
        super(f3903a);
    }

    @DoNotParseDetail
    public TrackFragmentHeaderBox d() {
        for (Box next : a()) {
            if (next instanceof TrackFragmentHeaderBox) {
                return (TrackFragmentHeaderBox) next;
            }
        }
        return null;
    }
}
