package com.xiaomi.jr.antifraud.por;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.xiaomi.jr.antifraud.AntifraudManager;
import com.xiaomi.jr.antifraud.por.MaskedPhoneNumHelper;
import com.xiaomi.phonenum.PhoneNumKeeper;
import com.xiaomi.phonenum.PhoneNumKeeperFactory;
import com.xiaomi.phonenum.bean.Error;
import com.xiaomi.phonenum.bean.PhoneNum;
import java.util.ArrayList;
import java.util.List;

public class MaskedPhoneNumHelper {

    /* renamed from: a  reason: collision with root package name */
    private static List<String> f10301a = new ArrayList();
    private static final Object b = new Object();
    private static final Handler c = new Handler(Looper.getMainLooper());

    public interface PhoneNumUpdateListener {
        void a();
    }

    public static List<String> a() {
        List<String> list;
        synchronized (b) {
            list = f10301a;
        }
        return list;
    }

    public static void a(Context context, String str) {
        a(context, str, (PhoneNumUpdateListener) null);
    }

    public static void a(Context context, String str, PhoneNumUpdateListener phoneNumUpdateListener) {
        if (context != null) {
            new Thread(new Runnable(context, str, phoneNumUpdateListener) {
                private final /* synthetic */ Context f$0;
                private final /* synthetic */ String f$1;
                private final /* synthetic */ MaskedPhoneNumHelper.PhoneNumUpdateListener f$2;

                {
                    this.f$0 = r1;
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    MaskedPhoneNumHelper.b(this.f$0, this.f$1, this.f$2);
                }
            }).start();
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void b(Context context, String str, PhoneNumUpdateListener phoneNumUpdateListener) {
        PhoneNumKeeper a2 = new PhoneNumKeeperFactory().a(context, str);
        a2.a((PhoneNumKeeper.SetupFinishedListener) new PhoneNumKeeper.SetupFinishedListener(phoneNumUpdateListener) {
            private final /* synthetic */ MaskedPhoneNumHelper.PhoneNumUpdateListener f$1;

            {
                this.f$1 = r2;
            }

            public final void onSetupFinished(Error error) {
                MaskedPhoneNumHelper.a(PhoneNumKeeper.this, this.f$1, error);
            }
        });
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void a(PhoneNumKeeper phoneNumKeeper, PhoneNumUpdateListener phoneNumUpdateListener, Error error) {
        if (error == Error.NONE) {
            new Thread(new Runnable(phoneNumUpdateListener) {
                private final /* synthetic */ MaskedPhoneNumHelper.PhoneNumUpdateListener f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    MaskedPhoneNumHelper.a(PhoneNumKeeper.this, this.f$1);
                }
            }).start();
            return;
        }
        phoneNumKeeper.b();
        if (phoneNumUpdateListener != null) {
            Handler handler = c;
            phoneNumUpdateListener.getClass();
            handler.post(new Runnable() {
                public final void run() {
                    MaskedPhoneNumHelper.PhoneNumUpdateListener.this.a();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void a(PhoneNumKeeper phoneNumKeeper, PhoneNumUpdateListener phoneNumUpdateListener) {
        try {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < phoneNumKeeper.a(); i++) {
                phoneNumKeeper.c(i);
                PhoneNum phoneNum = phoneNumKeeper.a(i).get();
                if (phoneNum != null && !TextUtils.isEmpty(phoneNum.c)) {
                    arrayList.add(phoneNum.c);
                }
            }
            synchronized (b) {
                f10301a.clear();
                f10301a.addAll(arrayList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        phoneNumKeeper.b();
        if (phoneNumUpdateListener != null) {
            Handler handler = c;
            phoneNumUpdateListener.getClass();
            handler.post(new Runnable() {
                public final void run() {
                    MaskedPhoneNumHelper.PhoneNumUpdateListener.this.a();
                }
            });
        }
    }

    public static class SimStateReceive extends BroadcastReceiver {

        /* renamed from: a  reason: collision with root package name */
        private static final String f10302a = "android.intent.action.SIM_STATE_CHANGED";

        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(f10302a)) {
                int simState = ((TelephonyManager) context.getSystemService("phone")).getSimState();
                if (simState != 1) {
                    switch (simState) {
                        case 5:
                        case 6:
                        case 7:
                        case 8:
                        case 9:
                            break;
                        default:
                            return;
                    }
                }
                MaskedPhoneNumHelper.a(context, AntifraudManager.a().c());
            }
        }
    }
}
