package com.xiaomi.smarthome.camera.activity.local;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.mijia.model.local.LocalFileManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.XmVideoViewGl;
import com.xiaomi.smarthome.camera.activity.CameraBaseActivity;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;

public class LocalPicReviewActivity extends CameraBaseActivity {
    View mBottomViewContainer;
    /* access modifiers changed from: private */
    public boolean mFullScreen;
    ImageView mFullScreenView;
    LocalFileManager.LocalFile mLocalFile;
    LocalFileManager mLocalFileManager;
    /* access modifiers changed from: private */
    public int mRotation = 0;
    View mTopViewContainer;
    FrameLayout mVideoViewFrame;
    /* access modifiers changed from: private */
    public int mVideoViewFrameHeight;
    /* access modifiers changed from: private */
    public XmVideoViewGl xmVideoViewGl;

    public void doCreate(Bundle bundle) {
        super.doCreate(bundle);
        this.mLocalFileManager = this.mCameraDevice.b();
        this.mLocalFile = this.mLocalFileManager.b(getIntent().getStringExtra("file_path"));
        if (this.mLocalFile == null) {
            finish();
            return;
        }
        setContentView(R.layout.camera_activity_local_pic_review);
        ((TextView) findViewById(R.id.title_bar_title)).setText(this.mLocalFile.c.getName());
        findViewById(R.id.title_bar_return).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LocalPicReviewActivity.this.finish();
            }
        });
        findViewById(R.id.title_bar_more).setVisibility(8);
        this.mVideoViewFrame = (FrameLayout) findViewById(R.id.video_frame);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        FrameLayout frameLayout = new FrameLayout(activity());
        this.mVideoViewFrame.addView(frameLayout, 0, layoutParams);
        this.xmVideoViewGl = XmPluginHostApi.instance().createVideoView(activity(), frameLayout, false, 1);
        this.xmVideoViewGl.setMiniScale(true);
        findViewById(R.id.local_share).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LocalPicReviewActivity.this.openSharePictureActivity("", "", LocalPicReviewActivity.this.mLocalFile.c.getAbsolutePath());
            }
        });
        findViewById(R.id.local_delete).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MLAlertDialog.Builder builder = new MLAlertDialog.Builder(LocalPicReviewActivity.this.activity());
                builder.a((int) R.string.delete_title);
                builder.a((int) R.string.delete, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        LocalPicReviewActivity.this.mLocalFileManager.a(LocalPicReviewActivity.this.mLocalFile);
                        Toast.makeText(LocalPicReviewActivity.this.activity(), R.string.local_file_delete_success, 0).show();
                        LocalPicReviewActivity.this.finish();
                    }
                });
                builder.b((int) R.string.cancel, (DialogInterface.OnClickListener) null);
                builder.d();
            }
        });
        this.mTopViewContainer = findViewById(R.id.top_tools_container);
        findViewById(R.id.flip).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int unused = LocalPicReviewActivity.this.mRotation = LocalPicReviewActivity.this.mRotation + 90;
                int unused2 = LocalPicReviewActivity.this.mRotation = LocalPicReviewActivity.this.mRotation % 360;
                LocalPicReviewActivity.this.xmVideoViewGl.setRotation(LocalPicReviewActivity.this.mRotation);
            }
        });
        this.mFullScreenView = (ImageView) findViewById(R.id.full_screen);
        this.mFullScreenView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LocalPicReviewActivity.this.setFullScreen(!LocalPicReviewActivity.this.mFullScreen);
            }
        });
        this.mBottomViewContainer = findViewById(R.id.bottom_tools_container);
        this.xmVideoViewGl.setVideoViewListener(new XmVideoViewGl.IVideoViewListener() {
            public void onVideoViewClick() {
                if (LocalPicReviewActivity.this.mTopViewContainer.isShown()) {
                    Animation loadAnimation = AnimationUtils.loadAnimation(LocalPicReviewActivity.this.activity(), R.anim.slide_out_top);
                    loadAnimation.setFillAfter(true);
                    LocalPicReviewActivity.this.mTopViewContainer.startAnimation(loadAnimation);
                    return;
                }
                Animation loadAnimation2 = AnimationUtils.loadAnimation(LocalPicReviewActivity.this.activity(), R.anim.slide_in_top);
                loadAnimation2.setFillAfter(true);
                LocalPicReviewActivity.this.mTopViewContainer.startAnimation(loadAnimation2);
            }
        });
        setFullScreen(false);
        this.xmVideoViewGl.initial();
        Bitmap decodeFile = BitmapFactory.decodeFile(this.mLocalFile.c.getAbsolutePath());
        if (decodeFile != null) {
            this.xmVideoViewGl.setFirstBitmap(decodeFile);
        }
    }

    public void onStop() {
        super.onStop();
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.xmVideoViewGl != null) {
            this.xmVideoViewGl.release();
        }
    }

    public void onBackPressed() {
        if (this.mFullScreen) {
            runMainThread(new Runnable() {
                public void run() {
                    LocalPicReviewActivity.this.setFullScreen(false);
                }
            });
        } else {
            super.onBackPressed();
        }
    }

    /* access modifiers changed from: package-private */
    public void setFullScreen(boolean z) {
        this.mFullScreen = z;
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        if (i > i2) {
            i = displayMetrics.heightPixels;
            i2 = displayMetrics.widthPixels;
        }
        if (this.mFullScreen) {
            setRequestedOrientation(6);
            this.mFullScreenView.setImageResource(R.drawable.camera_icon_mixscreen_land);
            findViewById(R.id.title_bar).setVisibility(8);
            this.xmVideoViewGl.setVideoFrameSize(i2, i, true);
            this.mBottomViewContainer.setVisibility(8);
            this.mTopViewContainer.setPadding(0, getResources().getDimensionPixelOffset(R.dimen.full_screen_top_bar_padding), 0, 0);
            return;
        }
        this.mFullScreenView.setImageResource(R.drawable.camera_icon_fullscreen2);
        this.mTopViewContainer.setPadding(0, 0, 0, 0);
        if (this.mVideoViewFrameHeight == 0) {
            this.mHandler.postDelayed(new Runnable() {
                public void run() {
                    int unused = LocalPicReviewActivity.this.mVideoViewFrameHeight = LocalPicReviewActivity.this.mVideoViewFrame.getHeight();
                    LocalPicReviewActivity.this.setFullScreen(LocalPicReviewActivity.this.mFullScreen);
                }
            }, 300);
            return;
        }
        setRequestedOrientation(1);
        findViewById(R.id.title_bar).setVisibility(0);
        this.xmVideoViewGl.setVideoFrameSize(i, this.mVideoViewFrameHeight, false);
        this.mBottomViewContainer.setVisibility(0);
    }

    public void onResume() {
        super.onResume();
    }
}
