package com.xiaomi.smarthome.newui;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.h6ah4i.android.widget.advrecyclerview.animator.SwipeDismissItemAnimator;
import com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager;
import com.h6ah4i.android.widget.advrecyclerview.touchguard.RecyclerViewTouchActionGuardManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.SmartHomeMainActivity;
import com.xiaomi.smarthome.framework.page.BaseFragment;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.library.common.widget.DevicePtrFrameLayout;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.newui.HomeEnvInfoViewModel;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import java.util.List;
import java.util.Map;

public class HomeEnvInfoFragment extends BaseFragment {

    /* renamed from: a  reason: collision with root package name */
    protected RecyclerViewExpandableItemManager f20281a;
    protected RecyclerViewTouchActionGuardManager b;
    protected RecyclerView.Adapter c;
    protected LinearLayoutManager d;
    private View e;
    private HomeEnvInfoAdapter f;
    private String g;
    private boolean h = false;
    private HomeEnvInfoViewModel i;
    private Unbinder j;
    @BindView(2131428992)
    TextView mEmpty;
    @BindView(2131432956)
    View mGroupTitle;
    @BindView(2131430797)
    View mMaskView;
    @BindView(2131429586)
    View mMenuIcom;
    @BindView(2131431674)
    DevicePtrFrameLayout mPullRefresh;
    @BindView(2131431792)
    RecyclerView mRecyclerView;
    @BindView(2131430969)
    ImageView mReturnImageView;
    @BindView(2131432919)
    View mTitleBar;
    @BindView(2131430975)
    TextView mTitleTv;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public void onResume() {
        super.onResume();
        this.g = HomeManager.a().l();
        Home m = HomeManager.a().m();
        if (!(m == null || m.k() == null || this.mTitleTv == null)) {
            this.mTitleTv.setText(m.k());
        }
        this.i.a(this.g);
    }

    public void onPause() {
        super.onPause();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.e == null) {
            this.e = layoutInflater.inflate(R.layout.fragment_home_env_info, (ViewGroup) null);
            this.j = ButterKnife.bind((Object) this, this.e);
            a();
            e();
        }
        return this.e;
    }

    public void onViewCreated(View view, Bundle bundle) {
        if (!this.h) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.mRecyclerView, "translationY", new float[]{300.0f, 0.0f});
            ofFloat.setInterpolator(new AccelerateDecelerateInterpolator());
            ofFloat.setDuration(200);
            ofFloat.start();
            super.onViewCreated(view, bundle);
        }
        this.h = true;
    }

    public void onDestroy() {
        super.onDestroy();
        this.h = false;
        if (this.j != null) {
            this.j.unbind();
        }
    }

    private void a() {
        d();
        this.mReturnImageView.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                HomeEnvInfoFragment.this.b(view);
            }
        });
        b();
        this.mMenuIcom.setVisibility(8);
        Home m = HomeManager.a().m();
        if (!(m == null || m.k() == null)) {
            this.mTitleTv.setText(m.k());
        }
        this.mEmpty.setVisibility(8);
        TitleBarUtil.a((Activity) getActivity());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(View view) {
        onBackPressed();
    }

    private void b() {
        this.mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                View currentFocus;
                super.onScrollStateChanged(recyclerView, i);
                if (i != 0 && HomeEnvInfoFragment.this.isValid() && (currentFocus = HomeEnvInfoFragment.this.getActivity().getCurrentFocus()) != null) {
                    currentFocus.clearFocus();
                }
            }
        });
        this.f20281a = new RecyclerViewExpandableItemManager((Parcelable) null);
        this.f = new HomeEnvInfoAdapter(getActivity(), false);
        this.c = this.f20281a.a((RecyclerView.Adapter) this.f);
        this.d = new LinearLayoutManager(getActivity());
        this.d.setAutoMeasureEnabled(true);
        this.d.setSmoothScrollbarEnabled(true);
        SwipeDismissItemAnimator swipeDismissItemAnimator = new SwipeDismissItemAnimator();
        swipeDismissItemAnimator.setSupportsChangeAnimations(false);
        this.mRecyclerView.setLayoutManager(this.d);
        this.mRecyclerView.setAdapter(this.c);
        this.mRecyclerView.setItemAnimator(swipeDismissItemAnimator);
        this.mRecyclerView.setHasFixedSize(true);
        this.b = new RecyclerViewTouchActionGuardManager();
        this.b.b(true);
        this.b.a(true);
        this.b.a(this.mRecyclerView);
        this.f20281a.a(this.mRecyclerView);
        this.f20281a.d();
        c();
    }

    private void c() {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.home_env_info_footer, this.mRecyclerView, false);
        inflate.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                HomeEnvInfoFragment.this.a(view);
            }
        });
        this.f.a(inflate);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        a(view, new Runnable() {
            public final void run() {
                HomeEnvInfoFragment.this.g();
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void g() {
        FragmentActivity activity = getActivity();
        if (activity instanceof SmartHomeMainActivity) {
            ((SmartHomeMainActivity) activity).enterHomeEnvInfoSettingFragment();
        }
    }

    private void d() {
        this.mPullRefresh.setPtrHandler(new PtrDefaultHandler() {
            public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
                HomeEnvInfoFragment.this.mPullRefresh.refreshComplete();
            }

            public boolean checkCanDoRefresh(PtrFrameLayout ptrFrameLayout, View view, View view2) {
                if (HomeEnvInfoFragment.this.mRecyclerView.canScrollVertically(-1)) {
                    return false;
                }
                return super.checkCanDoRefresh(ptrFrameLayout, view, view2);
            }
        });
    }

    private void e() {
        this.g = HomeManager.a().l();
        if (getActivity() != null) {
            this.i = (HomeEnvInfoViewModel) ViewModelProviders.a(getActivity()).a(HomeEnvInfoViewModel.class);
            this.i.a().observe(this, new Observer() {
                public final void onChanged(Object obj) {
                    HomeEnvInfoFragment.this.a((Map) obj);
                }
            });
        }
        f();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(Map map) {
        f();
    }

    private void f() {
        List<HomeEnvInfoViewModel.GroupBean> c2 = this.i.c(this.g);
        if (c2 == null || c2.isEmpty()) {
            this.mEmpty.setVisibility(0);
            this.mRecyclerView.setVisibility(8);
            return;
        }
        this.mRecyclerView.setVisibility(0);
        this.mEmpty.setVisibility(8);
        this.f.a(c2);
        this.f.notifyDataSetChanged();
        this.f20281a.d();
    }

    public boolean onBackPressed() {
        if (!(getActivity() instanceof SmartHomeMainActivity)) {
            return true;
        }
        ((SmartHomeMainActivity) getActivity()).exitHomeEnvInfoFragment();
        return true;
    }

    public void onDestroyView() {
        super.onDestroyView();
    }

    private void a(View view, final Runnable runnable) {
        MyScaleAnimation myScaleAnimation = new MyScaleAnimation(0.9f, 1, 0.5f, 1, 0.5f);
        myScaleAnimation.setDuration(360);
        myScaleAnimation.setInterpolator(new LinearInterpolator());
        myScaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                runnable.run();
            }
        });
        view.startAnimation(myScaleAnimation);
    }
}
