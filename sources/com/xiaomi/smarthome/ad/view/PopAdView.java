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
import com.xiaomi.smarthome.ad.api.Advertisement;
import com.xiaomi.smarthome.library.common.util.CommonUtils;

class PopAdView extends PopupWindow {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public View.OnClickListener f13659a;

    PopAdView(Context context) {
        super(LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.view_ad_pop, (ViewGroup) null), -2, -2);
        setTouchable(true);
        setFocusable(false);
        setOutsideTouchable(false);
    }

    public void a(final Advertisement advertisement) {
        ((TextView) getContentView().findViewById(R.id.title)).setText(advertisement.b());
        getContentView().findViewById(R.id.pop_ad_close).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PluginAdUtil.b(advertisement);
                PopAdView.this.dismiss();
            }
        });
        if (PluginServiceManager.a().b() != null) {
            try {
                PluginServiceManager.a().b().loadBitmap(advertisement.e(), new IPluginCallback3.Stub() {
                    public void onFailed() throws RemoteException {
                    }

                    public void onSuccess(final Bitmap bitmap) throws RemoteException {
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            public void run() {
                                ((ImageView) PopAdView.this.getContentView().findViewById(R.id.pop_ad_pic)).setImageBitmap(CommonUtils.a(bitmap, (int) CommonUtils.a(PopAdView.this.getContentView().getContext(), 4.0f), false, false, false, false));
                            }
                        });
                    }
                });
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        getContentView().findViewById(R.id.jump_to_product).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (PopAdView.this.f13659a != null) {
                    PopAdView.this.f13659a.onClick(view);
                }
            }
        });
    }

    public void a(View.OnClickListener onClickListener) {
        this.f13659a = onClickListener;
    }
}
