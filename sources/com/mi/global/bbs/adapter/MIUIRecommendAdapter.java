package com.mi.global.bbs.adapter;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;
import com.mi.global.bbs.model.MIUIContentModel;
import com.mi.global.bbs.ui.column.ColumnDetailActivity;
import com.mi.global.bbs.utils.GoogleTrackerUtil;
import com.mi.global.bbs.utils.ImageLoader;
import com.mi.util.Device;
import java.util.List;

public class MIUIRecommendAdapter extends RecyclerView.Adapter {
    private List<MIUIContentModel.DataBean.ColumnsBean> columns;

    public class MIUIRecommendHolder_ViewBinding implements Unbinder {
        private MIUIRecommendHolder target;

        @UiThread
        public MIUIRecommendHolder_ViewBinding(MIUIRecommendHolder mIUIRecommendHolder, View view) {
            this.target = mIUIRecommendHolder;
            mIUIRecommendHolder.mMiuiRecommendedColumnImg = (ImageView) Utils.findRequiredViewAsType(view, R.id.miui_recommended_column_img, "field 'mMiuiRecommendedColumnImg'", ImageView.class);
            mIUIRecommendHolder.mMiuiRecommendedColumnTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.miui_recommended_column_title, "field 'mMiuiRecommendedColumnTitle'", TextView.class);
            mIUIRecommendHolder.mMiuiRecommendedColumnDes = (TextView) Utils.findRequiredViewAsType(view, R.id.miui_recommended_column_des, "field 'mMiuiRecommendedColumnDes'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            MIUIRecommendHolder mIUIRecommendHolder = this.target;
            if (mIUIRecommendHolder != null) {
                this.target = null;
                mIUIRecommendHolder.mMiuiRecommendedColumnImg = null;
                mIUIRecommendHolder.mMiuiRecommendedColumnTitle = null;
                mIUIRecommendHolder.mMiuiRecommendedColumnDes = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public MIUIRecommendAdapter(List<MIUIContentModel.DataBean.ColumnsBean> list) {
        this.columns = list;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MIUIRecommendHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bbs_miui_recommended_column_item, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        MIUIRecommendHolder mIUIRecommendHolder = (MIUIRecommendHolder) viewHolder;
        final MIUIContentModel.DataBean.ColumnsBean columnsBean = this.columns.get(i);
        mIUIRecommendHolder.mMiuiRecommendedColumnTitle.setText(columnsBean.name);
        mIUIRecommendHolder.mMiuiRecommendedColumnDes.setText(columnsBean.info);
        ImageLoader.showImage(mIUIRecommendHolder.mMiuiRecommendedColumnImg, columnsBean.background);
        mIUIRecommendHolder.itemView.getLayoutParams().width = ((Device.f1349a - mIUIRecommendHolder.itemView.getResources().getDimensionPixelOffset(R.dimen.miui_recommend_left_space)) * 5) / 6;
        mIUIRecommendHolder.itemView.requestLayout();
        mIUIRecommendHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                GoogleTrackerUtil.sendRecordEvent("miui", "click_column", "click_column_" + columnsBean.columnID);
                Context context = view.getContext();
                ColumnDetailActivity.jumpWithId(context, columnsBean.columnID + "");
            }
        });
    }

    public int getItemCount() {
        if (this.columns == null) {
            return 0;
        }
        return this.columns.size();
    }

    public class MIUIRecommendHolder extends RecyclerView.ViewHolder {
        @BindView(2131493644)
        TextView mMiuiRecommendedColumnDes;
        @BindView(2131493645)
        ImageView mMiuiRecommendedColumnImg;
        @BindView(2131493647)
        TextView mMiuiRecommendedColumnTitle;

        public MIUIRecommendHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }
}
