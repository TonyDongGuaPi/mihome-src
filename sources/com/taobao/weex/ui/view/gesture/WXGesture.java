package com.taobao.weex.ui.view.gesture;

import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mi.global.shop.constants.UIConstant;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.bridge.EventResult;
import com.taobao.weex.common.Constants;
import com.taobao.weex.dom.WXEvent;
import com.taobao.weex.ui.component.Scrollable;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.view.gesture.WXGestureType;
import com.taobao.weex.utils.WXLogUtils;
import com.taobao.weex.utils.WXUtils;
import com.taobao.weex.utils.WXViewUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXGesture extends GestureDetector.SimpleOnGestureListener implements View.OnTouchListener {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    private static final int CUR_EVENT = -1;
    public static final String DOWN = "down";
    public static final String END = "end";
    public static final String LEFT = "left";
    public static final String MOVE = "move";
    public static final String RIGHT = "right";
    public static final String START = "start";
    private static final String TAG = "Gesture";
    public static final String UNKNOWN = "unknown";
    public static final String UP = "up";
    private WXComponent component;
    private Point globalEventOffset;
    private Point globalOffset;
    private Rect globalRect;
    private PointF locEventOffset;
    private PointF locLeftTop;
    private GestureDetector mGestureDetector;
    private boolean mIsPreventMoveEvent = false;
    private boolean mIsTouchEventConsumed = false;
    private int mParentOrientation = -1;
    private WXGestureType mPendingPan = null;
    private final List<View.OnTouchListener> mTouchListeners;
    private long panDownTime = -1;
    private boolean requestDisallowInterceptTouchEvent = false;
    private int shouldBubbleCallRemainTimes = 0;
    private int shouldBubbleInterval = 0;
    private boolean shouldBubbleResult = true;
    private long swipeDownTime = -1;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(2939402996413014236L, "com/taobao/weex/ui/view/gesture/WXGesture", UIConstant.h);
        $jacocoData = a2;
        return a2;
    }

    public WXGesture(WXComponent wXComponent, Context context) {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
        this.mTouchListeners = new LinkedList();
        this.component = wXComponent;
        $jacocoInit[1] = true;
        this.globalRect = new Rect();
        $jacocoInit[2] = true;
        this.globalOffset = new Point();
        $jacocoInit[3] = true;
        this.globalEventOffset = new Point();
        $jacocoInit[4] = true;
        this.locEventOffset = new PointF();
        $jacocoInit[5] = true;
        this.locLeftTop = new PointF();
        $jacocoInit[6] = true;
        this.mGestureDetector = new GestureDetector(context, this, new GestureHandler());
        $jacocoInit[7] = true;
        Scrollable parentScroller = wXComponent.getParentScroller();
        if (parentScroller == null) {
            $jacocoInit[8] = true;
        } else {
            $jacocoInit[9] = true;
            this.mParentOrientation = parentScroller.getOrientation();
            $jacocoInit[10] = true;
        }
        this.shouldBubbleResult = WXUtils.getBoolean(wXComponent.getAttrs().get(Constants.Name.SHOULD_STOP_PROPAGATION_INIT_RESULT), true).booleanValue();
        $jacocoInit[11] = true;
        this.shouldBubbleInterval = WXUtils.getNumberInt(wXComponent.getAttrs().get(Constants.Name.SHOULD_STOP_PROPAGATION_INTERVAL), 0);
        $jacocoInit[12] = true;
    }

    private boolean isParentScrollable() {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.component == null) {
            $jacocoInit[13] = true;
            return true;
        }
        Scrollable parentScroller = this.component.getParentScroller();
        $jacocoInit[14] = true;
        if (parentScroller == null) {
            $jacocoInit[15] = true;
        } else if (parentScroller.isScrollable()) {
            $jacocoInit[16] = true;
        } else {
            z = false;
            $jacocoInit[18] = true;
            $jacocoInit[19] = true;
            return z;
        }
        $jacocoInit[17] = true;
        z = true;
        $jacocoInit[19] = true;
        return z;
    }

    private boolean hasSameOrientationWithParent() {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mParentOrientation != 0) {
            $jacocoInit[20] = true;
        } else if (this.component.containsGesture(WXGestureType.HighLevelGesture.HORIZONTALPAN)) {
            $jacocoInit[21] = true;
            $jacocoInit[27] = true;
            z = true;
            $jacocoInit[29] = true;
            return z;
        } else {
            $jacocoInit[22] = true;
        }
        if (this.mParentOrientation != 1) {
            $jacocoInit[23] = true;
        } else {
            WXComponent wXComponent = this.component;
            WXGestureType.HighLevelGesture highLevelGesture = WXGestureType.HighLevelGesture.VERTICALPAN;
            $jacocoInit[24] = true;
            if (!wXComponent.containsGesture(highLevelGesture)) {
                $jacocoInit[25] = true;
            } else {
                $jacocoInit[26] = true;
                $jacocoInit[27] = true;
                z = true;
                $jacocoInit[29] = true;
                return z;
            }
        }
        z = false;
        $jacocoInit[28] = true;
        $jacocoInit[29] = true;
        return z;
    }

    public void setPreventMoveEvent(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mIsPreventMoveEvent = z;
        $jacocoInit[30] = true;
    }

    public boolean isTouchEventConsumedByAdvancedGesture() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = this.mIsTouchEventConsumed;
        $jacocoInit[31] = true;
        return z;
    }

    public static boolean isStopPropagation(String str) {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (Constants.Event.STOP_PROPAGATION.equals(str)) {
            $jacocoInit[32] = true;
        } else if (Constants.Event.STOP_PROPAGATION_RAX.equals(str)) {
            $jacocoInit[33] = true;
        } else {
            z = false;
            $jacocoInit[35] = true;
            $jacocoInit[36] = true;
            return z;
        }
        $jacocoInit[34] = true;
        z = true;
        $jacocoInit[36] = true;
        return z;
    }

    public static boolean hasStopPropagation(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        WXEvent events = wXComponent.getEvents();
        if (events == null) {
            $jacocoInit[37] = true;
            return false;
        }
        int size = events.size();
        $jacocoInit[38] = true;
        int i = 0;
        while (true) {
            if (i >= size) {
                $jacocoInit[39] = true;
                break;
            }
            $jacocoInit[40] = true;
            if (i >= events.size()) {
                $jacocoInit[41] = true;
                break;
            }
            $jacocoInit[42] = true;
            if (isStopPropagation((String) events.get(i))) {
                $jacocoInit[43] = true;
                return true;
            }
            i++;
            $jacocoInit[44] = true;
        }
        $jacocoInit[45] = true;
        return false;
    }

    private boolean shouldBubbleTouchEvent(MotionEvent motionEvent) {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (hasStopPropagation(this.component)) {
            if (this.shouldBubbleInterval <= 0) {
                $jacocoInit[46] = true;
            } else if (this.shouldBubbleCallRemainTimes <= 0) {
                $jacocoInit[47] = true;
            } else {
                this.shouldBubbleCallRemainTimes--;
                boolean z2 = this.shouldBubbleResult;
                $jacocoInit[48] = true;
                return z2;
            }
            Map<String, Object> createFireEventParam = createFireEventParam(motionEvent, -1, (String) null);
            $jacocoInit[49] = true;
            createFireEventParam.put("type", "touch");
            $jacocoInit[50] = true;
            if (motionEvent.getAction() == 0) {
                $jacocoInit[51] = true;
                createFireEventParam.put("action", "start");
                $jacocoInit[52] = true;
            } else {
                if (motionEvent.getAction() == 3) {
                    $jacocoInit[53] = true;
                } else {
                    $jacocoInit[54] = true;
                    if (motionEvent.getAction() == 1) {
                        $jacocoInit[55] = true;
                    } else {
                        createFireEventParam.put("action", MOVE);
                        $jacocoInit[57] = true;
                    }
                }
                createFireEventParam.put("action", "end");
                $jacocoInit[56] = true;
            }
            String str = Constants.Event.STOP_PROPAGATION;
            $jacocoInit[58] = true;
            if (this.component.getEvents().contains(Constants.Event.STOP_PROPAGATION)) {
                $jacocoInit[59] = true;
            } else {
                str = Constants.Event.STOP_PROPAGATION_RAX;
                $jacocoInit[60] = true;
            }
            EventResult fireEventWait = this.component.fireEventWait(str, createFireEventParam);
            $jacocoInit[61] = true;
            if (!fireEventWait.isSuccess()) {
                $jacocoInit[62] = true;
            } else if (fireEventWait.getResult() == null) {
                $jacocoInit[63] = true;
            } else {
                $jacocoInit[64] = true;
                Object result = fireEventWait.getResult();
                boolean z3 = false;
                if (!this.shouldBubbleResult) {
                    $jacocoInit[65] = true;
                    z = true;
                } else {
                    $jacocoInit[66] = true;
                    z = false;
                }
                if (!WXUtils.getBoolean(result, Boolean.valueOf(z)).booleanValue()) {
                    $jacocoInit[67] = true;
                    z3 = true;
                } else {
                    $jacocoInit[68] = true;
                }
                this.shouldBubbleResult = z3;
                $jacocoInit[69] = true;
            }
            this.shouldBubbleCallRemainTimes = this.shouldBubbleInterval;
            boolean z4 = this.shouldBubbleResult;
            $jacocoInit[70] = true;
            return z4;
        }
        $jacocoInit[71] = true;
        return true;
    }

    public void addOnTouchListener(View.OnTouchListener onTouchListener) {
        boolean[] $jacocoInit = $jacocoInit();
        if (onTouchListener == null) {
            $jacocoInit[72] = true;
        } else {
            $jacocoInit[73] = true;
            this.mTouchListeners.add(onTouchListener);
            $jacocoInit[74] = true;
        }
        $jacocoInit[75] = true;
    }

    public boolean removeTouchListener(View.OnTouchListener onTouchListener) {
        boolean[] $jacocoInit = $jacocoInit();
        if (onTouchListener != null) {
            $jacocoInit[76] = true;
            boolean remove = this.mTouchListeners.remove(onTouchListener);
            $jacocoInit[77] = true;
            return remove;
        }
        $jacocoInit[78] = true;
        return false;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (!this.requestDisallowInterceptTouchEvent) {
            $jacocoInit[79] = true;
            try {
                boolean onTouchEvent = this.mGestureDetector.onTouchEvent(motionEvent);
                $jacocoInit[81] = true;
                if (this.mTouchListeners == null) {
                    $jacocoInit[82] = true;
                } else if (this.mTouchListeners.isEmpty()) {
                    $jacocoInit[83] = true;
                } else {
                    $jacocoInit[84] = true;
                    $jacocoInit[85] = true;
                    for (View.OnTouchListener onTouch : this.mTouchListeners) {
                        $jacocoInit[87] = true;
                        onTouchEvent |= onTouch.onTouch(view, motionEvent);
                        $jacocoInit[88] = true;
                    }
                    $jacocoInit[86] = true;
                }
                switch (motionEvent.getActionMasked()) {
                    case 0:
                    case 5:
                        this.mIsTouchEventConsumed = false;
                        $jacocoInit[90] = true;
                        if (!hasSameOrientationWithParent()) {
                            $jacocoInit[91] = true;
                        } else if (isParentScrollable()) {
                            $jacocoInit[92] = true;
                        } else {
                            $jacocoInit[93] = true;
                            ViewParent parent = this.component.getRealView().getParent();
                            if (parent == null) {
                                $jacocoInit[94] = true;
                            } else {
                                $jacocoInit[95] = true;
                                parent.requestDisallowInterceptTouchEvent(true);
                                $jacocoInit[96] = true;
                            }
                        }
                        onTouchEvent |= handleMotionEvent(WXGestureType.LowLevelGesture.ACTION_DOWN, motionEvent);
                        $jacocoInit[97] = true;
                        break;
                    case 1:
                    case 6:
                        finishDisallowInterceptTouchEvent(view);
                        $jacocoInit[99] = true;
                        $jacocoInit[100] = true;
                        onTouchEvent = onTouchEvent | handleMotionEvent(WXGestureType.LowLevelGesture.ACTION_UP, motionEvent) | handlePanMotionEvent(motionEvent);
                        $jacocoInit[101] = true;
                        break;
                    case 2:
                        onTouchEvent |= handleMotionEvent(WXGestureType.LowLevelGesture.ACTION_MOVE, motionEvent);
                        $jacocoInit[98] = true;
                        break;
                    case 3:
                        finishDisallowInterceptTouchEvent(view);
                        $jacocoInit[102] = true;
                        $jacocoInit[103] = true;
                        onTouchEvent = onTouchEvent | handleMotionEvent(WXGestureType.LowLevelGesture.ACTION_CANCEL, motionEvent) | handlePanMotionEvent(motionEvent);
                        $jacocoInit[104] = true;
                        break;
                    default:
                        $jacocoInit[89] = true;
                        break;
                }
                if (!hasStopPropagation(this.component)) {
                    $jacocoInit[105] = true;
                } else {
                    $jacocoInit[106] = true;
                    ViewGroup viewGroup = (ViewGroup) view.getParent();
                    if (viewGroup == null) {
                        $jacocoInit[107] = true;
                        z = false;
                    } else {
                        $jacocoInit[108] = true;
                        if (shouldBubbleTouchEvent(motionEvent)) {
                            $jacocoInit[109] = true;
                            z = false;
                        } else {
                            $jacocoInit[110] = true;
                            z = true;
                        }
                        viewGroup.requestDisallowInterceptTouchEvent(z);
                        $jacocoInit[111] = true;
                    }
                    if (this.component.getParent() == null) {
                        $jacocoInit[112] = true;
                    } else {
                        $jacocoInit[113] = true;
                        this.component.getParent().requestDisallowInterceptTouchEvent(z);
                        $jacocoInit[114] = true;
                    }
                    if (!this.mIsTouchEventConsumed) {
                        $jacocoInit[115] = true;
                    } else if (!WXUtils.getBoolean(this.component.getAttrs().get("cancelTouchOnConsume"), false).booleanValue()) {
                        $jacocoInit[116] = true;
                    } else {
                        $jacocoInit[117] = true;
                        motionEvent.setAction(3);
                        $jacocoInit[118] = true;
                    }
                }
                $jacocoInit[119] = true;
                return onTouchEvent;
            } catch (Exception e) {
                $jacocoInit[120] = true;
                WXLogUtils.e("Gesture RunTime Error ", (Throwable) e);
                $jacocoInit[121] = true;
                return false;
            }
        } else {
            this.requestDisallowInterceptTouchEvent = false;
            $jacocoInit[80] = true;
            return false;
        }
    }

    private String getPanEventAction(MotionEvent motionEvent) {
        boolean[] $jacocoInit = $jacocoInit();
        switch (motionEvent.getAction()) {
            case 0:
                $jacocoInit[122] = true;
                return "start";
            case 1:
                $jacocoInit[124] = true;
                return "end";
            case 2:
                $jacocoInit[123] = true;
                return MOVE;
            case 3:
                $jacocoInit[125] = true;
                return "end";
            default:
                $jacocoInit[126] = true;
                return "unknown";
        }
    }

    private void finishDisallowInterceptTouchEvent(View view) {
        boolean[] $jacocoInit = $jacocoInit();
        if (view.getParent() == null) {
            $jacocoInit[127] = true;
        } else {
            $jacocoInit[128] = true;
            view.getParent().requestDisallowInterceptTouchEvent(false);
            $jacocoInit[129] = true;
        }
        $jacocoInit[130] = true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x003d  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00b1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean handlePanMotionEvent(android.view.MotionEvent r8) {
        /*
            r7 = this;
            boolean[] r0 = $jacocoInit()
            com.taobao.weex.ui.view.gesture.WXGestureType r1 = r7.mPendingPan
            r2 = 0
            r3 = 1
            if (r1 != 0) goto L_0x000f
            r8 = 131(0x83, float:1.84E-43)
            r0[r8] = r3
            return r2
        L_0x000f:
            com.taobao.weex.ui.view.gesture.WXGestureType r1 = r7.mPendingPan
            com.taobao.weex.ui.view.gesture.WXGestureType$HighLevelGesture r4 = com.taobao.weex.ui.view.gesture.WXGestureType.HighLevelGesture.HORIZONTALPAN
            r5 = 0
            if (r1 != r4) goto L_0x001b
            r1 = 132(0x84, float:1.85E-43)
            r0[r1] = r3
            goto L_0x002b
        L_0x001b:
            com.taobao.weex.ui.view.gesture.WXGestureType r1 = r7.mPendingPan
            com.taobao.weex.ui.view.gesture.WXGestureType$HighLevelGesture r4 = com.taobao.weex.ui.view.gesture.WXGestureType.HighLevelGesture.VERTICALPAN
            if (r1 == r4) goto L_0x0027
            r1 = 133(0x85, float:1.86E-43)
            r0[r1] = r3
            r1 = r5
            goto L_0x0033
        L_0x0027:
            r1 = 134(0x86, float:1.88E-43)
            r0[r1] = r3
        L_0x002b:
            java.lang.String r1 = r7.getPanEventAction(r8)
            r4 = 135(0x87, float:1.89E-43)
            r0[r4] = r3
        L_0x0033:
            com.taobao.weex.ui.component.WXComponent r4 = r7.component
            com.taobao.weex.ui.view.gesture.WXGestureType r6 = r7.mPendingPan
            boolean r4 = r4.containsGesture(r6)
            if (r4 == 0) goto L_0x00b1
            r2 = 136(0x88, float:1.9E-43)
            r0[r2] = r3
            boolean r2 = r7.mIsPreventMoveEvent
            if (r2 != 0) goto L_0x004a
            r2 = 137(0x89, float:1.92E-43)
            r0[r2] = r3
            goto L_0x0056
        L_0x004a:
            java.lang.String r2 = "move"
            boolean r2 = r2.equals(r1)
            if (r2 != 0) goto L_0x00ac
            r2 = 138(0x8a, float:1.93E-43)
            r0[r2] = r3
        L_0x0056:
            java.util.List r1 = r7.createMultipleFireEventParam(r8, r1)
            r2 = 140(0x8c, float:1.96E-43)
            r0[r2] = r3
            java.util.Iterator r1 = r1.iterator()
            r2 = 141(0x8d, float:1.98E-43)
            r0[r2] = r3
        L_0x0066:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0086
            java.lang.Object r2 = r1.next()
            java.util.Map r2 = (java.util.Map) r2
            r4 = 142(0x8e, float:1.99E-43)
            r0[r4] = r3
            com.taobao.weex.ui.component.WXComponent r4 = r7.component
            com.taobao.weex.ui.view.gesture.WXGestureType r6 = r7.mPendingPan
            java.lang.String r6 = r6.toString()
            r4.fireEvent(r6, r2)
            r2 = 143(0x8f, float:2.0E-43)
            r0[r2] = r3
            goto L_0x0066
        L_0x0086:
            int r1 = r8.getAction()
            if (r1 != r3) goto L_0x0091
            r8 = 144(0x90, float:2.02E-43)
            r0[r8] = r3
            goto L_0x00a1
        L_0x0091:
            int r8 = r8.getAction()
            r1 = 3
            if (r8 == r1) goto L_0x009d
            r8 = 145(0x91, float:2.03E-43)
            r0[r8] = r3
            goto L_0x00a7
        L_0x009d:
            r8 = 146(0x92, float:2.05E-43)
            r0[r8] = r3
        L_0x00a1:
            r7.mPendingPan = r5
            r8 = 147(0x93, float:2.06E-43)
            r0[r8] = r3
        L_0x00a7:
            r8 = 148(0x94, float:2.07E-43)
            r0[r8] = r3
            return r3
        L_0x00ac:
            r8 = 139(0x8b, float:1.95E-43)
            r0[r8] = r3
            return r3
        L_0x00b1:
            r8 = 149(0x95, float:2.09E-43)
            r0[r8] = r3
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.view.gesture.WXGesture.handlePanMotionEvent(android.view.MotionEvent):boolean");
    }

    private boolean handleMotionEvent(WXGestureType wXGestureType, MotionEvent motionEvent) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.component.containsGesture(wXGestureType)) {
            $jacocoInit[150] = true;
            List<Map<String, Object>> createMultipleFireEventParam = createMultipleFireEventParam(motionEvent, (String) null);
            $jacocoInit[151] = true;
            $jacocoInit[152] = true;
            for (Map<String, Object> fireEvent : createMultipleFireEventParam) {
                $jacocoInit[153] = true;
                this.component.fireEvent(wXGestureType.toString(), fireEvent);
                $jacocoInit[154] = true;
            }
            $jacocoInit[155] = true;
            return true;
        }
        $jacocoInit[156] = true;
        return false;
    }

    private List<Map<String, Object>> createMultipleFireEventParam(MotionEvent motionEvent, String str) {
        boolean[] $jacocoInit = $jacocoInit();
        ArrayList arrayList = new ArrayList(motionEvent.getHistorySize() + 1);
        $jacocoInit[157] = true;
        arrayList.add(createFireEventParam(motionEvent, -1, str));
        $jacocoInit[158] = true;
        return arrayList;
    }

    private List<Map<String, Object>> getHistoricalEvents(MotionEvent motionEvent) {
        boolean[] $jacocoInit = $jacocoInit();
        ArrayList arrayList = new ArrayList(motionEvent.getHistorySize());
        $jacocoInit[159] = true;
        if (motionEvent.getActionMasked() != 2) {
            $jacocoInit[160] = true;
        } else {
            $jacocoInit[161] = true;
            int i = 0;
            $jacocoInit[162] = true;
            while (i < motionEvent.getHistorySize()) {
                $jacocoInit[164] = true;
                Map<String, Object> createFireEventParam = createFireEventParam(motionEvent, i, (String) null);
                $jacocoInit[165] = true;
                arrayList.add(createFireEventParam);
                i++;
                $jacocoInit[166] = true;
            }
            $jacocoInit[163] = true;
        }
        $jacocoInit[167] = true;
        return arrayList;
    }

    private Map<String, Object> createFireEventParam(MotionEvent motionEvent, int i, String str) {
        boolean[] $jacocoInit = $jacocoInit();
        JSONArray jSONArray = new JSONArray(motionEvent.getPointerCount());
        $jacocoInit[168] = true;
        if (motionEvent.getActionMasked() == 2) {
            $jacocoInit[169] = true;
            int i2 = 0;
            $jacocoInit[170] = true;
            while (i2 < motionEvent.getPointerCount()) {
                $jacocoInit[171] = true;
                jSONArray.add(createJSONObject(motionEvent, i, i2));
                i2++;
                $jacocoInit[172] = true;
            }
            $jacocoInit[173] = true;
        } else if (!isPointerNumChanged(motionEvent)) {
            $jacocoInit[174] = true;
        } else {
            $jacocoInit[175] = true;
            int actionIndex = motionEvent.getActionIndex();
            $jacocoInit[176] = true;
            jSONArray.add(createJSONObject(motionEvent, -1, actionIndex));
            $jacocoInit[177] = true;
        }
        HashMap hashMap = new HashMap();
        $jacocoInit[178] = true;
        hashMap.put("changedTouches", jSONArray);
        if (str == null) {
            $jacocoInit[179] = true;
        } else {
            $jacocoInit[180] = true;
            hashMap.put("state", str);
            $jacocoInit[181] = true;
        }
        $jacocoInit[182] = true;
        return hashMap;
    }

    private boolean isPointerNumChanged(MotionEvent motionEvent) {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (motionEvent.getActionMasked() == 0) {
            $jacocoInit[183] = true;
        } else {
            $jacocoInit[184] = true;
            if (motionEvent.getActionMasked() == 5) {
                $jacocoInit[185] = true;
            } else {
                $jacocoInit[186] = true;
                if (motionEvent.getActionMasked() == 1) {
                    $jacocoInit[187] = true;
                } else {
                    $jacocoInit[188] = true;
                    if (motionEvent.getActionMasked() == 6) {
                        $jacocoInit[189] = true;
                    } else {
                        $jacocoInit[190] = true;
                        if (motionEvent.getActionMasked() == 3) {
                            $jacocoInit[191] = true;
                        } else {
                            z = false;
                            $jacocoInit[193] = true;
                            $jacocoInit[194] = true;
                            return z;
                        }
                    }
                }
            }
        }
        $jacocoInit[192] = true;
        z = true;
        $jacocoInit[194] = true;
        return z;
    }

    private boolean containsSimplePan() {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.component.containsGesture(WXGestureType.HighLevelGesture.PAN_START)) {
            $jacocoInit[195] = true;
        } else {
            WXComponent wXComponent = this.component;
            WXGestureType.HighLevelGesture highLevelGesture = WXGestureType.HighLevelGesture.PAN_MOVE;
            $jacocoInit[196] = true;
            if (wXComponent.containsGesture(highLevelGesture)) {
                $jacocoInit[197] = true;
            } else {
                WXComponent wXComponent2 = this.component;
                WXGestureType.HighLevelGesture highLevelGesture2 = WXGestureType.HighLevelGesture.PAN_END;
                $jacocoInit[198] = true;
                if (wXComponent2.containsGesture(highLevelGesture2)) {
                    $jacocoInit[199] = true;
                } else {
                    z = false;
                    $jacocoInit[201] = true;
                    $jacocoInit[202] = true;
                    return z;
                }
            }
        }
        $jacocoInit[200] = true;
        z = true;
        $jacocoInit[202] = true;
        return z;
    }

    private JSONObject createJSONObject(MotionEvent motionEvent, int i, int i2) {
        PointF pointF;
        PointF pointF2;
        boolean[] $jacocoInit = $jacocoInit();
        if (i == -1) {
            $jacocoInit[203] = true;
            PointF eventLocInPageCoordinate = getEventLocInPageCoordinate(motionEvent, i2);
            $jacocoInit[204] = true;
            PointF eventLocInScreenCoordinate = getEventLocInScreenCoordinate(motionEvent, i2);
            $jacocoInit[205] = true;
            PointF pointF3 = eventLocInScreenCoordinate;
            pointF2 = eventLocInPageCoordinate;
            pointF = pointF3;
        } else {
            pointF2 = getEventLocInPageCoordinate(motionEvent, i2, i);
            $jacocoInit[206] = true;
            pointF = getEventLocInScreenCoordinate(motionEvent, i2, i);
            $jacocoInit[207] = true;
        }
        JSONObject createJSONObject = createJSONObject(pointF, pointF2, (float) motionEvent.getPointerId(i2));
        $jacocoInit[208] = true;
        float pressure = motionEvent.getPressure();
        if (pressure <= 0.0f) {
            $jacocoInit[209] = true;
        } else if (pressure >= 1.0f) {
            $jacocoInit[210] = true;
        } else {
            $jacocoInit[211] = true;
            createJSONObject.put("force", (Object) Float.valueOf(motionEvent.getPressure()));
            $jacocoInit[212] = true;
        }
        $jacocoInit[213] = true;
        return createJSONObject;
    }

    @NonNull
    private JSONObject createJSONObject(PointF pointF, PointF pointF2, float f) {
        boolean[] $jacocoInit = $jacocoInit();
        JSONObject jSONObject = new JSONObject();
        $jacocoInit[214] = true;
        jSONObject.put(WXGestureType.GestureInfo.PAGE_X, (Object) Float.valueOf(pointF2.x));
        $jacocoInit[215] = true;
        jSONObject.put(WXGestureType.GestureInfo.PAGE_Y, (Object) Float.valueOf(pointF2.y));
        $jacocoInit[216] = true;
        jSONObject.put(WXGestureType.GestureInfo.SCREEN_X, (Object) Float.valueOf(pointF.x));
        $jacocoInit[217] = true;
        jSONObject.put(WXGestureType.GestureInfo.SCREEN_Y, (Object) Float.valueOf(pointF.y));
        $jacocoInit[218] = true;
        jSONObject.put(WXGestureType.GestureInfo.POINTER_ID, (Object) Float.valueOf(f));
        $jacocoInit[219] = true;
        return jSONObject;
    }

    private PointF getEventLocInScreenCoordinate(MotionEvent motionEvent, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        PointF eventLocInScreenCoordinate = getEventLocInScreenCoordinate(motionEvent, i, -1);
        $jacocoInit[220] = true;
        return eventLocInScreenCoordinate;
    }

    private PointF getEventLocInScreenCoordinate(MotionEvent motionEvent, int i, int i2) {
        float f;
        float f2;
        boolean[] $jacocoInit = $jacocoInit();
        if (i2 == -1) {
            $jacocoInit[221] = true;
            f = motionEvent.getX(i);
            $jacocoInit[222] = true;
            f2 = motionEvent.getY(i);
            $jacocoInit[223] = true;
        } else {
            float historicalX = motionEvent.getHistoricalX(i, i2);
            $jacocoInit[224] = true;
            f2 = motionEvent.getHistoricalY(i, i2);
            $jacocoInit[225] = true;
            f = historicalX;
        }
        PointF eventLocInScreenCoordinate = getEventLocInScreenCoordinate(f, f2);
        $jacocoInit[226] = true;
        return eventLocInScreenCoordinate;
    }

    @NonNull
    private PointF getEventLocInScreenCoordinate(float f, float f2) {
        boolean[] $jacocoInit = $jacocoInit();
        this.globalRect.set(0, 0, 0, 0);
        $jacocoInit[227] = true;
        this.globalOffset.set(0, 0);
        $jacocoInit[228] = true;
        this.globalEventOffset.set((int) f, (int) f2);
        $jacocoInit[229] = true;
        this.component.getRealView().getGlobalVisibleRect(this.globalRect, this.globalOffset);
        $jacocoInit[230] = true;
        this.globalEventOffset.offset(this.globalOffset.x, this.globalOffset.y);
        $jacocoInit[231] = true;
        float webPxByWidth = WXViewUtils.getWebPxByWidth((float) this.globalEventOffset.x, this.component.getInstance().getInstanceViewPortWidth());
        WXComponent wXComponent = this.component;
        $jacocoInit[232] = true;
        PointF pointF = new PointF(webPxByWidth, WXViewUtils.getWebPxByWidth((float) this.globalEventOffset.y, wXComponent.getInstance().getInstanceViewPortWidth()));
        $jacocoInit[233] = true;
        return pointF;
    }

    private PointF getEventLocInPageCoordinate(MotionEvent motionEvent, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        PointF eventLocInPageCoordinate = getEventLocInPageCoordinate(motionEvent, i, -1);
        $jacocoInit[234] = true;
        return eventLocInPageCoordinate;
    }

    private PointF getEventLocInPageCoordinate(MotionEvent motionEvent, int i, int i2) {
        float f;
        float f2;
        boolean[] $jacocoInit = $jacocoInit();
        if (i2 == -1) {
            $jacocoInit[235] = true;
            f = motionEvent.getX(i);
            $jacocoInit[236] = true;
            f2 = motionEvent.getY(i);
            $jacocoInit[237] = true;
        } else {
            float historicalX = motionEvent.getHistoricalX(i, i2);
            $jacocoInit[238] = true;
            f2 = motionEvent.getHistoricalY(i, i2);
            $jacocoInit[239] = true;
            f = historicalX;
        }
        PointF eventLocInPageCoordinate = getEventLocInPageCoordinate(f, f2);
        $jacocoInit[240] = true;
        return eventLocInPageCoordinate;
    }

    @NonNull
    private PointF getEventLocInPageCoordinate(float f, float f2) {
        boolean[] $jacocoInit = $jacocoInit();
        this.locEventOffset.set(f, f2);
        $jacocoInit[241] = true;
        this.locLeftTop.set(0.0f, 0.0f);
        $jacocoInit[242] = true;
        this.component.computeVisiblePointInViewCoordinate(this.locLeftTop);
        $jacocoInit[243] = true;
        this.locEventOffset.offset(this.locLeftTop.x, this.locLeftTop.y);
        $jacocoInit[244] = true;
        float webPxByWidth = WXViewUtils.getWebPxByWidth(this.locEventOffset.x, this.component.getInstance().getInstanceViewPortWidth());
        float f3 = this.locEventOffset.y;
        WXComponent wXComponent = this.component;
        $jacocoInit[245] = true;
        PointF pointF = new PointF(webPxByWidth, WXViewUtils.getWebPxByWidth(f3, wXComponent.getInstance().getInstanceViewPortWidth()));
        $jacocoInit[246] = true;
        return pointF;
    }

    private static class GestureHandler extends Handler {
        private static transient /* synthetic */ boolean[] $jacocoData;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(-4444535209432315093L, "com/taobao/weex/ui/view/gesture/WXGesture$GestureHandler", 1);
            $jacocoData = a2;
            return a2;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public GestureHandler() {
            super(Looper.getMainLooper());
            boolean[] $jacocoInit = $jacocoInit();
            $jacocoInit[0] = true;
        }
    }

    public void onLongPress(MotionEvent motionEvent) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!this.component.containsGesture(WXGestureType.HighLevelGesture.LONG_PRESS)) {
            $jacocoInit[247] = true;
        } else {
            $jacocoInit[248] = true;
            List<Map<String, Object>> createMultipleFireEventParam = createMultipleFireEventParam(motionEvent, (String) null);
            $jacocoInit[249] = true;
            WXSDKInstance instance = this.component.getInstance();
            WXComponent wXComponent = this.component;
            $jacocoInit[250] = true;
            String ref = wXComponent.getRef();
            WXGestureType.HighLevelGesture highLevelGesture = WXGestureType.HighLevelGesture.LONG_PRESS;
            $jacocoInit[251] = true;
            String highLevelGesture2 = highLevelGesture.toString();
            $jacocoInit[252] = true;
            $jacocoInit[253] = true;
            instance.fireEvent(ref, highLevelGesture2, createMultipleFireEventParam.get(createMultipleFireEventParam.size() - 1));
            this.mIsTouchEventConsumed = true;
            $jacocoInit[254] = true;
        }
        $jacocoInit[255] = true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:59:0x01de  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x01e3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onScroll(android.view.MotionEvent r10, android.view.MotionEvent r11, float r12, float r13) {
        /*
            r9 = this;
            boolean[] r0 = $jacocoInit()
            r1 = 0
            r2 = 1
            if (r10 != 0) goto L_0x000d
            r10 = 256(0x100, float:3.59E-43)
            r0[r10] = r2
            goto L_0x0013
        L_0x000d:
            if (r11 != 0) goto L_0x0018
            r10 = 257(0x101, float:3.6E-43)
            r0[r10] = r2
        L_0x0013:
            r10 = 258(0x102, float:3.62E-43)
            r0[r10] = r2
            return r1
        L_0x0018:
            float r3 = r11.getX()
            float r4 = r10.getX()
            float r3 = r3 - r4
            float r3 = java.lang.Math.abs(r3)
            r4 = 259(0x103, float:3.63E-43)
            r0[r4] = r2
            float r4 = r11.getY()
            float r5 = r10.getY()
            float r4 = r4 - r5
            float r4 = java.lang.Math.abs(r4)
            int r3 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r3 <= 0) goto L_0x0041
            com.taobao.weex.ui.view.gesture.WXGestureType$HighLevelGesture r3 = com.taobao.weex.ui.view.gesture.WXGestureType.HighLevelGesture.HORIZONTALPAN
            r4 = 260(0x104, float:3.64E-43)
            r0[r4] = r2
            goto L_0x0047
        L_0x0041:
            com.taobao.weex.ui.view.gesture.WXGestureType$HighLevelGesture r3 = com.taobao.weex.ui.view.gesture.WXGestureType.HighLevelGesture.VERTICALPAN
            r4 = 261(0x105, float:3.66E-43)
            r0[r4] = r2
        L_0x0047:
            com.taobao.weex.ui.view.gesture.WXGestureType r4 = r9.mPendingPan
            com.taobao.weex.ui.view.gesture.WXGestureType$HighLevelGesture r5 = com.taobao.weex.ui.view.gesture.WXGestureType.HighLevelGesture.HORIZONTALPAN
            if (r4 != r5) goto L_0x0052
            r10 = 262(0x106, float:3.67E-43)
            r0[r10] = r2
            goto L_0x005c
        L_0x0052:
            com.taobao.weex.ui.view.gesture.WXGestureType r4 = r9.mPendingPan
            com.taobao.weex.ui.view.gesture.WXGestureType$HighLevelGesture r5 = com.taobao.weex.ui.view.gesture.WXGestureType.HighLevelGesture.VERTICALPAN
            if (r4 != r5) goto L_0x0066
            r10 = 263(0x107, float:3.69E-43)
            r0[r10] = r2
        L_0x005c:
            boolean r10 = r9.handlePanMotionEvent(r11)
            r11 = 264(0x108, float:3.7E-43)
            r0[r11] = r2
            goto L_0x01da
        L_0x0066:
            com.taobao.weex.ui.component.WXComponent r4 = r9.component
            boolean r4 = r4.containsGesture(r3)
            r5 = -1
            if (r4 == 0) goto L_0x00c3
            r10 = 265(0x109, float:3.71E-43)
            r0[r10] = r2
            com.taobao.weex.ui.component.WXComponent r10 = r9.component
            android.view.View r10 = r10.getRealView()
            android.view.ViewParent r10 = r10.getParent()
            if (r10 != 0) goto L_0x0084
            r10 = 266(0x10a, float:3.73E-43)
            r0[r10] = r2
            goto L_0x008f
        L_0x0084:
            r12 = 267(0x10b, float:3.74E-43)
            r0[r12] = r2
            r10.requestDisallowInterceptTouchEvent(r2)
            r10 = 268(0x10c, float:3.76E-43)
            r0[r10] = r2
        L_0x008f:
            com.taobao.weex.ui.view.gesture.WXGestureType r10 = r9.mPendingPan
            if (r10 != 0) goto L_0x0098
            r10 = 269(0x10d, float:3.77E-43)
            r0[r10] = r2
            goto L_0x00a5
        L_0x0098:
            r10 = 270(0x10e, float:3.78E-43)
            r0[r10] = r2
            com.taobao.weex.ui.view.gesture.WXGestureType r10 = r9.mPendingPan
            r9.handleMotionEvent(r10, r11)
            r10 = 271(0x10f, float:3.8E-43)
            r0[r10] = r2
        L_0x00a5:
            r9.mPendingPan = r3
            r10 = 272(0x110, float:3.81E-43)
            r0[r10] = r2
            com.taobao.weex.ui.component.WXComponent r10 = r9.component
            java.lang.String r12 = r3.toString()
            java.lang.String r13 = "start"
            java.util.Map r11 = r9.createFireEventParam(r11, r5, r13)
            r10.fireEvent(r12, r11)
            r10 = 273(0x111, float:3.83E-43)
            r0[r10] = r2
            r10 = 274(0x112, float:3.84E-43)
            r0[r10] = r2
            goto L_0x0125
        L_0x00c3:
            boolean r3 = r9.containsSimplePan()
            r4 = 0
            if (r3 == 0) goto L_0x0128
            r12 = 275(0x113, float:3.85E-43)
            r0[r12] = r2
            long r12 = r9.panDownTime
            long r6 = r10.getEventTime()
            int r3 = (r12 > r6 ? 1 : (r12 == r6 ? 0 : -1))
            if (r3 == 0) goto L_0x0106
            r11 = 276(0x114, float:3.87E-43)
            r0[r11] = r2
            long r11 = r10.getEventTime()
            r9.panDownTime = r11
            com.taobao.weex.ui.view.gesture.WXGestureType$HighLevelGesture r11 = com.taobao.weex.ui.view.gesture.WXGestureType.HighLevelGesture.PAN_END
            r9.mPendingPan = r11
            r11 = 277(0x115, float:3.88E-43)
            r0[r11] = r2
            com.taobao.weex.ui.component.WXComponent r11 = r9.component
            com.taobao.weex.ui.view.gesture.WXGestureType$HighLevelGesture r12 = com.taobao.weex.ui.view.gesture.WXGestureType.HighLevelGesture.PAN_START
            java.lang.String r12 = r12.toString()
            r13 = 278(0x116, float:3.9E-43)
            r0[r13] = r2
            java.util.Map r10 = r9.createFireEventParam(r10, r5, r4)
            r13 = 279(0x117, float:3.91E-43)
            r0[r13] = r2
            r11.fireEvent(r12, r10)
            r10 = 280(0x118, float:3.92E-43)
            r0[r10] = r2
            goto L_0x0121
        L_0x0106:
            com.taobao.weex.ui.component.WXComponent r10 = r9.component
            com.taobao.weex.ui.view.gesture.WXGestureType$HighLevelGesture r12 = com.taobao.weex.ui.view.gesture.WXGestureType.HighLevelGesture.PAN_MOVE
            java.lang.String r12 = r12.toString()
            r13 = 281(0x119, float:3.94E-43)
            r0[r13] = r2
            java.util.Map r11 = r9.createFireEventParam(r11, r5, r4)
            r13 = 282(0x11a, float:3.95E-43)
            r0[r13] = r2
            r10.fireEvent(r12, r11)
            r10 = 283(0x11b, float:3.97E-43)
            r0[r10] = r2
        L_0x0121:
            r10 = 284(0x11c, float:3.98E-43)
            r0[r10] = r2
        L_0x0125:
            r10 = 1
            goto L_0x01da
        L_0x0128:
            com.taobao.weex.ui.component.WXComponent r3 = r9.component
            com.taobao.weex.ui.view.gesture.WXGestureType$HighLevelGesture r5 = com.taobao.weex.ui.view.gesture.WXGestureType.HighLevelGesture.SWIPE
            boolean r3 = r3.containsGesture(r5)
            if (r3 != 0) goto L_0x0137
            r10 = 285(0x11d, float:4.0E-43)
            r0[r10] = r2
            goto L_0x0149
        L_0x0137:
            r3 = 286(0x11e, float:4.01E-43)
            r0[r3] = r2
            long r5 = r9.swipeDownTime
            long r7 = r10.getEventTime()
            int r3 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r3 != 0) goto L_0x014c
            r10 = 287(0x11f, float:4.02E-43)
            r0[r10] = r2
        L_0x0149:
            r10 = 0
            goto L_0x01da
        L_0x014c:
            r3 = 288(0x120, float:4.04E-43)
            r0[r3] = r2
            long r5 = r10.getEventTime()
            r9.swipeDownTime = r5
            r10 = 289(0x121, float:4.05E-43)
            r0[r10] = r2
            java.util.List r10 = r9.createMultipleFireEventParam(r11, r4)
            r11 = 290(0x122, float:4.06E-43)
            r0[r11] = r2
            int r11 = r10.size()
            int r11 = r11 - r2
            java.lang.Object r10 = r10.get(r11)
            java.util.Map r10 = (java.util.Map) r10
            r11 = 291(0x123, float:4.08E-43)
            r0[r11] = r2
            float r11 = java.lang.Math.abs(r12)
            float r3 = java.lang.Math.abs(r13)
            int r11 = (r11 > r3 ? 1 : (r11 == r3 ? 0 : -1))
            r3 = 0
            if (r11 <= 0) goto L_0x019d
            r11 = 292(0x124, float:4.09E-43)
            r0[r11] = r2
            java.lang.String r11 = "direction"
            int r12 = (r12 > r3 ? 1 : (r12 == r3 ? 0 : -1))
            if (r12 <= 0) goto L_0x018f
            java.lang.String r12 = "left"
            r13 = 293(0x125, float:4.1E-43)
            r0[r13] = r2
            goto L_0x0195
        L_0x018f:
            java.lang.String r12 = "right"
            r13 = 294(0x126, float:4.12E-43)
            r0[r13] = r2
        L_0x0195:
            r10.put(r11, r12)
            r11 = 295(0x127, float:4.13E-43)
            r0[r11] = r2
            goto L_0x01b7
        L_0x019d:
            java.lang.String r11 = "direction"
            int r12 = (r13 > r3 ? 1 : (r13 == r3 ? 0 : -1))
            if (r12 <= 0) goto L_0x01aa
            java.lang.String r12 = "up"
            r13 = 296(0x128, float:4.15E-43)
            r0[r13] = r2
            goto L_0x01b0
        L_0x01aa:
            java.lang.String r12 = "down"
            r13 = 297(0x129, float:4.16E-43)
            r0[r13] = r2
        L_0x01b0:
            r10.put(r11, r12)
            r11 = 298(0x12a, float:4.18E-43)
            r0[r11] = r2
        L_0x01b7:
            com.taobao.weex.ui.component.WXComponent r11 = r9.component
            com.taobao.weex.WXSDKInstance r11 = r11.getInstance()
            com.taobao.weex.ui.component.WXComponent r12 = r9.component
            java.lang.String r12 = r12.getRef()
            com.taobao.weex.ui.view.gesture.WXGestureType$HighLevelGesture r13 = com.taobao.weex.ui.view.gesture.WXGestureType.HighLevelGesture.SWIPE
            r3 = 299(0x12b, float:4.19E-43)
            r0[r3] = r2
            java.lang.String r13 = r13.toString()
            r3 = 300(0x12c, float:4.2E-43)
            r0[r3] = r2
            r11.fireEvent(r12, r13, r10)
            r10 = 301(0x12d, float:4.22E-43)
            r0[r10] = r2
            goto L_0x0125
        L_0x01da:
            boolean r11 = r9.mIsTouchEventConsumed
            if (r11 == 0) goto L_0x01e3
            r11 = 302(0x12e, float:4.23E-43)
            r0[r11] = r2
            goto L_0x01e9
        L_0x01e3:
            if (r10 == 0) goto L_0x01ef
            r11 = 303(0x12f, float:4.25E-43)
            r0[r11] = r2
        L_0x01e9:
            r11 = 304(0x130, float:4.26E-43)
            r0[r11] = r2
            r1 = 1
            goto L_0x01f3
        L_0x01ef:
            r11 = 305(0x131, float:4.27E-43)
            r0[r11] = r2
        L_0x01f3:
            r9.mIsTouchEventConsumed = r1
            r11 = 306(0x132, float:4.29E-43)
            r0[r11] = r2
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.view.gesture.WXGesture.onScroll(android.view.MotionEvent, android.view.MotionEvent, float, float):boolean");
    }

    public boolean onDown(MotionEvent motionEvent) {
        $jacocoInit()[307] = true;
        return true;
    }

    public boolean isRequestDisallowInterceptTouchEvent() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = this.requestDisallowInterceptTouchEvent;
        $jacocoInit[308] = true;
        return z;
    }

    public void setRequestDisallowInterceptTouchEvent(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        this.requestDisallowInterceptTouchEvent = z;
        $jacocoInit[309] = true;
    }
}
