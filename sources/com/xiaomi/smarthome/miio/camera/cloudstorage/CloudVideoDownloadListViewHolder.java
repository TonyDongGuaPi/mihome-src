package com.xiaomi.smarthome.miio.camera.cloudstorage;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.xiaomi.smarthome.R;

public class CloudVideoDownloadListViewHolder extends RecyclerView.ViewHolder {
    public ImageView ivPic;
    public TextView tvDuration;
    public TextView tvStatus;
    public TextView tvTime;
    public TextView tvTitle;

    public CloudVideoDownloadListViewHolder(View view) {
        super(view);
        this.ivPic = (ImageView) view.findViewById(R.id.ivPic);
        this.tvDuration = (TextView) view.findViewById(R.id.tvDuration);
        this.tvTitle = (TextView) view.findViewById(R.id.tvTime);
        this.tvStatus = (TextView) view.findViewById(R.id.tvStatus);
    }

    public String toString() {
        return super.toString();
    }
}
