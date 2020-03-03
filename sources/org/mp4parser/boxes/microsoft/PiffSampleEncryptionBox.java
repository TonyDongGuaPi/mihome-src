package org.mp4parser.boxes.microsoft;

import cn.com.fmsh.script.constants.ScriptToolsConst;
import cn.com.fmsh.tsm.business.constants.Constants;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.internal.Conversions;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.boxes.iso23001.part7.AbstractSampleEncryptionBox;
import org.mp4parser.support.DoNotParseDetail;
import org.mp4parser.support.RequiresParseDetailAspect;

public class PiffSampleEncryptionBox extends AbstractSampleEncryptionBox {
    private static final JoinPoint.StaticPart e = null;
    private static final JoinPoint.StaticPart f = null;
    private static final JoinPoint.StaticPart g = null;
    private static final JoinPoint.StaticPart h = null;
    private static final JoinPoint.StaticPart i = null;
    private static final JoinPoint.StaticPart j = null;

    static {
        n();
    }

    private static void n() {
        Factory factory = new Factory("PiffSampleEncryptionBox.java", PiffSampleEncryptionBox.class);
        e = factory.a("method-execution", (Signature) factory.a("1", "getAlgorithmId", "org.mp4parser.boxes.microsoft.PiffSampleEncryptionBox", "", "", "", "int"), 46);
        f = factory.a("method-execution", (Signature) factory.a("1", "setAlgorithmId", "org.mp4parser.boxes.microsoft.PiffSampleEncryptionBox", "int", "algorithmId", "", "void"), 50);
        g = factory.a("method-execution", (Signature) factory.a("1", "getIvSize", "org.mp4parser.boxes.microsoft.PiffSampleEncryptionBox", "", "", "", "int"), 54);
        h = factory.a("method-execution", (Signature) factory.a("1", "setIvSize", "org.mp4parser.boxes.microsoft.PiffSampleEncryptionBox", "int", "ivSize", "", "void"), 58);
        i = factory.a("method-execution", (Signature) factory.a("1", "getKid", "org.mp4parser.boxes.microsoft.PiffSampleEncryptionBox", "", "", "", "[B"), 62);
        j = factory.a("method-execution", (Signature) factory.a("1", "setKid", "org.mp4parser.boxes.microsoft.PiffSampleEncryptionBox", "[B", "kid", "", "void"), 66);
    }

    public PiffSampleEncryptionBox() {
        super("uuid");
    }

    public byte[] af_() {
        return new byte[]{ScriptToolsConst.TagName.ResponseSingle, ScriptToolsConst.TagName.TagApdu, Constants.TagName.CP_NO, Constants.TagName.TERMINAL_BACK_QUESTION_FLAG, Constants.TagName.PREDEPOSIT_TOTAL, Constants.TagName.CITY_CODE, Constants.TagName.CP_NO, 20, ScriptToolsConst.TagName.ResponseSingle, Constants.TagName.TERMINAL_OS_VERSION, Constants.TagName.ELECTRONIC_LIST, Constants.TagName.INVOICE_TOKEN, Constants.TagName.PRICE, Constants.TagName.PAY_ORDER_LIST, Constants.TagName.ACTIVITY_STATUS, -12};
    }

    public int k() {
        RequiresParseDetailAspect.a().a(Factory.a(e, (Object) this, (Object) this));
        return this.f3932a;
    }

    public void c(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(f, (Object) this, (Object) this, Conversions.a(i2)));
        this.f3932a = i2;
    }

    public int l() {
        RequiresParseDetailAspect.a().a(Factory.a(g, (Object) this, (Object) this));
        return this.b;
    }

    public void d(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(h, (Object) this, (Object) this, Conversions.a(i2)));
        this.b = i2;
    }

    public byte[] m() {
        RequiresParseDetailAspect.a().a(Factory.a(i, (Object) this, (Object) this));
        return this.c;
    }

    public void a(byte[] bArr) {
        RequiresParseDetailAspect.a().a(Factory.a(j, (Object) this, (Object) this, (Object) bArr));
        this.c = bArr;
    }

    @DoNotParseDetail
    public boolean h() {
        return (d() & 1) > 0;
    }

    @DoNotParseDetail
    public void b(boolean z) {
        if (z) {
            b(d() | 1);
        } else {
            b(d() & 16777214);
        }
    }
}
