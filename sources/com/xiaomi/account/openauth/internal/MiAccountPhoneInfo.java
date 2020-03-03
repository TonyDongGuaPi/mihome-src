package com.xiaomi.account.openauth.internal;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.xiaomi.account.auth.PhoneInfo;
import com.xiaomi.account.openauth.AuthorizeActivityBase;
import com.xiaomi.account.utils.Base64Coder;
import com.xiaomi.phonenum.PhoneNumKeeper;
import com.xiaomi.phonenum.PhoneNumKeeperFactory;
import com.xiaomi.phonenum.bean.Error;
import com.xiaomi.phonenum.bean.PhoneNum;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MiAccountPhoneInfo implements PhoneInfo {
    private String TAG = "MiAccountPhoneInfo";
    private Context mContext;
    private PhoneNumKeeper mPhoneNumKeeper;
    private long timeOutMs = 2000;

    public MiAccountPhoneInfo(Context context, long j) {
        this.mContext = context;
        this.timeOutMs = j;
        this.mPhoneNumKeeper = new PhoneNumKeeperFactory().a(this.mContext, "");
        this.mPhoneNumKeeper.a((PhoneNumKeeper.SetupFinishedListener) new PhoneNumKeeper.SetupFinishedListener() {
            public void onSetupFinished(Error error) {
            }
        });
    }

    public Bundle blokingGetPhoneInfo(int i) {
        Bundle bundle = new Bundle();
        try {
            PhoneNum phoneNum = this.mPhoneNumKeeper.a(i).get(this.timeOutMs, TimeUnit.MILLISECONDS);
            if (phoneNum == null || phoneNum.f12553a != 0) {
                String str = this.TAG;
                Log.i(str, "blokingGetPhoneInfo " + phoneNum);
            } else {
                bundle.putString(AuthorizeActivityBase.KEY_ACTIVATORTOKEN, "activatorToken=" + phoneNum.f);
                bundle.putString("hash", "hash=" + phoneNum.d);
                bundle.putString(AuthorizeActivityBase.KEY_OPERATOR, "operator=" + Base64Coder.encodeString(phoneNum.i));
                bundle.putString(AuthorizeActivityBase.KEY_OPERATORLINK, "operatorLink=" + phoneNum.j);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e2) {
            e2.printStackTrace();
        } catch (TimeoutException e3) {
            e3.printStackTrace();
        }
        return bundle;
    }
}
