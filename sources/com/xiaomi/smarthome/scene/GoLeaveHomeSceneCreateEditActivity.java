package com.xiaomi.smarthome.scene;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.router.api.SceneManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.SmartHomeConfig;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.config.SHConfig;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.log.MyLog;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.CorntabUtils;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.util.StringUtil;
import com.xiaomi.smarthome.library.common.util.ViewUtils;
import com.xiaomi.smarthome.library.common.widget.SwitchButton;
import com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity;
import com.xiaomi.smarthome.scene.SmartHomeSceneUtility;
import com.xiaomi.smarthome.scene.api.RemoteSceneApi;
import com.xiaomi.smarthome.scene.api.SceneApi;
import com.xiaomi.youpin.app_sdk.url_dispatch.UrlDispatchManger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class GoLeaveHomeSceneCreateEditActivity extends BaseActivity {
    public static final String EXTRA_SCENE_ID = "extra_scene_id";
    public static final String GO_HOME_RECOMMEND_FLAG = "go_home_recommend_flag";
    public static final int GROUP_CONDITION_REQUEST = 8192;
    public static final int REQUEST_CODE_CONDITION = 1;
    public static final int REQUEST_CODE_TASK = 8193;
    public static final String SCENE_ID = "scene_id";
    public static final int SCENE_MORE_PAGE = 1000;
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public SceneApi.SmartHomeScene f21234a;
    /* access modifiers changed from: private */
    public SceneApi.SmartHomeScene b;
    private ArrayList<SmartHomeSceneCreateEditActivity.DefaultSceneItemSet> c;
    private ArrayList<SmartHomeSceneCreateEditActivity.DefaultSceneItemSet> d;
    MLAlertDialog dialog = null;
    /* access modifiers changed from: private */
    public ArrayList<SmartHomeSceneCreateEditActivity.DefaultSceneItemSet> e = new ArrayList<>();
    private ArrayList<SmartHomeSceneCreateEditActivity.DefaultSceneItemSet> f;
    /* access modifiers changed from: private */
    public XQProgressDialog g;
    View groupView;
    private MLAlertDialog h;
    /* access modifiers changed from: private */
    public boolean[] i;
    private int[] j;
    /* access modifiers changed from: private */
    public Context k;
    /* access modifiers changed from: private */
    public ConditionsAdapter l;
    private ActionsAdapter m;
    @BindView(2131432753)
    ListView mActionListView;
    @BindView(2131427579)
    View mAddAction;
    @BindView(2131427585)
    View mAddCondition;
    @BindView(2131431289)
    SwitchButton mCheckBox;
    @BindView(2131432528)
    LinearLayout mConditionExpress;
    @BindView(2131428507)
    View mConditionLayout;
    @BindView(2131428508)
    ListView mConditionListView;
    boolean[] mConditionOnlineItems;
    @BindView(2131428510)
    TextView mConditionTitle;
    @BindView(2131430989)
    Button mConfirmBtn;
    @BindView(2131432527)
    LinearLayout mContainer;
    @BindView(2131428902)
    View mDivider2;
    @BindView(2131428904)
    View mDivider4;
    @BindView(2131430988)
    TextView mModuleA4Commit;
    @BindView(2131430997)
    TextView mModuleA4ReturnTitle;
    @BindView(2131431194)
    TextView mNoActionTitle;
    @BindView(2131431196)
    TextView mNoConditionTitle;
    @BindView(2131431295)
    View mSwitchContainer;
    @BindView(2131432752)
    View mTaskLayout;
    @BindView(2131432919)
    View mTitleBar;
    @BindView(2131433850)
    View mViewTag;
    /* access modifiers changed from: private */
    public String n;
    /* access modifiers changed from: private */
    public boolean o;
    /* access modifiers changed from: private */
    public boolean p;
    View timerView;

    public class ActionsAdapter_ViewBinding implements Unbinder {

        /* renamed from: a  reason: collision with root package name */
        private ActionsAdapter f21266a;

        @UiThread
        public ActionsAdapter_ViewBinding(ActionsAdapter actionsAdapter, View view) {
            this.f21266a = actionsAdapter;
            actionsAdapter.mImage = (SimpleDraweeView) Utils.findRequiredViewAsType(view, R.id.image, "field 'mImage'", SimpleDraweeView.class);
            actionsAdapter.mTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.title, "field 'mTitle'", TextView.class);
            actionsAdapter.mSubTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.sub_title, "field 'mSubTitle'", TextView.class);
            actionsAdapter.mOffline = (TextView) Utils.findRequiredViewAsType(view, R.id.offline, "field 'mOffline'", TextView.class);
            actionsAdapter.mAnchor = (ImageView) Utils.findRequiredViewAsType(view, R.id.anchor, "field 'mAnchor'", ImageView.class);
            actionsAdapter.mBuyButton = (TextView) Utils.findRequiredViewAsType(view, R.id.buy_button, "field 'mBuyButton'", TextView.class);
            actionsAdapter.mBottomline = Utils.findRequiredView(view, R.id.bottom_line, "field 'mBottomline'");
            actionsAdapter.mTaskLayout = Utils.findRequiredView(view, R.id.task_layout, "field 'mTaskLayout'");
            actionsAdapter.mDelayLayout = Utils.findRequiredView(view, R.id.delay_timer_layout, "field 'mDelayLayout'");
            actionsAdapter.mDelayTimeText = (TextView) Utils.findRequiredViewAsType(view, R.id.delay_time_text, "field 'mDelayTimeText'", TextView.class);
            actionsAdapter.mDelayTimeLine = Utils.findRequiredView(view, R.id.delay_time_line, "field 'mDelayTimeLine'");
            actionsAdapter.mDelayTopLine = Utils.findRequiredView(view, R.id.delay_top_line, "field 'mDelayTopLine'");
            actionsAdapter.mDelayBottomLine = Utils.findRequiredView(view, R.id.delay_bottom_line, "field 'mDelayBottomLine'");
            actionsAdapter.mIconBottomLine = Utils.findRequiredView(view, R.id.icon_bottom_line, "field 'mIconBottomLine'");
            actionsAdapter.mIconTopLine = Utils.findRequiredView(view, R.id.icon_top_line, "field 'mIconTopLine'");
        }

        @CallSuper
        public void unbind() {
            ActionsAdapter actionsAdapter = this.f21266a;
            if (actionsAdapter != null) {
                this.f21266a = null;
                actionsAdapter.mImage = null;
                actionsAdapter.mTitle = null;
                actionsAdapter.mSubTitle = null;
                actionsAdapter.mOffline = null;
                actionsAdapter.mAnchor = null;
                actionsAdapter.mBuyButton = null;
                actionsAdapter.mBottomline = null;
                actionsAdapter.mTaskLayout = null;
                actionsAdapter.mDelayLayout = null;
                actionsAdapter.mDelayTimeText = null;
                actionsAdapter.mDelayTimeLine = null;
                actionsAdapter.mDelayTopLine = null;
                actionsAdapter.mDelayBottomLine = null;
                actionsAdapter.mIconBottomLine = null;
                actionsAdapter.mIconTopLine = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class ConditionsAdapter_ViewBinding implements Unbinder {

        /* renamed from: a  reason: collision with root package name */
        private ConditionsAdapter f21277a;

        @UiThread
        public ConditionsAdapter_ViewBinding(ConditionsAdapter conditionsAdapter, View view) {
            this.f21277a = conditionsAdapter;
            conditionsAdapter.mIcon = (SimpleDraweeView) Utils.findRequiredViewAsType(view, R.id.icon, "field 'mIcon'", SimpleDraweeView.class);
            conditionsAdapter.mName = (TextView) Utils.findRequiredViewAsType(view, R.id.name, "field 'mName'", TextView.class);
            conditionsAdapter.mKeyName = (TextView) Utils.findRequiredViewAsType(view, R.id.key_name, "field 'mKeyName'", TextView.class);
            conditionsAdapter.mTimeSpan = (TextView) Utils.findRequiredViewAsType(view, R.id.add_timesp, "field 'mTimeSpan'", TextView.class);
            conditionsAdapter.mTimeSetButton = (ImageView) Utils.findRequiredViewAsType(view, R.id.set_time_btn, "field 'mTimeSetButton'", ImageView.class);
            conditionsAdapter.mOffline = (TextView) Utils.findRequiredViewAsType(view, R.id.offline, "field 'mOffline'", TextView.class);
            conditionsAdapter.mBuyButton = (TextView) Utils.findRequiredViewAsType(view, R.id.buy_button, "field 'mBuyButton'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            ConditionsAdapter conditionsAdapter = this.f21277a;
            if (conditionsAdapter != null) {
                this.f21277a = null;
                conditionsAdapter.mIcon = null;
                conditionsAdapter.mName = null;
                conditionsAdapter.mKeyName = null;
                conditionsAdapter.mTimeSpan = null;
                conditionsAdapter.mTimeSetButton = null;
                conditionsAdapter.mOffline = null;
                conditionsAdapter.mBuyButton = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.smarthome_scene_create_edit);
        ButterKnife.bind((Activity) this);
        if (!SceneManager.x().q()) {
            finish();
            return;
        }
        this.k = this;
        Intent intent = getIntent();
        this.n = intent.getStringExtra(SCENE_ID);
        this.c = intent.getParcelableArrayListExtra(SmartHomeSceneCreateEditActivity.EXTRA_DEFAULT_ACTION_ITMES);
        this.d = intent.getParcelableArrayListExtra(SmartHomeSceneCreateEditActivity.EXTRA_DEFAULT_CONDITION_ITMES);
        this.p = intent.getBooleanExtra(GO_HOME_RECOMMEND_FLAG, true);
        if (!TextUtils.isEmpty(this.n)) {
            this.b = SceneManager.x().e(this.n);
            if (this.b != null) {
                try {
                    this.f21234a = SceneApi.SmartHomeScene.a(this.b.a(), false);
                    this.p = SceneManager.x().b(this.b);
                    this.o = false;
                } catch (JSONException e2) {
                    MyLog.a((Throwable) e2);
                }
            }
        } else {
            if (this.p) {
                this.f21234a = HomeSceneFactory.INSTANCE.createDefaultGoHomeScene(this, SceneManager.x().u());
                this.b = HomeSceneFactory.INSTANCE.createDefaultGoHomeScene(this, SceneManager.x().u());
            } else {
                this.f21234a = HomeSceneFactory.INSTANCE.createDefaultLeaveHomeScene(this, SceneManager.x().t());
                this.b = HomeSceneFactory.INSTANCE.createDefaultLeaveHomeScene(this, SceneManager.x().t());
            }
            this.o = true;
            a();
        }
        h();
        c();
        if (TextUtils.isEmpty(this.n) || SmartHomeSceneUtility.b(this.f21234a)) {
            this.mSwitchContainer.setVisibility(8);
        } else {
            this.mSwitchContainer.setVisibility(0);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:63:0x0152  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x016e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a() {
        /*
            r12 = this;
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r0 = r12.f21234a
            r1 = 0
            r0.m = r1
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            com.xiaomi.smarthome.device.SmartHomeDeviceManager r2 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.a()
            java.util.List r2 = r2.d()
            r0.addAll(r2)
            int r2 = r0.size()
            r3 = 1
            int r2 = r2 - r3
        L_0x001b:
            if (r2 < 0) goto L_0x002f
            java.lang.Object r4 = r0.get(r2)
            com.xiaomi.smarthome.device.Device r4 = (com.xiaomi.smarthome.device.Device) r4
            boolean r4 = r4.isSubDevice()
            if (r4 == 0) goto L_0x002c
            r0.remove(r2)
        L_0x002c:
            int r2 = r2 + -1
            goto L_0x001b
        L_0x002f:
            com.xiaomi.smarthome.device.SmartHomeDeviceManager r2 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.a()
            java.util.List r2 = r2.k()
            r0.addAll(r2)
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            java.util.ArrayList<com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet> r5 = r12.c
            r6 = 0
            if (r5 == 0) goto L_0x0069
            java.util.ArrayList<com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet> r5 = r12.c
            int r5 = r5.size()
            if (r5 <= 0) goto L_0x0069
            java.util.ArrayList<com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet> r5 = r12.e
            java.util.ArrayList<com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet> r7 = r12.c
            r5.addAll(r7)
            java.util.ArrayList<com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet> r5 = r12.c
            int r5 = r5.size()
            boolean[] r5 = new boolean[r5]
            int r7 = r5.length
            r8 = 0
        L_0x0062:
            if (r8 >= r7) goto L_0x006a
            boolean r9 = r5[r8]
            int r8 = r8 + 1
            goto L_0x0062
        L_0x0069:
            r5 = r6
        L_0x006a:
            java.util.Iterator r0 = r0.iterator()
        L_0x006e:
            boolean r7 = r0.hasNext()
            if (r7 == 0) goto L_0x00c8
            java.lang.Object r7 = r0.next()
            com.xiaomi.smarthome.device.Device r7 = (com.xiaomi.smarthome.device.Device) r7
            boolean r8 = r7.isOwner()
            if (r8 != 0) goto L_0x0081
            goto L_0x006e
        L_0x0081:
            java.util.ArrayList<com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet> r8 = r12.c
            if (r8 == 0) goto L_0x006e
            r8 = 0
        L_0x0086:
            java.util.ArrayList<com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet> r9 = r12.c
            int r9 = r9.size()
            if (r8 >= r9) goto L_0x006e
            java.util.ArrayList<com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet> r9 = r12.c
            java.lang.Object r9 = r9.get(r8)
            com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet r9 = (com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity.DefaultSceneItemSet) r9
            java.lang.String[] r10 = r9.b
            if (r10 == 0) goto L_0x00c5
            java.lang.String r10 = r7.model
            java.lang.String[] r11 = r9.b
            boolean r10 = com.xiaomi.smarthome.device.DeviceFactory.a((java.lang.String) r10, (java.lang.String[]) r11)
            if (r10 == 0) goto L_0x00c5
            java.lang.Boolean r10 = r9.f21427a
            boolean r10 = r10.booleanValue()
            if (r10 == 0) goto L_0x00b3
            r2.add(r7)
            r4.add(r9)
            goto L_0x00bf
        L_0x00b3:
            boolean r10 = r5[r8]
            if (r10 != 0) goto L_0x00bf
            r2.add(r7)
            r4.add(r9)
            r5[r8] = r3
        L_0x00bf:
            java.util.ArrayList<com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet> r7 = r12.e
            r7.remove(r9)
            goto L_0x006e
        L_0x00c5:
            int r8 = r8 + 1
            goto L_0x0086
        L_0x00c8:
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r0 = r12.f21234a
            java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Action> r0 = r0.k
            int r0 = r0.size()
            if (r0 != 0) goto L_0x01d4
            r0 = 0
        L_0x00d3:
            int r3 = r2.size()
            if (r0 >= r3) goto L_0x01d4
            java.lang.Object r3 = r2.get(r0)
            com.xiaomi.smarthome.scene.action.BaseInnerAction r3 = com.xiaomi.smarthome.scene.SmartHomeScenceActionFactory.a(r3)
            if (r3 == 0) goto L_0x01d0
            java.lang.Object r5 = r4.get(r0)
            com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet r5 = (com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity.DefaultSceneItemSet) r5
            com.xiaomi.smarthome.device.api.RecommendSceneItem$Key[] r5 = r5.c
            if (r5 == 0) goto L_0x01b5
            java.lang.Object r5 = r4.get(r1)
            com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet r5 = (com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity.DefaultSceneItemSet) r5
            com.xiaomi.smarthome.device.api.RecommendSceneItem$Key[] r5 = r5.c
            if (r5 == 0) goto L_0x010d
            java.lang.Object r5 = r4.get(r1)
            com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet r5 = (com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity.DefaultSceneItemSet) r5
            com.xiaomi.smarthome.device.api.RecommendSceneItem$Key[] r5 = r5.c
            int r5 = r5.length
            if (r5 <= 0) goto L_0x010d
            java.lang.Object r5 = r4.get(r1)
            com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet r5 = (com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity.DefaultSceneItemSet) r5
            com.xiaomi.smarthome.device.api.RecommendSceneItem$Key[] r5 = r5.c
            r5 = r5[r1]
            goto L_0x010e
        L_0x010d:
            r5 = r6
        L_0x010e:
            if (r5 == 0) goto L_0x014f
            java.lang.String r7 = r5.mKey
            java.lang.Object r8 = r2.get(r0)
            com.xiaomi.smarthome.device.Device r8 = (com.xiaomi.smarthome.device.Device) r8
            java.lang.String r8 = r8.model
            boolean r7 = r7.contains(r8)
            if (r7 == 0) goto L_0x0123
            java.lang.String r7 = r5.mKey
            goto L_0x0150
        L_0x0123:
            java.lang.Object r7 = r4.get(r0)
            com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet r7 = (com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity.DefaultSceneItemSet) r7
            java.lang.String[] r7 = r7.b
            int r8 = r7.length
            r9 = 0
        L_0x012d:
            if (r9 >= r8) goto L_0x014f
            r10 = r7[r9]
            java.lang.String r11 = r5.mKey
            boolean r11 = r11.contains(r10)
            if (r11 == 0) goto L_0x014c
            java.lang.String r7 = r5.mKey
            java.lang.String r7 = java.lang.String.valueOf(r7)
            java.lang.Object r8 = r2.get(r0)
            com.xiaomi.smarthome.device.Device r8 = (com.xiaomi.smarthome.device.Device) r8
            java.lang.String r8 = r8.model
            java.lang.String r7 = r7.replace(r10, r8)
            goto L_0x0150
        L_0x014c:
            int r9 = r9 + 1
            goto L_0x012d
        L_0x014f:
            r7 = r6
        L_0x0150:
            if (r7 != 0) goto L_0x016e
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r5 = r12.f21234a
            java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Action> r5 = r5.k
            java.lang.String[] r7 = r3.b()
            r7 = r7[r1]
            int[] r8 = r3.c()
            r8 = r8[r1]
            java.lang.Object r9 = r2.get(r0)
            com.xiaomi.smarthome.scene.api.SceneApi$Action r3 = r3.a(r7, r8, r9, r6)
            r5.add(r3)
            goto L_0x01d0
        L_0x016e:
            java.lang.Object r5 = r5.mValues
            java.lang.Object r8 = r2.get(r0)
            com.xiaomi.smarthome.device.Device r8 = (com.xiaomi.smarthome.device.Device) r8
            int r5 = r3.a(r7, r5, r8)
            r7 = -1
            if (r5 == r7) goto L_0x0199
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r7 = r12.f21234a
            java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Action> r7 = r7.k
            java.lang.String[] r8 = r3.b()
            r8 = r8[r5]
            int[] r9 = r3.c()
            r5 = r9[r5]
            java.lang.Object r9 = r2.get(r0)
            com.xiaomi.smarthome.scene.api.SceneApi$Action r3 = r3.a(r8, r5, r9, r6)
            r7.add(r3)
            goto L_0x01d0
        L_0x0199:
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r5 = r12.f21234a
            java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Action> r5 = r5.k
            java.lang.String[] r7 = r3.b()
            r7 = r7[r1]
            int[] r8 = r3.c()
            r8 = r8[r1]
            java.lang.Object r9 = r2.get(r0)
            com.xiaomi.smarthome.scene.api.SceneApi$Action r3 = r3.a(r7, r8, r9, r6)
            r5.add(r3)
            goto L_0x01d0
        L_0x01b5:
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r5 = r12.f21234a
            java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Action> r5 = r5.k
            java.lang.String[] r7 = r3.b()
            r7 = r7[r1]
            int[] r8 = r3.c()
            r8 = r8[r1]
            java.lang.Object r9 = r2.get(r0)
            com.xiaomi.smarthome.scene.api.SceneApi$Action r3 = r3.a(r7, r8, r9, r6)
            r5.add(r3)
        L_0x01d0:
            int r0 = r0 + 1
            goto L_0x00d3
        L_0x01d4:
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r0 = r12.f21234a
            java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Action> r0 = r0.k
            int r0 = r0.size()
            if (r0 <= 0) goto L_0x0209
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r0 = r12.b
            java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Action> r0 = r0.k
            r0.clear()
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r0 = r12.f21234a
            java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Action> r0 = r0.k
            java.util.Iterator r0 = r0.iterator()
        L_0x01ed:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0209
            java.lang.Object r1 = r0.next()
            com.xiaomi.smarthome.scene.api.SceneApi$Action r1 = (com.xiaomi.smarthome.scene.api.SceneApi.Action) r1
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r2 = r12.b     // Catch:{ JSONException -> 0x01ed }
            java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Action> r2 = r2.k     // Catch:{ JSONException -> 0x01ed }
            org.json.JSONObject r1 = r1.a()     // Catch:{ JSONException -> 0x01ed }
            com.xiaomi.smarthome.scene.api.SceneApi$Action r1 = com.xiaomi.smarthome.scene.api.SceneApi.Action.a(r1)     // Catch:{ JSONException -> 0x01ed }
            r2.add(r1)     // Catch:{ JSONException -> 0x01ed }
            goto L_0x01ed
        L_0x0209:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.scene.GoLeaveHomeSceneCreateEditActivity.a():void");
    }

    /* access modifiers changed from: private */
    public void b() {
        boolean z = true;
        if (!(this.b == null || this.f21234a == null || !this.f21234a.a(this.b))) {
            z = false;
        }
        if (this.f21234a != null && this.f21234a.x.e.size() <= 0 && this.o) {
            z = false;
        }
        if (z) {
            new MLAlertDialog.Builder(this.k).b((int) R.string.smarthome_scene_quit).a((int) R.string.smarthome_scene_quest_ok, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    GoLeaveHomeSceneCreateEditActivity.this.finish();
                }
            }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            }).d();
        } else {
            finish();
        }
    }

    public void onBackPressed() {
        b();
    }

    private void c() {
        this.mConditionTitle.setText(R.string.scene_any_condition_satified);
        j();
        View findViewById = findViewById(R.id.module_a_4_return_btn);
        if (findViewById != null) {
            findViewById.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    GoLeaveHomeSceneCreateEditActivity.this.b();
                }
            });
        }
        this.l = new ConditionsAdapter();
        this.m = new ActionsAdapter();
        this.mConditionListView.setAdapter(this.l);
        this.mActionListView.setAdapter(this.m);
        this.mConditionExpress.setVisibility(8);
        this.mDivider2.setVisibility(8);
        this.mNoConditionTitle.setVisibility(8);
        this.mModuleA4Commit.setVisibility(8);
        this.mConfirmBtn.setText(R.string.confirm);
        this.mConfirmBtn.setVisibility(0);
        this.mConfirmBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                GoLeaveHomeSceneCreateEditActivity.this.completeScene();
            }
        });
        this.mAddAction.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                GoLeaveHomeSceneCreateEditActivity.this.startAddAction();
            }
        });
        this.mAddCondition.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                GoLeaveHomeSceneCreateEditActivity.this.startAddCondition();
            }
        });
        this.mConditionLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                GoLeaveHomeSceneCreateEditActivity.this.startAddCondition();
            }
        });
        this.mTaskLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                GoLeaveHomeSceneCreateEditActivity.this.startAddAction();
            }
        });
        this.mCheckBox.setChecked(this.b.n);
        this.mCheckBox.setOnPerformCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                GoLeaveHomeSceneCreateEditActivity.this.b.n = z;
                GoLeaveHomeSceneCreateEditActivity.this.f21234a.n = z;
                if (!TextUtils.isEmpty(GoLeaveHomeSceneCreateEditActivity.this.n)) {
                    final XQProgressDialog a2 = XQProgressDialog.a(GoLeaveHomeSceneCreateEditActivity.this.k, (CharSequence) null, GoLeaveHomeSceneCreateEditActivity.this.getString(R.string.smarthome_scene_saving_scene, new Object[]{true, false}));
                    if (SmartHomeConfig.c) {
                        RemoteSceneApi.a().c(GoLeaveHomeSceneCreateEditActivity.this.getContext(), GoLeaveHomeSceneCreateEditActivity.this.b, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                            /* renamed from: a */
                            public void onSuccess(Void voidR) {
                                a2.dismiss();
                            }

                            public void onFailure(Error error) {
                                a2.dismiss();
                                Toast.makeText(GoLeaveHomeSceneCreateEditActivity.this.k, R.string.smarthome_scene_update_fail, 0).show();
                            }
                        });
                    }
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void startAddCondition() {
        if (this.f21234a.l.size() >= 5) {
            Toast.makeText(this, R.string.add_condition_error, 0).show();
            return;
        }
        Intent intent = new Intent(this.k, StartConditionActivity.class);
        intent.putParcelableArrayListExtra("extra_exclude_conditions", this.d);
        startActivityForResult(intent, 1);
        CreateSceneManager.a().l();
        CreateSceneManager.a().a(this.f21234a);
    }

    /* access modifiers changed from: package-private */
    public void startAddAction() {
        startActivityForResult(new Intent(this.k, SmartHomeSceneActionChooseActivity.class), 8193);
        CreateSceneManager.a().l();
        CreateSceneManager.a().a(this.f21234a);
    }

    private SceneApi.Condition d() {
        if (this.f21234a == null || this.f21234a.l == null) {
            return null;
        }
        for (SceneApi.Condition next : this.f21234a.l) {
            if (next.f21522a == SceneApi.Condition.LAUNCH_TYPE.MIKEY) {
                return next;
            }
        }
        return null;
    }

    private void e() {
        if (this.h == null) {
            this.h = new MLAlertDialog.Builder(this.k).a((int) R.string.common_hint).d(false).b((int) R.string.mikey_reopen_tips).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    GoLeaveHomeSceneCreateEditActivity.this.f();
                }
            }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    ((MLAlertDialog) dialogInterface).setAudoDismiss(true);
                }
            }).b();
        }
        this.h.show();
    }

    public void completeScene() {
        boolean z;
        Iterator<SceneApi.Action> it = this.f21234a.k.iterator();
        while (true) {
            z = true;
            if (!it.hasNext()) {
                break;
            }
            SceneApi.Action next = it.next();
            if (next.f21521a == 1) {
                String str = null;
                Iterator<SceneApi.Condition> it2 = this.f21234a.l.iterator();
                if (it2.hasNext()) {
                    str = SmartHomeSceneUtility.e(SHApplication.getAppContext(), it2.next());
                }
                if (str == null && this.f21234a.x.e.size() > 0) {
                    if (this.p) {
                        str = getString(R.string.condition_come_home);
                    } else {
                        str = getString(R.string.condition_leave_home);
                    }
                }
                ((SceneApi.SHScenePushPayload) next.g).b = str;
            }
        }
        SceneApi.Condition d2 = d();
        if (d2 != null) {
            String str2 = d2.e.f21523a;
            String str3 = d2.e.j;
        }
        if (SHConfig.a().a("show_scene_user_lincense") != -1) {
            z = false;
        }
        if (z) {
            SHConfig.a().a("show_scene_user_lincense", 0);
            new MLAlertDialog.Builder(getContext()).a(StringUtil.a((Context) this, (int) R.string.scene_user_license_title_1_underline, (int) R.string.scene_user_license_title_1_tpl, (ClickableSpan) new ClickableSpan() {
                public void updateDrawState(TextPaint textPaint) {
                    super.updateDrawState(textPaint);
                    textPaint.setColor(GoLeaveHomeSceneCreateEditActivity.this.getResources().getColor(R.color.class_text_27));
                    textPaint.setUnderlineText(true);
                }

                public void onClick(View view) {
                    GoLeaveHomeSceneCreateEditActivity.this.startActivity(new Intent(GoLeaveHomeSceneCreateEditActivity.this.getContext(), SceneUseLicnece.class));
                }
            })).a((int) R.string.scene_user_license_title).a((int) R.string.smarthome_share_accept, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    GoLeaveHomeSceneCreateEditActivity.this.f();
                }
            }).b((int) R.string.smarthome_share_reject, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    SHConfig.a().a("show_scene_user_lincense", -1);
                }
            }).a(false).b().show();
            return;
        }
        f();
    }

    /* access modifiers changed from: private */
    public void f() {
        if (this.o) {
            if ((this.f21234a.l.size() > 0 || this.f21234a.x.e.size() > 0) && this.f21234a.k.size() > 0) {
                saveNewScene();
            } else if (this.f21234a.l.size() == 0 && this.f21234a.x.e.size() == 0) {
                Toast.makeText(this.k, R.string.smarthome_scene_add_start_condition, 0).show();
            } else {
                Toast.makeText(this.k, R.string.smarthome_scene_set_fail_at_least_0, 0).show();
            }
        } else if ((this.f21234a.l.size() <= 0 && this.f21234a.x.e.size() <= 0) || this.f21234a.k.size() <= 0) {
            Toast.makeText(this.k, R.string.smarthome_scene_set_fail_at_least_0, 0).show();
        } else if (SceneManager.x().e(this.f21234a)) {
            MLAlertDialog b2 = new MLAlertDialog.Builder(this.k).a((int) R.string.smarthome_scene_conflict).b((int) R.string.sh_confirm, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            }).b();
            TextView textView = new TextView(b2.getContext());
            textView.setText(SceneManager.x().B());
            textView.setLineSpacing(0.0f, 1.5f);
            textView.setPadding(50, 0, 50, 0);
            b2.setView(textView);
            b2.show();
        } else {
            g();
        }
    }

    private void g() {
        if (this.f21234a.a(this.b)) {
            finish();
            return;
        }
        final XQProgressDialog a2 = XQProgressDialog.a(this.k, (CharSequence) null, getString(R.string.smarthome_scene_saving_scene, new Object[]{true, false}));
        if (SmartHomeConfig.c) {
            RemoteSceneApi.a().c(getContext(), this.f21234a, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                /* renamed from: a */
                public void onSuccess(Void voidR) {
                    a2.dismiss();
                    if (!TextUtils.isEmpty(GoLeaveHomeSceneCreateEditActivity.this.n)) {
                        SceneManager.x().g(GoLeaveHomeSceneCreateEditActivity.this.n);
                    }
                    SceneManager.x().d(GoLeaveHomeSceneCreateEditActivity.this.f21234a);
                    SceneManager.x().c((String) null);
                    GoLeaveHomeSceneCreateEditActivity.this.finish();
                }

                public void onFailure(Error error) {
                    a2.dismiss();
                    Toast.makeText(GoLeaveHomeSceneCreateEditActivity.this.k, R.string.smarthome_scene_update_fail, 0).show();
                }
            });
        } else {
            a2.dismiss();
        }
    }

    @OnClick({2131430988})
    public void gotoMorePage() {
        if (!TextUtils.isEmpty(this.n) && this.f21234a.a(this.b)) {
            Intent intent = new Intent(this, SceneMoreActivity.class);
            intent.putExtra("extra_scene_id", this.n);
            startActivityForResult(intent, 1000);
        }
    }

    public void saveNewScene() {
        if (this.f21234a.a(this.b)) {
            finish();
            return;
        }
        this.g = XQProgressDialog.a(this.k, (CharSequence) null, getString(R.string.smarthome_scene_saving_scene, new Object[]{true, false}));
        if (SmartHomeConfig.c) {
            RemoteSceneApi.a().a((Context) this, this.f21234a, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    String optString = jSONObject.optString("us_id");
                    GoLeaveHomeSceneCreateEditActivity.this.g.dismiss();
                    String str = GoLeaveHomeSceneCreateEditActivity.this.f21234a.f;
                    GoLeaveHomeSceneCreateEditActivity.this.f21234a.f = optString;
                    if (!TextUtils.isEmpty(GoLeaveHomeSceneCreateEditActivity.this.n)) {
                        SceneManager.x().g(GoLeaveHomeSceneCreateEditActivity.this.n);
                    }
                    SceneManager.x().d(GoLeaveHomeSceneCreateEditActivity.this.f21234a);
                    SceneManager.x().c((String) null);
                    Toast.makeText(GoLeaveHomeSceneCreateEditActivity.this.k, R.string.smarthome_scene_set_succ, 0).show();
                    Intent intent = new Intent();
                    intent.putExtra("old_id", str);
                    intent.putExtra("new_id", GoLeaveHomeSceneCreateEditActivity.this.f21234a.f);
                    GoLeaveHomeSceneCreateEditActivity.this.setResult(-1, intent);
                    GoLeaveHomeSceneCreateEditActivity.this.finish();
                }

                public void onFailure(Error error) {
                    GoLeaveHomeSceneCreateEditActivity.this.g.dismiss();
                    Toast.makeText(GoLeaveHomeSceneCreateEditActivity.this.k, R.string.smarthome_scene_set_fail, 0).show();
                }
            });
        } else {
            this.g.dismiss();
        }
    }

    private void h() {
        int i2;
        String str = "";
        if (TextUtils.isEmpty(this.n)) {
            if (this.p) {
                i2 = R.string.condition_come_home;
            } else {
                i2 = R.string.condition_leave_home;
            }
            str = getString(i2);
        } else if (!this.f21234a.g.isEmpty()) {
            str = this.f21234a.g;
        }
        this.mModuleA4ReturnTitle.setText(str);
    }

    private class ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public ImageView f21278a;
        public SwitchButton b;
        public TextView c;
        public TextView d;
        public TextView e;
        public ImageView f;
        public TextView g;
        public TextView h;

        private ViewHolder() {
        }
    }

    private View i() {
        View inflate = getLayoutInflater().inflate(R.layout.scene_add_scene_condition_item, (ViewGroup) null);
        if (inflate != null) {
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.f21278a = (ImageView) inflate.findViewById(R.id.icon);
            viewHolder.b = (SwitchButton) inflate.findViewById(R.id.item_enable);
            viewHolder.e = (TextView) inflate.findViewById(R.id.add_timesp);
            viewHolder.e.setVisibility(8);
            viewHolder.f = (ImageView) inflate.findViewById(R.id.set_time_btn);
            viewHolder.f.setVisibility(8);
            viewHolder.c = (TextView) inflate.findViewById(R.id.name);
            viewHolder.d = (TextView) inflate.findViewById(R.id.key_name);
            viewHolder.g = (TextView) inflate.findViewById(R.id.offline);
            viewHolder.g.setVisibility(8);
            viewHolder.h = (TextView) inflate.findViewById(R.id.buy_button);
            viewHolder.h.setVisibility(8);
            inflate.setTag(viewHolder);
        }
        return inflate;
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i2, int i3, Intent intent) {
        boolean z = false;
        if (i3 == -1 && i2 == 1000 && intent.getBooleanExtra(SceneMoreActivity.TAG_SCENE_DELETED, false)) {
            finish();
        } else if (i2 == 8192) {
            this.f21234a = SceneDataCache.INSTANCE.getCahcedScene();
            if (this.f21234a.x.e.size() > 0 && this.o) {
                this.f21234a.x.b = true;
                ViewHolder viewHolder = (ViewHolder) this.groupView.getTag();
            }
        } else if (i2 != 102) {
            a(false);
        } else if (i3 == -1 && intent != null) {
            CorntabUtils.CorntabParam corntabParam = (CorntabUtils.CorntabParam) intent.getParcelableExtra(SmartHomeSceneTimerActivity.TIME_PARAM);
            Iterator<SceneApi.Condition> it = this.f21234a.l.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                SceneApi.Condition next = it.next();
                if (next.b != null) {
                    next.b.f21527a = corntabParam;
                    z = true;
                    break;
                }
            }
            ViewHolder viewHolder2 = (ViewHolder) this.timerView.getTag();
            if (!z) {
                this.f21234a.l.add(HomeSceneFactory.INSTANCE.createTimerCondition(corntabParam));
            }
            if (viewHolder2 != null) {
                viewHolder2.d.setText(SmartHomeSceneTimerActivity.getTimerHint(getContext(), corntabParam));
            }
        }
    }

    private void j() {
        CorntabUtils.CorntabParam corntabParam;
        this.timerView = i();
        ViewHolder viewHolder = (ViewHolder) this.timerView.getTag();
        viewHolder.c.setText(R.string.smarthome_scene_start_timer);
        viewHolder.f21278a.setImageResource(R.drawable.std_scene_icon_timing);
        if (this.p) {
            corntabParam = HomeSceneFactory.INSTANCE.getDefaultGoHomeTimer();
        } else {
            corntabParam = HomeSceneFactory.INSTANCE.getDefaultLeaveHomeTimer();
        }
        if (!this.o) {
            Iterator<SceneApi.Condition> it = this.f21234a.l.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                SceneApi.Condition next = it.next();
                if (next.f21522a.equals(SceneApi.Condition.LAUNCH_TYPE.TIMER) && next.b != null) {
                    corntabParam = next.b.f21527a;
                    break;
                }
            }
        }
        viewHolder.d.setText(SmartHomeSceneTimerActivity.getTimerHint(this, corntabParam));
        this.timerView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CorntabUtils.CorntabParam corntabParam;
                Intent intent = new Intent(GoLeaveHomeSceneCreateEditActivity.this.getContext(), SmartHomeSceneTimerActivity.class);
                if (GoLeaveHomeSceneCreateEditActivity.this.p) {
                    corntabParam = HomeSceneFactory.INSTANCE.getDefaultGoHomeTimer();
                } else {
                    corntabParam = HomeSceneFactory.INSTANCE.getDefaultLeaveHomeTimer();
                }
                Iterator<SceneApi.Condition> it = GoLeaveHomeSceneCreateEditActivity.this.f21234a.l.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    SceneApi.Condition next = it.next();
                    if (next.f21522a.equals(SceneApi.Condition.LAUNCH_TYPE.TIMER) && next.b != null) {
                        corntabParam = next.b.f21527a;
                        break;
                    }
                }
                intent.putExtra(SmartHomeSceneTimerActivity.TIME_PARAM, corntabParam);
                GoLeaveHomeSceneCreateEditActivity.this.startActivityForResult(intent, 102);
            }
        });
        this.groupView = i();
        ViewHolder viewHolder2 = (ViewHolder) this.groupView.getTag();
        if (this.p) {
            viewHolder2.c.setText(R.string.scene2_device_group_condition_come);
        } else {
            viewHolder2.c.setText(R.string.scene2_device_group_condition_leave);
        }
        viewHolder2.d.setText(R.string.scene2_device_group_condition_desc);
        viewHolder2.f21278a.setImageResource(R.drawable.std_sence_icon_judgment);
        if (!this.o) {
            SceneApi.SmartHomeScene.GroupCondition groupCondition = this.f21234a.x;
        }
        this.groupView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(GoLeaveHomeSceneCreateEditActivity.this.getContext(), GoLeaveHomeGroupConditionActivity.class);
                SceneDataCache.INSTANCE.setCachedScene(GoLeaveHomeSceneCreateEditActivity.this.f21234a);
                if (GoLeaveHomeSceneCreateEditActivity.this.o) {
                    intent.putExtra(GoLeaveHomeSceneCreateEditActivity.GO_HOME_RECOMMEND_FLAG, GoLeaveHomeSceneCreateEditActivity.this.p);
                } else {
                    intent.putExtra(GoLeaveHomeSceneCreateEditActivity.GO_HOME_RECOMMEND_FLAG, SceneManager.x().b(GoLeaveHomeSceneCreateEditActivity.this.f21234a));
                }
                GoLeaveHomeSceneCreateEditActivity.this.startActivityForResult(intent, 8192);
            }
        });
        this.mConditionListView.addHeaderView(this.groupView);
    }

    /* access modifiers changed from: package-private */
    public void initOnlineAndAvatarResource() {
        if (this.f21234a != null && this.f21234a.k != null) {
            this.i = new boolean[this.f21234a.k.size()];
            this.j = new int[this.f21234a.k.size()];
            Arrays.fill(this.i, false);
            Arrays.fill(this.j, R.drawable.device_list_phone_no);
            for (int i2 = 0; i2 < this.f21234a.k.size(); i2++) {
                SmartHomeSceneUtility.SceneItemInfo a2 = SmartHomeSceneUtility.a(this.f21234a.k.get(i2));
                if (a2.b) {
                    this.i[i2] = true;
                }
                this.j[i2] = a2.f21465a;
            }
            if (this.f21234a.l != null) {
                this.mConditionOnlineItems = new boolean[this.f21234a.l.size()];
                for (int i3 = 0; i3 < this.f21234a.l.size(); i3++) {
                    if (this.f21234a.l.get(i3).c == null && this.f21234a.l.get(i3).e == null && this.f21234a.l.get(i3).f == null) {
                        this.mConditionOnlineItems[i3] = true;
                    } else {
                        String str = null;
                        if (this.f21234a.l.get(i3).c != null) {
                            str = this.f21234a.l.get(i3).c.f21523a;
                        }
                        if (this.f21234a.l.get(i3).e != null) {
                            str = this.f21234a.l.get(i3).e.f21523a;
                        }
                        if (this.f21234a.l.get(i3).f != null) {
                            str = this.f21234a.l.get(i3).f.f21523a;
                        }
                        if (str != null) {
                            Device b2 = SmartHomeDeviceManager.a().b(str);
                            if (b2 == null) {
                                b2 = SmartHomeDeviceManager.a().l(str);
                            }
                            this.mConditionOnlineItems[i3] = b2 != null && b2.did.equalsIgnoreCase(str) && b2.isOnline;
                        } else {
                            this.mConditionOnlineItems[i3] = true;
                        }
                    }
                }
            }
        }
    }

    class ConditionsAdapter extends BaseAdapter {

        /* renamed from: a  reason: collision with root package name */
        int f21267a;
        @BindView(2131428105)
        TextView mBuyButton;
        @BindView(2131429685)
        SimpleDraweeView mIcon;
        @BindView(2131430270)
        TextView mKeyName;
        @BindView(2131431126)
        TextView mName;
        @BindView(2131431264)
        TextView mOffline;
        @BindView(2131432302)
        ImageView mTimeSetButton;
        @BindView(2131427606)
        TextView mTimeSpan;

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return (long) i;
        }

        ConditionsAdapter() {
        }

        public int getCount() {
            if (!(GoLeaveHomeSceneCreateEditActivity.this.f21234a == null || GoLeaveHomeSceneCreateEditActivity.this.f21234a.l == null)) {
                this.f21267a = GoLeaveHomeSceneCreateEditActivity.this.f21234a.l.size();
            }
            return this.f21267a;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = GoLeaveHomeSceneCreateEditActivity.this.getLayoutInflater().inflate(R.layout.scene_add_scene_condition_item, (ViewGroup) null);
            }
            ButterKnife.bind((Object) this, view);
            if (i < GoLeaveHomeSceneCreateEditActivity.this.f21234a.l.size()) {
                final SceneApi.Condition condition = GoLeaveHomeSceneCreateEditActivity.this.f21234a.l.get(i);
                if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.DEVICE) {
                    this.mKeyName.setVisibility(0);
                    this.mKeyName.setText(condition.c.b);
                } else if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.TIMER) {
                    this.mKeyName.setVisibility(0);
                    this.mKeyName.setText(SmartHomeSceneTimerActivity.getTimerHint(GoLeaveHomeSceneCreateEditActivity.this.getContext(), condition.b != null ? condition.b.f21527a : null));
                } else {
                    this.mKeyName.setVisibility(8);
                }
                this.mName.setText(SmartHomeSceneUtility.b(GoLeaveHomeSceneCreateEditActivity.this.k, condition));
                SmartHomeSceneUtility.a(this.mIcon, condition);
                if (condition == null || !(condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.DEVICE || condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.MIKEY || condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.MIBAND)) {
                    this.mTimeSetButton.setVisibility(8);
                    this.mTimeSpan.setVisibility(8);
                    this.mOffline.setVisibility(8);
                    view.setOnClickListener((View.OnClickListener) null);
                    view.setOnLongClickListener(new View.OnLongClickListener() {
                        public boolean onLongClick(View view) {
                            new MLAlertDialog.Builder(GoLeaveHomeSceneCreateEditActivity.this.k).a((CharSequence[]) new String[]{GoLeaveHomeSceneCreateEditActivity.this.getString(R.string.delete)}, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    GoLeaveHomeSceneCreateEditActivity.this.f21234a.l.remove(condition);
                                    GoLeaveHomeSceneCreateEditActivity.this.l.notifyDataSetChanged();
                                    GoLeaveHomeSceneCreateEditActivity.this.a(false);
                                    dialogInterface.cancel();
                                }
                            }).b().show();
                            return true;
                        }
                    });
                } else {
                    this.mTimeSpan.setVisibility(8);
                    this.mOffline.setVisibility(8);
                    this.mTimeSetButton.setVisibility(8);
                    if (!GoLeaveHomeSceneCreateEditActivity.this.mConditionOnlineItems[i]) {
                        this.mOffline.setVisibility(0);
                        this.mTimeSetButton.setVisibility(8);
                        this.mTimeSpan.setVisibility(8);
                        view.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                GoLeaveHomeSceneCreateEditActivity.this.showOfflineDialog();
                            }
                        });
                    } else if (condition.c != null) {
                        this.mTimeSetButton.setVisibility(0);
                        if (condition.c.e != -1) {
                            this.mTimeSpan.setVisibility(0);
                            TextView textView = this.mTimeSpan;
                            textView.setText("" + condition.c.e + ":00-" + condition.c.f + ":00");
                            this.mTimeSetButton.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View view) {
                                    int[] iArr = {R.string.smarthome_delete_timespan, R.string.smarthome_modify_timespan, R.string.smarthome_cancel_timespan};
                                    String[] strArr = new String[iArr.length];
                                    for (int i = 0; i < iArr.length; i++) {
                                        strArr[i] = GoLeaveHomeSceneCreateEditActivity.this.getString(iArr[i]);
                                    }
                                    new MLAlertDialog.Builder(GoLeaveHomeSceneCreateEditActivity.this.getContext()).a((CharSequence[]) strArr, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            switch (i) {
                                                case 0:
                                                    GoLeaveHomeSceneCreateEditActivity.this.deleteTimeSpan(condition);
                                                    return;
                                                case 1:
                                                    Intent intent = new Intent(GoLeaveHomeSceneCreateEditActivity.this.k, SmartHomeSceneTimeSpan.class);
                                                    CreateSceneManager.a().c(condition);
                                                    GoLeaveHomeSceneCreateEditActivity.this.startActivityForResult(intent, 8193);
                                                    return;
                                                default:
                                                    return;
                                            }
                                        }
                                    }).d();
                                }
                            });
                        } else {
                            this.mTimeSpan.setVisibility(8);
                            this.mOffline.setVisibility(8);
                            this.mTimeSetButton.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View view) {
                                    GoLeaveHomeSceneCreateEditActivity.this.startActivityForResult(new Intent(GoLeaveHomeSceneCreateEditActivity.this.k, SmartHomeSceneTimeSpan.class), 8193);
                                    CreateSceneManager.a().c(condition);
                                }
                            });
                        }
                        view.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                Intent intent = new Intent(GoLeaveHomeSceneCreateEditActivity.this.k, StartConditionActivity.class);
                                CreateSceneManager.a().c(condition);
                                CreateSceneManager.a().a(GoLeaveHomeSceneCreateEditActivity.this.f21234a);
                                GoLeaveHomeSceneCreateEditActivity.this.startActivityForResult(intent, 1);
                            }
                        });
                    }
                    view.setOnLongClickListener(new View.OnLongClickListener() {
                        public boolean onLongClick(View view) {
                            new MLAlertDialog.Builder(GoLeaveHomeSceneCreateEditActivity.this.k).a((CharSequence[]) new String[]{GoLeaveHomeSceneCreateEditActivity.this.getString(R.string.delete)}, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    GoLeaveHomeSceneCreateEditActivity.this.f21234a.l.remove(condition);
                                    GoLeaveHomeSceneCreateEditActivity.this.l.notifyDataSetChanged();
                                    GoLeaveHomeSceneCreateEditActivity.this.a(false);
                                    dialogInterface.cancel();
                                }
                            }).b().show();
                            return true;
                        }
                    });
                }
            }
            return view;
        }
    }

    /* access modifiers changed from: package-private */
    public void deleteTimeSpan(SceneApi.Condition condition) {
        condition.c.e = -1;
        condition.c.f = -1;
        condition.c.g = -1;
        condition.c.h = -1;
        condition.c.i = null;
        a(false);
    }

    class ActionsAdapter extends BaseAdapter {
        private int b = 0;
        /* access modifiers changed from: private */
        public int c = 0;
        @BindView(2131427680)
        ImageView mAnchor;
        @BindView(2131427890)
        View mBottomline;
        @BindView(2131428105)
        TextView mBuyButton;
        @BindView(2131428716)
        View mDelayBottomLine;
        @BindView(2131428720)
        View mDelayLayout;
        @BindView(2131428718)
        View mDelayTimeLine;
        @BindView(2131428719)
        TextView mDelayTimeText;
        @BindView(2131428721)
        View mDelayTopLine;
        @BindView(2131429697)
        View mIconBottomLine;
        @BindView(2131429735)
        View mIconTopLine;
        @BindView(2131429757)
        SimpleDraweeView mImage;
        @BindView(2131431264)
        TextView mOffline;
        @BindView(2131432650)
        TextView mSubTitle;
        @BindView(2131432752)
        View mTaskLayout;
        @BindView(2131432910)
        TextView mTitle;

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        ActionsAdapter() {
        }

        public int getCount() {
            if (GoLeaveHomeSceneCreateEditActivity.this.e != null) {
                this.b = GoLeaveHomeSceneCreateEditActivity.this.e.size();
            }
            if (!(GoLeaveHomeSceneCreateEditActivity.this.f21234a == null || GoLeaveHomeSceneCreateEditActivity.this.f21234a.k == null)) {
                this.c = GoLeaveHomeSceneCreateEditActivity.this.f21234a.k.size();
            }
            return this.b + this.c;
        }

        public View getView(final int i, View view, ViewGroup viewGroup) {
            String str;
            if (view == null) {
                view = GoLeaveHomeSceneCreateEditActivity.this.getLayoutInflater().inflate(R.layout.smarthome_scene_condition_task_item, (ViewGroup) null);
            }
            ButterKnife.bind((Object) this, view);
            if (i >= this.c) {
                this.mTaskLayout.setVisibility(0);
                this.mDelayLayout.setVisibility(8);
                Device k = DeviceFactory.k(((SmartHomeSceneCreateEditActivity.DefaultSceneItemSet) GoLeaveHomeSceneCreateEditActivity.this.e.get(i - this.c)).b[0]);
                this.mTitle.setText(k.name);
                this.mOffline.setVisibility(8);
                this.mTitle.setTextColor(GoLeaveHomeSceneCreateEditActivity.this.k.getResources().getColor(R.color.class_text_12));
                DeviceFactory.c(k.model, this.mImage);
                if (i == 0 && i == GoLeaveHomeSceneCreateEditActivity.this.f21234a.k.size() - 1) {
                    this.mIconTopLine.setVisibility(4);
                    this.mIconBottomLine.setVisibility(4);
                    this.mBottomline.setVisibility(4);
                } else if (i == 0) {
                    this.mBottomline.setVisibility(0);
                    this.mIconTopLine.setVisibility(4);
                    this.mIconBottomLine.setVisibility(0);
                } else if (i == GoLeaveHomeSceneCreateEditActivity.this.f21234a.k.size() - 1) {
                    this.mIconTopLine.setVisibility(0);
                    this.mIconBottomLine.setVisibility(4);
                    this.mBottomline.setVisibility(4);
                } else {
                    this.mIconTopLine.setVisibility(0);
                    this.mIconBottomLine.setVisibility(0);
                    this.mBottomline.setVisibility(0);
                }
                view.setOnClickListener((View.OnClickListener) null);
                view.setOnLongClickListener((View.OnLongClickListener) null);
                this.mBuyButton.setVisibility(0);
                this.mBuyButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (TextUtils.isEmpty(((SmartHomeSceneCreateEditActivity.DefaultSceneItemSet) GoLeaveHomeSceneCreateEditActivity.this.e.get(i - ActionsAdapter.this.c)).d)) {
                            GoLeaveHomeSceneCreateEditActivity.this.showBuyDialog();
                            return;
                        }
                        UrlDispatchManger a2 = UrlDispatchManger.a();
                        a2.c("https://home.mi.com/shop/detail?gid=" + ((SmartHomeSceneCreateEditActivity.DefaultSceneItemSet) GoLeaveHomeSceneCreateEditActivity.this.e.get(i - ActionsAdapter.this.c)).d);
                    }
                });
                this.mAnchor.setVisibility(8);
                return view;
            }
            final SceneApi.Action action = GoLeaveHomeSceneCreateEditActivity.this.f21234a.k.get(i);
            this.mBuyButton.setVisibility(8);
            if (action.g instanceof SceneApi.SHSceneDelayPayload) {
                this.mTaskLayout.setVisibility(8);
                this.mDelayLayout.setVisibility(0);
                if (i == 0 && i == GoLeaveHomeSceneCreateEditActivity.this.f21234a.k.size() - 1) {
                    this.mDelayTimeLine.setVisibility(4);
                    this.mDelayTopLine.setVisibility(4);
                    this.mDelayBottomLine.setVisibility(4);
                } else if (i == 0) {
                    this.mDelayTimeLine.setVisibility(4);
                    this.mDelayTopLine.setVisibility(4);
                    this.mDelayBottomLine.setVisibility(0);
                } else if (i == GoLeaveHomeSceneCreateEditActivity.this.f21234a.k.size() - 1) {
                    this.mDelayTimeLine.setVisibility(4);
                    this.mDelayTopLine.setVisibility(0);
                    this.mDelayBottomLine.setVisibility(4);
                } else {
                    this.mDelayTimeLine.setVisibility(0);
                    this.mDelayTopLine.setVisibility(0);
                    this.mDelayBottomLine.setVisibility(0);
                }
                if (action.g.g < 60) {
                    str = GoLeaveHomeSceneCreateEditActivity.this.getResources().getQuantityString(R.plurals.smarthome_scene_delay_detal_sec, action.g.g % 60, new Object[]{Integer.valueOf(action.g.g % 60)});
                } else if (action.g.g % 60 == 0) {
                    str = GoLeaveHomeSceneCreateEditActivity.this.getResources().getQuantityString(R.plurals.smarthome_scene_delay_detal_min, action.g.g / 60, new Object[]{Integer.valueOf(action.g.g / 60)});
                } else {
                    String quantityString = GoLeaveHomeSceneCreateEditActivity.this.getResources().getQuantityString(R.plurals.automation_minute, action.g.g / 60, new Object[]{Integer.valueOf(action.g.g / 60)});
                    String quantityString2 = GoLeaveHomeSceneCreateEditActivity.this.getResources().getQuantityString(R.plurals.automation_seconds, action.g.g % 60, new Object[]{Integer.valueOf(action.g.g % 60)});
                    str = String.format(GoLeaveHomeSceneCreateEditActivity.this.getString(R.string.smarthome_scene_delay_detal_min_sec), new Object[]{quantityString, quantityString2});
                }
                this.mDelayTimeText.setText(str);
                view.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Intent intent = new Intent(GoLeaveHomeSceneCreateEditActivity.this.k, SmartHomeSceneTimerDelay.class);
                        CreateSceneManager.a().a(GoLeaveHomeSceneCreateEditActivity.this.f21234a);
                        CreateSceneManager.a().c(action);
                        GoLeaveHomeSceneCreateEditActivity.this.startActivityForResult(intent, 1);
                    }
                });
            } else {
                this.mTaskLayout.setVisibility(0);
                this.mDelayLayout.setVisibility(8);
                if (i == 0 && i == GoLeaveHomeSceneCreateEditActivity.this.f21234a.k.size() - 1) {
                    this.mIconTopLine.setVisibility(4);
                    this.mIconBottomLine.setVisibility(4);
                    this.mBottomline.setVisibility(4);
                } else if (i == 0) {
                    this.mBottomline.setVisibility(0);
                    this.mIconTopLine.setVisibility(4);
                    this.mIconBottomLine.setVisibility(0);
                } else if (i == GoLeaveHomeSceneCreateEditActivity.this.f21234a.k.size() - 1) {
                    this.mIconTopLine.setVisibility(0);
                    this.mIconBottomLine.setVisibility(4);
                    this.mBottomline.setVisibility(4);
                } else {
                    this.mIconTopLine.setVisibility(0);
                    this.mIconBottomLine.setVisibility(0);
                    this.mBottomline.setVisibility(0);
                }
                if (i != 0 && (GoLeaveHomeSceneCreateEditActivity.this.f21234a.k.get(i).g instanceof SceneApi.SHSceneDelayPayload)) {
                    this.mBottomline.setVisibility(4);
                } else if (i == GoLeaveHomeSceneCreateEditActivity.this.f21234a.k.size() - 1) {
                    this.mBottomline.setVisibility(4);
                } else {
                    this.mBottomline.setVisibility(0);
                }
                this.mOffline.setVisibility(8);
                this.mAnchor.setVisibility(8);
                SmartHomeSceneUtility.a(this.mImage, this.mTitle, action);
                this.mSubTitle.setText(action.c);
                if (GoLeaveHomeSceneCreateEditActivity.this.i == null || GoLeaveHomeSceneCreateEditActivity.this.i.length <= i || !GoLeaveHomeSceneCreateEditActivity.this.i[i]) {
                    this.mOffline.setVisibility(0);
                    this.mAnchor.setVisibility(8);
                    view.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            GoLeaveHomeSceneCreateEditActivity.this.showOfflineDialog();
                        }
                    });
                } else {
                    view.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            Intent intent = new Intent(GoLeaveHomeSceneCreateEditActivity.this.k, SmartHomeSceneActionChooseActivity.class);
                            CreateSceneManager.a().c(action);
                            CreateSceneManager.a().a(GoLeaveHomeSceneCreateEditActivity.this.f21234a);
                            GoLeaveHomeSceneCreateEditActivity.this.startActivityForResult(intent, 1);
                        }
                    });
                }
            }
            view.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View view) {
                    new MLAlertDialog.Builder(GoLeaveHomeSceneCreateEditActivity.this.k).a((CharSequence[]) new String[]{GoLeaveHomeSceneCreateEditActivity.this.getString(R.string.delete)}, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (i == 0) {
                                GoLeaveHomeSceneCreateEditActivity.this.f21234a.k.remove(i);
                                GoLeaveHomeSceneCreateEditActivity.this.a(false);
                            }
                        }
                    }).d();
                    return true;
                }
            });
            return view;
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        a(false);
    }

    /* access modifiers changed from: private */
    public void a(boolean z) {
        initOnlineAndAvatarResource();
        if (this.f21234a.k.size() > 0 || this.e.size() > 0) {
            this.mNoActionTitle.setVisibility(8);
            this.mDivider4.setVisibility(0);
            this.mConfirmBtn.setEnabled(true);
        } else {
            this.mNoActionTitle.setVisibility(0);
            this.mDivider4.setVisibility(0);
            this.mConfirmBtn.setEnabled(false);
        }
        if (TextUtils.isEmpty(this.n) || !this.f21234a.a(this.b)) {
            this.mConfirmBtn.setVisibility(0);
            this.mModuleA4Commit.setVisibility(8);
            this.mConfirmBtn.setText(R.string.confirm);
        } else {
            this.mModuleA4Commit.setVisibility(0);
            this.mConfirmBtn.setVisibility(8);
            this.mModuleA4Commit.setText("");
            this.mModuleA4Commit.setBackgroundResource(R.drawable.std_tittlebar_main_device_more);
        }
        this.m.notifyDataSetChanged();
        this.l.notifyDataSetChanged();
    }

    /* access modifiers changed from: package-private */
    public void showBuyDialog() {
        new MLAlertDialog.Builder(this).b((int) R.string.no_device_title).a((int) R.string.go_to_buy, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                GoLeaveHomeSceneCreateEditActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://m.mi.com")));
            }
        }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }).d();
    }

    /* access modifiers changed from: package-private */
    public void showOfflineDialog() {
        this.dialog = new MLAlertDialog.Builder(this.k).a((int) R.string.device_offline).a(ViewUtils.a(this.k, this.dialog, (String) null), DisplayUtils.a(20.0f), 0, DisplayUtils.a(20.0f), DisplayUtils.a(20.0f)).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }).a((int) R.string.refresh_list, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                SmartHomeDeviceManager.a().p();
            }
        }).d();
    }
}
