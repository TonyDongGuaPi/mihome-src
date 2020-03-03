package com.taobao.weex.ui.component;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.annotation.Component;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.common.Constants;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.view.IWebView;
import com.taobao.weex.ui.view.WXWebView;
import java.util.HashMap;
import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

@Component(lazyload = false)
public class WXWeb extends WXComponent {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    public static final String GO_BACK = "goBack";
    public static final String GO_FORWARD = "goForward";
    public static final String POST_MESSAGE = "postMessage";
    public static final String RELOAD = "reload";
    protected IWebView mWebView;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(9027807358433498158L, "com/taobao/weex/ui/component/WXWeb", 81);
        $jacocoData = a2;
        return a2;
    }

    static /* synthetic */ void access$000(WXWeb wXWeb, String str, Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        wXWeb.fireEvent(str, obj);
        $jacocoInit[80] = true;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @Deprecated
    public WXWeb(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, String str, boolean z, BasicComponentData basicComponentData) {
        this(wXSDKInstance, wXVContainer, z, basicComponentData);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WXWeb(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, boolean z, BasicComponentData basicComponentData) {
        super(wXSDKInstance, wXVContainer, z, basicComponentData);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[1] = true;
        createWebView();
        $jacocoInit[2] = true;
    }

    /* access modifiers changed from: protected */
    public void createWebView() {
        String str;
        boolean[] $jacocoInit = $jacocoInit();
        try {
            $jacocoInit[3] = true;
            String bundleUrl = WXSDKManager.getInstance().getSDKInstance(getInstanceId()).getBundleUrl();
            $jacocoInit[4] = true;
            Uri parse = Uri.parse(bundleUrl);
            $jacocoInit[5] = true;
            String scheme = parse.getScheme();
            $jacocoInit[6] = true;
            String authority = parse.getAuthority();
            $jacocoInit[7] = true;
            if (TextUtils.isEmpty(scheme)) {
                $jacocoInit[8] = true;
            } else if (TextUtils.isEmpty(authority)) {
                $jacocoInit[9] = true;
            } else {
                $jacocoInit[10] = true;
                str = scheme + "://" + authority;
                try {
                    $jacocoInit[11] = true;
                    $jacocoInit[12] = true;
                } catch (Exception unused) {
                    $jacocoInit[13] = true;
                    this.mWebView = new WXWebView(getContext(), str);
                    $jacocoInit[14] = true;
                }
                this.mWebView = new WXWebView(getContext(), str);
                $jacocoInit[14] = true;
            }
            str = null;
            $jacocoInit[12] = true;
        } catch (Exception unused2) {
            str = null;
            $jacocoInit[13] = true;
            this.mWebView = new WXWebView(getContext(), str);
            $jacocoInit[14] = true;
        }
        this.mWebView = new WXWebView(getContext(), str);
        $jacocoInit[14] = true;
    }

    /* access modifiers changed from: protected */
    public View initComponentHostView(@NonNull Context context) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mWebView.setOnErrorListener(new IWebView.OnErrorListener(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ WXWeb this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(-2123134282415749911L, "com/taobao/weex/ui/component/WXWeb$1", 2);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r3;
                $jacocoInit[0] = true;
            }

            public void onError(String str, Object obj) {
                boolean[] $jacocoInit = $jacocoInit();
                WXWeb.access$000(this.this$0, str, obj);
                $jacocoInit[1] = true;
            }
        });
        $jacocoInit[15] = true;
        this.mWebView.setOnPageListener(new IWebView.OnPageListener(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ WXWeb this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(5764012283075660108L, "com/taobao/weex/ui/component/WXWeb$2", 21);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r3;
                $jacocoInit[0] = true;
            }

            public void onReceivedTitle(String str) {
                boolean[] $jacocoInit = $jacocoInit();
                if (!this.this$0.getEvents().contains(Constants.Event.RECEIVEDTITLE)) {
                    $jacocoInit[1] = true;
                } else {
                    $jacocoInit[2] = true;
                    HashMap hashMap = new HashMap();
                    $jacocoInit[3] = true;
                    hashMap.put("title", str);
                    $jacocoInit[4] = true;
                    this.this$0.fireEvent(Constants.Event.RECEIVEDTITLE, hashMap);
                    $jacocoInit[5] = true;
                }
                $jacocoInit[6] = true;
            }

            public void onPageStart(String str) {
                boolean[] $jacocoInit = $jacocoInit();
                if (!this.this$0.getEvents().contains(Constants.Event.PAGESTART)) {
                    $jacocoInit[7] = true;
                } else {
                    $jacocoInit[8] = true;
                    HashMap hashMap = new HashMap();
                    $jacocoInit[9] = true;
                    hashMap.put("url", str);
                    $jacocoInit[10] = true;
                    this.this$0.fireEvent(Constants.Event.PAGESTART, hashMap);
                    $jacocoInit[11] = true;
                }
                $jacocoInit[12] = true;
            }

            public void onPageFinish(String str, boolean z, boolean z2) {
                boolean[] $jacocoInit = $jacocoInit();
                if (!this.this$0.getEvents().contains(Constants.Event.PAGEFINISH)) {
                    $jacocoInit[13] = true;
                } else {
                    $jacocoInit[14] = true;
                    HashMap hashMap = new HashMap();
                    $jacocoInit[15] = true;
                    hashMap.put("url", str);
                    $jacocoInit[16] = true;
                    hashMap.put("canGoBack", Boolean.valueOf(z));
                    $jacocoInit[17] = true;
                    hashMap.put("canGoForward", Boolean.valueOf(z2));
                    $jacocoInit[18] = true;
                    this.this$0.fireEvent(Constants.Event.PAGEFINISH, hashMap);
                    $jacocoInit[19] = true;
                }
                $jacocoInit[20] = true;
            }
        });
        $jacocoInit[16] = true;
        this.mWebView.setOnMessageListener(new IWebView.OnMessageListener(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ WXWeb this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(7826225727990282527L, "com/taobao/weex/ui/component/WXWeb$3", 2);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r3;
                $jacocoInit[0] = true;
            }

            public void onMessage(Map<String, Object> map) {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0.fireEvent("message", map);
                $jacocoInit[1] = true;
            }
        });
        $jacocoInit[17] = true;
        View view = this.mWebView.getView();
        $jacocoInit[18] = true;
        return view;
    }

    public void destroy() {
        boolean[] $jacocoInit = $jacocoInit();
        super.destroy();
        $jacocoInit[19] = true;
        getWebView().destroy();
        $jacocoInit[20] = true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x005a  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0063  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x007e  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0099  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean setProperty(java.lang.String r5, java.lang.Object r6) {
        /*
            r4 = this;
            boolean[] r0 = $jacocoInit()
            int r1 = r5.hashCode()
            r2 = -896505829(0xffffffffca90681b, float:-4731917.5)
            r3 = 1
            if (r1 == r2) goto L_0x0043
            r2 = 114148(0x1bde4, float:1.59955E-40)
            if (r1 == r2) goto L_0x0030
            r2 = 537088620(0x2003526c, float:1.1123403E-19)
            if (r1 == r2) goto L_0x001d
            r1 = 21
            r0[r1] = r3
            goto L_0x004f
        L_0x001d:
            java.lang.String r1 = "show-loading"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L_0x002a
            r1 = 22
            r0[r1] = r3
            goto L_0x004f
        L_0x002a:
            r1 = 0
            r2 = 23
            r0[r2] = r3
            goto L_0x0056
        L_0x0030:
            java.lang.String r1 = "src"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L_0x003d
            r1 = 24
            r0[r1] = r3
            goto L_0x004f
        L_0x003d:
            r1 = 25
            r0[r1] = r3
            r1 = 1
            goto L_0x0056
        L_0x0043:
            java.lang.String r1 = "source"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L_0x0051
            r1 = 26
            r0[r1] = r3
        L_0x004f:
            r1 = -1
            goto L_0x0056
        L_0x0051:
            r1 = 2
            r2 = 27
            r0[r2] = r3
        L_0x0056:
            r2 = 0
            switch(r1) {
                case 0: goto L_0x0099;
                case 1: goto L_0x007e;
                case 2: goto L_0x0063;
                default: goto L_0x005a;
            }
        L_0x005a:
            boolean r5 = super.setProperty(r5, r6)
            r6 = 40
            r0[r6] = r3
            return r5
        L_0x0063:
            java.lang.String r5 = com.taobao.weex.utils.WXUtils.getString(r6, r2)
            if (r5 != 0) goto L_0x006e
            r5 = 36
            r0[r5] = r3
            goto L_0x0079
        L_0x006e:
            r6 = 37
            r0[r6] = r3
            r4.setSource(r5)
            r5 = 38
            r0[r5] = r3
        L_0x0079:
            r5 = 39
            r0[r5] = r3
            return r3
        L_0x007e:
            java.lang.String r5 = com.taobao.weex.utils.WXUtils.getString(r6, r2)
            if (r5 != 0) goto L_0x0089
            r5 = 32
            r0[r5] = r3
            goto L_0x0094
        L_0x0089:
            r6 = 33
            r0[r6] = r3
            r4.setUrl(r5)
            r5 = 34
            r0[r5] = r3
        L_0x0094:
            r5 = 35
            r0[r5] = r3
            return r3
        L_0x0099:
            java.lang.Boolean r5 = com.taobao.weex.utils.WXUtils.getBoolean(r6, r2)
            if (r5 != 0) goto L_0x00a4
            r5 = 28
            r0[r5] = r3
            goto L_0x00b3
        L_0x00a4:
            r6 = 29
            r0[r6] = r3
            boolean r5 = r5.booleanValue()
            r4.setShowLoading(r5)
            r5 = 30
            r0[r5] = r3
        L_0x00b3:
            r5 = 31
            r0[r5] = r3
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.component.WXWeb.setProperty(java.lang.String, java.lang.Object):boolean");
    }

    @WXComponentProp(name = "show-loading")
    public void setShowLoading(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        getWebView().setShowLoading(z);
        $jacocoInit[41] = true;
    }

    @WXComponentProp(name = "src")
    public void setUrl(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[42] = true;
        } else if (getHostView() == null) {
            $jacocoInit[43] = true;
        } else {
            if (TextUtils.isEmpty(str)) {
                $jacocoInit[45] = true;
            } else {
                $jacocoInit[46] = true;
                loadUrl(getInstance().rewriteUri(Uri.parse(str), "web").toString());
                $jacocoInit[47] = true;
            }
            $jacocoInit[48] = true;
            return;
        }
        $jacocoInit[44] = true;
    }

    @WXComponentProp(name = "source")
    public void setSource(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[49] = true;
        } else if (getHostView() == null) {
            $jacocoInit[50] = true;
        } else {
            $jacocoInit[51] = true;
            loadDataWithBaseURL(str);
            $jacocoInit[52] = true;
        }
        $jacocoInit[53] = true;
    }

    public void setAction(String str, Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[54] = true;
        } else {
            $jacocoInit[55] = true;
            if (str.equals(GO_BACK)) {
                $jacocoInit[56] = true;
                goBack();
                $jacocoInit[57] = true;
            } else if (str.equals(GO_FORWARD)) {
                $jacocoInit[58] = true;
                goForward();
                $jacocoInit[59] = true;
            } else if (str.equals("reload")) {
                $jacocoInit[60] = true;
                reload();
                $jacocoInit[61] = true;
            } else if (!str.equals(POST_MESSAGE)) {
                $jacocoInit[62] = true;
            } else {
                $jacocoInit[63] = true;
                postMessage(obj);
                $jacocoInit[64] = true;
            }
        }
        $jacocoInit[65] = true;
    }

    private void fireEvent(String str, Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!getEvents().contains("error")) {
            $jacocoInit[66] = true;
        } else {
            $jacocoInit[67] = true;
            HashMap hashMap = new HashMap();
            $jacocoInit[68] = true;
            hashMap.put("type", str);
            $jacocoInit[69] = true;
            hashMap.put("errorMsg", obj);
            $jacocoInit[70] = true;
            fireEvent("error", hashMap);
            $jacocoInit[71] = true;
        }
        $jacocoInit[72] = true;
    }

    private void loadUrl(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        getWebView().loadUrl(str);
        $jacocoInit[73] = true;
    }

    private void loadDataWithBaseURL(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        getWebView().loadDataWithBaseURL(str);
        $jacocoInit[74] = true;
    }

    @JSMethod
    public void reload() {
        boolean[] $jacocoInit = $jacocoInit();
        getWebView().reload();
        $jacocoInit[75] = true;
    }

    @JSMethod
    public void goForward() {
        boolean[] $jacocoInit = $jacocoInit();
        getWebView().goForward();
        $jacocoInit[76] = true;
    }

    @JSMethod
    public void goBack() {
        boolean[] $jacocoInit = $jacocoInit();
        getWebView().goBack();
        $jacocoInit[77] = true;
    }

    @JSMethod
    public void postMessage(Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        getWebView().postMessage(obj);
        $jacocoInit[78] = true;
    }

    private IWebView getWebView() {
        boolean[] $jacocoInit = $jacocoInit();
        IWebView iWebView = this.mWebView;
        $jacocoInit[79] = true;
        return iWebView;
    }
}
