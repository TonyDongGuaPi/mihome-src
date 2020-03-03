package com.xiaomi.smarthome.listcamera.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.mijia.debug.SDKLog;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.fastvideo.VideoView;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.page.verify.DevicePinVerifyEnterActivity;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.listcamera.AllCameraPage;
import com.xiaomi.smarthome.listcamera.CameraDeviceOpManager;
import com.xiaomi.smarthome.listcamera.CameraFrameManager;
import com.xiaomi.smarthome.listcamera.CameraGroupManager;
import com.xiaomi.smarthome.listcamera.CameraHorizontalActivity;
import com.xiaomi.smarthome.listcamera.CameraInfoRefreshManager;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class CameraSmallAdapter extends RecyclerView.Adapter {
    public static final String e = "CameraSmallAdapter";

    /* renamed from: a  reason: collision with root package name */
    Context f19295a;
    RecyclerView b;
    AllCameraPage c;
    SmallViewHolder d;
    /* access modifiers changed from: private */
    public int f = 0;
    /* access modifiers changed from: private */
    public List<SmallViewHolder> g = new ArrayList();
    /* access modifiers changed from: private */
    public HashSet<String> h = new HashSet<>();
    private Queue<PlayItem> i = new ConcurrentLinkedQueue();

    private class PlayItem {

        /* renamed from: a  reason: collision with root package name */
        SmallViewHolder f19311a;
        Device b;

        public PlayItem(SmallViewHolder smallViewHolder, Device device) {
            this.f19311a = smallViewHolder;
            this.b = device;
        }
    }

    public CameraSmallAdapter(Context context, AllCameraPage allCameraPage, RecyclerView recyclerView) {
        this.c = allCameraPage;
        this.b = recyclerView;
        this.f19295a = context;
        this.b.addOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
                if (i == 0 && CameraSmallAdapter.this.f != i) {
                    for (SmallViewHolder smallViewHolder : CameraSmallAdapter.this.g) {
                        CameraSmallAdapter.this.b(smallViewHolder, (Device) smallViewHolder.f19312a.getTag());
                        smallViewHolder.f.setVisibility(8);
                    }
                    CameraSmallAdapter.this.g.clear();
                }
                int unused = CameraSmallAdapter.this.f = i;
            }
        });
        setHasStableIds(true);
        this.h.clear();
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return new SmallViewHolder(LayoutInflater.from(this.f19295a).inflate(R.layout.camera_smarll_view_layout, (ViewGroup) null));
    }

    public void a() {
        Device device;
        if (this.d != null && (device = (Device) this.d.f19312a.getTag()) != null) {
            b(this.d, device);
            this.d.j.setImageResource(R.drawable.camera_icon_btn_stop_2);
            this.d.o.setVisibility(8);
            this.h.add(device.did);
        }
    }

    public void b() {
        this.h.clear();
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i2) {
        final SmallViewHolder smallViewHolder = (SmallViewHolder) viewHolder;
        if (!this.c.f) {
            int measuredWidth = (this.b.getMeasuredWidth() - DisplayUtils.a(3.0f)) / 2;
            int i3 = (measuredWidth * 12) / 16;
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(smallViewHolder.g.getLayoutParams());
            layoutParams.setMargins(DisplayUtils.a(4.0f), DisplayUtils.a(8.0f), DisplayUtils.a(4.0f), 0);
            layoutParams.height = i3;
            smallViewHolder.f19312a.setLayoutParams(layoutParams);
            FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(smallViewHolder.f.getLayoutParams());
            layoutParams2.width = measuredWidth;
            layoutParams2.height = i3;
            layoutParams2.gravity = 17;
            smallViewHolder.f.setLayoutParams(layoutParams2);
            CameraGroupManager.GroupInfo groupInfo = CameraGroupManager.a().d().get(i2);
            final Device b2 = SmartHomeDeviceManager.a().b(groupInfo.f19240a);
            if (b2 != null) {
                if (groupInfo != null && groupInfo.c && groupInfo.b) {
                    smallViewHolder.n.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            if (CameraSmallAdapter.this.f19295a != null) {
                                CameraSmallAdapter.this.c(smallViewHolder, b2);
                                CameraFrameManager.a().a(CameraSmallAdapter.this.f19295a, b2, 1, new Intent());
                            }
                        }
                    });
                }
                if (b2 != null) {
                    smallViewHolder.f19312a.setTag(b2);
                    if (b2.isShared()) {
                        smallViewHolder.b.setText(R.string.shared_device_room_name);
                    } else {
                        Room p = HomeManager.a().p(b2.did);
                        if (p == null || TextUtils.isEmpty(p.e())) {
                            smallViewHolder.b.setText(R.string.room_default);
                        } else {
                            smallViewHolder.b.setText(p.e());
                        }
                    }
                    if (b2.isOnline) {
                        smallViewHolder.p.setText(R.string.device_online);
                    } else {
                        smallViewHolder.p.setText(R.string.offline_device);
                    }
                    smallViewHolder.c.setText(b2.name);
                    AnonymousClass3 r1 = new View.OnClickListener() {
                        public void onClick(View view) {
                            if (b2 != null) {
                                CameraSmallAdapter.this.c(smallViewHolder, b2);
                                CameraFrameManager.a().a(CameraSmallAdapter.this.f19295a, b2, 1, new Intent());
                            }
                        }
                    };
                    smallViewHolder.b.setOnClickListener(r1);
                    smallViewHolder.c.setOnClickListener(r1);
                    if (!b2.isOnline) {
                        smallViewHolder.e.setVisibility(8);
                        smallViewHolder.o.setVisibility(8);
                        smallViewHolder.q.setVisibility(0);
                    } else {
                        smallViewHolder.q.setVisibility(8);
                    }
                }
                smallViewHolder.e.setVisibility(8);
                PluginRecord d2 = CoreApi.a().d(b2.model);
                if (!((d2 == null || d2.h() == null) ? true : d2.h().d())) {
                    smallViewHolder.o.setVisibility(8);
                    return;
                }
                smallViewHolder.g.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (smallViewHolder.e.getVisibility() == 0) {
                            DisplayUtils.a(smallViewHolder.e, 100, 8);
                            smallViewHolder.o.setVisibility(8);
                            smallViewHolder.p.setVisibility(8);
                            return;
                        }
                        smallViewHolder.p.setVisibility(0);
                        if (smallViewHolder.o.getVisibility() != 0) {
                            DisplayUtils.a(smallViewHolder.e, 100);
                        }
                    }
                });
                CameraDeviceOpManager.a().f();
                CameraFrameManager.FrameContext a2 = CameraFrameManager.a().a(b2.did);
                if (a2 == null || !a2.h) {
                    if (CameraFrameManager.a().a(b2.did) == null) {
                        CameraFrameManager.a().a(b2, false);
                    }
                    smallViewHolder.l.setVisibility(0);
                    smallViewHolder.j.setVisibility(0);
                    smallViewHolder.k.setVisibility(0);
                    CameraFrameManager.FrameContext a3 = CameraFrameManager.a().a(b2.did);
                    if (a3 == null || !a3.a()) {
                        smallViewHolder.j.setImageResource(R.drawable.camera_icon_btn_play_2);
                    } else {
                        smallViewHolder.j.setImageResource(R.drawable.camera_icon_btn_stop_2);
                        smallViewHolder.o.setVisibility(8);
                    }
                    if (a3 == null || !a3.b()) {
                        smallViewHolder.k.setImageResource(R.drawable.camera_icon_unmute);
                    } else {
                        smallViewHolder.k.setImageResource(R.drawable.camera_icon_mute);
                    }
                    if (CameraInfoRefreshManager.a().b() || !CameraInfoRefreshManager.a().d()) {
                        smallViewHolder.l.setEnabled(false);
                        smallViewHolder.j.setEnabled(false);
                        smallViewHolder.k.setEnabled(false);
                    } else {
                        smallViewHolder.l.setEnabled(true);
                        smallViewHolder.j.setEnabled(true);
                        smallViewHolder.k.setEnabled(true);
                    }
                    smallViewHolder.m.setVisibility(8);
                    if (smallViewHolder.r) {
                        CameraFrameManager.a().a(b2, smallViewHolder.f, smallViewHolder.m);
                        if (CameraFrameManager.a().b(b2)) {
                            if (smallViewHolder.i == null) {
                                a(smallViewHolder, b2);
                            }
                            CameraFrameManager.a().a(b2, smallViewHolder.i);
                            smallViewHolder.f.setVisibility(8);
                        }
                    }
                } else {
                    smallViewHolder.l.setVisibility(8);
                    smallViewHolder.j.setVisibility(8);
                    smallViewHolder.k.setVisibility(8);
                    smallViewHolder.f19312a.setTag((Object) null);
                }
                smallViewHolder.f.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (b2 == null || b2.isOnline) {
                            CameraFrameManager.FrameContext a2 = b2 != null ? CameraFrameManager.a().a(b2.did) : null;
                            if (a2 != null) {
                                if (a2.a()) {
                                    smallViewHolder.o.setVisibility(8);
                                    smallViewHolder.p.setVisibility(8);
                                } else {
                                    smallViewHolder.o.setVisibility(0);
                                    smallViewHolder.p.setVisibility(0);
                                }
                            }
                            if (smallViewHolder.e.getVisibility() == 0) {
                                DisplayUtils.a(smallViewHolder.e, 100, 8);
                                return;
                            }
                            DisplayUtils.a(smallViewHolder.e, 100);
                            smallViewHolder.s.removeCallbacksAndMessages((Object) null);
                            smallViewHolder.s.postDelayed(new Runnable() {
                                public void run() {
                                    CameraFrameManager.FrameContext a2;
                                    if (smallViewHolder.f19312a.getTag() == b2 && (a2 = CameraFrameManager.a().a(b2.did)) != null && a2.a()) {
                                        DisplayUtils.a(smallViewHolder.e, 100, 4);
                                    }
                                }
                            }, 3000);
                        }
                    }
                });
                smallViewHolder.l.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (CameraFrameManager.a().a(b2)) {
                            smallViewHolder.j.performClick();
                        }
                        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                            public void run() {
                                Intent intent = new Intent(CameraSmallAdapter.this.f19295a, CameraHorizontalActivity.class);
                                intent.putExtra("did", b2.did);
                                intent.putExtra("isAutoPlay", true);
                                CameraSmallAdapter.this.f19295a.startActivity(intent);
                            }
                        }, 100);
                    }
                });
                smallViewHolder.b.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                    }
                });
                smallViewHolder.k.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        CameraFrameManager.a().d(b2);
                        if (CameraFrameManager.a().b(b2)) {
                            CameraSmallAdapter.this.b(smallViewHolder, b2);
                        }
                        if (CameraFrameManager.a().c(b2)) {
                            smallViewHolder.k.setImageResource(R.drawable.camera_icon_mute);
                            return;
                        }
                        smallViewHolder.k.setImageResource(R.drawable.camera_icon_unmute);
                        CameraSmallAdapter.this.d(smallViewHolder, b2);
                    }
                });
                smallViewHolder.j.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (CameraFrameManager.a().a(b2)) {
                            smallViewHolder.p.setVisibility(0);
                            CameraSmallAdapter.this.c(smallViewHolder, b2);
                            DisplayUtils.a((View) smallViewHolder.o, 100);
                            DisplayUtils.a(smallViewHolder.e, 100, 8);
                        } else if (b2 == null || b2.isSetPinCode == 0 || CameraSmallAdapter.this.h.contains(b2.did)) {
                            CameraSmallAdapter.this.c();
                            CameraSmallAdapter.this.b(smallViewHolder, b2);
                            smallViewHolder.p.setVisibility(8);
                            DisplayUtils.a((View) smallViewHolder.o, 100, 8);
                            DisplayUtils.a(smallViewHolder.e, 100);
                            smallViewHolder.s.removeCallbacksAndMessages((Object) null);
                            smallViewHolder.s.postDelayed(new Runnable() {
                                public void run() {
                                    CameraFrameManager.FrameContext a2;
                                    if (smallViewHolder.f19312a.getTag() == b2 && (a2 = CameraFrameManager.a().a(b2.did)) != null && a2.a()) {
                                        DisplayUtils.a(smallViewHolder.e, 100, 4);
                                    }
                                }
                            }, 3000);
                        } else {
                            Intent intent = new Intent(CameraSmallAdapter.this.f19295a, DevicePinVerifyEnterActivity.class);
                            intent.putExtra("extra_device_did", b2.did);
                            intent.putExtra("verfy_pincode_first", true);
                            CameraSmallAdapter.this.c.getActivity().startActivityForResult(intent, 100);
                            CameraSmallAdapter.this.d = smallViewHolder;
                            return;
                        }
                        if (CameraFrameManager.a().a(b2)) {
                            smallViewHolder.j.setImageResource(R.drawable.camera_icon_btn_stop_2);
                            smallViewHolder.o.setClickable(false);
                            return;
                        }
                        smallViewHolder.j.setImageResource(R.drawable.camera_icon_btn_play_2);
                        smallViewHolder.o.setClickable(true);
                    }
                });
                if (!b2.isOnline) {
                    smallViewHolder.o.setVisibility(8);
                } else if (!CameraFrameManager.a().a(b2)) {
                    smallViewHolder.o.setVisibility(0);
                    smallViewHolder.p.setVisibility(0);
                    smallViewHolder.o.bringToFront();
                } else {
                    smallViewHolder.o.setVisibility(8);
                    smallViewHolder.p.setVisibility(8);
                }
                smallViewHolder.o.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        smallViewHolder.j.performClick();
                    }
                });
            }
        }
    }

    public void onViewAttachedToWindow(RecyclerView.ViewHolder viewHolder) {
        super.onViewAttachedToWindow(viewHolder);
        if (viewHolder instanceof SmallViewHolder) {
            SmallViewHolder smallViewHolder = (SmallViewHolder) viewHolder;
            Device device = (Device) smallViewHolder.f19312a.getTag();
            boolean z = true;
            smallViewHolder.r = true;
            if (device != null) {
                CameraFrameManager.a().a(device, smallViewHolder.f, smallViewHolder.m);
                if (this.f == 0) {
                    if (CameraFrameManager.a().b(device)) {
                        b(smallViewHolder, device);
                        smallViewHolder.f.setVisibility(8);
                    } else if (CameraFrameManager.a().a(device)) {
                        b(smallViewHolder, device);
                        smallViewHolder.m.setVisibility(0);
                    } else {
                        smallViewHolder.m.setVisibility(8);
                    }
                } else if (CameraFrameManager.a().b(device)) {
                    Iterator<SmallViewHolder> it = this.g.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            if (it.next().equals(smallViewHolder)) {
                                break;
                            }
                        } else {
                            z = false;
                            break;
                        }
                    }
                    if (!z) {
                        this.g.add(smallViewHolder);
                    }
                } else {
                    smallViewHolder.m.setVisibility(8);
                }
            }
        }
    }

    public void onViewDetachedFromWindow(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder instanceof SmallViewHolder) {
            SmallViewHolder smallViewHolder = (SmallViewHolder) viewHolder;
            Device device = (Device) smallViewHolder.f19312a.getTag();
            smallViewHolder.r = false;
            if (!(device == null || smallViewHolder == null || smallViewHolder.j == null || (!CameraFrameManager.a().a(device) && !CameraFrameManager.a().b(device)))) {
                smallViewHolder.j.performClick();
            }
        }
        super.onViewDetachedFromWindow(viewHolder);
    }

    /* access modifiers changed from: package-private */
    public void a(final SmallViewHolder smallViewHolder, final Device device) {
        smallViewHolder.i = new VideoView(this.f19295a);
        int measuredWidth = (this.b.getMeasuredWidth() - DisplayUtils.a(3.0f)) / 2;
        int i2 = (measuredWidth * 12) / 16;
        smallViewHolder.i.setVideoFrameSize(measuredWidth, i2, false);
        smallViewHolder.i.setVideoViewListener(new VideoView.IVideoViewListener() {
            public void a() {
                if (smallViewHolder.e.getVisibility() == 0) {
                    DisplayUtils.a(smallViewHolder.e, 100, 8);
                } else {
                    DisplayUtils.a(smallViewHolder.e, 100);
                    smallViewHolder.s.removeCallbacksAndMessages((Object) null);
                    smallViewHolder.s.postDelayed(new Runnable() {
                        public void run() {
                            CameraFrameManager.FrameContext a2;
                            if (smallViewHolder.f19312a.getTag() == device && (a2 = CameraFrameManager.a().a(device.did)) != null && a2.a()) {
                                DisplayUtils.a(smallViewHolder.e, 100, 4);
                            }
                        }
                    }, 3000);
                }
                if (smallViewHolder.o.getVisibility() == 0) {
                    DisplayUtils.a((View) smallViewHolder.o, 100, 8);
                }
            }
        });
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, i2);
        layoutParams.gravity = 17;
        smallViewHolder.h.addView(smallViewHolder.i, layoutParams);
    }

    /* access modifiers changed from: package-private */
    public void b(SmallViewHolder smallViewHolder, Device device) {
        if (smallViewHolder.i == null) {
            a(smallViewHolder, device);
        }
        CameraFrameManager.a().a(device, smallViewHolder.i);
        CameraFrameManager.a().a(this.f19295a, device, smallViewHolder.m);
        this.i.offer(new PlayItem(smallViewHolder, device));
    }

    /* access modifiers changed from: package-private */
    public void a(SmallViewHolder smallViewHolder) {
        smallViewHolder.h.removeAllViews();
        smallViewHolder.i = null;
    }

    /* access modifiers changed from: private */
    public void d(SmallViewHolder smallViewHolder, Device device) {
        CameraFrameManager.FrameContext a2;
        for (PlayItem playItem : this.i) {
            if (playItem.f19311a == smallViewHolder && playItem.b == device) {
                String str = e;
                SDKLog.b(str, "muteOthers continue" + device.name);
            } else if (playItem.f19311a.f19312a.getTag() == playItem.b && (a2 = CameraFrameManager.a().a(playItem.b.did)) != null && !a2.b()) {
                playItem.f19311a.k.performClick();
                String str2 = e;
                SDKLog.b(str2, "muteOthers hited" + playItem.b.name);
            }
        }
    }

    /* access modifiers changed from: private */
    public void c() {
        if (this.i.size() >= 3) {
            PlayItem poll = this.i.poll();
            if (poll.f19311a.f19312a.getTag() == poll.b && CameraFrameManager.a().a(poll.b.did).a()) {
                poll.f19311a.j.performClick();
            }
        }
    }

    private void e(SmallViewHolder smallViewHolder, Device device) {
        Iterator it = this.i.iterator();
        while (it.hasNext()) {
            PlayItem playItem = (PlayItem) it.next();
            if (playItem.f19311a == smallViewHolder && playItem.b == device) {
                it.remove();
                SDKLog.b(e, "removeItem hited");
            }
        }
        String str = e;
        SDKLog.b(str, "removeItem playintQueue.size=" + this.i.size());
    }

    /* access modifiers changed from: package-private */
    public void c(SmallViewHolder smallViewHolder, Device device) {
        CameraFrameManager.a().b(device, smallViewHolder.i);
        CameraFrameManager.a().a(this.f19295a, device);
        a(smallViewHolder);
        e(smallViewHolder, device);
    }

    public int getItemCount() {
        return CameraGroupManager.a().c().size();
    }

    public static class SmallViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public View f19312a;
        public TextView b;
        public TextView c;
        public View d;
        public View e;
        public ImageView f;
        public FrameLayout g;
        public FrameLayout h;
        public VideoView i;
        public ImageView j;
        public ImageView k;
        public ImageView l;
        public ImageView m;
        public FrameLayout n;
        public ImageView o;
        public TextView p;
        public View q;
        public boolean r = false;
        public Handler s = new Handler(Looper.getMainLooper());

        public SmallViewHolder(View view) {
            super(view);
            if (view instanceof ViewGroup) {
                this.f19312a = view;
                this.b = (TextView) view.findViewById(R.id.title);
                this.c = (TextView) view.findViewById(R.id.device_name);
                this.f = (ImageView) view.findViewById(R.id.video_view_cover);
                this.g = (FrameLayout) view.findViewById(R.id.video_container);
                this.h = (FrameLayout) view.findViewById(R.id.video_view_fl);
                this.e = view.findViewById(R.id.btn_top_view);
                this.d = view.findViewById(R.id.btn_title_view);
                this.j = (ImageView) view.findViewById(R.id.pause_button);
                this.k = (ImageView) view.findViewById(R.id.mute_button);
                this.l = (ImageView) view.findViewById(R.id.full_screen_button);
                this.m = (ImageView) view.findViewById(R.id.camera_loading);
                this.n = (FrameLayout) view.findViewById(R.id.cloud_storage_container);
                this.o = (ImageView) view.findViewById(R.id.ivPlayCenter);
                this.p = (TextView) view.findViewById(R.id.tvStatus);
                this.q = view.findViewById(R.id.tvOffline);
            }
        }
    }
}
