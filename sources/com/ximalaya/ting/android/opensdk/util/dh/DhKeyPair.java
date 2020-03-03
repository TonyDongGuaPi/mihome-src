package com.ximalaya.ting.android.opensdk.util.dh;

import com.taobao.weex.el.parse.Operators;
import java.io.Serializable;
import java.math.BigInteger;

public class DhKeyPair implements Serializable {
    private BigInteger privateKey;
    private BigInteger publicKey;

    public BigInteger getPublicKey() {
        return this.publicKey;
    }

    public DhKeyPair setPublicKey(BigInteger bigInteger) {
        this.publicKey = bigInteger;
        return this;
    }

    public BigInteger getPrivateKey() {
        return this.privateKey;
    }

    public DhKeyPair setPrivateKey(BigInteger bigInteger) {
        this.privateKey = bigInteger;
        return this;
    }

    public String toString() {
        return "DhKeyPair{publicKey=" + this.publicKey + ", privateKey=" + this.privateKey + Operators.BLOCK_END;
    }
}
