package org.mp4parser.boxes.iso14496.part12;

import org.mp4parser.support.AbstractContainerBox;
import org.mp4parser.tools.Path;

public class MediaBox extends AbstractContainerBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3858a = "mdia";

    public MediaBox() {
        super("mdia");
    }

    public MediaInformationBox d() {
        return (MediaInformationBox) Path.a((AbstractContainerBox) this, "minf[0]");
    }

    public MediaHeaderBox e() {
        return (MediaHeaderBox) Path.a((AbstractContainerBox) this, "mdhd[0]");
    }

    public HandlerBox f() {
        return (HandlerBox) Path.a((AbstractContainerBox) this, "hdlr[0]");
    }
}
