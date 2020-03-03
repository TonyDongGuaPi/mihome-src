package org.apache.commons.compress.archivers.zip;

import cn.com.fmsh.script.constants.ScriptToolsConst;
import cn.com.fmsh.tsm.business.constants.Constants;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import kotlin.text.Typography;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.apache.commons.compress.utils.Charsets;

public abstract class ZipEncodingHelper {

    /* renamed from: a  reason: collision with root package name */
    static final String f3290a = "UTF8";
    static final ZipEncoding b = new FallbackZipEncoding(f3290a);
    private static final Map<String, SimpleEncodingHolder> c;
    private static final byte[] d = {48, 49, 50, 51, 52, TarConstants.R, 54, 55, ScriptToolsConst.TagName.TagSerial, ScriptToolsConst.TagName.TagApdu, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.INVOICE_TOKEN, Constants.TagName.TERMINAL_BACK_INFO_TYPE, Constants.TagName.TERMINAL_OS_VERSION, Constants.TagName.TERMINAL_MODEL_NUMBER, Constants.TagName.TERMINAL_BASEBAND_VERSION};

    private static class SimpleEncodingHolder {

        /* renamed from: a  reason: collision with root package name */
        private final char[] f3291a;
        private Simple8BitZipEncoding b;

        SimpleEncodingHolder(char[] cArr) {
            this.f3291a = cArr;
        }

        public synchronized Simple8BitZipEncoding a() {
            if (this.b == null) {
                this.b = new Simple8BitZipEncoding(this.f3291a);
            }
            return this.b;
        }
    }

    static {
        HashMap hashMap = new HashMap();
        SimpleEncodingHolder simpleEncodingHolder = new SimpleEncodingHolder(new char[]{199, 252, 233, 226, 228, 224, 229, 231, 234, 235, 232, 239, 238, 236, 196, 197, 201, 230, 198, 244, 246, 242, 251, 249, 255, 214, 220, Typography.h, Typography.i, 165, 8359, 402, 225, 237, 243, 250, 241, 209, 170, 186, 191, 8976, 172, Typography.s, 188, 161, Typography.l, Typography.m, 9617, 9618, 9619, 9474, 9508, 9569, 9570, 9558, 9557, 9571, 9553, 9559, 9565, 9564, 9563, 9488, 9492, 9524, 9516, 9500, 9472, 9532, 9566, 9567, 9562, 9556, 9577, 9574, 9568, 9552, 9580, 9575, 9576, 9572, 9573, 9561, 9560, 9554, 9555, 9579, 9578, 9496, 9484, 9608, 9604, 9612, 9616, 9600, 945, 223, 915, 960, 931, 963, 181, 964, 934, 920, 937, 948, 8734, 966, 949, 8745, 8801, Typography.p, Typography.M, Typography.L, 8992, 8993, 247, Typography.J, Typography.o, 8729, Typography.r, 8730, 8319, 178, 9632, Typography.f});
        hashMap.put("CP437", simpleEncodingHolder);
        hashMap.put("Cp437", simpleEncodingHolder);
        hashMap.put("cp437", simpleEncodingHolder);
        hashMap.put("IBM437", simpleEncodingHolder);
        hashMap.put("ibm437", simpleEncodingHolder);
        SimpleEncodingHolder simpleEncodingHolder2 = new SimpleEncodingHolder(new char[]{199, 252, 233, 226, 228, 224, 229, 231, 234, 235, 232, 239, 238, 236, 196, 197, 201, 230, 198, 244, 246, 242, 251, 249, 255, 214, 220, 248, Typography.i, 216, Typography.g, 402, 225, 237, 243, 250, 241, 209, 170, 186, 191, Typography.n, 172, Typography.s, 188, 161, Typography.l, Typography.m, 9617, 9618, 9619, 9474, 9508, 193, 194, 192, Typography.k, 9571, 9553, 9559, 9565, Typography.h, 165, 9488, 9492, 9524, 9516, 9500, 9472, 9532, 227, 195, 9562, 9556, 9577, 9574, 9568, 9552, 9580, 164, 240, 208, 202, 203, 200, 305, 205, 206, 207, 9496, 9484, 9608, 9604, 166, 204, 9600, 211, 223, 212, 210, 245, 213, 181, 254, 222, 218, 219, 217, 253, 221, 175, 180, 173, Typography.p, 8215, 190, Typography.q, Typography.j, 247, 184, Typography.o, 168, Typography.r, 185, 179, 178, 9632, Typography.f});
        hashMap.put("CP850", simpleEncodingHolder2);
        hashMap.put("Cp850", simpleEncodingHolder2);
        hashMap.put("cp850", simpleEncodingHolder2);
        hashMap.put("IBM850", simpleEncodingHolder2);
        hashMap.put("ibm850", simpleEncodingHolder2);
        c = Collections.unmodifiableMap(hashMap);
    }

    static ByteBuffer a(ByteBuffer byteBuffer, int i) {
        byteBuffer.limit(byteBuffer.position());
        byteBuffer.rewind();
        int capacity = byteBuffer.capacity() * 2;
        if (capacity >= i) {
            i = capacity;
        }
        ByteBuffer allocate = ByteBuffer.allocate(i);
        allocate.put(byteBuffer);
        return allocate;
    }

    static void a(ByteBuffer byteBuffer, char c2) {
        byteBuffer.put(Constants.TagName.ORDER_RANGE_TYPE);
        byteBuffer.put(Constants.TagName.TERMINAL_BACK_CHILDREN_ID);
        byteBuffer.put(d[(c2 >> 12) & 15]);
        byteBuffer.put(d[(c2 >> 8) & 15]);
        byteBuffer.put(d[(c2 >> 4) & 15]);
        byteBuffer.put(d[c2 & 15]);
    }

    public static ZipEncoding a(String str) {
        if (b(str)) {
            return b;
        }
        if (str == null) {
            return new FallbackZipEncoding();
        }
        SimpleEncodingHolder simpleEncodingHolder = c.get(str);
        if (simpleEncodingHolder != null) {
            return simpleEncodingHolder.a();
        }
        try {
            return new NioZipEncoding(Charset.forName(str));
        } catch (UnsupportedCharsetException unused) {
            return new FallbackZipEncoding(str);
        }
    }

    static boolean b(String str) {
        if (str == null) {
            str = Charset.defaultCharset().name();
        }
        if (Charsets.f.name().equalsIgnoreCase(str)) {
            return true;
        }
        for (String equalsIgnoreCase : Charsets.f.aliases()) {
            if (equalsIgnoreCase.equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }
}
