package com.xiaomi.smarthome.miio.camera.face.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.mijia.debug.SDKLog;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.miio.camera.face.FaceManager;
import com.xiaomi.smarthome.miio.camera.face.model.FaceInfo;
import java.util.ArrayList;
import java.util.List;

public class FigureFaceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final String TAG = "FigureFaceAdapter";
    /* access modifiers changed from: private */
    public ClickCallBack clickCallBack;
    private Context context;
    /* access modifiers changed from: private */
    public FaceManager faceManager;
    public List<FaceInfo> mData = new ArrayList();
    /* access modifiers changed from: private */
    public DisplayImageOptions mOptions;
    private int selectedCount;
    /* access modifiers changed from: private */
    public boolean selectedMod;

    public interface ClickCallBack {
        void onRecyclerClick(int i);

        void onRecyclerLongClick(int i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public FigureFaceAdapter(Context context2, ClickCallBack clickCallBack2, FaceManager faceManager2) {
        initImageDisplayOptions(context2);
        this.context = context2;
        this.clickCallBack = clickCallBack2;
        this.faceManager = faceManager2;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_figure_faces, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        ((ItemViewHolder) viewHolder).bindView(i);
    }

    public void setData(ArrayList<FaceInfo> arrayList) {
        this.mData.clear();
        this.selectedCount = 0;
        this.mData.addAll(arrayList);
        this.mData.add(new FaceInfo());
        notifyDataSetChanged();
    }

    public void addData(ArrayList<FaceInfo> arrayList) {
        unSelectAll();
        this.mData.addAll(arrayList);
        notifyDataSetChanged();
    }

    public List<String> getSelectedFaceIds() {
        ArrayList arrayList = new ArrayList();
        for (FaceInfo next : this.mData) {
            if (next.selected) {
                arrayList.add(next.faceId);
            }
        }
        return arrayList;
    }

    public int getItemCount() {
        return this.mData.size();
    }

    public void setSelectedMod(boolean z) {
        this.selectedMod = z;
        if (!this.selectedMod) {
            unSelectAll();
        }
        notifyDataSetChanged();
    }

    public int getSelectedCount() {
        return this.selectedCount;
    }

    public void toggolSelected(FaceInfo faceInfo) {
        faceInfo.selected = !faceInfo.selected;
        if (faceInfo.selected) {
            this.selectedCount++;
        } else {
            this.selectedCount--;
        }
    }

    public void selectAll() {
        for (int i = 0; i < this.mData.size() - 1; i++) {
            this.mData.get(i).selected = true;
        }
        this.selectedCount = this.mData.size() - 1;
        notifyDataSetChanged();
    }

    public void unSelectAll() {
        for (FaceInfo faceInfo : this.mData) {
            faceInfo.selected = false;
        }
        this.selectedCount = 0;
        notifyDataSetChanged();
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView faceSelected;
        ImageView figureFace;

        ItemViewHolder(View view) {
            super(view);
            this.figureFace = (ImageView) view.findViewById(R.id.figure_face);
            this.faceSelected = (ImageView) view.findViewById(R.id.face_selected_img);
            view.setOnClickListener(new View.OnClickListener(FigureFaceAdapter.this) {
                public void onClick(View view) {
                    int layoutPosition = ItemViewHolder.this.getLayoutPosition();
                    if (layoutPosition == -1) {
                        layoutPosition = ItemViewHolder.this.getAdapterPosition();
                    }
                    String str = FigureFaceAdapter.TAG;
                    SDKLog.b(str, layoutPosition + "onClick");
                    if (FigureFaceAdapter.this.selectedMod) {
                        FigureFaceAdapter.this.toggolSelected(FigureFaceAdapter.this.mData.get(layoutPosition));
                        FigureFaceAdapter.this.notifyItemChanged(layoutPosition);
                    }
                    FigureFaceAdapter.this.clickCallBack.onRecyclerClick(layoutPosition);
                }
            });
            view.setOnLongClickListener(new View.OnLongClickListener(FigureFaceAdapter.this) {
                public boolean onLongClick(View view) {
                    int layoutPosition = ItemViewHolder.this.getLayoutPosition();
                    if (layoutPosition == -1) {
                        layoutPosition = ItemViewHolder.this.getAdapterPosition();
                    }
                    String str = FigureFaceAdapter.TAG;
                    SDKLog.b(str, layoutPosition + "onLongClick");
                    FigureFaceAdapter.this.toggolSelected(FigureFaceAdapter.this.mData.get(layoutPosition));
                    FigureFaceAdapter.this.notifyDataSetChanged();
                    FigureFaceAdapter.this.clickCallBack.onRecyclerLongClick(layoutPosition);
                    return true;
                }
            });
        }

        /* access modifiers changed from: package-private */
        public void bindView(int i) {
            if (i >= FigureFaceAdapter.this.mData.size() - 1) {
                this.faceSelected.setVisibility(8);
                this.figureFace.setImageResource(R.drawable.icon_face_add);
                return;
            }
            final FaceInfo faceInfo = FigureFaceAdapter.this.mData.get(i);
            if (FigureFaceAdapter.this.selectedMod) {
                this.faceSelected.setVisibility(0);
                if (faceInfo.selected) {
                    this.faceSelected.setImageResource(R.drawable.icon_selected);
                } else {
                    this.faceSelected.setImageResource(R.drawable.icon_unselected);
                }
            } else {
                this.faceSelected.setVisibility(8);
            }
            this.figureFace.setTag(faceInfo.faceId);
            if (!TextUtils.isEmpty(faceInfo.faceId)) {
                ImageLoader.a().a(FigureFaceAdapter.this.faceManager.getFaceImg(faceInfo.faceId), this.figureFace, FigureFaceAdapter.this.mOptions, (ImageLoadingListener) new SimpleImageLoadingListener() {
                    int failCount = 0;

                    public void onLoadingFailed(String str, View view, FailReason failReason) {
                        if (this.failCount <= 3) {
                            this.failCount++;
                            ImageLoader.a().a(FigureFaceAdapter.this.faceManager.getFaceImg(faceInfo.faceId), ItemViewHolder.this.figureFace, FigureFaceAdapter.this.mOptions, (ImageLoadingListener) this);
                        }
                    }
                });
                return;
            }
            this.figureFace.setImageResource(R.drawable.picture_busy);
        }
    }

    public void destroyDisplayImageOptions() {
        this.mOptions = null;
    }

    private void initImageDisplayOptions(Context context2) {
        this.mOptions = new DisplayImageOptions.Builder().b(true).d(true).e(true).a(Bitmap.Config.RGB_565).d();
    }
}
