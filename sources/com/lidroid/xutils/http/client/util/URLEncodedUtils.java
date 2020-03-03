package com.lidroid.xutils.http.client.util;

import android.text.TextUtils;
import cn.com.fmsh.tsm.business.constants.Constants;
import com.taobao.weex.el.parse.Operators;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import kotlin.text.Typography;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicHeaderValueParser;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.message.ParserCursor;
import org.apache.http.util.CharArrayBuffer;

public class URLEncodedUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final String f6355a = "application/x-www-form-urlencoded";
    private static final String b = "&";
    private static final String c = "=";
    private static final char[] d = {Typography.c};
    private static final BitSet e = new BitSet(256);
    private static final BitSet f = new BitSet(256);
    private static final BitSet g = new BitSet(256);
    private static final BitSet h = new BitSet(256);
    private static final BitSet i = new BitSet(256);
    private static final BitSet j = new BitSet(256);
    private static final BitSet k = new BitSet(256);
    private static final int l = 16;

    public static boolean a(HttpEntity httpEntity) {
        Header contentType = httpEntity.getContentType();
        if (contentType == null) {
            return false;
        }
        HeaderElement[] elements = contentType.getElements();
        if (elements.length > 0) {
            return elements[0].getName().equalsIgnoreCase("application/x-www-form-urlencoded");
        }
        return false;
    }

    public static List<NameValuePair> a(URI uri) {
        String rawQuery = uri.getRawQuery();
        if (TextUtils.isEmpty(rawQuery)) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        a((List<NameValuePair>) arrayList, new Scanner(rawQuery));
        return arrayList;
    }

    public static void a(List<NameValuePair> list, Scanner scanner) {
        String str;
        String str2;
        scanner.useDelimiter("&");
        while (scanner.hasNext()) {
            String next = scanner.next();
            int indexOf = next.indexOf("=");
            if (indexOf != -1) {
                str2 = next.substring(0, indexOf).trim();
                str = next.substring(indexOf + 1).trim();
            } else {
                String trim = next.trim();
                str = null;
                str2 = trim;
            }
            list.add(new BasicNameValuePair(str2, str));
        }
    }

    static {
        for (int i2 = 97; i2 <= 122; i2++) {
            e.set(i2);
        }
        for (int i3 = 65; i3 <= 90; i3++) {
            e.set(i3);
        }
        for (int i4 = 48; i4 <= 57; i4++) {
            e.set(i4);
        }
        e.set(95);
        e.set(45);
        e.set(46);
        e.set(42);
        k.or(e);
        e.set(33);
        e.set(126);
        e.set(39);
        e.set(40);
        e.set(41);
        f.set(44);
        f.set(59);
        f.set(58);
        f.set(36);
        f.set(38);
        f.set(43);
        f.set(61);
        g.or(e);
        g.or(f);
        h.or(e);
        h.set(47);
        h.set(59);
        h.set(58);
        h.set(64);
        h.set(38);
        h.set(61);
        h.set(43);
        h.set(36);
        h.set(44);
        j.set(59);
        j.set(47);
        j.set(63);
        j.set(58);
        j.set(64);
        j.set(38);
        j.set(61);
        j.set(43);
        j.set(36);
        j.set(44);
        j.set(91);
        j.set(93);
        i.or(j);
        i.or(e);
    }

    public static List<NameValuePair> a(String str) {
        if (str == null) {
            return Collections.emptyList();
        }
        BasicHeaderValueParser basicHeaderValueParser = BasicHeaderValueParser.DEFAULT;
        CharArrayBuffer charArrayBuffer = new CharArrayBuffer(str.length());
        charArrayBuffer.append(str);
        ParserCursor parserCursor = new ParserCursor(0, charArrayBuffer.length());
        ArrayList arrayList = new ArrayList();
        while (!parserCursor.atEnd()) {
            NameValuePair parseNameValuePair = basicHeaderValueParser.parseNameValuePair(charArrayBuffer, parserCursor, d);
            if (parseNameValuePair.getName().length() > 0) {
                arrayList.add(new BasicNameValuePair(parseNameValuePair.getName(), parseNameValuePair.getValue()));
            }
        }
        return arrayList;
    }

    public static String a(List<? extends NameValuePair> list, String str) {
        StringBuilder sb = new StringBuilder();
        for (NameValuePair nameValuePair : list) {
            String b2 = b(nameValuePair.getName(), str);
            String b3 = b(nameValuePair.getValue(), str);
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(b2);
            if (b3 != null) {
                sb.append("=");
                sb.append(b3);
            }
        }
        return sb.toString();
    }

    public static String a(Iterable<? extends NameValuePair> iterable, Charset charset) {
        StringBuilder sb = new StringBuilder();
        for (NameValuePair nameValuePair : iterable) {
            String e2 = e(nameValuePair.getName(), charset);
            String e3 = e(nameValuePair.getValue(), charset);
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(e2);
            if (e3 != null) {
                sb.append("=");
                sb.append(e3);
            }
        }
        return sb.toString();
    }

    private static String a(String str, Charset charset, BitSet bitSet, boolean z) {
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        ByteBuffer encode = charset.encode(str);
        while (encode.hasRemaining()) {
            byte b2 = encode.get() & 255;
            if (bitSet.get(b2)) {
                sb.append((char) b2);
            } else if (!z || b2 != 32) {
                sb.append(Operators.MOD);
                char upperCase = Character.toUpperCase(Character.forDigit((b2 >> 4) & 15, 16));
                char upperCase2 = Character.toUpperCase(Character.forDigit(b2 & 15, 16));
                sb.append(upperCase);
                sb.append(upperCase2);
            } else {
                sb.append('+');
            }
        }
        return sb.toString();
    }

    private static String a(String str, Charset charset, boolean z) {
        if (str == null) {
            return null;
        }
        ByteBuffer allocate = ByteBuffer.allocate(str.length());
        CharBuffer wrap = CharBuffer.wrap(str);
        while (wrap.hasRemaining()) {
            char c2 = wrap.get();
            if (c2 == '%' && wrap.remaining() >= 2) {
                char c3 = wrap.get();
                char c4 = wrap.get();
                int digit = Character.digit(c3, 16);
                int digit2 = Character.digit(c4, 16);
                if (digit == -1 || digit2 == -1) {
                    allocate.put(Constants.TagName.ORDER_RANGE_TYPE);
                    allocate.put((byte) c3);
                    allocate.put((byte) c4);
                } else {
                    allocate.put((byte) ((digit << 4) + digit2));
                }
            } else if (!z || c2 != '+') {
                allocate.put((byte) c2);
            } else {
                allocate.put((byte) 32);
            }
        }
        allocate.flip();
        return charset.decode(allocate).toString();
    }

    private static String a(String str, String str2) {
        if (str == null) {
            return null;
        }
        if (str2 == null) {
            str2 = "UTF-8";
        }
        return a(str, Charset.forName(str2), true);
    }

    private static String d(String str, Charset charset) {
        if (str == null) {
            return null;
        }
        if (charset == null) {
            charset = Charset.forName("UTF-8");
        }
        return a(str, charset, true);
    }

    private static String b(String str, String str2) {
        Charset charset;
        if (str == null) {
            return null;
        }
        if (str2 != null) {
            charset = Charset.forName(str2);
        } else {
            charset = Charset.forName("UTF-8");
        }
        return a(str, charset, k, true);
    }

    private static String e(String str, Charset charset) {
        if (str == null) {
            return null;
        }
        if (charset == null) {
            charset = Charset.forName("UTF-8");
        }
        return a(str, charset, k, true);
    }

    static String a(String str, Charset charset) {
        return a(str, charset, g, false);
    }

    static String b(String str, Charset charset) {
        return a(str, charset, i, false);
    }

    static String c(String str, Charset charset) {
        return a(str, charset, h, false);
    }
}
