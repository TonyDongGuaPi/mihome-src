package com.mi.global.bbs.adapter;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.mi.global.bbs.R;
import com.mi.global.bbs.model.HomeSuggest;
import com.mi.log.LogUtil;
import java.util.ArrayList;
import java.util.List;

public class HomeGridAdapter extends BaseAdapter {
    private static String TAG = "HomeGridAdapter";
    onGridClick gridClickListener = null;
    private List<HomeSuggest> homeSuggests = new ArrayList();
    private Context mContext;
    final int mGridWidth;
    private LayoutInflater mInflater;

    public interface onGridClick {
        void onClickLink(String str);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public HomeGridAdapter(Context context, int i) {
        int i2;
        this.mContext = context;
        this.mInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (Build.VERSION.SDK_INT >= 13) {
            Point point = new Point();
            windowManager.getDefaultDisplay().getSize(point);
            i2 = point.x;
        } else {
            i2 = windowManager.getDefaultDisplay().getWidth();
        }
        this.mGridWidth = i2 / i;
    }

    public void setData(List<HomeSuggest> list) {
        if (list != null) {
            this.homeSuggests.clear();
            this.homeSuggests.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void setOnGridClick(onGridClick ongridclick) {
        this.gridClickListener = ongridclick;
    }

    public List<HomeSuggest> getData() {
        return this.homeSuggests;
    }

    public int getCount() {
        return this.homeSuggests.size();
    }

    public HomeSuggest getItem(int i) {
        return this.homeSuggests.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = this.mInflater.inflate(R.layout.bbs_home_suggest_grid_item, viewGroup, false);
            viewHolder = new ViewHolder(view);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        final HomeSuggest homeSuggest = this.homeSuggests.get(i);
        viewHolder.tvSuggest.setText(homeSuggest.getFname());
        String str = TAG;
        LogUtil.b(str, "suggest.getFname()" + homeSuggest.getFname());
        switch (i % 4) {
            case 0:
                viewHolder.tvSuggest.setBackgroundResource(R.drawable.bg_suggest_grid_0);
                viewHolder.tvSuggest.setTextColor(this.mContext.getResources().getColor(R.color.item_suggest_grid_1));
                break;
            case 1:
                viewHolder.tvSuggest.setBackgroundResource(R.drawable.bg_suggest_grid_1);
                viewHolder.tvSuggest.setTextColor(this.mContext.getResources().getColor(R.color.item_suggest_grid_2));
                break;
            case 2:
                viewHolder.tvSuggest.setBackgroundResource(R.drawable.bg_suggest_grid_2);
                viewHolder.tvSuggest.setTextColor(this.mContext.getResources().getColor(R.color.item_suggest_grid_3));
                break;
            case 3:
                viewHolder.tvSuggest.setBackgroundResource(R.drawable.bg_suggest_grid_3);
                viewHolder.tvSuggest.setTextColor(this.mContext.getResources().getColor(R.color.item_suggest_grid_4));
                break;
        }
        viewHolder.tvSuggest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (HomeGridAdapter.this.gridClickListener != null) {
                    HomeGridAdapter.this.gridClickListener.onClickLink(homeSuggest.getLink());
                }
            }
        });
        return view;
    }

    static class ViewHolder {
        public TextView tvSuggest;

        ViewHolder(View view) {
            this.tvSuggest = (TextView) view.findViewById(R.id.tv_suggest_item);
            view.setTag(this);
        }
    }
}
