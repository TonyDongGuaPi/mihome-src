package cn.com.fmsh.script.exception;

import cn.com.fmsh.FM_Exception;
import com.amap.api.services.core.AMapException;

public class FMScriptHandleException extends FM_Exception {
    private static final long serialVersionUID = 1872758501585762351L;
    private ScriptHandleExceptionType type;

    public ScriptHandleExceptionType getType() {
        return this.type;
    }

    public void setType(ScriptHandleExceptionType scriptHandleExceptionType) {
        this.type = scriptHandleExceptionType;
    }

    public FMScriptHandleException(String str) {
        super(str);
    }

    public enum ScriptHandleExceptionType {
        STOPED((byte) 1, "当前操作被停止"),
        INVALID_FIRST_ID((byte) 2, "第一条指令无效"),
        INVALID_NEXT((byte) 3, "下条指令序号大于当前序号"),
        OPENMOBILE_TRANSMIT_EXCEPTION((byte) 4, "open mobile 交互异常"),
        UNKNOW((byte) -1, AMapException.AMAP_CLIENT_UNKNOWN_ERROR);
        
        private String description;
        private byte value;

        private ScriptHandleExceptionType(byte b, String str) {
            this.value = b;
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
