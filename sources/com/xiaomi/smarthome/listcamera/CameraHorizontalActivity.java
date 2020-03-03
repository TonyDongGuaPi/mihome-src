package com.xiaomi.smarthome.listcamera;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.fastvideo.VideoView;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.page.verify.DevicePinVerifyEnterActivity;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.listcamera.CameraFrameManager;
import com.xiaomi.smarthome.listcamera.CameraGroupManager;
import com.xiaomi.smarthome.listcamera.adapter.CameraLargeAdapter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class CameraHorizontalActivity extends BaseActivity {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public List<Device> f19242a = new ArrayList();
    private HashSet<String> b = new HashSet<>();
    private FullscreenViewHolder c;
    private boolean d = true;
    /* access modifiers changed from: private */
    public int e;
    /* access modifiers changed from: private */
    public boolean f;
    private boolean g;
    private int h = 0;
    List<FullscreenViewHolder> mBlockingViews = new ArrayList();
    RecyclerView mRecyclerView;
    int mScrollState;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        super.onCreate(bundle);
        setRequestedOrientation(0);
        setContentView(R.layout.all_camera_layout);
        String stringExtra = getIntent().getStringExtra("did");
        this.f = getIntent().getBooleanExtra("isAutoPlay", false);
        this.g = getIntent().getBooleanExtra("isQuickPass", false);
        List<CameraGroupManager.GroupInfo> c2 = CameraGroupManager.a().c();
        for (int i = 0; i < c2.size(); i++) {
            CameraGroupManager.GroupInfo groupInfo = c2.get(i);
            if (groupInfo.f19240a.equals(stringExtra)) {
                this.h = i;
            }
            Device b2 = SmartHomeDeviceManager.a().b(groupInfo.f19240a);
            if (b2 != null) {
                this.f19242a.add(b2);
            }
        }
        initView();
    }

    /* access modifiers changed from: private */
    public void a() {
        this.mHandler.postDelayed(new Runnable() {
            public void run() {
                if (CameraHorizontalActivity.this.f && CameraHorizontalActivity.this.mRecyclerView != null) {
                    boolean unused = CameraHorizontalActivity.this.f = false;
                    int findFirstVisibleItemPosition = ((LinearLayoutManager) CameraHorizontalActivity.this.mRecyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                    if (findFirstVisibleItemPosition >= 0) {
                        Device device = (Device) CameraHorizontalActivity.this.f19242a.get(findFirstVisibleItemPosition);
                        FullscreenViewHolder fullscreenViewHolder = (FullscreenViewHolder) CameraHorizontalActivity.this.mRecyclerView.findViewHolderForAdapterPosition(findFirstVisibleItemPosition);
                        if (device != null && fullscreenViewHolder != null && !CameraFrameManager.a().a(device)) {
                            CameraHorizontalActivity.this.togglePlay(fullscreenViewHolder, device);
                        }
                    }
                }
            }
        }, 100);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View view) {
            super(view);
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        for (Device next : this.f19242a) {
            CameraFrameManager.FrameContext a2 = CameraFrameManager.a().a(next.did);
            if (a2 != null && a2.a()) {
                CameraFrameManager.a().a(getContext(), next);
            }
        }
        this.b.clear();
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.d) {
            this.mRecyclerView.getAdapter().notifyDataSetChanged();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (configuration.orientation == 2) {
            this.mRecyclerView.getAdapter().notifyDataSetChanged();
            this.d = true;
            return;
        }
        this.d = false;
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
    }

    /* access modifiers changed from: package-private */
    public void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(0);
        this.mRecyclerView = (RecyclerView) findViewById(R.id.device_grid_view);
        this.mRecyclerView.setLayoutManager(linearLayoutManager);
        this.mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int i) {
                int i2;
                super.onScrollStateChanged(recyclerView, i);
                if (i == 0) {
                    LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    int i3 = -1;
                    if (CameraHorizontalActivity.this.e <= 0) {
                        i3 = linearLayoutManager.findFirstVisibleItemPosition();
                        i2 = linearLayoutManager.findLastVisibleItemPosition();
                    } else if (CameraHorizontalActivity.this.e > 0) {
                        int findFirstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
                        i3 = linearLayoutManager.findLastVisibleItemPosition();
                        i2 = findFirstVisibleItemPosition;
                    } else {
                        i2 = -1;
                    }
                    if (i3 >= 0 && i2 >= 0) {
                        Device device = (Device) CameraHorizontalActivity.this.f19242a.get(i3);
                        Device device2 = (Device) CameraHorizontalActivity.this.f19242a.get(i2);
                        FullscreenViewHolder fullscreenViewHolder = (FullscreenViewHolder) recyclerView.findViewHolderForAdapterPosition(i3);
                        FullscreenViewHolder fullscreenViewHolder2 = (FullscreenViewHolder) recyclerView.findViewHolderForAdapterPosition(i2);
                        if (!(fullscreenViewHolder == null || device == null || CameraFrameManager.a().a(device))) {
                            CameraHorizontalActivity.this.togglePlay(fullscreenViewHolder, device);
                        }
                        if (device2 != null && fullscreenViewHolder2 != null && CameraFrameManager.a().a(device2) && i2 != i3) {
                            CameraHorizontalActivity.this.togglePlay(fullscreenViewHolder2, device2);
                        }
                    }
                }
            }

            public void onScrolled(@NonNull RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                int unused = CameraHorizontalActivity.this.e = i;
            }
        });
        this.mRecyclerView.setAdapter(new RecyclerView.Adapter() {
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
                return new FullscreenViewHolder(LayoutInflater.from(CameraHorizontalActivity.this.getContext()).inflate(R.layout.all_camera_item, (ViewGroup) null));
            }

            public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
                Context context;
                int i2;
                final FullscreenViewHolder fullscreenViewHolder = (FullscreenViewHolder) viewHolder;
                int measuredWidth = CameraHorizontalActivity.this.mRecyclerView.getMeasuredWidth();
                fullscreenViewHolder.d.setLayoutParams(new FrameLayout.LayoutParams(measuredWidth, (measuredWidth * 9) / 16));
                final Device device = (Device) CameraHorizontalActivity.this.f19242a.get(i);
                if (device != null) {
                    if (device.isShared()) {
                        fullscreenViewHolder.f19253a.setText(R.string.shared_device_room_name);
                    } else {
                        Room p = HomeManager.a().p(device.did);
                        if (p == null || TextUtils.isEmpty(p.e())) {
                            fullscreenViewHolder.f19253a.setText(R.string.room_default);
                        } else {
                            fullscreenViewHolder.f19253a.setText(p.e());
                        }
                    }
                    TextView textView = fullscreenViewHolder.b;
                    if (device.isOnline) {
                        context = CameraHorizontalActivity.this.getContext();
                        i2 = R.string.device_online;
                    } else {
                        context = CameraHorizontalActivity.this.getContext();
                        i2 = R.string.offline_device;
                    }
                    textView.setText(context.getString(i2));
                    fullscreenViewHolder.c.setText(device.name);
                }
                CameraDeviceOpManager.a().f();
                if (CameraFrameManager.a().a(device.did) == null) {
                    CameraFrameManager.a().a(device, false);
                }
                fullscreenViewHolder.v.setTag(device);
                fullscreenViewHolder.i.setVisibility(0);
                fullscreenViewHolder.j.setVisibility(0);
                CameraFrameManager.FrameContext a2 = CameraFrameManager.a().a(device.did);
                if (a2.a()) {
                    fullscreenViewHolder.i.setImageResource(R.drawable.camera_icon_btn_stop_2);
                } else {
                    fullscreenViewHolder.i.setImageResource(R.drawable.camera_icon_btn_play_2);
                }
                if (a2.a()) {
                    fullscreenViewHolder.m.setVisibility(8);
                } else {
                    fullscreenViewHolder.m.setVisibility(0);
                }
                if (a2.b()) {
                    fullscreenViewHolder.j.setImageResource(R.drawable.camera_icon_mute);
                } else {
                    fullscreenViewHolder.j.setImageResource(R.drawable.camera_icon_unmute);
                }
                fullscreenViewHolder.d.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (fullscreenViewHolder.f.getVisibility() == 0) {
                            DisplayUtils.a(fullscreenViewHolder.f, 100, 8);
                            DisplayUtils.a(fullscreenViewHolder.g, 100, 8);
                            return;
                        }
                        DisplayUtils.a(fullscreenViewHolder.f, 100);
                        DisplayUtils.a(fullscreenViewHolder.g, 100);
                    }
                });
                if (CameraInfoRefreshManager.a().b() || !CameraInfoRefreshManager.a().d()) {
                    fullscreenViewHolder.i.setEnabled(false);
                    fullscreenViewHolder.j.setEnabled(false);
                } else {
                    fullscreenViewHolder.i.setEnabled(true);
                    fullscreenViewHolder.j.setEnabled(true);
                }
                fullscreenViewHolder.k.setVisibility(8);
                if (fullscreenViewHolder.n) {
                    CameraFrameManager.a().a(device, fullscreenViewHolder.d, fullscreenViewHolder.k);
                    if (CameraFrameManager.a().b(device)) {
                        if (fullscreenViewHolder.h == null) {
                            CameraHorizontalActivity.this.addVideoView(fullscreenViewHolder);
                        }
                        CameraFrameManager.a().a(device, fullscreenViewHolder.h);
                        fullscreenViewHolder.d.setVisibility(8);
                    }
                }
                fullscreenViewHolder.g.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (device != null && fullscreenViewHolder != null) {
                            CameraHorizontalActivity.this.stopPlay(fullscreenViewHolder, device);
                            CameraFrameManager.a().a(CameraHorizontalActivity.this.getContext(), device, 1, new Intent());
                            CameraHorizontalActivity.this.finish();
                        }
                    }
                });
                fullscreenViewHolder.j.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        CameraFrameManager.a().d(device);
                        if (CameraFrameManager.a().a(device)) {
                            CameraFrameManager.a().b(CameraHorizontalActivity.this.getContext(), device, fullscreenViewHolder.k);
                        }
                        if (CameraFrameManager.a().c(device)) {
                            fullscreenViewHolder.j.setImageResource(R.drawable.camera_icon_mute);
                        } else {
                            fullscreenViewHolder.j.setImageResource(R.drawable.camera_icon_unmute);
                        }
                    }
                });
                fullscreenViewHolder.l.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        CameraHorizontalActivity.this.onBackPressed();
                    }
                });
                fullscreenViewHolder.m.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        fullscreenViewHolder.i.performClick();
                    }
                });
                fullscreenViewHolder.i.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        CameraHorizontalActivity.this.togglePlay(fullscreenViewHolder, device);
                    }
                });
            }

            public int getItemCount() {
                return CameraHorizontalActivity.this.f19242a.size();
            }

            public void onViewAttachedToWindow(RecyclerView.ViewHolder viewHolder) {
                if (viewHolder instanceof FullscreenViewHolder) {
                    FullscreenViewHolder fullscreenViewHolder = (FullscreenViewHolder) viewHolder;
                    Device device = (Device) fullscreenViewHolder.v.getTag();
                    boolean z = true;
                    fullscreenViewHolder.n = true;
                    if (device != null) {
                        CameraFrameManager.a().a(device, fullscreenViewHolder.d, fullscreenViewHolder.k);
                        if (CameraHorizontalActivity.this.mScrollState == 0) {
                            if (CameraFrameManager.a().b(device)) {
                                CameraHorizontalActivity.this.startPlay(fullscreenViewHolder, device);
                                fullscreenViewHolder.d.setVisibility(8);
                            } else if (CameraFrameManager.a().a(device)) {
                                CameraHorizontalActivity.this.startPlay(fullscreenViewHolder, device);
                                fullscreenViewHolder.k.setVisibility(0);
                            } else {
                                fullscreenViewHolder.k.setVisibility(8);
                            }
                        } else if (CameraFrameManager.a().b(device)) {
                            Iterator<FullscreenViewHolder> it = CameraHorizontalActivity.this.mBlockingViews.iterator();
                            while (true) {
                                if (it.hasNext()) {
                                    if (it.next().equals(fullscreenViewHolder)) {
                                        break;
                                    }
                                } else {
                                    z = false;
                                    break;
                                }
                            }
                            if (!z) {
                                CameraHorizontalActivity.this.mBlockingViews.add(fullscreenViewHolder);
                            }
                        } else {
                            fullscreenViewHolder.k.setVisibility(8);
                        }
                    }
                }
                CameraHorizontalActivity.this.a();
            }

            public void onViewDetachedFromWindow(RecyclerView.ViewHolder viewHolder) {
                if (viewHolder instanceof FullscreenViewHolder) {
                    FullscreenViewHolder fullscreenViewHolder = (FullscreenViewHolder) viewHolder;
                    Device device = (Device) fullscreenViewHolder.v.getTag();
                    fullscreenViewHolder.n = false;
                    if (device != null && fullscreenViewHolder != null) {
                        CameraHorizontalActivity.this.stopPlay(fullscreenViewHolder, device);
                    }
                }
            }
        });
        new PagerSnapHelper().attachToRecyclerView(this.mRecyclerView);
        a(linearLayoutManager, this.mRecyclerView, this.h);
    }

    private void a(LinearLayoutManager linearLayoutManager, RecyclerView recyclerView, int i) {
        int findFirstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
        int findLastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
        if (i <= findFirstVisibleItemPosition) {
            recyclerView.scrollToPosition(i);
        } else if (i <= findLastVisibleItemPosition) {
            recyclerView.scrollBy(0, recyclerView.getChildAt(i - findFirstVisibleItemPosition).getTop());
        } else {
            recyclerView.scrollToPosition(i);
        }
    }

    public void togglePlay(FullscreenViewHolder fullscreenViewHolder, Device device) {
        if (CameraFrameManager.a().a(device)) {
            stopPlay(fullscreenViewHolder, device);
            DisplayUtils.a(fullscreenViewHolder.f, 100, 8);
            DisplayUtils.a(fullscreenViewHolder.g, 100, 8);
            fullscreenViewHolder.m.setVisibility(0);
        } else if (!CameraFrameManager.a().b()) {
            if (device == null || this.g || device.isSetPinCode == 0 || this.b.contains(device.did)) {
                startPlay(fullscreenViewHolder, device);
                DisplayUtils.a(fullscreenViewHolder.f, 100);
                DisplayUtils.a(fullscreenViewHolder.g, 100);
                fullscreenViewHolder.m.setVisibility(8);
            } else {
                Intent intent = new Intent(this, DevicePinVerifyEnterActivity.class);
                intent.putExtra("extra_device_did", device.did);
                intent.putExtra("verfy_pincode_first", true);
                startActivityForResult(intent, 100);
                this.c = fullscreenViewHolder;
                return;
            }
        } else {
            return;
        }
        if (CameraFrameManager.a().a(device)) {
            fullscreenViewHolder.i.setImageResource(R.drawable.camera_icon_btn_stop_2);
        } else {
            fullscreenViewHolder.i.setImageResource(R.drawable.camera_icon_btn_play_2);
        }
    }

    /* access modifiers changed from: package-private */
    public void addVideoView(final FullscreenViewHolder fullscreenViewHolder) {
        fullscreenViewHolder.h = new VideoView(getContext());
        int measuredWidth = this.mRecyclerView.getMeasuredWidth();
        fullscreenViewHolder.h.setVideoFrameSize(measuredWidth, (measuredWidth * 9) / 16, true);
        fullscreenViewHolder.h.setVideoViewListener(new VideoView.IVideoViewListener() {
            public void a() {
                if (fullscreenViewHolder.f.getVisibility() == 0) {
                    DisplayUtils.a(fullscreenViewHolder.f, 100, 8);
                    DisplayUtils.a(fullscreenViewHolder.g, 100, 8);
                    return;
                }
                DisplayUtils.a(fullscreenViewHolder.f, 100);
                DisplayUtils.a(fullscreenViewHolder.g, 100);
            }
        });
        fullscreenViewHolder.e.addView(fullscreenViewHolder.h);
    }

    /* access modifiers changed from: package-private */
    public void startPlay(FullscreenViewHolder fullscreenViewHolder, Device device) {
        if (fullscreenViewHolder.h == null) {
            addVideoView(fullscreenViewHolder);
        }
        CameraFrameManager.a().a(device, fullscreenViewHolder.h);
        CameraFrameManager.a().b(getContext(), device, fullscreenViewHolder.k);
    }

    /* access modifiers changed from: package-private */
    public void removeVideoView(FullscreenViewHolder fullscreenViewHolder) {
        fullscreenViewHolder.e.removeAllViews();
        fullscreenViewHolder.h = null;
    }

    /* access modifiers changed from: package-private */
    public void stopPlay(FullscreenViewHolder fullscreenViewHolder, Device device) {
        CameraFrameManager.a().b(device, fullscreenViewHolder.h);
        CameraFrameManager.a().a(getContext(), device);
        removeVideoView(fullscreenViewHolder);
    }

    public static class FullscreenViewHolder extends CameraLargeAdapter.MyBaseViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public TextView f19253a;
        public TextView b;
        public TextView c;
        public ImageView d;
        public FrameLayout e;
        public View f;
        public View g;
        public VideoView h;
        public ImageView i;
        public ImageView j;
        public ImageView k;
        public ImageView l;
        public ImageView m;
        public boolean n = false;

        public FullscreenViewHolder(View view) {
            super(view);
            if (view instanceof ViewGroup) {
                this.b = (TextView) view.findViewById(R.id.tvStatus);
                this.f19253a = (TextView) view.findViewById(R.id.title);
                this.c = (TextView) view.findViewById(R.id.device_name);
                this.f = view.findViewById(R.id.btn_large_view);
                this.e = (FrameLayout) view.findViewById(R.id.video_view_container);
                this.g = view.findViewById(R.id.control_indicator);
                this.d = (ImageView) view.findViewById(R.id.video_view_cover);
                this.i = (ImageView) view.findViewById(R.id.pause_button);
                this.j = (ImageView) view.findViewById(R.id.mute_button);
                this.k = (ImageView) view.findViewById(R.id.camera_loading);
                this.l = (ImageView) view.findViewById(R.id.ivExitFullscreenButton);
                this.m = (ImageView) view.findViewById(R.id.ivPlayCenter);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        Device device;
        if (i2 == -1 && i == 100 && this.c != null && (device = (Device) this.c.v.getTag()) != null) {
            startPlay(this.c, device);
            this.c.i.setImageResource(R.drawable.camera_icon_btn_stop_2);
            this.b.add(device.did);
        }
    }
}
