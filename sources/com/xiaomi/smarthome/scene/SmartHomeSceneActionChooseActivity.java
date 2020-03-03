package com.xiaomi.smarthome.scene;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.server.internal.globaldynamicsetting.GlobalDynamicSettingConst;
import com.xiaomi.smarthome.core.server.internal.globaldynamicsetting.GlobalDynamicSettingManager;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.SmartHomeDeviceHelper;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.HomeVirtualDeviceHelper;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.CommonUtils;
import com.xiaomi.smarthome.library.common.widget.ExpandableItemIndicator;
import com.xiaomi.smarthome.lite.scene.LiteSceneManager;
import com.xiaomi.smarthome.scene.SmartHomeSceneActionChooseActivity;
import com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity;
import com.xiaomi.smarthome.scene.action.AutoSceneAction;
import com.xiaomi.smarthome.scene.action.BaseInnerAction;
import com.xiaomi.smarthome.scene.action.CommonTextAction;
import com.xiaomi.smarthome.scene.action.DelayInnerAction;
import com.xiaomi.smarthome.scene.action.DeviceActionFilter;
import com.xiaomi.smarthome.scene.action.InnerActionCommon;
import com.xiaomi.smarthome.scene.action.IntelligentTextAction;
import com.xiaomi.smarthome.scene.action.LiteSceneAction;
import com.xiaomi.smarthome.scene.action.PushInnerAction;
import com.xiaomi.smarthome.scene.api.SceneApi;
import com.xiaomi.smarthome.scene.condition.LocaleGetResourceFixHelper;
import com.xiaomi.smarthome.scene.view.SceneFilterManager;
import com.xiaomi.smarthome.scene.view.SceneFilterView;
import com.xiaomi.smarthome.scene.widget.ConditionDeviceFilter;
import com.xiaomi.smarthome.scenenew.actiivity.SmarthomeCreateAutoSceneActivity;
import com.xiaomi.smarthome.scenenew.view.SelectRoomDialogView;
import com.xiaomi.youpin.app_sdk.url_dispatch.UrlDispatchManger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class SmartHomeSceneActionChooseActivity extends BaseActivity {
    public static final String EXTRA_DEFAULT_ACTIONS = "extra_default_actions";
    public static final String EXTRA_SCENE_ACTION_INDEX = "extra_action_index";
    public static final int GET_ACTION_DETAIL = 101;

    /* renamed from: a  reason: collision with root package name */
    private static final String f21347a = "com.xiaomi.smarthome.scene.SmartHomeSceneActionChooseActivity";
    private SceneFilterView b;
    /* access modifiers changed from: private */
    public ActionAdapter c;
    /* access modifiers changed from: private */
    public int d = -1;
    List<Object> deviceList = new ArrayList();
    /* access modifiers changed from: private */
    public LinearLayoutManager e;
    private List<SmartHomeSceneCreateEditActivity.DefaultSceneItemSet> f;
    ConditionDeviceFilter filter;
    MLAlertDialog filterDialog;
    /* access modifiers changed from: private */
    public int g = -1;
    private int h = 0;
    /* access modifiers changed from: private */
    public int i = -1;
    private HashMap<BaseInnerAction, Boolean> j = new HashMap<>();
    private LiteSceneAction k;
    private DelayInnerAction l = new DelayInnerAction();
    private AutoSceneAction m;
    @BindView(2131428019)
    Button mBuyButton;
    @BindView(2131428133)
    View mBuyView;
    @BindView(2131428548)
    RecyclerView mContentListView;
    Context mContext;
    Object mCurrentDevice;
    BaseInnerAction mCurrentScenceAction;
    @BindView(2131427981)
    ExpandableItemIndicator mItemIndicator;
    @BindView(2131430975)
    TextView mModuleA3ReturnTransparentTitle;
    int mRequestId = -1;
    SceneApi.SmartHomeScene mScene;
    int mSceneActionIndex;
    String mSceneID;
    SceneApi.Action mSelectedAction;
    ArrayList<BaseInnerAction> mSmartHomeScenceActions = new ArrayList<>();
    ArrayList<BaseInnerAction> mTempActions = new ArrayList<>();
    @BindView(2131432919)
    RelativeLayout mTitleBarFL;
    @BindView(2131428335)
    TextView mTitleTopTV;
    private PushInnerAction n;
    private CommonTextAction o = new CommonTextAction();
    private IntelligentTextAction p = new IntelligentTextAction();
    /* access modifiers changed from: private */
    public DeviceActionFilter q = new DeviceActionFilter();
    private Locale r;
    private boolean s = false;
    @BindView(2131433002)
    View topDeviceTitleLayout;
    @BindView(2131433004)
    View topFilterLayout;

    public interface InnerValueCallback {
        void a(int i, Intent intent);

        void a(Intent intent, int i);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.smarthome_scene_action_v2);
        this.mContext = this;
        if (CoreApi.a().l()) {
            this.r = CoreApi.a().I();
        } else {
            try {
                this.r = SHApplication.getAppContext().getResources().getConfiguration().locale;
            } catch (Exception unused) {
                SharedPreferences sharedPreferences = SHApplication.getAppContext().getSharedPreferences(GlobalDynamicSettingManager.f14576a, 0);
                this.r = new Locale(sharedPreferences.getString(GlobalDynamicSettingConst.g, ""), sharedPreferences.getString(GlobalDynamicSettingConst.h, ""));
            }
        }
        ButterKnife.bind((Activity) this);
        this.mSceneActionIndex = getIntent().getIntExtra("extra_action_index", -1);
        this.f = getIntent().getParcelableArrayListExtra("extra_default_actions");
        this.mScene = CreateSceneManager.a().g();
        if (this.mScene == null) {
            finish();
            return;
        }
        if (this.mScene.l != null && this.mScene.l.size() > 0) {
            String c2 = SmartHomeSceneUtility.c(this, this.mScene.l.get(0));
            if (Locale.CHINA.equals(this.r) && !TextUtils.isEmpty(c2)) {
                this.mTitleTopTV.setText(String.format(getString(R.string.choose_action_activity_title_top_format), new Object[]{c2}));
            }
        }
        this.mSceneID = this.mScene.f;
        SceneFilterManager.c().c((List<BaseInnerAction>) this.mSmartHomeScenceActions);
        this.b = (SceneFilterView) LayoutInflater.from(this.mContext).inflate(R.layout.scene_filter_view, (ViewGroup) null);
        this.b.setmDismissListener(new SceneFilterView.OnWindowDismissListener() {
            public void a() {
                SmartHomeSceneActionChooseActivity.this.mItemIndicator.setExpandedState(false, false);
            }
        });
        this.e = new LinearLayoutManager(this);
        this.mContentListView.setLayoutManager(this.e);
        this.mContentListView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
            }

            public void onScrolled(@NonNull RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                if (SmartHomeSceneActionChooseActivity.this.d >= 0) {
                    int findFirstVisibleItemPosition = SmartHomeSceneActionChooseActivity.this.e.findFirstVisibleItemPosition();
                    if (findFirstVisibleItemPosition >= SmartHomeSceneActionChooseActivity.this.d) {
                        SmartHomeSceneActionChooseActivity.this.topFilterLayout.setVisibility(0);
                        SmartHomeSceneActionChooseActivity.this.topDeviceTitleLayout.setVisibility(0);
                    } else if (findFirstVisibleItemPosition >= 0 && findFirstVisibleItemPosition < SmartHomeSceneActionChooseActivity.this.d) {
                        SmartHomeSceneActionChooseActivity.this.topFilterLayout.setVisibility(8);
                        SmartHomeSceneActionChooseActivity.this.topDeviceTitleLayout.setVisibility(8);
                    }
                }
            }
        });
        this.c = new ActionAdapter();
        this.mContentListView.setAdapter(this.c);
        initDeviceList();
        if (this.mSmartHomeScenceActions.size() == 0) {
            this.mBuyView.setVisibility(0);
            this.mContentListView.setVisibility(8);
            this.mBuyButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    UrlDispatchManger.a().c("https://home.mi.com/shop/main");
                }
            });
        } else {
            this.mBuyView.setVisibility(8);
        }
        a();
        if (!this.mSmartHomeScenceActions.isEmpty()) {
            this.filter = new ConditionDeviceFilter(this) {
                public int b(Home home) {
                    return 1;
                }

                public void a(Home home, DeviceTagInterface.Category category) {
                    if (SmartHomeSceneActionChooseActivity.this.isValid() && SmartHomeSceneActionChooseActivity.this.filterDialog != null) {
                        SmartHomeSceneActionChooseActivity.this.a(home, category);
                        ((TextView) SmartHomeSceneActionChooseActivity.this.topFilterLayout.findViewById(R.id.filter_name)).setText(category.d);
                        SmartHomeSceneActionChooseActivity.this.q.a(category.d);
                        SmartHomeSceneActionChooseActivity.this.c.notifyDataSetChanged();
                        SmartHomeSceneActionChooseActivity.this.filterDialog.dismiss();
                    }
                }

                public void a(Home home, Room room) {
                    if (SmartHomeSceneActionChooseActivity.this.isValid() && SmartHomeSceneActionChooseActivity.this.filterDialog != null) {
                        if (room == null || !TextUtils.equals(SelectRoomDialogView.ALL_ROOM_ID, room.d())) {
                            SmartHomeSceneActionChooseActivity.this.a(home, room);
                        } else {
                            SmartHomeSceneActionChooseActivity.this.b(home);
                        }
                        ((TextView) SmartHomeSceneActionChooseActivity.this.topFilterLayout.findViewById(R.id.filter_name)).setText(room.e());
                        SmartHomeSceneActionChooseActivity.this.q.a(room.e());
                        SmartHomeSceneActionChooseActivity.this.c.notifyDataSetChanged();
                        SmartHomeSceneActionChooseActivity.this.filterDialog.dismiss();
                    }
                }

                public void a(Home home) {
                    if (SmartHomeSceneActionChooseActivity.this.isValid() && SmartHomeSceneActionChooseActivity.this.filterDialog != null) {
                        SmartHomeSceneActionChooseActivity.this.b(home);
                        ((TextView) SmartHomeSceneActionChooseActivity.this.topFilterLayout.findViewById(R.id.filter_name)).setText(R.string.tag_all);
                        SmartHomeSceneActionChooseActivity.this.q.a((String) null);
                        SmartHomeSceneActionChooseActivity.this.c.notifyDataSetChanged();
                    }
                }

                public int a(String str, Room room) {
                    return SmartHomeSceneActionChooseActivity.this.a(str, room);
                }

                public int a(String str, DeviceTagInterface.Category category) {
                    return SmartHomeSceneActionChooseActivity.this.a(str, category);
                }
            };
            this.filterDialog = this.filter.a();
        }
        this.topFilterLayout.setOnClickListener((View.OnClickListener) null);
        this.topFilterLayout.findViewById(R.id.tv_group_filter).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (SmartHomeSceneActionChooseActivity.this.filter != null) {
                    SmartHomeSceneActionChooseActivity.this.filter.c();
                }
            }
        });
    }

    private void a() {
        this.mItemIndicator.setVisibility(8);
        this.mModuleA3ReturnTransparentTitle.setText(R.string.smarthome_scene_choose_action);
    }

    /* access modifiers changed from: package-private */
    public void initDeviceList() {
        this.deviceList.clear();
        this.deviceList.addAll(SmartHomeDeviceManager.a().k());
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(SmartHomeDeviceManager.a().d());
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            if (((Device) arrayList.get(size)).isSubDevice()) {
                arrayList.remove(size);
            }
        }
        this.deviceList.addAll(arrayList);
        this.k = new LiteSceneAction(LiteSceneManager.j().e());
        this.m = new AutoSceneAction(SceneManager.x().h());
        this.n = new PushInnerAction();
        this.mSmartHomeScenceActions.addAll(a(false));
        int size2 = this.mSmartHomeScenceActions.size();
        this.h = this.mSmartHomeScenceActions.size();
        this.mSelectedAction = CreateSceneManager.a().k();
        BaseInnerAction baseInnerAction = null;
        Device device = null;
        for (int size3 = this.deviceList.size() - 1; size3 >= 0; size3--) {
            Device device2 = (Device) this.deviceList.get(size3);
            if (device2.isOwner()) {
                BaseInnerAction a2 = SmartHomeScenceActionFactory.a(device2, (SmartHomeSceneCreateEditActivity.DefaultSceneItemSet) null);
                if (a2 != null) {
                    this.mSmartHomeScenceActions.add(a2);
                    Iterator<SceneApi.Action> it = this.mScene.k.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            SceneApi.Action next = it.next();
                            int a3 = a2.a(next, this.deviceList.get(size3));
                            if (a3 >= 0 && this.mSelectedAction != null && this.mSelectedAction.equals(next)) {
                                this.g = a3;
                                device = (Device) this.deviceList.get(size3);
                                baseInnerAction = a2;
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                } else {
                    this.deviceList.remove(size3);
                }
            } else {
                this.deviceList.remove(size3);
            }
        }
        if (size2 == this.mSmartHomeScenceActions.size()) {
            this.s = false;
        } else {
            this.s = true;
        }
        if (this.g != -1) {
            this.i = this.mScene.k.indexOf(this.mSelectedAction);
            if (this.i == -1) {
                this.g = -1;
                this.mSelectedAction = null;
            } else {
                this.mScene.k.remove(this.mSelectedAction);
                CreateSceneManager.a().d(this.mScene);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (int size4 = this.mSmartHomeScenceActions.size() - 1; size4 >= 0; size4--) {
            if ((this.mSmartHomeScenceActions.get(size4) instanceof InnerActionCommon) && this.mSmartHomeScenceActions.get(size4).e()) {
                this.j.put(this.mSmartHomeScenceActions.get(size4), false);
                arrayList2.add(this.mSmartHomeScenceActions.remove(size4));
            }
        }
        this.mSmartHomeScenceActions.addAll(arrayList2);
        if (this.g != -1) {
            gotoDetailPage(baseInnerAction, device);
        }
        if (this.mSelectedAction != null && (this.mSelectedAction.g instanceof SceneApi.SHLiteScenePayload)) {
            this.i = this.mScene.k.indexOf(this.mSelectedAction);
            if (this.i == -1) {
                this.g = -1;
                this.mSelectedAction = null;
                return;
            }
            Intent intent = new Intent();
            intent.setClass(this, LiteAutomationChooseScene.class);
            intent.putExtra("select_us_id", ((SceneApi.SHLiteScenePayload) this.mSelectedAction.g).f21529a);
            startActivityForResult(intent, 101);
            CreateSceneManager.a().a((BaseInnerAction) this.k);
            this.mCurrentScenceAction = this.k;
            this.mScene.k.remove(this.mSelectedAction);
        }
        if (this.mSelectedAction != null && (this.mSelectedAction.g instanceof SceneApi.SHSceneAutoPayload)) {
            this.i = this.mScene.k.indexOf(this.mSelectedAction);
            if (this.i == -1) {
                this.g = -1;
                this.mSelectedAction = null;
                return;
            }
            Intent intent2 = new Intent();
            intent2.setClass(this, AutoSceneActionChooseActivity.class);
            intent2.putExtra(AutoSceneActionChooseActivity.SELECT_SCENE_ID, ((SceneApi.SHSceneAutoPayload) this.mSelectedAction.g).f21530a);
            intent2.putExtra(AutoSceneActionDetailChooseActivity.EXTRA_ENABEL, ((SceneApi.SHSceneAutoPayload) this.mSelectedAction.g).b);
            startActivityForResult(intent2, 101);
            CreateSceneManager.a().a((BaseInnerAction) this.m);
            this.mCurrentScenceAction = this.m;
            this.mScene.k.remove(this.mSelectedAction);
        }
        a(CreateSceneManager.a().m());
    }

    /* access modifiers changed from: private */
    public int a(String str, DeviceTagInterface.Category category) {
        if (TextUtils.isEmpty(str)) {
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
        for (int i2 = 0; i2 < this.mSmartHomeScenceActions.size(); i2++) {
            BaseInnerAction baseInnerAction = this.mSmartHomeScenceActions.get(i2);
            if (baseInnerAction instanceof InnerActionCommon) {
                InnerActionCommon innerActionCommon = (InnerActionCommon) baseInnerAction;
                if (innerActionCommon.g() != null && hashSet.contains(innerActionCommon.g().did)) {
                    return 1;
                }
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
        for (int i2 = 0; i2 < this.mSmartHomeScenceActions.size(); i2++) {
            BaseInnerAction baseInnerAction = this.mSmartHomeScenceActions.get(i2);
            if (baseInnerAction instanceof InnerActionCommon) {
                InnerActionCommon innerActionCommon = (InnerActionCommon) baseInnerAction;
                if (innerActionCommon.g() != null && hashSet.contains(innerActionCommon.g().did)) {
                    return 1;
                }
            }
        }
        return 0;
    }

    /* access modifiers changed from: private */
    public int a(String str, Room room) {
        if (TextUtils.isEmpty(str) || room == null) {
            return 0;
        }
        if (!room.d().equalsIgnoreCase(SelectRoomDialogView.ALL_ROOM_ID)) {
            if (room.d().equalsIgnoreCase(SelectRoomDialogView.DEFAULT_ROOM_ID)) {
                List<Device> l2 = HomeManager.a().l(str);
                if (l2.isEmpty()) {
                    return 0;
                }
                HashSet hashSet = new HashSet(l2);
                for (int i2 = 0; i2 < this.mSmartHomeScenceActions.size(); i2++) {
                    BaseInnerAction baseInnerAction = this.mSmartHomeScenceActions.get(i2);
                    if (baseInnerAction instanceof InnerActionCommon) {
                        InnerActionCommon innerActionCommon = (InnerActionCommon) baseInnerAction;
                        if (innerActionCommon.g() != null && hashSet.contains(innerActionCommon.g())) {
                        }
                    }
                }
                return 0;
            }
            List<String> h2 = room.h();
            if (h2.isEmpty()) {
                return 0;
            }
            HashSet hashSet2 = new HashSet(h2);
            for (int i3 = 0; i3 < this.mSmartHomeScenceActions.size(); i3++) {
                BaseInnerAction baseInnerAction2 = this.mSmartHomeScenceActions.get(i3);
                if (baseInnerAction2 instanceof InnerActionCommon) {
                    InnerActionCommon innerActionCommon2 = (InnerActionCommon) baseInnerAction2;
                    if (innerActionCommon2.g() != null && hashSet2.contains(innerActionCommon2.g().did)) {
                    }
                }
            }
            return 0;
        }
        return 1;
    }

    /* access modifiers changed from: private */
    public void b(Home home) {
        if (home == null) {
            this.c.a(this.mSmartHomeScenceActions);
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(a(true));
        List<String> a2 = HomeManager.a().a(home.j(), true);
        if (a2.isEmpty()) {
            this.c.a(this.mSmartHomeScenceActions);
        }
        HashSet hashSet = new HashSet();
        if (!a2.isEmpty()) {
            hashSet.addAll(a2);
        }
        for (int i2 = 0; i2 < this.mSmartHomeScenceActions.size(); i2++) {
            BaseInnerAction baseInnerAction = this.mSmartHomeScenceActions.get(i2);
            if (baseInnerAction instanceof InnerActionCommon) {
                InnerActionCommon innerActionCommon = (InnerActionCommon) baseInnerAction;
                if (innerActionCommon.g() != null && hashSet.contains(innerActionCommon.g().did)) {
                    arrayList.add(baseInnerAction);
                }
            }
        }
        this.c.a(arrayList);
    }

    /* access modifiers changed from: private */
    public void a(Home home, DeviceTagInterface.Category category) {
        if (home == null) {
            this.c.a(this.mSmartHomeScenceActions);
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
            for (int i2 = 0; i2 < this.mSmartHomeScenceActions.size(); i2++) {
                BaseInnerAction baseInnerAction = this.mSmartHomeScenceActions.get(i2);
                if (baseInnerAction instanceof InnerActionCommon) {
                    InnerActionCommon innerActionCommon = (InnerActionCommon) baseInnerAction;
                    if (innerActionCommon.g() != null && hashSet.contains(innerActionCommon.g().did)) {
                        arrayList.add(baseInnerAction);
                    }
                }
            }
            this.c.a(arrayList);
        }
    }

    /* access modifiers changed from: private */
    public void a(Home home, Room room) {
        if (home == null) {
            this.c.a(this.mSmartHomeScenceActions);
        } else if (room == null) {
            b(home);
        } else if (room.d().equalsIgnoreCase(SelectRoomDialogView.ALL_ROOM_ID)) {
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
                while (i2 < this.mSmartHomeScenceActions.size()) {
                    BaseInnerAction baseInnerAction = this.mSmartHomeScenceActions.get(i2);
                    boolean z = baseInnerAction instanceof InnerActionCommon;
                    if (z && z) {
                        InnerActionCommon innerActionCommon = (InnerActionCommon) baseInnerAction;
                        if (innerActionCommon.g() != null && hashSet.contains(innerActionCommon.g())) {
                            arrayList.add(baseInnerAction);
                        }
                    }
                    i2++;
                }
                this.c.a(arrayList);
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
            while (i2 < this.mSmartHomeScenceActions.size()) {
                BaseInnerAction baseInnerAction2 = this.mSmartHomeScenceActions.get(i2);
                if (baseInnerAction2 instanceof InnerActionCommon) {
                    InnerActionCommon innerActionCommon2 = (InnerActionCommon) baseInnerAction2;
                    if (innerActionCommon2.g() != null && hashSet2.contains(innerActionCommon2.g().did)) {
                        arrayList2.add(baseInnerAction2);
                    }
                }
                i2++;
            }
            this.c.a(arrayList2);
        }
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
        ArrayList arrayList2 = new ArrayList();
        arrayList2.addAll(a(true));
        int i2 = 0;
        while (true) {
            if (i2 < (this.mSmartHomeScenceActions.isEmpty() ? 0 : this.mSmartHomeScenceActions.size())) {
                if (this.mSmartHomeScenceActions.get(i2) instanceof InnerActionCommon) {
                    arrayList2.add(this.mSmartHomeScenceActions.get(i2));
                }
                i2++;
            } else {
                Collections.sort(arrayList2, new BaseInnerActionComparator(room, arrayList2));
                this.mSmartHomeScenceActions.clear();
                this.mSmartHomeScenceActions.addAll(arrayList2);
                this.c.a(this.mSmartHomeScenceActions);
                return;
            }
        }
    }

    private List<BaseInnerAction> a(boolean z) {
        boolean z2;
        ArrayList arrayList = new ArrayList();
        if (z) {
            arrayList.add(this.o);
        }
        if (!CreateSceneManager.a().c(this.mScene.l)) {
            arrayList.add(this.k);
        }
        arrayList.add(this.m);
        Iterator<SceneApi.Action> it = this.mScene.k.iterator();
        while (true) {
            if (!it.hasNext()) {
                z2 = true;
                break;
            }
            SceneApi.Action next = it.next();
            if (next.g != null && (next.g instanceof SceneApi.SHScenePushPayload)) {
                z2 = false;
                break;
            }
        }
        if (z2) {
            arrayList.add(this.n);
        }
        if (this.mScene.k.size() == 0 || !(this.mScene.k.get(this.mScene.k.size() - 1).g instanceof SceneApi.SHSceneDelayPayload)) {
            arrayList.add(this.l);
        }
        if (z) {
            arrayList.add(this.p);
        }
        if (this.s) {
            arrayList.add(this.q);
            this.d = arrayList.size() - 2;
        } else {
            this.d = -1;
        }
        return arrayList;
    }

    private List<BaseInnerAction> b(boolean z) {
        Set<String> a2 = SceneFilterManager.c().a();
        String str = SceneFilterManager.c().d().c;
        if (SceneFilterManager.c().d().b == 0) {
            this.mTempActions.clear();
            this.mTempActions.addAll(this.mSmartHomeScenceActions);
            return this.mTempActions;
        } else if (!z || SceneFilterManager.c().a(SceneFilterManager.c().d().a(), 1, str)) {
            this.mTempActions.clear();
            for (int i2 = 0; i2 < this.mSmartHomeScenceActions.size(); i2++) {
                BaseInnerAction baseInnerAction = this.mSmartHomeScenceActions.get(i2);
                if (baseInnerAction instanceof InnerActionCommon) {
                    CommonSceneOnline h2 = ((InnerActionCommon) baseInnerAction).h();
                    if (h2 != null) {
                        Iterator<String> it = a2.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            if (h2.b.equalsIgnoreCase(it.next())) {
                                this.mTempActions.add(baseInnerAction);
                                break;
                            }
                        }
                    }
                } else {
                    this.mTempActions.add(baseInnerAction);
                }
            }
            Collections.reverse(this.mTempActions);
            return this.mTempActions;
        } else {
            this.mTempActions.clear();
            this.mTempActions.addAll(this.mSmartHomeScenceActions);
            SceneFilterManager.c().g();
            return this.mTempActions;
        }
    }

    /* access modifiers changed from: package-private */
    public void gotoDetailPage(BaseInnerAction baseInnerAction, Device device) {
        Intent intent = new Intent();
        intent.setClass(this, SmartHomeSceneDetailActivity.class);
        if (baseInnerAction instanceof LocaleGetResourceFixHelper) {
            try {
                intent.putExtra("extra_title", getResources().getString(((LocaleGetResourceFixHelper) baseInnerAction).a()));
            } catch (Exception unused) {
                intent.putExtra("extra_title", baseInnerAction.a((Object) device));
            }
        } else {
            intent.putExtra("extra_title", baseInnerAction.a((Object) device));
        }
        intent.putExtra("extra_selected_title", this.g);
        startActivityForResult(intent, 101);
        CreateSceneManager.a().a(baseInnerAction);
        this.mCurrentScenceAction = baseInnerAction;
        this.mCurrentDevice = device;
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (i3 == -1 && i2 == 101) {
            this.mHandler.post(new Runnable(intent.getIntExtra("extra_index", -1), intent.getIntExtra(AutoSceneActionDetailChooseActivity.EXTRA_ENABEL, -1)) {
                private final /* synthetic */ int f$1;
                private final /* synthetic */ int f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    SmartHomeSceneActionChooseActivity.this.a(this.f$1, this.f$2);
                }
            });
        } else if (i3 == -1 && this.mRequestId >= 0) {
            SceneApi.Action a2 = this.mCurrentScenceAction.a(this.mCurrentScenceAction.b()[this.mRequestId], this.mCurrentScenceAction.c()[this.mRequestId], this.mCurrentDevice, intent);
            addScence(a2);
            CreateSceneManager.a().a(a2);
            if (a2.d != 0) {
                addCompatibleId(a2.d);
            }
            finish();
        }
        if (i3 == 0 && this.mSelectedAction != null && this.i != -1) {
            this.mScene.k.add(this.i, this.mSelectedAction);
            if (this.mSelectedAction.d != 0) {
                addCompatibleId(this.mSelectedAction.d);
            }
            finish();
        }
    }

    public void onActivityResult(final int i2, final Intent intent) {
        this.mHandler.post(new Runnable() {
            public void run() {
                if (i2 != -1) {
                    if (SmartHomeSceneActionChooseActivity.this.g != -1) {
                        SmartHomeSceneActionChooseActivity.this.mScene.k.add(SmartHomeSceneActionChooseActivity.this.i, SmartHomeSceneActionChooseActivity.this.mSelectedAction);
                        if (SmartHomeSceneActionChooseActivity.this.mSelectedAction.d != 0) {
                            SmartHomeSceneActionChooseActivity.this.addCompatibleId(SmartHomeSceneActionChooseActivity.this.mSelectedAction.d);
                        }
                        SmartHomeSceneActionChooseActivity.this.finish();
                    }
                    SmartHomeSceneActionChooseActivity.this.mRequestId = 0;
                    SmartHomeSceneActionChooseActivity.this.mCurrentScenceAction.b(-1);
                    SmartHomeSceneActionChooseActivity.this.mCurrentDevice = null;
                    SmartHomeSceneActionChooseActivity.this.c.notifyDataSetChanged();
                } else if (SmartHomeSceneActionChooseActivity.this.mCurrentDevice != null && SmartHomeSceneActionChooseActivity.this.mRequestId < SmartHomeSceneActionChooseActivity.this.mCurrentScenceAction.b().length && SmartHomeSceneActionChooseActivity.this.mRequestId < SmartHomeSceneActionChooseActivity.this.mCurrentScenceAction.c().length) {
                    SceneApi.Action a2 = SmartHomeSceneActionChooseActivity.this.mCurrentScenceAction.a(SmartHomeSceneActionChooseActivity.this.mCurrentScenceAction.b()[SmartHomeSceneActionChooseActivity.this.mRequestId], SmartHomeSceneActionChooseActivity.this.mCurrentScenceAction.c()[SmartHomeSceneActionChooseActivity.this.mRequestId], SmartHomeSceneActionChooseActivity.this.mCurrentDevice, intent);
                    SmartHomeSceneActionChooseActivity.this.addScence(a2);
                    SmartHomeSceneActionChooseActivity.this.addCompatibleId(a2.d);
                    SmartHomeSceneActionChooseActivity.this.finish();
                }
            }
        });
    }

    @OnClick({2131430969})
    public void close() {
        if (SmarthomeCreateAutoSceneActivity.mIsInitStep) {
            CreateSceneManager.a().b = 4;
        }
        finish();
    }

    private class ActionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private List<BaseInnerAction> b = new ArrayList();
        private LayoutInflater c;

        public long getItemId(int i) {
            return (long) i;
        }

        public ActionAdapter() {
            this.c = SmartHomeSceneActionChooseActivity.this.getLayoutInflater();
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
                BaseInnerAction baseInnerAction = this.b.get(i);
                switch (itemViewType) {
                    case 0:
                        if ((viewHolder instanceof FilterViewHolder) && (baseInnerAction instanceof DeviceActionFilter)) {
                            ((FilterViewHolder) viewHolder).a((DeviceActionFilter) baseInnerAction);
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
                            if (baseInnerAction instanceof LiteSceneAction) {
                                ((CommonSelectionViewHolder) viewHolder).a(baseInnerAction, z);
                                return;
                            } else if (baseInnerAction instanceof DelayInnerAction) {
                                ((CommonSelectionViewHolder) viewHolder).a(baseInnerAction, z);
                                return;
                            } else if (baseInnerAction instanceof AutoSceneAction) {
                                ((CommonSelectionViewHolder) viewHolder).a(baseInnerAction, z);
                                return;
                            } else if (baseInnerAction instanceof PushInnerAction) {
                                ((CommonSelectionViewHolder) viewHolder).a(baseInnerAction, z);
                                return;
                            } else {
                                return;
                            }
                        } else {
                            return;
                        }
                    case 3:
                        if ((viewHolder instanceof DeviceSelectionViewHolder) && (baseInnerAction instanceof InnerActionCommon)) {
                            ((DeviceSelectionViewHolder) viewHolder).a((InnerActionCommon) baseInnerAction, i);
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
                if (this.b.get(i) instanceof CommonTextAction) {
                    return 1;
                }
                if (this.b.get(i) instanceof IntelligentTextAction) {
                    return 4;
                }
                if (this.b.get(i) instanceof DeviceActionFilter) {
                    return 0;
                }
                if (this.b.get(i) instanceof InnerActionCommon) {
                    return 3;
                }
                if ((this.b.get(i) instanceof LiteSceneAction) || (this.b.get(i) instanceof AutoSceneAction) || (this.b.get(i) instanceof PushInnerAction) || (this.b.get(i) instanceof DelayInnerAction)) {
                    return 2;
                }
            }
            return super.getItemViewType(i);
        }

        public void a(List<BaseInnerAction> list) {
            this.b.clear();
            if (!list.isEmpty()) {
                this.b.addAll(list);
            }
            SmartHomeSceneActionChooseActivity.this.mContentListView.stopScroll();
            SmartHomeSceneActionChooseActivity.this.mContentListView.getRecycledViewPool().clear();
            notifyDataSetChanged();
        }
    }

    class TextTitleViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f21362a = 1;

        public TextTitleViewHolder(View view) {
            super(view);
        }
    }

    class TextTitle4DeviceViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f21361a = 4;

        public TextTitle4DeviceViewHolder(View view) {
            super(view);
        }
    }

    class FilterViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f21360a = 0;
        View b;
        TextView c;

        public FilterViewHolder(View view) {
            super(view);
            this.b = view.findViewById(R.id.tv_group_filter);
            this.c = (TextView) view.findViewById(R.id.filter_name);
            this.b.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    SmartHomeSceneActionChooseActivity.FilterViewHolder.this.a(view);
                }
            });
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(View view) {
            SmartHomeSceneActionChooseActivity.this.filter.c();
        }

        public void a(DeviceActionFilter deviceActionFilter) {
            if (deviceActionFilter != null) {
                String f = deviceActionFilter.f();
                TextView textView = this.c;
                if (TextUtils.isEmpty(f)) {
                    f = "";
                }
                textView.setText(f);
            }
        }
    }

    class CommonSelectionViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f21357a = 2;
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

        public void a(BaseInnerAction baseInnerAction, boolean z) {
            if (baseInnerAction != null) {
                this.e.setVisibility(z ? 0 : 8);
                String string = baseInnerAction instanceof LocaleGetResourceFixHelper ? SmartHomeSceneActionChooseActivity.this.getString(((LocaleGetResourceFixHelper) baseInnerAction).a()) : baseInnerAction.a((Object) null);
                TextView textView = this.b;
                if (TextUtils.isEmpty(string)) {
                    string = "";
                }
                textView.setText(string);
                SmartHomeSceneActionChooseActivity.this.a(baseInnerAction, this.c);
                this.itemView.setOnClickListener(new View.OnClickListener(baseInnerAction) {
                    private final /* synthetic */ BaseInnerAction f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void onClick(View view) {
                        SmartHomeSceneActionChooseActivity.CommonSelectionViewHolder.this.a(this.f$1, view);
                    }
                });
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(BaseInnerAction baseInnerAction, View view) {
            if (baseInnerAction instanceof PushInnerAction) {
                SmartHomeSceneActionChooseActivity.this.addScence(baseInnerAction.a((String) null, 0, (Object) null, (Intent) null));
                SmartHomeSceneActionChooseActivity.this.finish();
            } else if (baseInnerAction instanceof LiteSceneAction) {
                Intent intent = new Intent();
                intent.setClass(SmartHomeSceneActionChooseActivity.this, LiteAutomationChooseScene.class);
                SmartHomeSceneActionChooseActivity.this.startActivityForResult(intent, 101);
                CreateSceneManager.a().a(baseInnerAction);
                SmartHomeSceneActionChooseActivity.this.mCurrentScenceAction = baseInnerAction;
            } else if (baseInnerAction instanceof AutoSceneAction) {
                Intent intent2 = new Intent();
                intent2.setClass(SmartHomeSceneActionChooseActivity.this, AutoSceneActionChooseActivity.class);
                SmartHomeSceneActionChooseActivity.this.startActivityForResult(intent2, 101);
                CreateSceneManager.a().a(baseInnerAction);
                SmartHomeSceneActionChooseActivity.this.mCurrentScenceAction = baseInnerAction;
            } else if (baseInnerAction instanceof DelayInnerAction) {
                SmartHomeSceneActionChooseActivity.this.mCurrentScenceAction = baseInnerAction;
                SmartHomeSceneActionChooseActivity.this.a(0, -1);
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(BaseInnerAction baseInnerAction, SimpleDraweeView simpleDraweeView) {
        if (simpleDraweeView != null && baseInnerAction != null) {
            simpleDraweeView.setHierarchy(new GenericDraweeHierarchyBuilder(simpleDraweeView.getResources()).setFadeDuration(200).setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER).setPlaceholderImageScaleType(ScalingUtils.ScaleType.FIT_CENTER).build());
            if (baseInnerAction instanceof PushInnerAction) {
                simpleDraweeView.setImageURI(CommonUtils.c((int) R.drawable.std_scene_icon_push));
            } else if (baseInnerAction instanceof LiteSceneAction) {
                simpleDraweeView.setImageURI(CommonUtils.c((int) R.drawable.scene_click_lite_scene_icon));
            } else if (baseInnerAction instanceof AutoSceneAction) {
                simpleDraweeView.setImageURI(CommonUtils.c((int) R.drawable.scene_auto_icon));
            } else if (baseInnerAction instanceof DelayInnerAction) {
                simpleDraweeView.setImageURI(CommonUtils.c((int) R.drawable.std_scene_icon_delayed));
            } else if (baseInnerAction instanceof InnerActionCommon) {
                DeviceFactory.b(((InnerActionCommon) baseInnerAction).g().model, simpleDraweeView);
            }
        }
    }

    class DeviceSelectionViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f21358a = 3;
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

        public void a(final InnerActionCommon innerActionCommon, int i) {
            if (innerActionCommon != null) {
                final boolean z = false;
                this.f.setVisibility(0);
                final String str = null;
                Device n = SmartHomeDeviceManager.a().n(innerActionCommon.g().did);
                if (n == null) {
                    this.f.setText(R.string.samrthome_scene_device_off_line);
                } else if (CreateSceneManager.a().b(n.did)) {
                    this.f.setText(R.string.scene_has_selected);
                    z = true;
                    str = n.did;
                } else if (!n.isOnline) {
                    this.f.setText(R.string.samrthome_scene_device_off_line);
                } else {
                    this.f.setVisibility(8);
                }
                this.c.setText(SmartHomeSceneActionChooseActivity.this.a(innerActionCommon));
                SmartHomeSceneActionChooseActivity.this.a((BaseInnerAction) innerActionCommon, this.d);
                this.b.setText(innerActionCommon.a(innerActionCommon.g()));
                if (SmartHomeSceneActionChooseActivity.this.a(innerActionCommon, this.e)) {
                    this.itemView.setOnClickListener(new View.OnClickListener() {
                        public final void onClick(View view) {
                            SmartHomeSceneActionChooseActivity.DeviceSelectionViewHolder.this.a(view);
                        }
                    });
                } else {
                    this.itemView.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            Intent intent = new Intent();
                            intent.setClass(SmartHomeSceneActionChooseActivity.this, SmartHomeSceneDetailActivity.class);
                            intent.putExtra("extra_title", innerActionCommon.a(innerActionCommon.g()));
                            if (z) {
                                intent.putExtra(SmartHomeSceneDetailActivity.EXTRA_SELECTED_DID, str);
                            }
                            SmartHomeSceneActionChooseActivity.this.startActivityForResult(intent, 101);
                            CreateSceneManager.a().a((BaseInnerAction) innerActionCommon);
                            SmartHomeSceneActionChooseActivity.this.mCurrentScenceAction = innerActionCommon;
                            SmartHomeSceneActionChooseActivity.this.mCurrentDevice = innerActionCommon.g();
                            CreateSceneManager.a().a(HomeManager.a().p(innerActionCommon.g().did));
                        }
                    });
                }
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(View view) {
            Toast.makeText(SmartHomeSceneActionChooseActivity.this, R.string.scene_unclickable_toast_2, 0).show();
        }
    }

    /* access modifiers changed from: private */
    public boolean a(InnerActionCommon innerActionCommon, ImageView imageView) {
        if (imageView == null) {
            return false;
        }
        if (!this.j.containsKey(innerActionCommon) || this.j.get(innerActionCommon).booleanValue()) {
            imageView.setImageResource(R.drawable.std_list_main_next);
            return false;
        }
        imageView.setImageResource(R.drawable.std_scene_icon_lock);
        return true;
    }

    /* access modifiers changed from: private */
    public String a(InnerActionCommon innerActionCommon) {
        String string = getString(R.string.room_default);
        if (HomeVirtualDeviceHelper.a(innerActionCommon.g().model)) {
            return "";
        }
        Room p2 = (innerActionCommon == null || innerActionCommon.g() == null) ? null : HomeManager.a().p(innerActionCommon.g().did);
        return p2 != null ? p2.e() : string;
    }

    public void onResume() {
        super.onResume();
    }

    public void addScence(SceneApi.Action action) {
        for (int size = this.mScene.k.size() - 1; size >= 0; size--) {
            if ((this.mScene.k.get(size).g instanceof SceneApi.SHScenePushPayload) && (action.g instanceof SceneApi.SHScenePushPayload)) {
                this.mScene.k.remove(this.mScene.k.get(size));
            }
        }
        if (this.i != -1) {
            this.mScene.k.add(this.i, action);
        } else {
            this.mScene.k.add(action);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0037, code lost:
        r0 = ((com.xiaomi.smarthome.scene.action.InnerActionCommon) r10.mCurrentScenceAction).h();
     */
    /* renamed from: getActionItem */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(int r11, int r12) {
        /*
            r10 = this;
            com.xiaomi.smarthome.scene.action.BaseInnerAction r0 = r10.mCurrentScenceAction
            if (r0 != 0) goto L_0x0005
            return
        L_0x0005:
            if (r11 >= 0) goto L_0x0008
            return
        L_0x0008:
            com.xiaomi.smarthome.scene.action.BaseInnerAction r0 = r10.mCurrentScenceAction
            java.lang.String[] r0 = r0.b()
            if (r0 == 0) goto L_0x001a
            com.xiaomi.smarthome.scene.action.BaseInnerAction r0 = r10.mCurrentScenceAction
            java.lang.String[] r0 = r0.b()
            int r0 = r0.length
            if (r0 > r11) goto L_0x001a
            return
        L_0x001a:
            com.xiaomi.smarthome.scene.action.BaseInnerAction r0 = r10.mCurrentScenceAction
            int[] r0 = r0.c()
            if (r0 == 0) goto L_0x002c
            com.xiaomi.smarthome.scene.action.BaseInnerAction r0 = r10.mCurrentScenceAction
            int[] r0 = r0.c()
            int r0 = r0.length
            if (r0 > r11) goto L_0x002c
            return
        L_0x002c:
            com.xiaomi.smarthome.scene.api.SceneApi$Action r0 = r10.mSelectedAction
            r1 = 0
            if (r0 == 0) goto L_0x005f
            com.xiaomi.smarthome.scene.action.BaseInnerAction r0 = r10.mCurrentScenceAction
            boolean r0 = r0 instanceof com.xiaomi.smarthome.scene.action.InnerActionCommon
            if (r0 == 0) goto L_0x005f
            com.xiaomi.smarthome.scene.action.BaseInnerAction r0 = r10.mCurrentScenceAction
            com.xiaomi.smarthome.scene.action.InnerActionCommon r0 = (com.xiaomi.smarthome.scene.action.InnerActionCommon) r0
            com.xiaomi.smarthome.scene.CommonSceneOnline r0 = r0.h()
            if (r0 == 0) goto L_0x005f
            java.util.ArrayList<com.xiaomi.smarthome.scene.CommonSceneOnline$CommonSceneAction> r0 = r0.f
            if (r0 == 0) goto L_0x005f
            com.xiaomi.smarthome.scene.api.SceneApi$Action r0 = r10.mSelectedAction
            int r0 = r0.f
            com.xiaomi.smarthome.scene.action.BaseInnerAction r2 = r10.mCurrentScenceAction
            com.xiaomi.smarthome.scene.action.InnerActionCommon r2 = (com.xiaomi.smarthome.scene.action.InnerActionCommon) r2
            com.xiaomi.smarthome.scene.CommonSceneOnline r2 = r2.h()
            java.util.ArrayList<com.xiaomi.smarthome.scene.CommonSceneOnline$CommonSceneAction> r2 = r2.f
            java.lang.Object r2 = r2.get(r11)
            com.xiaomi.smarthome.scene.CommonSceneOnline$CommonSceneAction r2 = (com.xiaomi.smarthome.scene.CommonSceneOnline.CommonSceneAction) r2
            int r2 = r2.b
            if (r0 != r2) goto L_0x005f
            r0 = 1
            goto L_0x0060
        L_0x005f:
            r0 = 0
        L_0x0060:
            com.xiaomi.smarthome.scene.action.BaseInnerAction r2 = r10.mCurrentScenceAction
            com.xiaomi.smarthome.scene.SmartHomeSceneActionChooseActivity$7 r3 = new com.xiaomi.smarthome.scene.SmartHomeSceneActionChooseActivity$7
            r3.<init>()
            android.content.Context r4 = r10.getContext()
            com.xiaomi.smarthome.scene.action.BaseInnerAction r5 = r10.mCurrentScenceAction
            java.lang.String[] r5 = r5.b()
            r9 = 0
            if (r5 != 0) goto L_0x0076
            r5 = r9
            goto L_0x007e
        L_0x0076:
            com.xiaomi.smarthome.scene.action.BaseInnerAction r5 = r10.mCurrentScenceAction
            java.lang.String[] r5 = r5.b()
            r5 = r5[r11]
        L_0x007e:
            com.xiaomi.smarthome.scene.action.BaseInnerAction r6 = r10.mCurrentScenceAction
            int[] r6 = r6.c()
            r6 = r6[r11]
            java.lang.Object r7 = r10.mCurrentDevice
            if (r0 == 0) goto L_0x0097
            com.xiaomi.smarthome.scene.api.SceneApi$Action r0 = r10.mSelectedAction
            if (r0 != 0) goto L_0x008f
            goto L_0x0097
        L_0x008f:
            com.xiaomi.smarthome.scene.api.SceneApi$Action r0 = r10.mSelectedAction
            com.xiaomi.smarthome.scene.api.SceneApi$SHSceneItemPayload r0 = r0.g
            java.lang.Object r0 = r0.f
            r8 = r0
            goto L_0x0098
        L_0x0097:
            r8 = r9
        L_0x0098:
            int r0 = r2.a(r3, r4, r5, r6, r7, r8)
            if (r0 < 0) goto L_0x00a2
            r10.mRequestId = r11
            goto L_0x013e
        L_0x00a2:
            r2 = -1
            if (r0 != r2) goto L_0x00ac
            com.xiaomi.smarthome.scene.SmartHomeSceneActionChooseActivity$ActionAdapter r11 = r10.c
            r11.notifyDataSetChanged()
            goto L_0x013e
        L_0x00ac:
            r3 = -99
            if (r0 != r3) goto L_0x00e2
            int r11 = r10.g
            if (r11 == r2) goto L_0x00dc
            com.xiaomi.smarthome.scene.api.SceneApi$Action r11 = r10.mSelectedAction
            if (r11 == 0) goto L_0x00d8
            int r11 = r10.i
            if (r11 != r2) goto L_0x00c6
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r11 = r10.mScene
            java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Action> r11 = r11.k
            com.xiaomi.smarthome.scene.api.SceneApi$Action r12 = r10.mSelectedAction
            r11.add(r1, r12)
            goto L_0x00d1
        L_0x00c6:
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r11 = r10.mScene
            java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Action> r11 = r11.k
            int r12 = r10.i
            com.xiaomi.smarthome.scene.api.SceneApi$Action r0 = r10.mSelectedAction
            r11.add(r12, r0)
        L_0x00d1:
            com.xiaomi.smarthome.scene.api.SceneApi$Action r11 = r10.mSelectedAction
            int r11 = r11.d
            r10.addCompatibleId(r11)
        L_0x00d8:
            r10.finish()
            goto L_0x013e
        L_0x00dc:
            com.xiaomi.smarthome.scene.SmartHomeSceneActionChooseActivity$ActionAdapter r11 = r10.c
            r11.notifyDataSetChanged()
            goto L_0x013e
        L_0x00e2:
            r1 = -3
            if (r0 != r1) goto L_0x0117
            android.content.Intent r0 = new android.content.Intent
            r0.<init>()
            java.lang.String r1 = "enable"
            r0.putExtra(r1, r12)
            com.xiaomi.smarthome.scene.action.BaseInnerAction r12 = r10.mCurrentScenceAction
            com.xiaomi.smarthome.scene.action.BaseInnerAction r1 = r10.mCurrentScenceAction
            java.lang.String[] r1 = r1.b()
            r1 = r1[r11]
            com.xiaomi.smarthome.scene.action.BaseInnerAction r2 = r10.mCurrentScenceAction
            int[] r2 = r2.c()
            r11 = r2[r11]
            java.lang.Object r2 = r10.mCurrentDevice
            com.xiaomi.smarthome.scene.api.SceneApi$Action r11 = r12.a(r1, r11, r2, r0)
            r10.addScence(r11)
            int r12 = r11.d
            if (r12 == 0) goto L_0x0113
            int r11 = r11.d
            r10.addCompatibleId(r11)
        L_0x0113:
            r10.finish()
            goto L_0x013e
        L_0x0117:
            com.xiaomi.smarthome.scene.action.BaseInnerAction r12 = r10.mCurrentScenceAction
            com.xiaomi.smarthome.scene.action.BaseInnerAction r0 = r10.mCurrentScenceAction
            java.lang.String[] r0 = r0.b()
            r0 = r0[r11]
            com.xiaomi.smarthome.scene.action.BaseInnerAction r1 = r10.mCurrentScenceAction
            int[] r1 = r1.c()
            r11 = r1[r11]
            java.lang.Object r1 = r10.mCurrentDevice
            com.xiaomi.smarthome.scene.api.SceneApi$Action r11 = r12.a(r0, r11, r1, r9)
            r10.addScence(r11)
            int r12 = r11.d
            if (r12 == 0) goto L_0x013b
            int r11 = r11.d
            r10.addCompatibleId(r11)
        L_0x013b:
            r10.finish()
        L_0x013e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.scene.SmartHomeSceneActionChooseActivity.a(int, int):void");
    }

    public void addCompatibleId(int i2) {
        if (CreateSceneManager.a().b(Integer.valueOf(i2))) {
            CreateSceneManager.a().a(SceneManager.x().a(i2));
            CreateSceneManager.a().b(SceneManager.x().b(i2));
            return;
        }
        CreateSceneManager.a().e();
    }

    public void onBackPressed() {
        if (SmarthomeCreateAutoSceneActivity.mIsInitStep) {
            CreateSceneManager.a().b = 4;
        }
        super.onBackPressed();
    }

    private class BaseInnerActionComparator implements Comparator<BaseInnerAction> {
        private Map<String, Integer> b = new HashMap();
        private Map<String, Integer> c = new HashMap();

        public BaseInnerActionComparator(Room room, List<BaseInnerAction> list) {
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
                    if (list.get(i2) instanceof LiteSceneAction) {
                        this.c.put(LiteSceneAction.class.getName(), Integer.valueOf(i2));
                    } else if (list.get(i2) instanceof AutoSceneAction) {
                        this.c.put(AutoSceneAction.class.getName(), Integer.valueOf(i2));
                    } else if (list.get(i2) instanceof PushInnerAction) {
                        this.c.put(PushInnerAction.class.getName(), Integer.valueOf(i2));
                    } else if (list.get(i2) instanceof DelayInnerAction) {
                        this.c.put(DelayInnerAction.class.getName(), Integer.valueOf(i2));
                    } else if (list.get(i2) instanceof IntelligentTextAction) {
                        this.c.put(IntelligentTextAction.class.getName(), Integer.valueOf(i2));
                    } else if (list.get(i2) instanceof DeviceActionFilter) {
                        this.c.put(DeviceActionFilter.class.getName(), Integer.valueOf(i2));
                    } else if ((list.get(i2) instanceof InnerActionCommon) && ((InnerActionCommon) list.get(i2)).g() != null) {
                        this.c.put(((InnerActionCommon) list.get(i2)).g().did, Integer.valueOf(i2));
                    }
                    i2++;
                } else {
                    return;
                }
            }
        }

        /* renamed from: a */
        public int compare(BaseInnerAction baseInnerAction, BaseInnerAction baseInnerAction2) {
            boolean z = baseInnerAction instanceof InnerActionCommon;
            if (z && (baseInnerAction2 instanceof InnerActionCommon)) {
                Device g = ((InnerActionCommon) baseInnerAction).g();
                Device g2 = ((InnerActionCommon) baseInnerAction2).g();
                if (g == null && g2 == null) {
                    return 0;
                }
                if (g != null && g2 != null) {
                    int a2 = a(g, g2);
                    if (a2 != 0) {
                        return a2;
                    }
                    Room p = HomeManager.a().p(g.did);
                    Room p2 = HomeManager.a().p(g2.did);
                    if (p == null && p2 == null) {
                        if (HomeVirtualDeviceHelper.a(g.model) && !HomeVirtualDeviceHelper.a(g2.model)) {
                            return 1;
                        }
                        if (HomeVirtualDeviceHelper.a(g.model) || !HomeVirtualDeviceHelper.a(g2.model)) {
                            return b(g, g2);
                        }
                        return -1;
                    } else if (p != null && p2 != null) {
                        int a3 = a(p, p2);
                        if (a3 != 0) {
                            return a3;
                        }
                        return b(g, g2);
                    } else if (p == null) {
                        return 1;
                    } else {
                        return -1;
                    }
                } else if (g == null) {
                    return 1;
                } else {
                    return -1;
                }
            } else if (z || (baseInnerAction2 instanceof InnerActionCommon)) {
                if (z) {
                    return 1;
                }
                return -1;
            } else if (this.c.get(baseInnerAction.getClass().getName()) == null || this.c.get(baseInnerAction2.getClass().getName()) == null) {
                return this.c.get(baseInnerAction.getClass().getName()) != null ? 1 : 0;
            } else {
                return this.c.get(baseInnerAction.getClass().getName()).intValue() - this.c.get(baseInnerAction2.getClass().getName()).intValue();
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
