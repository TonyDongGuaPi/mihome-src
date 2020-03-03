package com.xiaomi.smarthome.miio.camera.face.photopicker;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import java.util.ArrayList;
import java.util.List;

public class ImagePickerAdapter extends RecyclerView.Adapter<ImagePickerViewHolder> {
    /* access modifiers changed from: private */
    public int limit = 1;
    private Context mContext;
    private List<Image> mData = new ArrayList();
    /* access modifiers changed from: private */
    public OnImageClickListener mOnImageClickListener;
    /* access modifiers changed from: private */
    public List<Image> mSelectedList = new ArrayList();

    public interface OnImageClickListener {
        void onImageClick(Image image);
    }

    public ImagePickerAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    public ImagePickerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ImagePickerViewHolder(LayoutInflater.from(this.mContext).inflate(R.layout.recycler_item_img_chooser, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull ImagePickerViewHolder imagePickerViewHolder, int i) {
        imagePickerViewHolder.bindView(i);
    }

    public int getItemCount() {
        return this.mData.size();
    }

    public void setData(List<Image> list) {
        this.mData = list;
        notifyDataSetChanged();
    }

    public Image getItem(int i) {
        return this.mData.get(i);
    }

    public void setOnImageClickListener(OnImageClickListener onImageClickListener) {
        this.mOnImageClickListener = onImageClickListener;
    }

    public void setLimit(int i) {
        this.limit = i;
    }

    public List<Image> getSelectImages() {
        return this.mSelectedList;
    }

    public void release() {
        this.mSelectedList.clear();
        this.mData.clear();
    }

    class ImagePickerViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        /* access modifiers changed from: private */
        public ImageView ivPick;

        public ImagePickerViewHolder(View view) {
            super(view);
            this.imageView = (ImageView) view.findViewById(R.id.iv_image);
            this.ivPick = (ImageView) view.findViewById(R.id.iv_picker);
        }

        public void bindView(int i) {
            final Image item = ImagePickerAdapter.this.getItem(i);
            if (ImagePickerAdapter.this.mSelectedList.contains(item)) {
                this.ivPick.setImageResource(R.drawable.icon_selected);
            } else {
                this.ivPick.setImageResource(R.drawable.icon_unselected);
            }
            this.imageView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (ImagePickerAdapter.this.mSelectedList.contains(item)) {
                        ImagePickerViewHolder.this.ivPick.setImageResource(R.drawable.icon_unselected);
                        ImagePickerAdapter.this.mSelectedList.remove(item);
                    } else if (ImagePickerAdapter.this.mSelectedList.size() >= ImagePickerAdapter.this.limit) {
                        ToastUtil.a((int) R.string.pick_photo_limit_tips);
                        return;
                    } else {
                        ImagePickerViewHolder.this.ivPick.setImageResource(R.drawable.icon_selected);
                        ImagePickerAdapter.this.mSelectedList.add(item);
                    }
                    if (ImagePickerAdapter.this.mOnImageClickListener != null) {
                        ImagePickerAdapter.this.mOnImageClickListener.onImageClick(item);
                    }
                }
            });
            ImageLoader a2 = ImageLoader.a();
            a2.a("file://" + item.path, this.imageView);
        }
    }
}
