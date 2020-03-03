package com.mi.global.bbs.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.mi.global.bbs.R;
import com.mi.global.bbs.utils.TextHelper;

public class UnsignedView extends LinearLayout {
    @BindView(2131493248)
    ImageView face1Iv;
    @BindView(2131493249)
    ImageView face2Iv;
    @BindView(2131493250)
    ImageView face3Iv;
    private Context mContext;
    private OnItemClick onItemClick;
    @BindView(2131494166)
    TextView unsignTip;
    @BindView(2131494167)
    TextView unsignTitle;

    public interface OnItemClick {
        void onFaceItemClick(View view, int i);
    }

    public UnsignedView(Context context) {
        this(context, (AttributeSet) null);
    }

    public UnsignedView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        setOrientation(1);
        setGravity(1);
        LayoutInflater.from(context).inflate(R.layout.bbs_unsign_layout, this, true);
        ButterKnife.bind((View) this);
    }

    public UnsignedView(Context context, @Nullable AttributeSet attributeSet, int i) {
        this(context, attributeSet);
    }

    public UnsignedView(Context context, AttributeSet attributeSet, int i, int i2) {
        this(context, attributeSet, i);
    }

    @OnClick({2131493248, 2131493249, 2131493250})
    public void onClick(View view) {
        if (this.onItemClick != null) {
            if (view.getId() == R.id.face1_iv) {
                this.onItemClick.onFaceItemClick(this.face1Iv, 0);
            } else if (view.getId() == R.id.face2_iv) {
                this.onItemClick.onFaceItemClick(this.face2Iv, 1);
            } else if (view.getId() == R.id.face3_iv) {
                this.onItemClick.onFaceItemClick(this.face3Iv, 2);
            }
        }
    }

    public OnItemClick getOnItemClick() {
        return this.onItemClick;
    }

    public void setOnItemClick(OnItemClick onItemClick2) {
        this.onItemClick = onItemClick2;
    }

    public void setTitle(CharSequence charSequence) {
        this.unsignTitle.setText(charSequence);
    }

    public void setTip(CharSequence charSequence) {
        this.unsignTip.setText(TextHelper.getQuantityString(this.mContext, R.plurals.fans_checked_in_today, (String) charSequence));
    }
}
