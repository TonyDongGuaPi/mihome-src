package com.xiaomi.payment.giftcard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.mibi.common.base.BaseFragment;
import com.mibi.common.base.IPresenter;
import com.mibi.common.data.PageableListener;
import com.xiaomi.payment.giftcard.GiftcardContract;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.task.rxjava.RxGiftcardTask;
import java.util.ArrayList;

public class GiftcardTabFragment extends BaseFragment implements GiftcardContract.GiftcardTabView {
    private TextView A;
    private ImageView B;
    private ProgressBar C;
    private ProgressBar D;
    private TextView E;
    private View F;
    private TextView G;
    private ListView t;
    private GiftcardAdatper u;
    private GiftcardTabPresenter v;
    private boolean w = false;
    /* access modifiers changed from: private */
    public boolean x;
    private int y;
    private Button z;

    public IPresenter onCreatePresenter() {
        this.v = new GiftcardTabPresenter(this.y);
        return this.v;
    }

    public void c(Bundle bundle) {
        super.c(bundle);
        d(false);
        this.u = GiftcardAdapterFactory.a(getActivity(), this.y);
        this.t.setAdapter(this.u);
        K();
    }

    /* access modifiers changed from: protected */
    public void b_(Bundle bundle) {
        super.b_(bundle);
        this.y = bundle.getInt(GiftcardContract.d);
    }

    public final View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.mibi_giftcard_page, (ViewGroup) null);
        this.t = (ListView) inflate.findViewById(R.id.list);
        this.t.setVerticalScrollBarEnabled(false);
        this.t.setOnScrollListener(new PageableListener() {
            public void a() {
                boolean unused = GiftcardTabFragment.this.x = false;
                ((GiftcardTabPresenter) GiftcardTabFragment.this.H_()).a(false);
            }
        });
        this.z = (Button) inflate.findViewById(R.id.button_retry);
        this.A = (TextView) inflate.findViewById(R.id.error);
        this.B = (ImageView) inflate.findViewById(R.id.error_icon);
        this.C = (ProgressBar) inflate.findViewById(R.id.progress);
        this.D = (ProgressBar) inflate.findViewById(R.id.append_progress);
        this.E = (TextView) inflate.findViewById(R.id.progress_text);
        this.F = inflate.findViewById(R.id.none_giftcard);
        this.G = (TextView) inflate.findViewById(R.id.giftcard_empty_dsc);
        this.z.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                boolean unused = GiftcardTabFragment.this.x = true;
                GiftcardTabFragment.this.g(true);
                GiftcardTabFragment.this.L();
                ((GiftcardTabPresenter) GiftcardTabFragment.this.H_()).a(true);
            }
        });
        return inflate;
    }

    /* access modifiers changed from: protected */
    public void K() {
        if (!this.w) {
            this.x = true;
            this.v.a(true);
            this.w = true;
        }
    }

    public void a_(String str) {
        g(false);
        this.G.setText(getString(R.string.mibi_giftcard_empty_detail, new Object[]{str}));
        this.F.setVisibility(0);
    }

    public void a(boolean z2, ArrayList<RxGiftcardTask.Result.GiftcardItem> arrayList) {
        this.t.setVisibility(0);
        L();
        g(false);
        if (arrayList == null || arrayList.isEmpty()) {
            Toast.makeText(getActivity(), getResources().getString(R.string.mibi_list_item_bottom_hint), 0).show();
        } else if (z2) {
            this.u.a(arrayList);
        } else {
            this.u.a(arrayList, true);
        }
    }

    /* access modifiers changed from: private */
    public void L() {
        this.A.setVisibility(8);
        this.B.setVisibility(8);
        this.z.setVisibility(8);
    }

    public void b_(String str) {
        g(false);
        if (this.x) {
            this.z.setVisibility(0);
        }
        d(str);
    }

    public void a(int i, String str, Throwable th) {
        g(false);
        d(str);
    }

    public void a(int i, boolean z2) {
        if (!z2) {
            g(false);
            this.D.setVisibility(8);
        } else if (this.x) {
            this.t.setVisibility(8);
            this.C.setVisibility(0);
            this.E.setVisibility(0);
            g(true);
            L();
        } else {
            this.D.setVisibility(0);
        }
    }

    private void d(String str) {
        if (this.x) {
            this.A.setText(str);
            this.A.setVisibility(0);
            this.B.setVisibility(0);
            return;
        }
        Toast.makeText(getActivity(), str, 0).show();
    }

    /* access modifiers changed from: private */
    public void g(boolean z2) {
        if (z2) {
            this.C.setVisibility(0);
            this.E.setVisibility(0);
            return;
        }
        this.C.setVisibility(8);
        this.E.setVisibility(8);
    }
}
