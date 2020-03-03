package com.xiaomi.smarthome.scene;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
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
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.widget.ExpandableItemIndicator;
import com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity;
import com.xiaomi.smarthome.scene.api.SceneApi;
import com.xiaomi.smarthome.scene.condition.BaseInnerCondition;
import com.xiaomi.smarthome.scene.condition.InnerConditionCommon;
import com.xiaomi.smarthome.scene.condition.LocaleGetResourceFixHelper;
import com.xiaomi.smarthome.scene.view.SceneFilterManager;
import com.xiaomi.smarthome.scene.view.SceneFilterView;
import com.xiaomi.youpin.app_sdk.url_dispatch.UrlDispatchManger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class StartConditionActivity extends BaseActivity {
    public static final String EXTRA_DEFAULT_CONDITIONS = "extra_default_conditions";
    public static final String EXTRA_EXCLUDE_CONDITIONS = "extra_exclude_conditions";
    public static final int GET_CONDITION_DETAIL = 100;

    /* renamed from: a  reason: collision with root package name */
    private List<SmartHomeSceneCreateEditActivity.DefaultSceneItemSet> f21466a;
    private List<SmartHomeSceneCreateEditActivity.DefaultSceneItemSet> b;
    /* access modifiers changed from: private */
    public int c = 0;
    private int d = 0;
    /* access modifiers changed from: private */
    public BaseInnerCondition e;
    /* access modifiers changed from: private */
    public SceneFilterView f;
    @BindView(2131428019)
    Button mBuyButton;
    @BindView(2131428133)
    View mBuyView;
    HashMap<BaseInnerCondition, Boolean> mConditionEnableMap = new HashMap<>();
    @BindView(2131428548)
    ListView mContentListView;
    Context mContext;
    List<BaseInnerCondition> mDeviceConditions = new ArrayList();
    @BindView(2131429497)
    View mGrayView;
    boolean mIsFirstIn = false;
    @BindView(2131427981)
    ExpandableItemIndicator mItemIndicator;
    ConditionAdapter mListAdapter;
    @BindView(2131430969)
    ImageView mModuleA3ReturnTransparentBtn;
    @BindView(2131430975)
    TextView mModuleA3ReturnTransparentTitle;
    int mSId = -1;
    SceneApi.SmartHomeScene mScene;
    String mSceneID;
    SceneApi.Condition mSelectedCondition = null;
    int mSelectedConditionPos = -1;
    @BindView(2131432919)
    RelativeLayout mTitleBarFL;
    List<BaseInnerCondition> tempConditions = new ArrayList();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.smarthome_start_condition);
        ButterKnife.bind((Activity) this);
        this.mContext = this;
        Intent intent = getIntent();
        if (intent != null) {
            this.f21466a = intent.getParcelableArrayListExtra("extra_default_conditions");
            this.b = intent.getParcelableArrayListExtra("extra_exclude_conditions");
        }
        this.mScene = CreateSceneManager.a().g();
        if (this.mScene == null) {
            finish();
            return;
        }
        this.mSceneID = this.mScene.f;
        this.mIsFirstIn = SmartHomeSceneCreateEditActivity.isNewScene;
        a();
        SceneFilterManager.c().b(this.mDeviceConditions);
        SceneFilterManager.c().a(this.mModuleA3ReturnTransparentTitle, 1, true);
        this.f = (SceneFilterView) LayoutInflater.from(this.mContext).inflate(R.layout.scene_filter_view, (ViewGroup) null);
        this.f.setmDismissListener(new SceneFilterView.OnWindowDismissListener() {
            public void a() {
                StartConditionActivity.this.mItemIndicator.setExpandedState(false, false);
            }
        });
        AnonymousClass2 r5 = new View.OnClickListener() {
            public void onClick(View view) {
                StartConditionActivity.this.mItemIndicator.setExpandedState(true, false);
                StartConditionActivity.this.f.show(StartConditionActivity.this.mTitleBarFL, StartConditionActivity.this.mGrayView, SceneFilterManager.c().d().a(), SceneFilterManager.c().d().b(), SceneFilterManager.c().d().c(), 1);
                StartConditionActivity.this.f.setOnItemSelectListener(new SceneFilterView.OnItemSelectListener() {
                    public void a(Set<String> set, String str) {
                        StartConditionActivity.this.f.dismiss();
                        SceneFilterManager.c().a(StartConditionActivity.this.mModuleA3ReturnTransparentTitle, 1, true);
                        StartConditionActivity.this.mListAdapter.a(StartConditionActivity.this.a(false));
                    }
                });
            }
        };
        this.mModuleA3ReturnTransparentTitle.setOnClickListener(r5);
        this.mItemIndicator.setOnClickListener(r5);
        if (this.mDeviceConditions.size() != 0 || this.f21466a == null || this.f21466a.size() <= 0) {
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
        this.mListAdapter = new ConditionAdapter();
        this.mListAdapter.a(a(true));
        this.mContentListView.addHeaderView(LayoutInflater.from(this).inflate(R.layout.common_list_space_empty, this.mContentListView, false));
        this.mContentListView.setAdapter(this.mListAdapter);
    }

    /* access modifiers changed from: private */
    public List<BaseInnerCondition> a(boolean z) {
        Set<String> a2 = SceneFilterManager.c().a();
        String str = SceneFilterManager.c().d().c;
        if (SceneFilterManager.c().d().b == 0) {
            this.tempConditions.clear();
            this.tempConditions.addAll(this.mDeviceConditions);
            return this.tempConditions;
        } else if (!z || SceneFilterManager.c().a(SceneFilterManager.c().d().a(), 1, str)) {
            this.tempConditions.clear();
            for (int i = 0; i < this.mDeviceConditions.size(); i++) {
                BaseInnerCondition baseInnerCondition = this.mDeviceConditions.get(i);
                if (baseInnerCondition instanceof InnerConditionCommon) {
                    CommonSceneOnline a3 = ((InnerConditionCommon) baseInnerCondition).a();
                    if (a3 != null) {
                        Iterator<String> it = a2.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            if (a3.b.equalsIgnoreCase(it.next())) {
                                this.tempConditions.add(baseInnerCondition);
                                break;
                            }
                        }
                    }
                } else {
                    this.tempConditions.add(baseInnerCondition);
                }
            }
            Collections.reverse(this.tempConditions);
            return this.tempConditions;
        } else {
            this.tempConditions.clear();
            this.tempConditions.addAll(this.mDeviceConditions);
            SceneFilterManager.c().g();
            return this.tempConditions;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v5, resolved type: com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet} */
    /* JADX WARNING: type inference failed for: r3v0 */
    /* JADX WARNING: type inference failed for: r3v1, types: [com.xiaomi.smarthome.scene.condition.BaseInnerCondition] */
    /* JADX WARNING: type inference failed for: r3v7 */
    /* JADX WARNING: type inference failed for: r3v8 */
    /* JADX WARNING: type inference failed for: r3v16 */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a() {
        /*
            r12 = this;
            com.xiaomi.smarthome.device.SmartHomeDeviceManager r0 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.a()
            java.util.List r0 = r0.d()
            int r1 = r0.size()
            r2 = 1
            int r1 = r1 - r2
        L_0x000e:
            if (r1 < 0) goto L_0x0022
            java.lang.Object r3 = r0.get(r1)
            com.xiaomi.smarthome.device.Device r3 = (com.xiaomi.smarthome.device.Device) r3
            boolean r3 = r3.isSubDevice()
            if (r3 == 0) goto L_0x001f
            r0.remove(r1)
        L_0x001f:
            int r1 = r1 + -1
            goto L_0x000e
        L_0x0022:
            java.util.Iterator r0 = r0.iterator()
        L_0x0026:
            boolean r1 = r0.hasNext()
            r3 = 0
            r4 = 0
            if (r1 == 0) goto L_0x0082
            java.lang.Object r1 = r0.next()
            com.xiaomi.smarthome.device.Device r1 = (com.xiaomi.smarthome.device.Device) r1
            boolean r5 = r1.isOwner()
            if (r5 != 0) goto L_0x003b
            goto L_0x0026
        L_0x003b:
            java.util.List<com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet> r5 = r12.b
            if (r5 == 0) goto L_0x0076
            java.util.List<com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet> r5 = r12.b
            int r5 = r5.size()
            if (r5 <= 0) goto L_0x0076
            java.util.List<com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet> r5 = r12.b
            java.util.Iterator r5 = r5.iterator()
        L_0x004d:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L_0x0073
            java.lang.Object r6 = r5.next()
            com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet r6 = (com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity.DefaultSceneItemSet) r6
            java.lang.String r7 = r1.model
            java.lang.String[] r8 = r6.b
            boolean r7 = com.xiaomi.smarthome.device.DeviceFactory.a((java.lang.String) r7, (java.lang.String[]) r8)
            if (r7 != 0) goto L_0x0071
            java.lang.String r7 = r6.e
            if (r7 == 0) goto L_0x004d
            java.lang.String r7 = r6.e
            java.lang.String r8 = r1.did
            boolean r7 = r7.equalsIgnoreCase(r8)
            if (r7 == 0) goto L_0x004d
        L_0x0071:
            r3 = r6
            r4 = 1
        L_0x0073:
            if (r4 == 0) goto L_0x0076
            goto L_0x0026
        L_0x0076:
            com.xiaomi.smarthome.scene.condition.BaseInnerCondition r1 = com.xiaomi.smarthome.scene.condition.BaseInnerCondition.a((com.xiaomi.smarthome.device.Device) r1, (com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity.DefaultSceneItemSet) r3)
            if (r1 == 0) goto L_0x0026
            java.util.List<com.xiaomi.smarthome.scene.condition.BaseInnerCondition> r3 = r12.mDeviceConditions
            r3.add(r1)
            goto L_0x0026
        L_0x0082:
            com.xiaomi.smarthome.device.SmartHomeDeviceManager r0 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.a()
            java.util.List r0 = r0.k()
            java.util.Iterator r0 = r0.iterator()
        L_0x008e:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x011f
            java.lang.Object r1 = r0.next()
            com.xiaomi.smarthome.device.Device r1 = (com.xiaomi.smarthome.device.Device) r1
            boolean r5 = r1.isOwner()
            if (r5 != 0) goto L_0x00a1
            goto L_0x008e
        L_0x00a1:
            com.xiaomi.smarthome.device.SmartHomeDeviceManager r5 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.a()
            java.lang.String r6 = r1.parentId
            com.xiaomi.smarthome.device.Device r5 = r5.b((java.lang.String) r6)
            if (r5 == 0) goto L_0x008e
            boolean r5 = r5.isOwner()
            if (r5 != 0) goto L_0x00b4
            goto L_0x008e
        L_0x00b4:
            java.util.List<com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet> r5 = r12.b
            if (r5 == 0) goto L_0x00db
            java.util.List<com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet> r5 = r12.b
            java.util.Iterator r5 = r5.iterator()
        L_0x00be:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L_0x00d6
            java.lang.Object r6 = r5.next()
            com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet r6 = (com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity.DefaultSceneItemSet) r6
            java.lang.String r7 = r1.model
            java.lang.String[] r8 = r6.b
            boolean r7 = com.xiaomi.smarthome.device.DeviceFactory.a((java.lang.String) r7, (java.lang.String[]) r8)
            if (r7 == 0) goto L_0x00be
            r5 = 1
            goto L_0x00d8
        L_0x00d6:
            r6 = r3
            r5 = 0
        L_0x00d8:
            if (r5 == 0) goto L_0x00dc
            goto L_0x008e
        L_0x00db:
            r6 = r3
        L_0x00dc:
            com.xiaomi.smarthome.scene.condition.BaseInnerCondition r5 = com.xiaomi.smarthome.scene.condition.BaseInnerCondition.a((com.xiaomi.smarthome.device.Device) r1, (com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity.DefaultSceneItemSet) r6)
            if (r5 == 0) goto L_0x008e
            r6 = 0
        L_0x00e3:
            java.util.List<com.xiaomi.smarthome.scene.condition.BaseInnerCondition> r7 = r12.mDeviceConditions
            int r7 = r7.size()
            if (r6 >= r7) goto L_0x0115
            java.util.List<com.xiaomi.smarthome.scene.condition.BaseInnerCondition> r7 = r12.mDeviceConditions
            java.lang.Object r7 = r7.get(r6)
            com.xiaomi.smarthome.scene.condition.BaseInnerCondition r7 = (com.xiaomi.smarthome.scene.condition.BaseInnerCondition) r7
            com.xiaomi.smarthome.device.Device r7 = r7.d()
            if (r7 == 0) goto L_0x0112
            boolean r8 = r7.isSubDevice()
            if (r8 != 0) goto L_0x0112
            java.lang.String r8 = r1.parentId
            java.lang.String r7 = r7.did
            boolean r7 = r8.equalsIgnoreCase(r7)
            if (r7 == 0) goto L_0x0112
            java.util.List<com.xiaomi.smarthome.scene.condition.BaseInnerCondition> r1 = r12.mDeviceConditions
            int r6 = r6 + 1
            r1.add(r6, r5)
            r1 = 1
            goto L_0x0116
        L_0x0112:
            int r6 = r6 + 1
            goto L_0x00e3
        L_0x0115:
            r1 = 0
        L_0x0116:
            if (r1 != 0) goto L_0x008e
            java.util.List<com.xiaomi.smarthome.scene.condition.BaseInnerCondition> r1 = r12.mDeviceConditions
            r1.add(r5)
            goto L_0x008e
        L_0x011f:
            java.util.List<com.xiaomi.smarthome.scene.condition.BaseInnerCondition> r0 = r12.mDeviceConditions
            com.xiaomi.smarthome.scene.condition.TimerInnerCondition r1 = new com.xiaomi.smarthome.scene.condition.TimerInnerCondition
            r1.<init>(r3)
            r0.add(r4, r1)
            com.xiaomi.smarthome.scene.CreateSceneManager r0 = com.xiaomi.smarthome.scene.CreateSceneManager.a()
            com.xiaomi.smarthome.scene.api.SceneApi$Condition r0 = r0.i()
            r12.mSelectedCondition = r0
            r0 = 0
        L_0x0134:
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r1 = r12.mScene
            java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Condition> r1 = r1.l
            int r1 = r1.size()
            r5 = -1
            if (r0 >= r1) goto L_0x01cf
            java.util.List<com.xiaomi.smarthome.scene.condition.BaseInnerCondition> r1 = r12.mDeviceConditions
            int r1 = r1.size()
            int r1 = r1 - r2
        L_0x0146:
            if (r1 < 0) goto L_0x01cb
            java.util.List<com.xiaomi.smarthome.scene.condition.BaseInnerCondition> r6 = r12.mDeviceConditions
            java.lang.Object r6 = r6.get(r1)
            com.xiaomi.smarthome.scene.condition.BaseInnerCondition r6 = (com.xiaomi.smarthome.scene.condition.BaseInnerCondition) r6
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r7 = r12.mScene
            java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Condition> r7 = r7.l
            java.lang.Object r7 = r7.get(r0)
            com.xiaomi.smarthome.scene.api.SceneApi$Condition r7 = (com.xiaomi.smarthome.scene.api.SceneApi.Condition) r7
            int r6 = r6.a((com.xiaomi.smarthome.scene.api.SceneApi.Condition) r7)
            if (r6 == r5) goto L_0x01c7
            com.xiaomi.smarthome.scene.api.SceneApi$Condition r5 = r12.mSelectedCondition
            if (r5 == 0) goto L_0x0180
            com.xiaomi.smarthome.scene.api.SceneApi$Condition r5 = r12.mSelectedCondition
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r7 = r12.mScene
            java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Condition> r7 = r7.l
            java.lang.Object r7 = r7.get(r0)
            boolean r5 = r5.equals(r7)
            if (r5 == 0) goto L_0x0180
            java.util.List<com.xiaomi.smarthome.scene.condition.BaseInnerCondition> r3 = r12.mDeviceConditions
            java.lang.Object r1 = r3.get(r1)
            com.xiaomi.smarthome.scene.condition.BaseInnerCondition r1 = (com.xiaomi.smarthome.scene.condition.BaseInnerCondition) r1
            r12.mSId = r6
            r3 = r1
            goto L_0x01cb
        L_0x0180:
            java.util.List<com.xiaomi.smarthome.scene.condition.BaseInnerCondition> r5 = r12.mDeviceConditions
            java.lang.Object r5 = r5.get(r1)
            com.xiaomi.smarthome.scene.condition.BaseInnerCondition r5 = (com.xiaomi.smarthome.scene.condition.BaseInnerCondition) r5
            boolean r5 = r5.g()
            if (r5 != 0) goto L_0x019e
            java.util.HashMap<com.xiaomi.smarthome.scene.condition.BaseInnerCondition, java.lang.Boolean> r5 = r12.mConditionEnableMap
            java.util.List<com.xiaomi.smarthome.scene.condition.BaseInnerCondition> r6 = r12.mDeviceConditions
            java.lang.Object r1 = r6.get(r1)
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r4)
            r5.put(r1, r6)
            goto L_0x01cb
        L_0x019e:
            java.util.List<com.xiaomi.smarthome.scene.condition.BaseInnerCondition> r5 = r12.mDeviceConditions
            java.lang.Object r5 = r5.get(r1)
            com.xiaomi.smarthome.scene.condition.BaseInnerCondition r5 = (com.xiaomi.smarthome.scene.condition.BaseInnerCondition) r5
            r5.a((int) r6)
            java.util.List<com.xiaomi.smarthome.scene.condition.BaseInnerCondition> r5 = r12.mDeviceConditions
            java.lang.Object r5 = r5.get(r1)
            com.xiaomi.smarthome.scene.condition.BaseInnerCondition r5 = (com.xiaomi.smarthome.scene.condition.BaseInnerCondition) r5
            boolean r5 = r5.c()
            if (r5 == 0) goto L_0x01cb
            java.util.HashMap<com.xiaomi.smarthome.scene.condition.BaseInnerCondition, java.lang.Boolean> r5 = r12.mConditionEnableMap
            java.util.List<com.xiaomi.smarthome.scene.condition.BaseInnerCondition> r6 = r12.mDeviceConditions
            java.lang.Object r1 = r6.get(r1)
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r4)
            r5.put(r1, r6)
            goto L_0x01cb
        L_0x01c7:
            int r1 = r1 + -1
            goto L_0x0146
        L_0x01cb:
            int r0 = r0 + 1
            goto L_0x0134
        L_0x01cf:
            int r0 = r12.mSId
            if (r0 == r5) goto L_0x01f1
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r0 = r12.mScene
            java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Condition> r0 = r0.l
            com.xiaomi.smarthome.scene.api.SceneApi$Condition r1 = r12.mSelectedCondition
            int r0 = r0.indexOf(r1)
            r12.mSelectedConditionPos = r0
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r0 = r12.mScene
            java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Condition> r0 = r0.l
            com.xiaomi.smarthome.scene.api.SceneApi$Condition r1 = r12.mSelectedCondition
            r0.remove(r1)
            com.xiaomi.smarthome.scene.CreateSceneManager r0 = com.xiaomi.smarthome.scene.CreateSceneManager.a()
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r1 = r12.mScene
            r0.d(r1)
        L_0x01f1:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.List<com.xiaomi.smarthome.scene.condition.BaseInnerCondition> r1 = r12.mDeviceConditions
            int r1 = r1.size()
            int r1 = r1 - r2
        L_0x01fd:
            if (r1 < 0) goto L_0x02d3
            java.util.List<com.xiaomi.smarthome.scene.condition.BaseInnerCondition> r6 = r12.mDeviceConditions
            java.lang.Object r6 = r6.get(r1)
            com.xiaomi.smarthome.scene.condition.BaseInnerCondition r6 = (com.xiaomi.smarthome.scene.condition.BaseInnerCondition) r6
            boolean r6 = r6.g()
            if (r6 == 0) goto L_0x0280
            java.util.List<com.xiaomi.smarthome.scene.condition.BaseInnerCondition> r6 = r12.mDeviceConditions
            java.lang.Object r6 = r6.get(r1)
            com.xiaomi.smarthome.scene.condition.BaseInnerCondition r6 = (com.xiaomi.smarthome.scene.condition.BaseInnerCondition) r6
            int[] r6 = r6.f()
            int r7 = r6.length
            r8 = 0
        L_0x021b:
            if (r8 >= r7) goto L_0x0253
            r9 = r6[r8]
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            com.xiaomi.smarthome.scene.CreateSceneManager r10 = com.xiaomi.smarthome.scene.CreateSceneManager.a()
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r10 = r10.g()
            int r10 = r10.q
            if (r10 == r2) goto L_0x0251
            com.xiaomi.smarthome.scene.CreateSceneManager r10 = com.xiaomi.smarthome.scene.CreateSceneManager.a()
            java.util.List<com.xiaomi.smarthome.scene.condition.BaseInnerCondition> r11 = r12.mDeviceConditions
            java.lang.Object r11 = r11.get(r1)
            com.xiaomi.smarthome.scene.condition.BaseInnerCondition r11 = (com.xiaomi.smarthome.scene.condition.BaseInnerCondition) r11
            int r9 = r9.intValue()
            int r9 = r11.c(r9)
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            boolean r9 = r10.a((java.lang.Integer) r9)
            if (r9 == 0) goto L_0x024e
            goto L_0x0251
        L_0x024e:
            int r8 = r8 + 1
            goto L_0x021b
        L_0x0251:
            r6 = 0
            goto L_0x0254
        L_0x0253:
            r6 = 1
        L_0x0254:
            if (r6 != 0) goto L_0x0265
            java.util.List<com.xiaomi.smarthome.scene.condition.BaseInnerCondition> r7 = r12.mDeviceConditions
            java.lang.Object r7 = r7.get(r1)
            com.xiaomi.smarthome.scene.condition.BaseInnerCondition r7 = (com.xiaomi.smarthome.scene.condition.BaseInnerCondition) r7
            boolean r7 = r7.c()
            if (r7 == 0) goto L_0x0265
            r6 = 1
        L_0x0265:
            if (r6 == 0) goto L_0x02cf
            java.util.HashMap<com.xiaomi.smarthome.scene.condition.BaseInnerCondition, java.lang.Boolean> r6 = r12.mConditionEnableMap
            java.util.List<com.xiaomi.smarthome.scene.condition.BaseInnerCondition> r7 = r12.mDeviceConditions
            java.lang.Object r7 = r7.get(r1)
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r4)
            r6.put(r7, r8)
            java.util.List<com.xiaomi.smarthome.scene.condition.BaseInnerCondition> r6 = r12.mDeviceConditions
            java.lang.Object r6 = r6.remove(r1)
            r0.add(r6)
            goto L_0x02cf
        L_0x0280:
            com.xiaomi.smarthome.scene.CreateSceneManager r6 = com.xiaomi.smarthome.scene.CreateSceneManager.a()
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r6 = r6.g()
            int r6 = r6.q
            if (r6 == r2) goto L_0x02c0
            com.xiaomi.smarthome.scene.CreateSceneManager r6 = com.xiaomi.smarthome.scene.CreateSceneManager.a()
            java.util.List<com.xiaomi.smarthome.scene.condition.BaseInnerCondition> r7 = r12.mDeviceConditions
            java.lang.Object r7 = r7.get(r1)
            com.xiaomi.smarthome.scene.condition.BaseInnerCondition r7 = (com.xiaomi.smarthome.scene.condition.BaseInnerCondition) r7
            int r7 = r7.c(r4)
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            boolean r6 = r6.a((java.lang.Integer) r7)
            if (r6 == 0) goto L_0x02a7
            goto L_0x02c0
        L_0x02a7:
            java.util.HashMap<com.xiaomi.smarthome.scene.condition.BaseInnerCondition, java.lang.Boolean> r6 = r12.mConditionEnableMap
            java.util.List<com.xiaomi.smarthome.scene.condition.BaseInnerCondition> r7 = r12.mDeviceConditions
            java.lang.Object r7 = r7.get(r1)
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r4)
            r6.put(r7, r8)
            java.util.List<com.xiaomi.smarthome.scene.condition.BaseInnerCondition> r6 = r12.mDeviceConditions
            java.lang.Object r6 = r6.remove(r1)
            r0.add(r6)
            goto L_0x02cf
        L_0x02c0:
            java.util.HashMap<com.xiaomi.smarthome.scene.condition.BaseInnerCondition, java.lang.Boolean> r6 = r12.mConditionEnableMap
            java.util.List<com.xiaomi.smarthome.scene.condition.BaseInnerCondition> r7 = r12.mDeviceConditions
            java.lang.Object r7 = r7.get(r1)
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r2)
            r6.put(r7, r8)
        L_0x02cf:
            int r1 = r1 + -1
            goto L_0x01fd
        L_0x02d3:
            java.util.List<com.xiaomi.smarthome.scene.condition.BaseInnerCondition> r1 = r12.mDeviceConditions
            r1.addAll(r0)
            int r0 = r12.mSId
            if (r0 == r5) goto L_0x02df
            r12.a((com.xiaomi.smarthome.scene.condition.BaseInnerCondition) r3)
        L_0x02df:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.scene.StartConditionActivity.a():void");
    }

    /* access modifiers changed from: private */
    public void a(BaseInnerCondition baseInnerCondition) {
        Intent intent = new Intent();
        intent.setClass(this, SmartHomeSceneDetailActivity.class);
        if (this.mSId != -1) {
            intent.putExtra("extra_selected_title", this.mSId);
        }
        if (baseInnerCondition instanceof LocaleGetResourceFixHelper) {
            try {
                intent.putExtra("extra_title", getString(((LocaleGetResourceFixHelper) baseInnerCondition).a()));
            } catch (Exception unused) {
                intent.putExtra("extra_title", baseInnerCondition.e());
            }
        } else {
            intent.putExtra("extra_title", baseInnerCondition.e());
        }
        startActivityForResult(intent, 100);
        CreateSceneManager.a().a(baseInnerCondition);
        this.e = baseInnerCondition;
    }

    public void onActivityResult(final int i, final Intent intent) {
        this.mHandler.post(new Runnable() {
            public void run() {
                if (i == -1) {
                    if (StartConditionActivity.this.mSId != -1) {
                        StartConditionActivity.this.mScene.l.remove(StartConditionActivity.this.mSelectedCondition);
                    }
                    StartConditionActivity.this.mScene.l.add(StartConditionActivity.this.e.a(StartConditionActivity.this.e.f()[StartConditionActivity.this.c], intent));
                    StartConditionActivity.this.addCompatibleId(StartConditionActivity.this.e.c(StartConditionActivity.this.e.f()[StartConditionActivity.this.c]));
                    StartConditionActivity.this.finish();
                } else if (StartConditionActivity.this.mSId != -1) {
                    StartConditionActivity.this.mScene.l.add(StartConditionActivity.this.mSelectedConditionPos, StartConditionActivity.this.mSelectedCondition);
                    if (StartConditionActivity.this.mSelectedCondition.k != 0) {
                        StartConditionActivity.this.addCompatibleId(StartConditionActivity.this.mSelectedCondition.k);
                    }
                    StartConditionActivity.this.finish();
                } else {
                    int unused = StartConditionActivity.this.c = 0;
                    BaseInnerCondition unused2 = StartConditionActivity.this.e = null;
                    StartConditionActivity.this.mListAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 100) {
            if (i2 == -1) {
                int intExtra = intent.getIntExtra("extra_index", -1);
                if (intExtra != -1) {
                    getConditionItem(intExtra);
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
        } else if (i == 102 && i2 == -1) {
            SceneApi.Condition i3 = CreateSceneManager.a().i();
            this.mScene.l.add(i3);
            addCompatibleId(i3.k);
            finish();
        } else if (i == this.d) {
            onActivityResult(i2, intent);
        }
    }

    @OnClick({2131430969})
    public void close() {
        finish();
    }

    private class ConditionAdapter extends BaseAdapter {
        private List<BaseInnerCondition> b;

        public long getItemId(int i) {
            return (long) i;
        }

        private ConditionAdapter() {
            this.b = new ArrayList();
        }

        public void a(List<BaseInnerCondition> list) {
            this.b.clear();
            this.b.addAll(list);
            notifyDataSetChanged();
        }

        public int getCount() {
            return this.b.size();
        }

        public Object getItem(int i) {
            return this.b.get(i);
        }

        public View getView(final int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = StartConditionActivity.this.getLayoutInflater().inflate(R.layout.smarthome_action_group_layout, (ViewGroup) null);
            }
            SimpleDraweeView simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.content_icon);
            TextView textView = (TextView) view.findViewById(R.id.content);
            ImageView imageView = (ImageView) view.findViewById(R.id.expand_hint);
            ((TextView) view.findViewById(R.id.content_description)).setVisibility(8);
            final BaseInnerCondition baseInnerCondition = this.b.get(i);
            if (baseInnerCondition instanceof LocaleGetResourceFixHelper) {
                try {
                    textView.setText(((LocaleGetResourceFixHelper) baseInnerCondition).a());
                } catch (Exception unused) {
                    textView.setText(baseInnerCondition.e());
                }
            } else {
                textView.setText(baseInnerCondition.e());
            }
            imageView.setVisibility(0);
            simpleDraweeView.setHierarchy(new GenericDraweeHierarchyBuilder(simpleDraweeView.getResources()).setFadeDuration(200).setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER).setPlaceholderImageScaleType(ScalingUtils.ScaleType.FIT_CENTER).build());
            baseInnerCondition.a(simpleDraweeView);
            if (!StartConditionActivity.this.mConditionEnableMap.containsKey(baseInnerCondition) || StartConditionActivity.this.mConditionEnableMap.get(baseInnerCondition).booleanValue()) {
                imageView.setImageResource(R.drawable.std_list_main_next);
                view.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (baseInnerCondition.g()) {
                            CreateSceneManager.a().l();
                            StartConditionActivity.this.a(baseInnerCondition);
                            return;
                        }
                        int unused = StartConditionActivity.this.c = baseInnerCondition.a(-1, StartConditionActivity.this, StartConditionActivity.this.mSelectedCondition);
                        if (StartConditionActivity.this.c != -1) {
                            BaseInnerCondition unused2 = StartConditionActivity.this.e = baseInnerCondition;
                            CreateSceneManager.a().c(StartConditionActivity.this.e.a(i, (Intent) null));
                            return;
                        }
                        StartConditionActivity.this.mScene.l.add(baseInnerCondition.a(-1, (Intent) null));
                        StartConditionActivity.this.addCompatibleId(baseInnerCondition.c(0));
                        StartConditionActivity.this.finish();
                    }
                });
            } else {
                imageView.setImageResource(R.drawable.std_scene_icon_lock);
                view.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Toast.makeText(StartConditionActivity.this, R.string.scene_unclickable_toast_2, 0).show();
                    }
                });
            }
            return view;
        }
    }

    public void onResume() {
        super.onResume();
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x000c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String b() {
        /*
            r5 = this;
            java.util.List<com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet> r0 = r5.f21466a
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
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.scene.StartConditionActivity.b():java.lang.String");
    }

    public void getConditionItem(int i) {
        if (this.e != null && this.e.f() != null && this.e.f().length > 0) {
            int a2 = this.e.a(this.e.f()[i], this, this.mSelectedCondition);
            if (a2 >= 0) {
                this.c = i;
                this.d = a2;
                CreateSceneManager.a().c(this.e.a(i, (Intent) null));
            } else if (a2 == -1) {
                finish();
            } else {
                if (this.mSelectedConditionPos == -1) {
                    this.mSelectedConditionPos = this.mScene.l.size();
                }
                this.mScene.l.add(this.mSelectedConditionPos, this.e.a(this.e.f()[i], (Intent) null));
                addCompatibleId(this.e.c(this.e.f()[i]));
                finish();
            }
        }
    }

    public void addCompatibleId(int i) {
        if (CreateSceneManager.a().a(Integer.valueOf(i))) {
            CreateSceneManager.a().a(SceneManager.x().a(i));
            CreateSceneManager.a().b(SceneManager.x().b(i));
            return;
        }
        CreateSceneManager.a().c();
    }
}
