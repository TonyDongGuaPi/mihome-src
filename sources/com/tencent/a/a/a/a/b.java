package com.tencent.a.a.a.a;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import com.paytm.pgsdk.PaytmConstants;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

final class b extends f {
    b(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public final void a(String str) {
        synchronized (this) {
            Log.i(PaytmConstants.f8536a, "write mid to InternalStorage");
            a.a(Environment.getExternalStorageDirectory() + "/" + h.c("6X8Y4XdM2Vhvn0I="));
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(Environment.getExternalStorageDirectory(), h.c("6X8Y4XdM2Vhvn0KfzcEatGnWaNU="))));
                bufferedWriter.write(h.c("4kU71lN96TJUomD1vOU9lgj9Tw==") + "," + str);
                bufferedWriter.write("\n");
                bufferedWriter.close();
            } catch (Exception e) {
                Log.w(PaytmConstants.f8536a, e);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final boolean a() {
        return h.a(this.f8975a, "android.permission.WRITE_EXTERNAL_STORAGE") && Environment.getExternalStorageState().equals("mounted");
    }

    /* access modifiers changed from: protected */
    public final String b() {
        String str;
        synchronized (this) {
            Log.i(PaytmConstants.f8536a, "read mid from InternalStorage");
            try {
                Iterator<String> it = a.a(new File(Environment.getExternalStorageDirectory(), h.c("6X8Y4XdM2Vhvn0KfzcEatGnWaNU="))).iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    String[] split = it.next().split(",");
                    if (split.length == 2 && split[0].equals(h.c("4kU71lN96TJUomD1vOU9lgj9Tw=="))) {
                        Log.i(PaytmConstants.f8536a, "read mid from InternalStorage:" + split[1]);
                        str = split[1];
                        break;
                    }
                }
            } catch (IOException e) {
                Log.w(PaytmConstants.f8536a, e);
            }
            str = null;
        }
        return str;
    }
}
