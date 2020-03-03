package com.mi.global.bbs.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.mi.global.bbs.R;
import com.mi.global.bbs.model.HomeSuggest;
import java.util.ArrayList;
import java.util.List;

public class HomeIconGridAdapter extends BaseAdapter {
    private static String TAG = "HomeIconGridAdapter";
    onGridClick gridClickListener = null;
    private List<HomeSuggest> homeSuggests = new ArrayList();
    private final int[] itemBgs = {R.drawable.bbs_bg_suggest_3, R.drawable.bbs_bg_suggest_4, R.drawable.bbs_bg_suggest_1, R.drawable.bbs_bg_suggest_2};
    private Context mContext;
    private LayoutInflater mInflater;

    public interface onGridClick {
        void onClickLink(String str, String str2);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public HomeIconGridAdapter(Context context, int i) {
        this.mContext = context;
        this.mInflater = (LayoutInflater) context.getSystemService("layout_inflater");
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
        HomeSuggest homeSuggest;
        if (view == null) {
            view = this.mInflater.inflate(R.layout.bbs_home_recommend_grid_item, viewGroup, false);
            viewHolder = new ViewHolder(view);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (i != 3 || this.homeSuggests.size() <= 4) {
            viewHolder.relItem.setBackgroundResource(this.itemBgs[i]);
            homeSuggest = this.homeSuggests.get(i);
        } else {
            homeSuggest = this.homeSuggests.get(4);
        }
        String icon = homeSuggest.getIcon();
        final String fname = homeSuggest.getFname();
        final String link = homeSuggest.getLink();
        viewHolder.tvRecommend.setText(fname);
        viewHolder.tvRecommend.setSelected(true);
        Glide.a((View) viewHolder.ivRecommend).a(icon).a(viewHolder.ivRecommend);
        viewHolder.relItem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (HomeIconGridAdapter.this.gridClickListener != null) {
                    HomeIconGridAdapter.this.gridClickListener.onClickLink(link, fname);
                }
            }
        });
        return view;
    }

    static class ViewHolder {
        public ImageView ivRecommend;
        RelativeLayout relItem;
        public TextView tvRecommend;

        ViewHolder(View view) {
            this.relItem = (RelativeLayout) view.findViewById(R.id.item_layout);
            this.tvRecommend = (TextView) view.findViewById(R.id.tv_recommend_icon);
            this.ivRecommend = (ImageView) view.findViewById(R.id.iv_recommend_icon);
            view.setTag(this);
        }
    }
}
