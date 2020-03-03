package com.mobikwik.sdk;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import cn.com.fmsh.nfcos.client.service.constants.Constants;
import com.mobikwik.sdk.lib.Constants;
import com.mobikwik.sdk.lib.SDKErrorCodes;
import com.mobikwik.sdk.lib.Transaction;
import com.mobikwik.sdk.lib.TransactionConfiguration;
import com.mobikwik.sdk.lib.payinstrument.PaymentInstrumentType;
import com.mobikwik.sdk.lib.utils.CommonUtils;
import com.mobikwik.sdk.lib.utils.Network;
import com.mobikwik.sdk.lib.utils.Utils;
import com.mobikwik.sdk.lib.wallet.WalletAPIs;
import com.mobikwik.sdk.ui.data.b;
import com.mobikwik.sdk.ui.frag.a;
import com.mobikwik.sdk.ui.frag.d;
import com.mobikwik.sdk.ui.frag.f;
import com.mobikwik.sdk.ui.frag.m;
import com.mobikwik.sdk.ui.frag.o;
import com.mobikwik.sdk.ui.frag.t;
import com.mobikwik.sdk.ui.frag.y;
import com.xiaomi.zxing.integration.android.IntentIntegrator;

public class PaymentActivity extends FragmentActivity implements d.a, f.a, o.b, t.a {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public ProgressDialog f8357a;
    private Runnable b;
    private WalletAPIs c;
    /* access modifiers changed from: private */
    public b d;
    private TransactionConfiguration e;

    private void a(Intent intent) {
        PaymentInstrumentType paymentInstrumentType = (PaymentInstrumentType) intent.getSerializableExtra(Constants.DATA_EXTRA);
        intent.removeExtra(Constants.DATA_EXTRA);
        if (PaymentInstrumentType.SAVED_CARD.equals(paymentInstrumentType)) {
            b();
        } else if (PaymentInstrumentType.CREDIT_CARD.equals(paymentInstrumentType)) {
            c();
        } else if (PaymentInstrumentType.DEBIT_CARD.equals(paymentInstrumentType)) {
            d();
        } else if (PaymentInstrumentType.NETBANKING.equals(paymentInstrumentType)) {
            e();
        } else if (PaymentInstrumentType.MK_WALLET.equals(paymentInstrumentType)) {
            f();
        }
    }

    /* access modifiers changed from: private */
    public void a(Intent intent, String str, String str2) {
        Intent intent2 = new Intent();
        if (intent != null) {
            intent2 = new Intent(intent);
        }
        if (Utils.isNull(intent2.getStringExtra(Constants.STATUS_CODE))) {
            if (Utils.isNull(str)) {
                intent2.putExtra(Constants.STATUS_CODE, "1");
            } else {
                intent2.putExtra(Constants.STATUS_CODE, str);
            }
        }
        if (Utils.isNull(intent2.getStringExtra(Constants.STATUS_MSG))) {
            if (Utils.isNull(str)) {
                intent2.putExtra(Constants.STATUS_MSG, "Something went bad");
            } else {
                intent2.putExtra(Constants.STATUS_MSG, str2);
            }
        }
        setResult(1, intent2);
        finish();
    }

    private void a(String str) {
        b b2 = b.b((Context) this);
        String str2 = str;
        this.c.getUserBalance(b2.d().getUser().getEmail(), b2.d().getUser().getCell(), b2.d().getOrderId(), b2.d().getAmount(), b2.f().getMbkId(), b.b((Context) this).i(), true, str2, true, !b2.f().isDebitWallet(), b2.f().getPgResponseUrl(), b2.f().getMerchantName(), new m(this, b2));
    }

    /* access modifiers changed from: private */
    public void a(boolean z, String str) {
        f fVar = new f();
        Bundle bundle = new Bundle();
        bundle.putString("MSG", str);
        if (z) {
            bundle.putString("NEWUSER", "true");
        }
        fVar.setArguments(bundle);
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.payment_frame, fVar);
        beginTransaction.commit();
    }

    private void b() {
        o oVar = new o();
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.payment_frame, oVar);
        beginTransaction.commit();
    }

    private void c() {
        a aVar = new a();
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.payment_frame, aVar);
        beginTransaction.commit();
    }

    private void d() {
        d dVar = new d();
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.payment_frame, dVar);
        beginTransaction.commit();
    }

    private void e() {
        m mVar = new m();
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.payment_frame, mVar);
        beginTransaction.commit();
    }

    private void f() {
        if (Network.isConnected(this)) {
            this.f8357a = ProgressDialog.show(this, "", "Processing...", false);
            g();
        } else if (this.d.b().size() < 2) {
            a((Intent) null, SDKErrorCodes.INTERNET_NOT_WORKING.getErrorCode(), SDKErrorCodes.INTERNET_NOT_WORKING.getErrorDescription());
        }
    }

    private void g() {
        Transaction d2 = this.d.d();
        if (!Utils.isNull(d2.getChecksum())) {
            b.b((Context) this).b(d2.getChecksum());
            h();
            return;
        }
        this.c.getTransactionHash(this.e.getChecksumUrl(), d2.getOrderId(), d2.getAmount(), this.e.getMbkId(), new j(this));
    }

    /* access modifiers changed from: private */
    public void h() {
        String a2 = com.mobikwik.sdk.ui.data.a.a(this, this.d.d().getUser());
        TransactionConfiguration f = this.d.f();
        if (Utils.isNull(a2)) {
            this.c.resolveUser(this.d.d().getUser().getEmail(), this.d.d().getUser().getCell(), this.d.d().getOrderId(), this.d.d().getAmount(), this.e.getMbkId(), b.b((Context) this).i(), true, f.getPgResponseUrl(), f.getMerchantName(), new k(this));
        } else {
            a(a2);
        }
    }

    public void a() {
        if (b.b((Context) this).j()) {
            t tVar = new t();
            tVar.a(false);
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            beginTransaction.replace(R.id.payment_frame, tVar);
            beginTransaction.commit();
            return;
        }
        finish();
    }

    public void a(int i, Intent intent) {
        if (i == -1) {
            a(Double.valueOf(intent.getDoubleExtra("balance", 0.0d)));
        } else {
            a(intent, (String) null, (String) null);
        }
    }

    public void a(Bundle bundle) {
        a aVar = new a();
        aVar.setArguments(bundle);
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.payment_frame, aVar);
        beginTransaction.commit();
    }

    public void a(PaymentInstrumentType paymentInstrumentType) {
        switch (q.f8375a[paymentInstrumentType.ordinal()]) {
            case 1:
                b();
                return;
            case 2:
                c();
                return;
            case 3:
                d();
                return;
            case 4:
                e();
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void a(Double d2) {
        FragmentTransaction beginTransaction;
        b b2 = b.b((Context) this);
        Double valueOf = Double.valueOf(Double.parseDouble(b2.d().getAmount()));
        if (!b2.f().isDebitWallet() || d2.doubleValue() < Double.parseDouble(b2.d().getAmount())) {
            t tVar = new t();
            Double performArithmeticCalculations = CommonUtils.performArithmeticCalculations(valueOf, d2, 2);
            if (!b2.f().isDebitWallet()) {
                performArithmeticCalculations = valueOf;
            }
            if (performArithmeticCalculations.doubleValue() < 1.0d) {
                performArithmeticCalculations = Double.valueOf(1.0d);
            }
            b2.a(String.valueOf(performArithmeticCalculations));
            beginTransaction = getSupportFragmentManager().beginTransaction();
            beginTransaction.replace(R.id.payment_frame, tVar);
        } else {
            y yVar = new y();
            Bundle bundle = new Bundle();
            bundle.putDouble(Constants.DataBase.Column4Card.COLUMN_BALANCE, d2.doubleValue());
            yVar.setArguments(bundle);
            beginTransaction = getSupportFragmentManager().beginTransaction();
            beginTransaction.replace(R.id.payment_frame, yVar);
        }
        beginTransaction.commit();
    }

    /* access modifiers changed from: protected */
    public void a(boolean z) {
        b b2 = b.b((Context) this);
        this.c.generateOtp(b2.d().getUser().getEmail(), b2.d().getUser().getCell(), b2.d().getOrderId(), b2.d().getAmount(), b2.f().getMbkId(), b.b((Context) this).i(), b2.f().getPgResponseUrl(), b2.f().getMerchantName(), new l(this, z));
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        Utils.print("PaymentActivity.onActivityResult() " + intent.getStringExtra(com.mobikwik.sdk.lib.Constants.STATUS_CODE));
        b b2 = b.b((Context) this);
        if (i2 != 1 || !b2.j() || !SDKErrorCodes.SUCCESS.getErrorCode().equals(intent.getStringExtra(com.mobikwik.sdk.lib.Constants.STATUS_CODE)) || !b2.f().isDebitWallet()) {
            a(intent, (String) null, (String) null);
        } else {
            this.b = new p(this, intent);
        }
    }

    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false).setIcon(17301543).setTitle(com.mobikwik.sdk.lib.Constants.QUIT_PAYMENT_FLOW_TITLE).setMessage(com.mobikwik.sdk.lib.Constants.QUIT_PAYMENT_FLOW_DESC).setPositiveButton(IntentIntegrator.d, new o(this)).setNegativeButton(IntentIntegrator.e, new n(this));
        builder.create().show();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(R.layout.payment_page);
        this.d = b.b((Context) this);
        this.e = b.b((Context) this).f();
        this.c = WalletAPIs.getInstance("1".equals(this.e.getMode()), this);
        if (bundle == null) {
            a(getIntent());
        }
    }

    /* access modifiers changed from: protected */
    public void onResumeFragments() {
        super.onResumeFragments();
        if (this.b != null) {
            this.b.run();
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        b.b((Context) this).a();
    }
}
