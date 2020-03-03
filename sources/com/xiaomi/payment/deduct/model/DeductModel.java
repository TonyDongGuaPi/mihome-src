package com.xiaomi.payment.deduct.model;

import android.app.Fragment;
import android.os.Bundle;
import com.mibi.common.base.Model;
import com.mibi.common.data.Session;
import com.xiaomi.payment.deduct.contract.DoDeductContract;
import junit.framework.Assert;

public abstract class DeductModel extends Model {

    /* renamed from: a  reason: collision with root package name */
    public static final String f12262a = "deductStart";
    private String b;
    private IDeductListener c;

    public interface IDeductListener {
        void a(int i, String str);

        void a(Bundle bundle);

        void a(DoDeductContract.Function<Fragment> function);
    }

    public void a(int i, int i2, Bundle bundle) {
    }

    public abstract void d();

    public DeductModel(Session session, String str) {
        super(session);
        Assert.assertNotNull(str);
        this.b = str;
    }

    /* access modifiers changed from: protected */
    public String e() {
        return this.b;
    }

    /* access modifiers changed from: protected */
    public void a(String str) {
        this.b = str;
    }

    public void a(IDeductListener iDeductListener) {
        this.c = iDeductListener;
    }

    public IDeductListener f() {
        return this.c;
    }
}
