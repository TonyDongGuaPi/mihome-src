package com.taobao.weex.yp_compoment.pullrefresh.youpinptr;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;
import android.widget.TextView;
import com.taobao.weex.yp_compoment.pullrefresh.SmartHomePtrHeader;
import com.taobao.weex.yp_compoment.pullrefresh.youpinptr.indicator.PtrIndicator;
import com.taobao.weex.yp_compoment.pullrefresh.youpinptr.utils.PtrCLog;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class PtrFrameLayout extends ViewGroup {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    public static boolean DEBUG = false;
    private static final boolean DEBUG_LAYOUT = true;
    private static byte FLAG_AUTO_REFRESH_AT_ONCE = 1;
    private static byte FLAG_AUTO_REFRESH_BUT_LATER = 2;
    private static byte FLAG_ENABLE_NEXT_PTR_AT_ONCE = 4;
    private static byte FLAG_PIN_CONTENT = 8;
    private static int ID = 1;
    private static byte MASK_AUTO_REFRESH = 3;
    public static final byte PTR_STATUS_COMPLETE = 4;
    public static final byte PTR_STATUS_INIT = 1;
    public static final byte PTR_STATUS_LOADING = 3;
    public static final byte PTR_STATUS_PREPARE = 2;
    protected final String LOG_TAG;
    private int mContainerId;
    protected View mContent;
    private boolean mDisableWhenHorizontalMove;
    private int mDurationToClose;
    private int mDurationToCloseHeader;
    private int mFlag;
    private boolean mHasSendCancelEvent;
    private int mHeaderHeight;
    private int mHeaderId;
    private View mHeaderView;
    private boolean mKeepHeaderWhenRefresh;
    private MotionEvent mLastMoveEvent;
    private int mLoadingMinTime;
    private long mLoadingStartTime;
    private int mPagingTouchSlop;
    private Runnable mPerformRefreshCompleteDelay;
    private boolean mPreventForHorizontal;
    private PtrHandler mPtrHandler;
    private PtrIndicator mPtrIndicator;
    private PtrUIHandlerHolder mPtrUIHandlerHolder;
    private boolean mPullToRefresh;
    private PtrUIHandlerHook mRefreshCompleteHook;
    private ScrollChecker mScrollChecker;
    private byte mStatus;
    SmartHomePtrHeader smartHomePtrHeader;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(3752000419121717664L, "com/taobao/weex/yp_compoment/pullrefresh/youpinptr/PtrFrameLayout", 443);
        $jacocoData = a2;
        return a2;
    }

    static /* synthetic */ void access$000(PtrFrameLayout ptrFrameLayout) {
        boolean[] $jacocoInit = $jacocoInit();
        ptrFrameLayout.performRefreshComplete();
        $jacocoInit[438] = true;
    }

    static /* synthetic */ void access$200(PtrFrameLayout ptrFrameLayout, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        ptrFrameLayout.notifyUIRefreshComplete(z);
        $jacocoInit[439] = true;
    }

    static /* synthetic */ PtrIndicator access$400(PtrFrameLayout ptrFrameLayout) {
        boolean[] $jacocoInit = $jacocoInit();
        PtrIndicator ptrIndicator = ptrFrameLayout.mPtrIndicator;
        $jacocoInit[440] = true;
        return ptrIndicator;
    }

    static /* synthetic */ void access$500(PtrFrameLayout ptrFrameLayout, float f) {
        boolean[] $jacocoInit = $jacocoInit();
        ptrFrameLayout.movePos(f);
        $jacocoInit[441] = true;
    }

    static {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[442] = true;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public PtrFrameLayout(Context context) {
        this(context, (AttributeSet) null);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public PtrFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[1] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PtrFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        boolean[] $jacocoInit = $jacocoInit();
        this.mStatus = 1;
        $jacocoInit[2] = true;
        StringBuilder sb = new StringBuilder();
        sb.append("ptr-frame-");
        int i2 = ID + 1;
        ID = i2;
        sb.append(i2);
        this.LOG_TAG = sb.toString();
        this.mHeaderId = 0;
        this.mContainerId = 0;
        this.mDurationToClose = 200;
        this.mDurationToCloseHeader = 1000;
        this.mKeepHeaderWhenRefresh = true;
        this.mPullToRefresh = false;
        $jacocoInit[3] = true;
        this.mPtrUIHandlerHolder = PtrUIHandlerHolder.create();
        this.mDisableWhenHorizontalMove = false;
        this.mFlag = 0;
        this.mPreventForHorizontal = false;
        this.mLoadingMinTime = 500;
        this.mLoadingStartTime = 0;
        this.mHasSendCancelEvent = false;
        $jacocoInit[4] = true;
        this.mPerformRefreshCompleteDelay = new Runnable(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ PtrFrameLayout this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(2715811019573037521L, "com/taobao/weex/yp_compoment/pullrefresh/youpinptr/PtrFrameLayout$1", 2);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r3;
                $jacocoInit[0] = true;
            }

            public void run() {
                boolean[] $jacocoInit = $jacocoInit();
                PtrFrameLayout.access$000(this.this$0);
                $jacocoInit[1] = true;
            }
        };
        $jacocoInit[5] = true;
        this.mPtrIndicator = new PtrIndicator();
        $jacocoInit[6] = true;
        this.mScrollChecker = new ScrollChecker(this);
        $jacocoInit[7] = true;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        $jacocoInit[8] = true;
        this.mPagingTouchSlop = viewConfiguration.getScaledTouchSlop() * 2;
        $jacocoInit[9] = true;
    }

    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        boolean[] $jacocoInit = $jacocoInit();
        super.addView(view, i, layoutParams);
        $jacocoInit[10] = true;
        config();
        $jacocoInit[11] = true;
    }

    public void config() {
        boolean[] $jacocoInit = $jacocoInit();
        int childCount = getChildCount();
        if (childCount > 2) {
            $jacocoInit[12] = true;
            return;
        }
        if (childCount == 2) {
            if (this.mHeaderId == 0) {
                $jacocoInit[13] = true;
            } else if (this.mHeaderView != null) {
                $jacocoInit[14] = true;
            } else {
                $jacocoInit[15] = true;
                this.mHeaderView = findViewById(this.mHeaderId);
                $jacocoInit[16] = true;
            }
            if (this.mContainerId == 0) {
                $jacocoInit[17] = true;
            } else if (this.mContent != null) {
                $jacocoInit[18] = true;
            } else {
                $jacocoInit[19] = true;
                this.mContent = findViewById(this.mContainerId);
                $jacocoInit[20] = true;
            }
            if (this.mContent == null) {
                $jacocoInit[21] = true;
            } else if (this.mHeaderView != null) {
                $jacocoInit[22] = true;
            } else {
                $jacocoInit[23] = true;
            }
            View childAt = getChildAt(0);
            $jacocoInit[24] = true;
            View childAt2 = getChildAt(1);
            if (childAt instanceof PtrUIHandler) {
                this.mHeaderView = childAt;
                this.mContent = childAt2;
                $jacocoInit[25] = true;
            } else if (childAt2 instanceof PtrUIHandler) {
                this.mHeaderView = childAt2;
                this.mContent = childAt;
                $jacocoInit[26] = true;
            } else {
                if (this.mContent != null) {
                    $jacocoInit[27] = true;
                } else if (this.mHeaderView != null) {
                    $jacocoInit[28] = true;
                } else {
                    this.mHeaderView = childAt;
                    this.mContent = childAt2;
                    $jacocoInit[29] = true;
                }
                if (this.mHeaderView == null) {
                    if (this.mContent == childAt) {
                        $jacocoInit[30] = true;
                        childAt = childAt2;
                    } else {
                        $jacocoInit[31] = true;
                    }
                    this.mHeaderView = childAt;
                    $jacocoInit[32] = true;
                } else {
                    if (this.mHeaderView == childAt) {
                        $jacocoInit[33] = true;
                        childAt = childAt2;
                    } else {
                        $jacocoInit[34] = true;
                    }
                    this.mContent = childAt;
                    $jacocoInit[35] = true;
                }
            }
            $jacocoInit[36] = true;
        } else if (childCount == 1) {
            $jacocoInit[37] = true;
        } else {
            TextView textView = new TextView(getContext());
            $jacocoInit[38] = true;
            textView.setClickable(true);
            $jacocoInit[39] = true;
            textView.setTextColor(-39424);
            $jacocoInit[40] = true;
            textView.setGravity(17);
            $jacocoInit[41] = true;
            textView.setTextSize(20.0f);
            $jacocoInit[42] = true;
            textView.setText("The content view in PtrFrameLayout is empty. Do you forget to specify its id in xml layout file?");
            this.mContent = textView;
            $jacocoInit[43] = true;
            addView(this.mContent);
            $jacocoInit[44] = true;
        }
        if (this.mHeaderView == null) {
            $jacocoInit[45] = true;
        } else {
            $jacocoInit[46] = true;
            this.mHeaderView.bringToFront();
            $jacocoInit[47] = true;
        }
        $jacocoInit[48] = true;
    }

    public void setContentView(View view) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mContent == null) {
            $jacocoInit[49] = true;
        } else if (view == null) {
            $jacocoInit[50] = true;
        } else if (this.mContent == view) {
            $jacocoInit[51] = true;
        } else {
            $jacocoInit[52] = true;
            removeView(this.mContent);
            $jacocoInit[53] = true;
        }
        this.mContent = view;
        $jacocoInit[54] = true;
        addView(view);
        $jacocoInit[55] = true;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        boolean[] $jacocoInit = $jacocoInit();
        super.onDetachedFromWindow();
        if (this.mScrollChecker == null) {
            $jacocoInit[56] = true;
        } else {
            $jacocoInit[57] = true;
            ScrollChecker.access$100(this.mScrollChecker);
            $jacocoInit[58] = true;
        }
        if (this.mPerformRefreshCompleteDelay == null) {
            $jacocoInit[59] = true;
        } else {
            $jacocoInit[60] = true;
            removeCallbacks(this.mPerformRefreshCompleteDelay);
            $jacocoInit[61] = true;
        }
        $jacocoInit[62] = true;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        boolean[] $jacocoInit = $jacocoInit();
        super.onMeasure(i, i2);
        if (!DEBUG) {
            $jacocoInit[63] = true;
        } else {
            String str = this.LOG_TAG;
            $jacocoInit[64] = true;
            $jacocoInit[65] = true;
            Object[] objArr = {Integer.valueOf(getMeasuredHeight()), Integer.valueOf(getMeasuredWidth()), Integer.valueOf(getPaddingLeft()), Integer.valueOf(getPaddingRight()), Integer.valueOf(getPaddingTop()), Integer.valueOf(getPaddingBottom())};
            $jacocoInit[66] = true;
            PtrCLog.d(str, "onMeasure frame: width: %s, height: %s, padding: %s %s %s %s", objArr);
            $jacocoInit[67] = true;
        }
        if (this.mHeaderView == null) {
            $jacocoInit[68] = true;
        } else {
            $jacocoInit[69] = true;
            measureChildWithMargins(this.mHeaderView, i, 0, i2, 0);
            $jacocoInit[70] = true;
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mHeaderView.getLayoutParams();
            $jacocoInit[71] = true;
            this.mHeaderHeight = this.mHeaderView.getMeasuredHeight() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
            $jacocoInit[72] = true;
            this.mPtrIndicator.setHeaderHeight(this.mHeaderHeight);
            $jacocoInit[73] = true;
        }
        if (this.mContent == null) {
            $jacocoInit[74] = true;
        } else {
            $jacocoInit[75] = true;
            measureContentView(this.mContent, i, i2);
            if (!DEBUG) {
                $jacocoInit[76] = true;
            } else {
                $jacocoInit[77] = true;
                ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) this.mContent.getLayoutParams();
                String str2 = this.LOG_TAG;
                $jacocoInit[78] = true;
                int i3 = marginLayoutParams2.leftMargin;
                $jacocoInit[79] = true;
                Object[] objArr2 = {Integer.valueOf(getMeasuredWidth()), Integer.valueOf(getMeasuredHeight()), Integer.valueOf(i3), Integer.valueOf(marginLayoutParams2.topMargin), Integer.valueOf(marginLayoutParams2.rightMargin), Integer.valueOf(marginLayoutParams2.bottomMargin)};
                $jacocoInit[80] = true;
                PtrCLog.d(str2, "onMeasure content, width: %s, height: %s, margin: %s %s %s %s", objArr2);
                String str3 = this.LOG_TAG;
                PtrIndicator ptrIndicator = this.mPtrIndicator;
                $jacocoInit[81] = true;
                Object[] objArr3 = {Integer.valueOf(ptrIndicator.getCurrentPosY()), Integer.valueOf(this.mPtrIndicator.getLastPosY()), Integer.valueOf(this.mContent.getTop())};
                $jacocoInit[82] = true;
                PtrCLog.d(str3, "onMeasure, currentPos: %s, lastPos: %s, top: %s", objArr3);
                $jacocoInit[83] = true;
            }
        }
        $jacocoInit[84] = true;
    }

    private void measureContentView(View view, int i, int i2) {
        boolean[] $jacocoInit = $jacocoInit();
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        $jacocoInit[85] = true;
        int paddingLeft = getPaddingLeft() + getPaddingRight() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
        int i3 = marginLayoutParams.width;
        $jacocoInit[86] = true;
        int childMeasureSpec = getChildMeasureSpec(i, paddingLeft, i3);
        $jacocoInit[87] = true;
        int i4 = marginLayoutParams.height;
        $jacocoInit[88] = true;
        int childMeasureSpec2 = getChildMeasureSpec(i2, getPaddingTop() + getPaddingBottom() + marginLayoutParams.topMargin, i4);
        $jacocoInit[89] = true;
        view.measure(childMeasureSpec, childMeasureSpec2);
        $jacocoInit[90] = true;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        boolean[] $jacocoInit = $jacocoInit();
        layoutChildren();
        $jacocoInit[91] = true;
    }

    private void layoutChildren() {
        boolean[] $jacocoInit = $jacocoInit();
        int currentPosY = this.mPtrIndicator.getCurrentPosY();
        $jacocoInit[92] = true;
        int paddingLeft = getPaddingLeft();
        $jacocoInit[93] = true;
        int paddingTop = getPaddingTop();
        if (this.mHeaderView == null) {
            $jacocoInit[94] = true;
        } else {
            $jacocoInit[95] = true;
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mHeaderView.getLayoutParams();
            int i = marginLayoutParams.leftMargin + paddingLeft;
            int i2 = ((marginLayoutParams.topMargin + paddingTop) + currentPosY) - this.mHeaderHeight;
            $jacocoInit[96] = true;
            int measuredWidth = this.mHeaderView.getMeasuredWidth() + i;
            $jacocoInit[97] = true;
            int measuredHeight = this.mHeaderView.getMeasuredHeight() + i2;
            $jacocoInit[98] = true;
            this.mHeaderView.layout(i, i2, measuredWidth, measuredHeight);
            if (!DEBUG) {
                $jacocoInit[99] = true;
            } else {
                $jacocoInit[100] = true;
                PtrCLog.d(this.LOG_TAG, "onLayout header: %s %s %s %s", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(measuredWidth), Integer.valueOf(measuredHeight));
                $jacocoInit[101] = true;
            }
        }
        if (this.mContent == null) {
            $jacocoInit[102] = true;
        } else {
            $jacocoInit[103] = true;
            if (!isPinContent()) {
                $jacocoInit[104] = true;
            } else {
                $jacocoInit[105] = true;
                currentPosY = 0;
            }
            ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) this.mContent.getLayoutParams();
            int i3 = paddingLeft + marginLayoutParams2.leftMargin;
            int i4 = paddingTop + marginLayoutParams2.topMargin + currentPosY;
            $jacocoInit[106] = true;
            int measuredWidth2 = this.mContent.getMeasuredWidth() + i3;
            $jacocoInit[107] = true;
            int measuredHeight2 = this.mContent.getMeasuredHeight() + i4;
            if (!DEBUG) {
                $jacocoInit[108] = true;
            } else {
                $jacocoInit[109] = true;
                PtrCLog.d(this.LOG_TAG, "onLayout content: %s %s %s %s", Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(measuredWidth2), Integer.valueOf(measuredHeight2));
                $jacocoInit[110] = true;
            }
            this.mContent.layout(i3, i4, measuredWidth2, measuredHeight2);
            $jacocoInit[111] = true;
        }
        $jacocoInit[112] = true;
    }

    public boolean dispatchTouchEventSupper(MotionEvent motionEvent) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean dispatchTouchEvent = super.dispatchTouchEvent(motionEvent);
        $jacocoInit[113] = true;
        return dispatchTouchEvent;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean[] $jacocoInit = $jacocoInit();
        if (!isEnabled()) {
            $jacocoInit[114] = true;
        } else if (this.mContent == null) {
            $jacocoInit[115] = true;
        } else if (this.mHeaderView == null) {
            $jacocoInit[116] = true;
        } else {
            switch (motionEvent.getAction()) {
                case 0:
                    this.mHasSendCancelEvent = false;
                    $jacocoInit[128] = true;
                    this.mPtrIndicator.onPressDown(motionEvent.getX(), motionEvent.getY());
                    $jacocoInit[129] = true;
                    this.mScrollChecker.abortIfWorking();
                    this.mPreventForHorizontal = false;
                    $jacocoInit[130] = true;
                    dispatchTouchEventSupper(motionEvent);
                    $jacocoInit[131] = true;
                    return true;
                case 1:
                case 3:
                    this.mPtrIndicator.onRelease();
                    $jacocoInit[119] = true;
                    if (this.mPtrIndicator.hasLeftStartPosition()) {
                        if (!DEBUG) {
                            $jacocoInit[120] = true;
                        } else {
                            $jacocoInit[121] = true;
                            PtrCLog.d(this.LOG_TAG, "call onRelease when user release");
                            $jacocoInit[122] = true;
                        }
                        onRelease(false);
                        $jacocoInit[123] = true;
                        if (this.mPtrIndicator.hasMovedAfterPressedDown()) {
                            $jacocoInit[124] = true;
                            sendCancelEvent();
                            $jacocoInit[125] = true;
                            return true;
                        }
                        boolean dispatchTouchEventSupper = dispatchTouchEventSupper(motionEvent);
                        $jacocoInit[126] = true;
                        return dispatchTouchEventSupper;
                    }
                    boolean dispatchTouchEventSupper2 = dispatchTouchEventSupper(motionEvent);
                    $jacocoInit[127] = true;
                    return dispatchTouchEventSupper2;
                case 2:
                    this.mLastMoveEvent = motionEvent;
                    $jacocoInit[132] = true;
                    this.mPtrIndicator.onMove(motionEvent.getX(), motionEvent.getY());
                    $jacocoInit[133] = true;
                    float offsetX = this.mPtrIndicator.getOffsetX();
                    $jacocoInit[134] = true;
                    float offsetY = this.mPtrIndicator.getOffsetY();
                    $jacocoInit[135] = true;
                    if (!this.mDisableWhenHorizontalMove) {
                        $jacocoInit[136] = true;
                    } else if (this.mPreventForHorizontal) {
                        $jacocoInit[137] = true;
                    } else if (Math.abs(offsetX) <= ((float) this.mPagingTouchSlop)) {
                        $jacocoInit[138] = true;
                    } else if (Math.abs(offsetX) <= Math.abs(offsetY)) {
                        $jacocoInit[139] = true;
                    } else {
                        $jacocoInit[140] = true;
                        if (!this.mPtrIndicator.isInStartPosition()) {
                            $jacocoInit[141] = true;
                        } else {
                            this.mPreventForHorizontal = true;
                            $jacocoInit[142] = true;
                        }
                    }
                    if (this.mPreventForHorizontal) {
                        $jacocoInit[143] = true;
                        boolean dispatchTouchEventSupper3 = dispatchTouchEventSupper(motionEvent);
                        $jacocoInit[144] = true;
                        return dispatchTouchEventSupper3;
                    }
                    if (offsetY > 0.0f) {
                        $jacocoInit[145] = true;
                        z = true;
                    } else {
                        $jacocoInit[146] = true;
                        z = false;
                    }
                    if (!z) {
                        $jacocoInit[147] = true;
                        z2 = true;
                    } else {
                        $jacocoInit[148] = true;
                        z2 = false;
                    }
                    $jacocoInit[149] = true;
                    boolean hasLeftStartPosition = this.mPtrIndicator.hasLeftStartPosition();
                    if (!DEBUG) {
                        $jacocoInit[150] = true;
                    } else {
                        $jacocoInit[151] = true;
                        if (this.mPtrHandler == null) {
                            $jacocoInit[152] = true;
                        } else if (!this.mPtrHandler.checkCanDoRefresh(this, this.mContent, this.mHeaderView)) {
                            $jacocoInit[153] = true;
                        } else {
                            $jacocoInit[154] = true;
                            z3 = true;
                            $jacocoInit[156] = true;
                            PtrCLog.v(this.LOG_TAG, "ACTION_MOVE: offsetY:%s, currentPos: %s, moveUp: %s, canMoveUp: %s, moveDown: %s: canMoveDown: %s", Float.valueOf(offsetY), Integer.valueOf(this.mPtrIndicator.getCurrentPosY()), Boolean.valueOf(z2), Boolean.valueOf(hasLeftStartPosition), Boolean.valueOf(z), Boolean.valueOf(z3));
                            $jacocoInit[157] = true;
                        }
                        $jacocoInit[155] = true;
                        z3 = false;
                        $jacocoInit[156] = true;
                        PtrCLog.v(this.LOG_TAG, "ACTION_MOVE: offsetY:%s, currentPos: %s, moveUp: %s, canMoveUp: %s, moveDown: %s: canMoveDown: %s", Float.valueOf(offsetY), Integer.valueOf(this.mPtrIndicator.getCurrentPosY()), Boolean.valueOf(z2), Boolean.valueOf(hasLeftStartPosition), Boolean.valueOf(z), Boolean.valueOf(z3));
                        $jacocoInit[157] = true;
                    }
                    if (!z) {
                        $jacocoInit[158] = true;
                    } else if (this.mPtrHandler == null) {
                        $jacocoInit[159] = true;
                    } else if (this.mPtrHandler.checkCanDoRefresh(this, this.mContent, this.mHeaderView)) {
                        $jacocoInit[160] = true;
                    } else {
                        $jacocoInit[161] = true;
                        boolean dispatchTouchEventSupper4 = dispatchTouchEventSupper(motionEvent);
                        $jacocoInit[162] = true;
                        return dispatchTouchEventSupper4;
                    }
                    if (!z2) {
                        $jacocoInit[163] = true;
                    } else if (hasLeftStartPosition) {
                        $jacocoInit[164] = true;
                        movePos(offsetY);
                        $jacocoInit[168] = true;
                        return true;
                    } else {
                        $jacocoInit[165] = true;
                    }
                    if (!z) {
                        $jacocoInit[166] = true;
                        break;
                    } else {
                        $jacocoInit[167] = true;
                        movePos(offsetY);
                        $jacocoInit[168] = true;
                        return true;
                    }
                default:
                    $jacocoInit[118] = true;
                    break;
            }
            boolean dispatchTouchEventSupper5 = dispatchTouchEventSupper(motionEvent);
            $jacocoInit[169] = true;
            return dispatchTouchEventSupper5;
        }
        boolean dispatchTouchEventSupper6 = dispatchTouchEventSupper(motionEvent);
        $jacocoInit[117] = true;
        return dispatchTouchEventSupper6;
    }

    private void movePos(float f) {
        boolean[] $jacocoInit = $jacocoInit();
        if (f >= 0.0f) {
            $jacocoInit[170] = true;
        } else if (!this.mPtrIndicator.isInStartPosition()) {
            $jacocoInit[171] = true;
        } else {
            if (!DEBUG) {
                $jacocoInit[172] = true;
            } else {
                $jacocoInit[173] = true;
                PtrCLog.e(this.LOG_TAG, String.format("has reached the top", new Object[0]));
                $jacocoInit[174] = true;
            }
            $jacocoInit[175] = true;
            return;
        }
        int currentPosY = ((int) f) + this.mPtrIndicator.getCurrentPosY();
        $jacocoInit[176] = true;
        if (!this.mPtrIndicator.willOverTop(currentPosY)) {
            $jacocoInit[177] = true;
        } else {
            if (!DEBUG) {
                $jacocoInit[178] = true;
            } else {
                $jacocoInit[179] = true;
                PtrCLog.e(this.LOG_TAG, String.format("over top", new Object[0]));
                $jacocoInit[180] = true;
            }
            $jacocoInit[181] = true;
            currentPosY = 0;
        }
        this.mPtrIndicator.setCurrentPos(currentPosY);
        $jacocoInit[182] = true;
        $jacocoInit[183] = true;
        updatePos(currentPosY - this.mPtrIndicator.getLastPosY());
        $jacocoInit[184] = true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x0095  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x009a  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00bb  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00c0  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00dd  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00e2  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0144  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0149  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x01a1  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x01a6  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x01c2  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x01c7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void updatePos(int r11) {
        /*
            r10 = this;
            boolean[] r0 = $jacocoInit()
            r1 = 1
            if (r11 != 0) goto L_0x000c
            r11 = 185(0xb9, float:2.59E-43)
            r0[r11] = r1
            return
        L_0x000c:
            com.taobao.weex.yp_compoment.pullrefresh.youpinptr.indicator.PtrIndicator r2 = r10.mPtrIndicator
            boolean r2 = r2.isUnderTouch()
            r3 = 186(0xba, float:2.6E-43)
            r0[r3] = r1
            if (r2 != 0) goto L_0x001d
            r3 = 187(0xbb, float:2.62E-43)
            r0[r3] = r1
            goto L_0x0040
        L_0x001d:
            boolean r3 = r10.mHasSendCancelEvent
            if (r3 == 0) goto L_0x0026
            r3 = 188(0xbc, float:2.63E-43)
            r0[r3] = r1
            goto L_0x0040
        L_0x0026:
            com.taobao.weex.yp_compoment.pullrefresh.youpinptr.indicator.PtrIndicator r3 = r10.mPtrIndicator
            boolean r3 = r3.hasMovedAfterPressedDown()
            if (r3 != 0) goto L_0x0033
            r3 = 189(0xbd, float:2.65E-43)
            r0[r3] = r1
            goto L_0x0040
        L_0x0033:
            r10.mHasSendCancelEvent = r1
            r3 = 190(0xbe, float:2.66E-43)
            r0[r3] = r1
            r10.sendCancelEvent()
            r3 = 191(0xbf, float:2.68E-43)
            r0[r3] = r1
        L_0x0040:
            com.taobao.weex.yp_compoment.pullrefresh.youpinptr.indicator.PtrIndicator r3 = r10.mPtrIndicator
            boolean r3 = r3.hasJustLeftStartPosition()
            r4 = 0
            r5 = 4
            r6 = 2
            if (r3 != 0) goto L_0x0050
            r3 = 192(0xc0, float:2.69E-43)
            r0[r3] = r1
            goto L_0x005d
        L_0x0050:
            byte r3 = r10.mStatus
            if (r3 != r1) goto L_0x0059
            r3 = 193(0xc1, float:2.7E-43)
            r0[r3] = r1
            goto L_0x0086
        L_0x0059:
            r3 = 194(0xc2, float:2.72E-43)
            r0[r3] = r1
        L_0x005d:
            com.taobao.weex.yp_compoment.pullrefresh.youpinptr.indicator.PtrIndicator r3 = r10.mPtrIndicator
            r7 = 195(0xc3, float:2.73E-43)
            r0[r7] = r1
            boolean r3 = r3.goDownCrossFinishPosition()
            if (r3 != 0) goto L_0x006e
            r3 = 196(0xc4, float:2.75E-43)
            r0[r3] = r1
            goto L_0x00b3
        L_0x006e:
            byte r3 = r10.mStatus
            if (r3 == r5) goto L_0x0077
            r3 = 197(0xc5, float:2.76E-43)
            r0[r3] = r1
            goto L_0x00b3
        L_0x0077:
            boolean r3 = r10.isEnabledNextPtrAtOnce()
            if (r3 != 0) goto L_0x0082
            r3 = 198(0xc6, float:2.77E-43)
            r0[r3] = r1
            goto L_0x00b3
        L_0x0082:
            r3 = 199(0xc7, float:2.79E-43)
            r0[r3] = r1
        L_0x0086:
            r10.mStatus = r6
            r3 = 200(0xc8, float:2.8E-43)
            r0[r3] = r1
            com.taobao.weex.yp_compoment.pullrefresh.youpinptr.PtrUIHandlerHolder r3 = r10.mPtrUIHandlerHolder
            r3.onUIRefreshPrepare(r10)
            boolean r3 = DEBUG
            if (r3 != 0) goto L_0x009a
            r3 = 201(0xc9, float:2.82E-43)
            r0[r3] = r1
            goto L_0x00b3
        L_0x009a:
            r3 = 202(0xca, float:2.83E-43)
            r0[r3] = r1
            java.lang.String r3 = r10.LOG_TAG
            java.lang.String r7 = "PtrUIHandler: onUIRefreshPrepare, mFlag %s"
            java.lang.Object[] r8 = new java.lang.Object[r1]
            int r9 = r10.mFlag
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            r8[r4] = r9
            com.taobao.weex.yp_compoment.pullrefresh.youpinptr.utils.PtrCLog.i((java.lang.String) r3, (java.lang.String) r7, (java.lang.Object[]) r8)
            r3 = 203(0xcb, float:2.84E-43)
            r0[r3] = r1
        L_0x00b3:
            com.taobao.weex.yp_compoment.pullrefresh.youpinptr.indicator.PtrIndicator r3 = r10.mPtrIndicator
            boolean r3 = r3.hasJustBackToStartPosition()
            if (r3 != 0) goto L_0x00c0
            r3 = 204(0xcc, float:2.86E-43)
            r0[r3] = r1
            goto L_0x00d9
        L_0x00c0:
            r3 = 205(0xcd, float:2.87E-43)
            r0[r3] = r1
            r10.tryToNotifyReset()
            if (r2 != 0) goto L_0x00ce
            r3 = 206(0xce, float:2.89E-43)
            r0[r3] = r1
            goto L_0x00d9
        L_0x00ce:
            r3 = 207(0xcf, float:2.9E-43)
            r0[r3] = r1
            r10.sendDownEvent()
            r3 = 208(0xd0, float:2.91E-43)
            r0[r3] = r1
        L_0x00d9:
            byte r3 = r10.mStatus
            if (r3 == r6) goto L_0x00e2
            r3 = 209(0xd1, float:2.93E-43)
            r0[r3] = r1
            goto L_0x0140
        L_0x00e2:
            r3 = 210(0xd2, float:2.94E-43)
            r0[r3] = r1
            if (r2 != 0) goto L_0x00ed
            r3 = 211(0xd3, float:2.96E-43)
            r0[r3] = r1
            goto L_0x011d
        L_0x00ed:
            boolean r3 = r10.isAutoRefresh()
            if (r3 == 0) goto L_0x00f8
            r3 = 212(0xd4, float:2.97E-43)
            r0[r3] = r1
            goto L_0x011d
        L_0x00f8:
            boolean r3 = r10.mPullToRefresh
            if (r3 != 0) goto L_0x0101
            r3 = 213(0xd5, float:2.98E-43)
            r0[r3] = r1
            goto L_0x011d
        L_0x0101:
            com.taobao.weex.yp_compoment.pullrefresh.youpinptr.indicator.PtrIndicator r3 = r10.mPtrIndicator
            r7 = 214(0xd6, float:3.0E-43)
            r0[r7] = r1
            boolean r3 = r3.crossRefreshLineFromTopToBottom()
            if (r3 != 0) goto L_0x0112
            r3 = 215(0xd7, float:3.01E-43)
            r0[r3] = r1
            goto L_0x011d
        L_0x0112:
            r3 = 216(0xd8, float:3.03E-43)
            r0[r3] = r1
            r10.tryToPerformRefresh()
            r3 = 217(0xd9, float:3.04E-43)
            r0[r3] = r1
        L_0x011d:
            boolean r3 = r10.performAutoRefreshButLater()
            if (r3 != 0) goto L_0x0128
            r3 = 218(0xda, float:3.05E-43)
            r0[r3] = r1
            goto L_0x0140
        L_0x0128:
            com.taobao.weex.yp_compoment.pullrefresh.youpinptr.indicator.PtrIndicator r3 = r10.mPtrIndicator
            boolean r3 = r3.hasJustReachedHeaderHeightFromTopToBottom()
            if (r3 != 0) goto L_0x0135
            r3 = 219(0xdb, float:3.07E-43)
            r0[r3] = r1
            goto L_0x0140
        L_0x0135:
            r3 = 220(0xdc, float:3.08E-43)
            r0[r3] = r1
            r10.tryToPerformRefresh()
            r3 = 221(0xdd, float:3.1E-43)
            r0[r3] = r1
        L_0x0140:
            boolean r3 = DEBUG
            if (r3 != 0) goto L_0x0149
            r3 = 222(0xde, float:3.11E-43)
            r0[r3] = r1
            goto L_0x0192
        L_0x0149:
            java.lang.String r3 = r10.LOG_TAG
            java.lang.String r7 = "updatePos: change: %s, current: %s last: %s, top: %s, headerHeight: %s"
            r8 = 5
            java.lang.Object[] r8 = new java.lang.Object[r8]
            r9 = 223(0xdf, float:3.12E-43)
            r0[r9] = r1
            java.lang.Integer r9 = java.lang.Integer.valueOf(r11)
            r8[r4] = r9
            com.taobao.weex.yp_compoment.pullrefresh.youpinptr.indicator.PtrIndicator r4 = r10.mPtrIndicator
            int r4 = r4.getCurrentPosY()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r8[r1] = r4
            com.taobao.weex.yp_compoment.pullrefresh.youpinptr.indicator.PtrIndicator r4 = r10.mPtrIndicator
            int r4 = r4.getLastPosY()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r8[r6] = r4
            r4 = 3
            android.view.View r6 = r10.mContent
            int r6 = r6.getTop()
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            r8[r4] = r6
            int r4 = r10.mHeaderHeight
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r8[r5] = r4
            r4 = 224(0xe0, float:3.14E-43)
            r0[r4] = r1
            com.taobao.weex.yp_compoment.pullrefresh.youpinptr.utils.PtrCLog.v((java.lang.String) r3, (java.lang.String) r7, (java.lang.Object[]) r8)
            r3 = 225(0xe1, float:3.15E-43)
            r0[r3] = r1
        L_0x0192:
            android.view.View r3 = r10.mHeaderView
            r3.offsetTopAndBottom(r11)
            r3 = 226(0xe2, float:3.17E-43)
            r0[r3] = r1
            boolean r3 = r10.isPinContent()
            if (r3 == 0) goto L_0x01a6
            r11 = 227(0xe3, float:3.18E-43)
            r0[r11] = r1
            goto L_0x01b3
        L_0x01a6:
            r3 = 228(0xe4, float:3.2E-43)
            r0[r3] = r1
            android.view.View r3 = r10.mContent
            r3.offsetTopAndBottom(r11)
            r11 = 229(0xe5, float:3.21E-43)
            r0[r11] = r1
        L_0x01b3:
            r10.invalidate()
            r11 = 230(0xe6, float:3.22E-43)
            r0[r11] = r1
            com.taobao.weex.yp_compoment.pullrefresh.youpinptr.PtrUIHandlerHolder r11 = r10.mPtrUIHandlerHolder
            boolean r11 = r11.hasHandler()
            if (r11 != 0) goto L_0x01c7
            r11 = 231(0xe7, float:3.24E-43)
            r0[r11] = r1
            goto L_0x01d8
        L_0x01c7:
            r11 = 232(0xe8, float:3.25E-43)
            r0[r11] = r1
            com.taobao.weex.yp_compoment.pullrefresh.youpinptr.PtrUIHandlerHolder r11 = r10.mPtrUIHandlerHolder
            byte r3 = r10.mStatus
            com.taobao.weex.yp_compoment.pullrefresh.youpinptr.indicator.PtrIndicator r4 = r10.mPtrIndicator
            r11.onUIPositionChange(r10, r2, r3, r4)
            r11 = 233(0xe9, float:3.27E-43)
            r0[r11] = r1
        L_0x01d8:
            byte r11 = r10.mStatus
            com.taobao.weex.yp_compoment.pullrefresh.youpinptr.indicator.PtrIndicator r3 = r10.mPtrIndicator
            r10.onPositionChange(r2, r11, r3)
            r11 = 234(0xea, float:3.28E-43)
            r0[r11] = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.yp_compoment.pullrefresh.youpinptr.PtrFrameLayout.updatePos(int):void");
    }

    /* access modifiers changed from: protected */
    public void onPositionChange(boolean z, byte b, PtrIndicator ptrIndicator) {
        $jacocoInit()[235] = true;
    }

    public int getHeaderHeight() {
        boolean[] $jacocoInit = $jacocoInit();
        int i = this.mHeaderHeight;
        $jacocoInit[236] = true;
        return i;
    }

    private void onRelease(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        tryToPerformRefresh();
        if (this.mStatus == 3) {
            if (this.mKeepHeaderWhenRefresh) {
                $jacocoInit[237] = true;
                if (!this.mPtrIndicator.isOverOffsetToKeepHeaderWhileLoading()) {
                    $jacocoInit[238] = true;
                } else if (z) {
                    $jacocoInit[239] = true;
                } else {
                    $jacocoInit[240] = true;
                    this.mScrollChecker.tryToScrollTo(this.mPtrIndicator.getOffsetToKeepHeaderWhileLoading(), this.mDurationToClose);
                    $jacocoInit[241] = true;
                }
            } else {
                tryScrollBackToTopWhileLoading();
                $jacocoInit[242] = true;
            }
        } else if (this.mStatus == 4) {
            $jacocoInit[243] = true;
            notifyUIRefreshComplete(false);
            $jacocoInit[244] = true;
        } else {
            tryScrollBackToTopAbortRefresh();
            $jacocoInit[245] = true;
        }
        $jacocoInit[246] = true;
    }

    public void setRefreshCompleteHook(PtrUIHandlerHook ptrUIHandlerHook) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mRefreshCompleteHook = ptrUIHandlerHook;
        $jacocoInit[247] = true;
        ptrUIHandlerHook.setResumeAction(new Runnable(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ PtrFrameLayout this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(5795232563013192884L, "com/taobao/weex/yp_compoment/pullrefresh/youpinptr/PtrFrameLayout$2", 5);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r3;
                $jacocoInit[0] = true;
            }

            public void run() {
                boolean[] $jacocoInit = $jacocoInit();
                if (!PtrFrameLayout.DEBUG) {
                    $jacocoInit[1] = true;
                } else {
                    $jacocoInit[2] = true;
                    PtrCLog.d(this.this$0.LOG_TAG, "mRefreshCompleteHook resume.");
                    $jacocoInit[3] = true;
                }
                PtrFrameLayout.access$200(this.this$0, true);
                $jacocoInit[4] = true;
            }
        });
        $jacocoInit[248] = true;
    }

    private void tryScrollBackToTop() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mPtrIndicator.isUnderTouch()) {
            $jacocoInit[249] = true;
        } else {
            $jacocoInit[250] = true;
            this.mScrollChecker.tryToScrollTo(0, this.mDurationToCloseHeader);
            $jacocoInit[251] = true;
        }
        $jacocoInit[252] = true;
    }

    private void tryScrollBackToTopWhileLoading() {
        boolean[] $jacocoInit = $jacocoInit();
        tryScrollBackToTop();
        $jacocoInit[253] = true;
    }

    private void tryScrollBackToTopAfterComplete() {
        boolean[] $jacocoInit = $jacocoInit();
        tryScrollBackToTop();
        $jacocoInit[254] = true;
    }

    private void tryScrollBackToTopAbortRefresh() {
        boolean[] $jacocoInit = $jacocoInit();
        tryScrollBackToTop();
        $jacocoInit[255] = true;
    }

    private boolean tryToPerformRefresh() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mStatus != 2) {
            $jacocoInit[256] = true;
            return false;
        }
        if (!this.mPtrIndicator.isOverOffsetToKeepHeaderWhileLoading()) {
            $jacocoInit[257] = true;
        } else if (isAutoRefresh()) {
            $jacocoInit[258] = true;
            this.mStatus = 3;
            $jacocoInit[262] = true;
            performRefresh();
            $jacocoInit[263] = true;
            $jacocoInit[264] = true;
            return false;
        } else {
            $jacocoInit[259] = true;
        }
        if (!this.mPtrIndicator.isOverOffsetToRefresh()) {
            $jacocoInit[260] = true;
            $jacocoInit[264] = true;
            return false;
        }
        $jacocoInit[261] = true;
        this.mStatus = 3;
        $jacocoInit[262] = true;
        performRefresh();
        $jacocoInit[263] = true;
        $jacocoInit[264] = true;
        return false;
    }

    private void performRefresh() {
        boolean[] $jacocoInit = $jacocoInit();
        this.mLoadingStartTime = System.currentTimeMillis();
        $jacocoInit[265] = true;
        if (!this.mPtrUIHandlerHolder.hasHandler()) {
            $jacocoInit[266] = true;
        } else {
            $jacocoInit[267] = true;
            this.mPtrUIHandlerHolder.onUIRefreshBegin(this);
            if (!DEBUG) {
                $jacocoInit[268] = true;
            } else {
                $jacocoInit[269] = true;
                PtrCLog.i(this.LOG_TAG, "PtrUIHandler: onUIRefreshBegin");
                $jacocoInit[270] = true;
            }
        }
        if (this.mPtrHandler == null) {
            $jacocoInit[271] = true;
        } else {
            $jacocoInit[272] = true;
            this.mPtrHandler.onRefreshBegin(this);
            $jacocoInit[273] = true;
        }
        $jacocoInit[274] = true;
    }

    private boolean tryToNotifyReset() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mStatus == 4) {
            $jacocoInit[275] = true;
        } else if (this.mStatus != 2) {
            $jacocoInit[276] = true;
            $jacocoInit[287] = true;
            return false;
        } else {
            $jacocoInit[277] = true;
        }
        if (!this.mPtrIndicator.isInStartPosition()) {
            $jacocoInit[278] = true;
            $jacocoInit[287] = true;
            return false;
        }
        $jacocoInit[279] = true;
        if (!this.mPtrUIHandlerHolder.hasHandler()) {
            $jacocoInit[280] = true;
        } else {
            $jacocoInit[281] = true;
            this.mPtrUIHandlerHolder.onUIReset(this);
            if (!DEBUG) {
                $jacocoInit[282] = true;
            } else {
                $jacocoInit[283] = true;
                PtrCLog.i(this.LOG_TAG, "PtrUIHandler: onUIReset");
                $jacocoInit[284] = true;
            }
        }
        this.mStatus = 1;
        $jacocoInit[285] = true;
        clearFlag();
        $jacocoInit[286] = true;
        return true;
    }

    /* access modifiers changed from: protected */
    public void onPtrScrollAbort() {
        boolean[] $jacocoInit = $jacocoInit();
        if (!this.mPtrIndicator.hasLeftStartPosition()) {
            $jacocoInit[288] = true;
        } else if (!isAutoRefresh()) {
            $jacocoInit[289] = true;
        } else {
            if (!DEBUG) {
                $jacocoInit[290] = true;
            } else {
                $jacocoInit[291] = true;
                PtrCLog.d(this.LOG_TAG, "call onRelease after scroll abort");
                $jacocoInit[292] = true;
            }
            onRelease(true);
            $jacocoInit[293] = true;
        }
        $jacocoInit[294] = true;
    }

    /* access modifiers changed from: protected */
    public void onPtrScrollFinish() {
        boolean[] $jacocoInit = $jacocoInit();
        if (!this.mPtrIndicator.hasLeftStartPosition()) {
            $jacocoInit[295] = true;
        } else if (!isAutoRefresh()) {
            $jacocoInit[296] = true;
        } else {
            if (!DEBUG) {
                $jacocoInit[297] = true;
            } else {
                $jacocoInit[298] = true;
                PtrCLog.d(this.LOG_TAG, "call onRelease after scroll finish");
                $jacocoInit[299] = true;
            }
            onRelease(true);
            $jacocoInit[300] = true;
        }
        $jacocoInit[301] = true;
    }

    public boolean isRefreshing() {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mStatus == 3) {
            $jacocoInit[302] = true;
            z = true;
        } else {
            z = false;
            $jacocoInit[303] = true;
        }
        $jacocoInit[304] = true;
        return z;
    }

    public final void refreshComplete() {
        boolean[] $jacocoInit = $jacocoInit();
        if (!DEBUG) {
            $jacocoInit[305] = true;
        } else {
            $jacocoInit[306] = true;
            PtrCLog.i(this.LOG_TAG, "refreshComplete");
            $jacocoInit[307] = true;
        }
        if (this.mRefreshCompleteHook == null) {
            $jacocoInit[308] = true;
        } else {
            $jacocoInit[309] = true;
            this.mRefreshCompleteHook.reset();
            $jacocoInit[310] = true;
        }
        int currentTimeMillis = (int) (((long) this.mLoadingMinTime) - (System.currentTimeMillis() - this.mLoadingStartTime));
        if (currentTimeMillis <= 0) {
            if (!DEBUG) {
                $jacocoInit[311] = true;
            } else {
                $jacocoInit[312] = true;
                PtrCLog.d(this.LOG_TAG, "performRefreshComplete at once");
                $jacocoInit[313] = true;
            }
            performRefreshComplete();
            $jacocoInit[314] = true;
        } else {
            postDelayed(this.mPerformRefreshCompleteDelay, (long) currentTimeMillis);
            if (!DEBUG) {
                $jacocoInit[315] = true;
            } else {
                $jacocoInit[316] = true;
                PtrCLog.d(this.LOG_TAG, "performRefreshComplete after delay: %s", Integer.valueOf(currentTimeMillis));
                $jacocoInit[317] = true;
            }
        }
        $jacocoInit[318] = true;
    }

    private void performRefreshComplete() {
        boolean[] $jacocoInit = $jacocoInit();
        this.mStatus = 4;
        $jacocoInit[319] = true;
        if (!ScrollChecker.access$300(this.mScrollChecker)) {
            $jacocoInit[320] = true;
        } else if (!isAutoRefresh()) {
            $jacocoInit[321] = true;
        } else {
            if (!DEBUG) {
                $jacocoInit[322] = true;
            } else {
                String str = this.LOG_TAG;
                ScrollChecker scrollChecker = this.mScrollChecker;
                $jacocoInit[323] = true;
                Object[] objArr = {Boolean.valueOf(ScrollChecker.access$300(scrollChecker)), Integer.valueOf(this.mFlag)};
                $jacocoInit[324] = true;
                PtrCLog.d(str, "performRefreshComplete do nothing, scrolling: %s, auto refresh: %s", objArr);
                $jacocoInit[325] = true;
            }
            $jacocoInit[326] = true;
            return;
        }
        notifyUIRefreshComplete(false);
        $jacocoInit[327] = true;
    }

    private void notifyUIRefreshComplete(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!this.mPtrIndicator.hasLeftStartPosition()) {
            $jacocoInit[328] = true;
        } else if (z) {
            $jacocoInit[329] = true;
        } else if (this.mRefreshCompleteHook == null) {
            $jacocoInit[330] = true;
        } else {
            if (!DEBUG) {
                $jacocoInit[331] = true;
            } else {
                $jacocoInit[332] = true;
                PtrCLog.d(this.LOG_TAG, "notifyUIRefreshComplete mRefreshCompleteHook run.");
                $jacocoInit[333] = true;
            }
            this.mRefreshCompleteHook.takeOver();
            $jacocoInit[334] = true;
            return;
        }
        if (!this.mPtrUIHandlerHolder.hasHandler()) {
            $jacocoInit[335] = true;
        } else {
            if (!DEBUG) {
                $jacocoInit[336] = true;
            } else {
                $jacocoInit[337] = true;
                PtrCLog.i(this.LOG_TAG, "PtrUIHandler: onUIRefreshComplete");
                $jacocoInit[338] = true;
            }
            this.mPtrUIHandlerHolder.onUIRefreshComplete(this);
            $jacocoInit[339] = true;
        }
        this.mPtrIndicator.onUIRefreshComplete();
        $jacocoInit[340] = true;
        tryScrollBackToTopAfterComplete();
        $jacocoInit[341] = true;
        tryToNotifyReset();
        $jacocoInit[342] = true;
    }

    public void autoRefresh() {
        boolean[] $jacocoInit = $jacocoInit();
        autoRefresh(true, this.mDurationToCloseHeader);
        $jacocoInit[343] = true;
    }

    public void autoRefresh(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        autoRefresh(z, this.mDurationToCloseHeader);
        $jacocoInit[344] = true;
    }

    private void clearFlag() {
        boolean[] $jacocoInit = $jacocoInit();
        this.mFlag &= MASK_AUTO_REFRESH ^ -1;
        $jacocoInit[345] = true;
    }

    public void autoRefresh(boolean z, int i) {
        byte b;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mStatus != 1) {
            $jacocoInit[346] = true;
            return;
        }
        int i2 = this.mFlag;
        if (z) {
            b = FLAG_AUTO_REFRESH_AT_ONCE;
            $jacocoInit[347] = true;
        } else {
            b = FLAG_AUTO_REFRESH_BUT_LATER;
            $jacocoInit[348] = true;
        }
        this.mFlag = i2 | b;
        this.mStatus = 2;
        $jacocoInit[349] = true;
        if (!this.mPtrUIHandlerHolder.hasHandler()) {
            $jacocoInit[350] = true;
        } else {
            $jacocoInit[351] = true;
            this.mPtrUIHandlerHolder.onUIRefreshPrepare(this);
            if (!DEBUG) {
                $jacocoInit[352] = true;
            } else {
                $jacocoInit[353] = true;
                PtrCLog.i(this.LOG_TAG, "PtrUIHandler: onUIRefreshPrepare, mFlag %s", Integer.valueOf(this.mFlag));
                $jacocoInit[354] = true;
            }
        }
        this.mScrollChecker.tryToScrollTo(this.mPtrIndicator.getOffsetToRefresh(), i);
        if (!z) {
            $jacocoInit[355] = true;
        } else {
            this.mStatus = 3;
            $jacocoInit[356] = true;
            performRefresh();
            $jacocoInit[357] = true;
        }
        $jacocoInit[358] = true;
    }

    public boolean isAutoRefresh() {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if ((this.mFlag & MASK_AUTO_REFRESH) > 0) {
            $jacocoInit[359] = true;
            z = true;
        } else {
            z = false;
            $jacocoInit[360] = true;
        }
        $jacocoInit[361] = true;
        return z;
    }

    private boolean performAutoRefreshButLater() {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if ((this.mFlag & MASK_AUTO_REFRESH) == FLAG_AUTO_REFRESH_BUT_LATER) {
            $jacocoInit[362] = true;
            z = true;
        } else {
            z = false;
            $jacocoInit[363] = true;
        }
        $jacocoInit[364] = true;
        return z;
    }

    public boolean isEnabledNextPtrAtOnce() {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if ((this.mFlag & FLAG_ENABLE_NEXT_PTR_AT_ONCE) > 0) {
            $jacocoInit[365] = true;
            z = true;
        } else {
            z = false;
            $jacocoInit[366] = true;
        }
        $jacocoInit[367] = true;
        return z;
    }

    public void setEnabledNextPtrAtOnce(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        if (z) {
            this.mFlag |= FLAG_ENABLE_NEXT_PTR_AT_ONCE;
            $jacocoInit[368] = true;
        } else {
            this.mFlag &= FLAG_ENABLE_NEXT_PTR_AT_ONCE ^ -1;
            $jacocoInit[369] = true;
        }
        $jacocoInit[370] = true;
    }

    public boolean isPinContent() {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if ((this.mFlag & FLAG_PIN_CONTENT) > 0) {
            $jacocoInit[371] = true;
            z = true;
        } else {
            z = false;
            $jacocoInit[372] = true;
        }
        $jacocoInit[373] = true;
        return z;
    }

    public void setPinContent(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        if (z) {
            this.mFlag |= FLAG_PIN_CONTENT;
            $jacocoInit[374] = true;
        } else {
            this.mFlag &= FLAG_PIN_CONTENT ^ -1;
            $jacocoInit[375] = true;
        }
        $jacocoInit[376] = true;
    }

    public void disableWhenHorizontalMove(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mDisableWhenHorizontalMove = z;
        $jacocoInit[377] = true;
    }

    public void setLoadingMinTime(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mLoadingMinTime = i;
        $jacocoInit[378] = true;
    }

    @Deprecated
    public void setInterceptEventWhileWorking(boolean z) {
        $jacocoInit()[379] = true;
    }

    public View getContentView() {
        boolean[] $jacocoInit = $jacocoInit();
        View view = this.mContent;
        $jacocoInit[380] = true;
        return view;
    }

    public void setPtrHandler(PtrHandler ptrHandler) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mPtrHandler = ptrHandler;
        $jacocoInit[381] = true;
    }

    public void addPtrUIHandler(PtrUIHandler ptrUIHandler) {
        boolean[] $jacocoInit = $jacocoInit();
        PtrUIHandlerHolder.addHandler(this.mPtrUIHandlerHolder, ptrUIHandler);
        $jacocoInit[382] = true;
    }

    public void removePtrUIHandler(PtrUIHandler ptrUIHandler) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mPtrUIHandlerHolder = PtrUIHandlerHolder.removeHandler(this.mPtrUIHandlerHolder, ptrUIHandler);
        $jacocoInit[383] = true;
    }

    public void setPtrIndicator(PtrIndicator ptrIndicator) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mPtrIndicator == null) {
            $jacocoInit[384] = true;
        } else if (this.mPtrIndicator == ptrIndicator) {
            $jacocoInit[385] = true;
        } else {
            $jacocoInit[386] = true;
            ptrIndicator.convertFrom(this.mPtrIndicator);
            $jacocoInit[387] = true;
        }
        this.mPtrIndicator = ptrIndicator;
        $jacocoInit[388] = true;
    }

    public float getResistance() {
        boolean[] $jacocoInit = $jacocoInit();
        float resistance = this.mPtrIndicator.getResistance();
        $jacocoInit[389] = true;
        return resistance;
    }

    public void setResistance(float f) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mPtrIndicator.setResistance(f);
        $jacocoInit[390] = true;
    }

    public float getDurationToClose() {
        boolean[] $jacocoInit = $jacocoInit();
        float f = (float) this.mDurationToClose;
        $jacocoInit[391] = true;
        return f;
    }

    public void setDurationToClose(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mDurationToClose = i;
        $jacocoInit[392] = true;
    }

    public long getDurationToCloseHeader() {
        boolean[] $jacocoInit = $jacocoInit();
        long j = (long) this.mDurationToCloseHeader;
        $jacocoInit[393] = true;
        return j;
    }

    public void setDurationToCloseHeader(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mDurationToCloseHeader = i;
        $jacocoInit[394] = true;
    }

    public void setRatioOfHeaderHeightToRefresh(float f) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mPtrIndicator.setRatioOfHeaderHeightToRefresh(f);
        $jacocoInit[395] = true;
    }

    public int getOffsetToRefresh() {
        boolean[] $jacocoInit = $jacocoInit();
        int offsetToRefresh = this.mPtrIndicator.getOffsetToRefresh();
        $jacocoInit[396] = true;
        return offsetToRefresh;
    }

    public void setOffsetToRefresh(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mPtrIndicator.setOffsetToRefresh(i);
        $jacocoInit[397] = true;
    }

    public float getRatioOfHeaderToHeightRefresh() {
        boolean[] $jacocoInit = $jacocoInit();
        float ratioOfHeaderToHeightRefresh = this.mPtrIndicator.getRatioOfHeaderToHeightRefresh();
        $jacocoInit[398] = true;
        return ratioOfHeaderToHeightRefresh;
    }

    public int getOffsetToKeepHeaderWhileLoading() {
        boolean[] $jacocoInit = $jacocoInit();
        int offsetToKeepHeaderWhileLoading = this.mPtrIndicator.getOffsetToKeepHeaderWhileLoading();
        $jacocoInit[399] = true;
        return offsetToKeepHeaderWhileLoading;
    }

    public void setOffsetToKeepHeaderWhileLoading(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mPtrIndicator.setOffsetToKeepHeaderWhileLoading(i);
        $jacocoInit[400] = true;
    }

    public boolean isKeepHeaderWhenRefresh() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = this.mKeepHeaderWhenRefresh;
        $jacocoInit[401] = true;
        return z;
    }

    public void setKeepHeaderWhenRefresh(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mKeepHeaderWhenRefresh = z;
        $jacocoInit[402] = true;
    }

    public boolean isPullToRefresh() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = this.mPullToRefresh;
        $jacocoInit[403] = true;
        return z;
    }

    public void setPullToRefresh(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mPullToRefresh = z;
        $jacocoInit[404] = true;
    }

    public View getHeaderView() {
        boolean[] $jacocoInit = $jacocoInit();
        View view = this.mHeaderView;
        $jacocoInit[405] = true;
        return view;
    }

    public void setHeaderView(View view) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mHeaderView == null) {
            $jacocoInit[406] = true;
        } else if (view == null) {
            $jacocoInit[407] = true;
        } else if (this.mHeaderView == view) {
            $jacocoInit[408] = true;
        } else {
            $jacocoInit[409] = true;
            removeView(this.mHeaderView);
            $jacocoInit[410] = true;
        }
        if (view.getLayoutParams() != null) {
            $jacocoInit[411] = true;
        } else {
            $jacocoInit[412] = true;
            LayoutParams layoutParams = new LayoutParams(-1, -2);
            $jacocoInit[413] = true;
            view.setLayoutParams(layoutParams);
            $jacocoInit[414] = true;
        }
        this.mHeaderView = view;
        $jacocoInit[415] = true;
        addView(view);
        $jacocoInit[416] = true;
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        boolean z = layoutParams instanceof LayoutParams;
        $jacocoInit()[417] = true;
        return z;
    }

    /* access modifiers changed from: protected */
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        boolean[] $jacocoInit = $jacocoInit();
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        $jacocoInit[418] = true;
        return layoutParams;
    }

    /* access modifiers changed from: protected */
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        boolean[] $jacocoInit = $jacocoInit();
        LayoutParams layoutParams2 = new LayoutParams(layoutParams);
        $jacocoInit[419] = true;
        return layoutParams2;
    }

    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        boolean[] $jacocoInit = $jacocoInit();
        LayoutParams layoutParams = new LayoutParams(getContext(), attributeSet);
        $jacocoInit[420] = true;
        return layoutParams;
    }

    private void sendCancelEvent() {
        boolean[] $jacocoInit = $jacocoInit();
        if (!DEBUG) {
            $jacocoInit[421] = true;
        } else {
            $jacocoInit[422] = true;
            PtrCLog.d(this.LOG_TAG, "send cancel event");
            $jacocoInit[423] = true;
        }
        if (this.mLastMoveEvent == null) {
            $jacocoInit[424] = true;
            return;
        }
        MotionEvent motionEvent = this.mLastMoveEvent;
        $jacocoInit[425] = true;
        MotionEvent obtain = MotionEvent.obtain(motionEvent.getDownTime(), motionEvent.getEventTime() + ((long) ViewConfiguration.getLongPressTimeout()), 3, motionEvent.getX(), motionEvent.getY(), motionEvent.getMetaState());
        $jacocoInit[426] = true;
        dispatchTouchEventSupper(obtain);
        $jacocoInit[427] = true;
    }

    private void sendDownEvent() {
        boolean[] $jacocoInit = $jacocoInit();
        if (!DEBUG) {
            $jacocoInit[428] = true;
        } else {
            $jacocoInit[429] = true;
            PtrCLog.d(this.LOG_TAG, "send down event");
            $jacocoInit[430] = true;
        }
        MotionEvent motionEvent = this.mLastMoveEvent;
        $jacocoInit[431] = true;
        MotionEvent obtain = MotionEvent.obtain(motionEvent.getDownTime(), motionEvent.getEventTime(), 0, motionEvent.getX(), motionEvent.getY(), motionEvent.getMetaState());
        $jacocoInit[432] = true;
        dispatchTouchEventSupper(obtain);
        $jacocoInit[433] = true;
    }

    public void removeHeaderView(View view) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mHeaderView == null) {
            $jacocoInit[434] = true;
        } else {
            $jacocoInit[435] = true;
            removeView(this.mHeaderView);
            $jacocoInit[436] = true;
        }
        this.mHeaderView = null;
        $jacocoInit[437] = true;
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        private static transient /* synthetic */ boolean[] $jacocoData;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(-1374666844389030724L, "com/taobao/weex/yp_compoment/pullrefresh/youpinptr/PtrFrameLayout$LayoutParams", 4);
            $jacocoData = a2;
            return a2;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            boolean[] $jacocoInit = $jacocoInit();
            $jacocoInit[0] = true;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LayoutParams(int i, int i2) {
            super(i, i2);
            boolean[] $jacocoInit = $jacocoInit();
            $jacocoInit[1] = true;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            boolean[] $jacocoInit = $jacocoInit();
            $jacocoInit[2] = true;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            boolean[] $jacocoInit = $jacocoInit();
            $jacocoInit[3] = true;
        }
    }

    class ScrollChecker implements Runnable {
        private static transient /* synthetic */ boolean[] $jacocoData;
        private boolean mIsRunning = false;
        private int mLastFlingY;
        private Scroller mScroller;
        private int mStart;
        private int mTo;
        final /* synthetic */ PtrFrameLayout this$0;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(8731887810510586007L, "com/taobao/weex/yp_compoment/pullrefresh/youpinptr/PtrFrameLayout$ScrollChecker", 49);
            $jacocoData = a2;
            return a2;
        }

        static /* synthetic */ void access$100(ScrollChecker scrollChecker) {
            boolean[] $jacocoInit = $jacocoInit();
            scrollChecker.destroy();
            $jacocoInit[47] = true;
        }

        static /* synthetic */ boolean access$300(ScrollChecker scrollChecker) {
            boolean[] $jacocoInit = $jacocoInit();
            boolean z = scrollChecker.mIsRunning;
            $jacocoInit[48] = true;
            return z;
        }

        public ScrollChecker(PtrFrameLayout ptrFrameLayout) {
            boolean[] $jacocoInit = $jacocoInit();
            this.this$0 = ptrFrameLayout;
            $jacocoInit[0] = true;
            this.mScroller = new Scroller(ptrFrameLayout.getContext());
            $jacocoInit[1] = true;
        }

        /* JADX WARNING: Removed duplicated region for block: B:10:0x0038  */
        /* JADX WARNING: Removed duplicated region for block: B:11:0x003b  */
        /* JADX WARNING: Removed duplicated region for block: B:15:0x0095  */
        /* JADX WARNING: Removed duplicated region for block: B:16:0x00af  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r15 = this;
                boolean[] r0 = $jacocoInit()
                android.widget.Scroller r1 = r15.mScroller
                boolean r1 = r1.computeScrollOffset()
                r2 = 5
                r3 = 0
                r4 = 3
                r5 = 4
                r6 = 2
                r7 = 1
                if (r1 != 0) goto L_0x0015
                r0[r6] = r7
                goto L_0x001f
            L_0x0015:
                android.widget.Scroller r1 = r15.mScroller
                boolean r1 = r1.isFinished()
                if (r1 == 0) goto L_0x0023
                r0[r4] = r7
            L_0x001f:
                r0[r5] = r7
                r1 = 1
                goto L_0x0026
            L_0x0023:
                r0[r2] = r7
                r1 = 0
            L_0x0026:
                r8 = 6
                r0[r8] = r7
                android.widget.Scroller r9 = r15.mScroller
                int r9 = r9.getCurrY()
                int r10 = r15.mLastFlingY
                int r10 = r9 - r10
                boolean r11 = com.taobao.weex.yp_compoment.pullrefresh.youpinptr.PtrFrameLayout.DEBUG
                r12 = 7
                if (r11 != 0) goto L_0x003b
                r0[r12] = r7
                goto L_0x0093
            L_0x003b:
                if (r10 != 0) goto L_0x0042
                r2 = 8
                r0[r2] = r7
                goto L_0x0093
            L_0x0042:
                com.taobao.weex.yp_compoment.pullrefresh.youpinptr.PtrFrameLayout r11 = r15.this$0
                java.lang.String r11 = r11.LOG_TAG
                java.lang.String r13 = "scroll: %s, start: %s, to: %s, currentPos: %s, current :%s, last: %s, delta: %s"
                java.lang.Object[] r12 = new java.lang.Object[r12]
                r14 = 9
                r0[r14] = r7
                java.lang.Boolean r14 = java.lang.Boolean.valueOf(r1)
                r12[r3] = r14
                int r3 = r15.mStart
                java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
                r12[r7] = r3
                int r3 = r15.mTo
                java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
                r12[r6] = r3
                com.taobao.weex.yp_compoment.pullrefresh.youpinptr.PtrFrameLayout r3 = r15.this$0
                com.taobao.weex.yp_compoment.pullrefresh.youpinptr.indicator.PtrIndicator r3 = com.taobao.weex.yp_compoment.pullrefresh.youpinptr.PtrFrameLayout.access$400(r3)
                int r3 = r3.getCurrentPosY()
                java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
                r12[r4] = r3
                java.lang.Integer r3 = java.lang.Integer.valueOf(r9)
                r12[r5] = r3
                int r3 = r15.mLastFlingY
                java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
                r12[r2] = r3
                java.lang.Integer r2 = java.lang.Integer.valueOf(r10)
                r12[r8] = r2
                r2 = 10
                r0[r2] = r7
                com.taobao.weex.yp_compoment.pullrefresh.youpinptr.utils.PtrCLog.v((java.lang.String) r11, (java.lang.String) r13, (java.lang.Object[]) r12)
                r2 = 11
                r0[r2] = r7
            L_0x0093:
                if (r1 != 0) goto L_0x00af
                r15.mLastFlingY = r9
                r1 = 12
                r0[r1] = r7
                com.taobao.weex.yp_compoment.pullrefresh.youpinptr.PtrFrameLayout r1 = r15.this$0
                float r2 = (float) r10
                com.taobao.weex.yp_compoment.pullrefresh.youpinptr.PtrFrameLayout.access$500(r1, r2)
                r1 = 13
                r0[r1] = r7
                com.taobao.weex.yp_compoment.pullrefresh.youpinptr.PtrFrameLayout r1 = r15.this$0
                r1.post(r15)
                r1 = 14
                r0[r1] = r7
                goto L_0x00b6
            L_0x00af:
                r15.finish()
                r1 = 15
                r0[r1] = r7
            L_0x00b6:
                r1 = 16
                r0[r1] = r7
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.yp_compoment.pullrefresh.youpinptr.PtrFrameLayout.ScrollChecker.run():void");
        }

        private void finish() {
            boolean[] $jacocoInit = $jacocoInit();
            if (!PtrFrameLayout.DEBUG) {
                $jacocoInit[17] = true;
            } else {
                $jacocoInit[18] = true;
                PtrCLog.v(this.this$0.LOG_TAG, "finish, currentPos:%s", Integer.valueOf(PtrFrameLayout.access$400(this.this$0).getCurrentPosY()));
                $jacocoInit[19] = true;
            }
            reset();
            $jacocoInit[20] = true;
            this.this$0.onPtrScrollFinish();
            $jacocoInit[21] = true;
        }

        private void reset() {
            boolean[] $jacocoInit = $jacocoInit();
            this.mIsRunning = false;
            this.mLastFlingY = 0;
            $jacocoInit[22] = true;
            this.this$0.removeCallbacks(this);
            $jacocoInit[23] = true;
        }

        private void destroy() {
            boolean[] $jacocoInit = $jacocoInit();
            reset();
            $jacocoInit[24] = true;
            if (this.mScroller.isFinished()) {
                $jacocoInit[25] = true;
            } else {
                $jacocoInit[26] = true;
                this.mScroller.forceFinished(true);
                $jacocoInit[27] = true;
            }
            $jacocoInit[28] = true;
        }

        public void abortIfWorking() {
            boolean[] $jacocoInit = $jacocoInit();
            if (!this.mIsRunning) {
                $jacocoInit[29] = true;
            } else {
                $jacocoInit[30] = true;
                if (this.mScroller.isFinished()) {
                    $jacocoInit[31] = true;
                } else {
                    $jacocoInit[32] = true;
                    this.mScroller.forceFinished(true);
                    $jacocoInit[33] = true;
                }
                this.this$0.onPtrScrollAbort();
                $jacocoInit[34] = true;
                reset();
                $jacocoInit[35] = true;
            }
            $jacocoInit[36] = true;
        }

        public void tryToScrollTo(int i, int i2) {
            boolean[] $jacocoInit = $jacocoInit();
            if (PtrFrameLayout.access$400(this.this$0).isAlreadyHere(i)) {
                $jacocoInit[37] = true;
                return;
            }
            this.mStart = PtrFrameLayout.access$400(this.this$0).getCurrentPosY();
            this.mTo = i;
            int i3 = i - this.mStart;
            if (!PtrFrameLayout.DEBUG) {
                $jacocoInit[38] = true;
            } else {
                $jacocoInit[39] = true;
                PtrCLog.d(this.this$0.LOG_TAG, "tryToScrollTo: start: %s, distance:%s, to:%s", Integer.valueOf(this.mStart), Integer.valueOf(i3), Integer.valueOf(i));
                $jacocoInit[40] = true;
            }
            this.this$0.removeCallbacks(this);
            this.mLastFlingY = 0;
            $jacocoInit[41] = true;
            if (this.mScroller.isFinished()) {
                $jacocoInit[42] = true;
            } else {
                $jacocoInit[43] = true;
                this.mScroller.forceFinished(true);
                $jacocoInit[44] = true;
            }
            this.mScroller.startScroll(0, 0, 0, i3, i2);
            $jacocoInit[45] = true;
            this.this$0.post(this);
            this.mIsRunning = true;
            $jacocoInit[46] = true;
        }
    }
}
