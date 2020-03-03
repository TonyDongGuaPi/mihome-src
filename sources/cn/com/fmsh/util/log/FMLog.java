package cn.com.fmsh.util.log;

public interface FMLog {
    void debug(String str, String str2);

    void error(String str, String str2);

    boolean getShowLogFlag();

    Level getShowLogLevel();

    void info(String str, String str2);

    void setShowLogFlag(boolean z);

    void setShowLogLevel(Level level);

    void warn(String str, String str2);
}
