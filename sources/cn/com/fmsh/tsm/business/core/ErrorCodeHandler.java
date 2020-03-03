package cn.com.fmsh.tsm.business.core;

import cn.com.fmsh.util.Util4Java;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ErrorCodeHandler {
    private Properties errorCode = new Properties();
    private FMLog fmLog = LogFactory.getInstance().getLog();
    private final String logTag = ErrorCodeHandler.class.getName();

    public boolean init(InputStream inputStream) {
        if (inputStream == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "响应码初始化失败时，传入的配置文件为空");
            }
            return false;
        }
        try {
            this.errorCode.load(inputStream);
            return true;
        } catch (IOException e) {
            if (this.fmLog != null) {
                FMLog fMLog = this.fmLog;
                String str = this.logTag;
                fMLog.warn(str, "响应码初始化失败:" + Util4Java.getExceptionInfo(e));
            }
            return false;
        }
    }

    public int getCode(String str) {
        String property = this.errorCode.getProperty(str);
        if (property != null && property.length() >= 1) {
            return Integer.parseInt(property.trim());
        }
        if (this.fmLog == null) {
            return 99;
        }
        FMLog fMLog = this.fmLog;
        String str2 = this.logTag;
        fMLog.debug(str2, "获取[" + str + "]的响应码失败");
        return 99;
    }
}
