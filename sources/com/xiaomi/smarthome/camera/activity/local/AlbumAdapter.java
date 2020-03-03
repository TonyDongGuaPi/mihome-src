package com.xiaomi.smarthome.camera.activity.local;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.mijia.app.AppConfig;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.view.recycle.RecyclerClickListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumViewHolder> {
    private boolean isV4;
    /* access modifiers changed from: private */
    public List<LocalFileData> mData = new ArrayList();
    /* access modifiers changed from: private */
    public boolean mIsMultiSelectMode;
    /* access modifiers changed from: private */
    public RecyclerClickListener mListener;
    /* access modifiers changed from: private */
    public DisplayImageOptions mOptions;
    private boolean[] mSelectMap;

    public AlbumAdapter(Context context, RecyclerClickListener recyclerClickListener, boolean z) {
        this.isV4 = z;
        initImageDisplayOptions(context);
        this.mListener = recyclerClickListener;
    }

    public AlbumViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new AlbumViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.album_item, viewGroup, false));
    }

    public void onBindViewHolder(AlbumViewHolder albumViewHolder, int i) {
        albumViewHolder.bindView(i);
    }

    public int getItemCount() {
        return this.mData.size();
    }

    public LocalFileData getItem(int i) {
        if (i < 0 || i >= this.mData.size()) {
            return null;
        }
        return this.mData.get(i);
    }

    class AlbumViewHolder extends RecyclerView.ViewHolder {
        private ImageView checkView;
        private ImageView imageView;
        private TextView timeView;
        private View videoImg;

        public AlbumViewHolder(View view) {
            super(view);
            this.timeView = (TextView) view.findViewById(R.id.title);
            this.imageView = (ImageView) view.findViewById(R.id.thumb);
            this.checkView = (ImageView) view.findViewById(R.id.checkbox);
            this.videoImg = view.findViewById(R.id.album_video);
            this.imageView.getLayoutParams().height = (((int) (((float) AppConfig.b) - (AppConfig.d * 4.0f))) * 3) / 16;
            view.setOnLongClickListener(new View.OnLongClickListener(AlbumAdapter.this) {
                public boolean onLongClick(View view) {
                    if (AlbumAdapter.this.mListener == null) {
                        return true;
                    }
                    AlbumAdapter.this.mListener.onRecyclerLongClick(view);
                    return true;
                }
            });
            view.setOnClickListener(new View.OnClickListener(AlbumAdapter.this) {
                public void onClick(View view) {
                    if (AlbumAdapter.this.mListener != null) {
                        AlbumAdapter.this.mListener.onRecyclerClick(view);
                    }
                }
            });
        }

        /* access modifiers changed from: package-private */
        public void bindView(int i) {
            LocalFileData localFileData = (LocalFileData) AlbumAdapter.this.mData.get(i);
            if (localFileData.videoType == 3) {
                this.videoImg.setVisibility(0);
                this.timeView.setText(localFileData.title);
                ImageLoader.a().a(localFileData.displayImgPath, this.imageView, AlbumAdapter.this.mOptions);
                if (AlbumAdapter.this.mIsMultiSelectMode) {
                    this.checkView.setVisibility(0);
                    if (AlbumAdapter.this.isSelected(i)) {
                        this.checkView.setImageResource(R.drawable.delete_checked);
                    } else {
                        this.checkView.setImageResource(R.drawable.delete_unchecked);
                    }
                } else {
                    this.checkView.setVisibility(8);
                }
            } else {
                if (localFileData.item.e) {
                    this.videoImg.setVisibility(0);
                    this.timeView.setText(localFileData.title);
                } else {
                    this.videoImg.setVisibility(8);
                    this.timeView.setText("");
                }
                ImageLoader.a().a(localFileData.item.d, this.imageView, AlbumAdapter.this.mOptions);
                if (AlbumAdapter.this.mIsMultiSelectMode) {
                    this.checkView.setVisibility(0);
                    if (AlbumAdapter.this.isSelected(i)) {
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

    public void setData(List<LocalFileData> list) {
        this.mData = list;
        this.mSelectMap = new boolean[this.mData.size()];
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
        if (this.mSelectMap == null) {
            return 0;
        }
        int i = 0;
        for (boolean z : this.mSelectMap) {
            if (z) {
                i++;
            }
        }
        return i;
    }

    public void selectToggle(int i) {
        if (i >= 0 && i < this.mSelectMap.length) {
            this.mSelectMap[i] = !this.mSelectMap[i];
        }
    }

    /* access modifiers changed from: private */
    public boolean isSelected(int i) {
        if (i < 0 || i >= this.mSelectMap.length) {
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

    public ArrayList<LocalFileData> getSelectItems() {
        ArrayList<LocalFileData> arrayList = new ArrayList<>();
        int i = 0;
        while (i < this.mData.size() && i < this.mSelectMap.length) {
            if (this.mSelectMap[i]) {
                arrayList.add(this.mData.get(i));
            }
            i++;
        }
        return arrayList;
    }

    public void destroyDisplayImageOptions() {
        this.mOptions = null;
        this.mListener = null;
    }
}
