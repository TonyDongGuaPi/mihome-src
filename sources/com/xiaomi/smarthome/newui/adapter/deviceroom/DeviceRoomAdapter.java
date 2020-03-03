package com.xiaomi.smarthome.newui.adapter.deviceroom;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.config.AndroidCommonConfigManager;
import com.xiaomi.smarthome.config.model.DeviceRoomConfig;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.renderer.DeviceRenderer;
import com.xiaomi.smarthome.frame.login.LoginApi;
import com.xiaomi.smarthome.framework.page.CommonActivity;
import com.xiaomi.smarthome.homeroom.HomeDefaultRoomDeviceTransferGuide;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.HomeRoomManageListActivity;
import com.xiaomi.smarthome.homeroom.HomeRoomRecommendActivity;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.library.DarkModeCompat;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.newui.RoomDetialsActivity;
import com.xiaomi.smarthome.newui.adapter.deviceroom.DeviceRoomAdapter;
import com.xiaomi.smarthome.newui.roomenv.RoomEnvManager;
import com.xiaomi.smarthome.newui.roomenv.model.RoomEnvData;
import com.xiaomi.smarthome.stat.STAT;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DeviceRoomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /* renamed from: a  reason: collision with root package name */
    protected WeakReference<CommonActivity> f20396a;
    private Context b;
    private List<AbstractDeviceRoomAdapterData> c = new ArrayList();
    private BroadcastReceiver d = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            DeviceRoomAdapter.this.b();
        }
    };

    public DeviceRoomAdapter(Context context) {
        this.b = context;
        this.f20396a = new WeakReference<>((CommonActivity) context);
        LocalBroadcastManager.getInstance(context.getApplicationContext()).registerReceiver(this.d, new IntentFilter(RoomEnvManager.f20702a));
    }

    /* access modifiers changed from: private */
    public void b() {
        Map<String, RoomEnvData> a2 = RoomEnvManager.a().a(HomeManager.a().l());
        if (a2 != null) {
            List<AbstractDeviceRoomAdapterData> list = this.c;
            for (int i = 0; i < list.size(); i++) {
                AbstractDeviceRoomAdapterData abstractDeviceRoomAdapterData = list.get(i);
                if (abstractDeviceRoomAdapterData != null && (abstractDeviceRoomAdapterData instanceof NormalRoomData)) {
                    NormalRoomData normalRoomData = (NormalRoomData) abstractDeviceRoomAdapterData;
                    if (normalRoomData.b != null && !TextUtils.isEmpty(normalRoomData.b.d())) {
                        normalRoomData.c = a2.get(normalRoomData.b.d());
                    }
                }
            }
            notifyDataSetChanged();
        }
    }

    public void a() {
        ArrayList arrayList;
        DeviceRoomData d2 = DeviceRoomData.d();
        Home m = HomeManager.a().m();
        ArrayList arrayList2 = new ArrayList();
        if (d2 == null) {
            arrayList2.add(new EmptyData(EmptyViewHolder.class.hashCode()));
        } else {
            List<Room> b2 = d2.b();
            if (b2 == null) {
                arrayList = null;
            } else {
                arrayList = new ArrayList(b2);
            }
            if (arrayList == null || arrayList.isEmpty()) {
                arrayList2.add(new EmptyData(EmptyViewHolder.class.hashCode()));
            } else {
                int size = d2.a() == null ? 0 : d2.a().size();
                if (size > 0 && m.p()) {
                    arrayList2.add(new DefaultRoomData(size, DefaultRoomDeviceViewHolder.class.hashCode()));
                }
                for (int i = 0; i < arrayList.size(); i++) {
                    NormalRoomData normalRoomData = new NormalRoomData((Room) arrayList.get(i), DeviceRoomViewHolder.class.hashCode());
                    Map<String, RoomEnvData> a2 = RoomEnvManager.a().a(HomeManager.a().l());
                    if (a2 != null) {
                        normalRoomData.c = a2.get(normalRoomData.b.d());
                    }
                    arrayList2.add(normalRoomData);
                }
                if (m.p()) {
                    arrayList2.add(new ManageRoomData(ManageRoomViewHolder.class.hashCode()));
                }
            }
        }
        this.c = arrayList2;
        notifyDataSetChanged();
    }

    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == DefaultRoomDeviceViewHolder.class.hashCode()) {
            return new DefaultRoomDeviceViewHolder(LayoutInflater.from(this.b.getApplicationContext()).inflate(R.layout.device_room_no_room_tips_layout, viewGroup, false), (CommonActivity) this.f20396a.get());
        }
        if (i == ManageRoomViewHolder.class.hashCode()) {
            return new ManageRoomViewHolder(LayoutInflater.from(this.b.getApplicationContext()).inflate(R.layout.device_room_manage_room_layout, viewGroup, false), (CommonActivity) this.f20396a.get());
        }
        if (i == DeviceRoomViewHolder.class.hashCode()) {
            return new DeviceRoomViewHolder(LayoutInflater.from(this.b.getApplicationContext()).inflate(R.layout.device_room_list_item_layout, viewGroup, false), (CommonActivity) this.f20396a.get());
        }
        if (i == EmptyViewHolder.class.hashCode()) {
            return new EmptyViewHolder(LayoutInflater.from(this.b.getApplicationContext()).inflate(R.layout.device_room_empty_layout, viewGroup, false), (CommonActivity) this.f20396a.get());
        }
        return null;
    }

    private AbstractDeviceRoomAdapterData a(int i) {
        if (i < 0 || i >= this.c.size()) {
            return null;
        }
        return this.c.get(i);
    }

    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        AbstractDeviceRoomAdapterData a2 = a(i);
        if ((viewHolder instanceof DefaultRoomDeviceViewHolder) && (a2 instanceof DefaultRoomData)) {
            ((DefaultRoomDeviceViewHolder) viewHolder).a(((DefaultRoomData) a2).b);
        } else if (a2 != null && (viewHolder instanceof DeviceRoomViewHolder) && (a2 instanceof NormalRoomData)) {
            ((DeviceRoomViewHolder) viewHolder).a((NormalRoomData) a2);
        } else if (viewHolder instanceof EmptyViewHolder) {
            ((EmptyViewHolder) viewHolder).a();
        }
    }

    public int getItemViewType(int i) {
        AbstractDeviceRoomAdapterData a2 = a(i);
        if (a2 == null) {
            return 0;
        }
        return a2.f20398a;
    }

    public int getItemCount() {
        return this.c.size();
    }

    static class BaseViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        protected final WeakReference<CommonActivity> f20399a;

        public BaseViewHolder(@NonNull View view, CommonActivity commonActivity) {
            super(view);
            this.f20399a = new WeakReference<>(commonActivity);
        }
    }

    static class DefaultRoomDeviceViewHolder extends BaseViewHolder {
        private TextView b;

        public DefaultRoomDeviceViewHolder(@NonNull View view, CommonActivity commonActivity) {
            super(view, commonActivity);
            this.b = (TextView) view.findViewById(R.id.count_tips);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:5:0x001f, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:6:0x0020, code lost:
            r6 = r1;
            r1 = r0;
            r0 = r6;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:7:0x0024, code lost:
            r8 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:8:0x0025, code lost:
            r8.printStackTrace();
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Removed duplicated region for block: B:7:0x0024 A[ExcHandler: Exception (r8v3 'e' java.lang.Exception A[CUSTOM_DECLARE]), Splitter:B:1:0x0002] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a(int r8) {
            /*
                r7 = this;
                java.lang.String r0 = ""
                android.widget.TextView r1 = r7.b     // Catch:{ UnknownFormatConversionException -> 0x0029, Exception -> 0x0024 }
                android.content.res.Resources r1 = r1.getResources()     // Catch:{ UnknownFormatConversionException -> 0x0029, Exception -> 0x0024 }
                r2 = 2132017184(0x7f140020, float:1.967264E38)
                r3 = 1
                java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ UnknownFormatConversionException -> 0x0029, Exception -> 0x0024 }
                r4 = 0
                java.lang.Integer r5 = java.lang.Integer.valueOf(r8)     // Catch:{ UnknownFormatConversionException -> 0x0029, Exception -> 0x0024 }
                r3[r4] = r5     // Catch:{ UnknownFormatConversionException -> 0x0029, Exception -> 0x0024 }
                java.lang.String r1 = r1.getQuantityString(r2, r8, r3)     // Catch:{ UnknownFormatConversionException -> 0x0029, Exception -> 0x0024 }
                android.widget.TextView r0 = r7.b     // Catch:{ UnknownFormatConversionException -> 0x001f, Exception -> 0x0024 }
                r0.setText(r1)     // Catch:{ UnknownFormatConversionException -> 0x001f, Exception -> 0x0024 }
                goto L_0x0059
            L_0x001f:
                r0 = move-exception
                r6 = r1
                r1 = r0
                r0 = r6
                goto L_0x002a
            L_0x0024:
                r8 = move-exception
                r8.printStackTrace()
                goto L_0x0059
            L_0x0029:
                r1 = move-exception
            L_0x002a:
                java.lang.String r2 = "UnknownFormatConversionException"
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>()
                java.lang.String r4 = "String = "
                r3.append(r4)
                r3.append(r0)
                java.lang.String r0 = r3.toString()
                com.xiaomi.smarthome.framework.log.LogUtil.b(r2, r0)
                java.lang.String r0 = "UnknownFormatConversionException"
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                r2.<init>()
                java.lang.String r3 = "cnt = "
                r2.append(r3)
                r2.append(r8)
                java.lang.String r8 = r2.toString()
                com.xiaomi.smarthome.framework.log.LogUtil.b(r0, r8)
                r1.printStackTrace()
            L_0x0059:
                android.view.View r8 = r7.itemView
                com.xiaomi.smarthome.newui.adapter.deviceroom.-$$Lambda$DeviceRoomAdapter$DefaultRoomDeviceViewHolder$9MvAuyFZOu0hdOAC3fZAep6a2MQ r0 = new com.xiaomi.smarthome.newui.adapter.deviceroom.-$$Lambda$DeviceRoomAdapter$DefaultRoomDeviceViewHolder$9MvAuyFZOu0hdOAC3fZAep6a2MQ
                r0.<init>()
                r8.setOnClickListener(r0)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.newui.adapter.deviceroom.DeviceRoomAdapter.DefaultRoomDeviceViewHolder.a(int):void");
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(View view) {
            HomeDefaultRoomDeviceTransferGuide.start(this.itemView.getContext());
            STAT.d.f(HomeManager.a().j().size());
        }
    }

    static class ManageRoomViewHolder extends BaseViewHolder {
        public ManageRoomViewHolder(@NonNull View view, CommonActivity commonActivity) {
            super(view, commonActivity);
            view.setOnClickListener(new View.OnClickListener(view) {
                private final /* synthetic */ View f$0;

                {
                    this.f$0 = r1;
                }

                public final void onClick(View view) {
                    DeviceRoomAdapter.ManageRoomViewHolder.a(this.f$0, view);
                }
            });
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ void a(@NonNull View view, View view2) {
            HomeRoomManageListActivity.startActivity(view.getContext(), HomeManager.a().l());
            STAT.d.aG();
        }
    }

    static class DeviceRoomViewHolder extends BaseViewHolder {
        private TextView b;
        private TextView c;
        private View d;
        private View e;
        private View f;
        private SimpleDraweeView[] g = new SimpleDraweeView[3];

        public DeviceRoomViewHolder(@NonNull View view, CommonActivity commonActivity) {
            super(view, commonActivity);
            this.b = (TextView) view.findViewById(R.id.room_name_tv);
            this.c = (TextView) view.findViewById(R.id.room_desc_tv);
            this.f = view.findViewById(R.id.device_list_container);
            this.g[0] = (SimpleDraweeView) view.findViewById(R.id.icon_1);
            this.g[1] = (SimpleDraweeView) view.findViewById(R.id.icon_2);
            this.g[2] = (SimpleDraweeView) view.findViewById(R.id.icon_3);
            this.d = view.findViewById(R.id.more_icon);
            this.e = view.findViewById(R.id.item_root);
        }

        public void a(final NormalRoomData normalRoomData) {
            if (normalRoomData != null && normalRoomData.b != null) {
                this.b.setText(normalRoomData.b.e());
                b(normalRoomData);
                a(normalRoomData.b);
                this.itemView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        RoomDetialsActivity.startActivity(DeviceRoomViewHolder.this.itemView.getContext(), normalRoomData.b.d());
                        STAT.d.e(normalRoomData.b.d(), normalRoomData.b.h().size());
                    }
                });
            }
        }

        private void b(NormalRoomData normalRoomData) {
            int i;
            List<Device> c2 = HomeManager.a().c(normalRoomData.b);
            if (c2 == null) {
                i = 0;
            } else {
                i = c2.size();
            }
            String e2 = normalRoomData.c == null ? null : normalRoomData.c.e();
            if (TextUtils.isEmpty(e2)) {
                this.c.setText(this.c.getContext().getResources().getQuantityString(R.plurals.home_device_size, i, new Object[]{Integer.valueOf(i)}));
                return;
            }
            TextView textView = this.c;
            textView.setText(this.c.getContext().getResources().getQuantityString(R.plurals.home_device_size, i, new Object[]{Integer.valueOf(i)}) + " | " + e2);
        }

        private void a(Room room) {
            List<Device> c2 = HomeManager.a().c(room);
            if (c2 == null || c2.isEmpty()) {
                this.e.setPadding(this.e.getPaddingLeft(), DisplayUtils.a(10.0f), this.e.getPaddingRight(), DisplayUtils.a(13.0f));
                this.f.setVisibility(4);
                return;
            }
            this.e.setPadding(this.e.getPaddingLeft(), DisplayUtils.a(10.0f), this.e.getPaddingRight(), DisplayUtils.a(15.0f));
            this.f.setVisibility(0);
            int i = 3;
            DeviceRoomConfig j = AndroidCommonConfigManager.a().j();
            if (j != null) {
                i = j.b();
            }
            for (int i2 = 0; i2 < i; i2++) {
                if (i2 < c2.size()) {
                    final Device device = c2.get(i2);
                    this.g[i2].setVisibility(0);
                    DeviceFactory.b(device.model, this.g[i2]);
                    this.g[i2].setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            DeviceRoomViewHolder.this.a(device);
                        }
                    });
                } else {
                    this.g[i2].setVisibility(4);
                }
            }
            if (c2.size() > i) {
                this.d.setVisibility(0);
            } else {
                this.d.setVisibility(8);
            }
        }

        /* access modifiers changed from: private */
        public void a(Device device) {
            STAT.d.aD(device.model);
            AndroidCommonConfigManager a2 = AndroidCommonConfigManager.a();
            if (a2 == null || a2.j() == null) {
                Room p = HomeManager.a().p(device.did);
                if (p != null) {
                    RoomDetialsActivity.startActivity(this.itemView.getContext(), p.d());
                    return;
                }
                return;
            }
            DeviceRoomConfig j = a2.j();
            CommonActivity commonActivity = (CommonActivity) this.f20399a.get();
            if (commonActivity == null || j.a() != 1) {
                Room p2 = HomeManager.a().p(device.did);
                if (p2 != null) {
                    RoomDetialsActivity.startActivity(this.itemView.getContext(), p2.d());
                    return;
                }
                return;
            }
            DeviceRenderer.a(device).a(this.itemView, commonActivity.mHandler, device, this.itemView.getContext());
        }
    }

    static class EmptyViewHolder extends BaseViewHolder {
        private final View b;

        public EmptyViewHolder(@NonNull View view, CommonActivity commonActivity) {
            super(view, commonActivity);
            this.b = view.findViewById(R.id.add_room_tv);
            ImageView imageView = (ImageView) view.findViewById(R.id.device_empty_bg);
            if (DarkModeCompat.a((Context) commonActivity)) {
                DarkModeCompat.a((View) imageView, false);
                imageView.setImageResource(R.drawable.device_room_empty_icon_dark);
            }
            this.b.setOnClickListener(new View.OnClickListener(view) {
                private final /* synthetic */ View f$0;

                {
                    this.f$0 = r1;
                }

                public final void onClick(View view) {
                    DeviceRoomAdapter.EmptyViewHolder.a(this.f$0, view);
                }
            });
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ void a(@NonNull View view, View view2) {
            if (SHApplication.getStateNotifier().a() != 4) {
                LoginApi.a().a(view.getContext(), 1, (LoginApi.LoginCallback) null);
            } else {
                HomeRoomRecommendActivity.startActivity(view.getContext(), HomeManager.a().l());
            }
        }

        public void a() {
            Home m = HomeManager.a().m();
            if (m != null) {
                this.b.setVisibility(m.p() ? 0 : 8);
            }
        }
    }

    private static abstract class AbstractDeviceRoomAdapterData {

        /* renamed from: a  reason: collision with root package name */
        protected final int f20398a;

        protected AbstractDeviceRoomAdapterData(int i) {
            this.f20398a = i;
        }
    }

    private static class DefaultRoomData extends AbstractDeviceRoomAdapterData {
        /* access modifiers changed from: private */
        public final int b;

        private DefaultRoomData(int i, int i2) {
            super(i2);
            this.b = i;
        }
    }

    private static class NormalRoomData extends AbstractDeviceRoomAdapterData {
        public final Room b;
        public RoomEnvData c;

        private NormalRoomData(Room room, int i) {
            super(i);
            this.b = room;
        }
    }

    private static class ManageRoomData extends AbstractDeviceRoomAdapterData {
        private ManageRoomData(int i) {
            super(i);
        }
    }

    private static class EmptyData extends AbstractDeviceRoomAdapterData {
        private EmptyData(int i) {
            super(i);
        }
    }
}
