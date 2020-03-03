package com.xiaomi.smarthome.scene;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.router.api.SceneManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceHelper;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.SharePrefsManager;
import com.xiaomi.smarthome.library.common.widget.ExpandableItemIndicator;
import com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity;
import com.xiaomi.smarthome.scene.StartConditionActivityNew;
import com.xiaomi.smarthome.scene.api.SceneApi;
import com.xiaomi.smarthome.scene.condition.BaseInnerCondition;
import com.xiaomi.smarthome.scene.condition.ClickInnerCondition;
import com.xiaomi.smarthome.scene.condition.CommonTextCondition;
import com.xiaomi.smarthome.scene.condition.DeviceConditionFilter;
import com.xiaomi.smarthome.scene.condition.IZatGeoFencingInnerCondition;
import com.xiaomi.smarthome.scene.condition.InnerConditionCommon;
import com.xiaomi.smarthome.scene.condition.IntelligentTextCondition;
import com.xiaomi.smarthome.scene.condition.TimerInnerCondition;
import com.xiaomi.smarthome.scene.condition.WeatherInnerCondition;
import com.xiaomi.smarthome.scene.geofence.manager.MIUIGeoFenceManager;
import com.xiaomi.smarthome.scene.view.SceneFilterManager;
import com.xiaomi.smarthome.scene.view.SceneFilterView;
import com.xiaomi.smarthome.scene.widget.ConditionDeviceFilter;
import com.xiaomi.smarthome.scenenew.actiivity.SmarthomeChooseWeatherConditionActivity;
import com.xiaomi.smarthome.scenenew.actiivity.SmarthomeCreateAutoSceneActivity;
import com.xiaomi.smarthome.scenenew.view.SelectRoomDialogView;
import com.xiaomi.youpin.app_sdk.url_dispatch.UrlDispatchManger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class StartConditionActivityNew extends BaseActivity {
    public static final String EXTRA_DEFAULT_CONDITIONS = "extra_default_conditions";
    public static final String EXTRA_EXCLUDE_CONDITIONS = "extra_exclude_conditions";
    public static final int GET_CONDITION_DETAIL = 100;

    /* renamed from: a  reason: collision with root package name */
    private static final String f21475a = "com.xiaomi.smarthome.scene.StartConditionActivityNew";
    private List<SmartHomeSceneCreateEditActivity.DefaultSceneItemSet> b;
    private List<SmartHomeSceneCreateEditActivity.DefaultSceneItemSet> c;
    ClickInnerCondition clickInnerCondition = new ClickInnerCondition((Device) null);
    private int d;
    DeviceConditionFilter deviceConditionFilter = new DeviceConditionFilter();
    private boolean e;
    /* access modifiers changed from: private */
    public int f = 0;
    private int g = 0;
    IZatGeoFencingInnerCondition geoFencingCondition = new IZatGeoFencingInnerCondition();
    /* access modifiers changed from: private */
    public BaseInnerCondition h;
    /* access modifiers changed from: private */
    public int i = -1;
    IntelligentTextCondition intelligentTextCondition = new IntelligentTextCondition((Device) null);
    /* access modifiers changed from: private */
    public LinearLayoutManager j;
    private SceneFilterView k;
    /* access modifiers changed from: private */
    public ConditionDeviceFilter l;
    /* access modifiers changed from: private */
    public MLAlertDialog m;
    @BindView(2131428019)
    Button mBuyButton;
    @BindView(2131428133)
    View mBuyView;
    CommonTextCondition mCommonTextCondition = new CommonTextCondition((Device) null);
    HashMap<BaseInnerCondition, Boolean> mConditionEnableMap = new HashMap<>();
    @BindView(2131428548)
    RecyclerView mContentListView;
    Context mContext;
    List<BaseInnerCondition> mDeviceConditions = new ArrayList();
    boolean mIsFirstIn = false;
    @BindView(2131427981)
    ExpandableItemIndicator mItemIndicator;
    ConditionAdapter mListAdapter;
    @BindView(2131430975)
    TextView mModuleA3ReturnTransparentTitle;
    List<Room> mRoomList = new ArrayList();
    int mSId = -1;
    SceneApi.SmartHomeScene mScene;
    String mSceneID;
    SceneApi.Condition mSelectedCondition = null;
    int mSelectedConditionPos = -1;
    /* access modifiers changed from: private */
    public MLAlertDialog n;
    private boolean o = false;
    TimerInnerCondition timerInnerCondition = new TimerInnerCondition((Device) null);
    @BindView(2131433002)
    View topDeviceTitleLayout;
    @BindView(2131433004)
    View topFilterLayout;
    WeatherInnerCondition weatherInnerCondition = new WeatherInnerCondition((Device) null);

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.smarthome_start_condition_v2);
        ButterKnife.bind((Activity) this);
        this.mContext = this;
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("extra_default_conditions")) {
                this.b = intent.getParcelableArrayListExtra("extra_default_conditions");
            }
            if (intent.hasExtra("extra_default_conditions")) {
                this.c = intent.getParcelableArrayListExtra("extra_exclude_conditions");
            }
            if (intent.hasExtra("from")) {
                this.d = intent.getIntExtra("from", 0);
            }
            if (intent.hasExtra(SmarthomeCreateAutoSceneActivity.FROM_MAIN)) {
                this.e = intent.getBooleanExtra(SmarthomeCreateAutoSceneActivity.FROM_MAIN, false);
            }
        }
        this.mScene = CreateSceneManager.a().g();
        if (this.mScene == null) {
            finish();
            return;
        }
        this.mSceneID = this.mScene.f;
        this.mIsFirstIn = SmartHomeSceneCreateEditActivity.isNewScene;
        SceneFilterManager.c().b(this.mDeviceConditions);
        this.k = (SceneFilterView) LayoutInflater.from(this.mContext).inflate(R.layout.scene_filter_view, (ViewGroup) null);
        this.k.setmDismissListener(new SceneFilterView.OnWindowDismissListener() {
            public void a() {
                StartConditionActivityNew.this.mItemIndicator.setExpandedState(false, false);
            }
        });
        this.j = new LinearLayoutManager(this);
        this.mContentListView.setLayoutManager(this.j);
        this.mContentListView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
            }

            public void onScrolled(@NonNull RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                if (StartConditionActivityNew.this.i >= 0) {
                    int findFirstVisibleItemPosition = StartConditionActivityNew.this.j.findFirstVisibleItemPosition();
                    if (findFirstVisibleItemPosition >= StartConditionActivityNew.this.i) {
                        StartConditionActivityNew.this.topFilterLayout.setVisibility(0);
                        StartConditionActivityNew.this.topDeviceTitleLayout.setVisibility(0);
                    } else if (findFirstVisibleItemPosition >= 0 && findFirstVisibleItemPosition < StartConditionActivityNew.this.i) {
                        StartConditionActivityNew.this.topFilterLayout.setVisibility(8);
                        StartConditionActivityNew.this.topDeviceTitleLayout.setVisibility(8);
                    }
                }
            }
        });
        this.mListAdapter = new ConditionAdapter();
        this.mContentListView.setAdapter(this.mListAdapter);
        b();
        if (this.mDeviceConditions.size() != 0 || this.b == null || this.b.size() <= 0) {
            this.mBuyView.setVisibility(8);
        } else {
            this.mBuyView.setVisibility(0);
            this.mContentListView.setVisibility(8);
            this.mBuyButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    UrlDispatchManger.a().c("https://home.mi.com/shop/main");
                }
            });
        }
        a();
        if (this.e) {
            CreateSceneManager.a().a((Room) null);
            ClickInnerCondition clickInnerCondition2 = new ClickInnerCondition((Device) null);
            this.mScene.l.add(clickInnerCondition2.a(-1, (Intent) null));
            addCompatibleId(clickInnerCondition2.c(0));
            finish();
            this.e = false;
        }
        if (!this.mDeviceConditions.isEmpty()) {
            c();
            this.l = new ConditionDeviceFilter(this) {
                public int b(Home home) {
                    return 1;
                }

                public void a(Home home, DeviceTagInterface.Category category) {
                    if (StartConditionActivityNew.this.isValid() && StartConditionActivityNew.this.m != null) {
                        StartConditionActivityNew.this.a(home, category);
                        ((TextView) StartConditionActivityNew.this.topFilterLayout.findViewById(R.id.filter_name)).setText(category.d);
                        StartConditionActivityNew.this.deviceConditionFilter.a(category.d);
                        StartConditionActivityNew.this.mListAdapter.notifyDataSetChanged();
                        StartConditionActivityNew.this.m.dismiss();
                    }
                }

                public void a(Home home, Room room) {
                    if (StartConditionActivityNew.this.isValid() && StartConditionActivityNew.this.m != null) {
                        if (room == null || !TextUtils.equals(SelectRoomDialogView.ALL_ROOM_ID, room.d())) {
                            StartConditionActivityNew.this.a(home, room);
                        } else {
                            StartConditionActivityNew.this.b(home);
                        }
                        ((TextView) StartConditionActivityNew.this.topFilterLayout.findViewById(R.id.filter_name)).setText(room.e());
                        StartConditionActivityNew.this.deviceConditionFilter.a(room.e());
                        StartConditionActivityNew.this.mListAdapter.notifyDataSetChanged();
                        StartConditionActivityNew.this.m.dismiss();
                    }
                }

                public void a(Home home) {
                    if (StartConditionActivityNew.this.isValid() && StartConditionActivityNew.this.m != null) {
                        StartConditionActivityNew.this.b(home);
                        ((TextView) StartConditionActivityNew.this.topFilterLayout.findViewById(R.id.filter_name)).setText(R.string.tag_all);
                        StartConditionActivityNew.this.deviceConditionFilter.a((String) null);
                        StartConditionActivityNew.this.mListAdapter.notifyDataSetChanged();
                    }
                }

                public int a(String str, Room room) {
                    return StartConditionActivityNew.this.a(str, room);
                }

                public int a(String str, DeviceTagInterface.Category category) {
                    return StartConditionActivityNew.this.a(str, category);
                }
            };
            this.m = this.l.a();
        }
        this.topFilterLayout.setOnClickListener((View.OnClickListener) null);
        this.topFilterLayout.findViewById(R.id.tv_group_filter).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (StartConditionActivityNew.this.l != null) {
                    StartConditionActivityNew.this.l.c();
                }
            }
        });
    }

    private void a() {
        this.mItemIndicator.setVisibility(8);
        this.mModuleA3ReturnTransparentTitle.setText(R.string.smarthome_scene_choose_condition);
    }

    /* JADX WARNING: Removed duplicated region for block: B:119:0x02ef  */
    /* JADX WARNING: Removed duplicated region for block: B:175:0x0371 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void b() {
        /*
            r11 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "initDeviceCondition OnlineDevice size:  "
            r0.append(r1)
            com.xiaomi.smarthome.device.SmartHomeDeviceManager r1 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.a()
            java.util.List r1 = r1.d()
            int r1 = r1.size()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.xiaomi.smarthome.lite.scene.SceneLogUtil.a(r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "initDeviceCondition CommonOnlineScenes size:  "
            r0.append(r1)
            com.xiaomi.router.api.SceneManager r1 = com.xiaomi.router.api.SceneManager.x()
            java.util.Map r1 = r1.e()
            int r1 = r1.size()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.xiaomi.smarthome.lite.scene.SceneLogUtil.a(r0)
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            com.xiaomi.smarthome.device.SmartHomeDeviceManager r1 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.a()
            java.util.List r1 = r1.d()
            r0.addAll(r1)
            int r1 = r0.size()
            r2 = 1
            int r1 = r1 - r2
        L_0x0056:
            if (r1 < 0) goto L_0x006a
            java.lang.Object r3 = r0.get(r1)
            com.xiaomi.smarthome.device.Device r3 = (com.xiaomi.smarthome.device.Device) r3
            boolean r3 = r3.isSubDevice()
            if (r3 == 0) goto L_0x0067
            r0.remove(r1)
        L_0x0067:
            int r1 = r1 + -1
            goto L_0x0056
        L_0x006a:
            java.util.Iterator r0 = r0.iterator()
        L_0x006e:
            boolean r1 = r0.hasNext()
            r3 = 0
            r4 = 0
            if (r1 == 0) goto L_0x00ca
            java.lang.Object r1 = r0.next()
            com.xiaomi.smarthome.device.Device r1 = (com.xiaomi.smarthome.device.Device) r1
            boolean r5 = r1.isOwner()
            if (r5 != 0) goto L_0x0083
            goto L_0x006e
        L_0x0083:
            java.util.List<com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet> r5 = r11.c
            if (r5 == 0) goto L_0x00be
            java.util.List<com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet> r5 = r11.c
            int r5 = r5.size()
            if (r5 <= 0) goto L_0x00be
            java.util.List<com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet> r5 = r11.c
            java.util.Iterator r5 = r5.iterator()
        L_0x0095:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L_0x00bb
            java.lang.Object r6 = r5.next()
            com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet r6 = (com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity.DefaultSceneItemSet) r6
            java.lang.String r7 = r1.model
            java.lang.String[] r8 = r6.b
            boolean r7 = com.xiaomi.smarthome.device.DeviceFactory.a((java.lang.String) r7, (java.lang.String[]) r8)
            if (r7 != 0) goto L_0x00b9
            java.lang.String r7 = r6.e
            if (r7 == 0) goto L_0x0095
            java.lang.String r7 = r6.e
            java.lang.String r8 = r1.did
            boolean r7 = r7.equalsIgnoreCase(r8)
            if (r7 == 0) goto L_0x0095
        L_0x00b9:
            r3 = r6
            r4 = 1
        L_0x00bb:
            if (r4 == 0) goto L_0x00be
            goto L_0x006e
        L_0x00be:
            com.xiaomi.smarthome.scene.condition.BaseInnerCondition r1 = com.xiaomi.smarthome.scene.condition.BaseInnerCondition.a((com.xiaomi.smarthome.device.Device) r1, (com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity.DefaultSceneItemSet) r3)
            if (r1 == 0) goto L_0x006e
            java.util.List<com.xiaomi.smarthome.scene.condition.BaseInnerCondition> r3 = r11.mDeviceConditions
            r3.add(r1)
            goto L_0x006e
        L_0x00ca:
            com.xiaomi.smarthome.device.SmartHomeDeviceManager r0 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.a()
            java.util.List r0 = r0.k()
            java.util.Iterator r0 = r0.iterator()
        L_0x00d6:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0167
            java.lang.Object r1 = r0.next()
            com.xiaomi.smarthome.device.Device r1 = (com.xiaomi.smarthome.device.Device) r1
            boolean r5 = r1.isOwner()
            if (r5 != 0) goto L_0x00e9
            goto L_0x00d6
        L_0x00e9:
            com.xiaomi.smarthome.device.SmartHomeDeviceManager r5 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.a()
            java.lang.String r6 = r1.parentId
            com.xiaomi.smarthome.device.Device r5 = r5.b((java.lang.String) r6)
            if (r5 == 0) goto L_0x00d6
            boolean r5 = r5.isOwner()
            if (r5 != 0) goto L_0x00fc
            goto L_0x00d6
        L_0x00fc:
            java.util.List<com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet> r5 = r11.c
            if (r5 == 0) goto L_0x0123
            java.util.List<com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet> r5 = r11.c
            java.util.Iterator r5 = r5.iterator()
        L_0x0106:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L_0x011e
            java.lang.Object r6 = r5.next()
            com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet r6 = (com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity.DefaultSceneItemSet) r6
            java.lang.String r7 = r1.model
            java.lang.String[] r8 = r6.b
            boolean r7 = com.xiaomi.smarthome.device.DeviceFactory.a((java.lang.String) r7, (java.lang.String[]) r8)
            if (r7 == 0) goto L_0x0106
            r5 = 1
            goto L_0x0120
        L_0x011e:
            r6 = r3
            r5 = 0
        L_0x0120:
            if (r5 == 0) goto L_0x0124
            goto L_0x00d6
        L_0x0123:
            r6 = r3
        L_0x0124:
            com.xiaomi.smarthome.scene.condition.BaseInnerCondition r5 = com.xiaomi.smarthome.scene.condition.BaseInnerCondition.a((com.xiaomi.smarthome.device.Device) r1, (com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity.DefaultSceneItemSet) r6)
            if (r5 == 0) goto L_0x00d6
            r6 = 0
        L_0x012b:
            java.util.List<com.xiaomi.smarthome.scene.condition.BaseInnerCondition> r7 = r11.mDeviceConditions
            int r7 = r7.size()
            if (r6 >= r7) goto L_0x015d
            java.util.List<com.xiaomi.smarthome.scene.condition.BaseInnerCondition> r7 = r11.mDeviceConditions
            java.lang.Object r7 = r7.get(r6)
            com.xiaomi.smarthome.scene.condition.BaseInnerCondition r7 = (com.xiaomi.smarthome.scene.condition.BaseInnerCondition) r7
            com.xiaomi.smarthome.device.Device r7 = r7.d()
            if (r7 == 0) goto L_0x015a
            boolean r8 = r7.isSubDevice()
            if (r8 != 0) goto L_0x015a
            java.lang.String r8 = r1.parentId
            java.lang.String r7 = r7.did
            boolean r7 = r8.equalsIgnoreCase(r7)
            if (r7 == 0) goto L_0x015a
            java.util.List<com.xiaomi.smarthome.scene.condition.BaseInnerCondition> r1 = r11.mDeviceConditions
            int r6 = r6 + 1
            r1.add(r6, r5)
            r1 = 1
            goto L_0x015e
        L_0x015a:
            int r6 = r6 + 1
            goto L_0x012b
        L_0x015d:
            r1 = 0
        L_0x015e:
            if (r1 != 0) goto L_0x00d6
            java.util.List<com.xiaomi.smarthome.scene.condition.BaseInnerCondition> r1 = r11.mDeviceConditions
            r1.add(r5)
            goto L_0x00d6
        L_0x0167:
            java.util.List<com.xiaomi.smarthome.scene.condition.BaseInnerCondition> r0 = r11.mDeviceConditions
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L_0x0172
            r11.o = r4
            goto L_0x0174
        L_0x0172:
            r11.o = r2
        L_0x0174:
            java.util.List<com.xiaomi.smarthome.scene.condition.BaseInnerCondition> r0 = r11.mDeviceConditions
            java.util.List r1 = r11.a((boolean) r4)
            r0.addAll(r4, r1)
            com.xiaomi.smarthome.scene.CreateSceneManager r0 = com.xiaomi.smarthome.scene.CreateSceneManager.a()
            com.xiaomi.smarthome.scene.api.SceneApi$Condition r0 = r0.i()
            r11.mSelectedCondition = r0
            r1 = r3
            r0 = 0
        L_0x0189:
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r5 = r11.mScene
            java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Condition> r5 = r5.l
            int r5 = r5.size()
            r6 = -1
            if (r0 >= r5) goto L_0x0245
            java.util.List<com.xiaomi.smarthome.scene.condition.BaseInnerCondition> r5 = r11.mDeviceConditions
            int r5 = r5.size()
            int r5 = r5 - r2
        L_0x019b:
            if (r5 < 0) goto L_0x0241
            java.util.List<com.xiaomi.smarthome.scene.condition.BaseInnerCondition> r7 = r11.mDeviceConditions
            java.lang.Object r7 = r7.get(r5)
            com.xiaomi.smarthome.scene.condition.BaseInnerCondition r7 = (com.xiaomi.smarthome.scene.condition.BaseInnerCondition) r7
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r8 = r11.mScene
            java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Condition> r8 = r8.l
            java.lang.Object r8 = r8.get(r0)
            com.xiaomi.smarthome.scene.api.SceneApi$Condition r8 = (com.xiaomi.smarthome.scene.api.SceneApi.Condition) r8
            int r7 = r7.a((com.xiaomi.smarthome.scene.api.SceneApi.Condition) r8)
            r8 = -2
            if (r7 != r8) goto L_0x01d5
            com.xiaomi.smarthome.scene.api.SceneApi$Condition r8 = r11.mSelectedCondition
            if (r8 == 0) goto L_0x01d5
            com.xiaomi.smarthome.scene.api.SceneApi$Condition r8 = r11.mSelectedCondition
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r9 = r11.mScene
            java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Condition> r9 = r9.l
            java.lang.Object r9 = r9.get(r0)
            boolean r8 = r8.equals(r9)
            if (r8 == 0) goto L_0x01d5
            java.util.List<com.xiaomi.smarthome.scene.condition.BaseInnerCondition> r1 = r11.mDeviceConditions
            java.lang.Object r1 = r1.get(r5)
            com.xiaomi.smarthome.scene.condition.BaseInnerCondition r1 = (com.xiaomi.smarthome.scene.condition.BaseInnerCondition) r1
            r11.mSId = r7
            goto L_0x0241
        L_0x01d5:
            if (r7 == r6) goto L_0x023d
            com.xiaomi.smarthome.scene.api.SceneApi$Condition r6 = r11.mSelectedCondition
            if (r6 == 0) goto L_0x01f6
            com.xiaomi.smarthome.scene.api.SceneApi$Condition r6 = r11.mSelectedCondition
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r8 = r11.mScene
            java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Condition> r8 = r8.l
            java.lang.Object r8 = r8.get(r0)
            boolean r6 = r6.equals(r8)
            if (r6 == 0) goto L_0x01f6
            java.util.List<com.xiaomi.smarthome.scene.condition.BaseInnerCondition> r1 = r11.mDeviceConditions
            java.lang.Object r1 = r1.get(r5)
            com.xiaomi.smarthome.scene.condition.BaseInnerCondition r1 = (com.xiaomi.smarthome.scene.condition.BaseInnerCondition) r1
            r11.mSId = r7
            goto L_0x0241
        L_0x01f6:
            java.util.List<com.xiaomi.smarthome.scene.condition.BaseInnerCondition> r6 = r11.mDeviceConditions
            java.lang.Object r6 = r6.get(r5)
            com.xiaomi.smarthome.scene.condition.BaseInnerCondition r6 = (com.xiaomi.smarthome.scene.condition.BaseInnerCondition) r6
            boolean r6 = r6.g()
            if (r6 != 0) goto L_0x0214
            java.util.HashMap<com.xiaomi.smarthome.scene.condition.BaseInnerCondition, java.lang.Boolean> r6 = r11.mConditionEnableMap
            java.util.List<com.xiaomi.smarthome.scene.condition.BaseInnerCondition> r7 = r11.mDeviceConditions
            java.lang.Object r5 = r7.get(r5)
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r4)
            r6.put(r5, r7)
            goto L_0x0241
        L_0x0214:
            java.util.List<com.xiaomi.smarthome.scene.condition.BaseInnerCondition> r6 = r11.mDeviceConditions
            java.lang.Object r6 = r6.get(r5)
            com.xiaomi.smarthome.scene.condition.BaseInnerCondition r6 = (com.xiaomi.smarthome.scene.condition.BaseInnerCondition) r6
            r6.a((int) r7)
            java.util.List<com.xiaomi.smarthome.scene.condition.BaseInnerCondition> r6 = r11.mDeviceConditions
            java.lang.Object r6 = r6.get(r5)
            com.xiaomi.smarthome.scene.condition.BaseInnerCondition r6 = (com.xiaomi.smarthome.scene.condition.BaseInnerCondition) r6
            boolean r6 = r6.c()
            if (r6 == 0) goto L_0x0241
            java.util.HashMap<com.xiaomi.smarthome.scene.condition.BaseInnerCondition, java.lang.Boolean> r6 = r11.mConditionEnableMap
            java.util.List<com.xiaomi.smarthome.scene.condition.BaseInnerCondition> r7 = r11.mDeviceConditions
            java.lang.Object r5 = r7.get(r5)
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r4)
            r6.put(r5, r7)
            goto L_0x0241
        L_0x023d:
            int r5 = r5 + -1
            goto L_0x019b
        L_0x0241:
            int r0 = r0 + 1
            goto L_0x0189
        L_0x0245:
            int r0 = r11.mSId
            if (r0 == r6) goto L_0x0267
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r0 = r11.mScene
            java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Condition> r0 = r0.l
            com.xiaomi.smarthome.scene.api.SceneApi$Condition r5 = r11.mSelectedCondition
            int r0 = r0.indexOf(r5)
            r11.mSelectedConditionPos = r0
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r0 = r11.mScene
            java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Condition> r0 = r0.l
            com.xiaomi.smarthome.scene.api.SceneApi$Condition r5 = r11.mSelectedCondition
            r0.remove(r5)
            com.xiaomi.smarthome.scene.CreateSceneManager r0 = com.xiaomi.smarthome.scene.CreateSceneManager.a()
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r5 = r11.mScene
            r0.d(r5)
        L_0x0267:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.List<com.xiaomi.smarthome.scene.condition.BaseInnerCondition> r5 = r11.mDeviceConditions
            int r5 = r5.size()
            int r5 = r5 - r2
        L_0x0273:
            if (r5 < 0) goto L_0x0375
            java.util.List<com.xiaomi.smarthome.scene.condition.BaseInnerCondition> r7 = r11.mDeviceConditions
            java.lang.Object r7 = r7.get(r5)
            com.xiaomi.smarthome.scene.condition.BaseInnerCondition r7 = (com.xiaomi.smarthome.scene.condition.BaseInnerCondition) r7
            boolean r7 = r7.g()
            if (r7 == 0) goto L_0x0308
            r7 = 0
        L_0x0284:
            java.util.List<com.xiaomi.smarthome.scene.condition.BaseInnerCondition> r8 = r11.mDeviceConditions
            java.lang.Object r8 = r8.get(r5)
            com.xiaomi.smarthome.scene.condition.BaseInnerCondition r8 = (com.xiaomi.smarthome.scene.condition.BaseInnerCondition) r8
            int[] r8 = r8.f()
            if (r8 == 0) goto L_0x02db
            java.util.List<com.xiaomi.smarthome.scene.condition.BaseInnerCondition> r8 = r11.mDeviceConditions
            java.lang.Object r8 = r8.get(r5)
            com.xiaomi.smarthome.scene.condition.BaseInnerCondition r8 = (com.xiaomi.smarthome.scene.condition.BaseInnerCondition) r8
            int[] r8 = r8.f()
            int r8 = r8.length
            if (r7 >= r8) goto L_0x02db
            com.xiaomi.smarthome.scene.CreateSceneManager r8 = com.xiaomi.smarthome.scene.CreateSceneManager.a()
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r8 = r8.g()
            int r8 = r8.q
            if (r8 == r2) goto L_0x02d9
            com.xiaomi.smarthome.scene.CreateSceneManager r8 = com.xiaomi.smarthome.scene.CreateSceneManager.a()
            java.util.List<com.xiaomi.smarthome.scene.condition.BaseInnerCondition> r9 = r11.mDeviceConditions
            java.lang.Object r9 = r9.get(r5)
            com.xiaomi.smarthome.scene.condition.BaseInnerCondition r9 = (com.xiaomi.smarthome.scene.condition.BaseInnerCondition) r9
            java.util.List<com.xiaomi.smarthome.scene.condition.BaseInnerCondition> r10 = r11.mDeviceConditions
            java.lang.Object r10 = r10.get(r5)
            com.xiaomi.smarthome.scene.condition.BaseInnerCondition r10 = (com.xiaomi.smarthome.scene.condition.BaseInnerCondition) r10
            int[] r10 = r10.f()
            r10 = r10[r7]
            int r9 = r9.c(r10)
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            boolean r8 = r8.a((java.lang.Integer) r9)
            if (r8 == 0) goto L_0x02d6
            goto L_0x02d9
        L_0x02d6:
            int r7 = r7 + 1
            goto L_0x0284
        L_0x02d9:
            r7 = 0
            goto L_0x02dc
        L_0x02db:
            r7 = 1
        L_0x02dc:
            if (r7 != 0) goto L_0x02ed
            java.util.List<com.xiaomi.smarthome.scene.condition.BaseInnerCondition> r8 = r11.mDeviceConditions
            java.lang.Object r8 = r8.get(r5)
            com.xiaomi.smarthome.scene.condition.BaseInnerCondition r8 = (com.xiaomi.smarthome.scene.condition.BaseInnerCondition) r8
            boolean r8 = r8.c()
            if (r8 == 0) goto L_0x02ed
            r7 = 1
        L_0x02ed:
            if (r7 == 0) goto L_0x0371
            java.util.HashMap<com.xiaomi.smarthome.scene.condition.BaseInnerCondition, java.lang.Boolean> r7 = r11.mConditionEnableMap
            java.util.List<com.xiaomi.smarthome.scene.condition.BaseInnerCondition> r8 = r11.mDeviceConditions
            java.lang.Object r8 = r8.get(r5)
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r4)
            r7.put(r8, r9)
            java.util.List<com.xiaomi.smarthome.scene.condition.BaseInnerCondition> r7 = r11.mDeviceConditions
            java.lang.Object r7 = r7.remove(r5)
            r0.add(r7)
            goto L_0x0371
        L_0x0308:
            java.util.List<com.xiaomi.smarthome.scene.condition.BaseInnerCondition> r7 = r11.mDeviceConditions
            java.lang.Object r7 = r7.get(r5)
            boolean r7 = r7 instanceof com.xiaomi.smarthome.scene.condition.WeatherInnerCondition
            if (r7 == 0) goto L_0x0322
            java.util.HashMap<com.xiaomi.smarthome.scene.condition.BaseInnerCondition, java.lang.Boolean> r7 = r11.mConditionEnableMap
            java.util.List<com.xiaomi.smarthome.scene.condition.BaseInnerCondition> r8 = r11.mDeviceConditions
            java.lang.Object r8 = r8.get(r5)
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r2)
            r7.put(r8, r9)
            goto L_0x0371
        L_0x0322:
            com.xiaomi.smarthome.scene.CreateSceneManager r7 = com.xiaomi.smarthome.scene.CreateSceneManager.a()
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r7 = r7.g()
            int r7 = r7.q
            if (r7 == r2) goto L_0x0362
            com.xiaomi.smarthome.scene.CreateSceneManager r7 = com.xiaomi.smarthome.scene.CreateSceneManager.a()
            java.util.List<com.xiaomi.smarthome.scene.condition.BaseInnerCondition> r8 = r11.mDeviceConditions
            java.lang.Object r8 = r8.get(r5)
            com.xiaomi.smarthome.scene.condition.BaseInnerCondition r8 = (com.xiaomi.smarthome.scene.condition.BaseInnerCondition) r8
            int r8 = r8.c(r4)
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
            boolean r7 = r7.a((java.lang.Integer) r8)
            if (r7 == 0) goto L_0x0349
            goto L_0x0362
        L_0x0349:
            java.util.HashMap<com.xiaomi.smarthome.scene.condition.BaseInnerCondition, java.lang.Boolean> r7 = r11.mConditionEnableMap
            java.util.List<com.xiaomi.smarthome.scene.condition.BaseInnerCondition> r8 = r11.mDeviceConditions
            java.lang.Object r8 = r8.get(r5)
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r4)
            r7.put(r8, r9)
            java.util.List<com.xiaomi.smarthome.scene.condition.BaseInnerCondition> r7 = r11.mDeviceConditions
            java.lang.Object r7 = r7.remove(r5)
            r0.add(r7)
            goto L_0x0371
        L_0x0362:
            java.util.HashMap<com.xiaomi.smarthome.scene.condition.BaseInnerCondition, java.lang.Boolean> r7 = r11.mConditionEnableMap
            java.util.List<com.xiaomi.smarthome.scene.condition.BaseInnerCondition> r8 = r11.mDeviceConditions
            java.lang.Object r8 = r8.get(r5)
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r2)
            r7.put(r8, r9)
        L_0x0371:
            int r5 = r5 + -1
            goto L_0x0273
        L_0x0375:
            java.util.List<com.xiaomi.smarthome.scene.condition.BaseInnerCondition> r2 = r11.mDeviceConditions
            r2.addAll(r0)
            com.xiaomi.smarthome.scene.CreateSceneManager r0 = com.xiaomi.smarthome.scene.CreateSceneManager.a()
            com.xiaomi.smarthome.homeroom.model.Room r0 = r0.m()
            r11.a((com.xiaomi.smarthome.homeroom.model.Room) r0)
            int r0 = r11.mSId
            if (r0 == r6) goto L_0x038c
            r11.a(r1, r4, r3)
        L_0x038c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.scene.StartConditionActivityNew.b():void");
    }

    private void c() {
        this.mRoomList.clear();
        Room room = new Room();
        room.e(getString(R.string.smarthome_scene_all_room));
        room.d(SelectRoomDialogView.ALL_ROOM_ID);
        this.mRoomList.add(room);
        List<Room> e2 = HomeManager.a().e();
        if (e2 != null) {
            for (int i2 = 0; i2 < e2.size(); i2++) {
                Room room2 = e2.get(i2);
                if (a(HomeManager.a().l(), room2) > 0) {
                    this.mRoomList.add(room2);
                }
            }
        }
        Room room3 = new Room();
        room3.e(getString(R.string.tag_recommend_defaultroom));
        room3.d(SelectRoomDialogView.DEFAULT_ROOM_ID);
        if (a(HomeManager.a().l(), room3) > 0) {
            this.mRoomList.add(room3);
        }
    }

    /* access modifiers changed from: private */
    public int a(String str, DeviceTagInterface.Category category) {
        if (TextUtils.isEmpty(str) || category == null) {
            return 0;
        }
        Map<String, List<String>> j2 = SmartHomeDeviceHelper.a().b().j(str);
        if (j2.isEmpty()) {
            return 0;
        }
        List list = j2.get(category.d);
        if (list.isEmpty()) {
            return 0;
        }
        HashSet hashSet = new HashSet(list);
        for (int i2 = 0; i2 < this.mDeviceConditions.size(); i2++) {
            BaseInnerCondition baseInnerCondition = this.mDeviceConditions.get(i2);
            if (a(baseInnerCondition) && baseInnerCondition.d() != null && hashSet.contains(baseInnerCondition.d().did)) {
                return 1;
            }
        }
        return 0;
    }

    private int a(Home home) {
        if (home == null) {
            return 0;
        }
        List<String> a2 = HomeManager.a().a(home.j(), true);
        if (a2.isEmpty()) {
            return 0;
        }
        HashSet hashSet = new HashSet(a2);
        for (int i2 = 0; i2 < this.mDeviceConditions.size(); i2++) {
            BaseInnerCondition baseInnerCondition = this.mDeviceConditions.get(i2);
            if (a(baseInnerCondition) && baseInnerCondition.d() != null && hashSet.contains(baseInnerCondition.d().did)) {
                return 1;
            }
        }
        return 0;
    }

    /* access modifiers changed from: private */
    public int a(String str, Room room) {
        if (TextUtils.isEmpty(str) || room == null) {
            return 0;
        }
        if (room.d().equalsIgnoreCase(SelectRoomDialogView.ALL_ROOM_ID)) {
            return this.mDeviceConditions.size();
        }
        if (room.d().equalsIgnoreCase(SelectRoomDialogView.DEFAULT_ROOM_ID)) {
            List<Device> l2 = HomeManager.a().l(str);
            if (l2.isEmpty()) {
                return 0;
            }
            HashSet hashSet = new HashSet(l2);
            int i2 = 0;
            while (i2 < this.mDeviceConditions.size()) {
                BaseInnerCondition baseInnerCondition = this.mDeviceConditions.get(i2);
                if (!a(baseInnerCondition) || baseInnerCondition.d() == null || !hashSet.contains(baseInnerCondition.d())) {
                    i2++;
                }
            }
            return 0;
        }
        List<String> h2 = room.h();
        if (h2.isEmpty()) {
            return 0;
        }
        HashSet hashSet2 = new HashSet(h2);
        int i3 = 0;
        while (i3 < this.mDeviceConditions.size()) {
            BaseInnerCondition baseInnerCondition2 = this.mDeviceConditions.get(i3);
            if (!a(baseInnerCondition2) || baseInnerCondition2.d() == null || !hashSet2.contains(baseInnerCondition2.d().did)) {
                i3++;
            }
        }
        return 0;
        return 1;
    }

    /* access modifiers changed from: private */
    public void a(Home home, DeviceTagInterface.Category category) {
        if (home == null) {
            this.mListAdapter.a(this.mDeviceConditions);
        } else if (category == null) {
            b(home);
        } else {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(a(true));
            Map<String, List<String>> j2 = SmartHomeDeviceHelper.a().b().j(home.j());
            if (j2.isEmpty()) {
                b(home);
                return;
            }
            List list = j2.get(category.d);
            if (list.isEmpty()) {
                b(home);
                return;
            }
            HashSet hashSet = new HashSet(list);
            for (int i2 = 0; i2 < this.mDeviceConditions.size(); i2++) {
                BaseInnerCondition baseInnerCondition = this.mDeviceConditions.get(i2);
                if (a(baseInnerCondition) && baseInnerCondition.d() != null && hashSet.contains(baseInnerCondition.d().did)) {
                    arrayList.add(baseInnerCondition);
                }
            }
            this.mListAdapter.a(arrayList);
        }
    }

    /* access modifiers changed from: private */
    public void b(Home home) {
        if (home == null) {
            this.mListAdapter.a(this.mDeviceConditions);
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(a(true));
        List<String> a2 = HomeManager.a().a(home.j(), true);
        if (a2.isEmpty()) {
            this.mListAdapter.a(this.mDeviceConditions);
        }
        HashSet hashSet = new HashSet();
        if (!a2.isEmpty()) {
            hashSet.addAll(a2);
        }
        for (int i2 = 0; i2 < this.mDeviceConditions.size(); i2++) {
            BaseInnerCondition baseInnerCondition = this.mDeviceConditions.get(i2);
            if (a(baseInnerCondition) && baseInnerCondition.d() != null && hashSet.contains(baseInnerCondition.d().did)) {
                arrayList.add(baseInnerCondition);
            }
        }
        this.mListAdapter.a(arrayList);
    }

    /* access modifiers changed from: private */
    public void a(Home home, Room room) {
        if (home == null) {
            this.mListAdapter.a(this.mDeviceConditions);
        } else if (room == null) {
            b(home);
        } else {
            int i2 = 0;
            if (room.d().equalsIgnoreCase(SelectRoomDialogView.DEFAULT_ROOM_ID)) {
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(a(true));
                List<Device> l2 = HomeManager.a().l(home.j());
                if (l2.isEmpty()) {
                    b(home);
                    return;
                }
                HashSet hashSet = new HashSet(l2);
                while (i2 < this.mDeviceConditions.size()) {
                    BaseInnerCondition baseInnerCondition = this.mDeviceConditions.get(i2);
                    if (a(baseInnerCondition) && baseInnerCondition.d() != null && hashSet.contains(baseInnerCondition.d())) {
                        arrayList.add(baseInnerCondition);
                    }
                    i2++;
                }
                this.mListAdapter.a(arrayList);
                return;
            }
            ArrayList arrayList2 = new ArrayList();
            arrayList2.addAll(a(true));
            List<String> h2 = room.h();
            if (h2.isEmpty()) {
                b(home);
                return;
            }
            HashSet hashSet2 = new HashSet(h2);
            while (i2 < this.mDeviceConditions.size()) {
                BaseInnerCondition baseInnerCondition2 = this.mDeviceConditions.get(i2);
                if (a(baseInnerCondition2) && baseInnerCondition2.d() != null && hashSet2.contains(baseInnerCondition2.d().did)) {
                    arrayList2.add(baseInnerCondition2);
                }
                i2++;
            }
            this.mListAdapter.a(arrayList2);
            this.mListAdapter.notifyDataSetChanged();
        }
    }

    private List<BaseInnerCondition> a(boolean z) {
        ArrayList arrayList = new ArrayList();
        if (z) {
            arrayList.add(this.mCommonTextCondition);
        }
        if (this.mScene.l.size() < 1 && !d() && this.d != 1) {
            arrayList.add(this.clickInnerCondition);
        }
        arrayList.add(this.timerInnerCondition);
        if (!CoreApi.a().D()) {
            arrayList.add(this.weatherInnerCondition);
        }
        if (MIUIGeoFenceManager.b().a()) {
            arrayList.add(this.geoFencingCondition);
        }
        if (z) {
            arrayList.add(this.intelligentTextCondition);
        }
        if (this.o) {
            arrayList.add(this.deviceConditionFilter);
            this.i = arrayList.size() - 2;
        } else {
            this.i = -1;
        }
        return arrayList;
    }

    private boolean d() {
        if (this.mScene == null || this.mScene.k == null) {
            return false;
        }
        for (int i2 = 0; i2 < this.mScene.k.size(); i2++) {
            SceneApi.Action action = this.mScene.k.get(i2);
            if (action.g != null && (action.g instanceof SceneApi.SHLiteScenePayload)) {
                return true;
            }
        }
        return false;
    }

    private void a(Room room) {
        ArrayList arrayList = new ArrayList();
        for (Home next : HomeManager.a().f()) {
            if (next.p()) {
                List<Room> a2 = HomeManager.a().a(next.j());
                if (!a2.isEmpty()) {
                    arrayList.addAll(a2);
                }
            }
        }
        if (arrayList.size() == 0) {
            ArrayList arrayList2 = new ArrayList();
            arrayList2.addAll(a(true));
            for (int i2 = 0; i2 < this.mDeviceConditions.size(); i2++) {
                if (a(this.mDeviceConditions.get(i2))) {
                    arrayList2.add(this.mDeviceConditions.get(i2));
                }
            }
            this.mListAdapter.a(arrayList2);
            this.mDeviceConditions.clear();
            this.mDeviceConditions.addAll(arrayList2);
            return;
        }
        ArrayList arrayList3 = new ArrayList();
        arrayList3.addAll(a(true));
        int i3 = 0;
        while (true) {
            if (i3 < (this.mDeviceConditions.isEmpty() ? 0 : this.mDeviceConditions.size())) {
                if (a(this.mDeviceConditions.get(i3))) {
                    arrayList3.add(this.mDeviceConditions.get(i3));
                }
                i3++;
            } else {
                Collections.sort(arrayList3, new BaseInnerConditionComparator(room, arrayList3));
                this.mDeviceConditions.clear();
                this.mDeviceConditions.addAll(arrayList3);
                this.mListAdapter.a(this.mDeviceConditions);
                return;
            }
        }
    }

    private boolean a(BaseInnerCondition baseInnerCondition) {
        return baseInnerCondition instanceof InnerConditionCommon;
    }

    /* access modifiers changed from: private */
    public void a(BaseInnerCondition baseInnerCondition, boolean z, String str) {
        Intent intent = new Intent();
        intent.setClass(this, SmartHomeSceneDetailActivity.class);
        if (this.mSId != -1) {
            if (this.mSId == -2) {
                intent.putExtra("extra_selected_title", -1);
            } else {
                intent.putExtra("extra_selected_title", this.mSId);
            }
        }
        if (z && !TextUtils.isEmpty(str)) {
            intent.putExtra(SmartHomeSceneDetailActivity.EXTRA_SELECTED_DID, str);
        }
        intent.putExtra("extra_title", baseInnerCondition.e());
        startActivityForResult(intent, 100);
        CreateSceneManager.a().a(baseInnerCondition);
        this.h = baseInnerCondition;
    }

    public void onActivityResult(final int i2, final Intent intent) {
        this.mHandler.post(new Runnable() {
            public void run() {
                if (i2 == -1) {
                    if (StartConditionActivityNew.this.mSId != -1) {
                        StartConditionActivityNew.this.mScene.l.remove(StartConditionActivityNew.this.mSelectedCondition);
                    }
                    if (StartConditionActivityNew.this.f >= 0 && StartConditionActivityNew.this.f < StartConditionActivityNew.this.h.f().length) {
                        SceneApi.Condition a2 = StartConditionActivityNew.this.h.a(StartConditionActivityNew.this.h.f()[StartConditionActivityNew.this.f], intent);
                        StartConditionActivityNew.this.mScene.l.add(a2);
                        CreateSceneManager.a().a(a2);
                        StartConditionActivityNew.this.addCompatibleId(StartConditionActivityNew.this.h.c(StartConditionActivityNew.this.h.f()[StartConditionActivityNew.this.f]));
                    }
                    StartConditionActivityNew.this.finish();
                } else if (StartConditionActivityNew.this.mSId != -1) {
                    StartConditionActivityNew.this.mScene.l.add(StartConditionActivityNew.this.mSelectedConditionPos, StartConditionActivityNew.this.mSelectedCondition);
                    CreateSceneManager.a().a(StartConditionActivityNew.this.mSelectedCondition);
                    if (StartConditionActivityNew.this.mSelectedCondition.k != 0) {
                        StartConditionActivityNew.this.addCompatibleId(StartConditionActivityNew.this.mSelectedCondition.k);
                    }
                    StartConditionActivityNew.this.finish();
                } else {
                    int unused = StartConditionActivityNew.this.f = 0;
                    BaseInnerCondition unused2 = StartConditionActivityNew.this.h = null;
                    StartConditionActivityNew.this.mListAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (i2 == 100) {
            if (i3 == -1) {
                int intExtra = intent.getIntExtra("extra_index", -1);
                if (intExtra != -1) {
                    a(intExtra);
                } else {
                    finish();
                }
            } else if (this.mSId != -1) {
                if (this.mSelectedCondition != null) {
                    if (this.mSelectedConditionPos == -1) {
                        this.mScene.l.add(0, this.mSelectedCondition);
                    } else {
                        this.mScene.l.add(this.mSelectedConditionPos, this.mSelectedCondition);
                    }
                    addCompatibleId(this.mSelectedCondition.k);
                }
                finish();
            } else {
                this.mListAdapter.notifyDataSetChanged();
            }
        } else if (i2 == 102 && i3 == -1) {
            SceneApi.Condition i4 = CreateSceneManager.a().i();
            this.mScene.l.add(i4);
            addCompatibleId(i4.k);
            finish();
        } else if (i2 == 106 && i3 == -1) {
            WeatherInnerCondition n2 = CreateSceneManager.a().n();
            if (n2 != null) {
                SceneApi.Condition a2 = n2.a(n2.e, (Intent) null);
                this.mScene.l.add(a2);
                addCompatibleId(a2.k);
                finish();
                CreateSceneManager.a().a((WeatherInnerCondition) null);
            }
        } else if (i2 == this.g) {
            onActivityResult(i3, intent);
        }
    }

    @OnClick({2131430969})
    public void close() {
        if (SmarthomeCreateAutoSceneActivity.mIsInitStep) {
            CreateSceneManager.a().b = 3;
        }
        finish();
    }

    private class ConditionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private List<BaseInnerCondition> b = new ArrayList();
        private LayoutInflater c;

        public long getItemId(int i) {
            return (long) i;
        }

        public ConditionAdapter() {
            this.c = StartConditionActivityNew.this.getLayoutInflater();
        }

        public void a(List<BaseInnerCondition> list) {
            this.b.clear();
            if (!list.isEmpty()) {
                this.b.addAll(list);
            }
            StartConditionActivityNew.this.mContentListView.stopScroll();
            StartConditionActivityNew.this.mContentListView.getRecycledViewPool().clear();
            notifyDataSetChanged();
        }

        public void a() {
            this.b.clear();
        }

        @NonNull
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            switch (i) {
                case 0:
                    return new FilterViewHolder(this.c.inflate(R.layout.create_scene_filter_layout, viewGroup, false));
                case 1:
                    return new TextTitleViewHolder(this.c.inflate(R.layout.create_scene_common_title_layout, viewGroup, false));
                case 2:
                    return new CommonSelectionViewHolder(this.c.inflate(R.layout.create_scene_common_selection_layout, viewGroup, false));
                case 3:
                    return new DeviceSelectionViewHolder(this.c.inflate(R.layout.create_scene_device_selection_layout, viewGroup, false));
                case 4:
                    return new TextTitleViewHolder(this.c.inflate(R.layout.create_scene_device_title_layout, viewGroup, false));
                default:
                    return null;
            }
        }

        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            int itemViewType = getItemViewType(i);
            if (!this.b.isEmpty() && i < this.b.size() && i >= 0) {
                BaseInnerCondition baseInnerCondition = this.b.get(i);
                switch (itemViewType) {
                    case 0:
                        if ((viewHolder instanceof FilterViewHolder) && (baseInnerCondition instanceof DeviceConditionFilter)) {
                            ((FilterViewHolder) viewHolder).a((DeviceConditionFilter) baseInnerCondition);
                        }
                        viewHolder.itemView.setOnClickListener((View.OnClickListener) null);
                        return;
                    case 1:
                    case 4:
                        viewHolder.itemView.setOnClickListener((View.OnClickListener) null);
                        return;
                    case 2:
                        if (viewHolder instanceof CommonSelectionViewHolder) {
                            boolean z = true;
                            int i2 = i + 1;
                            if (i2 > 0 && i2 < getItemCount() && getItemViewType(i2) == 4) {
                                z = false;
                            }
                            if (baseInnerCondition instanceof ClickInnerCondition) {
                                ((CommonSelectionViewHolder) viewHolder).a(baseInnerCondition, z);
                                return;
                            } else if (baseInnerCondition instanceof TimerInnerCondition) {
                                ((CommonSelectionViewHolder) viewHolder).a(baseInnerCondition, z);
                                return;
                            } else if (baseInnerCondition instanceof WeatherInnerCondition) {
                                ((CommonSelectionViewHolder) viewHolder).a(baseInnerCondition, z);
                                return;
                            } else if (baseInnerCondition instanceof IZatGeoFencingInnerCondition) {
                                ((CommonSelectionViewHolder) viewHolder).a(baseInnerCondition, z);
                                return;
                            } else {
                                return;
                            }
                        } else {
                            return;
                        }
                    case 3:
                        if ((viewHolder instanceof DeviceSelectionViewHolder) && (baseInnerCondition instanceof InnerConditionCommon)) {
                            ((DeviceSelectionViewHolder) viewHolder).a((InnerConditionCommon) baseInnerCondition, i);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        }

        public int getItemCount() {
            if (this.b.isEmpty()) {
                return 0;
            }
            return this.b.size();
        }

        public int getItemViewType(int i) {
            if (this.b != null && i < this.b.size()) {
                if (this.b.get(i) instanceof CommonTextCondition) {
                    return 1;
                }
                if (this.b.get(i) instanceof IntelligentTextCondition) {
                    return 4;
                }
                if (this.b.get(i) instanceof DeviceConditionFilter) {
                    return 0;
                }
                if (this.b.get(i) instanceof InnerConditionCommon) {
                    return 3;
                }
                if ((this.b.get(i) instanceof ClickInnerCondition) || (this.b.get(i) instanceof TimerInnerCondition) || (this.b.get(i) instanceof WeatherInnerCondition) || (this.b.get(i) instanceof IZatGeoFencingInnerCondition)) {
                    return 2;
                }
            }
            return super.getItemViewType(i);
        }
    }

    class FilterViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f21488a = 0;
        View b;
        TextView c;

        public FilterViewHolder(View view) {
            super(view);
            this.b = view.findViewById(R.id.tv_group_filter);
            this.c = (TextView) view.findViewById(R.id.filter_name);
            this.b.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    StartConditionActivityNew.FilterViewHolder.this.a(view);
                }
            });
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(View view) {
            StartConditionActivityNew.this.l.c();
        }

        public void a(DeviceConditionFilter deviceConditionFilter) {
            if (deviceConditionFilter != null) {
                String h = deviceConditionFilter.h();
                TextView textView = this.c;
                if (TextUtils.isEmpty(h)) {
                    h = "";
                }
                textView.setText(h);
            }
        }
    }

    class TextTitleViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f21490a = 1;

        public TextTitleViewHolder(View view) {
            super(view);
        }
    }

    class TextTitle4DeviceViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f21489a = 4;

        public TextTitle4DeviceViewHolder(View view) {
            super(view);
        }
    }

    class CommonSelectionViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f21483a = 2;
        public TextView b;
        public SimpleDraweeView c;
        public ImageView d;
        public View e;

        public CommonSelectionViewHolder(View view) {
            super(view);
            this.b = (TextView) view.findViewById(R.id.condition_name);
            this.c = (SimpleDraweeView) view.findViewById(R.id.content_icon);
            this.d = (ImageView) view.findViewById(R.id.expand_hint);
            this.e = view.findViewById(R.id.item_divider);
        }

        public void a(BaseInnerCondition baseInnerCondition, boolean z) {
            if (baseInnerCondition != null) {
                this.e.setVisibility(z ? 0 : 8);
                String e2 = baseInnerCondition.e();
                TextView textView = this.b;
                if (TextUtils.isEmpty(e2)) {
                    e2 = "";
                }
                textView.setText(e2);
                StartConditionActivityNew.this.a(baseInnerCondition, this.c);
                if (StartConditionActivityNew.this.a(baseInnerCondition, this.d)) {
                    this.itemView.setOnClickListener(new View.OnClickListener() {
                        public final void onClick(View view) {
                            StartConditionActivityNew.CommonSelectionViewHolder.this.a(view);
                        }
                    });
                } else {
                    this.itemView.setOnClickListener(new View.OnClickListener(baseInnerCondition) {
                        private final /* synthetic */ BaseInnerCondition f$1;

                        {
                            this.f$1 = r2;
                        }

                        public final void onClick(View view) {
                            StartConditionActivityNew.CommonSelectionViewHolder.this.a(this.f$1, view);
                        }
                    });
                }
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(View view) {
            Toast.makeText(StartConditionActivityNew.this.mContext, R.string.scene_unclickable_toast_2, 0).show();
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(BaseInnerCondition baseInnerCondition, View view) {
            if ((baseInnerCondition instanceof IZatGeoFencingInnerCondition) && SharePrefsManager.b((Context) StartConditionActivityNew.this, "scene_pref", "is_first_setting_geo", true)) {
                SharePrefsManager.a((Context) StartConditionActivityNew.this, "scene_pref", "is_first_setting_geo", false);
                MLAlertDialog unused = StartConditionActivityNew.this.n = new MLAlertDialog.Builder(StartConditionActivityNew.this).a((int) R.string.scene_geo_tip_title).b((CharSequence) StartConditionActivityNew.this.getString(R.string.scene_geo_tip_content)).a((CharSequence) StartConditionActivityNew.this.getString(R.string.sdcard_tip_ok), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        StartConditionActivityNew.this.startActivity(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.parse("package:" + StartConditionActivityNew.this.getPackageName())));
                        if (StartConditionActivityNew.this.isValid()) {
                            StartConditionActivityNew.this.n.dismiss();
                        }
                    }
                }).b((CharSequence) StartConditionActivityNew.this.getString(R.string.scene_geo_tip_no), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (StartConditionActivityNew.this.isValid()) {
                            StartConditionActivityNew.this.n.dismiss();
                        }
                    }
                }).b();
                StartConditionActivityNew.this.n.show();
            } else if (baseInnerCondition.g()) {
                CreateSceneManager.a().l();
                StartConditionActivityNew.this.a(baseInnerCondition, false, (String) null);
            } else if (baseInnerCondition instanceof WeatherInnerCondition) {
                CreateSceneManager.a().a(new WeatherInnerCondition((Device) null));
                StartConditionActivityNew.this.startActivityForResult(new Intent(StartConditionActivityNew.this.mContext, SmarthomeChooseWeatherConditionActivity.class), 106);
            } else {
                CreateSceneManager.a().a((Room) null);
                int unused2 = StartConditionActivityNew.this.f = baseInnerCondition.a(-1, StartConditionActivityNew.this, StartConditionActivityNew.this.mSelectedCondition);
                if (StartConditionActivityNew.this.f != -1) {
                    BaseInnerCondition unused3 = StartConditionActivityNew.this.h = baseInnerCondition;
                    CreateSceneManager.a().c(StartConditionActivityNew.this.h.a(-1, (Intent) null));
                    return;
                }
                StartConditionActivityNew.this.mScene.l.add(baseInnerCondition.a(-1, (Intent) null));
                StartConditionActivityNew.this.addCompatibleId(baseInnerCondition.c(0));
                StartConditionActivityNew.this.finish();
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(BaseInnerCondition baseInnerCondition, SimpleDraweeView simpleDraweeView) {
        if (simpleDraweeView != null) {
            simpleDraweeView.setHierarchy(new GenericDraweeHierarchyBuilder(simpleDraweeView.getResources()).setFadeDuration(200).setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER).setPlaceholderImageScaleType(ScalingUtils.ScaleType.FIT_CENTER).build());
            baseInnerCondition.a(simpleDraweeView);
        }
    }

    /* access modifiers changed from: private */
    public boolean a(BaseInnerCondition baseInnerCondition, ImageView imageView) {
        if (imageView == null) {
            return false;
        }
        if (!this.mConditionEnableMap.containsKey(baseInnerCondition) || this.mConditionEnableMap.get(baseInnerCondition).booleanValue()) {
            imageView.setImageResource(R.drawable.std_list_main_next);
            return false;
        }
        imageView.setImageResource(R.drawable.std_scene_icon_lock);
        return true;
    }

    class DeviceSelectionViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f21487a = 3;
        public TextView b;
        public TextView c;
        public SimpleDraweeView d;
        public ImageView e;
        public TextView f;

        public DeviceSelectionViewHolder(View view) {
            super(view);
            this.b = (TextView) view.findViewById(R.id.device_name);
            this.c = (TextView) view.findViewById(R.id.room_name);
            this.f = (TextView) view.findViewById(R.id.offline_tv);
            this.d = (SimpleDraweeView) view.findViewById(R.id.content_icon);
            this.e = (ImageView) view.findViewById(R.id.expand_hint);
        }

        /* JADX WARNING: Removed duplicated region for block: B:15:0x0064  */
        /* JADX WARNING: Removed duplicated region for block: B:20:0x007b  */
        /* JADX WARNING: Removed duplicated region for block: B:23:0x008c  */
        /* JADX WARNING: Removed duplicated region for block: B:24:0x0097  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a(com.xiaomi.smarthome.scene.condition.InnerConditionCommon r12, int r13) {
            /*
                r11 = this;
                if (r12 != 0) goto L_0x0003
                return
            L_0x0003:
                android.widget.TextView r0 = r11.f
                r1 = 0
                r0.setVisibility(r1)
                r0 = 0
                com.xiaomi.smarthome.device.SmartHomeDeviceManager r2 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.a()
                com.xiaomi.smarthome.device.Device r3 = r12.d()
                java.lang.String r3 = r3.did
                com.xiaomi.smarthome.device.Device r2 = r2.n((java.lang.String) r3)
                r3 = 2131498233(0x7f0c14f9, float:1.8620082E38)
                if (r2 != 0) goto L_0x0023
                android.widget.TextView r4 = r11.f
                r4.setText(r3)
                goto L_0x004e
            L_0x0023:
                com.xiaomi.smarthome.scene.CreateSceneManager r4 = com.xiaomi.smarthome.scene.CreateSceneManager.a()
                java.lang.String r5 = r2.did
                boolean r4 = r4.a((java.lang.String) r5)
                if (r4 == 0) goto L_0x003d
                android.widget.TextView r0 = r11.f
                r1 = 2131498334(0x7f0c155e, float:1.8620286E38)
                r0.setText(r1)
                r1 = 1
                java.lang.String r0 = r2.did
                r9 = r0
                r8 = 1
                goto L_0x0050
            L_0x003d:
                boolean r4 = r2.isOnline
                if (r4 != 0) goto L_0x0047
                android.widget.TextView r4 = r11.f
                r4.setText(r3)
                goto L_0x004e
            L_0x0047:
                android.widget.TextView r3 = r11.f
                r4 = 8
                r3.setVisibility(r4)
            L_0x004e:
                r9 = r0
                r8 = 0
            L_0x0050:
                android.widget.TextView r0 = r11.c
                com.xiaomi.smarthome.scene.StartConditionActivityNew r1 = com.xiaomi.smarthome.scene.StartConditionActivityNew.this
                java.lang.String r1 = r1.b((com.xiaomi.smarthome.scene.condition.BaseInnerCondition) r12)
                r0.setText(r1)
                com.xiaomi.smarthome.scene.StartConditionActivityNew r0 = com.xiaomi.smarthome.scene.StartConditionActivityNew.this
                com.facebook.drawee.view.SimpleDraweeView r1 = r11.d
                r0.a((com.xiaomi.smarthome.scene.condition.BaseInnerCondition) r12, (com.facebook.drawee.view.SimpleDraweeView) r1)
                if (r2 == 0) goto L_0x007b
                android.widget.TextView r0 = r11.b
                java.lang.CharSequence r1 = r2.getName()
                boolean r1 = android.text.TextUtils.isEmpty(r1)
                if (r1 == 0) goto L_0x0073
                java.lang.String r1 = ""
                goto L_0x0077
            L_0x0073:
                java.lang.CharSequence r1 = r2.getName()
            L_0x0077:
                r0.setText(r1)
                goto L_0x0082
            L_0x007b:
                android.widget.TextView r0 = r11.b
                java.lang.String r1 = ""
                r0.setText(r1)
            L_0x0082:
                com.xiaomi.smarthome.scene.StartConditionActivityNew r0 = com.xiaomi.smarthome.scene.StartConditionActivityNew.this
                android.widget.ImageView r1 = r11.e
                boolean r0 = r0.a((com.xiaomi.smarthome.scene.condition.BaseInnerCondition) r12, (android.widget.ImageView) r1)
                if (r0 == 0) goto L_0x0097
                android.view.View r12 = r11.itemView
                com.xiaomi.smarthome.scene.-$$Lambda$StartConditionActivityNew$DeviceSelectionViewHolder$CNG5UfrVJq3q97qL3BipeqNrRjE r13 = new com.xiaomi.smarthome.scene.-$$Lambda$StartConditionActivityNew$DeviceSelectionViewHolder$CNG5UfrVJq3q97qL3BipeqNrRjE
                r13.<init>()
                r12.setOnClickListener(r13)
                goto L_0x00a5
            L_0x0097:
                android.view.View r0 = r11.itemView
                com.xiaomi.smarthome.scene.-$$Lambda$StartConditionActivityNew$DeviceSelectionViewHolder$-ONmehbyUsY4VVJttq7CIKaoL-4 r1 = new com.xiaomi.smarthome.scene.-$$Lambda$StartConditionActivityNew$DeviceSelectionViewHolder$-ONmehbyUsY4VVJttq7CIKaoL-4
                r5 = r1
                r6 = r11
                r7 = r12
                r10 = r13
                r5.<init>(r7, r8, r9, r10)
                r0.setOnClickListener(r1)
            L_0x00a5:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.scene.StartConditionActivityNew.DeviceSelectionViewHolder.a(com.xiaomi.smarthome.scene.condition.InnerConditionCommon, int):void");
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(View view) {
            Toast.makeText(StartConditionActivityNew.this.mContext, R.string.scene_unclickable_toast_2, 0).show();
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(InnerConditionCommon innerConditionCommon, boolean z, String str, int i, View view) {
            if (innerConditionCommon.g()) {
                CreateSceneManager.a().l();
                StartConditionActivityNew.this.a(innerConditionCommon, z, str);
                return;
            }
            CreateSceneManager.a().a((Room) null);
            int unused = StartConditionActivityNew.this.f = innerConditionCommon.a(-1, (Activity) StartConditionActivityNew.this, StartConditionActivityNew.this.mSelectedCondition);
            if (StartConditionActivityNew.this.f != -1) {
                BaseInnerCondition unused2 = StartConditionActivityNew.this.h = innerConditionCommon;
                CreateSceneManager.a().c(StartConditionActivityNew.this.h.a(i, (Intent) null));
                return;
            }
            StartConditionActivityNew.this.mScene.l.add(innerConditionCommon.a(-1, (Intent) null));
            StartConditionActivityNew.this.addCompatibleId(innerConditionCommon.c(0));
            StartConditionActivityNew.this.finish();
        }
    }

    /* access modifiers changed from: private */
    public String b(BaseInnerCondition baseInnerCondition) {
        if (!(baseInnerCondition instanceof InnerConditionCommon)) {
            return "";
        }
        String string = getString(R.string.room_default);
        Room room = null;
        InnerConditionCommon innerConditionCommon = (InnerConditionCommon) baseInnerCondition;
        if (innerConditionCommon.a() != null) {
            room = HomeManager.a().p(innerConditionCommon.a().b);
        }
        return room != null ? room.e() : string;
    }

    public void onResume() {
        super.onResume();
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x000c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String e() {
        /*
            r5 = this;
            java.util.List<com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet> r0 = r5.b
            java.util.Iterator r0 = r0.iterator()
        L_0x0006:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0048
            java.lang.Object r1 = r0.next()
            com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet r1 = (com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity.DefaultSceneItemSet) r1
            java.lang.String[] r2 = r1.b
            r3 = 0
            r2 = r2[r3]
            java.lang.String r4 = "click"
            boolean r2 = r2.equalsIgnoreCase(r4)
            if (r2 != 0) goto L_0x0043
            java.lang.String[] r2 = r1.b
            r2 = r2[r3]
            java.lang.String r4 = "come_home"
            boolean r2 = r2.equalsIgnoreCase(r4)
            if (r2 != 0) goto L_0x0043
            java.lang.String[] r2 = r1.b
            r2 = r2[r3]
            java.lang.String r4 = "leave_home"
            boolean r2 = r2.equalsIgnoreCase(r4)
            if (r2 != 0) goto L_0x0043
            java.lang.String[] r2 = r1.b
            r2 = r2[r3]
            java.lang.String r4 = "timer"
            boolean r2 = r2.equalsIgnoreCase(r4)
            if (r2 == 0) goto L_0x0006
        L_0x0043:
            java.lang.String[] r0 = r1.b
            r0 = r0[r3]
            return r0
        L_0x0048:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.scene.StartConditionActivityNew.e():java.lang.String");
    }

    private void a(int i2) {
        if (this.h != null && this.h.f() != null && this.h.f().length > 0) {
            int a2 = this.h.a(this.h.f()[i2], this, this.mSelectedCondition);
            if (a2 >= 0) {
                this.f = i2;
                this.g = a2;
                CreateSceneManager.a().c(this.h.a(i2, (Intent) null));
            } else if (a2 == -1) {
                finish();
            } else if (a2 != -99) {
                if (this.mSelectedConditionPos == -1) {
                    this.mSelectedConditionPos = this.mScene.l.size();
                }
                this.mScene.l.add(this.mSelectedConditionPos, this.h.a(this.h.f()[i2], (Intent) null));
                addCompatibleId(this.h.c(this.h.f()[i2]));
                finish();
            } else if (this.mSId != -1) {
                if (this.mSelectedCondition != null) {
                    if (this.mSelectedConditionPos == -1) {
                        this.mScene.l.add(0, this.mSelectedCondition);
                    } else {
                        this.mScene.l.add(this.mSelectedConditionPos, this.mSelectedCondition);
                    }
                    addCompatibleId(this.mSelectedCondition.k);
                }
                finish();
            }
        }
    }

    public void addCompatibleId(int i2) {
        if (CreateSceneManager.a().a(Integer.valueOf(i2))) {
            CreateSceneManager.a().a(SceneManager.x().a(i2));
            CreateSceneManager.a().b(SceneManager.x().b(i2));
            return;
        }
        CreateSceneManager.a().c();
    }

    public void onBackPressed() {
        if (SmarthomeCreateAutoSceneActivity.mIsInitStep) {
            CreateSceneManager.a().b = 3;
        }
        super.onBackPressed();
    }

    private class BaseInnerConditionComparator implements Comparator<BaseInnerCondition> {
        private Map<String, Integer> b = new HashMap();
        private Map<String, Integer> c = new HashMap();

        public BaseInnerConditionComparator(Room room, List<BaseInnerCondition> list) {
            if (room != null) {
                this.b.put(room.d(), 0);
            }
            ArrayList arrayList = new ArrayList();
            for (Home next : HomeManager.a().f()) {
                if (next.p()) {
                    List<Room> a2 = HomeManager.a().a(next.j());
                    if (!a2.isEmpty()) {
                        arrayList.addAll(a2);
                    }
                }
            }
            int i = 0;
            while (true) {
                if (i >= (arrayList.isEmpty() ? 0 : arrayList.size())) {
                    break;
                }
                if (room == null || !TextUtils.equals(room.d(), ((Room) arrayList.get(i)).d()) || !this.b.containsKey(room.d())) {
                    this.b.put(((Room) arrayList.get(i)).d(), Integer.valueOf(i));
                }
                i++;
            }
            int i2 = 0;
            while (true) {
                if (i2 < (list.isEmpty() ? 0 : list.size())) {
                    if (list.get(i2) instanceof CommonTextCondition) {
                        this.c.put(CommonTextCondition.class.getName(), Integer.valueOf(i2));
                    } else if (list.get(i2) instanceof ClickInnerCondition) {
                        this.c.put(ClickInnerCondition.class.getName(), Integer.valueOf(i2));
                    } else if (list.get(i2) instanceof TimerInnerCondition) {
                        this.c.put(TimerInnerCondition.class.getName(), Integer.valueOf(i2));
                    } else if (list.get(i2) instanceof WeatherInnerCondition) {
                        this.c.put(WeatherInnerCondition.class.getName(), Integer.valueOf(i2));
                    } else if (list.get(i2) instanceof IntelligentTextCondition) {
                        this.c.put(IntelligentTextCondition.class.getName(), Integer.valueOf(i2));
                    } else if (list.get(i2) instanceof DeviceConditionFilter) {
                        this.c.put(DeviceConditionFilter.class.getName(), Integer.valueOf(i2));
                    } else if ((list.get(i2) instanceof InnerConditionCommon) && list.get(i2).d() != null) {
                        this.c.put(list.get(i2).d().did, Integer.valueOf(i2));
                    }
                    i2++;
                } else {
                    return;
                }
            }
        }

        /* renamed from: a */
        public int compare(BaseInnerCondition baseInnerCondition, BaseInnerCondition baseInnerCondition2) {
            boolean z = baseInnerCondition instanceof InnerConditionCommon;
            if (z && (baseInnerCondition2 instanceof InnerConditionCommon)) {
                Device d = baseInnerCondition.d();
                Device d2 = baseInnerCondition2.d();
                if (d == null && d2 == null) {
                    return 0;
                }
                if (d != null && d2 != null) {
                    int a2 = a(d, d2);
                    if (a2 != 0) {
                        return a2;
                    }
                    Room p = HomeManager.a().p(d.did);
                    Room p2 = HomeManager.a().p(d2.did);
                    if (p == null && p2 == null) {
                        return b(d, d2);
                    }
                    if (p != null && p2 != null) {
                        int a3 = a(p, p2);
                        if (a3 != 0) {
                            return a3;
                        }
                        return b(d, d2);
                    } else if (p == null) {
                        return 1;
                    } else {
                        return -1;
                    }
                } else if (d == null) {
                    return 1;
                } else {
                    return -1;
                }
            } else if (z || (baseInnerCondition2 instanceof InnerConditionCommon)) {
                if (z) {
                    return 1;
                }
                return -1;
            } else if (this.c.get(baseInnerCondition.getClass().getName()) == null || this.c.get(baseInnerCondition2.getClass().getName()) == null) {
                return this.c.get(baseInnerCondition.getClass().getName()) != null ? 1 : 0;
            } else {
                return this.c.get(baseInnerCondition.getClass().getName()).intValue() - this.c.get(baseInnerCondition2.getClass().getName()).intValue();
            }
        }

        public int a(Room room, Room room2) {
            if (!this.b.containsKey(room.d()) && !this.b.containsKey(room2.d())) {
                return 0;
            }
            if (!this.b.containsKey(room.d()) || !this.b.containsKey(room2.d())) {
                return !this.b.containsKey(room.d()) ? 1 : -1;
            }
            return this.b.get(room2.d()).intValue() - this.b.get(room.d()).intValue();
        }

        public int a(Device device, Device device2) {
            boolean z = device.isOnline;
            boolean z2 = device2.isOnline;
            return (z2 ? 1 : 0) - (z ? 1 : 0);
        }

        public int b(Device device, Device device2) {
            if (!this.c.containsKey(device.did) && !this.c.containsKey(device2.did)) {
                return 0;
            }
            if (!this.c.containsKey(device.did) || !this.c.containsKey(device2.did)) {
                return !this.c.containsKey(device.did) ? 1 : -1;
            }
            return this.c.get(device2.did).intValue() - this.c.get(device.did).intValue();
        }
    }
}
