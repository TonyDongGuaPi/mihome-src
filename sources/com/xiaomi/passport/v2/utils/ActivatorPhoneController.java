package com.xiaomi.passport.v2.utils;

import android.content.Context;
import com.xiaomi.accountsdk.account.data.ActivatorPhoneInfo;
import com.xiaomi.accountsdk.utils.AccountLog;
import com.xiaomi.passport.uicontroller.SimpleFutureTask;
import com.xiaomi.passport.utils.XiaomiPassportExecutor;
import com.xiaomi.phonenum.PhoneNumKeeper;
import com.xiaomi.phonenum.PhoneNumKeeperFactory;
import com.xiaomi.phonenum.bean.Error;
import com.xiaomi.phonenum.bean.PhoneNum;
import com.xiaomi.phonenum.obtain.PhoneLevel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class ActivatorPhoneController {
    private static final String APP_ID = "2882303761517565051";
    private static final long MILLI_TIME_OUT_GET_ACTIVATOR_PHONE = 180000;
    private static final String TAG = "ActivatorPhoneController";
    protected SimpleFutureTask<List<ActivatorPhoneInfo>> mGetActivatorPhoneTask;
    protected PhoneNumKeeper mPhoneNumKeeper;

    public interface PhoneNumCallback {
        void onDualSIM(ActivatorPhoneInfo activatorPhoneInfo, ActivatorPhoneInfo activatorPhoneInfo2);

        void onNone();

        void onSingleSIM(ActivatorPhoneInfo activatorPhoneInfo);
    }

    public ActivatorPhoneController(Context context) {
        this.mPhoneNumKeeper = new PhoneNumKeeperFactory().a(context, APP_ID);
        this.mPhoneNumKeeper.a((PhoneNumKeeper.SetupFinishedListener) new PhoneNumKeeper.SetupFinishedListener() {
            public void onSetupFinished(Error error) {
                AccountLog.i(ActivatorPhoneController.TAG, "setup" + error.toString());
            }
        });
    }

    public List<ActivatorPhoneInfo> peekPhoneNumLevelData() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.mPhoneNumKeeper.a(); i++) {
            this.mPhoneNumKeeper.a(i, PhoneLevel.DATA);
            PhoneNum b = this.mPhoneNumKeeper.b(i, PhoneLevel.DATA);
            if (!(b == null || b.f == null)) {
                arrayList.add(new ActivatorPhoneInfo.Builder().phone(b.c).phoneHash(b.d).activatorToken(b.f).slotId(i).copyWriter(b.i).operatorLink(b.j).build());
            }
        }
        return arrayList;
    }

    public SimpleFutureTask<List<ActivatorPhoneInfo>> getLocalActivatorPhone(final PhoneNumCallback phoneNumCallback, final boolean z) {
        if (phoneNumCallback != null) {
            this.mGetActivatorPhoneTask = new SimpleFutureTask<>(new Callable<List<ActivatorPhoneInfo>>() {
                public List<ActivatorPhoneInfo> call() throws Exception {
                    int a2 = ActivatorPhoneController.this.mPhoneNumKeeper.a();
                    ArrayList arrayList = new ArrayList();
                    for (int i = 0; i < a2; i++) {
                        if (!z) {
                            ActivatorPhoneController.this.mPhoneNumKeeper.c(i);
                        }
                        PhoneNum phoneNum = ActivatorPhoneController.this.mPhoneNumKeeper.a(i).get(ActivatorPhoneController.MILLI_TIME_OUT_GET_ACTIVATOR_PHONE, TimeUnit.MILLISECONDS);
                        if (phoneNum.f12553a == 0) {
                            arrayList.add(new ActivatorPhoneInfo.Builder().phone(phoneNum.c).phoneHash(phoneNum.d).activatorToken(phoneNum.f).slotId(i).copyWriter(phoneNum.i).operatorLink(phoneNum.j).build());
                        } else {
                            AccountLog.w(ActivatorPhoneController.TAG, "getLocalActivatorPhone: " + phoneNum);
                        }
                    }
                    return arrayList;
                }
            }, new SimpleFutureTask.Callback<List<ActivatorPhoneInfo>>() {
                public void call(SimpleFutureTask<List<ActivatorPhoneInfo>> simpleFutureTask) {
                    try {
                        List list = (List) simpleFutureTask.get();
                        if (list != null) {
                            if (list.size() != 0) {
                                switch (list.size()) {
                                    case 0:
                                        AccountLog.i(ActivatorPhoneController.TAG, "no activator phone");
                                        phoneNumCallback.onNone();
                                        return;
                                    case 1:
                                        AccountLog.i(ActivatorPhoneController.TAG, "one activator phone");
                                        phoneNumCallback.onSingleSIM((ActivatorPhoneInfo) list.get(0));
                                        return;
                                    case 2:
                                        AccountLog.i(ActivatorPhoneController.TAG, "two activator phone");
                                        phoneNumCallback.onDualSIM((ActivatorPhoneInfo) list.get(0), (ActivatorPhoneInfo) list.get(1));
                                        return;
                                    default:
                                        throw new RuntimeException("should not happen");
                                }
                            }
                        }
                        AccountLog.i(ActivatorPhoneController.TAG, "no inserted phone");
                        phoneNumCallback.onNone();
                    } catch (InterruptedException e) {
                        AccountLog.e(ActivatorPhoneController.TAG, "getLocalActivatorPhone", e);
                        phoneNumCallback.onNone();
                    } catch (ExecutionException e2) {
                        AccountLog.e(ActivatorPhoneController.TAG, "getLocalActivatorPhone", e2);
                        phoneNumCallback.onNone();
                    }
                }
            });
            XiaomiPassportExecutor.getSingleton().execute(this.mGetActivatorPhoneTask);
            return this.mGetActivatorPhoneTask;
        }
        throw new IllegalArgumentException("get phone num callback should not be null");
    }

    public void invalidateCathePhoneNum(int i) {
        this.mPhoneNumKeeper.c(i);
    }

    public void cancel() {
        if (this.mGetActivatorPhoneTask != null) {
            this.mGetActivatorPhoneTask.cancel(true);
            this.mGetActivatorPhoneTask = null;
        }
        this.mPhoneNumKeeper.b();
    }
}
