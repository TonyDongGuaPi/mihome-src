package com.xiaomi.accountsdk.utils;

public class FidSigningUtil {

    public static final class FidSignerImplDefault implements IFidSigner {
        public boolean canSign() {
            return false;
        }

        public String getFid() throws FidSignException {
            return null;
        }

        public byte[] sign(byte[] bArr) throws FidSignException {
            return bArr;
        }
    }

    public interface IFidSigner {
        boolean canSign() throws FidSignException;

        String getFid() throws FidSignException;

        byte[] sign(byte[] bArr) throws FidSignException;
    }

    public static class FidSignException extends Exception {
        public FidSignException(Throwable th) {
            super(th);
        }
    }

    private static class FidSignerHolder {
        /* access modifiers changed from: private */
        public static volatile IFidSigner sInstance = new FidSignerImplDefault();

        private FidSignerHolder() {
        }
    }

    public static IFidSigner getFidSigner() {
        return FidSignerHolder.sInstance;
    }

    public static void setFidSigner(IFidSigner iFidSigner) {
        if (iFidSigner != null) {
            IFidSigner unused = FidSignerHolder.sInstance = iFidSigner;
            return;
        }
        throw new IllegalArgumentException("signer == null");
    }
}
