package org.cybergarage.util;

import com.taobao.weex.el.parse.Operators;
import java.io.PrintStream;

public final class Debug {
    public static Debug debug = new Debug();
    public static boolean enabled = false;
    private static final CommonLog log = LogFactory.createNewLog("dlna_framework");
    private PrintStream out = System.out;

    public synchronized PrintStream getOut() {
        return this.out;
    }

    public synchronized void setOut(PrintStream printStream) {
        this.out = printStream;
    }

    public static Debug getDebug() {
        return debug;
    }

    public static final void on() {
        enabled = true;
    }

    public static final void off() {
        enabled = false;
    }

    public static boolean isOn() {
        return enabled;
    }

    public static final void message(String str) {
        if (enabled) {
            PrintStream out2 = debug.getOut();
            out2.println("CyberGarage message : " + str);
        }
    }

    public static final void message(String str, String str2) {
        if (enabled) {
            debug.getOut().println("CyberGarage message : ");
        }
        debug.getOut().println(str);
        debug.getOut().println(str2);
    }

    public static final void warning(String str) {
        CommonLog commonLog = log;
        commonLog.e((Object) "CyberGarage warning : " + str);
    }

    public static final void warning(String str, Exception exc) {
        if (exc.getMessage() == null) {
            PrintStream out2 = debug.getOut();
            out2.println("CyberGarage warning : " + str + " START");
            exc.printStackTrace(debug.getOut());
            PrintStream out3 = debug.getOut();
            out3.println("CyberGarage warning : " + str + " END");
            return;
        }
        PrintStream out4 = debug.getOut();
        out4.println("CyberGarage warning : " + str + " (" + exc.getMessage() + Operators.BRACKET_END_STR);
        exc.printStackTrace(debug.getOut());
    }

    public static final void warning(Exception exc) {
        warning(exc.getMessage());
    }
}
