package com.xiaomi.payment.deduct.model;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import com.mibi.common.data.MarketUtils;
import com.mibi.common.data.Session;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.rxjava.RxBaseErrorHandleTaskListener;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.deduct.DeductManager;
import com.xiaomi.payment.deduct.contract.DoDeductContract;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.task.rxjava.RxDoMipayDeductTask;
import com.xiaomi.payment.task.rxjava.RxUploadResponseTask;
import junit.framework.Assert;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MipayDeductModel extends DeductModel {
    private static final String b = "MipayDeductModel";
    private static final int c = 100;
    private static final String d = "com.mipay.wallet";
    private static final String e = "app.mipay.com";
    private static final String f = "https";
    private static final String g = "mipay.partnerAutoPay";

    public MipayDeductModel(Session session, String str) {
        super(session, str);
    }

    public void d() {
        if (TextUtils.isEmpty((String) c().m().c(e(), "responseData"))) {
            g();
        } else {
            h();
        }
    }

    private void g() {
        RxDoMipayDeductTask rxDoMipayDeductTask = new RxDoMipayDeductTask(l_(), c());
        MipayDeductListener mipayDeductListener = new MipayDeductListener(l_());
        SortedParameter sortedParameter = new SortedParameter();
        sortedParameter.a("processId", (Object) e());
        rxDoMipayDeductTask.a(sortedParameter);
        Observable.create(rxDoMipayDeductTask).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(mipayDeductListener);
    }

    private void h() {
        RxUploadResponseTask rxUploadResponseTask = new RxUploadResponseTask(l_(), c());
        UploadDataListener uploadDataListener = new UploadDataListener(l_());
        String str = (String) c().m().c(e(), "responseData");
        Assert.assertNotNull(str);
        SortedParameter sortedParameter = new SortedParameter();
        sortedParameter.a("processId", (Object) e());
        sortedParameter.a("responseData", (Object) str);
        rxUploadResponseTask.a(sortedParameter);
        Observable.create(rxUploadResponseTask).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(uploadDataListener);
    }

    private class UploadDataListener extends RxBaseErrorHandleTaskListener<RxUploadResponseTask.Result> {
        private UploadDataListener(Context context) {
            super(context);
        }

        /* access modifiers changed from: protected */
        public void a(int i, String str, Throwable th) {
            MipayDeductModel.this.f().a(201, str);
            MipayDeductModel.this.c().m().a(MipayDeductModel.this.e(), "responseData");
        }

        /* access modifiers changed from: protected */
        public void a(RxUploadResponseTask.Result result) {
            MipayDeductModel.this.i();
        }
    }

    /* access modifiers changed from: private */
    public void i() {
        Bundle bundle = new Bundle();
        bundle.putString("processId", e());
        bundle.putString(MibiConstants.gA, DeductManager.CHANNELS.MIPAY.getChannel());
        f().a(bundle);
        c().m().a(e(), "responseData");
    }

    private class MipayDeductListener extends RxBaseErrorHandleTaskListener<RxDoMipayDeductTask.Result> {
        private MipayDeductListener(Context context) {
            super(context);
        }

        /* access modifiers changed from: protected */
        public void a(int i, String str, Throwable th) {
            super.a(i, str, th);
            MipayDeductModel.this.f().a(201, str);
        }

        /* access modifiers changed from: protected */
        public void a(final RxDoMipayDeductTask.Result result) {
            super.a(result);
            if (!TextUtils.isEmpty(result.mRequestData)) {
                MipayDeductModel.this.f().a((DoDeductContract.Function<Fragment>) new DoDeductContract.Function<Fragment>() {
                    public void a(Fragment fragment) {
                        if (MarketUtils.a(fragment.getActivity(), "com.mipay.wallet")) {
                            fragment.startActivityForResult(MipayDeductListener.this.b(result), 100);
                            Log.d(MipayDeductModel.b, "start Mipay deduct success");
                            return;
                        }
                        MipayDeductModel.this.f().a(201, fragment.getActivity().getResources().getString(R.string.mibi_error_mipay_no_installed));
                        Log.d(MipayDeductModel.b, "start Mipay deduct failed");
                    }
                });
            } else {
                MipayDeductModel.this.i();
            }
        }

        /* access modifiers changed from: private */
        public Intent b(@NonNull RxDoMipayDeductTask.Result result) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setPackage("com.mipay.wallet");
            Uri.Builder builder = new Uri.Builder();
            builder.scheme("https");
            builder.authority("app.mipay.com");
            builder.appendQueryParameter("id", MipayDeductModel.g);
            builder.appendQueryParameter("requestData", result.mRequestData);
            builder.appendQueryParameter("merchantName", result.mMerchant);
            builder.appendQueryParameter(MibiConstants.gE, result.mCancelMerchant);
            builder.appendQueryParameter(MibiConstants.gL, result.mGoodsName);
            intent.setData(builder.build());
            return intent;
        }
    }

    public void a(int i, int i2, Bundle bundle) {
        super.a(i, i2, bundle);
        if (i != 100) {
            return;
        }
        if (-1 == i2 && bundle != null) {
            Bundle bundle2 = bundle.getBundle("result");
            Assert.assertNotNull(bundle2);
            c().m().a(e(), "responseData", (Object) bundle2.getString("responseData"));
            h();
        } else if (i2 != 0) {
            f().a(201, "mipay deduct failed");
        } else if (bundle != null) {
            f().a(202, bundle.getString("message"));
        } else {
            f().a(202, "user cancel mipay deduct");
        }
    }
}
