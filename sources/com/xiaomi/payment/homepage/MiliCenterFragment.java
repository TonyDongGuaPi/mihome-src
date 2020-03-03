package com.xiaomi.payment.homepage;

import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import com.libra.Color;
import com.mibi.common.base.BaseActivity;
import com.mibi.common.base.BaseFragment;
import com.mibi.common.base.IPresenter;
import com.mibi.common.base.StepActivity;
import com.mibi.common.base.StepFragment;
import com.mibi.common.component.CommonErrorView;
import com.mibi.common.component.FloatingProgressView;
import com.mibi.common.component.SimpleDialogFragment;
import com.mibi.common.component.UnevenGrid;
import com.mibi.common.data.Client;
import com.mibi.common.data.MistatisticUtils;
import com.mibi.common.data.PrivacyManager;
import com.mibi.common.data.Utils;
import com.mibi.common.ui.PadDialogActivity;
import com.mibi.common.ui.PhoneCommonActivity;
import com.mibi.common.ui.PhoneDialogActivity;
import com.xiaomi.payment.data.AnalyticsConstants;
import com.xiaomi.payment.data.EntryData;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.data.MibiPrivacyUtils;
import com.xiaomi.payment.data.UnevenGridData;
import com.xiaomi.payment.entry.EntryManager;
import com.xiaomi.payment.homepage.MiliCenterContract;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.task.rxjava.RxHomePageGridTask;
import com.xiaomi.payment.ui.IEntryProxy;
import com.xiaomi.payment.ui.PadFixedWidthActivity;
import com.xiaomi.payment.ui.adapter.CustomImmersionMenuAdapter;
import com.xiaomi.payment.ui.adapter.GuideInfoGridAdapter;
import com.xiaomi.payment.ui.adapter.HomeGridAdapter;
import com.xiaomi.payment.ui.component.AdaptiveBalanceRelativeLayout;
import com.xiaomi.payment.ui.component.HomeDiscountsView;
import com.xiaomi.payment.ui.fragment.PromptFragment;
import com.xiaomi.payment.ui.item.GuideGridItem;
import com.xiaomi.payment.ui.item.MenuListItem;
import com.xiaomi.payment.ui.menu.ImmersionMenuPopupWindowWrapper;
import com.xiaomi.payment.ui.menu.MenuItemClickListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MiliCenterFragment extends BaseFragment implements MiliCenterContract.View {
    private static final String t = "MiliCenterFragment";
    private static final String u = "bill_record";
    private static final String v = "giftcard_record";
    private static final String w = "send_shortcut";
    private static final String x = "lock_pattern";
    private static final String y = "faq";
    private static final String z = "feedback";
    /* access modifiers changed from: private */
    public MiliCenterPresenter A;
    /* access modifiers changed from: private */
    public ImmersionMenuPopupWindowWrapper B;
    private ViewStub C;
    private TextView D;
    private View E;
    private View F;
    private TextView G;
    private TextView H;
    private View I;
    private View J;
    private TextView K;
    private TextView L;
    private AdaptiveBalanceRelativeLayout M;
    private TextView N;
    private View O;
    private TextView P;
    private ViewStub Q;
    private FloatingProgressView R;
    private ViewStub S;
    private View T;
    private View U;
    private HomeDiscountsView V;
    private TextView W;
    private View X;
    private GridView Y;
    private UnevenGrid Z;
    private View aa;
    private CommonErrorView ab;
    /* access modifiers changed from: private */
    public boolean ac = false;
    /* access modifiers changed from: private */
    public boolean ad = false;
    /* access modifiers changed from: private */
    public boolean ae = false;
    /* access modifiers changed from: private */
    public boolean af = true;
    /* access modifiers changed from: private */
    public CustomImmersionMenuAdapter ag;
    private GuideInfoGridAdapter ah;
    private HomeGridAdapter ai;
    private HomeDiscountsView.DiscountItemClickListener aj = new HomeDiscountsView.DiscountItemClickListener() {
        public void a(RxHomePageGridTask.Result.DiscountsItemData discountsItemData) {
            boolean unused = MiliCenterFragment.this.af = false;
            MiliCenterFragment.this.a(discountsItemData.b);
        }
    };
    private AdapterView.OnItemClickListener ak = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            MiliCenterFragment.this.a(view);
        }
    };
    private View.OnClickListener al = new View.OnClickListener() {
        public void onClick(View view) {
            boolean unused = MiliCenterFragment.this.af = false;
            MiliCenterFragment.this.x_();
        }
    };
    private View.OnClickListener am = new View.OnClickListener() {
        public void onClick(View view) {
            Utils.a(MiliCenterFragment.this.getActivity());
        }
    };
    private View.OnClickListener an = new View.OnClickListener() {
        public void onClick(View view) {
            boolean unused = MiliCenterFragment.this.af = false;
            if (PromptFragment.a((Context) MiliCenterFragment.this.f7451a)) {
                MiliCenterFragment.this.K();
                return;
            }
            MiliCenterFragment.this.A.B_();
            MiliCenterFragment.this.y_();
        }
    };
    private View.OnClickListener ao = new View.OnClickListener() {
        public void onClick(View view) {
            boolean unused = MiliCenterFragment.this.af = false;
            MiliCenterFragment.this.j();
        }
    };
    private View.OnClickListener ap = new View.OnClickListener() {
        public void onClick(View view) {
            MiliCenterFragment.this.A.a(true);
        }
    };
    private View.OnClickListener aq = new View.OnClickListener() {
        public void onClick(View view) {
            MiliCenterFragment.this.A.a(false, true);
        }
    };
    private View.OnClickListener ar = new View.OnClickListener() {
        public void onClick(View view) {
            MiliCenterFragment.this.b(view);
        }
    };
    private MenuItemClickListener as = new MenuItemClickListener() {
        public void a(AdapterView<?> adapterView, View view, int i, long j) {
            MenuListItem.ItemData itemData;
            if (j >= -1 && MiliCenterFragment.this.ag != null && (itemData = ((MenuListItem) view).getItemData()) != null) {
                if (TextUtils.equals(MiliCenterFragment.u, itemData.b)) {
                    boolean unused = MiliCenterFragment.this.af = false;
                    MiliCenterFragment.this.j();
                    MiliCenterFragment.this.B.a(false);
                } else if (TextUtils.equals(MiliCenterFragment.v, itemData.b)) {
                    boolean unused2 = MiliCenterFragment.this.af = false;
                    MiliCenterFragment.this.x_();
                    MiliCenterFragment.this.B.a(false);
                } else if (TextUtils.equals(MiliCenterFragment.w, itemData.b)) {
                    MiliCenterFragment.this.A.a(MiliCenterFragment.this.getActivity());
                    MiliCenterFragment.this.B.b();
                } else if (TextUtils.equals(MiliCenterFragment.x, itemData.b)) {
                    if (Utils.h(MiliCenterFragment.this.getActivity())) {
                        Toast.makeText(MiliCenterFragment.this.f7451a, MiliCenterFragment.this.getString(R.string.mibi_applock_config), 1).show();
                    } else {
                        MiliCenterFragment.this.A.g();
                    }
                    MiliCenterFragment.this.B.a(false);
                } else if (TextUtils.equals("faq", itemData.b)) {
                    boolean unused3 = MiliCenterFragment.this.af = false;
                    MiliCenterFragment.this.A.i();
                    MiliCenterFragment.this.B.a(false);
                } else if (TextUtils.equals("feedback", itemData.b)) {
                    boolean unused4 = MiliCenterFragment.this.af = false;
                    MiliCenterFragment.this.A.j();
                    MiliCenterFragment.this.B.a(false);
                }
            }
        }
    };

    public IPresenter onCreatePresenter() {
        this.A = new MiliCenterPresenter();
        return this.A;
    }

    public View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.mibi_mili_center, (ViewGroup) null);
        this.C = (ViewStub) inflate.findViewById(R.id.stub_header_error);
        this.I = inflate.findViewById(R.id.balance_value_empty);
        this.J = inflate.findViewById(R.id.balance_view);
        this.K = (TextView) inflate.findViewById(R.id.balance_label);
        this.M = (AdaptiveBalanceRelativeLayout) inflate.findViewById(R.id.balance_value);
        this.L = (TextView) inflate.findViewById(R.id.bill_record_label);
        this.O = inflate.findViewById(R.id.recharge);
        this.P = (TextView) inflate.findViewById(R.id.recharge_label_text);
        this.F = inflate.findViewById(R.id.giftcard_view);
        this.G = (TextView) inflate.findViewById(R.id.giftcard_label_text);
        this.H = (TextView) inflate.findViewById(R.id.giftcard_label_sub_text);
        this.N = (TextView) inflate.findViewById(R.id.frozen_text);
        this.Q = (ViewStub) inflate.findViewById(R.id.stub_header_progress);
        this.S = (ViewStub) inflate.findViewById(R.id.stub_home_container);
        return inflate;
    }

    private void L() {
        if (!this.ac) {
            View inflate = this.C.inflate();
            this.D = (TextView) inflate.findViewById(R.id.head_error_text);
            this.E = inflate.findViewById(R.id.head_error_bottom_line);
        }
    }

    private void M() {
        if (!this.ad) {
            this.R = (FloatingProgressView) this.Q.inflate().findViewById(R.id.header_progress);
        }
    }

    public void p_() {
        if (this.ah == null) {
            this.ah = new GuideInfoGridAdapter(getActivity());
        }
        this.Y.setOnItemClickListener(this.ak);
        this.Y.setAdapter(this.ah);
        if (this.ai == null) {
            this.ai = new HomeGridAdapter(getActivity());
        }
        this.ai.a((HomeGridAdapter.GridItemClickListener) new HomeGridItemClickListener());
        this.Z.setAdapter(this.ai);
    }

    public void q_() {
        if (!this.ae) {
            View inflate = this.S.inflate();
            this.T = inflate.findViewById(R.id.home_grid_normal);
            this.U = inflate.findViewById(R.id.discounts_gap);
            this.V = (HomeDiscountsView) inflate.findViewById(R.id.discounts);
            this.W = (TextView) inflate.findViewById(R.id.guide_title);
            this.X = inflate.findViewById(R.id.guide_gap);
            this.Y = (GridView) inflate.findViewById(R.id.guide);
            this.aa = inflate.findViewById(R.id.business_gap);
            this.Z = (UnevenGrid) inflate.findViewById(R.id.business);
            this.ab = (CommonErrorView) inflate.findViewById(R.id.home_grid_progress_layout);
            this.T.setVisibility(4);
        }
    }

    public void r_() {
        SimpleDialogFragment.SimpleDialogFragmentBuilder simpleDialogFragmentBuilder = new SimpleDialogFragment.SimpleDialogFragmentBuilder(1);
        simpleDialogFragmentBuilder.b(getResources().getString(R.string.mibi_applock_dialog_title));
        simpleDialogFragmentBuilder.a(getResources().getString(R.string.mibi_applock_dialog_content));
        SimpleDialogFragment a2 = simpleDialogFragmentBuilder.a();
        a2.a(getResources().getString(R.string.mibi_applock_dialog_ok), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                MiliCenterFragment.this.A.g();
            }
        });
        a2.b(getResources().getString(R.string.mibi_applock_dialog_cancel), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        a2.setCancelable(true);
        a2.show(getFragmentManager(), "simpleDialog");
    }

    public void a_(Intent intent) {
        startActivity(intent);
    }

    public void a(Class<? extends StepFragment> cls, Bundle bundle) {
        a(cls, bundle, (String) null, (Class<? extends StepActivity>) Utils.b() ? PadDialogActivity.class : PhoneCommonActivity.class);
    }

    public void s_() {
        getActivity().finish();
    }

    public void c(Bundle bundle) {
        super.c(bundle);
        a(R.string.mibi_milicenter_title);
        a((String) null, miuipub.v6.R.drawable.v6_action_mode_immersion_more_light, (View.OnClickListener) null);
        this.C.setOnInflateListener(new ViewStub.OnInflateListener() {
            public void onInflate(ViewStub viewStub, View view) {
                boolean unused = MiliCenterFragment.this.ac = true;
            }
        });
        this.Q.setOnInflateListener(new ViewStub.OnInflateListener() {
            public void onInflate(ViewStub viewStub, View view) {
                boolean unused = MiliCenterFragment.this.ad = true;
            }
        });
        this.S.setOnInflateListener(new ViewStub.OnInflateListener() {
            public void onInflate(ViewStub viewStub, View view) {
                boolean unused = MiliCenterFragment.this.ae = true;
            }
        });
        if (!((BaseActivity) getActivity()).hasSession()) {
            ((IEntryProxy) getActivity()).startEntryProcess(bundle);
        } else if (this.af) {
            this.A.h();
        } else {
            this.af = true;
        }
    }

    public void t_() {
        this.ag = new CustomImmersionMenuAdapter(getActivity());
        this.O.setOnClickListener(this.an);
        this.F.setOnClickListener(this.al);
        this.L.setOnClickListener(this.ao);
        N();
    }

    public void a_(boolean z2) {
        this.af = z2;
    }

    public void k() {
        super.k();
        if (((BaseActivity) getActivity()).hasSession()) {
            MistatisticUtils.a((Fragment) this, "milicenter_show");
        }
    }

    public void l() {
        super.l();
        if (((BaseActivity) getActivity()).hasSession()) {
            MistatisticUtils.b((Fragment) this, "milicenter_show");
        }
    }

    public void a(RxHomePageGridTask.Result result) {
        if (result.b == null || result.b.size() <= 0) {
            this.U.setVisibility(8);
            this.V.setVisibility(8);
        } else {
            this.U.setVisibility(0);
            this.V.setVisibility(0);
            this.V.setDiscountViewClickListener(this.aj);
            this.V.bindBannerInfo(result.b);
        }
        if (result.c == null || result.c.size() <= 0) {
            this.W.setVisibility(8);
            this.X.setVisibility(8);
            this.Y.setVisibility(8);
        } else {
            this.W.setVisibility(0);
            this.X.setVisibility(0);
            this.Y.setVisibility(0);
            this.ah.a(result.c);
        }
        if (result.f12421a != null) {
            this.aa.setVisibility(0);
            this.Z.setVisibility(0);
            if (!Utils.b()) {
                this.Z.setNumColumns(result.f12421a.f12232a);
            } else {
                a(result.f12421a.b);
            }
            ArrayList<HomeGridAdapter.HomeGridItemData> b = HomeGridAdapter.b(result.f12421a.b);
            if (b != null && !b.isEmpty()) {
                this.ai.a(b);
                return;
            }
            return;
        }
        this.aa.setVisibility(8);
        this.Z.setVisibility(8);
    }

    public void c() {
        this.D.setVisibility(8);
        this.E.setVisibility(8);
        this.I.setVisibility(8);
        this.J.setBackgroundResource(R.drawable.mibi_milicenter_header_bg);
        this.N.setVisibility(8);
        this.M.setVisibility(0);
        this.O.setEnabled(true);
        this.P.setTextColor(-16777216);
        this.F.setEnabled(true);
        this.G.setTextColor(-16777216);
        this.H.setTextColor(-16777216);
        int color = getResources().getColor(R.color.mibi_text_color_white_alpha_90);
        this.K.setTextColor(color);
        this.M.setColor(color);
        this.L.setTextColor(color);
    }

    public void u_() {
        this.I.setVisibility(8);
        this.J.setBackgroundResource(R.drawable.mibi_milicenter_header_frozon_bg);
        this.M.setVisibility(0);
        if (!Utils.b()) {
            this.N.setOnClickListener(this.am);
            this.N.setText(Html.fromHtml(getResources().getString(R.string.mibi_frozen_text)));
        } else {
            this.N.setText(getString(R.string.mibi_frozen_text_pad));
        }
        this.N.setVisibility(0);
        this.L.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.mibi_arrow_right_normal, 0);
        this.K.setTextColor(-16777216);
        this.M.setColor(-16777216);
        this.L.setTextColor(-16777216);
        this.O.setEnabled(false);
        this.P.setTextColor(Color.c);
        this.F.setEnabled(false);
        this.G.setTextColor(Color.c);
        this.H.setTextColor(Color.c);
    }

    public void a(long j) {
        this.M.setBalance(j);
    }

    public void a(long j, int i) {
        this.A.a(j);
        if (i > 0) {
            this.H.setText(Html.fromHtml(getString(R.string.mibi_giftcard_with_num, new Object[]{Integer.valueOf(i)})));
            this.H.setVisibility(0);
            return;
        }
        this.H.setVisibility(8);
    }

    public void v_() {
        this.ab.setErrorIcon(R.drawable.mibi_network_error);
    }

    public void a(int i, String str) {
        PrivacyManager.a(((BaseActivity) getActivity()).getSession(), false);
        MibiPrivacyUtils.a(getActivity(), new MibiPrivacyUtils.PrivacyCallBack() {
            public void a() {
                MiliCenterFragment.this.getActivity().finish();
            }
        });
    }

    private void d(String str) {
        this.E.setVisibility(0);
        this.D.setVisibility(0);
        this.D.setText(str);
        this.D.setOnClickListener(this.ap);
        this.P.setTextColor(Color.c);
        this.O.setEnabled(false);
        this.F.setEnabled(false);
        this.G.setTextColor(Color.c);
        this.H.setTextColor(Color.c);
        this.J.setEnabled(false);
    }

    public void w_() {
        this.T.setVisibility(0);
        this.ab.setVisibility(8);
    }

    public void b_(int i) {
        this.T.setVisibility(8);
        this.ab.setVisibility(i);
        if (i == 0) {
            this.ab.startProgress();
        } else {
            this.ab.stopProgress();
        }
    }

    public void b_(boolean z2) {
        M();
        L();
        if (z2) {
            this.R.startProgress();
        }
    }

    public void c(boolean z2) {
        if (z2) {
            this.R.stopProgress();
        }
    }

    public void c_(String str) {
        L();
        this.M.setBalance(0);
        d(str);
    }

    public void d_(String str) {
        if (this.ac) {
            this.D.setVisibility(8);
            this.E.setVisibility(8);
        }
        this.T.setVisibility(8);
        this.ab.setVisibility(0);
        this.ab.showError(str, this.aq);
    }

    private void a(ArrayList<UnevenGridData.SingleGridItemInfo> arrayList) {
        this.Z.setNumColumns(getResources().getInteger(R.integer.mibi_num_home_grid_columns));
        this.Z.setGridHeight(getResources().getDimensionPixelSize(R.dimen.mibi_milicenter_home_grid_height));
        if (arrayList != null && !arrayList.isEmpty()) {
            UnevenGridData.SingleGridItemInfo singleGridItemInfo = arrayList.get(0);
            if (singleGridItemInfo.g == 1) {
                singleGridItemInfo.h = getResources().getInteger(R.integer.mibi_num_home_grid_columns);
            }
        }
    }

    private class HomeGridItemClickListener implements HomeGridAdapter.GridItemClickListener {
        private HomeGridItemClickListener() {
        }

        public void a(UnevenGridData.SingleGridItemInfo singleGridItemInfo) {
            if (singleGridItemInfo != null && singleGridItemInfo.g == 0) {
                UnevenGridData.NormalGridItemInfo normalGridItemInfo = (UnevenGridData.NormalGridItemInfo) singleGridItemInfo;
                EntryData entryData = normalGridItemInfo.d;
                String str = (String) normalGridItemInfo.d.mExtraData.get(MibiConstants.dP);
                if (!TextUtils.isEmpty(str)) {
                    HashMap hashMap = new HashMap();
                    hashMap.put(AnalyticsConstants.by, str);
                    MistatisticUtils.a("business", (Map<String, String>) hashMap);
                }
                boolean unused = MiliCenterFragment.this.af = true;
                if (entryData != null) {
                    MiliCenterFragment.this.A.a(entryData);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(View view) {
        EntryManager.a().a(getActivity(), ((GuideGridItem) view).getGuideItemType().d, (Bundle) null, -1);
    }

    public void a(EntryData entryData) {
        MistatisticUtils.a(AnalyticsConstants.bu);
        EntryManager a2 = EntryManager.a();
        if (entryData == null) {
            a2.a(MibiConstants.eT, (Fragment) this, new Bundle(), MiliCenterContract.c);
        } else {
            a2.a((Fragment) this, entryData, (Bundle) null, MiliCenterContract.c);
        }
    }

    public void j() {
        HashMap hashMap = new HashMap();
        hashMap.put(AnalyticsConstants.bw, "menu_show_bill_record");
        MistatisticUtils.a("menu", (Map<String, String>) hashMap);
        EntryManager.a().a(MibiConstants.eU, (Fragment) this, (Bundle) null, -1);
    }

    public void x_() {
        HashMap hashMap = new HashMap();
        hashMap.put(AnalyticsConstants.bw, "menu_show_giftcard_record");
        MistatisticUtils.a("menu", (Map<String, String>) hashMap);
        EntryManager.a().a(MibiConstants.eV, (Fragment) this, (Bundle) null, MiliCenterContract.b);
    }

    public void y_() {
        EntryManager.a().a(MibiConstants.eX, (Fragment) this, new Bundle(), MiliCenterContract.f12310a);
    }

    public void K() {
        a(PromptFragment.class, (Bundle) null, MiliCenterContract.d, (String) null, Utils.b() ? PadFixedWidthActivity.class : PhoneDialogActivity.class);
    }

    public void b(EntryData entryData) {
        if (isResumed()) {
            EntryManager.a().a(getActivity(), entryData, (Bundle) null, -1);
        }
    }

    private void N() {
        a((String) null, miuipub.v6.R.drawable.v6_action_mode_immersion_more_light, this.ar);
        if (this.ag != null) {
            ArrayList arrayList = new ArrayList();
            Resources resources = getResources();
            String string = resources.getString(R.string.mibi_menu_lock);
            resources.getString(R.string.mibi_menu_shotcut);
            String string2 = resources.getString(R.string.mibi_menu_qa);
            String string3 = resources.getString(R.string.mibi_menu_feedback);
            if (!Utils.b() && Client.x() > 5) {
                arrayList.add(a(string, x));
            }
            arrayList.add(a(string2, "faq"));
            if (Utils.b((Context) getActivity(), ((MiliCenterPresenter) H_()).k())) {
                arrayList.add(a(string3, "feedback"));
            }
            this.ag.a(arrayList);
        }
    }

    private MenuListItem.ItemData a(String str, String str2) {
        MenuListItem.ItemData itemData = new MenuListItem.ItemData();
        itemData.f12527a = str;
        itemData.b = str2;
        return itemData;
    }

    /* access modifiers changed from: private */
    public void b(View view) {
        MistatisticUtils.a(AnalyticsConstants.bt);
        this.B = new ImmersionMenuPopupWindowWrapper(getActivity());
        this.B.a((BaseAdapter) this.ag);
        this.B.a(this.as);
        this.af = false;
        this.B.a(view, (ViewGroup) null);
    }

    public void y() {
        getActivity().finish();
    }

    public void o() {
        super.o();
        if (this.B != null) {
            this.B.a(false);
        }
    }
}
