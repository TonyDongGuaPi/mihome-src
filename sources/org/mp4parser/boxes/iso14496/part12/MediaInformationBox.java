package org.mp4parser.boxes.iso14496.part12;

import org.mp4parser.Box;
import org.mp4parser.support.AbstractContainerBox;
import org.mp4parser.tools.Path;

public class MediaInformationBox extends AbstractContainerBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3861a = "minf";

    public MediaInformationBox() {
        super("minf");
    }

    public SampleTableBox d() {
        return (SampleTableBox) Path.a((AbstractContainerBox) this, "stbl[0]");
    }

    public AbstractMediaHeaderBox e() {
        for (Box next : a()) {
            if (next instanceof AbstractMediaHeaderBox) {
                return (AbstractMediaHeaderBox) next;
            }
        }
        return null;
    }
}
