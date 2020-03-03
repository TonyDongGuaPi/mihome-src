package com.xiaomi.smarthome.miio.camera.face.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.miio.camera.face.FaceManager;
import com.xiaomi.smarthome.miio.camera.face.model.UnmarkedFaceInfo;
import java.util.ArrayList;
import java.util.List;

public class UnmarkedFaceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    /* access modifiers changed from: private */
    public ClickCallBack clickCallBack;
    /* access modifiers changed from: private */
    public FaceManager faceManager;
    private Context mContext;
    /* access modifiers changed from: private */
    public List<UnmarkedFaceInfo> mData = new ArrayList();

    public interface ClickCallBack {
        void onRecyclerClick(int i);
    }

    public UnmarkedFaceAdapter(Context context, ClickCallBack clickCallBack2, FaceManager faceManager2) {
        this.mContext = context;
        this.clickCallBack = clickCallBack2;
        this.faceManager = faceManager2;
    }

    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ItemViewHolder(LayoutInflater.from(this.mContext).inflate(R.layout.recycler_item_unmarked_face, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((ItemViewHolder) viewHolder).bindView(i);
    }

    public void setData(ArrayList<UnmarkedFaceInfo> arrayList) {
        this.mData.clear();
        this.mData.addAll(arrayList);
        notifyDataSetChanged();
    }

    public void addData(ArrayList<UnmarkedFaceInfo> arrayList) {
        this.mData.addAll(arrayList);
        notifyDataSetChanged();
    }

    public UnmarkedFaceInfo getItem(int i) {
        return this.mData.get(i);
    }

    public int getItemCount() {
        return this.mData.size();
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView ivFaceImg;
        TextView tvEdit;

        public ItemViewHolder(View view) {
            super(view);
            this.ivFaceImg = (ImageView) view.findViewById(R.id.iv_face_img);
            this.tvEdit = (TextView) view.findViewById(R.id.tv_edit_mark);
            view.setOnClickListener(new View.OnClickListener(UnmarkedFaceAdapter.this) {
                public void onClick(View view) {
                    int layoutPosition = ItemViewHolder.this.getLayoutPosition();
                    if (layoutPosition == -1) {
                        layoutPosition = ItemViewHolder.this.getAdapterPosition();
                    }
                    UnmarkedFaceAdapter.this.clickCallBack.onRecyclerClick(layoutPosition);
                }
            });
        }

        /* access modifiers changed from: package-private */
        public void bindView(int i) {
            UnmarkedFaceInfo unmarkedFaceInfo = (UnmarkedFaceInfo) UnmarkedFaceAdapter.this.mData.get(i);
            this.tvEdit.setVisibility(0);
            if (TextUtils.isEmpty(unmarkedFaceInfo.faceId)) {
                this.ivFaceImg.setImageResource(R.drawable.icon_face_manager_first_larg);
            } else if (!unmarkedFaceInfo.faceId.equals(this.ivFaceImg.getTag())) {
                this.ivFaceImg.setTag(unmarkedFaceInfo.faceId);
                ImageLoader.a().a(UnmarkedFaceAdapter.this.faceManager.getFaceImg(unmarkedFaceInfo.faceId), this.ivFaceImg);
            }
        }
    }
}
