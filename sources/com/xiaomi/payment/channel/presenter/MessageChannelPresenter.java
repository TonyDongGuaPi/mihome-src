package com.xiaomi.payment.channel.presenter;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import com.mibi.common.base.Presenter;
import com.mibi.common.data.Client;
import com.mibi.common.data.SortedParameter;
import com.xiaomi.payment.channel.model.APITelecomMSGChannelModel;
import com.xiaomi.payment.channel.model.IGetMSGInfoListener;
import com.xiaomi.payment.channel.model.IGetMSGInfoModel;
import com.xiaomi.payment.channel.model.TyUnicomMSGChannelModel;
import com.xiaomi.payment.channel.model.WoUnicomMSGChannelModel;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.recharge.MsgPayRechargeMethod;
import com.xiaomi.payment.recharge.RechargeManager;
import com.xiaomi.payment.ui.contract.MessageChannelContract;
import java.util.HashMap;
import java.util.Map;
import junit.framework.Assert;

public class MessageChannelPresenter extends Presenter<MessageChannelContract.View> implements MessageChannelContract.Presenter {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12207a = "MessageChannelPresenter";
    private Map<Long, String> b = new HashMap();
    private IGetMSGInfoModel c;
    private String d;
    /* access modifiers changed from: private */
    public String e;
    private int f;
    private int g;
    /* access modifiers changed from: private */
    public long h;
    /* access modifiers changed from: private */
    public long i;
    /* access modifiers changed from: private */
    public boolean j;
    private MsgPayRechargeMethod k;
    private IGetMSGInfoListener l = new IGetMSGInfoListener() {
        public void a(int i, boolean z) {
            ((MessageChannelContract.View) MessageChannelPresenter.this.l()).a(i, z);
        }

        public void a(int i, String str, Throwable th) {
            ((MessageChannelContract.View) MessageChannelPresenter.this.l()).a(i, str, th);
        }

        public void a(Bundle bundle) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putLong(MibiConstants.dd, MessageChannelPresenter.this.h);
            bundle.putLong(MibiConstants.f12224de, MessageChannelPresenter.this.i);
            bundle.putString("channel", MessageChannelPresenter.this.e);
            boolean unused = MessageChannelPresenter.this.j = bundle.getBoolean(MibiConstants.hj);
            if (MessageChannelPresenter.this.j) {
                ((MessageChannelContract.View) MessageChannelPresenter.this.l()).e(bundle);
            } else {
                ((MessageChannelContract.View) MessageChannelPresenter.this.l()).N();
            }
        }
    };

    public MessageChannelPresenter() {
        super(MessageChannelContract.View.class);
    }

    public void b(Bundle bundle) {
        super.b(bundle);
        this.d = n_().getString("processId");
        this.k = (MsgPayRechargeMethod) n_().getSerializable(MibiConstants.cE);
        this.b = this.k.mPayIdMap;
        this.e = this.k.mChannel;
        this.f = this.k.mMibi;
        this.g = this.k.mMoney;
    }

    public String[] n() {
        return new String[]{"android.permission.SEND_SMS", "android.permission.WRITE_EXTERNAL_STORAGE"};
    }

    public void a(Activity activity, long j2) {
        Assert.assertNotNull(activity);
        Assert.assertTrue(j2 > 0);
        this.h = j2;
        this.i = a(j2);
        String str = this.b.get(Long.valueOf(j2));
        SortedParameter sortedParameter = new SortedParameter();
        sortedParameter.a("processId", (Object) this.d);
        sortedParameter.a(MibiConstants.dq, (Object) Long.valueOf(this.i));
        sortedParameter.a(MibiConstants.eY, (Object) this.e);
        sortedParameter.a(MibiConstants.eZ, (Object) str);
        if (!TextUtils.isEmpty(str)) {
            if (TextUtils.equals(this.e, RechargeManager.CHANNELS.WOUNICOMMSGPAY.getChannel())) {
                sortedParameter.a("imei", (Object) Client.A().i());
                sortedParameter.a("mac", (Object) Client.B().a());
                this.c = new WoUnicomMSGChannelModel(f());
            } else if (TextUtils.equals(this.e, RechargeManager.CHANNELS.APITELCOMMSGPAY.getChannel())) {
                this.c = new APITelecomMSGChannelModel(f());
            } else if (TextUtils.equals(this.e, RechargeManager.CHANNELS.TYUNICOMMSGPAY.getChannel())) {
                this.c = new TyUnicomMSGChannelModel(f());
            } else {
                ((MessageChannelContract.View) l()).a(6, e().getResources().getString(R.string.mibi_msg_error), (Throwable) null);
                return;
            }
        }
        this.c.a(sortedParameter, activity, this.l);
    }

    public long a(long j2) {
        return (((long) this.g) * j2) / ((long) this.f);
    }
}
