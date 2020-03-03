package com.taobao.weex.el.parse;

import com.drew.metadata.exif.makernotes.OlympusRawInfoMakernoteDirectory;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.utils.WXLogUtils;
import java.util.ArrayList;
import java.util.List;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class Parser {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private String code;
    private ArrayStack<Symbol> operators;
    private int position = 0;
    private ArrayStack<Token> stacks;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-518973406019981313L, "com/taobao/weex/el/parse/Parser", OlympusRawInfoMakernoteDirectory.l);
        $jacocoData = a2;
        return a2;
    }

    public Parser(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        this.code = str;
        $jacocoInit[0] = true;
        this.stacks = new ArrayStack<>();
        $jacocoInit[1] = true;
        this.operators = new ArrayStack<>();
        $jacocoInit[2] = true;
    }

    public final Token parse() {
        boolean[] $jacocoInit = $jacocoInit();
        while (hasNextToken()) {
            $jacocoInit[4] = true;
            scanNextToken();
            $jacocoInit[5] = true;
        }
        $jacocoInit[3] = true;
        while (!this.operators.isEmpty()) {
            $jacocoInit[6] = true;
            $jacocoInit[7] = true;
            doOperator(this.operators.pop());
            $jacocoInit[8] = true;
        }
        if (this.stacks.size() == 1) {
            $jacocoInit[9] = true;
            Token pop = this.stacks.pop();
            $jacocoInit[10] = true;
            return pop;
        }
        Block block = new Block(this.stacks.getList(), 6);
        $jacocoInit[11] = true;
        return block;
    }

    public static Token parse(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        try {
            Parser parser = new Parser(str);
            $jacocoInit[12] = true;
            Token parse = parser.parse();
            $jacocoInit[13] = true;
            return parse;
        } catch (Exception e) {
            $jacocoInit[14] = true;
            if (!WXEnvironment.isApkDebugable()) {
                $jacocoInit[15] = true;
            } else {
                $jacocoInit[16] = true;
                WXLogUtils.e("code " + str, (Throwable) e);
                $jacocoInit[17] = true;
            }
            Block block = new Block((List<Token>) null, 6);
            $jacocoInit[18] = true;
            return block;
        }
    }

    /* access modifiers changed from: package-private */
    public final char scanNextToken() {
        boolean[] $jacocoInit = $jacocoInit();
        char nextToken = nextToken();
        if (nextToken == '$') {
            this.position++;
            $jacocoInit[19] = true;
            return nextToken;
        }
        if (Character.isJavaIdentifierStart(nextToken)) {
            $jacocoInit[20] = true;
            scanIdentifier();
            $jacocoInit[21] = true;
        } else {
            if (nextToken == '(') {
                $jacocoInit[22] = true;
            } else if (nextToken == '{') {
                $jacocoInit[23] = true;
            } else if (nextToken == '[') {
                $jacocoInit[25] = true;
                scanArray();
                $jacocoInit[26] = true;
            } else {
                if (nextToken == '\"') {
                    $jacocoInit[27] = true;
                } else if (nextToken == '\'') {
                    $jacocoInit[28] = true;
                } else {
                    if (nextToken != '.') {
                        $jacocoInit[30] = true;
                    } else if (Character.isDigit(this.code.charAt(this.position + 1))) {
                        $jacocoInit[31] = true;
                        scanNumber();
                        $jacocoInit[35] = true;
                    } else {
                        $jacocoInit[32] = true;
                    }
                    $jacocoInit[33] = true;
                    if (Character.isDigit(nextToken)) {
                        $jacocoInit[34] = true;
                        scanNumber();
                        $jacocoInit[35] = true;
                    } else if (nextToken == '?') {
                        $jacocoInit[36] = true;
                        scanIf();
                        $jacocoInit[37] = true;
                    } else {
                        if (nextToken == ':') {
                            $jacocoInit[38] = true;
                        } else if (nextToken == ')') {
                            $jacocoInit[39] = true;
                        } else if (nextToken == '}') {
                            $jacocoInit[40] = true;
                        } else if (nextToken == ' ') {
                            $jacocoInit[41] = true;
                        } else if (nextToken == ']') {
                            $jacocoInit[42] = true;
                        } else {
                            scanOperator();
                            $jacocoInit[44] = true;
                        }
                        this.position++;
                        $jacocoInit[43] = true;
                        return nextToken;
                    }
                }
                scanString();
                $jacocoInit[29] = true;
            }
            scanBracket();
            $jacocoInit[24] = true;
        }
        $jacocoInit[45] = true;
        return nextToken;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0081  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x016a  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0064 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void scanArray() {
        /*
            r11 = this;
            boolean[] r0 = $jacocoInit()
            com.taobao.weex.el.parse.ArrayStack<com.taobao.weex.el.parse.Token> r1 = r11.stacks
            int r1 = r1.size()
            r2 = 1
            r3 = 46
            r0[r3] = r2
            com.taobao.weex.el.parse.ArrayStack<com.taobao.weex.el.parse.Symbol> r3 = r11.operators
            int r3 = r3.size()
            r4 = 47
            r0[r4] = r2
            int r4 = r11.position
            int r4 = r4 - r2
            r5 = 0
            r6 = 7
            if (r4 >= 0) goto L_0x0025
            r4 = 48
            r0[r4] = r2
            goto L_0x003e
        L_0x0025:
            java.lang.String r4 = r11.code
            int r7 = r11.position
            int r7 = r7 - r2
            char r4 = r4.charAt(r7)
            boolean r4 = java.lang.Character.isJavaIdentifierPart(r4)
            if (r4 == 0) goto L_0x003a
            r4 = 49
            r0[r4] = r2
            r4 = 0
            goto L_0x0043
        L_0x003a:
            r4 = 50
            r0[r4] = r2
        L_0x003e:
            r4 = 51
            r0[r4] = r2
            r4 = 7
        L_0x0043:
            com.taobao.weex.el.parse.ArrayStack<com.taobao.weex.el.parse.Symbol> r7 = r11.operators
            com.taobao.weex.el.parse.Symbol r8 = new com.taobao.weex.el.parse.Symbol
            java.lang.String r9 = "["
            com.taobao.weex.el.parse.ArrayStack<com.taobao.weex.el.parse.Token> r10 = r11.stacks
            int r10 = r10.size()
            r8.<init>(r9, r10)
            r7.push(r8)
            int r7 = r11.position
            int r7 = r7 + r2
            r11.position = r7
            r7 = 52
            r0[r7] = r2
        L_0x005e:
            boolean r7 = r11.hasNextToken()
            if (r7 != 0) goto L_0x0069
            r7 = 53
            r0[r7] = r2
            goto L_0x0079
        L_0x0069:
            r7 = 54
            r0[r7] = r2
            char r7 = r11.scanNextToken()
            r8 = 93
            if (r7 != r8) goto L_0x0189
            r7 = 55
            r0[r7] = r2
        L_0x0079:
            com.taobao.weex.el.parse.ArrayStack<com.taobao.weex.el.parse.Token> r7 = r11.stacks
            int r7 = r7.size()
            if (r7 <= r1) goto L_0x016a
            r7 = 57
            r0[r7] = r2
        L_0x0085:
            com.taobao.weex.el.parse.ArrayStack<com.taobao.weex.el.parse.Symbol> r7 = r11.operators
            int r7 = r7.size()
            if (r7 <= r3) goto L_0x00ba
            r7 = 62
            r0[r7] = r2
            com.taobao.weex.el.parse.ArrayStack<com.taobao.weex.el.parse.Symbol> r7 = r11.operators
            java.lang.Object r7 = r7.pop()
            com.taobao.weex.el.parse.Symbol r7 = (com.taobao.weex.el.parse.Symbol) r7
            r8 = 63
            r0[r8] = r2
            com.taobao.weex.el.parse.ArrayStack<com.taobao.weex.el.parse.Token> r8 = r11.stacks
            int r8 = r8.size()
            if (r8 > r1) goto L_0x00aa
            r7 = 64
            r0[r7] = r2
            goto L_0x00b5
        L_0x00aa:
            r8 = 65
            r0[r8] = r2
            r11.doOperator(r7)
            r7 = 66
            r0[r7] = r2
        L_0x00b5:
            r7 = 67
            r0[r7] = r2
            goto L_0x0085
        L_0x00ba:
            java.util.ArrayList r7 = new java.util.ArrayList
            r3 = 4
            r7.<init>(r3)
            r3 = 68
            r0[r3] = r2
            r3 = 69
            r0[r3] = r2
            r3 = r1
        L_0x00c9:
            com.taobao.weex.el.parse.ArrayStack<com.taobao.weex.el.parse.Token> r8 = r11.stacks
            int r8 = r8.size()
            if (r3 < r8) goto L_0x0155
            r3 = 70
            r0[r3] = r2
        L_0x00d5:
            com.taobao.weex.el.parse.ArrayStack<com.taobao.weex.el.parse.Token> r3 = r11.stacks
            int r3 = r3.size()
            if (r3 <= r1) goto L_0x00eb
            r3 = 73
            r0[r3] = r2
            com.taobao.weex.el.parse.ArrayStack<com.taobao.weex.el.parse.Token> r3 = r11.stacks
            r3.pop()
            r3 = 74
            r0[r3] = r2
            goto L_0x00d5
        L_0x00eb:
            if (r4 != r6) goto L_0x00f2
            r1 = 75
            r0[r1] = r2
            goto L_0x00fe
        L_0x00f2:
            com.taobao.weex.el.parse.ArrayStack<com.taobao.weex.el.parse.Token> r1 = r11.stacks
            int r1 = r1.size()
            if (r1 != 0) goto L_0x0111
            r1 = 76
            r0[r1] = r2
        L_0x00fe:
            com.taobao.weex.el.parse.Block r1 = new com.taobao.weex.el.parse.Block
            r1.<init>(r7, r6)
            r3 = 77
            r0[r3] = r2
            com.taobao.weex.el.parse.ArrayStack<com.taobao.weex.el.parse.Token> r3 = r11.stacks
            r3.push(r1)
            r1 = 78
            r0[r1] = r2
            return
        L_0x0111:
            com.taobao.weex.el.parse.ArrayStack<com.taobao.weex.el.parse.Token> r1 = r11.stacks
            java.lang.Object r1 = r1.pop()
            com.taobao.weex.el.parse.Token r1 = (com.taobao.weex.el.parse.Token) r1
            r3 = 79
            r0[r3] = r2
            int r3 = r7.size()
            if (r3 != r2) goto L_0x0132
            r3 = 80
            r0[r3] = r2
            java.lang.Object r3 = r7.get(r5)
            com.taobao.weex.el.parse.Token r3 = (com.taobao.weex.el.parse.Token) r3
            r5 = 81
            r0[r5] = r2
            goto L_0x013c
        L_0x0132:
            com.taobao.weex.el.parse.Block r3 = new com.taobao.weex.el.parse.Block
            r5 = 6
            r3.<init>(r7, r5)
            r5 = 82
            r0[r5] = r2
        L_0x013c:
            com.taobao.weex.el.parse.Operator r5 = new com.taobao.weex.el.parse.Operator
            java.lang.String r6 = "."
            r5.<init>(r6, r4)
            r5.first = r1
            r5.second = r3
            r1 = 83
            r0[r1] = r2
            com.taobao.weex.el.parse.ArrayStack<com.taobao.weex.el.parse.Token> r1 = r11.stacks
            r1.push(r5)
            r1 = 84
            r0[r1] = r2
            return
        L_0x0155:
            r8 = 71
            r0[r8] = r2
            com.taobao.weex.el.parse.ArrayStack<com.taobao.weex.el.parse.Token> r8 = r11.stacks
            java.lang.Object r8 = r8.get(r3)
            r7.add(r8)
            int r3 = r3 + 1
            r8 = 72
            r0[r8] = r2
            goto L_0x00c9
        L_0x016a:
            r1 = 58
            r0[r1] = r2
        L_0x016e:
            com.taobao.weex.el.parse.ArrayStack<com.taobao.weex.el.parse.Symbol> r1 = r11.operators
            int r1 = r1.size()
            if (r1 <= r3) goto L_0x0184
            r1 = 59
            r0[r1] = r2
            com.taobao.weex.el.parse.ArrayStack<com.taobao.weex.el.parse.Symbol> r1 = r11.operators
            r1.pop()
            r1 = 60
            r0[r1] = r2
            goto L_0x016e
        L_0x0184:
            r1 = 61
            r0[r1] = r2
            return
        L_0x0189:
            r7 = 56
            r0[r7] = r2
            goto L_0x005e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.el.parse.Parser.scanArray():void");
    }

    /* access modifiers changed from: package-private */
    public void scanBracket() {
        boolean[] $jacocoInit = $jacocoInit();
        int size = this.stacks.size();
        $jacocoInit[85] = true;
        int size2 = this.operators.size();
        $jacocoInit[86] = true;
        if (this.code.charAt(this.position) != '{') {
            this.operators.push(new Symbol(Operators.BRACKET_START_STR, this.stacks.size()));
            this.position++;
            $jacocoInit[93] = true;
            while (true) {
                if (hasNextToken()) {
                    $jacocoInit[95] = true;
                    if (scanNextToken() == ')') {
                        $jacocoInit[97] = true;
                        break;
                    }
                    $jacocoInit[96] = true;
                } else {
                    $jacocoInit[94] = true;
                    break;
                }
            }
        } else {
            $jacocoInit[87] = true;
            this.operators.push(new Symbol(Operators.BLOCK_START_STR, this.stacks.size()));
            this.position++;
            $jacocoInit[88] = true;
            while (true) {
                if (hasNextToken()) {
                    $jacocoInit[90] = true;
                    if (scanNextToken() == '}') {
                        $jacocoInit[92] = true;
                        break;
                    }
                    $jacocoInit[91] = true;
                } else {
                    $jacocoInit[89] = true;
                    break;
                }
            }
        }
        if (this.stacks.size() > size) {
            $jacocoInit[98] = true;
            while (this.operators.size() > size2) {
                $jacocoInit[103] = true;
                Symbol pop = this.operators.pop();
                $jacocoInit[104] = true;
                if (this.stacks.size() <= size) {
                    $jacocoInit[105] = true;
                } else {
                    $jacocoInit[106] = true;
                    doOperator(pop);
                    $jacocoInit[107] = true;
                }
                $jacocoInit[108] = true;
            }
            ArrayList arrayList = new ArrayList(4);
            $jacocoInit[109] = true;
            $jacocoInit[110] = true;
            int i = size;
            while (i < this.stacks.size()) {
                $jacocoInit[112] = true;
                arrayList.add(this.stacks.get(i));
                i++;
                $jacocoInit[113] = true;
            }
            $jacocoInit[111] = true;
            while (this.stacks.size() > size) {
                $jacocoInit[114] = true;
                this.stacks.pop();
                $jacocoInit[115] = true;
            }
            if (arrayList.size() == 1) {
                $jacocoInit[116] = true;
                this.stacks.push(arrayList.get(0));
                $jacocoInit[117] = true;
            } else {
                Block block = new Block(arrayList, 6);
                $jacocoInit[118] = true;
                this.stacks.push(block);
                $jacocoInit[119] = true;
            }
            $jacocoInit[120] = true;
            return;
        }
        $jacocoInit[99] = true;
        while (this.operators.size() > size2) {
            $jacocoInit[100] = true;
            this.operators.pop();
            $jacocoInit[101] = true;
        }
        $jacocoInit[102] = true;
    }

    /* access modifiers changed from: package-private */
    public void scanOperator() {
        boolean[] $jacocoInit = $jacocoInit();
        int i = this.position;
        $jacocoInit[121] = true;
        int min = Math.min(this.position + 3, this.code.length());
        $jacocoInit[122] = true;
        String substring = this.code.substring(this.position, min);
        $jacocoInit[123] = true;
        if (substring.length() < 3) {
            $jacocoInit[124] = true;
        } else {
            $jacocoInit[125] = true;
            if (Operators.OPERATORS_PRIORITY.containsKey(substring)) {
                $jacocoInit[126] = true;
            } else {
                $jacocoInit[127] = true;
                substring = substring.substring(0, 2);
                $jacocoInit[128] = true;
            }
        }
        if (substring.length() < 2) {
            $jacocoInit[129] = true;
        } else {
            $jacocoInit[130] = true;
            if (Operators.OPERATORS_PRIORITY.containsKey(substring)) {
                $jacocoInit[131] = true;
            } else {
                $jacocoInit[132] = true;
                substring = substring.substring(0, 1);
                $jacocoInit[133] = true;
            }
        }
        if (!Operators.OPERATORS_PRIORITY.containsKey(substring)) {
            $jacocoInit[134] = true;
            int min2 = Math.min(i + 1, this.code.length());
            $jacocoInit[135] = true;
            WXLogUtils.e("weex", (Throwable) new IllegalArgumentException(this.code.substring(0, min2) + " illegal code operator" + substring));
            $jacocoInit[136] = true;
            this.position = this.position + substring.length();
            $jacocoInit[137] = true;
            return;
        }
        if (this.operators.isEmpty()) {
            $jacocoInit[138] = true;
        } else if (this.operators.peek() == null) {
            $jacocoInit[139] = true;
        } else {
            $jacocoInit[140] = true;
            String str = this.operators.peek().op;
            $jacocoInit[141] = true;
            if (Operators.OPERATORS_PRIORITY.get(str).intValue() < Operators.OPERATORS_PRIORITY.get(substring).intValue()) {
                $jacocoInit[142] = true;
            } else {
                $jacocoInit[143] = true;
                $jacocoInit[144] = true;
                doOperator(this.operators.pop());
                $jacocoInit[145] = true;
            }
        }
        if (Operators.isOpEnd(substring)) {
            $jacocoInit[146] = true;
        } else {
            $jacocoInit[147] = true;
            this.operators.push(new Symbol(substring, this.stacks.size()));
            $jacocoInit[148] = true;
        }
        this.position += substring.length();
        $jacocoInit[149] = true;
    }

    /* access modifiers changed from: package-private */
    public void doOperator(Symbol symbol) {
        boolean[] $jacocoInit = $jacocoInit();
        String str = symbol.op;
        $jacocoInit[150] = true;
        if (Operators.BRACKET_START_STR.equals(symbol.op)) {
            $jacocoInit[151] = true;
        } else {
            String str2 = symbol.op;
            $jacocoInit[152] = true;
            if (Operators.BLOCK_START_STR.equals(str2)) {
                $jacocoInit[153] = true;
            } else {
                String str3 = symbol.op;
                $jacocoInit[154] = true;
                if (Operators.ARRAY_START_STR.equals(str3)) {
                    $jacocoInit[155] = true;
                } else {
                    String str4 = symbol.op;
                    $jacocoInit[156] = true;
                    if (Operators.DOLLAR_STR.equals(str4)) {
                        $jacocoInit[157] = true;
                    } else if (Operators.BLOCK_START_STR.equals(symbol.op)) {
                        $jacocoInit[159] = true;
                        return;
                    } else {
                        int i = symbol.pos;
                        $jacocoInit[160] = true;
                        int max = Math.max(symbol.pos - 1, 0);
                        $jacocoInit[161] = true;
                        if (this.operators.isEmpty()) {
                            $jacocoInit[162] = true;
                        } else {
                            $jacocoInit[163] = true;
                            max = Math.max(max, this.operators.peek().pos);
                            $jacocoInit[164] = true;
                        }
                        Operator operator = new Operator(str, 5);
                        $jacocoInit[165] = true;
                        if ("!".equals(str)) {
                            $jacocoInit[166] = true;
                            if (this.stacks.size() > i) {
                                $jacocoInit[167] = true;
                                operator.self = this.stacks.remove(i);
                                $jacocoInit[168] = true;
                                this.stacks.add(i, operator);
                                $jacocoInit[169] = true;
                                return;
                            }
                            $jacocoInit[170] = true;
                            return;
                        } else if (this.stacks.size() > i) {
                            $jacocoInit[171] = true;
                            operator.second = this.stacks.remove(i);
                            if (this.stacks.size() > max) {
                                $jacocoInit[173] = true;
                                operator.first = this.stacks.remove(max);
                                $jacocoInit[174] = true;
                            } else if (operator.second != null) {
                                $jacocoInit[175] = true;
                            } else {
                                $jacocoInit[176] = true;
                                return;
                            }
                            this.stacks.add(max, operator);
                            $jacocoInit[177] = true;
                            return;
                        } else {
                            $jacocoInit[172] = true;
                            return;
                        }
                    }
                }
            }
        }
        $jacocoInit[158] = true;
    }

    /* access modifiers changed from: package-private */
    public void scanIf() {
        boolean[] $jacocoInit = $jacocoInit();
        Operator operator = new Operator("?", 5);
        $jacocoInit[178] = true;
        int i = 0;
        doStackOperators(0);
        $jacocoInit[179] = true;
        if (this.operators.size() <= 0) {
            $jacocoInit[180] = true;
        } else {
            $jacocoInit[181] = true;
            i = Math.max(this.operators.peek().pos, 0);
            $jacocoInit[182] = true;
        }
        if (this.stacks.size() <= i) {
            $jacocoInit[183] = true;
        } else {
            $jacocoInit[184] = true;
            operator.self = this.stacks.pop();
            $jacocoInit[185] = true;
        }
        int size = this.stacks.size();
        $jacocoInit[186] = true;
        int size2 = this.operators.size();
        this.position++;
        $jacocoInit[187] = true;
        while (true) {
            if (hasNextToken()) {
                $jacocoInit[189] = true;
                if (scanNextToken() == ':') {
                    $jacocoInit[191] = true;
                    break;
                }
                $jacocoInit[190] = true;
            } else {
                $jacocoInit[188] = true;
                break;
            }
        }
        while (this.operators.size() > size2) {
            $jacocoInit[193] = true;
            $jacocoInit[194] = true;
            doOperator(this.operators.pop());
            $jacocoInit[195] = true;
        }
        $jacocoInit[192] = true;
        while (this.stacks.size() > size) {
            $jacocoInit[196] = true;
            operator.first = this.stacks.pop();
            $jacocoInit[197] = true;
        }
        int size3 = this.operators.size();
        $jacocoInit[198] = true;
        while (true) {
            if (hasNextToken()) {
                $jacocoInit[200] = true;
                scanNextToken();
                $jacocoInit[201] = true;
                if (!hasNextToken()) {
                    $jacocoInit[202] = true;
                } else {
                    $jacocoInit[203] = true;
                    scanNextToken();
                    $jacocoInit[204] = true;
                }
                if (this.operators.size() <= size3) {
                    $jacocoInit[206] = true;
                    break;
                }
                $jacocoInit[205] = true;
            } else {
                $jacocoInit[199] = true;
                break;
            }
        }
        doStackOperators(size3);
        $jacocoInit[207] = true;
        while (this.stacks.size() > size) {
            $jacocoInit[208] = true;
            operator.second = this.stacks.pop();
            $jacocoInit[209] = true;
        }
        this.stacks.push(operator);
        $jacocoInit[210] = true;
    }

    private final void doStackOperators(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        while (this.operators.size() > i) {
            $jacocoInit[211] = true;
            $jacocoInit[212] = true;
            doOperator(this.operators.pop());
            $jacocoInit[213] = true;
        }
        $jacocoInit[214] = true;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x004c  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0088  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0047 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void scanNumber() {
        /*
            r9 = this;
            boolean[] r0 = $jacocoInit()
            int r1 = r9.position
            r2 = 1
            r3 = 215(0xd7, float:3.01E-43)
            r0[r3] = r2
            java.lang.String r3 = r9.code
            int r4 = r9.position
            char r3 = r3.charAt(r4)
            r4 = 0
            r5 = 46
            r6 = 101(0x65, float:1.42E-43)
            if (r3 != r6) goto L_0x001f
            r3 = 216(0xd8, float:3.03E-43)
            r0[r3] = r2
            goto L_0x0033
        L_0x001f:
            java.lang.String r3 = r9.code
            int r7 = r9.position
            char r3 = r3.charAt(r7)
            if (r3 == r5) goto L_0x002f
            r3 = 217(0xd9, float:3.04E-43)
            r0[r3] = r2
            r3 = 1
            goto L_0x0038
        L_0x002f:
            r3 = 218(0xda, float:3.05E-43)
            r0[r3] = r2
        L_0x0033:
            r3 = 219(0xdb, float:3.07E-43)
            r0[r3] = r2
            r3 = 0
        L_0x0038:
            int r7 = r9.position
            int r7 = r7 + r2
            r9.position = r7
            r7 = 220(0xdc, float:3.08E-43)
            r0[r7] = r2
        L_0x0041:
            boolean r7 = r9.hasNext()
            if (r7 != 0) goto L_0x004c
            r4 = 221(0xdd, float:3.1E-43)
            r0[r4] = r2
            goto L_0x0074
        L_0x004c:
            r7 = 222(0xde, float:3.11E-43)
            r0[r7] = r2
            java.lang.String r7 = r9.code
            int r8 = r9.position
            char r7 = r7.charAt(r8)
            r8 = 223(0xdf, float:3.12E-43)
            r0[r8] = r2
            boolean r8 = java.lang.Character.isDigit(r7)
            if (r8 == 0) goto L_0x0067
            r8 = 224(0xe0, float:3.14E-43)
            r0[r8] = r2
            goto L_0x00b5
        L_0x0067:
            if (r7 != r5) goto L_0x006e
            r8 = 225(0xe1, float:3.15E-43)
            r0[r8] = r2
            goto L_0x00b5
        L_0x006e:
            if (r7 == r6) goto L_0x00b1
            r4 = 226(0xe2, float:3.17E-43)
            r0[r4] = r2
        L_0x0074:
            java.lang.String r4 = r9.code
            int r5 = r9.position
            java.lang.String r1 = r4.substring(r1, r5)
            r4 = 233(0xe9, float:3.27E-43)
            r0[r4] = r2
            java.lang.String r4 = "."
            boolean r4 = r4.equals(r1)
            if (r4 == 0) goto L_0x008d
            r1 = 234(0xea, float:3.28E-43)
            r0[r1] = r2
            return
        L_0x008d:
            if (r3 == 0) goto L_0x009d
            r3 = 235(0xeb, float:3.3E-43)
            r0[r3] = r2
            com.taobao.weex.el.parse.Token r3 = new com.taobao.weex.el.parse.Token
            r3.<init>(r1, r2)
            r1 = 236(0xec, float:3.31E-43)
            r0[r1] = r2
            goto L_0x00a7
        L_0x009d:
            com.taobao.weex.el.parse.Token r3 = new com.taobao.weex.el.parse.Token
            r4 = 2
            r3.<init>(r1, r4)
            r1 = 237(0xed, float:3.32E-43)
            r0[r1] = r2
        L_0x00a7:
            com.taobao.weex.el.parse.ArrayStack<com.taobao.weex.el.parse.Token> r1 = r9.stacks
            r1.push(r3)
            r1 = 238(0xee, float:3.34E-43)
            r0[r1] = r2
            return
        L_0x00b1:
            r8 = 227(0xe3, float:3.18E-43)
            r0[r8] = r2
        L_0x00b5:
            if (r7 != r6) goto L_0x00bc
            r3 = 228(0xe4, float:3.2E-43)
            r0[r3] = r2
            goto L_0x00c7
        L_0x00bc:
            if (r7 == r5) goto L_0x00c3
            r7 = 229(0xe5, float:3.21E-43)
            r0[r7] = r2
            goto L_0x00cc
        L_0x00c3:
            r3 = 230(0xe6, float:3.22E-43)
            r0[r3] = r2
        L_0x00c7:
            r3 = 231(0xe7, float:3.24E-43)
            r0[r3] = r2
            r3 = 0
        L_0x00cc:
            int r7 = r9.position
            int r7 = r7 + r2
            r9.position = r7
            r7 = 232(0xe8, float:3.25E-43)
            r0[r7] = r2
            goto L_0x0041
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.el.parse.Parser.scanNumber():void");
    }

    /* access modifiers changed from: package-private */
    public final void scanString() {
        boolean[] $jacocoInit = $jacocoInit();
        int i = this.position;
        $jacocoInit[239] = true;
        ArrayStack arrayStack = new ArrayStack();
        $jacocoInit[240] = true;
        char charAt = this.code.charAt(i);
        $jacocoInit[241] = true;
        arrayStack.push(Character.valueOf(charAt));
        $jacocoInit[242] = true;
        StringBuilder sb = new StringBuilder();
        $jacocoInit[243] = true;
        this.position = i + 1;
        $jacocoInit[244] = true;
        while (true) {
            if (this.position >= this.code.length()) {
                $jacocoInit[245] = true;
                break;
            }
            $jacocoInit[246] = true;
            char charAt2 = this.code.charAt(this.position);
            if (charAt2 == charAt) {
                $jacocoInit[247] = true;
                if (this.code.charAt(this.position - 1) != '\\') {
                    $jacocoInit[248] = true;
                    arrayStack.pop();
                    $jacocoInit[249] = true;
                    if (arrayStack.size() == 0) {
                        this.position++;
                        $jacocoInit[251] = true;
                        break;
                    }
                    $jacocoInit[250] = true;
                } else {
                    sb.deleteCharAt(sb.length() - 1);
                    $jacocoInit[252] = true;
                    sb.append(charAt2);
                    $jacocoInit[253] = true;
                }
            } else {
                sb.append(charAt2);
                $jacocoInit[254] = true;
            }
            this.position++;
            $jacocoInit[255] = true;
        }
        String sb2 = sb.toString();
        $jacocoInit[256] = true;
        Token token = new Token(sb2, 3);
        $jacocoInit[257] = true;
        this.stacks.push(token);
        $jacocoInit[258] = true;
    }

    /* access modifiers changed from: package-private */
    public final void scanIdentifier() {
        boolean[] $jacocoInit = $jacocoInit();
        int i = this.position;
        this.position++;
        $jacocoInit[259] = true;
        while (true) {
            if (!hasNext()) {
                $jacocoInit[260] = true;
                break;
            }
            $jacocoInit[261] = true;
            char charAt = this.code.charAt(this.position);
            $jacocoInit[262] = true;
            if (!Character.isJavaIdentifierPart(charAt)) {
                $jacocoInit[263] = true;
                break;
            } else {
                this.position++;
                $jacocoInit[264] = true;
            }
        }
        String substring = this.code.substring(i, this.position);
        $jacocoInit[265] = true;
        if (!substring.startsWith(Operators.DOLLAR_STR)) {
            $jacocoInit[266] = true;
        } else {
            $jacocoInit[267] = true;
            if (substring.length() == Operators.DOLLAR_STR.length()) {
                $jacocoInit[268] = true;
                return;
            } else {
                substring = substring.substring(Operators.DOLLAR_STR.length());
                $jacocoInit[269] = true;
            }
        }
        int i2 = 0;
        $jacocoInit[270] = true;
        if (!Operators.KEYWORDS.containsKey(substring)) {
            $jacocoInit[271] = true;
        } else {
            $jacocoInit[272] = true;
            if (this.operators.isEmpty()) {
                $jacocoInit[273] = true;
            } else if (Operators.isDot(this.operators.peek().op)) {
                $jacocoInit[274] = true;
            } else {
                $jacocoInit[275] = true;
            }
            i2 = 4;
            $jacocoInit[276] = true;
        }
        Token token = new Token(substring, i2);
        $jacocoInit[277] = true;
        this.stacks.push(token);
        $jacocoInit[278] = true;
    }

    /* access modifiers changed from: package-private */
    public final boolean hasNext() {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.position < this.code.length()) {
            $jacocoInit[279] = true;
            z = true;
        } else {
            z = false;
            $jacocoInit[280] = true;
        }
        $jacocoInit[281] = true;
        return z;
    }

    /* access modifiers changed from: package-private */
    public final boolean hasNextToken() {
        boolean[] $jacocoInit = $jacocoInit();
        while (hasNext()) {
            $jacocoInit[282] = true;
            if (this.code.charAt(this.position) == ' ') {
                this.position++;
                $jacocoInit[283] = true;
            } else {
                $jacocoInit[284] = true;
                return true;
            }
        }
        $jacocoInit[285] = true;
        return false;
    }

    /* access modifiers changed from: package-private */
    public final char nextToken() {
        boolean[] $jacocoInit = $jacocoInit();
        char charAt = this.code.charAt(this.position);
        $jacocoInit[286] = true;
        while (true) {
            if (charAt != ' ') {
                $jacocoInit[287] = true;
                break;
            }
            this.position++;
            $jacocoInit[288] = true;
            if (this.code.length() <= this.position) {
                $jacocoInit[289] = true;
                break;
            }
            charAt = this.code.charAt(this.position);
            $jacocoInit[290] = true;
        }
        $jacocoInit[291] = true;
        return charAt;
    }
}
