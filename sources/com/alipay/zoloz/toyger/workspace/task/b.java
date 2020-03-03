package com.alipay.zoloz.toyger.workspace.task;

import android.graphics.Color;

class b implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ CherryCaptureTask f1235a;

    b(CherryCaptureTask cherryCaptureTask) {
        this.f1235a = cherryCaptureTask;
    }

    public void run() {
        this.f1235a.mToygerCirclePattern.getTitleBar().setSoundButton(8);
        this.f1235a.mToygerCirclePattern.getCircleUploadPattern().setBackgroundColor(Color.parseColor("#DA000000"));
        this.f1235a.mToygerCirclePattern.getCircleUploadPattern().setVisibility(0);
        this.f1235a.mToygerCirclePattern.getTitleBar().setCloseButtonVisible(8);
        this.f1235a.mToygerCirclePattern.getTopTip().setVisibility(8);
        this.f1235a.mToygerCirclePattern.getOuterBakRoundProgressBar().setRoundColor(Color.parseColor("#414146"));
        this.f1235a.mToygerCirclePattern.getOuterBakRoundProgressBar().setCricleProgressColor(Color.parseColor("#414146"));
        this.f1235a.mToygerCirclePattern.getOuterBakRoundProgressBar().setVisibility(8);
    }
}
