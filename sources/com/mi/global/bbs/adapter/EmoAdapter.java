package com.mi.global.bbs.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;
import com.mi.global.bbs.model.SignHomeData;
import com.mi.global.bbs.ui.checkin.SignPostActivity;
import com.mi.global.bbs.utils.ImageLoader;
import com.mi.global.bbs.utils.TextHelper;
import java.util.List;

public class EmoAdapter extends RecyclerView.Adapter {
    private List<SignHomeData.SignBean.EmotionBean> emotionlist;
    /* access modifiers changed from: private */
    public Context mContext;
    OnEmotionItemClickListener mOnEmotionItemClickListener;

    public interface OnEmotionItemClickListener {
        void onItemClick(int i);
    }

    public class EmoHolder_ViewBinding implements Unbinder {
        private EmoHolder target;

        @UiThread
        public EmoHolder_ViewBinding(EmoHolder emoHolder, View view) {
            this.target = emoHolder;
            emoHolder.mEmoIcon = (ImageView) Utils.findRequiredViewAsType(view, R.id.emo_icon, "field 'mEmoIcon'", ImageView.class);
            emoHolder.mEmoLock = (ImageView) Utils.findRequiredViewAsType(view, R.id.emo_lock, "field 'mEmoLock'", ImageView.class);
        }

        @CallSuper
        public void unbind() {
            EmoHolder emoHolder = this.target;
            if (emoHolder != null) {
                this.target = null;
                emoHolder.mEmoIcon = null;
                emoHolder.mEmoLock = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public EmoAdapter(Context context, List<SignHomeData.SignBean.EmotionBean> list) {
        this.emotionlist = list;
        this.mContext = context;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new EmoHolder(LayoutInflater.from(this.mContext).inflate(R.layout.bbs_emo_item, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        EmoHolder emoHolder = (EmoHolder) viewHolder;
        final SignHomeData.SignBean.EmotionBean emotionBean = this.emotionlist.get(i);
        if (emotionBean.enable) {
            ImageLoader.showImage(emoHolder.mEmoIcon, emotionBean.enableurl);
            emoHolder.mEmoLock.setVisibility(8);
            emoHolder.itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (EmoAdapter.this.mOnEmotionItemClickListener != null) {
                        EmoAdapter.this.mOnEmotionItemClickListener.onItemClick(i);
                    }
                    EmoAdapter.this.mContext.startActivity(new Intent(EmoAdapter.this.mContext, SignPostActivity.class).putExtra(SignPostActivity.EMO, emotionBean));
                }
            });
            return;
        }
        ImageLoader.showImage(emoHolder.mEmoIcon, emotionBean.disableurl);
        emoHolder.mEmoLock.setVisibility(0);
        emoHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Context access$000 = EmoAdapter.this.mContext;
                Context access$0002 = EmoAdapter.this.mContext;
                int i = R.plurals.emo_lock;
                Toast.makeText(access$000, TextHelper.getQuantityString(access$0002, i, emotionBean.needsign + ""), 0).show();
            }
        });
    }

    public int getItemCount() {
        if (this.emotionlist == null) {
            return 0;
        }
        return this.emotionlist.size();
    }

    class EmoHolder extends RecyclerView.ViewHolder {
        @BindView(2131493220)
        ImageView mEmoIcon;
        @BindView(2131493221)
        ImageView mEmoLock;

        public EmoHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    public void setOnEmotionItemClickListener(OnEmotionItemClickListener onEmotionItemClickListener) {
        this.mOnEmotionItemClickListener = onEmotionItemClickListener;
    }
}
