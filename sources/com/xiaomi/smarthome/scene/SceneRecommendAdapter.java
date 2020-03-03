package com.xiaomi.smarthome.scene;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaomi.router.api.SceneManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.core.entity.statistic.StatType;
import com.xiaomi.smarthome.device.api.RecommendSceneItem;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.library.common.widget.MultiIconView;
import com.xiaomi.smarthome.library.common.widget.StarImageView;
import com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity;
import java.util.ArrayList;
import java.util.List;

public class SceneRecommendAdapter extends BaseAdapter {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public Context f21329a;
    private LayoutInflater b;
    private List<RecommendSceneItem> c = new ArrayList();
    private Bitmap d;
    private Bitmap e;
    private Bitmap f;

    public long getItemId(int i) {
        return (long) i;
    }

    public class ViewHolder_ViewBinding implements Unbinder {

        /* renamed from: a  reason: collision with root package name */
        private ViewHolder f21331a;

        @UiThread
        public ViewHolder_ViewBinding(ViewHolder viewHolder, View view) {
            this.f21331a = viewHolder;
            viewHolder.ivSceneIcon = (MultiIconView) Utils.findRequiredViewAsType(view, R.id.iv_scene_icon, "field 'ivSceneIcon'", MultiIconView.class);
            viewHolder.tvSceneName = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_scene_name, "field 'tvSceneName'", TextView.class);
            viewHolder.ivSceneStars = (StarImageView) Utils.findRequiredViewAsType(view, R.id.iv_scene_stars, "field 'ivSceneStars'", StarImageView.class);
        }

        @CallSuper
        public void unbind() {
            ViewHolder viewHolder = this.f21331a;
            if (viewHolder != null) {
                this.f21331a = null;
                viewHolder.ivSceneIcon = null;
                viewHolder.tvSceneName = null;
                viewHolder.ivSceneStars = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public SceneRecommendAdapter(Context context) {
        this.f21329a = context;
        this.b = LayoutInflater.from(this.f21329a);
    }

    public int getCount() {
        return this.c.size();
    }

    public Object getItem(int i) {
        return this.c.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = this.b.inflate(R.layout.recommend_scene_item, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (i < this.c.size()) {
            final RecommendSceneItem recommendSceneItem = this.c.get(i);
            viewHolder.ivSceneIcon.clear();
            if (recommendSceneItem != null) {
                if (SceneManager.x().a(recommendSceneItem)) {
                    viewHolder.ivSceneIcon.addResource(R.drawable.scene_gohome_icon);
                } else if (SceneManager.x().b(recommendSceneItem)) {
                    viewHolder.ivSceneIcon.addResource(R.drawable.scene_leavehome_icon);
                } else if (!TextUtils.isEmpty(recommendSceneItem.mIcon)) {
                    viewHolder.ivSceneIcon.addUrl(recommendSceneItem.mIcon);
                } else {
                    ArrayList<String> arrayList = new ArrayList<>();
                    SmartHomeSceneUtility.a(recommendSceneItem, (List<String>) arrayList);
                    for (String addUrl : arrayList) {
                        viewHolder.ivSceneIcon.addUrl(addUrl);
                    }
                }
                viewHolder.ivSceneStars.setStartBitmap(this.d, this.e, this.f);
                viewHolder.ivSceneStars.setLevel((float) recommendSceneItem.mRecommLevel);
                viewHolder.tvSceneName.setText(recommendSceneItem.mName);
                view.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        ArrayList arrayList = new ArrayList();
                        for (RecommendSceneItem.RemommendSceneCondition remommendSceneCondition : recommendSceneItem.mRecommendConditionList) {
                            SmartHomeSceneCreateEditActivity.DefaultSceneItemSet defaultSceneItemSet = new SmartHomeSceneCreateEditActivity.DefaultSceneItemSet();
                            defaultSceneItemSet.b = remommendSceneCondition.mDeviceModels;
                            defaultSceneItemSet.c = remommendSceneCondition.mKeys;
                            defaultSceneItemSet.d = remommendSceneCondition.mProductId;
                            arrayList.add(defaultSceneItemSet);
                        }
                        ArrayList arrayList2 = new ArrayList();
                        for (RecommendSceneItem.RemommendSceneAction remommendSceneAction : recommendSceneItem.mRecommendActionList) {
                            SmartHomeSceneCreateEditActivity.DefaultSceneItemSet defaultSceneItemSet2 = new SmartHomeSceneCreateEditActivity.DefaultSceneItemSet();
                            defaultSceneItemSet2.b = remommendSceneAction.mDeviceModels;
                            defaultSceneItemSet2.c = remommendSceneAction.mKeys;
                            defaultSceneItemSet2.d = remommendSceneAction.mProductId;
                            arrayList2.add(defaultSceneItemSet2);
                        }
                        Intent intent = new Intent();
                        intent.putParcelableArrayListExtra(SmartHomeSceneCreateEditActivity.EXTRA_DEFAULT_CONDITION_ITMES, arrayList);
                        intent.putParcelableArrayListExtra(SmartHomeSceneCreateEditActivity.EXTRA_DEFAULT_ACTION_ITMES, arrayList2);
                        if (SceneManager.x().b(recommendSceneItem)) {
                            intent.setClass(SceneRecommendAdapter.this.f21329a, GoLeaveHomeSceneCreateEditActivity.class);
                            intent.putExtra(GoLeaveHomeSceneCreateEditActivity.GO_HOME_RECOMMEND_FLAG, false);
                            SceneRecommendAdapter.this.f21329a.startActivity(intent);
                        } else if (SceneManager.x().a(recommendSceneItem)) {
                            intent.setClass(SceneRecommendAdapter.this.f21329a, GoLeaveHomeSceneCreateEditActivity.class);
                            intent.putExtra(GoLeaveHomeSceneCreateEditActivity.GO_HOME_RECOMMEND_FLAG, true);
                            SceneRecommendAdapter.this.f21329a.startActivity(intent);
                        } else {
                            intent.setClass(SceneRecommendAdapter.this.f21329a, SmartHomeSceneCreateEditActivity.class);
                            intent.putExtra(SmartHomeSceneCreateEditActivity.EXTRA_DEFAULT_SCENE_NAME, recommendSceneItem.mName);
                            intent.putExtra("extra_recommend_scene_id", recommendSceneItem.mRecommId);
                            SceneRecommendAdapter.this.f21329a.startActivity(intent);
                        }
                        CoreApi.a().a(StatType.EVENT, "scene_recom_click_tab", "" + recommendSceneItem.mRecommId, (String) null, false);
                    }
                });
            }
        }
        return view;
    }

    public void a(List<RecommendSceneItem> list) {
        this.c.clear();
        this.c.addAll(list);
        for (int size = this.c.size() - 1; size >= 0; size--) {
            if (!this.c.get(size).mShowInMainPage) {
                this.c.remove(size);
            }
        }
        notifyDataSetChanged();
    }

    static class ViewHolder {
        @BindView(2131430218)
        MultiIconView ivSceneIcon;
        @BindView(2131430219)
        StarImageView ivSceneStars;
        @BindView(2131433475)
        TextView tvSceneName;

        ViewHolder(View view) {
            ButterKnife.bind((Object) this, view);
        }
    }
}
