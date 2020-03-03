package com.mi.global.bbs.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.mi.global.bbs.R;
import com.mi.global.bbs.model.HomeNotify;
import java.util.ArrayList;
import java.util.List;

public class HomeNotifyRecycleAdapter extends RecyclerBaseAdapter<ViewHolder> {
    private static String TAG = "HomeNotifyRecycleAdapter";
    private List<HomeNotify> notifys = new ArrayList();

    public HomeNotifyRecycleAdapter(Context context) {
        super(context);
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(this.mInflater.inflate(R.layout.bbs_home_post_list_item, (ViewGroup) null));
    }

    public void refreshDatas(List<HomeNotify> list) {
        this.notifys.clear();
        this.notifys.addAll(list);
    }

    public int getItemCount() {
        return this.notifys.size();
    }

    public Object getItem(int i) {
        return this.notifys.get(i);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.tvNotifyMes.setText(this.notifys.get(i).getMessage());
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNotifyMes;

        public ViewHolder(View view) {
            super(view);
            this.tvNotifyMes = (TextView) view.findViewById(R.id.tv_notify_mes);
        }
    }
}
