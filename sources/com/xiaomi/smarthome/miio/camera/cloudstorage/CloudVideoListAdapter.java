package com.xiaomi.smarthome.miio.camera.cloudstorage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.miui.tsmclient.util.StringUtils;
import com.nostra13.universalimageloader.cache.disc.naming.FileNameGenerator;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.miio.camera.cloudstorage.model.CloudVideoData;
import com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoImageDownloader;
import com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoNetUtils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CloudVideoListAdapter extends RecyclerView.Adapter {
    private Context context;
    public int currentPlayPosition = -1;
    public String did = null;
    private DisplayImageOptions displayImageOptions;
    public IItemClickListener iItemClickListener;
    public IItemLongClickListener iItemLongClickListener;
    private InternalClickListener internalClickListener = new InternalClickListener();
    private InternalLongClickListener internalLongClickListener = new InternalLongClickListener();
    /* access modifiers changed from: private */
    public boolean isInEditMode = false;
    public ModeChangedListener modeChangedListener;
    public RecyclerView recyclerView;
    private SimpleDateFormat sdf = new SimpleDateFormat(StringUtils.EXPECT_TIME_FORMAT);
    /* access modifiers changed from: private */
    public List<CloudVideoData> videoDataList;

    public interface IItemClickListener<T> {
        void onItemClick(View view, int i, T t);
    }

    public interface IItemLongClickListener {
        void onItemLongClick(View view, int i);
    }

    public interface ModeChangedListener {
        void onEditModeChanged(boolean z);
    }

    public List<CloudVideoData> getVideoDataList() {
        return this.videoDataList;
    }

    public void setVideoDataList(List<CloudVideoData> list) {
        this.videoDataList.clear();
        this.videoDataList.addAll(list);
    }

    public void appendVideoDataList(List<CloudVideoData> list) {
        this.videoDataList.addAll(list);
    }

    public boolean getEditMode() {
        return this.isInEditMode;
    }

    public void setEditMode(boolean z) {
        if (!z) {
            for (CloudVideoData cloudVideoData : this.videoDataList) {
                cloudVideoData.isSelected = false;
            }
        }
        if (this.modeChangedListener != null) {
            this.modeChangedListener.onEditModeChanged(z);
        }
        this.isInEditMode = z;
    }

    public int getSelectedItemCount() {
        if (this.videoDataList == null || this.videoDataList.size() <= 0) {
            return 0;
        }
        int i = 0;
        for (int i2 = 0; i2 < this.videoDataList.size(); i2++) {
            i += this.videoDataList.get(i2).isSelected ? 1 : 0;
        }
        return i;
    }

    public List<CloudVideoData> getSelectedItems() {
        ArrayList arrayList = new ArrayList();
        if (getSelectedItemCount() > 0) {
            for (CloudVideoData next : this.videoDataList) {
                if (next.isSelected) {
                    arrayList.add(next);
                }
            }
        }
        return arrayList;
    }

    public CloudVideoListAdapter(Context context2, String str) {
        this.context = context2;
        this.did = str;
        this.videoDataList = new ArrayList();
    }

    public CloudVideoListAdapter(Context context2, String str, List<CloudVideoData> list) {
        this.context = context2;
        this.did = str;
        if (list != null) {
            this.videoDataList = list;
        } else {
            this.videoDataList = new ArrayList();
        }
    }

    private void init() {
        if (!ImageLoader.a().b()) {
            ImageLoaderConfiguration.Builder builder = new ImageLoaderConfiguration.Builder(this.context);
            builder.b(3);
            builder.a();
            builder.b((FileNameGenerator) new Md5FileNameGenerator());
            builder.f(52428800);
            builder.a(QueueProcessingType.LIFO);
            builder.a((ImageDownloader) new CloudVideoImageDownloader(this.context));
            ImageLoader.a().a(builder.c());
            DisplayImageOptions.Builder builder2 = new DisplayImageOptions.Builder();
            builder2.b(true);
            builder2.d(true);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            builder2.a(options);
            this.displayImageOptions = builder2.d();
        }
    }

    private void release() {
        if (ImageLoader.a().b()) {
            ImageLoader.a().l();
        }
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cs_view_video_list_item, viewGroup, false);
        CloudVideoListViewHolder cloudVideoListViewHolder = new CloudVideoListViewHolder(inflate);
        inflate.setOnClickListener(this.internalClickListener);
        return cloudVideoListViewHolder;
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        TextView textView = (TextView) viewHolder.itemView.findViewById(R.id.tvDuration);
        TextView textView2 = (TextView) viewHolder.itemView.findViewById(R.id.tvStartTime);
        ImageView imageView = (ImageView) viewHolder.itemView.findViewById(R.id.ivImage);
        CheckBox checkBox = (CheckBox) viewHolder.itemView.findViewById(R.id.cbEdit);
        ImageView imageView2 = (ImageView) viewHolder.itemView.findViewById(R.id.ivPlaying);
        final CloudVideoData cloudVideoData = this.videoDataList.get(i);
        if (cloudVideoData.isPlaying) {
            imageView2.setVisibility(0);
        } else {
            imageView2.setVisibility(8);
        }
        viewHolder.itemView.setOnLongClickListener(this.internalLongClickListener);
        checkBox.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (cloudVideoData.isSelected || CloudVideoListAdapter.this.getSelectedItemCount() < 5) {
                    return false;
                }
                ToastUtil.a((int) R.string.cs_max_download_5);
                return true;
            }
        });
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                cloudVideoData.isSelected = z;
            }
        });
        textView.setText(longToDate(cloudVideoData.duration));
        if (cloudVideoData.isPeople) {
            textView2.setCompoundDrawablePadding(DisplayUtils.a(2.0f));
            textView2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.cs_icon_people_move, 0);
        } else {
            textView2.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        }
        textView2.setText(this.sdf.format(Long.valueOf(cloudVideoData.startTime)));
        imageView.setImageResource(R.drawable.set_bg_02_visual_nor);
        if (TextUtils.isEmpty(cloudVideoData.imgStoreUrl)) {
            cloudVideoData.imgStoreUrl = CloudVideoNetUtils.getInstance().getSnapshotUrl(this.did, cloudVideoData.fileId, cloudVideoData.imgStoreId);
        }
        if (!TextUtils.isEmpty(cloudVideoData.imgStoreUrl)) {
            ImageLoader.a().a(cloudVideoData.imgStoreUrl, imageView, this.displayImageOptions);
        }
        if (this.isInEditMode) {
            checkBox.setVisibility(0);
            if (cloudVideoData.isSelected) {
                checkBox.setChecked(true);
            } else {
                checkBox.setChecked(false);
            }
        } else {
            checkBox.setVisibility(8);
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

    public int getItemCount() {
        return this.videoDataList.size();
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
        init();
        this.recyclerView = recyclerView2;
    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView2) {
        this.recyclerView = null;
        super.onDetachedFromRecyclerView(recyclerView2);
        release();
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i, List list) {
        if (!list.isEmpty()) {
            RelativeLayout relativeLayout = (RelativeLayout) viewHolder.itemView.findViewById(R.id.rlItemContainer);
            ImageView imageView = (ImageView) viewHolder.itemView.findViewById(R.id.ivImage);
            TextView textView = (TextView) viewHolder.itemView.findViewById(R.id.tvDuration);
            TextView textView2 = (TextView) viewHolder.itemView.findViewById(R.id.tvStartTime);
            CheckBox checkBox = (CheckBox) viewHolder.itemView.findViewById(R.id.cbEdit);
            ImageView imageView2 = (ImageView) viewHolder.itemView.findViewById(R.id.ivPlaying);
            if (this.videoDataList.get(i).isPlaying) {
                imageView2.setVisibility(0);
            } else {
                imageView2.setVisibility(8);
            }
            checkBox.setChecked(this.videoDataList.get(i).isSelected);
            return;
        }
        super.onBindViewHolder(viewHolder, i, list);
    }

    private class InternalClickListener implements View.OnClickListener {
        private InternalClickListener() {
        }

        public void onClick(View view) {
            if (CloudVideoListAdapter.this.recyclerView != null) {
                int childAdapterPosition = CloudVideoListAdapter.this.recyclerView.getChildAdapterPosition(view);
                if (!CloudVideoListAdapter.this.isInEditMode) {
                    CloudVideoListAdapter.this.currentPlayPosition = childAdapterPosition;
                    if (CloudVideoListAdapter.this.iItemClickListener != null) {
                        CloudVideoListAdapter.this.iItemClickListener.onItemClick(view, CloudVideoListAdapter.this.recyclerView.getChildAdapterPosition(view), null);
                    }
                } else if (((CloudVideoData) CloudVideoListAdapter.this.videoDataList.get(childAdapterPosition)).isSelected || CloudVideoListAdapter.this.getSelectedItemCount() < 5) {
                    if (((CloudVideoData) CloudVideoListAdapter.this.videoDataList.get(childAdapterPosition)).isSelected) {
                        ((CloudVideoData) CloudVideoListAdapter.this.videoDataList.get(childAdapterPosition)).isSelected = false;
                    } else {
                        ((CloudVideoData) CloudVideoListAdapter.this.videoDataList.get(childAdapterPosition)).isSelected = true;
                    }
                    CloudVideoListAdapter.this.notifyItemChanged(childAdapterPosition);
                } else {
                    ToastUtil.a((int) R.string.cs_max_download_5);
                }
            }
        }
    }

    private class InternalLongClickListener implements View.OnLongClickListener {
        private InternalLongClickListener() {
        }

        public boolean onLongClick(View view) {
            if (CloudVideoListAdapter.this.iItemLongClickListener == null) {
                return false;
            }
            CloudVideoListAdapter.this.iItemLongClickListener.onItemLongClick(view, CloudVideoListAdapter.this.recyclerView.getChildAdapterPosition(view));
            return true;
        }
    }

    public String longToDate(long j) {
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

    public int calCurrentPlayPosition(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        for (int i = 0; i < this.videoDataList.size(); i++) {
            if (str.equals(this.videoDataList.get(i).fileId)) {
                return i;
            }
        }
        return -1;
    }

    public void setCurrentPlayItem(String str) {
        if (!TextUtils.isEmpty(str)) {
            for (int i = 0; i < this.videoDataList.size(); i++) {
                if (str.equals(this.videoDataList.get(i).fileId)) {
                    this.videoDataList.get(i).isPlaying = true;
                    this.currentPlayPosition = i;
                } else {
                    this.videoDataList.get(i).isPlaying = false;
                }
            }
        }
    }

    public void selectAll() {
        for (int i = 0; i < this.videoDataList.size(); i++) {
            this.videoDataList.get(i).isSelected = true;
        }
        notifyDataSetChanged();
    }

    public void clearAll() {
        for (int i = 0; i < this.videoDataList.size(); i++) {
            this.videoDataList.get(i).isSelected = false;
        }
        notifyDataSetChanged();
    }
}
