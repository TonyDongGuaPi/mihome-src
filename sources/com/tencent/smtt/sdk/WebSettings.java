package com.tencent.smtt.sdk;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.webkit.WebSettings;
import com.tencent.smtt.export.external.interfaces.IX5WebSettings;
import com.tencent.smtt.utils.q;

public class WebSettings {
    public static final int LOAD_CACHE_ELSE_NETWORK = 1;
    public static final int LOAD_CACHE_ONLY = 3;
    public static final int LOAD_DEFAULT = -1;
    public static final int LOAD_NORMAL = 0;
    public static final int LOAD_NO_CACHE = 2;

    /* renamed from: a  reason: collision with root package name */
    private IX5WebSettings f9108a;
    private android.webkit.WebSettings b;
    private boolean c;

    public enum LayoutAlgorithm {
        NORMAL,
        SINGLE_COLUMN,
        NARROW_COLUMNS
    }

    public enum PluginState {
        ON,
        ON_DEMAND,
        OFF
    }

    public enum RenderPriority {
        NORMAL,
        HIGH,
        LOW
    }

    public enum TextSize {
        SMALLEST(50),
        SMALLER(75),
        NORMAL(100),
        LARGER(125),
        LARGEST(150);
        
        int value;

        private TextSize(int i) {
            this.value = i;
        }
    }

    public enum ZoomDensity {
        FAR(150),
        MEDIUM(100),
        CLOSE(75);
        
        int value;

        private ZoomDensity(int i) {
            this.value = i;
        }
    }

    WebSettings(android.webkit.WebSettings webSettings) {
        this.f9108a = null;
        this.b = null;
        this.c = false;
        this.f9108a = null;
        this.b = webSettings;
        this.c = false;
    }

    WebSettings(IX5WebSettings iX5WebSettings) {
        this.f9108a = null;
        this.b = null;
        this.c = false;
        this.f9108a = iX5WebSettings;
        this.b = null;
        this.c = true;
    }

    @TargetApi(17)
    public static String getDefaultUserAgent(Context context) {
        Object a2;
        if (bt.a().b()) {
            return bt.a().c().i(context);
        }
        if (Build.VERSION.SDK_INT >= 17 && (a2 = q.a((Class<?>) android.webkit.WebSettings.class, "getDefaultUserAgent", (Class<?>[]) new Class[]{Context.class}, context)) != null) {
            return (String) a2;
        }
        return null;
    }

    @Deprecated
    public boolean enableSmoothTransition() {
        Object a2;
        if (this.c && this.f9108a != null) {
            return this.f9108a.enableSmoothTransition();
        }
        if (this.c || this.b == null || Build.VERSION.SDK_INT < 11 || (a2 = q.a((Object) this.b, "enableSmoothTransition")) == null) {
            return false;
        }
        return ((Boolean) a2).booleanValue();
    }

    @TargetApi(11)
    public boolean getAllowContentAccess() {
        Object a2;
        if (this.c && this.f9108a != null) {
            return this.f9108a.getAllowContentAccess();
        }
        if (this.c || this.b == null || Build.VERSION.SDK_INT < 11 || (a2 = q.a((Object) this.b, "getAllowContentAccess")) == null) {
            return false;
        }
        return ((Boolean) a2).booleanValue();
    }

    @TargetApi(3)
    public boolean getAllowFileAccess() {
        if (this.c && this.f9108a != null) {
            return this.f9108a.getAllowFileAccess();
        }
        if (this.c || this.b == null) {
            return false;
        }
        return this.b.getAllowFileAccess();
    }

    public synchronized boolean getBlockNetworkImage() {
        if (this.c && this.f9108a != null) {
            return this.f9108a.getBlockNetworkImage();
        } else if (this.c || this.b == null) {
            return false;
        } else {
            return this.b.getBlockNetworkImage();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x002b, code lost:
        return false;
     */
    @android.annotation.TargetApi(8)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean getBlockNetworkLoads() {
        /*
            r3 = this;
            monitor-enter(r3)
            boolean r0 = r3.c     // Catch:{ all -> 0x002c }
            if (r0 == 0) goto L_0x0011
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r3.f9108a     // Catch:{ all -> 0x002c }
            if (r0 == 0) goto L_0x0011
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r3.f9108a     // Catch:{ all -> 0x002c }
            boolean r0 = r0.getBlockNetworkLoads()     // Catch:{ all -> 0x002c }
            monitor-exit(r3)
            return r0
        L_0x0011:
            boolean r0 = r3.c     // Catch:{ all -> 0x002c }
            r1 = 0
            if (r0 != 0) goto L_0x002a
            android.webkit.WebSettings r0 = r3.b     // Catch:{ all -> 0x002c }
            if (r0 == 0) goto L_0x002a
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x002c }
            r2 = 8
            if (r0 < r2) goto L_0x0028
            android.webkit.WebSettings r0 = r3.b     // Catch:{ all -> 0x002c }
            boolean r0 = r0.getBlockNetworkLoads()     // Catch:{ all -> 0x002c }
            monitor-exit(r3)
            return r0
        L_0x0028:
            monitor-exit(r3)
            return r1
        L_0x002a:
            monitor-exit(r3)
            return r1
        L_0x002c:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.WebSettings.getBlockNetworkLoads():boolean");
    }

    @TargetApi(3)
    public boolean getBuiltInZoomControls() {
        if (this.c && this.f9108a != null) {
            return this.f9108a.getBuiltInZoomControls();
        }
        if (this.c || this.b == null) {
            return false;
        }
        return this.b.getBuiltInZoomControls();
    }

    public int getCacheMode() {
        if (this.c && this.f9108a != null) {
            return this.f9108a.getCacheMode();
        }
        if (this.c || this.b == null) {
            return 0;
        }
        return this.b.getCacheMode();
    }

    public synchronized String getCursiveFontFamily() {
        if (this.c && this.f9108a != null) {
            return this.f9108a.getCursiveFontFamily();
        } else if (this.c || this.b == null) {
            return "";
        } else {
            return this.b.getCursiveFontFamily();
        }
    }

    @TargetApi(5)
    public synchronized boolean getDatabaseEnabled() {
        if (this.c && this.f9108a != null) {
            return this.f9108a.getDatabaseEnabled();
        } else if (this.c || this.b == null) {
            return false;
        } else {
            return this.b.getDatabaseEnabled();
        }
    }

    @TargetApi(5)
    public synchronized String getDatabasePath() {
        if (this.c && this.f9108a != null) {
            return this.f9108a.getDatabasePath();
        } else if (this.c || this.b == null) {
            return "";
        } else {
            return this.b.getDatabasePath();
        }
    }

    public synchronized int getDefaultFixedFontSize() {
        if (this.c && this.f9108a != null) {
            return this.f9108a.getDefaultFixedFontSize();
        } else if (this.c || this.b == null) {
            return 0;
        } else {
            return this.b.getDefaultFixedFontSize();
        }
    }

    public synchronized int getDefaultFontSize() {
        if (this.c && this.f9108a != null) {
            return this.f9108a.getDefaultFontSize();
        } else if (this.c || this.b == null) {
            return 0;
        } else {
            return this.b.getDefaultFontSize();
        }
    }

    public synchronized String getDefaultTextEncodingName() {
        if (this.c && this.f9108a != null) {
            return this.f9108a.getDefaultTextEncodingName();
        } else if (this.c || this.b == null) {
            return "";
        } else {
            return this.b.getDefaultTextEncodingName();
        }
    }

    @TargetApi(7)
    public ZoomDensity getDefaultZoom() {
        String name;
        if (this.c && this.f9108a != null) {
            name = this.f9108a.getDefaultZoom().name();
        } else if (this.c || this.b == null) {
            return null;
        } else {
            name = this.b.getDefaultZoom().name();
        }
        return ZoomDensity.valueOf(name);
    }

    @TargetApi(11)
    public boolean getDisplayZoomControls() {
        Object a2;
        if (this.c && this.f9108a != null) {
            return this.f9108a.getDisplayZoomControls();
        }
        if (this.c || this.b == null || Build.VERSION.SDK_INT < 11 || (a2 = q.a((Object) this.b, "getDisplayZoomControls")) == null) {
            return false;
        }
        return ((Boolean) a2).booleanValue();
    }

    @TargetApi(7)
    public synchronized boolean getDomStorageEnabled() {
        if (this.c && this.f9108a != null) {
            return this.f9108a.getDomStorageEnabled();
        } else if (this.c || this.b == null) {
            return false;
        } else {
            return this.b.getDomStorageEnabled();
        }
    }

    public synchronized String getFantasyFontFamily() {
        if (this.c && this.f9108a != null) {
            return this.f9108a.getFantasyFontFamily();
        } else if (this.c || this.b == null) {
            return "";
        } else {
            return this.b.getFantasyFontFamily();
        }
    }

    public synchronized String getFixedFontFamily() {
        if (this.c && this.f9108a != null) {
            return this.f9108a.getFixedFontFamily();
        } else if (this.c || this.b == null) {
            return "";
        } else {
            return this.b.getFixedFontFamily();
        }
    }

    public synchronized boolean getJavaScriptCanOpenWindowsAutomatically() {
        if (this.c && this.f9108a != null) {
            return this.f9108a.getJavaScriptCanOpenWindowsAutomatically();
        } else if (this.c || this.b == null) {
            return false;
        } else {
            return this.b.getJavaScriptCanOpenWindowsAutomatically();
        }
    }

    public synchronized boolean getJavaScriptEnabled() {
        if (this.c && this.f9108a != null) {
            return this.f9108a.getJavaScriptEnabled();
        } else if (this.c || this.b == null) {
            return false;
        } else {
            return this.b.getJavaScriptEnabled();
        }
    }

    public synchronized LayoutAlgorithm getLayoutAlgorithm() {
        if (this.c && this.f9108a != null) {
            return LayoutAlgorithm.valueOf(this.f9108a.getLayoutAlgorithm().name());
        } else if (this.c || this.b == null) {
            return null;
        } else {
            return LayoutAlgorithm.valueOf(this.b.getLayoutAlgorithm().name());
        }
    }

    public boolean getLightTouchEnabled() {
        if (this.c && this.f9108a != null) {
            return this.f9108a.getLightTouchEnabled();
        }
        if (this.c || this.b == null) {
            return false;
        }
        return this.b.getLightTouchEnabled();
    }

    @TargetApi(7)
    public boolean getLoadWithOverviewMode() {
        if (this.c && this.f9108a != null) {
            return this.f9108a.getLoadWithOverviewMode();
        }
        if (this.c || this.b == null) {
            return false;
        }
        return this.b.getLoadWithOverviewMode();
    }

    public synchronized boolean getLoadsImagesAutomatically() {
        if (this.c && this.f9108a != null) {
            return this.f9108a.getLoadsImagesAutomatically();
        } else if (this.c || this.b == null) {
            return false;
        } else {
            return this.b.getLoadsImagesAutomatically();
        }
    }

    @TargetApi(17)
    public boolean getMediaPlaybackRequiresUserGesture() {
        Object a2;
        if (this.c && this.f9108a != null) {
            return this.f9108a.getMediaPlaybackRequiresUserGesture();
        }
        if (this.c || this.b == null || Build.VERSION.SDK_INT < 17 || (a2 = q.a((Object) this.b, "getMediaPlaybackRequiresUserGesture")) == null) {
            return false;
        }
        return ((Boolean) a2).booleanValue();
    }

    public synchronized int getMinimumFontSize() {
        if (this.c && this.f9108a != null) {
            return this.f9108a.getMinimumFontSize();
        } else if (this.c || this.b == null) {
            return 0;
        } else {
            return this.b.getMinimumFontSize();
        }
    }

    public synchronized int getMinimumLogicalFontSize() {
        if (this.c && this.f9108a != null) {
            return this.f9108a.getMinimumLogicalFontSize();
        } else if (this.c || this.b == null) {
            return 0;
        } else {
            return this.b.getMinimumLogicalFontSize();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0037, code lost:
        return r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int getMixedContentMode() {
        /*
            r5 = this;
            monitor-enter(r5)
            boolean r0 = r5.c     // Catch:{ all -> 0x0038 }
            r1 = -1
            if (r0 == 0) goto L_0x0018
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r5.f9108a     // Catch:{ all -> 0x0038 }
            if (r0 == 0) goto L_0x0018
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r5.f9108a     // Catch:{ Throwable -> 0x0012 }
            int r0 = r0.getMixedContentMode()     // Catch:{ Throwable -> 0x0012 }
            monitor-exit(r5)
            return r0
        L_0x0012:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x0038 }
            monitor-exit(r5)
            return r1
        L_0x0018:
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x0038 }
            r2 = 21
            if (r0 >= r2) goto L_0x0020
            monitor-exit(r5)
            return r1
        L_0x0020:
            android.webkit.WebSettings r0 = r5.b     // Catch:{ all -> 0x0038 }
            java.lang.String r2 = "getMixedContentMode"
            r3 = 0
            java.lang.Class[] r4 = new java.lang.Class[r3]     // Catch:{ all -> 0x0038 }
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x0038 }
            java.lang.Object r0 = com.tencent.smtt.utils.q.a((java.lang.Object) r0, (java.lang.String) r2, (java.lang.Class<?>[]) r4, (java.lang.Object[]) r3)     // Catch:{ all -> 0x0038 }
            if (r0 != 0) goto L_0x0030
            goto L_0x0036
        L_0x0030:
            java.lang.Integer r0 = (java.lang.Integer) r0     // Catch:{ all -> 0x0038 }
            int r1 = r0.intValue()     // Catch:{ all -> 0x0038 }
        L_0x0036:
            monitor-exit(r5)
            return r1
        L_0x0038:
            r0 = move-exception
            monitor-exit(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.WebSettings.getMixedContentMode():int");
    }

    public boolean getNavDump() {
        Object a2;
        if (this.c && this.f9108a != null) {
            return this.f9108a.getNavDump();
        }
        if (this.c || this.b == null || (a2 = q.a((Object) this.b, "getNavDump")) == null) {
            return false;
        }
        return ((Boolean) a2).booleanValue();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0043, code lost:
        return null;
     */
    @android.annotation.TargetApi(8)
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.tencent.smtt.sdk.WebSettings.PluginState getPluginState() {
        /*
            r3 = this;
            monitor-enter(r3)
            boolean r0 = r3.c     // Catch:{ all -> 0x0044 }
            if (r0 == 0) goto L_0x0019
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r3.f9108a     // Catch:{ all -> 0x0044 }
            if (r0 == 0) goto L_0x0019
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r3.f9108a     // Catch:{ all -> 0x0044 }
            com.tencent.smtt.export.external.interfaces.IX5WebSettings$PluginState r0 = r0.getPluginState()     // Catch:{ all -> 0x0044 }
            java.lang.String r0 = r0.name()     // Catch:{ all -> 0x0044 }
            com.tencent.smtt.sdk.WebSettings$PluginState r0 = com.tencent.smtt.sdk.WebSettings.PluginState.valueOf(r0)     // Catch:{ all -> 0x0044 }
            monitor-exit(r3)
            return r0
        L_0x0019:
            boolean r0 = r3.c     // Catch:{ all -> 0x0044 }
            r1 = 0
            if (r0 != 0) goto L_0x0042
            android.webkit.WebSettings r0 = r3.b     // Catch:{ all -> 0x0044 }
            if (r0 == 0) goto L_0x0042
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x0044 }
            r2 = 8
            if (r0 < r2) goto L_0x0040
            android.webkit.WebSettings r0 = r3.b     // Catch:{ all -> 0x0044 }
            java.lang.String r2 = "getPluginState"
            java.lang.Object r0 = com.tencent.smtt.utils.q.a((java.lang.Object) r0, (java.lang.String) r2)     // Catch:{ all -> 0x0044 }
            if (r0 != 0) goto L_0x0034
            monitor-exit(r3)
            return r1
        L_0x0034:
            android.webkit.WebSettings$PluginState r0 = (android.webkit.WebSettings.PluginState) r0     // Catch:{ all -> 0x0044 }
            java.lang.String r0 = r0.name()     // Catch:{ all -> 0x0044 }
            com.tencent.smtt.sdk.WebSettings$PluginState r0 = com.tencent.smtt.sdk.WebSettings.PluginState.valueOf(r0)     // Catch:{ all -> 0x0044 }
            monitor-exit(r3)
            return r0
        L_0x0040:
            monitor-exit(r3)
            return r1
        L_0x0042:
            monitor-exit(r3)
            return r1
        L_0x0044:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.WebSettings.getPluginState():com.tencent.smtt.sdk.WebSettings$PluginState");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0032, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0045, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0049, code lost:
        return false;
     */
    @android.annotation.TargetApi(8)
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean getPluginsEnabled() {
        /*
            r3 = this;
            monitor-enter(r3)
            boolean r0 = r3.c     // Catch:{ all -> 0x004a }
            if (r0 == 0) goto L_0x0011
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r3.f9108a     // Catch:{ all -> 0x004a }
            if (r0 == 0) goto L_0x0011
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r3.f9108a     // Catch:{ all -> 0x004a }
            boolean r0 = r0.getPluginsEnabled()     // Catch:{ all -> 0x004a }
            monitor-exit(r3)
            return r0
        L_0x0011:
            boolean r0 = r3.c     // Catch:{ all -> 0x004a }
            r1 = 0
            if (r0 != 0) goto L_0x0048
            android.webkit.WebSettings r0 = r3.b     // Catch:{ all -> 0x004a }
            if (r0 == 0) goto L_0x0048
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x004a }
            r2 = 17
            if (r0 > r2) goto L_0x0033
            android.webkit.WebSettings r0 = r3.b     // Catch:{ all -> 0x004a }
            java.lang.String r2 = "getPluginsEnabled"
            java.lang.Object r0 = com.tencent.smtt.utils.q.a((java.lang.Object) r0, (java.lang.String) r2)     // Catch:{ all -> 0x004a }
            if (r0 != 0) goto L_0x002b
            goto L_0x0031
        L_0x002b:
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x004a }
            boolean r1 = r0.booleanValue()     // Catch:{ all -> 0x004a }
        L_0x0031:
            monitor-exit(r3)
            return r1
        L_0x0033:
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x004a }
            r2 = 18
            if (r0 != r2) goto L_0x0046
            android.webkit.WebSettings r0 = r3.b     // Catch:{ all -> 0x004a }
            android.webkit.WebSettings$PluginState r0 = r0.getPluginState()     // Catch:{ all -> 0x004a }
            android.webkit.WebSettings$PluginState r2 = android.webkit.WebSettings.PluginState.ON     // Catch:{ all -> 0x004a }
            if (r2 != r0) goto L_0x0044
            r1 = 1
        L_0x0044:
            monitor-exit(r3)
            return r1
        L_0x0046:
            monitor-exit(r3)
            return r1
        L_0x0048:
            monitor-exit(r3)
            return r1
        L_0x004a:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.WebSettings.getPluginsEnabled():boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x002e, code lost:
        return r0 == null ? null : (java.lang.String) r0;
     */
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.lang.String getPluginsPath() {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.c     // Catch:{ all -> 0x0037 }
            if (r0 == 0) goto L_0x0011
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r2.f9108a     // Catch:{ all -> 0x0037 }
            if (r0 == 0) goto L_0x0011
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r2.f9108a     // Catch:{ all -> 0x0037 }
            java.lang.String r0 = r0.getPluginsPath()     // Catch:{ all -> 0x0037 }
            monitor-exit(r2)
            return r0
        L_0x0011:
            boolean r0 = r2.c     // Catch:{ all -> 0x0037 }
            if (r0 != 0) goto L_0x0033
            android.webkit.WebSettings r0 = r2.b     // Catch:{ all -> 0x0037 }
            if (r0 == 0) goto L_0x0033
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x0037 }
            r1 = 17
            if (r0 > r1) goto L_0x002f
            android.webkit.WebSettings r0 = r2.b     // Catch:{ all -> 0x0037 }
            java.lang.String r1 = "getPluginsPath"
            java.lang.Object r0 = com.tencent.smtt.utils.q.a((java.lang.Object) r0, (java.lang.String) r1)     // Catch:{ all -> 0x0037 }
            if (r0 != 0) goto L_0x002b
            r0 = 0
            goto L_0x002d
        L_0x002b:
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x0037 }
        L_0x002d:
            monitor-exit(r2)
            return r0
        L_0x002f:
            java.lang.String r0 = ""
            monitor-exit(r2)
            return r0
        L_0x0033:
            java.lang.String r0 = ""
            monitor-exit(r2)
            return r0
        L_0x0037:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.WebSettings.getPluginsPath():java.lang.String");
    }

    public synchronized String getSansSerifFontFamily() {
        if (this.c && this.f9108a != null) {
            return this.f9108a.getSansSerifFontFamily();
        } else if (this.c || this.b == null) {
            return "";
        } else {
            return this.b.getSansSerifFontFamily();
        }
    }

    public boolean getSaveFormData() {
        if (this.c && this.f9108a != null) {
            return this.f9108a.getSaveFormData();
        }
        if (this.c || this.b == null) {
            return false;
        }
        return this.b.getSaveFormData();
    }

    public boolean getSavePassword() {
        if (this.c && this.f9108a != null) {
            return this.f9108a.getSavePassword();
        }
        if (this.c || this.b == null) {
            return false;
        }
        return this.b.getSavePassword();
    }

    public synchronized String getSerifFontFamily() {
        if (this.c && this.f9108a != null) {
            return this.f9108a.getSerifFontFamily();
        } else if (this.c || this.b == null) {
            return "";
        } else {
            return this.b.getSerifFontFamily();
        }
    }

    public synchronized String getStandardFontFamily() {
        if (this.c && this.f9108a != null) {
            return this.f9108a.getStandardFontFamily();
        } else if (this.c || this.b == null) {
            return "";
        } else {
            return this.b.getStandardFontFamily();
        }
    }

    public TextSize getTextSize() {
        String name;
        if (this.c && this.f9108a != null) {
            name = this.f9108a.getTextSize().name();
        } else if (this.c || this.b == null) {
            return null;
        } else {
            name = this.b.getTextSize().name();
        }
        return TextSize.valueOf(name);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        r0 = com.tencent.smtt.utils.q.a((java.lang.Object) r3.b, "getTextZoom");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0032, code lost:
        if (r0 == null) goto L_0x0034;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0035, code lost:
        r1 = ((java.lang.Integer) r0).intValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x003c, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x003e, code lost:
        return 0;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x002a */
    @android.annotation.TargetApi(14)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int getTextZoom() {
        /*
            r3 = this;
            monitor-enter(r3)
            boolean r0 = r3.c     // Catch:{ all -> 0x003f }
            if (r0 == 0) goto L_0x0011
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r3.f9108a     // Catch:{ all -> 0x003f }
            if (r0 == 0) goto L_0x0011
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r3.f9108a     // Catch:{ all -> 0x003f }
            int r0 = r0.getTextZoom()     // Catch:{ all -> 0x003f }
            monitor-exit(r3)
            return r0
        L_0x0011:
            boolean r0 = r3.c     // Catch:{ all -> 0x003f }
            r1 = 0
            if (r0 != 0) goto L_0x003d
            android.webkit.WebSettings r0 = r3.b     // Catch:{ all -> 0x003f }
            if (r0 == 0) goto L_0x003d
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x003f }
            r2 = 14
            if (r0 >= r2) goto L_0x0022
            monitor-exit(r3)
            return r1
        L_0x0022:
            android.webkit.WebSettings r0 = r3.b     // Catch:{ Exception -> 0x002a }
            int r0 = r0.getTextZoom()     // Catch:{ Exception -> 0x002a }
            monitor-exit(r3)
            return r0
        L_0x002a:
            android.webkit.WebSettings r0 = r3.b     // Catch:{ all -> 0x003f }
            java.lang.String r2 = "getTextZoom"
            java.lang.Object r0 = com.tencent.smtt.utils.q.a((java.lang.Object) r0, (java.lang.String) r2)     // Catch:{ all -> 0x003f }
            if (r0 != 0) goto L_0x0035
            goto L_0x003b
        L_0x0035:
            java.lang.Integer r0 = (java.lang.Integer) r0     // Catch:{ all -> 0x003f }
            int r1 = r0.intValue()     // Catch:{ all -> 0x003f }
        L_0x003b:
            monitor-exit(r3)
            return r1
        L_0x003d:
            monitor-exit(r3)
            return r1
        L_0x003f:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.WebSettings.getTextZoom():int");
    }

    @Deprecated
    public boolean getUseWebViewBackgroundForOverscrollBackground() {
        Object a2;
        if (this.c && this.f9108a != null) {
            return this.f9108a.getUseWebViewBackgroundForOverscrollBackground();
        }
        if (this.c || this.b == null || (a2 = q.a((Object) this.b, "getUseWebViewBackgroundForOverscrollBackground")) == null) {
            return false;
        }
        return ((Boolean) a2).booleanValue();
    }

    public synchronized boolean getUseWideViewPort() {
        if (this.c && this.f9108a != null) {
            return this.f9108a.getUseWideViewPort();
        } else if (this.c || this.b == null) {
            return false;
        } else {
            return this.b.getUseWideViewPort();
        }
    }

    @TargetApi(3)
    public String getUserAgentString() {
        return (!this.c || this.f9108a == null) ? (this.c || this.b == null) ? "" : this.b.getUserAgentString() : this.f9108a.getUserAgentString();
    }

    @TargetApi(11)
    public void setAllowContentAccess(boolean z) {
        if (this.c && this.f9108a != null) {
            this.f9108a.setAllowContentAccess(z);
        } else if (!this.c && this.b != null && Build.VERSION.SDK_INT >= 11) {
            q.a((Object) this.b, "setAllowContentAccess", (Class<?>[]) new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
    }

    @TargetApi(3)
    public void setAllowFileAccess(boolean z) {
        if (this.c && this.f9108a != null) {
            this.f9108a.setAllowFileAccess(z);
        } else if (!this.c && this.b != null) {
            this.b.setAllowFileAccess(z);
        }
    }

    @TargetApi(16)
    public void setAllowFileAccessFromFileURLs(boolean z) {
        if (this.c && this.f9108a != null) {
            this.f9108a.setAllowFileAccessFromFileURLs(z);
        } else if (!this.c && this.b != null) {
            q.a((Object) this.b, "setAllowFileAccessFromFileURLs", (Class<?>[]) new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
    }

    @TargetApi(16)
    public void setAllowUniversalAccessFromFileURLs(boolean z) {
        if (this.c && this.f9108a != null) {
            this.f9108a.setAllowUniversalAccessFromFileURLs(z);
        } else if (!this.c && this.b != null) {
            q.a((Object) this.b, "setAllowUniversalAccessFromFileURLs", (Class<?>[]) new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
    }

    @TargetApi(7)
    public void setAppCacheEnabled(boolean z) {
        if (this.c && this.f9108a != null) {
            this.f9108a.setAppCacheEnabled(z);
        } else if (!this.c && this.b != null) {
            this.b.setAppCacheEnabled(z);
        }
    }

    @TargetApi(7)
    public void setAppCacheMaxSize(long j) {
        if (this.c && this.f9108a != null) {
            this.f9108a.setAppCacheMaxSize(j);
        } else if (!this.c && this.b != null) {
            this.b.setAppCacheMaxSize(j);
        }
    }

    @TargetApi(7)
    public void setAppCachePath(String str) {
        if (this.c && this.f9108a != null) {
            this.f9108a.setAppCachePath(str);
        } else if (!this.c && this.b != null) {
            this.b.setAppCachePath(str);
        }
    }

    public void setBlockNetworkImage(boolean z) {
        if (this.c && this.f9108a != null) {
            this.f9108a.setBlockNetworkImage(z);
        } else if (!this.c && this.b != null) {
            this.b.setBlockNetworkImage(z);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0023, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0025, code lost:
        return;
     */
    @android.annotation.TargetApi(8)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void setBlockNetworkLoads(boolean r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.c     // Catch:{ all -> 0x0026 }
            if (r0 == 0) goto L_0x000f
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r2.f9108a     // Catch:{ all -> 0x0026 }
            if (r0 == 0) goto L_0x000f
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r2.f9108a     // Catch:{ all -> 0x0026 }
            r0.setBlockNetworkLoads(r3)     // Catch:{ all -> 0x0026 }
            goto L_0x0022
        L_0x000f:
            boolean r0 = r2.c     // Catch:{ all -> 0x0026 }
            if (r0 != 0) goto L_0x0024
            android.webkit.WebSettings r0 = r2.b     // Catch:{ all -> 0x0026 }
            if (r0 == 0) goto L_0x0024
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x0026 }
            r1 = 8
            if (r0 < r1) goto L_0x0022
            android.webkit.WebSettings r0 = r2.b     // Catch:{ all -> 0x0026 }
            r0.setBlockNetworkLoads(r3)     // Catch:{ all -> 0x0026 }
        L_0x0022:
            monitor-exit(r2)
            return
        L_0x0024:
            monitor-exit(r2)
            return
        L_0x0026:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.WebSettings.setBlockNetworkLoads(boolean):void");
    }

    @TargetApi(3)
    public void setBuiltInZoomControls(boolean z) {
        if (this.c && this.f9108a != null) {
            this.f9108a.setBuiltInZoomControls(z);
        } else if (!this.c && this.b != null) {
            this.b.setBuiltInZoomControls(z);
        }
    }

    public void setCacheMode(int i) {
        if (this.c && this.f9108a != null) {
            this.f9108a.setCacheMode(i);
        } else if (!this.c && this.b != null) {
            this.b.setCacheMode(i);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001d, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void setCursiveFontFamily(java.lang.String r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.c     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x000f
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r1.f9108a     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x000f
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r1.f9108a     // Catch:{ all -> 0x0020 }
            r0.setCursiveFontFamily(r2)     // Catch:{ all -> 0x0020 }
            goto L_0x001c
        L_0x000f:
            boolean r0 = r1.c     // Catch:{ all -> 0x0020 }
            if (r0 != 0) goto L_0x001e
            android.webkit.WebSettings r0 = r1.b     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x001e
            android.webkit.WebSettings r0 = r1.b     // Catch:{ all -> 0x0020 }
            r0.setCursiveFontFamily(r2)     // Catch:{ all -> 0x0020 }
        L_0x001c:
            monitor-exit(r1)
            return
        L_0x001e:
            monitor-exit(r1)
            return
        L_0x0020:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.WebSettings.setCursiveFontFamily(java.lang.String):void");
    }

    @TargetApi(5)
    public void setDatabaseEnabled(boolean z) {
        if (this.c && this.f9108a != null) {
            this.f9108a.setDatabaseEnabled(z);
        } else if (!this.c && this.b != null) {
            this.b.setDatabaseEnabled(z);
        }
    }

    @TargetApi(5)
    @Deprecated
    public void setDatabasePath(String str) {
        if (this.c && this.f9108a != null) {
            this.f9108a.setDatabasePath(str);
        } else if (!this.c && this.b != null) {
            q.a((Object) this.b, "setDatabasePath", (Class<?>[]) new Class[]{String.class}, str);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001d, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void setDefaultFixedFontSize(int r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.c     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x000f
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r1.f9108a     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x000f
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r1.f9108a     // Catch:{ all -> 0x0020 }
            r0.setDefaultFixedFontSize(r2)     // Catch:{ all -> 0x0020 }
            goto L_0x001c
        L_0x000f:
            boolean r0 = r1.c     // Catch:{ all -> 0x0020 }
            if (r0 != 0) goto L_0x001e
            android.webkit.WebSettings r0 = r1.b     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x001e
            android.webkit.WebSettings r0 = r1.b     // Catch:{ all -> 0x0020 }
            r0.setDefaultFixedFontSize(r2)     // Catch:{ all -> 0x0020 }
        L_0x001c:
            monitor-exit(r1)
            return
        L_0x001e:
            monitor-exit(r1)
            return
        L_0x0020:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.WebSettings.setDefaultFixedFontSize(int):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001d, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void setDefaultFontSize(int r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.c     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x000f
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r1.f9108a     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x000f
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r1.f9108a     // Catch:{ all -> 0x0020 }
            r0.setDefaultFontSize(r2)     // Catch:{ all -> 0x0020 }
            goto L_0x001c
        L_0x000f:
            boolean r0 = r1.c     // Catch:{ all -> 0x0020 }
            if (r0 != 0) goto L_0x001e
            android.webkit.WebSettings r0 = r1.b     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x001e
            android.webkit.WebSettings r0 = r1.b     // Catch:{ all -> 0x0020 }
            r0.setDefaultFontSize(r2)     // Catch:{ all -> 0x0020 }
        L_0x001c:
            monitor-exit(r1)
            return
        L_0x001e:
            monitor-exit(r1)
            return
        L_0x0020:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.WebSettings.setDefaultFontSize(int):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001d, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void setDefaultTextEncodingName(java.lang.String r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.c     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x000f
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r1.f9108a     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x000f
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r1.f9108a     // Catch:{ all -> 0x0020 }
            r0.setDefaultTextEncodingName(r2)     // Catch:{ all -> 0x0020 }
            goto L_0x001c
        L_0x000f:
            boolean r0 = r1.c     // Catch:{ all -> 0x0020 }
            if (r0 != 0) goto L_0x001e
            android.webkit.WebSettings r0 = r1.b     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x001e
            android.webkit.WebSettings r0 = r1.b     // Catch:{ all -> 0x0020 }
            r0.setDefaultTextEncodingName(r2)     // Catch:{ all -> 0x0020 }
        L_0x001c:
            monitor-exit(r1)
            return
        L_0x001e:
            monitor-exit(r1)
            return
        L_0x0020:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.WebSettings.setDefaultTextEncodingName(java.lang.String):void");
    }

    @TargetApi(7)
    public void setDefaultZoom(ZoomDensity zoomDensity) {
        if (this.c && this.f9108a != null) {
            this.f9108a.setDefaultZoom(IX5WebSettings.ZoomDensity.valueOf(zoomDensity.name()));
        } else if (!this.c && this.b != null) {
            this.b.setDefaultZoom(WebSettings.ZoomDensity.valueOf(zoomDensity.name()));
        }
    }

    @TargetApi(11)
    public void setDisplayZoomControls(boolean z) {
        if (this.c && this.f9108a != null) {
            this.f9108a.setDisplayZoomControls(z);
        } else if (!this.c && this.b != null && Build.VERSION.SDK_INT >= 11) {
            q.a((Object) this.b, "setDisplayZoomControls", (Class<?>[]) new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
    }

    @TargetApi(7)
    public void setDomStorageEnabled(boolean z) {
        if (this.c && this.f9108a != null) {
            this.f9108a.setDomStorageEnabled(z);
        } else if (!this.c && this.b != null) {
            this.b.setDomStorageEnabled(z);
        }
    }

    @TargetApi(11)
    @Deprecated
    public void setEnableSmoothTransition(boolean z) {
        if (this.c && this.f9108a != null) {
            this.f9108a.setEnableSmoothTransition(z);
        } else if (!this.c && this.b != null && Build.VERSION.SDK_INT >= 11) {
            q.a((Object) this.b, "setEnableSmoothTransition", (Class<?>[]) new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001d, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void setFantasyFontFamily(java.lang.String r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.c     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x000f
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r1.f9108a     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x000f
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r1.f9108a     // Catch:{ all -> 0x0020 }
            r0.setFantasyFontFamily(r2)     // Catch:{ all -> 0x0020 }
            goto L_0x001c
        L_0x000f:
            boolean r0 = r1.c     // Catch:{ all -> 0x0020 }
            if (r0 != 0) goto L_0x001e
            android.webkit.WebSettings r0 = r1.b     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x001e
            android.webkit.WebSettings r0 = r1.b     // Catch:{ all -> 0x0020 }
            r0.setFantasyFontFamily(r2)     // Catch:{ all -> 0x0020 }
        L_0x001c:
            monitor-exit(r1)
            return
        L_0x001e:
            monitor-exit(r1)
            return
        L_0x0020:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.WebSettings.setFantasyFontFamily(java.lang.String):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001d, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void setFixedFontFamily(java.lang.String r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.c     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x000f
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r1.f9108a     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x000f
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r1.f9108a     // Catch:{ all -> 0x0020 }
            r0.setFixedFontFamily(r2)     // Catch:{ all -> 0x0020 }
            goto L_0x001c
        L_0x000f:
            boolean r0 = r1.c     // Catch:{ all -> 0x0020 }
            if (r0 != 0) goto L_0x001e
            android.webkit.WebSettings r0 = r1.b     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x001e
            android.webkit.WebSettings r0 = r1.b     // Catch:{ all -> 0x0020 }
            r0.setFixedFontFamily(r2)     // Catch:{ all -> 0x0020 }
        L_0x001c:
            monitor-exit(r1)
            return
        L_0x001e:
            monitor-exit(r1)
            return
        L_0x0020:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.WebSettings.setFixedFontFamily(java.lang.String):void");
    }

    @TargetApi(5)
    public void setGeolocationDatabasePath(String str) {
        if (this.c && this.f9108a != null) {
            this.f9108a.setGeolocationDatabasePath(str);
        } else if (!this.c && this.b != null) {
            this.b.setGeolocationDatabasePath(str);
        }
    }

    @TargetApi(5)
    public void setGeolocationEnabled(boolean z) {
        if (this.c && this.f9108a != null) {
            this.f9108a.setGeolocationEnabled(z);
        } else if (!this.c && this.b != null) {
            this.b.setGeolocationEnabled(z);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001d, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void setJavaScriptCanOpenWindowsAutomatically(boolean r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.c     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x000f
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r1.f9108a     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x000f
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r1.f9108a     // Catch:{ all -> 0x0020 }
            r0.setJavaScriptCanOpenWindowsAutomatically(r2)     // Catch:{ all -> 0x0020 }
            goto L_0x001c
        L_0x000f:
            boolean r0 = r1.c     // Catch:{ all -> 0x0020 }
            if (r0 != 0) goto L_0x001e
            android.webkit.WebSettings r0 = r1.b     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x001e
            android.webkit.WebSettings r0 = r1.b     // Catch:{ all -> 0x0020 }
            r0.setJavaScriptCanOpenWindowsAutomatically(r2)     // Catch:{ all -> 0x0020 }
        L_0x001c:
            monitor-exit(r1)
            return
        L_0x001e:
            monitor-exit(r1)
            return
        L_0x0020:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.WebSettings.setJavaScriptCanOpenWindowsAutomatically(boolean):void");
    }

    @Deprecated
    public void setJavaScriptEnabled(boolean z) {
        try {
            if (this.c && this.f9108a != null) {
                this.f9108a.setJavaScriptEnabled(z);
            } else if (!this.c && this.b != null) {
                this.b.setJavaScriptEnabled(z);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void setLayoutAlgorithm(LayoutAlgorithm layoutAlgorithm) {
        if (this.c && this.f9108a != null) {
            this.f9108a.setLayoutAlgorithm(IX5WebSettings.LayoutAlgorithm.valueOf(layoutAlgorithm.name()));
        } else if (!this.c && this.b != null) {
            this.b.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.valueOf(layoutAlgorithm.name()));
        }
    }

    public void setLightTouchEnabled(boolean z) {
        if (this.c && this.f9108a != null) {
            this.f9108a.setLightTouchEnabled(z);
        } else if (!this.c && this.b != null) {
            this.b.setLightTouchEnabled(z);
        }
    }

    @TargetApi(7)
    public void setLoadWithOverviewMode(boolean z) {
        if (this.c && this.f9108a != null) {
            this.f9108a.setLoadWithOverviewMode(z);
        } else if (!this.c && this.b != null) {
            this.b.setLoadWithOverviewMode(z);
        }
    }

    public void setLoadsImagesAutomatically(boolean z) {
        if (this.c && this.f9108a != null) {
            this.f9108a.setLoadsImagesAutomatically(z);
        } else if (!this.c && this.b != null) {
            this.b.setLoadsImagesAutomatically(z);
        }
    }

    @TargetApi(17)
    public void setMediaPlaybackRequiresUserGesture(boolean z) {
        if (this.c && this.f9108a != null) {
            this.f9108a.setMediaPlaybackRequiresUserGesture(z);
        } else if (!this.c && this.b != null && Build.VERSION.SDK_INT >= 17) {
            q.a((Object) this.b, "setMediaPlaybackRequiresUserGesture", (Class<?>[]) new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001d, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void setMinimumFontSize(int r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.c     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x000f
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r1.f9108a     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x000f
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r1.f9108a     // Catch:{ all -> 0x0020 }
            r0.setMinimumFontSize(r2)     // Catch:{ all -> 0x0020 }
            goto L_0x001c
        L_0x000f:
            boolean r0 = r1.c     // Catch:{ all -> 0x0020 }
            if (r0 != 0) goto L_0x001e
            android.webkit.WebSettings r0 = r1.b     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x001e
            android.webkit.WebSettings r0 = r1.b     // Catch:{ all -> 0x0020 }
            r0.setMinimumFontSize(r2)     // Catch:{ all -> 0x0020 }
        L_0x001c:
            monitor-exit(r1)
            return
        L_0x001e:
            monitor-exit(r1)
            return
        L_0x0020:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.WebSettings.setMinimumFontSize(int):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001d, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void setMinimumLogicalFontSize(int r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.c     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x000f
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r1.f9108a     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x000f
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r1.f9108a     // Catch:{ all -> 0x0020 }
            r0.setMinimumLogicalFontSize(r2)     // Catch:{ all -> 0x0020 }
            goto L_0x001c
        L_0x000f:
            boolean r0 = r1.c     // Catch:{ all -> 0x0020 }
            if (r0 != 0) goto L_0x001e
            android.webkit.WebSettings r0 = r1.b     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x001e
            android.webkit.WebSettings r0 = r1.b     // Catch:{ all -> 0x0020 }
            r0.setMinimumLogicalFontSize(r2)     // Catch:{ all -> 0x0020 }
        L_0x001c:
            monitor-exit(r1)
            return
        L_0x001e:
            monitor-exit(r1)
            return
        L_0x0020:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.WebSettings.setMinimumLogicalFontSize(int):void");
    }

    @TargetApi(21)
    public void setMixedContentMode(int i) {
        if ((!this.c || this.f9108a == null) && !this.c && this.b != null && Build.VERSION.SDK_INT >= 21) {
            q.a((Object) this.b, "setMixedContentMode", (Class<?>[]) new Class[]{Integer.TYPE}, Integer.valueOf(i));
        }
    }

    public void setNavDump(boolean z) {
        if (this.c && this.f9108a != null) {
            this.f9108a.setNavDump(z);
        } else if (!this.c && this.b != null) {
            q.a((Object) this.b, "setNavDump", (Class<?>[]) new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
    }

    public void setNeedInitialFocus(boolean z) {
        if (this.c && this.f9108a != null) {
            this.f9108a.setNeedInitialFocus(z);
        } else if (!this.c && this.b != null) {
            this.b.setNeedInitialFocus(z);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0041, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0043, code lost:
        return;
     */
    @android.annotation.TargetApi(8)
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void setPluginState(com.tencent.smtt.sdk.WebSettings.PluginState r7) {
        /*
            r6 = this;
            monitor-enter(r6)
            boolean r0 = r6.c     // Catch:{ all -> 0x0044 }
            if (r0 == 0) goto L_0x0017
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r6.f9108a     // Catch:{ all -> 0x0044 }
            if (r0 == 0) goto L_0x0017
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r6.f9108a     // Catch:{ all -> 0x0044 }
            java.lang.String r7 = r7.name()     // Catch:{ all -> 0x0044 }
            com.tencent.smtt.export.external.interfaces.IX5WebSettings$PluginState r7 = com.tencent.smtt.export.external.interfaces.IX5WebSettings.PluginState.valueOf(r7)     // Catch:{ all -> 0x0044 }
            r0.setPluginState(r7)     // Catch:{ all -> 0x0044 }
            goto L_0x0040
        L_0x0017:
            boolean r0 = r6.c     // Catch:{ all -> 0x0044 }
            if (r0 != 0) goto L_0x0042
            android.webkit.WebSettings r0 = r6.b     // Catch:{ all -> 0x0044 }
            if (r0 == 0) goto L_0x0042
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x0044 }
            r1 = 8
            if (r0 < r1) goto L_0x0040
            java.lang.String r7 = r7.name()     // Catch:{ all -> 0x0044 }
            android.webkit.WebSettings$PluginState r7 = android.webkit.WebSettings.PluginState.valueOf(r7)     // Catch:{ all -> 0x0044 }
            android.webkit.WebSettings r0 = r6.b     // Catch:{ all -> 0x0044 }
            java.lang.String r1 = "setPluginState"
            r2 = 1
            java.lang.Class[] r3 = new java.lang.Class[r2]     // Catch:{ all -> 0x0044 }
            java.lang.Class<android.webkit.WebSettings$PluginState> r4 = android.webkit.WebSettings.PluginState.class
            r5 = 0
            r3[r5] = r4     // Catch:{ all -> 0x0044 }
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x0044 }
            r2[r5] = r7     // Catch:{ all -> 0x0044 }
            com.tencent.smtt.utils.q.a((java.lang.Object) r0, (java.lang.String) r1, (java.lang.Class<?>[]) r3, (java.lang.Object[]) r2)     // Catch:{ all -> 0x0044 }
        L_0x0040:
            monitor-exit(r6)
            return
        L_0x0042:
            monitor-exit(r6)
            return
        L_0x0044:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.WebSettings.setPluginState(com.tencent.smtt.sdk.WebSettings$PluginState):void");
    }

    @Deprecated
    public void setPluginsEnabled(boolean z) {
        if (this.c && this.f9108a != null) {
            this.f9108a.setPluginsEnabled(z);
        } else if (!this.c && this.b != null) {
            q.a((Object) this.b, "setPluginsEnabled", (Class<?>[]) new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002b, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002d, code lost:
        return;
     */
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void setPluginsPath(java.lang.String r7) {
        /*
            r6 = this;
            monitor-enter(r6)
            boolean r0 = r6.c     // Catch:{ all -> 0x002e }
            if (r0 == 0) goto L_0x000f
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r6.f9108a     // Catch:{ all -> 0x002e }
            if (r0 == 0) goto L_0x000f
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r6.f9108a     // Catch:{ all -> 0x002e }
            r0.setPluginsPath(r7)     // Catch:{ all -> 0x002e }
            goto L_0x002a
        L_0x000f:
            boolean r0 = r6.c     // Catch:{ all -> 0x002e }
            if (r0 != 0) goto L_0x002c
            android.webkit.WebSettings r0 = r6.b     // Catch:{ all -> 0x002e }
            if (r0 == 0) goto L_0x002c
            android.webkit.WebSettings r0 = r6.b     // Catch:{ all -> 0x002e }
            java.lang.String r1 = "setPluginsPath"
            r2 = 1
            java.lang.Class[] r3 = new java.lang.Class[r2]     // Catch:{ all -> 0x002e }
            java.lang.Class<java.lang.String> r4 = java.lang.String.class
            r5 = 0
            r3[r5] = r4     // Catch:{ all -> 0x002e }
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x002e }
            r2[r5] = r7     // Catch:{ all -> 0x002e }
            com.tencent.smtt.utils.q.a((java.lang.Object) r0, (java.lang.String) r1, (java.lang.Class<?>[]) r3, (java.lang.Object[]) r2)     // Catch:{ all -> 0x002e }
        L_0x002a:
            monitor-exit(r6)
            return
        L_0x002c:
            monitor-exit(r6)
            return
        L_0x002e:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.WebSettings.setPluginsPath(java.lang.String):void");
    }

    public void setRenderPriority(RenderPriority renderPriority) {
        if (this.c && this.f9108a != null) {
            this.f9108a.setRenderPriority(IX5WebSettings.RenderPriority.valueOf(renderPriority.name()));
        } else if (!this.c && this.b != null) {
            this.b.setRenderPriority(WebSettings.RenderPriority.valueOf(renderPriority.name()));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001d, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void setSansSerifFontFamily(java.lang.String r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.c     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x000f
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r1.f9108a     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x000f
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r1.f9108a     // Catch:{ all -> 0x0020 }
            r0.setSansSerifFontFamily(r2)     // Catch:{ all -> 0x0020 }
            goto L_0x001c
        L_0x000f:
            boolean r0 = r1.c     // Catch:{ all -> 0x0020 }
            if (r0 != 0) goto L_0x001e
            android.webkit.WebSettings r0 = r1.b     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x001e
            android.webkit.WebSettings r0 = r1.b     // Catch:{ all -> 0x0020 }
            r0.setSansSerifFontFamily(r2)     // Catch:{ all -> 0x0020 }
        L_0x001c:
            monitor-exit(r1)
            return
        L_0x001e:
            monitor-exit(r1)
            return
        L_0x0020:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.WebSettings.setSansSerifFontFamily(java.lang.String):void");
    }

    public void setSaveFormData(boolean z) {
        if (this.c && this.f9108a != null) {
            this.f9108a.setSaveFormData(z);
        } else if (!this.c && this.b != null) {
            this.b.setSaveFormData(z);
        }
    }

    public void setSavePassword(boolean z) {
        if (this.c && this.f9108a != null) {
            this.f9108a.setSavePassword(z);
        } else if (!this.c && this.b != null) {
            this.b.setSavePassword(z);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001d, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void setSerifFontFamily(java.lang.String r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.c     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x000f
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r1.f9108a     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x000f
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r1.f9108a     // Catch:{ all -> 0x0020 }
            r0.setSerifFontFamily(r2)     // Catch:{ all -> 0x0020 }
            goto L_0x001c
        L_0x000f:
            boolean r0 = r1.c     // Catch:{ all -> 0x0020 }
            if (r0 != 0) goto L_0x001e
            android.webkit.WebSettings r0 = r1.b     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x001e
            android.webkit.WebSettings r0 = r1.b     // Catch:{ all -> 0x0020 }
            r0.setSerifFontFamily(r2)     // Catch:{ all -> 0x0020 }
        L_0x001c:
            monitor-exit(r1)
            return
        L_0x001e:
            monitor-exit(r1)
            return
        L_0x0020:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.WebSettings.setSerifFontFamily(java.lang.String):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001d, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void setStandardFontFamily(java.lang.String r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.c     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x000f
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r1.f9108a     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x000f
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r1.f9108a     // Catch:{ all -> 0x0020 }
            r0.setStandardFontFamily(r2)     // Catch:{ all -> 0x0020 }
            goto L_0x001c
        L_0x000f:
            boolean r0 = r1.c     // Catch:{ all -> 0x0020 }
            if (r0 != 0) goto L_0x001e
            android.webkit.WebSettings r0 = r1.b     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x001e
            android.webkit.WebSettings r0 = r1.b     // Catch:{ all -> 0x0020 }
            r0.setStandardFontFamily(r2)     // Catch:{ all -> 0x0020 }
        L_0x001c:
            monitor-exit(r1)
            return
        L_0x001e:
            monitor-exit(r1)
            return
        L_0x0020:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.WebSettings.setStandardFontFamily(java.lang.String):void");
    }

    public void setSupportMultipleWindows(boolean z) {
        if (this.c && this.f9108a != null) {
            this.f9108a.setSupportMultipleWindows(z);
        } else if (!this.c && this.b != null) {
            this.b.setSupportMultipleWindows(z);
        }
    }

    public void setSupportZoom(boolean z) {
        if (this.c && this.f9108a != null) {
            this.f9108a.setSupportZoom(z);
        } else if (!this.c && this.b != null) {
            this.b.setSupportZoom(z);
        }
    }

    public void setTextSize(TextSize textSize) {
        if (this.c && this.f9108a != null) {
            this.f9108a.setTextSize(IX5WebSettings.TextSize.valueOf(textSize.name()));
        } else if (!this.c && this.b != null) {
            this.b.setTextSize(WebSettings.TextSize.valueOf(textSize.name()));
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:16|17|18|19) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:18:0x0025 */
    @android.annotation.TargetApi(14)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void setTextZoom(int r7) {
        /*
            r6 = this;
            monitor-enter(r6)
            boolean r0 = r6.c     // Catch:{ all -> 0x003e }
            if (r0 == 0) goto L_0x000f
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r6.f9108a     // Catch:{ all -> 0x003e }
            if (r0 == 0) goto L_0x000f
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r6.f9108a     // Catch:{ all -> 0x003e }
            r0.setTextZoom(r7)     // Catch:{ all -> 0x003e }
            goto L_0x003c
        L_0x000f:
            boolean r0 = r6.c     // Catch:{ all -> 0x003e }
            if (r0 != 0) goto L_0x003c
            android.webkit.WebSettings r0 = r6.b     // Catch:{ all -> 0x003e }
            if (r0 == 0) goto L_0x003c
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x003e }
            r1 = 14
            if (r0 >= r1) goto L_0x001f
            monitor-exit(r6)
            return
        L_0x001f:
            android.webkit.WebSettings r0 = r6.b     // Catch:{ Exception -> 0x0025 }
            r0.setTextZoom(r7)     // Catch:{ Exception -> 0x0025 }
            goto L_0x003c
        L_0x0025:
            android.webkit.WebSettings r0 = r6.b     // Catch:{ all -> 0x003e }
            java.lang.String r1 = "setTextZoom"
            r2 = 1
            java.lang.Class[] r3 = new java.lang.Class[r2]     // Catch:{ all -> 0x003e }
            java.lang.Class r4 = java.lang.Integer.TYPE     // Catch:{ all -> 0x003e }
            r5 = 0
            r3[r5] = r4     // Catch:{ all -> 0x003e }
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x003e }
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch:{ all -> 0x003e }
            r2[r5] = r7     // Catch:{ all -> 0x003e }
            com.tencent.smtt.utils.q.a((java.lang.Object) r0, (java.lang.String) r1, (java.lang.Class<?>[]) r3, (java.lang.Object[]) r2)     // Catch:{ all -> 0x003e }
        L_0x003c:
            monitor-exit(r6)
            return
        L_0x003e:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.WebSettings.setTextZoom(int):void");
    }

    @Deprecated
    public void setUseWebViewBackgroundForOverscrollBackground(boolean z) {
        if (this.c && this.f9108a != null) {
            this.f9108a.setUseWebViewBackgroundForOverscrollBackground(z);
        } else if (!this.c && this.b != null) {
            q.a((Object) this.b, "setUseWebViewBackgroundForOverscrollBackground", (Class<?>[]) new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
    }

    public void setUseWideViewPort(boolean z) {
        if (this.c && this.f9108a != null) {
            this.f9108a.setUseWideViewPort(z);
        } else if (!this.c && this.b != null) {
            this.b.setUseWideViewPort(z);
        }
    }

    public void setUserAgent(String str) {
        if (this.c && this.f9108a != null) {
            this.f9108a.setUserAgent(str);
        } else if (!this.c && this.b != null) {
            this.b.setUserAgentString(str);
        }
    }

    @TargetApi(3)
    public void setUserAgentString(String str) {
        if (this.c && this.f9108a != null) {
            this.f9108a.setUserAgentString(str);
        } else if (!this.c && this.b != null) {
            this.b.setUserAgentString(str);
        }
    }

    public synchronized boolean supportMultipleWindows() {
        if (this.c && this.f9108a != null) {
            return this.f9108a.supportMultipleWindows();
        } else if (this.c || this.b == null) {
            return false;
        } else {
            return this.b.supportMultipleWindows();
        }
    }

    public boolean supportZoom() {
        if (this.c && this.f9108a != null) {
            return this.f9108a.supportZoom();
        }
        if (this.c || this.b == null) {
            return false;
        }
        return this.b.supportZoom();
    }
}
