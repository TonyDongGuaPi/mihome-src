package com.taobao.weex.ui.component;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import com.alibaba.fastjson.JSONObject;
import com.mi.global.bbs.ui.activity.ActivitiesActivity;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.WXBridgeManager;
import com.taobao.weex.common.Constants;
import com.taobao.weex.common.WXThread;
import com.taobao.weex.dom.CSSConstants;
import com.taobao.weex.dom.WXStyle;
import com.taobao.weex.layout.ContentBoxMeasurement;
import com.taobao.weex.layout.MeasureMode;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.helper.SoftKeyboardDetector;
import com.taobao.weex.ui.view.WXEditText;
import com.taobao.weex.utils.TypefaceUtil;
import com.taobao.weex.utils.WXLogUtils;
import com.taobao.weex.utils.WXResourceUtils;
import com.taobao.weex.utils.WXViewUtils;
import com.xiaomi.mistatistic.sdk.BaseService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public abstract class AbstractEditComponent extends WXComponent<WXEditText> {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    private static final int MAX_TEXT_FORMAT_REPEAT = 3;
    private boolean mAutoFocus;
    private String mBeforeText = "";
    private int mEditorAction = 6;
    private List<TextView.OnEditorActionListener> mEditorActionListeners;
    private int mFormatRepeatCount = 0;
    private TextFormatter mFormatter = null;
    private boolean mIgnoreNextOnInputEvent = false;
    private final InputMethodManager mInputMethodManager;
    private boolean mKeepSelectionIndex = false;
    private String mLastValue = "";
    private int mLineHeight;
    private boolean mListeningKeyboard = false;
    private String mMax = null;
    private String mMin = null;
    private WXComponent.OnClickListener mOnClickListener;
    private TextPaint mPaint;
    private String mReturnKeyType = null;
    private TextWatcher mTextChangedEventDispatcher;
    private List<TextWatcher> mTextChangedListeners;
    private String mType = "text";
    private SoftKeyboardDetector.Unregister mUnregister;

    private interface ReturnTypes {
        public static final String DEFAULT = "default";
        public static final String DONE = "done";
        public static final String GO = "go";
        public static final String NEXT = "next";
        public static final String SEARCH = "search";
        public static final String SEND = "send";
    }

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-7479207086825260734L, "com/taobao/weex/ui/component/AbstractEditComponent", 480);
        $jacocoData = a2;
        return a2;
    }

    static /* synthetic */ void access$000(AbstractEditComponent abstractEditComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        abstractEditComponent.decideSoftKeyboard();
        $jacocoInit[459] = true;
    }

    static /* synthetic */ String access$100(AbstractEditComponent abstractEditComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        String str = abstractEditComponent.mType;
        $jacocoInit[460] = true;
        return str;
    }

    static /* synthetic */ String access$1000(AbstractEditComponent abstractEditComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        String str = abstractEditComponent.mLastValue;
        $jacocoInit[469] = true;
        return str;
    }

    static /* synthetic */ String access$1002(AbstractEditComponent abstractEditComponent, String str) {
        boolean[] $jacocoInit = $jacocoInit();
        abstractEditComponent.mLastValue = str;
        $jacocoInit[468] = true;
        return str;
    }

    static /* synthetic */ void access$1100(AbstractEditComponent abstractEditComponent, String str, String str2) {
        boolean[] $jacocoInit = $jacocoInit();
        abstractEditComponent.fireEvent(str, str2);
        $jacocoInit[470] = true;
    }

    static /* synthetic */ int access$1200(AbstractEditComponent abstractEditComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        int i = abstractEditComponent.mEditorAction;
        $jacocoInit[471] = true;
        return i;
    }

    static /* synthetic */ boolean access$1300(AbstractEditComponent abstractEditComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = abstractEditComponent.mIgnoreNextOnInputEvent;
        $jacocoInit[472] = true;
        return z;
    }

    static /* synthetic */ boolean access$1302(AbstractEditComponent abstractEditComponent, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        abstractEditComponent.mIgnoreNextOnInputEvent = z;
        $jacocoInit[473] = true;
        return z;
    }

    static /* synthetic */ String access$1400(AbstractEditComponent abstractEditComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        String str = abstractEditComponent.mBeforeText;
        $jacocoInit[475] = true;
        return str;
    }

    static /* synthetic */ String access$1402(AbstractEditComponent abstractEditComponent, String str) {
        boolean[] $jacocoInit = $jacocoInit();
        abstractEditComponent.mBeforeText = str;
        $jacocoInit[474] = true;
        return str;
    }

    static /* synthetic */ String access$1500(AbstractEditComponent abstractEditComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        String str = abstractEditComponent.mReturnKeyType;
        $jacocoInit[476] = true;
        return str;
    }

    static /* synthetic */ InputMethodManager access$1600(AbstractEditComponent abstractEditComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        InputMethodManager inputMethodManager = abstractEditComponent.mInputMethodManager;
        $jacocoInit[477] = true;
        return inputMethodManager;
    }

    static /* synthetic */ List access$1800(AbstractEditComponent abstractEditComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        List<TextView.OnEditorActionListener> list = abstractEditComponent.mEditorActionListeners;
        $jacocoInit[478] = true;
        return list;
    }

    static /* synthetic */ boolean access$1900(AbstractEditComponent abstractEditComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = abstractEditComponent.mListeningKeyboard;
        $jacocoInit[479] = true;
        return z;
    }

    static /* synthetic */ void access$200(AbstractEditComponent abstractEditComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        abstractEditComponent.hideSoftKeyboard();
        $jacocoInit[461] = true;
    }

    static /* synthetic */ String access$300(AbstractEditComponent abstractEditComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        String str = abstractEditComponent.mMax;
        $jacocoInit[462] = true;
        return str;
    }

    static /* synthetic */ String access$400(AbstractEditComponent abstractEditComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        String str = abstractEditComponent.mMin;
        $jacocoInit[463] = true;
        return str;
    }

    static /* synthetic */ List access$500(AbstractEditComponent abstractEditComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        List<TextWatcher> list = abstractEditComponent.mTextChangedListeners;
        $jacocoInit[464] = true;
        return list;
    }

    static /* synthetic */ TextFormatter access$600(AbstractEditComponent abstractEditComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        TextFormatter textFormatter = abstractEditComponent.mFormatter;
        $jacocoInit[465] = true;
        return textFormatter;
    }

    static /* synthetic */ int access$900(AbstractEditComponent abstractEditComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        int i = abstractEditComponent.mFormatRepeatCount;
        $jacocoInit[466] = true;
        return i;
    }

    static /* synthetic */ int access$902(AbstractEditComponent abstractEditComponent, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        abstractEditComponent.mFormatRepeatCount = i;
        $jacocoInit[467] = true;
        return i;
    }

    /* access modifiers changed from: protected */
    public /* synthetic */ void onHostViewInitialized(View view) {
        boolean[] $jacocoInit = $jacocoInit();
        onHostViewInitialized((WXEditText) view);
        $jacocoInit[457] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AbstractEditComponent(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, boolean z, BasicComponentData basicComponentData) {
        super(wXSDKInstance, wXVContainer, z, basicComponentData);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
        this.mPaint = new TextPaint();
        this.mLineHeight = -1;
        $jacocoInit[1] = true;
        this.mOnClickListener = new WXComponent.OnClickListener(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ AbstractEditComponent this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(-1406938497801548201L, "com/taobao/weex/ui/component/AbstractEditComponent$3", 18);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r3;
                $jacocoInit[0] = true;
            }

            /* JADX WARNING: Removed duplicated region for block: B:15:0x0041  */
            /* JADX WARNING: Removed duplicated region for block: B:16:0x0045  */
            /* JADX WARNING: Removed duplicated region for block: B:21:0x0076  */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onHostViewClick() {
                /*
                    r5 = this;
                    boolean[] r0 = $jacocoInit()
                    com.taobao.weex.ui.component.AbstractEditComponent r1 = r5.this$0
                    java.lang.String r1 = com.taobao.weex.ui.component.AbstractEditComponent.access$100(r1)
                    int r2 = r1.hashCode()
                    r3 = 3076014(0x2eefae, float:4.310414E-39)
                    r4 = 1
                    if (r2 == r3) goto L_0x002d
                    r3 = 3560141(0x3652cd, float:4.98882E-39)
                    if (r2 == r3) goto L_0x001c
                    r0[r4] = r4
                    goto L_0x0038
                L_0x001c:
                    java.lang.String r2 = "time"
                    boolean r1 = r1.equals(r2)
                    if (r1 != 0) goto L_0x0028
                    r1 = 4
                    r0[r1] = r4
                    goto L_0x0038
                L_0x0028:
                    r1 = 5
                    r0[r1] = r4
                    r1 = 1
                    goto L_0x003e
                L_0x002d:
                    java.lang.String r2 = "date"
                    boolean r1 = r1.equals(r2)
                    if (r1 != 0) goto L_0x003a
                    r1 = 2
                    r0[r1] = r4
                L_0x0038:
                    r1 = -1
                    goto L_0x003e
                L_0x003a:
                    r1 = 0
                    r2 = 3
                    r0[r2] = r4
                L_0x003e:
                    switch(r1) {
                        case 0: goto L_0x0076;
                        case 1: goto L_0x0045;
                        default: goto L_0x0041;
                    }
                L_0x0041:
                    r1 = 6
                    r0[r1] = r4
                    goto L_0x00b1
                L_0x0045:
                    com.taobao.weex.ui.component.AbstractEditComponent r1 = r5.this$0
                    com.taobao.weex.ui.component.AbstractEditComponent.access$200(r1)
                    r1 = 12
                    r0[r1] = r4
                    com.taobao.weex.ui.component.AbstractEditComponent r1 = r5.this$0
                    com.taobao.weex.ui.component.WXVContainer r1 = r1.getParent()
                    if (r1 != 0) goto L_0x005b
                    r1 = 13
                    r0[r1] = r4
                    goto L_0x006c
                L_0x005b:
                    r1 = 14
                    r0[r1] = r4
                    com.taobao.weex.ui.component.AbstractEditComponent r1 = r5.this$0
                    com.taobao.weex.ui.component.WXVContainer r1 = r1.getParent()
                    r1.interceptFocus()
                    r1 = 15
                    r0[r1] = r4
                L_0x006c:
                    com.taobao.weex.ui.component.AbstractEditComponent r1 = r5.this$0
                    com.taobao.weex.ui.component.helper.WXTimeInputHelper.pickTime(r1)
                    r1 = 16
                    r0[r1] = r4
                    goto L_0x00b1
                L_0x0076:
                    com.taobao.weex.ui.component.AbstractEditComponent r1 = r5.this$0
                    com.taobao.weex.ui.component.AbstractEditComponent.access$200(r1)
                    r1 = 7
                    r0[r1] = r4
                    com.taobao.weex.ui.component.AbstractEditComponent r1 = r5.this$0
                    com.taobao.weex.ui.component.WXVContainer r1 = r1.getParent()
                    if (r1 != 0) goto L_0x008b
                    r1 = 8
                    r0[r1] = r4
                    goto L_0x009c
                L_0x008b:
                    r1 = 9
                    r0[r1] = r4
                    com.taobao.weex.ui.component.AbstractEditComponent r1 = r5.this$0
                    com.taobao.weex.ui.component.WXVContainer r1 = r1.getParent()
                    r1.interceptFocus()
                    r1 = 10
                    r0[r1] = r4
                L_0x009c:
                    com.taobao.weex.ui.component.AbstractEditComponent r1 = r5.this$0
                    java.lang.String r1 = com.taobao.weex.ui.component.AbstractEditComponent.access$300(r1)
                    com.taobao.weex.ui.component.AbstractEditComponent r2 = r5.this$0
                    java.lang.String r2 = com.taobao.weex.ui.component.AbstractEditComponent.access$400(r2)
                    com.taobao.weex.ui.component.AbstractEditComponent r3 = r5.this$0
                    com.taobao.weex.ui.component.helper.WXTimeInputHelper.pickDate(r1, r2, r3)
                    r1 = 11
                    r0[r1] = r4
                L_0x00b1:
                    r1 = 17
                    r0[r1] = r4
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.component.AbstractEditComponent.AnonymousClass3.onHostViewClick():void");
            }
        };
        $jacocoInit[2] = true;
        this.mInputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
        $jacocoInit[3] = true;
        setContentBoxMeasurement(new ContentBoxMeasurement(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ AbstractEditComponent this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(-5641358977518806048L, "com/taobao/weex/ui/component/AbstractEditComponent$1", 9);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r3;
                $jacocoInit[0] = true;
            }

            public void measureInternal(float f, float f2, int i, int i2) {
                boolean[] $jacocoInit = $jacocoInit();
                if (CSSConstants.isUndefined(f)) {
                    $jacocoInit[1] = true;
                } else if (i != MeasureMode.UNSPECIFIED) {
                    $jacocoInit[2] = true;
                    this.mMeasureWidth = f;
                    $jacocoInit[5] = true;
                    this.mMeasureHeight = this.this$0.getMeasureHeight();
                    $jacocoInit[6] = true;
                } else {
                    $jacocoInit[3] = true;
                }
                f = 0.0f;
                $jacocoInit[4] = true;
                this.mMeasureWidth = f;
                $jacocoInit[5] = true;
                this.mMeasureHeight = this.this$0.getMeasureHeight();
                $jacocoInit[6] = true;
            }

            public void layoutBefore() {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0.updateStyleAndAttrs();
                $jacocoInit[7] = true;
            }

            public void layoutAfter(float f, float f2) {
                $jacocoInit()[8] = true;
            }
        });
        $jacocoInit[4] = true;
    }

    /* access modifiers changed from: protected */
    public void layoutDirectionDidChanged(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[5] = true;
        int textAlign = getTextAlign((String) getStyles().get("textAlign"));
        if (textAlign > 0) {
            $jacocoInit[6] = true;
        } else {
            textAlign = GravityCompat.START;
            $jacocoInit[7] = true;
        }
        if (!(getHostView() instanceof WXEditText)) {
            $jacocoInit[8] = true;
        } else {
            $jacocoInit[9] = true;
            ((WXEditText) getHostView()).setGravity(textAlign | getVerticalGravity());
            $jacocoInit[10] = true;
        }
        $jacocoInit[11] = true;
    }

    /* access modifiers changed from: protected */
    public final float getMeasuredLineHeight() {
        float f;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mLineHeight == -1) {
            $jacocoInit[12] = true;
        } else if (this.mLineHeight <= 0) {
            $jacocoInit[13] = true;
        } else {
            f = (float) this.mLineHeight;
            $jacocoInit[14] = true;
            $jacocoInit[16] = true;
            return f;
        }
        f = this.mPaint.getFontMetrics((Paint.FontMetrics) null);
        $jacocoInit[15] = true;
        $jacocoInit[16] = true;
        return f;
    }

    /* access modifiers changed from: protected */
    public float getMeasureHeight() {
        boolean[] $jacocoInit = $jacocoInit();
        float measuredLineHeight = getMeasuredLineHeight();
        $jacocoInit[17] = true;
        return measuredLineHeight;
    }

    /* access modifiers changed from: protected */
    public void updateStyleAndAttrs() {
        int i;
        int i2;
        int i3;
        boolean[] $jacocoInit = $jacocoInit();
        if (getStyles().size() <= 0) {
            $jacocoInit[18] = true;
        } else {
            String str = null;
            $jacocoInit[19] = true;
            if (!getStyles().containsKey("fontSize")) {
                $jacocoInit[20] = true;
                i = -1;
            } else {
                $jacocoInit[21] = true;
                i = WXStyle.getFontSize(getStyles(), getViewPortWidth());
                $jacocoInit[22] = true;
            }
            if (!getStyles().containsKey("fontFamily")) {
                $jacocoInit[23] = true;
            } else {
                $jacocoInit[24] = true;
                str = WXStyle.getFontFamily(getStyles());
                $jacocoInit[25] = true;
            }
            if (!getStyles().containsKey("fontStyle")) {
                $jacocoInit[26] = true;
                i2 = -1;
            } else {
                $jacocoInit[27] = true;
                i2 = WXStyle.getFontStyle(getStyles());
                $jacocoInit[28] = true;
            }
            if (!getStyles().containsKey("fontWeight")) {
                $jacocoInit[29] = true;
                i3 = -1;
            } else {
                $jacocoInit[30] = true;
                i3 = WXStyle.getFontWeight(getStyles());
                $jacocoInit[31] = true;
            }
            int lineHeight = WXStyle.getLineHeight(getStyles(), getViewPortWidth());
            if (lineHeight == -1) {
                $jacocoInit[32] = true;
            } else {
                this.mLineHeight = lineHeight;
                $jacocoInit[33] = true;
            }
            if (i == -1) {
                $jacocoInit[34] = true;
            } else {
                $jacocoInit[35] = true;
                this.mPaint.setTextSize((float) i);
                $jacocoInit[36] = true;
            }
            if (str == null) {
                $jacocoInit[37] = true;
            } else {
                $jacocoInit[38] = true;
                TypefaceUtil.applyFontStyle(this.mPaint, i2, i3, str);
                $jacocoInit[39] = true;
            }
        }
        $jacocoInit[40] = true;
    }

    /* access modifiers changed from: protected */
    public WXEditText initComponentHostView(@NonNull Context context) {
        boolean[] $jacocoInit = $jacocoInit();
        WXEditText wXEditText = new WXEditText(context);
        $jacocoInit[41] = true;
        appleStyleAfterCreated(wXEditText);
        $jacocoInit[42] = true;
        return wXEditText;
    }

    /* access modifiers changed from: protected */
    public void onHostViewInitialized(WXEditText wXEditText) {
        boolean[] $jacocoInit = $jacocoInit();
        super.onHostViewInitialized(wXEditText);
        $jacocoInit[43] = true;
        addFocusChangeListener(new WXComponent.OnFocusChangeListener(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ AbstractEditComponent this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(6124706660004788558L, "com/taobao/weex/ui/component/AbstractEditComponent$2", 5);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r3;
                $jacocoInit[0] = true;
            }

            public void onFocusChange(boolean z) {
                boolean[] $jacocoInit = $jacocoInit();
                if (z) {
                    $jacocoInit[1] = true;
                } else {
                    $jacocoInit[2] = true;
                    AbstractEditComponent.access$000(this.this$0);
                    $jacocoInit[3] = true;
                }
                this.this$0.setPseudoClassStatus(Constants.PSEUDO.FOCUS, z);
                $jacocoInit[4] = true;
            }
        });
        $jacocoInit[44] = true;
        addKeyboardListener(wXEditText);
        $jacocoInit[45] = true;
    }

    /* access modifiers changed from: protected */
    public boolean isConsumeTouch() {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (!isDisabled()) {
            $jacocoInit[46] = true;
            z = true;
        } else {
            z = false;
            $jacocoInit[47] = true;
        }
        $jacocoInit[48] = true;
        return z;
    }

    private void applyOnClickListener() {
        boolean[] $jacocoInit = $jacocoInit();
        addClickListener(this.mOnClickListener);
        $jacocoInit[49] = true;
    }

    /* access modifiers changed from: protected */
    public int getVerticalGravity() {
        $jacocoInit()[50] = true;
        return 16;
    }

    /* access modifiers changed from: protected */
    public void appleStyleAfterCreated(final WXEditText wXEditText) {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[51] = true;
        int textAlign = getTextAlign((String) getStyles().get("textAlign"));
        if (textAlign > 0) {
            $jacocoInit[52] = true;
        } else {
            textAlign = GravityCompat.START;
            $jacocoInit[53] = true;
        }
        wXEditText.setGravity(textAlign | getVerticalGravity());
        $jacocoInit[54] = true;
        int color = WXResourceUtils.getColor(ActivitiesActivity.ONLINE_ACTIVITY_TAB_TEXT_COLOR);
        if (color == Integer.MIN_VALUE) {
            $jacocoInit[55] = true;
        } else {
            $jacocoInit[56] = true;
            wXEditText.setHintTextColor(color);
            $jacocoInit[57] = true;
        }
        this.mTextChangedEventDispatcher = new TextWatcher(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ AbstractEditComponent this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(-4396712001482348559L, "com/taobao/weex/ui/component/AbstractEditComponent$4", 35);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r2;
                $jacocoInit[0] = true;
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                boolean[] $jacocoInit = $jacocoInit();
                if (AbstractEditComponent.access$500(this.this$0) == null) {
                    $jacocoInit[1] = true;
                } else {
                    $jacocoInit[2] = true;
                    $jacocoInit[3] = true;
                    for (TextWatcher beforeTextChanged : AbstractEditComponent.access$500(this.this$0)) {
                        $jacocoInit[5] = true;
                        beforeTextChanged.beforeTextChanged(charSequence, i, i2, i3);
                        $jacocoInit[6] = true;
                    }
                    $jacocoInit[4] = true;
                }
                $jacocoInit[7] = true;
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                boolean[] $jacocoInit = $jacocoInit();
                if (AbstractEditComponent.access$600(this.this$0) == null) {
                    $jacocoInit[8] = true;
                } else {
                    $jacocoInit[9] = true;
                    String access$700 = TextFormatter.access$700(AbstractEditComponent.access$600(this.this$0), charSequence.toString());
                    $jacocoInit[10] = true;
                    String access$800 = TextFormatter.access$800(AbstractEditComponent.access$600(this.this$0), access$700);
                    $jacocoInit[11] = true;
                    if (access$800.equals(charSequence.toString())) {
                        $jacocoInit[12] = true;
                    } else if (AbstractEditComponent.access$900(this.this$0) >= 3) {
                        $jacocoInit[13] = true;
                    } else {
                        $jacocoInit[14] = true;
                        AbstractEditComponent.access$902(this.this$0, AbstractEditComponent.access$900(this.this$0) + 1);
                        $jacocoInit[15] = true;
                        int selectionStart = wXEditText.getSelectionStart();
                        $jacocoInit[16] = true;
                        int length = TextFormatter.access$800(AbstractEditComponent.access$600(this.this$0), TextFormatter.access$700(AbstractEditComponent.access$600(this.this$0), charSequence.subSequence(0, selectionStart).toString())).length();
                        $jacocoInit[17] = true;
                        wXEditText.setText(access$800);
                        $jacocoInit[18] = true;
                        wXEditText.setSelection(length);
                        $jacocoInit[19] = true;
                        return;
                    }
                    AbstractEditComponent.access$902(this.this$0, 0);
                    $jacocoInit[20] = true;
                }
                if (AbstractEditComponent.access$500(this.this$0) == null) {
                    $jacocoInit[21] = true;
                } else {
                    $jacocoInit[22] = true;
                    $jacocoInit[23] = true;
                    for (TextWatcher onTextChanged : AbstractEditComponent.access$500(this.this$0)) {
                        $jacocoInit[25] = true;
                        onTextChanged.onTextChanged(charSequence, i, i2, i3);
                        $jacocoInit[26] = true;
                    }
                    $jacocoInit[24] = true;
                }
                $jacocoInit[27] = true;
            }

            public void afterTextChanged(Editable editable) {
                boolean[] $jacocoInit = $jacocoInit();
                if (AbstractEditComponent.access$500(this.this$0) == null) {
                    $jacocoInit[28] = true;
                } else {
                    $jacocoInit[29] = true;
                    $jacocoInit[30] = true;
                    for (TextWatcher afterTextChanged : AbstractEditComponent.access$500(this.this$0)) {
                        $jacocoInit[32] = true;
                        afterTextChanged.afterTextChanged(editable);
                        $jacocoInit[33] = true;
                    }
                    $jacocoInit[31] = true;
                }
                $jacocoInit[34] = true;
            }
        };
        $jacocoInit[58] = true;
        wXEditText.addTextChangedListener(this.mTextChangedEventDispatcher);
        $jacocoInit[59] = true;
        wXEditText.setTextSize(0, (float) WXStyle.getFontSize(getStyles(), getInstance().getInstanceViewPortWidth()));
        $jacocoInit[60] = true;
    }

    public void addEvent(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        super.addEvent(str);
        $jacocoInit[61] = true;
        if (getHostView() == null) {
            $jacocoInit[62] = true;
        } else if (TextUtils.isEmpty(str)) {
            $jacocoInit[63] = true;
        } else {
            final TextView textView = (TextView) getHostView();
            $jacocoInit[65] = true;
            if (str.equals(Constants.Event.CHANGE)) {
                $jacocoInit[66] = true;
                addFocusChangeListener(new WXComponent.OnFocusChangeListener(this) {
                    private static transient /* synthetic */ boolean[] $jacocoData;
                    final /* synthetic */ AbstractEditComponent this$0;

                    private static /* synthetic */ boolean[] $jacocoInit() {
                        boolean[] zArr = $jacocoData;
                        if (zArr != null) {
                            return zArr;
                        }
                        boolean[] a2 = Offline.a(5664714610342622680L, "com/taobao/weex/ui/component/AbstractEditComponent$5", 11);
                        $jacocoData = a2;
                        return a2;
                    }

                    {
                        boolean[] $jacocoInit = $jacocoInit();
                        this.this$0 = r2;
                        $jacocoInit[0] = true;
                    }

                    public void onFocusChange(boolean z) {
                        boolean[] $jacocoInit = $jacocoInit();
                        if (z) {
                            $jacocoInit[1] = true;
                            AbstractEditComponent.access$1002(this.this$0, textView.getText().toString());
                            $jacocoInit[2] = true;
                        } else {
                            CharSequence text = textView.getText();
                            if (text == null) {
                                text = "";
                                $jacocoInit[3] = true;
                            } else {
                                $jacocoInit[4] = true;
                            }
                            $jacocoInit[5] = true;
                            if (text.toString().equals(AbstractEditComponent.access$1000(this.this$0))) {
                                $jacocoInit[6] = true;
                            } else {
                                $jacocoInit[7] = true;
                                AbstractEditComponent.access$1100(this.this$0, Constants.Event.CHANGE, text.toString());
                                $jacocoInit[8] = true;
                                AbstractEditComponent.access$1002(this.this$0, textView.getText().toString());
                                $jacocoInit[9] = true;
                            }
                        }
                        $jacocoInit[10] = true;
                    }
                });
                $jacocoInit[67] = true;
                addEditorActionListener(new TextView.OnEditorActionListener(this) {
                    private static transient /* synthetic */ boolean[] $jacocoData;
                    final /* synthetic */ AbstractEditComponent this$0;

                    private static /* synthetic */ boolean[] $jacocoInit() {
                        boolean[] zArr = $jacocoData;
                        if (zArr != null) {
                            return zArr;
                        }
                        boolean[] a2 = Offline.a(2721504766762569419L, "com/taobao/weex/ui/component/AbstractEditComponent$6", 14);
                        $jacocoData = a2;
                        return a2;
                    }

                    {
                        boolean[] $jacocoInit = $jacocoInit();
                        this.this$0 = r2;
                        $jacocoInit[0] = true;
                    }

                    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                        boolean[] $jacocoInit = $jacocoInit();
                        if (i == AbstractEditComponent.access$1200(this.this$0)) {
                            $jacocoInit[1] = true;
                            CharSequence text = textView.getText();
                            if (text == null) {
                                text = "";
                                $jacocoInit[2] = true;
                            } else {
                                $jacocoInit[3] = true;
                            }
                            $jacocoInit[4] = true;
                            if (text.toString().equals(AbstractEditComponent.access$1000(this.this$0))) {
                                $jacocoInit[5] = true;
                            } else {
                                $jacocoInit[6] = true;
                                AbstractEditComponent.access$1100(this.this$0, Constants.Event.CHANGE, text.toString());
                                $jacocoInit[7] = true;
                                AbstractEditComponent.access$1002(this.this$0, textView.getText().toString());
                                $jacocoInit[8] = true;
                            }
                            if (this.this$0.getParent() == null) {
                                $jacocoInit[9] = true;
                            } else {
                                $jacocoInit[10] = true;
                                this.this$0.getParent().interceptFocus();
                                $jacocoInit[11] = true;
                            }
                            AbstractEditComponent.access$200(this.this$0);
                            $jacocoInit[12] = true;
                            return true;
                        }
                        $jacocoInit[13] = true;
                        return false;
                    }
                });
                $jacocoInit[68] = true;
            } else if (!str.equals("input")) {
                $jacocoInit[69] = true;
            } else {
                $jacocoInit[70] = true;
                addTextChangedListener(new TextWatcher(this) {
                    private static transient /* synthetic */ boolean[] $jacocoData;
                    final /* synthetic */ AbstractEditComponent this$0;

                    private static /* synthetic */ boolean[] $jacocoInit() {
                        boolean[] zArr = $jacocoData;
                        if (zArr != null) {
                            return zArr;
                        }
                        boolean[] a2 = Offline.a(5666378981391396882L, "com/taobao/weex/ui/component/AbstractEditComponent$7", 9);
                        $jacocoData = a2;
                        return a2;
                    }

                    {
                        boolean[] $jacocoInit = $jacocoInit();
                        this.this$0 = r3;
                        $jacocoInit[0] = true;
                    }

                    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                        $jacocoInit()[1] = true;
                    }

                    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                        boolean[] $jacocoInit = $jacocoInit();
                        if (AbstractEditComponent.access$1300(this.this$0)) {
                            $jacocoInit[2] = true;
                            AbstractEditComponent.access$1302(this.this$0, false);
                            $jacocoInit[3] = true;
                            AbstractEditComponent.access$1402(this.this$0, charSequence.toString());
                            $jacocoInit[4] = true;
                        } else if (AbstractEditComponent.access$1400(this.this$0).equals(charSequence.toString())) {
                            $jacocoInit[5] = true;
                        } else {
                            AbstractEditComponent.access$1402(this.this$0, charSequence.toString());
                            $jacocoInit[6] = true;
                            AbstractEditComponent.access$1100(this.this$0, "input", charSequence.toString());
                            $jacocoInit[7] = true;
                        }
                    }

                    public void afterTextChanged(Editable editable) {
                        $jacocoInit()[8] = true;
                    }
                });
                $jacocoInit[71] = true;
            }
            if (!"return".equals(str)) {
                $jacocoInit[72] = true;
            } else {
                $jacocoInit[73] = true;
                addEditorActionListener(new TextView.OnEditorActionListener(this) {
                    private static transient /* synthetic */ boolean[] $jacocoData;
                    final /* synthetic */ AbstractEditComponent this$0;

                    private static /* synthetic */ boolean[] $jacocoInit() {
                        boolean[] zArr = $jacocoData;
                        if (zArr != null) {
                            return zArr;
                        }
                        boolean[] a2 = Offline.a(5551554254552390429L, "com/taobao/weex/ui/component/AbstractEditComponent$8", 7);
                        $jacocoData = a2;
                        return a2;
                    }

                    {
                        boolean[] $jacocoInit = $jacocoInit();
                        this.this$0 = r3;
                        $jacocoInit[0] = true;
                    }

                    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                        boolean[] $jacocoInit = $jacocoInit();
                        if (i == AbstractEditComponent.access$1200(this.this$0)) {
                            $jacocoInit[1] = true;
                            HashMap hashMap = new HashMap(2);
                            $jacocoInit[2] = true;
                            hashMap.put(Constants.Name.RETURN_KEY_TYPE, AbstractEditComponent.access$1500(this.this$0));
                            $jacocoInit[3] = true;
                            hashMap.put("value", textView.getText().toString());
                            $jacocoInit[4] = true;
                            this.this$0.fireEvent("return", hashMap);
                            $jacocoInit[5] = true;
                            return true;
                        }
                        $jacocoInit[6] = true;
                        return false;
                    }
                });
                $jacocoInit[74] = true;
            }
            if (!Constants.Event.KEYBOARD.equals(str)) {
                $jacocoInit[75] = true;
            } else {
                this.mListeningKeyboard = true;
                $jacocoInit[76] = true;
            }
            $jacocoInit[77] = true;
            return;
        }
        $jacocoInit[64] = true;
    }

    private void fireEvent(String str, String str2) {
        boolean[] $jacocoInit = $jacocoInit();
        if (str == null) {
            $jacocoInit[78] = true;
        } else {
            $jacocoInit[79] = true;
            HashMap hashMap = new HashMap(2);
            $jacocoInit[80] = true;
            hashMap.put("value", str2);
            $jacocoInit[81] = true;
            hashMap.put(BaseService.TIME_STAMP, Long.valueOf(System.currentTimeMillis()));
            $jacocoInit[82] = true;
            HashMap hashMap2 = new HashMap();
            $jacocoInit[83] = true;
            HashMap hashMap3 = new HashMap();
            $jacocoInit[84] = true;
            hashMap3.put("value", str2);
            $jacocoInit[85] = true;
            hashMap2.put("attrs", hashMap3);
            $jacocoInit[86] = true;
            WXSDKManager.getInstance().fireEvent(getInstanceId(), getRef(), str, hashMap, hashMap2);
            $jacocoInit[87] = true;
        }
        $jacocoInit[88] = true;
    }

    public void performOnChange(String str) {
        String str2;
        boolean[] $jacocoInit = $jacocoInit();
        if (getEvents() == null) {
            $jacocoInit[89] = true;
        } else {
            $jacocoInit[90] = true;
            if (getEvents().contains(Constants.Event.CHANGE)) {
                str2 = Constants.Event.CHANGE;
                $jacocoInit[91] = true;
            } else {
                str2 = null;
                $jacocoInit[92] = true;
            }
            $jacocoInit[93] = true;
            fireEvent(str2, str);
            $jacocoInit[94] = true;
        }
        $jacocoInit[95] = true;
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
                case -1898657397: goto L_0x0162;
                case -1576785488: goto L_0x014f;
                case -1065511464: goto L_0x013c;
                case -791400086: goto L_0x0128;
                case 107876: goto L_0x0114;
                case 108114: goto L_0x00ff;
                case 3575610: goto L_0x00ea;
                case 94842723: goto L_0x00d5;
                case 102977279: goto L_0x00bf;
                case 124732746: goto L_0x00a9;
                case 270940796: goto L_0x0094;
                case 365601008: goto L_0x007f;
                case 598246771: goto L_0x006a;
                case 914346044: goto L_0x0054;
                case 947486441: goto L_0x003e;
                case 1625554645: goto L_0x0028;
                case 1667607689: goto L_0x0013;
                default: goto L_0x000d;
            }
        L_0x000d:
            r1 = 96
            r0[r1] = r3
            goto L_0x0176
        L_0x0013:
            java.lang.String r1 = "autofocus"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L_0x0021
            r1 = 105(0x69, float:1.47E-43)
            r0[r1] = r3
            goto L_0x0176
        L_0x0021:
            r1 = 4
            r4 = 106(0x6a, float:1.49E-43)
            r0[r4] = r3
            goto L_0x0177
        L_0x0028:
            java.lang.String r1 = "allowCopyPaste"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L_0x0036
            r1 = 129(0x81, float:1.81E-43)
            r0[r1] = r3
            goto L_0x0176
        L_0x0036:
            r1 = 16
            r4 = 130(0x82, float:1.82E-43)
            r0[r4] = r3
            goto L_0x0177
        L_0x003e:
            java.lang.String r1 = "returnKeyType"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L_0x004c
            r1 = 125(0x7d, float:1.75E-43)
            r0[r1] = r3
            goto L_0x0176
        L_0x004c:
            r1 = 14
            r4 = 126(0x7e, float:1.77E-43)
            r0[r4] = r3
            goto L_0x0177
        L_0x0054:
            java.lang.String r1 = "singleline"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L_0x0062
            r1 = 113(0x71, float:1.58E-43)
            r0[r1] = r3
            goto L_0x0176
        L_0x0062:
            r1 = 8
            r4 = 114(0x72, float:1.6E-43)
            r0[r4] = r3
            goto L_0x0177
        L_0x006a:
            java.lang.String r1 = "placeholder"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L_0x0078
            r1 = 99
            r0[r1] = r3
            goto L_0x0176
        L_0x0078:
            r1 = 100
            r0[r1] = r3
            r1 = 1
            goto L_0x0177
        L_0x007f:
            java.lang.String r1 = "fontSize"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L_0x008d
            r1 = 109(0x6d, float:1.53E-43)
            r0[r1] = r3
            goto L_0x0176
        L_0x008d:
            r1 = 6
            r4 = 110(0x6e, float:1.54E-43)
            r0[r4] = r3
            goto L_0x0177
        L_0x0094:
            java.lang.String r1 = "disabled"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L_0x00a2
            r1 = 97
            r0[r1] = r3
            goto L_0x0176
        L_0x00a2:
            r1 = 98
            r0[r1] = r3
            r1 = 0
            goto L_0x0177
        L_0x00a9:
            java.lang.String r1 = "maxlength"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L_0x00b7
            r1 = 119(0x77, float:1.67E-43)
            r0[r1] = r3
            goto L_0x0176
        L_0x00b7:
            r1 = 11
            r4 = 120(0x78, float:1.68E-43)
            r0[r4] = r3
            goto L_0x0177
        L_0x00bf:
            java.lang.String r1 = "lines"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L_0x00cd
            r1 = 115(0x73, float:1.61E-43)
            r0[r1] = r3
            goto L_0x0176
        L_0x00cd:
            r1 = 9
            r4 = 116(0x74, float:1.63E-43)
            r0[r4] = r3
            goto L_0x0177
        L_0x00d5:
            java.lang.String r1 = "color"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L_0x00e3
            r1 = 107(0x6b, float:1.5E-43)
            r0[r1] = r3
            goto L_0x0176
        L_0x00e3:
            r1 = 5
            r4 = 108(0x6c, float:1.51E-43)
            r0[r4] = r3
            goto L_0x0177
        L_0x00ea:
            java.lang.String r1 = "type"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L_0x00f8
            r1 = 103(0x67, float:1.44E-43)
            r0[r1] = r3
            goto L_0x0176
        L_0x00f8:
            r1 = 3
            r4 = 104(0x68, float:1.46E-43)
            r0[r4] = r3
            goto L_0x0177
        L_0x00ff:
            java.lang.String r1 = "min"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L_0x010d
            r1 = 123(0x7b, float:1.72E-43)
            r0[r1] = r3
            goto L_0x0176
        L_0x010d:
            r1 = 13
            r4 = 124(0x7c, float:1.74E-43)
            r0[r4] = r3
            goto L_0x0177
        L_0x0114:
            java.lang.String r1 = "max"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L_0x0121
            r1 = 121(0x79, float:1.7E-43)
            r0[r1] = r3
            goto L_0x0176
        L_0x0121:
            r1 = 12
            r4 = 122(0x7a, float:1.71E-43)
            r0[r4] = r3
            goto L_0x0177
        L_0x0128:
            java.lang.String r1 = "maxLength"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L_0x0135
            r1 = 117(0x75, float:1.64E-43)
            r0[r1] = r3
            goto L_0x0176
        L_0x0135:
            r1 = 10
            r4 = 118(0x76, float:1.65E-43)
            r0[r4] = r3
            goto L_0x0177
        L_0x013c:
            java.lang.String r1 = "textAlign"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L_0x0149
            r1 = 111(0x6f, float:1.56E-43)
            r0[r1] = r3
            goto L_0x0176
        L_0x0149:
            r1 = 7
            r4 = 112(0x70, float:1.57E-43)
            r0[r4] = r3
            goto L_0x0177
        L_0x014f:
            java.lang.String r1 = "placeholderColor"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L_0x015c
            r1 = 101(0x65, float:1.42E-43)
            r0[r1] = r3
            goto L_0x0176
        L_0x015c:
            r1 = 2
            r4 = 102(0x66, float:1.43E-43)
            r0[r4] = r3
            goto L_0x0177
        L_0x0162:
            java.lang.String r1 = "keepSelectionIndex"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L_0x016f
            r1 = 127(0x7f, float:1.78E-43)
            r0[r1] = r3
            goto L_0x0176
        L_0x016f:
            r1 = 15
            r4 = 128(0x80, float:1.794E-43)
            r0[r4] = r3
            goto L_0x0177
        L_0x0176:
            r1 = -1
        L_0x0177:
            r4 = 0
            switch(r1) {
                case 0: goto L_0x0329;
                case 1: goto L_0x030e;
                case 2: goto L_0x02f3;
                case 3: goto L_0x02d8;
                case 4: goto L_0x02b9;
                case 5: goto L_0x029e;
                case 6: goto L_0x0283;
                case 7: goto L_0x0268;
                case 8: goto L_0x0249;
                case 9: goto L_0x022a;
                case 10: goto L_0x020b;
                case 11: goto L_0x01ec;
                case 12: goto L_0x01e0;
                case 13: goto L_0x01d4;
                case 14: goto L_0x01c8;
                case 15: goto L_0x01b5;
                case 16: goto L_0x0184;
                default: goto L_0x017b;
            }
        L_0x017b:
            boolean r6 = super.setProperty(r6, r7)
            r7 = 193(0xc1, float:2.7E-43)
            r0[r7] = r3
            return r6
        L_0x0184:
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r3)
            java.lang.Boolean r6 = com.taobao.weex.utils.WXUtils.getBoolean(r7, r6)
            boolean r6 = r6.booleanValue()
            r7 = 188(0xbc, float:2.63E-43)
            r0[r7] = r3
            android.view.View r7 = r5.getHostView()
            if (r7 != 0) goto L_0x019f
            r6 = 189(0xbd, float:2.65E-43)
            r0[r6] = r3
            goto L_0x01b0
        L_0x019f:
            r7 = 190(0xbe, float:2.66E-43)
            r0[r7] = r3
            android.view.View r7 = r5.getHostView()
            com.taobao.weex.ui.view.WXEditText r7 = (com.taobao.weex.ui.view.WXEditText) r7
            r7.setAllowCopyPaste(r6)
            r6 = 191(0xbf, float:2.68E-43)
            r0[r6] = r3
        L_0x01b0:
            r6 = 192(0xc0, float:2.69E-43)
            r0[r6] = r3
            return r3
        L_0x01b5:
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r2)
            java.lang.Boolean r6 = com.taobao.weex.utils.WXUtils.getBoolean(r7, r6)
            boolean r6 = r6.booleanValue()
            r5.mKeepSelectionIndex = r6
            r6 = 187(0xbb, float:2.62E-43)
            r0[r6] = r3
            return r3
        L_0x01c8:
            java.lang.String r6 = java.lang.String.valueOf(r7)
            r5.setReturnKeyType(r6)
            r6 = 186(0xba, float:2.6E-43)
            r0[r6] = r3
            return r3
        L_0x01d4:
            java.lang.String r6 = java.lang.String.valueOf(r7)
            r5.setMin(r6)
            r6 = 185(0xb9, float:2.59E-43)
            r0[r6] = r3
            return r3
        L_0x01e0:
            java.lang.String r6 = java.lang.String.valueOf(r7)
            r5.setMax(r6)
            r6 = 184(0xb8, float:2.58E-43)
            r0[r6] = r3
            return r3
        L_0x01ec:
            java.lang.Integer r6 = com.taobao.weex.utils.WXUtils.getInteger(r7, r4)
            if (r6 != 0) goto L_0x01f7
            r6 = 180(0xb4, float:2.52E-43)
            r0[r6] = r3
            goto L_0x0206
        L_0x01f7:
            r7 = 181(0xb5, float:2.54E-43)
            r0[r7] = r3
            int r6 = r6.intValue()
            r5.setMaxLength(r6)
            r6 = 182(0xb6, float:2.55E-43)
            r0[r6] = r3
        L_0x0206:
            r6 = 183(0xb7, float:2.56E-43)
            r0[r6] = r3
            return r3
        L_0x020b:
            java.lang.Integer r6 = com.taobao.weex.utils.WXUtils.getInteger(r7, r4)
            if (r6 != 0) goto L_0x0216
            r6 = 176(0xb0, float:2.47E-43)
            r0[r6] = r3
            goto L_0x0225
        L_0x0216:
            r7 = 177(0xb1, float:2.48E-43)
            r0[r7] = r3
            int r6 = r6.intValue()
            r5.setMaxLength(r6)
            r6 = 178(0xb2, float:2.5E-43)
            r0[r6] = r3
        L_0x0225:
            r6 = 179(0xb3, float:2.51E-43)
            r0[r6] = r3
            return r3
        L_0x022a:
            java.lang.Integer r6 = com.taobao.weex.utils.WXUtils.getInteger(r7, r4)
            if (r6 != 0) goto L_0x0235
            r6 = 172(0xac, float:2.41E-43)
            r0[r6] = r3
            goto L_0x0244
        L_0x0235:
            r7 = 173(0xad, float:2.42E-43)
            r0[r7] = r3
            int r6 = r6.intValue()
            r5.setLines(r6)
            r6 = 174(0xae, float:2.44E-43)
            r0[r6] = r3
        L_0x0244:
            r6 = 175(0xaf, float:2.45E-43)
            r0[r6] = r3
            return r3
        L_0x0249:
            java.lang.Boolean r6 = com.taobao.weex.utils.WXUtils.getBoolean(r7, r4)
            if (r6 != 0) goto L_0x0254
            r6 = 168(0xa8, float:2.35E-43)
            r0[r6] = r3
            goto L_0x0263
        L_0x0254:
            r7 = 169(0xa9, float:2.37E-43)
            r0[r7] = r3
            boolean r6 = r6.booleanValue()
            r5.setSingleLine(r6)
            r6 = 170(0xaa, float:2.38E-43)
            r0[r6] = r3
        L_0x0263:
            r6 = 171(0xab, float:2.4E-43)
            r0[r6] = r3
            return r3
        L_0x0268:
            java.lang.String r6 = com.taobao.weex.utils.WXUtils.getString(r7, r4)
            if (r6 != 0) goto L_0x0273
            r6 = 164(0xa4, float:2.3E-43)
            r0[r6] = r3
            goto L_0x027e
        L_0x0273:
            r7 = 165(0xa5, float:2.31E-43)
            r0[r7] = r3
            r5.setTextAlign(r6)
            r6 = 166(0xa6, float:2.33E-43)
            r0[r6] = r3
        L_0x027e:
            r6 = 167(0xa7, float:2.34E-43)
            r0[r6] = r3
            return r3
        L_0x0283:
            java.lang.String r6 = com.taobao.weex.utils.WXUtils.getString(r7, r4)
            if (r6 != 0) goto L_0x028e
            r6 = 160(0xa0, float:2.24E-43)
            r0[r6] = r3
            goto L_0x0299
        L_0x028e:
            r7 = 161(0xa1, float:2.26E-43)
            r0[r7] = r3
            r5.setFontSize(r6)
            r6 = 162(0xa2, float:2.27E-43)
            r0[r6] = r3
        L_0x0299:
            r6 = 163(0xa3, float:2.28E-43)
            r0[r6] = r3
            return r3
        L_0x029e:
            java.lang.String r6 = com.taobao.weex.utils.WXUtils.getString(r7, r4)
            if (r6 != 0) goto L_0x02a9
            r6 = 156(0x9c, float:2.19E-43)
            r0[r6] = r3
            goto L_0x02b4
        L_0x02a9:
            r7 = 157(0x9d, float:2.2E-43)
            r0[r7] = r3
            r5.setColor(r6)
            r6 = 158(0x9e, float:2.21E-43)
            r0[r6] = r3
        L_0x02b4:
            r6 = 159(0x9f, float:2.23E-43)
            r0[r6] = r3
            return r3
        L_0x02b9:
            java.lang.Boolean r6 = com.taobao.weex.utils.WXUtils.getBoolean(r7, r4)
            if (r6 != 0) goto L_0x02c4
            r6 = 152(0x98, float:2.13E-43)
            r0[r6] = r3
            goto L_0x02d3
        L_0x02c4:
            r7 = 153(0x99, float:2.14E-43)
            r0[r7] = r3
            boolean r6 = r6.booleanValue()
            r5.setAutofocus(r6)
            r6 = 154(0x9a, float:2.16E-43)
            r0[r6] = r3
        L_0x02d3:
            r6 = 155(0x9b, float:2.17E-43)
            r0[r6] = r3
            return r3
        L_0x02d8:
            java.lang.String r6 = com.taobao.weex.utils.WXUtils.getString(r7, r4)
            if (r6 != 0) goto L_0x02e3
            r6 = 148(0x94, float:2.07E-43)
            r0[r6] = r3
            goto L_0x02ee
        L_0x02e3:
            r7 = 149(0x95, float:2.09E-43)
            r0[r7] = r3
            r5.setType(r6)
            r6 = 150(0x96, float:2.1E-43)
            r0[r6] = r3
        L_0x02ee:
            r6 = 151(0x97, float:2.12E-43)
            r0[r6] = r3
            return r3
        L_0x02f3:
            java.lang.String r6 = com.taobao.weex.utils.WXUtils.getString(r7, r4)
            if (r6 != 0) goto L_0x02fe
            r6 = 144(0x90, float:2.02E-43)
            r0[r6] = r3
            goto L_0x0309
        L_0x02fe:
            r7 = 145(0x91, float:2.03E-43)
            r0[r7] = r3
            r5.setPlaceholderColor(r6)
            r6 = 146(0x92, float:2.05E-43)
            r0[r6] = r3
        L_0x0309:
            r6 = 147(0x93, float:2.06E-43)
            r0[r6] = r3
            return r3
        L_0x030e:
            java.lang.String r6 = com.taobao.weex.utils.WXUtils.getString(r7, r4)
            if (r6 != 0) goto L_0x0319
            r6 = 140(0x8c, float:1.96E-43)
            r0[r6] = r3
            goto L_0x0324
        L_0x0319:
            r7 = 141(0x8d, float:1.98E-43)
            r0[r7] = r3
            r5.setPlaceholder(r6)
            r6 = 142(0x8e, float:1.99E-43)
            r0[r6] = r3
        L_0x0324:
            r6 = 143(0x8f, float:2.0E-43)
            r0[r6] = r3
            return r3
        L_0x0329:
            java.lang.Boolean r6 = com.taobao.weex.utils.WXUtils.getBoolean(r7, r4)
            if (r6 != 0) goto L_0x0334
            r6 = 131(0x83, float:1.84E-43)
            r0[r6] = r3
            goto L_0x0378
        L_0x0334:
            android.view.View r7 = r5.mHost
            if (r7 != 0) goto L_0x033d
            r6 = 132(0x84, float:1.85E-43)
            r0[r6] = r3
            goto L_0x0378
        L_0x033d:
            r7 = 133(0x85, float:1.86E-43)
            r0[r7] = r3
            boolean r6 = r6.booleanValue()
            if (r6 == 0) goto L_0x0362
            r6 = 134(0x86, float:1.88E-43)
            r0[r6] = r3
            android.view.View r6 = r5.mHost
            com.taobao.weex.ui.view.WXEditText r6 = (com.taobao.weex.ui.view.WXEditText) r6
            r6.setFocusable(r2)
            r6 = 135(0x87, float:1.89E-43)
            r0[r6] = r3
            android.view.View r6 = r5.mHost
            com.taobao.weex.ui.view.WXEditText r6 = (com.taobao.weex.ui.view.WXEditText) r6
            r6.setFocusableInTouchMode(r2)
            r6 = 136(0x88, float:1.9E-43)
            r0[r6] = r3
            goto L_0x0378
        L_0x0362:
            android.view.View r6 = r5.mHost
            com.taobao.weex.ui.view.WXEditText r6 = (com.taobao.weex.ui.view.WXEditText) r6
            r6.setFocusableInTouchMode(r3)
            r6 = 137(0x89, float:1.92E-43)
            r0[r6] = r3
            android.view.View r6 = r5.mHost
            com.taobao.weex.ui.view.WXEditText r6 = (com.taobao.weex.ui.view.WXEditText) r6
            r6.setFocusable(r3)
            r6 = 138(0x8a, float:1.93E-43)
            r0[r6] = r3
        L_0x0378:
            r6 = 139(0x8b, float:1.95E-43)
            r0[r6] = r3
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.component.AbstractEditComponent.setProperty(java.lang.String, java.lang.Object):boolean");
    }

    @WXComponentProp(name = "returnKeyType")
    public void setReturnKeyType(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (getHostView() == null) {
            $jacocoInit[194] = true;
            return;
        }
        this.mReturnKeyType = str;
        $jacocoInit[195] = true;
        char c = 65535;
        switch (str.hashCode()) {
            case -906336856:
                if (str.equals("search")) {
                    $jacocoInit[204] = true;
                    c = 3;
                    break;
                } else {
                    $jacocoInit[203] = true;
                    break;
                }
            case 3304:
                if (str.equals(ReturnTypes.GO)) {
                    $jacocoInit[200] = true;
                    c = 1;
                    break;
                } else {
                    $jacocoInit[199] = true;
                    break;
                }
            case 3089282:
                if (str.equals(ReturnTypes.DONE)) {
                    $jacocoInit[208] = true;
                    c = 5;
                    break;
                } else {
                    $jacocoInit[207] = true;
                    break;
                }
            case 3377907:
                if (str.equals("next")) {
                    $jacocoInit[202] = true;
                    c = 2;
                    break;
                } else {
                    $jacocoInit[201] = true;
                    break;
                }
            case 3526536:
                if (str.equals(ReturnTypes.SEND)) {
                    $jacocoInit[206] = true;
                    c = 4;
                    break;
                } else {
                    $jacocoInit[205] = true;
                    break;
                }
            case 1544803905:
                if (str.equals("default")) {
                    $jacocoInit[198] = true;
                    c = 0;
                    break;
                } else {
                    $jacocoInit[197] = true;
                    break;
                }
            default:
                $jacocoInit[196] = true;
                break;
        }
        switch (c) {
            case 0:
                this.mEditorAction = 0;
                $jacocoInit[210] = true;
                break;
            case 1:
                this.mEditorAction = 2;
                $jacocoInit[211] = true;
                break;
            case 2:
                this.mEditorAction = 5;
                $jacocoInit[212] = true;
                break;
            case 3:
                this.mEditorAction = 3;
                $jacocoInit[213] = true;
                break;
            case 4:
                this.mEditorAction = 4;
                $jacocoInit[214] = true;
                break;
            case 5:
                this.mEditorAction = 6;
                $jacocoInit[215] = true;
                break;
            default:
                $jacocoInit[209] = true;
                break;
        }
        blur();
        $jacocoInit[216] = true;
        ((WXEditText) getHostView()).setImeOptions(this.mEditorAction);
        $jacocoInit[217] = true;
    }

    @WXComponentProp(name = "placeholder")
    public void setPlaceholder(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (str == null) {
            $jacocoInit[218] = true;
        } else if (getHostView() == null) {
            $jacocoInit[219] = true;
        } else {
            ((WXEditText) getHostView()).setHint(str);
            $jacocoInit[221] = true;
            return;
        }
        $jacocoInit[220] = true;
    }

    @WXComponentProp(name = "placeholderColor")
    public void setPlaceholderColor(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (getHostView() == null) {
            $jacocoInit[222] = true;
        } else if (TextUtils.isEmpty(str)) {
            $jacocoInit[223] = true;
        } else {
            $jacocoInit[224] = true;
            int color = WXResourceUtils.getColor(str);
            if (color == Integer.MIN_VALUE) {
                $jacocoInit[225] = true;
            } else {
                $jacocoInit[226] = true;
                ((WXEditText) getHostView()).setHintTextColor(color);
                $jacocoInit[227] = true;
            }
        }
        $jacocoInit[228] = true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0092  */
    @com.taobao.weex.ui.component.WXComponentProp(name = "type")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setType(java.lang.String r6) {
        /*
            r5 = this;
            boolean[] r0 = $jacocoInit()
            java.lang.String r1 = "weex"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "setType="
            r2.append(r3)
            r2.append(r6)
            java.lang.String r2 = r2.toString()
            android.util.Log.e(r1, r2)
            r1 = 1
            r2 = 229(0xe5, float:3.21E-43)
            r0[r2] = r1
            if (r6 != 0) goto L_0x0026
            r6 = 230(0xe6, float:3.22E-43)
            r0[r6] = r1
            goto L_0x0030
        L_0x0026:
            android.view.View r2 = r5.getHostView()
            if (r2 != 0) goto L_0x0035
            r6 = 231(0xe7, float:3.24E-43)
            r0[r6] = r1
        L_0x0030:
            r6 = 232(0xe8, float:3.25E-43)
            r0[r6] = r1
            return
        L_0x0035:
            r5.mType = r6
            r6 = 233(0xe9, float:3.27E-43)
            r0[r6] = r1
            android.view.View r6 = r5.getHostView()
            android.widget.EditText r6 = (android.widget.EditText) r6
            java.lang.String r2 = r5.mType
            int r2 = r5.getInputType(r2)
            r6.setInputType(r2)
            r6 = 234(0xea, float:3.28E-43)
            r0[r6] = r1
            java.lang.String r6 = r5.mType
            r2 = -1
            int r3 = r6.hashCode()
            r4 = 3076014(0x2eefae, float:4.310414E-39)
            if (r3 == r4) goto L_0x0077
            r4 = 3560141(0x3652cd, float:4.98882E-39)
            if (r3 == r4) goto L_0x0064
            r6 = 235(0xeb, float:3.3E-43)
            r0[r6] = r1
            goto L_0x0083
        L_0x0064:
            java.lang.String r3 = "time"
            boolean r6 = r6.equals(r3)
            if (r6 != 0) goto L_0x0071
            r6 = 238(0xee, float:3.34E-43)
            r0[r6] = r1
            goto L_0x0083
        L_0x0071:
            r6 = 239(0xef, float:3.35E-43)
            r0[r6] = r1
            r6 = 1
            goto L_0x008a
        L_0x0077:
            java.lang.String r3 = "date"
            boolean r6 = r6.equals(r3)
            if (r6 != 0) goto L_0x0085
            r6 = 236(0xec, float:3.31E-43)
            r0[r6] = r1
        L_0x0083:
            r6 = -1
            goto L_0x008a
        L_0x0085:
            r6 = 0
            r2 = 237(0xed, float:3.32E-43)
            r0[r2] = r1
        L_0x008a:
            switch(r6) {
                case 0: goto L_0x0092;
                case 1: goto L_0x0092;
                default: goto L_0x008d;
            }
        L_0x008d:
            r6 = 240(0xf0, float:3.36E-43)
            r0[r6] = r1
            goto L_0x0099
        L_0x0092:
            r5.applyOnClickListener()
            r6 = 241(0xf1, float:3.38E-43)
            r0[r6] = r1
        L_0x0099:
            r6 = 242(0xf2, float:3.39E-43)
            r0[r6] = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.component.AbstractEditComponent.setType(java.lang.String):void");
    }

    @WXComponentProp(name = "autofocus")
    public void setAutofocus(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        if (getHostView() == null) {
            $jacocoInit[243] = true;
            return;
        }
        this.mAutoFocus = z;
        $jacocoInit[244] = true;
        EditText editText = (EditText) getHostView();
        if (this.mAutoFocus) {
            $jacocoInit[245] = true;
            editText.setFocusable(true);
            $jacocoInit[246] = true;
            editText.requestFocus();
            $jacocoInit[247] = true;
            editText.setFocusableInTouchMode(true);
            $jacocoInit[248] = true;
            showSoftKeyboard();
            $jacocoInit[249] = true;
        } else {
            hideSoftKeyboard();
            $jacocoInit[250] = true;
        }
        $jacocoInit[251] = true;
    }

    @WXComponentProp(name = "value")
    public void setValue(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        WXEditText wXEditText = (WXEditText) getHostView();
        if (wXEditText == null) {
            $jacocoInit[252] = true;
            return;
        }
        this.mIgnoreNextOnInputEvent = true;
        $jacocoInit[253] = true;
        int selectionStart = wXEditText.getSelectionStart();
        $jacocoInit[254] = true;
        wXEditText.setText(str);
        $jacocoInit[255] = true;
        if (this.mKeepSelectionIndex) {
            $jacocoInit[256] = true;
        } else {
            selectionStart = str.length();
            $jacocoInit[257] = true;
        }
        $jacocoInit[258] = true;
        if (str == null) {
            selectionStart = 0;
            $jacocoInit[259] = true;
        } else {
            $jacocoInit[260] = true;
        }
        wXEditText.setSelection(selectionStart);
        $jacocoInit[261] = true;
    }

    @WXComponentProp(name = "color")
    public void setColor(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (getHostView() == null) {
            $jacocoInit[262] = true;
        } else if (TextUtils.isEmpty(str)) {
            $jacocoInit[263] = true;
        } else {
            $jacocoInit[264] = true;
            int color = WXResourceUtils.getColor(str);
            if (color == Integer.MIN_VALUE) {
                $jacocoInit[265] = true;
            } else {
                $jacocoInit[266] = true;
                ((WXEditText) getHostView()).setTextColor(color);
                $jacocoInit[267] = true;
            }
        }
        $jacocoInit[268] = true;
    }

    @WXComponentProp(name = "fontSize")
    public void setFontSize(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (getHostView() == null) {
            $jacocoInit[269] = true;
        } else if (str == null) {
            $jacocoInit[270] = true;
        } else {
            $jacocoInit[271] = true;
            HashMap hashMap = new HashMap(1);
            $jacocoInit[272] = true;
            hashMap.put("fontSize", str);
            $jacocoInit[273] = true;
            ((WXEditText) getHostView()).setTextSize(0, (float) WXStyle.getFontSize(hashMap, getInstance().getInstanceViewPortWidth()));
            $jacocoInit[274] = true;
        }
        $jacocoInit[275] = true;
    }

    @WXComponentProp(name = "textAlign")
    public void setTextAlign(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        int textAlign = getTextAlign(str);
        if (textAlign <= 0) {
            $jacocoInit[276] = true;
        } else {
            $jacocoInit[277] = true;
            ((WXEditText) getHostView()).setGravity(textAlign | getVerticalGravity());
            $jacocoInit[278] = true;
        }
        $jacocoInit[279] = true;
    }

    @WXComponentProp(name = "singleline")
    public void setSingleLine(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        if (getHostView() == null) {
            $jacocoInit[280] = true;
            return;
        }
        ((WXEditText) getHostView()).setSingleLine(z);
        $jacocoInit[281] = true;
    }

    @WXComponentProp(name = "lines")
    public void setLines(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        if (getHostView() == null) {
            $jacocoInit[282] = true;
            return;
        }
        ((WXEditText) getHostView()).setLines(i);
        $jacocoInit[283] = true;
    }

    @WXComponentProp(name = "maxLength")
    public void setMaxLength(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        if (getHostView() == null) {
            $jacocoInit[284] = true;
            return;
        }
        ((WXEditText) getHostView()).setFilters(new InputFilter[]{new InputFilter.LengthFilter(i)});
        $jacocoInit[285] = true;
    }

    @WXComponentProp(name = "maxlength")
    @Deprecated
    public void setMaxlength(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        setMaxLength(i);
        $jacocoInit[286] = true;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00d0, code lost:
        r2 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x014e, code lost:
        r2 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0156, code lost:
        r0[323(0x143, float:4.53E-43)] = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x015a, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int getInputType(java.lang.String r7) {
        /*
            r6 = this;
            boolean[] r0 = $jacocoInit()
            int r1 = r7.hashCode()
            r2 = 4
            r3 = 3
            r4 = 0
            r5 = 1
            switch(r1) {
                case -1034364087: goto L_0x00b4;
                case 114715: goto L_0x00a1;
                case 116079: goto L_0x008e;
                case 3076014: goto L_0x007b;
                case 3556653: goto L_0x0068;
                case 3560141: goto L_0x0054;
                case 96619420: goto L_0x003f;
                case 1216985755: goto L_0x002a;
                case 1793702779: goto L_0x0015;
                default: goto L_0x000f;
            }
        L_0x000f:
            r7 = 287(0x11f, float:4.02E-43)
            r0[r7] = r5
            goto L_0x00c8
        L_0x0015:
            java.lang.String r1 = "datetime"
            boolean r7 = r7.equals(r1)
            if (r7 != 0) goto L_0x0023
            r7 = 292(0x124, float:4.09E-43)
            r0[r7] = r5
            goto L_0x00c8
        L_0x0023:
            r7 = 2
            r1 = 293(0x125, float:4.1E-43)
            r0[r1] = r5
            goto L_0x00c9
        L_0x002a:
            java.lang.String r1 = "password"
            boolean r7 = r7.equals(r1)
            if (r7 != 0) goto L_0x0038
            r7 = 296(0x128, float:4.15E-43)
            r0[r7] = r5
            goto L_0x00c8
        L_0x0038:
            r7 = 297(0x129, float:4.16E-43)
            r0[r7] = r5
            r7 = 4
            goto L_0x00c9
        L_0x003f:
            java.lang.String r1 = "email"
            boolean r7 = r7.equals(r1)
            if (r7 != 0) goto L_0x004d
            r7 = 294(0x126, float:4.12E-43)
            r0[r7] = r5
            goto L_0x00c8
        L_0x004d:
            r7 = 295(0x127, float:4.13E-43)
            r0[r7] = r5
            r7 = 3
            goto L_0x00c9
        L_0x0054:
            java.lang.String r1 = "time"
            boolean r7 = r7.equals(r1)
            if (r7 != 0) goto L_0x0062
            r7 = 300(0x12c, float:4.2E-43)
            r0[r7] = r5
            goto L_0x00c8
        L_0x0062:
            r7 = 6
            r1 = 301(0x12d, float:4.22E-43)
            r0[r1] = r5
            goto L_0x00c9
        L_0x0068:
            java.lang.String r1 = "text"
            boolean r7 = r7.equals(r1)
            if (r7 != 0) goto L_0x0075
            r7 = 288(0x120, float:4.04E-43)
            r0[r7] = r5
            goto L_0x00c8
        L_0x0075:
            r7 = 289(0x121, float:4.05E-43)
            r0[r7] = r5
            r7 = 0
            goto L_0x00c9
        L_0x007b:
            java.lang.String r1 = "date"
            boolean r7 = r7.equals(r1)
            if (r7 != 0) goto L_0x0088
            r7 = 290(0x122, float:4.06E-43)
            r0[r7] = r5
            goto L_0x00c8
        L_0x0088:
            r7 = 291(0x123, float:4.08E-43)
            r0[r7] = r5
            r7 = 1
            goto L_0x00c9
        L_0x008e:
            java.lang.String r1 = "url"
            boolean r7 = r7.equals(r1)
            if (r7 != 0) goto L_0x009b
            r7 = 302(0x12e, float:4.23E-43)
            r0[r7] = r5
            goto L_0x00c8
        L_0x009b:
            r7 = 7
            r1 = 303(0x12f, float:4.25E-43)
            r0[r1] = r5
            goto L_0x00c9
        L_0x00a1:
            java.lang.String r1 = "tel"
            boolean r7 = r7.equals(r1)
            if (r7 != 0) goto L_0x00ae
            r7 = 298(0x12a, float:4.18E-43)
            r0[r7] = r5
            goto L_0x00c8
        L_0x00ae:
            r7 = 5
            r1 = 299(0x12b, float:4.19E-43)
            r0[r1] = r5
            goto L_0x00c9
        L_0x00b4:
            java.lang.String r1 = "number"
            boolean r7 = r7.equals(r1)
            if (r7 != 0) goto L_0x00c1
            r7 = 304(0x130, float:4.26E-43)
            r0[r7] = r5
            goto L_0x00c8
        L_0x00c1:
            r7 = 8
            r1 = 305(0x131, float:4.27E-43)
            r0[r1] = r5
            goto L_0x00c9
        L_0x00c8:
            r7 = -1
        L_0x00c9:
            switch(r7) {
                case 0: goto L_0x0150;
                case 1: goto L_0x013d;
                case 2: goto L_0x0138;
                case 3: goto L_0x0131;
                case 4: goto L_0x010a;
                case 5: goto L_0x0104;
                case 6: goto L_0x00e3;
                case 7: goto L_0x00db;
                case 8: goto L_0x00d3;
                default: goto L_0x00cc;
            }
        L_0x00cc:
            r7 = 322(0x142, float:4.51E-43)
            r0[r7] = r5
        L_0x00d0:
            r2 = 1
            goto L_0x0156
        L_0x00d3:
            r2 = 8194(0x2002, float:1.1482E-41)
            r7 = 321(0x141, float:4.5E-43)
            r0[r7] = r5
            goto L_0x0156
        L_0x00db:
            r2 = 17
            r7 = 320(0x140, float:4.48E-43)
            r0[r7] = r5
            goto L_0x0156
        L_0x00e3:
            r7 = 316(0x13c, float:4.43E-43)
            r0[r7] = r5
            android.view.View r7 = r6.getHostView()
            if (r7 != 0) goto L_0x00f2
            r7 = 317(0x13d, float:4.44E-43)
            r0[r7] = r5
            goto L_0x014e
        L_0x00f2:
            r7 = 318(0x13e, float:4.46E-43)
            r0[r7] = r5
            android.view.View r7 = r6.getHostView()
            com.taobao.weex.ui.view.WXEditText r7 = (com.taobao.weex.ui.view.WXEditText) r7
            r7.setFocusable(r4)
            r7 = 319(0x13f, float:4.47E-43)
            r0[r7] = r5
            goto L_0x014e
        L_0x0104:
            r7 = 315(0x13b, float:4.41E-43)
            r0[r7] = r5
            r2 = 3
            goto L_0x0156
        L_0x010a:
            r2 = 129(0x81, float:1.81E-43)
            r7 = 311(0x137, float:4.36E-43)
            r0[r7] = r5
            android.view.View r7 = r6.getHostView()
            if (r7 != 0) goto L_0x011b
            r7 = 312(0x138, float:4.37E-43)
            r0[r7] = r5
            goto L_0x0156
        L_0x011b:
            r7 = 313(0x139, float:4.39E-43)
            r0[r7] = r5
            android.view.View r7 = r6.getHostView()
            com.taobao.weex.ui.view.WXEditText r7 = (com.taobao.weex.ui.view.WXEditText) r7
            android.text.method.PasswordTransformationMethod r1 = android.text.method.PasswordTransformationMethod.getInstance()
            r7.setTransformationMethod(r1)
            r7 = 314(0x13a, float:4.4E-43)
            r0[r7] = r5
            goto L_0x0156
        L_0x0131:
            r2 = 33
            r7 = 310(0x136, float:4.34E-43)
            r0[r7] = r5
            goto L_0x0156
        L_0x0138:
            r7 = 309(0x135, float:4.33E-43)
            r0[r7] = r5
            goto L_0x0156
        L_0x013d:
            r7 = 307(0x133, float:4.3E-43)
            r0[r7] = r5
            android.view.View r7 = r6.getHostView()
            com.taobao.weex.ui.view.WXEditText r7 = (com.taobao.weex.ui.view.WXEditText) r7
            r7.setFocusable(r4)
            r7 = 308(0x134, float:4.32E-43)
            r0[r7] = r5
        L_0x014e:
            r2 = 0
            goto L_0x0156
        L_0x0150:
            r7 = 306(0x132, float:4.29E-43)
            r0[r7] = r5
            goto L_0x00d0
        L_0x0156:
            r7 = 323(0x143, float:4.53E-43)
            r0[r7] = r5
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.component.AbstractEditComponent.getInputType(java.lang.String):int");
    }

    @WXComponentProp(name = "max")
    public void setMax(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mMax = str;
        $jacocoInit[324] = true;
    }

    @WXComponentProp(name = "min")
    public void setMin(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mMin = str;
        $jacocoInit[325] = true;
    }

    private boolean showSoftKeyboard() {
        boolean[] $jacocoInit = $jacocoInit();
        if (getHostView() == null) {
            $jacocoInit[326] = true;
            return false;
        }
        ((WXEditText) getHostView()).postDelayed(WXThread.secure((Runnable) new Runnable(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ AbstractEditComponent this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(-2173043644322524423L, "com/taobao/weex/ui/component/AbstractEditComponent$9", 2);
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
                AbstractEditComponent.access$1600(this.this$0).showSoftInput(this.this$0.getHostView(), 1);
                $jacocoInit[1] = true;
            }
        }), 100);
        $jacocoInit[327] = true;
        return true;
    }

    private void hideSoftKeyboard() {
        boolean[] $jacocoInit = $jacocoInit();
        if (getHostView() == null) {
            $jacocoInit[328] = true;
        } else {
            $jacocoInit[329] = true;
            ((WXEditText) getHostView()).postDelayed(WXThread.secure((Runnable) new Runnable(this) {
                private static transient /* synthetic */ boolean[] $jacocoData;
                final /* synthetic */ AbstractEditComponent this$0;

                private static /* synthetic */ boolean[] $jacocoInit() {
                    boolean[] zArr = $jacocoData;
                    if (zArr != null) {
                        return zArr;
                    }
                    boolean[] a2 = Offline.a(3222859566780825541L, "com/taobao/weex/ui/component/AbstractEditComponent$10", 2);
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
                    AbstractEditComponent.access$1600(this.this$0).hideSoftInputFromWindow(((WXEditText) this.this$0.getHostView()).getWindowToken(), 0);
                    $jacocoInit[1] = true;
                }
            }), 16);
            $jacocoInit[330] = true;
        }
        $jacocoInit[331] = true;
    }

    private int getTextAlign(String str) {
        int i;
        boolean[] $jacocoInit = $jacocoInit();
        boolean isLayoutRTL = isLayoutRTL();
        int i2 = GravityCompat.START;
        if (isLayoutRTL) {
            $jacocoInit[332] = true;
            i = GravityCompat.END;
        } else {
            $jacocoInit[333] = true;
            i = GravityCompat.START;
        }
        $jacocoInit[334] = true;
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[335] = true;
            return i;
        }
        if (str.equals("left")) {
            $jacocoInit[336] = true;
        } else if (str.equals("center")) {
            i2 = 17;
            $jacocoInit[337] = true;
        } else if (!str.equals("right")) {
            $jacocoInit[338] = true;
            i2 = i;
        } else {
            $jacocoInit[339] = true;
            i2 = GravityCompat.END;
        }
        $jacocoInit[340] = true;
        return i2;
    }

    @JSMethod
    public void blur() {
        boolean[] $jacocoInit = $jacocoInit();
        WXEditText wXEditText = (WXEditText) getHostView();
        $jacocoInit[341] = true;
        if (wXEditText == null) {
            $jacocoInit[342] = true;
        } else if (!wXEditText.hasFocus()) {
            $jacocoInit[343] = true;
        } else {
            $jacocoInit[344] = true;
            if (getParent() == null) {
                $jacocoInit[345] = true;
            } else {
                $jacocoInit[346] = true;
                getParent().interceptFocus();
                $jacocoInit[347] = true;
            }
            wXEditText.clearFocus();
            $jacocoInit[348] = true;
            hideSoftKeyboard();
            $jacocoInit[349] = true;
        }
        $jacocoInit[350] = true;
    }

    @JSMethod
    public void focus() {
        boolean[] $jacocoInit = $jacocoInit();
        WXEditText wXEditText = (WXEditText) getHostView();
        $jacocoInit[351] = true;
        if (wXEditText == null) {
            $jacocoInit[352] = true;
        } else if (wXEditText.hasFocus()) {
            $jacocoInit[353] = true;
        } else {
            $jacocoInit[354] = true;
            if (getParent() == null) {
                $jacocoInit[355] = true;
            } else {
                $jacocoInit[356] = true;
                getParent().ignoreFocus();
                $jacocoInit[357] = true;
            }
            wXEditText.requestFocus();
            $jacocoInit[358] = true;
            wXEditText.setFocusable(true);
            $jacocoInit[359] = true;
            wXEditText.setFocusableInTouchMode(true);
            $jacocoInit[360] = true;
            showSoftKeyboard();
            $jacocoInit[361] = true;
        }
        $jacocoInit[362] = true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x004a  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0051  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object convertEmptyProperty(java.lang.String r5, java.lang.Object r6) {
        /*
            r4 = this;
            boolean[] r0 = $jacocoInit()
            int r1 = r5.hashCode()
            r2 = 94842723(0x5a72f63, float:1.5722012E-35)
            r3 = 1
            if (r1 == r2) goto L_0x002b
            r2 = 365601008(0x15caa0f0, float:8.1841065E-26)
            if (r1 == r2) goto L_0x0018
            r1 = 363(0x16b, float:5.09E-43)
            r0[r1] = r3
            goto L_0x0037
        L_0x0018:
            java.lang.String r1 = "fontSize"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L_0x0025
            r1 = 364(0x16c, float:5.1E-43)
            r0[r1] = r3
            goto L_0x0037
        L_0x0025:
            r1 = 0
            r2 = 365(0x16d, float:5.11E-43)
            r0[r2] = r3
            goto L_0x003e
        L_0x002b:
            java.lang.String r1 = "color"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L_0x0039
            r1 = 366(0x16e, float:5.13E-43)
            r0[r1] = r3
        L_0x0037:
            r1 = -1
            goto L_0x003e
        L_0x0039:
            r1 = 367(0x16f, float:5.14E-43)
            r0[r1] = r3
            r1 = 1
        L_0x003e:
            switch(r1) {
                case 0: goto L_0x0051;
                case 1: goto L_0x004a;
                default: goto L_0x0041;
            }
        L_0x0041:
            java.lang.Object r5 = super.convertEmptyProperty(r5, r6)
            r6 = 370(0x172, float:5.18E-43)
            r0[r6] = r3
            return r5
        L_0x004a:
            java.lang.String r5 = "black"
            r6 = 369(0x171, float:5.17E-43)
            r0[r6] = r3
            return r5
        L_0x0051:
            r5 = 32
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r6 = 368(0x170, float:5.16E-43)
            r0[r6] = r3
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.component.AbstractEditComponent.convertEmptyProperty(java.lang.String, java.lang.Object):java.lang.Object");
    }

    private void decideSoftKeyboard() {
        boolean[] $jacocoInit = $jacocoInit();
        View hostView = getHostView();
        if (hostView == null) {
            $jacocoInit[371] = true;
        } else {
            $jacocoInit[372] = true;
            final Context context = getContext();
            if (context == null) {
                $jacocoInit[373] = true;
            } else if (!(context instanceof Activity)) {
                $jacocoInit[374] = true;
            } else {
                $jacocoInit[375] = true;
                hostView.postDelayed(WXThread.secure((Runnable) new Runnable(this) {
                    private static transient /* synthetic */ boolean[] $jacocoData;
                    final /* synthetic */ AbstractEditComponent this$0;

                    private static /* synthetic */ boolean[] $jacocoInit() {
                        boolean[] zArr = $jacocoData;
                        if (zArr != null) {
                            return zArr;
                        }
                        boolean[] a2 = Offline.a(4162463321473808897L, "com/taobao/weex/ui/component/AbstractEditComponent$11", 5);
                        $jacocoData = a2;
                        return a2;
                    }

                    {
                        boolean[] $jacocoInit = $jacocoInit();
                        this.this$0 = r2;
                        $jacocoInit[0] = true;
                    }

                    public void run() {
                        boolean[] $jacocoInit = $jacocoInit();
                        if (((Activity) context).getCurrentFocus() instanceof EditText) {
                            $jacocoInit[1] = true;
                        } else {
                            $jacocoInit[2] = true;
                            AbstractEditComponent.access$1600(this.this$0).hideSoftInputFromWindow(((WXEditText) this.this$0.getHostView()).getWindowToken(), 0);
                            $jacocoInit[3] = true;
                        }
                        $jacocoInit[4] = true;
                    }
                }), 16);
                $jacocoInit[376] = true;
            }
        }
        $jacocoInit[377] = true;
    }

    @JSMethod
    public void setSelectionRange(int i, int i2) {
        boolean[] $jacocoInit = $jacocoInit();
        EditText editText = (EditText) getHostView();
        if (editText == null) {
            $jacocoInit[378] = true;
        } else {
            $jacocoInit[379] = true;
            int length = ((WXEditText) getHostView()).length();
            if (i > length) {
                $jacocoInit[380] = true;
            } else if (i2 > length) {
                $jacocoInit[381] = true;
            } else {
                focus();
                $jacocoInit[383] = true;
                editText.setSelection(i, i2);
                $jacocoInit[384] = true;
            }
            $jacocoInit[382] = true;
            return;
        }
        $jacocoInit[385] = true;
    }

    @JSMethod
    public void getSelectionRange(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        HashMap hashMap = new HashMap(2);
        $jacocoInit[386] = true;
        EditText editText = (EditText) getHostView();
        if (editText == null) {
            $jacocoInit[387] = true;
        } else {
            $jacocoInit[388] = true;
            int selectionStart = editText.getSelectionStart();
            $jacocoInit[389] = true;
            int selectionEnd = editText.getSelectionEnd();
            $jacocoInit[390] = true;
            if (editText.hasFocus()) {
                $jacocoInit[391] = true;
            } else {
                $jacocoInit[392] = true;
                selectionStart = 0;
                selectionEnd = 0;
            }
            hashMap.put(Constants.Name.SELECTION_START, Integer.valueOf(selectionStart));
            $jacocoInit[393] = true;
            hashMap.put(Constants.Name.SELECTION_END, Integer.valueOf(selectionEnd));
            $jacocoInit[394] = true;
        }
        WXBridgeManager.getInstance().callback(getInstanceId(), str, hashMap, false);
        $jacocoInit[395] = true;
    }

    @JSMethod
    public void setTextFormatter(JSONObject jSONObject) {
        boolean[] $jacocoInit = $jacocoInit();
        try {
            String string = jSONObject.getString("formatRule");
            $jacocoInit[396] = true;
            String string2 = jSONObject.getString("formatReplace");
            $jacocoInit[397] = true;
            String string3 = jSONObject.getString("recoverRule");
            $jacocoInit[398] = true;
            String string4 = jSONObject.getString("recoverReplace");
            $jacocoInit[399] = true;
            PatternWrapper parseToPattern = parseToPattern(string, string2);
            $jacocoInit[400] = true;
            PatternWrapper parseToPattern2 = parseToPattern(string3, string4);
            if (parseToPattern == null) {
                $jacocoInit[401] = true;
            } else if (parseToPattern2 == null) {
                $jacocoInit[402] = true;
            } else {
                $jacocoInit[403] = true;
                this.mFormatter = new TextFormatter(parseToPattern, parseToPattern2, (AnonymousClass1) null);
                $jacocoInit[404] = true;
            }
            $jacocoInit[405] = true;
        } catch (Throwable th) {
            $jacocoInit[406] = true;
            th.printStackTrace();
            $jacocoInit[407] = true;
        }
        $jacocoInit[408] = true;
    }

    /* access modifiers changed from: protected */
    public final void addEditorActionListener(TextView.OnEditorActionListener onEditorActionListener) {
        boolean[] $jacocoInit = $jacocoInit();
        if (onEditorActionListener == null) {
            $jacocoInit[409] = true;
        } else {
            TextView textView = (TextView) getHostView();
            if (textView == null) {
                $jacocoInit[410] = true;
            } else {
                if (this.mEditorActionListeners != null) {
                    $jacocoInit[411] = true;
                } else {
                    $jacocoInit[412] = true;
                    this.mEditorActionListeners = new ArrayList();
                    $jacocoInit[413] = true;
                    textView.setOnEditorActionListener(new TextView.OnEditorActionListener(this) {
                        private static transient /* synthetic */ boolean[] $jacocoData;
                        private boolean handled = true;
                        final /* synthetic */ AbstractEditComponent this$0;

                        private static /* synthetic */ boolean[] $jacocoInit() {
                            boolean[] zArr = $jacocoData;
                            if (zArr != null) {
                                return zArr;
                            }
                            boolean[] a2 = Offline.a(5305568872401376339L, "com/taobao/weex/ui/component/AbstractEditComponent$12", 7);
                            $jacocoData = a2;
                            return a2;
                        }

                        {
                            boolean[] $jacocoInit = $jacocoInit();
                            this.this$0 = r3;
                            $jacocoInit[0] = true;
                        }

                        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                            boolean[] $jacocoInit = $jacocoInit();
                            $jacocoInit[1] = true;
                            for (TextView.OnEditorActionListener onEditorActionListener : AbstractEditComponent.access$1800(this.this$0)) {
                                if (onEditorActionListener == null) {
                                    $jacocoInit[2] = true;
                                } else {
                                    $jacocoInit[3] = true;
                                    this.handled = onEditorActionListener.onEditorAction(textView, i, keyEvent) & this.handled;
                                    $jacocoInit[4] = true;
                                }
                                $jacocoInit[5] = true;
                            }
                            boolean z = this.handled;
                            $jacocoInit[6] = true;
                            return z;
                        }
                    });
                    $jacocoInit[414] = true;
                }
                this.mEditorActionListeners.add(onEditorActionListener);
                $jacocoInit[415] = true;
            }
        }
        $jacocoInit[416] = true;
    }

    public final void addTextChangedListener(TextWatcher textWatcher) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mTextChangedListeners != null) {
            $jacocoInit[417] = true;
        } else {
            $jacocoInit[418] = true;
            this.mTextChangedListeners = new ArrayList();
            $jacocoInit[419] = true;
        }
        this.mTextChangedListeners.add(textWatcher);
        $jacocoInit[420] = true;
    }

    private void addKeyboardListener(WXEditText wXEditText) {
        boolean[] $jacocoInit = $jacocoInit();
        if (wXEditText == null) {
            $jacocoInit[421] = true;
            return;
        }
        final Context context = wXEditText.getContext();
        if (context == null) {
            $jacocoInit[422] = true;
        } else if (!(context instanceof Activity)) {
            $jacocoInit[423] = true;
        } else {
            $jacocoInit[424] = true;
            SoftKeyboardDetector.registerKeyboardEventListener((Activity) context, new SoftKeyboardDetector.OnKeyboardEventListener(this) {
                private static transient /* synthetic */ boolean[] $jacocoData;
                final /* synthetic */ AbstractEditComponent this$0;

                private static /* synthetic */ boolean[] $jacocoInit() {
                    boolean[] zArr = $jacocoData;
                    if (zArr != null) {
                        return zArr;
                    }
                    boolean[] a2 = Offline.a(-6788334626588567195L, "com/taobao/weex/ui/component/AbstractEditComponent$13", 17);
                    $jacocoData = a2;
                    return a2;
                }

                {
                    boolean[] $jacocoInit = $jacocoInit();
                    this.this$0 = r2;
                    $jacocoInit[0] = true;
                }

                public void onKeyboardEvent(boolean z) {
                    boolean[] $jacocoInit = $jacocoInit();
                    if (!AbstractEditComponent.access$1900(this.this$0)) {
                        $jacocoInit[1] = true;
                    } else {
                        $jacocoInit[2] = true;
                        HashMap hashMap = new HashMap(1);
                        $jacocoInit[3] = true;
                        hashMap.put("isShow", Boolean.valueOf(z));
                        if (!z) {
                            $jacocoInit[4] = true;
                        } else {
                            $jacocoInit[5] = true;
                            Rect rect = new Rect();
                            $jacocoInit[6] = true;
                            ((Activity) context).getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
                            $jacocoInit[7] = true;
                            float screenHeight = (float) (WXViewUtils.getScreenHeight(context) - (rect.bottom - rect.top));
                            AbstractEditComponent abstractEditComponent = this.this$0;
                            $jacocoInit[8] = true;
                            int instanceViewPortWidth = abstractEditComponent.getInstance().getInstanceViewPortWidth();
                            $jacocoInit[9] = true;
                            float webPxByWidth = WXViewUtils.getWebPxByWidth(screenHeight, instanceViewPortWidth);
                            $jacocoInit[10] = true;
                            hashMap.put("keyboardSize", Float.valueOf(webPxByWidth));
                            $jacocoInit[11] = true;
                        }
                        this.this$0.fireEvent(Constants.Event.KEYBOARD, hashMap);
                        $jacocoInit[12] = true;
                    }
                    if (z) {
                        $jacocoInit[13] = true;
                    } else {
                        $jacocoInit[14] = true;
                        this.this$0.blur();
                        $jacocoInit[15] = true;
                    }
                    $jacocoInit[16] = true;
                }
            });
            $jacocoInit[425] = true;
        }
        $jacocoInit[426] = true;
    }

    public void destroy() {
        boolean[] $jacocoInit = $jacocoInit();
        super.destroy();
        if (this.mUnregister == null) {
            $jacocoInit[427] = true;
        } else {
            try {
                $jacocoInit[428] = true;
                this.mUnregister.execute();
                this.mUnregister = null;
                $jacocoInit[429] = true;
            } catch (Throwable th) {
                $jacocoInit[430] = true;
                WXLogUtils.w("Unregister throw ", th);
                $jacocoInit[431] = true;
            }
        }
        $jacocoInit[432] = true;
    }

    private PatternWrapper parseToPattern(String str, String str2) {
        int i;
        Pattern pattern;
        boolean[] $jacocoInit = $jacocoInit();
        if (str == null) {
            $jacocoInit[433] = true;
        } else if (str2 == null) {
            $jacocoInit[434] = true;
        } else {
            $jacocoInit[436] = true;
            if (!Pattern.compile("/[\\S]+/[i]?[m]?[g]?").matcher(str).matches()) {
                $jacocoInit[437] = true;
                WXLogUtils.w("WXInput", "Illegal js pattern syntax: " + str);
                $jacocoInit[438] = true;
                return null;
            }
            $jacocoInit[439] = true;
            String substring = str.substring(str.lastIndexOf("/") + 1);
            $jacocoInit[440] = true;
            String substring2 = str.substring(str.indexOf("/") + 1, str.lastIndexOf("/"));
            $jacocoInit[441] = true;
            boolean z = false;
            if (!substring.contains("i")) {
                $jacocoInit[442] = true;
                i = 0;
            } else {
                i = 2;
                $jacocoInit[443] = true;
            }
            if (!substring.contains("m")) {
                $jacocoInit[444] = true;
            } else {
                i |= 32;
                $jacocoInit[445] = true;
            }
            if (!substring.contains("g")) {
                $jacocoInit[446] = true;
            } else {
                $jacocoInit[447] = true;
                z = true;
            }
            try {
                $jacocoInit[448] = true;
                pattern = Pattern.compile(substring2, i);
                $jacocoInit[449] = true;
            } catch (PatternSyntaxException unused) {
                $jacocoInit[450] = true;
                WXLogUtils.w("WXInput", "Pattern syntax error: " + substring2);
                $jacocoInit[451] = true;
                pattern = null;
            }
            if (pattern == null) {
                $jacocoInit[452] = true;
                return null;
            }
            PatternWrapper patternWrapper = new PatternWrapper((AnonymousClass1) null);
            $jacocoInit[453] = true;
            PatternWrapper.access$2102(patternWrapper, z);
            $jacocoInit[454] = true;
            PatternWrapper.access$2202(patternWrapper, pattern);
            $jacocoInit[455] = true;
            PatternWrapper.access$2302(patternWrapper, str2);
            $jacocoInit[456] = true;
            return patternWrapper;
        }
        $jacocoInit[435] = true;
        return null;
    }

    private static class PatternWrapper {
        private static transient /* synthetic */ boolean[] $jacocoData;
        private boolean global;
        private Pattern matcher;
        private String replace;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(-7391331230838175721L, "com/taobao/weex/ui/component/AbstractEditComponent$PatternWrapper", 8);
            $jacocoData = a2;
            return a2;
        }

        private PatternWrapper() {
            boolean[] $jacocoInit = $jacocoInit();
            this.global = false;
            $jacocoInit[0] = true;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ PatternWrapper(AnonymousClass1 r2) {
            this();
            boolean[] $jacocoInit = $jacocoInit();
            $jacocoInit[1] = true;
        }

        static /* synthetic */ boolean access$2100(PatternWrapper patternWrapper) {
            boolean[] $jacocoInit = $jacocoInit();
            boolean z = patternWrapper.global;
            $jacocoInit[5] = true;
            return z;
        }

        static /* synthetic */ boolean access$2102(PatternWrapper patternWrapper, boolean z) {
            boolean[] $jacocoInit = $jacocoInit();
            patternWrapper.global = z;
            $jacocoInit[2] = true;
            return z;
        }

        static /* synthetic */ Pattern access$2200(PatternWrapper patternWrapper) {
            boolean[] $jacocoInit = $jacocoInit();
            Pattern pattern = patternWrapper.matcher;
            $jacocoInit[7] = true;
            return pattern;
        }

        static /* synthetic */ Pattern access$2202(PatternWrapper patternWrapper, Pattern pattern) {
            boolean[] $jacocoInit = $jacocoInit();
            patternWrapper.matcher = pattern;
            $jacocoInit[3] = true;
            return pattern;
        }

        static /* synthetic */ String access$2300(PatternWrapper patternWrapper) {
            boolean[] $jacocoInit = $jacocoInit();
            String str = patternWrapper.replace;
            $jacocoInit[6] = true;
            return str;
        }

        static /* synthetic */ String access$2302(PatternWrapper patternWrapper, String str) {
            boolean[] $jacocoInit = $jacocoInit();
            patternWrapper.replace = str;
            $jacocoInit[4] = true;
            return str;
        }
    }

    private static class TextFormatter {
        private static transient /* synthetic */ boolean[] $jacocoData;
        private PatternWrapper format;
        private PatternWrapper recover;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(-6626042541368374544L, "com/taobao/weex/ui/component/AbstractEditComponent$TextFormatter", 22);
            $jacocoData = a2;
            return a2;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ TextFormatter(PatternWrapper patternWrapper, PatternWrapper patternWrapper2, AnonymousClass1 r3) {
            this(patternWrapper, patternWrapper2);
            boolean[] $jacocoInit = $jacocoInit();
            $jacocoInit[21] = true;
        }

        static /* synthetic */ String access$700(TextFormatter textFormatter, String str) {
            boolean[] $jacocoInit = $jacocoInit();
            String recover2 = textFormatter.recover(str);
            $jacocoInit[19] = true;
            return recover2;
        }

        static /* synthetic */ String access$800(TextFormatter textFormatter, String str) {
            boolean[] $jacocoInit = $jacocoInit();
            String format2 = textFormatter.format(str);
            $jacocoInit[20] = true;
            return format2;
        }

        private TextFormatter(PatternWrapper patternWrapper, PatternWrapper patternWrapper2) {
            boolean[] $jacocoInit = $jacocoInit();
            this.format = patternWrapper;
            this.recover = patternWrapper2;
            $jacocoInit[0] = true;
        }

        private String format(String str) {
            boolean[] $jacocoInit = $jacocoInit();
            try {
                if (this.format != null) {
                    $jacocoInit[1] = true;
                    if (!PatternWrapper.access$2100(this.format)) {
                        $jacocoInit[2] = true;
                        String replaceFirst = PatternWrapper.access$2200(this.format).matcher(str).replaceFirst(PatternWrapper.access$2300(this.format));
                        $jacocoInit[5] = true;
                        return replaceFirst;
                    }
                    $jacocoInit[3] = true;
                    String replaceAll = PatternWrapper.access$2200(this.format).matcher(str).replaceAll(PatternWrapper.access$2300(this.format));
                    $jacocoInit[4] = true;
                    return replaceAll;
                }
                $jacocoInit[6] = true;
                $jacocoInit[9] = true;
                return str;
            } catch (Throwable th) {
                $jacocoInit[7] = true;
                WXLogUtils.w("WXInput", "[format] " + th.getMessage());
                $jacocoInit[8] = true;
            }
        }

        private String recover(String str) {
            boolean[] $jacocoInit = $jacocoInit();
            try {
                if (this.recover != null) {
                    $jacocoInit[10] = true;
                    if (!PatternWrapper.access$2100(this.recover)) {
                        $jacocoInit[11] = true;
                        String replaceFirst = PatternWrapper.access$2200(this.recover).matcher(str).replaceFirst(PatternWrapper.access$2300(this.recover));
                        $jacocoInit[14] = true;
                        return replaceFirst;
                    }
                    $jacocoInit[12] = true;
                    String replaceAll = PatternWrapper.access$2200(this.recover).matcher(str).replaceAll(PatternWrapper.access$2300(this.recover));
                    $jacocoInit[13] = true;
                    return replaceAll;
                }
                $jacocoInit[15] = true;
                $jacocoInit[18] = true;
                return str;
            } catch (Throwable th) {
                $jacocoInit[16] = true;
                WXLogUtils.w("WXInput", "[formatted] " + th.getMessage());
                $jacocoInit[17] = true;
            }
        }
    }
}
