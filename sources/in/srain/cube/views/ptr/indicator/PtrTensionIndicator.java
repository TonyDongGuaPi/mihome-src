package in.srain.cube.views.ptr.indicator;

public class PtrTensionIndicator extends PtrIndicator {
    private float DRAG_RATE = 0.5f;
    private float mCurrentDragPercent;
    private float mDownPos;
    private float mDownY;
    private float mOneHeight = 0.0f;
    private float mReleasePercent = -1.0f;
    private int mReleasePos;

    public void onPressDown(float f, float f2) {
        super.onPressDown(f, f2);
        this.mDownY = f2;
        this.mDownPos = (float) getCurrentPosY();
    }

    public void onRelease() {
        super.onRelease();
        this.mReleasePos = getCurrentPosY();
        this.mReleasePercent = this.mCurrentDragPercent;
    }

    public void onUIRefreshComplete() {
        this.mReleasePos = getCurrentPosY();
        this.mReleasePercent = getOverDragPercent();
    }

    public void setHeaderHeight(int i) {
        super.setHeaderHeight(i);
        this.mOneHeight = (((float) i) * 4.0f) / 5.0f;
    }

    /* access modifiers changed from: protected */
    public void processOnMove(float f, float f2, float f3, float f4) {
        if (f2 < this.mDownY) {
            super.processOnMove(f, f2, f3, f4);
            return;
        }
        float f5 = ((f2 - this.mDownY) * this.DRAG_RATE) + this.mDownPos;
        float f6 = f5 / this.mOneHeight;
        if (f6 < 0.0f) {
            setOffset(f3, 0.0f);
            return;
        }
        this.mCurrentDragPercent = f6;
        float min = Math.min(1.0f, Math.abs(f6));
        double max = (double) (Math.max(0.0f, Math.min(f5 - this.mOneHeight, this.mOneHeight * 2.0f) / this.mOneHeight) / 4.0f);
        double pow = Math.pow(max, 2.0d);
        Double.isNaN(max);
        float f7 = this.mOneHeight;
        setOffset(f, (float) (((int) ((this.mOneHeight * min) + ((f7 * (((float) (max - pow)) * 2.0f)) / 2.0f))) - getCurrentPosY()));
    }

    private float offsetToTarget(float f) {
        float f2 = f / this.mOneHeight;
        this.mCurrentDragPercent = f2;
        Math.min(1.0f, Math.abs(f2));
        Math.pow((double) (Math.max(0.0f, Math.min(f - this.mOneHeight, this.mOneHeight * 2.0f) / this.mOneHeight) / 4.0f), 2.0d);
        float f3 = this.mOneHeight;
        float f4 = this.mOneHeight;
        return 0.0f;
    }

    public int getOffsetToKeepHeaderWhileLoading() {
        return getOffsetToRefresh();
    }

    public int getOffsetToRefresh() {
        return (int) this.mOneHeight;
    }

    public float getOverDragPercent() {
        if (isUnderTouch()) {
            return this.mCurrentDragPercent;
        }
        if (this.mReleasePercent <= 0.0f) {
            return (((float) getCurrentPosY()) * 1.0f) / ((float) getOffsetToKeepHeaderWhileLoading());
        }
        return (this.mReleasePercent * ((float) getCurrentPosY())) / ((float) this.mReleasePos);
    }
}
