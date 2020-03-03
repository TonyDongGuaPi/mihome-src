package com.mi.global.bbs.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import com.mi.global.bbs.R;

public class UploadImageView extends RelativeLayout {
    public static final int STATE_UPLOADING = 1;
    public static final int STATE_UPLOAD_FAIL = 2;
    public static final int STATE_UPLOAD_SUCCESS = 3;
    public static final int STATE_WAITING_UPLOAD = 0;
    @BindView(2131493447)
    ImageView imageItem;
    private ImageView mImageView;
    private ProgressBar mProgressBar;
    private TextView mTextView;
    /* access modifiers changed from: private */
    public int position;
    @BindView(2131493847)
    ProgressBar progressItem;
    RetryUploadListener retryUploadListener;
    /* access modifiers changed from: private */
    public int state;
    @BindView(2131494073)
    TextView textItem;

    public interface RetryUploadListener {
        void onRetryUpload(int i);
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int i) {
        this.position = i;
    }

    public UploadImageView(Context context) {
        super(context);
        this.state = 0;
        LayoutInflater.from(context).inflate(R.layout.bbs_feedback_select_image_layout, this, true);
        this.mImageView = (ImageView) findViewById(R.id.image_item);
        this.mProgressBar = (ProgressBar) findViewById(R.id.progress_item);
        this.mTextView = (TextView) findViewById(R.id.text_item);
        this.mTextView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (UploadImageView.this.state == 2 && UploadImageView.this.retryUploadListener != null) {
                    UploadImageView.this.retryUploadListener.onRetryUpload(UploadImageView.this.position);
                }
            }
        });
    }

    public UploadImageView(Context context, AttributeSet attributeSet) {
        this(context);
    }

    public UploadImageView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet);
    }

    public UploadImageView(Context context, AttributeSet attributeSet, int i, int i2) {
        this(context, attributeSet, i);
    }

    public void setBitmap(Bitmap bitmap) {
        this.mImageView.setImageBitmap(bitmap);
    }

    public void setState(int i) {
        this.state = i;
        switch (i) {
            case 0:
                this.mProgressBar.setVisibility(8);
                this.mTextView.setVisibility(0);
                this.mTextView.setText(getResources().getString(R.string.upload_waiting_tips));
                return;
            case 1:
                this.mProgressBar.setVisibility(0);
                this.mTextView.setVisibility(8);
                return;
            case 2:
                this.mProgressBar.setVisibility(8);
                this.mTextView.setVisibility(0);
                this.mTextView.setText(getResources().getString(R.string.upload_fail_tips));
                return;
            case 3:
                this.mProgressBar.setVisibility(8);
                this.mTextView.setVisibility(8);
                return;
            default:
                return;
        }
    }

    public void setRetryUploadListener(RetryUploadListener retryUploadListener2) {
        this.retryUploadListener = retryUploadListener2;
    }
}
