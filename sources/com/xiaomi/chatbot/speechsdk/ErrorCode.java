package com.xiaomi.chatbot.speechsdk;

public enum ErrorCode {
    INVALID_PARAMETER(101),
    INVALID_PARAM_END_WITHOUT_AUDIO(102),
    EXECUTION_TIMEOUT(111),
    EXECUTION_ERROR(121),
    TRUNCATION(201),
    STORE_FAILED(202),
    UNKNOWN(1000);
    
    private int value;

    private ErrorCode(int i) {
        this.value = 0;
        this.value = i;
    }

    public static ErrorCode valueOf(int i) {
        switch (i) {
            case 101:
                return INVALID_PARAMETER;
            case 102:
                return INVALID_PARAM_END_WITHOUT_AUDIO;
            case 111:
                return EXECUTION_TIMEOUT;
            case 121:
                return EXECUTION_ERROR;
            case 201:
                return TRUNCATION;
            case 202:
                return STORE_FAILED;
            case 1000:
                return UNKNOWN;
            default:
                return null;
        }
    }

    public int value() {
        return this.value;
    }
}
