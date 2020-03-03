package cn.com.fmsh.communication.exception;

import cn.com.fmsh.FM_Exception;
import com.amap.api.services.core.AMapException;

public class CommunicationException extends FM_Exception {
    private static final long serialVersionUID = -638900000836743962L;
    private CommandDirection direction;
    private CommunicationExceptionType exceptionType;

    public CommunicationException(String str) {
        super(str);
    }

    public CommunicationExceptionType getExceptionType() {
        return this.exceptionType;
    }

    public void setExceptionType(CommunicationExceptionType communicationExceptionType) {
        this.exceptionType = communicationExceptionType;
    }

    public CommandDirection getDirection() {
        return this.direction;
    }

    public void setDirection(CommandDirection commandDirection) {
        this.direction = commandDirection;
    }

    public enum CommunicationExceptionType {
        INVALID_VERSION((byte) 1, "无效协议版本"),
        INVALID_FORMAT((byte) 2, "报文格式无效"),
        CHECK_FAILED((byte) 3, "报文检验失败"),
        INVALID_CONTROL((byte) 4, "报文控制字无效"),
        INVALID_SESSION((byte) 5, "会话无效"),
        INVALID_SESSION_NUMBER((byte) 6, "会话流水无效"),
        INVALID_DIRECTION((byte) 7, "数据方向异常"),
        NO_REPONSE((byte) 8, "未收到响应数据"),
        INVALID_REPONSE((byte) 9, "响应数据不合法"),
        UNKNOW((byte) -1, AMapException.AMAP_CLIENT_UNKNOWN_ERROR);
        
        private String description;
        private byte value;

        private CommunicationExceptionType(byte b, String str) {
            this.value = b;
            this.description = str;
        }

        public int getValue() {
            return this.value;
        }

        public String getDescription() {
            return this.description;
        }

        public static CommunicationExceptionType instance(int i) {
            for (CommunicationExceptionType communicationExceptionType : values()) {
                if (communicationExceptionType.getValue() == i) {
                    return communicationExceptionType;
                }
            }
            return UNKNOW;
        }
    }

    public enum CommandDirection {
        REQUESR((byte) 0, "终端请求"),
        RESPONSE((byte) 1, "平台响应");
        
        private String description;
        private byte value;

        private CommandDirection(byte b, String str) {
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
