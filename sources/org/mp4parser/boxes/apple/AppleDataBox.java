package org.mp4parser.boxes.apple;

import com.amap.location.common.model.AmapLoc;
import com.mi.global.shop.model.Tags;
import com.unionpay.tsmservice.data.Constant;
import com.xiaomi.smarthome.framework.api.UserConfig;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Locale;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.internal.Conversions;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractBox;
import org.mp4parser.support.DoNotParseDetail;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;

public abstract class AppleDataBox extends AbstractBox {
    private static HashMap<String, String> d = new HashMap<>();
    private static final JoinPoint.StaticPart e = null;
    private static final JoinPoint.StaticPart f = null;
    private static final JoinPoint.StaticPart g = null;
    private static final JoinPoint.StaticPart h = null;
    private static final JoinPoint.StaticPart i = null;
    private static final JoinPoint.StaticPart j = null;

    /* renamed from: a  reason: collision with root package name */
    int f3785a;
    int b;
    int c;

    private static void d() {
        Factory factory = new Factory("AppleDataBox.java", AppleDataBox.class);
        e = factory.a("method-execution", (Signature) factory.a("1", "getLanguageString", "org.mp4parser.boxes.apple.AppleDataBox", "", "", "", "java.lang.String"), 137);
        f = factory.a("method-execution", (Signature) factory.a("1", "getDataType", "org.mp4parser.boxes.apple.AppleDataBox", "", "", "", "int"), 155);
        g = factory.a("method-execution", (Signature) factory.a("1", "getDataCountry", "org.mp4parser.boxes.apple.AppleDataBox", "", "", "", "int"), 159);
        h = factory.a("method-execution", (Signature) factory.a("1", "setDataCountry", "org.mp4parser.boxes.apple.AppleDataBox", "int", "dataCountry", "", "void"), 163);
        i = factory.a("method-execution", (Signature) factory.a("1", "getDataLanguage", "org.mp4parser.boxes.apple.AppleDataBox", "", "", "", "int"), 167);
        j = factory.a("method-execution", (Signature) factory.a("1", "setDataLanguage", "org.mp4parser.boxes.apple.AppleDataBox", "int", "dataLanguage", "", "void"), 171);
    }

    /* access modifiers changed from: protected */
    public abstract void c(ByteBuffer byteBuffer);

    /* access modifiers changed from: protected */
    public abstract byte[] e();

    /* access modifiers changed from: protected */
    public abstract int f();

    static {
        d();
        d.put("0", "English");
        d.put("1", "French");
        d.put("2", "German");
        d.put("3", "Italian");
        d.put("4", "Dutch");
        d.put("5", "Swedish");
        d.put("6", "Spanish");
        d.put("7", "Danish");
        d.put("8", "Portuguese");
        d.put("9", "Norwegian");
        d.put("10", "Hebrew");
        d.put("11", "Japanese");
        d.put("12", "Arabic");
        d.put("13", "Finnish");
        d.put(AmapLoc.u, "Greek");
        d.put("15", "Icelandic");
        d.put(Tags.Phone.M22S_PHONE, "Maltese");
        d.put("17", "Turkish");
        d.put("18", "Croatian");
        d.put("19", "Traditional_Chinese");
        d.put(UserConfig.g, "Urdu");
        d.put("21", "Hindi");
        d.put("22", "Thai");
        d.put("23", "Korean");
        d.put(AmapLoc.w, "Lithuanian");
        d.put("25", "Polish");
        d.put("26", "Hungarian");
        d.put("27", "Estonian");
        d.put("28", "Lettish");
        d.put("29", "Sami");
        d.put("30", "Faroese");
        d.put("31", "Farsi");
        d.put(Tags.Phone.M2A_PHONE, "Russian");
        d.put("33", "Simplified_Chinese");
        d.put("34", "Flemish");
        d.put("35", "Irish");
        d.put("36", "Albanian");
        d.put("37", "Romanian");
        d.put("38", "Czech");
        d.put("39", "Slovak");
        d.put("40", "Slovenian");
        d.put("41", "Yiddish");
        d.put("42", "Serbian");
        d.put("43", "Macedonian");
        d.put("44", "Bulgarian");
        d.put("45", "Ukrainian");
        d.put("46", "Belarusian");
        d.put("47", "Uzbek");
        d.put("48", "Kazakh");
        d.put("49", "Azerbaijani");
        d.put("50", "AzerbaijanAr");
        d.put("51", "Armenian");
        d.put("52", "Georgian");
        d.put("53", "Moldavian");
        d.put("54", "Kirghiz");
        d.put("55", "Tajiki");
        d.put("56", "Turkmen");
        d.put("57", "Mongolian");
        d.put("58", "MongolianCyr");
        d.put("59", "Pashto");
        d.put(Constant.TRANS_TYPE_LOAD, "Kurdish");
        d.put("61", "Kashmiri");
        d.put("62", "Sindhi");
        d.put(Constant.TRANS_TYPE_CASH_LOAD, "Tibetan");
        d.put(Tags.Phone.MRED_PHONE, "Nepali");
        d.put("65", "Sanskrit");
        d.put("66", "Marathi");
        d.put("67", "Bengali");
        d.put("68", "Assamese");
        d.put("69", "Gujarati");
        d.put("70", "Punjabi");
        d.put("71", "Oriya");
        d.put("72", "Malayalam");
        d.put("73", "Kannada");
        d.put("74", "Tamil");
        d.put("75", "Telugu");
        d.put("76", "Sinhala");
        d.put("77", "Burmese");
        d.put("78", "Khmer");
        d.put("79", "Lao");
        d.put("80", "Vietnamese");
        d.put("81", "Indonesian");
        d.put("82", "Tagalog");
        d.put("83", "MalayRoman");
        d.put("84", "MalayArabic");
        d.put("85", "Amharic");
        d.put("87", "Galla");
        d.put("87", "Oromo");
        d.put("88", "Somali");
        d.put("89", "Swahili");
        d.put("90", "Kinyarwanda");
        d.put("91", "Rundi");
        d.put("92", "Nyanja");
        d.put("93", "Malagasy");
        d.put("94", "Esperanto");
        d.put(Tags.Phone.M3_PHONE, "Welsh");
        d.put("129", "Basque");
        d.put("130", "Catalan");
        d.put("131", "Latin");
        d.put("132", "Quechua");
        d.put("133", "Guarani");
        d.put("134", "Aymara");
        d.put("135", "Tatar");
        d.put("136", "Uighur");
        d.put("137", "Dzongkha");
        d.put("138", "JavaneseRom");
        d.put("32767", "Unspecified");
    }

    protected AppleDataBox(String str, int i2) {
        super(str);
        this.f3785a = i2;
    }

    public String g() {
        RequiresParseDetailAspect.a().a(Factory.a(e, (Object) this, (Object) this));
        HashMap<String, String> hashMap = d;
        StringBuilder sb = new StringBuilder();
        sb.append(this.c);
        String str = hashMap.get(sb.toString());
        if (str != null) {
            return str;
        }
        ByteBuffer wrap = ByteBuffer.wrap(new byte[2]);
        IsoTypeWriter.b(wrap, this.c);
        wrap.reset();
        return new Locale(IsoTypeReader.l(wrap)).getDisplayLanguage();
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return (long) (f() + 16);
    }

    public int h() {
        RequiresParseDetailAspect.a().a(Factory.a(f, (Object) this, (Object) this));
        return this.f3785a;
    }

    public int i() {
        RequiresParseDetailAspect.a().a(Factory.a(g, (Object) this, (Object) this));
        return this.b;
    }

    public void a(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(h, (Object) this, (Object) this, Conversions.a(i2)));
        this.b = i2;
    }

    public int j() {
        RequiresParseDetailAspect.a().a(Factory.a(i, (Object) this, (Object) this));
        return this.c;
    }

    public void b(int i2) {
        RequiresParseDetailAspect.a().a(Factory.a(j, (Object) this, (Object) this, Conversions.a(i2)));
        this.c = i2;
    }

    /* access modifiers changed from: protected */
    @DoNotParseDetail
    public ByteBuffer d(ByteBuffer byteBuffer) {
        int i2 = byteBuffer.getInt();
        byteBuffer.getInt();
        this.f3785a = byteBuffer.getInt();
        this.b = byteBuffer.getShort();
        if (this.b < 0) {
            this.b += 65536;
        }
        this.c = byteBuffer.getShort();
        if (this.c < 0) {
            this.c += 65536;
        }
        int i3 = i2 - 16;
        ByteBuffer byteBuffer2 = (ByteBuffer) byteBuffer.duplicate().slice().limit(i3);
        byteBuffer.position(i3 + byteBuffer.position());
        return byteBuffer2;
    }

    /* access modifiers changed from: protected */
    public void a(ByteBuffer byteBuffer) {
        c(d(byteBuffer));
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        e(byteBuffer);
        byteBuffer.put(e());
    }

    /* access modifiers changed from: protected */
    @DoNotParseDetail
    public void e(ByteBuffer byteBuffer) {
        byteBuffer.putInt(f() + 16);
        byteBuffer.put("data".getBytes());
        byteBuffer.putInt(this.f3785a);
        IsoTypeWriter.b(byteBuffer, this.b);
        IsoTypeWriter.b(byteBuffer, this.c);
    }
}
