package com.xiaomi.zxing.oned.rss.expanded.decoders;

import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.fastvideo.IOUtils;
import com.xiaomi.zxing.FormatException;
import com.xiaomi.zxing.NotFoundException;
import com.xiaomi.zxing.common.BitArray;
import kotlin.text.Typography;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

final class GeneralAppIdDecoder {

    /* renamed from: a  reason: collision with root package name */
    private final BitArray f1729a;
    private final CurrentParsingState b = new CurrentParsingState();
    private final StringBuilder c = new StringBuilder();

    GeneralAppIdDecoder(BitArray bitArray) {
        this.f1729a = bitArray;
    }

    /* access modifiers changed from: package-private */
    public String a(StringBuilder sb, int i) throws NotFoundException, FormatException {
        String str = null;
        while (true) {
            DecodedInformation a2 = a(i, str);
            String a3 = FieldParser.a(a2.a());
            if (a3 != null) {
                sb.append(a3);
            }
            String valueOf = a2.b() ? String.valueOf(a2.c()) : null;
            if (i == a2.g()) {
                return sb.toString();
            }
            i = a2.g();
            str = valueOf;
        }
    }

    private boolean a(int i) {
        if (i + 7 <= this.f1729a.a()) {
            int i2 = i;
            while (true) {
                int i3 = i + 3;
                if (i2 >= i3) {
                    return this.f1729a.a(i3);
                }
                if (this.f1729a.a(i2)) {
                    return true;
                }
                i2++;
            }
        } else if (i + 4 <= this.f1729a.a()) {
            return true;
        } else {
            return false;
        }
    }

    private DecodedNumeric b(int i) throws FormatException {
        int i2 = i + 7;
        if (i2 > this.f1729a.a()) {
            int a2 = a(i, 4);
            if (a2 == 0) {
                return new DecodedNumeric(this.f1729a.a(), 10, 10);
            }
            return new DecodedNumeric(this.f1729a.a(), a2 - 1, 10);
        }
        int a3 = a(i, 7) - 8;
        return new DecodedNumeric(i2, a3 / 11, a3 % 11);
    }

    /* access modifiers changed from: package-private */
    public int a(int i, int i2) {
        return a(this.f1729a, i, i2);
    }

    static int a(BitArray bitArray, int i, int i2) {
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            if (bitArray.a(i + i4)) {
                i3 |= 1 << ((i2 - i4) - 1);
            }
        }
        return i3;
    }

    /* access modifiers changed from: package-private */
    public DecodedInformation a(int i, String str) throws FormatException {
        this.c.setLength(0);
        if (str != null) {
            this.c.append(str);
        }
        this.b.a(i);
        DecodedInformation a2 = a();
        if (a2 == null || !a2.b()) {
            return new DecodedInformation(this.b.a(), this.c.toString());
        }
        return new DecodedInformation(this.b.a(), this.c.toString(), a2.c());
    }

    private DecodedInformation a() throws FormatException {
        boolean z;
        BlockParsedResult blockParsedResult;
        do {
            int a2 = this.b.a();
            if (this.b.b()) {
                blockParsedResult = d();
                z = blockParsedResult.b();
            } else if (this.b.d()) {
                blockParsedResult = c();
                z = blockParsedResult.b();
            } else {
                blockParsedResult = b();
                z = blockParsedResult.b();
            }
            if (!(a2 != this.b.a()) && !z) {
                break;
            }
        } while (!z);
        return blockParsedResult.a();
    }

    private BlockParsedResult b() throws FormatException {
        DecodedInformation decodedInformation;
        while (a(this.b.a())) {
            DecodedNumeric b2 = b(this.b.a());
            this.b.a(b2.g());
            if (b2.d()) {
                if (b2.e()) {
                    decodedInformation = new DecodedInformation(this.b.a(), this.c.toString());
                } else {
                    decodedInformation = new DecodedInformation(this.b.a(), this.c.toString(), b2.b());
                }
                return new BlockParsedResult(decodedInformation, true);
            }
            this.c.append(b2.a());
            if (b2.e()) {
                return new BlockParsedResult(new DecodedInformation(this.b.a(), this.c.toString()), true);
            }
            this.c.append(b2.b());
        }
        if (i(this.b.a())) {
            this.b.f();
            this.b.b(4);
        }
        return new BlockParsedResult(false);
    }

    private BlockParsedResult c() throws FormatException {
        while (c(this.b.a())) {
            DecodedChar d = d(this.b.a());
            this.b.a(d.g());
            if (d.b()) {
                return new BlockParsedResult(new DecodedInformation(this.b.a(), this.c.toString()), true);
            }
            this.c.append(d.a());
        }
        if (h(this.b.a())) {
            this.b.b(3);
            this.b.e();
        } else if (g(this.b.a())) {
            if (this.b.a() + 5 < this.f1729a.a()) {
                this.b.b(5);
            } else {
                this.b.a(this.f1729a.a());
            }
            this.b.f();
        }
        return new BlockParsedResult(false);
    }

    private BlockParsedResult d() {
        while (e(this.b.a())) {
            DecodedChar f = f(this.b.a());
            this.b.a(f.g());
            if (f.b()) {
                return new BlockParsedResult(new DecodedInformation(this.b.a(), this.c.toString()), true);
            }
            this.c.append(f.a());
        }
        if (h(this.b.a())) {
            this.b.b(3);
            this.b.e();
        } else if (g(this.b.a())) {
            if (this.b.a() + 5 < this.f1729a.a()) {
                this.b.b(5);
            } else {
                this.b.a(this.f1729a.a());
            }
            this.b.g();
        }
        return new BlockParsedResult(false);
    }

    private boolean c(int i) {
        int a2;
        if (i + 5 > this.f1729a.a()) {
            return false;
        }
        int a3 = a(i, 5);
        if (a3 >= 5 && a3 < 16) {
            return true;
        }
        if (i + 7 > this.f1729a.a()) {
            return false;
        }
        int a4 = a(i, 7);
        if (a4 >= 64 && a4 < 116) {
            return true;
        }
        if (i + 8 <= this.f1729a.a() && (a2 = a(i, 8)) >= 232 && a2 < 253) {
            return true;
        }
        return false;
    }

    private DecodedChar d(int i) throws FormatException {
        char c2;
        int a2 = a(i, 5);
        if (a2 == 15) {
            return new DecodedChar(i + 5, '$');
        }
        if (a2 >= 5 && a2 < 15) {
            return new DecodedChar(i + 5, (char) ((a2 + 48) - 5));
        }
        int a3 = a(i, 7);
        if (a3 >= 64 && a3 < 90) {
            return new DecodedChar(i + 7, (char) (a3 + 1));
        }
        if (a3 >= 90 && a3 < 116) {
            return new DecodedChar(i + 7, (char) (a3 + 7));
        }
        switch (a(i, 8)) {
            case 232:
                c2 = '!';
                break;
            case 233:
                c2 = '\"';
                break;
            case 234:
                c2 = '%';
                break;
            case 235:
                c2 = Typography.c;
                break;
            case 236:
                c2 = Operators.SINGLE_QUOTE;
                break;
            case 237:
                c2 = Operators.BRACKET_START;
                break;
            case 238:
                c2 = Operators.BRACKET_END;
                break;
            case 239:
                c2 = '*';
                break;
            case PsExtractor.VIDEO_STREAM_MASK /*240*/:
                c2 = '+';
                break;
            case 241:
                c2 = ',';
                break;
            case 242:
                c2 = '-';
                break;
            case 243:
                c2 = '.';
                break;
            case IjkMediaMeta.FF_PROFILE_H264_HIGH_444_PREDICTIVE /*244*/:
                c2 = IOUtils.f15883a;
                break;
            case 245:
                c2 = Operators.CONDITION_IF_MIDDLE;
                break;
            case 246:
                c2 = ';';
                break;
            case 247:
                c2 = Typography.d;
                break;
            case 248:
                c2 = '=';
                break;
            case 249:
                c2 = Typography.e;
                break;
            case 250:
                c2 = Operators.CONDITION_IF;
                break;
            case 251:
                c2 = '_';
                break;
            case 252:
                c2 = ' ';
                break;
            default:
                throw FormatException.getFormatInstance();
        }
        return new DecodedChar(i + 8, c2);
    }

    private boolean e(int i) {
        int a2;
        if (i + 5 > this.f1729a.a()) {
            return false;
        }
        int a3 = a(i, 5);
        if (a3 >= 5 && a3 < 16) {
            return true;
        }
        if (i + 6 <= this.f1729a.a() && (a2 = a(i, 6)) >= 16 && a2 < 63) {
            return true;
        }
        return false;
    }

    private DecodedChar f(int i) {
        char c2;
        int a2 = a(i, 5);
        if (a2 == 15) {
            return new DecodedChar(i + 5, '$');
        }
        if (a2 >= 5 && a2 < 15) {
            return new DecodedChar(i + 5, (char) ((a2 + 48) - 5));
        }
        int a3 = a(i, 6);
        if (a3 >= 32 && a3 < 58) {
            return new DecodedChar(i + 6, (char) (a3 + 33));
        }
        switch (a3) {
            case 58:
                c2 = '*';
                break;
            case 59:
                c2 = ',';
                break;
            case 60:
                c2 = '-';
                break;
            case 61:
                c2 = '.';
                break;
            case 62:
                c2 = IOUtils.f15883a;
                break;
            default:
                throw new IllegalStateException("Decoding invalid alphanumeric value: " + a3);
        }
        return new DecodedChar(i + 6, c2);
    }

    private boolean g(int i) {
        int i2;
        if (i + 1 > this.f1729a.a()) {
            return false;
        }
        int i3 = 0;
        while (i3 < 5 && (i2 = i3 + i) < this.f1729a.a()) {
            if (i3 == 2) {
                if (!this.f1729a.a(i + 2)) {
                    return false;
                }
            } else if (this.f1729a.a(i2)) {
                return false;
            }
            i3++;
        }
        return true;
    }

    private boolean h(int i) {
        int i2 = i + 3;
        if (i2 > this.f1729a.a()) {
            return false;
        }
        while (i < i2) {
            if (this.f1729a.a(i)) {
                return false;
            }
            i++;
        }
        return true;
    }

    private boolean i(int i) {
        int i2;
        if (i + 1 > this.f1729a.a()) {
            return false;
        }
        int i3 = 0;
        while (i3 < 4 && (i2 = i3 + i) < this.f1729a.a()) {
            if (this.f1729a.a(i2)) {
                return false;
            }
            i3++;
        }
        return true;
    }
}
