package com.xiaomi.smarthome.framework.plugin.rn.fixbug.image;

import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.views.image.GlobalImageLoadListener;
import com.facebook.react.views.image.ReactImageManager;
import com.facebook.react.views.image.ReactImageView;
import javax.annotation.Nullable;

public class FixReactImageManager extends ReactImageManager {
    public ReactImageView createViewInstance(ThemedReactContext themedReactContext) {
        return new FixReactImageView(themedReactContext, getDraweeControllerBuilder(), (GlobalImageLoadListener) null, getCallerContext());
    }

    public void setResizeMode(ReactImageView reactImageView, @Nullable String str) {
        if ((reactImageView instanceof FixReactImageView) && "stretch".equalsIgnoreCase(str)) {
            ((FixReactImageView) reactImageView).mFiltered = true;
        }
        super.setResizeMode(reactImageView, str);
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x008e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setSource(com.facebook.react.views.image.ReactImageView r8, @javax.annotation.Nullable com.facebook.react.bridge.ReadableArray r9) {
        /*
            r7 = this;
            if (r9 == 0) goto L_0x009c
            int r0 = r9.size()
            if (r0 <= 0) goto L_0x009c
            r0 = 0
            com.facebook.react.bridge.ReadableType r1 = r9.getType(r0)
            com.facebook.react.bridge.ReadableType r2 = com.facebook.react.bridge.ReadableType.Map
            if (r1 != r2) goto L_0x009c
            com.facebook.react.bridge.ReadableMap r1 = r9.getMap(r0)
            r2 = 0
            java.lang.String r3 = "uri"
            boolean r3 = r1.hasKey(r3)
            if (r3 == 0) goto L_0x0049
            java.lang.String r3 = "uri"
            java.lang.String r3 = r1.getString(r3)
            if (r3 == 0) goto L_0x008c
            java.lang.String r4 = "/"
            boolean r4 = r3.startsWith(r4)
            if (r4 == 0) goto L_0x008c
            java.util.HashMap r1 = r1.toHashMap()
            java.lang.String r2 = "uri"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "file://"
            r4.append(r5)
            r4.append(r3)
            java.lang.String r3 = r4.toString()
            r1.put(r2, r3)
            goto L_0x008b
        L_0x0049:
            java.lang.String r3 = "local"
            boolean r3 = r1.hasKey(r3)
            if (r3 == 0) goto L_0x008c
            java.lang.String r2 = "local"
            java.lang.String r2 = r1.getString(r2)
            java.util.HashMap r1 = r1.toHashMap()
            java.lang.String r3 = "local"
            r1.remove(r3)
            java.lang.String r3 = "uri"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "file://"
            r4.append(r5)
            java.io.File r5 = new java.io.File
            com.xiaomi.smarthome.framework.plugin.rn.RNRuntime r6 = com.xiaomi.smarthome.framework.plugin.rn.RNRuntime.a()
            com.xiaomi.smarthome.framework.plugin.rn.RNRuntime$RuntimeInfo r6 = r6.b()
            java.io.File r6 = r6.c()
            r5.<init>(r6, r2)
            java.lang.String r2 = r5.getAbsolutePath()
            r4.append(r2)
            java.lang.String r2 = r4.toString()
            r1.put(r3, r2)
        L_0x008b:
            r2 = r1
        L_0x008c:
            if (r2 == 0) goto L_0x009c
            java.util.ArrayList r9 = r9.toArrayList()
            r9.remove(r0)
            r9.add(r0, r2)
            com.facebook.react.bridge.WritableNativeArray r9 = com.facebook.react.bridge.Arguments.makeNativeArray((java.util.List) r9)
        L_0x009c:
            r8.setSource(r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.plugin.rn.fixbug.image.FixReactImageManager.setSource(com.facebook.react.views.image.ReactImageView, com.facebook.react.bridge.ReadableArray):void");
    }
}
