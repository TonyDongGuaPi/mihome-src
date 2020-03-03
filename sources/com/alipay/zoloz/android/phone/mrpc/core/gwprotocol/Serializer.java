package com.alipay.zoloz.android.phone.mrpc.core.gwprotocol;

public interface Serializer {
    byte[] packet();

    void setExtParam(Object obj);
}
