package com.xiaomi.smarthome.scenenew;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.xiaomi.router.api.SceneManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.config.AndroidCommonConfigManager;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.framework.page.BaseFragment;
import com.xiaomi.smarthome.framework.page.TabFragment;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.library.DarkModeCompat;
import com.xiaomi.smarthome.library.common.util.PreferenceUtils;
import com.xiaomi.smarthome.lite.scene.LiteSceneManager;
import com.xiaomi.smarthome.lite.scene.SceneLogUtil;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.operation.js_sdk.CommonWebViewFragment;
import com.xiaomi.smarthome.operation.smartrecommend.SmartRecommendOperation;
import com.xiaomi.smarthome.scene.CreateSceneManager;
import com.xiaomi.smarthome.scene.api.SceneApi;
import com.xiaomi.smarthome.scenenew.actiivity.SmarthomeCreateAutoSceneActivity;
import com.xiaomi.smarthome.scenenew.manager.RecommendSceneManager;
import com.xiaomi.smarthome.scenenew.view.SceneTabViewPagerWithSwipeEnable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SceneTabFragment extends TabFragment {
    private static final String t = "_last_select_index";
    View b;
    ViewPagerAdapter c;
    public SceneTabViewPagerWithSwipeEnable d;
    TabLayout e;
    int f = -1;
    public CommonWebViewFragment g;
    public RecommendSceneFragment h;
    public SceneLogFragment i;
    public MySceneFragmentNew j;
    IntentFilter k = new IntentFilter("action_on_login_success");
    BroadcastReceiver l = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            SceneTabFragment.this.a(intent);
        }
    };
    /* access modifiers changed from: private */
    public BaseFragment m;
    private ImageView n;
    private View o;
    private boolean p = false;
    private Context q;
    /* access modifiers changed from: private */
    public List<Integer> r;
    private List<Pair<Integer, Predicate>> s = Arrays.asList(new Pair[]{Pair.create(Integer.valueOf(R.string.smarthome_new_scene_choiceness), new Predicate() {
        public final boolean test() {
            return SceneTabFragment.this.l();
        }
    }), Pair.create(Integer.valueOf(R.string.smarthome_new_scene_recommend), new Predicate() {
        public final boolean test() {
            return SceneTabFragment.this.k();
        }
    }), Pair.create(Integer.valueOf(R.string.smarthome_new_scene_my), $$Lambda$SceneTabFragment$YZj4d12THIq5CZaROjoOOiW5Ks.INSTANCE), Pair.create(Integer.valueOf(R.string.smarthome_new_scene_log), $$Lambda$SceneTabFragment$ruGp5G5THFdFVVJWe5o_m766rgA.INSTANCE)});

    private interface Predicate {
        boolean test();
    }

    /* access modifiers changed from: private */
    public boolean h() {
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean i() {
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean j() {
        return true;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        StringBuilder sb = new StringBuilder();
        sb.append("SceneTabFragment  onCreateViewmRootView == null ");
        sb.append(this.b == null);
        SceneLogUtil.a(sb.toString());
        if (this.b == null) {
            this.p = false;
            this.b = layoutInflater.inflate(R.layout.fragment_scene_tab_layout, (ViewGroup) null);
            this.e = (TabLayout) this.b.findViewById(R.id.tab_layout);
            this.d = (SceneTabViewPagerWithSwipeEnable) this.b.findViewById(R.id.view_pager);
            this.o = this.b.findViewById(R.id.loading_view);
            this.n = (ImageView) this.b.findViewById(R.id.add_btn);
            if (!DarkModeCompat.a(viewGroup.getContext())) {
                this.b.findViewById(R.id.title_bar).setBackgroundResource(R.drawable.common_title_bar_bg);
            }
            this.q = getActivity();
        }
        return this.b;
    }

    public void onViewCreated(View view, Bundle bundle) {
        SceneLogUtil.a("SceneTabFragment  onViewCreated");
        super.onViewCreated(view, bundle);
        SHApplication.getGlobalHandler().postDelayed(new Runnable() {
            public final void run() {
                SceneTabFragment.this.m();
            }
        }, 10);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void m() {
        if (!this.p) {
            d();
            this.p = true;
        }
    }

    private List<Integer> b() {
        ArrayList arrayList = new ArrayList();
        for (Pair next : this.s) {
            if (((Predicate) next.second).test()) {
                arrayList.add(next.first);
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean l() {
        return !ServerCompact.e(getContext()) && AndroidCommonConfigManager.a().h();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean k() {
        return !ServerCompact.e(getContext());
    }

    public void a(boolean z) {
        a((String) null, z);
        SceneLogUtil.a("SceneTabFragment  onSwitchtoPage switchIn=" + z);
        if (z) {
            TitleBarUtil.b((Activity) getActivity());
        }
        if (z && this.p && isValid()) {
            SHApplication.getGlobalHandler().postDelayed(new Runnable() {
                public void run() {
                    if (SceneTabFragment.this.j != null && SceneTabFragment.this.m == SceneTabFragment.this.j) {
                        SceneTabFragment.this.j.d();
                    }
                }
            }, 200);
        }
        if (z && !HomeManager.A()) {
            SmartRecommendOperation.m();
        }
        f();
    }

    private void c() {
        SceneLogUtil.a("SceneTabFragment  initViewPager");
        if (getActivity() != null && !getActivity().isFinishing()) {
            this.c = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
            this.d.setAdapter(this.c);
            this.e.setTabMode(1);
            this.e.setupWithViewPager(this.d);
            this.e.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                public void onTabReselected(TabLayout.Tab tab) {
                }

                public void onTabUnselected(TabLayout.Tab tab) {
                }

                public void onTabSelected(TabLayout.Tab tab) {
                    PreferenceUtils.a(SHApplication.getAppContext(), SceneTabFragment.this.g(), tab.getPosition());
                    BaseFragment a2 = SceneTabFragment.this.m;
                    BaseFragment baseFragment = (BaseFragment) SceneTabFragment.this.c.getItem(tab.getPosition());
                    if (!(a2 == null || a2 == baseFragment)) {
                        a2.onPageDeselected();
                    }
                    BaseFragment unused = SceneTabFragment.this.m = baseFragment;
                    if (SceneTabFragment.this.m != null) {
                        SceneTabFragment.this.m.onPageSelected();
                    }
                }
            });
            f();
            if (this.j != null && this.m == this.j) {
                this.j.d();
            }
            this.o.setVisibility(8);
        }
    }

    private void d() {
        this.r = b();
        c();
        if (this.n != null) {
            this.n.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (SHApplication.getStateNotifier().a() != 4) {
                        SHApplication.showLoginDialog(SceneTabFragment.this.getActivity(), false);
                        return;
                    }
                    CreateSceneManager.a().a((SceneApi.SmartHomeScene) null);
                    SceneTabFragment.this.startActivity(new Intent(SceneTabFragment.this.getActivity(), SmarthomeCreateAutoSceneActivity.class));
                }
            });
        }
        RecommendSceneManager.a().a(this);
        SceneLogUtil.a("SceneTabFragment  initView");
        if (SHApplication.getStateNotifier().a() == 4) {
            e();
        }
    }

    private void e() {
        if (CoreApi.a().l()) {
            SceneManager.x().c((String) null);
            SceneManager.x().a();
            LiteSceneManager.j().b();
            return;
        }
        CoreApi.a().a((Context) getActivity(), (CoreApi.IsCoreReadyCallback) new CoreApi.IsCoreReadyCallback() {
            public void onCoreReady() {
                SceneManager.x().c((String) null);
                SceneManager.x().a();
                LiteSceneManager.j().b();
            }
        });
    }

    private void f() {
        SceneLogUtil.a("SceneTabFragment  onSwitchtoPage" + RecommendSceneManager.a().i());
        if (this.c == null) {
            this.f = PreferenceUtils.c(SHApplication.getAppContext(), g(), 0);
        } else if (RecommendSceneManager.a().i()) {
            this.f = this.c.b();
            RecommendSceneManager.a().c(false);
        } else if (RecommendSceneManager.a().j()) {
            this.f = this.c.a();
            RecommendSceneManager.a().b(false);
        } else {
            this.f = PreferenceUtils.c(SHApplication.getAppContext(), g(), 0);
        }
        if (this.f >= 0 && this.e.getTabCount() > this.f) {
            this.e.getTabAt(this.f).select();
            this.c.notifyDataSetChanged();
            this.d.setCurrentItem(this.f);
        }
    }

    public boolean a() {
        if (this.d == null || this.c == null || this.c.c < 0 || this.c.c >= this.c.getCount()) {
            return false;
        }
        try {
            this.d.setCurrentItem(this.c.c);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    /* access modifiers changed from: private */
    public String g() {
        if (CoreApi.a().D()) {
            return CoreApi.a().s() + t + "inter";
        }
        return CoreApi.a().s() + t;
    }

    public boolean onBackPressed() {
        if (this.m instanceof MySceneFragmentNew) {
            return this.m.onBackPressed();
        }
        getActivity().finish();
        return true;
    }

    public void onActivityResult(int i2, int i3, Intent intent) {
        if (i2 != 999) {
            super.onActivityResult(i2, i3, intent);
        } else if (this.j != null) {
            this.j.onActivityResult(i2, i3, intent);
        }
    }

    public void onCreate(Bundle bundle) {
        SceneLogUtil.a("SceneTabFragment  onCreate");
        super.onCreate(bundle);
        this.k.addAction("action_on_logout");
        this.k.addAction("action_on_login_success");
        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).registerReceiver(this.l, this.k);
    }

    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).unregisterReceiver(this.l);
        SceneLogUtil.a("SceneTabFragment  onDestroy");
    }

    /* access modifiers changed from: private */
    public void a(Intent intent) {
        if (this.j != null) {
            this.j.c();
        }
        if (this.h != null) {
            this.h.a();
        }
        if (this.i != null) {
            this.i.a(intent);
        }
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {

        /* renamed from: a  reason: collision with root package name */
        List<BaseFragment> f21804a = new ArrayList();
        int b;
        int c;
        int d;

        public ViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
            if (AndroidCommonConfigManager.a().h()) {
                SceneTabFragment.this.g = ChoicenessFragment.a(SceneTabFragment.this.h() ? "http://st.iot.home.mi.com/miot/activity/operation-smart/index.html?native_page=smart" : "https://home.mi.com/miot/activity/operation-smart/index.html?native_page=smart", "");
                this.f21804a.add(SceneTabFragment.this.g);
            }
            if (!CoreApi.a().D()) {
                SceneTabFragment.this.h = new RecommendSceneFragment();
                SceneTabFragment.this.h.setArguments(SceneTabFragment.this.getArguments());
                this.f21804a.add(SceneTabFragment.this.h);
                this.b = this.f21804a.size() - 1;
            }
            SceneTabFragment.this.j = new MySceneFragmentNew();
            SceneTabFragment.this.j.setArguments(SceneTabFragment.this.getArguments());
            SceneTabFragment.this.j.a((SceneTabFragment) SceneTabFragment.this);
            this.f21804a.add(SceneTabFragment.this.j);
            this.c = this.f21804a.size() - 1;
            SceneTabFragment.this.i = new SceneLogFragment();
            SceneTabFragment.this.i.setArguments(SceneTabFragment.this.getArguments());
            this.f21804a.add(SceneTabFragment.this.i);
            BaseFragment unused = SceneTabFragment.this.m = this.f21804a.get(0);
            SceneTabFragment.this.m.onPageSelected();
            this.d = this.f21804a.size() - 1;
        }

        public Fragment getItem(int i) {
            return this.f21804a.get(i);
        }

        public CharSequence getPageTitle(int i) {
            return SceneTabFragment.this.getString(((Integer) SceneTabFragment.this.r.get(i)).intValue());
        }

        public int getCount() {
            return SceneTabFragment.this.r.size();
        }

        /* access modifiers changed from: package-private */
        public int a() {
            return this.b;
        }

        /* access modifiers changed from: package-private */
        public int b() {
            return this.d;
        }

        /* access modifiers changed from: package-private */
        public int c() {
            return this.c;
        }
    }
}
