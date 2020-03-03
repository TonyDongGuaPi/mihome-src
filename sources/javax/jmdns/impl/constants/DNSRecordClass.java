package javax.jmdns.impl.constants;

import com.drew.metadata.exif.ExifDirectoryBase;
import com.xiaomi.stat.d;
import java.util.logging.Level;
import java.util.logging.Logger;

public enum DNSRecordClass {
    CLASS_UNKNOWN("?", 0),
    CLASS_IN("in", 1),
    CLASS_CS(d.v, 2),
    CLASS_CH("ch", 3),
    CLASS_HS("hs", 4),
    CLASS_NONE("none", ExifDirectoryBase.g),
    CLASS_ANY("any", 255);
    
    public static final int CLASS_MASK = 32767;
    public static final int CLASS_UNIQUE = 32768;
    public static final boolean NOT_UNIQUE = false;
    public static final boolean UNIQUE = true;
    private static Logger logger;
    private final String _externalName;
    private final int _index;

    static {
        logger = Logger.getLogger(DNSRecordClass.class.getName());
    }

    private DNSRecordClass(String str, int i) {
        this._externalName = str;
        this._index = i;
    }

    public String externalName() {
        return this._externalName;
    }

    public int indexValue() {
        return this._index;
    }

    public boolean isUnique(int i) {
        return (this == CLASS_UNKNOWN || (i & 32768) == 0) ? false : true;
    }

    public static DNSRecordClass classForName(String str) {
        if (str != null) {
            String lowerCase = str.toLowerCase();
            for (DNSRecordClass dNSRecordClass : values()) {
                if (dNSRecordClass._externalName.equals(lowerCase)) {
                    return dNSRecordClass;
                }
            }
        }
        logger.log(Level.WARNING, "Could not find record class for name: " + str);
        return CLASS_UNKNOWN;
    }

    public static DNSRecordClass classForIndex(int i) {
        int i2 = i & CLASS_MASK;
        for (DNSRecordClass dNSRecordClass : values()) {
            if (dNSRecordClass._index == i2) {
                return dNSRecordClass;
            }
        }
        logger.log(Level.WARNING, "Could not find record class for index: " + i);
        return CLASS_UNKNOWN;
    }

    public String toString() {
        return name() + " index " + indexValue();
    }
}
