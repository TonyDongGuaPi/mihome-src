package com.taobao.weex.el.parse;

import com.taobao.weex.common.Constants;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class Operators {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    public static final String AND = "&&";
    public static final String AND_NOT = "!";
    public static final char ARRAY_END = ']';
    public static final String ARRAY_END_STR = "]";
    public static final char ARRAY_SEPRATOR = ',';
    public static final String ARRAY_SEPRATOR_STR = ",";
    public static final char ARRAY_START = '[';
    public static final String ARRAY_START_STR = "[";
    public static final char BLOCK_END = '}';
    public static final String BLOCK_END_STR = "}";
    public static final char BLOCK_START = '{';
    public static final String BLOCK_START_STR = "{";
    public static final char BRACKET_END = ')';
    public static final String BRACKET_END_STR = ")";
    public static final char BRACKET_START = '(';
    public static final String BRACKET_START_STR = "(";
    public static final char CONDITION_IF = '?';
    public static final char CONDITION_IF_MIDDLE = ':';
    public static final String CONDITION_IF_STRING = "?";
    public static final String DIV = "/";
    public static final char DOLLAR = '$';
    public static final String DOLLAR_STR = "$";
    public static final char DOT = '.';
    public static final String DOT_STR = ".";
    public static final String EQUAL = "===";
    public static final String EQUAL2 = "==";
    public static final String G = ">";
    public static final String GE = ">=";
    public static final Map<String, Object> KEYWORDS = new HashMap();
    public static final String L = "<";
    public static final String LE = "<=";
    public static final String MOD = "%";
    public static final String MUL = "*";
    public static final String NOT_EQUAL = "!==";
    public static final String NOT_EQUAL2 = "!=";
    public static Map<String, Integer> OPERATORS_PRIORITY = new HashMap();
    public static final String OR = "||";
    public static final String PLUS = "+";
    public static final char QUOTE = '\"';
    public static final char SINGLE_QUOTE = '\'';
    public static final char SPACE = ' ';
    public static final String SPACE_STR = " ";
    public static final String SUB = "-";

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(643479400282584339L, "com/taobao/weex/el/parse/Operators", 233);
        $jacocoData = a2;
        return a2;
    }

    public Operators() {
        $jacocoInit()[0] = true;
    }

    public static Object dot(Token token, Token token2, Object obj) {
        Object obj2;
        String str;
        boolean[] $jacocoInit = $jacocoInit();
        if (token == null) {
            $jacocoInit[1] = true;
        } else if (token2 == null) {
            $jacocoInit[2] = true;
        } else {
            Object execute = token.execute(obj);
            if (execute == null) {
                $jacocoInit[4] = true;
                return null;
            }
            $jacocoInit[5] = true;
            if (token2.getType() != 0) {
                $jacocoInit[6] = true;
                Object execute2 = token2.execute(obj);
                if (!(execute2 instanceof Double)) {
                    $jacocoInit[7] = true;
                } else {
                    $jacocoInit[8] = true;
                    execute2 = Integer.valueOf(((Double) execute2).intValue());
                    $jacocoInit[9] = true;
                }
                if (execute2 == null) {
                    str = "";
                    $jacocoInit[10] = true;
                } else {
                    str = execute2.toString().trim();
                    $jacocoInit[11] = true;
                }
                $jacocoInit[12] = true;
                obj2 = el(execute, str);
                $jacocoInit[13] = true;
            } else {
                obj2 = token2.execute(execute);
                $jacocoInit[14] = true;
            }
            if (obj2 != null) {
                $jacocoInit[15] = true;
                return obj2;
            }
            Object specialKey = specialKey(execute, token2.getToken());
            $jacocoInit[16] = true;
            return specialKey;
        }
        $jacocoInit[3] = true;
        return null;
    }

    public static Object el(Object obj, String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (obj == null) {
            $jacocoInit[17] = true;
            return null;
        }
        if (!(obj instanceof ArrayStack)) {
            $jacocoInit[18] = true;
        } else {
            ArrayStack arrayStack = (ArrayStack) obj;
            $jacocoInit[19] = true;
            int size = arrayStack.size() - 1;
            $jacocoInit[20] = true;
            while (size >= 0) {
                $jacocoInit[22] = true;
                Object obj2 = arrayStack.get(size);
                if (!(obj2 instanceof Map)) {
                    $jacocoInit[23] = true;
                } else {
                    Map map = (Map) obj2;
                    $jacocoInit[24] = true;
                    if (!map.containsKey(str)) {
                        $jacocoInit[25] = true;
                    } else {
                        $jacocoInit[26] = true;
                        Object obj3 = map.get(str);
                        $jacocoInit[27] = true;
                        return obj3;
                    }
                }
                size--;
                $jacocoInit[28] = true;
            }
            $jacocoInit[21] = true;
        }
        if (!(obj instanceof Stack)) {
            $jacocoInit[29] = true;
        } else {
            Stack stack = (Stack) obj;
            $jacocoInit[30] = true;
            int size2 = stack.size() - 1;
            $jacocoInit[31] = true;
            while (size2 >= 0) {
                $jacocoInit[33] = true;
                Object obj4 = stack.get(size2);
                if (!(obj4 instanceof Map)) {
                    $jacocoInit[34] = true;
                } else {
                    Map map2 = (Map) obj4;
                    $jacocoInit[35] = true;
                    if (!map2.containsKey(str)) {
                        $jacocoInit[36] = true;
                    } else {
                        $jacocoInit[37] = true;
                        Object obj5 = map2.get(str);
                        $jacocoInit[38] = true;
                        return obj5;
                    }
                }
                size2--;
                $jacocoInit[39] = true;
            }
            $jacocoInit[32] = true;
        }
        if (obj instanceof Map) {
            $jacocoInit[40] = true;
            Object obj6 = ((Map) obj).get(str);
            $jacocoInit[41] = true;
            return obj6;
        }
        if (!(obj instanceof List)) {
            $jacocoInit[42] = true;
        } else {
            List list = (List) obj;
            try {
                $jacocoInit[43] = true;
                Object obj7 = list.get(Integer.parseInt(str));
                $jacocoInit[44] = true;
                return obj7;
            } catch (Exception unused) {
                $jacocoInit[45] = true;
            }
        }
        if (!obj.getClass().isArray()) {
            $jacocoInit[46] = true;
        } else {
            try {
                $jacocoInit[47] = true;
                Object obj8 = Array.get(obj, Integer.parseInt(str));
                $jacocoInit[48] = true;
                return obj8;
            } catch (Exception unused2) {
                $jacocoInit[49] = true;
            }
        }
        $jacocoInit[50] = true;
        return null;
    }

    public static Object specialKey(Object obj, String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!"length".equals(str)) {
            $jacocoInit[51] = true;
        } else if (obj instanceof CharSequence) {
            $jacocoInit[52] = true;
            Integer valueOf = Integer.valueOf(((CharSequence) obj).length());
            $jacocoInit[53] = true;
            return valueOf;
        } else {
            boolean z = obj instanceof Map;
            if (z) {
                $jacocoInit[54] = true;
                Integer valueOf2 = Integer.valueOf(((Map) obj).size());
                $jacocoInit[55] = true;
                return valueOf2;
            } else if (z) {
                $jacocoInit[56] = true;
                Integer valueOf3 = Integer.valueOf(((Map) obj).size());
                $jacocoInit[57] = true;
                return valueOf3;
            } else if (obj instanceof List) {
                $jacocoInit[58] = true;
                Integer valueOf4 = Integer.valueOf(((List) obj).size());
                $jacocoInit[59] = true;
                return valueOf4;
            } else if (!obj.getClass().isArray()) {
                $jacocoInit[60] = true;
            } else {
                $jacocoInit[61] = true;
                Integer valueOf5 = Integer.valueOf(Array.getLength(obj));
                $jacocoInit[62] = true;
                return valueOf5;
            }
        }
        $jacocoInit[63] = true;
        return null;
    }

    public static Object plus(Token token, Token token2, Object obj) {
        Object obj2;
        Object obj3;
        String str;
        String str2;
        boolean[] $jacocoInit = $jacocoInit();
        if (token == null) {
            $jacocoInit[64] = true;
            obj2 = null;
        } else {
            $jacocoInit[65] = true;
            obj2 = token.execute(obj);
            $jacocoInit[66] = true;
        }
        if (token2 == null) {
            $jacocoInit[67] = true;
            obj3 = null;
        } else {
            $jacocoInit[68] = true;
            obj3 = token2.execute(obj);
            $jacocoInit[69] = true;
        }
        if (obj2 instanceof CharSequence) {
            $jacocoInit[70] = true;
        } else if (obj3 instanceof CharSequence) {
            $jacocoInit[71] = true;
        } else {
            if (obj2 instanceof Number) {
                $jacocoInit[76] = true;
            } else if (obj3 instanceof Number) {
                $jacocoInit[77] = true;
            } else {
                if (obj2 != null) {
                    $jacocoInit[79] = true;
                } else if (obj3 != null) {
                    $jacocoInit[80] = true;
                } else {
                    $jacocoInit[81] = true;
                    return null;
                }
                if (obj2 == null) {
                    $jacocoInit[82] = true;
                    String obj4 = obj3.toString();
                    $jacocoInit[83] = true;
                    return obj4;
                }
                StringBuilder sb = new StringBuilder();
                sb.append(obj2.toString());
                if (obj3 == null) {
                    str2 = "";
                    $jacocoInit[84] = true;
                } else {
                    str2 = obj3.toString();
                    $jacocoInit[85] = true;
                }
                sb.append(str2);
                String sb2 = sb.toString();
                $jacocoInit[86] = true;
                return sb2;
            }
            Double valueOf = Double.valueOf(getNumber(obj2) + getNumber(obj3));
            $jacocoInit[78] = true;
            return valueOf;
        }
        if (obj2 == null) {
            $jacocoInit[72] = true;
            return obj3;
        }
        StringBuilder sb3 = new StringBuilder();
        sb3.append(obj2.toString());
        if (obj3 == null) {
            str = "";
            $jacocoInit[73] = true;
        } else {
            str = obj3.toString();
            $jacocoInit[74] = true;
        }
        sb3.append(str);
        String sb4 = sb3.toString();
        $jacocoInit[75] = true;
        return sb4;
    }

    public static Object sub(Token token, Token token2, Object obj) {
        Object obj2;
        boolean[] $jacocoInit = $jacocoInit();
        Object obj3 = null;
        if (token == null) {
            $jacocoInit[87] = true;
            obj2 = null;
        } else {
            $jacocoInit[88] = true;
            obj2 = token.execute(obj);
            $jacocoInit[89] = true;
        }
        if (token2 == null) {
            $jacocoInit[90] = true;
        } else {
            $jacocoInit[91] = true;
            obj3 = token2.execute(obj);
            $jacocoInit[92] = true;
        }
        Double valueOf = Double.valueOf(getNumber(obj2) - getNumber(obj3));
        $jacocoInit[93] = true;
        return valueOf;
    }

    public static Object div(Token token, Token token2, Object obj) {
        Object obj2;
        boolean[] $jacocoInit = $jacocoInit();
        Object obj3 = null;
        if (token == null) {
            $jacocoInit[94] = true;
            obj2 = null;
        } else {
            $jacocoInit[95] = true;
            obj2 = token.execute(obj);
            $jacocoInit[96] = true;
        }
        if (token2 == null) {
            $jacocoInit[97] = true;
        } else {
            $jacocoInit[98] = true;
            obj3 = token2.execute(obj);
            $jacocoInit[99] = true;
        }
        Double valueOf = Double.valueOf(getNumber(obj2) / getNumber(obj3));
        $jacocoInit[100] = true;
        return valueOf;
    }

    public static Object mul(Token token, Token token2, Object obj) {
        Object obj2;
        boolean[] $jacocoInit = $jacocoInit();
        Object obj3 = null;
        if (token == null) {
            $jacocoInit[101] = true;
            obj2 = null;
        } else {
            $jacocoInit[102] = true;
            obj2 = token.execute(obj);
            $jacocoInit[103] = true;
        }
        if (token2 == null) {
            $jacocoInit[104] = true;
        } else {
            $jacocoInit[105] = true;
            obj3 = token2.execute(obj);
            $jacocoInit[106] = true;
        }
        Double valueOf = Double.valueOf(getNumber(obj2) * getNumber(obj3));
        $jacocoInit[107] = true;
        return valueOf;
    }

    public static Object mod(Token token, Token token2, Object obj) {
        Object obj2;
        boolean[] $jacocoInit = $jacocoInit();
        Object obj3 = null;
        if (token == null) {
            $jacocoInit[108] = true;
            obj2 = null;
        } else {
            $jacocoInit[109] = true;
            obj2 = token.execute(obj);
            $jacocoInit[110] = true;
        }
        if (token2 == null) {
            $jacocoInit[111] = true;
        } else {
            $jacocoInit[112] = true;
            obj3 = token2.execute(obj);
            $jacocoInit[113] = true;
        }
        Double valueOf = Double.valueOf(getNumber(obj2) % getNumber(obj3));
        $jacocoInit[114] = true;
        return valueOf;
    }

    public static Object condition(Token token, Token token2, Token token3, Object obj) {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (token == null) {
            $jacocoInit[115] = true;
            z = false;
        } else {
            $jacocoInit[116] = true;
            z = isTrue(token.execute(obj));
            $jacocoInit[117] = true;
        }
        if (z) {
            if (token2 == null) {
                $jacocoInit[118] = true;
            } else {
                $jacocoInit[119] = true;
                Object execute = token2.execute(obj);
                $jacocoInit[120] = true;
                return execute;
            }
        } else if (token3 == null) {
            $jacocoInit[121] = true;
        } else {
            $jacocoInit[122] = true;
            Object execute2 = token3.execute(obj);
            $jacocoInit[123] = true;
            return execute2;
        }
        $jacocoInit[124] = true;
        return null;
    }

    public static boolean tokenTrue(Token token, Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        if (token == null) {
            $jacocoInit[125] = true;
            return false;
        }
        Object execute = token.execute(obj);
        $jacocoInit[126] = true;
        boolean isTrue = isTrue(execute);
        $jacocoInit[127] = true;
        return isTrue;
    }

    public static double tokenNumber(Token token, Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        if (token == null) {
            $jacocoInit[128] = true;
            return 0.0d;
        }
        Object execute = token.execute(obj);
        $jacocoInit[129] = true;
        double number = getNumber(execute);
        $jacocoInit[130] = true;
        return number;
    }

    public static boolean isEquals(Token token, Token token2, Object obj) {
        Object obj2;
        boolean[] $jacocoInit = $jacocoInit();
        if (token != null) {
            $jacocoInit[131] = true;
        } else if (token2 != null) {
            $jacocoInit[132] = true;
        } else {
            $jacocoInit[133] = true;
            return true;
        }
        Object obj3 = null;
        if (token == null) {
            $jacocoInit[134] = true;
            obj2 = null;
        } else {
            $jacocoInit[135] = true;
            obj2 = token.execute(obj);
            $jacocoInit[136] = true;
        }
        if (token2 == null) {
            $jacocoInit[137] = true;
        } else {
            $jacocoInit[138] = true;
            obj3 = token2.execute(obj);
            $jacocoInit[139] = true;
        }
        boolean z = false;
        if (obj2 == null) {
            if (obj3 == null) {
                $jacocoInit[140] = true;
                return true;
            }
            if (!(obj3 instanceof CharSequence)) {
                $jacocoInit[141] = true;
            } else {
                $jacocoInit[142] = true;
                if (!isEmpty(obj3.toString())) {
                    $jacocoInit[143] = true;
                } else {
                    $jacocoInit[144] = true;
                    return true;
                }
            }
            $jacocoInit[145] = true;
            return false;
        } else if (obj3 == null) {
            $jacocoInit[146] = true;
            if (isEmpty(obj2.toString())) {
                $jacocoInit[147] = true;
                return true;
            }
            $jacocoInit[148] = true;
            return false;
        } else if (obj2 instanceof Number) {
            if (obj3 instanceof Number) {
                $jacocoInit[149] = true;
                if (((Number) obj2).doubleValue() == ((Number) obj3).doubleValue()) {
                    $jacocoInit[150] = true;
                    z = true;
                } else {
                    $jacocoInit[151] = true;
                }
                $jacocoInit[152] = true;
                return z;
            }
            if (((Number) obj2).doubleValue() == getNumber(obj3)) {
                $jacocoInit[153] = true;
                z = true;
            } else {
                $jacocoInit[154] = true;
            }
            $jacocoInit[155] = true;
            return z;
        } else if (obj3 instanceof Number) {
            $jacocoInit[156] = true;
            if (getNumber(obj2) == ((Number) obj3).doubleValue()) {
                $jacocoInit[157] = true;
                z = true;
            } else {
                $jacocoInit[158] = true;
            }
            $jacocoInit[159] = true;
            return z;
        } else {
            if (obj2 instanceof CharSequence) {
                $jacocoInit[160] = true;
            } else if (obj3 instanceof CharSequence) {
                $jacocoInit[161] = true;
            } else {
                boolean equals = obj2.equals(obj3);
                $jacocoInit[163] = true;
                return equals;
            }
            boolean equals2 = obj2.toString().trim().equals(obj3.toString().trim());
            $jacocoInit[162] = true;
            return equals2;
        }
    }

    public static boolean isTrue(Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = false;
        if (obj == null) {
            $jacocoInit[164] = true;
            return false;
        } else if (obj instanceof Number) {
            $jacocoInit[165] = true;
            if (((Number) obj).doubleValue() != 0.0d) {
                $jacocoInit[166] = true;
                z = true;
            } else {
                $jacocoInit[167] = true;
            }
            $jacocoInit[168] = true;
            return z;
        } else {
            String trim = obj.toString().trim();
            $jacocoInit[169] = true;
            if ("false".equals(trim)) {
                $jacocoInit[170] = true;
            } else {
                $jacocoInit[171] = true;
                if (Constants.Name.UNDEFINED.equals(trim)) {
                    $jacocoInit[172] = true;
                } else {
                    $jacocoInit[173] = true;
                    if ("null".equals(trim)) {
                        $jacocoInit[174] = true;
                    } else if (isEmpty(trim)) {
                        $jacocoInit[176] = true;
                        return false;
                    } else {
                        $jacocoInit[177] = true;
                        return true;
                    }
                }
            }
            $jacocoInit[175] = true;
            return false;
        }
    }

    public static boolean isEmpty(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (str == null) {
            $jacocoInit[178] = true;
            return true;
        }
        $jacocoInit[179] = true;
        int i = 0;
        while (i < str.length()) {
            $jacocoInit[180] = true;
            if (str.charAt(i) != ' ') {
                $jacocoInit[181] = true;
                return false;
            }
            i++;
            $jacocoInit[182] = true;
        }
        $jacocoInit[183] = true;
        return true;
    }

    public static double getNumber(Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        if (obj == null) {
            $jacocoInit[184] = true;
            return 0.0d;
        } else if (!(obj instanceof Number)) {
            $jacocoInit[185] = true;
            try {
                double parseDouble = Double.parseDouble(obj.toString());
                $jacocoInit[188] = true;
                return parseDouble;
            } catch (Exception unused) {
                $jacocoInit[189] = true;
                return 0.0d;
            }
        } else {
            $jacocoInit[186] = true;
            double doubleValue = ((Number) obj).doubleValue();
            $jacocoInit[187] = true;
            return doubleValue;
        }
    }

    public static boolean isOpEnd(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean isOpEnd = isOpEnd(str.charAt(0));
        $jacocoInit[190] = true;
        return isOpEnd;
    }

    public static boolean isOpEnd(char c) {
        boolean[] $jacocoInit = $jacocoInit();
        if (c == ')') {
            $jacocoInit[191] = true;
        } else if (c == ']') {
            $jacocoInit[192] = true;
        } else if (c == ' ') {
            $jacocoInit[193] = true;
        } else if (c == ',') {
            $jacocoInit[194] = true;
        } else {
            $jacocoInit[196] = true;
            return false;
        }
        $jacocoInit[195] = true;
        return true;
    }

    public static boolean isDot(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = false;
        char charAt = str.charAt(0);
        if (charAt == '.') {
            $jacocoInit[197] = true;
        } else if (charAt == '[') {
            $jacocoInit[198] = true;
        } else {
            $jacocoInit[200] = true;
            $jacocoInit[201] = true;
            return z;
        }
        $jacocoInit[199] = true;
        z = true;
        $jacocoInit[201] = true;
        return z;
    }

    static {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[202] = true;
        OPERATORS_PRIORITY.put("}", 0);
        $jacocoInit[203] = true;
        OPERATORS_PRIORITY.put(BRACKET_END_STR, 0);
        $jacocoInit[204] = true;
        OPERATORS_PRIORITY.put(" ", 0);
        $jacocoInit[205] = true;
        OPERATORS_PRIORITY.put(",", 0);
        $jacocoInit[206] = true;
        OPERATORS_PRIORITY.put(ARRAY_END_STR, 0);
        $jacocoInit[207] = true;
        OPERATORS_PRIORITY.put("||", 1);
        $jacocoInit[208] = true;
        OPERATORS_PRIORITY.put(AND, 1);
        $jacocoInit[209] = true;
        OPERATORS_PRIORITY.put(EQUAL, 2);
        $jacocoInit[210] = true;
        OPERATORS_PRIORITY.put(EQUAL2, 2);
        $jacocoInit[211] = true;
        OPERATORS_PRIORITY.put(NOT_EQUAL, 2);
        $jacocoInit[212] = true;
        OPERATORS_PRIORITY.put(NOT_EQUAL2, 2);
        $jacocoInit[213] = true;
        OPERATORS_PRIORITY.put(">", 7);
        $jacocoInit[214] = true;
        OPERATORS_PRIORITY.put(">=", 7);
        $jacocoInit[215] = true;
        OPERATORS_PRIORITY.put("<", 7);
        $jacocoInit[216] = true;
        OPERATORS_PRIORITY.put("<=", 8);
        $jacocoInit[217] = true;
        OPERATORS_PRIORITY.put("+", 9);
        $jacocoInit[218] = true;
        OPERATORS_PRIORITY.put("-", 9);
        $jacocoInit[219] = true;
        OPERATORS_PRIORITY.put("*", 10);
        $jacocoInit[220] = true;
        OPERATORS_PRIORITY.put("/", 10);
        $jacocoInit[221] = true;
        OPERATORS_PRIORITY.put(MOD, 10);
        $jacocoInit[222] = true;
        OPERATORS_PRIORITY.put("!", 11);
        $jacocoInit[223] = true;
        OPERATORS_PRIORITY.put(".", 15);
        $jacocoInit[224] = true;
        OPERATORS_PRIORITY.put(ARRAY_START_STR, 16);
        $jacocoInit[225] = true;
        OPERATORS_PRIORITY.put(BRACKET_START_STR, 17);
        $jacocoInit[226] = true;
        OPERATORS_PRIORITY.put(BLOCK_START_STR, 17);
        $jacocoInit[227] = true;
        $jacocoInit[228] = true;
        KEYWORDS.put("null", (Object) null);
        $jacocoInit[229] = true;
        KEYWORDS.put("true", Boolean.TRUE);
        $jacocoInit[230] = true;
        KEYWORDS.put("false", Boolean.FALSE);
        $jacocoInit[231] = true;
        KEYWORDS.put(Constants.Name.UNDEFINED, (Object) null);
        $jacocoInit[232] = true;
    }
}
