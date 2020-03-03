package com.xiaomi.smarthome.newui;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.h6ah4i.android.widget.advrecyclerview.animator.SwipeDismissItemAnimator;
import com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager;
import com.h6ah4i.android.widget.advrecyclerview.touchguard.RecyclerViewTouchActionGuardManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.page.BaseFragment;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.widget.DevicePtrFrameLayout;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.newui.HomeEnvInfoViewModel;
import com.xiaomi.smarthome.newui.topwidget.EnvDevice;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class HomeEnvInfoSettingFragment extends BaseFragment {

    /* renamed from: a  reason: collision with root package name */
    public XQProgressDialog f20286a;
    protected RecyclerViewExpandableItemManager b;
    protected RecyclerViewTouchActionGuardManager c;
    protected RecyclerView.Adapter d;
    protected LinearLayoutManager e;
    private View f;
    private HomeEnvInfoAdapter g;
    private String h;
    private HomeEnvInfoViewModel i;
    private Unbinder j;
    @BindView(2131428992)
    TextView mEmpty;
    @BindView(2131429586)
    View mMenuIcom;
    @BindView(2131431674)
    DevicePtrFrameLayout mPullRefresh;
    @BindView(2131431792)
    RecyclerView mRecyclerView;
    @BindView(2131430969)
    ImageView mReturnImageView;
    @BindView(2131430975)
    TextView mTextTitle;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.f == null) {
            this.f = layoutInflater.inflate(R.layout.fragment_home_env_info, (ViewGroup) null);
            this.j = ButterKnife.bind((Object) this, this.f);
            a();
            d();
        }
        return this.f;
    }

    private void a() {
        c();
        this.mReturnImageView.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                HomeEnvInfoSettingFragment.this.a(view);
            }
        });
        b();
        this.mTextTitle.setText(R.string.home_env_info_setting);
        this.mMenuIcom.setVisibility(8);
        TitleBarUtil.a((Activity) getActivity());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        onBackPressed();
    }

    private void b() {
        this.mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                View currentFocus;
                super.onScrollStateChanged(recyclerView, i);
                if (i != 0 && HomeEnvInfoSettingFragment.this.isValid() && (currentFocus = HomeEnvInfoSettingFragment.this.getActivity().getCurrentFocus()) != null) {
                    currentFocus.clearFocus();
                }
            }
        });
        this.b = new RecyclerViewExpandableItemManager((Parcelable) null);
        this.g = new HomeEnvInfoAdapter(getActivity(), true);
        this.d = this.b.a((RecyclerView.Adapter) this.g);
        this.e = new LinearLayoutManager(getActivity());
        this.e.setAutoMeasureEnabled(true);
        this.e.setSmoothScrollbarEnabled(true);
        SwipeDismissItemAnimator swipeDismissItemAnimator = new SwipeDismissItemAnimator();
        swipeDismissItemAnimator.setSupportsChangeAnimations(false);
        this.mRecyclerView.setLayoutManager(this.e);
        this.mRecyclerView.setAdapter(this.d);
        this.mRecyclerView.setItemAnimator(swipeDismissItemAnimator);
        this.mRecyclerView.setHasFixedSize(true);
        this.c = new RecyclerViewTouchActionGuardManager();
        this.c.b(true);
        this.c.a(true);
        this.c.a(this.mRecyclerView);
        this.b.a(this.mRecyclerView);
        this.b.d();
    }

    private void c() {
        this.mPullRefresh.setPtrHandler(new PtrDefaultHandler() {
            public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
                HomeEnvInfoSettingFragment.this.mPullRefresh.refreshComplete();
            }

            public boolean checkCanDoRefresh(PtrFrameLayout ptrFrameLayout, View view, View view2) {
                if (HomeEnvInfoSettingFragment.this.mRecyclerView.canScrollVertically(-1)) {
                    return false;
                }
                return super.checkCanDoRefresh(ptrFrameLayout, view, view2);
            }
        });
    }

    private void d() {
        this.h = HomeManager.a().l();
        if (getActivity() != null) {
            this.i = (HomeEnvInfoViewModel) ViewModelProviders.a(getActivity()).a(HomeEnvInfoViewModel.class);
            this.i.a().observe(this, new Observer() {
                public final void onChanged(Object obj) {
                    HomeEnvInfoSettingFragment.this.b((Map) obj);
                }
            });
            this.i.c().observe(this, new Observer() {
                public final void onChanged(Object obj) {
                    HomeEnvInfoSettingFragment.this.a((Map<String, List<EnvDevice>>) (Map) obj);
                }
            });
        }
        e();
        a(this.i.c().getValue());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(Map map) {
        e();
    }

    /* access modifiers changed from: private */
    public void a(Map<String, List<EnvDevice>> map) {
        if (map != null && !map.isEmpty() && map.get(this.h) != null) {
            HashMap hashMap = new HashMap();
            for (EnvDevice envDevice : map.get(this.h)) {
                hashMap.put(envDevice.f20709a, envDevice.b);
            }
            this.g.a((HashMap<String, String>) hashMap);
        }
    }

    private void e() {
        List<HomeEnvInfoViewModel.GroupBean> d2 = this.i.d(this.h);
        if (d2 == null || d2.isEmpty()) {
            this.mEmpty.setVisibility(0);
            this.mRecyclerView.setVisibility(8);
            return;
        }
        this.mRecyclerView.setVisibility(0);
        this.mEmpty.setVisibility(8);
        this.g.a(d2);
        this.g.notifyDataSetChanged();
        this.b.d();
    }

    public boolean onBackPressed() {
        if (this.g.f20279a) {
            f();
            return true;
        }
        getActivity().getSupportFragmentManager().popBackStackImmediate();
        return true;
    }

    private void f() {
        this.f20286a = new XQProgressDialog(getContext());
        this.f20286a.setMessage(getResources().getString(R.string.loading));
        this.f20286a.setCancelable(false);
        this.f20286a.show();
        ArrayList arrayList = new ArrayList();
        Map<String, String> b2 = this.g.b();
        if (b2 != null) {
            for (String next : HomeEnvInfoViewModel.e) {
                arrayList.add(new EnvDevice(next, b2.get(next)));
            }
            this.i.a(this.h, (List<EnvDevice>) arrayList, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    if (HomeEnvInfoSettingFragment.this.isValid()) {
                        HomeEnvInfoSettingFragment.this.getActivity().getSupportFragmentManager().popBackStackImmediate();
                    }
                    if (HomeEnvInfoSettingFragment.this.f20286a != null && HomeEnvInfoSettingFragment.this.f20286a.isShowing()) {
                        HomeEnvInfoSettingFragment.this.f20286a.dismiss();
                    }
                }

                public void onFailure(Error error) {
                    if (HomeEnvInfoSettingFragment.this.isValid()) {
                        HomeEnvInfoSettingFragment.this.getActivity().getSupportFragmentManager().popBackStackImmediate();
                    }
                    if (HomeEnvInfoSettingFragment.this.f20286a != null && HomeEnvInfoSettingFragment.this.f20286a.isShowing()) {
                        HomeEnvInfoSettingFragment.this.f20286a.dismiss();
                    }
                }
            });
        }
    }

    public void onDestroyView() {
        if (this.f20286a != null && this.f20286a.isShowing()) {
            this.f20286a.dismiss();
        }
        super.onDestroyView();
    }

    public void onDestroy() {
        if (this.j != null) {
            this.j.unbind();
        }
        super.onDestroy();
    }
}
