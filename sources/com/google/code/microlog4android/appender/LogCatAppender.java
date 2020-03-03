package com.google.code.microlog4android.appender;

import android.util.Log;
import com.google.code.microlog4android.Level;
import java.io.IOException;

public class LogCatAppender extends AbstractAppender {
    public void clear() {
    }

    public long getLogSize() {
        return -1;
    }

    public void doLog(String str, String str2, long j, Level level, Object obj, Throwable th) {
        String str3 = str;
        if (this.logOpen && this.formatter != null) {
            switch (level) {
                case FATAL:
                case ERROR:
                    Log.e(str, this.formatter.format(str, str2, j, level, obj, th));
                    return;
                case WARN:
                    Log.w(str, this.formatter.format(str, str2, j, level, obj, th));
                    return;
                case INFO:
                    Log.i(str, this.formatter.format(str, str2, j, level, obj, th));
                    return;
                case DEBUG:
                case TRACE:
                    Log.d(str, this.formatter.format(str, str2, j, level, obj, th));
                    return;
                default:
                    return;
            }
        }
    }

    public void open() throws IOException {
        this.logOpen = true;
    }

    public void close() throws IOException {
        this.logOpen = false;
    }
}
