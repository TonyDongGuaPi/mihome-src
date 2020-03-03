package com.taobao.weex.utils;

import android.support.annotation.NonNull;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class FunctionParser<K, V> {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    public static final char SPACE = ' ';
    private Lexer lexer;
    private Mapper<K, V> mapper;

    /* renamed from: com.taobao.weex.utils.FunctionParser$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        private static transient /* synthetic */ boolean[] $jacocoData;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(-4822917845883840080L, "com/taobao/weex/utils/FunctionParser$1", 0);
            $jacocoData = a2;
            return a2;
        }
    }

    public interface Mapper<K, V> {
        Map<K, V> map(String str, List<String> list);
    }

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(3416882541370489204L, "com/taobao/weex/utils/FunctionParser", 24);
        $jacocoData = a2;
        return a2;
    }

    public FunctionParser(@NonNull String str, @NonNull Mapper<K, V> mapper2) {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
        this.lexer = new Lexer(str, (AnonymousClass1) null);
        this.mapper = mapper2;
        $jacocoInit[1] = true;
    }

    public LinkedHashMap<K, V> parse() {
        boolean[] $jacocoInit = $jacocoInit();
        Lexer.access$100(this.lexer);
        $jacocoInit[2] = true;
        LinkedHashMap<K, V> definition = definition();
        $jacocoInit[3] = true;
        return definition;
    }

    private LinkedHashMap<K, V> definition() {
        boolean[] $jacocoInit = $jacocoInit();
        LinkedHashMap<K, V> linkedHashMap = new LinkedHashMap<>();
        $jacocoInit[4] = true;
        while (true) {
            linkedHashMap.putAll(function());
            $jacocoInit[5] = true;
            if (Lexer.access$200(this.lexer) == Token.FUNC_NAME) {
                $jacocoInit[6] = true;
            } else {
                $jacocoInit[7] = true;
                return linkedHashMap;
            }
        }
    }

    private Map<K, V> function() {
        boolean[] $jacocoInit = $jacocoInit();
        LinkedList linkedList = new LinkedList();
        $jacocoInit[8] = true;
        String match = match(Token.FUNC_NAME);
        $jacocoInit[9] = true;
        match(Token.LEFT_PARENT);
        $jacocoInit[10] = true;
        linkedList.add(match(Token.PARAM_VALUE));
        $jacocoInit[11] = true;
        while (Lexer.access$200(this.lexer) == Token.COMMA) {
            $jacocoInit[12] = true;
            match(Token.COMMA);
            $jacocoInit[13] = true;
            linkedList.add(match(Token.PARAM_VALUE));
            $jacocoInit[14] = true;
        }
        match(Token.RIGHT_PARENT);
        $jacocoInit[15] = true;
        Map<K, V> map = this.mapper.map(match, linkedList);
        $jacocoInit[16] = true;
        return map;
    }

    private String match(Token token) {
        boolean[] $jacocoInit = $jacocoInit();
        try {
            if (token == Lexer.access$200(this.lexer)) {
                $jacocoInit[17] = true;
                String access$300 = Lexer.access$300(this.lexer);
                $jacocoInit[18] = true;
                Lexer.access$100(this.lexer);
                $jacocoInit[19] = true;
                return access$300;
            }
            $jacocoInit[20] = true;
            $jacocoInit[23] = true;
            return "";
        } catch (Exception unused) {
            $jacocoInit[21] = true;
            WXLogUtils.e(token + "Token doesn't match" + Lexer.access$400(this.lexer));
            $jacocoInit[22] = true;
        }
    }

    private enum Token {
        FUNC_NAME,
        PARAM_VALUE,
        LEFT_PARENT,
        RIGHT_PARENT,
        COMMA;

        static {
            boolean[] $jacocoInit;
            $jacocoInit[3] = true;
        }
    }

    private static class WXInterpretationException extends RuntimeException {
        private static transient /* synthetic */ boolean[] $jacocoData;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(4681028329452542278L, "com/taobao/weex/utils/FunctionParser$WXInterpretationException", 1);
            $jacocoData = a2;
            return a2;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        private WXInterpretationException(String str) {
            super(str);
            boolean[] $jacocoInit = $jacocoInit();
            $jacocoInit[0] = true;
        }
    }

    private static class Lexer {
        private static transient /* synthetic */ boolean[] $jacocoData = null;
        private static final char A_LOWER = 'a';
        private static final char A_UPPER = 'A';
        private static final String COMMA = ",";
        private static final char DOT = '.';
        private static final String LEFT_PARENT = "(";
        private static final char MINUS = '-';
        private static final char NINE = '9';
        private static final char PLUS = '+';
        private static final String RIGHT_PARENT = ")";
        private static final char ZERO = '0';
        private static final char Z_LOWER = 'z';
        private static final char Z_UPPER = 'Z';
        private Token current;
        private int pointer;
        private String source;
        private String value;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(-988704532423746874L, "com/taobao/weex/utils/FunctionParser$Lexer", 55);
            $jacocoData = a2;
            return a2;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ Lexer(String str, AnonymousClass1 r3) {
            this(str);
            boolean[] $jacocoInit = $jacocoInit();
            $jacocoInit[50] = true;
        }

        static /* synthetic */ boolean access$100(Lexer lexer) {
            boolean[] $jacocoInit = $jacocoInit();
            boolean moveOn = lexer.moveOn();
            $jacocoInit[51] = true;
            return moveOn;
        }

        static /* synthetic */ Token access$200(Lexer lexer) {
            boolean[] $jacocoInit = $jacocoInit();
            Token currentToken = lexer.getCurrentToken();
            $jacocoInit[52] = true;
            return currentToken;
        }

        static /* synthetic */ String access$300(Lexer lexer) {
            boolean[] $jacocoInit = $jacocoInit();
            String currentTokenValue = lexer.getCurrentTokenValue();
            $jacocoInit[53] = true;
            return currentTokenValue;
        }

        static /* synthetic */ String access$400(Lexer lexer) {
            boolean[] $jacocoInit = $jacocoInit();
            String str = lexer.source;
            $jacocoInit[54] = true;
            return str;
        }

        private Lexer(String str) {
            boolean[] $jacocoInit = $jacocoInit();
            this.pointer = 0;
            this.source = str;
            $jacocoInit[0] = true;
        }

        private Token getCurrentToken() {
            boolean[] $jacocoInit = $jacocoInit();
            Token token = this.current;
            $jacocoInit[1] = true;
            return token;
        }

        private String getCurrentTokenValue() {
            boolean[] $jacocoInit = $jacocoInit();
            String str = this.value;
            $jacocoInit[2] = true;
            return str;
        }

        private boolean moveOn() {
            boolean[] $jacocoInit = $jacocoInit();
            int i = this.pointer;
            $jacocoInit[3] = true;
            while (true) {
                if (this.pointer >= this.source.length()) {
                    $jacocoInit[4] = true;
                    break;
                }
                $jacocoInit[5] = true;
                char charAt = this.source.charAt(this.pointer);
                if (charAt == ' ') {
                    int i2 = this.pointer;
                    this.pointer = i2 + 1;
                    if (i != i2) {
                        $jacocoInit[6] = true;
                        break;
                    }
                    i++;
                    $jacocoInit[7] = true;
                } else {
                    if (isCharacterOrDigit(charAt)) {
                        $jacocoInit[8] = true;
                    } else if (charAt == '.') {
                        $jacocoInit[9] = true;
                    } else if (charAt == '%') {
                        $jacocoInit[10] = true;
                    } else if (charAt == '-') {
                        $jacocoInit[11] = true;
                    } else if (charAt == '+') {
                        $jacocoInit[12] = true;
                    } else if (i != this.pointer) {
                        $jacocoInit[14] = true;
                    } else {
                        this.pointer++;
                        $jacocoInit[15] = true;
                    }
                    this.pointer++;
                    $jacocoInit[13] = true;
                }
            }
            if (i != this.pointer) {
                $jacocoInit[16] = true;
                String substring = this.source.substring(i, this.pointer);
                $jacocoInit[17] = true;
                moveOn(substring);
                $jacocoInit[18] = true;
                return true;
            }
            this.current = null;
            this.value = null;
            $jacocoInit[19] = true;
            return false;
        }

        private void moveOn(String str) {
            boolean[] $jacocoInit = $jacocoInit();
            if ("(".equals(str)) {
                this.current = Token.LEFT_PARENT;
                this.value = "(";
                $jacocoInit[20] = true;
            } else if (")".equals(str)) {
                this.current = Token.RIGHT_PARENT;
                this.value = ")";
                $jacocoInit[21] = true;
            } else if (",".equals(str)) {
                this.current = Token.COMMA;
                this.value = ",";
                $jacocoInit[22] = true;
            } else if (isFuncName(str)) {
                this.current = Token.FUNC_NAME;
                this.value = str;
                $jacocoInit[23] = true;
            } else {
                this.current = Token.PARAM_VALUE;
                this.value = str;
                $jacocoInit[24] = true;
            }
            $jacocoInit[25] = true;
        }

        private boolean isFuncName(CharSequence charSequence) {
            boolean[] $jacocoInit = $jacocoInit();
            $jacocoInit[26] = true;
            int i = 0;
            while (i < charSequence.length()) {
                $jacocoInit[27] = true;
                char charAt = charSequence.charAt(i);
                if ('a' > charAt) {
                    $jacocoInit[28] = true;
                } else if (charAt <= 'z') {
                    $jacocoInit[29] = true;
                    i++;
                    $jacocoInit[36] = true;
                } else {
                    $jacocoInit[30] = true;
                }
                if ('A' > charAt) {
                    $jacocoInit[31] = true;
                } else if (charAt <= 'Z') {
                    $jacocoInit[32] = true;
                    i++;
                    $jacocoInit[36] = true;
                } else {
                    $jacocoInit[33] = true;
                }
                if (charAt == '-') {
                    $jacocoInit[34] = true;
                    i++;
                    $jacocoInit[36] = true;
                } else {
                    $jacocoInit[35] = true;
                    return false;
                }
            }
            $jacocoInit[37] = true;
            return true;
        }

        private boolean isCharacterOrDigit(char c) {
            boolean z;
            boolean[] $jacocoInit = $jacocoInit();
            if ('0' > c) {
                $jacocoInit[38] = true;
            } else if (c <= '9') {
                $jacocoInit[39] = true;
                $jacocoInit[47] = true;
                z = true;
                $jacocoInit[49] = true;
                return z;
            } else {
                $jacocoInit[40] = true;
            }
            if ('a' > c) {
                $jacocoInit[41] = true;
            } else if (c <= 'z') {
                $jacocoInit[42] = true;
                $jacocoInit[47] = true;
                z = true;
                $jacocoInit[49] = true;
                return z;
            } else {
                $jacocoInit[43] = true;
            }
            if ('A' > c) {
                $jacocoInit[44] = true;
            } else if (c > 'Z') {
                $jacocoInit[45] = true;
            } else {
                $jacocoInit[46] = true;
                $jacocoInit[47] = true;
                z = true;
                $jacocoInit[49] = true;
                return z;
            }
            z = false;
            $jacocoInit[48] = true;
            $jacocoInit[49] = true;
            return z;
        }
    }
}
