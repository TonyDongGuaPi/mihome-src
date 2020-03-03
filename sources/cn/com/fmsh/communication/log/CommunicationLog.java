package cn.com.fmsh.communication.log;

import android.util.Log;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.Level;

public class CommunicationLog implements FMLog {
    public boolean showLogFlag = true;
    private Level showLogLevel;

    public void setShowLogLevel(Level level) {
        this.showLogLevel = level;
    }

    public Level getShowLogLevel() {
        return this.showLogLevel;
    }

    public boolean getShowLogFlag() {
        return this.showLogFlag;
    }

    public void setShowLogFlag(boolean z) {
        this.showLogFlag = z;
    }

    public void debug(String str, String str2) {
        Log.d(str, str2);
    }

    public void error(String str, String str2) {
        Log.e(str, str2);
    }

    public void info(String str, String str2) {
        Log.i(str, str2);
    }

    public void warn(String str, String str2) {
        Log.w(str, str2);
    }
}
