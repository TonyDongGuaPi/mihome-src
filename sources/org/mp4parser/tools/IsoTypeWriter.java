package org.mp4parser.tools;

import cn.com.fmsh.communication.core.MessageHead;
import cn.com.fmsh.script.constants.ScriptToolsConst;
import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

public final class IsoTypeWriter {

    /* renamed from: a  reason: collision with root package name */
    static final /* synthetic */ boolean f4109a = (!IsoTypeWriter.class.desiredAssertionStatus());

    public static void a(ByteBuffer byteBuffer, long j) {
        if (f4109a || j >= 0) {
            byteBuffer.putLong(j);
            return;
        }
        throw new AssertionError("The given long is negative");
    }

    public static void b(ByteBuffer byteBuffer, long j) {
        if (f4109a || (j >= 0 && j <= IjkMediaMeta.AV_CH_WIDE_RIGHT)) {
            byteBuffer.putInt((int) j);
            return;
        }
        throw new AssertionError("The given long is not in the range of uint32 (" + j + Operators.BRACKET_END_STR);
    }

    public static void c(ByteBuffer byteBuffer, long j) {
        if (f4109a || (j >= 0 && j <= IjkMediaMeta.AV_CH_WIDE_RIGHT)) {
            c(byteBuffer, ((int) j) & 65535);
            c(byteBuffer, (int) ((j >> 16) & 65535));
            return;
        }
        throw new AssertionError("The given long is not in the range of uint32 (" + j + Operators.BRACKET_END_STR);
    }

    public static void a(ByteBuffer byteBuffer, int i) {
        int i2 = i & 16777215;
        b(byteBuffer, i2 >> 8);
        d(byteBuffer, i2);
    }

    public static void d(ByteBuffer byteBuffer, long j) {
        long j2 = j & 281474976710655L;
        b(byteBuffer, (int) (j2 >> 32));
        b(byteBuffer, j2 & MessageHead.SERIAL_MAK);
    }

    public static void b(ByteBuffer byteBuffer, int i) {
        int i2 = i & 65535;
        d(byteBuffer, i2 >> 8);
        d(byteBuffer, i2 & 255);
    }

    public static void c(ByteBuffer byteBuffer, int i) {
        int i2 = i & 65535;
        d(byteBuffer, i2 & 255);
        d(byteBuffer, i2 >> 8);
    }

    public static void d(ByteBuffer byteBuffer, int i) {
        byteBuffer.put((byte) (i & 255));
    }

    public static void a(ByteBuffer byteBuffer, double d) {
        int i = (int) (d * 65536.0d);
        byteBuffer.put((byte) ((-16777216 & i) >> 24));
        byteBuffer.put((byte) ((16711680 & i) >> 16));
        byteBuffer.put((byte) ((65280 & i) >> 8));
        byteBuffer.put((byte) (i & 255));
    }

    public static void b(ByteBuffer byteBuffer, double d) {
        int i = (int) (d * 1.073741824E9d);
        byteBuffer.put((byte) ((-16777216 & i) >> 24));
        byteBuffer.put((byte) ((16711680 & i) >> 16));
        byteBuffer.put((byte) ((65280 & i) >> 8));
        byteBuffer.put((byte) (i & 255));
    }

    public static void c(ByteBuffer byteBuffer, double d) {
        short s = (short) ((int) (d * 256.0d));
        byteBuffer.put((byte) ((65280 & s) >> 8));
        byteBuffer.put((byte) (s & 255));
    }

    public static void a(ByteBuffer byteBuffer, String str) {
        if (str.getBytes().length == 3) {
            int i = 0;
            for (int i2 = 0; i2 < 3; i2++) {
                i += (str.getBytes()[i2] + ScriptToolsConst.TagName.CommandSingle) << ((2 - i2) * 5);
            }
            b(byteBuffer, i);
            return;
        }
        throw new IllegalArgumentException("\"" + str + "\" language string isn't exactly 3 characters long!");
    }

    public static void b(ByteBuffer byteBuffer, String str) {
        byte[] a2 = Utf8.a(str);
        if (f4109a || a2.length < 255) {
            d(byteBuffer, a2.length);
            byteBuffer.put(a2);
            return;
        }
        throw new AssertionError();
    }

    public static void c(ByteBuffer byteBuffer, String str) {
        byteBuffer.put(Utf8.a(str));
        d(byteBuffer, 0);
    }

    public static void d(ByteBuffer byteBuffer, String str) {
        byteBuffer.put(Utf8.a(str));
        d(byteBuffer, 0);
    }
}
