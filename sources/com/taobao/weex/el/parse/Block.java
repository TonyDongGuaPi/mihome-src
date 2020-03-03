package com.taobao.weex.el.parse;

import com.alibaba.fastjson.JSONArray;
import java.util.List;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

class Block extends Token {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private List<Token> tokens;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(463647501694952232L, "com/taobao/weex/el/parse/Block", 25);
        $jacocoData = a2;
        return a2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Block(List<Token> list, int i) {
        super("", i);
        boolean[] $jacocoInit = $jacocoInit();
        this.tokens = list;
        $jacocoInit[0] = true;
    }

    public Object execute(Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        int i = 0;
        if (getType() == 7) {
            $jacocoInit[1] = true;
            if (this.tokens == null) {
                $jacocoInit[2] = true;
            } else if (this.tokens.size() == 0) {
                $jacocoInit[3] = true;
            } else {
                JSONArray jSONArray = new JSONArray(this.tokens.size());
                $jacocoInit[5] = true;
                $jacocoInit[6] = true;
                while (i < this.tokens.size()) {
                    $jacocoInit[7] = true;
                    Token token = this.tokens.get(i);
                    if (token == null) {
                        $jacocoInit[8] = true;
                        jSONArray.add((Object) null);
                        $jacocoInit[9] = true;
                    } else {
                        jSONArray.add(token.execute(obj));
                        $jacocoInit[10] = true;
                    }
                    i++;
                    $jacocoInit[11] = true;
                }
                $jacocoInit[12] = true;
                return jSONArray;
            }
            JSONArray jSONArray2 = new JSONArray(4);
            $jacocoInit[4] = true;
            return jSONArray2;
        }
        if (this.tokens == null) {
            $jacocoInit[13] = true;
        } else if (this.tokens.size() == 0) {
            $jacocoInit[14] = true;
        } else {
            $jacocoInit[16] = true;
            Object execute = this.tokens.get(0).execute(obj);
            $jacocoInit[17] = true;
            return execute;
        }
        $jacocoInit[15] = true;
        return null;
    }

    public String toString() {
        boolean[] $jacocoInit = $jacocoInit();
        if (getType() == 7) {
            $jacocoInit[18] = true;
            String str = "" + this.tokens + "";
            $jacocoInit[19] = true;
            return str;
        }
        if (this.tokens == null) {
            $jacocoInit[20] = true;
        } else if (this.tokens.size() != 1) {
            $jacocoInit[21] = true;
        } else {
            $jacocoInit[22] = true;
            String str2 = Operators.BLOCK_START_STR + this.tokens.get(0) + Operators.BLOCK_END;
            $jacocoInit[23] = true;
            return str2;
        }
        String str3 = Operators.BLOCK_START_STR + this.tokens + Operators.BLOCK_END;
        $jacocoInit[24] = true;
        return str3;
    }
}
