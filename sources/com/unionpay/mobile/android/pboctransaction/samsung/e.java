package com.unionpay.mobile.android.pboctransaction.samsung;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.unionpay.mobile.android.utils.k;
import com.unionpay.tsmservice.ITsmCallback;
import com.unionpay.tsmservice.data.Constant;
import com.unionpay.tsmservice.result.CheckSSamsungPayResult;
import com.unionpay.tsmservice.result.GetCardInfoBySpayResult;
import com.unionpay.tsmservice.result.GetSeAppListResult;
import com.unionpay.tsmservice.result.InitResult;
import com.unionpay.tsmservice.result.OpenChannelResult;
import com.unionpay.tsmservice.result.SendApduResult;
import com.unionpay.tsmservice.result.VendorPayStatusResult;

public final class e extends ITsmCallback.Stub {

    /* renamed from: a  reason: collision with root package name */
    private int f9657a;
    private Handler b;

    public e(int i, Handler handler) {
        this.f9657a = i;
        this.b = handler;
    }

    public final void onError(String str, String str2) throws RemoteException {
        Handler handler;
        Message obtain;
        Log.e("uppay", "errorCode:" + str + ", errorDesc:" + str2);
        if (!"1003700023".equals(str) || this.f9657a != 1018) {
            handler = this.b;
            obtain = Message.obtain(this.b, 1, this.f9657a, 0, str);
        } else {
            k.c("uppay", "error 100370023 from get vendor pay status");
            Bundle bundle = new Bundle();
            bundle.putBoolean("KEY_SUCCESS_VENDOR", false);
            handler = this.b;
            obtain = Message.obtain(this.b, 1018, bundle);
        }
        handler.sendMessage(obtain);
    }

    public final void onResult(Bundle bundle) throws RemoteException {
        int i = this.f9657a;
        if (i == 1000) {
            bundle.setClassLoader(InitResult.class.getClassLoader());
            this.b.sendMessage(Message.obtain(this.b, 1000, bundle));
        } else if (i != 1018) {
            switch (i) {
                case 1011:
                    bundle.setClassLoader(OpenChannelResult.class.getClassLoader());
                    OpenChannelResult openChannelResult = (OpenChannelResult) bundle.get("result");
                    String channel = openChannelResult.getChannel();
                    String outHexApdu = openChannelResult.getOutHexApdu();
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("channel", channel);
                    bundle2.putString("apdu", outHexApdu);
                    this.b.sendMessage(Message.obtain(this.b, 1011, bundle2));
                    return;
                case 1012:
                    bundle.setClassLoader(SendApduResult.class.getClassLoader());
                    this.b.sendMessage(Message.obtain(this.b, 1012, ((SendApduResult) bundle.get("result")).getOutHexApdu()));
                    return;
                case 1013:
                    this.b.sendMessage(Message.obtain(this.b, 1013, ""));
                    return;
                case 1014:
                    bundle.setClassLoader(GetSeAppListResult.class.getClassLoader());
                    this.b.sendMessage(Message.obtain(this.b, 1014, (GetSeAppListResult) bundle.get("result")));
                    return;
                case 1015:
                    bundle.setClassLoader(GetCardInfoBySpayResult.class.getClassLoader());
                    this.b.sendMessage(Message.obtain(this.b, 1015, ((GetCardInfoBySpayResult) bundle.get("result")).getVirtualCardInfo()));
                    return;
                case 1016:
                    k.c("uppay-spay", "check spay support callback");
                    bundle.setClassLoader(CheckSSamsungPayResult.class.getClassLoader());
                    bundle.get("result");
                    this.b.sendMessage(Message.obtain(this.b, 1016, ""));
                    return;
                default:
                    return;
            }
        } else {
            k.c("uppay-spay", "get vendor pay status callback");
            bundle.setClassLoader(VendorPayStatusResult.class.getClassLoader());
            Bundle vendorPayStatusResult = ((VendorPayStatusResult) bundle.get("result")).getVendorPayStatusResult();
            vendorPayStatusResult.putBoolean("KEY_SUCCESS_VENDOR", true);
            this.b.sendMessage(Message.obtain(this.b, 1018, vendorPayStatusResult));
            k.c("unpay", "result vendorPayStatusResult max card num reached:" + vendorPayStatusResult.getBoolean(Constant.KEY_MAX_CARD_NUM_REACHED));
        }
    }
}
