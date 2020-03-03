package com.taobao.weex.wson;

import android.support.v4.util.LruCache;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.taobao.weex.utils.WXLogUtils;
import com.tencent.smtt.sdk.TbsListener;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class Wson {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    private static final byte ARRAY_TYPE = 91;
    private static final byte BOOLEAN_TYPE_FALSE = 102;
    private static final byte BOOLEAN_TYPE_TRUE = 116;
    private static final int GLOBAL_STRING_CACHE_SIZE = 2048;
    private static final boolean IS_NATIVE_LITTLE_ENDIAN;
    private static final byte MAP_TYPE = 123;
    private static final String METHOD_PREFIX_GET = "get";
    private static final String METHOD_PREFIX_IS = "is";
    private static final byte NULL_TYPE = 48;
    private static final byte NUMBER_BIG_DECIMAL_TYPE = 101;
    private static final byte NUMBER_BIG_INTEGER_TYPE = 103;
    private static final byte NUMBER_DOUBLE_TYPE = 100;
    private static final byte NUMBER_FLOAT_TYPE = 70;
    private static final byte NUMBER_INT_TYPE = 105;
    private static final byte NUMBER_LONG_TYPE = 108;
    private static final byte STRING_TYPE = 115;
    public static final boolean WriteMapNullValue = false;
    private static LruCache<String, List<Field>> fieldsCache = new LruCache<>(128);
    private static final String[] globalStringBytesCache = new String[2048];
    private static final ThreadLocal<char[]> localCharsBufferCache = new ThreadLocal<>();
    private static LruCache<String, List<Method>> methodsCache = new LruCache<>(128);
    private static LruCache<String, Boolean> specialClass = new LruCache<>(16);

    /* renamed from: com.taobao.weex.wson.Wson$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        private static transient /* synthetic */ boolean[] $jacocoData;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(6779802696156068277L, "com/taobao/weex/wson/Wson$1", 0);
            $jacocoData = a2;
            return a2;
        }
    }

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(4912169872907080935L, "com/taobao/weex/wson/Wson", 55);
        $jacocoData = a2;
        return a2;
    }

    public Wson() {
        $jacocoInit()[0] = true;
    }

    static /* synthetic */ List access$1000(String str, Class cls) {
        boolean[] $jacocoInit = $jacocoInit();
        List<Method> beanMethod = getBeanMethod(str, cls);
        $jacocoInit[46] = true;
        return beanMethod;
    }

    static /* synthetic */ List access$1100(String str, Class cls) {
        boolean[] $jacocoInit = $jacocoInit();
        List<Field> beanFields = getBeanFields(str, cls);
        $jacocoInit[47] = true;
        return beanFields;
    }

    static /* synthetic */ ThreadLocal access$600() {
        boolean[] $jacocoInit = $jacocoInit();
        ThreadLocal<char[]> threadLocal = localCharsBufferCache;
        $jacocoInit[42] = true;
        return threadLocal;
    }

    static /* synthetic */ boolean access$700() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = IS_NATIVE_LITTLE_ENDIAN;
        $jacocoInit[43] = true;
        return z;
    }

    static /* synthetic */ String[] access$800() {
        boolean[] $jacocoInit = $jacocoInit();
        String[] strArr = globalStringBytesCache;
        $jacocoInit[44] = true;
        return strArr;
    }

    static /* synthetic */ LruCache access$900() {
        boolean[] $jacocoInit = $jacocoInit();
        LruCache<String, Boolean> lruCache = specialClass;
        $jacocoInit[45] = true;
        return lruCache;
    }

    static {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN) {
            $jacocoInit[48] = true;
            z = true;
        } else {
            z = false;
            $jacocoInit[49] = true;
        }
        IS_NATIVE_LITTLE_ENDIAN = z;
        $jacocoInit[50] = true;
        $jacocoInit[51] = true;
        $jacocoInit[52] = true;
        $jacocoInit[53] = true;
        $jacocoInit[54] = true;
    }

    public static final Object parse(byte[] bArr) {
        boolean[] $jacocoInit = $jacocoInit();
        if (bArr != null) {
            $jacocoInit[1] = true;
            try {
                Parser parser = new Parser(bArr, (AnonymousClass1) null);
                $jacocoInit[3] = true;
                Object access$100 = Parser.access$100(parser);
                $jacocoInit[4] = true;
                Parser.access$200(parser);
                $jacocoInit[5] = true;
                return access$100;
            } catch (Exception e) {
                $jacocoInit[6] = true;
                WXLogUtils.e("parseWson", (Throwable) e);
                $jacocoInit[7] = true;
                return null;
            }
        } else {
            $jacocoInit[2] = true;
            return null;
        }
    }

    public static final byte[] toWson(Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        if (obj == null) {
            $jacocoInit[8] = true;
            return null;
        }
        Builder builder = new Builder((AnonymousClass1) null);
        $jacocoInit[9] = true;
        byte[] access$400 = Builder.access$400(builder, obj);
        $jacocoInit[10] = true;
        Builder.access$500(builder);
        $jacocoInit[11] = true;
        return access$400;
    }

    private static final class Parser {
        private static transient /* synthetic */ boolean[] $jacocoData;
        private byte[] buffer;
        private char[] charsBuffer;
        private int position;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(-2513277265195507225L, "com/taobao/weex/wson/Wson$Parser", 86);
            $jacocoData = a2;
            return a2;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ Parser(byte[] bArr, AnonymousClass1 r3) {
            this(bArr);
            boolean[] $jacocoInit = $jacocoInit();
            $jacocoInit[83] = true;
        }

        static /* synthetic */ Object access$100(Parser parser) {
            boolean[] $jacocoInit = $jacocoInit();
            Object parse = parser.parse();
            $jacocoInit[84] = true;
            return parse;
        }

        static /* synthetic */ void access$200(Parser parser) {
            boolean[] $jacocoInit = $jacocoInit();
            parser.close();
            $jacocoInit[85] = true;
        }

        private Parser(byte[] bArr) {
            boolean[] $jacocoInit = $jacocoInit();
            this.position = 0;
            this.buffer = bArr;
            $jacocoInit[0] = true;
            this.charsBuffer = (char[]) Wson.access$600().get();
            if (this.charsBuffer != null) {
                $jacocoInit[1] = true;
                Wson.access$600().set((Object) null);
                $jacocoInit[2] = true;
            } else {
                this.charsBuffer = new char[512];
                $jacocoInit[3] = true;
            }
            $jacocoInit[4] = true;
        }

        private final Object parse() {
            boolean[] $jacocoInit = $jacocoInit();
            Object readObject = readObject();
            $jacocoInit[5] = true;
            return readObject;
        }

        private final void close() {
            boolean[] $jacocoInit = $jacocoInit();
            this.position = 0;
            this.buffer = null;
            if (this.charsBuffer == null) {
                $jacocoInit[6] = true;
            } else {
                $jacocoInit[7] = true;
                Wson.access$600().set(this.charsBuffer);
                $jacocoInit[8] = true;
            }
            this.charsBuffer = null;
            $jacocoInit[9] = true;
        }

        private final Object readObject() {
            boolean[] $jacocoInit = $jacocoInit();
            byte readType = readType();
            if (readType == 48) {
                $jacocoInit[21] = true;
                return null;
            } else if (readType == 70) {
                Object readFloat = readFloat();
                $jacocoInit[12] = true;
                return readFloat;
            } else if (readType == 91) {
                Object readArray = readArray();
                $jacocoInit[14] = true;
                return readArray;
            } else if (readType == 105) {
                Integer valueOf = Integer.valueOf(readVarInt());
                $jacocoInit[11] = true;
                return valueOf;
            } else if (readType == 108) {
                Long valueOf2 = Long.valueOf(readLong());
                $jacocoInit[16] = true;
                return valueOf2;
            } else if (readType != 123) {
                switch (readType) {
                    case 100:
                        Object readDouble = readDouble();
                        $jacocoInit[15] = true;
                        return readDouble;
                    case 101:
                        BigDecimal bigDecimal = new BigDecimal(readUTF16String());
                        $jacocoInit[18] = true;
                        return bigDecimal;
                    case 102:
                        Boolean bool = Boolean.FALSE;
                        $jacocoInit[19] = true;
                        return bool;
                    case 103:
                        BigInteger bigInteger = new BigInteger(readUTF16String());
                        $jacocoInit[17] = true;
                        return bigInteger;
                    default:
                        switch (readType) {
                            case 115:
                                String readUTF16String = readUTF16String();
                                $jacocoInit[10] = true;
                                return readUTF16String;
                            case 116:
                                Boolean bool2 = Boolean.TRUE;
                                $jacocoInit[20] = true;
                                return bool2;
                            default:
                                RuntimeException runtimeException = new RuntimeException("wson unhandled type " + readType + " " + this.position + " length " + this.buffer.length);
                                $jacocoInit[22] = true;
                                throw runtimeException;
                        }
                }
            } else {
                Object readMap = readMap();
                $jacocoInit[13] = true;
                return readMap;
            }
        }

        private final Object readMap() {
            boolean[] $jacocoInit = $jacocoInit();
            int readUInt = readUInt();
            $jacocoInit[23] = true;
            JSONObject jSONObject = new JSONObject();
            $jacocoInit[24] = true;
            int i = 0;
            while (i < readUInt) {
                $jacocoInit[25] = true;
                String readMapKeyUTF16 = readMapKeyUTF16();
                $jacocoInit[26] = true;
                Object readObject = readObject();
                $jacocoInit[27] = true;
                jSONObject.put(readMapKeyUTF16, readObject);
                i++;
                $jacocoInit[28] = true;
            }
            $jacocoInit[29] = true;
            return jSONObject;
        }

        private final Object readArray() {
            boolean[] $jacocoInit = $jacocoInit();
            int readUInt = readUInt();
            $jacocoInit[30] = true;
            JSONArray jSONArray = new JSONArray(readUInt);
            $jacocoInit[31] = true;
            int i = 0;
            while (i < readUInt) {
                $jacocoInit[32] = true;
                jSONArray.add(readObject());
                i++;
                $jacocoInit[33] = true;
            }
            $jacocoInit[34] = true;
            return jSONArray;
        }

        private final byte readType() {
            boolean[] $jacocoInit = $jacocoInit();
            byte b = this.buffer[this.position];
            this.position++;
            $jacocoInit[35] = true;
            return b;
        }

        private final String readMapKeyUTF16() {
            int i;
            boolean z;
            boolean[] $jacocoInit = $jacocoInit();
            int readUInt = readUInt() / 2;
            if (this.charsBuffer.length >= readUInt) {
                $jacocoInit[36] = true;
            } else {
                this.charsBuffer = new char[readUInt];
                $jacocoInit[37] = true;
            }
            $jacocoInit[38] = true;
            if (Wson.access$700()) {
                $jacocoInit[39] = true;
                int i2 = 0;
                i = 5381;
                while (i2 < readUInt) {
                    char c = (char) ((this.buffer[this.position] & 255) + (this.buffer[this.position + 1] << 8));
                    this.charsBuffer[i2] = c;
                    i = (i << 5) + i + c;
                    this.position += 2;
                    i2++;
                    $jacocoInit[40] = true;
                }
                $jacocoInit[41] = true;
            } else {
                $jacocoInit[42] = true;
                int i3 = 0;
                int i4 = 5381;
                while (i3 < readUInt) {
                    char c2 = (char) ((this.buffer[this.position + 1] & 255) + (this.buffer[this.position] << 8));
                    this.charsBuffer[i3] = c2;
                    i4 = (i << 5) + i + c2;
                    this.position += 2;
                    i3++;
                    $jacocoInit[44] = true;
                }
                $jacocoInit[43] = true;
            }
            int length = (Wson.access$800().length - 1) & i;
            $jacocoInit[45] = true;
            String str = Wson.access$800()[length];
            if (str == null) {
                $jacocoInit[46] = true;
            } else {
                $jacocoInit[47] = true;
                if (str.length() != readUInt) {
                    $jacocoInit[48] = true;
                } else {
                    $jacocoInit[49] = true;
                    int i5 = 0;
                    while (true) {
                        if (i5 >= readUInt) {
                            $jacocoInit[50] = true;
                            z = true;
                            break;
                        }
                        $jacocoInit[51] = true;
                        if (this.charsBuffer[i5] != str.charAt(i5)) {
                            $jacocoInit[52] = true;
                            z = false;
                            break;
                        }
                        i5++;
                        $jacocoInit[53] = true;
                    }
                    if (!z) {
                        $jacocoInit[54] = true;
                    } else {
                        $jacocoInit[55] = true;
                        return str;
                    }
                }
            }
            String str2 = new String(this.charsBuffer, 0, readUInt);
            if (readUInt >= 64) {
                $jacocoInit[56] = true;
            } else {
                $jacocoInit[57] = true;
                Wson.access$800()[length] = str2;
                $jacocoInit[58] = true;
            }
            $jacocoInit[59] = true;
            return str2;
        }

        private final String readUTF16String() {
            boolean[] $jacocoInit = $jacocoInit();
            int readUInt = readUInt() / 2;
            if (this.charsBuffer.length >= readUInt) {
                $jacocoInit[60] = true;
            } else {
                this.charsBuffer = new char[readUInt];
                $jacocoInit[61] = true;
            }
            if (Wson.access$700()) {
                $jacocoInit[62] = true;
                int i = 0;
                while (i < readUInt) {
                    this.charsBuffer[i] = (char) ((this.buffer[this.position] & 255) + (this.buffer[this.position + 1] << 8));
                    this.position += 2;
                    i++;
                    $jacocoInit[63] = true;
                }
                $jacocoInit[64] = true;
            } else {
                $jacocoInit[65] = true;
                int i2 = 0;
                while (i2 < readUInt) {
                    this.charsBuffer[i2] = (char) ((this.buffer[this.position + 1] & 255) + (this.buffer[this.position] << 8));
                    this.position += 2;
                    i2++;
                    $jacocoInit[67] = true;
                }
                $jacocoInit[66] = true;
            }
            String str = new String(this.charsBuffer, 0, readUInt);
            $jacocoInit[68] = true;
            return str;
        }

        private final int readVarInt() {
            boolean[] $jacocoInit = $jacocoInit();
            int readUInt = readUInt();
            int i = (readUInt & Integer.MIN_VALUE) ^ ((((readUInt << 31) >> 31) ^ readUInt) >> 1);
            $jacocoInit[69] = true;
            return i;
        }

        private final int readUInt() {
            boolean[] $jacocoInit = $jacocoInit();
            $jacocoInit[70] = true;
            int i = 0;
            int i2 = 0;
            while (true) {
                byte b = this.buffer[this.position];
                if ((b & 128) != 0) {
                    i |= (b & Byte.MAX_VALUE) << i2;
                    i2 += 7;
                    this.position++;
                    if (i2 <= 35) {
                        $jacocoInit[71] = true;
                    } else {
                        $jacocoInit[72] = true;
                        IllegalArgumentException illegalArgumentException = new IllegalArgumentException("Variable length quantity is too long");
                        $jacocoInit[73] = true;
                        throw illegalArgumentException;
                    }
                } else {
                    this.position++;
                    int i3 = i | (b << i2);
                    $jacocoInit[74] = true;
                    return i3;
                }
            }
        }

        private final long readLong() {
            boolean[] $jacocoInit = $jacocoInit();
            long j = (((long) this.buffer[this.position + 7]) & 255) + ((((long) this.buffer[this.position + 6]) & 255) << 8) + ((((long) this.buffer[this.position + 5]) & 255) << 16) + ((((long) this.buffer[this.position + 4]) & 255) << 24) + ((((long) this.buffer[this.position + 3]) & 255) << 32) + ((((long) this.buffer[this.position + 2]) & 255) << 40) + ((255 & ((long) this.buffer[this.position + 1])) << 48) + (((long) this.buffer[this.position]) << 56);
            this.position += 8;
            $jacocoInit[75] = true;
            return j;
        }

        private final Object readDouble() {
            boolean[] $jacocoInit = $jacocoInit();
            double longBitsToDouble = Double.longBitsToDouble(readLong());
            if (longBitsToDouble <= 2.147483647E9d) {
                $jacocoInit[76] = true;
            } else {
                long j = (long) longBitsToDouble;
                double d = (double) j;
                Double.isNaN(d);
                if (longBitsToDouble - d >= Double.MIN_NORMAL) {
                    $jacocoInit[77] = true;
                } else {
                    $jacocoInit[78] = true;
                    Long valueOf = Long.valueOf(j);
                    $jacocoInit[79] = true;
                    return valueOf;
                }
            }
            Double valueOf2 = Double.valueOf(longBitsToDouble);
            $jacocoInit[80] = true;
            return valueOf2;
        }

        private Object readFloat() {
            boolean[] $jacocoInit = $jacocoInit();
            int i = (this.buffer[this.position + 3] & 255) + ((this.buffer[this.position + 2] & 255) << 8) + ((this.buffer[this.position + 1] & 255) << 16) + ((this.buffer[this.position] & 255) << 24);
            this.position += 4;
            $jacocoInit[81] = true;
            Float valueOf = Float.valueOf(Float.intBitsToFloat(i));
            $jacocoInit[82] = true;
            return valueOf;
        }
    }

    private static final class Builder {
        private static transient /* synthetic */ boolean[] $jacocoData;
        private static final ThreadLocal<byte[]> bufLocal = new ThreadLocal<>();
        private static final ThreadLocal<ArrayList> refsLocal = new ThreadLocal<>();
        private byte[] buffer;
        private int position;
        private ArrayList refs;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(5588942071272804489L, "com/taobao/weex/wson/Wson$Builder", TbsListener.ErrorCode.COPY_SRCDIR_ERROR);
            $jacocoData = a2;
            return a2;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ Builder(AnonymousClass1 r3) {
            this();
            boolean[] $jacocoInit = $jacocoInit();
            $jacocoInit[208] = true;
        }

        static /* synthetic */ byte[] access$400(Builder builder, Object obj) {
            boolean[] $jacocoInit = $jacocoInit();
            byte[] wson = builder.toWson(obj);
            $jacocoInit[209] = true;
            return wson;
        }

        static /* synthetic */ void access$500(Builder builder) {
            boolean[] $jacocoInit = $jacocoInit();
            builder.close();
            $jacocoInit[210] = true;
        }

        static {
            boolean[] $jacocoInit = $jacocoInit();
            $jacocoInit[211] = true;
            $jacocoInit[212] = true;
        }

        private Builder() {
            boolean[] $jacocoInit = $jacocoInit();
            $jacocoInit[0] = true;
            this.buffer = bufLocal.get();
            if (this.buffer != null) {
                $jacocoInit[1] = true;
                bufLocal.set((Object) null);
                $jacocoInit[2] = true;
            } else {
                this.buffer = new byte[1024];
                $jacocoInit[3] = true;
            }
            this.refs = refsLocal.get();
            if (this.refs != null) {
                $jacocoInit[4] = true;
                refsLocal.set((Object) null);
                $jacocoInit[5] = true;
            } else {
                this.refs = new ArrayList(16);
                $jacocoInit[6] = true;
            }
            $jacocoInit[7] = true;
        }

        private final byte[] toWson(Object obj) {
            boolean[] $jacocoInit = $jacocoInit();
            writeObject(obj);
            byte[] bArr = new byte[this.position];
            $jacocoInit[8] = true;
            System.arraycopy(this.buffer, 0, bArr, 0, this.position);
            $jacocoInit[9] = true;
            return bArr;
        }

        private final void close() {
            boolean[] $jacocoInit = $jacocoInit();
            if (this.buffer.length > 16384) {
                $jacocoInit[10] = true;
            } else {
                $jacocoInit[11] = true;
                bufLocal.set(this.buffer);
                $jacocoInit[12] = true;
            }
            if (this.refs.isEmpty()) {
                $jacocoInit[13] = true;
                refsLocal.set(this.refs);
                $jacocoInit[14] = true;
            } else {
                this.refs.clear();
                $jacocoInit[15] = true;
            }
            this.refs = null;
            this.buffer = null;
            this.position = 0;
            $jacocoInit[16] = true;
        }

        private final void writeObject(Object obj) {
            boolean[] $jacocoInit = $jacocoInit();
            if (obj instanceof CharSequence) {
                $jacocoInit[17] = true;
                ensureCapacity(2);
                $jacocoInit[18] = true;
                writeByte((byte) 115);
                $jacocoInit[19] = true;
                writeUTF16String((CharSequence) obj);
                $jacocoInit[20] = true;
            } else if (obj instanceof Map) {
                $jacocoInit[21] = true;
                if (this.refs.contains(obj)) {
                    $jacocoInit[22] = true;
                    ensureCapacity(2);
                    $jacocoInit[23] = true;
                    writeByte((byte) 48);
                    $jacocoInit[24] = true;
                    return;
                }
                this.refs.add(obj);
                $jacocoInit[25] = true;
                writeMap((Map) obj);
                $jacocoInit[26] = true;
                this.refs.remove(this.refs.size() - 1);
                $jacocoInit[27] = true;
            } else if (obj instanceof List) {
                $jacocoInit[28] = true;
                if (this.refs.contains(obj)) {
                    $jacocoInit[29] = true;
                    ensureCapacity(2);
                    $jacocoInit[30] = true;
                    writeByte((byte) 48);
                    $jacocoInit[31] = true;
                    return;
                }
                this.refs.add(obj);
                $jacocoInit[32] = true;
                ensureCapacity(8);
                List list = (List) obj;
                $jacocoInit[33] = true;
                writeByte((byte) 91);
                $jacocoInit[34] = true;
                writeUInt(list.size());
                $jacocoInit[35] = true;
                $jacocoInit[36] = true;
                for (Object next : list) {
                    $jacocoInit[37] = true;
                    writeObject(next);
                    $jacocoInit[38] = true;
                }
                this.refs.remove(this.refs.size() - 1);
                $jacocoInit[39] = true;
            } else if (obj instanceof Number) {
                $jacocoInit[40] = true;
                writeNumber((Number) obj);
                $jacocoInit[41] = true;
            } else if (obj instanceof Boolean) {
                $jacocoInit[42] = true;
                ensureCapacity(2);
                $jacocoInit[43] = true;
                if (((Boolean) obj).booleanValue()) {
                    $jacocoInit[44] = true;
                    writeByte((byte) 116);
                    $jacocoInit[45] = true;
                } else {
                    writeByte(Wson.BOOLEAN_TYPE_FALSE);
                    $jacocoInit[46] = true;
                }
                $jacocoInit[47] = true;
            } else if (obj == null) {
                $jacocoInit[48] = true;
                ensureCapacity(2);
                $jacocoInit[49] = true;
                writeByte((byte) 48);
                $jacocoInit[50] = true;
            } else if (obj.getClass().isArray()) {
                $jacocoInit[51] = true;
                if (this.refs.contains(obj)) {
                    $jacocoInit[52] = true;
                    ensureCapacity(2);
                    $jacocoInit[53] = true;
                    writeByte((byte) 48);
                    $jacocoInit[54] = true;
                    return;
                }
                this.refs.add(obj);
                $jacocoInit[55] = true;
                ensureCapacity(8);
                $jacocoInit[56] = true;
                int length = Array.getLength(obj);
                $jacocoInit[57] = true;
                writeByte((byte) 91);
                $jacocoInit[58] = true;
                writeUInt(length);
                int i = 0;
                $jacocoInit[59] = true;
                while (i < length) {
                    $jacocoInit[60] = true;
                    Object obj2 = Array.get(obj, i);
                    $jacocoInit[61] = true;
                    writeObject(obj2);
                    i++;
                    $jacocoInit[62] = true;
                }
                this.refs.remove(this.refs.size() - 1);
                $jacocoInit[63] = true;
            } else {
                if (obj instanceof Date) {
                    $jacocoInit[64] = true;
                    ensureCapacity(10);
                    $jacocoInit[65] = true;
                    $jacocoInit[66] = true;
                    writeByte((byte) 100);
                    $jacocoInit[67] = true;
                    writeDouble((double) ((Date) obj).getTime());
                    $jacocoInit[68] = true;
                } else if (obj instanceof Calendar) {
                    $jacocoInit[69] = true;
                    ensureCapacity(10);
                    $jacocoInit[70] = true;
                    $jacocoInit[71] = true;
                    writeByte((byte) 100);
                    $jacocoInit[72] = true;
                    writeDouble((double) ((Calendar) obj).getTime().getTime());
                    $jacocoInit[73] = true;
                } else if (obj instanceof Collection) {
                    $jacocoInit[74] = true;
                    if (this.refs.contains(obj)) {
                        $jacocoInit[75] = true;
                        ensureCapacity(2);
                        $jacocoInit[76] = true;
                        writeByte((byte) 48);
                        $jacocoInit[77] = true;
                        return;
                    }
                    this.refs.add(obj);
                    $jacocoInit[78] = true;
                    ensureCapacity(8);
                    Collection collection = (Collection) obj;
                    $jacocoInit[79] = true;
                    writeByte((byte) 91);
                    $jacocoInit[80] = true;
                    writeUInt(collection.size());
                    $jacocoInit[81] = true;
                    $jacocoInit[82] = true;
                    for (Object next2 : collection) {
                        $jacocoInit[83] = true;
                        writeObject(next2);
                        $jacocoInit[84] = true;
                    }
                    this.refs.remove(this.refs.size() - 1);
                    $jacocoInit[85] = true;
                } else {
                    if (this.refs.contains(obj)) {
                        $jacocoInit[86] = true;
                        ensureCapacity(2);
                        $jacocoInit[87] = true;
                        writeByte((byte) 48);
                        $jacocoInit[88] = true;
                    } else {
                        this.refs.add(obj);
                        $jacocoInit[89] = true;
                        if (obj.getClass().isEnum()) {
                            $jacocoInit[90] = true;
                            writeObject(JSON.toJSONString(obj));
                            $jacocoInit[91] = true;
                        } else {
                            writeAdapterObject(obj);
                            $jacocoInit[92] = true;
                        }
                        this.refs.remove(this.refs.size() - 1);
                        $jacocoInit[93] = true;
                    }
                    $jacocoInit[94] = true;
                    return;
                }
                $jacocoInit[95] = true;
            }
        }

        private final void writeNumber(Number number) {
            boolean[] $jacocoInit = $jacocoInit();
            ensureCapacity(12);
            if (number instanceof Integer) {
                $jacocoInit[96] = true;
                writeByte((byte) 105);
                $jacocoInit[97] = true;
                writeVarInt(number.intValue());
                $jacocoInit[98] = true;
            } else if (number instanceof Float) {
                $jacocoInit[99] = true;
                writeByte((byte) 70);
                $jacocoInit[100] = true;
                writeFloat(number.floatValue());
                $jacocoInit[101] = true;
            } else if (number instanceof Double) {
                $jacocoInit[102] = true;
                writeByte((byte) 100);
                $jacocoInit[103] = true;
                writeDouble(number.doubleValue());
                $jacocoInit[104] = true;
            } else if (number instanceof Long) {
                $jacocoInit[105] = true;
                writeByte((byte) 108);
                $jacocoInit[106] = true;
                writeLong(number.longValue());
                $jacocoInit[107] = true;
            } else {
                if (number instanceof Short) {
                    $jacocoInit[108] = true;
                } else if (number instanceof Byte) {
                    $jacocoInit[109] = true;
                } else if (number instanceof BigInteger) {
                    $jacocoInit[112] = true;
                    writeByte((byte) 103);
                    $jacocoInit[113] = true;
                    writeUTF16String(number.toString());
                    $jacocoInit[114] = true;
                    return;
                } else if (number instanceof BigDecimal) {
                    $jacocoInit[115] = true;
                    String obj = number.toString();
                    $jacocoInit[116] = true;
                    double doubleValue = number.doubleValue();
                    $jacocoInit[117] = true;
                    if (obj.equals(Double.toString(doubleValue))) {
                        $jacocoInit[118] = true;
                        writeByte((byte) 100);
                        $jacocoInit[119] = true;
                        writeDouble(doubleValue);
                        $jacocoInit[120] = true;
                    } else {
                        writeByte((byte) 101);
                        $jacocoInit[121] = true;
                        writeUTF16String(obj);
                        $jacocoInit[122] = true;
                    }
                    $jacocoInit[123] = true;
                    return;
                } else {
                    writeByte((byte) 115);
                    $jacocoInit[124] = true;
                    writeUTF16String(number.toString());
                    $jacocoInit[125] = true;
                    return;
                }
                writeByte((byte) 105);
                $jacocoInit[110] = true;
                writeVarInt(number.intValue());
                $jacocoInit[111] = true;
            }
        }

        private final void writeMap(Map map) {
            boolean[] $jacocoInit = $jacocoInit();
            Set<Map.Entry> entrySet = map.entrySet();
            $jacocoInit[126] = true;
            $jacocoInit[127] = true;
            int i = 0;
            for (Map.Entry value : entrySet) {
                $jacocoInit[128] = true;
                if (value.getValue() != null) {
                    $jacocoInit[129] = true;
                } else {
                    i++;
                    $jacocoInit[130] = true;
                }
                $jacocoInit[131] = true;
            }
            ensureCapacity(8);
            $jacocoInit[132] = true;
            writeByte((byte) 123);
            $jacocoInit[133] = true;
            writeUInt(map.size() - i);
            $jacocoInit[134] = true;
            $jacocoInit[135] = true;
            for (Map.Entry entry : entrySet) {
                $jacocoInit[136] = true;
                if (entry.getValue() == null) {
                    $jacocoInit[137] = true;
                } else {
                    writeMapKeyUTF16(entry.getKey().toString());
                    $jacocoInit[138] = true;
                    writeObject(entry.getValue());
                    $jacocoInit[139] = true;
                }
            }
            $jacocoInit[140] = true;
        }

        private final void writeByte(byte b) {
            boolean[] $jacocoInit = $jacocoInit();
            this.buffer[this.position] = b;
            this.position++;
            $jacocoInit[141] = true;
        }

        private final void writeAdapterObject(Object obj) {
            boolean[] $jacocoInit = $jacocoInit();
            if (Wson.access$900().get(obj.getClass().getName()) == null) {
                $jacocoInit[142] = true;
                try {
                    writeMap(toMap(obj));
                    $jacocoInit[145] = true;
                } catch (Exception unused) {
                    $jacocoInit[146] = true;
                    Wson.access$900().put(obj.getClass().getName(), true);
                    $jacocoInit[147] = true;
                    writeObject(JSON.toJSON(obj));
                    $jacocoInit[148] = true;
                }
                $jacocoInit[149] = true;
                return;
            }
            $jacocoInit[143] = true;
            writeObject(JSON.toJSON(obj));
            $jacocoInit[144] = true;
        }

        private final Map toMap(Object obj) {
            boolean[] $jacocoInit = $jacocoInit();
            JSONObject jSONObject = new JSONObject();
            try {
                $jacocoInit[150] = true;
                Class<?> cls = obj.getClass();
                $jacocoInit[151] = true;
                String name = cls.getName();
                $jacocoInit[152] = true;
                List<Method> access$1000 = Wson.access$1000(name, cls);
                $jacocoInit[153] = true;
                $jacocoInit[154] = true;
                for (Method method : access$1000) {
                    $jacocoInit[155] = true;
                    String name2 = method.getName();
                    $jacocoInit[156] = true;
                    if (name2.startsWith("get")) {
                        $jacocoInit[157] = true;
                        Object invoke = method.invoke(obj, new Object[0]);
                        if (invoke == null) {
                            $jacocoInit[158] = true;
                        } else {
                            $jacocoInit[159] = true;
                            StringBuilder sb = new StringBuilder(method.getName().substring(3));
                            $jacocoInit[160] = true;
                            sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));
                            $jacocoInit[161] = true;
                            jSONObject.put(sb.toString(), invoke);
                            $jacocoInit[162] = true;
                        }
                        $jacocoInit[163] = true;
                    } else if (!name2.startsWith(Wson.METHOD_PREFIX_IS)) {
                        $jacocoInit[164] = true;
                    } else {
                        $jacocoInit[165] = true;
                        Object invoke2 = method.invoke(obj, new Object[0]);
                        if (invoke2 == null) {
                            $jacocoInit[166] = true;
                        } else {
                            $jacocoInit[167] = true;
                            StringBuilder sb2 = new StringBuilder(method.getName().substring(2));
                            $jacocoInit[168] = true;
                            sb2.setCharAt(0, Character.toLowerCase(sb2.charAt(0)));
                            $jacocoInit[169] = true;
                            jSONObject.put(sb2.toString(), invoke2);
                            $jacocoInit[170] = true;
                        }
                    }
                    $jacocoInit[171] = true;
                }
                List<Field> access$1100 = Wson.access$1100(name, cls);
                $jacocoInit[172] = true;
                $jacocoInit[173] = true;
                for (Field field : access$1100) {
                    $jacocoInit[174] = true;
                    String name3 = field.getName();
                    $jacocoInit[175] = true;
                    if (jSONObject.containsKey(name3)) {
                        $jacocoInit[176] = true;
                    } else {
                        Object obj2 = field.get(obj);
                        if (obj2 == null) {
                            $jacocoInit[177] = true;
                        } else {
                            jSONObject.put(name3, obj2);
                            $jacocoInit[178] = true;
                        }
                    }
                }
                $jacocoInit[181] = true;
                return jSONObject;
            } catch (Exception e) {
                if (e instanceof RuntimeException) {
                    RuntimeException runtimeException = (RuntimeException) e;
                    $jacocoInit[179] = true;
                    throw runtimeException;
                }
                RuntimeException runtimeException2 = new RuntimeException(e);
                $jacocoInit[180] = true;
                throw runtimeException2;
            }
        }

        private final void writeMapKeyUTF16(String str) {
            boolean[] $jacocoInit = $jacocoInit();
            writeUTF16String(str);
            $jacocoInit[182] = true;
        }

        private final void writeUTF16String(CharSequence charSequence) {
            boolean[] $jacocoInit = $jacocoInit();
            int length = charSequence.length();
            $jacocoInit[183] = true;
            int i = length * 2;
            ensureCapacity(i + 8);
            $jacocoInit[184] = true;
            writeUInt(i);
            $jacocoInit[185] = true;
            int i2 = 0;
            if (Wson.access$700()) {
                $jacocoInit[186] = true;
                while (i2 < length) {
                    $jacocoInit[187] = true;
                    char charAt = charSequence.charAt(i2);
                    this.buffer[this.position] = (byte) charAt;
                    this.buffer[this.position + 1] = (byte) (charAt >>> 8);
                    this.position += 2;
                    i2++;
                    $jacocoInit[188] = true;
                }
                $jacocoInit[189] = true;
            } else {
                $jacocoInit[190] = true;
                while (i2 < length) {
                    $jacocoInit[192] = true;
                    char charAt2 = charSequence.charAt(i2);
                    this.buffer[this.position + 1] = (byte) charAt2;
                    this.buffer[this.position] = (byte) (charAt2 >>> 8);
                    this.position += 2;
                    i2++;
                    $jacocoInit[193] = true;
                }
                $jacocoInit[191] = true;
            }
            $jacocoInit[194] = true;
        }

        private final void writeDouble(double d) {
            boolean[] $jacocoInit = $jacocoInit();
            writeLong(Double.doubleToLongBits(d));
            $jacocoInit[195] = true;
        }

        private final void writeFloat(float f) {
            boolean[] $jacocoInit = $jacocoInit();
            int floatToIntBits = Float.floatToIntBits(f);
            this.buffer[this.position + 3] = (byte) floatToIntBits;
            this.buffer[this.position + 2] = (byte) (floatToIntBits >>> 8);
            this.buffer[this.position + 1] = (byte) (floatToIntBits >>> 16);
            this.buffer[this.position] = (byte) (floatToIntBits >>> 24);
            this.position += 4;
            $jacocoInit[196] = true;
        }

        private final void writeLong(long j) {
            boolean[] $jacocoInit = $jacocoInit();
            this.buffer[this.position + 7] = (byte) ((int) j);
            this.buffer[this.position + 6] = (byte) ((int) (j >>> 8));
            this.buffer[this.position + 5] = (byte) ((int) (j >>> 16));
            this.buffer[this.position + 4] = (byte) ((int) (j >>> 24));
            this.buffer[this.position + 3] = (byte) ((int) (j >>> 32));
            this.buffer[this.position + 2] = (byte) ((int) (j >>> 40));
            this.buffer[this.position + 1] = (byte) ((int) (j >>> 48));
            this.buffer[this.position] = (byte) ((int) (j >>> 56));
            this.position += 8;
            $jacocoInit[197] = true;
        }

        private final void writeVarInt(int i) {
            boolean[] $jacocoInit = $jacocoInit();
            writeUInt((i >> 31) ^ (i << 1));
            $jacocoInit[198] = true;
        }

        private final void writeUInt(int i) {
            boolean[] $jacocoInit = $jacocoInit();
            while ((i & -128) != 0) {
                this.buffer[this.position] = (byte) ((i & 127) | 128);
                this.position++;
                i >>>= 7;
                $jacocoInit[199] = true;
            }
            this.buffer[this.position] = (byte) (i & 127);
            this.position++;
            $jacocoInit[200] = true;
        }

        private final void ensureCapacity(int i) {
            boolean[] $jacocoInit = $jacocoInit();
            int i2 = i + this.position;
            if (i2 - this.buffer.length <= 0) {
                $jacocoInit[201] = true;
            } else {
                int length = this.buffer.length << 1;
                if (length >= 16384) {
                    $jacocoInit[202] = true;
                } else {
                    $jacocoInit[203] = true;
                    length = 16384;
                }
                if (length - i2 >= 0) {
                    $jacocoInit[204] = true;
                    i2 = length;
                } else {
                    $jacocoInit[205] = true;
                }
                this.buffer = Arrays.copyOf(this.buffer, i2);
                $jacocoInit[206] = true;
            }
            $jacocoInit[207] = true;
        }
    }

    private static final List<Method> getBeanMethod(String str, Class cls) {
        boolean[] $jacocoInit = $jacocoInit();
        List<Method> list = methodsCache.get(str);
        if (list != null) {
            $jacocoInit[12] = true;
        } else {
            $jacocoInit[13] = true;
            list = new ArrayList<>();
            $jacocoInit[14] = true;
            Method[] methods = cls.getMethods();
            int length = methods.length;
            int i = 0;
            $jacocoInit[15] = true;
            while (i < length) {
                Method method = methods[i];
                $jacocoInit[16] = true;
                if (method.getDeclaringClass() == Object.class) {
                    $jacocoInit[17] = true;
                } else if ((method.getModifiers() & 8) != 0) {
                    $jacocoInit[18] = true;
                } else {
                    String name = method.getName();
                    $jacocoInit[19] = true;
                    if (name.startsWith("get")) {
                        $jacocoInit[20] = true;
                    } else {
                        $jacocoInit[21] = true;
                        if (!name.startsWith(METHOD_PREFIX_IS)) {
                            $jacocoInit[22] = true;
                        } else {
                            $jacocoInit[23] = true;
                        }
                    }
                    if (method.getAnnotation(JSONField.class) == null) {
                        list.add(method);
                        $jacocoInit[26] = true;
                    } else {
                        $jacocoInit[24] = true;
                        UnsupportedOperationException unsupportedOperationException = new UnsupportedOperationException("getBeanMethod JSONField Annotation Not Handled, Use toJSON");
                        $jacocoInit[25] = true;
                        throw unsupportedOperationException;
                    }
                }
                i++;
                $jacocoInit[27] = true;
            }
            methodsCache.put(str, list);
            $jacocoInit[28] = true;
        }
        $jacocoInit[29] = true;
        return list;
    }

    private static final List<Field> getBeanFields(String str, Class cls) {
        boolean[] $jacocoInit = $jacocoInit();
        List<Field> list = fieldsCache.get(str);
        if (list != null) {
            $jacocoInit[30] = true;
        } else {
            $jacocoInit[31] = true;
            Field[] fields = cls.getFields();
            $jacocoInit[32] = true;
            list = new ArrayList<>(fields.length);
            int length = fields.length;
            int i = 0;
            $jacocoInit[33] = true;
            while (i < length) {
                Field field = fields[i];
                $jacocoInit[34] = true;
                if ((field.getModifiers() & 8) != 0) {
                    $jacocoInit[35] = true;
                } else if (field.getAnnotation(JSONField.class) == null) {
                    list.add(field);
                    $jacocoInit[38] = true;
                } else {
                    $jacocoInit[36] = true;
                    UnsupportedOperationException unsupportedOperationException = new UnsupportedOperationException("getBeanMethod JSONField Annotation Not Handled, Use toJSON");
                    $jacocoInit[37] = true;
                    throw unsupportedOperationException;
                }
                i++;
                $jacocoInit[39] = true;
            }
            fieldsCache.put(str, list);
            $jacocoInit[40] = true;
        }
        $jacocoInit[41] = true;
        return list;
    }
}
