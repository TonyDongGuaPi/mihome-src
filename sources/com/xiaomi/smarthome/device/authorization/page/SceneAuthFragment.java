package com.xiaomi.smarthome.device.authorization.page;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.api.DeviceApi;
import com.xiaomi.smarthome.device.authorization.SceneAuthData;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.page.BaseFragment;
import com.xiaomi.smarthome.library.common.util.CommonUtils;
import com.xiaomi.smarthome.scene.SmartHomeSceneUtility;
import com.xiaomi.smarthome.scene.api.RemoteSceneApi;
import com.xiaomi.smarthome.scene.api.SceneApi;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class SceneAuthFragment extends BaseFragment {

    /* renamed from: a  reason: collision with root package name */
    View f15051a;
    List<SceneApi.SmartHomeScene> b = new ArrayList();
    Map<String, Boolean> c = new HashMap();
    /* access modifiers changed from: private */
    public SimpleSceneAdapter d;
    /* access modifiers changed from: private */
    public String e;
    @BindView(2131428503)
    View emptyView;
    /* access modifiers changed from: private */
    public Map<String, Boolean> f = new HashMap();
    /* access modifiers changed from: private */
    public SparseBooleanArray g = new SparseBooleanArray();
    /* access modifiers changed from: private */
    public boolean h = false;
    @BindView(2131428804)
    RecyclerView mSceneList;

    class SimpleSceneAdapter extends RecyclerView.Adapter<AutoSceneViewHolder> {

        public class AutoSceneViewHolder_ViewBinding implements Unbinder {

            /* renamed from: a  reason: collision with root package name */
            private AutoSceneViewHolder f15058a;

            @UiThread
            public AutoSceneViewHolder_ViewBinding(AutoSceneViewHolder autoSceneViewHolder, View view) {
                this.f15058a = autoSceneViewHolder;
                autoSceneViewHolder.actionLL = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.action_ll, "field 'actionLL'", LinearLayout.class);
                autoSceneViewHolder.mIconCondition = (SimpleDraweeView) Utils.findRequiredViewAsType(view, R.id.icon_condition, "field 'mIconCondition'", SimpleDraweeView.class);
                autoSceneViewHolder.mIconConditionMore = (ImageView) Utils.findRequiredViewAsType(view, R.id.icon_condition_more, "field 'mIconConditionMore'", ImageView.class);
                autoSceneViewHolder.tvSceneName = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_scene_name, "field 'tvSceneName'", TextView.class);
                autoSceneViewHolder.mCheckBox = (CheckBox) Utils.findRequiredViewAsType(view, R.id.ckb_edit_selected, "field 'mCheckBox'", CheckBox.class);
                autoSceneViewHolder.mMoreIcon = Utils.findRequiredView(view, R.id.scene_new_more_icon, "field 'mMoreIcon'");
                autoSceneViewHolder.mDivideLine = Utils.findRequiredView(view, R.id.divide_line, "field 'mDivideLine'");
                autoSceneViewHolder.mDivideLineBottom = Utils.findRequiredView(view, R.id.divide_line_bottom, "field 'mDivideLineBottom'");
            }

            @CallSuper
            public void unbind() {
                AutoSceneViewHolder autoSceneViewHolder = this.f15058a;
                if (autoSceneViewHolder != null) {
                    this.f15058a = null;
                    autoSceneViewHolder.actionLL = null;
                    autoSceneViewHolder.mIconCondition = null;
                    autoSceneViewHolder.mIconConditionMore = null;
                    autoSceneViewHolder.tvSceneName = null;
                    autoSceneViewHolder.mCheckBox = null;
                    autoSceneViewHolder.mMoreIcon = null;
                    autoSceneViewHolder.mDivideLine = null;
                    autoSceneViewHolder.mDivideLineBottom = null;
                    return;
                }
                throw new IllegalStateException("Bindings already cleared.");
            }
        }

        SimpleSceneAdapter() {
        }

        /* renamed from: a */
        public AutoSceneViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new AutoSceneViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_auth_scene, viewGroup, false));
        }

        /* renamed from: a */
        public void onBindViewHolder(AutoSceneViewHolder autoSceneViewHolder, final int i) {
            SceneApi.SmartHomeScene smartHomeScene = SceneAuthFragment.this.b.get(i);
            if (smartHomeScene != null) {
                if (smartHomeScene.l.size() > 0) {
                    autoSceneViewHolder.mIconCondition.setVisibility(0);
                    autoSceneViewHolder.mIconCondition.setHierarchy(new GenericDraweeHierarchyBuilder(autoSceneViewHolder.mIconCondition.getResources()).setFadeDuration(200).setPlaceholderImage(autoSceneViewHolder.mIconCondition.getResources().getDrawable(R.drawable.device_list_phone_no)).setActualImageScaleType(ScalingUtils.ScaleType.FIT_START).setPlaceholderImageScaleType(ScalingUtils.ScaleType.FIT_START).build());
                    if (smartHomeScene == null || smartHomeScene.l.size() <= 0) {
                        autoSceneViewHolder.mIconCondition.setImageURI(CommonUtils.c((int) R.drawable.intelligence_icon_default_nor));
                    } else {
                        SmartHomeSceneUtility.a(autoSceneViewHolder.mIconCondition, smartHomeScene.l.get(0));
                    }
                    if (smartHomeScene.l.size() > 1) {
                        autoSceneViewHolder.mIconConditionMore.setVisibility(0);
                    } else {
                        autoSceneViewHolder.mIconConditionMore.setVisibility(8);
                    }
                    autoSceneViewHolder.mIconConditionMore.setImageResource(SmartHomeSceneUtility.f(smartHomeScene.q));
                } else {
                    autoSceneViewHolder.mIconCondition.setVisibility(8);
                    autoSceneViewHolder.mIconConditionMore.setVisibility(8);
                }
                if (SmartHomeSceneUtility.a(smartHomeScene, autoSceneViewHolder.actionLL) > 3) {
                    autoSceneViewHolder.mMoreIcon.setVisibility(0);
                } else {
                    autoSceneViewHolder.mMoreIcon.setVisibility(8);
                }
                autoSceneViewHolder.tvSceneName.setText(smartHomeScene.g);
                autoSceneViewHolder.mCheckBox.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) null);
                autoSceneViewHolder.mCheckBox.setChecked(SceneAuthFragment.this.c.get(smartHomeScene.f).booleanValue());
                autoSceneViewHolder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                        SimpleSceneAdapter.this.a(i);
                    }
                });
                if (i == getItemCount() - 1) {
                    autoSceneViewHolder.mDivideLine.setVisibility(8);
                    autoSceneViewHolder.mDivideLineBottom.setVisibility(0);
                    return;
                }
                autoSceneViewHolder.mDivideLine.setVisibility(0);
                autoSceneViewHolder.mDivideLineBottom.setVisibility(8);
            }
        }

        public int getItemCount() {
            if (SceneAuthFragment.this.b == null) {
                return 0;
            }
            return SceneAuthFragment.this.b.size();
        }

        /* access modifiers changed from: private */
        public void a(int i) {
            if (!SceneAuthFragment.this.c.get(SceneAuthFragment.this.b.get(i).f).booleanValue()) {
                SceneAuthFragment.this.c.put(SceneAuthFragment.this.b.get(i).f, true);
            } else {
                SceneAuthFragment.this.c.put(SceneAuthFragment.this.b.get(i).f, false);
            }
            if (SceneAuthFragment.this.g.get(i)) {
                SceneAuthFragment.this.g.put(i, false);
            } else {
                SceneAuthFragment.this.g.put(i, true);
            }
            notifyDataSetChanged();
            ((DeviceAuthSlaveListActivity) SceneAuthFragment.this.getActivity()).setSceneAuthChanged(true);
        }

        class AutoSceneViewHolder extends RecyclerView.ViewHolder {
            @BindView(2131427494)
            LinearLayout actionLL;
            @BindView(2131428355)
            CheckBox mCheckBox;
            @BindView(2131428895)
            View mDivideLine;
            @BindView(2131428896)
            View mDivideLineBottom;
            @BindView(2131429703)
            SimpleDraweeView mIconCondition;
            @BindView(2131429704)
            ImageView mIconConditionMore;
            @BindView(2131432152)
            View mMoreIcon;
            @BindView(2131433475)
            TextView tvSceneName;

            public AutoSceneViewHolder(View view) {
                super(view);
                ButterKnife.bind((Object) this, view);
            }
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.e = ((DeviceAuthSlaveListActivity) getActivity()).mDid;
        ((DeviceAuthSlaveListActivity) getActivity()).showProgressDialog(getResources().getString(R.string.loading_share_info));
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.f15051a == null) {
            this.f15051a = layoutInflater.inflate(R.layout.fragment_auth_scene, (ViewGroup) null);
            ButterKnife.bind((Object) this, this.f15051a);
        }
        return this.f15051a;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        c();
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.e);
        a((List) arrayList);
    }

    /* access modifiers changed from: private */
    public void a(final List list) {
        if (!this.h) {
            this.h = true;
            RemoteSceneApi.a().a((Context) getActivity(), 30, (AsyncCallback<List<SceneApi.SmartHomeScene>, Error>) new AsyncCallback<List<SceneApi.SmartHomeScene>, Error>() {
                /* renamed from: a */
                public void onSuccess(List<SceneApi.SmartHomeScene> list) {
                    SceneAuthFragment.this.b = list;
                    DeviceApi.getInstance().getAllSceneAuthData(SceneAuthFragment.this.getActivity(), list, new AsyncCallback<JSONObject, Error>() {
                        /* renamed from: a */
                        public void onSuccess(JSONObject jSONObject) {
                            JSONArray optJSONArray = jSONObject.optJSONArray("result");
                            if (optJSONArray.length() >= 1) {
                                SceneAuthFragment.this.c = SceneAuthData.a(optJSONArray.optJSONObject(0)).b;
                                Map unused = SceneAuthFragment.this.f = SceneAuthData.a(optJSONArray.optJSONObject(0)).b;
                            }
                            if (SceneAuthFragment.this.b == null) {
                                SceneAuthFragment.this.b = new ArrayList();
                            }
                            if (SceneAuthFragment.this.c != null) {
                                for (int size = SceneAuthFragment.this.b.size() - 1; size >= 0; size--) {
                                    if (!SceneAuthFragment.this.c.containsKey(SceneAuthFragment.this.b.get(size).f)) {
                                        SceneAuthFragment.this.b.remove(size);
                                    }
                                }
                            } else {
                                SceneAuthFragment.this.b.clear();
                            }
                            SceneAuthFragment.this.d.notifyDataSetChanged();
                            ((DeviceAuthSlaveListActivity) SceneAuthFragment.this.getActivity()).setSceneDataReady(true, true);
                            if (SceneAuthFragment.this.b.size() == 0) {
                                SceneAuthFragment.this.b();
                            } else {
                                SceneAuthFragment.this.a();
                            }
                            boolean unused2 = SceneAuthFragment.this.h = false;
                        }

                        public void onFailure(Error error) {
                            SceneAuthFragment.this.b.clear();
                            SceneAuthFragment.this.d.notifyDataSetChanged();
                            ((DeviceAuthSlaveListActivity) SceneAuthFragment.this.getActivity()).setSceneDataReady(true, false);
                            SceneAuthFragment.this.b();
                            boolean unused = SceneAuthFragment.this.h = false;
                        }
                    });
                }

                public void onFailure(Error error) {
                    SceneAuthFragment.this.b.clear();
                    SceneAuthFragment.this.d.notifyDataSetChanged();
                    ((DeviceAuthSlaveListActivity) SceneAuthFragment.this.getActivity()).setSceneDataReady(true, false);
                    SceneAuthFragment.this.b();
                    boolean unused = SceneAuthFragment.this.h = false;
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void a() {
        this.emptyView.setVisibility(8);
        this.mSceneList.setVisibility(0);
    }

    /* access modifiers changed from: private */
    public void b() {
        this.emptyView.setVisibility(0);
        this.mSceneList.setVisibility(8);
        this.emptyView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!SceneAuthFragment.this.h) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(SceneAuthFragment.this.e);
                    SceneAuthFragment.this.a((List) arrayList);
                }
            }
        });
        this.emptyView.setBackgroundResource(R.color.transparent);
        ((ImageView) this.emptyView.findViewById(R.id.empty_icon)).setImageResource(R.drawable.device_empty);
        ((TextView) this.emptyView.findViewById(R.id.common_white_empty_text)).setText(R.string.no_data_tips);
    }

    private void c() {
        this.mSceneList.setLayoutManager(new LinearLayoutManager(this.mContext));
        this.d = new SimpleSceneAdapter();
        this.mSceneList.setAdapter(this.d);
        this.d.notifyDataSetChanged();
        ((DeviceAuthSlaveListActivity) getActivity()).mTitleTextView.setText(R.string.control_scene_auth);
    }

    /* JADX WARNING: Removed duplicated region for block: B:8:0x001e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean a(java.util.Map<java.lang.String, java.lang.Boolean> r4, java.util.Map<java.lang.String, java.lang.Boolean> r5) {
        /*
            r3 = this;
            r0 = 0
            if (r5 == 0) goto L_0x0040
            if (r4 == 0) goto L_0x0040
            int r1 = r5.size()
            int r2 = r4.size()
            if (r1 == r2) goto L_0x0010
            goto L_0x0040
        L_0x0010:
            java.util.Set r4 = r4.entrySet()
            java.util.Iterator r4 = r4.iterator()
        L_0x0018:
            boolean r1 = r4.hasNext()
            if (r1 == 0) goto L_0x003e
            java.lang.Object r1 = r4.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            java.lang.Object r2 = r1.getKey()
            boolean r2 = r5.containsKey(r2)
            if (r2 != 0) goto L_0x002f
            return r0
        L_0x002f:
            java.lang.Object r2 = r1.getValue()
            java.lang.Object r1 = r1.getKey()
            java.lang.Object r1 = r5.get(r1)
            if (r2 == r1) goto L_0x0018
            return r0
        L_0x003e:
            r4 = 1
            return r4
        L_0x0040:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.device.authorization.page.SceneAuthFragment.a(java.util.Map, java.util.Map):boolean");
    }
}
