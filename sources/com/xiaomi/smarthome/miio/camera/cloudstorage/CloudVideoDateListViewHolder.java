package com.xiaomi.smarthome.miio.camera.cloudstorage;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.xiaomi.smarthome.R;

public class CloudVideoDateListViewHolder extends RecyclerView.ViewHolder {
    private TextView tvDay;
    private TextView tvMonth;

    public CloudVideoDateListViewHolder(View view) {
        super(view);
        this.tvDay = (TextView) view.findViewById(R.id.tvDay);
        this.tvMonth = (TextView) view.findViewById(R.id.tvMonth);
    }

    public String toString() {
        return super.toString();
    }
}
