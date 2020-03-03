package cn.com.fmsh.communication.exception.session;

import cn.com.fmsh.FM_Exception;
import cn.com.fmsh.tsm.business.constants.Constants;
import com.amap.api.services.core.AMapException;

public class OpenSessionException extends FM_Exception {
    private static final long serialVersionUID = -638900000836743962L;
    private OpenSessionExceptionType exceptionType;

    public OpenSessionException(String str) {
        super(str);
    }

    public OpenSessionExceptionType getExceptionType() {
        return this.exceptionType;
    }

    public void setExceptionType(OpenSessionExceptionType openSessionExceptionType) {
        this.exceptionType = openSessionExceptionType;
    }

    public enum OpenSessionExceptionType {
        SYSTEM_BUSY(Byte.MIN_VALUE, "系统忙，暂停接入服务"),
        INVALID_TERMINAL((byte) -127, "无效终端类型"),
        INVALID_KEY_INDEX((byte) -126, "签到使用密钥索引无效"),
        DECRYPT_FAIL((byte) -125, "签到请求数据解密失败"),
        DATA_FORMAT_ERROR((byte) -124, "签到请求数据格式错误"),
        INVALID_TERMINAL_ID(Constants.TagName.ACTIVITY_END, "无效终端编号"),
        SECURITY_CODE(Constants.TagName.ACTIVITY_TOTAL, "防伪码验证失败"),
        INVALID_TIME(Constants.TagName.ACTIVITY_REMAINDER, "终端时间异常"),
        UNKNOW((byte) -1, AMapException.AMAP_CLIENT_UNKNOWN_ERROR);
        
        private String description;
        private byte value;

        private OpenSessionExceptionType(byte b, String str) {
            this.value = b;
            this.description = str;
        }

        public int getValue() {
            return this.value;
        }

        public String getDescription() {
            return this.description;
        }

        public static OpenSessionExceptionType instance(int i) {
            for (OpenSessionExceptionType openSessionExceptionType : values()) {
                if (openSessionExceptionType.getValue() == i) {
                    return openSessionExceptionType;
                }
            }
            return UNKNOW;
        }
    }
}
