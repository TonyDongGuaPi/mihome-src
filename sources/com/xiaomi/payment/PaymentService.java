package com.xiaomi.payment;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import com.mibi.common.account.AccountRegistry;
import com.mibi.common.account.AccountUtils;
import com.mibi.common.base.BasePaymentTaskAdapter;
import com.mibi.common.base.BaseService;
import com.mibi.common.base.TaskManager;
import com.mibi.common.data.MibiSdkConstants;
import com.mibi.common.data.PaymentResponse;
import com.mibi.common.data.Session;
import com.mibi.common.data.SessionManager;
import com.mibi.common.data.SortedParameter;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.data.UserEnterUtils;
import com.xiaomi.payment.task.GetBalanceTask;
import miuipub.payment.IPaymentManagerResponse;
import miuipub.payment.IPaymentManagerService;

public class PaymentService extends BaseService {

    /* renamed from: a  reason: collision with root package name */
    private IPaymentManagerService f12147a;

    public IBinder onBind(Intent intent) {
        if (this.f12147a == null) {
            this.f12147a = new ServiceImpl(this);
        }
        return this.f12147a.asBinder();
    }

    private class ServiceImpl extends IPaymentManagerService.Stub {
        private long mCallTimestamp = 0;
        private Context mContext;

        public ServiceImpl(Context context) {
            this.mContext = context;
        }

        public void payOrder(IPaymentManagerResponse iPaymentManagerResponse, Account account, String str, String str2, boolean z, boolean z2, boolean z3, Bundle bundle) throws RemoteException {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.mCallTimestamp < 1000) {
                iPaymentManagerResponse.onError(14, "call too fast", new Bundle());
                return;
            }
            this.mCallTimestamp = currentTimeMillis;
            if (TextUtils.isEmpty(str)) {
                iPaymentManagerResponse.onError(2, "order is empty", new Bundle());
                return;
            }
            boolean z4 = bundle.getBoolean("payment_is_no_account", false);
            boolean z5 = bundle.getBoolean(MibiConstants.gK, false);
            Bundle bundle2 = new Bundle();
            bundle2.putParcelable(MibiSdkConstants.F, new PaymentResponse(iPaymentManagerResponse));
            bundle2.putString(MibiConstants.cT, str);
            bundle2.putBoolean("payment_is_no_account", z4);
            bundle2.putBoolean(MibiConstants.gK, z5);
            bundle2.putBoolean(MibiConstants.cY, true);
            bundle2.putString(MibiConstants.cX, str2);
            bundle2.putBoolean(MibiConstants.eD, z);
            bundle2.putBoolean(MibiConstants.eE, z2);
            bundle2.putBoolean(MibiConstants.eG, z3);
            bundle2.putBundle(MibiConstants.cU, bundle);
            PaymentService.this.a(iPaymentManagerResponse, bundle2);
        }

        public void payForOrder(IPaymentManagerResponse iPaymentManagerResponse, Account account, String str, Bundle bundle) throws RemoteException {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.mCallTimestamp < 1000) {
                iPaymentManagerResponse.onError(14, "call too fast", new Bundle());
                return;
            }
            this.mCallTimestamp = currentTimeMillis;
            if (TextUtils.isEmpty(str)) {
                iPaymentManagerResponse.onError(2, "order is empty", new Bundle());
                return;
            }
            boolean z = bundle.getBoolean("payment_is_no_account", false);
            boolean z2 = bundle.getBoolean(MibiConstants.gK, false);
            Bundle bundle2 = new Bundle();
            bundle2.putParcelable(MibiSdkConstants.F, new PaymentResponse(iPaymentManagerResponse));
            bundle2.putString(MibiConstants.cT, str);
            bundle2.putBoolean("payment_is_no_account", z);
            bundle2.putBoolean(MibiConstants.gK, z2);
            bundle2.putBundle(MibiConstants.cU, bundle);
            PaymentService.this.a(iPaymentManagerResponse, bundle2);
        }

        @Deprecated
        public void showMiliCenter(IPaymentManagerResponse iPaymentManagerResponse, Account account) throws RemoteException {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setPackage(this.mContext.getPackageName());
            intent.setData(Uri.parse("https://publish.app.mibi.xiaomi.com?id=mibi.milicenter"));
            PaymentService.this.a(iPaymentManagerResponse, intent);
        }

        @Deprecated
        public void showRechargeRecord(IPaymentManagerResponse iPaymentManagerResponse, Account account, String str, String str2) throws RemoteException {
            showBillRecord(iPaymentManagerResponse);
        }

        @Deprecated
        public void showPayRecord(IPaymentManagerResponse iPaymentManagerResponse, Account account, String str, String str2) throws RemoteException {
            showBillRecord(iPaymentManagerResponse);
        }

        private void showBillRecord(IPaymentManagerResponse iPaymentManagerResponse) throws RemoteException {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setPackage(this.mContext.getPackageName());
            intent.setData(Uri.parse("https://publish.app.mibi.xiaomi.com?id=mibi.billRecord"));
            PaymentService.this.a(iPaymentManagerResponse, intent);
        }

        public void recharge(IPaymentManagerResponse iPaymentManagerResponse, Account account, String str, String str2) throws RemoteException {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setPackage(this.mContext.getPackageName());
            intent.setData(Uri.parse("https://publish.app.mibi.xiaomi.com?id=mibi.recharge"));
            PaymentService.this.a(iPaymentManagerResponse, intent);
        }

        public void getMiliBalance(IPaymentManagerResponse iPaymentManagerResponse, Account account, String str, String str2) throws RemoteException {
            if (TextUtils.isEmpty(str)) {
                iPaymentManagerResponse.onError(2, "service id is empty", new Bundle());
            } else if (TextUtils.isEmpty(str2)) {
                iPaymentManagerResponse.onError(2, "verify is empty", new Bundle());
            } else {
                Account[] accountsByType = AccountRegistry.a().getAccountsByType("com.xiaomi");
                if (accountsByType.length == 0) {
                    iPaymentManagerResponse.onError(2, "account is null", new Bundle());
                    return;
                }
                Session a2 = SessionManager.a(this.mContext, AccountUtils.a(accountsByType[0]), new PaymentResponse(iPaymentManagerResponse));
                this.mContext.getPackageManager();
                if (!UserEnterUtils.a(this.mContext)) {
                    iPaymentManagerResponse.onError(0, "cannot get balance", new Bundle());
                } else {
                    new GetBalanceTaskAdapter(PaymentService.this.getApplicationContext(), a2, PaymentService.this.getTaskManager()).start(str, str2);
                }
            }
        }
    }

    private class GetBalanceTaskAdapter extends BasePaymentTaskAdapter<GetBalanceTask, Void, GetBalanceTask.Result> {
        private Session f;
        private String g;
        private String h;

        public GetBalanceTaskAdapter(Context context, Session session, TaskManager taskManager) {
            super(context, taskManager, new GetBalanceTask(context, session));
            this.f = session;
        }

        public void start(String str, String str2) {
            this.g = str;
            this.h = str2;
            start();
        }

        /* access modifiers changed from: protected */
        public SortedParameter j() {
            SortedParameter sortedParameter = new SortedParameter();
            sortedParameter.a(MibiConstants.dP, (Object) this.g);
            sortedParameter.a(MibiConstants.dO, (Object) this.h);
            return sortedParameter;
        }

        /* access modifiers changed from: protected */
        public void a(String str, int i, GetBalanceTask.Result result) {
            this.f.b(i, str);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void c(GetBalanceTask.Result result) {
            Bundle bundle = new Bundle();
            bundle.putLong("payment_trade_balance", result.mBalance);
            this.f.a(bundle);
        }
    }

    /* access modifiers changed from: private */
    public void a(IPaymentManagerResponse iPaymentManagerResponse, Bundle bundle) {
        Intent intent = new Intent(this, PaymentOrderResponseEntryActivity.class);
        intent.putExtra(MibiConstants.cS, bundle);
        PaymentResponse paymentResponse = new PaymentResponse(iPaymentManagerResponse);
        Bundle bundle2 = new Bundle();
        bundle2.putParcelable("intent", intent);
        paymentResponse.a(bundle2);
    }

    /* access modifiers changed from: private */
    public void a(IPaymentManagerResponse iPaymentManagerResponse, Intent intent) {
        PaymentResponse paymentResponse = new PaymentResponse(iPaymentManagerResponse);
        Bundle bundle = new Bundle();
        bundle.putParcelable("intent", intent);
        paymentResponse.a(bundle);
    }
}
