package cn.com.fmsh.exception;

import cn.com.fmsh.FM_Exception;

public class InvalidParameterException extends FM_Exception {
    private static final long serialVersionUID = -6782875458998826408L;

    public InvalidParameterException(String str) {
        super(str);
    }
}
