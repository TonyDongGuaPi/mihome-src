package cn.com.fmsh.util.log;

public class LogFactory {
    private static LogFactory instance;
    private FMLog log = null;

    private LogFactory() {
    }

    public static LogFactory getInstance() {
        if (instance == null) {
            instance = new LogFactory();
        }
        return instance;
    }

    public void setLog(FMLog fMLog) {
        this.log = fMLog;
    }

    public FMLog getLog() {
        return this.log;
    }
}
