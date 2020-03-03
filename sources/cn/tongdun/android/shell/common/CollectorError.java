package cn.tongdun.android.shell.common;

import cn.com.fmsh.tsm.business.constants.Constants;
import cn.tongdun.android.shell.utils.LogUtil;
import com.unionpay.tsmservice.data.Constant;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class CollectorError {
    public static final String KEY_REASON = linkxxxxx("2f4f765d6a5c", 60);
    public static final String KEY_STACK = linkxxxxx("2e6c666e6e", 15);
    public static final String KEY_TYPE = linkxxxxx("29737d66", 26);
    private static Map errorMap = new Hashtable();

    public enum TYPE {
        ERROR_INIT(Constant.DEFAULT_CVN2),
        ERROR_SO_LOAD("001"),
        ERROR_PROFILE_FAILED("011"),
        ERROR_PROFILE_DELAY("012"),
        ERROR_SHORT_INTERVAL("013"),
        ERROR_ALWAYS_DEMOTION("014"),
        ERROR_SO_LOAD_FAIL("015"),
        ERROR_TRANSFORM("016"),
        ERROR_NATIVE_METHOD("017"),
        ERROR_SDCARD_FILE("018"),
        ERROR_GETSERVICE_NULL("019"),
        ERROR_JSONERROR("020"),
        ERROR_SIZE_LIMIT("101");
        
        /* access modifiers changed from: private */
        public String code;

        private TYPE(String str) {
            this.code = str;
        }

        public String code() {
            return this.code;
        }
    }

    static {
        addError(TYPE.ERROR_INIT, linkxxxxx("1952491607171c4355444d5d49530c1a0b1d16", 27));
    }

    public static void addError(TYPE type, JSONObject jSONObject) {
        if (errorMap == null) {
            errorMap = new Hashtable();
        }
        if (errorMap.containsKey(type.code())) {
            if (jSONObject != null) {
                String jSONObject2 = jSONObject.toString();
                if (jSONObject2 == null) {
                    jSONObject2 = "";
                }
                String str = (String) errorMap.get(type.code);
                if (str == null) {
                    errorMap.put(type.code(), jSONObject2);
                } else if (!str.contains(jSONObject2)) {
                    errorMap.put(type.code(), str + linkxxxxx("7100", 104) + jSONObject2);
                }
            }
        } else if (jSONObject != null) {
            String jSONObject3 = jSONObject.toString();
            if (jSONObject3 == null) {
                jSONObject3 = "";
            }
            errorMap.put(type.code(), jSONObject3);
        } else {
            errorMap.put(type.code(), linkxxxxx("2611", 115));
        }
    }

    public static void addError(TYPE type, String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(KEY_REASON, str);
        } catch (JSONException e) {
            LogUtil.err(e.toString());
        }
        addError(type, jSONObject);
    }

    public static void clearError() {
        if (errorMap != null) {
            errorMap.clear();
        }
    }

    public static void remove(TYPE type) {
        if (errorMap != null && errorMap.containsKey(type.code)) {
            errorMap.remove(type.code());
        }
    }

    public static String getErrorCode() {
        return errorMap != null ? errorMap.keySet().toString() : "";
    }

    public static String getErrorMsg() {
        if (errorMap == null) {
            return linkxxxxx("0645", 39);
        }
        if (errorMap.size() <= 0) {
            return linkxxxxx("0659", 59);
        }
        Collection values = errorMap.values();
        if (values.size() <= 0) {
            return linkxxxxx("0668", 10);
        }
        return values.toString();
    }

    public static JSONObject catchErr(Throwable th) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(KEY_REASON, th.getLocalizedMessage());
            jSONObject.put(KEY_TYPE, th.getClass().getName());
            StackTraceElement[] stackTrace = th.getStackTrace();
            int i = 0;
            while (true) {
                if (i >= stackTrace.length) {
                    break;
                } else if (stackTrace[i].getClassName().contains(linkxxxxx("3e41231b381a31192002604d6f47795a7f57", 40))) {
                    jSONObject.put(KEY_STACK, stackTrace[i].toString());
                    break;
                } else {
                    i++;
                }
            }
            return jSONObject;
        } catch (Throwable unused) {
            return new JSONObject();
        }
    }

    public static String linkxxxxx(String str, int i) {
        try {
            int length = str.length() / 2;
            char[] charArray = str.toCharArray();
            byte[] bArr = new byte[length];
            for (int i2 = 0; i2 < length; i2++) {
                int i3 = i2 * 2;
                bArr[i2] = (byte) ("0123456789abcdef".indexOf(charArray[i3 + 1]) | ("0123456789abcdef".indexOf(charArray[i3]) << 4));
            }
            byte b = (byte) (i ^ 100);
            int length2 = bArr.length;
            bArr[0] = (byte) (bArr[0] ^ Constants.TagName.TERMINAL_INFO_TYPE);
            byte b2 = bArr[0];
            int i4 = 1;
            while (i4 < length2) {
                byte b3 = bArr[i4];
                bArr[i4] = (byte) ((b2 ^ bArr[i4]) ^ b);
                i4++;
                b2 = b3;
            }
            return new String(bArr, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
