package com.xiaomi.smarthome.shop.analytics;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.xiaomi.smarthome.library.common.util.MessageHandlerThread;
import java.util.ArrayList;
import java.util.HashMap;

public class MIOTStat {
    private static final String ENGINE_TAG = "ScriptEngine";
    private static final int LOG = 11;
    public static final String PAYFAIL = "PAYFAIL";
    public static final String PAYSUCCESS = "PAYSUCCESS";
    public static final String RELEASE = "RELEASE";
    public static final String SOURCE = "SOURCE";
    public static final String TOUCH = "TOUCH";
    public static final String VIEW = "VIEW";
    public static final String VIEWEND = "VIEWEND";
    public static final String VISIT = "visit";
    private ArrayList<DelayJsEvent> delayJsEventArr;
    private Handler mHandler;
    private MessageHandlerThread thread;

    public static void Log(String str, String str2, String str3) {
    }

    private boolean _sendArray() {
        return true;
    }

    private boolean init() {
        return true;
    }

    public static class DelayJsEvent {
        public String key;
        public String name;
        public String value;

        public DelayJsEvent(String str, String str2, String str3) {
            this.name = str;
            this.key = str2;
            this.value = str3;
        }
    }

    private static class Instance {
        static MIOTStat instance = new MIOTStat();

        private Instance() {
        }
    }

    public static MIOTStat get() {
        return Instance.instance;
    }

    private MIOTStat() {
        this.thread = null;
        this.mHandler = null;
        this.delayJsEventArr = new ArrayList<>();
        this.thread = new MessageHandlerThread(ENGINE_TAG, 1);
        this.thread.start();
        this.mHandler = new Handler(this.thread.getLooper(), new Handler.Callback() {
            public boolean handleMessage(Message message) {
                if (message.what != 11) {
                    Log.e(MIOTStat.ENGINE_TAG, "error msg what: " + message.what);
                    return false;
                }
                MIOTStat.this.run((Object[]) message.obj);
                return true;
            }
        });
    }

    /* access modifiers changed from: private */
    public void run(Object[] objArr) {
        System.currentTimeMillis();
        sendStatInfo(objArr[0], objArr[1], objArr[2]);
    }

    private void waitT(long j) {
        try {
            synchronized (this) {
                wait(j);
            }
        } catch (InterruptedException unused) {
        }
    }

    private void stat(String str, String str2, String str3) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(11, new Object[]{str, str2, str3}));
    }

    public static void Log(String str, String str2) {
        Log(str, str2, "");
    }

    public static void touch(String str) {
        Log(TOUCH, str, "");
    }

    public static void touch(String str, String str2) {
        Log(TOUCH, str, str2);
    }

    public static void source(String str) {
        Log(SOURCE, str, "");
    }

    public static void paySuccess(String str) {
        Log(PAYSUCCESS, str, "");
    }

    public static void paySuccess(String str, String str2) {
        Log(PAYSUCCESS, str, str2);
    }

    public static void payFail(String str) {
        Log(PAYFAIL, str, "");
    }

    public static void payFail(String str, String str2) {
        Log(PAYFAIL, str, str2);
    }

    public void sendStatInfo(String str, String str2, String str3) {
        this.delayJsEventArr.add(new DelayJsEvent(str, str2, str3));
        _sendArray();
    }

    private boolean _sendToJs(String str, String str2, String str3) {
        HashMap hashMap = new HashMap();
        hashMap.put("event", str);
        hashMap.put("key", str2);
        hashMap.put("value", str3);
        return false;
    }
}
