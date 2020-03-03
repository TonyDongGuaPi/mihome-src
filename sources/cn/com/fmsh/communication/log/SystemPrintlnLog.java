package cn.com.fmsh.communication.log;

import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.Level;
import java.io.PrintStream;

public class SystemPrintlnLog implements FMLog {
    private Level level = Level.DEBUG;
    private boolean showLogFlag;

    public void debug(String str, String str2) {
        if (this.level.getId() <= Level.DEBUG.getId()) {
            PrintStream printStream = System.out;
            printStream.println("DEBUG--" + str + " " + str2);
        }
    }

    public void error(String str, String str2) {
        if (this.level.getId() <= Level.ERROR.getId()) {
            PrintStream printStream = System.out;
            printStream.println("ERROR--" + str + " " + str2);
        }
    }

    public boolean getShowLogFlag() {
        return this.showLogFlag;
    }

    public void info(String str, String str2) {
        if (this.level.getId() <= Level.INFO.getId()) {
            PrintStream printStream = System.out;
            printStream.println("INFO--" + str + " " + str2);
        }
    }

    public void setShowLogFlag(boolean z) {
        this.showLogFlag = z;
    }

    public void warn(String str, String str2) {
        if (this.level.getId() <= Level.WARNING.getId()) {
            PrintStream printStream = System.out;
            printStream.println("WARN--" + str + " " + str2);
        }
    }

    public Level getShowLogLevel() {
        return this.level;
    }

    public void setShowLogLevel(Level level2) {
        this.level = level2;
    }
}
