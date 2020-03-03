package com.taobao.weex.el.parse;

import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class Token {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    public static final int TYPE_ARRAY = 7;
    public static final int TYPE_BLOCK = 6;
    public static final int TYPE_DOUBLE = 2;
    public static final int TYPE_IDENTIFIER = 0;
    public static final int TYPE_INT = 1;
    public static final int TYPE_KEYWORD = 4;
    public static final int TYPE_OPERATOR = 5;
    public static final int TYPE_STRING = 3;
    private String token;
    private int type;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(8575010163226693304L, "com/taobao/weex/el/parse/Token", 18);
        $jacocoData = a2;
        return a2;
    }

    public Token(String str, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        this.token = str;
        this.type = i;
        $jacocoInit[0] = true;
    }

    public Object execute(Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.type == 0) {
            $jacocoInit[1] = true;
            Object el = Operators.el(obj, this.token);
            $jacocoInit[2] = true;
            return el;
        } else if (this.type == 3) {
            String str = this.token;
            $jacocoInit[3] = true;
            return str;
        } else if (this.type == 1) {
            try {
                $jacocoInit[4] = true;
                Integer valueOf = Integer.valueOf(Integer.parseInt(this.token));
                $jacocoInit[5] = true;
                return valueOf;
            } catch (Exception unused) {
                $jacocoInit[6] = true;
                $jacocoInit[7] = true;
                return 0;
            }
        } else if (this.type == 2) {
            try {
                $jacocoInit[8] = true;
                Double valueOf2 = Double.valueOf(Double.parseDouble(this.token));
                $jacocoInit[9] = true;
                return valueOf2;
            } catch (Exception unused2) {
                $jacocoInit[10] = true;
                $jacocoInit[11] = true;
                return 0;
            }
        } else if (this.type == 4) {
            $jacocoInit[12] = true;
            Object obj2 = Operators.KEYWORDS.get(this.token);
            $jacocoInit[13] = true;
            return obj2;
        } else {
            IllegalArgumentException illegalArgumentException = new IllegalArgumentException("unhandled token type " + this.type);
            $jacocoInit[14] = true;
            throw illegalArgumentException;
        }
    }

    public String toString() {
        String str = Operators.BLOCK_START_STR + this.token + "," + this.type + Operators.BLOCK_END;
        $jacocoInit()[15] = true;
        return str;
    }

    public String getToken() {
        boolean[] $jacocoInit = $jacocoInit();
        String str = this.token;
        $jacocoInit[16] = true;
        return str;
    }

    public int getType() {
        boolean[] $jacocoInit = $jacocoInit();
        int i = this.type;
        $jacocoInit[17] = true;
        return i;
    }
}
