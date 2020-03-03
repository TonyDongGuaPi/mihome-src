package com.alipay.zoloz.toyger.workspace.task;

import android.graphics.Color;

class a implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ CherryCaptureTask f1234a;

    a(CherryCaptureTask cherryCaptureTask) {
        this.f1234a = cherryCaptureTask;
    }

    public void run() {
        if (!this.f1234a.isDarkScreen) {
            this.f1234a.mToygerCirclePattern.getTitleBar().setSoundButton(8);
            this.f1234a.mToygerCirclePattern.getCircleUploadPattern().setVisibility(0);
            this.f1234a.mToygerCirclePattern.getTitleBar().setCloseButtonVisible(8);
            this.f1234a.mToygerCirclePattern.getTopTip().setVisibility(8);
            this.f1234a.mToygerCirclePattern.getOuterBakRoundProgressBar().setRoundColor(Color.parseColor("#414146"));
            this.f1234a.mToygerCirclePattern.getOuterBakRoundProgressBar().setCricleProgressColor(Color.parseColor("#414146"));
            this.f1234a.mToygerCirclePattern.getOuterBakRoundProgressBar().setVisibility(8);
            if (this.f1234a.mBestToygerFrame != null) {
                this.f1234a.showBestFrameBlur(this.f1234a.mBestToygerFrame.bestBitmap);
            }
        }
    }
}
