package com.tencent.smtt.utils;

import android.content.Context;
import android.os.Environment;
import android.os.Looper;
import android.os.Process;
import android.util.Log;
import android.widget.TextView;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class TbsLogClient {

    /* renamed from: a  reason: collision with root package name */
    static TbsLogClient f9199a = null;
    static File c = null;
    static String d = null;
    static byte[] e = null;
    private static boolean i = true;
    TextView b;
    private SimpleDateFormat f = null;
    private Context g = null;
    private StringBuffer h = new StringBuffer();

    private class a implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        String f9200a = null;

        a(String str) {
            this.f9200a = str;
        }

        public void run() {
            if (TbsLogClient.this.b != null) {
                TextView textView = TbsLogClient.this.b;
                textView.append(this.f9200a + "\n");
            }
        }
    }

    public TbsLogClient(Context context) {
        try {
            this.g = context.getApplicationContext();
            this.f = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.SSS", Locale.US);
        } catch (Exception unused) {
            this.f = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.SSS");
        }
    }

    private void a() {
        String a2;
        try {
            if (c != null) {
                return;
            }
            if (!Environment.getExternalStorageState().equals("mounted") || (a2 = j.a(this.g, 6)) == null) {
                c = null;
                return;
            }
            c = new File(a2, "tbslog.txt");
            d = LogFileUtils.createKey();
            e = LogFileUtils.createHeaderText(c.getName(), d);
        } catch (SecurityException e2) {
            e2.printStackTrace();
        } catch (NullPointerException e3) {
            e3.printStackTrace();
        }
    }

    public static void setWriteLogJIT(boolean z) {
        i = z;
    }

    public void d(String str, String str2) {
    }

    public void e(String str, String str2) {
        Log.e(str, str2);
    }

    public void i(String str, String str2) {
    }

    public void setLogView(TextView textView) {
        this.b = textView;
    }

    public void showLog(String str) {
        if (this.b != null) {
            this.b.post(new a(str));
        }
    }

    public void v(String str, String str2) {
    }

    public void w(String str, String str2) {
    }

    public void writeLog(String str) {
        try {
            String format = this.f.format(Long.valueOf(System.currentTimeMillis()));
            StringBuffer stringBuffer = this.h;
            stringBuffer.append(format);
            stringBuffer.append(" pid=");
            stringBuffer.append(Process.myPid());
            stringBuffer.append(" tid=");
            stringBuffer.append(Process.myTid());
            stringBuffer.append(str);
            stringBuffer.append("\n");
            if (Thread.currentThread() != Looper.getMainLooper().getThread() || i) {
                writeLogToDisk();
            }
            if (this.h.length() > 524288) {
                this.h.delete(0, this.h.length());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void writeLogToDisk() {
        try {
            a();
            if (c != null) {
                LogFileUtils.writeDataToStorage(c, d, e, this.h.toString(), true);
                this.h.delete(0, this.h.length());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
