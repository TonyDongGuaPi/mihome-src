package com.taobao.weex.ui.view.gesture;

import com.taobao.weex.ui.component.list.BasicListComponent;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public interface WXGestureType {

    public enum LowLevelGesture implements WXGestureType {
        ACTION_DOWN("touchstart"),
        ACTION_MOVE("touchmove"),
        ACTION_UP("touchend"),
        ACTION_CANCEL("touchcancel");
        
        private String description;

        static {
            boolean[] $jacocoInit;
            $jacocoInit[7] = true;
        }

        private LowLevelGesture(String str) {
            boolean[] $jacocoInit = $jacocoInit();
            this.description = str;
            $jacocoInit[2] = true;
        }

        public String toString() {
            boolean[] $jacocoInit = $jacocoInit();
            String str = this.description;
            $jacocoInit[3] = true;
            return str;
        }
    }

    public enum HighLevelGesture implements WXGestureType {
        SWIPE("swipe"),
        LONG_PRESS(BasicListComponent.DragTriggerType.LONG_PRESS),
        PAN_START("panstart"),
        PAN_MOVE("panmove"),
        PAN_END("panend"),
        HORIZONTALPAN("horizontalpan"),
        VERTICALPAN("verticalpan");
        
        private String description;

        static {
            boolean[] $jacocoInit;
            $jacocoInit[10] = true;
        }

        private HighLevelGesture(String str) {
            boolean[] $jacocoInit = $jacocoInit();
            this.description = str;
            $jacocoInit[2] = true;
        }

        public String toString() {
            boolean[] $jacocoInit = $jacocoInit();
            String str = this.description;
            $jacocoInit[3] = true;
            return str;
        }
    }

    public static class GestureInfo {
        private static transient /* synthetic */ boolean[] $jacocoData = null;
        public static final String DIRECTION = "direction";
        public static final String HISTORICAL_XY = "changedTouches";
        public static final String PAGE_X = "pageX";
        public static final String PAGE_Y = "pageY";
        public static final String POINTER_ID = "identifier";
        public static final String SCREEN_X = "screenX";
        public static final String SCREEN_Y = "screenY";
        public static final String STATE = "state";

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(576732173506657516L, "com/taobao/weex/ui/view/gesture/WXGestureType$GestureInfo", 1);
            $jacocoData = a2;
            return a2;
        }

        public GestureInfo() {
            $jacocoInit()[0] = true;
        }
    }
}
