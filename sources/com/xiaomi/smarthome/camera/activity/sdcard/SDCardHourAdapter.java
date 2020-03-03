package com.xiaomi.smarthome.camera.activity.sdcard;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.mijia.app.AppConfig;
import com.mijia.model.sdcard.TimeItem;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.view.recycle.RecyclerClickListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SDCardHourAdapter extends RecyclerView.Adapter<SDCardHourViewHolder> {
    /* access modifiers changed from: private */
    public boolean isV4;
    private Context mContext;
    /* access modifiers changed from: private */
    public List<TimeItemData> mData = new ArrayList();
    String mDid = "";
    public boolean mIsMultiSelectMode;
    /* access modifiers changed from: private */
    public RecyclerClickListener mListener;
    /* access modifiers changed from: private */
    public DisplayImageOptions mOptions;
    boolean[] mSelectMap;
    public int selectedPos = -1;

    public SDCardHourAdapter(Context context, RecyclerClickListener recyclerClickListener, String str, boolean z) {
        this.isV4 = z;
        this.mListener = recyclerClickListener;
        this.mContext = context;
        this.mDid = str;
        initImageDisplayOptions(context);
    }

    public void destroyDisplayImageOptions() {
        this.mOptions = null;
        this.mListener = null;
    }

    private void initImageDisplayOptions(Context context) {
        DisplayImageOptions.Builder builder = new DisplayImageOptions.Builder();
        if (this.isV4) {
            builder.a(context.getResources().getDrawable(R.drawable.camera_v4_remind_load_fail));
            builder.b(context.getResources().getDrawable(R.drawable.camera_v4_remind_load_fail));
            builder.c(context.getResources().getDrawable(R.drawable.camera_v4_remind_load_fail));
        } else {
            builder.a(context.getResources().getDrawable(R.drawable.camera_remind_load_fail));
            builder.b(context.getResources().getDrawable(R.drawable.camera_remind_load_fail));
            builder.c(context.getResources().getDrawable(R.drawable.camera_remind_load_fail));
        }
        this.mOptions = builder.b(true).d(true).e(true).a(Bitmap.Config.RGB_565).a(ImageScaleType.EXACTLY_STRETCHED).d();
    }

    public SDCardHourViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new SDCardHourViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.camera_sdcard_hour_item, viewGroup, false));
    }

    public void onBindViewHolder(SDCardHourViewHolder sDCardHourViewHolder, int i) {
        sDCardHourViewHolder.bindView(i);
    }

    public void setData(List<TimeItemData> list) {
        this.mData = list;
        this.mSelectMap = new boolean[this.mData.size()];
    }

    public void setSelected(int i) {
        if (i < 0 || i >= this.mData.size()) {
            this.selectedPos = -1;
        } else {
            this.selectedPos = i;
        }
    }

    public TimeItemData getData(int i) {
        if (i < 0 || i > this.mData.size()) {
            return null;
        }
        return this.mData.get(i);
    }

    public int getItemCount() {
        return this.mData.size();
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

    public void selectAll() {
        if (this.mSelectMap != null) {
            Arrays.fill(this.mSelectMap, true);
        }
        notifyDataSetChanged();
    }

    public void unSelectAll() {
        if (this.mSelectMap != null) {
            Arrays.fill(this.mSelectMap, false);
        }
        notifyDataSetChanged();
    }

    public int getSelectCount() {
        int i = 0;
        for (boolean z : this.mSelectMap) {
            if (z) {
                i++;
            }
        }
        return i;
    }

    public void selectToggle(int i) {
        if (this.mSelectMap != null && i >= 0 && i < this.mSelectMap.length) {
            this.mSelectMap[i] = !this.mSelectMap[i];
        }
    }

    /* access modifiers changed from: private */
    public boolean isSelected(int i) {
        if (this.mSelectMap == null || i < 0 || i >= this.mSelectMap.length) {
            return false;
        }
        return this.mSelectMap[i];
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

    public ArrayList<TimeItem> getSelectItems() {
        ArrayList<TimeItem> arrayList = new ArrayList<>();
        int i = 0;
        while (i < this.mData.size() && i < this.mSelectMap.length) {
            if (this.mSelectMap[i]) {
                arrayList.add(this.mData.get(i).timeItem);
            }
            i++;
        }
        return arrayList;
    }

    class SDCardHourViewHolder extends RecyclerView.ViewHolder {
        private ImageView checkView;
        private ImageView imageView;
        private ImageView ivForeverStoreIcon;
        public ImageView ivSelected;
        private TextView timeView;

        public SDCardHourViewHolder(View view) {
            super(view);
            this.imageView = (ImageView) view.findViewById(R.id.thumb);
            this.timeView = (TextView) view.findViewById(R.id.title);
            this.checkView = (ImageView) view.findViewById(R.id.checkbox);
            this.ivForeverStoreIcon = (ImageView) view.findViewById(R.id.ivForeverStoreIcon);
            this.ivForeverStoreIcon.setImageResource(SDCardHourAdapter.this.isV4 ? R.drawable.camera_v4_store_tag_forever_nor : R.drawable.camera_store_tag_forever_nor);
            this.ivSelected = (ImageView) view.findViewById(R.id.ivSelected);
            ViewGroup.LayoutParams layoutParams = this.imageView.getLayoutParams();
            layoutParams.height = (((int) (((float) AppConfig.b) - (AppConfig.d * 4.0f))) * 3) / 16;
            this.ivSelected.getLayoutParams().height = layoutParams.height;
            view.setOnClickListener(new View.OnClickListener(SDCardHourAdapter.this) {
                public void onClick(View view) {
                    if (SDCardHourAdapter.this.mListener != null) {
                        SDCardHourAdapter.this.mListener.onRecyclerClick(view);
                    }
                }
            });
            view.setOnLongClickListener(new View.OnLongClickListener(SDCardHourAdapter.this) {
                public boolean onLongClick(View view) {
                    if (SDCardHourAdapter.this.mListener == null) {
                        return true;
                    }
                    SDCardHourAdapter.this.mListener.onRecyclerLongClick(view);
                    return true;
                }
            });
        }

        public void bindView(int i) {
            TimeItemData timeItemData = (TimeItemData) SDCardHourAdapter.this.mData.get(i);
            this.itemView.setTag(timeItemData);
            ImageLoader.a().a(timeItemData.timeItem.a(SDCardHourAdapter.this.mDid), this.imageView, SDCardHourAdapter.this.mOptions);
            this.timeView.setText(timeItemData.title);
            if (SDCardHourAdapter.this.mIsMultiSelectMode) {
                this.checkView.setVisibility(0);
                if (SDCardHourAdapter.this.isSelected(i)) {
                    this.checkView.setImageResource(R.drawable.camera_delete_checked);
                } else {
                    this.checkView.setImageResource(R.drawable.camera_delete_unchecked);
                }
            } else {
                this.checkView.setVisibility(8);
            }
            if (i == SDCardHourAdapter.this.selectedPos) {
                this.ivSelected.setVisibility(0);
            } else {
                this.ivSelected.setVisibility(8);
            }
            if (timeItemData != null && timeItemData.timeItem != null && this.ivForeverStoreIcon != null) {
                if (timeItemData.timeItem.d != 0) {
                    this.ivForeverStoreIcon.setVisibility(0);
                } else {
                    this.ivForeverStoreIcon.setVisibility(8);
                }
            }
        }
    }
}
