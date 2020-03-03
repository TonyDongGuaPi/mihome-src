package cn.com.fmsh.communication.exception.session;

import cn.com.fmsh.FM_Exception;

public class CloseSessionException extends FM_Exception {
    private static final long serialVersionUID = 648510135747422790L;

    public CloseSessionException(String str) {
        super(str);
    }
}
