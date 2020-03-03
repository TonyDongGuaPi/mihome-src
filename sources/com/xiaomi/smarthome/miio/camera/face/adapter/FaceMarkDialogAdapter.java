package com.xiaomi.smarthome.miio.camera.face.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.miio.camera.face.FaceManager;
import com.xiaomi.smarthome.miio.camera.face.model.FigureInfo;
import java.util.List;

public class FaceMarkDialogAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    /* access modifiers changed from: private */
    public ClickCallBack mClickCallBack;
    private Context mContext;
    /* access modifiers changed from: private */
    public List<FigureInfo> mDatas;
    /* access modifiers changed from: private */
    public FaceManager mFaceManager;

    public interface ClickCallBack {
        void onRecyclerClick(int i);
    }

    public FaceMarkDialogAdapter(Context context, List<FigureInfo> list, ClickCallBack clickCallBack, FaceManager faceManager) {
        this.mContext = context;
        this.mDatas = list;
        this.mClickCallBack = clickCallBack;
        this.mFaceManager = faceManager;
    }

    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ItemViewHolder(LayoutInflater.from(this.mContext).inflate(R.layout.recylcer_item_face_mark_dialog, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((ItemViewHolder) viewHolder).bindView(i);
    }

    public int getItemCount() {
        if (this.mDatas.size() >= 6) {
            return 6;
        }
        return this.mDatas.size();
    }

    public FigureInfo getItem(int i) {
        return this.mDatas.get(i);
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView imageview;
        TextView textview;

        public ItemViewHolder(View view) {
            super(view);
            this.imageview = (ImageView) view.findViewById(R.id.iv);
            this.textview = (TextView) view.findViewById(R.id.f13396tv);
            view.setOnClickListener(new View.OnClickListener(FaceMarkDialogAdapter.this) {
                public void onClick(View view) {
                    int layoutPosition = ItemViewHolder.this.getLayoutPosition();
                    if (layoutPosition == -1) {
                        layoutPosition = ItemViewHolder.this.getAdapterPosition();
                    }
                    FaceMarkDialogAdapter.this.mClickCallBack.onRecyclerClick(layoutPosition);
                }
            });
        }

        /* access modifiers changed from: package-private */
        public void bindView(int i) {
            FigureInfo figureInfo = (FigureInfo) FaceMarkDialogAdapter.this.mDatas.get(i);
            this.textview.setText(figureInfo.figureName);
            ImageLoader.a().a(FaceMarkDialogAdapter.this.mFaceManager.getFaceImg(figureInfo.coverFaceId), this.imageview);
        }
    }
}
