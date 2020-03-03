package com.xiaomi.smarthome.framework.page.scene;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.page.BaseWhiteActivity;
import com.xiaomi.smarthome.framework.page.scene.DeviceSceneActivityNew;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.scene.CreateSceneManager;
import com.xiaomi.smarthome.scene.api.SceneApi;
import com.xiaomi.smarthome.scenenew.PluginRecommendSceneFragment;
import com.xiaomi.smarthome.scenenew.SceneLogFragment;
import com.xiaomi.smarthome.scenenew.actiivity.SmarthomeCreateAutoSceneActivity;
import com.xiaomi.smarthome.scenenew.manager.PluginRecommendSceneManager;
import com.xiaomi.smarthome.stat.STAT;
import java.util.ArrayList;
import java.util.List;

public class DeviceSceneActivityNew extends BaseWhiteActivity {
    public static final String DEVICE_ID = "device_id";

    /* renamed from: a  reason: collision with root package name */
    private static final String f16968a = "DeviceSceneActivityNew";
    private LayoutInflater b;
    private Context c;
    private Room d;
    private GroupData e;
    private GroupData f;
    /* access modifiers changed from: private */
    public List<Fragment> g = new ArrayList();
    private View h;
    private View i;
    private Device j;
    /* access modifiers changed from: private */
    public String[] k;
    /* access modifiers changed from: private */
    public int l;
    /* access modifiers changed from: private */
    public PluginRecommendSceneFragment m;
    SimpleFragmentPagerAdapter mAdapter;
    String mDid;
    @BindView(2131430623)
    View mLoadingView;
    @BindView(2131430969)
    ImageView mReturnIV;
    @BindView(2131430982)
    ImageView mRightTitleIV;
    @BindView(2131432720)
    TabLayout mTabLayout;
    @BindView(2131430975)
    TextView mTitleTV;
    @BindView(2131433842)
    ViewPager mViewPager;
    /* access modifiers changed from: private */
    public MyDeviceSceneFragmentNew n;
    /* access modifiers changed from: private */
    public SceneLogFragment o;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_device_scene_tab_layout);
        ButterKnife.bind((Activity) this);
        this.c = this;
        this.b = LayoutInflater.from(this.c);
        if (getIntent() == null) {
            finish();
            return;
        }
        this.mDid = getIntent().getStringExtra("device_id");
        if (TextUtils.isEmpty(this.mDid)) {
            finish();
            return;
        }
        this.j = SmartHomeDeviceManager.a().n(this.mDid);
        if (this.j == null) {
            finish();
        } else if (this.j == null || this.j.isOwner()) {
            if (getIntent().getBooleanExtra("is_from_home", false)) {
                STAT.e.l(this.j.model, "plugin");
            } else {
                STAT.e.l(this.j.model, "gt");
            }
            this.mTitleTV.setText(this.j.name);
            a();
            this.mReturnIV.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    DeviceSceneActivityNew.this.finish();
                }
            });
            this.mRightTitleIV.setVisibility(0);
            this.mRightTitleIV.setImageResource(R.drawable.home_icon_add_2);
            this.mRightTitleIV.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (SHApplication.getStateNotifier().a() != 4) {
                        SHApplication.showLoginDialog(DeviceSceneActivityNew.this, false);
                        return;
                    }
                    CreateSceneManager.a().a((SceneApi.SmartHomeScene) null);
                    Intent intent = new Intent(DeviceSceneActivityNew.this, SmarthomeCreateAutoSceneActivity.class);
                    intent.putExtra(SmarthomeCreateAutoSceneActivity.EXTRA_DEVICE_ID, DeviceSceneActivityNew.this.mDid);
                    intent.putExtra("from", 1);
                    DeviceSceneActivityNew.this.startActivity(intent);
                }
            });
        } else {
            ToastUtil.a((int) R.string.device_no_owner_not_support_automation);
            finish();
        }
    }

    private void a() {
        if (!ServerCompact.e(SHApplication.getAppContext())) {
            PluginRecommendSceneManager.a().b(this.mDid, -1, new AsyncCallback<PluginRecommendSceneInfo, Error>() {
                /* renamed from: a */
                public void onSuccess(PluginRecommendSceneInfo pluginRecommendSceneInfo) {
                    DeviceSceneActivityNew.this.mHandler.post(new Runnable(pluginRecommendSceneInfo) {
                        private final /* synthetic */ PluginRecommendSceneInfo f$1;

                        {
                            this.f$1 = r2;
                        }

                        public final void run() {
                            DeviceSceneActivityNew.AnonymousClass3.this.b(this.f$1);
                        }
                    });
                }

                /* access modifiers changed from: private */
                public /* synthetic */ void b(PluginRecommendSceneInfo pluginRecommendSceneInfo) {
                    if (pluginRecommendSceneInfo == null || pluginRecommendSceneInfo.mSceneItems == null || pluginRecommendSceneInfo.mSceneItems.size() <= 0) {
                        String[] unused = DeviceSceneActivityNew.this.k = new String[]{DeviceSceneActivityNew.this.getResources().getString(R.string.scene_plugin_title), DeviceSceneActivityNew.this.getResources().getString(R.string.smarthome_new_scene_log)};
                    } else {
                        String[] unused2 = DeviceSceneActivityNew.this.k = new String[]{DeviceSceneActivityNew.this.getResources().getString(R.string.smarthome_new_scene_recommend), DeviceSceneActivityNew.this.getResources().getString(R.string.scene_plugin_title), DeviceSceneActivityNew.this.getResources().getString(R.string.smarthome_new_scene_log)};
                    }
                    DeviceSceneActivityNew.this.b();
                }

                public void onFailure(Error error) {
                    DeviceSceneActivityNew.this.mHandler.post(new Runnable() {
                        public final void run() {
                            DeviceSceneActivityNew.AnonymousClass3.this.a();
                        }
                    });
                }

                /* access modifiers changed from: private */
                public /* synthetic */ void a() {
                    String[] unused = DeviceSceneActivityNew.this.k = new String[]{DeviceSceneActivityNew.this.getResources().getString(R.string.scene_plugin_title), DeviceSceneActivityNew.this.getResources().getString(R.string.smarthome_new_scene_log)};
                    DeviceSceneActivityNew.this.b();
                }
            });
        } else {
            this.mHandler.post(new Runnable() {
                public final void run() {
                    DeviceSceneActivityNew.this.c();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void c() {
        this.k = new String[]{getResources().getString(R.string.scene_plugin_title), getResources().getString(R.string.smarthome_new_scene_log)};
        b();
    }

    /* access modifiers changed from: private */
    public void b() {
        Bundle bundle = new Bundle();
        bundle.putString("device_id", this.mDid);
        if (this.m == null) {
            this.m = new PluginRecommendSceneFragment();
            this.m.setArguments(bundle);
        }
        if (this.n == null) {
            this.n = new MyDeviceSceneFragmentNew();
            this.n.setArguments(bundle);
        }
        if (this.o == null) {
            this.o = new SceneLogFragment();
            this.o.setArguments(bundle);
        }
        if (this.mAdapter == null) {
            this.mAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager());
            this.mViewPager.setAdapter(this.mAdapter);
            this.mTabLayout.setTabMode(1);
            this.mTabLayout.setupWithViewPager(this.mViewPager);
            this.mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                public void onTabReselected(TabLayout.Tab tab) {
                }

                public void onTabUnselected(TabLayout.Tab tab) {
                }

                public void onTabSelected(TabLayout.Tab tab) {
                    int unused = DeviceSceneActivityNew.this.l = tab.getPosition();
                    if (DeviceSceneActivityNew.this.l == DeviceSceneActivityNew.this.g.size() - 1 && (DeviceSceneActivityNew.this.mAdapter.getItem(DeviceSceneActivityNew.this.mAdapter.getCount() - 1) instanceof SceneLogFragment)) {
                        ((SceneLogFragment) DeviceSceneActivityNew.this.mAdapter.getItem(DeviceSceneActivityNew.this.mAdapter.getCount() - 1)).onPageSelected();
                    }
                    if (DeviceSceneActivityNew.this.mAdapter.getItem(DeviceSceneActivityNew.this.l) instanceof PluginRecommendSceneFragment) {
                        ((PluginRecommendSceneFragment) DeviceSceneActivityNew.this.mAdapter.getItem(DeviceSceneActivityNew.this.l)).onPageSelected();
                    }
                    LogUtil.a(DeviceSceneActivityNew.f16968a, "onTabSelected" + tab.getPosition());
                }
            });
        }
        this.mAdapter.a();
        this.mTabLayout.getTabAt(0).select();
        this.mLoadingView.setVisibility(8);
    }

    private class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {
        public int getItemPosition(Object obj) {
            return -2;
        }

        public SimpleFragmentPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        public void a() {
            DeviceSceneActivityNew.this.g.clear();
            if (DeviceSceneActivityNew.this.k.length == 3) {
                DeviceSceneActivityNew.this.g.add(DeviceSceneActivityNew.this.m);
            }
            DeviceSceneActivityNew.this.g.add(DeviceSceneActivityNew.this.n);
            DeviceSceneActivityNew.this.g.add(DeviceSceneActivityNew.this.o);
            notifyDataSetChanged();
        }

        public CharSequence getPageTitle(int i) {
            return DeviceSceneActivityNew.this.k[i];
        }

        public Fragment getItem(int i) {
            if (i < DeviceSceneActivityNew.this.g.size()) {
                return (Fragment) DeviceSceneActivityNew.this.g.get(i);
            }
            return null;
        }

        public int getCount() {
            return DeviceSceneActivityNew.this.g.size();
        }

        public long getItemId(int i) {
            if (DeviceSceneActivityNew.this.g.get(i) instanceof PluginRecommendSceneFragment) {
                return 0;
            }
            if (DeviceSceneActivityNew.this.g.get(i) instanceof MyDeviceSceneFragmentNew) {
                return 1;
            }
            if (DeviceSceneActivityNew.this.g.get(i) instanceof SceneLogFragment) {
                return 2;
            }
            return 0;
        }
    }

    public View getChooseSceneTitleBar() {
        if (this.h == null) {
            this.h = ((ViewStub) findViewById(R.id.title_bar_choose_scene_stub)).inflate();
            TitleBarUtil.a(getResources().getDimensionPixelSize(R.dimen.title_bar_top_padding), findViewById(R.id.title_bar_choose_scene));
        }
        return this.h;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        TitleBarUtil.a((Activity) this);
    }

    public View getChooseSceneMenuBar() {
        if (this.i == null) {
            this.i = ((ViewStub) findViewById(R.id.menu_choose_scene_stub)).inflate();
        }
        return this.i;
    }

    public void onBackPressed() {
        if ((this.g.size() != 2 || this.l != 0) && (this.g.size() != 3 || this.l != 1)) {
            super.onBackPressed();
        } else if (this.g.get(this.l) instanceof MyDeviceSceneFragmentNew) {
            ((MyDeviceSceneFragmentNew) this.g.get(this.l)).a((Activity) this);
        }
    }
}
