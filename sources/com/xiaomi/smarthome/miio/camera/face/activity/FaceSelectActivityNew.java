package com.xiaomi.smarthome.miio.camera.face.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.xiaomi.smarthome.R;

public abstract class FaceSelectActivityNew extends FaceManagerBaseActivity {
    private boolean isAllSelected;
    private boolean isEditMode;
    private boolean isInit;
    private ImageView mBackView;
    private ImageView mMoreView;
    private View mSelectBottom;
    private TextView mTitleView;
    private Vibrator mVibrator;
    View.OnClickListener onClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            int id = view.getId();
            if (id == R.id.title_bar_more) {
                FaceSelectActivityNew.this.onClickMore();
            } else if (id == R.id.title_bar_return) {
                FaceSelectActivityNew.this.handleBackPressed();
            }
        }
    };

    /* access modifiers changed from: protected */
    public abstract String getPageTitle();

    /* access modifiers changed from: protected */
    public abstract int getSelectCount();

    /* access modifiers changed from: protected */
    public abstract boolean isAllSelected();

    /* access modifiers changed from: protected */
    public abstract void onEnterSelectMode();

    /* access modifiers changed from: protected */
    public abstract void onExitSelectMode();

    /* access modifiers changed from: protected */
    public abstract void onSelectAll();

    /* access modifiers changed from: protected */
    public abstract void onUnSelectAll();

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mVibrator = (Vibrator) getSystemService("vibrator");
    }

    public void onResume() {
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void initSelectView() {
        this.mMoreView = (ImageView) findViewById(R.id.title_bar_more);
        this.mTitleView = (TextView) findViewById(R.id.title_bar_title);
        this.mBackView = (ImageView) findViewById(R.id.title_bar_return);
        this.mSelectBottom = findViewById(R.id.layout_select_bottom);
        this.mBackView.setOnClickListener(this.onClickListener);
        this.mMoreView.setOnClickListener(this.onClickListener);
        this.isInit = true;
        exitSelectMode();
    }

    /* access modifiers changed from: private */
    public void onClickMore() {
        if (!this.isEditMode) {
            enterSelectMode();
        } else if (!this.isAllSelected) {
            selectAll();
        } else {
            unSelectAll();
        }
    }

    public void refreshSelectTitle() {
        if (isAllSelected()) {
            this.isAllSelected = true;
            hightLightImageView(this.mMoreView);
        } else {
            this.isAllSelected = false;
            resetImageView(this.mMoreView);
        }
        onRefreshSelectTitle();
    }

    private void onRefreshSelectTitle() {
        int selectCount = getSelectCount();
        if (!this.isEditMode) {
            this.mTitleView.setText(getPageTitle());
        } else if (selectCount == 0) {
            this.mTitleView.setText(R.string.select_title_1);
        } else {
            this.mTitleView.setText(getString(R.string.select_title_2, new Object[]{Integer.valueOf(selectCount)}));
        }
    }

    public boolean handleBackPressed() {
        if (!this.isEditMode) {
            return false;
        }
        exitSelectMode();
        return true;
    }

    public void enterSelectMode() {
        this.isEditMode = true;
        this.mBackView.setImageResource(R.drawable.icon_face_cancle);
        this.mMoreView.setImageResource(R.drawable.icon_select_all);
        onEnterSelectMode();
        refreshSelectTitle();
        this.mSelectBottom.setVisibility(0);
    }

    public void exitSelectMode() {
        this.isAllSelected = false;
        resetImageView(this.mMoreView);
        this.isEditMode = false;
        this.mBackView.setImageResource(R.drawable.icon_face_back);
        this.mMoreView.setImageResource(R.drawable.icon_common_edit);
        if (!this.isInit) {
            onExitSelectMode();
            refreshSelectTitle();
        }
        if (this.isInit) {
            this.isInit = false;
        }
        this.mSelectBottom.setVisibility(8);
    }

    private void selectAll() {
        this.isAllSelected = true;
        hightLightImageView(this.mMoreView);
        onSelectAll();
        onRefreshSelectTitle();
    }

    private void unSelectAll() {
        this.isAllSelected = false;
        resetImageView(this.mMoreView);
        onUnSelectAll();
        onRefreshSelectTitle();
    }

    private void hightLightImageView(ImageView imageView) {
        imageView.setColorFilter(Color.parseColor("#32BAC0"));
    }

    private void resetImageView(ImageView imageView) {
        imageView.clearColorFilter();
    }
}
