package org.cybergarage.util;

import android.util.Log;
import com.taobao.weex.el.parse.Operators;

public class CommonLog {
    public static boolean isDebug = false;
    public static int logLevel = 2;
    private String tag = "CommonLog";

    public CommonLog() {
    }

    public CommonLog(String str) {
        this.tag = str;
    }

    public void setTag(String str) {
        this.tag = str;
    }

    private String getFunctionName() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace == null) {
            return null;
        }
        for (StackTraceElement stackTraceElement : stackTrace) {
            if (!stackTraceElement.isNativeMethod() && !stackTraceElement.getClassName().equals(Thread.class.getName()) && !stackTraceElement.getClassName().equals(getClass().getName())) {
                return Operators.ARRAY_START_STR + Thread.currentThread().getId() + ": " + stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber() + Operators.ARRAY_END_STR;
            }
        }
        return null;
    }

    public void info(Object obj) {
        String str;
        if (logLevel <= 4) {
            String functionName = getFunctionName();
            if (functionName == null) {
                str = obj.toString();
            } else {
                str = functionName + " - " + obj;
            }
            Log.i(this.tag, str);
        }
    }

    public void i(Object obj) {
        if (isDebug) {
            info(obj);
        }
    }

    public void verbose(Object obj) {
        String str;
        if (logLevel <= 2) {
            String functionName = getFunctionName();
            if (functionName == null) {
                str = obj.toString();
            } else {
                str = functionName + " - " + obj;
            }
            Log.v(this.tag, str);
        }
    }

    public void v(Object obj) {
        if (isDebug) {
            verbose(obj);
        }
    }

    public void warn(Object obj) {
        String str;
        if (logLevel <= 5) {
            String functionName = getFunctionName();
            if (functionName == null) {
                str = obj.toString();
            } else {
                str = functionName + " - " + obj;
            }
            Log.w(this.tag, str);
        }
    }

    public void w(Object obj) {
        if (isDebug) {
            warn(obj);
        }
    }

    public void error(Object obj) {
        String str;
        if (logLevel <= 6) {
            String functionName = getFunctionName();
            if (functionName == null) {
                str = obj.toString();
            } else {
                str = functionName + " - " + obj;
            }
            Log.e(this.tag, str);
        }
    }

    public void error(Exception exc) {
        if (logLevel <= 6) {
            StringBuffer stringBuffer = new StringBuffer();
            String functionName = getFunctionName();
            StackTraceElement[] stackTrace = exc.getStackTrace();
            if (functionName != null) {
                stringBuffer.append(functionName + " - " + exc + "\r\n");
            } else {
                stringBuffer.append(exc + "\r\n");
            }
            if (stackTrace != null && stackTrace.length > 0) {
                for (StackTraceElement stackTraceElement : stackTrace) {
                    if (stackTraceElement != null) {
                        stringBuffer.append("[ " + stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber() + " ]\r\n");
                    }
                }
            }
            Log.e(this.tag, stringBuffer.toString());
        }
    }

    public void e(Object obj) {
        if (isDebug) {
            error(obj);
        }
    }

    public void e(Exception exc) {
        if (isDebug) {
            error(exc);
        }
    }

    public void debug(Object obj) {
        String str;
        if (logLevel <= 3) {
            String functionName = getFunctionName();
            if (functionName == null) {
                str = obj.toString();
            } else {
                str = functionName + " - " + obj;
            }
            Log.d(this.tag, str);
        }
    }

    public void d(Object obj) {
        if (isDebug) {
            debug(obj);
        }
    }
}
