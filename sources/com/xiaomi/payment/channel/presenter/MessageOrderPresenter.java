package com.xiaomi.payment.channel.presenter;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import com.mibi.common.base.Presenter;
import com.mibi.common.data.SortedParameter;
import com.xiaomi.payment.channel.model.APITelecomOrderModel;
import com.xiaomi.payment.channel.model.IMSGOrderListener;
import com.xiaomi.payment.channel.model.IMSGOrderModel;
import com.xiaomi.payment.channel.model.TyUnicomOrderModel;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.recharge.RechargeManager;
import com.xiaomi.payment.ui.contract.MessageOrderContract;
import junit.framework.Assert;

public class MessageOrderPresenter extends Presenter<MessageOrderContract.View> implements MessageOrderContract.Presenter {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12209a = "MessageOrderPresenter";
    private IMSGOrderModel b;
    /* access modifiers changed from: private */
    public String c;
    private Bundle d;
    /* access modifiers changed from: private */
    public String e;
    private String f;
    /* access modifiers changed from: private */
    public long g;
    /* access modifiers changed from: private */
    public long h;
    private IMSGOrderListener i = new IMSGOrderListener() {
        public void a(int i, boolean z) {
            ((MessageOrderContract.View) MessageOrderPresenter.this.l()).a(i, z);
        }

        public void a(int i, String str, Throwable th) {
            ((MessageOrderContract.View) MessageOrderPresenter.this.l()).a(i, str, th);
        }

        public void a(Bundle bundle) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putString("processId", MessageOrderPresenter.this.c);
            bundle.putLong(MibiConstants.dd, MessageOrderPresenter.this.g);
            bundle.putLong(MibiConstants.f12224de, MessageOrderPresenter.this.h);
            bundle.putString("channel", MessageOrderPresenter.this.e);
            if (((Class) bundle.getSerializable(MibiConstants.cR)) != null) {
                ((MessageOrderContract.View) MessageOrderPresenter.this.l()).e(bundle);
            } else {
                ((MessageOrderContract.View) MessageOrderPresenter.this.l()).N();
            }
        }
    };

    public MessageOrderPresenter() {
        super(MessageOrderContract.View.class);
    }

    /* access modifiers changed from: protected */
    public void b(Bundle bundle) {
        super.b(bundle);
        this.d = n_();
        this.c = this.d.getString("processId");
        this.e = this.d.getString("channel");
        this.h = this.d.getLong(MibiConstants.f12224de);
        this.g = this.d.getLong(MibiConstants.dd);
        this.f = this.d.getString(MibiConstants.dr);
    }

    public String[] n() {
        return new String[0];
    }

    public void a(Activity activity, Fragment fragment) {
        SortedParameter sortedParameter;
        if (TextUtils.equals(this.e, RechargeManager.CHANNELS.TYUNICOMMSGPAY.getChannel())) {
            String string = this.d.getString(MibiConstants.eZ);
            sortedParameter = new SortedParameter();
            sortedParameter.a(MibiConstants.eZ, (Object) string);
            this.b = new TyUnicomOrderModel(f());
        } else if (TextUtils.equals(this.e, RechargeManager.CHANNELS.APITELCOMMSGPAY.getChannel())) {
            if (TextUtils.isEmpty(this.f)) {
                ((MessageOrderContract.View) l()).a(6, e().getResources().getString(R.string.mibi_msg_error), (Throwable) null);
            }
            String string2 = this.d.getString(MibiConstants.fo);
            String string3 = this.d.getString(MibiConstants.fp);
            SortedParameter sortedParameter2 = new SortedParameter();
            sortedParameter2.a(MibiConstants.fo, (Object) string2);
            sortedParameter2.a(MibiConstants.fp, (Object) string3);
            this.b = new APITelecomOrderModel(f());
            sortedParameter = sortedParameter2;
        } else {
            ((MessageOrderContract.View) l()).a(6, e().getResources().getString(R.string.mibi_msg_error), (Throwable) null);
            return;
        }
        Assert.assertNotNull(this.b);
        this.b.a(sortedParameter, activity, this.i);
    }

    public void a(int i2, int i3, Bundle bundle) {
        super.a(i2, i3, bundle);
        ((MessageOrderContract.View) l()).a(0, false);
        this.b.a(i2, i3, bundle);
    }
}
