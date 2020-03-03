package com.taobao.weex.ui.flat;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.view.View;
import com.taobao.weex.common.Constants;
import com.taobao.weex.common.Destroyable;
import com.taobao.weex.dom.WXAttr;
import com.taobao.weex.dom.WXStyle;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.flat.widget.AndroidViewWidget;
import com.taobao.weex.ui.flat.widget.Widget;
import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

@RestrictTo({RestrictTo.Scope.LIBRARY})
public class FlatGUIContext implements Destroyable {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private Map<WXComponent, AndroidViewWidget> mViewWidgetRegistry;
    private Map<WXComponent, WidgetContainer> mWidgetRegistry = new ArrayMap();
    private Map<Widget, WXComponent> widgetToComponent;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-3990536230104377106L, "com/taobao/weex/ui/flat/FlatGUIContext", 76);
        $jacocoData = a2;
        return a2;
    }

    public FlatGUIContext() {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
        $jacocoInit[1] = true;
        this.mViewWidgetRegistry = new ArrayMap();
        $jacocoInit[2] = true;
        this.widgetToComponent = new ArrayMap();
        $jacocoInit[3] = true;
    }

    public boolean isFlatUIEnabled(WXComponent wXComponent) {
        $jacocoInit()[4] = true;
        return false;
    }

    public void register(@NonNull WXComponent wXComponent, @NonNull WidgetContainer widgetContainer) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!(widgetContainer instanceof FlatComponent)) {
            $jacocoInit[5] = true;
        } else {
            $jacocoInit[6] = true;
            if (!((FlatComponent) widgetContainer).promoteToView(true)) {
                $jacocoInit[7] = true;
                $jacocoInit[10] = true;
            }
            $jacocoInit[8] = true;
        }
        this.mWidgetRegistry.put(wXComponent, widgetContainer);
        $jacocoInit[9] = true;
        $jacocoInit[10] = true;
    }

    public void register(@NonNull WXComponent wXComponent, @NonNull AndroidViewWidget androidViewWidget) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mViewWidgetRegistry.put(wXComponent, androidViewWidget);
        $jacocoInit[11] = true;
    }

    public void register(@NonNull Widget widget, @NonNull WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        this.widgetToComponent.put(widget, wXComponent);
        $jacocoInit[12] = true;
    }

    @Nullable
    public WidgetContainer getFlatComponentAncestor(@NonNull WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        WidgetContainer widgetContainer = this.mWidgetRegistry.get(wXComponent);
        $jacocoInit[13] = true;
        return widgetContainer;
    }

    @Nullable
    public AndroidViewWidget getAndroidViewWidget(@NonNull WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        AndroidViewWidget androidViewWidget = this.mViewWidgetRegistry.get(wXComponent);
        $jacocoInit[14] = true;
        return androidViewWidget;
    }

    public boolean promoteToView(@NonNull WXComponent wXComponent, boolean z, @NonNull Class<? extends WXComponent<?>> cls) {
        boolean z2;
        boolean[] $jacocoInit = $jacocoInit();
        if (!isFlatUIEnabled(wXComponent)) {
            $jacocoInit[15] = true;
        } else {
            $jacocoInit[16] = true;
            if (!cls.equals(wXComponent.getClass())) {
                $jacocoInit[17] = true;
            } else {
                $jacocoInit[18] = true;
                if (TextUtils.equals(wXComponent.getRef(), WXComponent.ROOT)) {
                    $jacocoInit[19] = true;
                } else {
                    if (!z) {
                        $jacocoInit[20] = true;
                    } else {
                        $jacocoInit[21] = true;
                        if (getFlatComponentAncestor(wXComponent) == null) {
                            $jacocoInit[22] = true;
                        } else {
                            $jacocoInit[23] = true;
                        }
                    }
                    $jacocoInit[24] = true;
                    if (checkComponent(wXComponent)) {
                        $jacocoInit[25] = true;
                    } else {
                        z2 = false;
                        $jacocoInit[27] = true;
                        $jacocoInit[28] = true;
                        return z2;
                    }
                }
            }
        }
        $jacocoInit[26] = true;
        z2 = true;
        $jacocoInit[28] = true;
        return z2;
    }

    @Nullable
    public View getWidgetContainerView(Widget widget) {
        View view;
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[29] = true;
        WXComponent component = getComponent(widget);
        if (component == null) {
            $jacocoInit[30] = true;
        } else {
            $jacocoInit[31] = true;
            WidgetContainer flatComponentAncestor = getFlatComponentAncestor(component);
            if (flatComponentAncestor == null) {
                $jacocoInit[32] = true;
            } else {
                $jacocoInit[33] = true;
                view = flatComponentAncestor.getHostView();
                $jacocoInit[34] = true;
                $jacocoInit[35] = true;
                return view;
            }
        }
        view = null;
        $jacocoInit[35] = true;
        return view;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public void destroy() {
        boolean[] $jacocoInit = $jacocoInit();
        this.widgetToComponent.clear();
        $jacocoInit[36] = true;
        $jacocoInit[37] = true;
        for (Map.Entry<WXComponent, AndroidViewWidget> value : this.mViewWidgetRegistry.entrySet()) {
            $jacocoInit[38] = true;
            ((AndroidViewWidget) value.getValue()).destroy();
            $jacocoInit[39] = true;
        }
        this.mViewWidgetRegistry.clear();
        $jacocoInit[40] = true;
        $jacocoInit[41] = true;
        for (Map.Entry<WXComponent, WidgetContainer> value2 : this.mWidgetRegistry.entrySet()) {
            $jacocoInit[42] = true;
            ((WidgetContainer) value2.getValue()).unmountFlatGUI();
            $jacocoInit[43] = true;
        }
        this.mWidgetRegistry.clear();
        $jacocoInit[44] = true;
    }

    @Nullable
    private WXComponent getComponent(@NonNull Widget widget) {
        boolean[] $jacocoInit = $jacocoInit();
        WXComponent wXComponent = this.widgetToComponent.get(widget);
        $jacocoInit[45] = true;
        return wXComponent;
    }

    private boolean checkComponent(@NonNull WXComponent wXComponent) {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (wXComponent == null) {
            $jacocoInit[46] = true;
        } else {
            $jacocoInit[47] = true;
            WXStyle styles = wXComponent.getStyles();
            $jacocoInit[48] = true;
            WXAttr attrs = wXComponent.getAttrs();
            $jacocoInit[49] = true;
            if (styles.containsKey("opacity")) {
                $jacocoInit[50] = true;
            } else {
                $jacocoInit[51] = true;
                if (styles.containsKey("transform")) {
                    $jacocoInit[52] = true;
                } else {
                    $jacocoInit[53] = true;
                    if (styles.containsKey("visibility")) {
                        $jacocoInit[54] = true;
                    } else {
                        $jacocoInit[55] = true;
                        if (attrs.containsKey("elevation")) {
                            $jacocoInit[56] = true;
                        } else {
                            $jacocoInit[57] = true;
                            if (attrs.containsKey(Constants.Name.ARIA_HIDDEN)) {
                                $jacocoInit[58] = true;
                            } else {
                                $jacocoInit[59] = true;
                                if (attrs.containsKey(Constants.Name.ARIA_LABEL)) {
                                    $jacocoInit[60] = true;
                                } else {
                                    $jacocoInit[61] = true;
                                    if (attrs.containsKey(WXComponent.PROP_FIXED_SIZE)) {
                                        $jacocoInit[62] = true;
                                    } else {
                                        $jacocoInit[63] = true;
                                        if (attrs.containsKey(Constants.Name.DISABLED)) {
                                            $jacocoInit[64] = true;
                                        } else {
                                            $jacocoInit[65] = true;
                                            if (styles.isFixed()) {
                                                $jacocoInit[66] = true;
                                            } else {
                                                $jacocoInit[67] = true;
                                                if (styles.isSticky()) {
                                                    $jacocoInit[68] = true;
                                                } else {
                                                    $jacocoInit[69] = true;
                                                    if (!styles.getPesudoStyles().isEmpty()) {
                                                        $jacocoInit[70] = true;
                                                    } else {
                                                        $jacocoInit[71] = true;
                                                        if (wXComponent.getEvents().size() <= 0) {
                                                            $jacocoInit[72] = true;
                                                        } else {
                                                            $jacocoInit[73] = true;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            $jacocoInit[74] = true;
            z = true;
            $jacocoInit[75] = true;
            return z;
        }
        z = false;
        $jacocoInit[75] = true;
        return z;
    }
}
