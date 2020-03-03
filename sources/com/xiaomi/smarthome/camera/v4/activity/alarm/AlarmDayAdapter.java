package com.xiaomi.smarthome.camera.v4.activity.alarm;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.mijia.model.alarm.AlarmItem;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.view.recycle.BaseViewHolder;
import java.util.ArrayList;
import java.util.Arrays;

public class AlarmDayAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private int TYPE_DATA = 1;
    /* access modifiers changed from: private */
    public int TYPE_TITLE = 0;
    /* access modifiers changed from: private */
    public ClickCallBack mClickCallBak = null;
    /* access modifiers changed from: private */
    public ArrayList<AlarmDayBean> mData = new ArrayList<>();
    private int mDataCount = 0;
    /* access modifiers changed from: private */
    public boolean mIsMultiSelectMode = false;
    /* access modifiers changed from: private */
    public DisplayImageOptions mOptions;
    private boolean[] mSelectList;

    interface ClickCallBack {
        void onRecyclerClick(View view);

        void onRecyclerLongClick(View view);
    }

    public void destroyDisplayImageOptions() {
        this.mOptions = null;
    }

    private void initImageDisplayOptions(Context context) {
        this.mOptions = new DisplayImageOptions.Builder().a(context.getResources().getDrawable(R.drawable.camera_v4_remind_load_fail)).b(context.getResources().getDrawable(R.drawable.camera_v4_remind_load_fail)).c(context.getResources().getDrawable(R.drawable.camera_v4_remind_load_fail)).b(true).d(true).e(true).a(Bitmap.Config.RGB_565).a(ImageScaleType.EXACTLY_STRETCHED).d();
    }

    public AlarmDayAdapter(Context context, ClickCallBack clickCallBack) {
        initImageDisplayOptions(context);
        this.mClickCallBak = clickCallBack;
    }

    public void setData(ArrayList<AlarmDayBean> arrayList) {
        this.mData = arrayList;
        this.mSelectList = new boolean[this.mData.size()];
        Arrays.fill(this.mSelectList, false);
        int i = 0;
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            if (arrayList.get(i2).item != null) {
                i++;
            }
        }
        this.mDataCount = i;
        notifyDataSetChanged();
    }

    public BaseViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == this.TYPE_TITLE) {
            return new TitleViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.alarm_day_title, viewGroup, false));
        }
        return new DataViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.alarm_day_item, viewGroup, false));
    }

    public void onBindViewHolder(BaseViewHolder baseViewHolder, int i) {
        if (getItemViewType(i) == this.TYPE_TITLE) {
            ((TitleViewHolder) baseViewHolder).bindView(this.mData.get(i).title);
        } else {
            ((DataViewHolder) baseViewHolder).bindView(i);
        }
    }

    public int getItemCount() {
        return this.mData.size();
    }

    public int getItemViewType(int i) {
        if (i <= -1 || i >= this.mData.size() || this.mData.get(i).item != null) {
            return this.TYPE_DATA;
        }
        return this.TYPE_TITLE;
    }

    public void setSelectMode(boolean z) {
        if (this.mIsMultiSelectMode != z) {
            if (!z) {
                unSelectAll();
            }
            this.mIsMultiSelectMode = z;
            notifyDataSetChanged();
        }
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                public int getSpanSize(int i) {
                    if (AlarmDayAdapter.this.getItemViewType(i) == AlarmDayAdapter.this.TYPE_TITLE) {
                        return gridLayoutManager.getSpanCount();
                    }
                    return 1;
                }
            });
        }
    }

    private class DataViewHolder extends BaseViewHolder {
        private ImageView checkView;
        private ImageView imageView;
        private TextView timeView;

        DataViewHolder(View view) {
            super(view);
            view.setOnClickListener(new View.OnClickListener(AlarmDayAdapter.this) {
                public void onClick(View view) {
                    if (AlarmDayAdapter.this.mClickCallBak != null) {
                        AlarmDayAdapter.this.mClickCallBak.onRecyclerClick(view);
                    }
                }
            });
            view.setOnLongClickListener(new View.OnLongClickListener(AlarmDayAdapter.this) {
                public boolean onLongClick(View view) {
                    if (AlarmDayAdapter.this.mClickCallBak == null) {
                        return true;
                    }
                    AlarmDayAdapter.this.mClickCallBak.onRecyclerLongClick(view);
                    return true;
                }
            });
            this.imageView = (ImageView) view.findViewById(R.id.thumb);
            this.checkView = (ImageView) view.findViewById(R.id.checkbox);
            this.timeView = (TextView) view.findViewById(R.id.title);
        }

        /* access modifiers changed from: package-private */
        public void bindView(int i) {
            if (i >= 0 && i <= AlarmDayAdapter.this.getItemCount()) {
                AlarmDayBean alarmDayBean = (AlarmDayBean) AlarmDayAdapter.this.mData.get(i);
                if (alarmDayBean.item != null) {
                    this.itemView.setTag(alarmDayBean.item);
                    ImageLoader.a().a(alarmDayBean.item.a(), this.imageView, AlarmDayAdapter.this.mOptions);
                    this.timeView.setText(alarmDayBean.title);
                    if (AlarmDayAdapter.this.mIsMultiSelectMode) {
                        this.checkView.setVisibility(0);
                        if (AlarmDayAdapter.this.isSelected(i)) {
                            this.checkView.setImageResource(R.drawable.delete_checked);
                        } else {
                            this.checkView.setImageResource(R.drawable.delete_unchecked);
                        }
                    } else {
                        this.checkView.setVisibility(8);
                    }
                }
            }
        }
    }

    public void selectAll() {
        if (this.mSelectList != null) {
            for (int i = 0; i < this.mSelectList.length; i++) {
                if (getItemViewType(i) == this.TYPE_DATA) {
                    this.mSelectList[i] = true;
                }
            }
            notifyDataSetChanged();
        }
    }

    public void unSelectAll() {
        if (getItemCount() != 0 || !this.mIsMultiSelectMode) {
            if (this.mSelectList != null) {
                Arrays.fill(this.mSelectList, false);
            }
            notifyDataSetChanged();
        }
    }

    public void selectToggle(int i) {
        if (i >= 0 && i < this.mSelectList.length) {
            this.mSelectList[i] = !this.mSelectList[i];
        }
    }

    public int getSelectedCount() {
        if (this.mSelectList == null) {
            return 0;
        }
        int i = 0;
        for (boolean z : this.mSelectList) {
            if (z) {
                i++;
            }
        }
        return i;
    }

    /* access modifiers changed from: protected */
    public int getDayIndex(int i) {
        for (int i2 = i; i2 >= 0; i2--) {
            if (getItemViewType(i2) == 0) {
                return i - i2;
            }
        }
        return 0;
    }

    public ArrayList<AlarmItem> getSelectList() {
        AlarmItem alarmItem;
        ArrayList<AlarmItem> arrayList = new ArrayList<>();
        for (int i = 0; i < this.mData.size(); i++) {
            if (i < this.mSelectList.length && this.mSelectList[i] && (alarmItem = this.mData.get(i).item) != null) {
                arrayList.add(alarmItem);
            }
        }
        return arrayList;
    }

    public int getDataCount() {
        return this.mDataCount;
    }

    /* access modifiers changed from: private */
    public boolean isSelected(int i) {
        return this.mSelectList == null || i < 0 || i >= this.mSelectList.length || this.mSelectList[i];
    }

    private class TitleViewHolder extends BaseViewHolder {
        private TextView titleView;

        TitleViewHolder(View view) {
            super(view);
            this.titleView = (TextView) view.findViewById(R.id.list_title_txt);
        }

        /* access modifiers changed from: package-private */
        public void bindView(String str) {
            this.titleView.setText(str);
        }
    }
}
