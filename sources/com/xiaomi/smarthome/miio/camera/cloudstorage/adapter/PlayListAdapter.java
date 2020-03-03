package com.xiaomi.smarthome.miio.camera.cloudstorage.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
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
import com.xiaomi.smarthome.miio.camera.cloudstorage.model.CloudVideoChildListData;
import com.xiaomi.smarthome.miio.camera.cloudstorage.model.CloudVideoParentListData;
import com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoImageDownloader;
import com.xiaomi.smarthome.miio.camera.cloudstorage.viewholder.PlayListChildHolder;
import com.xiaomi.smarthome.miio.camera.cloudstorage.viewholder.PlayListParentHolder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PlayListAdapter extends RecyclerView.Adapter {
    public static int MAX_DOWNLOAD_COUNT = 5;
    public static int TYPE_CHILD = 2;
    public static int TYPE_PARENT = 1;
    public static int TYPE_UNKNOWN;
    public int currentPlayPosition = -1;
    public String did = null;
    private boolean disableLongPress;
    private DisplayImageOptions displayImageOptions;
    public IItemClickListener iItemClickListener;
    public IItemLongClickListener iItemLongClickListener;
    private InternalClickListener internalClickListener = new InternalClickListener();
    private InternalLongClickListener internalLongClickListener = new InternalLongClickListener();
    public boolean isDownloadEnabled = false;
    /* access modifiers changed from: private */
    public boolean isInEditMode = false;
    public List<CloudVideoParentListData> listData = new ArrayList();
    public ModeChangedListener modeChangedListener;
    public RecyclerView recyclerView;
    private SimpleDateFormat sdfHMS = new SimpleDateFormat(StringUtils.EXPECT_TIME_FORMAT);

    public interface IItemClickListener<T> {
        void onItemClick(View view, int i, T t);
    }

    public interface IItemLongClickListener {
        void onItemLongClick(View view, int i);
    }

    public interface ModeChangedListener {
        void onEditModeChanged(boolean z);
    }

    public void disableLongPress() {
        this.disableLongPress = true;
    }

    private void init(Context context) {
        if (!ImageLoader.a().b()) {
            ImageLoaderConfiguration.Builder builder = new ImageLoaderConfiguration.Builder(context);
            builder.b(3);
            builder.a();
            builder.b((FileNameGenerator) new Md5FileNameGenerator());
            builder.f(52428800);
            builder.a(QueueProcessingType.LIFO);
            builder.a((ImageDownloader) new CloudVideoImageDownloader(context));
            ImageLoader.a().a(builder.c());
            DisplayImageOptions.Builder builder2 = new DisplayImageOptions.Builder();
            builder2.b(true);
            builder2.d(true);
            builder2.d((int) R.drawable.set_bg_02_visual_nor);
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

    public boolean getEditMode() {
        return this.isInEditMode;
    }

    public void setEditMode(boolean z) {
        if (!z) {
            for (int i = 0; i < getItemCount(); i++) {
                Object itemDataByPosition = getItemDataByPosition(i);
                if (itemDataByPosition instanceof CloudVideoChildListData) {
                    ((CloudVideoChildListData) itemDataByPosition).isSelected = false;
                }
            }
        }
        if (this.modeChangedListener != null) {
            this.modeChangedListener.onEditModeChanged(z);
        }
        this.isInEditMode = z;
    }

    public int getSelectedItemCount() {
        int size = this.listData.size();
        int i = 0;
        int i2 = 0;
        while (i < size) {
            int size2 = this.listData.get(i).childListData.size();
            int i3 = i2;
            for (int i4 = 0; i4 < size2; i4++) {
                if (this.listData.get(i).childListData.get(i4).isSelected) {
                    i3++;
                }
            }
            i++;
            i2 = i3;
        }
        return i2;
    }

    public List<CloudVideoChildListData> getSelectedItems() {
        ArrayList arrayList = new ArrayList();
        int size = this.listData.size();
        for (int i = 0; i < size; i++) {
            int size2 = this.listData.get(i).childListData.size();
            for (int i2 = 0; i2 < size2; i2++) {
                if (this.listData.get(i).childListData.get(i2).isSelected) {
                    arrayList.add(this.listData.get(i).childListData.get(i2));
                }
            }
        }
        return arrayList;
    }

    public void append(int i, CloudVideoChildListData cloudVideoChildListData) {
        boolean z;
        List<CloudVideoChildListData> list;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i3 >= this.listData.size()) {
                z = false;
                break;
            } else if (i == this.listData.get(i3).timeSpan) {
                z = true;
                break;
            } else {
                i3++;
            }
        }
        if (!z) {
            this.listData.add(new CloudVideoParentListData(i));
        }
        int i4 = 0;
        while (i4 < this.listData.size()) {
            if (i != this.listData.get(i4).timeSpan || (list = this.listData.get(i4).childListData) == null) {
                i4++;
            } else {
                while (i2 < list.size()) {
                    CloudVideoChildListData cloudVideoChildListData2 = list.get(i2);
                    if (cloudVideoChildListData2 == null || cloudVideoChildListData2.fileId == null || cloudVideoChildListData == null || cloudVideoChildListData.fileId == null || !cloudVideoChildListData2.fileId.equals(cloudVideoChildListData.fileId)) {
                        i2++;
                    } else {
                        return;
                    }
                }
                list.add(cloudVideoChildListData);
                return;
            }
        }
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == TYPE_PARENT) {
            return new PlayListParentHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cs_view_video_parent_list_item, viewGroup, false));
        }
        if (i != TYPE_CHILD) {
            return null;
        }
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cs_view_video_child_list_item, viewGroup, false);
        inflate.setOnClickListener(this.internalClickListener);
        if (!this.disableLongPress) {
            inflate.setOnLongClickListener(this.internalLongClickListener);
        }
        return new PlayListChildHolder(inflate);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder.getItemViewType() == TYPE_PARENT) {
            PlayListParentHolder playListParentHolder = (PlayListParentHolder) viewHolder;
            try {
                Object itemDataByPosition = getItemDataByPosition(i);
                if (itemDataByPosition instanceof CloudVideoParentListData) {
                    CloudVideoParentListData cloudVideoParentListData = (CloudVideoParentListData) itemDataByPosition;
                    if (cloudVideoParentListData == null || cloudVideoParentListData.childListData == null || cloudVideoParentListData.childListData.size() <= 0) {
                        ViewGroup.LayoutParams layoutParams = viewHolder.itemView.getLayoutParams();
                        layoutParams.height = 0;
                        viewHolder.itemView.setLayoutParams(layoutParams);
                        playListParentHolder.rlItemContainer.setVisibility(8);
                        return;
                    }
                    ViewGroup.LayoutParams layoutParams2 = viewHolder.itemView.getLayoutParams();
                    layoutParams2.height = DisplayUtils.a(30.0f);
                    viewHolder.itemView.setLayoutParams(layoutParams2);
                    playListParentHolder.rlItemContainer.setVisibility(0);
                    TextView textView = playListParentHolder.tvTitle;
                    textView.setText("" + formatHour(cloudVideoParentListData.timeSpan));
                }
            } catch (Exception unused) {
            }
        } else if (viewHolder.getItemViewType() == TYPE_CHILD) {
            PlayListChildHolder playListChildHolder = (PlayListChildHolder) viewHolder;
            Object itemDataByPosition2 = getItemDataByPosition(i);
            if (itemDataByPosition2 instanceof CloudVideoChildListData) {
                final CloudVideoChildListData cloudVideoChildListData = (CloudVideoChildListData) itemDataByPosition2;
                TextView textView2 = playListChildHolder.tvStartTime;
                textView2.setText("" + this.sdfHMS.format(Long.valueOf(cloudVideoChildListData.startTime)));
                TextView textView3 = playListChildHolder.tvDuration;
                textView3.setText("" + longToDate(cloudVideoChildListData.duration));
                if (cloudVideoChildListData.isPlaying) {
                    playListChildHolder.ivPlaying.setVisibility(0);
                } else {
                    playListChildHolder.ivPlaying.setVisibility(8);
                }
                if (!cloudVideoChildListData.isPeople) {
                    playListChildHolder.tvStartTime.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
                } else if (this.recyclerView != null) {
                    playListChildHolder.tvStartTime.setCompoundDrawablePadding(DisplayUtils.a(5.0f));
                    playListChildHolder.tvStartTime.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, this.recyclerView.getContext().getResources().getDrawable(R.drawable.motion_icon), (Drawable) null);
                }
                if (playListChildHolder.ivImage.getDrawable() == null) {
                    playListChildHolder.ivImage.setImageResource(R.drawable.set_bg_02_visual_nor);
                }
                if (!TextUtils.isEmpty(cloudVideoChildListData.imgStoreUrl)) {
                    ImageLoader.a().a(cloudVideoChildListData.imgStoreUrl, playListChildHolder.ivImage, this.displayImageOptions);
                }
                playListChildHolder.cbEdit.setOnTouchListener(new View.OnTouchListener() {
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        if (cloudVideoChildListData.isSelected || !PlayListAdapter.this.isDownloadEnabled || PlayListAdapter.this.getSelectedItemCount() < PlayListAdapter.MAX_DOWNLOAD_COUNT) {
                            return false;
                        }
                        ToastUtil.a((int) R.string.cs_max_download_5);
                        return true;
                    }
                });
                playListChildHolder.cbEdit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                        cloudVideoChildListData.isSelected = z;
                    }
                });
                if (this.isInEditMode) {
                    playListChildHolder.cbEdit.setVisibility(0);
                    if (cloudVideoChildListData.isSelected) {
                        playListChildHolder.cbEdit.setChecked(true);
                    } else {
                        playListChildHolder.cbEdit.setChecked(false);
                    }
                } else {
                    playListChildHolder.cbEdit.setVisibility(8);
                }
            }
        }
    }

    public int getItemViewType(int i) {
        return getItemTypeByPosition(i);
    }

    public void setHasStableIds(boolean z) {
        super.setHasStableIds(z);
    }

    public long getItemId(int i) {
        return super.getItemId(i);
    }

    public int getItemCount() {
        return getAllItemCount();
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
        init(recyclerView2.getContext());
        RecyclerView.LayoutManager layoutManager = recyclerView2.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                public int getSpanSize(int i) {
                    return PlayListAdapter.this.getItemTypeByPosition(i) == PlayListAdapter.TYPE_PARENT ? 3 : 1;
                }
            });
            gridLayoutManager.setSpanCount(gridLayoutManager.getSpanCount());
        }
    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView2) {
        this.recyclerView = null;
        release();
        super.onDetachedFromRecyclerView(recyclerView2);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i, List list) {
        if (viewHolder.getItemViewType() != TYPE_CHILD) {
            super.onBindViewHolder(viewHolder, i, list);
        } else if (!list.isEmpty()) {
            PlayListChildHolder playListChildHolder = (PlayListChildHolder) viewHolder;
            try {
                Object itemDataByPosition = getItemDataByPosition(i);
                if (itemDataByPosition instanceof CloudVideoChildListData) {
                    CloudVideoChildListData cloudVideoChildListData = (CloudVideoChildListData) itemDataByPosition;
                    if (cloudVideoChildListData.isPlaying) {
                        playListChildHolder.ivPlaying.setVisibility(0);
                    } else {
                        playListChildHolder.ivPlaying.setVisibility(8);
                    }
                    if (!cloudVideoChildListData.isPeople) {
                        playListChildHolder.tvStartTime.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
                    } else if (this.recyclerView != null) {
                        playListChildHolder.tvStartTime.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, this.recyclerView.getContext().getResources().getDrawable(R.drawable.motion_icon), (Drawable) null);
                        playListChildHolder.tvStartTime.setCompoundDrawablePadding(DisplayUtils.a(3.0f));
                    }
                    if (this.isInEditMode) {
                        playListChildHolder.cbEdit.setVisibility(0);
                        if (cloudVideoChildListData.isSelected) {
                            playListChildHolder.cbEdit.setChecked(true);
                        } else {
                            playListChildHolder.cbEdit.setChecked(false);
                        }
                    } else {
                        playListChildHolder.cbEdit.setVisibility(8);
                    }
                }
            } catch (Exception unused) {
            }
        } else {
            super.onBindViewHolder(viewHolder, i, list);
        }
    }

    public int getItemTypeByPosition(int i) {
        int i2 = TYPE_UNKNOWN;
        if (this.listData != null) {
            int i3 = 0;
            for (int i4 = 0; i4 < this.listData.size(); i4++) {
                if (i == i3) {
                    return TYPE_PARENT;
                }
                i3++;
                if (this.listData.get(i4).childListData.size() > 0) {
                    int size = this.listData.get(i4).childListData.size() + i3;
                    if (i >= i3 && i < size) {
                        return TYPE_CHILD;
                    }
                    i3 = size;
                }
            }
        }
        return i2;
    }

    public <T> T getItemDataByPosition(int i) {
        for (int i2 = 0; i2 < this.listData.size(); i2++) {
            if (i <= 0) {
                return this.listData.get(i2);
            }
            int i3 = i - 1;
            if (i3 < this.listData.get(i2).childListData.size()) {
                return this.listData.get(i2).childListData.get(i3);
            }
            i = i3 - this.listData.get(i2).childListData.size();
        }
        return null;
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

    private String formatHour(int i) {
        StringBuilder sb = new StringBuilder();
        if (i < 10) {
            sb.append(0);
            sb.append(i);
            sb.append(":");
            sb.append("00");
            return sb.toString();
        }
        sb.append(i);
        sb.append(":");
        sb.append("00");
        return sb.toString();
    }

    public boolean isContainVideoData() {
        for (int i = 0; i < this.listData.size(); i++) {
            if (this.listData != null && this.listData.get(i) != null && this.listData.get(i).childListData != null && this.listData.get(i).childListData.size() > 0) {
                return true;
            }
        }
        return false;
    }

    public void setCurrentPlayItem(String str) {
        if (!TextUtils.isEmpty(str)) {
            int i = 0;
            int i2 = 0;
            while (i < this.listData.size()) {
                int i3 = i2 + 1;
                for (int i4 = 0; i4 < this.listData.get(i).childListData.size(); i4++) {
                    CloudVideoChildListData cloudVideoChildListData = this.listData.get(i).childListData.get(i4);
                    if (str.equals(cloudVideoChildListData.fileId)) {
                        cloudVideoChildListData.isPlaying = true;
                        this.currentPlayPosition = i3;
                    } else {
                        cloudVideoChildListData.isPlaying = false;
                    }
                    i3++;
                }
                i++;
                i2 = i3;
            }
        }
    }

    public int getAllItemCount() {
        if (this.listData == null) {
            return 0;
        }
        int i = 0;
        for (int i2 = 0; i2 < this.listData.size(); i2++) {
            i++;
            if (!(this.listData.get(i2) == null || this.listData.get(i2).childListData == null)) {
                i += this.listData.get(i2).childListData.size();
            }
        }
        return i;
    }

    public int getChildItemCount() {
        if (this.listData == null) {
            return 0;
        }
        int i = 0;
        for (int i2 = 0; i2 < this.listData.size(); i2++) {
            if (!(this.listData.get(i2) == null || this.listData.get(i2).childListData == null)) {
                i += this.listData.get(i2).childListData.size();
            }
        }
        return i;
    }

    public CloudVideoChildListData getFirstVideoData() {
        int allItemCount = getAllItemCount();
        for (int i = 0; i < allItemCount; i++) {
            if (TYPE_CHILD == getItemTypeByPosition(i)) {
                return (CloudVideoChildListData) getItemDataByPosition(i);
            }
        }
        return null;
    }

    public CloudVideoChildListData getCurrentVideoData() {
        int allItemCount = getAllItemCount();
        for (int i = this.currentPlayPosition; i < allItemCount; i++) {
            if (TYPE_CHILD == getItemTypeByPosition(i)) {
                return (CloudVideoChildListData) getItemDataByPosition(i);
            }
        }
        return null;
    }

    public CloudVideoChildListData getNextVideoData() {
        int allItemCount = getAllItemCount();
        int i = this.currentPlayPosition;
        do {
            i++;
            if (i >= allItemCount) {
                return null;
            }
        } while (TYPE_CHILD != getItemTypeByPosition(i));
        return (CloudVideoChildListData) getItemDataByPosition(i);
    }

    private class InternalClickListener implements View.OnClickListener {
        private InternalClickListener() {
        }

        public void onClick(View view) {
            if (PlayListAdapter.this.recyclerView != null) {
                int childAdapterPosition = PlayListAdapter.this.recyclerView.getChildAdapterPosition(view);
                if (PlayListAdapter.this.isInEditMode) {
                    Object itemDataByPosition = PlayListAdapter.this.getItemDataByPosition(childAdapterPosition);
                    if (itemDataByPosition instanceof CloudVideoChildListData) {
                        CloudVideoChildListData cloudVideoChildListData = (CloudVideoChildListData) itemDataByPosition;
                        if (!cloudVideoChildListData.isSelected && PlayListAdapter.this.getSelectedItemCount() >= PlayListAdapter.MAX_DOWNLOAD_COUNT && PlayListAdapter.this.isDownloadEnabled) {
                            ToastUtil.a((int) R.string.cs_max_download_5);
                            return;
                        } else if (cloudVideoChildListData.isSelected) {
                            cloudVideoChildListData.isSelected = false;
                        } else {
                            cloudVideoChildListData.isSelected = true;
                        }
                    }
                    PlayListAdapter.this.notifyItemChanged(childAdapterPosition);
                    return;
                }
                PlayListAdapter.this.currentPlayPosition = childAdapterPosition;
                if (PlayListAdapter.this.iItemClickListener != null) {
                    PlayListAdapter.this.iItemClickListener.onItemClick(view, PlayListAdapter.this.recyclerView.getChildAdapterPosition(view), null);
                }
            }
        }
    }

    private class InternalLongClickListener implements View.OnLongClickListener {
        private InternalLongClickListener() {
        }

        public boolean onLongClick(View view) {
            if (PlayListAdapter.this.iItemLongClickListener == null) {
                return false;
            }
            PlayListAdapter.this.iItemLongClickListener.onItemLongClick(view, PlayListAdapter.this.recyclerView.getChildAdapterPosition(view));
            return true;
        }
    }

    public void selectAllItem() {
        int size = this.listData.size();
        for (int i = 0; i < size; i++) {
            int size2 = this.listData.get(i).childListData.size();
            for (int i2 = 0; i2 < size2; i2++) {
                this.listData.get(i).childListData.get(i2).isSelected = true;
            }
        }
        notifyDataSetChanged();
    }

    public void unSelectAllItem() {
        int size = this.listData.size();
        for (int i = 0; i < size; i++) {
            int size2 = this.listData.get(i).childListData.size();
            for (int i2 = 0; i2 < size2; i2++) {
                this.listData.get(i).childListData.get(i2).isSelected = false;
            }
        }
        notifyDataSetChanged();
    }

    public void clearAllData() {
        for (CloudVideoParentListData next : this.listData) {
            if (next.childListData != null) {
                next.childListData.clear();
                next.timeSpan = 0;
            }
        }
        this.listData.clear();
    }
}
