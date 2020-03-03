package com.xiaomi.smarthome.scenenew.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.scenenew.actiivity.SmarthomeRecommendDetailActivity;
import com.xiaomi.smarthome.scenenew.manager.RecommendSceneManager;
import com.xiaomi.smarthome.scenenew.model.RecommendSceneInfo;

public class RecommendSceneAdapter extends BaseAdapter {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public Context f21925a;
    private LayoutInflater b;
    private RecommendSceneInfo c = new RecommendSceneInfo();

    public long getItemId(int i) {
        return (long) i;
    }

    public RecommendSceneAdapter(Context context) {
        this.f21925a = context;
        this.b = LayoutInflater.from(context);
    }

    public void a(RecommendSceneInfo recommendSceneInfo) {
        if (recommendSceneInfo != null) {
            this.c = recommendSceneInfo;
        } else {
            this.c = new RecommendSceneInfo();
        }
        notifyDataSetChanged();
    }

    public int getCount() {
        if (this.c == null || this.c.c == null) {
            return 0;
        }
        return this.c.c.size();
    }

    public Object getItem(int i) {
        return this.c.c.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ChildViewHolder childViewHolder;
        if (view == null) {
            view = this.b.inflate(R.layout.recommend_scene_new_item, (ViewGroup) null);
            childViewHolder = new ChildViewHolder(view);
            view.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) view.getTag();
        }
        final RecommendSceneInfo.RecommendSceneItem recommendSceneItem = this.c.c.get(i);
        childViewHolder.f21927a.setHierarchy(new GenericDraweeHierarchyBuilder(this.f21925a.getResources()).setFadeDuration(200).setPlaceholderImage(this.f21925a.getResources().getDrawable(R.drawable.recommend_scene_list_default_icon)).setRoundingParams(RoundingParams.fromCornersRadius(20.0f)).setActualImageScaleType(ScalingUtils.ScaleType.CENTER).setPlaceholderImageScaleType(ScalingUtils.ScaleType.FIT_START).build());
        childViewHolder.f21927a.setImageURI(Uri.parse(recommendSceneItem.g));
        childViewHolder.b.setText(recommendSceneItem.b);
        childViewHolder.c.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!RecommendSceneManager.a().e) {
                    ToastUtil.a((int) R.string.recommend_scenem_not_update_success);
                    return;
                }
                Intent intent = new Intent(RecommendSceneAdapter.this.f21925a, SmarthomeRecommendDetailActivity.class);
                RecommendSceneManager.a().a(recommendSceneItem);
                RecommendSceneAdapter.this.f21925a.startActivity(intent);
            }
        });
        return view;
    }

    class ChildViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        SimpleDraweeView f21927a;
        TextView b;
        View c;

        public ChildViewHolder(View view) {
            super(view);
            this.c = view;
            this.f21927a = (SimpleDraweeView) view.findViewById(R.id.recommend_sdv);
            this.b = (TextView) view.findViewById(R.id.recommend_tv);
        }
    }
}
