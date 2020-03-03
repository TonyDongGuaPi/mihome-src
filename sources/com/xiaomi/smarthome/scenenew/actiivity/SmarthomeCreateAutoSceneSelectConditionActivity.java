package com.xiaomi.smarthome.scenenew.actiivity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractDraggableItemViewHolder;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.scene.CreateSceneManager;
import com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity;
import com.xiaomi.smarthome.scene.api.SceneApi;
import com.xiaomi.smarthome.scene.condition.BaseInnerCondition;
import com.xiaomi.smarthome.scene.condition.CommonTextCondition;
import com.xiaomi.smarthome.scene.condition.IntelligentTextCondition;
import com.xiaomi.smarthome.scene.condition.LocaleGetResourceFixHelper;
import com.xiaomi.smarthome.scene.condition.TimerInnerCondition;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SmarthomeCreateAutoSceneSelectConditionActivity extends BaseActivity {
    HashMap<BaseInnerCondition, Boolean> mConditionEnableMap = new HashMap<>();
    RecyclerView mConditionRV;
    Context mContext;
    List<BaseInnerCondition> mDeviceConditions = new ArrayList();
    LayoutInflater mInflater;
    int mSId = -1;
    SceneApi.SmartHomeScene mScene;
    SceneApi.Condition mSelectedCondition = null;
    int mSelectedConditionPos = -1;

    public class ConditionViewHolder_ViewBinding implements Unbinder {

        /* renamed from: a  reason: collision with root package name */
        private ConditionViewHolder f21869a;

        @UiThread
        public ConditionViewHolder_ViewBinding(ConditionViewHolder conditionViewHolder, View view) {
            this.f21869a = conditionViewHolder;
            conditionViewHolder.mIcon = (SimpleDraweeView) Utils.findRequiredViewAsType(view, R.id.content_icon, "field 'mIcon'", SimpleDraweeView.class);
            conditionViewHolder.mRootView = Utils.findRequiredView(view, R.id.true_item_view, "field 'mRootView'");
            conditionViewHolder.mExpandHint = (ImageView) Utils.findRequiredViewAsType(view, R.id.expand_hint, "field 'mExpandHint'", ImageView.class);
            conditionViewHolder.mContentTV = (TextView) Utils.findRequiredViewAsType(view, R.id.content, "field 'mContentTV'", TextView.class);
            conditionViewHolder.mTitleRl = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.title_rl, "field 'mTitleRl'", RelativeLayout.class);
            conditionViewHolder.mTitleTV = (TextView) Utils.findRequiredViewAsType(view, R.id.title, "field 'mTitleTV'", TextView.class);
            conditionViewHolder.mFilterTV = (TextView) Utils.findRequiredViewAsType(view, R.id.filter, "field 'mFilterTV'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            ConditionViewHolder conditionViewHolder = this.f21869a;
            if (conditionViewHolder != null) {
                this.f21869a = null;
                conditionViewHolder.mIcon = null;
                conditionViewHolder.mRootView = null;
                conditionViewHolder.mExpandHint = null;
                conditionViewHolder.mContentTV = null;
                conditionViewHolder.mTitleRl = null;
                conditionViewHolder.mTitleTV = null;
                conditionViewHolder.mFilterTV = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_create_auto_scene_select_condition_layout);
        this.mContext = this;
        this.mInflater = LayoutInflater.from(this.mContext);
        this.mScene = CreateSceneManager.a().g();
        if (this.mScene == null) {
            finish();
        } else {
            a();
        }
    }

    public class ConditionAdapter extends RecyclerView.Adapter<ConditionViewHolder> {
        private List<BaseInnerCondition> b = new ArrayList();

        public ConditionAdapter() {
        }

        /* renamed from: a */
        public ConditionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new ConditionViewHolder(SmarthomeCreateAutoSceneSelectConditionActivity.this.mInflater.inflate(R.layout.smarthome_create_auto_scene_select_condition_item, (ViewGroup) null));
        }

        /* renamed from: a */
        public void onBindViewHolder(ConditionViewHolder conditionViewHolder, int i) {
            BaseInnerCondition baseInnerCondition = this.b.get(i);
            if (baseInnerCondition instanceof CommonTextCondition) {
                conditionViewHolder.mRootView.setVisibility(8);
                conditionViewHolder.mTitleRl.setVisibility(0);
                conditionViewHolder.mFilterTV.setVisibility(8);
                if (baseInnerCondition instanceof LocaleGetResourceFixHelper) {
                    try {
                        conditionViewHolder.mTitleTV.setText(((CommonTextCondition) baseInnerCondition).a());
                    } catch (Exception unused) {
                        conditionViewHolder.mTitleTV.setText(baseInnerCondition.e());
                    }
                } else {
                    conditionViewHolder.mTitleTV.setText(baseInnerCondition.e());
                }
            } else if (baseInnerCondition instanceof IntelligentTextCondition) {
                conditionViewHolder.mRootView.setVisibility(8);
                conditionViewHolder.mTitleRl.setVisibility(0);
                conditionViewHolder.mFilterTV.setVisibility(0);
                if (baseInnerCondition instanceof LocaleGetResourceFixHelper) {
                    try {
                        conditionViewHolder.mTitleTV.setText(((CommonTextCondition) baseInnerCondition).a());
                    } catch (Exception unused2) {
                        conditionViewHolder.mTitleTV.setText(baseInnerCondition.e());
                    }
                } else {
                    conditionViewHolder.mTitleTV.setText(baseInnerCondition.e());
                }
                conditionViewHolder.mFilterTV.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        ToastUtil.a((CharSequence) "filter");
                    }
                });
            } else {
                conditionViewHolder.mRootView.setVisibility(0);
                conditionViewHolder.mTitleRl.setVisibility(8);
                if (baseInnerCondition instanceof LocaleGetResourceFixHelper) {
                    try {
                        conditionViewHolder.mContentTV.setText(((CommonTextCondition) baseInnerCondition).a());
                    } catch (Exception unused3) {
                        conditionViewHolder.mContentTV.setText(baseInnerCondition.e());
                    }
                } else {
                    conditionViewHolder.mContentTV.setText(baseInnerCondition.e());
                }
                conditionViewHolder.mIcon.setHierarchy(new GenericDraweeHierarchyBuilder(conditionViewHolder.mIcon.getResources()).setFadeDuration(200).setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER).setPlaceholderImageScaleType(ScalingUtils.ScaleType.FIT_CENTER).build());
                baseInnerCondition.a(conditionViewHolder.mIcon);
            }
        }

        public int getItemCount() {
            return this.b.size();
        }
    }

    public class ConditionViewHolder extends AbstractDraggableItemViewHolder {
        @BindView(2131428541)
        TextView mContentTV;
        @BindView(2131429112)
        ImageView mExpandHint;
        @BindView(2131429228)
        TextView mFilterTV;
        @BindView(2131428547)
        SimpleDraweeView mIcon;
        @BindView(2131433086)
        View mRootView;
        @BindView(2131432968)
        RelativeLayout mTitleRl;
        @BindView(2131432910)
        TextView mTitleTV;

        public ConditionViewHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    private void a() {
        boolean z;
        Device b;
        BaseInnerCondition a2;
        boolean z2;
        List<Device> d = SmartHomeDeviceManager.a().d();
        for (int size = d.size() - 1; size >= 0; size--) {
            if (d.get(size).isSubDevice()) {
                d.remove(size);
            }
        }
        for (int i = 0; i < d.size(); i++) {
            BaseInnerCondition a3 = BaseInnerCondition.a(d.get(i), (SmartHomeSceneCreateEditActivity.DefaultSceneItemSet) null);
            if (a3 != null) {
                this.mDeviceConditions.add(a3);
            }
        }
        for (Device next : SmartHomeDeviceManager.a().k()) {
            if (next.isOwner() && (b = SmartHomeDeviceManager.a().b(next.parentId)) != null && b.isOwner() && (a2 = BaseInnerCondition.a(next, (SmartHomeSceneCreateEditActivity.DefaultSceneItemSet) null)) != null) {
                int i2 = 0;
                while (true) {
                    if (i2 >= this.mDeviceConditions.size()) {
                        z2 = false;
                        break;
                    }
                    Device d2 = this.mDeviceConditions.get(i2).d();
                    if (d2 != null && !d2.isSubDevice() && next.parentId.equalsIgnoreCase(d2.did)) {
                        this.mDeviceConditions.add(i2 + 1, a2);
                        z2 = true;
                        break;
                    }
                    i2++;
                }
                if (!z2) {
                    this.mDeviceConditions.add(a2);
                }
            }
        }
        this.mDeviceConditions.add(0, new CommonTextCondition((Device) null));
        this.mDeviceConditions.add(1, new TimerInnerCondition((Device) null));
        this.mDeviceConditions.add(4, new IntelligentTextCondition((Device) null));
        this.mSelectedCondition = CreateSceneManager.a().i();
        for (int i3 = 0; i3 < this.mScene.l.size(); i3++) {
            int size2 = this.mDeviceConditions.size() - 1;
            while (true) {
                if (size2 < 0) {
                    break;
                }
                int a4 = this.mDeviceConditions.get(size2).a(this.mScene.l.get(i3));
                if (a4 == -1) {
                    size2--;
                } else if (this.mSelectedCondition != null && this.mSelectedCondition.equals(this.mScene.l.get(i3))) {
                    BaseInnerCondition baseInnerCondition = this.mDeviceConditions.get(size2);
                    this.mSId = a4;
                } else if (!this.mDeviceConditions.get(size2).g()) {
                    this.mConditionEnableMap.put(this.mDeviceConditions.get(size2), false);
                } else {
                    this.mDeviceConditions.get(size2).a(a4);
                    if (this.mDeviceConditions.get(size2).c()) {
                        this.mConditionEnableMap.put(this.mDeviceConditions.get(size2), false);
                    }
                }
            }
        }
        if (this.mSId != -1) {
            this.mSelectedConditionPos = this.mScene.l.indexOf(this.mSelectedCondition);
            this.mScene.l.remove(this.mSelectedCondition);
            CreateSceneManager.a().d(this.mScene);
        }
        ArrayList arrayList = new ArrayList();
        for (int size3 = this.mDeviceConditions.size() - 1; size3 >= 0; size3--) {
            if (this.mDeviceConditions.get(size3).g()) {
                int[] f = this.mDeviceConditions.get(size3).f();
                int length = f.length;
                int i4 = 0;
                while (true) {
                    if (i4 >= length) {
                        z = true;
                        break;
                    }
                    Integer valueOf = Integer.valueOf(f[i4]);
                    if (CreateSceneManager.a().g().q == 1 || CreateSceneManager.a().a(Integer.valueOf(this.mDeviceConditions.get(size3).c(valueOf.intValue())))) {
                        z = false;
                    } else {
                        i4++;
                    }
                }
                z = false;
                if (!z && this.mDeviceConditions.get(size3).c()) {
                    z = true;
                }
                if (z) {
                    this.mConditionEnableMap.put(this.mDeviceConditions.get(size3), false);
                    arrayList.add(this.mDeviceConditions.remove(size3));
                }
            } else if (CreateSceneManager.a().g().q == 1 || CreateSceneManager.a().a(Integer.valueOf(this.mDeviceConditions.get(size3).c(0)))) {
                this.mConditionEnableMap.put(this.mDeviceConditions.get(size3), true);
            } else {
                this.mConditionEnableMap.put(this.mDeviceConditions.get(size3), false);
                arrayList.add(this.mDeviceConditions.remove(size3));
            }
        }
        this.mDeviceConditions.addAll(arrayList);
        int i5 = this.mSId;
    }
}
