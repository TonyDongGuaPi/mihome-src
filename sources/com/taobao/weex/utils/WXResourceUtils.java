package com.taobao.weex.utils;

import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Pair;
import com.libra.Color;
import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.utils.SingleFunctionParser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXResourceUtils {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    private static final int COLOR_RANGE = 255;
    private static final SingleFunctionParser.NonUniformMapper<Number> FUNCTIONAL_RGBA_MAPPER = new SingleFunctionParser.NonUniformMapper<Number>() {
        private static transient /* synthetic */ boolean[] $jacocoData;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(-2652361270181204986L, "com/taobao/weex/utils/WXResourceUtils$2", 8);
            $jacocoData = a2;
            return a2;
        }

        {
            $jacocoInit()[0] = true;
        }

        public List<Number> map(List<String> list) {
            boolean[] $jacocoInit = $jacocoInit();
            ArrayList arrayList = new ArrayList(4);
            $jacocoInit[1] = true;
            int i = 0;
            while (i < 3) {
                $jacocoInit[2] = true;
                int parseUnitOrPercent = WXUtils.parseUnitOrPercent(list.get(i), 255);
                if (parseUnitOrPercent < 0) {
                    $jacocoInit[3] = true;
                    parseUnitOrPercent = 0;
                } else if (parseUnitOrPercent <= 255) {
                    $jacocoInit[4] = true;
                } else {
                    $jacocoInit[5] = true;
                    parseUnitOrPercent = 255;
                }
                arrayList.add(Integer.valueOf(parseUnitOrPercent));
                i++;
                $jacocoInit[6] = true;
            }
            arrayList.add(Float.valueOf(list.get(i)));
            $jacocoInit[7] = true;
            return arrayList;
        }
    };
    private static final SingleFunctionParser.FlatMapper<Integer> FUNCTIONAL_RGB_MAPPER = new SingleFunctionParser.FlatMapper<Integer>() {
        private static transient /* synthetic */ boolean[] $jacocoData;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(5483528575177484301L, "com/taobao/weex/utils/WXResourceUtils$1", 6);
            $jacocoData = a2;
            return a2;
        }

        {
            $jacocoInit()[0] = true;
        }

        public Integer map(String str) {
            boolean[] $jacocoInit = $jacocoInit();
            int i = 255;
            int parseUnitOrPercent = WXUtils.parseUnitOrPercent(str, 255);
            if (parseUnitOrPercent < 0) {
                i = 0;
                $jacocoInit[1] = true;
            } else if (parseUnitOrPercent <= 255) {
                $jacocoInit[2] = true;
                i = parseUnitOrPercent;
            } else {
                $jacocoInit[3] = true;
            }
            Integer valueOf = Integer.valueOf(i);
            $jacocoInit[4] = true;
            return valueOf;
        }
    };
    private static final int HEX = 16;
    private static final String RGB = "rgb";
    private static final String RGBA = "rgba";
    private static final int RGBA_SIZE = 4;
    private static final int RGB_SIZE = 3;
    private static final Map<String, Integer> colorMap = new HashMap();

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-849663688656712625L, "com/taobao/weex/utils/WXResourceUtils", 224);
        $jacocoData = a2;
        return a2;
    }

    public WXResourceUtils() {
        $jacocoInit()[0] = true;
    }

    static /* synthetic */ Map access$100() {
        boolean[] $jacocoInit = $jacocoInit();
        Map<String, Integer> map = colorMap;
        $jacocoInit[70] = true;
        return map;
    }

    static /* synthetic */ SingleFunctionParser.FlatMapper access$200() {
        boolean[] $jacocoInit = $jacocoInit();
        SingleFunctionParser.FlatMapper<Integer> flatMapper = FUNCTIONAL_RGB_MAPPER;
        $jacocoInit[71] = true;
        return flatMapper;
    }

    static /* synthetic */ SingleFunctionParser.NonUniformMapper access$300() {
        boolean[] $jacocoInit = $jacocoInit();
        SingleFunctionParser.NonUniformMapper<Number> nonUniformMapper = FUNCTIONAL_RGBA_MAPPER;
        $jacocoInit[72] = true;
        return nonUniformMapper;
    }

    static {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[73] = true;
        $jacocoInit[74] = true;
        $jacocoInit[75] = true;
        colorMap.put("aliceblue", -984833);
        $jacocoInit[76] = true;
        colorMap.put("antiquewhite", -332841);
        $jacocoInit[77] = true;
        colorMap.put("aqua", Integer.valueOf(Color.j));
        $jacocoInit[78] = true;
        colorMap.put("aquamarine", -8388652);
        $jacocoInit[79] = true;
        colorMap.put("azure", -983041);
        $jacocoInit[80] = true;
        colorMap.put("beige", -657956);
        $jacocoInit[81] = true;
        colorMap.put("bisque", -6972);
        $jacocoInit[82] = true;
        colorMap.put("black", -16777216);
        $jacocoInit[83] = true;
        colorMap.put("blanchedalmond", -5171);
        $jacocoInit[84] = true;
        colorMap.put("blue", Integer.valueOf(Color.h));
        $jacocoInit[85] = true;
        colorMap.put("blueviolet", -7722014);
        $jacocoInit[86] = true;
        colorMap.put("brown", -5952982);
        $jacocoInit[87] = true;
        colorMap.put("burlywood", -2180985);
        $jacocoInit[88] = true;
        colorMap.put("cadetblue", -10510688);
        $jacocoInit[89] = true;
        colorMap.put("chartreuse", -8388864);
        $jacocoInit[90] = true;
        colorMap.put("chocolate", -2987746);
        $jacocoInit[91] = true;
        colorMap.put("coral", -32944);
        $jacocoInit[92] = true;
        colorMap.put("cornflowerblue", -10185235);
        $jacocoInit[93] = true;
        colorMap.put("cornsilk", -1828);
        $jacocoInit[94] = true;
        colorMap.put("crimson", -2354116);
        $jacocoInit[95] = true;
        colorMap.put("cyan", Integer.valueOf(Color.j));
        $jacocoInit[96] = true;
        colorMap.put("darkblue", -16777077);
        $jacocoInit[97] = true;
        colorMap.put("darkcyan", -16741493);
        $jacocoInit[98] = true;
        colorMap.put("darkgoldenrod", -4684277);
        $jacocoInit[99] = true;
        colorMap.put("darkgray", -5658199);
        $jacocoInit[100] = true;
        colorMap.put("darkgreen", -16751616);
        $jacocoInit[101] = true;
        colorMap.put("darkkhaki", -4343957);
        $jacocoInit[102] = true;
        colorMap.put("darkmagenta", -7667573);
        $jacocoInit[103] = true;
        colorMap.put("darkolivegreen", -11179217);
        $jacocoInit[104] = true;
        colorMap.put("darkorange", -29696);
        $jacocoInit[105] = true;
        colorMap.put("darkorchid", -6737204);
        $jacocoInit[106] = true;
        colorMap.put("darkred", -7667712);
        $jacocoInit[107] = true;
        colorMap.put("darksalmon", -1468806);
        $jacocoInit[108] = true;
        colorMap.put("darkseagreen", -7357297);
        $jacocoInit[109] = true;
        colorMap.put("darkslateblue", -12042869);
        $jacocoInit[110] = true;
        colorMap.put("darkslategray", -13676721);
        $jacocoInit[111] = true;
        colorMap.put("darkslategrey", -13676721);
        $jacocoInit[112] = true;
        colorMap.put("darkturquoise", -16724271);
        $jacocoInit[113] = true;
        colorMap.put("darkviolet", -7077677);
        $jacocoInit[114] = true;
        colorMap.put("deeppink", -60269);
        $jacocoInit[115] = true;
        colorMap.put("deepskyblue", -16728065);
        $jacocoInit[116] = true;
        colorMap.put("dimgray", -9868951);
        $jacocoInit[117] = true;
        colorMap.put("dimgrey", -9868951);
        $jacocoInit[118] = true;
        colorMap.put("dodgerblue", -14774017);
        $jacocoInit[119] = true;
        colorMap.put("firebrick", -5103070);
        $jacocoInit[120] = true;
        colorMap.put("floralwhite", -1296);
        $jacocoInit[121] = true;
        colorMap.put("forestgreen", -14513374);
        $jacocoInit[122] = true;
        colorMap.put("fuchsia", Integer.valueOf(Color.k));
        $jacocoInit[123] = true;
        colorMap.put("gainsboro", -2302756);
        $jacocoInit[124] = true;
        colorMap.put("ghostwhite", -460545);
        $jacocoInit[125] = true;
        colorMap.put("gold", -10496);
        $jacocoInit[126] = true;
        colorMap.put("goldenrod", -2448096);
        $jacocoInit[127] = true;
        colorMap.put("gray", -8355712);
        $jacocoInit[128] = true;
        colorMap.put("grey", -8355712);
        $jacocoInit[129] = true;
        colorMap.put("green", -16744448);
        $jacocoInit[130] = true;
        colorMap.put("greenyellow", -5374161);
        $jacocoInit[131] = true;
        colorMap.put("honeydew", -983056);
        $jacocoInit[132] = true;
        colorMap.put("hotpink", -38476);
        $jacocoInit[133] = true;
        colorMap.put("indianred", -3318692);
        $jacocoInit[134] = true;
        colorMap.put("indigo", -11861886);
        $jacocoInit[135] = true;
        colorMap.put("ivory", -16);
        $jacocoInit[136] = true;
        colorMap.put("khaki", -989556);
        $jacocoInit[137] = true;
        colorMap.put("lavender", -1644806);
        $jacocoInit[138] = true;
        colorMap.put("lavenderblush", -3851);
        $jacocoInit[139] = true;
        colorMap.put("lawngreen", -8586240);
        $jacocoInit[140] = true;
        colorMap.put("lemonchiffon", -1331);
        $jacocoInit[141] = true;
        colorMap.put("lightblue", -5383962);
        $jacocoInit[142] = true;
        colorMap.put("lightcoral", -1015680);
        $jacocoInit[143] = true;
        colorMap.put("lightcyan", -2031617);
        $jacocoInit[144] = true;
        colorMap.put("lightgoldenrodyellow", -329006);
        $jacocoInit[145] = true;
        colorMap.put("lightgray", -2894893);
        $jacocoInit[146] = true;
        colorMap.put("lightgrey", -2894893);
        $jacocoInit[147] = true;
        colorMap.put("lightgreen", -7278960);
        $jacocoInit[148] = true;
        colorMap.put("lightpink", -18751);
        $jacocoInit[149] = true;
        colorMap.put("lightsalmon", -24454);
        $jacocoInit[150] = true;
        colorMap.put("lightseagreen", -14634326);
        $jacocoInit[151] = true;
        colorMap.put("lightskyblue", -7876870);
        $jacocoInit[152] = true;
        colorMap.put("lightslategray", -8943463);
        $jacocoInit[153] = true;
        colorMap.put("lightslategrey", -8943463);
        $jacocoInit[154] = true;
        colorMap.put("lightsteelblue", -5192482);
        $jacocoInit[155] = true;
        colorMap.put("lightyellow", -32);
        $jacocoInit[156] = true;
        colorMap.put("lime", Integer.valueOf(Color.g));
        $jacocoInit[157] = true;
        colorMap.put("limegreen", -13447886);
        $jacocoInit[158] = true;
        colorMap.put("linen", -331546);
        $jacocoInit[159] = true;
        colorMap.put("magenta", Integer.valueOf(Color.k));
        $jacocoInit[160] = true;
        colorMap.put("maroon", -8388608);
        $jacocoInit[161] = true;
        colorMap.put("mediumaquamarine", -10039894);
        $jacocoInit[162] = true;
        colorMap.put("mediumblue", -16777011);
        $jacocoInit[163] = true;
        colorMap.put("mediumorchid", -4565549);
        $jacocoInit[164] = true;
        colorMap.put("mediumpurple", -7114533);
        $jacocoInit[165] = true;
        colorMap.put("mediumseagreen", -12799119);
        $jacocoInit[166] = true;
        colorMap.put("mediumslateblue", -8689426);
        $jacocoInit[167] = true;
        colorMap.put("mediumspringgreen", -16713062);
        $jacocoInit[168] = true;
        colorMap.put("mediumturquoise", -12004916);
        $jacocoInit[169] = true;
        colorMap.put("mediumvioletred", -3730043);
        $jacocoInit[170] = true;
        colorMap.put("midnightblue", -15132304);
        $jacocoInit[171] = true;
        colorMap.put("mintcream", -655366);
        $jacocoInit[172] = true;
        colorMap.put("mistyrose", -6943);
        $jacocoInit[173] = true;
        colorMap.put("moccasin", -6987);
        $jacocoInit[174] = true;
        colorMap.put("navajowhite", -8531);
        $jacocoInit[175] = true;
        colorMap.put("navy", -16777088);
        $jacocoInit[176] = true;
        colorMap.put("oldlace", -133658);
        $jacocoInit[177] = true;
        colorMap.put("olive", -8355840);
        $jacocoInit[178] = true;
        colorMap.put("olivedrab", -9728477);
        $jacocoInit[179] = true;
        colorMap.put("orange", -23296);
        $jacocoInit[180] = true;
        colorMap.put("orangered", -47872);
        $jacocoInit[181] = true;
        colorMap.put("orchid", -2461482);
        $jacocoInit[182] = true;
        colorMap.put("palegoldenrod", -1120086);
        $jacocoInit[183] = true;
        colorMap.put("palegreen", -6751336);
        $jacocoInit[184] = true;
        colorMap.put("paleturquoise", -5247250);
        $jacocoInit[185] = true;
        colorMap.put("palevioletred", -2396013);
        $jacocoInit[186] = true;
        colorMap.put("papayawhip", -4139);
        $jacocoInit[187] = true;
        colorMap.put("peachpuff", -9543);
        $jacocoInit[188] = true;
        colorMap.put("peru", -3308225);
        $jacocoInit[189] = true;
        colorMap.put("pink", -16181);
        $jacocoInit[190] = true;
        colorMap.put("plum", -2252579);
        $jacocoInit[191] = true;
        colorMap.put("powderblue", -5185306);
        $jacocoInit[192] = true;
        colorMap.put("purple", -8388480);
        $jacocoInit[193] = true;
        colorMap.put("rebeccapurple", -10079335);
        $jacocoInit[194] = true;
        colorMap.put("red", -65536);
        $jacocoInit[195] = true;
        colorMap.put("rosybrown", -4419697);
        $jacocoInit[196] = true;
        colorMap.put("royalblue", -12490271);
        $jacocoInit[197] = true;
        colorMap.put("saddlebrown", -7650029);
        $jacocoInit[198] = true;
        colorMap.put("salmon", -360334);
        $jacocoInit[199] = true;
        colorMap.put("sandybrown", -744352);
        $jacocoInit[200] = true;
        colorMap.put("seagreen", -13726889);
        $jacocoInit[201] = true;
        colorMap.put("seashell", -2578);
        $jacocoInit[202] = true;
        colorMap.put("sienna", -6270419);
        $jacocoInit[203] = true;
        colorMap.put("silver", -4144960);
        $jacocoInit[204] = true;
        colorMap.put("skyblue", -7876885);
        $jacocoInit[205] = true;
        colorMap.put("slateblue", -9807155);
        $jacocoInit[206] = true;
        colorMap.put("slategray", -9404272);
        $jacocoInit[207] = true;
        colorMap.put("slategrey", -9404272);
        $jacocoInit[208] = true;
        colorMap.put("snow", -1286);
        $jacocoInit[209] = true;
        colorMap.put("springgreen", -16711809);
        $jacocoInit[210] = true;
        colorMap.put("steelblue", -12156236);
        $jacocoInit[211] = true;
        colorMap.put("tan", -2968436);
        $jacocoInit[212] = true;
        colorMap.put("teal", -16744320);
        $jacocoInit[213] = true;
        colorMap.put("thistle", -2572328);
        $jacocoInit[214] = true;
        colorMap.put("tomato", -40121);
        $jacocoInit[215] = true;
        colorMap.put("turquoise", -12525360);
        $jacocoInit[216] = true;
        colorMap.put("violet", -1146130);
        $jacocoInit[217] = true;
        colorMap.put("wheat", -663885);
        $jacocoInit[218] = true;
        colorMap.put("white", -1);
        $jacocoInit[219] = true;
        colorMap.put("whitesmoke", -657931);
        $jacocoInit[220] = true;
        colorMap.put("yellow", -256);
        $jacocoInit[221] = true;
        colorMap.put("yellowgreen", -6632142);
        $jacocoInit[222] = true;
        colorMap.put("transparent", 0);
        $jacocoInit[223] = true;
    }

    public static int getColor(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        int color = getColor(str, Integer.MIN_VALUE);
        $jacocoInit[1] = true;
        return color;
    }

    public static int getColor(String str, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[2] = true;
            return i;
        }
        String trim = str.trim();
        $jacocoInit[3] = true;
        Integer num = WXUtils.sCache.get(trim);
        if (num != null) {
            $jacocoInit[4] = true;
            int intValue = num.intValue();
            $jacocoInit[5] = true;
            return intValue;
        }
        $jacocoInit[6] = true;
        ColorConvertHandler[] values = ColorConvertHandler.values();
        int length = values.length;
        int i2 = 0;
        $jacocoInit[7] = true;
        while (true) {
            if (i2 >= length) {
                $jacocoInit[8] = true;
                break;
            }
            ColorConvertHandler colorConvertHandler = values[i2];
            try {
                $jacocoInit[9] = true;
                Pair<Boolean, Integer> handle = colorConvertHandler.handle(trim);
                $jacocoInit[10] = true;
                if (((Boolean) handle.first).booleanValue()) {
                    $jacocoInit[11] = true;
                    int intValue2 = ((Integer) handle.second).intValue();
                    try {
                        $jacocoInit[12] = true;
                        WXUtils.sCache.put(trim, Integer.valueOf(intValue2));
                        $jacocoInit[13] = true;
                        i = intValue2;
                        break;
                    } catch (RuntimeException e) {
                        int i3 = intValue2;
                        e = e;
                        i = i3;
                    }
                } else {
                    $jacocoInit[14] = true;
                    i2++;
                    $jacocoInit[17] = true;
                }
            } catch (RuntimeException e2) {
                e = e2;
                $jacocoInit[15] = true;
                WXLogUtils.v("Color_Parser", WXLogUtils.getStackTrace(e));
                $jacocoInit[16] = true;
                i2++;
                $jacocoInit[17] = true;
            }
        }
        $jacocoInit[18] = true;
        return i;
    }

    public static Shader getShader(String str, float f, float f2) {
        boolean[] $jacocoInit = $jacocoInit();
        List<String> parseGradientValues = parseGradientValues(str);
        $jacocoInit[19] = true;
        if (parseGradientValues == null) {
            $jacocoInit[20] = true;
        } else if (parseGradientValues.size() != 3) {
            $jacocoInit[21] = true;
        } else {
            $jacocoInit[22] = true;
            float[] parseGradientDirection = parseGradientDirection(parseGradientValues.get(0), f, f2);
            float f3 = parseGradientDirection[0];
            float f4 = parseGradientDirection[1];
            float f5 = parseGradientDirection[2];
            float f6 = parseGradientDirection[3];
            $jacocoInit[23] = true;
            LinearGradient linearGradient = new LinearGradient(f3, f4, f5, f6, getColor(parseGradientValues.get(1), -1), getColor(parseGradientValues.get(2), -1), Shader.TileMode.CLAMP);
            $jacocoInit[24] = true;
            return linearGradient;
        }
        $jacocoInit[25] = true;
        return null;
    }

    @NonNull
    private static List<String> parseGradientValues(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[26] = true;
            return null;
        }
        str.trim();
        $jacocoInit[27] = true;
        if (str.startsWith("linear-gradient")) {
            $jacocoInit[28] = true;
            String substring = str.substring(str.indexOf(Operators.BRACKET_START_STR) + 1, str.lastIndexOf(Operators.BRACKET_END_STR));
            $jacocoInit[29] = true;
            StringTokenizer stringTokenizer = new StringTokenizer(substring, ",");
            $jacocoInit[30] = true;
            ArrayList arrayList = new ArrayList();
            $jacocoInit[31] = true;
            while (true) {
                String str2 = null;
                while (stringTokenizer.hasMoreTokens()) {
                    $jacocoInit[32] = true;
                    String nextToken = stringTokenizer.nextToken();
                    $jacocoInit[33] = true;
                    if (nextToken.contains(Operators.BRACKET_START_STR)) {
                        $jacocoInit[34] = true;
                        str2 = nextToken + ",";
                        $jacocoInit[35] = true;
                    } else if (nextToken.contains(Operators.BRACKET_END_STR)) {
                        $jacocoInit[36] = true;
                        $jacocoInit[37] = true;
                        arrayList.add(str2 + nextToken);
                        $jacocoInit[38] = true;
                    } else if (str2 != null) {
                        $jacocoInit[39] = true;
                        str2 = str2 + nextToken + ",";
                        $jacocoInit[40] = true;
                    } else {
                        arrayList.add(nextToken);
                        $jacocoInit[41] = true;
                    }
                }
                $jacocoInit[42] = true;
                return arrayList;
            }
        }
        $jacocoInit[43] = true;
        return null;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static float[] parseGradientDirection(java.lang.String r9, float r10, float r11) {
        /*
            boolean[] r0 = $jacocoInit()
            r1 = 4
            float[] r2 = new float[r1]
            r2 = {0, 0, 0, 0} // fill-array
            r3 = 1
            r4 = 44
            r0[r4] = r3
            boolean r4 = android.text.TextUtils.isEmpty(r9)
            if (r4 == 0) goto L_0x001a
            r4 = 45
            r0[r4] = r3
            goto L_0x002e
        L_0x001a:
            r4 = 46
            r0[r4] = r3
            java.lang.String r4 = "\\s*"
            java.lang.String r5 = ""
            java.lang.String r9 = r9.replaceAll(r4, r5)
            java.lang.String r9 = r9.toLowerCase()
            r4 = 47
            r0[r4] = r3
        L_0x002e:
            r4 = -1
            int r5 = r9.hashCode()
            r6 = 3
            r7 = 2
            r8 = 0
            switch(r5) {
                case -1352032154: goto L_0x009e;
                case -1137407871: goto L_0x008b;
                case -868157182: goto L_0x0078;
                case -172068863: goto L_0x0065;
                case 110550266: goto L_0x0052;
                case 1176531318: goto L_0x003f;
                default: goto L_0x0039;
            }
        L_0x0039:
            r9 = 48
            r0[r9] = r3
            goto L_0x00b1
        L_0x003f:
            java.lang.String r5 = "tobottomright"
            boolean r9 = r9.equals(r5)
            if (r9 != 0) goto L_0x004d
            r9 = 57
            r0[r9] = r3
            goto L_0x00b1
        L_0x004d:
            r9 = 58
            r0[r9] = r3
            goto L_0x00b2
        L_0x0052:
            java.lang.String r1 = "totop"
            boolean r9 = r9.equals(r1)
            if (r9 != 0) goto L_0x005f
            r9 = 55
            r0[r9] = r3
            goto L_0x00b1
        L_0x005f:
            r9 = 56
            r0[r9] = r3
            r1 = 3
            goto L_0x00b2
        L_0x0065:
            java.lang.String r1 = "totopleft"
            boolean r9 = r9.equals(r1)
            if (r9 != 0) goto L_0x0072
            r9 = 59
            r0[r9] = r3
            goto L_0x00b1
        L_0x0072:
            r1 = 5
            r9 = 60
            r0[r9] = r3
            goto L_0x00b2
        L_0x0078:
            java.lang.String r1 = "toleft"
            boolean r9 = r9.equals(r1)
            if (r9 != 0) goto L_0x0085
            r9 = 51
            r0[r9] = r3
            goto L_0x00b1
        L_0x0085:
            r9 = 52
            r0[r9] = r3
            r1 = 1
            goto L_0x00b2
        L_0x008b:
            java.lang.String r1 = "toright"
            boolean r9 = r9.equals(r1)
            if (r9 != 0) goto L_0x0098
            r9 = 49
            r0[r9] = r3
            goto L_0x00b1
        L_0x0098:
            r9 = 50
            r0[r9] = r3
            r1 = 0
            goto L_0x00b2
        L_0x009e:
            java.lang.String r1 = "tobottom"
            boolean r9 = r9.equals(r1)
            if (r9 != 0) goto L_0x00ab
            r9 = 53
            r0[r9] = r3
            goto L_0x00b1
        L_0x00ab:
            r9 = 54
            r0[r9] = r3
            r1 = 2
            goto L_0x00b2
        L_0x00b1:
            r1 = -1
        L_0x00b2:
            switch(r1) {
                case 0: goto L_0x00e1;
                case 1: goto L_0x00da;
                case 2: goto L_0x00d3;
                case 3: goto L_0x00cc;
                case 4: goto L_0x00c3;
                case 5: goto L_0x00ba;
                default: goto L_0x00b5;
            }
        L_0x00b5:
            r9 = 61
            r0[r9] = r3
            goto L_0x00e7
        L_0x00ba:
            r2[r8] = r10
            r2[r3] = r11
            r9 = 67
            r0[r9] = r3
            goto L_0x00e7
        L_0x00c3:
            r2[r7] = r10
            r2[r6] = r11
            r9 = 66
            r0[r9] = r3
            goto L_0x00e7
        L_0x00cc:
            r2[r3] = r11
            r9 = 65
            r0[r9] = r3
            goto L_0x00e7
        L_0x00d3:
            r2[r6] = r11
            r9 = 64
            r0[r9] = r3
            goto L_0x00e7
        L_0x00da:
            r2[r8] = r10
            r9 = 63
            r0[r9] = r3
            goto L_0x00e7
        L_0x00e1:
            r2[r7] = r10
            r9 = 62
            r0[r9] = r3
        L_0x00e7:
            r9 = 68
            r0[r9] = r3
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.utils.WXResourceUtils.parseGradientDirection(java.lang.String, float, float):float[]");
    }

    public static boolean isNamedColor(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean containsKey = colorMap.containsKey(str);
        $jacocoInit[69] = true;
        return containsKey;
    }

    enum ColorConvertHandler {
        NAMED_COLOR_HANDLER {
            private static transient /* synthetic */ boolean[] $jacocoData;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(-8858134797913925420L, "com/taobao/weex/utils/WXResourceUtils$ColorConvertHandler$1", 4);
                $jacocoData = a2;
                return a2;
            }

            /* access modifiers changed from: package-private */
            @NonNull
            public Pair<Boolean, Integer> handle(String str) {
                boolean[] $jacocoInit = $jacocoInit();
                if (WXResourceUtils.access$100().containsKey(str)) {
                    $jacocoInit[1] = true;
                    Pair<Boolean, Integer> pair = new Pair<>(Boolean.TRUE, WXResourceUtils.access$100().get(str));
                    $jacocoInit[2] = true;
                    return pair;
                }
                Pair<Boolean, Integer> pair2 = new Pair<>(Boolean.FALSE, 0);
                $jacocoInit[3] = true;
                return pair2;
            }
        },
        RGB_HANDLER {
            private static transient /* synthetic */ boolean[] $jacocoData;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(-7890114325884233720L, "com/taobao/weex/utils/WXResourceUtils$ColorConvertHandler$2", 10);
                $jacocoData = a2;
                return a2;
            }

            /* access modifiers changed from: package-private */
            @NonNull
            public Pair<Boolean, Integer> handle(String str) {
                boolean[] $jacocoInit = $jacocoInit();
                if (str.length() == 4) {
                    $jacocoInit[1] = true;
                    int parseInt = Integer.parseInt(str.substring(1, 2), 16);
                    $jacocoInit[2] = true;
                    int parseInt2 = Integer.parseInt(str.substring(2, 3), 16);
                    $jacocoInit[3] = true;
                    int parseInt3 = Integer.parseInt(str.substring(3, 4), 16);
                    $jacocoInit[4] = true;
                    Pair<Boolean, Integer> pair = new Pair<>(Boolean.TRUE, Integer.valueOf(android.graphics.Color.rgb(parseInt + (parseInt << 4), parseInt2 + (parseInt2 << 4), parseInt3 + (parseInt3 << 4))));
                    $jacocoInit[5] = true;
                    return pair;
                }
                if (str.length() == 7) {
                    $jacocoInit[6] = true;
                } else if (str.length() == 9) {
                    $jacocoInit[7] = true;
                } else {
                    Pair<Boolean, Integer> pair2 = new Pair<>(Boolean.FALSE, 0);
                    $jacocoInit[9] = true;
                    return pair2;
                }
                Pair<Boolean, Integer> pair3 = new Pair<>(Boolean.TRUE, Integer.valueOf(android.graphics.Color.parseColor(str)));
                $jacocoInit[8] = true;
                return pair3;
            }
        },
        FUNCTIONAL_RGB_HANDLER {
            private static transient /* synthetic */ boolean[] $jacocoData;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(6685670496811348421L, "com/taobao/weex/utils/WXResourceUtils$ColorConvertHandler$3", 8);
                $jacocoData = a2;
                return a2;
            }

            /* access modifiers changed from: package-private */
            @NonNull
            public Pair<Boolean, Integer> handle(String str) {
                boolean[] $jacocoInit = $jacocoInit();
                SingleFunctionParser singleFunctionParser = new SingleFunctionParser(str, WXResourceUtils.access$200());
                $jacocoInit[1] = true;
                List parse = singleFunctionParser.parse(WXResourceUtils.RGB);
                $jacocoInit[2] = true;
                if (parse == null) {
                    $jacocoInit[3] = true;
                } else if (parse.size() != 3) {
                    $jacocoInit[4] = true;
                } else {
                    $jacocoInit[5] = true;
                    Pair<Boolean, Integer> pair = new Pair<>(Boolean.TRUE, Integer.valueOf(android.graphics.Color.rgb(((Integer) parse.get(0)).intValue(), ((Integer) parse.get(1)).intValue(), ((Integer) parse.get(2)).intValue())));
                    $jacocoInit[6] = true;
                    return pair;
                }
                Pair<Boolean, Integer> pair2 = new Pair<>(Boolean.FALSE, 0);
                $jacocoInit[7] = true;
                return pair2;
            }
        },
        FUNCTIONAL_RGBA_HANDLER {
            private static transient /* synthetic */ boolean[] $jacocoData;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(3198653938146384196L, "com/taobao/weex/utils/WXResourceUtils$ColorConvertHandler$4", 10);
                $jacocoData = a2;
                return a2;
            }

            /* access modifiers changed from: package-private */
            @NonNull
            public Pair<Boolean, Integer> handle(String str) {
                boolean[] $jacocoInit = $jacocoInit();
                SingleFunctionParser singleFunctionParser = new SingleFunctionParser(str, WXResourceUtils.access$300());
                $jacocoInit[1] = true;
                List parse = singleFunctionParser.parse(WXResourceUtils.RGBA);
                $jacocoInit[2] = true;
                if (parse.size() == 4) {
                    Boolean bool = Boolean.TRUE;
                    $jacocoInit[3] = true;
                    int access$400 = ColorConvertHandler.access$400(((Number) parse.get(3)).floatValue());
                    $jacocoInit[4] = true;
                    int intValue = ((Number) parse.get(0)).intValue();
                    $jacocoInit[5] = true;
                    int intValue2 = ((Number) parse.get(1)).intValue();
                    $jacocoInit[6] = true;
                    int intValue3 = ((Number) parse.get(2)).intValue();
                    $jacocoInit[7] = true;
                    Pair<Boolean, Integer> pair = new Pair<>(bool, Integer.valueOf(android.graphics.Color.argb(access$400, intValue, intValue2, intValue3)));
                    $jacocoInit[8] = true;
                    return pair;
                }
                Pair<Boolean, Integer> pair2 = new Pair<>(Boolean.FALSE, 0);
                $jacocoInit[9] = true;
                return pair2;
            }
        };

        /* access modifiers changed from: package-private */
        @NonNull
        public abstract Pair<Boolean, Integer> handle(String str);

        static {
            boolean[] $jacocoInit;
            $jacocoInit[9] = true;
        }

        private static int parseAlpha(float f) {
            int i = (int) (f * 255.0f);
            $jacocoInit()[3] = true;
            return i;
        }
    }
}
