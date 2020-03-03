package com.alipay.mobile.common.logging.api;

import android.support.media.ExifInterface;
import com.drew.metadata.wav.WavDirectory;
import com.google.android.gms.stats.netstats.NetstatsParserPatterns;
import java.io.Serializable;
import miuipub.reflect.Field;
import org.apache.http.client.methods.HttpTrace;

public class LogEvent implements Serializable {
    private static final long serialVersionUID = 1;
    protected String category;
    protected Level level;
    protected String message;
    protected String tag;
    protected long timeStamp;

    public LogEvent(String str, String str2, Level level2, String str3) {
        this.category = str;
        this.tag = str2;
        this.level = level2;
        this.message = str3;
        this.timeStamp = System.currentTimeMillis();
    }

    public LogEvent() {
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String str) {
        this.category = str;
    }

    public Level getLevel() {
        return this.level;
    }

    public void setLevel(Level level2) {
        this.level = level2;
    }

    public String getTag() {
        return this.tag;
    }

    public void setTag(String str) {
        this.tag = str;
    }

    public long getTimeStamp() {
        return this.timeStamp;
    }

    public void setTimeStamp(long j) {
        this.timeStamp = j;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public String toString() {
        return this.message;
    }

    public boolean isIllegal() {
        return getCategory() == null || getLevel() == null || !getLevel().isGreaterOrEqual(Level.OFF);
    }

    public static class Level implements Serializable {
        public static final Level ALL = new Level(Integer.MIN_VALUE, NetstatsParserPatterns.TYPE_BOTH_PATTERN);
        public static final int ALL_INT = Integer.MIN_VALUE;
        public static final Level DEBUG = new Level(10000, Field.h);
        public static final int DEBUG_INT = 10000;
        public static final Level ERROR = new Level(40000, ExifInterface.LONGITUDE_EAST);
        public static final int ERROR_INT = 40000;
        public static final Level INFO = new Level(20000, Field.e);
        public static final int INFO_INT = 20000;
        public static final Level OFF = new Level(5000, "OFF");
        public static final int OFF_INT = 5000;
        public static final Level VERBOSE = new Level(5000, "V");
        public static final int VERBOSE_INT = 5000;
        public static final Level WARN = new Level(30000, "W");
        public static final int WARN_INT = 30000;
        private static final long serialVersionUID = -814092767334282137L;
        public final int levelInt;
        public final String levelStr;

        private Level(int i, String str) {
            this.levelInt = i;
            this.levelStr = str;
        }

        public String toString() {
            return this.levelStr;
        }

        public int toInt() {
            return this.levelInt;
        }

        public boolean isGreaterOrEqual(Level level) {
            return this.levelInt >= level.levelInt;
        }

        public static Level toLevel(String str) {
            return toLevel(str, DEBUG);
        }

        public static Level valueOf(String str) {
            return toLevel(str, DEBUG);
        }

        public static Level toLevel(int i) {
            return toLevel(i, DEBUG);
        }

        public static Level toLevel(int i, Level level) {
            if (i == 5000) {
                return VERBOSE;
            }
            if (i == 10000) {
                return DEBUG;
            }
            if (i == 20000) {
                return INFO;
            }
            if (i != 30000) {
                return i != 40000 ? level : ERROR;
            }
            return WARN;
        }

        public static Level toLevel(String str, Level level) {
            if (str == null) {
                return level;
            }
            if (str.equalsIgnoreCase(NetstatsParserPatterns.TYPE_BOTH_PATTERN)) {
                return ALL;
            }
            if (str.equalsIgnoreCase(HttpTrace.METHOD_NAME)) {
                return VERBOSE;
            }
            if (str.equalsIgnoreCase("DEBUG")) {
                return DEBUG;
            }
            if (str.equalsIgnoreCase(WavDirectory.w)) {
                return INFO;
            }
            if (str.equalsIgnoreCase("WARN")) {
                return WARN;
            }
            if (str.equalsIgnoreCase("ERROR")) {
                return ERROR;
            }
            return str.equalsIgnoreCase("OFF") ? OFF : level;
        }
    }
}
