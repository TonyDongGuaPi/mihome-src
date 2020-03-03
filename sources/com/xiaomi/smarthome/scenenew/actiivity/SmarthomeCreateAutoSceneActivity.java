package com.xiaomi.smarthome.scenenew.actiivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.facebook.drawee.view.SimpleDraweeView;
import com.h6ah4i.android.widget.advrecyclerview.animator.SwipeDismissItemAnimator;
import com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.draggable.ItemDraggableRange;
import com.h6ah4i.android.widget.advrecyclerview.draggable.RecyclerViewDragDropManager;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractDraggableSwipeableItemViewHolder;
import com.taobao.weex.el.parse.Operators;
import com.umeng.analytics.MobclickAgent;
import com.xiaomi.dragdrop.annotation.RecyclerViewTouchActionGuardManager;
import com.xiaomi.mistatistic.sdk.MiStatInterface;
import com.xiaomi.router.api.SceneManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.SmartHomeConfig;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.config.SHConfig;
import com.xiaomi.smarthome.core.entity.statistic.StatType;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.MessageCallback;
import com.xiaomi.smarthome.device.api.SceneInfo;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.device.bluetooth.utils.BluetoothConstants;
import com.xiaomi.smarthome.device.utils.ClientRemarkInputView;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.statistic.MiStatType;
import com.xiaomi.smarthome.framework.statistic.StatHelper;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.HomeVirtualDeviceHelper;
import com.xiaomi.smarthome.homeroom.MultiHomeDeviceManager;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.network.NetworkUtils;
import com.xiaomi.smarthome.library.common.util.CommonUtils;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.util.StringUtil;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.lite.scene.HomeSceneScrollView;
import com.xiaomi.smarthome.lite.scene.LiteSceneManager;
import com.xiaomi.smarthome.lite.scene.SceneLogUtil;
import com.xiaomi.smarthome.newui.NameEditDialogHelper;
import com.xiaomi.smarthome.scene.CommonSceneOnline;
import com.xiaomi.smarthome.scene.CreateSceneManager;
import com.xiaomi.smarthome.scene.SceneCreateTimeEdit2Activity;
import com.xiaomi.smarthome.scene.SceneMoreActivity;
import com.xiaomi.smarthome.scene.SceneUseLicnece;
import com.xiaomi.smarthome.scene.SmartHomeSceneActionChooseActivity;
import com.xiaomi.smarthome.scene.SmartHomeSceneTimerActivity;
import com.xiaomi.smarthome.scene.SmartHomeSceneTimerDelay;
import com.xiaomi.smarthome.scene.SmartHomeSceneUtility;
import com.xiaomi.smarthome.scene.StartConditionActivityNew;
import com.xiaomi.smarthome.scene.api.RemoteSceneApi;
import com.xiaomi.smarthome.scene.api.SceneApi;
import com.xiaomi.smarthome.scene.condition.WeatherInnerCondition;
import com.xiaomi.smarthome.scenenew.actiivity.SmarthomeCreateAutoSceneActivity;
import com.xiaomi.smarthome.scenenew.lumiscene.LocalSceneBuilder;
import com.xiaomi.smarthome.scenenew.lumiscene.SceneExtraBuilder;
import com.xiaomi.smarthome.scenenew.manager.RecommendSceneManager;
import com.xiaomi.smarthome.scenenew.pluginrecommend.CreateSceneFromRecommendActivity;
import com.xiaomi.smarthome.scenenew.pluginrecommend.LightActionStartActivity;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

public class SmarthomeCreateAutoSceneActivity extends BaseActivity {
    public static final String EXTRA_DEVICE_ID = "extra_device_id";
    public static final String EXTRA_IS_LITE = "extra_is_lite";
    public static final String EXTRA_RECOMMEND_SCENE_ID = "extra_recommend_scene_id";
    public static final String EXTRA_SCENE_ID = "extra_scene_id";
    public static final String EXTRA_SCENE_STAT_FROM = "scene_stat_from";
    public static final String FROM = "from";
    public static final String FROM_MAIN = "from_main_page";
    public static final int FROM_PLUGIN = 1;
    public static final int MAX_CONDITION_NUM = 10;
    public static final int MAX_LITE_SCENE_NUM = 256;
    public static final int MIN_IN_HOUR = 60;
    public static final int REQUEST_CODE_CONDITION = 1;
    public static final int REQUEST_CODE_TASK = 2;
    public static final int SCENE_MORE_PAGE = 1000;
    public static final String SCENE_STATUS_UPDATE = "scene_status_update";

    /* renamed from: a  reason: collision with root package name */
    private static final String f21843a = "SmarthomeCreateAutoSceneActivity";
    private static final int b = 100;
    private static final int c = 10000;
    private static final int d = 101;
    public static boolean isNewScene;
    public static boolean mIsInitStep;
    public static SceneApi.SmartHomeScene mScene;
    /* access modifiers changed from: private */
    public int e = 0;
    private boolean f = false;
    /* access modifiers changed from: private */
    public XQProgressDialog g;
    private MLAlertDialog h;
    private String i;
    int[] items = {R.string.smarthome_scene_support_all, R.string.smarthome_scene_support_any};
    private int j = -1;
    private SelectModeAdapter k = new SelectModeAdapter();
    /* access modifiers changed from: private */
    public boolean l = false;
    private boolean m = false;
    ActionAdapter mActionAdapter;
    RecyclerView.Adapter mActionAdapterWrap;
    boolean[] mActionOnlineItems;
    @BindView(2131427753)
    RecyclerView mActionRV;
    @BindView(2131427600)
    TextView mAddActionTV;
    @BindView(2131427601)
    TextView mAddContionTV;
    ConditionAdapter mConditionAdapter;
    RecyclerView.Adapter mConditionAdapterWrap;
    boolean[] mConditionOnlineItems;
    @BindView(2131427755)
    RecyclerView mConditionRV;
    @BindView(2131430989)
    ImageButton mConfirmBtn;
    Context mContext;
    @BindView(2131428959)
    Button mEditCancaelBtn;
    @BindView(2131428961)
    Button mEditCompleteBtn;
    @BindView(2131429074)
    TextView mExeTimeHint;
    @BindView(2131432143)
    RelativeLayout mExeTimeRL;
    @BindView(2131432144)
    TextView mExeTimeTV;
    @BindView(2131429527)
    TextView mHandTV;
    Handler mHandler = new Handler() {
        public void handleMessage(Message message) {
            LogUtil.a(SmarthomeCreateAutoSceneActivity.f21843a, "msg.what" + message.what);
            switch (message.what) {
                case 100:
                    SmarthomeCreateAutoSceneActivity.this.mHandler.post(new Runnable() {
                        public final void run() {
                            SmarthomeCreateAutoSceneActivity.AnonymousClass1.this.a();
                        }
                    });
                    return;
                case 101:
                    SmarthomeCreateAutoSceneActivity.this.onFinishSaved(false);
                    return;
                default:
                    return;
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a() {
            if (SmarthomeCreateAutoSceneActivity.this.isValid()) {
                SmarthomeCreateAutoSceneActivity.this.g.dismiss();
            }
            Toast.makeText(SmarthomeCreateAutoSceneActivity.this.mContext, R.string.smarthome_scene_set_fail, 0).show();
        }
    };
    LayoutInflater mInflater;
    @BindView(2131430988)
    TextView mModuleA4MoreBtn;
    @BindView(2131430990)
    ImageView mModuleA4ReturnBtn;
    @BindView(2131430997)
    TextView mModuleA4ReturnTitle;
    SceneApi.SmartHomeScene mSceneBeforeEdit;
    String mSceneID;
    SceneApi.SmartHomeScene mSceneSrc;
    @BindView(2131429647)
    HomeSceneScrollView mScrollView;
    private SceneManager.IScenceListener n = new SceneManager.IScenceListener() {
        public void onRefreshScenceSuccess(int i) {
            if (i == 5) {
                SceneManager.x().b((SceneManager.IScenceListener) this);
                SmarthomeCreateAutoSceneActivity.this.completeScene();
            }
        }

        public void onRefreshScenceFailed(int i) {
            SceneManager.x().b((SceneManager.IScenceListener) this);
            ToastUtil.a((int) R.string.smarthome_scene_set_fail);
        }
    };
    private SceneApi.ConditionDevice o = null;
    int[] res = {R.drawable.intelligence_icon_if_nor, R.drawable.intelligence_icon_ifonly_nor};
    int[] resSelect = {R.drawable.intelligence_icon_if_hig, R.drawable.intelligence_icon_ifonly_hig};

    /* access modifiers changed from: private */
    public static /* synthetic */ void a(DialogInterface dialogInterface, int i2) {
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void c(DialogInterface dialogInterface, int i2) {
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void g(DialogInterface dialogInterface, int i2) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_create_auto_scene_layout);
        ButterKnife.bind((Activity) this);
        this.mInflater = LayoutInflater.from(this);
        this.mContext = this;
        if (!SceneManager.x().p()) {
            SceneManager.x().a();
        }
        if (getIntent() != null && getIntent().hasExtra("from")) {
            this.e = getIntent().getIntExtra("from", 0);
        }
        if (getIntent() != null && getIntent().hasExtra(FROM_MAIN)) {
            this.f = getIntent().getBooleanExtra(FROM_MAIN, false);
        }
        CreateSceneManager.a().a((Room) null);
        b();
        a();
        g();
        refreshUI();
    }

    private void a() {
        this.mModuleA4MoreBtn.setContentDescription(getString(R.string.scene2_more));
        if (isNewScene) {
            this.mModuleA4ReturnTitle.setText(R.string.smarthome_create_new_auto_scene);
        } else {
            this.mModuleA4ReturnTitle.setText(mScene.g);
        }
        this.mAddContionTV.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                SmarthomeCreateAutoSceneActivity.this.k(view);
            }
        });
        this.mAddActionTV.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                SmarthomeCreateAutoSceneActivity.this.j(view);
            }
        });
        this.mModuleA4ReturnBtn.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                SmarthomeCreateAutoSceneActivity.this.i(view);
            }
        });
        this.mEditCancaelBtn.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                SmarthomeCreateAutoSceneActivity.this.h(view);
            }
        });
        this.mEditCompleteBtn.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                SmarthomeCreateAutoSceneActivity.this.g(view);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void k(View view) {
        a(true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void j(View view) {
        j();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void i(View view) {
        c();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void h(View view) {
        b(false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void g(View view) {
        b(true);
    }

    private void b() {
        this.mSceneSrc = CreateSceneManager.a().g();
        Intent intent = getIntent();
        this.mSceneID = intent.getStringExtra("extra_scene_id");
        this.i = intent.getStringExtra(EXTRA_DEVICE_ID);
        if (!TextUtils.isEmpty(this.mSceneID)) {
            this.mSceneSrc = SceneManager.x().e(this.mSceneID);
        }
        if (this.mSceneSrc == null) {
            isNewScene = true;
            mIsInitStep = true;
            this.mSceneID = null;
            this.mSceneSrc = new SceneApi.SmartHomeScene();
            this.mSceneSrc.u = new SceneApi.EffectiveTimeSpan();
            mScene = new SceneApi.SmartHomeScene();
            mScene.u = new SceneApi.EffectiveTimeSpan();
            LogUtil.a(f21843a, "mScene mIsLite:" + mScene.t);
            CreateSceneManager.a().b = 1;
        } else {
            this.mSceneID = this.mSceneSrc.f;
            try {
                mScene = SceneApi.SmartHomeScene.a(this.mSceneSrc.a(), this.mSceneSrc.t);
                if (mScene != null) {
                    mScene.t = this.mSceneSrc.t;
                    LogUtil.a(f21843a, "mIsLite:" + mScene.t);
                    isNewScene = false;
                    mIsInitStep = false;
                    CreateSceneManager.a().b = 0;
                    if (this.mSceneSrc.i > 0) {
                        if (this.mSceneSrc.i / 1000 != 2) {
                            switch (this.mSceneSrc.i) {
                                case 1000:
                                    if (SmartHomeDeviceManager.a().b(this.mSceneSrc.l.get(0).c.f21523a) != null) {
                                        Intent intent2 = new Intent(this, PluginRecommendSceneActivity.class);
                                        intent2.putExtra("sr_id", this.mSceneSrc.i);
                                        if (!(this.mSceneSrc.l == null || this.mSceneSrc.l.size() <= 0 || this.mSceneSrc.l.get(0).c == null)) {
                                            intent2.putExtra("did", this.mSceneSrc.l.get(0).c.f21523a);
                                        }
                                        startActivity(intent2);
                                        finish();
                                        return;
                                    }
                                    break;
                                case 1001:
                                case 1002:
                                case 1003:
                                    if (SmartHomeDeviceManager.a().b(this.mSceneSrc.k.get(0).g.e) != null) {
                                        Intent intent3 = new Intent(this, LightActionStartActivity.class);
                                        intent3.putExtra("sr_id", this.mSceneSrc.i);
                                        if (this.mSceneSrc.k != null && this.mSceneSrc.k.size() > 0 && this.mSceneSrc.k.get(0).g != null && this.mSceneSrc.k.get(0).f21521a == 0) {
                                            intent3.putExtra("did", this.mSceneSrc.k.get(0).g.e);
                                        }
                                        startActivity(intent3);
                                        finish();
                                        return;
                                    }
                                    break;
                            }
                        } else {
                            Intent intent4 = new Intent(this, CreateSceneFromRecommendActivity.class);
                            intent4.putExtra("sr_id", this.mSceneSrc.i);
                            if (this.mSceneSrc.i % 2 == 0) {
                                if (!(this.mSceneSrc.l == null || this.mSceneSrc.l.size() <= 0 || this.mSceneSrc.l.get(0).c == null)) {
                                    intent4.putExtra("did", this.mSceneSrc.l.get(0).c.f21523a);
                                }
                            } else if (!(this.mSceneSrc.k == null || this.mSceneSrc.k.size() <= 0 || this.mSceneSrc.k.get(0).g == null)) {
                                intent4.putExtra("did", this.mSceneSrc.k.get(0).g.e);
                            }
                            startActivity(intent4);
                            finish();
                            return;
                        }
                    }
                } else {
                    finish();
                    return;
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        CreateSceneManager.a().d(mScene);
        this.j = intent.getIntExtra("extra_recommend_scene_id", -1);
        getFrom(intent);
    }

    public void getFrom(Intent intent) {
        if (intent.getExtras() != null) {
            String string = intent.getExtras().getString("scene_stat_from", "");
            if (TextUtils.isEmpty(string)) {
                return;
            }
            if (this.j != -1) {
                CoreApi a2 = CoreApi.a();
                StatType statType = StatType.EVENT;
                a2.a(statType, "value", "" + this.j, (String) null, false);
                return;
            }
            MobclickAgent.a(this.mContext, MiStatType.CLICK.getValue(), string);
        }
    }

    private void c() {
        boolean z = true;
        if (!(this.mSceneSrc == null || mScene == null || !mScene.a(this.mSceneSrc))) {
            z = false;
        }
        if (z) {
            new MLAlertDialog.Builder(this.mContext).b((int) R.string.smarthome_scene_quit).a((int) R.string.smarthome_scene_quest_ok, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public final void onClick(DialogInterface dialogInterface, int i) {
                    SmarthomeCreateAutoSceneActivity.this.h(dialogInterface, i);
                }
            }).b((int) R.string.cancel, (DialogInterface.OnClickListener) $$Lambda$SmarthomeCreateAutoSceneActivity$QsVlSJS542OIBCxhbbk0DSg22k.INSTANCE).d();
        } else {
            finish();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void h(DialogInterface dialogInterface, int i2) {
        finish();
    }

    private void d() {
        LogUtil.a(f21843a, "misinitsetp" + mIsInitStep + "   mCurrentStep  " + CreateSceneManager.a().b);
        if (mIsInitStep) {
            if (CreateSceneManager.a().b == 1) {
                if (this.f) {
                    t();
                } else {
                    a(true);
                }
                CreateSceneManager.a().b = 2;
            } else if (CreateSceneManager.a().b == 2) {
                j();
                CreateSceneManager.a().b = 0;
            } else if (CreateSceneManager.a().b == 4) {
                mScene.l.clear();
                CreateSceneManager.a().d(mScene);
                if (!this.f) {
                    a(false);
                    CreateSceneManager.a().b = 2;
                    return;
                }
                finish();
                CreateSceneManager.a().b = 0;
            } else if (CreateSceneManager.a().b == 3) {
                finish();
                CreateSceneManager.a().b = 0;
            } else if (CreateSceneManager.a().b == 0) {
                mIsInitStep = false;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void refreshUI() {
        f();
        e();
        r();
        initOnlineAndAvatarResource();
        this.mConditionAdapter.notifyDataSetChanged();
        this.mActionAdapter.notifyDataSetChanged();
        if (mScene != null) {
            CreateSceneManager.a().b(mScene);
            CreateSceneManager.a().c(mScene);
        }
    }

    private void e() {
        if (mScene == null || mScene.l == null || mScene.l.size() < 2) {
            this.mHandTV.setText(R.string.smarthome_scene_create_if);
            Drawable drawable = this.mContext.getResources().getDrawable(this.res[0]);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            this.mHandTV.setCompoundDrawables(drawable, (Drawable) null, (Drawable) null, (Drawable) null);
        } else if (mScene.q == 0) {
            this.mHandTV.setText(R.string.smarthome_scene_support_all);
            Drawable drawable2 = this.mContext.getResources().getDrawable(this.res[mScene.q]);
            drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
            this.mHandTV.setCompoundDrawables(drawable2, (Drawable) null, (Drawable) null, (Drawable) null);
        } else if (mScene.q == 1) {
            this.mHandTV.setText(R.string.smarthome_scene_support_any);
            Drawable drawable3 = this.mContext.getResources().getDrawable(this.res[mScene.q]);
            drawable3.setBounds(0, 0, drawable3.getMinimumWidth(), drawable3.getMinimumHeight());
            this.mHandTV.setCompoundDrawables(drawable3, (Drawable) null, (Drawable) null, (Drawable) null);
        }
    }

    private void f() {
        if (this.l) {
            this.mEditCancaelBtn.setVisibility(0);
            this.mEditCompleteBtn.setVisibility(0);
            this.mConfirmBtn.setVisibility(8);
            this.mModuleA4MoreBtn.setVisibility(8);
            this.mModuleA4ReturnBtn.setVisibility(8);
        } else {
            this.mEditCancaelBtn.setVisibility(8);
            this.mEditCompleteBtn.setVisibility(8);
            this.mModuleA4ReturnBtn.setVisibility(0);
            if (TextUtils.isEmpty(this.mSceneID) || !mScene.a(this.mSceneSrc)) {
                this.mConfirmBtn.setVisibility(0);
                this.mModuleA4MoreBtn.setVisibility(8);
            } else {
                this.mModuleA4MoreBtn.setVisibility(0);
                this.mConfirmBtn.setVisibility(8);
                this.mModuleA4MoreBtn.setText("");
                this.mModuleA4MoreBtn.setBackgroundResource(R.drawable.std_tittlebar_main_device_more);
            }
        }
        if (mScene.l.size() <= 0 || mScene.k.size() <= 0) {
            this.mConfirmBtn.setEnabled(false);
            return;
        }
        this.mConfirmBtn.setEnabled(true);
        if (mScene.k.size() == 1 && (mScene.k.get(0).g instanceof SceneApi.SHSceneDelayPayload)) {
            this.mConfirmBtn.setEnabled(false);
        }
    }

    private void g() {
        h();
        i();
        this.mScrollView.setListener($$Lambda$SmarthomeCreateAutoSceneActivity$3Vrf7L98vACQLS5YqTtn5BJpqPU.INSTANCE);
    }

    private void h() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        RecyclerViewDragDropManager recyclerViewDragDropManager = new RecyclerViewDragDropManager();
        recyclerViewDragDropManager.c(true);
        recyclerViewDragDropManager.a((NinePatchDrawable) ContextCompat.getDrawable(getContext(), R.drawable.std_list_item_drag_shadow));
        recyclerViewDragDropManager.a(true);
        recyclerViewDragDropManager.a((int) (((float) ViewConfiguration.getLongPressTimeout()) * 0.5f));
        RecyclerViewTouchActionGuardManager recyclerViewTouchActionGuardManager = new RecyclerViewTouchActionGuardManager();
        recyclerViewTouchActionGuardManager.b(true);
        recyclerViewTouchActionGuardManager.a(true);
        this.mConditionAdapter = new ConditionAdapter();
        this.mConditionAdapterWrap = recyclerViewDragDropManager.a((RecyclerView.Adapter) this.mConditionAdapter);
        this.mConditionRV.setLayoutManager(linearLayoutManager);
        this.mConditionRV.setAdapter(this.mConditionAdapterWrap);
        SwipeDismissItemAnimator swipeDismissItemAnimator = new SwipeDismissItemAnimator();
        swipeDismissItemAnimator.setSupportsChangeAnimations(false);
        this.mConditionRV.setItemAnimator(swipeDismissItemAnimator);
        recyclerViewTouchActionGuardManager.a(this.mConditionRV);
        recyclerViewDragDropManager.a(this.mConditionRV);
        recyclerViewDragDropManager.b(true);
    }

    private void i() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        RecyclerViewDragDropManager recyclerViewDragDropManager = new RecyclerViewDragDropManager();
        recyclerViewDragDropManager.c(true);
        recyclerViewDragDropManager.a((NinePatchDrawable) ContextCompat.getDrawable(getContext(), R.drawable.std_list_item_drag_shadow));
        recyclerViewDragDropManager.a(true);
        recyclerViewDragDropManager.a((int) (((float) ViewConfiguration.getLongPressTimeout()) * 0.5f));
        RecyclerViewTouchActionGuardManager recyclerViewTouchActionGuardManager = new RecyclerViewTouchActionGuardManager();
        recyclerViewTouchActionGuardManager.b(true);
        recyclerViewTouchActionGuardManager.a(true);
        this.mActionAdapter = new ActionAdapter();
        this.mActionAdapterWrap = recyclerViewDragDropManager.a((RecyclerView.Adapter) this.mActionAdapter);
        this.mActionRV.setLayoutManager(linearLayoutManager);
        this.mActionRV.setAdapter(this.mActionAdapterWrap);
        SwipeDismissItemAnimator swipeDismissItemAnimator = new SwipeDismissItemAnimator();
        swipeDismissItemAnimator.setSupportsChangeAnimations(false);
        this.mActionRV.setItemAnimator(swipeDismissItemAnimator);
        recyclerViewTouchActionGuardManager.a(this.mActionRV);
        recyclerViewDragDropManager.a(this.mActionRV);
        recyclerViewDragDropManager.b(true);
    }

    private void a(boolean z) {
        if (mScene.l.size() >= 10) {
            Toast.makeText(this, R.string.add_condition_error, 0).show();
        } else if (CreateSceneManager.a().c(mScene.l)) {
            ToastUtil.a((CharSequence) getString(R.string.smarthome_is_lite_scene));
        } else if (mScene.l.size() == 1) {
            a(mScene.q);
        } else {
            Intent intent = new Intent(this.mContext, StartConditionActivityNew.class);
            intent.putExtra("from", this.e);
            startActivityForResult(intent, 1);
            if (!z) {
                overridePendingTransition(0, 0);
            }
            CreateSceneManager.a().l();
        }
    }

    class SelectModeAdapter extends BaseAdapter {

        /* renamed from: a  reason: collision with root package name */
        int f21862a = -1;

        public long getItemId(int i) {
            return (long) i;
        }

        SelectModeAdapter() {
        }

        public void a(int i) {
            this.f21862a = i;
        }

        public int getCount() {
            return SmarthomeCreateAutoSceneActivity.this.items.length;
        }

        public Object getItem(int i) {
            return Integer.valueOf(SmarthomeCreateAutoSceneActivity.this.items[i]);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = SmarthomeCreateAutoSceneActivity.this.mInflater.inflate(R.layout.smarthome_select_scene_mode_dialog_item, viewGroup, false);
            }
            ImageView imageView = (ImageView) view.findViewById(R.id.right_arrow);
            SimpleDraweeView simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.room_icon);
            TextView textView = (TextView) view.findViewById(R.id.room_name);
            textView.setText(SmarthomeCreateAutoSceneActivity.this.items[i]);
            if (i == this.f21862a) {
                imageView.setVisibility(0);
                simpleDraweeView.setImageURI(CommonUtils.c(SmarthomeCreateAutoSceneActivity.this.resSelect[i]));
                textView.setTextColor(ContextCompat.getColor(SmarthomeCreateAutoSceneActivity.this.mContext, R.color.class_text_17));
            } else {
                imageView.setVisibility(4);
                simpleDraweeView.setImageURI(CommonUtils.c(SmarthomeCreateAutoSceneActivity.this.res[i]));
                textView.setTextColor(ContextCompat.getColor(SmarthomeCreateAutoSceneActivity.this.mContext, R.color.class_V));
            }
            return view;
        }
    }

    private void a(int i2) {
        this.k.a(i2);
        new MLAlertDialog.Builder(this).a((ListAdapter) this.k, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                SmarthomeCreateAutoSceneActivity.this.f(dialogInterface, i);
            }
        }).a((int) R.string.scene_choose_scene_mode_dialog_title).b().show();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void f(DialogInterface dialogInterface, int i2) {
        if (i2 == 0) {
            mScene.q = 0;
            Intent intent = new Intent(this.mContext, StartConditionActivityNew.class);
            intent.putExtra("from", this.e);
            startActivityForResult(intent, 1);
            CreateSceneManager.a().l();
        } else if (i2 == 1) {
            mScene.q = 1;
            Intent intent2 = new Intent(this.mContext, StartConditionActivityNew.class);
            intent2.putExtra("from", this.e);
            startActivityForResult(intent2, 1);
            CreateSceneManager.a().l();
        }
        refreshUI();
    }

    private void j() {
        if (mScene.k.size() >= SceneManager.o) {
            Toast.makeText(this, R.string.add_condition_error, 0).show();
            return;
        }
        startActivityForResult(new Intent(this.mContext, SmartHomeSceneActionChooseActivity.class), 2);
        CreateSceneManager.a().l();
    }

    private class ConditionAdapter extends RecyclerView.Adapter<ConditionViewHolder> implements DraggableItemAdapter<ConditionViewHolder> {
        /* renamed from: b */
        public ItemDraggableRange a(ConditionViewHolder conditionViewHolder, int i) {
            return null;
        }

        public boolean b(int i, int i2) {
            return true;
        }

        ConditionAdapter() {
            setHasStableIds(true);
        }

        @NonNull
        /* renamed from: a */
        public ConditionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new ConditionViewHolder(SmarthomeCreateAutoSceneActivity.this.mInflater.inflate(R.layout.create_auto_scene_item, viewGroup, false));
        }

        /* renamed from: a */
        public void onBindViewHolder(@NonNull ConditionViewHolder conditionViewHolder, int i) {
            SceneApi.Condition condition = SmarthomeCreateAutoSceneActivity.mScene.l.get(i);
            if (SmarthomeCreateAutoSceneActivity.this.l) {
                conditionViewHolder.b.setVisibility(0);
                conditionViewHolder.h.setVisibility(0);
            } else {
                conditionViewHolder.b.setVisibility(8);
                conditionViewHolder.h.setVisibility(8);
            }
            if (condition.f21522a != SceneApi.Condition.LAUNCH_TYPE.CLICK || !SmarthomeCreateAutoSceneActivity.mScene.n) {
                conditionViewHolder.i.setVisibility(8);
            } else {
                conditionViewHolder.i.setVisibility(8);
                conditionViewHolder.i.setOnClickListener(new View.OnClickListener() {
                    public final void onClick(View view) {
                        SmarthomeCreateAutoSceneActivity.ConditionAdapter.this.b(view);
                    }
                });
            }
            SmartHomeSceneUtility.a(conditionViewHolder.g, condition);
            conditionViewHolder.itemView.setOnClickListener(new View.OnClickListener(condition) {
                private final /* synthetic */ SceneApi.Condition f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(View view) {
                    SmarthomeCreateAutoSceneActivity.ConditionAdapter.this.a(this.f$1, view);
                }
            });
            conditionViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                public final boolean onLongClick(View view) {
                    return SmarthomeCreateAutoSceneActivity.ConditionAdapter.this.a(view);
                }
            });
            conditionViewHolder.b.setOnClickListener(new View.OnClickListener(i) {
                private final /* synthetic */ int f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(View view) {
                    SmarthomeCreateAutoSceneActivity.ConditionAdapter.this.a(this.f$1, view);
                }
            });
            if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.DEVICE) {
                conditionViewHolder.j.setVisibility(0);
                conditionViewHolder.e.setText(SmartHomeSceneUtility.b(SmarthomeCreateAutoSceneActivity.this.mContext, condition));
                Room p = HomeManager.a().p(condition.c.f21523a);
                conditionViewHolder.f.setText(p == null ? SmarthomeCreateAutoSceneActivity.this.getResources().getString(R.string.tag_recommend_defaultroom) : p.e());
                conditionViewHolder.c.setText(condition.c.b);
            } else if (condition.b()) {
                conditionViewHolder.j.setVisibility(0);
                String str = "";
                if (TextUtils.equals(condition.j.f21525a, WeatherInnerCondition.WeatherConditionType.TYPE_SUNRISE.key) || TextUtils.equals(condition.j.f21525a, WeatherInnerCondition.WeatherConditionType.TYPE_SUNSET.key)) {
                    str = SmarthomeCreateAutoSceneActivity.this.getString(R.string.condition_weather_sunrise);
                } else if (TextUtils.equals(condition.j.f21525a, WeatherInnerCondition.WeatherConditionType.TYPE_ABOVE_TEMPERATURE.key)) {
                    str = SmarthomeCreateAutoSceneActivity.this.getString(R.string.condition_weather_temperature);
                } else if (TextUtils.equals(condition.j.f21525a, WeatherInnerCondition.WeatherConditionType.TYPE_ABOVE_HUMIDITY.key)) {
                    str = SmarthomeCreateAutoSceneActivity.this.getString(R.string.condition_weather_humidity);
                } else if (TextUtils.equals(condition.j.f21525a, WeatherInnerCondition.WeatherConditionType.TYPE_ABOVE_AQI.key)) {
                    str = SmarthomeCreateAutoSceneActivity.this.getString(R.string.condition_weather_aqi);
                }
                conditionViewHolder.e.setText(str);
                conditionViewHolder.f.setVisibility(8);
                conditionViewHolder.c.setText(SmartHomeSceneUtility.c(SmarthomeCreateAutoSceneActivity.this.mContext, condition));
            } else if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.COME_LOC || condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.LEAVE_LOC || condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.ENTER_FENCE || condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.LEAVE_FENCE) {
                conditionViewHolder.j.setVisibility(0);
                conditionViewHolder.f.setVisibility(8);
                conditionViewHolder.c.setText(SmartHomeSceneUtility.c(SmarthomeCreateAutoSceneActivity.this.mContext, condition));
                conditionViewHolder.e.setText(SmartHomeSceneUtility.a(SmarthomeCreateAutoSceneActivity.this.mContext, condition));
            } else {
                conditionViewHolder.j.setVisibility(0);
                conditionViewHolder.c.setText(SmartHomeSceneUtility.c(SmarthomeCreateAutoSceneActivity.this.mContext, condition));
                conditionViewHolder.f.setVisibility(8);
                conditionViewHolder.e.setText(SmartHomeSceneUtility.a(SmarthomeCreateAutoSceneActivity.this.mContext, condition));
            }
            if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.DEVICE && condition.c != null && !TextUtils.isEmpty(condition.c.f21523a)) {
                conditionViewHolder.d.setVisibility(0);
                Device n = SmartHomeDeviceManager.a().n(condition.c.f21523a);
                if (n == null) {
                    conditionViewHolder.d.setImageResource(R.drawable.ic_scene_invalid);
                    conditionViewHolder.g.setTag(-1);
                } else if (!n.isOwner()) {
                    conditionViewHolder.d.setImageResource(R.drawable.ic_scene_invalid);
                    conditionViewHolder.g.setTag(-1);
                } else if (SmarthomeCreateAutoSceneActivity.this.mConditionOnlineItems != null && SmarthomeCreateAutoSceneActivity.this.mConditionOnlineItems.length > i && !SmarthomeCreateAutoSceneActivity.this.mConditionOnlineItems[i]) {
                    conditionViewHolder.d.setImageResource(R.drawable.ic_scene_offline);
                    conditionViewHolder.g.setTag(-2);
                } else if (MultiHomeDeviceManager.a().b(n.did) == null) {
                    conditionViewHolder.d.setImageResource(R.drawable.ic_scene_otherhome);
                    conditionViewHolder.g.setTag(-3);
                } else {
                    conditionViewHolder.d.setVisibility(8);
                    conditionViewHolder.g.setTag(0);
                }
            } else if (SmarthomeCreateAutoSceneActivity.this.mConditionOnlineItems == null || SmarthomeCreateAutoSceneActivity.this.mConditionOnlineItems.length <= i || SmarthomeCreateAutoSceneActivity.this.mConditionOnlineItems[i]) {
                conditionViewHolder.d.setVisibility(8);
                conditionViewHolder.g.setTag(0);
            } else {
                conditionViewHolder.d.setVisibility(0);
                conditionViewHolder.d.setImageResource(R.drawable.ic_scene_offline);
                conditionViewHolder.g.setTag(-2);
            }
            conditionViewHolder.g.setOnClickListener(new View.OnClickListener(conditionViewHolder) {
                private final /* synthetic */ SmarthomeCreateAutoSceneActivity.ConditionViewHolder f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(View view) {
                    SmarthomeCreateAutoSceneActivity.ConditionAdapter.this.a(this.f$1, view);
                }
            });
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void b(View view) {
            RemoteSceneApi.a().b(SmarthomeCreateAutoSceneActivity.this.getApplicationContext(), SmarthomeCreateAutoSceneActivity.mScene.f, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                /* renamed from: a */
                public void onSuccess(Void voidR) {
                    ToastUtil.a((CharSequence) SmarthomeCreateAutoSceneActivity.this.getString(R.string.execute_success));
                }

                public void onFailure(Error error) {
                    Toast.makeText(SmarthomeCreateAutoSceneActivity.this.getApplicationContext(), R.string.smarthome_scene_start_error, 0).show();
                }
            });
            CoreApi.a().a(StatType.EVENT, "run_button_click", "smart_home_scene_create_edit_activity", (String) null, false);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(SceneApi.Condition condition, View view) {
            if (!SmarthomeCreateAutoSceneActivity.this.l) {
                if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.DEVICE || condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.MIKEY || condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.MIBAND) {
                    Intent intent = new Intent(SmarthomeCreateAutoSceneActivity.this.mContext, StartConditionActivityNew.class);
                    intent.putExtra("from", SmarthomeCreateAutoSceneActivity.this.e);
                    CreateSceneManager.a().c(condition);
                    SmarthomeCreateAutoSceneActivity.this.startActivityForResult(intent, 1);
                } else if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.TIMER) {
                    SmarthomeCreateAutoSceneActivity.this.startActivity(new Intent(SmarthomeCreateAutoSceneActivity.this.mContext, SmartHomeSceneTimerActivity.class));
                    CreateSceneManager.a().c(condition);
                } else if (condition.b()) {
                    SmarthomeCreateAutoSceneActivity.this.startActivity(new Intent(SmarthomeCreateAutoSceneActivity.this.mContext, SmarthomeChooseWeatherConditionDetailActivity.class));
                    CreateSceneManager.a().c(condition);
                } else if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.COME_LOC || condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.LEAVE_LOC) {
                    Intent intent2 = new Intent(SmarthomeCreateAutoSceneActivity.this.mContext, StartConditionActivityNew.class);
                    intent2.putExtra("from", SmarthomeCreateAutoSceneActivity.this.e);
                    CreateSceneManager.a().c(condition);
                    SmarthomeCreateAutoSceneActivity.this.startActivityForResult(intent2, 1);
                } else if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.ENTER_FENCE || condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.LEAVE_FENCE) {
                    Intent intent3 = new Intent(SmarthomeCreateAutoSceneActivity.this.mContext, StartConditionActivityNew.class);
                    intent3.putExtra("from", SmarthomeCreateAutoSceneActivity.this.e);
                    CreateSceneManager.a().c(condition);
                    SmarthomeCreateAutoSceneActivity.this.startActivityForResult(intent3, 1);
                }
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ boolean a(View view) {
            SmarthomeCreateAutoSceneActivity.this.k();
            return true;
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(int i, View view) {
            CreateSceneManager.a().b(SmarthomeCreateAutoSceneActivity.mScene.l.get(i));
            SmarthomeCreateAutoSceneActivity.mScene.l.remove(i);
            SmarthomeCreateAutoSceneActivity.this.refreshUI();
            CreateSceneManager.a().d(SmarthomeCreateAutoSceneActivity.mScene);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(@NonNull ConditionViewHolder conditionViewHolder, View view) {
            int intValue = ((Integer) view.getTag()).intValue();
            int[] iArr = new int[2];
            Rect rect = new Rect();
            SmarthomeCreateAutoSceneActivity.this.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
            conditionViewHolder.f21861a.getLocationOnScreen(iArr);
            iArr[0] = 0;
            iArr[1] = (iArr[1] - rect.top) - DisplayUtils.a(40.0f);
            ToastUtil.a();
            switch (intValue) {
                case -4:
                    ToastUtil.a(iArr, (int) R.string.scene_deleted);
                    return;
                case -3:
                    ToastUtil.a(iArr, (int) R.string.smarthome_scene_client_otherhome);
                    return;
                case -2:
                    ToastUtil.a(iArr, (int) R.string.smarthome_scene_client_offline);
                    return;
                case -1:
                    ToastUtil.a(iArr, (int) R.string.smarthome_scene_client_deleted);
                    return;
                default:
                    return;
            }
        }

        public int getItemCount() {
            return SmarthomeCreateAutoSceneActivity.mScene.l.size();
        }

        public boolean a(ConditionViewHolder conditionViewHolder, int i, int i2, int i3) {
            ImageView imageView;
            if (!SmarthomeCreateAutoSceneActivity.this.l || (imageView = conditionViewHolder.h) == null) {
                return false;
            }
            Rect rect = new Rect();
            conditionViewHolder.itemView.getGlobalVisibleRect(rect);
            Rect rect2 = new Rect();
            imageView.getGlobalVisibleRect(rect2);
            rect2.left -= rect.left;
            rect2.top -= rect.top;
            return rect2.contains(i2, i3);
        }

        public void b_(int i, int i2) {
            SmarthomeCreateAutoSceneActivity.mScene.l.add(i2, SmarthomeCreateAutoSceneActivity.mScene.l.remove(i));
            notifyItemMoved(i, i2);
            SmarthomeCreateAutoSceneActivity.this.refreshUI();
        }

        public long getItemId(int i) {
            return (long) SmarthomeCreateAutoSceneActivity.mScene.l.get(i).hashCode();
        }
    }

    private static class ConditionViewHolder extends AbstractDraggableSwipeableItemViewHolder {

        /* renamed from: a  reason: collision with root package name */
        View f21861a;
        ImageView b;
        TextView c;
        ImageView d;
        TextView e;
        TextView f;
        SimpleDraweeView g;
        ImageView h;
        View i;
        LinearLayout j;

        ConditionViewHolder(View view) {
            super(view);
            this.f21861a = view;
            this.c = (TextView) view.findViewById(R.id.sub_name);
            this.d = (ImageView) view.findViewById(R.id.device_status_img);
            this.g = (SimpleDraweeView) view.findViewById(R.id.image);
            this.h = (ImageView) view.findViewById(R.id.img_handle);
            this.b = (ImageView) view.findViewById(R.id.del_action_item);
            this.i = view.findViewById(R.id.right_view_click_btn);
            this.e = (TextView) view.findViewById(R.id.device_name);
            this.f = (TextView) view.findViewById(R.id.device_room_name);
            this.j = (LinearLayout) view.findViewById(R.id.device_name_ll);
        }

        public View k() {
            return this.f21861a;
        }
    }

    private class ActionAdapter extends RecyclerView.Adapter<ActionViewHolder> implements DraggableItemAdapter<ActionViewHolder> {
        /* renamed from: b */
        public ItemDraggableRange a(ActionViewHolder actionViewHolder, int i) {
            return null;
        }

        public boolean b(int i, int i2) {
            return true;
        }

        ActionAdapter() {
            setHasStableIds(true);
        }

        @NonNull
        /* renamed from: a */
        public ActionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new ActionViewHolder(SmarthomeCreateAutoSceneActivity.this.mInflater.inflate(R.layout.create_auto_scene_item, viewGroup, false));
        }

        /* renamed from: a */
        public void onBindViewHolder(@NonNull ActionViewHolder actionViewHolder, int i) {
            String str;
            SceneApi.Action action = SmarthomeCreateAutoSceneActivity.mScene.k.get(i);
            if (SmarthomeCreateAutoSceneActivity.this.l) {
                actionViewHolder.b.setVisibility(0);
                actionViewHolder.h.setVisibility(0);
            } else {
                actionViewHolder.b.setVisibility(8);
                actionViewHolder.h.setVisibility(8);
            }
            boolean z = true;
            if (action.g instanceof SceneApi.SHSceneDelayPayload) {
                actionViewHolder.j.setVisibility(8);
                actionViewHolder.g.setImageURI(CommonUtils.c((int) R.drawable.std_scene_icon_delayed));
                if (action.g.g < 60) {
                    str = SmarthomeCreateAutoSceneActivity.this.getResources().getQuantityString(R.plurals.smarthome_scene_delay_detal_sec, action.g.g % 60, new Object[]{Integer.valueOf(action.g.g % 60)});
                } else if (action.g.g % 60 == 0) {
                    str = SmarthomeCreateAutoSceneActivity.this.getResources().getQuantityString(R.plurals.smarthome_scene_delay_detal_min, action.g.g / 60, new Object[]{Integer.valueOf(action.g.g / 60)});
                } else {
                    str = String.format(SmarthomeCreateAutoSceneActivity.this.getString(R.string.smarthome_scene_delay_detal_min_sec), new Object[]{SmarthomeCreateAutoSceneActivity.this.getResources().getQuantityString(R.plurals.automation_minute, action.g.g / 60, new Object[]{Integer.valueOf(action.g.g / 60)}), SmarthomeCreateAutoSceneActivity.this.getResources().getQuantityString(R.plurals.automation_seconds, action.g.g % 60, new Object[]{Integer.valueOf(action.g.g % 60)})});
                }
                actionViewHolder.c.setText(str);
                actionViewHolder.itemView.setOnClickListener(new View.OnClickListener(action) {
                    private final /* synthetic */ SceneApi.Action f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void onClick(View view) {
                        SmarthomeCreateAutoSceneActivity.ActionAdapter.this.b(this.f$1, view);
                    }
                });
            } else {
                actionViewHolder.itemView.setOnClickListener(new View.OnClickListener(action) {
                    private final /* synthetic */ SceneApi.Action f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void onClick(View view) {
                        SmarthomeCreateAutoSceneActivity.ActionAdapter.this.a(this.f$1, view);
                    }
                });
                if (action.g instanceof SceneApi.SHSceneItemPayloadCommon) {
                    actionViewHolder.j.setVisibility(0);
                    SmartHomeSceneUtility.a(actionViewHolder.g, actionViewHolder.e, action);
                    if (HomeVirtualDeviceHelper.a(action.e)) {
                        actionViewHolder.f.setText("");
                        actionViewHolder.f.setVisibility(8);
                    } else {
                        Room p = HomeManager.a().p(action.g.e);
                        actionViewHolder.f.setText(p == null ? SmarthomeCreateAutoSceneActivity.this.getString(R.string.room_default) : p.e());
                        actionViewHolder.f.setVisibility(0);
                    }
                    String a2 = a(action);
                    if (!TextUtils.isEmpty(a2)) {
                        TextView textView = actionViewHolder.c;
                        textView.setText(action.c + " " + action.g.f + a2);
                    } else {
                        actionViewHolder.c.setText(action.c);
                    }
                } else if (action.g instanceof SceneApi.SHLiteScenePayload) {
                    actionViewHolder.j.setVisibility(8);
                    actionViewHolder.e.setVisibility(8);
                    actionViewHolder.f.setVisibility(8);
                    actionViewHolder.c.setText(action.b);
                    SceneApi.SmartHomeScene a3 = LiteSceneManager.j().a(((SceneApi.SHLiteScenePayload) action.g).f21529a);
                    if (a3 != null) {
                        actionViewHolder.g.setImageURI(CommonUtils.c(SmartHomeSceneUtility.a(a3.i)));
                    } else {
                        actionViewHolder.g.setImageURI(CommonUtils.c((int) R.drawable.intelligence_icon_default_nor));
                    }
                } else {
                    actionViewHolder.j.setVisibility(8);
                    SmartHomeSceneUtility.a(actionViewHolder.g, actionViewHolder.c, action);
                }
            }
            actionViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                public final boolean onLongClick(View view) {
                    return SmarthomeCreateAutoSceneActivity.ActionAdapter.this.a(view);
                }
            });
            actionViewHolder.b.setOnClickListener(new View.OnClickListener(i) {
                private final /* synthetic */ int f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(View view) {
                    SmarthomeCreateAutoSceneActivity.ActionAdapter.this.a(this.f$1, view);
                }
            });
            if (SmarthomeCreateAutoSceneActivity.this.mActionOnlineItems == null || SmarthomeCreateAutoSceneActivity.this.mActionOnlineItems.length <= i || !SmarthomeCreateAutoSceneActivity.this.mActionOnlineItems[i]) {
                z = false;
            }
            if (action.g instanceof SceneApi.SHSceneAutoPayload) {
                actionViewHolder.d.setVisibility(0);
                if (SceneManager.x().e(((SceneApi.SHSceneAutoPayload) action.g).f21530a) == null) {
                    actionViewHolder.e.setVisibility(8);
                    actionViewHolder.f.setVisibility(8);
                    actionViewHolder.j.setVisibility(0);
                    actionViewHolder.d.setImageResource(R.drawable.ic_scene_invalid);
                    actionViewHolder.g.setTag(-4);
                } else {
                    actionViewHolder.d.setVisibility(8);
                    actionViewHolder.g.setTag(0);
                }
            } else if (action.g instanceof SceneApi.SHLiteScenePayload) {
                actionViewHolder.d.setVisibility(0);
                if (LiteSceneManager.j().a(((SceneApi.SHLiteScenePayload) action.g).f21529a) == null) {
                    actionViewHolder.e.setVisibility(8);
                    actionViewHolder.f.setVisibility(8);
                    actionViewHolder.j.setVisibility(0);
                    actionViewHolder.d.setImageResource(R.drawable.ic_scene_invalid);
                    actionViewHolder.g.setTag(-4);
                } else {
                    actionViewHolder.d.setVisibility(8);
                    actionViewHolder.g.setTag(0);
                }
            } else if (!(action.g instanceof SceneApi.SHSceneItemPayloadCommon) || TextUtils.isEmpty(action.g.e)) {
                actionViewHolder.d.setVisibility(8);
                actionViewHolder.g.setTag(0);
            } else {
                actionViewHolder.d.setVisibility(0);
                Device n = SmartHomeDeviceManager.a().n(action.g.e);
                if (n == null) {
                    actionViewHolder.d.setImageResource(R.drawable.ic_scene_invalid);
                    actionViewHolder.g.setTag(-1);
                } else if (!n.isOwner()) {
                    actionViewHolder.d.setImageResource(R.drawable.ic_scene_invalid);
                    actionViewHolder.g.setTag(-1);
                } else if (!z) {
                    actionViewHolder.d.setImageResource(R.drawable.ic_scene_offline);
                    actionViewHolder.g.setTag(-2);
                } else if (MultiHomeDeviceManager.a().b(n.did) == null) {
                    actionViewHolder.d.setImageResource(R.drawable.ic_scene_otherhome);
                    actionViewHolder.g.setTag(-3);
                } else {
                    actionViewHolder.d.setVisibility(8);
                    actionViewHolder.g.setTag(0);
                }
            }
            actionViewHolder.g.setOnClickListener(new View.OnClickListener(actionViewHolder) {
                private final /* synthetic */ SmarthomeCreateAutoSceneActivity.ActionViewHolder f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(View view) {
                    SmarthomeCreateAutoSceneActivity.ActionAdapter.this.a(this.f$1, view);
                }
            });
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void b(SceneApi.Action action, View view) {
            Intent intent = new Intent(SmarthomeCreateAutoSceneActivity.this.mContext, SmartHomeSceneTimerDelay.class);
            CreateSceneManager.a().c(action);
            SmarthomeCreateAutoSceneActivity.this.startActivityForResult(intent, 1);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(SceneApi.Action action, View view) {
            if (!SmarthomeCreateAutoSceneActivity.this.l) {
                Intent intent = new Intent(SmarthomeCreateAutoSceneActivity.this.mContext, SmartHomeSceneActionChooseActivity.class);
                CreateSceneManager.a().c(action);
                SmarthomeCreateAutoSceneActivity.this.startActivityForResult(intent, 2);
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ boolean a(View view) {
            SmarthomeCreateAutoSceneActivity.this.k();
            return true;
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(int i, View view) {
            if (SmarthomeCreateAutoSceneActivity.mScene != null && SmarthomeCreateAutoSceneActivity.mScene.k != null && SmarthomeCreateAutoSceneActivity.mScene.k.size() != 0) {
                CreateSceneManager.a().b(SmarthomeCreateAutoSceneActivity.mScene.k.get(i));
                SmarthomeCreateAutoSceneActivity.mScene.k.remove(i);
                SmarthomeCreateAutoSceneActivity.this.refreshUI();
                CreateSceneManager.a().d(SmarthomeCreateAutoSceneActivity.mScene);
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(@NonNull ActionViewHolder actionViewHolder, View view) {
            int intValue = ((Integer) view.getTag()).intValue();
            int[] iArr = new int[2];
            Rect rect = new Rect();
            SmarthomeCreateAutoSceneActivity.this.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
            actionViewHolder.f21858a.getLocationOnScreen(iArr);
            iArr[0] = 0;
            iArr[1] = (iArr[1] - rect.top) - DisplayUtils.a(40.0f);
            ToastUtil.a();
            switch (intValue) {
                case -4:
                    ToastUtil.a(iArr, (int) R.string.scene_deleted);
                    return;
                case -3:
                    ToastUtil.a(iArr, (int) R.string.smarthome_scene_client_otherhome);
                    return;
                case -2:
                    ToastUtil.a(iArr, (int) R.string.smarthome_scene_client_offline);
                    return;
                case -1:
                    ToastUtil.a(iArr, (int) R.string.smarthome_scene_client_deleted);
                    return;
                default:
                    return;
            }
        }

        public int getItemCount() {
            return SmarthomeCreateAutoSceneActivity.mScene.k.size();
        }

        public boolean a(ActionViewHolder actionViewHolder, int i, int i2, int i3) {
            ImageView imageView;
            if (!SmarthomeCreateAutoSceneActivity.this.l || (imageView = actionViewHolder.h) == null) {
                return false;
            }
            Rect rect = new Rect();
            actionViewHolder.itemView.getGlobalVisibleRect(rect);
            Rect rect2 = new Rect();
            imageView.getGlobalVisibleRect(rect2);
            rect2.left -= rect.left;
            rect2.top -= rect.top;
            return rect2.contains(i2, i3);
        }

        public void b_(int i, int i2) {
            SmarthomeCreateAutoSceneActivity.mScene.k.add(i2, SmarthomeCreateAutoSceneActivity.mScene.k.remove(i));
            notifyItemMoved(i, i2);
            SmarthomeCreateAutoSceneActivity.this.refreshUI();
        }

        public long getItemId(int i) {
            return (long) SmarthomeCreateAutoSceneActivity.mScene.k.get(i).hashCode();
        }

        private String a(SceneApi.Action action) {
            CommonSceneOnline a2;
            if (action.e == null || action.g == null || (a2 = SceneManager.x().a(action.e, action.g.e)) == null || a2.f == null) {
                return "";
            }
            Iterator<CommonSceneOnline.CommonSceneAction> it = a2.f.iterator();
            while (it.hasNext()) {
                CommonSceneOnline.CommonSceneAction next = it.next();
                if (TextUtils.equals(next.f21199a, action.c) && (next.i instanceof CommonSceneOnline.SceneAttrNumberPicker)) {
                    CommonSceneOnline.SceneAttrNumberPicker sceneAttrNumberPicker = (CommonSceneOnline.SceneAttrNumberPicker) next.i;
                    if (TextUtils.equals("equal", sceneAttrNumberPicker.f)) {
                        return sceneAttrNumberPicker.e;
                    }
                }
            }
            return "";
        }
    }

    public void onBackPressed() {
        if (this.l) {
            b(false);
        } else {
            c();
        }
    }

    /* access modifiers changed from: private */
    public void k() {
        if (!this.l) {
            try {
                this.l = true;
                this.mSceneBeforeEdit = SceneApi.SmartHomeScene.a(mScene.a(), mScene.t);
                if (this.mSceneBeforeEdit != null) {
                    f();
                    this.mActionAdapter.notifyDataSetChanged();
                    this.mConditionAdapter.notifyDataSetChanged();
                    this.mAddContionTV.setEnabled(false);
                    this.mAddContionTV.setTextColor(getResources().getColor(R.color.class_text_32));
                    this.mAddActionTV.setEnabled(false);
                    this.mAddActionTV.setTextColor(getResources().getColor(R.color.class_text_32));
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
    }

    private void b(boolean z) {
        try {
            if (this.l) {
                this.l = false;
                if (!z && this.mSceneBeforeEdit != null) {
                    mScene = SceneApi.SmartHomeScene.a(this.mSceneBeforeEdit.a(), this.mSceneBeforeEdit.t);
                }
                CreateSceneManager.a().d(mScene);
                CreateSceneManager.a().b(mScene);
                CreateSceneManager.a().c(mScene);
                f();
                this.mActionAdapter.notifyDataSetChanged();
                this.mConditionAdapter.notifyDataSetChanged();
                this.mAddContionTV.setTextColor(getResources().getColor(R.color.choose_connect_device_error_link));
                this.mAddContionTV.setEnabled(true);
                this.mAddActionTV.setTextColor(getResources().getColor(R.color.choose_connect_device_error_link));
                this.mAddActionTV.setEnabled(true);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private static class ActionViewHolder extends AbstractDraggableSwipeableItemViewHolder {

        /* renamed from: a  reason: collision with root package name */
        View f21858a;
        ImageView b;
        TextView c;
        ImageView d;
        TextView e;
        TextView f;
        SimpleDraweeView g;
        ImageView h;
        View i;
        LinearLayout j;

        ActionViewHolder(View view) {
            super(view);
            this.f21858a = view;
            this.c = (TextView) view.findViewById(R.id.sub_name);
            this.d = (ImageView) view.findViewById(R.id.device_status_img);
            this.g = (SimpleDraweeView) view.findViewById(R.id.image);
            this.h = (ImageView) view.findViewById(R.id.img_handle);
            this.b = (ImageView) view.findViewById(R.id.del_action_item);
            this.i = view.findViewById(R.id.right_view_click_btn);
            this.e = (TextView) view.findViewById(R.id.device_name);
            this.f = (TextView) view.findViewById(R.id.device_room_name);
            this.j = (LinearLayout) view.findViewById(R.id.device_name_ll);
        }

        public View k() {
            return this.f21858a;
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i2, int i3, Intent intent) {
        LogUtil.a(f21843a, "onActivityResult requestCode " + i2 + "  resultCode  " + i3);
        if (i2 == 1000 && i3 == -1) {
            if (intent == null || !intent.hasExtra(SceneMoreActivity.TAG_SCENE_DELETED) || !intent.getBooleanExtra(SceneMoreActivity.TAG_SCENE_DELETED, false)) {
                refreshUI();
            } else {
                finish();
            }
        } else if (i3 == -1) {
            refreshUI();
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.m) {
            refreshUI();
        } else {
            this.m = true;
        }
        if (this.e != 1) {
            d();
        }
    }

    @OnClick({2131430989})
    public void completeScene() {
        boolean z = true;
        if (!SceneManager.x().n() && mScene.l != null && mScene.l.size() == 1 && mScene.l.get(0).f21522a == SceneApi.Condition.LAUNCH_TYPE.CLICK) {
            SceneManager.x().a((String) null, this.n);
        } else if (mScene.l == null || mScene.l.size() != 1 || mScene.l.get(0).f21522a != SceneApi.Condition.LAUNCH_TYPE.CLICK || !LiteSceneManager.j().h() || LiteSceneManager.j().e().size() + 1 <= 256) {
            Iterator<SceneApi.Action> it = mScene.k.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                SceneApi.Action next = it.next();
                if (next.f21521a == 1 && mScene.l != null) {
                    for (SceneApi.Condition e2 : mScene.l) {
                        ((SceneApi.SHScenePushPayload) next.g).b = SmartHomeSceneUtility.e(SHApplication.getAppContext(), e2);
                    }
                }
            }
            SceneApi.Condition p = p();
            if (p != null) {
                String str = p.e.f21523a;
                String str2 = p.e.j;
            }
            if (SHConfig.a().a("show_scene_user_lincense") != -1) {
                z = false;
            }
            if (z) {
                SHConfig.a().a("show_scene_user_lincense", 0);
                new MLAlertDialog.Builder(this).a(StringUtil.a((Context) this, (int) R.string.scene_user_license_title_1_underline, (int) R.string.scene_user_license_title_1_tpl, (ClickableSpan) new ClickableSpan() {
                    public void updateDrawState(@NonNull TextPaint textPaint) {
                        super.updateDrawState(textPaint);
                        textPaint.setColor(SmarthomeCreateAutoSceneActivity.this.getResources().getColor(R.color.class_text_27));
                        textPaint.setUnderlineText(true);
                    }

                    public void onClick(@NonNull View view) {
                        SmarthomeCreateAutoSceneActivity.this.startActivity(new Intent(SmarthomeCreateAutoSceneActivity.this, SceneUseLicnece.class));
                    }
                })).a((int) R.string.scene_user_license_title).a((int) R.string.smarthome_share_accept, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        SmarthomeCreateAutoSceneActivity.this.e(dialogInterface, i);
                    }
                }).b((int) R.string.smarthome_share_reject, (DialogInterface.OnClickListener) $$Lambda$SmarthomeCreateAutoSceneActivity$8iUABgCDOfgyNU4ibZFUdm0_YIw.INSTANCE).a(false).b().show();
                return;
            }
            l();
        } else {
            ToastUtil.a((int) R.string.add_lite_scene_error);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void e(DialogInterface dialogInterface, int i2) {
        l();
    }

    @OnClick({2131430988})
    public void gotoMorePage() {
        if (!TextUtils.isEmpty(this.mSceneID) && mScene.a(this.mSceneSrc)) {
            Intent intent = new Intent(this, SceneMoreActivity.class);
            intent.putExtra("extra_scene_id", this.mSceneID);
            intent.putExtra("from", this.e);
            intent.putExtra(EXTRA_DEVICE_ID, this.i);
            intent.putExtra(EXTRA_IS_LITE, mScene.t);
            startActivityForResult(intent, 1000);
        }
    }

    private void l() {
        if (isNewScene) {
            if (mScene.l.size() > 0 && mScene.k.size() > 0) {
                String a2 = SmartHomeSceneUtility.a(mScene);
                if (!TextUtils.isEmpty(mScene.g)) {
                    a2 = mScene.g;
                }
                final String b2 = StringUtil.b((CharSequence) a2, CreateSceneManager.f21204a * 2);
                NameEditDialogHelper.a((Context) this, (int) R.layout.client_input_view_checkbox, b2, getString(R.string.smarthome_scene_set_name), b2, CreateSceneManager.f21204a, (NameEditDialogHelper.NameEditListenerV2) new NameEditDialogHelper.NameEditListenerV2() {
                    public void a(String str) {
                    }

                    public void a(ClientRemarkInputView clientRemarkInputView, String str) {
                        if (((CheckBox) clientRemarkInputView.findViewById(R.id.enable_push_checkbox)).isChecked()) {
                            SmarthomeCreateAutoSceneActivity.mScene.o = true;
                        }
                        if (!b2.equals(str)) {
                            MiStatInterface.a(MiStatType.CLICK.getValue(), "scene_save_rename");
                        }
                        SmarthomeCreateAutoSceneActivity.this.a(str, clientRemarkInputView);
                    }

                    public String b(String str) {
                        for (SceneApi.SmartHomeScene next : SceneManager.x().v()) {
                            if (!TextUtils.equals(next.f, SmarthomeCreateAutoSceneActivity.mScene.f) && TextUtils.equals(next.g, str)) {
                                return SmarthomeCreateAutoSceneActivity.this.getString(R.string.scene_modify_name_error);
                            }
                        }
                        for (SceneApi.SmartHomeScene next2 : LiteSceneManager.j().e()) {
                            if (!TextUtils.equals(next2.f, SmarthomeCreateAutoSceneActivity.mScene.f) && TextUtils.equals(next2.g, str)) {
                                return SmarthomeCreateAutoSceneActivity.this.getString(R.string.scene_modify_name_error);
                            }
                        }
                        return "";
                    }
                });
            } else if (mScene.l.size() == 0) {
                Toast.makeText(this.mContext, R.string.smarthome_scene_add_start_condition, 0).show();
            } else {
                Toast.makeText(this.mContext, R.string.smarthome_scene_set_fail_at_least_0, 0).show();
            }
        } else if (mScene.l.size() <= 0 || mScene.k.size() <= 0) {
            Toast.makeText(this.mContext, R.string.smarthome_scene_set_fail_at_least_0, 0).show();
        } else if (SceneManager.x().e(mScene)) {
            MLAlertDialog b3 = new MLAlertDialog.Builder(this.mContext).a((int) R.string.smarthome_scene_conflict).b((int) R.string.sh_confirm, (DialogInterface.OnClickListener) $$Lambda$SmarthomeCreateAutoSceneActivity$7cQoiZb43StqOpxjOY4xZFNUdaU.INSTANCE).b();
            TextView textView = new TextView(b3.getContext());
            textView.setText(SceneManager.x().B());
            textView.setLineSpacing(0.0f, 1.5f);
            textView.setPadding(50, 0, 50, 0);
            b3.setView(textView);
            b3.show();
        } else {
            if (q() == 1 && this.o != null) {
                deleteTimeSpan(this.o);
            }
            a(mScene.g, (ClientRemarkInputView) null);
        }
    }

    /* access modifiers changed from: private */
    public void a(String str, ClientRemarkInputView clientRemarkInputView) {
        Device n2;
        String str2;
        Device n3;
        String str3;
        if (!NetworkUtils.c()) {
            ToastUtil.a((int) R.string.network_fake_connected);
            return;
        }
        String str4 = null;
        this.g = XQProgressDialog.a(this.mContext, (CharSequence) null, getResources().getString(R.string.smarthome_scene_saving_scene));
        mScene.g = str;
        if (!TextUtils.isEmpty(str) && !mScene.k.isEmpty()) {
            Iterator<SceneApi.Action> it = mScene.k.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                SceneApi.Action next = it.next();
                if (next.g != null && (next.g instanceof SceneApi.SHScenePushPayload)) {
                    ((SceneApi.SHScenePushPayload) next.g).b = str;
                    break;
                }
            }
        }
        if (clientRemarkInputView != null) {
            ((InputMethodManager) this.mContext.getSystemService("input_method")).hideSoftInputFromWindow(clientRemarkInputView.getEditText().getWindowToken(), 0);
        }
        boolean z = false;
        for (SceneApi.Condition next2 : mScene.l) {
            if (!(next2.c == null || next2.c.d == null || (n3 = SmartHomeDeviceManager.a().n(next2.c.f21523a)) == null)) {
                if (n3.isSubDevice()) {
                    str3 = n3.parentId;
                } else {
                    str3 = n3.did;
                }
                if (SmartHomeSceneUtility.a(SmartHomeDeviceManager.a().n(str3))) {
                    str4 = str3;
                    z = true;
                }
            }
        }
        if (!z) {
            for (SceneApi.Action next3 : mScene.k) {
                if (!(next3.e == null || next3.g.e == null || (n2 = SmartHomeDeviceManager.a().n(next3.g.e)) == null)) {
                    if (n2.isSubDevice()) {
                        str2 = n2.parentId;
                    } else {
                        str2 = n2.did;
                    }
                    if (SmartHomeSceneUtility.a(SmartHomeDeviceManager.a().n(str2))) {
                        str4 = str2;
                        z = true;
                    }
                }
            }
        }
        if (!z || str4 == null) {
            m();
            return;
        }
        Device n4 = SmartHomeDeviceManager.a().n(str4);
        if (n4 == null || n4.isOnline) {
            SceneApi.a(mScene);
            SceneInfo f2 = SceneManager.f(mScene);
            this.mHandler.sendEmptyMessageDelayed(100, 10000);
            Log.i("lumiscene", "is rn plugin, scene in app exec..");
            a(f2, n4);
            return;
        }
        this.g.dismiss();
        Toast.makeText(this, R.string.smarthome_scene_getway_offline, 0).show();
    }

    private void a(SceneInfo sceneInfo, final Device device) {
        SceneExtraBuilder.a().a(sceneInfo, (Callback<SceneInfo>) new Callback<SceneInfo>() {
            /* renamed from: a */
            public void onSuccess(SceneInfo sceneInfo) {
                if (SmarthomeCreateAutoSceneActivity.this.mHandler.hasMessages(100)) {
                    SmarthomeCreateAutoSceneActivity.this.mHandler.removeMessages(100);
                    if (sceneInfo != null) {
                        int i = 0;
                        for (int i2 = 0; i2 < sceneInfo.mLaunchList.size(); i2++) {
                            if (SmarthomeCreateAutoSceneActivity.mScene.l.get(i2).c != null && (SmarthomeCreateAutoSceneActivity.mScene.l.get(i2).c instanceof SceneApi.ConditionDeviceCommon)) {
                                ((SceneApi.ConditionDeviceCommon) SmarthomeCreateAutoSceneActivity.mScene.l.get(i2).c).m = sceneInfo.mLaunchList.get(i2).mExtra;
                            }
                        }
                        while (i < sceneInfo.mActions.size() && i < SmarthomeCreateAutoSceneActivity.mScene.k.size()) {
                            if (SmarthomeCreateAutoSceneActivity.mScene.k.get(i).g != null && (SmarthomeCreateAutoSceneActivity.mScene.k.get(i).g instanceof SceneApi.SHSceneItemPayloadCommon)) {
                                ((SceneApi.SHSceneItemPayloadCommon) SmarthomeCreateAutoSceneActivity.mScene.k.get(i).g).f21531a = sceneInfo.mActions.get(i).mExtra;
                            }
                            i++;
                        }
                    }
                    SmarthomeCreateAutoSceneActivity.this.m();
                }
            }

            public void onFailure(int i, String str) {
                LogUtil.b("lumiscene", str);
                if (SmarthomeCreateAutoSceneActivity.this.mHandler.hasMessages(100)) {
                    SmarthomeCreateAutoSceneActivity.this.mHandler.removeMessages(100);
                }
                SmarthomeCreateAutoSceneActivity.this.mHandler.post(new Runnable() {
                    public void run() {
                        if (SmarthomeCreateAutoSceneActivity.this.isValid()) {
                            SmarthomeCreateAutoSceneActivity.this.g.dismiss();
                        }
                        Toast.makeText(SmarthomeCreateAutoSceneActivity.this.mContext, R.string.smarthome_scene_set_fail, 0).show();
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public void m() {
        SceneApi.ConditionIZatGeoFencing conditionIZatGeoFencing;
        final int i2 = 0;
        while (true) {
            if (i2 < (mScene.l == null ? 0 : mScene.l.size())) {
                if (mScene.l.get(i2) != null && mScene.l.get(i2) != null) {
                    conditionIZatGeoFencing = mScene.l.get(i2).i;
                    break;
                }
                i2++;
            } else {
                conditionIZatGeoFencing = null;
                i2 = -1;
                break;
            }
        }
        if (conditionIZatGeoFencing != null) {
            RemoteSceneApi.a().a(this, conditionIZatGeoFencing.p, conditionIZatGeoFencing.r, conditionIZatGeoFencing.s, conditionIZatGeoFencing.n, new AsyncCallback<JSONObject, Error>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    if (SmarthomeCreateAutoSceneActivity.this.isValid()) {
                        if (jSONObject != null && jSONObject.has("po_id") && i2 >= 0 && SmarthomeCreateAutoSceneActivity.mScene != null && SmarthomeCreateAutoSceneActivity.mScene.l != null && i2 < SmarthomeCreateAutoSceneActivity.mScene.l.size()) {
                            SmarthomeCreateAutoSceneActivity.mScene.l.get(i2).i.p = jSONObject.optLong("po_id");
                        }
                        SmarthomeCreateAutoSceneActivity.this.n();
                    }
                }

                public void onFailure(Error error) {
                    if (SmarthomeCreateAutoSceneActivity.this.isValid()) {
                        SmarthomeCreateAutoSceneActivity.this.g.dismiss();
                        Toast.makeText(SmarthomeCreateAutoSceneActivity.this.mContext, R.string.smarthome_scene_set_fail, 0).show();
                    }
                }
            });
        } else {
            n();
        }
    }

    /* access modifiers changed from: private */
    public void n() {
        if (SmartHomeConfig.c) {
            RemoteSceneApi.a().a((Context) this, mScene, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    SmarthomeCreateAutoSceneActivity.this.a(jSONObject);
                }

                public void onFailure(Error error) {
                    SmarthomeCreateAutoSceneActivity.this.g.dismiss();
                    if (error.a() == -23) {
                        Toast.makeText(SmarthomeCreateAutoSceneActivity.this.mContext, R.string.dead_loop_error, 0).show();
                    } else if (error.a() == -22) {
                        Toast.makeText(SmarthomeCreateAutoSceneActivity.this.mContext, R.string.reach_maximum_number, 0).show();
                    } else if (error.a() == -1) {
                        Toast.makeText(SmarthomeCreateAutoSceneActivity.this.getContext(), R.string.smarthome_scene_has_deleted_device, 0).show();
                    } else if (error.a() == -38) {
                        ToastUtil.a((int) R.string.scene_create_fail_by_change_hand_auto);
                    } else {
                        Toast.makeText(SmarthomeCreateAutoSceneActivity.this.mContext, R.string.smarthome_scene_set_fail, 0).show();
                    }
                }
            });
        } else {
            this.g.dismiss();
        }
    }

    /* access modifiers changed from: package-private */
    public void onFinishSaved(boolean z) {
        if (isValid()) {
            this.mHandler.post(new Runnable(z) {
                private final /* synthetic */ boolean f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    SmarthomeCreateAutoSceneActivity.this.c(this.f$1);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void c(boolean z) {
        o();
        if (z) {
            if (this.e == 1) {
                if (isNewScene) {
                    notifyPlugin(this, true, mScene, this.i);
                } else {
                    a(this, this.f, true);
                }
            }
            if (!isNewScene && CreateSceneManager.a().c(mScene.l) != CreateSceneManager.a().c(this.mSceneSrc.l)) {
                if (CreateSceneManager.a().c(mScene.l)) {
                    if (!TextUtils.isEmpty(this.mSceneID)) {
                        SceneManager.x().g(this.mSceneID);
                    }
                    CoreApi.a().U();
                    SceneManager.x().c((String) null);
                    LiteSceneManager.j().b(mScene, new AsyncCallback() {
                        public void onSuccess(Object obj) {
                            SmarthomeCreateAutoSceneActivity.this.g.dismiss();
                            LiteSceneManager.j().b();
                        }

                        public void onFailure(Error error) {
                            SmarthomeCreateAutoSceneActivity.this.g.dismiss();
                        }
                    });
                } else {
                    LiteSceneManager.j().a(mScene, (AsyncCallback) new AsyncCallback() {
                        public void onSuccess(Object obj) {
                            SmarthomeCreateAutoSceneActivity.this.g.dismiss();
                            LiteSceneManager.j().b();
                            SceneManager.x().d(SmarthomeCreateAutoSceneActivity.mScene);
                            CoreApi.a().U();
                            SceneManager.x().c((String) null);
                        }

                        public void onFailure(Error error) {
                            SmarthomeCreateAutoSceneActivity.this.g.dismiss();
                        }
                    });
                }
                Toast.makeText(this.mContext, R.string.smarthome_scene_set_succ, 0).show();
                setResult(-1);
                finish();
                return;
            } else if (!isNewScene) {
                if (CreateSceneManager.a().c(mScene.l)) {
                    LiteSceneManager.j().a(this.mSceneID, mScene);
                    LiteSceneManager.j().b();
                } else {
                    if (!TextUtils.isEmpty(this.mSceneID)) {
                        SceneManager.x().a(this.mSceneID, mScene);
                    }
                    CoreApi.a().U();
                    SceneManager.x().c((String) null);
                }
                this.g.dismiss();
                Toast.makeText(this.mContext, R.string.smarthome_scene_set_succ, 0).show();
                setResult(-1);
                finish();
                return;
            } else {
                if (CreateSceneManager.a().c(mScene.l)) {
                    LiteSceneManager.j().b(mScene, new AsyncCallback() {
                        public void onSuccess(Object obj) {
                            SmarthomeCreateAutoSceneActivity.this.g.dismiss();
                            LiteSceneManager.j().b();
                        }

                        public void onFailure(Error error) {
                            SmarthomeCreateAutoSceneActivity.this.g.dismiss();
                        }
                    });
                } else {
                    SceneManager.x().d(mScene);
                    CoreApi.a().U();
                    SceneManager.x().c((String) null);
                }
                RecommendSceneManager.a().b(mScene.f);
                this.g.dismiss();
                Toast.makeText(this.mContext, R.string.smarthome_scene_set_succ, 0).show();
            }
        } else {
            Toast.makeText(this.mContext, R.string.local_sync_failed, 0).show();
        }
        finish();
    }

    public static void notifyPlugin(Context context, boolean z, SceneApi.SmartHomeScene smartHomeScene, String str) {
        a(context, z, a(smartHomeScene, str));
    }

    private static void a(Context context, boolean z, boolean z2) {
        if (z && z2) {
            context.sendBroadcast(new Intent(SCENE_STATUS_UPDATE));
        }
    }

    private static boolean a(SceneApi.SmartHomeScene smartHomeScene, String str) {
        if (TextUtils.isEmpty(str) || smartHomeScene == null) {
            return false;
        }
        if (smartHomeScene.l != null) {
            for (SceneApi.Condition next : smartHomeScene.l) {
                if (next.c != null && TextUtils.equals(next.c.f21523a, str)) {
                    return true;
                }
                if (next.f != null && TextUtils.equals(next.f.f21523a, str)) {
                    return true;
                }
                if (next.e != null && TextUtils.equals(next.e.f21523a, str)) {
                    return true;
                }
            }
        }
        if (smartHomeScene.k != null) {
            for (SceneApi.Action next2 : smartHomeScene.k) {
                if (next2.g != null && TextUtils.equals(next2.g.e, str)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void a(JSONObject jSONObject) {
        String optString = jSONObject.optString("us_id");
        boolean optBoolean = jSONObject.optBoolean("local");
        String optString2 = jSONObject.optString("local_dev");
        mScene.f = optString;
        if (TextUtils.isEmpty(optString2) || !optBoolean) {
            onFinishSaved(true);
            return;
        }
        Device n2 = SmartHomeDeviceManager.a().n(optString2);
        if (n2 != null) {
            LocalSceneBuilder.a().a(XmPluginHostApi.instance().getDeviceByDid(n2.did), jSONObject.optJSONObject("data").toString(), (MessageCallback) new MessageCallback() {
                public void onSuccess(Intent intent) {
                    RemoteSceneApi.a().b((Context) SmarthomeCreateAutoSceneActivity.this, SmarthomeCreateAutoSceneActivity.mScene, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                        /* renamed from: a */
                        public void onSuccess(Void voidR) {
                            SmarthomeCreateAutoSceneActivity.this.onFinishSaved(true);
                        }

                        public void onFailure(Error error) {
                            SmarthomeCreateAutoSceneActivity.this.onFinishSaved(false);
                        }
                    });
                }

                public void onFailure(int i, String str) {
                    LogUtil.b("lumiscene", str);
                    SmarthomeCreateAutoSceneActivity.this.onFinishSaved(false);
                }
            });
            this.mHandler.sendEmptyMessageDelayed(101, 10000);
        }
    }

    private void o() {
        SceneApi.Condition p = p();
        if (p != null) {
            Intent intent = new Intent();
            String str = p.e.f21523a;
            String str2 = p.e.j;
            intent.putExtra("extra_did", str);
            intent.putExtra(BluetoothConstants.g, str2);
            setResult(-1, intent);
        }
    }

    private SceneApi.Condition p() {
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

    private int q() {
        int i2 = 0;
        if (mScene == null) {
            return 0;
        }
        for (SceneApi.Condition next : mScene.l) {
            if (!(next.c == null || next.c.e == -1)) {
                i2++;
                this.o = next.c;
            }
        }
        return i2;
    }

    /* access modifiers changed from: package-private */
    public void deleteTimeSpan(SceneApi.ConditionDevice conditionDevice) {
        conditionDevice.e = -1;
        conditionDevice.f = -1;
        conditionDevice.g = -1;
        conditionDevice.h = -1;
        conditionDevice.i = null;
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        CreateSceneManager.a().a((SceneApi.SmartHomeScene) null);
        if (this.mHandler != null) {
            this.mHandler.removeCallbacksAndMessages((Object) null);
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        ToastUtil.a();
    }

    private void r() {
        if (CreateSceneManager.a().c(mScene.l)) {
            mScene.u = new SceneApi.EffectiveTimeSpan();
            this.mExeTimeTV.setText(R.string.scene_exetime_all_day);
            this.mExeTimeTV.setTextColor(getResources().getColor(R.color.class_D));
            this.mExeTimeHint.setTextColor(getResources().getColor(R.color.class_D));
            this.mExeTimeRL.setOnClickListener($$Lambda$SmarthomeCreateAutoSceneActivity$tDdWoEwx588ljXJb_kqBAhNYVWY.INSTANCE);
            return;
        }
        this.mExeTimeTV.setTextColor(getResources().getColor(R.color.black_80_transparent));
        this.mExeTimeHint.setTextColor(getResources().getColor(R.color.black_80_transparent));
        int q = q();
        if (mScene.v || isNewScene) {
            this.mExeTimeRL.setVisibility(0);
            this.mExeTimeRL.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    SmarthomeCreateAutoSceneActivity.this.e(view);
                }
            });
        } else if (q == 1) {
            this.mExeTimeRL.setVisibility(0);
            if (this.o != null) {
                mScene.u = new SceneApi.EffectiveTimeSpan();
                mScene.u.f21526a = this.o.e;
                mScene.u.c = this.o.g;
                mScene.u.b = this.o.f;
                mScene.u.d = this.o.h;
                mScene.u.e = new int[this.o.i.length];
                System.arraycopy(this.o.i, 0, mScene.u.e, 0, this.o.i.length);
                this.mSceneSrc.u = new SceneApi.EffectiveTimeSpan();
                this.mSceneSrc.u.f21526a = this.o.e;
                this.mSceneSrc.u.c = this.o.g;
                this.mSceneSrc.u.b = this.o.f;
                this.mSceneSrc.u.d = this.o.h;
                this.mSceneSrc.u.e = new int[this.o.i.length];
                System.arraycopy(this.o.i, 0, this.mSceneSrc.u.e, 0, this.o.i.length);
            }
            this.mExeTimeRL.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    SmarthomeCreateAutoSceneActivity.this.d(view);
                }
            });
        } else if (q == 0) {
            this.mExeTimeRL.setVisibility(0);
            this.mExeTimeRL.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    SmarthomeCreateAutoSceneActivity.this.c(view);
                }
            });
        } else {
            this.mExeTimeRL.setVisibility(0);
            this.mExeTimeRL.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    SmarthomeCreateAutoSceneActivity.this.b(view);
                }
            });
        }
        if (mScene == null || mScene.u == null) {
            this.mExeTimeTV.setText(R.string.scene_exetime_all_day);
        } else if (mScene.u.f21526a == mScene.u.b && mScene.u.c == mScene.u.d) {
            this.mExeTimeTV.setText(R.string.scene_exetime_all_day);
        } else {
            int rawOffset = new GregorianCalendar().getTimeZone().getRawOffset();
            int convert = (int) TimeUnit.HOURS.convert((long) rawOffset, TimeUnit.MILLISECONDS);
            SceneLogUtil.a("offsetHOser----" + convert + "--mGTMoffeset---" + rawOffset);
            int i2 = (((mScene.u.f21526a + convert) + -8) + 24) % 24;
            int i3 = mScene.u.c;
            int i4 = (((mScene.u.b + convert) + -8) + 24) % 24;
            int i5 = mScene.u.d;
            if (i4 < i2 || (i4 == i2 && i5 < i3)) {
                TextView textView = this.mExeTimeTV;
                textView.setText(a(i2, i3) + "-" + a(i4, i5) + Operators.BRACKET_START_STR + getResources().getString(R.string.scene_exetime_second_day) + Operators.BRACKET_END_STR);
            } else {
                TextView textView2 = this.mExeTimeTV;
                textView2.setText(a(i2, i3) + "-" + a(i4, i5));
            }
        }
        if (q() > 1) {
            this.mExeTimeTV.setText(R.string.scene_exetime_no_set);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void e(View view) {
        SceneLogUtil.a("启动生效时间段页面");
        startActivity(new Intent(this.mContext, SceneCreateTimeEdit2Activity.class));
        StatHelper.g();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void d(View view) {
        SceneLogUtil.a("启动生效时间段页面");
        startActivity(new Intent(this.mContext, SceneCreateTimeEdit2Activity.class));
        StatHelper.g();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void c(View view) {
        SceneLogUtil.a("启动生效时间段页面");
        startActivity(new Intent(this.mContext, SceneCreateTimeEdit2Activity.class));
        StatHelper.g();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(View view) {
        s();
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

    private void s() {
        new MLAlertDialog.Builder(this).b((int) R.string.delete_old_time_span_tips).a((int) R.string.delete, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                SmarthomeCreateAutoSceneActivity.this.b(dialogInterface, i);
            }
        }).b((int) R.string.cancel, (DialogInterface.OnClickListener) $$Lambda$SmarthomeCreateAutoSceneActivity$9mDbVNrczdl50CYWC_cN8HxcNQc.INSTANCE).b().show();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(DialogInterface dialogInterface, int i2) {
        for (SceneApi.Condition next : mScene.l) {
            if (next.c != null) {
                next.c.e = -1;
                next.c.f = -1;
                next.c.g = -1;
                next.c.h = -1;
                next.c.i = null;
            }
        }
        if (mScene.u == null) {
            mScene.u = new SceneApi.EffectiveTimeSpan();
        }
        mScene.v = true;
        this.mExeTimeRL.setVisibility(0);
        this.mExeTimeRL.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                SmarthomeCreateAutoSceneActivity.this.a(view);
            }
        });
        refreshUI();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        SceneLogUtil.a("启动生效时间段页面");
        startActivity(new Intent(this.mContext, SceneCreateTimeEdit2Activity.class));
        StatHelper.g();
    }

    /* access modifiers changed from: package-private */
    public void initOnlineAndAvatarResource() {
        if (mScene != null && mScene.k != null) {
            this.mActionOnlineItems = new boolean[mScene.k.size()];
            Arrays.fill(this.mActionOnlineItems, false);
            for (int i2 = 0; i2 < mScene.k.size(); i2++) {
                if (SmartHomeSceneUtility.a(mScene.k.get(i2)).b) {
                    this.mActionOnlineItems[i2] = true;
                }
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

    private void t() {
        Intent intent = new Intent(this.mContext, StartConditionActivityNew.class);
        intent.putExtra(FROM_MAIN, this.f);
        startActivityForResult(intent, 1);
        overridePendingTransition(0, 0);
        CreateSceneManager.a().l();
    }
}
