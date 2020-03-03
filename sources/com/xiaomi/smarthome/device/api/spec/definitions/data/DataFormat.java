package com.xiaomi.smarthome.device.api.spec.definitions.data;

import com.mi.mistatistic.sdk.data.EventData;
import com.xiaomi.smarthome.device.api.spec.definitions.data.value.Vbool;
import com.xiaomi.smarthome.device.api.spec.definitions.data.value.Vfloat;
import com.xiaomi.smarthome.device.api.spec.definitions.data.value.Vint16;
import com.xiaomi.smarthome.device.api.spec.definitions.data.value.Vint32;
import com.xiaomi.smarthome.device.api.spec.definitions.data.value.Vint64;
import com.xiaomi.smarthome.device.api.spec.definitions.data.value.Vint8;
import com.xiaomi.smarthome.device.api.spec.definitions.data.value.Vstring;
import com.xiaomi.smarthome.device.api.spec.definitions.data.value.Vuint16;
import com.xiaomi.smarthome.device.api.spec.definitions.data.value.Vuint32;
import com.xiaomi.smarthome.device.api.spec.definitions.data.value.Vuint8;
import java.util.Locale;

public enum DataFormat {
    UNKNOWN("unknown"),
    BOOL("bool"),
    UINT8("uint8"),
    UINT16("uint16"),
    UINT32("uint32"),
    INT8("int8"),
    INT16("int16"),
    INT32("int32"),
    INT64("int64"),
    FLOAT("float"),
    STRING(EventData.b);
    
    private String string;

    private DataFormat(String str) {
        this.string = str;
    }

    public String toString() {
        return this.string;
    }

    public boolean isNumber() {
        switch (this) {
            case INT8:
            case UINT8:
            case INT16:
            case UINT16:
            case INT32:
            case UINT32:
            case INT64:
            case FLOAT:
                return true;
            default:
                return false;
        }
    }

    public boolean isDigits() {
        switch (this) {
            case INT8:
            case UINT8:
            case INT16:
            case UINT16:
            case INT32:
            case UINT32:
            case INT64:
                return true;
            default:
                return false;
        }
    }

    public static DataFormat from(String str) throws IllegalArgumentException {
        for (DataFormat dataFormat : values()) {
            if (dataFormat.toString().equals(str)) {
                return dataFormat;
            }
        }
        throw new IllegalArgumentException("DataFormat invalid: " + str);
    }

    public boolean check(DataValue dataValue) {
        switch (this) {
            case INT8:
                if (dataValue.getClass() == Vint8.class) {
                    return true;
                }
                return false;
            case UINT8:
                if (dataValue.getClass() == Vuint8.class) {
                    return true;
                }
                return false;
            case INT16:
                if (dataValue.getClass() == Vint16.class) {
                    return true;
                }
                return false;
            case UINT16:
                if (dataValue.getClass() == Vuint16.class) {
                    return true;
                }
                return false;
            case INT32:
                if (dataValue.getClass() == Vint32.class) {
                    return true;
                }
                return false;
            case UINT32:
                if (dataValue.getClass() == Vuint32.class) {
                    return true;
                }
                return false;
            case INT64:
                if (dataValue.getClass() == Vint64.class) {
                    return true;
                }
                return false;
            case FLOAT:
                if (dataValue.getClass() == Vfloat.class) {
                    return true;
                }
                return false;
            case STRING:
                if (dataValue.getClass() == Vstring.class) {
                    return true;
                }
                return false;
            case BOOL:
                if (dataValue.getClass() == Vbool.class) {
                    return true;
                }
                return false;
            default:
                return false;
        }
    }

    public boolean check(DataValue dataValue, DataValue dataValue2, DataValue dataValue3) {
        return dataValue.lessEquals(dataValue2);
    }

    public boolean validate(DataValue dataValue, DataValue dataValue2, DataValue dataValue3, DataValue dataValue4) {
        return dataValue4 == null ? dataValue.validate(dataValue2, dataValue3) : dataValue.validate(dataValue2, dataValue3, dataValue4);
    }

    public String getFormatJavaBase() {
        switch (this) {
            case INT8:
                return "int";
            case UINT8:
                return "int";
            case INT16:
                return "int";
            case UINT16:
                return "int";
            case INT32:
                return "int";
            case UINT32:
                return "int";
            case INT64:
                return "long";
            case FLOAT:
                return "float";
            case STRING:
                return "String";
            case BOOL:
                return "boolean";
            default:
                return "unknown";
        }
    }

    public Class<?> getJavaClass() throws RuntimeException {
        switch (this) {
            case INT8:
                return Vint8.class;
            case UINT8:
                return Vuint8.class;
            case INT16:
                return Vint16.class;
            case UINT16:
                return Vuint16.class;
            case INT32:
                return Vint32.class;
            case UINT32:
                return Vuint32.class;
            case INT64:
                return Vint64.class;
            case FLOAT:
                return Vfloat.class;
            case STRING:
                return Vstring.class;
            case BOOL:
                return Vbool.class;
            default:
                throw new RuntimeException("DataFormat invalid");
        }
    }

    public Object createObjectValue(String str) throws IllegalArgumentException {
        switch (this) {
            case INT8:
                return Integer.valueOf(str);
            case UINT8:
                return Integer.valueOf(str);
            case INT16:
                return Integer.valueOf(str);
            case UINT16:
                return Integer.valueOf(str);
            case INT32:
                return Integer.valueOf(str);
            case UINT32:
                return Integer.valueOf(str);
            case INT64:
                return Long.valueOf(str);
            case FLOAT:
                if (str.equals("0")) {
                    str = "0.0f";
                }
                return Float.valueOf(str);
            case STRING:
                return str;
            case BOOL:
                return BooleanValueOf(str);
            default:
                throw new IllegalArgumentException("createObjectValue failed, invalid type");
        }
    }

    public DataValue createDefaultValue() {
        switch (this) {
            case INT8:
                return new Vint8();
            case UINT8:
                return new Vuint8();
            case INT16:
                return new Vint16();
            case UINT16:
                return new Vuint16();
            case INT32:
                return new Vint32();
            case UINT32:
                return new Vuint32();
            case INT64:
                return new Vint64();
            case FLOAT:
                return new Vfloat();
            case STRING:
                return new Vstring();
            case BOOL:
                return new Vbool();
            default:
                throw new IllegalArgumentException("createObjectValue failed, invalid type");
        }
    }

    public DataValue createValue(Object obj) {
        switch (this) {
            case INT8:
                return Vint8.valueOf(obj);
            case UINT8:
                return Vuint8.valueOf(obj);
            case INT16:
                return Vint16.valueOf(obj);
            case UINT16:
                return Vuint16.valueOf(obj);
            case INT32:
                return Vint32.valueOf(obj);
            case UINT32:
                return Vuint32.valueOf(obj);
            case INT64:
                return Vint64.valueOf(obj);
            case FLOAT:
                return Vfloat.valueOf(obj);
            case STRING:
                return Vstring.valueOf(obj);
            case BOOL:
                return Vbool.valueOf(obj);
            default:
                throw new IllegalArgumentException("createValue failed, invalid type");
        }
    }

    private static Boolean BooleanValueOf(String str) {
        if (str == null) {
            return false;
        }
        String upperCase = str.toUpperCase(Locale.ENGLISH);
        if (upperCase.equals("1") || upperCase.equals(Constants.ad) || upperCase.equals("TRUE")) {
            return true;
        }
        if (upperCase.equals("0") || upperCase.equals(Constants.ae) || upperCase.equals("FALSE")) {
            return false;
        }
        throw new IllegalArgumentException("BooleanValueOf failed: " + str);
    }
}
