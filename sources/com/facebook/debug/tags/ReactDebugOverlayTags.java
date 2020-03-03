package com.facebook.debug.tags;

import com.facebook.debug.debugoverlay.model.DebugOverlayTag;
import com.facebook.react.fabric.FabricUIManager;
import com.libra.Color;

public class ReactDebugOverlayTags {
    public static final DebugOverlayTag BRIDGE_CALLS = new DebugOverlayTag("Bridge Calls", "JS to Java calls (warning: this is spammy)", Color.k);
    public static final DebugOverlayTag FABRIC_RECONCILER = new DebugOverlayTag("FabricReconciler", "Reconciler for Fabric", Color.j);
    public static final DebugOverlayTag FABRIC_UI_MANAGER = new DebugOverlayTag(FabricUIManager.TAG, "Fabric UI Manager View Operations", Color.j);
    public static final DebugOverlayTag NATIVE_MODULE = new DebugOverlayTag("Native Module", "Native Module init", android.graphics.Color.rgb(128, 0, 128));
    public static final DebugOverlayTag NAVIGATION = new DebugOverlayTag("Navigation", "Tag for navigation", android.graphics.Color.rgb(156, 39, 176));
    public static final DebugOverlayTag PERFORMANCE = new DebugOverlayTag("Performance", "Markers for Performance", Color.g);
    public static final DebugOverlayTag RELAY = new DebugOverlayTag("Relay", "including prefetching", android.graphics.Color.rgb(255, 153, 0));
    public static final DebugOverlayTag RN_CORE = new DebugOverlayTag("RN Core", "Tag for React Native Core", -16777216);
    public static final DebugOverlayTag UI_MANAGER = new DebugOverlayTag("UI Manager", "UI Manager View Operations (requires restart\nwarning: this is spammy)", Color.j);
}
