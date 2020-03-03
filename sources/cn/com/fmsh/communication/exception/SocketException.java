package cn.com.fmsh.communication.exception;

import cn.com.fmsh.FM_Exception;

public class SocketException extends FM_Exception {
    private static final long serialVersionUID = 8451739125478699960L;

    public SocketException(String str) {
        super(str);
    }
}
