package com.taobao.weex.ui.module;

import android.app.Dialog;
import android.content.DialogInterface;
import android.widget.Toast;
import com.taobao.weex.WXSDKEngine;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXModalUIModule extends WXSDKEngine.DestroyableModule {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    public static final String CANCEL = "Cancel";
    public static final String CANCEL_TITLE = "cancelTitle";
    public static final String DATA = "data";
    public static final String DEFAULT = "default";
    public static final String DURATION = "duration";
    public static final String MESSAGE = "message";
    public static final String OK = "OK";
    public static final String OK_TITLE = "okTitle";
    public static final String RESULT = "result";
    private Dialog activeDialog;
    private Toast toast;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-6322802956384072871L, "com/taobao/weex/ui/module/WXModalUIModule", 103);
        $jacocoData = a2;
        return a2;
    }

    public WXModalUIModule() {
        $jacocoInit()[0] = true;
    }

    static /* synthetic */ Dialog access$002(WXModalUIModule wXModalUIModule, Dialog dialog) {
        boolean[] $jacocoInit = $jacocoInit();
        wXModalUIModule.activeDialog = dialog;
        $jacocoInit[102] = true;
        return dialog;
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x0068  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0076  */
    @com.taobao.weex.annotation.JSMethod(uiThread = true)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void toast(com.alibaba.fastjson.JSONObject r9) {
        /*
            r8 = this;
            boolean[] r0 = $jacocoInit()
            com.taobao.weex.WXSDKInstance r1 = r8.mWXSDKInstance
            android.content.Context r1 = r1.getContext()
            r2 = 1
            if (r1 != 0) goto L_0x0010
            r0[r2] = r2
            return
        L_0x0010:
            java.lang.String r1 = ""
            r3 = 3
            r4 = 0
            if (r9 != 0) goto L_0x001b
            r9 = 2
            r0[r9] = r2
            r9 = 0
            goto L_0x0062
        L_0x001b:
            r0[r3] = r2     // Catch:{ Exception -> 0x0052 }
            java.lang.String r5 = "message"
            java.lang.String r5 = r9.getString(r5)     // Catch:{ Exception -> 0x0052 }
            r1 = 4
            r0[r1] = r2     // Catch:{ Exception -> 0x004f }
            java.lang.String r1 = "duration"
            boolean r1 = r9.containsKey(r1)     // Catch:{ Exception -> 0x004f }
            if (r1 != 0) goto L_0x0033
            r9 = 5
            r0[r9] = r2     // Catch:{ Exception -> 0x004f }
            r9 = 0
            goto L_0x0043
        L_0x0033:
            r1 = 6
            r0[r1] = r2     // Catch:{ Exception -> 0x004f }
            java.lang.String r1 = "duration"
            java.lang.Integer r9 = r9.getInteger(r1)     // Catch:{ Exception -> 0x004f }
            int r9 = r9.intValue()     // Catch:{ Exception -> 0x004f }
            r1 = 7
            r0[r1] = r2     // Catch:{ Exception -> 0x0049 }
        L_0x0043:
            r1 = 8
            r0[r1] = r2
            r1 = r5
            goto L_0x0062
        L_0x0049:
            r1 = move-exception
            r7 = r5
            r5 = r9
            r9 = r1
            r1 = r7
            goto L_0x0054
        L_0x004f:
            r9 = move-exception
            r1 = r5
            goto L_0x0053
        L_0x0052:
            r9 = move-exception
        L_0x0053:
            r5 = 0
        L_0x0054:
            r6 = 9
            r0[r6] = r2
            java.lang.String r6 = "[WXModalUIModule] alert param parse error "
            com.taobao.weex.utils.WXLogUtils.e((java.lang.String) r6, (java.lang.Throwable) r9)
            r9 = 10
            r0[r9] = r2
            r9 = r5
        L_0x0062:
            boolean r5 = android.text.TextUtils.isEmpty(r1)
            if (r5 == 0) goto L_0x0076
            r9 = 11
            r0[r9] = r2
            java.lang.String r9 = "[WXModalUIModule] toast param parse is null "
            com.taobao.weex.utils.WXLogUtils.e(r9)
            r9 = 12
            r0[r9] = r2
            return
        L_0x0076:
            if (r9 <= r3) goto L_0x007e
            r9 = 13
            r0[r9] = r2
            r9 = 1
            goto L_0x0083
        L_0x007e:
            r9 = 14
            r0[r9] = r2
            r9 = 0
        L_0x0083:
            android.widget.Toast r3 = r8.toast
            r5 = 17
            if (r3 != 0) goto L_0x009e
            r3 = 15
            r0[r3] = r2
            com.taobao.weex.WXSDKInstance r3 = r8.mWXSDKInstance
            android.content.Context r3 = r3.getContext()
            android.widget.Toast r9 = android.widget.Toast.makeText(r3, r1, r9)
            r8.toast = r9
            r9 = 16
            r0[r9] = r2
            goto L_0x00ae
        L_0x009e:
            android.widget.Toast r3 = r8.toast
            r3.setDuration(r9)
            r0[r5] = r2
            android.widget.Toast r9 = r8.toast
            r9.setText(r1)
            r9 = 18
            r0[r9] = r2
        L_0x00ae:
            android.widget.Toast r9 = r8.toast
            r9.setGravity(r5, r4, r4)
            r9 = 19
            r0[r9] = r2
            android.widget.Toast r9 = r8.toast
            r9.show()
            r9 = 20
            r0[r9] = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.module.WXModalUIModule.toast(com.alibaba.fastjson.JSONObject):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x004c  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0073  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x007a  */
    @com.taobao.weex.annotation.JSMethod(uiThread = true)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void alert(com.alibaba.fastjson.JSONObject r6, final com.taobao.weex.bridge.JSCallback r7) {
        /*
            r5 = this;
            boolean[] r0 = $jacocoInit()
            com.taobao.weex.WXSDKInstance r1 = r5.mWXSDKInstance
            android.content.Context r1 = r1.getContext()
            boolean r1 = r1 instanceof android.app.Activity
            r2 = 1
            if (r1 == 0) goto L_0x00ad
            java.lang.String r1 = ""
            java.lang.String r3 = "OK"
            if (r6 != 0) goto L_0x001a
            r6 = 21
            r0[r6] = r2
            goto L_0x0046
        L_0x001a:
            r4 = 22
            r0[r4] = r2     // Catch:{ Exception -> 0x0038 }
            java.lang.String r4 = "message"
            java.lang.String r4 = r6.getString(r4)     // Catch:{ Exception -> 0x0038 }
            r1 = 23
            r0[r1] = r2     // Catch:{ Exception -> 0x0035 }
            java.lang.String r1 = "okTitle"
            java.lang.String r6 = r6.getString(r1)     // Catch:{ Exception -> 0x0035 }
            r1 = 24
            r0[r1] = r2
            r3 = r6
            r1 = r4
            goto L_0x0046
        L_0x0035:
            r6 = move-exception
            r1 = r4
            goto L_0x0039
        L_0x0038:
            r6 = move-exception
        L_0x0039:
            r4 = 25
            r0[r4] = r2
            java.lang.String r4 = "[WXModalUIModule] alert param parse error "
            com.taobao.weex.utils.WXLogUtils.e((java.lang.String) r4, (java.lang.Throwable) r6)
            r6 = 26
            r0[r6] = r2
        L_0x0046:
            boolean r6 = android.text.TextUtils.isEmpty(r1)
            if (r6 != 0) goto L_0x0051
            r6 = 27
            r0[r6] = r2
            goto L_0x0057
        L_0x0051:
            java.lang.String r1 = ""
            r6 = 28
            r0[r6] = r2
        L_0x0057:
            android.app.AlertDialog$Builder r6 = new android.app.AlertDialog$Builder
            com.taobao.weex.WXSDKInstance r4 = r5.mWXSDKInstance
            android.content.Context r4 = r4.getContext()
            r6.<init>(r4)
            r4 = 29
            r0[r4] = r2
            r6.setMessage(r1)
            r1 = 30
            r0[r1] = r2
            boolean r1 = android.text.TextUtils.isEmpty(r3)
            if (r1 == 0) goto L_0x007a
            java.lang.String r3 = "OK"
            r1 = 31
            r0[r1] = r2
            goto L_0x007e
        L_0x007a:
            r1 = 32
            r0[r1] = r2
        L_0x007e:
            r1 = 33
            r0[r1] = r2
            com.taobao.weex.ui.module.WXModalUIModule$1 r1 = new com.taobao.weex.ui.module.WXModalUIModule$1
            r1.<init>(r5, r7, r3)
            r6.setPositiveButton(r3, r1)
            r7 = 34
            r0[r7] = r2
            android.app.AlertDialog r6 = r6.create()
            r7 = 35
            r0[r7] = r2
            r7 = 0
            r6.setCanceledOnTouchOutside(r7)
            r7 = 36
            r0[r7] = r2
            r6.show()
            r7 = 37
            r0[r7] = r2
            r5.tracking(r6)
            r6 = 38
            r0[r6] = r2
            goto L_0x00b6
        L_0x00ad:
            java.lang.String r6 = "[WXModalUIModule] when call alert mWXSDKInstance.getContext() must instanceof Activity"
            com.taobao.weex.utils.WXLogUtils.e(r6)
            r6 = 39
            r0[r6] = r2
        L_0x00b6:
            r6 = 40
            r0[r6] = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.module.WXModalUIModule.alert(com.alibaba.fastjson.JSONObject, com.taobao.weex.bridge.JSCallback):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x005c  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x008a  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0098  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x009f  */
    @com.taobao.weex.annotation.JSMethod(uiThread = true)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void confirm(com.alibaba.fastjson.JSONObject r7, final com.taobao.weex.bridge.JSCallback r8) {
        /*
            r6 = this;
            boolean[] r0 = $jacocoInit()
            com.taobao.weex.WXSDKInstance r1 = r6.mWXSDKInstance
            android.content.Context r1 = r1.getContext()
            boolean r1 = r1 instanceof android.app.Activity
            r2 = 1
            if (r1 == 0) goto L_0x00de
            java.lang.String r1 = ""
            java.lang.String r3 = "OK"
            java.lang.String r4 = "Cancel"
            if (r7 != 0) goto L_0x001c
            r7 = 41
            r0[r7] = r2
            goto L_0x0056
        L_0x001c:
            r5 = 42
            r0[r5] = r2     // Catch:{ Exception -> 0x0048 }
            java.lang.String r5 = "message"
            java.lang.String r5 = r7.getString(r5)     // Catch:{ Exception -> 0x0048 }
            r1 = 43
            r0[r1] = r2     // Catch:{ Exception -> 0x0045 }
            java.lang.String r1 = "okTitle"
            java.lang.String r1 = r7.getString(r1)     // Catch:{ Exception -> 0x0045 }
            r3 = 44
            r0[r3] = r2     // Catch:{ Exception -> 0x0042 }
            java.lang.String r3 = "cancelTitle"
            java.lang.String r7 = r7.getString(r3)     // Catch:{ Exception -> 0x0042 }
            r3 = 45
            r0[r3] = r2
            r4 = r7
            r3 = r1
            r1 = r5
            goto L_0x0056
        L_0x0042:
            r7 = move-exception
            r3 = r1
            goto L_0x0046
        L_0x0045:
            r7 = move-exception
        L_0x0046:
            r1 = r5
            goto L_0x0049
        L_0x0048:
            r7 = move-exception
        L_0x0049:
            r5 = 46
            r0[r5] = r2
            java.lang.String r5 = "[WXModalUIModule] confirm param parse error "
            com.taobao.weex.utils.WXLogUtils.e((java.lang.String) r5, (java.lang.Throwable) r7)
            r7 = 47
            r0[r7] = r2
        L_0x0056:
            boolean r7 = android.text.TextUtils.isEmpty(r1)
            if (r7 != 0) goto L_0x0061
            r7 = 48
            r0[r7] = r2
            goto L_0x0067
        L_0x0061:
            java.lang.String r1 = ""
            r7 = 49
            r0[r7] = r2
        L_0x0067:
            android.app.AlertDialog$Builder r7 = new android.app.AlertDialog$Builder
            com.taobao.weex.WXSDKInstance r5 = r6.mWXSDKInstance
            android.content.Context r5 = r5.getContext()
            r7.<init>(r5)
            r5 = 50
            r0[r5] = r2
            r7.setMessage(r1)
            r1 = 51
            r0[r1] = r2
            boolean r1 = android.text.TextUtils.isEmpty(r3)
            if (r1 == 0) goto L_0x008a
            java.lang.String r3 = "OK"
            r1 = 52
            r0[r1] = r2
            goto L_0x008e
        L_0x008a:
            r1 = 53
            r0[r1] = r2
        L_0x008e:
            r1 = 54
            r0[r1] = r2
            boolean r1 = android.text.TextUtils.isEmpty(r4)
            if (r1 == 0) goto L_0x009f
            java.lang.String r4 = "Cancel"
            r1 = 55
            r0[r1] = r2
            goto L_0x00a3
        L_0x009f:
            r1 = 56
            r0[r1] = r2
        L_0x00a3:
            r1 = 57
            r0[r1] = r2
            com.taobao.weex.ui.module.WXModalUIModule$2 r1 = new com.taobao.weex.ui.module.WXModalUIModule$2
            r1.<init>(r6, r8, r3)
            r7.setPositiveButton(r3, r1)
            r1 = 58
            r0[r1] = r2
            com.taobao.weex.ui.module.WXModalUIModule$3 r1 = new com.taobao.weex.ui.module.WXModalUIModule$3
            r1.<init>(r6, r8, r4)
            r7.setNegativeButton(r4, r1)
            r8 = 59
            r0[r8] = r2
            android.app.AlertDialog r7 = r7.create()
            r8 = 60
            r0[r8] = r2
            r8 = 0
            r7.setCanceledOnTouchOutside(r8)
            r8 = 61
            r0[r8] = r2
            r7.show()
            r8 = 62
            r0[r8] = r2
            r6.tracking(r7)
            r7 = 63
            r0[r7] = r2
            goto L_0x00e7
        L_0x00de:
            java.lang.String r7 = "[WXModalUIModule] when call confirm mWXSDKInstance.getContext() must instanceof Activity"
            com.taobao.weex.utils.WXLogUtils.e(r7)
            r7 = 64
            r0[r7] = r2
        L_0x00e7:
            r7 = 65
            r0[r7] = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.module.WXModalUIModule.confirm(com.alibaba.fastjson.JSONObject, com.taobao.weex.bridge.JSCallback):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0071  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00b0  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00b7  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00c5  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00cc  */
    @com.taobao.weex.annotation.JSMethod(uiThread = true)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void prompt(com.alibaba.fastjson.JSONObject r8, final com.taobao.weex.bridge.JSCallback r9) {
        /*
            r7 = this;
            boolean[] r0 = $jacocoInit()
            com.taobao.weex.WXSDKInstance r1 = r7.mWXSDKInstance
            android.content.Context r1 = r1.getContext()
            boolean r1 = r1 instanceof android.app.Activity
            r2 = 1
            if (r1 == 0) goto L_0x010c
            java.lang.String r1 = ""
            java.lang.String r3 = ""
            java.lang.String r4 = "OK"
            java.lang.String r5 = "Cancel"
            if (r8 != 0) goto L_0x001e
            r8 = 66
            r0[r8] = r2
            goto L_0x0066
        L_0x001e:
            r6 = 67
            r0[r6] = r2     // Catch:{ Exception -> 0x0058 }
            java.lang.String r6 = "message"
            java.lang.String r6 = r8.getString(r6)     // Catch:{ Exception -> 0x0058 }
            r1 = 68
            r0[r1] = r2     // Catch:{ Exception -> 0x0055 }
            java.lang.String r1 = "okTitle"
            java.lang.String r1 = r8.getString(r1)     // Catch:{ Exception -> 0x0055 }
            r4 = 69
            r0[r4] = r2     // Catch:{ Exception -> 0x0052 }
            java.lang.String r4 = "cancelTitle"
            java.lang.String r4 = r8.getString(r4)     // Catch:{ Exception -> 0x0052 }
            r5 = 70
            r0[r5] = r2     // Catch:{ Exception -> 0x004f }
            java.lang.String r5 = "default"
            java.lang.String r8 = r8.getString(r5)     // Catch:{ Exception -> 0x004f }
            r3 = 71
            r0[r3] = r2
            r3 = r8
            r5 = r4
            r4 = r1
            r1 = r6
            goto L_0x0066
        L_0x004f:
            r8 = move-exception
            r5 = r4
            goto L_0x0053
        L_0x0052:
            r8 = move-exception
        L_0x0053:
            r4 = r1
            goto L_0x0056
        L_0x0055:
            r8 = move-exception
        L_0x0056:
            r1 = r6
            goto L_0x0059
        L_0x0058:
            r8 = move-exception
        L_0x0059:
            r6 = 72
            r0[r6] = r2
            java.lang.String r6 = "[WXModalUIModule] confirm param parse error "
            com.taobao.weex.utils.WXLogUtils.e((java.lang.String) r6, (java.lang.Throwable) r8)
            r8 = 73
            r0[r8] = r2
        L_0x0066:
            boolean r8 = android.text.TextUtils.isEmpty(r1)
            if (r8 != 0) goto L_0x0071
            r8 = 74
            r0[r8] = r2
            goto L_0x0077
        L_0x0071:
            java.lang.String r1 = ""
            r8 = 75
            r0[r8] = r2
        L_0x0077:
            android.app.AlertDialog$Builder r8 = new android.app.AlertDialog$Builder
            com.taobao.weex.WXSDKInstance r6 = r7.mWXSDKInstance
            android.content.Context r6 = r6.getContext()
            r8.<init>(r6)
            r6 = 76
            r0[r6] = r2
            r8.setMessage(r1)
            r1 = 77
            r0[r1] = r2
            android.widget.EditText r1 = new android.widget.EditText
            com.taobao.weex.WXSDKInstance r6 = r7.mWXSDKInstance
            android.content.Context r6 = r6.getContext()
            r1.<init>(r6)
            r6 = 78
            r0[r6] = r2
            r1.setText(r3)
            r3 = 79
            r0[r3] = r2
            r8.setView(r1)
            r3 = 80
            r0[r3] = r2
            boolean r3 = android.text.TextUtils.isEmpty(r4)
            if (r3 == 0) goto L_0x00b7
            java.lang.String r4 = "OK"
            r3 = 81
            r0[r3] = r2
            goto L_0x00bb
        L_0x00b7:
            r3 = 82
            r0[r3] = r2
        L_0x00bb:
            r3 = 83
            r0[r3] = r2
            boolean r3 = android.text.TextUtils.isEmpty(r5)
            if (r3 == 0) goto L_0x00cc
            java.lang.String r5 = "Cancel"
            r3 = 84
            r0[r3] = r2
            goto L_0x00d0
        L_0x00cc:
            r3 = 85
            r0[r3] = r2
        L_0x00d0:
            r3 = 86
            r0[r3] = r2
            com.taobao.weex.ui.module.WXModalUIModule$5 r3 = new com.taobao.weex.ui.module.WXModalUIModule$5
            r3.<init>(r7, r9, r4, r1)
            android.app.AlertDialog$Builder r3 = r8.setPositiveButton(r4, r3)
            com.taobao.weex.ui.module.WXModalUIModule$4 r4 = new com.taobao.weex.ui.module.WXModalUIModule$4
            r4.<init>(r7, r9, r5, r1)
            r9 = 87
            r0[r9] = r2
            r3.setNegativeButton(r5, r4)
            r9 = 88
            r0[r9] = r2
            android.app.AlertDialog r8 = r8.create()
            r9 = 89
            r0[r9] = r2
            r9 = 0
            r8.setCanceledOnTouchOutside(r9)
            r9 = 90
            r0[r9] = r2
            r8.show()
            r9 = 91
            r0[r9] = r2
            r7.tracking(r8)
            r8 = 92
            r0[r8] = r2
            goto L_0x0115
        L_0x010c:
            java.lang.String r8 = "when call prompt mWXSDKInstance.getContext() must instanceof Activity"
            com.taobao.weex.utils.WXLogUtils.e(r8)
            r8 = 93
            r0[r8] = r2
        L_0x0115:
            r8 = 94
            r0[r8] = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.module.WXModalUIModule.prompt(com.alibaba.fastjson.JSONObject, com.taobao.weex.bridge.JSCallback):void");
    }

    private void tracking(Dialog dialog) {
        boolean[] $jacocoInit = $jacocoInit();
        this.activeDialog = dialog;
        $jacocoInit[95] = true;
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ WXModalUIModule this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(-478113587960231827L, "com/taobao/weex/ui/module/WXModalUIModule$6", 2);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r3;
                $jacocoInit[0] = true;
            }

            public void onDismiss(DialogInterface dialogInterface) {
                boolean[] $jacocoInit = $jacocoInit();
                WXModalUIModule.access$002(this.this$0, (Dialog) null);
                $jacocoInit[1] = true;
            }
        });
        $jacocoInit[96] = true;
    }

    public void destroy() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.activeDialog == null) {
            $jacocoInit[97] = true;
        } else if (!this.activeDialog.isShowing()) {
            $jacocoInit[98] = true;
        } else {
            $jacocoInit[99] = true;
            this.activeDialog.dismiss();
            $jacocoInit[100] = true;
        }
        $jacocoInit[101] = true;
    }
}
