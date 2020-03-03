package com.xiaomi.smarthome.camera.activity.alarm;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.mijia.app.AppConfig;
import com.mijia.model.alarm.AlarmItemV2;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.view.recycle.BaseViewHolder;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AlarmDayV2Adapter extends RecyclerView.Adapter<BaseViewHolder> {
    private static final int MAX_DELETE_COUNT = 50;
    private static final String TAG = "AlarmDayV2Adapter";
    private int TYPE_DATA = 1;
    /* access modifiers changed from: private */
    public int TYPE_TITLE = 0;
    /* access modifiers changed from: private */
    public ClickCallBack mClickCallBak = null;
    /* access modifiers changed from: private */
    public ArrayList<AlarmDayBeanV2> mData = new ArrayList<>();
    private int mDataCount = 0;
    /* access modifiers changed from: private */
    public boolean mDisableLongPress;
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
        this.mOptions = new DisplayImageOptions.Builder().a(context.getResources().getDrawable(R.drawable.camera_remind_load_fail)).b(context.getResources().getDrawable(R.drawable.camera_remind_load_fail)).c(context.getResources().getDrawable(R.drawable.camera_remind_load_fail)).b(true).d(true).e(true).a(Bitmap.Config.RGB_565).a(ImageScaleType.EXACTLY_STRETCHED).d();
    }

    public AlarmDayV2Adapter(Context context, ClickCallBack clickCallBack) {
        initImageDisplayOptions(context);
        this.mClickCallBak = clickCallBack;
    }

    public void setData(ArrayList<AlarmDayBeanV2> arrayList) {
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

    public List<AlarmDayBeanV2> getData() {
        if (this.mData != null) {
            return this.mData;
        }
        return null;
    }

    public BaseViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == this.TYPE_TITLE) {
            return new TitleViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.camera_alarm_day_title, viewGroup, false));
        }
        return new DataViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.camera_alarm_day_item, viewGroup, false));
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
                    if (AlarmDayV2Adapter.this.getItemViewType(i) == AlarmDayV2Adapter.this.TYPE_TITLE) {
                        return gridLayoutManager.getSpanCount();
                    }
                    return 1;
                }
            });
        }
    }

    public void disableLongPress(boolean z) {
        this.mDisableLongPress = z;
    }

    public boolean getEditMode() {
        return this.mIsMultiSelectMode;
    }

    private class DataViewHolder extends BaseViewHolder {
        private ImageView checkView;
        private ImageView imageView;
        private TextView timeView;

        DataViewHolder(View view) {
            super(view);
            view.setOnClickListener(new View.OnClickListener(AlarmDayV2Adapter.this) {
                public void onClick(View view) {
                    if (AlarmDayV2Adapter.this.mClickCallBak != null) {
                        AlarmDayV2Adapter.this.mClickCallBak.onRecyclerClick(view);
                    }
                }
            });
            if (!AlarmDayV2Adapter.this.mDisableLongPress) {
                view.setOnLongClickListener(new View.OnLongClickListener(AlarmDayV2Adapter.this) {
                    public boolean onLongClick(View view) {
                        if (AlarmDayV2Adapter.this.mClickCallBak == null) {
                            return true;
                        }
                        AlarmDayV2Adapter.this.mClickCallBak.onRecyclerLongClick(view);
                        return true;
                    }
                });
            }
            this.imageView = (ImageView) view.findViewById(R.id.thumb);
            this.checkView = (ImageView) view.findViewById(R.id.checkbox);
            this.timeView = (TextView) view.findViewById(R.id.title);
            this.imageView.getLayoutParams().height = (((int) (((float) AppConfig.b) - (AppConfig.d * 4.0f))) * 3) / 16;
        }

        /* access modifiers changed from: package-private */
        public void bindView(int i) {
            if (i >= 0 && i <= AlarmDayV2Adapter.this.getItemCount()) {
                AlarmDayBeanV2 alarmDayBeanV2 = (AlarmDayBeanV2) AlarmDayV2Adapter.this.mData.get(i);
                if (alarmDayBeanV2.item != null) {
                    this.itemView.setTag(alarmDayBeanV2.item);
                    ImageLoader.a().a(alarmDayBeanV2.item.f, this.imageView, AlarmDayV2Adapter.this.mOptions);
                    this.timeView.setText(alarmDayBeanV2.title);
                    if (AlarmDayV2Adapter.this.mIsMultiSelectMode) {
                        this.checkView.setVisibility(0);
                        if (AlarmDayV2Adapter.this.isSelected(i)) {
                            this.checkView.setImageResource(R.drawable.camera_delete_checked);
                        } else {
                            this.checkView.setImageResource(R.drawable.camera_delete_unchecked);
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
            int i = 50;
            if (this.mSelectList.length <= 50) {
                i = this.mSelectList.length;
            }
            for (int i2 = 0; i2 < i; i2++) {
                if (getItemViewType(i2) == this.TYPE_DATA) {
                    this.mSelectList[i2] = true;
                } else {
                    i++;
                    if (i > this.mSelectList.length) {
                        i = this.mSelectList.length;
                    }
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
            if (this.mSelectList[i] || getSelectedCount() < 50) {
                this.mSelectList[i] = !this.mSelectList[i];
            } else {
                ToastUtil.a((int) R.string.cs_max_delete_50);
            }
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

    public ArrayList<AlarmItemV2> getSelectList() {
        AlarmItemV2 alarmItemV2;
        ArrayList<AlarmItemV2> arrayList = new ArrayList<>();
        for (int i = 0; i < this.mData.size(); i++) {
            if (i < this.mSelectList.length && this.mSelectList[i] && (alarmItemV2 = this.mData.get(i).item) != null) {
                arrayList.add(alarmItemV2);
            }
        }
        return arrayList;
    }

    public int getDataCount() {
        return this.mDataCount;
    }

    /* access modifiers changed from: private */
    public boolean isSelected(int i) {
        return i < 0 || i >= this.mSelectList.length || this.mSelectList[i];
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
