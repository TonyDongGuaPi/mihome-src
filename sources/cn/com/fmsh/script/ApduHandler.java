package cn.com.fmsh.script;

import cn.com.fmsh.script.exception.FMScriptHandleException;

public interface ApduHandler {
    void close();

    boolean connect();

    ApduHandlerType getApduHandlerType();

    boolean isConnect();

    boolean open(byte[] bArr);

    byte[] transceive(byte[] bArr) throws FMScriptHandleException;

    public enum ApduHandlerType {
        OPEN_MOBILE(1, "openMobile"),
        NFC(2, "NFC"),
        FM8301(3, "fm8301"),
        BLUETOOTH(4, "blueTooth");
        
        private String description;
        private int value;

        private ApduHandlerType(int i, String str) {
            this.value = i;
            this.description = str;
        }

        public int getValue() {
            return this.value;
        }

        public String getDescription() {
            return this.description;
        }
    }
}
