package com.taobao.weex.el.parse;

import org.jacoco.agent.rt.internal_8ff85ea.Offline;

class Operator extends Token {
    private static transient /* synthetic */ boolean[] $jacocoData;
    public Token first;
    public Token second;
    public Token self;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(283327569667773338L, "com/taobao/weex/el/parse/Operator", 83);
        $jacocoData = a2;
        return a2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Operator(String str, int i) {
        super(str, i);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object execute(java.lang.Object r21) {
        /*
            r20 = this;
            r0 = r20
            r1 = r21
            boolean[] r2 = $jacocoInit()
            java.lang.String r3 = r20.getToken()
            r4 = 1
            r2[r4] = r4
            int r5 = r3.hashCode()
            r6 = 5
            r7 = 6
            r8 = 10
            r9 = 12
            r10 = 4
            r11 = 3
            r12 = 15
            r13 = 14
            r14 = 16
            r15 = 18
            r16 = 7
            r17 = 17
            r18 = 2
            r19 = 0
            switch(r5) {
                case 33: goto L_0x019a;
                case 37: goto L_0x0186;
                case 42: goto L_0x0172;
                case 43: goto L_0x015e;
                case 45: goto L_0x014a;
                case 46: goto L_0x013a;
                case 47: goto L_0x0124;
                case 60: goto L_0x010e;
                case 62: goto L_0x00f8;
                case 63: goto L_0x00e7;
                case 91: goto L_0x00d6;
                case 1084: goto L_0x00c3;
                case 1216: goto L_0x00ad;
                case 1921: goto L_0x0097;
                case 1952: goto L_0x0084;
                case 1983: goto L_0x006e;
                case 3968: goto L_0x0058;
                case 33665: goto L_0x0045;
                case 60573: goto L_0x0032;
                default: goto L_0x002e;
            }
        L_0x002e:
            r2[r18] = r4
            goto L_0x01a9
        L_0x0032:
            java.lang.String r5 = "==="
            boolean r5 = r3.equals(r5)
            if (r5 != 0) goto L_0x003e
            r2[r16] = r4
            goto L_0x01a9
        L_0x003e:
            r5 = 8
            r2[r5] = r4
            r5 = 2
            goto L_0x01aa
        L_0x0045:
            java.lang.String r5 = "!=="
            boolean r5 = r3.equals(r5)
            if (r5 != 0) goto L_0x0053
            r5 = 11
            r2[r5] = r4
            goto L_0x01a9
        L_0x0053:
            r2[r9] = r4
            r5 = 4
            goto L_0x01aa
        L_0x0058:
            java.lang.String r5 = "||"
            boolean r5 = r3.equals(r5)
            if (r5 != 0) goto L_0x0066
            r5 = 21
            r2[r5] = r4
            goto L_0x01a9
        L_0x0066:
            r5 = 9
            r6 = 22
            r2[r6] = r4
            goto L_0x01aa
        L_0x006e:
            java.lang.String r5 = ">="
            boolean r5 = r3.equals(r5)
            if (r5 != 0) goto L_0x007c
            r5 = 25
            r2[r5] = r4
            goto L_0x01a9
        L_0x007c:
            r5 = 11
            r6 = 26
            r2[r6] = r4
            goto L_0x01aa
        L_0x0084:
            java.lang.String r5 = "=="
            boolean r5 = r3.equals(r5)
            if (r5 != 0) goto L_0x0092
            r5 = 9
            r2[r5] = r4
            goto L_0x01a9
        L_0x0092:
            r2[r8] = r4
            r5 = 3
            goto L_0x01aa
        L_0x0097:
            java.lang.String r5 = "<="
            boolean r5 = r3.equals(r5)
            if (r5 != 0) goto L_0x00a5
            r5 = 29
            r2[r5] = r4
            goto L_0x01a9
        L_0x00a5:
            r5 = 13
            r6 = 30
            r2[r6] = r4
            goto L_0x01aa
        L_0x00ad:
            java.lang.String r5 = "&&"
            boolean r5 = r3.equals(r5)
            if (r5 != 0) goto L_0x00bb
            r5 = 19
            r2[r5] = r4
            goto L_0x01a9
        L_0x00bb:
            r5 = 8
            r6 = 20
            r2[r6] = r4
            goto L_0x01aa
        L_0x00c3:
            java.lang.String r5 = "!="
            boolean r5 = r3.equals(r5)
            if (r5 != 0) goto L_0x00d1
            r5 = 13
            r2[r5] = r4
            goto L_0x01a9
        L_0x00d1:
            r2[r13] = r4
            r5 = 5
            goto L_0x01aa
        L_0x00d6:
            java.lang.String r5 = "["
            boolean r5 = r3.equals(r5)
            if (r5 != 0) goto L_0x00e2
            r2[r6] = r4
            goto L_0x01a9
        L_0x00e2:
            r2[r7] = r4
            r5 = 1
            goto L_0x01aa
        L_0x00e7:
            java.lang.String r5 = "?"
            boolean r5 = r3.equals(r5)
            if (r5 != 0) goto L_0x00f3
            r2[r12] = r4
            goto L_0x01a9
        L_0x00f3:
            r2[r14] = r4
            r5 = 6
            goto L_0x01aa
        L_0x00f8:
            java.lang.String r5 = ">"
            boolean r5 = r3.equals(r5)
            if (r5 != 0) goto L_0x0106
            r5 = 23
            r2[r5] = r4
            goto L_0x01a9
        L_0x0106:
            r5 = 24
            r2[r5] = r4
            r5 = 10
            goto L_0x01aa
        L_0x010e:
            java.lang.String r5 = "<"
            boolean r5 = r3.equals(r5)
            if (r5 != 0) goto L_0x011c
            r5 = 27
            r2[r5] = r4
            goto L_0x01a9
        L_0x011c:
            r5 = 28
            r2[r5] = r4
            r5 = 12
            goto L_0x01aa
        L_0x0124:
            java.lang.String r5 = "/"
            boolean r5 = r3.equals(r5)
            if (r5 != 0) goto L_0x0132
            r5 = 37
            r2[r5] = r4
            goto L_0x01a9
        L_0x0132:
            r5 = 38
            r2[r5] = r4
            r5 = 17
            goto L_0x01aa
        L_0x013a:
            java.lang.String r5 = "."
            boolean r5 = r3.equals(r5)
            if (r5 != 0) goto L_0x0146
            r2[r11] = r4
            goto L_0x01a9
        L_0x0146:
            r2[r10] = r4
            r5 = 0
            goto L_0x01aa
        L_0x014a:
            java.lang.String r5 = "-"
            boolean r5 = r3.equals(r5)
            if (r5 != 0) goto L_0x0157
            r5 = 33
            r2[r5] = r4
            goto L_0x01a9
        L_0x0157:
            r5 = 34
            r2[r5] = r4
            r5 = 15
            goto L_0x01aa
        L_0x015e:
            java.lang.String r5 = "+"
            boolean r5 = r3.equals(r5)
            if (r5 != 0) goto L_0x016b
            r5 = 31
            r2[r5] = r4
            goto L_0x01a9
        L_0x016b:
            r5 = 32
            r2[r5] = r4
            r5 = 14
            goto L_0x01aa
        L_0x0172:
            java.lang.String r5 = "*"
            boolean r5 = r3.equals(r5)
            if (r5 != 0) goto L_0x017f
            r5 = 35
            r2[r5] = r4
            goto L_0x01a9
        L_0x017f:
            r5 = 36
            r2[r5] = r4
            r5 = 16
            goto L_0x01aa
        L_0x0186:
            java.lang.String r5 = "%"
            boolean r5 = r3.equals(r5)
            if (r5 != 0) goto L_0x0193
            r5 = 39
            r2[r5] = r4
            goto L_0x01a9
        L_0x0193:
            r5 = 40
            r2[r5] = r4
            r5 = 18
            goto L_0x01aa
        L_0x019a:
            java.lang.String r5 = "!"
            boolean r5 = r3.equals(r5)
            if (r5 != 0) goto L_0x01a5
            r2[r17] = r4
            goto L_0x01a9
        L_0x01a5:
            r2[r15] = r4
            r5 = 7
            goto L_0x01aa
        L_0x01a9:
            r5 = -1
        L_0x01aa:
            switch(r5) {
                case 0: goto L_0x034d;
                case 1: goto L_0x034d;
                case 2: goto L_0x033c;
                case 3: goto L_0x033c;
                case 4: goto L_0x031e;
                case 5: goto L_0x031e;
                case 6: goto L_0x030f;
                case 7: goto L_0x02f3;
                case 8: goto L_0x02c6;
                case 9: goto L_0x0299;
                case 10: goto L_0x0275;
                case 11: goto L_0x0251;
                case 12: goto L_0x022d;
                case 13: goto L_0x0209;
                case 14: goto L_0x01fc;
                case 15: goto L_0x01ef;
                case 16: goto L_0x01e2;
                case 17: goto L_0x01d5;
                case 18: goto L_0x01c8;
                default: goto L_0x01ad;
            }
        L_0x01ad:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r3)
            java.lang.String r3 = " operator is not supported"
            r5.append(r3)
            java.lang.String r3 = r5.toString()
            r1.<init>(r3)
            r3 = 77
            r2[r3] = r4
            throw r1
        L_0x01c8:
            com.taobao.weex.el.parse.Token r3 = r0.first
            com.taobao.weex.el.parse.Token r5 = r0.second
            java.lang.Object r1 = com.taobao.weex.el.parse.Operators.mod(r3, r5, r1)
            r3 = 76
            r2[r3] = r4
            return r1
        L_0x01d5:
            com.taobao.weex.el.parse.Token r3 = r0.first
            com.taobao.weex.el.parse.Token r5 = r0.second
            java.lang.Object r1 = com.taobao.weex.el.parse.Operators.div(r3, r5, r1)
            r3 = 75
            r2[r3] = r4
            return r1
        L_0x01e2:
            com.taobao.weex.el.parse.Token r3 = r0.first
            com.taobao.weex.el.parse.Token r5 = r0.second
            java.lang.Object r1 = com.taobao.weex.el.parse.Operators.mul(r3, r5, r1)
            r3 = 74
            r2[r3] = r4
            return r1
        L_0x01ef:
            com.taobao.weex.el.parse.Token r3 = r0.first
            com.taobao.weex.el.parse.Token r5 = r0.second
            java.lang.Object r1 = com.taobao.weex.el.parse.Operators.sub(r3, r5, r1)
            r3 = 73
            r2[r3] = r4
            return r1
        L_0x01fc:
            com.taobao.weex.el.parse.Token r3 = r0.first
            com.taobao.weex.el.parse.Token r5 = r0.second
            java.lang.Object r1 = com.taobao.weex.el.parse.Operators.plus(r3, r5, r1)
            r3 = 72
            r2[r3] = r4
            return r1
        L_0x0209:
            com.taobao.weex.el.parse.Token r3 = r0.first
            double r5 = com.taobao.weex.el.parse.Operators.tokenNumber(r3, r1)
            com.taobao.weex.el.parse.Token r3 = r0.second
            double r7 = com.taobao.weex.el.parse.Operators.tokenNumber(r3, r1)
            int r1 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r1 > 0) goto L_0x0220
            r1 = 69
            r2[r1] = r4
            r19 = 1
            goto L_0x0224
        L_0x0220:
            r1 = 70
            r2[r1] = r4
        L_0x0224:
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r19)
            r3 = 71
            r2[r3] = r4
            return r1
        L_0x022d:
            com.taobao.weex.el.parse.Token r3 = r0.first
            double r5 = com.taobao.weex.el.parse.Operators.tokenNumber(r3, r1)
            com.taobao.weex.el.parse.Token r3 = r0.second
            double r7 = com.taobao.weex.el.parse.Operators.tokenNumber(r3, r1)
            int r1 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r1 >= 0) goto L_0x0244
            r1 = 66
            r2[r1] = r4
            r19 = 1
            goto L_0x0248
        L_0x0244:
            r1 = 67
            r2[r1] = r4
        L_0x0248:
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r19)
            r3 = 68
            r2[r3] = r4
            return r1
        L_0x0251:
            com.taobao.weex.el.parse.Token r3 = r0.first
            double r5 = com.taobao.weex.el.parse.Operators.tokenNumber(r3, r1)
            com.taobao.weex.el.parse.Token r3 = r0.second
            double r7 = com.taobao.weex.el.parse.Operators.tokenNumber(r3, r1)
            int r1 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r1 < 0) goto L_0x0268
            r1 = 63
            r2[r1] = r4
            r19 = 1
            goto L_0x026c
        L_0x0268:
            r1 = 64
            r2[r1] = r4
        L_0x026c:
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r19)
            r3 = 65
            r2[r3] = r4
            return r1
        L_0x0275:
            com.taobao.weex.el.parse.Token r3 = r0.first
            double r5 = com.taobao.weex.el.parse.Operators.tokenNumber(r3, r1)
            com.taobao.weex.el.parse.Token r3 = r0.second
            double r7 = com.taobao.weex.el.parse.Operators.tokenNumber(r3, r1)
            int r1 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r1 <= 0) goto L_0x028c
            r1 = 60
            r2[r1] = r4
            r19 = 1
            goto L_0x0290
        L_0x028c:
            r1 = 61
            r2[r1] = r4
        L_0x0290:
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r19)
            r3 = 62
            r2[r3] = r4
            return r1
        L_0x0299:
            com.taobao.weex.el.parse.Token r3 = r0.first
            boolean r3 = com.taobao.weex.el.parse.Operators.tokenTrue(r3, r1)
            if (r3 == 0) goto L_0x02a6
            r1 = 55
            r2[r1] = r4
            goto L_0x02b2
        L_0x02a6:
            com.taobao.weex.el.parse.Token r3 = r0.second
            boolean r1 = com.taobao.weex.el.parse.Operators.tokenTrue(r3, r1)
            if (r1 == 0) goto L_0x02b9
            r1 = 56
            r2[r1] = r4
        L_0x02b2:
            r1 = 57
            r2[r1] = r4
            r19 = 1
            goto L_0x02bd
        L_0x02b9:
            r1 = 58
            r2[r1] = r4
        L_0x02bd:
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r19)
            r3 = 59
            r2[r3] = r4
            return r1
        L_0x02c6:
            com.taobao.weex.el.parse.Token r3 = r0.first
            boolean r3 = com.taobao.weex.el.parse.Operators.tokenTrue(r3, r1)
            if (r3 != 0) goto L_0x02d3
            r1 = 50
            r2[r1] = r4
            goto L_0x02df
        L_0x02d3:
            com.taobao.weex.el.parse.Token r3 = r0.second
            boolean r1 = com.taobao.weex.el.parse.Operators.tokenTrue(r3, r1)
            if (r1 != 0) goto L_0x02e4
            r1 = 51
            r2[r1] = r4
        L_0x02df:
            r1 = 53
            r2[r1] = r4
            goto L_0x02ea
        L_0x02e4:
            r1 = 52
            r2[r1] = r4
            r19 = 1
        L_0x02ea:
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r19)
            r3 = 54
            r2[r3] = r4
            return r1
        L_0x02f3:
            com.taobao.weex.el.parse.Token r3 = r0.self
            boolean r1 = com.taobao.weex.el.parse.Operators.tokenTrue(r3, r1)
            if (r1 != 0) goto L_0x0302
            r1 = 47
            r2[r1] = r4
            r19 = 1
            goto L_0x0306
        L_0x0302:
            r1 = 48
            r2[r1] = r4
        L_0x0306:
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r19)
            r3 = 49
            r2[r3] = r4
            return r1
        L_0x030f:
            com.taobao.weex.el.parse.Token r3 = r0.self
            com.taobao.weex.el.parse.Token r5 = r0.first
            com.taobao.weex.el.parse.Token r6 = r0.second
            java.lang.Object r1 = com.taobao.weex.el.parse.Operators.condition(r3, r5, r6, r1)
            r3 = 46
            r2[r3] = r4
            return r1
        L_0x031e:
            com.taobao.weex.el.parse.Token r3 = r0.first
            com.taobao.weex.el.parse.Token r5 = r0.second
            boolean r1 = com.taobao.weex.el.parse.Operators.isEquals(r3, r5, r1)
            if (r1 != 0) goto L_0x032f
            r1 = 43
            r2[r1] = r4
            r19 = 1
            goto L_0x0333
        L_0x032f:
            r1 = 44
            r2[r1] = r4
        L_0x0333:
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r19)
            r3 = 45
            r2[r3] = r4
            return r1
        L_0x033c:
            com.taobao.weex.el.parse.Token r3 = r0.first
            com.taobao.weex.el.parse.Token r5 = r0.second
            boolean r1 = com.taobao.weex.el.parse.Operators.isEquals(r3, r5, r1)
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)
            r3 = 42
            r2[r3] = r4
            return r1
        L_0x034d:
            com.taobao.weex.el.parse.Token r3 = r0.first
            com.taobao.weex.el.parse.Token r5 = r0.second
            java.lang.Object r1 = com.taobao.weex.el.parse.Operators.dot(r3, r5, r1)
            r3 = 41
            r2[r3] = r4
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.el.parse.Operator.execute(java.lang.Object):java.lang.Object");
    }

    public String toString() {
        boolean[] $jacocoInit = $jacocoInit();
        if ("!".equals(getToken())) {
            $jacocoInit[78] = true;
            String str = "{!" + this.self + "}";
            $jacocoInit[79] = true;
            return str;
        } else if (this.self == null) {
            $jacocoInit[80] = true;
            String str2 = Operators.BLOCK_START_STR + this.first + getToken() + this.second + "}";
            $jacocoInit[81] = true;
            return str2;
        } else {
            String str3 = Operators.BLOCK_START_STR + this.self + getToken() + this.first + ":" + this.second + "}";
            $jacocoInit[82] = true;
            return str3;
        }
    }
}
