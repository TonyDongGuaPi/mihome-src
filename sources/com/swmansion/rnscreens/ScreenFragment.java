package com.swmansion.rnscreens;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.UIManagerModule;

public class ScreenFragment extends Fragment {

    /* renamed from: a  reason: collision with root package name */
    protected Screen f8951a;

    public ScreenFragment() {
        throw new IllegalStateException("Screen fragments should never be restored");
    }

    @SuppressLint({"ValidFragment"})
    public ScreenFragment(Screen screen) {
        this.f8951a = screen;
    }

    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return this.f8951a;
    }

    public Screen a() {
        return this.f8951a;
    }

    public void onDestroy() {
        super.onDestroy();
        ((UIManagerModule) ((ReactContext) this.f8951a.getContext()).getNativeModule(UIManagerModule.class)).getEventDispatcher().dispatchEvent(new ScreenDismissedEvent(this.f8951a.getId()));
    }
}
