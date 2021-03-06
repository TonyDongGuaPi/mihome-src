package com.alipay.zoloz.mobile.a.a;

import com.taobao.weex.el.parse.Operators;

public class b extends RuntimeException {
    private static final long serialVersionUID = -2875437994101380406L;
    private int mCode;
    private String mMsg;
    private String mOperationType;

    public b(Integer num, String str) {
        super(format(num, str));
        this.mCode = num.intValue();
        this.mMsg = str;
    }

    public b(Integer num, Throwable th) {
        super(th);
        this.mCode = num.intValue();
    }

    public b(Integer num, String str, Throwable th) {
        super(format(num, str), th);
        this.mCode = num.intValue();
        this.mMsg = str;
    }

    public b(String str) {
        super(str);
        this.mCode = 0;
        this.mMsg = str;
    }

    public String getOperationType() {
        return this.mOperationType;
    }

    public void setOperationType(String str) {
        this.mOperationType = str;
    }

    public int getCode() {
        return this.mCode;
    }

    public String getMsg() {
        return this.mMsg;
    }

    protected static String format(Integer num, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("RPCException: ");
        if (num != null) {
            sb.append(Operators.ARRAY_START_STR);
            sb.append(num);
            sb.append(Operators.ARRAY_END_STR);
        }
        sb.append(" : ");
        if (str != null) {
            sb.append(str);
        }
        return sb.toString();
    }
}
