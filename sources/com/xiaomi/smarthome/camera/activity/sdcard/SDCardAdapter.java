package com.xiaomi.smarthome.camera.activity.sdcard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.mijia.model.sdcard.TimeItem;
import com.mijia.model.sdcard.TimeItemDay;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.view.HourOfDayView;
import java.util.ArrayList;
import java.util.List;

public class SDCardAdapter extends BaseAdapter {
    private Context mContext;
    private List<TimeItemDay> mData = new ArrayList();
    /* access modifiers changed from: private */
    public boolean mIsMultiSelectMode;
    /* access modifiers changed from: private */
    public HourOfDayView.HourOfDayViewListener mListener = null;

    public long getItemId(int i) {
        return (long) i;
    }

    public SDCardAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<TimeItemDay> list) {
        this.mData = list;
        notifyDataSetChanged();
    }

    public void setMultiSelectMode(boolean z) {
        if (this.mIsMultiSelectMode != z) {
            this.mIsMultiSelectMode = z;
            if (!z) {
                unSelectAll();
            }
            notifyDataSetChanged();
        }
    }

    /* access modifiers changed from: package-private */
    public void setDayViewListener(HourOfDayView.HourOfDayViewListener hourOfDayViewListener) {
        this.mListener = hourOfDayViewListener;
    }

    public int getCount() {
        return this.mData.size();
    }

    /* access modifiers changed from: package-private */
    public int getSelectCount() {
        int i = 0;
        for (int i2 = 0; i2 < this.mData.size(); i2++) {
            i += this.mData.get(i2).b();
        }
        return i;
    }

    /* access modifiers changed from: package-private */
    public int getDataCount() {
        int i = 0;
        for (TimeItemDay timeItemDay : this.mData) {
            i += timeItemDay.e.size();
        }
        return i;
    }

    public void selectAll() {
        for (int i = 0; i < this.mData.size(); i++) {
            this.mData.get(i).a(true);
        }
        notifyDataSetChanged();
    }

    public void unSelectAll() {
        for (int i = 0; i < this.mData.size(); i++) {
            this.mData.get(i).a(false);
        }
        notifyDataSetChanged();
    }

    public List<TimeItem> getSelectItems() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.mData.size(); i++) {
            arrayList.addAll(this.mData.get(i).a());
        }
        return arrayList;
    }

    public TimeItemDay getItem(int i) {
        return this.mData.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2;
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view2 = LayoutInflater.from(this.mContext).inflate(R.layout.camera_sdcard_file_item, (ViewGroup) null);
            viewHolder.mTitle = (TextView) view2.findViewById(R.id.title);
            viewHolder.hourOfDayView = (HourOfDayView) view2.findViewById(R.id.hour_of_day_view);
            view2.setTag(viewHolder);
        } else {
            view2 = view;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.binView(i);
        return view2;
    }

    class ViewHolder {
        HourOfDayView hourOfDayView;
        TextView mTitle;

        ViewHolder() {
        }

        /* access modifiers changed from: package-private */
        public void binView(int i) {
            if (i == SDCardAdapter.this.getCount() - 1) {
                if (this.hourOfDayView.getPaddingBottom() == 0) {
                    this.hourOfDayView.setPadding(0, 0, 0, 51);
                }
            } else if (this.hourOfDayView.getPaddingBottom() != 0) {
                this.hourOfDayView.setPadding(0, 0, 0, 0);
            }
            TimeItemDay item = SDCardAdapter.this.getItem(i);
            this.mTitle.setText(item.c);
            this.hourOfDayView.setTimeItemDay(item, i);
            this.hourOfDayView.setMultiSelect(SDCardAdapter.this.mIsMultiSelectMode);
            this.hourOfDayView.setHourOfDayViewListener(SDCardAdapter.this.mListener);
        }
    }
}
