package cn.jiajixin.nuwa.util;

import com.google.code.microlog4android.format.PatternFormatter;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

public class ByteUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final char[] f662a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', PatternFormatter.CATEGORY_CONVERSION_CHAR, PatternFormatter.DATE_CONVERSION_CHAR, 'e', 'f'};

    static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            sb.append(f662a[(b & 240) >>> 4]);
            sb.append(f662a[b & 15]);
        }
        return sb.toString();
    }

    static String a(ByteBuffer byteBuffer) {
        try {
            CharBuffer decode = Charset.forName("UTF-8").newDecoder().decode(byteBuffer);
            byteBuffer.flip();
            return decode.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
