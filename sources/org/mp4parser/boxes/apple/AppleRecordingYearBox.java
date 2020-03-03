package org.mp4parser.boxes.apple;

import java.nio.ByteBuffer;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import miuipub.reflect.Field;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.Utf8;

public class AppleRecordingYearBox extends AppleDataBox {
    private static final JoinPoint.StaticPart f = null;
    private static final JoinPoint.StaticPart g = null;
    DateFormat d = new SimpleDateFormat("yyyy-MM-dd'T'kk:mm:ssZ");
    Date e = new Date();

    static {
        k();
    }

    private static void k() {
        Factory factory = new Factory("AppleRecordingYearBox.java", AppleRecordingYearBox.class);
        f = factory.a("method-execution", (Signature) factory.a("1", "getDate", "org.mp4parser.boxes.apple.AppleRecordingYearBox", "", "", "", "java.util.Date"), 38);
        g = factory.a("method-execution", (Signature) factory.a("1", "setDate", "org.mp4parser.boxes.apple.AppleRecordingYearBox", "java.util.Date", "date", "", "void"), 42);
    }

    public AppleRecordingYearBox() {
        super("©day", 1);
        this.d.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    protected static String a(String str) {
        return str.replaceAll("Z$", "+0000").replaceAll("([0-9][0-9]):([0-9][0-9])$", "$1$2");
    }

    protected static String b(String str) {
        return str.replaceAll("\\+0000$", Field.f3009a);
    }

    public Date d() {
        RequiresParseDetailAspect.a().a(Factory.a(f, (Object) this, (Object) this));
        return this.e;
    }

    public void a(Date date) {
        RequiresParseDetailAspect.a().a(Factory.a(g, (Object) this, (Object) this, (Object) date));
        this.e = date;
    }

    /* access modifiers changed from: protected */
    public byte[] e() {
        return Utf8.a(b(this.d.format(this.e)));
    }

    /* access modifiers changed from: protected */
    public void c(ByteBuffer byteBuffer) {
        try {
            this.e = this.d.parse(a(IsoTypeReader.a(byteBuffer, byteBuffer.remaining())));
        } catch (ParseException e2) {
            throw new RuntimeException(e2);
        }
    }

    /* access modifiers changed from: protected */
    public int f() {
        return Utf8.a(b(this.d.format(this.e))).length;
    }
}
