package com.xiaomi.smarthome.newui.card;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.mijia.camera.CameraNativePluginManager;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.renderer.PluginDownloadingRecord;
import com.xiaomi.smarthome.fastvideo.VideoView;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.widget.PieProgressBar;
import com.xiaomi.smarthome.listcamera.CameraFrameManager;
import com.xiaomi.smarthome.miotcard.R;
import com.xiaomi.smarthome.newui.card.profile.ProfileCardItem;
import com.xiaomi.smarthome.newui.card.profile.ProfileCardType;
import com.xiaomi.smarthome.newui.widget.DeviceDownloadItemViewWrapper;
import java.util.HashMap;
import java.util.Map;

public class CameraCardItem extends ProfileCardItem {

    /* renamed from: a  reason: collision with root package name */
    protected static Map<Device, PluginDownloadingRecord> f20461a = new HashMap();
    private PieProgressBar I;
    private ImageView J;
    private ImageView S;
    private ImageView T;
    private FrameLayout U;
    private VideoView V;
    private TextView W;
    private Device X;
    private boolean Y = false;
    private int Z;

    public void a(String str, Object obj) {
    }

    public void e() {
    }

    public void f() {
    }

    public void onUpdateViewScale(@NonNull View view, int i, float f) {
    }

    public CameraCardItem(ProfileCardType profileCardType) {
        super(profileCardType);
    }

    public void a(ViewGroup viewGroup, int i, int i2) {
        super.a(viewGroup, i, i2);
        final Device k = k();
        FrameLayout frameLayout = (FrameLayout) a(viewGroup, R.layout.card_item_camera);
        this.J = (ImageView) frameLayout.findViewById(R.id.play_button);
        this.T = (ImageView) frameLayout.findViewById(R.id.video_view_cover);
        this.S = (ImageView) frameLayout.findViewById(R.id.camera_loading);
        this.I = (PieProgressBar) frameLayout.findViewById(R.id.download_progress);
        this.U = (FrameLayout) frameLayout.findViewById(R.id.video_view_container);
        this.W = (TextView) frameLayout.findViewById(R.id.progress_text);
        PieProgressBar pieProgressBar = this.I;
        ImageView imageView = this.J;
        ImageView imageView2 = this.S;
        ImageView imageView3 = this.T;
        TextView textView = this.W;
        this.X = k;
        if (pieProgressBar != null && imageView2 != null && imageView3 != null && textView != null) {
            pieProgressBar.setPercentView(textView);
            imageView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    PluginRecord d;
                    if (CameraCardItem.this.j() || (d = CoreApi.a().d(k.model)) == null) {
                        return;
                    }
                    if (CameraNativePluginManager.a().a(d.o())) {
                        CameraCardItem.this.q();
                    } else if (!d.k()) {
                        CameraCardItem.this.a(k);
                    } else {
                        CameraCardItem.this.q();
                    }
                }
            });
            if (!k.isOnline) {
                imageView.setVisibility(8);
                imageView2.setVisibility(8);
                pieProgressBar.setVisibility(8);
            }
            if (CameraFrameManager.a().a(k.did) == null) {
                CameraFrameManager.a().a(k, false);
            }
            CameraFrameManager.a().a(k, imageView3, imageView2);
            if (!a()) {
                pieProgressBar.setVisibility(8);
                textView.setVisibility(8);
                if (CameraFrameManager.a().b(k)) {
                    q();
                    imageView3.setVisibility(8);
                    imageView.setVisibility(8);
                } else if (CameraFrameManager.a().a(k)) {
                    q();
                    imageView3.setVisibility(8);
                    imageView.setVisibility(8);
                } else {
                    imageView2.setVisibility(8);
                }
            } else {
                imageView3.setVisibility(8);
                imageView.setVisibility(8);
                imageView2.setVisibility(8);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a() {
        PluginDownloadingRecord pluginDownloadingRecord;
        PieProgressBar pieProgressBar = this.I;
        TextView textView = this.W;
        PluginRecord d = CoreApi.a().d(this.X.model);
        if (d == null || d.k() || d.l() || (pluginDownloadingRecord = f20461a.get(this.X)) == null || pluginDownloadingRecord.b != PluginDownloadingRecord.Status.DOWNLOADING) {
            return false;
        }
        if (textView != null) {
            textView.setVisibility(0);
        }
        if (pieProgressBar == null) {
            return true;
        }
        pieProgressBar.setVisibility(0);
        pieProgressBar.setPercent(pluginDownloadingRecord.c * 100.0f);
        return true;
    }

    /* access modifiers changed from: package-private */
    public void b() {
        this.V = new VideoView(CommonApplication.getAppContext());
        VideoView videoView = this.V;
        final ImageView imageView = this.J;
        FrameLayout frameLayout = this.U;
        if (videoView != null && frameLayout != null) {
            videoView.setVideoFrameSize(DisplayUtils.a(274.55f), DisplayUtils.a(155.85f), false);
            videoView.setVideoViewListener(new VideoView.IVideoViewListener() {
                public void a() {
                    if (!CameraCardItem.this.j()) {
                        CameraCardItem.this.d();
                        if (imageView != null) {
                            imageView.setVisibility(0);
                        }
                    }
                }
            });
            frameLayout.addView(videoView);
        }
    }

    /* access modifiers changed from: private */
    public void q() {
        if (!j()) {
            if (this.X == null || this.X.isSetPinCode == 0 || this.C == null || ((CardRender_1_item) this.C).a(this.X.did, this)) {
                if (this.V == null) {
                    b();
                }
                VideoView videoView = this.V;
                ImageView imageView = this.S;
                ImageView imageView2 = this.J;
                if (videoView != null && imageView != null && imageView2 != null) {
                    CameraFrameManager.a().a(this.X, videoView);
                    CameraFrameManager.a().e(this.X);
                    CameraFrameManager.a().b(CommonApplication.getAppContext(), this.X, imageView);
                    imageView.setVisibility(0);
                    imageView2.setVisibility(8);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void c() {
        FrameLayout frameLayout = this.U;
        if (frameLayout != null) {
            frameLayout.removeAllViews();
        }
        this.V = null;
    }

    public void d() {
        if (!j()) {
            VideoView videoView = this.V;
            ImageView imageView = this.S;
            ImageView imageView2 = this.J;
            if (videoView != null) {
                CameraFrameManager.a().b(this.X, videoView);
            }
            CameraFrameManager.a().a(CommonApplication.getAppContext(), this.X);
            c();
            if (imageView != null && imageView2 != null) {
                if (k() != null && k().isOnline) {
                    imageView2.setVisibility(0);
                }
                imageView.setVisibility(8);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(Device device) {
        if (!j()) {
            ImageView imageView = this.S;
            ImageView imageView2 = this.J;
            if (imageView2 != null && imageView != null) {
                imageView2.setVisibility(8);
                imageView.setVisibility(0);
                this.B.a((DeviceDownloadItemViewWrapper.ProgressCallback) new DeviceDownloadItemViewWrapper.ProgressCallback() {
                    public void a() {
                    }

                    public void a(float f) {
                    }

                    public void b() {
                    }

                    public void c() {
                        if (!CameraCardItem.this.j()) {
                            CameraCardItem.this.q();
                        }
                    }

                    public void d() {
                        if (!CameraCardItem.this.j()) {
                            CameraCardItem.this.d();
                        }
                    }

                    public void e() {
                        if (!CameraCardItem.this.j()) {
                            CameraCardItem.this.d();
                        }
                    }
                });
                this.B.a(device);
            }
        }
    }

    public void a(int i) {
        ImageView imageView;
        if (!j() && (imageView = this.J) != null) {
            if (i == 1 && this.Z != i && (CameraFrameManager.a().b(this.X) || CameraFrameManager.a().a(this.X))) {
                d();
                imageView.setVisibility(0);
            }
            this.Z = i;
        }
    }

    public String g() {
        return this.X.did;
    }

    public void h() {
        q();
    }

    public void i() {
        super.i();
        a(true);
        this.E = null;
        this.C = null;
        this.I = null;
        this.J = null;
        this.S = null;
        this.T = null;
        this.U = null;
        this.V = null;
        this.W = null;
    }
}
