package com.taobao.weex.ui.component;

import android.app.Activity;
import android.content.Context;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.mi.global.shop.model.Tags;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.adapter.IWXImgLoaderAdapter;
import com.taobao.weex.annotation.Component;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.Constants;
import com.taobao.weex.common.WXImageSharpen;
import com.taobao.weex.common.WXImageStrategy;
import com.taobao.weex.common.WXRuntimeException;
import com.taobao.weex.dom.WXImageQuality;
import com.taobao.weex.performance.WXAnalyzerDataTransfer;
import com.taobao.weex.performance.WXInstanceApm;
import com.taobao.weex.ui.ComponentCreator;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.view.WXImageView;
import com.taobao.weex.ui.view.border.BorderDrawable;
import com.taobao.weex.utils.ImageDrawable;
import com.taobao.weex.utils.ImgURIUtil;
import com.taobao.weex.utils.SingleFunctionParser;
import com.taobao.weex.utils.WXDomUtils;
import com.taobao.weex.utils.WXLogUtils;
import com.taobao.weex.utils.WXUtils;
import com.taobao.weex.utils.WXViewToImageUtil;
import com.taobao.weex.utils.WXViewUtils;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

@Component(lazyload = false)
public class WXImage extends WXComponent<ImageView> {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    private static SingleFunctionParser.FlatMapper<Integer> BLUR_RADIUS_MAPPER = new SingleFunctionParser.FlatMapper<Integer>() {
        private static transient /* synthetic */ boolean[] $jacocoData;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(-1626776994772928910L, "com/taobao/weex/ui/component/WXImage$1", 3);
            $jacocoData = a2;
            return a2;
        }

        {
            $jacocoInit()[0] = true;
        }

        public Integer map(String str) {
            boolean[] $jacocoInit = $jacocoInit();
            Integer integer = WXUtils.getInteger(str, 0);
            $jacocoInit[1] = true;
            return integer;
        }
    };
    public static final String ERRORDESC = "errorDesc";
    public static final String SUCCEED = "success";
    private static final int WRITE_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE = 2;
    private boolean mAutoRecycle;
    private int mBlurRadius;
    private String mSrc;
    private String preImgUrlStr;

    public interface Measurable {
        int getNaturalHeight();

        int getNaturalWidth();
    }

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(2231524292569509627L, "com/taobao/weex/ui/component/WXImage", 243);
        $jacocoData = a2;
        return a2;
    }

    static /* synthetic */ void access$000(WXImage wXImage, ImageView imageView, String str) {
        boolean[] $jacocoInit = $jacocoInit();
        wXImage.monitorImgSize(imageView, str);
        $jacocoInit[241] = true;
    }

    static {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[242] = true;
    }

    public static class Creator implements ComponentCreator {
        private static transient /* synthetic */ boolean[] $jacocoData;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(3575654740805611977L, "com/taobao/weex/ui/component/WXImage$Creator", 2);
            $jacocoData = a2;
            return a2;
        }

        public Creator() {
            $jacocoInit()[0] = true;
        }

        public WXComponent createInstance(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, BasicComponentData basicComponentData) throws IllegalAccessException, InvocationTargetException, InstantiationException {
            boolean[] $jacocoInit = $jacocoInit();
            WXImage wXImage = new WXImage(wXSDKInstance, wXVContainer, basicComponentData);
            $jacocoInit[1] = true;
            return wXImage;
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @Deprecated
    public WXImage(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, String str, boolean z, BasicComponentData basicComponentData) {
        this(wXSDKInstance, wXVContainer, basicComponentData);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WXImage(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, BasicComponentData basicComponentData) {
        super(wXSDKInstance, wXVContainer, basicComponentData);
        boolean[] $jacocoInit = $jacocoInit();
        this.mAutoRecycle = true;
        this.preImgUrlStr = "";
        $jacocoInit[1] = true;
    }

    /* access modifiers changed from: protected */
    public ImageView initComponentHostView(@NonNull Context context) {
        boolean[] $jacocoInit = $jacocoInit();
        WXImageView wXImageView = new WXImageView(context);
        $jacocoInit[2] = true;
        wXImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        if (Build.VERSION.SDK_INT < 16) {
            $jacocoInit[3] = true;
        } else {
            $jacocoInit[4] = true;
            wXImageView.setCropToPadding(true);
            $jacocoInit[5] = true;
        }
        wXImageView.holdComponent(this);
        $jacocoInit[6] = true;
        return wXImageView;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean setProperty(java.lang.String r6, java.lang.Object r7) {
        /*
            r5 = this;
            boolean[] r0 = $jacocoInit()
            int r1 = r6.hashCode()
            r2 = 0
            r3 = 1
            switch(r1) {
                case -1285653259: goto L_0x0072;
                case -1274492040: goto L_0x005f;
                case -934437708: goto L_0x004c;
                case 114148: goto L_0x0039;
                case 1249477412: goto L_0x0026;
                case 2049757303: goto L_0x0012;
                default: goto L_0x000d;
            }
        L_0x000d:
            r1 = 7
            r0[r1] = r3
            goto L_0x0085
        L_0x0012:
            java.lang.String r1 = "resizeMode"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L_0x0020
            r1 = 8
            r0[r1] = r3
            goto L_0x0085
        L_0x0020:
            r1 = 9
            r0[r1] = r3
            r1 = 0
            goto L_0x0086
        L_0x0026:
            java.lang.String r1 = "imageQuality"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L_0x0033
            r1 = 14
            r0[r1] = r3
            goto L_0x0085
        L_0x0033:
            r1 = 3
            r4 = 15
            r0[r4] = r3
            goto L_0x0086
        L_0x0039:
            java.lang.String r1 = "src"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L_0x0046
            r1 = 12
            r0[r1] = r3
            goto L_0x0085
        L_0x0046:
            r1 = 2
            r4 = 13
            r0[r4] = r3
            goto L_0x0086
        L_0x004c:
            java.lang.String r1 = "resize"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L_0x0059
            r1 = 10
            r0[r1] = r3
            goto L_0x0085
        L_0x0059:
            r1 = 11
            r0[r1] = r3
            r1 = 1
            goto L_0x0086
        L_0x005f:
            java.lang.String r1 = "filter"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L_0x006c
            r1 = 18
            r0[r1] = r3
            goto L_0x0085
        L_0x006c:
            r1 = 5
            r4 = 19
            r0[r4] = r3
            goto L_0x0086
        L_0x0072:
            java.lang.String r1 = "autoBitmapRecycle"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L_0x007f
            r1 = 16
            r0[r1] = r3
            goto L_0x0085
        L_0x007f:
            r1 = 4
            r4 = 17
            r0[r4] = r3
            goto L_0x0086
        L_0x0085:
            r1 = -1
        L_0x0086:
            r4 = 0
            switch(r1) {
                case 0: goto L_0x014f;
                case 1: goto L_0x0134;
                case 2: goto L_0x0119;
                case 3: goto L_0x0114;
                case 4: goto L_0x00d0;
                case 5: goto L_0x0093;
                default: goto L_0x008a;
            }
        L_0x008a:
            boolean r6 = super.setProperty(r6, r7)
            r7 = 47
            r0[r7] = r3
            return r6
        L_0x0093:
            if (r7 != 0) goto L_0x009a
            r6 = 39
            r0[r6] = r3
            goto L_0x00b1
        L_0x009a:
            boolean r6 = r7 instanceof java.lang.String
            if (r6 != 0) goto L_0x00a3
            r6 = 40
            r0[r6] = r3
            goto L_0x00b1
        L_0x00a3:
            r6 = 41
            r0[r6] = r3
            java.lang.String r7 = (java.lang.String) r7
            int r2 = r5.parseBlurRadius(r7)
            r6 = 42
            r0[r6] = r3
        L_0x00b1:
            java.lang.String r6 = r5.mSrc
            boolean r6 = android.text.TextUtils.isEmpty(r6)
            if (r6 == 0) goto L_0x00be
            r6 = 43
            r0[r6] = r3
            goto L_0x00cb
        L_0x00be:
            r6 = 44
            r0[r6] = r3
            java.lang.String r6 = r5.mSrc
            r5.setBlurRadius(r6, r2)
            r6 = 45
            r0[r6] = r3
        L_0x00cb:
            r6 = 46
            r0[r6] = r3
            return r3
        L_0x00d0:
            boolean r6 = r5.mAutoRecycle
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r6)
            java.lang.Boolean r6 = com.taobao.weex.utils.WXUtils.getBoolean(r7, r6)
            boolean r6 = r6.booleanValue()
            r5.mAutoRecycle = r6
            r6 = 33
            r0[r6] = r3
            boolean r6 = r5.mAutoRecycle
            if (r6 == 0) goto L_0x00ed
            r6 = 34
            r0[r6] = r3
            goto L_0x010f
        L_0x00ed:
            com.taobao.weex.WXSDKInstance r6 = r5.getInstance()
            if (r6 != 0) goto L_0x00f8
            r6 = 35
            r0[r6] = r3
            goto L_0x010f
        L_0x00f8:
            r6 = 36
            r0[r6] = r3
            com.taobao.weex.WXSDKInstance r6 = r5.getInstance()
            com.taobao.weex.performance.WXInstanceApm r6 = r6.getApmForInstance()
            java.lang.String r7 = "wxImgUnRecycleCount"
            r1 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r6.updateDiffStats(r7, r1)
            r6 = 37
            r0[r6] = r3
        L_0x010f:
            r6 = 38
            r0[r6] = r3
            return r3
        L_0x0114:
            r6 = 32
            r0[r6] = r3
            return r3
        L_0x0119:
            java.lang.String r6 = com.taobao.weex.utils.WXUtils.getString(r7, r4)
            r7 = 28
            r0[r7] = r3
            if (r6 != 0) goto L_0x0128
            r6 = 29
            r0[r6] = r3
            goto L_0x012f
        L_0x0128:
            r5.setSrc(r6)
            r6 = 30
            r0[r6] = r3
        L_0x012f:
            r6 = 31
            r0[r6] = r3
            return r3
        L_0x0134:
            java.lang.String r6 = com.taobao.weex.utils.WXUtils.getString(r7, r4)
            r7 = 24
            r0[r7] = r3
            if (r6 != 0) goto L_0x0143
            r6 = 25
            r0[r6] = r3
            goto L_0x014a
        L_0x0143:
            r5.setResize(r6)
            r6 = 26
            r0[r6] = r3
        L_0x014a:
            r6 = 27
            r0[r6] = r3
            return r3
        L_0x014f:
            java.lang.String r6 = com.taobao.weex.utils.WXUtils.getString(r7, r4)
            r7 = 20
            r0[r7] = r3
            if (r6 != 0) goto L_0x015e
            r6 = 21
            r0[r6] = r3
            goto L_0x0165
        L_0x015e:
            r5.setResizeMode(r6)
            r6 = 22
            r0[r6] = r3
        L_0x0165:
            r6 = 23
            r0[r6] = r3
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.component.WXImage.setProperty(java.lang.String, java.lang.Object):boolean");
    }

    public void refreshData(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        super.refreshData(wXComponent);
        if (!(wXComponent instanceof WXImage)) {
            $jacocoInit[48] = true;
        } else {
            $jacocoInit[49] = true;
            setSrc(wXComponent.getAttrs().getImageSrc());
            $jacocoInit[50] = true;
        }
        $jacocoInit[51] = true;
    }

    @WXComponentProp(name = "resizeMode")
    public void setResizeMode(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        ((ImageView) getHostView()).setScaleType(getResizeMode(str));
        $jacocoInit[52] = true;
        ((ImageView) getHostView()).setImageDrawable(((ImageView) getHostView()).getDrawable());
        $jacocoInit[53] = true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x007e  */
    @android.support.annotation.RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.widget.ImageView.ScaleType getResizeMode(java.lang.String r7) {
        /*
            r6 = this;
            boolean[] r0 = $jacocoInit()
            android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.FIT_XY
            r2 = 1
            r3 = 54
            r0[r3] = r2
            boolean r3 = android.text.TextUtils.isEmpty(r7)
            if (r3 == 0) goto L_0x0016
            r7 = 55
            r0[r7] = r2
            return r1
        L_0x0016:
            r3 = -1
            int r4 = r7.hashCode()
            r5 = -1881872635(0xffffffff8fd4e705, float:-2.09938E-29)
            if (r4 == r5) goto L_0x0055
            r5 = 94852023(0x5a753b7, float:1.5735357E-35)
            if (r4 == r5) goto L_0x0042
            r5 = 951526612(0x38b724d4, float:8.73298E-5)
            if (r4 == r5) goto L_0x002f
            r7 = 56
            r0[r7] = r2
            goto L_0x0061
        L_0x002f:
            java.lang.String r4 = "contain"
            boolean r7 = r7.equals(r4)
            if (r7 != 0) goto L_0x003c
            r7 = 59
            r0[r7] = r2
            goto L_0x0061
        L_0x003c:
            r7 = 60
            r0[r7] = r2
            r7 = 1
            goto L_0x0068
        L_0x0042:
            java.lang.String r4 = "cover"
            boolean r7 = r7.equals(r4)
            if (r7 != 0) goto L_0x004f
            r7 = 57
            r0[r7] = r2
            goto L_0x0061
        L_0x004f:
            r7 = 0
            r3 = 58
            r0[r3] = r2
            goto L_0x0068
        L_0x0055:
            java.lang.String r4 = "stretch"
            boolean r7 = r7.equals(r4)
            if (r7 != 0) goto L_0x0063
            r7 = 61
            r0[r7] = r2
        L_0x0061:
            r7 = -1
            goto L_0x0068
        L_0x0063:
            r7 = 2
            r3 = 62
            r0[r3] = r2
        L_0x0068:
            switch(r7) {
                case 0: goto L_0x007e;
                case 1: goto L_0x0077;
                case 2: goto L_0x0070;
                default: goto L_0x006b;
            }
        L_0x006b:
            r7 = 63
            r0[r7] = r2
            goto L_0x0084
        L_0x0070:
            android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.FIT_XY
            r7 = 66
            r0[r7] = r2
            goto L_0x0084
        L_0x0077:
            android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.FIT_CENTER
            r7 = 65
            r0[r7] = r2
            goto L_0x0084
        L_0x007e:
            android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.CENTER_CROP
            r7 = 64
            r0[r7] = r2
        L_0x0084:
            r7 = 67
            r0[r7] = r2
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.component.WXImage.getResizeMode(java.lang.String):android.widget.ImageView$ScaleType");
    }

    @WXComponentProp(name = "resize")
    public void setResize(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        setResizeMode(str);
        $jacocoInit[68] = true;
    }

    private void setLocalSrc(Uri uri) {
        boolean[] $jacocoInit = $jacocoInit();
        Drawable drawableFromLoaclSrc = ImgURIUtil.getDrawableFromLoaclSrc(getContext(), uri);
        $jacocoInit[69] = true;
        if (drawableFromLoaclSrc == null) {
            $jacocoInit[70] = true;
        } else {
            ImageView imageView = (ImageView) getHostView();
            if (imageView == null) {
                $jacocoInit[71] = true;
            } else {
                $jacocoInit[72] = true;
                imageView.setImageDrawable(drawableFromLoaclSrc);
                $jacocoInit[73] = true;
            }
        }
        $jacocoInit[74] = true;
    }

    @WXComponentProp(name = "src")
    public void setSrc(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (getInstance().getImageNetworkHandler() == null) {
            $jacocoInit[75] = true;
        } else {
            $jacocoInit[76] = true;
            String fetchLocal = getInstance().getImageNetworkHandler().fetchLocal(str);
            $jacocoInit[77] = true;
            if (TextUtils.isEmpty(fetchLocal)) {
                $jacocoInit[78] = true;
            } else {
                $jacocoInit[79] = true;
                str = fetchLocal;
            }
        }
        if (str == null) {
            $jacocoInit[80] = true;
            return;
        }
        ImageView imageView = (ImageView) getHostView();
        $jacocoInit[81] = true;
        if (!"".equals(str)) {
            $jacocoInit[82] = true;
        } else if (imageView == null) {
            $jacocoInit[83] = true;
        } else {
            $jacocoInit[84] = true;
            imageView.setImageDrawable((Drawable) null);
            $jacocoInit[85] = true;
            return;
        }
        if (imageView == null) {
            $jacocoInit[86] = true;
        } else {
            $jacocoInit[87] = true;
            if (imageView.getDrawable() == null) {
                $jacocoInit[88] = true;
            } else {
                $jacocoInit[89] = true;
                imageView.setImageDrawable((Drawable) null);
                $jacocoInit[90] = true;
            }
        }
        this.mSrc = str;
        $jacocoInit[91] = true;
        WXSDKInstance instance = getInstance();
        $jacocoInit[92] = true;
        Uri rewriteUri = instance.rewriteUri(Uri.parse(str), "image");
        $jacocoInit[93] = true;
        if ("local".equals(rewriteUri.getScheme())) {
            $jacocoInit[94] = true;
            setLocalSrc(rewriteUri);
            $jacocoInit[95] = true;
        } else {
            $jacocoInit[96] = true;
            String blur = getStyles().getBlur();
            $jacocoInit[97] = true;
            int parseBlurRadius = parseBlurRadius(blur);
            $jacocoInit[98] = true;
            setRemoteSrc(rewriteUri, parseBlurRadius);
            $jacocoInit[99] = true;
        }
        $jacocoInit[100] = true;
    }

    private void setBlurRadius(@NonNull String str, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        if (getInstance() == null) {
            $jacocoInit[101] = true;
        } else if (i == this.mBlurRadius) {
            $jacocoInit[102] = true;
        } else {
            $jacocoInit[103] = true;
            Uri rewriteUri = getInstance().rewriteUri(Uri.parse(str), "image");
            $jacocoInit[104] = true;
            if ("local".equals(rewriteUri.getScheme())) {
                $jacocoInit[105] = true;
            } else {
                $jacocoInit[106] = true;
                setRemoteSrc(rewriteUri, i);
                $jacocoInit[107] = true;
            }
        }
        $jacocoInit[108] = true;
    }

    private int parseBlurRadius(@Nullable String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (str == null) {
            $jacocoInit[109] = true;
            return 0;
        }
        SingleFunctionParser singleFunctionParser = new SingleFunctionParser(str, BLUR_RADIUS_MAPPER);
        try {
            $jacocoInit[110] = true;
            List parse = singleFunctionParser.parse(Constants.Event.BLUR);
            if (parse == null) {
                $jacocoInit[112] = true;
            } else if (parse.isEmpty()) {
                $jacocoInit[113] = true;
            } else {
                int intValue = ((Integer) parse.get(0)).intValue();
                $jacocoInit[115] = true;
                return intValue;
            }
            $jacocoInit[114] = true;
            return 0;
        } catch (Exception unused) {
            $jacocoInit[111] = true;
            return 0;
        }
    }

    public void recycled() {
        boolean[] $jacocoInit = $jacocoInit();
        super.recycled();
        $jacocoInit[116] = true;
        if (getInstance().getImgLoaderAdapter() != null) {
            $jacocoInit[117] = true;
            getInstance().getImgLoaderAdapter().setImage((String) null, (ImageView) this.mHost, (WXImageQuality) null, (WXImageStrategy) null);
            $jacocoInit[118] = true;
        } else if (!WXEnvironment.isApkDebugable()) {
            WXLogUtils.e("Error getImgLoaderAdapter() == null");
            $jacocoInit[121] = true;
        } else {
            $jacocoInit[119] = true;
            WXRuntimeException wXRuntimeException = new WXRuntimeException("getImgLoaderAdapter() == null");
            $jacocoInit[120] = true;
            throw wXRuntimeException;
        }
        $jacocoInit[122] = true;
    }

    public void autoReleaseImage() {
        boolean[] $jacocoInit = $jacocoInit();
        if (!this.mAutoRecycle) {
            $jacocoInit[123] = true;
        } else {
            $jacocoInit[124] = true;
            if (getHostView() == null) {
                $jacocoInit[125] = true;
            } else {
                $jacocoInit[126] = true;
                if (getInstance().getImgLoaderAdapter() == null) {
                    $jacocoInit[127] = true;
                } else {
                    $jacocoInit[128] = true;
                    getInstance().getImgLoaderAdapter().setImage((String) null, (ImageView) this.mHost, (WXImageQuality) null, (WXImageStrategy) null);
                    $jacocoInit[129] = true;
                }
            }
        }
        $jacocoInit[130] = true;
    }

    public void autoRecoverImage() {
        boolean[] $jacocoInit = $jacocoInit();
        if (!this.mAutoRecycle) {
            $jacocoInit[131] = true;
        } else {
            $jacocoInit[132] = true;
            setSrc(this.mSrc);
            $jacocoInit[133] = true;
        }
        $jacocoInit[134] = true;
    }

    private void setRemoteSrc(Uri uri, int i) {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        WXImageStrategy wXImageStrategy = new WXImageStrategy(getInstanceId());
        wXImageStrategy.isClipping = true;
        $jacocoInit[135] = true;
        if (getAttrs().getImageSharpen() == WXImageSharpen.SHARPEN) {
            $jacocoInit[136] = true;
            z = true;
        } else {
            $jacocoInit[137] = true;
            z = false;
        }
        wXImageStrategy.isSharpen = z;
        $jacocoInit[138] = true;
        wXImageStrategy.blurRadius = Math.max(0, i);
        this.mBlurRadius = i;
        $jacocoInit[139] = true;
        String uri2 = uri.toString();
        $jacocoInit[140] = true;
        wXImageStrategy.setImageListener(new MyImageListener(this, uri2));
        String str = null;
        $jacocoInit[141] = true;
        if (getAttrs().containsKey("placeholder")) {
            $jacocoInit[142] = true;
            str = (String) getAttrs().get("placeholder");
            $jacocoInit[143] = true;
        } else if (!getAttrs().containsKey(Constants.Name.PLACE_HOLDER)) {
            $jacocoInit[144] = true;
        } else {
            $jacocoInit[145] = true;
            str = (String) getAttrs().get(Constants.Name.PLACE_HOLDER);
            $jacocoInit[146] = true;
        }
        if (str == null) {
            $jacocoInit[147] = true;
        } else {
            $jacocoInit[148] = true;
            wXImageStrategy.placeHolder = getInstance().rewriteUri(Uri.parse(str), "image").toString();
            $jacocoInit[149] = true;
        }
        wXImageStrategy.instanceId = getInstanceId();
        $jacocoInit[150] = true;
        IWXImgLoaderAdapter imgLoaderAdapter = getInstance().getImgLoaderAdapter();
        if (imgLoaderAdapter == null) {
            $jacocoInit[151] = true;
        } else {
            $jacocoInit[152] = true;
            $jacocoInit[153] = true;
            WXImageQuality imageQuality = getImageQuality();
            $jacocoInit[154] = true;
            imgLoaderAdapter.setImage(uri2, (ImageView) getHostView(), imageQuality, wXImageStrategy);
            $jacocoInit[155] = true;
        }
        $jacocoInit[156] = true;
    }

    /* access modifiers changed from: protected */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public WXImageQuality getImageQuality() {
        boolean[] $jacocoInit = $jacocoInit();
        WXImageQuality imageQuality = getAttrs().getImageQuality();
        $jacocoInit[157] = true;
        return imageQuality;
    }

    /* access modifiers changed from: protected */
    public void onFinishLayout() {
        boolean[] $jacocoInit = $jacocoInit();
        super.onFinishLayout();
        $jacocoInit[158] = true;
        updateBorderRadius();
        $jacocoInit[159] = true;
    }

    public void updateProperties(Map<String, Object> map) {
        boolean[] $jacocoInit = $jacocoInit();
        super.updateProperties(map);
        $jacocoInit[160] = true;
        updateBorderRadius();
        $jacocoInit[161] = true;
    }

    private void updateBorderRadius() {
        float[] fArr;
        boolean[] $jacocoInit = $jacocoInit();
        if (!(getHostView() instanceof WXImageView)) {
            $jacocoInit[162] = true;
        } else {
            $jacocoInit[163] = true;
            WXImageView wXImageView = (WXImageView) getHostView();
            $jacocoInit[164] = true;
            BorderDrawable borderDrawable = WXViewUtils.getBorderDrawable(getHostView());
            if (borderDrawable != null) {
                $jacocoInit[165] = true;
                RectF rectF = new RectF(0.0f, 0.0f, WXDomUtils.getContentWidth(this), WXDomUtils.getContentHeight(this));
                $jacocoInit[166] = true;
                fArr = borderDrawable.getBorderInnerRadius(rectF);
                $jacocoInit[167] = true;
            } else {
                fArr = new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
                $jacocoInit[168] = true;
            }
            wXImageView.setBorderRadius(fArr);
            $jacocoInit[169] = true;
            if (!(wXImageView.getDrawable() instanceof ImageDrawable)) {
                $jacocoInit[170] = true;
            } else {
                $jacocoInit[171] = true;
                ImageDrawable imageDrawable = (ImageDrawable) wXImageView.getDrawable();
                $jacocoInit[172] = true;
                float[] cornerRadii = imageDrawable.getCornerRadii();
                $jacocoInit[173] = true;
                if (Arrays.equals(cornerRadii, fArr)) {
                    $jacocoInit[174] = true;
                } else {
                    $jacocoInit[175] = true;
                    imageDrawable.setCornerRadii(fArr);
                    $jacocoInit[176] = true;
                }
            }
        }
        $jacocoInit[177] = true;
    }

    @JSMethod(uiThread = false)
    public void save(final JSCallback jSCallback) {
        boolean[] $jacocoInit = $jacocoInit();
        if (ContextCompat.checkSelfPermission(getContext(), "android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
            $jacocoInit[178] = true;
        } else {
            $jacocoInit[179] = true;
            if (!(getContext() instanceof Activity)) {
                $jacocoInit[180] = true;
            } else {
                $jacocoInit[181] = true;
                ActivityCompat.requestPermissions((Activity) getContext(), new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 2);
                $jacocoInit[182] = true;
            }
        }
        if (ContextCompat.checkSelfPermission(getContext(), "android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            if (jSCallback == null) {
                $jacocoInit[183] = true;
            } else {
                $jacocoInit[184] = true;
                HashMap hashMap = new HashMap();
                $jacocoInit[185] = true;
                hashMap.put("success", false);
                $jacocoInit[186] = true;
                hashMap.put("errorDesc", "Permission denied: android.permission.WRITE_EXTERNAL_STORAGE");
                $jacocoInit[187] = true;
                jSCallback.invoke(hashMap);
                $jacocoInit[188] = true;
            }
            $jacocoInit[189] = true;
        } else if (this.mHost == null) {
            if (jSCallback == null) {
                $jacocoInit[190] = true;
            } else {
                $jacocoInit[191] = true;
                HashMap hashMap2 = new HashMap();
                $jacocoInit[192] = true;
                hashMap2.put("success", false);
                $jacocoInit[193] = true;
                hashMap2.put("errorDesc", "Image component not initialized");
                $jacocoInit[194] = true;
                jSCallback.invoke(hashMap2);
                $jacocoInit[195] = true;
            }
            $jacocoInit[196] = true;
        } else {
            if (this.mSrc == null) {
                $jacocoInit[197] = true;
            } else if (this.mSrc.equals("")) {
                $jacocoInit[198] = true;
            } else {
                WXViewToImageUtil.generateImage(this.mHost, 0, -460552, new WXViewToImageUtil.OnImageSavedCallback(this) {
                    private static transient /* synthetic */ boolean[] $jacocoData;
                    final /* synthetic */ WXImage this$0;

                    private static /* synthetic */ boolean[] $jacocoInit() {
                        boolean[] zArr = $jacocoData;
                        if (zArr != null) {
                            return zArr;
                        }
                        boolean[] a2 = Offline.a(-3242761516505656374L, "com/taobao/weex/ui/component/WXImage$2", 14);
                        $jacocoData = a2;
                        return a2;
                    }

                    {
                        boolean[] $jacocoInit = $jacocoInit();
                        this.this$0 = r2;
                        $jacocoInit[0] = true;
                    }

                    public void onSaveSucceed(String str) {
                        boolean[] $jacocoInit = $jacocoInit();
                        if (jSCallback == null) {
                            $jacocoInit[1] = true;
                        } else {
                            $jacocoInit[2] = true;
                            HashMap hashMap = new HashMap();
                            $jacocoInit[3] = true;
                            hashMap.put("success", true);
                            $jacocoInit[4] = true;
                            jSCallback.invoke(hashMap);
                            $jacocoInit[5] = true;
                        }
                        $jacocoInit[6] = true;
                    }

                    public void onSaveFailed(String str) {
                        boolean[] $jacocoInit = $jacocoInit();
                        if (jSCallback == null) {
                            $jacocoInit[7] = true;
                        } else {
                            $jacocoInit[8] = true;
                            HashMap hashMap = new HashMap();
                            $jacocoInit[9] = true;
                            hashMap.put("success", false);
                            $jacocoInit[10] = true;
                            hashMap.put("errorDesc", str);
                            $jacocoInit[11] = true;
                            jSCallback.invoke(hashMap);
                            $jacocoInit[12] = true;
                        }
                        $jacocoInit[13] = true;
                    }
                });
                $jacocoInit[206] = true;
                return;
            }
            if (jSCallback == null) {
                $jacocoInit[199] = true;
            } else {
                $jacocoInit[200] = true;
                HashMap hashMap3 = new HashMap();
                $jacocoInit[201] = true;
                hashMap3.put("success", false);
                $jacocoInit[202] = true;
                hashMap3.put("errorDesc", "Image does not have the correct src");
                $jacocoInit[203] = true;
                jSCallback.invoke(hashMap3);
                $jacocoInit[204] = true;
            }
            $jacocoInit[205] = true;
        }
    }

    private void monitorImgSize(ImageView imageView, String str) {
        boolean z;
        String str2 = str;
        boolean[] $jacocoInit = $jacocoInit();
        if (imageView == null) {
            $jacocoInit[207] = true;
            return;
        }
        WXSDKInstance instance = getInstance();
        if (instance == null) {
            $jacocoInit[208] = true;
            return;
        }
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        $jacocoInit[209] = true;
        Drawable drawable = imageView.getDrawable();
        if (layoutParams == null) {
            $jacocoInit[210] = true;
        } else if (drawable == null) {
            $jacocoInit[211] = true;
        } else {
            int intrinsicHeight = drawable.getIntrinsicHeight();
            $jacocoInit[213] = true;
            int intrinsicWidth = drawable.getIntrinsicWidth();
            $jacocoInit[214] = true;
            if (this.preImgUrlStr.equals(str2)) {
                $jacocoInit[215] = true;
            } else {
                this.preImgUrlStr = str2;
                if (intrinsicHeight <= 1081) {
                    $jacocoInit[216] = true;
                } else if (intrinsicWidth <= 721) {
                    $jacocoInit[217] = true;
                } else {
                    $jacocoInit[218] = true;
                    instance.getApmForInstance().updateDiffStats(WXInstanceApm.KEY_PAGE_STATS_LARGE_IMG_COUNT, 1.0d);
                    if (!WXAnalyzerDataTransfer.isOpenPerformance) {
                        $jacocoInit[219] = true;
                    } else {
                        $jacocoInit[220] = true;
                        WXAnalyzerDataTransfer.transferPerformance(getInstanceId(), Tags.MiPhoneDetails.DETAILS, WXInstanceApm.KEY_PAGE_STATS_LARGE_IMG_COUNT, intrinsicWidth + "*" + intrinsicHeight + "," + str2);
                        $jacocoInit[221] = true;
                    }
                }
                long j = (long) (intrinsicHeight * intrinsicWidth);
                $jacocoInit[222] = true;
                long measuredHeight = (long) (imageView.getMeasuredHeight() * imageView.getMeasuredWidth());
                if (measuredHeight == 0) {
                    $jacocoInit[223] = true;
                    return;
                }
                double d = (double) j;
                double d2 = (double) measuredHeight;
                Double.isNaN(d);
                Double.isNaN(d2);
                if (d / d2 <= 1.2d) {
                    $jacocoInit[224] = true;
                } else if (j - measuredHeight <= 1600) {
                    $jacocoInit[225] = true;
                } else {
                    $jacocoInit[226] = true;
                    instance.getWXPerformance().wrongImgSizeCount += 1.0d;
                    $jacocoInit[227] = true;
                    instance.getApmForInstance().updateDiffStats(WXInstanceApm.KEY_PAGE_STATS_WRONG_IMG_SIZE_COUNT, 1.0d);
                    if (!WXAnalyzerDataTransfer.isOpenPerformance) {
                        $jacocoInit[228] = true;
                    } else {
                        $jacocoInit[229] = true;
                        String instanceId = getInstanceId();
                        $jacocoInit[230] = true;
                        String format = String.format("imgSize:[%d,%d],viewSize:[%d,%d],urL:%s", new Object[]{Integer.valueOf(intrinsicWidth), Integer.valueOf(intrinsicHeight), Integer.valueOf(imageView.getMeasuredWidth()), Integer.valueOf(imageView.getMeasuredHeight()), str2});
                        z = true;
                        $jacocoInit[231] = true;
                        WXAnalyzerDataTransfer.transferPerformance(instanceId, Tags.MiPhoneDetails.DETAILS, WXInstanceApm.KEY_PAGE_STATS_WRONG_IMG_SIZE_COUNT, format);
                        $jacocoInit[232] = true;
                        $jacocoInit[233] = z;
                        return;
                    }
                }
            }
            z = true;
            $jacocoInit[233] = z;
            return;
        }
        $jacocoInit[212] = true;
    }

    public void destroy() {
        boolean[] $jacocoInit = $jacocoInit();
        if (!(getHostView() instanceof WXImageView)) {
            $jacocoInit[234] = true;
        } else {
            $jacocoInit[235] = true;
            if (getInstance().getImgLoaderAdapter() == null) {
                $jacocoInit[236] = true;
            } else {
                $jacocoInit[237] = true;
                getInstance().getImgLoaderAdapter().setImage((String) null, (ImageView) this.mHost, (WXImageQuality) null, (WXImageStrategy) null);
                $jacocoInit[238] = true;
            }
        }
        super.destroy();
        $jacocoInit[239] = true;
    }

    public static class MyImageListener implements WXImageStrategy.ImageListener {
        private static transient /* synthetic */ boolean[] $jacocoData;
        private String rewritedStr;
        private WeakReference<WXImage> wxImageWeakReference;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(-8280770150530843379L, "com/taobao/weex/ui/component/WXImage$MyImageListener", 19);
            $jacocoData = a2;
            return a2;
        }

        MyImageListener(WXImage wXImage, String str) {
            boolean[] $jacocoInit = $jacocoInit();
            $jacocoInit[0] = true;
            this.wxImageWeakReference = new WeakReference<>(wXImage);
            this.rewritedStr = str;
            $jacocoInit[1] = true;
        }

        /* JADX WARNING: Removed duplicated region for block: B:17:0x008e  */
        /* JADX WARNING: Removed duplicated region for block: B:18:0x0093  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onImageFinish(java.lang.String r7, android.widget.ImageView r8, boolean r9, java.util.Map r10) {
            /*
                r6 = this;
                boolean[] r7 = $jacocoInit()
                java.lang.ref.WeakReference<com.taobao.weex.ui.component.WXImage> r10 = r6.wxImageWeakReference
                java.lang.Object r10 = r10.get()
                com.taobao.weex.ui.component.WXImage r10 = (com.taobao.weex.ui.component.WXImage) r10
                r0 = 2
                r1 = 1
                if (r10 != 0) goto L_0x0013
                r7[r0] = r1
                return
            L_0x0013:
                com.taobao.weex.dom.WXEvent r2 = r10.getEvents()
                java.lang.String r3 = "load"
                boolean r2 = r2.contains(r3)
                if (r2 != 0) goto L_0x0024
                r9 = 3
                r7[r9] = r1
                goto L_0x00b6
            L_0x0024:
                r2 = 4
                r7[r2] = r1
                java.util.HashMap r2 = new java.util.HashMap
                r2.<init>()
                r3 = 5
                r7[r3] = r1
                java.util.HashMap r3 = new java.util.HashMap
                r3.<init>(r0)
                if (r8 != 0) goto L_0x003a
                r0 = 6
                r7[r0] = r1
                goto L_0x0041
            L_0x003a:
                boolean r0 = r8 instanceof com.taobao.weex.ui.component.WXImage.Measurable
                if (r0 != 0) goto L_0x005d
                r0 = 7
                r7[r0] = r1
            L_0x0041:
                java.lang.String r0 = "naturalWidth"
                r4 = 0
                java.lang.Integer r5 = java.lang.Integer.valueOf(r4)
                r3.put(r0, r5)
                r0 = 11
                r7[r0] = r1
                java.lang.String r0 = "naturalHeight"
                java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
                r3.put(r0, r4)
                r0 = 12
                r7[r0] = r1
                goto L_0x0086
            L_0x005d:
                r0 = 8
                r7[r0] = r1
                java.lang.String r0 = "naturalWidth"
                r4 = r8
                com.taobao.weex.ui.component.WXImage$Measurable r4 = (com.taobao.weex.ui.component.WXImage.Measurable) r4
                int r5 = r4.getNaturalWidth()
                java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
                r3.put(r0, r5)
                r0 = 9
                r7[r0] = r1
                java.lang.String r0 = "naturalHeight"
                int r4 = r4.getNaturalHeight()
                java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
                r3.put(r0, r4)
                r0 = 10
                r7[r0] = r1
            L_0x0086:
                java.lang.String r0 = "load"
                boolean r0 = r10.containsEvent(r0)
                if (r0 != 0) goto L_0x0093
                r9 = 13
                r7[r9] = r1
                goto L_0x00b6
            L_0x0093:
                r0 = 14
                r7[r0] = r1
                java.lang.String r0 = "success"
                java.lang.Boolean r9 = java.lang.Boolean.valueOf(r9)
                r2.put(r0, r9)
                r9 = 15
                r7[r9] = r1
                java.lang.String r9 = "size"
                r2.put(r9, r3)
                r9 = 16
                r7[r9] = r1
                java.lang.String r9 = "load"
                r10.fireEvent(r9, r2)
                r9 = 17
                r7[r9] = r1
            L_0x00b6:
                java.lang.String r9 = r6.rewritedStr
                com.taobao.weex.ui.component.WXImage.access$000(r10, r8, r9)
                r8 = 18
                r7[r8] = r1
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.component.WXImage.MyImageListener.onImageFinish(java.lang.String, android.widget.ImageView, boolean, java.util.Map):void");
        }
    }
}
