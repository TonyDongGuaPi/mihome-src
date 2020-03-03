package com.taobao.weex.ui.component;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.R;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.Component;
import com.taobao.weex.common.Constants;
import com.taobao.weex.common.OnWXScrollListener;
import com.taobao.weex.common.WXErrorCode;
import com.taobao.weex.common.WXRenderStrategy;
import com.taobao.weex.instance.InstanceOnFireEventInterceptor;
import com.taobao.weex.performance.WXInstanceApm;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.component.NestedContainer;
import com.taobao.weex.utils.WXLogUtils;
import com.taobao.weex.utils.WXUtils;
import com.taobao.weex.utils.WXViewUtils;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

@Component(lazyload = false)
public class WXEmbed extends WXDiv implements WXSDKInstance.OnInstanceVisibleListener, NestedContainer {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    private static int ERROR_IMG_HEIGHT = ((int) WXViewUtils.getRealPxByWidth(260.0f, 750));
    private static int ERROR_IMG_WIDTH = ((int) WXViewUtils.getRealPxByWidth(270.0f, 750));
    public static final String ITEM_ID = "itemId";
    public static final String PRIORITY_HIGH = "high";
    public static final String PRIORITY_LOW = "low";
    public static final String PRIORITY_NORMAL = "normal";
    public static final String STRATEGY_HIGH = "high";
    public static final String STRATEGY_NONE = "none";
    public static final String STRATEGY_NORMAL = "normal";
    private long hiddenTime;
    private EmbedInstanceOnScrollFireEventInterceptor mInstanceOnScrollFireEventInterceptor;
    private boolean mIsVisible;
    private EmbedRenderListener mListener;
    protected WXSDKInstance mNestedInstance;
    private String originUrl;
    private String priority;
    private String src;
    private String strategy;

    public interface EmbedManager {
        WXEmbed getEmbed(String str);

        void putEmbed(String str, WXEmbed wXEmbed);
    }

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-3771181283110696364L, "com/taobao/weex/ui/component/WXEmbed", 206);
        $jacocoData = a2;
        return a2;
    }

    static /* synthetic */ String access$000(WXEmbed wXEmbed) {
        boolean[] $jacocoInit = $jacocoInit();
        String str = wXEmbed.src;
        $jacocoInit[199] = true;
        return str;
    }

    static /* synthetic */ int access$100() {
        boolean[] $jacocoInit = $jacocoInit();
        int i = ERROR_IMG_WIDTH;
        $jacocoInit[200] = true;
        return i;
    }

    static /* synthetic */ int access$200() {
        boolean[] $jacocoInit = $jacocoInit();
        int i = ERROR_IMG_HEIGHT;
        $jacocoInit[201] = true;
        return i;
    }

    static /* synthetic */ int access$300(WXEmbed wXEmbed) {
        boolean[] $jacocoInit = $jacocoInit();
        int level = getLevel(wXEmbed);
        $jacocoInit[202] = true;
        return level;
    }

    static /* synthetic */ long access$400(WXEmbed wXEmbed) {
        boolean[] $jacocoInit = $jacocoInit();
        long j = wXEmbed.hiddenTime;
        $jacocoInit[203] = true;
        return j;
    }

    static {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[204] = true;
        $jacocoInit[205] = true;
    }

    public static class FailToH5Listener extends ClickToReloadListener {
        private static transient /* synthetic */ boolean[] $jacocoData;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(-3919460433787788405L, "com/taobao/weex/ui/component/WXEmbed$FailToH5Listener", 19);
            $jacocoData = a2;
            return a2;
        }

        public FailToH5Listener() {
            $jacocoInit()[0] = true;
        }

        @SuppressLint({"SetJavaScriptEnabled"})
        public void onException(NestedContainer nestedContainer, String str, String str2) {
            boolean[] $jacocoInit = $jacocoInit();
            if (str == null) {
                $jacocoInit[1] = true;
            } else if (!(nestedContainer instanceof WXEmbed)) {
                $jacocoInit[2] = true;
            } else if (!str.startsWith("1|")) {
                $jacocoInit[3] = true;
            } else {
                $jacocoInit[4] = true;
                ViewGroup viewContainer = nestedContainer.getViewContainer();
                $jacocoInit[5] = true;
                WebView webView = new WebView(viewContainer.getContext());
                $jacocoInit[6] = true;
                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -1);
                $jacocoInit[7] = true;
                webView.setLayoutParams(layoutParams);
                $jacocoInit[8] = true;
                webView.getSettings().setJavaScriptEnabled(true);
                $jacocoInit[9] = true;
                webView.removeJavascriptInterface("searchBoxJavaBridge_");
                $jacocoInit[10] = true;
                webView.removeJavascriptInterface("accessibility");
                $jacocoInit[11] = true;
                webView.removeJavascriptInterface("accessibilityTraversal");
                $jacocoInit[12] = true;
                webView.getSettings().setSavePassword(false);
                $jacocoInit[13] = true;
                viewContainer.removeAllViews();
                $jacocoInit[14] = true;
                viewContainer.addView(webView);
                $jacocoInit[15] = true;
                webView.loadUrl(WXEmbed.access$000((WXEmbed) nestedContainer));
                $jacocoInit[16] = true;
                $jacocoInit[18] = true;
            }
            super.onException(nestedContainer, str, str2);
            $jacocoInit[17] = true;
            $jacocoInit[18] = true;
        }
    }

    public static class ClickToReloadListener implements NestedContainer.OnNestedInstanceEventListener {
        private static transient /* synthetic */ boolean[] $jacocoData;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(749984508054221615L, "com/taobao/weex/ui/component/WXEmbed$ClickToReloadListener", 21);
            $jacocoData = a2;
            return a2;
        }

        public ClickToReloadListener() {
            $jacocoInit()[0] = true;
        }

        public void onException(NestedContainer nestedContainer, String str, String str2) {
            boolean[] $jacocoInit = $jacocoInit();
            WXErrorCode wXErrorCode = WXErrorCode.WX_DEGRAD_ERR_NETWORK_BUNDLE_DOWNLOAD_FAILED;
            $jacocoInit[1] = true;
            String errorCode = wXErrorCode.getErrorCode();
            $jacocoInit[2] = true;
            if (!TextUtils.equals(str, errorCode)) {
                $jacocoInit[3] = true;
            } else if (!(nestedContainer instanceof WXEmbed)) {
                $jacocoInit[4] = true;
            } else {
                final WXEmbed wXEmbed = (WXEmbed) nestedContainer;
                $jacocoInit[5] = true;
                final ImageView imageView = new ImageView(wXEmbed.getContext());
                $jacocoInit[6] = true;
                imageView.setImageResource(R.drawable.weex_error);
                $jacocoInit[7] = true;
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(WXEmbed.access$100(), WXEmbed.access$200());
                layoutParams.gravity = 17;
                $jacocoInit[8] = true;
                imageView.setLayoutParams(layoutParams);
                $jacocoInit[9] = true;
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                $jacocoInit[10] = true;
                imageView.setAdjustViewBounds(true);
                $jacocoInit[11] = true;
                imageView.setOnClickListener(new View.OnClickListener(this) {
                    private static transient /* synthetic */ boolean[] $jacocoData;
                    final /* synthetic */ ClickToReloadListener this$0;

                    private static /* synthetic */ boolean[] $jacocoInit() {
                        boolean[] zArr = $jacocoData;
                        if (zArr != null) {
                            return zArr;
                        }
                        boolean[] a2 = Offline.a(724439710475067525L, "com/taobao/weex/ui/component/WXEmbed$ClickToReloadListener$1", 4);
                        $jacocoData = a2;
                        return a2;
                    }

                    {
                        boolean[] $jacocoInit = $jacocoInit();
                        this.this$0 = r2;
                        $jacocoInit[0] = true;
                    }

                    public void onClick(View view) {
                        boolean[] $jacocoInit = $jacocoInit();
                        imageView.setOnClickListener((View.OnClickListener) null);
                        $jacocoInit[1] = true;
                        imageView.setEnabled(false);
                        $jacocoInit[2] = true;
                        wXEmbed.loadContent();
                        $jacocoInit[3] = true;
                    }
                });
                $jacocoInit[12] = true;
                FrameLayout frameLayout = (FrameLayout) wXEmbed.getHostView();
                $jacocoInit[13] = true;
                frameLayout.removeAllViews();
                $jacocoInit[14] = true;
                frameLayout.addView(imageView);
                $jacocoInit[15] = true;
                WXLogUtils.e("WXEmbed", "NetWork failure :" + str + ",\n error message :" + str2);
                $jacocoInit[16] = true;
            }
            $jacocoInit[17] = true;
        }

        public boolean onPreCreate(NestedContainer nestedContainer, String str) {
            $jacocoInit()[18] = true;
            return true;
        }

        public String transformUrl(String str) {
            $jacocoInit()[19] = true;
            return str;
        }

        public void onCreated(NestedContainer nestedContainer, WXSDKInstance wXSDKInstance) {
            $jacocoInit()[20] = true;
        }
    }

    static class EmbedRenderListener implements IWXRenderListener {
        private static transient /* synthetic */ boolean[] $jacocoData;
        WXEmbed mComponent;
        NestedContainer.OnNestedInstanceEventListener mEventListener = new ClickToReloadListener();

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(8358361366917916077L, "com/taobao/weex/ui/component/WXEmbed$EmbedRenderListener", 14);
            $jacocoData = a2;
            return a2;
        }

        EmbedRenderListener(WXEmbed wXEmbed) {
            boolean[] $jacocoInit = $jacocoInit();
            this.mComponent = wXEmbed;
            $jacocoInit[0] = true;
            $jacocoInit[1] = true;
        }

        public void onViewCreated(WXSDKInstance wXSDKInstance, View view) {
            boolean[] $jacocoInit = $jacocoInit();
            FrameLayout frameLayout = (FrameLayout) this.mComponent.getHostView();
            $jacocoInit[2] = true;
            frameLayout.removeAllViews();
            $jacocoInit[3] = true;
            frameLayout.addView(view);
            $jacocoInit[4] = true;
        }

        public void onRenderSuccess(WXSDKInstance wXSDKInstance, int i, int i2) {
            $jacocoInit()[5] = true;
        }

        public void onRefreshSuccess(WXSDKInstance wXSDKInstance, int i, int i2) {
            $jacocoInit()[6] = true;
        }

        public void onException(WXSDKInstance wXSDKInstance, String str, String str2) {
            boolean[] $jacocoInit = $jacocoInit();
            if (wXSDKInstance == null) {
                $jacocoInit[7] = true;
            } else {
                $jacocoInit[8] = true;
                wXSDKInstance.getExceptionRecorder().hasDegrade.set(true);
                $jacocoInit[9] = true;
            }
            if (this.mEventListener == null) {
                $jacocoInit[10] = true;
            } else {
                $jacocoInit[11] = true;
                this.mEventListener.onException(this.mComponent, str, str2);
                $jacocoInit[12] = true;
            }
            $jacocoInit[13] = true;
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @Deprecated
    public WXEmbed(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, String str, boolean z, BasicComponentData basicComponentData) {
        this(wXSDKInstance, wXVContainer, basicComponentData);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WXEmbed(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, BasicComponentData basicComponentData) {
        super(wXSDKInstance, wXVContainer, basicComponentData);
        boolean[] $jacocoInit = $jacocoInit();
        this.mIsVisible = true;
        this.priority = "normal";
        this.strategy = "normal";
        $jacocoInit[1] = true;
        this.mListener = new EmbedRenderListener(this);
        $jacocoInit[2] = true;
        this.mInstanceOnScrollFireEventInterceptor = new EmbedInstanceOnScrollFireEventInterceptor(this);
        $jacocoInit[3] = true;
        ERROR_IMG_WIDTH = (int) WXViewUtils.getRealPxByWidth(270.0f, wXSDKInstance.getInstanceViewPortWidth());
        $jacocoInit[4] = true;
        ERROR_IMG_HEIGHT = (int) WXViewUtils.getRealPxByWidth(260.0f, wXSDKInstance.getInstanceViewPortWidth());
        if (!(wXSDKInstance instanceof EmbedManager)) {
            $jacocoInit[5] = true;
        } else {
            $jacocoInit[6] = true;
            Object obj = getAttrs().get("itemId");
            if (obj == null) {
                $jacocoInit[7] = true;
            } else {
                $jacocoInit[8] = true;
                ((EmbedManager) wXSDKInstance).putEmbed(obj.toString(), this);
                $jacocoInit[9] = true;
            }
        }
        this.priority = WXUtils.getString(getAttrs().get("priority"), "normal");
        $jacocoInit[10] = true;
        this.strategy = WXUtils.getString(getAttrs().get(Constants.Name.STRATEGY), "none");
        $jacocoInit[11] = true;
        wXSDKInstance.getApmForInstance().updateDiffStats(WXInstanceApm.KEY_PAGE_STATS_EMBED_COUNT, 1.0d);
        $jacocoInit[12] = true;
    }

    public void setOnNestEventListener(NestedContainer.OnNestedInstanceEventListener onNestedInstanceEventListener) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mListener.mEventListener = onNestedInstanceEventListener;
        $jacocoInit[13] = true;
    }

    public ViewGroup getViewContainer() {
        boolean[] $jacocoInit = $jacocoInit();
        ViewGroup viewGroup = (ViewGroup) getHostView();
        $jacocoInit[14] = true;
        return viewGroup;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0066  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean setProperty(java.lang.String r5, java.lang.Object r6) {
        /*
            r4 = this;
            boolean[] r0 = $jacocoInit()
            int r1 = r5.hashCode()
            r2 = -1165461084(0xffffffffba8879a4, float:-0.0010412228)
            r3 = 1
            if (r1 == r2) goto L_0x002b
            r2 = 114148(0x1bde4, float:1.59955E-40)
            if (r1 == r2) goto L_0x0018
            r1 = 15
            r0[r1] = r3
            goto L_0x0037
        L_0x0018:
            java.lang.String r1 = "src"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L_0x0025
            r1 = 16
            r0[r1] = r3
            goto L_0x0037
        L_0x0025:
            r1 = 0
            r2 = 17
            r0[r2] = r3
            goto L_0x003e
        L_0x002b:
            java.lang.String r1 = "priority"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L_0x0039
            r1 = 18
            r0[r1] = r3
        L_0x0037:
            r1 = -1
            goto L_0x003e
        L_0x0039:
            r1 = 19
            r0[r1] = r3
            r1 = 1
        L_0x003e:
            r2 = 0
            switch(r1) {
                case 0: goto L_0x0066;
                case 1: goto L_0x004b;
                default: goto L_0x0042;
            }
        L_0x0042:
            boolean r5 = super.setProperty(r5, r6)
            r6 = 28
            r0[r6] = r3
            return r5
        L_0x004b:
            java.lang.String r5 = com.taobao.weex.utils.WXUtils.getString(r6, r2)
            if (r5 != 0) goto L_0x0056
            r5 = 24
            r0[r5] = r3
            goto L_0x0061
        L_0x0056:
            r6 = 25
            r0[r6] = r3
            r4.setPriority(r5)
            r5 = 26
            r0[r5] = r3
        L_0x0061:
            r5 = 27
            r0[r5] = r3
            return r3
        L_0x0066:
            java.lang.String r5 = com.taobao.weex.utils.WXUtils.getString(r6, r2)
            if (r5 != 0) goto L_0x0071
            r5 = 20
            r0[r5] = r3
            goto L_0x007c
        L_0x0071:
            r6 = 21
            r0[r6] = r3
            r4.setSrc(r5)
            r5 = 22
            r0[r5] = r3
        L_0x007c:
            r5 = 23
            r0[r5] = r3
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.component.WXEmbed.setProperty(java.lang.String, java.lang.Object):boolean");
    }

    public void renderNewURL(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        this.src = str;
        $jacocoInit[29] = true;
        loadContent();
        $jacocoInit[30] = true;
    }

    public void reload() {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(this.src)) {
            $jacocoInit[31] = true;
        } else {
            $jacocoInit[32] = true;
            loadContent();
            $jacocoInit[33] = true;
        }
        $jacocoInit[34] = true;
    }

    public String getOriginUrl() {
        boolean[] $jacocoInit = $jacocoInit();
        String str = this.originUrl;
        $jacocoInit[35] = true;
        return str;
    }

    public void setOriginUrl(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        this.originUrl = str;
        $jacocoInit[36] = true;
    }

    public void addEvent(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        super.addEvent(str);
        $jacocoInit[37] = true;
        if (Constants.Event.SCROLL_START.equals(str)) {
            $jacocoInit[38] = true;
            this.mInstanceOnScrollFireEventInterceptor.addInterceptEvent(str);
            $jacocoInit[39] = true;
        } else if (Constants.Event.SCROLL_END.equals(str)) {
            $jacocoInit[40] = true;
            this.mInstanceOnScrollFireEventInterceptor.addInterceptEvent(str);
            $jacocoInit[41] = true;
        } else if (!"scroll".equals(str)) {
            $jacocoInit[42] = true;
        } else {
            $jacocoInit[43] = true;
            this.mInstanceOnScrollFireEventInterceptor.addInterceptEvent(str);
            $jacocoInit[44] = true;
        }
        $jacocoInit[45] = true;
    }

    @WXComponentProp(name = "src")
    public void setSrc(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        this.originUrl = str;
        this.src = str;
        if (this.mNestedInstance == null) {
            $jacocoInit[46] = true;
        } else {
            $jacocoInit[47] = true;
            this.mNestedInstance.destroy();
            this.mNestedInstance = null;
            $jacocoInit[48] = true;
        }
        if (!this.mIsVisible) {
            $jacocoInit[49] = true;
        } else if (TextUtils.isEmpty(this.src)) {
            $jacocoInit[50] = true;
        } else {
            $jacocoInit[51] = true;
            loadContent();
            $jacocoInit[52] = true;
        }
        $jacocoInit[53] = true;
    }

    public String getSrc() {
        boolean[] $jacocoInit = $jacocoInit();
        String str = this.src;
        $jacocoInit[54] = true;
        return str;
    }

    @WXComponentProp(name = "priority")
    public void setPriority(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[55] = true;
            return;
        }
        this.priority = str;
        $jacocoInit[56] = true;
    }

    /* access modifiers changed from: protected */
    public void loadContent() {
        boolean[] $jacocoInit = $jacocoInit();
        this.mNestedInstance = createInstance();
        if (this.mListener == null) {
            $jacocoInit[57] = true;
        } else if (this.mListener.mEventListener == null) {
            $jacocoInit[58] = true;
        } else {
            $jacocoInit[59] = true;
            if (this.mListener.mEventListener.onPreCreate(this, this.src)) {
                $jacocoInit[60] = true;
            } else {
                $jacocoInit[61] = true;
                this.mListener.mEventListener.onCreated(this, this.mNestedInstance);
                $jacocoInit[62] = true;
            }
        }
        $jacocoInit[63] = true;
    }

    private static final int getLevel(WXEmbed wXEmbed) {
        int i;
        boolean[] $jacocoInit = $jacocoInit();
        String str = wXEmbed.priority;
        String str2 = wXEmbed.strategy;
        $jacocoInit[64] = true;
        if ("high".equals(str2)) {
            $jacocoInit[65] = true;
        } else {
            $jacocoInit[66] = true;
            if (TextUtils.equals(str, "low")) {
                i = 0;
                $jacocoInit[67] = true;
            } else if (!TextUtils.equals(str, "high")) {
                $jacocoInit[68] = true;
            } else {
                i = 10;
                $jacocoInit[69] = true;
            }
            $jacocoInit[70] = true;
            return i;
        }
        i = 5;
        $jacocoInit[70] = true;
        return i;
    }

    private WXSDKInstance createInstance() {
        boolean[] $jacocoInit = $jacocoInit();
        WXSDKInstance createNestedInstance = getInstance().createNestedInstance(this);
        $jacocoInit[71] = true;
        getInstance().addOnInstanceVisibleListener(this);
        $jacocoInit[72] = true;
        createNestedInstance.registerRenderListener(this.mListener);
        $jacocoInit[73] = true;
        this.mInstanceOnScrollFireEventInterceptor.resetFirstLaterScroller();
        $jacocoInit[74] = true;
        createNestedInstance.addInstanceOnFireEventInterceptor(this.mInstanceOnScrollFireEventInterceptor);
        $jacocoInit[75] = true;
        createNestedInstance.registerOnWXScrollListener(this.mInstanceOnScrollFireEventInterceptor);
        String str = this.src;
        if (this.mListener == null) {
            $jacocoInit[76] = true;
        } else if (this.mListener.mEventListener == null) {
            $jacocoInit[77] = true;
        } else {
            $jacocoInit[78] = true;
            str = this.mListener.mEventListener.transformUrl(this.src);
            $jacocoInit[79] = true;
            if (this.mListener.mEventListener.onPreCreate(this, this.src)) {
                $jacocoInit[80] = true;
            } else {
                $jacocoInit[81] = true;
                return null;
            }
        }
        String str2 = str;
        if (TextUtils.isEmpty(str2)) {
            NestedContainer.OnNestedInstanceEventListener onNestedInstanceEventListener = this.mListener.mEventListener;
            WXErrorCode wXErrorCode = WXErrorCode.WX_DEGRAD_ERR_BUNDLE_CONTENTTYPE_ERROR;
            $jacocoInit[82] = true;
            String errorCode = wXErrorCode.getErrorCode();
            StringBuilder sb = new StringBuilder();
            WXErrorCode wXErrorCode2 = WXErrorCode.WX_DEGRAD_ERR_BUNDLE_CONTENTTYPE_ERROR;
            $jacocoInit[83] = true;
            sb.append(wXErrorCode2.getErrorMsg());
            sb.append("!!wx embed src url is null");
            String sb2 = sb.toString();
            $jacocoInit[84] = true;
            onNestedInstanceEventListener.onException(this, errorCode, sb2);
            $jacocoInit[85] = true;
            return createNestedInstance;
        }
        createNestedInstance.setContainerInfo(WXInstanceApm.KEY_PAGE_PROPERTIES_INSTANCE_TYPE, WXBasicComponentType.EMBED);
        $jacocoInit[86] = true;
        createNestedInstance.setContainerInfo(WXInstanceApm.KEY_PAGE_PROPERTIES_PARENT_PAGE, getInstance().getWXPerformance().pageName);
        $jacocoInit[87] = true;
        createNestedInstance.renderByUrl(str2, str2, (Map<String, Object>) null, (String) null, WXRenderStrategy.APPEND_ASYNC);
        $jacocoInit[88] = true;
        return createNestedInstance;
    }

    public void setVisibility(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        super.setVisibility(str);
        $jacocoInit[89] = true;
        boolean equals = TextUtils.equals(str, "visible");
        if (this.mIsVisible == equals) {
            $jacocoInit[90] = true;
        } else {
            $jacocoInit[91] = true;
            if (TextUtils.isEmpty(this.src)) {
                $jacocoInit[92] = true;
            } else if (!equals) {
                $jacocoInit[93] = true;
            } else if (this.mNestedInstance == null) {
                $jacocoInit[94] = true;
                loadContent();
                $jacocoInit[95] = true;
            } else {
                this.mNestedInstance.onViewAppear();
                $jacocoInit[96] = true;
            }
            if (equals) {
                $jacocoInit[97] = true;
            } else if (this.mNestedInstance == null) {
                $jacocoInit[98] = true;
            } else {
                $jacocoInit[99] = true;
                this.mNestedInstance.onViewDisappear();
                $jacocoInit[100] = true;
            }
            this.mIsVisible = equals;
            $jacocoInit[101] = true;
            doAutoEmbedMemoryStrategy();
            $jacocoInit[102] = true;
        }
        $jacocoInit[103] = true;
    }

    public void destroy() {
        boolean[] $jacocoInit = $jacocoInit();
        super.destroy();
        $jacocoInit[104] = true;
        destoryNestInstance();
        this.src = null;
        $jacocoInit[105] = true;
        if (getInstance() == null) {
            $jacocoInit[106] = true;
        } else {
            $jacocoInit[107] = true;
            getInstance().removeOnInstanceVisibleListener(this);
            $jacocoInit[108] = true;
        }
        $jacocoInit[109] = true;
    }

    private void doAutoEmbedMemoryStrategy() {
        boolean[] $jacocoInit = $jacocoInit();
        if ("none".equals(this.strategy)) {
            $jacocoInit[110] = true;
        } else {
            if (this.mIsVisible) {
                $jacocoInit[111] = true;
            } else if (this.mNestedInstance == null) {
                $jacocoInit[112] = true;
            } else {
                $jacocoInit[113] = true;
                if ("low".equals(this.priority)) {
                    $jacocoInit[114] = true;
                    destoryNestInstance();
                    $jacocoInit[115] = true;
                } else {
                    if (getInstance().hiddenEmbeds != null) {
                        $jacocoInit[116] = true;
                    } else {
                        $jacocoInit[117] = true;
                        getInstance().hiddenEmbeds = new PriorityQueue<>(8, new Comparator<WXEmbed>(this) {
                            private static transient /* synthetic */ boolean[] $jacocoData;
                            final /* synthetic */ WXEmbed this$0;

                            private static /* synthetic */ boolean[] $jacocoInit() {
                                boolean[] zArr = $jacocoData;
                                if (zArr != null) {
                                    return zArr;
                                }
                                boolean[] a2 = Offline.a(1432355567466846066L, "com/taobao/weex/ui/component/WXEmbed$1", 4);
                                $jacocoData = a2;
                                return a2;
                            }

                            {
                                boolean[] $jacocoInit = $jacocoInit();
                                this.this$0 = r3;
                                $jacocoInit[0] = true;
                            }

                            public /* synthetic */ int compare(Object obj, Object obj2) {
                                boolean[] $jacocoInit = $jacocoInit();
                                int compare = compare((WXEmbed) obj, (WXEmbed) obj2);
                                $jacocoInit[3] = true;
                                return compare;
                            }

                            public int compare(WXEmbed wXEmbed, WXEmbed wXEmbed2) {
                                boolean[] $jacocoInit = $jacocoInit();
                                int access$300 = WXEmbed.access$300(wXEmbed) - WXEmbed.access$300(wXEmbed2);
                                if (access$300 != 0) {
                                    $jacocoInit[1] = true;
                                    return access$300;
                                }
                                int access$400 = (int) (WXEmbed.access$400(wXEmbed) - WXEmbed.access$400(wXEmbed2));
                                $jacocoInit[2] = true;
                                return access$400;
                            }
                        });
                        $jacocoInit[118] = true;
                    }
                    if (getInstance().hiddenEmbeds.contains(this)) {
                        $jacocoInit[119] = true;
                    } else {
                        $jacocoInit[120] = true;
                        this.hiddenTime = System.currentTimeMillis();
                        $jacocoInit[121] = true;
                        getInstance().hiddenEmbeds.add(this);
                        $jacocoInit[122] = true;
                    }
                    if (getInstance().hiddenEmbeds == null) {
                        $jacocoInit[123] = true;
                    } else if (getInstance().getMaxHiddenEmbedsNum() < 0) {
                        $jacocoInit[124] = true;
                    } else {
                        $jacocoInit[125] = true;
                        while (getInstance().hiddenEmbeds.size() > getInstance().getMaxHiddenEmbedsNum()) {
                            $jacocoInit[127] = true;
                            WXEmbed poll = getInstance().hiddenEmbeds.poll();
                            if (poll.mIsVisible) {
                                $jacocoInit[128] = true;
                            } else {
                                if (poll == null) {
                                    $jacocoInit[129] = true;
                                } else {
                                    $jacocoInit[130] = true;
                                    poll.destoryNestInstance();
                                    $jacocoInit[131] = true;
                                }
                                $jacocoInit[132] = true;
                            }
                        }
                        $jacocoInit[126] = true;
                    }
                }
            }
            if (!this.mIsVisible) {
                $jacocoInit[133] = true;
            } else if (this.mNestedInstance == null) {
                $jacocoInit[134] = true;
            } else {
                $jacocoInit[135] = true;
                if (getInstance().hiddenEmbeds == null) {
                    $jacocoInit[136] = true;
                } else if (!getInstance().hiddenEmbeds.contains(this)) {
                    $jacocoInit[137] = true;
                } else {
                    $jacocoInit[138] = true;
                    getInstance().hiddenEmbeds.remove(this);
                    $jacocoInit[139] = true;
                }
            }
        }
        $jacocoInit[140] = true;
    }

    public void onAppear() {
        boolean[] $jacocoInit = $jacocoInit();
        if (!this.mIsVisible) {
            $jacocoInit[141] = true;
        } else if (this.mNestedInstance == null) {
            $jacocoInit[142] = true;
        } else {
            $jacocoInit[143] = true;
            WXComponent rootComponent = this.mNestedInstance.getRootComponent();
            if (rootComponent == null) {
                $jacocoInit[144] = true;
            } else {
                $jacocoInit[145] = true;
                rootComponent.fireEvent(Constants.Event.VIEWAPPEAR);
                $jacocoInit[146] = true;
            }
        }
        $jacocoInit[147] = true;
    }

    public void onDisappear() {
        boolean[] $jacocoInit = $jacocoInit();
        if (!this.mIsVisible) {
            $jacocoInit[148] = true;
        } else if (this.mNestedInstance == null) {
            $jacocoInit[149] = true;
        } else {
            $jacocoInit[150] = true;
            WXComponent rootComponent = this.mNestedInstance.getRootComponent();
            if (rootComponent == null) {
                $jacocoInit[151] = true;
            } else {
                $jacocoInit[152] = true;
                rootComponent.fireEvent(Constants.Event.VIEWDISAPPEAR);
                $jacocoInit[153] = true;
            }
        }
        $jacocoInit[154] = true;
    }

    public void onActivityStart() {
        boolean[] $jacocoInit = $jacocoInit();
        super.onActivityStart();
        if (this.mNestedInstance == null) {
            $jacocoInit[155] = true;
        } else {
            $jacocoInit[156] = true;
            this.mNestedInstance.onActivityStart();
            $jacocoInit[157] = true;
        }
        $jacocoInit[158] = true;
    }

    public void onActivityResume() {
        boolean[] $jacocoInit = $jacocoInit();
        super.onActivityResume();
        if (this.mNestedInstance == null) {
            $jacocoInit[159] = true;
        } else {
            $jacocoInit[160] = true;
            this.mNestedInstance.onActivityResume();
            $jacocoInit[161] = true;
        }
        $jacocoInit[162] = true;
    }

    public void onActivityPause() {
        boolean[] $jacocoInit = $jacocoInit();
        super.onActivityPause();
        if (this.mNestedInstance == null) {
            $jacocoInit[163] = true;
        } else {
            $jacocoInit[164] = true;
            this.mNestedInstance.onActivityPause();
            $jacocoInit[165] = true;
        }
        $jacocoInit[166] = true;
    }

    public void onActivityStop() {
        boolean[] $jacocoInit = $jacocoInit();
        super.onActivityStop();
        if (this.mNestedInstance == null) {
            $jacocoInit[167] = true;
        } else {
            $jacocoInit[168] = true;
            this.mNestedInstance.onActivityStop();
            $jacocoInit[169] = true;
        }
        $jacocoInit[170] = true;
    }

    public void onActivityDestroy() {
        boolean[] $jacocoInit = $jacocoInit();
        super.onActivityDestroy();
        if (this.mNestedInstance == null) {
            $jacocoInit[171] = true;
        } else {
            $jacocoInit[172] = true;
            this.mNestedInstance.onActivityDestroy();
            $jacocoInit[173] = true;
        }
        $jacocoInit[174] = true;
    }

    public void setStrategy(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        this.strategy = str;
        $jacocoInit[175] = true;
    }

    private void destoryNestInstance() {
        int i;
        boolean[] $jacocoInit = $jacocoInit();
        if (getInstance().hiddenEmbeds == null) {
            $jacocoInit[176] = true;
        } else if (!getInstance().hiddenEmbeds.contains(this)) {
            $jacocoInit[177] = true;
        } else {
            $jacocoInit[178] = true;
            getInstance().hiddenEmbeds.remove(this);
            $jacocoInit[179] = true;
        }
        if (this.mNestedInstance == null) {
            $jacocoInit[180] = true;
        } else {
            $jacocoInit[181] = true;
            this.mNestedInstance.destroy();
            this.mNestedInstance = null;
            $jacocoInit[182] = true;
        }
        if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[183] = true;
        } else {
            $jacocoInit[184] = true;
            StringBuilder sb = new StringBuilder();
            sb.append("WXEmbed destoryNestInstance priority ");
            sb.append(this.priority);
            sb.append(" index ");
            sb.append(getAttrs().get("index"));
            sb.append("  ");
            sb.append(this.hiddenTime);
            sb.append(" embeds size ");
            $jacocoInit[185] = true;
            if (getInstance().hiddenEmbeds == null) {
                i = 0;
                $jacocoInit[186] = true;
            } else {
                i = getInstance().hiddenEmbeds.size();
                $jacocoInit[187] = true;
            }
            sb.append(i);
            sb.append(" strategy ");
            sb.append(this.strategy);
            String sb2 = sb.toString();
            $jacocoInit[188] = true;
            WXLogUtils.w(sb2);
            $jacocoInit[189] = true;
        }
        $jacocoInit[190] = true;
    }

    public void addLayerOverFlowListener(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mNestedInstance == null) {
            $jacocoInit[191] = true;
        } else {
            $jacocoInit[192] = true;
            this.mNestedInstance.addLayerOverFlowListener(getRef());
            $jacocoInit[193] = true;
        }
        $jacocoInit[194] = true;
    }

    public void removeLayerOverFlowListener(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mNestedInstance == null) {
            $jacocoInit[195] = true;
        } else {
            $jacocoInit[196] = true;
            this.mNestedInstance.removeLayerOverFlowListener(str);
            $jacocoInit[197] = true;
        }
        $jacocoInit[198] = true;
    }

    static class EmbedInstanceOnScrollFireEventInterceptor extends InstanceOnFireEventInterceptor implements OnWXScrollListener {
        private static transient /* synthetic */ boolean[] $jacocoData;
        private WXComponent firstLayerScroller;
        private WXEmbed mEmbed;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(919550840077212245L, "com/taobao/weex/ui/component/WXEmbed$EmbedInstanceOnScrollFireEventInterceptor", 50);
            $jacocoData = a2;
            return a2;
        }

        public EmbedInstanceOnScrollFireEventInterceptor(WXEmbed wXEmbed) {
            boolean[] $jacocoInit = $jacocoInit();
            this.mEmbed = wXEmbed;
            $jacocoInit[0] = true;
        }

        public void resetFirstLaterScroller() {
            boolean[] $jacocoInit = $jacocoInit();
            this.firstLayerScroller = null;
            $jacocoInit[1] = true;
        }

        public void onFireEvent(String str, String str2, String str3, Map<String, Object> map, Map<String, Object> map2) {
            boolean[] $jacocoInit = $jacocoInit();
            if (this.mEmbed == null) {
                $jacocoInit[2] = true;
            } else if (this.mEmbed.mNestedInstance == null) {
                $jacocoInit[3] = true;
            } else {
                WXSDKInstance wXSDKInstance = this.mEmbed.mNestedInstance;
                $jacocoInit[4] = true;
                if (!wXSDKInstance.getInstanceId().equals(str)) {
                    $jacocoInit[5] = true;
                } else {
                    if (this.firstLayerScroller != null) {
                        $jacocoInit[7] = true;
                    } else {
                        $jacocoInit[8] = true;
                        initFirstLayerScroller();
                        $jacocoInit[9] = true;
                    }
                    if (this.firstLayerScroller == null) {
                        $jacocoInit[10] = true;
                        return;
                    }
                    if (!this.firstLayerScroller.getRef().equals(str2)) {
                        $jacocoInit[11] = true;
                    } else {
                        $jacocoInit[12] = true;
                        this.mEmbed.getInstance().fireEvent(this.mEmbed.getRef(), str3, map, map2);
                        $jacocoInit[13] = true;
                    }
                    $jacocoInit[14] = true;
                    return;
                }
            }
            $jacocoInit[6] = true;
        }

        private void initFirstLayerScroller() {
            boolean[] $jacocoInit = $jacocoInit();
            if (this.firstLayerScroller != null) {
                $jacocoInit[15] = true;
            } else {
                $jacocoInit[16] = true;
                this.firstLayerScroller = findFirstLayerScroller();
                if (this.firstLayerScroller == null) {
                    $jacocoInit[17] = true;
                } else {
                    $jacocoInit[18] = true;
                    $jacocoInit[19] = true;
                    for (String next : getListenEvents()) {
                        $jacocoInit[21] = true;
                        if (this.firstLayerScroller.containsEvent(next)) {
                            $jacocoInit[22] = true;
                        } else {
                            $jacocoInit[23] = true;
                            this.firstLayerScroller.getEvents().add(next);
                            $jacocoInit[24] = true;
                            this.firstLayerScroller.addEvent(next);
                            $jacocoInit[25] = true;
                        }
                        $jacocoInit[26] = true;
                    }
                    $jacocoInit[20] = true;
                }
            }
            $jacocoInit[27] = true;
        }

        private WXComponent findFirstLayerScroller() {
            boolean[] $jacocoInit = $jacocoInit();
            if (this.mEmbed.mNestedInstance == null) {
                $jacocoInit[28] = true;
                return null;
            }
            WXComponent rootComponent = this.mEmbed.mNestedInstance.getRootComponent();
            if (rootComponent instanceof Scrollable) {
                $jacocoInit[29] = true;
                return rootComponent;
            }
            ArrayDeque arrayDeque = new ArrayDeque();
            $jacocoInit[30] = true;
            arrayDeque.offer(rootComponent);
            $jacocoInit[31] = true;
            while (true) {
                if (arrayDeque.isEmpty()) {
                    $jacocoInit[32] = true;
                    break;
                }
                $jacocoInit[33] = true;
                WXComponent wXComponent = (WXComponent) arrayDeque.poll();
                if (wXComponent == null) {
                    $jacocoInit[34] = true;
                    break;
                } else if (wXComponent instanceof Scrollable) {
                    $jacocoInit[35] = true;
                    return wXComponent;
                } else {
                    if (!(wXComponent instanceof WXVContainer)) {
                        $jacocoInit[36] = true;
                    } else {
                        WXVContainer wXVContainer = (WXVContainer) wXComponent;
                        $jacocoInit[37] = true;
                        int i = 0;
                        $jacocoInit[38] = true;
                        while (i < wXVContainer.getChildCount()) {
                            $jacocoInit[40] = true;
                            arrayDeque.offer(wXVContainer.getChild(i));
                            i++;
                            $jacocoInit[41] = true;
                        }
                        $jacocoInit[39] = true;
                    }
                    $jacocoInit[42] = true;
                }
            }
            $jacocoInit[43] = true;
            return null;
        }

        public void onScrolled(View view, int i, int i2) {
            boolean[] $jacocoInit = $jacocoInit();
            if (this.firstLayerScroller != null) {
                $jacocoInit[44] = true;
                return;
            }
            if (getListenEvents().size() <= 0) {
                $jacocoInit[45] = true;
            } else {
                $jacocoInit[46] = true;
                initFirstLayerScroller();
                $jacocoInit[47] = true;
            }
            $jacocoInit[48] = true;
        }

        public void onScrollStateChanged(View view, int i, int i2, int i3) {
            $jacocoInit()[49] = true;
        }
    }
}
