package com.xiaomi.accountsdk.utils;

import android.util.Base64;
import com.xiaomi.accountsdk.request.CipherException;
import com.xiaomi.accountsdk.utils.AESStringDef;
import java.security.SecureRandom;

public class AESWithIVCoder implements CryptCoder {
    private static final int IV_BASE64_FLAG = 11;
    private static final String TAG = "AESWithIVCoder";
    private static final String version = "1";
    private final int IV_LENGTH = 16;
    protected String aesKey;
    private AESCoder coder;
    /* access modifiers changed from: private */
    public byte[] ivRaw;
    private long threadId = 0;

    public AESWithIVCoder(String str) {
        this.aesKey = str;
        this.coder = new AESCoder(str) {
            /* access modifiers changed from: protected */
            public byte[] getInitalVector() {
                return AESWithIVCoder.this.ivRaw;
            }
        };
        initThreadInfo();
    }

    private void initThreadInfo() {
        this.threadId = Thread.currentThread().getId();
    }

    public String decrypt(String str) throws CipherException {
        checkThreadIdThrow();
        try {
            AESStringDef instance = AESStringDef.getInstance(str);
            if (instance.getVersion().equals("1")) {
                this.ivRaw = Base64.decode(instance.getIV(), 11);
                return this.coder.decrypt(instance.getData());
            }
            throw new CipherException("aes encrypt format version is wrong" + str);
        } catch (AESStringDef.InvalidAESDataException e) {
            throw new CipherException((Throwable) e);
        }
    }

    public String encrypt(String str) throws CipherException {
        checkThreadIdThrow();
        try {
            this.ivRaw = new byte[16];
            new SecureRandom().nextBytes(this.ivRaw);
            return AESStringDef.getInstance("1", Base64.encodeToString(this.ivRaw, 11), this.coder.encrypt(str)).toString();
        } catch (AESStringDef.InvalidAESDataException e) {
            throw new CipherException((Throwable) e);
        }
    }

    /* access modifiers changed from: protected */
    public void checkThreadIdThrow() {
        if (Thread.currentThread().getId() != this.threadId) {
            throw new IllegalAccessError("this method can not call concurrently");
        }
    }
}
