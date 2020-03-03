package com.xiaomi.smarthome.miio.camera.face.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.mijia.debug.SDKLog;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.miio.camera.face.FaceManager;
import com.xiaomi.smarthome.miio.camera.face.model.FigureInfo;
import java.util.ArrayList;
import java.util.List;

public class FaceRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final String TAG = "FaceRecyclerAdapter";
    /* access modifiers changed from: private */
    public ClickCallBack clickCallBack;
    /* access modifiers changed from: private */
    public Context context;
    /* access modifiers changed from: private */
    public FaceManager faceManager;
    public List<FigureInfo> mData = new ArrayList();
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

    public FaceRecyclerAdapter(Context context2, ClickCallBack clickCallBack2, FaceManager faceManager2) {
        initImageDisplayOptions(context2);
        this.context = context2;
        this.clickCallBack = clickCallBack2;
        this.faceManager = faceManager2;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_face_figures, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        ((ItemViewHolder) viewHolder).bindView(i);
    }

    public void setData(ArrayList<FigureInfo> arrayList) {
        this.mData.clear();
        this.selectedCount = 0;
        this.mData.addAll(arrayList);
        this.mData.add(new FigureInfo());
        notifyDataSetChanged();
    }

    public void addData(ArrayList<FigureInfo> arrayList) {
        unSelectAll();
        this.mData.addAll(arrayList);
        notifyDataSetChanged();
    }

    public List<String> getSelectedFigureIds() {
        ArrayList arrayList = new ArrayList();
        for (FigureInfo next : this.mData) {
            if (next.selected) {
                arrayList.add(next.figureId);
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
    }

    public int getSelectedCount() {
        return this.selectedCount;
    }

    public void toggolSelected(FigureInfo figureInfo) {
        figureInfo.selected = !figureInfo.selected;
        if (figureInfo.selected) {
            this.selectedCount++;
        } else {
            this.selectedCount--;
        }
    }

    public void selectAll() {
        for (FigureInfo figureInfo : this.mData) {
            figureInfo.selected = true;
        }
        this.selectedCount = this.mData.size();
        notifyDataSetChanged();
    }

    public void unSelectAll() {
        for (FigureInfo figureInfo : this.mData) {
            figureInfo.selected = false;
        }
        this.selectedCount = 0;
        notifyDataSetChanged();
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView faceSelected;
        ImageView figureFace;
        TextView figureName;

        ItemViewHolder(View view) {
            super(view);
            this.figureFace = (ImageView) view.findViewById(R.id.figure_face);
            this.figureName = (TextView) view.findViewById(R.id.figure_name);
            this.faceSelected = (ImageView) view.findViewById(R.id.face_selected_img);
            view.setOnClickListener(new View.OnClickListener(FaceRecyclerAdapter.this) {
                public void onClick(View view) {
                    int layoutPosition = ItemViewHolder.this.getLayoutPosition();
                    if (layoutPosition == -1) {
                        layoutPosition = ItemViewHolder.this.getAdapterPosition();
                    }
                    String str = FaceRecyclerAdapter.TAG;
                    SDKLog.b(str, layoutPosition + "onClick");
                    if (FaceRecyclerAdapter.this.selectedMod && layoutPosition < FaceRecyclerAdapter.this.mData.size() - 1) {
                        FaceRecyclerAdapter.this.toggolSelected(FaceRecyclerAdapter.this.mData.get(layoutPosition));
                        FaceRecyclerAdapter.this.notifyItemChanged(layoutPosition);
                    }
                    FaceRecyclerAdapter.this.clickCallBack.onRecyclerClick(layoutPosition);
                }
            });
            view.setOnLongClickListener(new View.OnLongClickListener(FaceRecyclerAdapter.this) {
                public boolean onLongClick(View view) {
                    int layoutPosition = ItemViewHolder.this.getLayoutPosition();
                    if (layoutPosition >= FaceRecyclerAdapter.this.mData.size() - 1) {
                        return true;
                    }
                    if (layoutPosition == -1) {
                        layoutPosition = ItemViewHolder.this.getAdapterPosition();
                    }
                    String str = FaceRecyclerAdapter.TAG;
                    SDKLog.b(str, layoutPosition + "onLongClick");
                    FaceRecyclerAdapter.this.toggolSelected(FaceRecyclerAdapter.this.mData.get(layoutPosition));
                    FaceRecyclerAdapter.this.notifyDataSetChanged();
                    FaceRecyclerAdapter.this.clickCallBack.onRecyclerLongClick(layoutPosition);
                    return true;
                }
            });
        }

        /* access modifiers changed from: package-private */
        public void bindView(int i) {
            if (i >= FaceRecyclerAdapter.this.mData.size() - 1) {
                this.faceSelected.setVisibility(8);
                this.figureName.setText(FaceRecyclerAdapter.this.context.getString(R.string.add));
                this.figureFace.setImageResource(R.drawable.face_add_item_icon_round);
                return;
            }
            final FigureInfo figureInfo = FaceRecyclerAdapter.this.mData.get(i);
            this.figureName.setText(figureInfo.figureName);
            if (FaceRecyclerAdapter.this.selectedMod) {
                this.faceSelected.setVisibility(0);
                if (figureInfo.selected) {
                    this.faceSelected.setImageResource(R.drawable.icon_selected);
                } else {
                    this.faceSelected.setImageResource(R.drawable.icon_unselected);
                }
            } else {
                this.faceSelected.setVisibility(8);
            }
            this.figureFace.setTag(figureInfo.coverFaceId);
            if (!TextUtils.isEmpty(figureInfo.coverFaceId)) {
                ImageLoader.a().a(FaceRecyclerAdapter.this.faceManager.getFaceImg(figureInfo.coverFaceId), this.figureFace, FaceRecyclerAdapter.this.mOptions, (ImageLoadingListener) new SimpleImageLoadingListener() {
                    int failCount = 0;

                    public void onLoadingFailed(String str, View view, FailReason failReason) {
                        if (this.failCount <= 3) {
                            this.failCount++;
                            ImageLoader.a().a(FaceRecyclerAdapter.this.faceManager.getFaceImg(figureInfo.coverFaceId), ItemViewHolder.this.figureFace, FaceRecyclerAdapter.this.mOptions, (ImageLoadingListener) this);
                        }
                    }
                });
            }
        }
    }

    public void destroyDisplayImageOptions() {
        this.mOptions = null;
    }

    private void initImageDisplayOptions(Context context2) {
        this.mOptions = new DisplayImageOptions.Builder().b(true).d(true).e(true).a(Bitmap.Config.RGB_565).d();
    }
}
