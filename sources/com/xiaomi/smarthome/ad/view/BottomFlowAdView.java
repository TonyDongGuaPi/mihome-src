package com.xiaomi.smarthome.ad.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.xiaomi.router.miio.miioplugin.IPluginCallback3;
import com.xiaomi.router.miio.miioplugin.PluginServiceManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.ad.api.AdPosition;
import com.xiaomi.smarthome.ad.api.Advertisement;
import com.xiaomi.smarthome.frame.FrameManager;

public class BottomFlowAdView extends PopupWindow {
    public BottomFlowAdView(Context context, AdPosition adPosition) {
        super(LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.view_ad_bottom_flow, (ViewGroup) null), -1, -2);
        final Advertisement a2 = PluginAdUtil.a(adPosition);
        setTouchable(true);
        setFocusable(false);
        setOutsideTouchable(false);
        ((TextView) getContentView().findViewById(R.id.title)).setText(a2.l());
        ((TextView) getContentView().findViewById(R.id.message)).setText(a2.m());
        a(a2, (ImageView) getContentView().findViewById(R.id.image));
        getContentView().findViewById(R.id.close_image).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PluginAdUtil.b(a2);
                BottomFlowAdView.this.dismiss();
            }
        });
        getContentView().setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FrameManager.b().k().loadWebView(a2.f(), "");
                PluginAdUtil.a();
                BottomFlowAdView.this.dismiss();
            }
        });
        PluginAdUtil.a(a2);
    }

    private void a(Advertisement advertisement, final ImageView imageView) {
        if (PluginServiceManager.a().b() != null) {
            try {
                PluginServiceManager.a().b().loadBitmap(advertisement.e(), new IPluginCallback3.Stub() {
                    public void onFailed() throws RemoteException {
                    }

                    public void onSuccess(final Bitmap bitmap) throws RemoteException {
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            public void run() {
                                imageView.setImageBitmap(bitmap);
                            }
                        });
                    }
                });
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
