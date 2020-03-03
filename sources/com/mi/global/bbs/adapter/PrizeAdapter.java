package com.mi.global.bbs.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.mi.global.bbs.R;
import com.mi.global.bbs.model.GiftInfo;
import java.util.List;

public class PrizeAdapter extends BaseAdapter {
    public static final String TAG = "PrizeAdapter";
    private Context mContext;
    private List<GiftInfo> mListData;

    public long getItemId(int i) {
        return (long) i;
    }

    public PrizeAdapter(Context context, List<GiftInfo> list) {
        this.mContext = context;
        this.mListData = list;
    }

    public int getCount() {
        return this.mListData.size();
    }

    public GiftInfo getItem(int i) {
        return this.mListData.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(this.mContext).inflate(R.layout.bbs_gift_item, viewGroup, false);
            viewHolder = new ViewHolder(view);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.name.setText(getItem(i).getName());
        return view;
    }

    private static class ViewHolder {
        TextView count;
        TextView name;

        ViewHolder(View view) {
            this.name = (TextView) view.findViewById(R.id.gift_name);
            this.count = (TextView) view.findViewById(R.id.gift_count);
            view.setTag(this);
        }
    }
}
