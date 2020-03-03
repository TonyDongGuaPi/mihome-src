package org.mp4parser.boxes.microsoft;

import cn.com.fmsh.tsm.business.constants.Constants;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.boxes.iso23001.part7.AbstractTrackEncryptionBox;
import org.mp4parser.support.RequiresParseDetailAspect;

public class PiffTrackEncryptionBox extends AbstractTrackEncryptionBox {
    private static final JoinPoint.StaticPart d = null;

    static {
        h();
    }

    private static void h() {
        Factory factory = new Factory("PiffTrackEncryptionBox.java", PiffTrackEncryptionBox.class);
        d = factory.a("method-execution", (Signature) factory.a("1", "getFlags", "org.mp4parser.boxes.microsoft.PiffTrackEncryptionBox", "", "", "", "int"), 29);
    }

    public PiffTrackEncryptionBox() {
        super("uuid");
    }

    public byte[] af_() {
        return new byte[]{Constants.TagName.COMPANY_CODE, Constants.TagName.ELECTRONIC_USE_TYPE, -37, -50, Constants.TagName.ELECTRONIC_USE_TIME, -25, 76, Constants.TagName.TERMINAL_BACK_MAIN_ID, -124, -7, Constants.TagName.ELECTRONIC_TYPE_ID, Constants.TagName.BUSINESS_ORDER_TYPE, -7, Constants.TagName.ACTIVITY_DEFINITION, Constants.TagName.ORDER_RANGE_TYPE, Constants.TagName.TERMINAL_BACK_INFO_LIST};
    }

    public int d() {
        RequiresParseDetailAspect.a().a(Factory.a(d, (Object) this, (Object) this));
        return 0;
    }
}
