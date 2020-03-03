package com.facebook.react.uimanager;

import java.util.Arrays;
import java.util.HashSet;

public class ViewProps {
    public static final String ACCESSIBILITY_ACTIONS = "accessibilityActions";
    public static final String ACCESSIBILITY_HINT = "accessibilityHint";
    public static final String ACCESSIBILITY_LABEL = "accessibilityLabel";
    public static final String ACCESSIBILITY_LIVE_REGION = "accessibilityLiveRegion";
    public static final String ACCESSIBILITY_ROLE = "accessibilityRole";
    public static final String ACCESSIBILITY_STATE = "accessibilityState";
    public static final String ACCESSIBILITY_STATES = "accessibilityStates";
    public static final String ALIGN_CONTENT = "alignContent";
    public static final String ALIGN_ITEMS = "alignItems";
    public static final String ALIGN_SELF = "alignSelf";
    public static final String ALLOW_FONT_SCALING = "allowFontScaling";
    public static final String ASPECT_RATIO = "aspectRatio";
    public static final String AUTO = "auto";
    public static final String BACKGROUND_COLOR = "backgroundColor";
    public static final String BORDER_BOTTOM_COLOR = "borderBottomColor";
    public static final String BORDER_BOTTOM_END_RADIUS = "borderBottomEndRadius";
    public static final String BORDER_BOTTOM_LEFT_RADIUS = "borderBottomLeftRadius";
    public static final String BORDER_BOTTOM_RIGHT_RADIUS = "borderBottomRightRadius";
    public static final String BORDER_BOTTOM_START_RADIUS = "borderBottomStartRadius";
    public static final String BORDER_BOTTOM_WIDTH = "borderBottomWidth";
    public static final String BORDER_COLOR = "borderColor";
    public static final String BORDER_END_COLOR = "borderEndColor";
    public static final String BORDER_END_WIDTH = "borderEndWidth";
    public static final String BORDER_LEFT_COLOR = "borderLeftColor";
    public static final String BORDER_LEFT_WIDTH = "borderLeftWidth";
    public static final String BORDER_RADIUS = "borderRadius";
    public static final String BORDER_RIGHT_COLOR = "borderRightColor";
    public static final String BORDER_RIGHT_WIDTH = "borderRightWidth";
    public static final int[] BORDER_SPACING_TYPES = {8, 4, 5, 1, 3, 0, 2};
    public static final String BORDER_START_COLOR = "borderStartColor";
    public static final String BORDER_START_WIDTH = "borderStartWidth";
    public static final String BORDER_TOP_COLOR = "borderTopColor";
    public static final String BORDER_TOP_END_RADIUS = "borderTopEndRadius";
    public static final String BORDER_TOP_LEFT_RADIUS = "borderTopLeftRadius";
    public static final String BORDER_TOP_RIGHT_RADIUS = "borderTopRightRadius";
    public static final String BORDER_TOP_START_RADIUS = "borderTopStartRadius";
    public static final String BORDER_TOP_WIDTH = "borderTopWidth";
    public static final String BORDER_WIDTH = "borderWidth";
    public static final String BOTTOM = "bottom";
    public static final String BOX_NONE = "box-none";
    public static final String COLLAPSABLE = "collapsable";
    public static final String COLOR = "color";
    public static final String DISPLAY = "display";
    public static final String ELEVATION = "elevation";
    public static final String ELLIPSIZE_MODE = "ellipsizeMode";
    public static final String ENABLED = "enabled";
    public static final String END = "end";
    public static final String FLEX = "flex";
    public static final String FLEX_BASIS = "flexBasis";
    public static final String FLEX_DIRECTION = "flexDirection";
    public static final String FLEX_GROW = "flexGrow";
    public static final String FLEX_SHRINK = "flexShrink";
    public static final String FLEX_WRAP = "flexWrap";
    public static final String FONT_FAMILY = "fontFamily";
    public static final String FONT_SIZE = "fontSize";
    public static final String FONT_STYLE = "fontStyle";
    public static final String FONT_WEIGHT = "fontWeight";
    public static final String HEIGHT = "height";
    public static final String HIDDEN = "hidden";
    public static final String IMPORTANT_FOR_ACCESSIBILITY = "importantForAccessibility";
    public static final String INCLUDE_FONT_PADDING = "includeFontPadding";
    public static final String JUSTIFY_CONTENT = "justifyContent";
    private static final HashSet<String> LAYOUT_ONLY_PROPS = new HashSet<>(Arrays.asList(new String[]{"alignSelf", "alignItems", COLLAPSABLE, "flex", FLEX_BASIS, "flexDirection", FLEX_GROW, FLEX_SHRINK, "flexWrap", "justifyContent", ALIGN_CONTENT, "display", "position", "right", "top", "bottom", "left", "start", "end", "width", "height", "minWidth", "maxWidth", "minHeight", "maxHeight", "margin", MARGIN_VERTICAL, MARGIN_HORIZONTAL, "marginLeft", "marginRight", "marginTop", "marginBottom", MARGIN_START, MARGIN_END, "padding", PADDING_VERTICAL, PADDING_HORIZONTAL, "paddingLeft", "paddingRight", "paddingTop", "paddingBottom", PADDING_START, PADDING_END}));
    public static final String LEFT = "left";
    public static final String LETTER_SPACING = "letterSpacing";
    public static final String LINE_HEIGHT = "lineHeight";
    public static final String MARGIN = "margin";
    public static final String MARGIN_BOTTOM = "marginBottom";
    public static final String MARGIN_END = "marginEnd";
    public static final String MARGIN_HORIZONTAL = "marginHorizontal";
    public static final String MARGIN_LEFT = "marginLeft";
    public static final String MARGIN_RIGHT = "marginRight";
    public static final String MARGIN_START = "marginStart";
    public static final String MARGIN_TOP = "marginTop";
    public static final String MARGIN_VERTICAL = "marginVertical";
    public static final String MAX_FONT_SIZE_MULTIPLIER = "maxFontSizeMultiplier";
    public static final String MAX_HEIGHT = "maxHeight";
    public static final String MAX_WIDTH = "maxWidth";
    public static final String MIN_HEIGHT = "minHeight";
    public static final String MIN_WIDTH = "minWidth";
    public static final String NATIVE_ID = "nativeID";
    public static final String NEEDS_OFFSCREEN_ALPHA_COMPOSITING = "needsOffscreenAlphaCompositing";
    public static final String NONE = "none";
    public static final String NUMBER_OF_LINES = "numberOfLines";
    public static final String ON = "on";
    public static final String ON_LAYOUT = "onLayout";
    public static final String OPACITY = "opacity";
    public static final String OVERFLOW = "overflow";
    public static final String PADDING = "padding";
    public static final String PADDING_BOTTOM = "paddingBottom";
    public static final String PADDING_END = "paddingEnd";
    public static final String PADDING_HORIZONTAL = "paddingHorizontal";
    public static final String PADDING_LEFT = "paddingLeft";
    public static final int[] PADDING_MARGIN_SPACING_TYPES = {8, 7, 6, 4, 5, 1, 3, 0, 2};
    public static final String PADDING_RIGHT = "paddingRight";
    public static final String PADDING_START = "paddingStart";
    public static final String PADDING_TOP = "paddingTop";
    public static final String PADDING_VERTICAL = "paddingVertical";
    public static final String POINTER_EVENTS = "pointerEvents";
    public static final String POSITION = "position";
    public static final int[] POSITION_SPACING_TYPES = {4, 5, 1, 3};
    public static final String RENDER_TO_HARDWARE_TEXTURE = "renderToHardwareTextureAndroid";
    public static final String RESIZE_METHOD = "resizeMethod";
    public static final String RESIZE_MODE = "resizeMode";
    public static final String RIGHT = "right";
    public static final String ROTATION = "rotation";
    public static final String SCALE_X = "scaleX";
    public static final String SCALE_Y = "scaleY";
    public static final String SCROLL = "scroll";
    public static final String START = "start";
    public static final String TEST_ID = "testID";
    public static final String TEXT_ALIGN = "textAlign";
    public static final String TEXT_ALIGN_VERTICAL = "textAlignVertical";
    public static final String TEXT_BREAK_STRATEGY = "textBreakStrategy";
    public static final String TEXT_DECORATION_LINE = "textDecorationLine";
    public static final String TOP = "top";
    public static final String TRANSFORM = "transform";
    public static final String TRANSLATE_X = "translateX";
    public static final String TRANSLATE_Y = "translateY";
    public static final String VIEW_CLASS_NAME = "RCTView";
    public static final String VISIBLE = "visible";
    public static final String WIDTH = "width";
    public static final String Z_INDEX = "zIndex";

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isLayoutOnly(com.facebook.react.bridge.ReadableMap r5, java.lang.String r6) {
        /*
            java.util.HashSet<java.lang.String> r0 = LAYOUT_ONLY_PROPS
            boolean r0 = r0.contains(r6)
            r1 = 1
            if (r0 == 0) goto L_0x000a
            return r1
        L_0x000a:
            java.lang.String r0 = "pointerEvents"
            boolean r0 = r0.equals(r6)
            r2 = 0
            if (r0 == 0) goto L_0x002a
            java.lang.String r5 = r5.getString(r6)
            java.lang.String r6 = "auto"
            boolean r6 = r6.equals(r5)
            if (r6 != 0) goto L_0x0029
            java.lang.String r6 = "box-none"
            boolean r5 = r6.equals(r5)
            if (r5 == 0) goto L_0x0028
            goto L_0x0029
        L_0x0028:
            r1 = 0
        L_0x0029:
            return r1
        L_0x002a:
            r0 = -1
            int r3 = r6.hashCode()
            switch(r3) {
                case -1989576717: goto L_0x00a8;
                case -1971292586: goto L_0x009d;
                case -1470826662: goto L_0x0093;
                case -1452542531: goto L_0x0088;
                case -1308858324: goto L_0x007e;
                case -1290574193: goto L_0x0073;
                case -1267206133: goto L_0x0069;
                case -242276144: goto L_0x005f;
                case -223992013: goto L_0x0055;
                case 529642498: goto L_0x004a;
                case 741115130: goto L_0x003f;
                case 1349188574: goto L_0x0034;
                default: goto L_0x0032;
            }
        L_0x0032:
            goto L_0x00b2
        L_0x0034:
            java.lang.String r3 = "borderRadius"
            boolean r6 = r6.equals(r3)
            if (r6 == 0) goto L_0x00b2
            r6 = 1
            goto L_0x00b3
        L_0x003f:
            java.lang.String r3 = "borderWidth"
            boolean r6 = r6.equals(r3)
            if (r6 == 0) goto L_0x00b2
            r6 = 6
            goto L_0x00b3
        L_0x004a:
            java.lang.String r3 = "overflow"
            boolean r6 = r6.equals(r3)
            if (r6 == 0) goto L_0x00b2
            r6 = 11
            goto L_0x00b3
        L_0x0055:
            java.lang.String r3 = "borderLeftWidth"
            boolean r6 = r6.equals(r3)
            if (r6 == 0) goto L_0x00b2
            r6 = 7
            goto L_0x00b3
        L_0x005f:
            java.lang.String r3 = "borderLeftColor"
            boolean r6 = r6.equals(r3)
            if (r6 == 0) goto L_0x00b2
            r6 = 2
            goto L_0x00b3
        L_0x0069:
            java.lang.String r3 = "opacity"
            boolean r6 = r6.equals(r3)
            if (r6 == 0) goto L_0x00b2
            r6 = 0
            goto L_0x00b3
        L_0x0073:
            java.lang.String r3 = "borderBottomWidth"
            boolean r6 = r6.equals(r3)
            if (r6 == 0) goto L_0x00b2
            r6 = 10
            goto L_0x00b3
        L_0x007e:
            java.lang.String r3 = "borderBottomColor"
            boolean r6 = r6.equals(r3)
            if (r6 == 0) goto L_0x00b2
            r6 = 5
            goto L_0x00b3
        L_0x0088:
            java.lang.String r3 = "borderTopWidth"
            boolean r6 = r6.equals(r3)
            if (r6 == 0) goto L_0x00b2
            r6 = 8
            goto L_0x00b3
        L_0x0093:
            java.lang.String r3 = "borderTopColor"
            boolean r6 = r6.equals(r3)
            if (r6 == 0) goto L_0x00b2
            r6 = 4
            goto L_0x00b3
        L_0x009d:
            java.lang.String r3 = "borderRightWidth"
            boolean r6 = r6.equals(r3)
            if (r6 == 0) goto L_0x00b2
            r6 = 9
            goto L_0x00b3
        L_0x00a8:
            java.lang.String r3 = "borderRightColor"
            boolean r6 = r6.equals(r3)
            if (r6 == 0) goto L_0x00b2
            r6 = 3
            goto L_0x00b3
        L_0x00b2:
            r6 = -1
        L_0x00b3:
            r3 = 0
            switch(r6) {
                case 0: goto L_0x01b5;
                case 1: goto L_0x0188;
                case 2: goto L_0x0175;
                case 3: goto L_0x0162;
                case 4: goto L_0x014f;
                case 5: goto L_0x013c;
                case 6: goto L_0x0127;
                case 7: goto L_0x0112;
                case 8: goto L_0x00fd;
                case 9: goto L_0x00e8;
                case 10: goto L_0x00d3;
                case 11: goto L_0x00b9;
                default: goto L_0x00b8;
            }
        L_0x00b8:
            return r2
        L_0x00b9:
            java.lang.String r6 = "overflow"
            boolean r6 = r5.isNull(r6)
            if (r6 != 0) goto L_0x00d2
            java.lang.String r6 = "visible"
            java.lang.String r0 = "overflow"
            java.lang.String r5 = r5.getString(r0)
            boolean r5 = r6.equals(r5)
            if (r5 == 0) goto L_0x00d1
            goto L_0x00d2
        L_0x00d1:
            r1 = 0
        L_0x00d2:
            return r1
        L_0x00d3:
            java.lang.String r6 = "borderBottomWidth"
            boolean r6 = r5.isNull(r6)
            if (r6 != 0) goto L_0x00e7
            java.lang.String r6 = "borderBottomWidth"
            double r5 = r5.getDouble(r6)
            int r0 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r0 != 0) goto L_0x00e6
            goto L_0x00e7
        L_0x00e6:
            r1 = 0
        L_0x00e7:
            return r1
        L_0x00e8:
            java.lang.String r6 = "borderRightWidth"
            boolean r6 = r5.isNull(r6)
            if (r6 != 0) goto L_0x00fc
            java.lang.String r6 = "borderRightWidth"
            double r5 = r5.getDouble(r6)
            int r0 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r0 != 0) goto L_0x00fb
            goto L_0x00fc
        L_0x00fb:
            r1 = 0
        L_0x00fc:
            return r1
        L_0x00fd:
            java.lang.String r6 = "borderTopWidth"
            boolean r6 = r5.isNull(r6)
            if (r6 != 0) goto L_0x0111
            java.lang.String r6 = "borderTopWidth"
            double r5 = r5.getDouble(r6)
            int r0 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r0 != 0) goto L_0x0110
            goto L_0x0111
        L_0x0110:
            r1 = 0
        L_0x0111:
            return r1
        L_0x0112:
            java.lang.String r6 = "borderLeftWidth"
            boolean r6 = r5.isNull(r6)
            if (r6 != 0) goto L_0x0126
            java.lang.String r6 = "borderLeftWidth"
            double r5 = r5.getDouble(r6)
            int r0 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r0 != 0) goto L_0x0125
            goto L_0x0126
        L_0x0125:
            r1 = 0
        L_0x0126:
            return r1
        L_0x0127:
            java.lang.String r6 = "borderWidth"
            boolean r6 = r5.isNull(r6)
            if (r6 != 0) goto L_0x013b
            java.lang.String r6 = "borderWidth"
            double r5 = r5.getDouble(r6)
            int r0 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r0 != 0) goto L_0x013a
            goto L_0x013b
        L_0x013a:
            r1 = 0
        L_0x013b:
            return r1
        L_0x013c:
            java.lang.String r6 = "borderBottomColor"
            boolean r6 = r5.isNull(r6)
            if (r6 != 0) goto L_0x014d
            java.lang.String r6 = "borderBottomColor"
            int r5 = r5.getInt(r6)
            if (r5 != 0) goto L_0x014d
            goto L_0x014e
        L_0x014d:
            r1 = 0
        L_0x014e:
            return r1
        L_0x014f:
            java.lang.String r6 = "borderTopColor"
            boolean r6 = r5.isNull(r6)
            if (r6 != 0) goto L_0x0160
            java.lang.String r6 = "borderTopColor"
            int r5 = r5.getInt(r6)
            if (r5 != 0) goto L_0x0160
            goto L_0x0161
        L_0x0160:
            r1 = 0
        L_0x0161:
            return r1
        L_0x0162:
            java.lang.String r6 = "borderRightColor"
            boolean r6 = r5.isNull(r6)
            if (r6 != 0) goto L_0x0173
            java.lang.String r6 = "borderRightColor"
            int r5 = r5.getInt(r6)
            if (r5 != 0) goto L_0x0173
            goto L_0x0174
        L_0x0173:
            r1 = 0
        L_0x0174:
            return r1
        L_0x0175:
            java.lang.String r6 = "borderLeftColor"
            boolean r6 = r5.isNull(r6)
            if (r6 != 0) goto L_0x0186
            java.lang.String r6 = "borderLeftColor"
            int r5 = r5.getInt(r6)
            if (r5 != 0) goto L_0x0186
            goto L_0x0187
        L_0x0186:
            r1 = 0
        L_0x0187:
            return r1
        L_0x0188:
            java.lang.String r6 = "backgroundColor"
            boolean r6 = r5.hasKey(r6)
            if (r6 == 0) goto L_0x0199
            java.lang.String r6 = "backgroundColor"
            int r6 = r5.getInt(r6)
            if (r6 == 0) goto L_0x0199
            return r2
        L_0x0199:
            java.lang.String r6 = "borderWidth"
            boolean r6 = r5.hasKey(r6)
            if (r6 == 0) goto L_0x01b4
            java.lang.String r6 = "borderWidth"
            boolean r6 = r5.isNull(r6)
            if (r6 != 0) goto L_0x01b4
            java.lang.String r6 = "borderWidth"
            double r5 = r5.getDouble(r6)
            int r0 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r0 == 0) goto L_0x01b4
            return r2
        L_0x01b4:
            return r1
        L_0x01b5:
            java.lang.String r6 = "opacity"
            boolean r6 = r5.isNull(r6)
            if (r6 != 0) goto L_0x01cb
            java.lang.String r6 = "opacity"
            double r5 = r5.getDouble(r6)
            r3 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r0 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r0 != 0) goto L_0x01ca
            goto L_0x01cb
        L_0x01ca:
            r1 = 0
        L_0x01cb:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.ViewProps.isLayoutOnly(com.facebook.react.bridge.ReadableMap, java.lang.String):boolean");
    }
}
