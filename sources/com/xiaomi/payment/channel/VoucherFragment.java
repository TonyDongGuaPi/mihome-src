package com.xiaomi.payment.channel;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.mibi.common.base.IPresenter;
import com.mibi.common.base.StepFragment;
import com.mibi.common.component.FormattableEditText;
import com.mibi.common.data.FormatterUtils;
import com.mibi.common.data.MemoryStorage;
import com.mibi.common.data.MistatisticUtils;
import com.mibi.common.ui.fragment.BaseProcessFragment;
import com.xiaomi.payment.channel.presenter.VoucherChannelPresenter;
import com.xiaomi.payment.data.EntryData;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.entry.EntryManager;
import com.xiaomi.payment.entry.IEntry;
import com.xiaomi.payment.entryData.CameraIntentData;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.recharge.VoucherPayRechargeMethod;
import com.xiaomi.payment.ui.contract.VoucherContract;
import java.util.ArrayList;

public class VoucherFragment extends BaseProcessFragment implements VoucherContract.View {
    private static String v = "VoucherFragment";
    private static int w = 31;
    private TextView A;
    private String B;
    private boolean C;
    private VoucherPayRechargeMethod D;
    /* access modifiers changed from: private */
    public long E;
    /* access modifiers changed from: private */
    public long F;
    private ArrayList<Integer> G;
    private String H;
    /* access modifiers changed from: private */
    public EntryData I;
    private View.OnClickListener J = new View.OnClickListener() {
        public void onClick(View view) {
            VoucherFragment.this.N();
        }
    };
    private View.OnClickListener K = new View.OnClickListener() {
        public void onClick(View view) {
            String a2 = FormatterUtils.a(VoucherFragment.this.x.getText().toString().trim(), FormatterUtils.FormatterType.TYPE_NORMAL);
            if (TextUtils.isEmpty(a2) || ((long) a2.length()) < VoucherFragment.this.E || ((long) a2.length()) > VoucherFragment.this.F) {
                Toast.makeText(VoucherFragment.this.getActivity(), R.string.mibi_voucher_error_password, 0).show();
                return;
            }
            VoucherFragment.this.z.setClickable(false);
            ((VoucherChannelPresenter) VoucherFragment.this.H_()).a(a2);
        }
    };
    /* access modifiers changed from: private */
    public FormattableEditText x;
    private ImageView y;
    /* access modifiers changed from: private */
    public Button z;

    public View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.mibi_voucher, (ViewGroup) null);
        this.x = (FormattableEditText) inflate.findViewById(R.id.voucher_edit);
        this.x.setFormatType(FormatterUtils.FormatterType.TYPE_NORMAL);
        this.x.setMaxLength((int) this.F);
        this.z = (Button) inflate.findViewById(R.id.button_recharge);
        this.y = (ImageView) inflate.findViewById(R.id.camera_btn);
        this.A = (TextView) inflate.findViewById(R.id.contentHint);
        return inflate;
    }

    /* access modifiers changed from: protected */
    public void b_(Bundle bundle) {
        super.b_(bundle);
        this.D = (VoucherPayRechargeMethod) bundle.getSerializable(MibiConstants.cE);
        this.B = this.D.mTitle;
        this.E = this.D.mMinLen;
        this.F = this.D.mMaxLen;
        this.G = new ArrayList<>();
        for (long j = this.E; j <= this.F; j++) {
            this.G.add(new Integer((int) j));
        }
        this.H = this.D.mContentHint;
        this.I = this.D.mContentHintEntryData;
    }

    /* access modifiers changed from: protected */
    public void a(MemoryStorage memoryStorage) {
        super.a(memoryStorage);
        if (!TextUtils.isEmpty(this.t)) {
            this.C = memoryStorage.d(this.t, MibiConstants.cZ);
        }
    }

    public void c(Bundle bundle) {
        super.c(bundle);
        a((CharSequence) this.B);
        b(R.string.mibi_btn_prev);
        this.z.setOnClickListener(this.K);
        if (EntryManager.a().a((Context) getActivity(), MibiConstants.cB)) {
            this.y.setVisibility(0);
            this.y.setOnClickListener(this.J);
        } else {
            this.y.setVisibility(4);
        }
        if (!TextUtils.isEmpty(this.H)) {
            this.A.setVisibility(0);
            this.A.getPaint().setFlags(8);
            this.A.setText(this.H);
            this.A.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (VoucherFragment.this.I != null) {
                        EntryManager.a().a((Fragment) VoucherFragment.this, VoucherFragment.this.I, new Bundle(), -1);
                    }
                }
            });
            return;
        }
        this.A.setVisibility(8);
    }

    public IPresenter onCreatePresenter() {
        return new VoucherChannelPresenter();
    }

    public void k() {
        super.k();
        this.z.setClickable(true);
        MistatisticUtils.a((Fragment) this, this.C ? "Pay:" : "Recharge:");
    }

    public void l() {
        super.l();
        MistatisticUtils.b((Fragment) this, this.C ? "Pay:" : "Recharge:");
    }

    public void a(int i, int i2, Intent intent) {
        Bundle extras;
        super.a(i, i2, intent);
        if (i == w && -1 == i2 && intent != null && (extras = intent.getExtras()) != null) {
            String string = extras.getString(Integer.toString(R.id.voucher_edit));
            if (!TextUtils.isEmpty(string)) {
                this.x.setText(string);
            }
        }
    }

    public void a(int i, boolean z2) {
        if (z2) {
            b(getString(R.string.mibi_progress_voucher_creating), false);
        } else {
            M();
        }
    }

    public void a(int i, String str, Throwable th) {
        a(0, false);
        this.z.setClickable(true);
        ChannelUtils.a((StepFragment) this, i, str);
    }

    public void a(long j) {
        a(0, false);
        p().m().a(this.t, MibiConstants.hg, (Object) Long.valueOf(j));
        Bundle bundle = new Bundle();
        bundle.putString("title", this.B);
        bundle.putBoolean(MibiConstants.hi, true);
        ChannelUtils.a((StepFragment) this, bundle, this.C);
    }

    /* access modifiers changed from: private */
    public void N() {
        a("android.permission.CAMERA");
    }

    /* access modifiers changed from: protected */
    public void A() {
        super.A();
        String string = getResources().getString(R.string.mibi_scan_prompt_voucher);
        Bundle bundle = new Bundle();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new CameraIntentData(R.id.voucher_edit, string, this.G));
        bundle.putParcelableArrayList(IEntry.b, arrayList);
        EntryManager.a().a(MibiConstants.cB, (Fragment) this, bundle, w);
    }

    public void a(int i, int i2, Bundle bundle) {
        String str = v;
        Log.v(str, this + ".onFragmentResult, requestCode = " + i + ",resultCode = " + i2);
        super.a(i, i2, bundle);
        if (i == 0) {
            b(i2, bundle);
        }
    }

    public void a(int i, Bundle bundle) {
        super.a(i, bundle);
        if (i == 111111) {
            this.x.setText("");
        }
    }
}
