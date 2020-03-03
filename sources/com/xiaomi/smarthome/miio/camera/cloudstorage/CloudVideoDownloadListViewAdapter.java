package com.xiaomi.smarthome.miio.camera.cloudstorage;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.miio.camera.cloudstorage.model.CloudVideoDownloadInfo;
import java.util.ArrayList;
import java.util.List;

public class CloudVideoDownloadListViewAdapter extends RecyclerView.Adapter {
    public static final String COLOR_CLASS_J = "#FC4949";
    public static final String COLOR_CLASS_S = "#3fb57d";
    public static final String COLOR_CLASS_W = "#969696";
    public static final String COLOR_PURPLE = "#6f97e4";
    private static final String TAG = "CloudVideoDownloadListViewAdapter";
    public IItemClickListener clickListener;
    public List<CloudVideoDownloadInfo> downloadInfoList = new ArrayList();
    private InternalClickListener internalClickListener = new InternalClickListener();
    private boolean isEditMode = false;
    public ModeChangedListener modeChangedListener;
    public RecyclerView recyclerView;

    public interface IItemClickListener<T> {
        void onItemClick(View view, int i, T t);

        void onItemLongClick(View view, int i, T t);
    }

    public interface ModeChangedListener {
        void onEditModeChanged(boolean z);
    }

    public void setEditMode(boolean z) {
        this.isEditMode = z;
        if (this.modeChangedListener != null) {
            this.modeChangedListener.onEditModeChanged(z);
        }
    }

    public boolean getEditMode() {
        return this.isEditMode;
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i, List list) {
        CloudVideoDownloadInfo cloudVideoDownloadInfo = this.downloadInfoList.get(i);
        if (list.isEmpty()) {
            onBindViewHolder(viewHolder, i);
        } else if (list.get(0).equals("editMode")) {
            CheckBox checkBox = (CheckBox) viewHolder.itemView.findViewById(R.id.cbDelete);
            if (cloudVideoDownloadInfo.isSelected) {
                checkBox.setChecked(true);
            } else {
                checkBox.setChecked(false);
            }
        } else {
            TextView textView = (TextView) viewHolder.itemView.findViewById(R.id.tvTitle);
            ImageView imageView = (ImageView) viewHolder.itemView.findViewById(R.id.ivDownloadStatus);
            TextView textView2 = (TextView) viewHolder.itemView.findViewById(R.id.tvProgress);
            SeekBar seekBar = (SeekBar) viewHolder.itemView.findViewById(R.id.sbProgress);
            TextView textView3 = (TextView) viewHolder.itemView.findViewById(R.id.tvStatus);
            TextView textView4 = (TextView) viewHolder.itemView.findViewById(R.id.tvDuration);
            textView4.setVisibility(8);
            ((TextView) viewHolder.itemView.findViewById(R.id.tvTime)).setText(cloudVideoDownloadInfo.startTimePretty);
            textView2.setText("" + cloudVideoDownloadInfo.progress + Operators.MOD);
            if (cloudVideoDownloadInfo.status == 1) {
                textView2.setVisibility(0);
                seekBar.setVisibility(0);
                seekBar.setProgress(cloudVideoDownloadInfo.progress);
                textView3.setText(R.string.cs_downloading);
                textView3.setTextColor(Color.parseColor(COLOR_CLASS_S));
                imageView.setImageDrawable((Drawable) null);
            } else if (cloudVideoDownloadInfo.status == 2) {
                seekBar.setVisibility(8);
                textView2.setVisibility(8);
                textView3.setText(R.string.cs_download_fail);
                textView3.setTextColor(Color.parseColor(COLOR_CLASS_J));
                if (this.recyclerView != null) {
                    imageView.setImageDrawable(this.recyclerView.getContext().getResources().getDrawable(R.drawable.cs_download_icon_retry_normal));
                }
            } else if (cloudVideoDownloadInfo.status == 4) {
                seekBar.setVisibility(8);
                textView3.setText(R.string.cs_download_prepare);
                textView2.setVisibility(8);
                textView3.setTextColor(Color.parseColor(COLOR_CLASS_W));
                imageView.setImageDrawable((Drawable) null);
            } else if (cloudVideoDownloadInfo.status == 0) {
                seekBar.setVisibility(8);
                textView4.setVisibility(0);
                textView2.setVisibility(8);
                textView4.setText("" + longToDate(cloudVideoDownloadInfo.duration));
                textView3.setText(R.string.cs_download_finish);
                textView3.setTextColor(Color.parseColor(COLOR_CLASS_W));
                imageView.setImageDrawable((Drawable) null);
            } else if (cloudVideoDownloadInfo.status == 8) {
                seekBar.setVisibility(8);
                textView2.setVisibility(8);
                textView3.setText(R.string.cs_download_pause);
                textView3.setTextColor(Color.parseColor(COLOR_PURPLE));
                if (this.recyclerView != null) {
                    imageView.setImageDrawable(this.recyclerView.getContext().getResources().getDrawable(R.drawable.cs_download_icon_play_normal));
                }
            }
        }
    }

    public int getItemViewType(int i) {
        return super.getItemViewType(i);
    }

    public void setHasStableIds(boolean z) {
        super.setHasStableIds(z);
    }

    public long getItemId(int i) {
        return super.getItemId(i);
    }

    public void onViewRecycled(RecyclerView.ViewHolder viewHolder) {
        super.onViewRecycled(viewHolder);
    }

    public boolean onFailedToRecycleView(RecyclerView.ViewHolder viewHolder) {
        return super.onFailedToRecycleView(viewHolder);
    }

    public void onViewAttachedToWindow(RecyclerView.ViewHolder viewHolder) {
        super.onViewAttachedToWindow(viewHolder);
    }

    public void onViewDetachedFromWindow(RecyclerView.ViewHolder viewHolder) {
        super.onViewDetachedFromWindow(viewHolder);
    }

    public void registerAdapterDataObserver(RecyclerView.AdapterDataObserver adapterDataObserver) {
        super.registerAdapterDataObserver(adapterDataObserver);
    }

    public void unregisterAdapterDataObserver(RecyclerView.AdapterDataObserver adapterDataObserver) {
        super.unregisterAdapterDataObserver(adapterDataObserver);
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView2) {
        super.onAttachedToRecyclerView(recyclerView2);
        this.recyclerView = recyclerView2;
    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView2) {
        super.onDetachedFromRecyclerView(recyclerView2);
        this.recyclerView = null;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cs_view_download_video_item, viewGroup, false);
        inflate.setOnClickListener(this.internalClickListener);
        return new CloudVideoDownloadListViewHolder(inflate);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        final CloudVideoDownloadInfo cloudVideoDownloadInfo = this.downloadInfoList.get(i);
        LogUtil.a(TAG, "onBindViewHolder position:" + i);
        ImageView imageView = (ImageView) viewHolder.itemView.findViewById(R.id.ivPic);
        ImageView imageView2 = (ImageView) viewHolder.itemView.findViewById(R.id.ivDownloadStatus);
        TextView textView = (TextView) viewHolder.itemView.findViewById(R.id.tvDuration);
        TextView textView2 = (TextView) viewHolder.itemView.findViewById(R.id.tvTitle);
        TextView textView3 = (TextView) viewHolder.itemView.findViewById(R.id.tvTime);
        TextView textView4 = (TextView) viewHolder.itemView.findViewById(R.id.tvStatus);
        CheckBox checkBox = (CheckBox) viewHolder.itemView.findViewById(R.id.cbDelete);
        TextView textView5 = (TextView) viewHolder.itemView.findViewById(R.id.tvProgress);
        SeekBar seekBar = (SeekBar) viewHolder.itemView.findViewById(R.id.sbProgress);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                cloudVideoDownloadInfo.isSelected = z;
            }
        });
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (CloudVideoDownloadListViewAdapter.this.clickListener != null) {
                    CloudVideoDownloadListViewAdapter.this.clickListener.onItemClick(view, i, cloudVideoDownloadInfo);
                }
            }
        });
        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                if (CloudVideoDownloadListViewAdapter.this.clickListener == null) {
                    return false;
                }
                CloudVideoDownloadListViewAdapter.this.clickListener.onItemLongClick(view, i, cloudVideoDownloadInfo);
                return true;
            }
        });
        textView.setVisibility(8);
        if (!TextUtils.isEmpty(cloudVideoDownloadInfo.title)) {
            textView2.setText(cloudVideoDownloadInfo.title);
        } else {
            textView2.setText(R.string.cs_default_deivce_name);
        }
        textView3.setText("" + cloudVideoDownloadInfo.startTimePretty);
        textView5.setText("" + cloudVideoDownloadInfo.progress + "/%");
        if (cloudVideoDownloadInfo.status == 1) {
            textView4.setText(R.string.cs_downloading);
            seekBar.setVisibility(0);
            textView5.setVisibility(0);
            textView4.setTextColor(Color.parseColor(COLOR_CLASS_S));
            imageView2.setImageDrawable((Drawable) null);
        } else if (cloudVideoDownloadInfo.status == 2) {
            textView4.setText(R.string.cs_download_fail);
            seekBar.setVisibility(8);
            textView5.setVisibility(8);
            textView4.setTextColor(Color.parseColor(COLOR_CLASS_J));
            if (this.recyclerView != null) {
                imageView2.setImageDrawable(this.recyclerView.getContext().getResources().getDrawable(R.drawable.cs_download_icon_retry_normal));
            }
        } else if (cloudVideoDownloadInfo.status == 4) {
            textView4.setText(R.string.cs_download_prepare);
            seekBar.setVisibility(8);
            textView5.setVisibility(8);
            textView4.setTextColor(Color.parseColor(COLOR_CLASS_W));
            imageView2.setImageDrawable((Drawable) null);
        } else if (cloudVideoDownloadInfo.status == 0) {
            textView.setVisibility(0);
            seekBar.setVisibility(8);
            textView5.setVisibility(8);
            textView.setText("" + longToDate(cloudVideoDownloadInfo.duration));
            textView4.setText(R.string.cs_download_finish);
            textView4.setTextColor(Color.parseColor(COLOR_CLASS_W));
            imageView2.setImageDrawable((Drawable) null);
        } else if (cloudVideoDownloadInfo.status == 8) {
            textView4.setText(R.string.cs_download_pause);
            seekBar.setVisibility(8);
            textView5.setVisibility(8);
            textView4.setTextColor(Color.parseColor(COLOR_PURPLE));
            if (this.recyclerView != null) {
                imageView2.setImageDrawable(this.recyclerView.getContext().getResources().getDrawable(R.drawable.cs_download_icon_play_normal));
            }
        }
        if (this.isEditMode) {
            checkBox.setVisibility(0);
            if (cloudVideoDownloadInfo.isSelected) {
                checkBox.setChecked(true);
            } else {
                checkBox.setChecked(false);
            }
        } else {
            checkBox.setVisibility(8);
        }
    }

    public int getItemCount() {
        if (this.downloadInfoList == null || this.downloadInfoList.size() <= 0) {
            return 0;
        }
        return this.downloadInfoList.size();
    }

    private class InternalClickListener implements View.OnClickListener {
        private InternalClickListener() {
        }

        public void onClick(View view) {
            int childAdapterPosition = CloudVideoDownloadListViewAdapter.this.recyclerView.getChildAdapterPosition(view);
            LogUtil.a(CloudVideoDownloadListViewAdapter.TAG, "InternalClickListener:" + childAdapterPosition);
            if (CloudVideoDownloadListViewAdapter.this.clickListener != null) {
                CloudVideoDownloadListViewAdapter.this.clickListener.onItemClick(view, childAdapterPosition, CloudVideoDownloadListViewAdapter.this.getItem(childAdapterPosition));
            }
        }
    }

    public int getSelectedItemCount() {
        int i = 0;
        if (this.downloadInfoList != null) {
            for (CloudVideoDownloadInfo cloudVideoDownloadInfo : this.downloadInfoList) {
                i += cloudVideoDownloadInfo.isSelected ? 1 : 0;
            }
        }
        return i;
    }

    public List<CloudVideoDownloadInfo> getSelectedItem() {
        ArrayList arrayList = new ArrayList();
        if (this.downloadInfoList != null) {
            for (CloudVideoDownloadInfo next : this.downloadInfoList) {
                if (next.isSelected) {
                    arrayList.add(next);
                }
            }
        }
        return arrayList;
    }

    public void updateDownloadingItem(CloudVideoDownloadInfo cloudVideoDownloadInfo) {
        for (CloudVideoDownloadInfo next : this.downloadInfoList) {
            if (cloudVideoDownloadInfo.id == next.id) {
                next.size = cloudVideoDownloadInfo.size;
                next.mp4FilePath = cloudVideoDownloadInfo.mp4FilePath;
                next.m3u8FilePath = cloudVideoDownloadInfo.m3u8FilePath;
                next.progress = cloudVideoDownloadInfo.progress;
                next.did = cloudVideoDownloadInfo.did;
                next.status = cloudVideoDownloadInfo.status;
                next.createTime = cloudVideoDownloadInfo.createTime;
                next.m3u8LocalPath = cloudVideoDownloadInfo.m3u8LocalPath;
                next.uid = cloudVideoDownloadInfo.uid;
                next.videoUrl = cloudVideoDownloadInfo.videoUrl;
                next.endTime = cloudVideoDownloadInfo.endTime;
                next.startTime = cloudVideoDownloadInfo.startTime;
                next.timezoneId = cloudVideoDownloadInfo.timezoneId;
                return;
            }
        }
    }

    private String longToDate(long j) {
        if (j <= 0) {
            return "";
        }
        int i = (int) j;
        int i2 = i / 3600;
        int i3 = i2 * 3600;
        int i4 = ((int) (j - ((long) i3))) / 60;
        int i5 = (i - (i4 * 60)) - i3;
        StringBuilder sb = new StringBuilder();
        if (i2 > 0) {
            if (i2 < 10) {
                sb.append("0" + i2);
            } else {
                sb.append(i2);
            }
            sb.append(":");
        }
        if (i4 < 10) {
            sb.append("0" + i4);
        } else {
            sb.append(i4);
        }
        sb.append(":");
        if (i5 < 10) {
            sb.append("0" + i5);
        } else {
            sb.append(i5);
        }
        return sb.toString();
    }

    public <T> T getItem(int i) {
        if (this.downloadInfoList == null || this.downloadInfoList.size() <= 0 || i < 0 || i >= this.downloadInfoList.size()) {
            return null;
        }
        return this.downloadInfoList.get(i);
    }

    public void clearAll() {
        for (int i = 0; i < this.downloadInfoList.size(); i++) {
            this.downloadInfoList.get(i).isSelected = false;
        }
        notifyDataSetChanged();
    }

    public void selectAll() {
        for (int i = 0; i < this.downloadInfoList.size(); i++) {
            this.downloadInfoList.get(i).isSelected = true;
        }
        notifyDataSetChanged();
    }
}
