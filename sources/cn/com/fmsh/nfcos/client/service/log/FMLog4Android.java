package cn.com.fmsh.nfcos.client.service.log;

import android.util.Log;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.Level;

public class FMLog4Android implements FMLog {
    private Level level = Level.DEBUG;
    private boolean showLogFlag = true;

    public void debug(String str, String str2) {
        if (this.level.getId() <= Level.DEBUG.getId()) {
            Log.d(str, str2);
        }
    }

    public void error(String str, String str2) {
        if (this.level.getId() <= Level.ERROR.getId()) {
            Log.e(str, str2);
        }
    }

    public boolean getShowLogFlag() {
        return this.showLogFlag;
    }

    public void info(String str, String str2) {
        if (this.level.getId() <= Level.INFO.getId()) {
            Log.i(str, str2);
        }
    }

    public void setShowLogFlag(boolean z) {
        this.showLogFlag = z;
    }

    public void warn(String str, String str2) {
        if (this.level.getId() <= Level.WARNING.getId()) {
            Log.w(str, str2);
        }
    }

    public Level getShowLogLevel() {
        return this.level;
    }

    public void setShowLogLevel(Level level2) {
        this.level = level2;
    }
}
