package com.mi.global.bbs.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import com.mi.global.bbs.R;
import com.mi.global.bbs.model.FeedbackUploadModel;
import com.mi.global.bbs.view.UploadImageView;
import com.mi.multi_image_selector.MultiImageSelector;
import java.util.ArrayList;

public class SelectImageAdapter extends RecyclerView.Adapter {
    private ArrayList<FeedbackUploadModel> list;
    UploadImageView.RetryUploadListener retryUploadListener;

    public SelectImageAdapter(ArrayList<FeedbackUploadModel> arrayList) {
        this.list = arrayList;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        UploadImageView uploadImageView = new UploadImageView(viewGroup.getContext());
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(-2, -2);
        layoutParams.rightMargin = viewGroup.getContext().getResources().getDimensionPixelSize(R.dimen.settings_margin_lr);
        uploadImageView.setLayoutParams(layoutParams);
        return new ImageHolder(uploadImageView);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (i == this.list.size()) {
            ((ImageHolder) viewHolder).mUploadImageView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    MultiImageSelector.a().c().a(9).a((Activity) view.getContext(), 101);
                }
            });
            return;
        }
        ImageHolder imageHolder = (ImageHolder) viewHolder;
        imageHolder.mUploadImageView.setBitmap(this.list.get(i).bitmap);
        imageHolder.mUploadImageView.setState(this.list.get(i).state);
        imageHolder.mUploadImageView.setPosition(i);
        imageHolder.mUploadImageView.setRetryUploadListener(this.retryUploadListener);
        imageHolder.mUploadImageView.setOnClickListener((View.OnClickListener) null);
    }

    public int getItemCount() {
        if (this.list == null) {
            return 1;
        }
        return this.list.size() != 9 ? 1 + this.list.size() : this.list.size();
    }

    public static class ImageHolder extends RecyclerView.ViewHolder {
        public UploadImageView mUploadImageView;

        public ImageHolder(View view) {
            super(view);
            this.mUploadImageView = (UploadImageView) view;
        }
    }

    public void setRetryUploadListener(UploadImageView.RetryUploadListener retryUploadListener2) {
        this.retryUploadListener = retryUploadListener2;
    }
}
