package com.xiaomi.smarthome.camera.activity;

import android.content.Context;
import android.content.SharedPreferences;
import com.mijia.debug.SDKLog;
import com.taobao.weex.annotation.JSMethod;
import com.xiaomi.smarthome.camera.activity.setting.bean.GetLeavMsgData;
import com.xiaomi.smarthome.camera.activity.setting.bean.LeaveMsg;
import com.xiaomi.smarthome.camera.activity.setting.record.GlobalParam;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class SPUtil {
    public static final String KEY_LEAVE_MSGS = "KEY_LEAVE_MSGS";
    public static final String TAG = "SPUtil";
    private static SPUtil instance;
    private Context context;
    private String did;
    private String fileName = "hualai_apartment.xml";

    public static SPUtil getInstance(Context context2, String str) {
        if (instance == null) {
            instance = new SPUtil(context2);
        }
        instance.did = str;
        return instance;
    }

    public SPUtil(Context context2) {
        this.context = context2;
    }

    public final void reSaveLeaveMsgs(ArrayList<LeaveMsg> arrayList) {
        GetLeavMsgData getLeavMsgData = new GetLeavMsgData();
        getLeavMsgData.resultList = arrayList;
        String json = GlobalParam.gGson.toJson((Object) getLeavMsgData);
        String str = TAG;
        SDKLog.b(str, "msgsJson=" + json);
        put(KEY_LEAVE_MSGS, json);
    }

    private static class SharedPreferencesCompat {
        private static final Method sApplyMethod = findApplyMethod();

        private SharedPreferencesCompat() {
        }

        public static void apply(SharedPreferences.Editor editor) {
            try {
                if (sApplyMethod != null) {
                    sApplyMethod.invoke(editor, new Object[0]);
                    return;
                }
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException unused) {
            }
            editor.commit();
        }

        private static Method findApplyMethod() {
            try {
                return SharedPreferences.Editor.class.getMethod("apply", new Class[0]);
            } catch (NoSuchMethodException unused) {
                return null;
            }
        }
    }

    public Object get(String str, Object obj) {
        String str2 = this.did + JSMethod.NOT_SET + str;
        if (this.context == null) {
            return obj;
        }
        SharedPreferences sharedPreferences = this.context.getSharedPreferences(this.fileName, 0);
        if (obj instanceof String) {
            return sharedPreferences.getString(str2, (String) obj);
        }
        if (obj instanceof Float) {
            return Float.valueOf(sharedPreferences.getFloat(str2, ((Float) obj).floatValue()));
        }
        if (obj instanceof Long) {
            return Long.valueOf(sharedPreferences.getLong(str2, ((Long) obj).longValue()));
        }
        if (obj instanceof Integer) {
            return Integer.valueOf(sharedPreferences.getInt(str2, ((Integer) obj).intValue()));
        }
        if (obj instanceof Boolean) {
            return Boolean.valueOf(sharedPreferences.getBoolean(str2, ((Boolean) obj).booleanValue()));
        }
        return null;
    }

    public void put(String str, Object obj) {
        String str2 = this.did + JSMethod.NOT_SET + str;
        if (this.context != null) {
            SharedPreferences.Editor edit = this.context.getSharedPreferences(this.fileName, 0).edit();
            if (obj instanceof String) {
                edit.putString(str2, (String) obj);
            } else if (obj instanceof Long) {
                edit.putLong(str2, ((Long) obj).longValue());
            } else if (obj instanceof Float) {
                edit.putFloat(str2, ((Float) obj).floatValue());
            } else if (obj instanceof Integer) {
                edit.putInt(str2, ((Integer) obj).intValue());
            } else if (obj instanceof Boolean) {
                edit.putBoolean(str2, ((Boolean) obj).booleanValue());
            } else {
                edit.putString(str2, obj.toString());
            }
            SharedPreferencesCompat.apply(edit);
        }
    }

    public void remove(String str) {
        SharedPreferences.Editor edit = this.context.getSharedPreferences(this.fileName, 0).edit();
        edit.remove(this.did + JSMethod.NOT_SET + str);
        SharedPreferencesCompat.apply(edit);
    }
}
