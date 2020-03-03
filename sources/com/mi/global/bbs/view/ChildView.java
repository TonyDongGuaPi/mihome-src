package com.mi.global.bbs.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.mi.global.bbs.R;
import com.mi.global.bbs.adapter.TextAdapter;
import com.mi.global.bbs.model.SlideMenu;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.bbs.utils.GoogleTrackerUtil;
import com.mi.log.LogUtil;
import java.util.ArrayList;
import java.util.List;

public class ChildView extends LinearLayout {
    public static final String TAG = "ChildView";
    /* access modifiers changed from: private */
    public List<SlideMenu> childrenItem = new ArrayList();
    /* access modifiers changed from: private */
    public TextAdapter earaListViewAdapter;
    /* access modifiers changed from: private */
    public List<SlideMenu> groups = new ArrayList();
    /* access modifiers changed from: private */
    public OnSelectListener mOnSelectListener;
    /* access modifiers changed from: private */
    public ListView plateListView;
    /* access modifiers changed from: private */
    public TextAdapter plateListViewAdapter;
    private ListView regionListView;

    public interface OnSelectListener {
        void getValue(String str, String str2, String str3);
    }

    public ChildView(Context context) {
        super(context);
        init(context);
    }

    public ChildView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    private void init(Context context) {
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.bbs_view_region, this, true);
        this.regionListView = (ListView) findViewById(R.id.listView);
        this.plateListView = (ListView) findViewById(R.id.listView2);
        this.regionListView.setVisibility(0);
        this.plateListView.setVisibility(8);
        this.earaListViewAdapter = new TextAdapter(context, this.groups);
        this.plateListViewAdapter = new TextAdapter(context, this.childrenItem);
        this.regionListView.setAdapter(this.earaListViewAdapter);
        this.plateListView.setAdapter(this.plateListViewAdapter);
        this.regionListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                LogUtil.b(ChildView.TAG, " regionListView onclick" + i);
                GoogleTrackerUtil.sendRecordEvent("slide", Constants.WebView.PAGE_INDEX, "top_navigation");
                ChildView.this.earaListViewAdapter.setSelectedPosition(i);
                ChildView.this.earaListViewAdapter.notifyDataSetChanged();
                SlideMenu slideMenu = (SlideMenu) ChildView.this.groups.get(i);
                LogUtil.b(ChildView.TAG, " regionListView menu " + slideMenu.getName());
                if (slideMenu.getSubmenu() == null || slideMenu.getSubmenu().size() <= 0) {
                    LogUtil.b(ChildView.TAG, " regionListView menu no  ");
                    ChildView.this.plateListView.setVisibility(8);
                    SlideMenu slideMenu2 = (SlideMenu) ChildView.this.groups.get(i);
                    if (ChildView.this.mOnSelectListener != null) {
                        ChildView.this.mOnSelectListener.getValue(slideMenu2.getUrl(), slideMenu2.getName(), slideMenu2.getId());
                        return;
                    }
                    return;
                }
                LogUtil.b(ChildView.TAG, " regionListView menu " + slideMenu.getSubmenu().toString());
                ChildView.this.childrenItem.clear();
                ChildView.this.plateListView.setVisibility(0);
                ChildView.this.childrenItem.addAll(slideMenu.getSubmenu());
                ChildView.this.plateListViewAdapter.setListData(ChildView.this.childrenItem);
                ChildView.this.plateListViewAdapter.notifyDataSetChanged();
            }
        });
        this.plateListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                LogUtil.b(ChildView.TAG, " plateListView onclick" + i);
                GoogleTrackerUtil.sendRecordEvent("slide", Constants.WebView.PAGE_INDEX, "second_navigation");
                ChildView.this.plateListViewAdapter.setSelectedPosition(i);
                ChildView.this.plateListViewAdapter.notifyDataSetChanged();
                SlideMenu slideMenu = (SlideMenu) ChildView.this.childrenItem.get(i);
                if (ChildView.this.mOnSelectListener != null) {
                    ChildView.this.mOnSelectListener.getValue(slideMenu.getUrl(), slideMenu.getName(), slideMenu.getId());
                }
            }
        });
    }

    public void setOnSelectListener(OnSelectListener onSelectListener) {
        this.mOnSelectListener = onSelectListener;
    }

    public void setGroups(List<SlideMenu> list) {
        this.groups = list;
        this.earaListViewAdapter.setListData(list);
        this.earaListViewAdapter.notifyDataSetChanged();
        this.plateListViewAdapter.notifyDataSetChanged();
    }
}
