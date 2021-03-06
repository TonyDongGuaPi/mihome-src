package com.scwang.smartrefresh.layout.constant;

public enum RefreshState {
    None(0, false, false, false),
    PullDownToRefresh(1, true, false, false),
    PullUpToLoad(2, true, false, false),
    PullDownCanceled(1, false, false, false),
    PullUpCanceled(2, false, false, false),
    ReleaseToRefresh(1, true, false, false),
    ReleaseToLoad(2, true, false, false),
    ReleaseToTwoLevel(1, true, false, false),
    TwoLevelReleased(1, false, false, false),
    RefreshReleased(1, false, false, false),
    LoadReleased(2, false, false, false),
    Refreshing(1, false, true, false),
    Loading(2, false, true, false),
    TwoLevel(1, false, true, false),
    RefreshFinish(1, false, false, true),
    LoadFinish(2, false, false, true),
    TwoLevelFinish(1, false, false, true);
    
    public final boolean isDragging;
    public final boolean isFinishing;
    public final boolean isFooter;
    public final boolean isHeader;
    public final boolean isOpening;

    private RefreshState(int i, boolean z, boolean z2, boolean z3) {
        boolean z4 = false;
        this.isHeader = i == 1;
        this.isFooter = i == 2 ? true : z4;
        this.isDragging = z;
        this.isOpening = z2;
        this.isFinishing = z3;
    }
}
