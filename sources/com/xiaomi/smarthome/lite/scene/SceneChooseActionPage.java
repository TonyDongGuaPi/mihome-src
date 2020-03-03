package com.xiaomi.smarthome.lite.scene;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
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
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.util.CommonUtils;
import com.xiaomi.smarthome.scene.AutoSceneActionChooseActivity;
import com.xiaomi.smarthome.scene.AutoSceneActionDetailChooseActivity;
import com.xiaomi.smarthome.scene.CreateSceneManager;
import com.xiaomi.smarthome.scene.SmartHomeScenceActionFactory;
import com.xiaomi.smarthome.scene.SmartHomeSceneActionChooseActivity;
import com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity;
import com.xiaomi.smarthome.scene.action.AutoSceneAction;
import com.xiaomi.smarthome.scene.action.BaseInnerAction;
import com.xiaomi.smarthome.scene.action.DelayInnerAction;
import com.xiaomi.smarthome.scene.action.InnerActionCommon;
import com.xiaomi.smarthome.scene.action.PushInnerAction;
import com.xiaomi.smarthome.scene.api.SceneApi;
import com.xiaomi.youpin.app_sdk.url_dispatch.UrlDispatchManger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class SceneChooseActionPage extends BaseActivity {
    public static final String EXTRA_DEFAULT_ACTIONS = "extra_default_actions";
    public static final String EXTRA_SCENE_ACTION_INDEX = "extra_action_index";
    public static final int GET_ACTION_DETAIL = 101;

    /* renamed from: a  reason: collision with root package name */
    private List<SmartHomeSceneCreateEditActivity.DefaultSceneItemSet> f19400a;
    /* access modifiers changed from: private */
    public int b = -1;
    /* access modifiers changed from: private */
    public int c = -1;
    /* access modifiers changed from: private */
    public HashMap<BaseInnerAction, Boolean> d = new HashMap<>();
    List<Object> deviceList = new ArrayList();
    @BindView(2131428019)
    Button mBuyButton;
    @BindView(2131428133)
    View mBuyView;
    @BindView(2131428548)
    ListView mContentListView;
    Context mContext;
    Object mCurrentDevice;
    BaseInnerAction mCurrentScenceAction;
    BaseAdapter mListAdapter;
    @BindView(2131430975)
    TextView mModuleA3ReturnTransparentTitle;
    int mRequestId = -1;
    SceneApi.SmartHomeScene mScene;
    int mSceneActionIndex;
    int mSceneClientPosition = -1;
    String mSceneID;
    SceneApi.Action mSelectedAction;
    ArrayList<BaseInnerAction> mSmartHomeScenceActions = new ArrayList<>();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.lte_scene_action_choose);
        this.mContext = this;
        ButterKnife.bind((Activity) this);
        this.mSceneActionIndex = getIntent().getIntExtra("extra_action_index", -1);
        this.f19400a = getIntent().getParcelableArrayListExtra("extra_default_actions");
        this.mScene = CreateSceneManager.a().g();
        if (this.mScene == null) {
            finish();
            return;
        }
        this.mSceneID = this.mScene.f;
        this.mModuleA3ReturnTransparentTitle.setText(R.string.do_task);
        initDeviceList();
        this.mListAdapter = new ItemAdapter();
        this.mContentListView.setAdapter(this.mListAdapter);
        if (this.mSmartHomeScenceActions.size() == 0) {
            this.mBuyView.setVisibility(0);
            this.mContentListView.setVisibility(8);
            this.mBuyButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    UrlDispatchManger.a().c("https://home.mi.com/shop/main");
                }
            });
            return;
        }
        this.mBuyView.setVisibility(8);
    }

    /* access modifiers changed from: package-private */
    public void initDeviceList() {
        AutoSceneAction autoSceneAction;
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
        this.mSmartHomeScenceActions.add(new PushInnerAction());
        Iterator<SceneApi.Action> it = this.mScene.k.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            SceneApi.Action next = it.next();
            if (next.g != null && (next.g instanceof SceneApi.SHScenePushPayload)) {
                this.mSmartHomeScenceActions.remove(this.mSmartHomeScenceActions.size() - 1);
                break;
            }
        }
        if (SceneManager.x().h() != null) {
            autoSceneAction = new AutoSceneAction(SceneManager.x().h());
            this.mSmartHomeScenceActions.add(autoSceneAction);
        } else {
            autoSceneAction = null;
        }
        this.mSelectedAction = CreateSceneManager.a().k();
        BaseInnerAction baseInnerAction = null;
        Device device = null;
        for (int size2 = this.deviceList.size() - 1; size2 >= 0; size2--) {
            Device device2 = (Device) this.deviceList.get(size2);
            if (device2.isOwner()) {
                BaseInnerAction a2 = SmartHomeScenceActionFactory.a(device2, (SmartHomeSceneCreateEditActivity.DefaultSceneItemSet) null);
                if (a2 != null) {
                    this.mSmartHomeScenceActions.add(a2);
                    Iterator<SceneApi.Action> it2 = this.mScene.k.iterator();
                    while (true) {
                        if (it2.hasNext()) {
                            SceneApi.Action next2 = it2.next();
                            int a3 = a2.a(next2, this.deviceList.get(size2));
                            if (a3 >= 0 && this.mSelectedAction != null && this.mSelectedAction.equals(next2)) {
                                this.b = a3;
                                device = (Device) this.deviceList.get(size2);
                                baseInnerAction = a2;
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                } else {
                    this.deviceList.remove(size2);
                }
            } else {
                this.deviceList.remove(size2);
            }
        }
        if (this.b != -1) {
            this.c = this.mScene.k.indexOf(this.mSelectedAction);
            this.mScene.k.remove(this.mSelectedAction);
            CreateSceneManager.a().d(this.mScene);
        }
        ArrayList arrayList2 = new ArrayList();
        for (int size3 = this.mSmartHomeScenceActions.size() - 1; size3 >= 0; size3--) {
            if ((this.mSmartHomeScenceActions.get(size3) instanceof InnerActionCommon) && this.mSmartHomeScenceActions.get(size3).e()) {
                this.d.put(this.mSmartHomeScenceActions.get(size3), false);
                arrayList2.add(this.mSmartHomeScenceActions.remove(size3));
            }
        }
        this.mSmartHomeScenceActions.addAll(arrayList2);
        if (this.b != -1) {
            gotoDetailPage(baseInnerAction, device);
        }
        if (this.mSelectedAction != null && (this.mSelectedAction.g instanceof SceneApi.SHSceneAutoPayload)) {
            this.c = this.mScene.k.indexOf(this.mSelectedAction);
            if (this.c == -1) {
                this.b = -1;
                this.mSelectedAction = null;
                return;
            }
            Intent intent = new Intent();
            intent.setClass(this, AutoSceneActionChooseActivity.class);
            intent.putExtra(AutoSceneActionChooseActivity.SELECT_SCENE_ID, ((SceneApi.SHSceneAutoPayload) this.mSelectedAction.g).f21530a);
            intent.putExtra(AutoSceneActionDetailChooseActivity.EXTRA_ENABEL, ((SceneApi.SHSceneAutoPayload) this.mSelectedAction.g).b);
            startActivityForResult(intent, 101);
            if (autoSceneAction == null) {
                autoSceneAction = new AutoSceneAction(SceneManager.x().h());
            }
            CreateSceneManager.a().a((BaseInnerAction) autoSceneAction);
            this.mCurrentScenceAction = autoSceneAction;
            this.mScene.k.remove(this.mSelectedAction);
        }
    }

    /* access modifiers changed from: package-private */
    public void gotoDetailPage(BaseInnerAction baseInnerAction, Device device) {
        Intent intent = new Intent();
        intent.setClass(this, LiteSceneActionDetailPage.class);
        intent.putExtra("extra_title", baseInnerAction.a((Object) device));
        intent.putExtra("extra_selected_title", this.b);
        startActivityForResult(intent, 101);
        CreateSceneManager.a().a(baseInnerAction);
        this.mCurrentScenceAction = baseInnerAction;
        this.mCurrentDevice = device;
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && i == 101) {
            getActionItem(intent.getIntExtra("extra_index", -1), intent.getIntExtra(AutoSceneActionDetailChooseActivity.EXTRA_ENABEL, -1));
        } else if (i2 == -1 && this.mRequestId >= 0) {
            SceneApi.Action a2 = this.mCurrentScenceAction.a(this.mCurrentScenceAction.b()[this.mRequestId], this.mCurrentScenceAction.c()[this.mRequestId], this.mCurrentDevice, intent);
            addScence(a2);
            if (a2.d != 0) {
                addCompatibleId(a2.d);
            }
            finish();
        }
        if (i2 == 0 && this.b != -1) {
            this.mScene.k.add(this.c, this.mSelectedAction);
            if (this.mSelectedAction.d != 0) {
                addCompatibleId(this.mSelectedAction.d);
            }
            finish();
        }
    }

    public void onActivityResult(final int i, final Intent intent) {
        this.mHandler.post(new Runnable() {
            public void run() {
                if (i == -1) {
                    SceneApi.Action a2 = SceneChooseActionPage.this.mCurrentScenceAction.a(SceneChooseActionPage.this.mCurrentScenceAction.b()[SceneChooseActionPage.this.mRequestId], SceneChooseActionPage.this.mCurrentScenceAction.c()[SceneChooseActionPage.this.mRequestId], SceneChooseActionPage.this.mCurrentDevice, intent);
                    SceneChooseActionPage.this.addScence(a2);
                    SceneChooseActionPage.this.addCompatibleId(a2.d);
                    SceneChooseActionPage.this.finish();
                    return;
                }
                if (!(SceneChooseActionPage.this.b == -1 || SceneChooseActionPage.this.mSelectedAction == null)) {
                    SceneChooseActionPage.this.mScene.k.add(SceneChooseActionPage.this.c, SceneChooseActionPage.this.mSelectedAction);
                    if (SceneChooseActionPage.this.mSelectedAction.d != 0) {
                        SceneChooseActionPage.this.addCompatibleId(SceneChooseActionPage.this.mSelectedAction.d);
                    }
                    SceneChooseActionPage.this.finish();
                }
                SceneChooseActionPage.this.mRequestId = 0;
                SceneChooseActionPage.this.mCurrentScenceAction.b(-1);
                SceneChooseActionPage.this.mCurrentDevice = null;
                SceneChooseActionPage.this.mListAdapter.notifyDataSetChanged();
            }
        });
    }

    @OnClick({2131430969})
    public void close() {
        finish();
    }

    class ItemAdapter extends BaseAdapter {
        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        ItemAdapter() {
        }

        public int getCount() {
            return SceneChooseActionPage.this.mSmartHomeScenceActions.size();
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = SceneChooseActionPage.this.getLayoutInflater().inflate(R.layout.scene_choose_device_item, (ViewGroup) null);
            }
            SimpleDraweeView simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.content_icon);
            TextView textView = (TextView) view.findViewById(R.id.content);
            ImageView imageView = (ImageView) SceneChooseActionPage.this.findViewById(R.id.expand_hint);
            View findViewById = view.findViewById(R.id.scene_chooese_device_root);
            View findViewById2 = view.findViewById(R.id.divide_line);
            int i2 = 8;
            ((TextView) view.findViewById(R.id.content_description)).setVisibility(8);
            findViewById.setBackgroundResource(i != getCount() + -1 ? R.drawable.choose_device_list_item_last_position_bg : R.drawable.common_white_list_padding_no_left_margin);
            if (i != getCount() - 1) {
                i2 = 0;
            }
            findViewById2.setVisibility(i2);
            simpleDraweeView.setHierarchy(new GenericDraweeHierarchyBuilder(simpleDraweeView.getResources()).setFadeDuration(200).setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER).setPlaceholderImage(simpleDraweeView.getResources().getDrawable(R.drawable.device_list_phone_no)).build());
            final BaseInnerAction baseInnerAction = SceneChooseActionPage.this.mSmartHomeScenceActions.get(i);
            if (baseInnerAction instanceof PushInnerAction) {
                simpleDraweeView.setImageURI(CommonUtils.c((int) R.drawable.std_scene_push_icon_real));
                textView.setText(baseInnerAction.a((Object) 0));
                view.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        SceneChooseActionPage.this.addScence(baseInnerAction.a((String) null, 0, (Object) null, (Intent) null));
                        SceneChooseActionPage.this.finish();
                    }
                });
                return view;
            } else if (baseInnerAction instanceof DelayInnerAction) {
                simpleDraweeView.setImageURI(CommonUtils.c((int) R.drawable.std_scene_icon_delayed));
                textView.setText(baseInnerAction.a((Object) 0));
                view.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        SceneChooseActionPage.this.mCurrentScenceAction = baseInnerAction;
                        SceneChooseActionPage.this.getActionItem(0, -1);
                    }
                });
                return view;
            } else if (baseInnerAction instanceof AutoSceneAction) {
                simpleDraweeView.setImageURI(CommonUtils.c((int) R.drawable.icon_auto_secne_icon));
                textView.setText(baseInnerAction.a((Object) null));
                view.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Intent intent = new Intent();
                        intent.setClass(SceneChooseActionPage.this, AutoSceneActionChooseActivity.class);
                        SceneChooseActionPage.this.startActivityForResult(intent, 101);
                        CreateSceneManager.a().a(baseInnerAction);
                        SceneChooseActionPage.this.mCurrentScenceAction = baseInnerAction;
                    }
                });
                return view;
            } else {
                final InnerActionCommon innerActionCommon = (InnerActionCommon) baseInnerAction;
                DeviceFactory.b(innerActionCommon.g().model, simpleDraweeView);
                textView.setText(baseInnerAction.a((Object) innerActionCommon.g()));
                if (!SceneChooseActionPage.this.d.containsKey(innerActionCommon) || ((Boolean) SceneChooseActionPage.this.d.get(innerActionCommon)).booleanValue()) {
                    view.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            Intent intent = new Intent();
                            intent.setClass(SceneChooseActionPage.this, LiteSceneActionDetailPage.class);
                            intent.putExtra("extra_title", baseInnerAction.a((Object) innerActionCommon.g()));
                            SceneChooseActionPage.this.startActivityForResult(intent, 101);
                            CreateSceneManager.a().a(baseInnerAction);
                            SceneChooseActionPage.this.mCurrentScenceAction = baseInnerAction;
                            SceneChooseActionPage.this.mCurrentDevice = innerActionCommon.g();
                        }
                    });
                } else {
                    imageView.setImageResource(R.drawable.std_scene_icon_lock);
                    view.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            Toast.makeText(SceneChooseActionPage.this, R.string.scene_unclickable_toast_2, 0).show();
                        }
                    });
                }
                return view;
            }
        }
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
        if (this.c != -1) {
            this.mScene.k.add(this.c, action);
        } else {
            this.mScene.k.add(action);
        }
    }

    public void getActionItem(int i, int i2) {
        if (this.mCurrentScenceAction != null && this.mCurrentScenceAction.b() != null && this.mCurrentScenceAction.c() != null && this.mCurrentScenceAction.b().length > 0 && this.mCurrentScenceAction.c().length > 0) {
            int a2 = this.mCurrentScenceAction.a(new SmartHomeSceneActionChooseActivity.InnerValueCallback() {
                public void a(Intent intent, int i) {
                    SceneChooseActionPage.this.startActivityForResult(intent, i);
                }

                public void a(int i, Intent intent) {
                    SceneChooseActionPage.this.onActivityResult(i, intent);
                }
            }, getContext(), this.mCurrentScenceAction.b()[i], this.mCurrentScenceAction.c()[i], this.mCurrentDevice, this.mSelectedAction == null ? null : this.mSelectedAction.g.f);
            if (a2 >= 0) {
                this.mRequestId = i;
            } else if (a2 == -1) {
                this.mListAdapter.notifyDataSetChanged();
            } else if (a2 == -3) {
                Intent intent = new Intent();
                intent.putExtra(AutoSceneAction.f21495a, i2);
                SceneApi.Action a3 = this.mCurrentScenceAction.a(this.mCurrentScenceAction.b()[i], this.mCurrentScenceAction.c()[i], this.mCurrentDevice, intent);
                addScence(a3);
                if (a3.d != 0) {
                    addCompatibleId(a3.d);
                }
                finish();
            } else {
                SceneApi.Action a4 = this.mCurrentScenceAction.a(this.mCurrentScenceAction.b()[i], this.mCurrentScenceAction.c()[i], this.mCurrentDevice, (Intent) null);
                addScence(a4);
                if (a4.d != 0) {
                    addCompatibleId(a4.d);
                }
                finish();
            }
        }
    }

    public void addCompatibleId(int i) {
        if (CreateSceneManager.a().b(Integer.valueOf(i))) {
            CreateSceneManager.a().a(SceneManager.x().a(i));
            CreateSceneManager.a().b(SceneManager.x().b(i));
            return;
        }
        CreateSceneManager.a().e();
    }
}
