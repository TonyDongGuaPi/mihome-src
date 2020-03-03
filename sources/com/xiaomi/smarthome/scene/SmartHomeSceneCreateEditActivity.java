package com.xiaomi.smarthome.scene;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.taobao.weex.el.parse.Operators;
import com.umeng.analytics.MobclickAgent;
import com.xiaomi.router.api.SceneManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.SmartHomeConfig;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.config.SHConfig;
import com.xiaomi.smarthome.core.entity.statistic.StatType;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.RecommendSceneItem;
import com.xiaomi.smarthome.device.api.SceneInfo;
import com.xiaomi.smarthome.device.bluetooth.utils.BluetoothConstants;
import com.xiaomi.smarthome.device.utils.ClientRemarkInputView;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.plugin.PluginApi;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.plugin.mpk.MpkPluginApi;
import com.xiaomi.smarthome.framework.statistic.MiStatType;
import com.xiaomi.smarthome.framework.statistic.StatHelper;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.util.StringUtil;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.widget.SwitchButton;
import com.xiaomi.smarthome.lite.scene.LiteSceneManager;
import com.xiaomi.smarthome.lite.scene.SceneLogUtil;
import com.xiaomi.smarthome.scene.SmartHomeSceneUtility;
import com.xiaomi.smarthome.scene.api.RemoteSceneApi;
import com.xiaomi.smarthome.scene.api.SceneApi;
import com.xiaomi.youpin.app_sdk.url_dispatch.UrlDispatchManger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

public class SmartHomeSceneCreateEditActivity extends BaseActivity {
    public static final String EXTRA_ADD_ALL_DEFAULT_ACTION = "extra_default_all_action";
    public static final String EXTRA_ADD_ALL_DEFAULT_CONDITION = "extra_default_all_condition";
    public static final String EXTRA_DEFAULT_ACTION_ITMES = "extra_default_action_items";
    public static final String EXTRA_DEFAULT_CONDITION_ITMES = "extra_default_condition_items";
    public static final String EXTRA_DEFAULT_SCENE_NAME = "extra_default_scene_name";
    public static final String EXTRA_RECOMMEND_SCENE_ID = "extra_recommend_scene_id";
    public static final String EXTRA_SCENE_ID = "extra_scene_id";
    public static final String EXTRA_SCENE_STAT_FROM = "scene_stat_from";
    public static final int REQUEST_CODE_CONDITION = 1;
    public static final int REQUEST_CODE_TASK = 2;
    public static final int SCENE_MORE_PAGE = 1000;

    /* renamed from: a  reason: collision with root package name */
    private static final int f21365a = 100;
    private static final int b = 101;
    public static boolean isNewScene;
    public static SceneApi.SmartHomeScene mScene;
    private boolean c = false;
    /* access modifiers changed from: private */
    public ArrayList<DefaultSceneItemSet> d = new ArrayList<>();
    MLAlertDialog dialog = null;
    /* access modifiers changed from: private */
    public ArrayList<DefaultSceneItemSet> e = new ArrayList<>();
    private boolean f = false;
    /* access modifiers changed from: private */
    public ArrayList<DefaultSceneItemSet> g = new ArrayList<>();
    /* access modifiers changed from: private */
    public ArrayList<DefaultSceneItemSet> h = new ArrayList<>();
    /* access modifiers changed from: private */
    public DefaultSceneItemSet i = null;
    /* access modifiers changed from: private */
    public DefaultSceneItemSet j = null;
    private String k;
    private MLAlertDialog l;
    /* access modifiers changed from: private */
    public XQProgressDialog m;
    BaseAdapter mActionAdapter;
    @BindView(2131431289)
    SwitchButton mCheckBox;
    BaseAdapter mConditionAdapter;
    @BindView(2131428508)
    ListView mConditionListView;
    boolean[] mConditionOnlineItems;
    @BindView(2131428510)
    TextView mConditionTitle;
    @BindView(2131430989)
    Button mConfirmBtn;
    @BindView(2131432527)
    LinearLayout mContainer;
    Context mContext;
    @BindView(2131428901)
    View mDivider1;
    @BindView(2131428902)
    View mDivider2;
    @BindView(2131428903)
    View mDivider3;
    @BindView(2131428904)
    View mDivider4;
    @BindView(2131432143)
    RelativeLayout mExeTimeRL;
    @BindView(2131432144)
    TextView mExeTimeTV;
    @BindView(2131429122)
    TextView mExpressAll;
    @BindView(2131429123)
    TextView mExpressAny;
    @BindView(2131432528)
    View mExpressView;
    Handler mHandler = new Handler() {
        public void handleMessage(Message message) {
            switch (message.what) {
                case 100:
                    SmartHomeSceneCreateEditActivity.this.startSaveScene((String) null);
                    return;
                case 101:
                    SmartHomeSceneCreateEditActivity.this.onFinishSaved(false);
                    return;
                default:
                    return;
            }
        }
    };
    SmartHomeDeviceManager.IClientDeviceListener mIClientDeviceListener = new SmartHomeDeviceManager.IClientDeviceListener() {
        public void a(int i, Device device) {
        }

        public void a(int i) {
            if (i == 3) {
                SmartHomeSceneCreateEditActivity.this.initOnlineAndAvatarResource();
                if (SmartHomeSceneCreateEditActivity.this.mConditionAdapter != null) {
                    SmartHomeSceneCreateEditActivity.this.mConditionAdapter.notifyDataSetChanged();
                    SmartHomeSceneCreateEditActivity.this.mActionAdapter.notifyDataSetChanged();
                }
            }
        }

        public void b(int i) {
            if (i == 3) {
                SmartHomeSceneCreateEditActivity.this.mConditionAdapter.notifyDataSetChanged();
                SmartHomeSceneCreateEditActivity.this.mActionAdapter.notifyDataSetChanged();
            }
        }
    };
    @BindView(2131427670)
    View mLockView;
    @BindView(2131430988)
    TextView mModuleA4Commit;
    @BindView(2131430990)
    ImageView mModuleA4ReturnBtn;
    @BindView(2131430997)
    TextView mModuleA4ReturnTitle;
    @BindView(2131431194)
    TextView mNoActionTitle;
    @BindView(2131431196)
    TextView mNoConditionTitle;
    boolean[] mOnlineItems;
    int[] mResource;
    String mSceneID;
    SceneApi.SmartHomeScene mSceneSrc;
    @BindView(2131432168)
    ScrollView mScrollVIew;
    @BindView(2131431295)
    View mSwitchContainer;
    @BindView(2131432752)
    View mTaskLayout;
    @BindView(2131432753)
    ListView mTaskListView;
    @BindView(2131432919)
    View mTitleBar;
    @BindView(2131433850)
    View mViewTag;
    private TextView n;
    private int o = -1;
    private List<DelayInfo> p;
    /* access modifiers changed from: private */
    public boolean q = false;
    private SceneApi.ConditionDevice r = null;
    private boolean s = false;

    public static class DelayInfo {

        /* renamed from: a  reason: collision with root package name */
        public int f21428a = -1;
        public SceneApi.Action b;
    }

    public class ActionsAdapter_ViewBinding implements Unbinder {

        /* renamed from: a  reason: collision with root package name */
        private ActionsAdapter f21409a;

        @UiThread
        public ActionsAdapter_ViewBinding(ActionsAdapter actionsAdapter, View view) {
            this.f21409a = actionsAdapter;
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
            ActionsAdapter actionsAdapter = this.f21409a;
            if (actionsAdapter != null) {
                this.f21409a = null;
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
        private ConditionsAdapter f21426a;

        @UiThread
        public ConditionsAdapter_ViewBinding(ConditionsAdapter conditionsAdapter, View view) {
            this.f21426a = conditionsAdapter;
            conditionsAdapter.mIcon = (SimpleDraweeView) Utils.findRequiredViewAsType(view, R.id.icon, "field 'mIcon'", SimpleDraweeView.class);
            conditionsAdapter.mName = (TextView) Utils.findRequiredViewAsType(view, R.id.name, "field 'mName'", TextView.class);
            conditionsAdapter.mKeyName = (TextView) Utils.findRequiredViewAsType(view, R.id.key_name, "field 'mKeyName'", TextView.class);
            conditionsAdapter.mTimeSpan = (TextView) Utils.findRequiredViewAsType(view, R.id.add_timesp, "field 'mTimeSpan'", TextView.class);
            conditionsAdapter.mTimeSetButton = (ImageView) Utils.findRequiredViewAsType(view, R.id.set_time_btn, "field 'mTimeSetButton'", ImageView.class);
            conditionsAdapter.mOffline = (TextView) Utils.findRequiredViewAsType(view, R.id.offline, "field 'mOffline'", TextView.class);
            conditionsAdapter.mBuyButton = (TextView) Utils.findRequiredViewAsType(view, R.id.buy_button, "field 'mBuyButton'", TextView.class);
            conditionsAdapter.mExpandHint = (ImageView) Utils.findRequiredViewAsType(view, R.id.expand_hint, "field 'mExpandHint'", ImageView.class);
            conditionsAdapter.mRunTV = (TextView) Utils.findRequiredViewAsType(view, R.id.right_view_btn, "field 'mRunTV'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            ConditionsAdapter conditionsAdapter = this.f21426a;
            if (conditionsAdapter != null) {
                this.f21426a = null;
                conditionsAdapter.mIcon = null;
                conditionsAdapter.mName = null;
                conditionsAdapter.mKeyName = null;
                conditionsAdapter.mTimeSpan = null;
                conditionsAdapter.mTimeSetButton = null;
                conditionsAdapter.mOffline = null;
                conditionsAdapter.mBuyButton = null;
                conditionsAdapter.mExpandHint = null;
                conditionsAdapter.mRunTV = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public static class DefaultSceneItemSet implements Parcelable {
        public static final Parcelable.Creator<DefaultSceneItemSet> CREATOR = new Parcelable.Creator<DefaultSceneItemSet>() {
            /* renamed from: a */
            public DefaultSceneItemSet createFromParcel(Parcel parcel) {
                return new DefaultSceneItemSet(parcel);
            }

            /* renamed from: a */
            public DefaultSceneItemSet[] newArray(int i) {
                return new DefaultSceneItemSet[i];
            }
        };

        /* renamed from: a  reason: collision with root package name */
        public Boolean f21427a;
        public String[] b;
        public RecommendSceneItem.Key[] c;
        public String d;
        public String e;

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeValue(this.f21427a);
            parcel.writeStringArray(this.b);
            if (this.c != null) {
                parcel.writeInt(this.c.length);
                for (RecommendSceneItem.Key writeToParcel : this.c) {
                    writeToParcel.writeToParcel(parcel);
                }
            } else {
                parcel.writeInt(0);
            }
            parcel.writeString(this.d);
            parcel.writeString(this.e);
        }

        public DefaultSceneItemSet() {
        }

        public DefaultSceneItemSet(Parcel parcel) {
            this.f21427a = (Boolean) parcel.readValue(ClassLoader.getSystemClassLoader());
            if (this.f21427a == null) {
                this.f21427a = false;
            }
            this.b = parcel.createStringArray();
            int readInt = parcel.readInt();
            if (readInt != 0) {
                this.c = new RecommendSceneItem.Key[readInt];
                for (int i = 0; i < readInt; i++) {
                    this.c[i] = new RecommendSceneItem.Key();
                    this.c[i].readFromParcel(parcel);
                }
            }
            this.d = parcel.readString();
            this.e = parcel.readString();
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.smarthome_scene_create_edit);
        ButterKnife.bind((Activity) this);
        this.mContext = this;
        ToastUtil.a((int) R.string.scene_edit_no_support);
        finish();
    }

    /* access modifiers changed from: private */
    public int a() {
        int i2 = 0;
        if (mScene == null) {
            return 0;
        }
        for (SceneApi.Condition next : mScene.l) {
            if (!(next.c == null || next.c.e == -1)) {
                i2++;
                this.r = next.c;
            }
        }
        return i2;
    }

    public void getFrom(Intent intent) {
        if (intent.getExtras() != null) {
            String string = intent.getExtras().getString("scene_stat_from", "");
            if (TextUtils.isEmpty(string)) {
                return;
            }
            if (this.o != -1) {
                CoreApi a2 = CoreApi.a();
                StatType statType = StatType.EVENT;
                a2.a(statType, "value", "" + this.o, (String) null, false);
                return;
            }
            MobclickAgent.a(this.mContext, MiStatType.CLICK.getValue(), string);
        }
    }

    /* access modifiers changed from: package-private */
    public void addLaunchToScene(SceneApi.Condition condition, SceneApi.SmartHomeScene smartHomeScene) {
        if (smartHomeScene.l == null) {
            smartHomeScene.l = new ArrayList();
        }
        smartHomeScene.l.add(condition);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        SmartHomeDeviceManager.a().c(this.mIClientDeviceListener);
        if (this.mHandler != null) {
            this.mHandler.removeCallbacksAndMessages((Object) null);
        }
    }

    private void b() {
        boolean z = true;
        if (!(this.mSceneSrc == null || mScene == null || !mScene.a(this.mSceneSrc))) {
            z = false;
        }
        if (!(this.g == null && this.g == null) && mScene.m) {
            z = false;
        }
        if (z) {
            new MLAlertDialog.Builder(this.mContext).b((int) R.string.smarthome_scene_quit).a((int) R.string.smarthome_scene_quest_ok, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    SmartHomeSceneCreateEditActivity.this.finish();
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

    @OnClick({2131430990})
    public void back() {
        b();
    }

    @OnClick({2131427585})
    public void startConditionForCondition() {
        startCondition();
    }

    @OnClick({2131428507})
    public void startConditionBar() {
        startCondition();
    }

    @OnClick({2131432752})
    public void startTaskBar() {
        startAddAction();
    }

    private SceneApi.Condition c() {
        if (mScene == null || mScene.l == null) {
            return null;
        }
        for (SceneApi.Condition next : mScene.l) {
            if (next.f21522a == SceneApi.Condition.LAUNCH_TYPE.MIKEY) {
                return next;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void startCondition() {
        if (!this.q) {
            if (mScene.l.size() >= 5) {
                Toast.makeText(this, R.string.add_condition_error, 0).show();
                return;
            }
            Intent intent = new Intent(this.mContext, StartConditionActivity.class);
            if (this.i != null) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(this.i);
                intent.putParcelableArrayListExtra("extra_default_conditions", arrayList);
            } else {
                intent.putParcelableArrayListExtra("extra_default_conditions", this.d);
            }
            startActivityForResult(intent, 1);
            CreateSceneManager.a().l();
        }
    }

    @OnClick({2131427579})
    public void startAddActionForTaskBtn() {
        startAddAction();
    }

    /* access modifiers changed from: package-private */
    public void startAddAction() {
        if (!this.q) {
            Intent intent = new Intent(this.mContext, SmartHomeSceneActionChooseActivity.class);
            if (this.j != null) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(this.j);
                intent.putParcelableArrayListExtra("extra_default_actions", arrayList);
            } else {
                intent.putParcelableArrayListExtra("extra_default_actions", this.g);
            }
            startActivityForResult(intent, 2);
            CreateSceneManager.a().l();
        }
    }

    /* access modifiers changed from: private */
    public void d() {
        if (isNewScene) {
            if (mScene.l.size() <= 0 || mScene.k.size() <= 0) {
                if (mScene.l.size() == 0) {
                    Toast.makeText(this.mContext, R.string.smarthome_scene_add_start_condition, 0).show();
                } else {
                    Toast.makeText(this.mContext, R.string.smarthome_scene_set_fail_at_least_0, 0).show();
                }
            } else if (this.k != null) {
                a(this.k, (ClientRemarkInputView) null);
            } else {
                final String a2 = SmartHomeSceneUtility.a(mScene);
                if (a2.length() > 30) {
                    a2 = a2.substring(0, 30);
                }
                final ClientRemarkInputView clientRemarkInputView = (ClientRemarkInputView) getLayoutInflater().inflate(R.layout.client_input_view_checkbox, (ViewGroup) null);
                final CheckBox checkBox = (CheckBox) clientRemarkInputView.findViewById(R.id.enable_push_checkbox);
                clientRemarkInputView.initialize((ClientRemarkInputView.RenameInterface) null, new MLAlertDialog.Builder(this.mContext).a((int) R.string.smarthome_scene_set_name).b((View) clientRemarkInputView).d(false).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        boolean z;
                        dialogInterface.dismiss();
                        Iterator<SceneApi.SmartHomeScene> it = SceneManager.x().v().iterator();
                        while (true) {
                            if (it.hasNext()) {
                                if (it.next().g.equals(clientRemarkInputView.getEditText().getText().toString())) {
                                    z = true;
                                    break;
                                }
                            } else {
                                z = false;
                                break;
                            }
                        }
                        if (z) {
                            Toast.makeText(SmartHomeSceneCreateEditActivity.this.mContext, SmartHomeSceneCreateEditActivity.this.getString(R.string.scene_modify_name_error), 0).show();
                            return;
                        }
                        if (checkBox.isChecked()) {
                            SmartHomeSceneCreateEditActivity.mScene.o = true;
                        }
                        if (!a2.equals(clientRemarkInputView.getEditText().getText().toString())) {
                            MobclickAgent.a(SmartHomeSceneCreateEditActivity.this.mContext, MiStatType.CLICK.getValue(), "scene_save_rename");
                        }
                        SmartHomeSceneCreateEditActivity.this.a(clientRemarkInputView.getEditText().getText().toString(), clientRemarkInputView);
                    }
                }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ((MLAlertDialog) dialogInterface).setAudoDismiss(true);
                    }
                }).d(), a2);
            }
        } else if (mScene.l.size() <= 0 || mScene.k.size() <= 0) {
            Toast.makeText(this.mContext, R.string.smarthome_scene_set_fail_at_least_0, 0).show();
        } else if (SceneManager.x().e(mScene)) {
            MLAlertDialog b2 = new MLAlertDialog.Builder(this.mContext).a((int) R.string.smarthome_scene_conflict).b((int) R.string.sh_confirm, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
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
            if (a() == 1 && this.r != null) {
                deleteTimeSpan(this.r);
            }
            a(mScene.g, (ClientRemarkInputView) null);
        }
    }

    @OnClick({2131430989})
    public void completeScene() {
        boolean z;
        Iterator<SceneApi.Action> it = mScene.k.iterator();
        while (true) {
            z = true;
            if (!it.hasNext()) {
                break;
            }
            SceneApi.Action next = it.next();
            if (next.f21521a == 1) {
                for (SceneApi.Condition e2 : mScene.l) {
                    ((SceneApi.SHScenePushPayload) next.g).b = SmartHomeSceneUtility.e(SHApplication.getAppContext(), e2);
                }
            }
        }
        SceneApi.Condition c2 = c();
        if (c2 != null) {
            String str = c2.e.f21523a;
            String str2 = c2.e.j;
        }
        if (SHConfig.a().a("show_scene_user_lincense") != -1) {
            z = false;
        }
        if (z) {
            SHConfig.a().a("show_scene_user_lincense", 0);
            new MLAlertDialog.Builder(this).a(StringUtil.a((Context) this, (int) R.string.scene_user_license_title_1_underline, (int) R.string.scene_user_license_title_1_tpl, (ClickableSpan) new ClickableSpan() {
                public void updateDrawState(TextPaint textPaint) {
                    super.updateDrawState(textPaint);
                    textPaint.setColor(SmartHomeSceneCreateEditActivity.this.getResources().getColor(R.color.class_text_27));
                    textPaint.setUnderlineText(true);
                }

                public void onClick(View view) {
                    SmartHomeSceneCreateEditActivity.this.startActivity(new Intent(SmartHomeSceneCreateEditActivity.this, SceneUseLicnece.class));
                }
            })).a((int) R.string.scene_user_license_title).a((int) R.string.smarthome_share_accept, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    SmartHomeSceneCreateEditActivity.this.d();
                }
            }).b((int) R.string.smarthome_share_reject, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    SHConfig.a().a("show_scene_user_lincense", -1);
                }
            }).a(false).b().show();
            return;
        }
        d();
    }

    @OnClick({2131430988})
    public void gotoMorePage() {
        if (!TextUtils.isEmpty(this.mSceneID) && mScene.a(this.mSceneSrc)) {
            Intent intent = new Intent(this, SceneMoreActivity.class);
            intent.putExtra("extra_scene_id", this.mSceneID);
            startActivityForResult(intent, 1000);
        }
    }

    /* access modifiers changed from: private */
    public void a(String str, ClientRemarkInputView clientRemarkInputView) {
        Device n2;
        String str2;
        Device n3;
        String str3;
        this.m = XQProgressDialog.a(this.mContext, (CharSequence) null, getString(R.string.smarthome_scene_saving_scene));
        mScene.g = str;
        if (clientRemarkInputView != null) {
            ((InputMethodManager) this.mContext.getSystemService("input_method")).hideSoftInputFromWindow(clientRemarkInputView.getEditText().getWindowToken(), 0);
        }
        String str4 = null;
        boolean z = false;
        for (SceneApi.Condition next : mScene.l) {
            if (!(next.c == null || next.c.d == null || (n3 = SmartHomeDeviceManager.a().n(next.c.f21523a)) == null)) {
                if (n3.isSubDevice()) {
                    str3 = n3.parentId;
                } else {
                    str3 = n3.did;
                }
                Device n4 = SmartHomeDeviceManager.a().n(str3);
                if (n4.model.equalsIgnoreCase("lumi.gateway.v1") || n4.model.equalsIgnoreCase("lumi.gateway.v2") || n4.model.equalsIgnoreCase("lumi.gateway.v3") || n4.model.equalsIgnoreCase("lumi.acpartner.v1") || n4.model.equalsIgnoreCase("lumi.acpartner.v2") || n4.model.equalsIgnoreCase("lumi.camera.v1") || n4.model.equalsIgnoreCase("lumi.camera.aq1") || n4.model.equalsIgnoreCase("lumi.acpartner.v3")) {
                    str4 = str3;
                    z = true;
                }
            }
        }
        if (!z) {
            for (SceneApi.Action next2 : mScene.k) {
                if (!(next2.e == null || next2.g.e == null || (n2 = SmartHomeDeviceManager.a().n(next2.g.e)) == null)) {
                    if (n2.isSubDevice()) {
                        str2 = n2.parentId;
                    } else {
                        str2 = n2.did;
                    }
                    Device n5 = SmartHomeDeviceManager.a().n(str2);
                    if (n5.model.equalsIgnoreCase("lumi.gateway.v1") || n5.model.equalsIgnoreCase("lumi.gateway.v2") || n5.model.equalsIgnoreCase("lumi.gateway.v3") || n5.model.equalsIgnoreCase("lumi.acpartner.v1") || n5.model.equalsIgnoreCase("lumi.acpartner.v2") || n5.model.equalsIgnoreCase("lumi.camera.v1") || n5.model.equalsIgnoreCase("lumi.camera.aq1") || n5.model.equalsIgnoreCase("lumi.acpartner.v3")) {
                        str4 = str2;
                        z = true;
                    }
                }
            }
        }
        if (!z || str4 == null) {
            startSaveScene((String) null);
            return;
        }
        final Device n6 = SmartHomeDeviceManager.a().n(str4);
        if (n6 == null || n6.isOnline) {
            Intent intent = new Intent();
            SceneApi.a(mScene);
            intent.putExtra("scene_info", SceneManager.f(mScene));
            this.mHandler.sendEmptyMessageDelayed(100, 1000);
            MpkPluginApi.callPlugin(n6.did, 10, intent, n6.newDeviceStat(), new PluginApi.SendMessageCallback() {
                public void onMessageSuccess(Intent intent) {
                    if (SmartHomeSceneCreateEditActivity.this.mHandler.hasMessages(100)) {
                        SmartHomeSceneCreateEditActivity.this.mHandler.removeMessages(100);
                        SceneInfo sceneInfo = (SceneInfo) intent.getParcelableExtra("scene_info");
                        if (sceneInfo != null) {
                            for (int i = 0; i < sceneInfo.mLaunchList.size(); i++) {
                                if (SmartHomeSceneCreateEditActivity.mScene.l.get(i).c != null && (SmartHomeSceneCreateEditActivity.mScene.l.get(i).c instanceof SceneApi.ConditionDeviceCommon)) {
                                    ((SceneApi.ConditionDeviceCommon) SmartHomeSceneCreateEditActivity.mScene.l.get(i).c).m = sceneInfo.mLaunchList.get(i).mExtra;
                                }
                            }
                            for (int i2 = 0; i2 < sceneInfo.mActions.size(); i2++) {
                                if (SmartHomeSceneCreateEditActivity.mScene.k.get(i2).g != null && (SmartHomeSceneCreateEditActivity.mScene.k.get(i2).g instanceof SceneApi.SHSceneItemPayloadCommon)) {
                                    ((SceneApi.SHSceneItemPayloadCommon) SmartHomeSceneCreateEditActivity.mScene.k.get(i2).g).f21531a = sceneInfo.mActions.get(i2).mExtra;
                                }
                            }
                        }
                        SmartHomeSceneCreateEditActivity.this.startSaveScene(n6.did);
                    }
                }

                public void onMessageFailure(int i, String str) {
                    if (SmartHomeSceneCreateEditActivity.this.mHandler.hasMessages(100)) {
                        SmartHomeSceneCreateEditActivity.this.mHandler.removeMessages(100);
                        SmartHomeSceneCreateEditActivity.this.startSaveScene((String) null);
                    }
                }

                public void onMessageHandle(boolean z) {
                    if (!z) {
                        SmartHomeSceneCreateEditActivity.this.startSaveScene((String) null);
                    }
                }
            });
            return;
        }
        this.m.dismiss();
        Toast.makeText(this, R.string.smarthome_scene_getway_offline, 0).show();
    }

    public void startSaveScene(final String str) {
        if (SmartHomeConfig.c) {
            if (this.o != -1) {
                mScene.i = this.o;
            }
            RemoteSceneApi.a().a((Context) this, mScene, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    SmartHomeSceneCreateEditActivity.this.processSaveResponse(str, jSONObject);
                }

                public void onFailure(Error error) {
                    SmartHomeSceneCreateEditActivity.this.m.dismiss();
                    if (error.a() == -23) {
                        Toast.makeText(SmartHomeSceneCreateEditActivity.this.mContext, R.string.dead_loop_error, 0).show();
                    } else if (error.a() == -1) {
                        Toast.makeText(SmartHomeSceneCreateEditActivity.this.getContext(), R.string.smarthome_scene_has_deleted_device, 0).show();
                    } else {
                        Toast.makeText(SmartHomeSceneCreateEditActivity.this.mContext, R.string.smarthome_scene_set_fail, 0).show();
                    }
                }
            });
            StatHelper.a(this.o);
            return;
        }
        this.m.dismiss();
    }

    /* access modifiers changed from: package-private */
    public void onFinishSaved(final boolean z) {
        this.mHandler.post(new Runnable() {
            public void run() {
                SmartHomeSceneCreateEditActivity.this.m.dismiss();
                SmartHomeSceneCreateEditActivity.this.e();
                if (z) {
                    if (!TextUtils.isEmpty(SmartHomeSceneCreateEditActivity.this.mSceneID)) {
                        SceneManager.x().g(SmartHomeSceneCreateEditActivity.this.mSceneID);
                    }
                    SceneManager.x().d(SmartHomeSceneCreateEditActivity.mScene);
                    CoreApi.a().U();
                    SceneManager.x().c((String) null);
                    Toast.makeText(SmartHomeSceneCreateEditActivity.this.mContext, R.string.smarthome_scene_set_succ, 0).show();
                } else {
                    Toast.makeText(SmartHomeSceneCreateEditActivity.this.mContext, R.string.local_sync_failed, 0).show();
                }
                SmartHomeSceneCreateEditActivity.this.finish();
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void processSaveResponse(String str, JSONObject jSONObject) {
        String optString = jSONObject.optString("us_id");
        boolean optBoolean = jSONObject.optBoolean("local");
        String optString2 = jSONObject.optString("local_dev");
        mScene.f = optString;
        if (TextUtils.isEmpty(optString2) || !optBoolean) {
            onFinishSaved(true);
            return;
        }
        Device n2 = SmartHomeDeviceManager.a().n(optString2);
        Intent intent = new Intent();
        intent.putExtra("extra", jSONObject.optJSONObject("data").toString());
        MpkPluginApi.callPlugin(n2.did, 14, intent, n2.newDeviceStat(), new PluginApi.SendMessageCallback() {
            public void onMessageSuccess(Intent intent) {
                RemoteSceneApi.a().b((Context) SmartHomeSceneCreateEditActivity.this, SmartHomeSceneCreateEditActivity.mScene, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                    /* renamed from: a */
                    public void onSuccess(Void voidR) {
                        SmartHomeSceneCreateEditActivity.this.onFinishSaved(true);
                    }

                    public void onFailure(Error error) {
                        SmartHomeSceneCreateEditActivity.this.onFinishSaved(false);
                    }
                });
            }

            public void onMessageFailure(int i, String str) {
                SmartHomeSceneCreateEditActivity.this.onFinishSaved(false);
            }

            public void onMessageHandle(boolean z) {
                if (!z) {
                    SmartHomeSceneCreateEditActivity.this.onFinishSaved(false);
                }
            }
        });
        this.mHandler.sendEmptyMessageDelayed(101, 10000);
    }

    /* access modifiers changed from: private */
    public void e() {
        SceneApi.Condition c2 = c();
        if (c2 != null) {
            Intent intent = new Intent();
            String str = c2.e.f21523a;
            String str2 = c2.e.j;
            intent.putExtra("extra_did", str);
            intent.putExtra(BluetoothConstants.g, str2);
            setResult(-1, intent);
        }
    }

    public void sortActions() {
        Collections.sort(mScene.k, new Comparator<SceneApi.Action>() {
            /* renamed from: a */
            public int compare(SceneApi.Action action, SceneApi.Action action2) {
                if (action.g == null || action2.g == null) {
                    return 0;
                }
                return action.g.g - action2.g.g;
            }
        });
        for (int i2 = 0; i2 < mScene.k.size(); i2++) {
            DelayInfo delayInfo = new DelayInfo();
            if (i2 != 0) {
                int i3 = i2 - 1;
                if (mScene.k.get(i2).g.g != mScene.k.get(i3).g.g) {
                    delayInfo.f21428a = mScene.k.get(i2).g.g - mScene.k.get(i3).g.g;
                    this.p.add(delayInfo);
                    DelayInfo delayInfo2 = new DelayInfo();
                    delayInfo2.b = mScene.k.get(i2);
                    this.p.add(delayInfo2);
                }
            }
            delayInfo.b = mScene.k.get(i2);
            this.p.add(delayInfo);
        }
    }

    class ConditionsAdapter extends BaseAdapter {

        /* renamed from: a  reason: collision with root package name */
        int f21410a;
        int b;
        @BindView(2131428105)
        TextView mBuyButton;
        @BindView(2131429112)
        ImageView mExpandHint;
        @BindView(2131429685)
        SimpleDraweeView mIcon;
        @BindView(2131430270)
        TextView mKeyName;
        @BindView(2131431126)
        TextView mName;
        @BindView(2131431264)
        TextView mOffline;
        @BindView(2131431922)
        TextView mRunTV;
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
            if (!(SmartHomeSceneCreateEditActivity.mScene == null || SmartHomeSceneCreateEditActivity.mScene.l == null)) {
                this.b = SmartHomeSceneCreateEditActivity.mScene.l.size();
            }
            if (SmartHomeSceneCreateEditActivity.this.e != null) {
                this.f21410a = SmartHomeSceneCreateEditActivity.this.e.size();
            }
            return this.f21410a + this.b;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = SmartHomeSceneCreateEditActivity.this.getLayoutInflater().inflate(R.layout.scene_add_scene_condition_item, (ViewGroup) null);
            }
            ButterKnife.bind((Object) this, view);
            if (i < SmartHomeSceneCreateEditActivity.mScene.l.size()) {
                final SceneApi.Condition condition = SmartHomeSceneCreateEditActivity.mScene.l.get(i);
                this.mBuyButton.setVisibility(8);
                if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.DEVICE) {
                    this.mKeyName.setVisibility(0);
                    this.mKeyName.setText(condition.c.b);
                } else if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.TIMER) {
                    this.mKeyName.setVisibility(0);
                    this.mKeyName.setText(SmartHomeSceneTimerActivity.getTimerHint(SmartHomeSceneCreateEditActivity.this, condition.b != null ? condition.b.f21527a : null));
                } else if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.LEAVE_LOC || condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.COME_LOC) {
                    this.mKeyName.setVisibility(0);
                    this.mKeyName.setText(condition.h.b);
                } else if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.LEAVE_FENCE || condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.ENTER_FENCE) {
                    this.mKeyName.setVisibility(0);
                    this.mKeyName.setText(condition.i.b);
                } else if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.PHONE_CALL || condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.PHONE_SMS) {
                    this.mKeyName.setVisibility(8);
                }
                if (condition.f21522a != SceneApi.Condition.LAUNCH_TYPE.CLICK || !SmartHomeSceneCreateEditActivity.mScene.n) {
                    this.mRunTV.setVisibility(8);
                } else {
                    this.mRunTV.setVisibility(0);
                    this.mRunTV.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            RemoteSceneApi.a().b(SmartHomeSceneCreateEditActivity.this.getApplicationContext(), SmartHomeSceneCreateEditActivity.mScene.f, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                                /* renamed from: a */
                                public void onSuccess(Void voidR) {
                                }

                                public void onFailure(Error error) {
                                    Toast.makeText(SmartHomeSceneCreateEditActivity.this.getApplicationContext(), R.string.smarthome_scene_start_error, 0).show();
                                }
                            });
                            CoreApi.a().a(StatType.EVENT, "run_button_click", "smart_home_scene_create_edit_activity", (String) null, false);
                        }
                    });
                }
                this.mName.setText(SmartHomeSceneUtility.b(SmartHomeSceneCreateEditActivity.this.mContext, condition));
                this.mExpandHint.setVisibility(8);
                SmartHomeSceneUtility.a(this.mIcon, condition);
                if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.DEVICE || condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.MIKEY || condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.MIBAND) {
                    this.mTimeSpan.setVisibility(8);
                    this.mOffline.setVisibility(8);
                    this.mTimeSetButton.setVisibility(8);
                    if (!SmartHomeSceneCreateEditActivity.this.mConditionOnlineItems[i]) {
                        this.mOffline.setVisibility(0);
                        this.mOffline.setText(R.string.smarthome_scene_client_offline);
                        if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.DEVICE && condition.c != null && !TextUtils.isEmpty(condition.c.f21523a)) {
                            Device n = SmartHomeDeviceManager.a().n(condition.c.f21523a);
                            if (n == null) {
                                this.mOffline.setText(R.string.smarthome_scene_client_deleted);
                            } else if (!n.isOwner()) {
                                this.mOffline.setText(R.string.smarthome_scene_client_deleted);
                            }
                        }
                        this.mTimeSetButton.setVisibility(8);
                        this.mTimeSpan.setVisibility(8);
                        view.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                if (!SmartHomeSceneCreateEditActivity.this.q) {
                                    Intent intent = new Intent(SmartHomeSceneCreateEditActivity.this.mContext, StartConditionActivity.class);
                                    if (SmartHomeSceneCreateEditActivity.this.i != null) {
                                        ArrayList arrayList = new ArrayList();
                                        arrayList.add(SmartHomeSceneCreateEditActivity.this.i);
                                        intent.putParcelableArrayListExtra("extra_default_conditions", arrayList);
                                    } else {
                                        intent.putParcelableArrayListExtra("extra_default_conditions", SmartHomeSceneCreateEditActivity.this.d);
                                    }
                                    CreateSceneManager.a().c(condition);
                                    SmartHomeSceneCreateEditActivity.this.startActivityForResult(intent, 1);
                                }
                            }
                        });
                    } else if (condition.c != null) {
                        if (condition.c.e == -1) {
                            this.mTimeSpan.setVisibility(8);
                            this.mOffline.setVisibility(8);
                            this.mTimeSetButton.setVisibility(8);
                        } else if (SmartHomeSceneCreateEditActivity.this.a() > 1) {
                            this.mTimeSetButton.setVisibility(0);
                            this.mTimeSpan.setVisibility(0);
                            TextView textView = this.mTimeSpan;
                            textView.setText("" + condition.c.e + ":00-" + condition.c.f + ":00");
                            this.mTimeSetButton.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View view) {
                                    if (!SmartHomeSceneCreateEditActivity.this.q) {
                                        SmartHomeSceneCreateEditActivity.this.g();
                                    }
                                }
                            });
                        } else {
                            this.mTimeSetButton.setVisibility(8);
                            this.mTimeSpan.setVisibility(8);
                        }
                        view.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                if (!SmartHomeSceneCreateEditActivity.this.q) {
                                    Intent intent = new Intent(SmartHomeSceneCreateEditActivity.this.mContext, StartConditionActivity.class);
                                    if (SmartHomeSceneCreateEditActivity.this.i != null) {
                                        ArrayList arrayList = new ArrayList();
                                        arrayList.add(SmartHomeSceneCreateEditActivity.this.i);
                                        intent.putParcelableArrayListExtra("extra_default_conditions", arrayList);
                                    } else {
                                        intent.putParcelableArrayListExtra("extra_default_conditions", SmartHomeSceneCreateEditActivity.this.d);
                                    }
                                    CreateSceneManager.a().c(condition);
                                    SmartHomeSceneCreateEditActivity.this.startActivityForResult(intent, 1);
                                }
                            }
                        });
                    }
                    view.setOnLongClickListener(new View.OnLongClickListener() {
                        public boolean onLongClick(View view) {
                            if (SmartHomeSceneCreateEditActivity.this.q) {
                                return false;
                            }
                            new MLAlertDialog.Builder(SmartHomeSceneCreateEditActivity.this.mContext).a((CharSequence[]) new String[]{SmartHomeSceneCreateEditActivity.this.getString(R.string.delete)}, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    SmartHomeSceneCreateEditActivity.mScene.l.remove(condition);
                                    CreateSceneManager.a().d(SmartHomeSceneCreateEditActivity.mScene);
                                    SmartHomeSceneCreateEditActivity.this.mConditionAdapter.notifyDataSetChanged();
                                    SmartHomeSceneCreateEditActivity.this.refreshUI(false);
                                    dialogInterface.cancel();
                                }
                            }).b().show();
                            return true;
                        }
                    });
                    return view;
                } else if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.COME_LOC || condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.LEAVE_LOC || condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.ENTER_FENCE || condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.LEAVE_FENCE) {
                    this.mTimeSpan.setVisibility(8);
                    this.mOffline.setVisibility(8);
                    this.mTimeSetButton.setVisibility(8);
                    this.mTimeSetButton.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            if (!SmartHomeSceneCreateEditActivity.this.q) {
                                SmartHomeSceneCreateEditActivity.this.startActivityForResult(new Intent(SmartHomeSceneCreateEditActivity.this.mContext, SmartHomeSceneTimeSpan.class), 2);
                                CreateSceneManager.a().c(condition);
                            }
                        }
                    });
                    view.setOnLongClickListener(new View.OnLongClickListener() {
                        public boolean onLongClick(View view) {
                            if (SmartHomeSceneCreateEditActivity.this.q) {
                                return false;
                            }
                            new MLAlertDialog.Builder(SmartHomeSceneCreateEditActivity.this.mContext).a((CharSequence[]) new String[]{SmartHomeSceneCreateEditActivity.this.getString(R.string.delete)}, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    SmartHomeSceneCreateEditActivity.mScene.l.remove(condition);
                                    CreateSceneManager.a().d(SmartHomeSceneCreateEditActivity.mScene);
                                    SmartHomeSceneCreateEditActivity.this.mConditionAdapter.notifyDataSetChanged();
                                    SmartHomeSceneCreateEditActivity.this.refreshUI(false);
                                    dialogInterface.cancel();
                                }
                            }).b().show();
                            return true;
                        }
                    });
                    view.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            if (!SmartHomeSceneCreateEditActivity.this.q) {
                                Intent intent = new Intent(SmartHomeSceneCreateEditActivity.this.mContext, StartConditionActivity.class);
                                CreateSceneManager.a().c(condition);
                                SmartHomeSceneCreateEditActivity.this.startActivityForResult(intent, 1);
                            }
                        }
                    });
                } else {
                    this.mTimeSetButton.setVisibility(8);
                    this.mTimeSpan.setVisibility(8);
                    this.mOffline.setVisibility(8);
                    if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.TIMER) {
                        this.mExpandHint.setVisibility(0);
                        view.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                if (!SmartHomeSceneCreateEditActivity.this.q) {
                                    SmartHomeSceneCreateEditActivity.this.startActivity(new Intent(SmartHomeSceneCreateEditActivity.this, SmartHomeSceneTimerActivity.class));
                                    CreateSceneManager.a().c(condition);
                                }
                            }
                        });
                    } else {
                        this.mExpandHint.setVisibility(8);
                        view.setOnClickListener((View.OnClickListener) null);
                    }
                    view.setOnLongClickListener(new View.OnLongClickListener() {
                        public boolean onLongClick(View view) {
                            if (SmartHomeSceneCreateEditActivity.this.q) {
                                return false;
                            }
                            new MLAlertDialog.Builder(SmartHomeSceneCreateEditActivity.this.mContext).a((CharSequence[]) new String[]{SmartHomeSceneCreateEditActivity.this.getString(R.string.delete)}, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    SmartHomeSceneCreateEditActivity.mScene.l.remove(condition);
                                    SmartHomeSceneCreateEditActivity.this.mConditionAdapter.notifyDataSetChanged();
                                    CreateSceneManager.a().d(SmartHomeSceneCreateEditActivity.mScene);
                                    SmartHomeSceneCreateEditActivity.this.refreshUI(false);
                                    dialogInterface.cancel();
                                }
                            }).b().show();
                            return true;
                        }
                    });
                }
            } else {
                final Device k = DeviceFactory.k(((DefaultSceneItemSet) SmartHomeSceneCreateEditActivity.this.e.get(i - this.b)).b[0]);
                if (k != null) {
                    this.mName.setText(k.name);
                    this.mExpandHint.setVisibility(8);
                    this.mName.setTextColor(SmartHomeSceneCreateEditActivity.this.mContext.getResources().getColor(R.color.class_text_12));
                    if (!TextUtils.isEmpty(((DefaultSceneItemSet) SmartHomeSceneCreateEditActivity.this.e.get(i - this.b)).c[0].mName)) {
                        this.mKeyName.setText(((DefaultSceneItemSet) SmartHomeSceneCreateEditActivity.this.e.get(i - this.b)).c[0].mName);
                    } else {
                        this.mKeyName.setVisibility(8);
                    }
                    DeviceFactory.b(k.model, this.mIcon);
                    view.setOnClickListener((View.OnClickListener) null);
                    view.setOnLongClickListener((View.OnLongClickListener) null);
                    this.mBuyButton.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            SmartHomeSceneCreateEditActivity.this.a(k.name);
                        }
                    });
                    this.mBuyButton.setVisibility(0);
                    this.mTimeSetButton.setVisibility(8);
                    this.mTimeSpan.setVisibility(8);
                    return view;
                }
            }
            return view;
        }
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            UrlDispatchManger a2 = UrlDispatchManger.a();
            a2.c("https://home.mi.com/shop/search?keyword=" + str);
            return;
        }
        UrlDispatchManger.a().c("https://home.mi.com/shop/main");
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
            if (SmartHomeSceneCreateEditActivity.this.h != null) {
                this.b = SmartHomeSceneCreateEditActivity.this.h.size();
            }
            if (!(SmartHomeSceneCreateEditActivity.mScene == null || SmartHomeSceneCreateEditActivity.mScene.k == null)) {
                this.c = SmartHomeSceneCreateEditActivity.mScene.k.size();
            }
            return this.b + this.c;
        }

        public View getView(final int i, View view, ViewGroup viewGroup) {
            String str;
            if (view == null) {
                view = SmartHomeSceneCreateEditActivity.this.getLayoutInflater().inflate(R.layout.smarthome_scene_condition_task_item, (ViewGroup) null);
            }
            ButterKnife.bind((Object) this, view);
            if (i >= this.c) {
                this.mTaskLayout.setVisibility(0);
                this.mDelayLayout.setVisibility(8);
                final Device k = DeviceFactory.k(((DefaultSceneItemSet) SmartHomeSceneCreateEditActivity.this.h.get(i - this.c)).b[0]);
                this.mTitle.setText(k.name);
                this.mOffline.setVisibility(8);
                this.mTitle.setTextColor(SmartHomeSceneCreateEditActivity.this.mContext.getResources().getColor(R.color.class_text_12));
                DeviceFactory.b(k.model, this.mImage);
                this.mSubTitle.setText(((DefaultSceneItemSet) SmartHomeSceneCreateEditActivity.this.h.get(i - this.c)).c[0].mName);
                view.setOnClickListener((View.OnClickListener) null);
                view.setOnLongClickListener((View.OnLongClickListener) null);
                if (i == 0 && i == (this.b + this.c) - 1) {
                    this.mIconTopLine.setVisibility(4);
                    this.mIconBottomLine.setVisibility(4);
                    this.mBottomline.setVisibility(4);
                } else if (i == 0) {
                    this.mBottomline.setVisibility(4);
                    this.mIconTopLine.setVisibility(4);
                    this.mIconBottomLine.setVisibility(0);
                } else if (i == (this.b + this.c) - 1) {
                    this.mIconTopLine.setVisibility(0);
                    this.mIconBottomLine.setVisibility(4);
                    this.mBottomline.setVisibility(4);
                } else {
                    this.mIconTopLine.setVisibility(0);
                    this.mIconBottomLine.setVisibility(0);
                    this.mBottomline.setVisibility(4);
                }
                this.mBuyButton.setVisibility(0);
                this.mBuyButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (TextUtils.isEmpty(((DefaultSceneItemSet) SmartHomeSceneCreateEditActivity.this.h.get(i - ActionsAdapter.this.c)).d)) {
                            SmartHomeSceneCreateEditActivity.this.showBuyDialog();
                        } else {
                            SmartHomeSceneCreateEditActivity.this.a(k.name);
                        }
                    }
                });
                this.mAnchor.setVisibility(8);
                return view;
            }
            final SceneApi.Action action = SmartHomeSceneCreateEditActivity.mScene.k.get(i);
            this.mBuyButton.setVisibility(8);
            if (action.g instanceof SceneApi.SHSceneDelayPayload) {
                this.mTaskLayout.setVisibility(8);
                this.mDelayLayout.setVisibility(0);
                if (i == 0 && i == (this.b + this.c) - 1) {
                    this.mDelayTimeLine.setVisibility(4);
                    this.mDelayTopLine.setVisibility(4);
                    this.mDelayBottomLine.setVisibility(4);
                } else if (i == 0) {
                    this.mDelayTimeLine.setVisibility(4);
                    this.mDelayTopLine.setVisibility(4);
                    this.mDelayBottomLine.setVisibility(0);
                } else if (i == (this.b + this.c) - 1) {
                    this.mDelayTimeLine.setVisibility(4);
                    this.mDelayTopLine.setVisibility(0);
                    this.mDelayBottomLine.setVisibility(4);
                } else {
                    this.mDelayTimeLine.setVisibility(0);
                    this.mDelayTopLine.setVisibility(0);
                    this.mDelayBottomLine.setVisibility(0);
                }
                if (action.g.g < 60) {
                    str = SmartHomeSceneCreateEditActivity.this.getResources().getQuantityString(R.plurals.smarthome_scene_delay_detal_sec, action.g.g % 60, new Object[]{Integer.valueOf(action.g.g % 60)});
                } else if (action.g.g % 60 == 0) {
                    str = SmartHomeSceneCreateEditActivity.this.getResources().getQuantityString(R.plurals.smarthome_scene_delay_detal_min, action.g.g / 60, new Object[]{Integer.valueOf(action.g.g / 60)});
                } else {
                    String quantityString = SmartHomeSceneCreateEditActivity.this.getResources().getQuantityString(R.plurals.automation_minute, action.g.g / 60, new Object[]{Integer.valueOf(action.g.g / 60)});
                    String quantityString2 = SmartHomeSceneCreateEditActivity.this.getResources().getQuantityString(R.plurals.automation_seconds, action.g.g % 60, new Object[]{Integer.valueOf(action.g.g % 60)});
                    str = String.format(SmartHomeSceneCreateEditActivity.this.getString(R.string.smarthome_scene_delay_detal_min_sec), new Object[]{quantityString, quantityString2});
                }
                this.mDelayTimeText.setText(str);
                view.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (!SmartHomeSceneCreateEditActivity.this.q) {
                            Intent intent = new Intent(SmartHomeSceneCreateEditActivity.this.mContext, SmartHomeSceneTimerDelay.class);
                            CreateSceneManager.a().c(action);
                            SmartHomeSceneCreateEditActivity.this.startActivityForResult(intent, 1);
                        }
                    }
                });
            } else {
                this.mTaskLayout.setVisibility(0);
                this.mDelayLayout.setVisibility(8);
                if (i == 0 && i == SmartHomeSceneCreateEditActivity.mScene.k.size() - 1) {
                    this.mIconTopLine.setVisibility(4);
                    this.mIconBottomLine.setVisibility(4);
                    this.mBottomline.setVisibility(4);
                } else if (i == 0) {
                    this.mBottomline.setVisibility(4);
                    this.mIconTopLine.setVisibility(4);
                    this.mIconBottomLine.setVisibility(0);
                } else if (i == SmartHomeSceneCreateEditActivity.mScene.k.size() - 1) {
                    this.mIconTopLine.setVisibility(0);
                    this.mIconBottomLine.setVisibility(4);
                    this.mBottomline.setVisibility(4);
                } else {
                    this.mIconTopLine.setVisibility(0);
                    this.mIconBottomLine.setVisibility(0);
                    this.mBottomline.setVisibility(4);
                }
                if (i != 0 && (SmartHomeSceneCreateEditActivity.mScene.k.get(i).g instanceof SceneApi.SHSceneDelayPayload)) {
                    this.mBottomline.setVisibility(4);
                } else if (i == SmartHomeSceneCreateEditActivity.mScene.k.size() - 1) {
                    this.mBottomline.setVisibility(4);
                } else {
                    this.mBottomline.setVisibility(4);
                }
                this.mOffline.setVisibility(8);
                this.mAnchor.setVisibility(8);
                SmartHomeSceneUtility.a(this.mImage, this.mTitle, action);
                if (action.g instanceof SceneApi.SHScenePushPayload) {
                    this.mSubTitle.setVisibility(8);
                    this.mSubTitle.setText(action.c);
                } else {
                    this.mSubTitle.setVisibility(0);
                    this.mSubTitle.setText(action.c);
                }
                view.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (!SmartHomeSceneCreateEditActivity.this.q) {
                            Intent intent = new Intent(SmartHomeSceneCreateEditActivity.this.mContext, SmartHomeSceneActionChooseActivity.class);
                            CreateSceneManager.a().c(action);
                            if (SmartHomeSceneCreateEditActivity.this.j != null) {
                                ArrayList arrayList = new ArrayList();
                                arrayList.add(SmartHomeSceneCreateEditActivity.this.j);
                                intent.putParcelableArrayListExtra("extra_default_actions", arrayList);
                            } else {
                                intent.putParcelableArrayListExtra("extra_default_actions", SmartHomeSceneCreateEditActivity.this.g);
                            }
                            SmartHomeSceneCreateEditActivity.this.startActivityForResult(intent, 1);
                        }
                    }
                });
                if ((action.g instanceof SceneApi.SHSceneAutoPayload) || (action.g instanceof SceneApi.SHScenePushPayload) || (action.g instanceof SceneApi.SHLiteScenePayload) || (SmartHomeSceneCreateEditActivity.this.mOnlineItems != null && SmartHomeSceneCreateEditActivity.this.mOnlineItems.length > i && SmartHomeSceneCreateEditActivity.this.mOnlineItems[i])) {
                    this.mOffline.setVisibility(8);
                    this.mAnchor.setVisibility(8);
                    if (action.g instanceof SceneApi.SHLiteScenePayload) {
                        if (LiteSceneManager.j().a(((SceneApi.SHLiteScenePayload) action.g).f21529a) == null) {
                            this.mOffline.setVisibility(0);
                            this.mOffline.setText(R.string.scene_invalid);
                        }
                    } else if ((action.g instanceof SceneApi.SHSceneAutoPayload) && SceneManager.x().e(((SceneApi.SHSceneAutoPayload) action.g).f21530a) == null) {
                        this.mOffline.setVisibility(0);
                        this.mOffline.setText(R.string.auto_scene_invalid);
                    }
                } else {
                    this.mOffline.setVisibility(0);
                    this.mOffline.setText(R.string.smarthome_scene_client_offline);
                    if (!TextUtils.isEmpty(action.g.e)) {
                        Device n = SmartHomeDeviceManager.a().n(action.g.e);
                        if (n == null) {
                            this.mOffline.setText(R.string.smarthome_scene_client_deleted);
                        } else if (!n.isOwner()) {
                            this.mOffline.setText(R.string.smarthome_scene_client_deleted);
                        }
                    }
                    this.mAnchor.setVisibility(8);
                    view.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            if (!SmartHomeSceneCreateEditActivity.this.q) {
                                Intent intent = new Intent(SmartHomeSceneCreateEditActivity.this.mContext, SmartHomeSceneActionChooseActivity.class);
                                CreateSceneManager.a().c(action);
                                if (SmartHomeSceneCreateEditActivity.this.j != null) {
                                    ArrayList arrayList = new ArrayList();
                                    arrayList.add(SmartHomeSceneCreateEditActivity.this.j);
                                    intent.putParcelableArrayListExtra("extra_default_actions", arrayList);
                                } else {
                                    intent.putParcelableArrayListExtra("extra_default_actions", SmartHomeSceneCreateEditActivity.this.g);
                                }
                                SmartHomeSceneCreateEditActivity.this.startActivityForResult(intent, 1);
                            }
                        }
                    });
                }
            }
            this.mBuyButton.setVisibility(8);
            view.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View view) {
                    if (SmartHomeSceneCreateEditActivity.this.q) {
                        return false;
                    }
                    new MLAlertDialog.Builder(SmartHomeSceneCreateEditActivity.this.mContext).a((CharSequence[]) new String[]{SmartHomeSceneCreateEditActivity.this.getString(R.string.delete)}, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (i == 0) {
                                SmartHomeSceneCreateEditActivity.mScene.k.remove(i);
                                CreateSceneManager.a().d(SmartHomeSceneCreateEditActivity.mScene);
                                SmartHomeSceneCreateEditActivity.this.refreshUI(false);
                            }
                        }
                    }).d();
                    return true;
                }
            });
            return view;
        }
    }

    /* access modifiers changed from: package-private */
    public void deleteTimeSpan(SceneApi.ConditionDevice conditionDevice) {
        conditionDevice.e = -1;
        conditionDevice.f = -1;
        conditionDevice.g = -1;
        conditionDevice.h = -1;
        conditionDevice.i = null;
    }

    /* access modifiers changed from: package-private */
    public void initOnlineAndAvatarResource() {
        if (mScene != null && mScene.k != null) {
            this.mOnlineItems = new boolean[mScene.k.size()];
            this.mResource = new int[mScene.k.size()];
            Arrays.fill(this.mOnlineItems, false);
            Arrays.fill(this.mResource, R.drawable.device_list_phone_no);
            for (int i2 = 0; i2 < mScene.k.size(); i2++) {
                SmartHomeSceneUtility.SceneItemInfo a2 = SmartHomeSceneUtility.a(mScene.k.get(i2));
                if (a2.b) {
                    this.mOnlineItems[i2] = true;
                }
                this.mResource[i2] = a2.f21465a;
            }
            if (mScene.l != null) {
                this.mConditionOnlineItems = new boolean[mScene.l.size()];
                for (int i3 = 0; i3 < mScene.l.size(); i3++) {
                    if (mScene.l.get(i3).c == null && mScene.l.get(i3).e == null && mScene.l.get(i3).f == null) {
                        this.mConditionOnlineItems[i3] = true;
                    } else {
                        String str = null;
                        if (mScene.l.get(i3).c != null) {
                            str = mScene.l.get(i3).c.f21523a;
                        }
                        if (mScene.l.get(i3).e != null) {
                            str = mScene.l.get(i3).e.f21523a;
                        }
                        if (mScene.l.get(i3).f != null) {
                            str = mScene.l.get(i3).f.f21523a;
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

    public void onResume() {
        super.onResume();
        if (this.s) {
            refreshUI(false);
        } else {
            this.s = true;
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i2, int i3, Intent intent) {
        if (i3 == -1 && i2 == 1000 && intent.getBooleanExtra(SceneMoreActivity.TAG_SCENE_DELETED, false)) {
            finish();
        } else if (i3 == -1) {
            refreshUI(false);
        }
    }

    /* access modifiers changed from: package-private */
    public void refreshUI(boolean z) {
        boolean z2;
        if (!(this.d == null && this.d == null) && z) {
            initDefaultScenes();
        }
        initOnlineAndAvatarResource();
        if (TextUtils.isEmpty(this.mSceneID) || !mScene.a(this.mSceneSrc)) {
            this.mConfirmBtn.setVisibility(0);
            this.mModuleA4Commit.setVisibility(8);
            this.mConfirmBtn.setText(R.string.confirm);
        } else {
            this.mModuleA4Commit.setVisibility(0);
            this.mConfirmBtn.setVisibility(8);
            this.mModuleA4Commit.setText("");
            this.mModuleA4Commit.setBackgroundResource(R.drawable.std_tittlebar_main_device_more);
        }
        initConditionTitle();
        for (int size = mScene.k.size() - 1; size >= 0; size--) {
            if (size != mScene.k.size() - 1 && (mScene.k.get(size).g instanceof SceneApi.SHSceneDelayPayload)) {
                int i2 = size + 1;
                if (mScene.k.get(i2).g instanceof SceneApi.SHSceneDelayPayload) {
                    mScene.k.get(size).g.g += mScene.k.get(i2).g.g;
                    mScene.k.remove(i2);
                }
            }
        }
        if (mScene.l.size() > 0 || this.e.size() > 0) {
            this.mNoConditionTitle.setVisibility(8);
            this.mDivider2.setVisibility(8);
            this.mConfirmBtn.setEnabled(true);
        } else {
            this.mNoConditionTitle.setVisibility(0);
            this.mNoConditionTitle.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    SmartHomeSceneCreateEditActivity.this.startCondition();
                }
            });
            this.mDivider2.setVisibility(0);
            this.mConfirmBtn.setEnabled(false);
        }
        if (mScene.k.size() > 0 || this.h.size() > 0) {
            if (mScene.k.size() != 1 || !(mScene.k.get(0).g instanceof SceneApi.SHSceneDelayPayload)) {
                this.mConfirmBtn.setEnabled(true);
            } else {
                this.mConfirmBtn.setEnabled(false);
            }
            this.mNoActionTitle.setVisibility(8);
            this.mDivider4.setVisibility(0);
        } else {
            this.mNoActionTitle.setVisibility(0);
            this.mNoActionTitle.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    SmartHomeSceneCreateEditActivity.this.startAddAction();
                }
            });
            this.mDivider4.setVisibility(0);
            this.mConfirmBtn.setEnabled(false);
        }
        Iterator<SceneApi.Condition> it = mScene.l.iterator();
        while (true) {
            if (!it.hasNext()) {
                z2 = false;
                break;
            }
            SceneApi.Condition next = it.next();
            if (next.c != null && isWarnningDevice(next.c.d)) {
                z2 = true;
                break;
            }
        }
        if (!z2) {
            Iterator<SceneApi.Action> it2 = mScene.k.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                SceneApi.Action next2 = it2.next();
                if (next2.e != null && isWarnningDevice(next2.e)) {
                    z2 = true;
                    break;
                }
            }
        }
        if (!z2 || !TextUtils.isEmpty(this.mSceneID)) {
            this.n.setVisibility(8);
        } else {
            this.n.setVisibility(0);
        }
        f();
        this.mConditionAdapter.notifyDataSetChanged();
        this.mActionAdapter.notifyDataSetChanged();
    }

    private void f() {
        int a2 = a();
        if (mScene.v || isNewScene) {
            this.mExeTimeRL.setVisibility(0);
            this.mExeTimeRL.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    SceneLogUtil.a("启动生效时间段页面");
                    SmartHomeSceneCreateEditActivity.this.startActivity(new Intent(SmartHomeSceneCreateEditActivity.this.mContext, SceneCreateTimeEdit2Activity.class));
                    StatHelper.g();
                }
            });
        } else if (a2 == 1) {
            this.mExeTimeRL.setVisibility(0);
            if (this.r != null) {
                mScene.u = new SceneApi.EffectiveTimeSpan();
                mScene.u.f21526a = this.r.e;
                mScene.u.b = this.r.f;
                mScene.u.e = new int[this.r.i.length];
                for (int i2 = 0; i2 < this.r.i.length; i2++) {
                    mScene.u.e[i2] = this.r.i[i2];
                }
                this.mSceneSrc.u = new SceneApi.EffectiveTimeSpan();
                this.mSceneSrc.u.f21526a = this.r.e;
                this.mSceneSrc.u.b = this.r.f;
                this.mSceneSrc.u.e = new int[this.r.i.length];
                for (int i3 = 0; i3 < this.r.i.length; i3++) {
                    this.mSceneSrc.u.e[i3] = this.r.i[i3];
                }
            }
            this.mExeTimeRL.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    SceneLogUtil.a("启动生效时间段页面");
                    SmartHomeSceneCreateEditActivity.this.startActivity(new Intent(SmartHomeSceneCreateEditActivity.this.mContext, SceneCreateTimeEdit2Activity.class));
                    StatHelper.g();
                }
            });
        } else if (a2 == 0) {
            this.mExeTimeRL.setVisibility(0);
            this.mExeTimeRL.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    SceneLogUtil.a("启动生效时间段页面");
                    SmartHomeSceneCreateEditActivity.this.startActivity(new Intent(SmartHomeSceneCreateEditActivity.this.mContext, SceneCreateTimeEdit2Activity.class));
                    StatHelper.g();
                }
            });
        } else {
            this.mExeTimeRL.setVisibility(0);
            this.mExeTimeRL.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    SmartHomeSceneCreateEditActivity.this.g();
                }
            });
        }
        if (mScene == null || mScene.u == null) {
            this.mExeTimeTV.setText(R.string.scene_exetime_all_day);
        } else if (mScene.u.f21526a == mScene.u.b && mScene.u.c == mScene.u.d) {
            this.mExeTimeTV.setText(R.string.scene_exetime_all_day);
        } else if (mScene.u.b <= mScene.u.f21526a) {
            TextView textView = this.mExeTimeTV;
            textView.setText(a(mScene.u.f21526a, mScene.u.c) + "-(" + getResources().getString(R.string.scene_exetime_second_day) + Operators.BRACKET_END_STR + a(mScene.u.b, mScene.u.d));
        } else {
            TextView textView2 = this.mExeTimeTV;
            textView2.setText(a(mScene.u.f21526a, mScene.u.c) + "-" + a(mScene.u.b, mScene.u.d));
        }
        if (a() > 1) {
            this.mExeTimeTV.setText(R.string.scene_exetime_no_set);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isWarnningDevice(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (str.equalsIgnoreCase("chuangmi.plug.mi") || str.equalsIgnoreCase("chuangmi.plug.v1") || str.equalsIgnoreCase("chuangmi.plug.v2") || str.equalsIgnoreCase("chuangmi.plug.m1") || str.equalsIgnoreCase("lumi.plug.v1") || str.equalsIgnoreCase("chuangmi.ir.v2") || str.equalsIgnoreCase("qmi.powerstrip.v1") || str.equalsIgnoreCase("zimi.powerstrip.v2")) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void initConditionTitle() {
        this.mConditionTitle.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.std_home_icon_drop_down_arrow_r, 0);
        this.mConditionTitle.setCompoundDrawablePadding(DisplayUtils.a(3.0f));
        this.mConditionTitle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!SmartHomeSceneCreateEditActivity.this.q) {
                    if (SmartHomeSceneCreateEditActivity.this.mExpressView.getVisibility() == 8) {
                        ViewGroup.LayoutParams layoutParams = SmartHomeSceneCreateEditActivity.this.mExpressView.getLayoutParams();
                        layoutParams.height = SmartHomeSceneCreateEditActivity.this.mContainer.getHeight() - SmartHomeSceneCreateEditActivity.this.mTitleBar.getHeight();
                        SmartHomeSceneCreateEditActivity.this.mExpressView.setLayoutParams(layoutParams);
                        SmartHomeSceneCreateEditActivity.this.mExpressView.setVisibility(0);
                        if (SmartHomeSceneCreateEditActivity.mScene.q == 0) {
                            SmartHomeSceneCreateEditActivity.this.mExpressAll.setSelected(true);
                            SmartHomeSceneCreateEditActivity.this.mExpressAny.setSelected(false);
                        } else {
                            SmartHomeSceneCreateEditActivity.this.mExpressAll.setSelected(false);
                            SmartHomeSceneCreateEditActivity.this.mExpressAny.setSelected(true);
                        }
                        if (CreateSceneManager.a().b() || SmartHomeSceneCreateEditActivity.mScene.q == 0) {
                            SmartHomeSceneCreateEditActivity.this.mExpressAll.setEnabled(true);
                            SmartHomeSceneCreateEditActivity.this.mLockView.setVisibility(8);
                            SmartHomeSceneCreateEditActivity.this.mExpressAll.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View view) {
                                    SmartHomeSceneCreateEditActivity.mScene.q = 0;
                                    SmartHomeSceneCreateEditActivity.this.mConditionTitle.setText(R.string.scene_all_condition_satified);
                                    SmartHomeSceneCreateEditActivity.this.mExpressView.setVisibility(8);
                                    SmartHomeSceneCreateEditActivity.this.refreshUI(false);
                                }
                            });
                            return;
                        }
                        SmartHomeSceneCreateEditActivity.this.mExpressAll.setEnabled(true);
                        SmartHomeSceneCreateEditActivity.this.mLockView.setVisibility(0);
                        SmartHomeSceneCreateEditActivity.this.mExpressAll.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                Toast.makeText(SmartHomeSceneCreateEditActivity.this, R.string.scene_unclickable_toast_1, 0).show();
                            }
                        });
                        return;
                    }
                    SmartHomeSceneCreateEditActivity.this.mExpressView.setVisibility(8);
                }
            }
        });
        if (mScene.q == 0) {
            this.mConditionTitle.setText(R.string.scene_add_condition);
        } else {
            this.mConditionTitle.setText(R.string.scene_any_condition_satified);
        }
        this.n = (TextView) findViewById(R.id.warnning_text);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(this.n.getText().toString());
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(getResources().getColor(R.color.warnning_text_color_1));
        ForegroundColorSpan foregroundColorSpan2 = new ForegroundColorSpan(getResources().getColor(R.color.black_50_transparent));
        spannableStringBuilder.setSpan(foregroundColorSpan, 0, 5, 33);
        spannableStringBuilder.setSpan(foregroundColorSpan2, 5, this.n.getText().toString().length(), 33);
        this.n.setText(spannableStringBuilder);
    }

    /* JADX WARNING: Removed duplicated region for block: B:132:0x02fc  */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x0318  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x01d8  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x01ea  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void initDefaultScenes() {
        /*
            r15 = this;
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r0 = mScene
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
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            java.util.ArrayList<com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet> r7 = r15.d
            r8 = 0
            if (r7 == 0) goto L_0x0073
            java.util.ArrayList<com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet> r7 = r15.d
            int r7 = r7.size()
            if (r7 <= 0) goto L_0x0073
            java.util.ArrayList<com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet> r7 = r15.e
            java.util.ArrayList<com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet> r9 = r15.d
            r7.addAll(r9)
            java.util.ArrayList<com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet> r7 = r15.d
            int r7 = r7.size()
            boolean[] r7 = new boolean[r7]
            int r9 = r7.length
            r10 = 0
        L_0x006c:
            if (r10 >= r9) goto L_0x0074
            boolean r11 = r7[r10]
            int r10 = r10 + 1
            goto L_0x006c
        L_0x0073:
            r7 = r8
        L_0x0074:
            java.util.ArrayList<com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet> r9 = r15.g
            if (r9 == 0) goto L_0x0098
            java.util.ArrayList<com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet> r9 = r15.g
            int r9 = r9.size()
            if (r9 <= 0) goto L_0x0098
            java.util.ArrayList<com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet> r9 = r15.h
            java.util.ArrayList<com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet> r10 = r15.g
            r9.addAll(r10)
            java.util.ArrayList<com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet> r9 = r15.g
            int r9 = r9.size()
            boolean[] r9 = new boolean[r9]
            int r10 = r9.length
            r11 = 0
        L_0x0091:
            if (r11 >= r10) goto L_0x0099
            boolean r12 = r9[r11]
            int r11 = r11 + 1
            goto L_0x0091
        L_0x0098:
            r9 = r8
        L_0x0099:
            java.util.Iterator r0 = r0.iterator()
        L_0x009d:
            boolean r10 = r0.hasNext()
            if (r10 == 0) goto L_0x0147
            java.lang.Object r10 = r0.next()
            com.xiaomi.smarthome.device.Device r10 = (com.xiaomi.smarthome.device.Device) r10
            boolean r11 = r10.isOwner()
            if (r11 != 0) goto L_0x00b0
            goto L_0x009d
        L_0x00b0:
            java.util.ArrayList<com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet> r11 = r15.d
            if (r11 == 0) goto L_0x00f7
            r11 = 0
        L_0x00b5:
            java.util.ArrayList<com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet> r12 = r15.d
            int r12 = r12.size()
            if (r11 >= r12) goto L_0x00f7
            java.util.ArrayList<com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet> r12 = r15.d
            java.lang.Object r12 = r12.get(r11)
            com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet r12 = (com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity.DefaultSceneItemSet) r12
            java.lang.String[] r13 = r12.b
            if (r13 == 0) goto L_0x00f4
            java.lang.String r13 = r10.model
            java.lang.String[] r14 = r12.b
            boolean r13 = com.xiaomi.smarthome.device.DeviceFactory.a((java.lang.String) r13, (java.lang.String[]) r14)
            if (r13 == 0) goto L_0x00f4
            java.lang.Boolean r13 = r12.f21427a
            boolean r13 = r13.booleanValue()
            if (r13 == 0) goto L_0x00e2
            r2.add(r10)
            r5.add(r12)
            goto L_0x00ee
        L_0x00e2:
            boolean r13 = r7[r11]
            if (r13 != 0) goto L_0x00ee
            r2.add(r10)
            r5.add(r12)
            r7[r11] = r3
        L_0x00ee:
            java.util.ArrayList<com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet> r11 = r15.e
            r11.remove(r12)
            goto L_0x00f7
        L_0x00f4:
            int r11 = r11 + 1
            goto L_0x00b5
        L_0x00f7:
            java.util.ArrayList<com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet> r11 = r15.g
            if (r11 == 0) goto L_0x009d
            r11 = 0
        L_0x00fc:
            java.util.ArrayList<com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet> r12 = r15.g
            int r12 = r12.size()
            if (r11 >= r12) goto L_0x009d
            java.util.ArrayList<com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet> r12 = r15.g
            java.lang.Object r12 = r12.get(r11)
            com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet r12 = (com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity.DefaultSceneItemSet) r12
            java.lang.String[] r13 = r12.b
            if (r13 == 0) goto L_0x0144
            java.lang.String r13 = r10.model
            boolean r13 = android.text.TextUtils.isEmpty(r13)
            if (r13 != 0) goto L_0x0144
            java.lang.String r13 = r10.model
            java.lang.String[] r14 = r12.b
            boolean r13 = com.xiaomi.smarthome.device.DeviceFactory.a((java.lang.String) r13, (java.lang.String[]) r14)
            if (r13 == 0) goto L_0x0144
            java.lang.Boolean r13 = r12.f21427a
            boolean r13 = r13.booleanValue()
            if (r13 == 0) goto L_0x0131
            r4.add(r10)
            r6.add(r12)
            goto L_0x013d
        L_0x0131:
            boolean r13 = r9[r11]
            if (r13 != 0) goto L_0x013d
            r4.add(r10)
            r6.add(r12)
            r9[r11] = r3
        L_0x013d:
            java.util.ArrayList<com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet> r10 = r15.h
            r10.remove(r12)
            goto L_0x009d
        L_0x0144:
            int r11 = r11 + 1
            goto L_0x00fc
        L_0x0147:
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r0 = mScene
            java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Condition> r0 = r0.l
            int r0 = r0.size()
            r7 = -1
            if (r0 != 0) goto L_0x026e
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r0 = mScene
            r0.m = r3
            r0 = 0
        L_0x0157:
            int r3 = r2.size()
            if (r0 >= r3) goto L_0x0237
            java.lang.Object r3 = r2.get(r0)
            com.xiaomi.smarthome.device.Device r3 = (com.xiaomi.smarthome.device.Device) r3
            com.xiaomi.smarthome.scene.condition.BaseInnerCondition r3 = com.xiaomi.smarthome.scene.condition.BaseInnerCondition.a((com.xiaomi.smarthome.device.Device) r3)
            if (r3 == 0) goto L_0x0233
            java.lang.Object r9 = r5.get(r1)
            com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet r9 = (com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity.DefaultSceneItemSet) r9
            com.xiaomi.smarthome.device.api.RecommendSceneItem$Key[] r9 = r9.c
            if (r9 == 0) goto L_0x0219
            java.lang.Object r9 = r5.get(r1)
            com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet r9 = (com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity.DefaultSceneItemSet) r9
            com.xiaomi.smarthome.device.api.RecommendSceneItem$Key[] r9 = r9.c
            if (r9 == 0) goto L_0x0193
            java.lang.Object r9 = r5.get(r1)
            com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet r9 = (com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity.DefaultSceneItemSet) r9
            com.xiaomi.smarthome.device.api.RecommendSceneItem$Key[] r9 = r9.c
            int r9 = r9.length
            if (r9 <= 0) goto L_0x0193
            java.lang.Object r9 = r5.get(r1)
            com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet r9 = (com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity.DefaultSceneItemSet) r9
            com.xiaomi.smarthome.device.api.RecommendSceneItem$Key[] r9 = r9.c
            r9 = r9[r1]
            goto L_0x0194
        L_0x0193:
            r9 = r8
        L_0x0194:
            if (r9 == 0) goto L_0x01d5
            java.lang.String r10 = r9.mKey
            java.lang.Object r11 = r2.get(r1)
            com.xiaomi.smarthome.device.Device r11 = (com.xiaomi.smarthome.device.Device) r11
            java.lang.String r11 = r11.model
            boolean r10 = r10.contains(r11)
            if (r10 == 0) goto L_0x01a9
            java.lang.String r10 = r9.mKey
            goto L_0x01d6
        L_0x01a9:
            java.lang.Object r10 = r5.get(r1)
            com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet r10 = (com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity.DefaultSceneItemSet) r10
            java.lang.String[] r10 = r10.b
            int r11 = r10.length
            r12 = 0
        L_0x01b3:
            if (r12 >= r11) goto L_0x01d5
            r13 = r10[r12]
            java.lang.String r14 = r9.mKey
            boolean r14 = r14.contains(r13)
            if (r14 == 0) goto L_0x01d2
            java.lang.String r10 = r9.mKey
            java.lang.String r10 = java.lang.String.valueOf(r10)
            java.lang.Object r11 = r2.get(r1)
            com.xiaomi.smarthome.device.Device r11 = (com.xiaomi.smarthome.device.Device) r11
            java.lang.String r11 = r11.model
            java.lang.String r10 = r10.replace(r13, r11)
            goto L_0x01d6
        L_0x01d2:
            int r12 = r12 + 1
            goto L_0x01b3
        L_0x01d5:
            r10 = r8
        L_0x01d6:
            if (r10 != 0) goto L_0x01ea
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r9 = mScene
            java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Condition> r9 = r9.l
            int[] r10 = r3.f()
            r10 = r10[r1]
            com.xiaomi.smarthome.scene.api.SceneApi$Condition r3 = r3.a((int) r10, (android.content.Intent) r8)
            r9.add(r3)
            goto L_0x022a
        L_0x01ea:
            r11 = r3
            com.xiaomi.smarthome.scene.condition.InnerConditionCommon r11 = (com.xiaomi.smarthome.scene.condition.InnerConditionCommon) r11
            java.lang.Object r9 = r9.mValues
            int r9 = r11.a((java.lang.String) r10, (java.lang.Object) r9)
            if (r9 == r7) goto L_0x0207
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r10 = mScene
            java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Condition> r10 = r10.l
            int[] r11 = r3.f()
            r9 = r11[r9]
            com.xiaomi.smarthome.scene.api.SceneApi$Condition r3 = r3.a((int) r9, (android.content.Intent) r8)
            r10.add(r3)
            goto L_0x022a
        L_0x0207:
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r9 = mScene
            java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Condition> r9 = r9.l
            int[] r10 = r3.f()
            r10 = r10[r1]
            com.xiaomi.smarthome.scene.api.SceneApi$Condition r3 = r3.a((int) r10, (android.content.Intent) r8)
            r9.add(r3)
            goto L_0x022a
        L_0x0219:
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r9 = mScene
            java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Condition> r9 = r9.l
            int[] r10 = r3.f()
            r10 = r10[r1]
            com.xiaomi.smarthome.scene.api.SceneApi$Condition r3 = r3.a((int) r10, (android.content.Intent) r8)
            r9.add(r3)
        L_0x022a:
            boolean r3 = r15.c
            if (r3 != 0) goto L_0x0233
            java.util.ArrayList<com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet> r3 = r15.e
            r3.clear()
        L_0x0233:
            int r0 = r0 + 1
            goto L_0x0157
        L_0x0237:
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r0 = mScene
            java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Condition> r0 = r0.l
            java.util.Iterator r0 = r0.iterator()
        L_0x023f:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x0272
            java.lang.Object r2 = r0.next()
            com.xiaomi.smarthome.scene.api.SceneApi$Condition r2 = (com.xiaomi.smarthome.scene.api.SceneApi.Condition) r2
            com.xiaomi.smarthome.scene.CreateSceneManager r3 = com.xiaomi.smarthome.scene.CreateSceneManager.a()
            com.xiaomi.router.api.SceneManager r5 = com.xiaomi.router.api.SceneManager.x()
            int r9 = r2.k
            java.util.List r5 = r5.a((int) r9)
            r3.a((java.util.List<java.lang.Integer>) r5)
            com.xiaomi.smarthome.scene.CreateSceneManager r3 = com.xiaomi.smarthome.scene.CreateSceneManager.a()
            com.xiaomi.router.api.SceneManager r5 = com.xiaomi.router.api.SceneManager.x()
            int r2 = r2.k
            java.util.List r2 = r5.b((int) r2)
            r3.b((java.util.List<java.lang.Integer>) r2)
            goto L_0x023f
        L_0x026e:
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r0 = mScene
            r0.m = r1
        L_0x0272:
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r0 = mScene
            java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Action> r0 = r0.k
            int r0 = r0.size()
            if (r0 != 0) goto L_0x03c3
            r0 = 0
        L_0x027d:
            int r2 = r4.size()
            if (r0 >= r2) goto L_0x037d
            java.lang.Object r2 = r4.get(r0)
            com.xiaomi.smarthome.scene.action.BaseInnerAction r2 = com.xiaomi.smarthome.scene.SmartHomeScenceActionFactory.a(r2)
            if (r2 == 0) goto L_0x0379
            java.lang.Object r3 = r6.get(r0)
            com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet r3 = (com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity.DefaultSceneItemSet) r3
            com.xiaomi.smarthome.device.api.RecommendSceneItem$Key[] r3 = r3.c
            if (r3 == 0) goto L_0x035e
            java.lang.Object r3 = r6.get(r1)
            com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet r3 = (com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity.DefaultSceneItemSet) r3
            com.xiaomi.smarthome.device.api.RecommendSceneItem$Key[] r3 = r3.c
            if (r3 == 0) goto L_0x02b7
            java.lang.Object r3 = r6.get(r1)
            com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet r3 = (com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity.DefaultSceneItemSet) r3
            com.xiaomi.smarthome.device.api.RecommendSceneItem$Key[] r3 = r3.c
            int r3 = r3.length
            if (r3 <= 0) goto L_0x02b7
            java.lang.Object r3 = r6.get(r1)
            com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet r3 = (com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity.DefaultSceneItemSet) r3
            com.xiaomi.smarthome.device.api.RecommendSceneItem$Key[] r3 = r3.c
            r3 = r3[r1]
            goto L_0x02b8
        L_0x02b7:
            r3 = r8
        L_0x02b8:
            if (r3 == 0) goto L_0x02f9
            java.lang.String r5 = r3.mKey
            java.lang.Object r9 = r4.get(r0)
            com.xiaomi.smarthome.device.Device r9 = (com.xiaomi.smarthome.device.Device) r9
            java.lang.String r9 = r9.model
            boolean r5 = r5.contains(r9)
            if (r5 == 0) goto L_0x02cd
            java.lang.String r5 = r3.mKey
            goto L_0x02fa
        L_0x02cd:
            java.lang.Object r5 = r6.get(r0)
            com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet r5 = (com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity.DefaultSceneItemSet) r5
            java.lang.String[] r5 = r5.b
            int r9 = r5.length
            r10 = 0
        L_0x02d7:
            if (r10 >= r9) goto L_0x02f9
            r11 = r5[r10]
            java.lang.String r12 = r3.mKey
            boolean r12 = r12.contains(r11)
            if (r12 == 0) goto L_0x02f6
            java.lang.String r5 = r3.mKey
            java.lang.String r5 = java.lang.String.valueOf(r5)
            java.lang.Object r9 = r4.get(r0)
            com.xiaomi.smarthome.device.Device r9 = (com.xiaomi.smarthome.device.Device) r9
            java.lang.String r9 = r9.model
            java.lang.String r5 = r5.replace(r11, r9)
            goto L_0x02fa
        L_0x02f6:
            int r10 = r10 + 1
            goto L_0x02d7
        L_0x02f9:
            r5 = r8
        L_0x02fa:
            if (r5 != 0) goto L_0x0318
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r3 = mScene
            java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Action> r3 = r3.k
            java.lang.String[] r5 = r2.b()
            r5 = r5[r1]
            int[] r9 = r2.c()
            r9 = r9[r1]
            java.lang.Object r10 = r4.get(r0)
            com.xiaomi.smarthome.scene.api.SceneApi$Action r2 = r2.a(r5, r9, r10, r8)
            r3.add(r2)
            goto L_0x0379
        L_0x0318:
            java.lang.Object r3 = r3.mValues
            java.lang.Object r9 = r4.get(r0)
            com.xiaomi.smarthome.device.Device r9 = (com.xiaomi.smarthome.device.Device) r9
            int r3 = r2.a(r5, r3, r9)
            if (r3 == r7) goto L_0x0342
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r5 = mScene
            java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Action> r5 = r5.k
            java.lang.String[] r9 = r2.b()
            r9 = r9[r3]
            int[] r10 = r2.c()
            r3 = r10[r3]
            java.lang.Object r10 = r4.get(r0)
            com.xiaomi.smarthome.scene.api.SceneApi$Action r2 = r2.a(r9, r3, r10, r8)
            r5.add(r2)
            goto L_0x0379
        L_0x0342:
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r3 = mScene
            java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Action> r3 = r3.k
            java.lang.String[] r5 = r2.b()
            r5 = r5[r1]
            int[] r9 = r2.c()
            r9 = r9[r1]
            java.lang.Object r10 = r4.get(r0)
            com.xiaomi.smarthome.scene.api.SceneApi$Action r2 = r2.a(r5, r9, r10, r8)
            r3.add(r2)
            goto L_0x0379
        L_0x035e:
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r3 = mScene
            java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Action> r3 = r3.k
            java.lang.String[] r5 = r2.b()
            r5 = r5[r1]
            int[] r9 = r2.c()
            r9 = r9[r1]
            java.lang.Object r10 = r4.get(r0)
            com.xiaomi.smarthome.scene.api.SceneApi$Action r2 = r2.a(r5, r9, r10, r8)
            r3.add(r2)
        L_0x0379:
            int r0 = r0 + 1
            goto L_0x027d
        L_0x037d:
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r0 = mScene
            java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Action> r0 = r0.k
            java.util.Iterator r0 = r0.iterator()
        L_0x0385:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x03b4
            java.lang.Object r1 = r0.next()
            com.xiaomi.smarthome.scene.api.SceneApi$Action r1 = (com.xiaomi.smarthome.scene.api.SceneApi.Action) r1
            com.xiaomi.smarthome.scene.CreateSceneManager r2 = com.xiaomi.smarthome.scene.CreateSceneManager.a()
            com.xiaomi.router.api.SceneManager r3 = com.xiaomi.router.api.SceneManager.x()
            int r5 = r1.d
            java.util.List r3 = r3.a((int) r5)
            r2.a((java.util.List<java.lang.Integer>) r3)
            com.xiaomi.smarthome.scene.CreateSceneManager r2 = com.xiaomi.smarthome.scene.CreateSceneManager.a()
            com.xiaomi.router.api.SceneManager r3 = com.xiaomi.router.api.SceneManager.x()
            int r1 = r1.d
            java.util.List r1 = r3.b((int) r1)
            r2.b((java.util.List<java.lang.Integer>) r1)
            goto L_0x0385
        L_0x03b4:
            int r0 = r4.size()
            if (r0 <= 0) goto L_0x03c3
            boolean r0 = r15.f
            if (r0 != 0) goto L_0x03c3
            java.util.ArrayList<com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity$DefaultSceneItemSet> r0 = r15.h
            r0.clear()
        L_0x03c3:
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r0 = mScene
            java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Condition> r0 = r0.l
            int r0 = r0.size()
            if (r0 <= 0) goto L_0x03f8
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r0 = r15.mSceneSrc
            java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Condition> r0 = r0.l
            r0.clear()
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r0 = mScene
            java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Condition> r0 = r0.l
            java.util.Iterator r0 = r0.iterator()
        L_0x03dc:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x03f8
            java.lang.Object r1 = r0.next()
            com.xiaomi.smarthome.scene.api.SceneApi$Condition r1 = (com.xiaomi.smarthome.scene.api.SceneApi.Condition) r1
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r2 = r15.mSceneSrc     // Catch:{ JSONException -> 0x03dc }
            java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Condition> r2 = r2.l     // Catch:{ JSONException -> 0x03dc }
            org.json.JSONObject r1 = r1.a()     // Catch:{ JSONException -> 0x03dc }
            com.xiaomi.smarthome.scene.api.SceneApi$Condition r1 = com.xiaomi.smarthome.scene.api.SceneApi.Condition.a((org.json.JSONObject) r1)     // Catch:{ JSONException -> 0x03dc }
            r2.add(r1)     // Catch:{ JSONException -> 0x03dc }
            goto L_0x03dc
        L_0x03f8:
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r0 = mScene
            java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Action> r0 = r0.k
            int r0 = r0.size()
            if (r0 <= 0) goto L_0x042d
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r0 = r15.mSceneSrc
            java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Action> r0 = r0.k
            r0.clear()
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r0 = mScene
            java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Action> r0 = r0.k
            java.util.Iterator r0 = r0.iterator()
        L_0x0411:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x042d
            java.lang.Object r1 = r0.next()
            com.xiaomi.smarthome.scene.api.SceneApi$Action r1 = (com.xiaomi.smarthome.scene.api.SceneApi.Action) r1
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r2 = r15.mSceneSrc     // Catch:{ JSONException -> 0x0411 }
            java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Action> r2 = r2.k     // Catch:{ JSONException -> 0x0411 }
            org.json.JSONObject r1 = r1.a()     // Catch:{ JSONException -> 0x0411 }
            com.xiaomi.smarthome.scene.api.SceneApi$Action r1 = com.xiaomi.smarthome.scene.api.SceneApi.Action.a(r1)     // Catch:{ JSONException -> 0x0411 }
            r2.add(r1)     // Catch:{ JSONException -> 0x0411 }
            goto L_0x0411
        L_0x042d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity.initDefaultScenes():void");
    }

    /* access modifiers changed from: package-private */
    public void showBuyDialog() {
        new MLAlertDialog.Builder(this).b((int) R.string.no_device_title).a((int) R.string.go_to_buy, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                SmartHomeSceneCreateEditActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://m.mi.com")));
            }
        }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }).d();
    }

    /* access modifiers changed from: private */
    public void g() {
        new MLAlertDialog.Builder(this).b((int) R.string.delete_old_time_span_tips).a((int) R.string.delete, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                for (SceneApi.Condition next : SmartHomeSceneCreateEditActivity.mScene.l) {
                    if (next.c != null) {
                        next.c.e = -1;
                        next.c.f = -1;
                        next.c.g = -1;
                        next.c.h = -1;
                        next.c.i = null;
                    }
                }
                if (SmartHomeSceneCreateEditActivity.mScene.u == null) {
                    SmartHomeSceneCreateEditActivity.mScene.u = new SceneApi.EffectiveTimeSpan();
                }
                SmartHomeSceneCreateEditActivity.mScene.v = true;
                SmartHomeSceneCreateEditActivity.this.mExeTimeRL.setVisibility(0);
                SmartHomeSceneCreateEditActivity.this.mExeTimeRL.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        SceneLogUtil.a("启动生效时间段页面");
                        SmartHomeSceneCreateEditActivity.this.startActivity(new Intent(SmartHomeSceneCreateEditActivity.this.mContext, SceneCreateTimeEdit2Activity.class));
                        StatHelper.g();
                    }
                });
                SmartHomeSceneCreateEditActivity.this.refreshUI(false);
            }
        }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }).b().show();
    }

    private String a(int i2, int i3) {
        StringBuilder sb = new StringBuilder();
        if (i2 >= 0 && i2 <= 9) {
            sb.append("0");
        }
        sb.append(i2);
        sb.append(":");
        if (i3 >= 0 && i3 <= 9) {
            sb.append("0");
        }
        sb.append(i3);
        return sb.toString();
    }
}
