package com.xiaomi.payment.channel.model;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import com.mibi.common.base.Model;
import com.mibi.common.data.Session;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.data.Utils;
import com.xiaomi.payment.channel.TyUnicomSMSCodeFragment;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.ui.fragment.GetPhoneNumberFragment;
import junit.framework.Assert;

public class TyUnicomOrderModel extends Model implements IMSGOrderModel {

    /* renamed from: a  reason: collision with root package name */
    public static final int f12196a = 0;
    private static final String b = "TyUnicomOrderModel";
    private static final int c = 0;
    private static final int d = 1;
    private IMSGOrderListener e;
    private String f;
    private long g;

    public TyUnicomOrderModel(Session session) {
        super(session);
    }

    public void a(SortedParameter sortedParameter, Activity activity, IMSGOrderListener iMSGOrderListener) {
        Assert.assertNotNull(sortedParameter);
        Assert.assertNotNull(activity);
        Assert.assertNotNull(iMSGOrderListener);
        this.e = iMSGOrderListener;
        this.f = sortedParameter.f(MibiConstants.eZ);
        this.g = sortedParameter.d(MibiConstants.f12224de);
        d();
    }

    private void d() {
        String i = Utils.i(l_());
        if (!TextUtils.isEmpty(i)) {
            a(this.f, this.g, i);
        } else {
            e();
        }
    }

    private void e() {
        Bundle bundle = new Bundle();
        bundle.putSerializable(MibiConstants.cR, GetPhoneNumberFragment.class);
        this.e.a(bundle);
    }

    private void a(String str, long j, String str2) {
        if (TextUtils.isEmpty(str) || j == 0 || TextUtils.isEmpty(str2)) {
            throw new IllegalArgumentException("phoneNum is empty or denominationMoney(payId) is not right");
        }
        Bundle bundle = new Bundle();
        bundle.putString(MibiConstants.eZ, str);
        bundle.putString(MibiConstants.dl, str2);
        bundle.putSerializable(MibiConstants.cR, TyUnicomSMSCodeFragment.class);
        this.e.a(bundle);
    }

    public void a(int i, int i2, Bundle bundle) {
        if (i == 0 && i2 == -1) {
            a(this.f, this.g, bundle.getString(MibiConstants.dl));
        } else if (i == 1 && i2 == -1) {
            this.e.a(new Bundle());
        }
    }
}
