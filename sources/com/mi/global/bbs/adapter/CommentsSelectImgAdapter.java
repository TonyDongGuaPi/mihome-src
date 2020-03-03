package com.mi.global.bbs.adapter;

import android.app.Activity;
import android.graphics.Point;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import butterknife.ButterKnife;
import com.mi.global.bbs.R;
import java.util.ArrayList;
import java.util.List;

public class CommentsSelectImgAdapter extends RecyclerView.Adapter {
    private Activity activity;
    private int column = 5;
    final int mGridWidth;
    OnDismissListener onDismissListener;
    private List<String> pathList = new ArrayList();

    public interface OnDismissListener {
        void onDismiss();
    }

    private void bindImgItem(ImgItem imgItem, int i) {
    }

    public CommentsSelectImgAdapter(Activity activity2) {
        int i;
        this.activity = activity2;
        WindowManager windowManager = (WindowManager) activity2.getSystemService("window");
        if (Build.VERSION.SDK_INT >= 13) {
            Point point = new Point();
            windowManager.getDefaultDisplay().getSize(point);
            i = point.x;
        } else {
            i = windowManager.getDefaultDisplay().getWidth();
        }
        this.mGridWidth = (i - ((this.column + 1) * activity2.getResources().getDimensionPixelOffset(R.dimen.padding_normal))) / 5;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ImgItem(LayoutInflater.from(this.activity).inflate(com.mi.multi_image_selector.R.layout.bbs_grid_item, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        bindImgItem((ImgItem) viewHolder, i);
    }

    public void removeItem(int i) {
        if (i >= this.pathList.size()) {
            i = this.pathList.size() - 1;
        }
        this.pathList.remove(i);
        notifyItemRemoved(i);
        if (i != this.pathList.size() - 1) {
            notifyItemRangeChanged(i, this.pathList.size() - i);
        }
        if (this.pathList.size() == 0 && this.onDismissListener != null) {
            this.onDismissListener.onDismiss();
        }
    }

    public void setData(List<String> list) {
        this.pathList.clear();
        if (list != null && list.size() > 0) {
            this.pathList.addAll(list);
        }
        notifyDataSetChanged();
    }

    public void clear() {
        this.pathList.clear();
        notifyDataSetChanged();
        if (this.onDismissListener != null) {
            this.onDismissListener.onDismiss();
        }
    }

    public int getItemCount() {
        return this.pathList.size();
    }

    public List<String> getPathList() {
        return this.pathList;
    }

    public void setOnDismissListener(OnDismissListener onDismissListener2) {
        this.onDismissListener = onDismissListener2;
    }

    public class ImgItem extends RecyclerView.ViewHolder {
        public ImgItem(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }
}
