package com.xiaomi.smarthome.listcamera.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
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
import com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableItemViewHolder;
import com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractDraggableSwipeableItemViewHolder;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemAdapter;
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
import com.xiaomi.smarthome.library.common.widget.ExpandableItemIndicator;
import com.xiaomi.smarthome.listcamera.AllCameraPage;
import com.xiaomi.smarthome.listcamera.CameraDeviceOpManager;
import com.xiaomi.smarthome.listcamera.CameraFrameManager;
import com.xiaomi.smarthome.listcamera.CameraGroupManager;
import com.xiaomi.smarthome.listcamera.CameraHorizontalActivity;
import com.xiaomi.smarthome.listcamera.CameraInfoRefreshManager;
import com.xiaomi.smarthome.listcamera.FloatingCameraManager;
import com.xiaomi.smarthome.listcamera.adapter.CameraLargeAdapter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class CameraLargeAdapter extends AbstractExpandableItemAdapter<GroupViewHolder, ChildViewHolder> {

    /* renamed from: a  reason: collision with root package name */
    private static final String f19283a = "CameraLargeAdapter";
    /* access modifiers changed from: private */
    public Context b;
    private RecyclerView c;
    private AllCameraPage d;
    /* access modifiers changed from: private */
    public List<GroupViewHolder> e = new ArrayList();
    private HashSet<String> f = new HashSet<>();
    private final RecyclerViewExpandableItemManager g;
    /* access modifiers changed from: private */
    public int h = 0;
    private long i;
    private GroupViewHolder j;
    private FloatingCameraManager k = new FloatingCameraManager();
    private Queue<PlayItem> l = new ConcurrentLinkedQueue();

    public enum ViewType {
        CHILD_OPERATION,
        CHILD_SETTING
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void a(View view) {
    }

    /* renamed from: a */
    public void b(ChildViewHolder childViewHolder, int i2, int i3, int i4) {
    }

    private class PlayItem {

        /* renamed from: a  reason: collision with root package name */
        GroupViewHolder f19294a;
        Device b;

        public PlayItem(GroupViewHolder groupViewHolder, Device device) {
            this.f19294a = groupViewHolder;
            this.b = device;
        }
    }

    public CameraLargeAdapter(Context context, AllCameraPage allCameraPage, RecyclerView recyclerView, RecyclerViewExpandableItemManager recyclerViewExpandableItemManager) {
        this.b = context;
        this.d = allCameraPage;
        this.c = recyclerView;
        this.g = recyclerViewExpandableItemManager;
        this.c.addOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
                if (i == 0 && CameraLargeAdapter.this.h != i) {
                    for (GroupViewHolder groupViewHolder : CameraLargeAdapter.this.e) {
                        Device device = (Device) groupViewHolder.v.getTag();
                        if (device != null) {
                            if (CameraFrameManager.a().b(device)) {
                                CameraLargeAdapter.this.b(groupViewHolder, device);
                                groupViewHolder.f.setVisibility(8);
                            } else if (CameraFrameManager.a().a(device)) {
                                CameraLargeAdapter.this.b(groupViewHolder, device);
                                groupViewHolder.l.setVisibility(0);
                            } else {
                                groupViewHolder.l.setVisibility(8);
                            }
                        }
                    }
                    CameraLargeAdapter.this.e.clear();
                }
                int unused = CameraLargeAdapter.this.h = i;
            }
        });
        setHasStableIds(true);
        this.f.clear();
    }

    public int a() {
        return CameraGroupManager.a().c().size();
    }

    public int a(int i2) {
        Device n;
        List<CameraGroupManager.GroupInfo> d2 = CameraGroupManager.a().d();
        if (i2 >= d2.size() || (n = SmartHomeDeviceManager.a().n(d2.get(i2).f19240a)) == null || !a(n)) {
            return 0;
        }
        return d2.get(i2).e.size() + 1;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0049 A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(com.xiaomi.smarthome.device.Device r9) {
        /*
            r8 = this;
            com.xiaomi.smarthome.listcamera.CameraDeviceOpManager r0 = com.xiaomi.smarthome.listcamera.CameraDeviceOpManager.a()
            java.util.Map r0 = r0.f()
            r1 = 0
            if (r9 == 0) goto L_0x005e
            java.lang.String r2 = r9.model
            boolean r2 = r0.containsKey(r2)
            if (r2 == 0) goto L_0x005e
            com.xiaomi.smarthome.frame.core.CoreApi r2 = com.xiaomi.smarthome.frame.core.CoreApi.a()
            java.lang.String r3 = r9.model
            com.xiaomi.smarthome.core.entity.plugin.PluginRecord r2 = r2.d((java.lang.String) r3)
            r3 = 1
            if (r2 == 0) goto L_0x004b
            com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo r4 = r2.h()
            if (r4 == 0) goto L_0x004b
            com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo r4 = r2.h()
            int r4 = r4.g()
            long r4 = (long) r4
            java.lang.String r9 = r9.model
            java.lang.Object r9 = r0.get(r9)
            java.lang.Long r9 = (java.lang.Long) r9
            long r6 = r9.longValue()
            int r9 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r9 >= 0) goto L_0x0049
            long r4 = r2.e()
            r6 = 100
            int r9 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r9 > 0) goto L_0x005e
        L_0x0049:
            r1 = 1
            goto L_0x005e
        L_0x004b:
            java.lang.String r9 = r9.model
            java.lang.Object r9 = r0.get(r9)
            java.lang.Long r9 = (java.lang.Long) r9
            long r4 = r9.longValue()
            r6 = 0
            int r9 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r9 != 0) goto L_0x005e
            goto L_0x0049
        L_0x005e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.listcamera.adapter.CameraLargeAdapter.a(com.xiaomi.smarthome.device.Device):boolean");
    }

    public int d(int i2, int i3) {
        if (i3 >= CameraGroupManager.a().d().get(i2).e.size()) {
            return ViewType.CHILD_SETTING.ordinal();
        }
        return ViewType.CHILD_OPERATION.ordinal();
    }

    public long b(int i2) {
        List<CameraGroupManager.GroupInfo> d2 = CameraGroupManager.a().d();
        if (i2 < 0 || i2 >= d2.size()) {
            return 0;
        }
        return (long) d2.get(i2).hashCode();
    }

    public long c(int i2, int i3) {
        List<CameraGroupManager.GroupInfo> d2 = CameraGroupManager.a().d();
        if (i3 < d2.get(i2).e.size()) {
            return (long) d2.get(i2).e.get(i3).hashCode();
        }
        return (long) ViewType.CHILD_OPERATION.hashCode();
    }

    /* renamed from: c */
    public GroupViewHolder a(ViewGroup viewGroup, int i2) {
        return new GroupViewHolder(LayoutInflater.from(this.b).inflate(R.layout.camera_large_view_group_layout, (ViewGroup) null));
    }

    /* renamed from: d */
    public ChildViewHolder b(ViewGroup viewGroup, int i2) {
        LayoutInflater from = LayoutInflater.from(this.b);
        if (i2 == ViewType.CHILD_OPERATION.ordinal()) {
            return new ControlViewHolder(from.inflate(R.layout.device_control_child_view, (ViewGroup) null));
        }
        return new SettingChildViewHolder(from.inflate(R.layout.camera_device_control_adjust, (ViewGroup) null));
    }

    public void b() {
        Device device;
        if (this.j != null && (device = (Device) this.j.v.getTag()) != null) {
            b(this.j, device);
            this.j.i.setImageResource(R.drawable.camera_icon_btn_stop_2);
            this.f.add(device.did);
        }
    }

    public void c() {
        this.f.clear();
    }

    /* renamed from: a */
    public void b(final GroupViewHolder groupViewHolder, int i2, int i3) {
        if (!this.d.f) {
            final Device b2 = SmartHomeDeviceManager.a().b(CameraGroupManager.a().d().get(i2).f19240a);
            if (b2 == null) {
                SDKLog.b(f19283a, "device == null");
                return;
            }
            groupViewHolder.v.setTag(b2);
            groupViewHolder.p.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    CameraLargeAdapter.this.c(groupViewHolder, b2);
                    CameraFrameManager.a().a(CameraLargeAdapter.this.b, b2, 1, new Intent());
                }
            });
            groupViewHolder.c.setVisibility(4);
            PluginRecord d2 = CoreApi.a().d(b2.model);
            boolean z = true;
            boolean d3 = (d2 == null || d2.h() == null) ? true : d2.h().d();
            if (!d3) {
                groupViewHolder.m.setVisibility(8);
                groupViewHolder.h.setVisibility(0);
                groupViewHolder.h.setText(R.string.unsupport_camera_play);
            }
            if (b2.isShared()) {
                groupViewHolder.f19292a.setText(R.string.shared_device_room_name);
            } else {
                Room p = HomeManager.a().p(b2.did);
                if (p == null || TextUtils.isEmpty(p.e())) {
                    groupViewHolder.f19292a.setText(R.string.room_default);
                } else {
                    groupViewHolder.f19292a.setText(p.e());
                }
            }
            groupViewHolder.b.setText(b2.name);
            groupViewHolder.f19292a.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    CameraLargeAdapter.this.c(groupViewHolder, b2);
                    CameraFrameManager.a().a(CameraLargeAdapter.this.b, b2, 1, new Intent());
                }
            });
            if (b2.isOnline) {
                groupViewHolder.n.setText(R.string.device_online);
            } else {
                groupViewHolder.n.setText(R.string.offline_device);
            }
            if (!b2.isOnline) {
                groupViewHolder.m.setVisibility(8);
                groupViewHolder.c.setVisibility(4);
                groupViewHolder.r.setVisibility(0);
                groupViewHolder.f.setVisibility(8);
            } else {
                groupViewHolder.r.setVisibility(8);
            }
            CameraDeviceOpManager.a().f();
            CameraFrameManager.FrameContext a2 = CameraFrameManager.a().a(b2.did);
            if (a2 == null || !a2.h) {
                if (CameraFrameManager.a().a(b2.did) == null) {
                    CameraFrameManager.a().a(b2, false);
                }
                groupViewHolder.k.setVisibility(0);
                groupViewHolder.i.setVisibility(0);
                groupViewHolder.j.setVisibility(0);
                if (d3) {
                    groupViewHolder.h.setVisibility(8);
                }
                if (a2 == null) {
                    a2 = CameraFrameManager.a().a(b2.did);
                }
                if (a2 == null || !a2.a()) {
                    groupViewHolder.n.setVisibility(0);
                    groupViewHolder.i.setImageResource(R.drawable.camera_icon_btn_play_2);
                    if (b2.isOnline && d3) {
                        groupViewHolder.m.setVisibility(0);
                    }
                } else {
                    groupViewHolder.i.setImageResource(R.drawable.camera_icon_btn_stop_2);
                    groupViewHolder.m.setVisibility(8);
                    groupViewHolder.n.setVisibility(8);
                }
                if (a2 == null || !a2.b()) {
                    groupViewHolder.j.setImageResource(R.drawable.camera_icon_unmute);
                } else {
                    groupViewHolder.j.setImageResource(R.drawable.camera_icon_mute);
                }
                if (CameraInfoRefreshManager.a().b() || !CameraInfoRefreshManager.a().d()) {
                    groupViewHolder.k.setEnabled(false);
                    groupViewHolder.i.setEnabled(false);
                    groupViewHolder.j.setEnabled(false);
                } else {
                    groupViewHolder.k.setEnabled(true);
                    groupViewHolder.i.setEnabled(true);
                    groupViewHolder.j.setEnabled(true);
                }
                groupViewHolder.l.setVisibility(8);
                if (groupViewHolder.t) {
                    CameraFrameManager.a().a(b2, groupViewHolder.f, groupViewHolder.l);
                    if (CameraFrameManager.a().b(b2)) {
                        if (groupViewHolder.e == null) {
                            a(groupViewHolder);
                        }
                        CameraFrameManager.a().a(b2, groupViewHolder.e);
                        groupViewHolder.f.setVisibility(8);
                    }
                }
            } else {
                groupViewHolder.k.setVisibility(8);
                groupViewHolder.i.setVisibility(8);
                groupViewHolder.j.setVisibility(8);
                groupViewHolder.l.setVisibility(8);
                groupViewHolder.f.setVisibility(8);
                groupViewHolder.v.setTag((Object) null);
                groupViewHolder.f.setVisibility(0);
                if (!b2.isOnline) {
                    if (d3) {
                        groupViewHolder.h.setVisibility(8);
                    }
                    groupViewHolder.f.setScaleType(ImageView.ScaleType.CENTER);
                } else {
                    PluginRecord d4 = CoreApi.a().d(b2.model);
                    groupViewHolder.f.setImageResource(0);
                    groupViewHolder.h.setVisibility(0);
                    if (d4 == null || d4.l()) {
                        groupViewHolder.h.setText(R.string.unsupport_camera_play);
                    } else {
                        groupViewHolder.h.setText(R.string.undownload_camera_play);
                    }
                }
            }
            groupViewHolder.k.setOnClickListener(new View.OnClickListener(b2, groupViewHolder) {
                private final /* synthetic */ Device f$1;
                private final /* synthetic */ CameraLargeAdapter.GroupViewHolder f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void onClick(View view) {
                    CameraLargeAdapter.this.b(this.f$1, this.f$2, view);
                }
            });
            groupViewHolder.g.setOnClickListener(new View.OnClickListener(groupViewHolder, b2) {
                private final /* synthetic */ CameraLargeAdapter.GroupViewHolder f$1;
                private final /* synthetic */ Device f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void onClick(View view) {
                    CameraLargeAdapter.this.c(this.f$1, this.f$2, view);
                }
            });
            groupViewHolder.c.setOnClickListener($$Lambda$CameraLargeAdapter$GdnQuxdqyp5OK_MEp7mnD8qoLwg.INSTANCE);
            groupViewHolder.j.setOnClickListener(new View.OnClickListener(b2, groupViewHolder) {
                private final /* synthetic */ Device f$1;
                private final /* synthetic */ CameraLargeAdapter.GroupViewHolder f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void onClick(View view) {
                    CameraLargeAdapter.this.a(this.f$1, this.f$2, view);
                }
            });
            groupViewHolder.i.setOnClickListener(new View.OnClickListener(groupViewHolder, b2) {
                private final /* synthetic */ CameraLargeAdapter.GroupViewHolder f$1;
                private final /* synthetic */ Device f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void onClick(View view) {
                    CameraLargeAdapter.this.b(this.f$1, this.f$2, view);
                }
            });
            groupViewHolder.m.setOnClickListener(new View.OnClickListener(groupViewHolder, b2) {
                private final /* synthetic */ CameraLargeAdapter.GroupViewHolder f$1;
                private final /* synthetic */ Device f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void onClick(View view) {
                    CameraLargeAdapter.this.a(this.f$1, this.f$2, view);
                }
            });
            if (groupViewHolder.o != null) {
                int K_ = groupViewHolder.K_();
                if ((Integer.MIN_VALUE & K_) != 0) {
                    boolean z2 = (K_ & 8) != 0;
                    if ((K_ & 4) == 0) {
                        z = false;
                    }
                    groupViewHolder.o.setExpandedState(z, z2);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(Device device, GroupViewHolder groupViewHolder, View view) {
        Intent intent = new Intent(this.b, CameraHorizontalActivity.class);
        intent.putExtra("did", device.did);
        intent.putExtra("isAutoPlay", true);
        DisplayUtils.a(groupViewHolder.c, 100, 4);
        this.b.startActivity(intent);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void c(final GroupViewHolder groupViewHolder, final Device device, View view) {
        if (groupViewHolder.q.getVisibility() == 0) {
            DisplayUtils.a(groupViewHolder.c, 100, 4);
            DisplayUtils.a(groupViewHolder.q, 100, 4);
            return;
        }
        CameraFrameManager.FrameContext a2 = CameraFrameManager.a().a(device.did);
        if (a2 != null && a2.a()) {
            DisplayUtils.a(groupViewHolder.c, 100);
            groupViewHolder.u.removeCallbacksAndMessages((Object) null);
            groupViewHolder.u.postDelayed(new Runnable() {
                public void run() {
                    CameraFrameManager.FrameContext a2;
                    if (groupViewHolder.v.getTag() == device && (a2 = CameraFrameManager.a().a(device.did)) != null && a2.a()) {
                        DisplayUtils.a(groupViewHolder.c, 100, 4);
                        DisplayUtils.a(groupViewHolder.q, 100, 4);
                    }
                }
            }, 3000);
        }
        DisplayUtils.a(groupViewHolder.q, 100);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(Device device, GroupViewHolder groupViewHolder, View view) {
        CameraFrameManager.a().d(device);
        if (CameraFrameManager.a().a(device)) {
            CameraFrameManager.a().a(this.b, device, groupViewHolder.l);
            groupViewHolder.f.setVisibility(8);
        }
        if (CameraFrameManager.a().c(device)) {
            groupViewHolder.j.setImageResource(R.drawable.camera_icon_mute);
            return;
        }
        groupViewHolder.j.setImageResource(R.drawable.camera_icon_unmute);
        d(groupViewHolder, device);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(GroupViewHolder groupViewHolder, Device device, View view) {
        a(groupViewHolder, device);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(GroupViewHolder groupViewHolder, Device device, View view) {
        a(groupViewHolder, device);
    }

    /* access modifiers changed from: package-private */
    public void a(final GroupViewHolder groupViewHolder, final Device device) {
        if (CameraFrameManager.a().a(device)) {
            c(groupViewHolder, device);
            DisplayUtils.a(groupViewHolder.c, 100, 4);
            if (groupViewHolder.q.getVisibility() != 0) {
                DisplayUtils.a(groupViewHolder.q, 100, 0);
            }
        } else if (device == null || device.isSetPinCode == 0 || this.f.contains(device.did)) {
            e();
            b(groupViewHolder, device);
            DisplayUtils.a(groupViewHolder.c, 100);
            DisplayUtils.a(groupViewHolder.q, 100);
            groupViewHolder.u.removeCallbacksAndMessages((Object) null);
            groupViewHolder.u.postDelayed(new Runnable() {
                public void run() {
                    CameraFrameManager.FrameContext a2;
                    if (groupViewHolder.v.getTag() == device && (a2 = CameraFrameManager.a().a(device.did)) != null && a2.a()) {
                        DisplayUtils.a(groupViewHolder.c, 100, 4);
                        DisplayUtils.a(groupViewHolder.q, 100, 4);
                    }
                }
            }, 3000);
        } else {
            Intent intent = new Intent(this.b, DevicePinVerifyEnterActivity.class);
            intent.putExtra("extra_device_did", device.did);
            intent.putExtra("verfy_pincode_first", true);
            this.d.getActivity().startActivityForResult(intent, 100);
            this.j = groupViewHolder;
            return;
        }
        if (CameraFrameManager.a().a(device)) {
            groupViewHolder.i.setImageResource(R.drawable.camera_icon_btn_stop_2);
            groupViewHolder.m.setVisibility(8);
            groupViewHolder.n.setVisibility(8);
            return;
        }
        groupViewHolder.i.setImageResource(R.drawable.camera_icon_btn_play_2);
        groupViewHolder.m.setVisibility(0);
        groupViewHolder.n.setVisibility(0);
    }

    /* access modifiers changed from: package-private */
    public void a(final GroupViewHolder groupViewHolder) {
        final Device device = (Device) groupViewHolder.v.getTag();
        if (device != null) {
            CameraFrameManager.a().a(device.did);
            groupViewHolder.e = new VideoView(this.b);
            int measuredWidth = groupViewHolder.f.getMeasuredWidth();
            groupViewHolder.e.setVideoFrameSize(measuredWidth, (measuredWidth * 9) / 16);
            groupViewHolder.e.setVideoViewListener(new VideoView.IVideoViewListener() {
                public void a() {
                    if (groupViewHolder.c.getVisibility() == 0) {
                        DisplayUtils.a(groupViewHolder.c, 100, 4);
                        DisplayUtils.a(groupViewHolder.q, 100, 4);
                    } else if (groupViewHolder.c.getVisibility() != 0) {
                        CameraFrameManager.FrameContext a2 = CameraFrameManager.a().a(device.did);
                        if (a2 != null && a2.a()) {
                            DisplayUtils.a(groupViewHolder.c, 100);
                            groupViewHolder.u.removeCallbacksAndMessages((Object) null);
                            groupViewHolder.u.postDelayed(new Runnable() {
                                public void run() {
                                    CameraFrameManager.FrameContext a2;
                                    if (groupViewHolder.v.getTag() == device && (a2 = CameraFrameManager.a().a(device.did)) != null && a2.a()) {
                                        DisplayUtils.a(groupViewHolder.c, 100, 4);
                                        DisplayUtils.a(groupViewHolder.q, 100, 4);
                                    }
                                }
                            }, 3000);
                        }
                        DisplayUtils.a(groupViewHolder.q, 100);
                    }
                }
            });
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
            layoutParams.gravity = 17;
            groupViewHolder.d.addView(groupViewHolder.e, layoutParams);
        }
    }

    /* access modifiers changed from: package-private */
    public void b(GroupViewHolder groupViewHolder, Device device) {
        if (groupViewHolder.e == null) {
            a(groupViewHolder);
        }
        CameraFrameManager.a().a(device, groupViewHolder.e);
        CameraFrameManager.a().b(this.b, device, groupViewHolder.l);
        this.l.offer(new PlayItem(groupViewHolder, device));
        if (!CameraFrameManager.a().c(device)) {
            d(groupViewHolder, device);
        }
    }

    /* access modifiers changed from: package-private */
    public void b(GroupViewHolder groupViewHolder) {
        groupViewHolder.d.removeAllViews();
        groupViewHolder.e = null;
    }

    private void e() {
        CameraFrameManager.FrameContext a2;
        if (this.l.size() >= 3) {
            PlayItem poll = this.l.poll();
            if (poll.f19294a.v.getTag() == poll.b && (a2 = CameraFrameManager.a().a(poll.b.did)) != null && a2.a()) {
                poll.f19294a.i.performClick();
            }
        }
    }

    private void d(GroupViewHolder groupViewHolder, Device device) {
        CameraFrameManager.FrameContext a2;
        for (PlayItem playItem : this.l) {
            if (playItem.f19294a == groupViewHolder && playItem.b == device) {
                SDKLog.b(f19283a, "muteOthers continue" + device.name);
            } else if (playItem.f19294a.v.getTag() == playItem.b && (a2 = CameraFrameManager.a().a(playItem.b.did)) != null && !a2.b()) {
                playItem.f19294a.j.performClick();
                SDKLog.b(f19283a, "muteOthers hited" + playItem.b.name);
            }
        }
    }

    private void e(GroupViewHolder groupViewHolder, Device device) {
        Iterator it = this.l.iterator();
        while (it.hasNext()) {
            PlayItem playItem = (PlayItem) it.next();
            if (playItem.f19294a == groupViewHolder && playItem.b == device) {
                it.remove();
                SDKLog.b(f19283a, "removeItem hited");
            }
        }
        SDKLog.b(f19283a, "removeItem playintQueue.size=" + this.l.size());
    }

    /* access modifiers changed from: package-private */
    public void c(GroupViewHolder groupViewHolder, Device device) {
        CameraFrameManager.a().b(device, groupViewHolder.e);
        CameraFrameManager.a().a(this.b, device);
        b(groupViewHolder);
        e(groupViewHolder, device);
    }

    public void onViewAttachedToWindow(RecyclerView.ViewHolder viewHolder) {
        super.onViewAttachedToWindow(viewHolder);
        if (viewHolder instanceof GroupViewHolder) {
            GroupViewHolder groupViewHolder = (GroupViewHolder) viewHolder;
            Device device = (Device) groupViewHolder.v.getTag();
            boolean z = true;
            groupViewHolder.t = true;
            if (device != null) {
                CameraFrameManager.a().a(device, groupViewHolder.f, groupViewHolder.l);
                if (this.h == 0) {
                    if (CameraFrameManager.a().b(device)) {
                        b(groupViewHolder, device);
                        groupViewHolder.f.setVisibility(8);
                    } else if (CameraFrameManager.a().a(device)) {
                        b(groupViewHolder, device);
                        groupViewHolder.l.setVisibility(0);
                    } else {
                        groupViewHolder.l.setVisibility(8);
                    }
                } else if (CameraFrameManager.a().a(device)) {
                    Iterator<GroupViewHolder> it = this.e.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            if (it.next().equals(groupViewHolder)) {
                                break;
                            }
                        } else {
                            z = false;
                            break;
                        }
                    }
                    if (!z) {
                        this.e.add(groupViewHolder);
                    }
                } else {
                    groupViewHolder.l.setVisibility(8);
                }
            }
        }
    }

    public void onViewDetachedFromWindow(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder instanceof GroupViewHolder) {
            GroupViewHolder groupViewHolder = (GroupViewHolder) viewHolder;
            Device device = (Device) groupViewHolder.v.getTag();
            groupViewHolder.t = false;
            if (!(device == null || groupViewHolder == null || groupViewHolder.i == null || (!CameraFrameManager.a().a(device) && !CameraFrameManager.a().b(device)))) {
                groupViewHolder.i.performClick();
            }
        }
        super.onViewDetachedFromWindow(viewHolder);
    }

    public void d() {
        CameraFrameManager.a().a(this.b);
    }

    public void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            List<CameraGroupManager.GroupInfo> d2 = CameraGroupManager.a().d();
            for (int i2 = 0; i2 < d2.size(); i2++) {
                for (int i3 = 0; i3 < d2.get(i2).e.size(); i3++) {
                    if (d2.get(i2).e.get(i3).f19241a.equalsIgnoreCase(str)) {
                        this.g.b(i2, i3);
                    }
                }
            }
        }
    }

    public boolean a(View view, View view2, int i2, int i3) {
        if (view == null || view.getVisibility() == 8) {
            return false;
        }
        view2.getGlobalVisibleRect(new Rect());
        Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);
        return rect.contains(i2, i3);
    }

    public boolean a(GroupViewHolder groupViewHolder, int i2, int i3, int i4, boolean z) {
        if (System.currentTimeMillis() - this.i < 500 || groupViewHolder.q.getVisibility() != 0) {
            return false;
        }
        this.i = System.currentTimeMillis();
        return a(groupViewHolder.q, groupViewHolder.v, i3, i4);
    }

    public static abstract class MyBaseViewHolder extends AbstractDraggableSwipeableItemViewHolder implements ExpandableItemViewHolder {

        /* renamed from: a  reason: collision with root package name */
        private int f19293a;
        public View v;

        public MyBaseViewHolder(View view) {
            super(view);
            this.v = view;
        }

        public int K_() {
            return this.f19293a;
        }

        public void c_(int i) {
            this.f19293a = i;
        }

        public View k() {
            return this.v;
        }
    }

    public static class ChildViewHolder extends MyBaseViewHolder {

        /* renamed from: a  reason: collision with root package name */
        ViewType f19291a;

        public ChildViewHolder(View view, ViewType viewType) {
            super(view);
            this.f19291a = viewType;
        }
    }

    public static class SettingChildViewHolder extends ChildViewHolder {
        public LinearLayout b;

        public SettingChildViewHolder(View view) {
            super(view, ViewType.CHILD_SETTING);
            this.b = (LinearLayout) view.findViewById(R.id.setting_device);
        }
    }

    public static class GroupViewHolder extends MyBaseViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public TextView f19292a;
        public TextView b;
        public View c;
        public FrameLayout d;
        public VideoView e;
        public ImageView f;
        public ImageView g;
        public TextView h;
        public ImageView i;
        public ImageView j;
        public ImageView k;
        public ImageView l;
        public ImageView m;
        public TextView n;
        public ExpandableItemIndicator o;
        public View p;
        public View q;
        public View r;
        public ImageView s;
        public boolean t = false;
        public Handler u = new Handler(Looper.getMainLooper());

        public GroupViewHolder(View view) {
            super(view);
            if (view instanceof ViewGroup) {
                this.f19292a = (TextView) view.findViewById(R.id.title);
                this.b = (TextView) view.findViewById(R.id.device_name);
                this.c = view.findViewById(R.id.btn_large_view);
                this.d = (FrameLayout) view.findViewById(R.id.video_view_container);
                this.f = (ImageView) view.findViewById(R.id.video_view_cover);
                this.g = (ImageView) view.findViewById(R.id.video_view_cover_corner);
                this.h = (TextView) view.findViewById(R.id.video_unsupport_view);
                this.i = (ImageView) view.findViewById(R.id.pause_button);
                this.j = (ImageView) view.findViewById(R.id.mute_button);
                this.m = (ImageView) view.findViewById(R.id.ivPlayCenter);
                this.n = (TextView) view.findViewById(R.id.tvStatus);
                this.k = (ImageView) view.findViewById(R.id.full_screen_button);
                this.o = (ExpandableItemIndicator) view.findViewById(R.id.btn_expand_indicator);
                this.l = (ImageView) view.findViewById(R.id.camera_loading);
                this.p = view.findViewById(R.id.cloud_storage_container_large);
                this.q = view.findViewById(R.id.control_indicator_large);
                this.r = view.findViewById(R.id.fl_Offline);
                this.s = (ImageView) view.findViewById(R.id.fl_Offline_img);
            }
        }
    }
}
