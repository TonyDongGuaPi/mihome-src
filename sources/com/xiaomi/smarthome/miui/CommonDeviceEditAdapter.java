package com.xiaomi.smarthome.miui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.DeviceUtils;
import com.xiaomi.smarthome.device.MiioDeviceV2;
import com.xiaomi.smarthome.device.utils.IRDeviceUtil;
import com.xiaomi.smarthome.homeroom.CommonUseDeviceDataManager;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.GridViewData;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.library.common.util.SharePrefsManager;
import com.xiaomi.smarthome.listcamera.CameraGroupManager;
import com.xiaomi.smarthome.newui.dragsort.ItemTouchHelperAdapter;
import com.xiaomi.smarthome.newui.dragsort.ItemTouchHelperViewHolder;
import com.xiaomi.smarthome.newui.dragsort.OnStartDragListener;
import com.xiaomi.smarthome.service.tasks.GetDeviceTask;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommonDeviceEditAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ItemTouchHelperAdapter {

    /* renamed from: a  reason: collision with root package name */
    public static int f20092a = 0;
    public static int b = 0;
    public static final String c = "[一-龥]";
    public static final String d = "[a-zA-Z]";
    public static final String e = "[0-9]";
    /* access modifiers changed from: private */
    public Context f;
    /* access modifiers changed from: private */
    public final WeakReference<AutoMaskLinearLayout> g;
    private List<GridViewData> h = new ArrayList();
    /* access modifiers changed from: private */
    public OnStartDragListener i;
    private Device j;
    /* access modifiers changed from: private */
    public boolean k = false;
    private boolean l = false;
    private int m = 0;
    private BroadcastReceiver n = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (TextUtils.equals(intent.getAction(), CommonUseDeviceDataManager.b)) {
                if (!TextUtils.equals(intent.getStringExtra(CommonUseDeviceDataManager.c), CommonUseDeviceDataManager.e)) {
                    boolean unused = CommonDeviceEditAdapter.this.k = true;
                }
            } else if (CommonDeviceEditAdapter.this.g.get() != null) {
                ((AutoMaskLinearLayout) CommonDeviceEditAdapter.this.g.get()).setShowLoading(false);
            }
            CommonDeviceEditAdapter.this.c();
            CommonDeviceEditAdapter.this.notifyDataSetChanged();
        }
    };
    /* access modifiers changed from: private */
    public boolean o = true;

    public void a(int i2) {
    }

    public void a(int[] iArr) {
    }

    public void b() {
    }

    public CommonDeviceEditAdapter(Context context, AutoMaskLinearLayout autoMaskLinearLayout, boolean z, int i2) {
        this.f = context;
        this.g = new WeakReference<>(autoMaskLinearLayout);
        IntentFilter intentFilter = new IntentFilter(CommonUseDeviceDataManager.b);
        intentFilter.addAction(HomeManager.S);
        LocalBroadcastManager.getInstance(this.f).registerReceiver(this.n, intentFilter);
        setHasStableIds(true);
        this.l = z;
        b = i2;
    }

    public void a(OnStartDragListener onStartDragListener) {
        this.i = onStartDragListener;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        LayoutInflater from = LayoutInflater.from(this.f);
        if (i2 == GridViewData.GridType.TYPE_TIPS.ordinal()) {
            return new MyTipsViewHolder(from.inflate(R.layout.common_device_edit_tips_item, viewGroup, false));
        }
        if (i2 == GridViewData.GridType.TYPE_CAMERA.ordinal()) {
            return new CameraViewHolder(from.inflate(R.layout.common_device_item_camera, viewGroup, false));
        }
        return new MyViewHolder(from.inflate(R.layout.common_device_edit_item, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i2) {
        viewHolder.itemView.setVisibility(0);
        GridViewData gridViewData = this.h.get(i2);
        if (gridViewData != null) {
            if (gridViewData.f18311a == GridViewData.GridType.TYPE_IR) {
                b(viewHolder, gridViewData);
                return;
            }
            if (gridViewData.f18311a == GridViewData.GridType.TYPE_CAMERA) {
                c(viewHolder, gridViewData);
            }
            if (viewHolder instanceof MyViewHolder) {
                a(viewHolder, gridViewData);
            }
        }
    }

    private void a(final RecyclerView.ViewHolder viewHolder, GridViewData gridViewData) {
        final Device device = gridViewData.b;
        if (device != null) {
            MyViewHolder myViewHolder = (MyViewHolder) viewHolder;
            DeviceFactory.b(device.model, myViewHolder.b);
            myViewHolder.f20101a.setText(gridViewData.c);
            myViewHolder.f.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View view) {
                    if (CommonDeviceEditAdapter.this.i != null) {
                        CommonDeviceEditAdapter.this.i.onStartDrag(viewHolder);
                    }
                    boolean unused = CommonDeviceEditAdapter.this.o = true;
                    return false;
                }
            });
            myViewHolder.d.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    CommonEditDialogHelper.a(CommonDeviceEditAdapter.this.f, device);
                }
            });
        }
    }

    private void b(final RecyclerView.ViewHolder viewHolder, GridViewData gridViewData) {
        MyViewHolder myViewHolder = (MyViewHolder) viewHolder;
        myViewHolder.f20101a.setText(R.string.phone_ir_device);
        myViewHolder.b.setImageResource(R.drawable.device_icon_ir_nor);
        final Device b2 = gridViewData.b != null ? gridViewData.b : IRDeviceUtil.b();
        myViewHolder.d.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CommonEditDialogHelper.a(CommonDeviceEditAdapter.this.f, b2);
            }
        });
        myViewHolder.f.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                if (CommonDeviceEditAdapter.this.i == null) {
                    return false;
                }
                CommonDeviceEditAdapter.this.i.onStartDrag(viewHolder);
                return false;
            }
        });
    }

    public long getItemId(int i2) {
        if (this.h.get(i2).f18311a == GridViewData.GridType.TYPE_CAMERA || this.h.get(i2).f18311a == GridViewData.GridType.TYPE_TIPS || this.h.get(i2).f18311a == GridViewData.GridType.TYPE_IR) {
            return (long) this.h.get(i2).f18311a.ordinal();
        }
        return (long) this.h.get(i2).b.did.hashCode();
    }

    private void c(RecyclerView.ViewHolder viewHolder, GridViewData gridViewData) {
        List<CameraGroupManager.GroupInfo> c2 = CameraGroupManager.a().c();
        CameraViewHolder cameraViewHolder = (CameraViewHolder) viewHolder;
        cameraViewHolder.e.setText(this.j.name);
        cameraViewHolder.b.setOnLongClickListener(new View.OnLongClickListener(viewHolder) {
            private final /* synthetic */ RecyclerView.ViewHolder f$1;

            {
                this.f$1 = r2;
            }

            public final boolean onLongClick(View view) {
                return CommonDeviceEditAdapter.this.a(this.f$1, view);
            }
        });
        cameraViewHolder.g.setText(this.f.getResources().getQuantityString(R.plurals.common_edit_camera_size, c2.size(), new Object[]{Integer.valueOf(c2.size())}));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean a(RecyclerView.ViewHolder viewHolder, View view) {
        if (this.i == null) {
            return false;
        }
        this.i.onStartDrag(viewHolder);
        return false;
    }

    public int getItemCount() {
        return this.h.size();
    }

    public int getItemViewType(int i2) {
        return this.h.get(i2).f18311a.ordinal();
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                public int getSpanSize(int i) {
                    int itemViewType = CommonDeviceEditAdapter.this.getItemViewType(i);
                    if (itemViewType == GridViewData.GridType.TYPE_TIPS.ordinal() || itemViewType == GridViewData.GridType.TYPE_CAMERA.ordinal()) {
                        return gridLayoutManager.getSpanCount();
                    }
                    return 1;
                }
            });
        }
    }

    public boolean a(int i2, int i3) {
        try {
            if (this.h.get(i2).f18311a == GridViewData.GridType.TYPE_TIPS) {
                return false;
            }
            if (this.h.get(i2).f18311a == GridViewData.GridType.TYPE_CAMERA) {
                if (this.h.get(i3).f18311a != GridViewData.GridType.TYPE_TIPS) {
                    return false;
                }
                if (i2 < i3) {
                    SharePrefsManager.a(SHApplication.getAppContext().getSharedPreferences(GetDeviceTask.b, 0), GetDeviceTask.f22065a, false);
                    if (this.h.size() > b) {
                        int i4 = f20092a + 1;
                        f20092a = b;
                        int i5 = (this.m >= b ? f20092a : this.m) + 1;
                        this.h.add(i5, this.h.remove(i4));
                        notifyItemMoved(i4, i5);
                        this.h.add(i5, this.h.remove(i2));
                        notifyItemMoved(i2, i5);
                    } else {
                        this.h.add(i3, this.h.remove(i2));
                        notifyItemMoved(i2, i3);
                    }
                    f20092a = b;
                    ((AutoMaskLinearLayout) this.g.get()).setRearrangeView();
                } else {
                    SharePrefsManager.a(SHApplication.getAppContext().getSharedPreferences(GetDeviceTask.b, 0), GetDeviceTask.f22065a, true);
                    if (this.h.size() > b) {
                        this.h.add(0, this.h.remove(i2));
                        notifyItemMoved(i2, 0);
                        f20092a = b - 2;
                        this.h.add(f20092a + 1, this.h.remove(i2));
                        notifyItemMoved(i2, f20092a + 1);
                    } else {
                        this.h.add(0, this.h.remove(i2));
                        notifyItemMoved(i2, 0);
                    }
                    f20092a = b - 2;
                    ((AutoMaskLinearLayout) this.g.get()).setRearrangeView();
                }
                return true;
            } else if (this.h.get(i3).f18311a == GridViewData.GridType.TYPE_TIPS || this.h.get(i3).f18311a == GridViewData.GridType.TYPE_CAMERA) {
                return false;
            } else {
                if (i2 < i3) {
                    int i6 = i2;
                    for (int i7 = i2 + 1; i7 <= i3; i7++) {
                        if (this.h.get(i7).f18311a != GridViewData.GridType.TYPE_TIPS) {
                            if (this.h.get(i7).f18311a != GridViewData.GridType.TYPE_CAMERA) {
                                Collections.swap(this.h, i6, i7);
                                i6 = i7;
                            }
                        }
                    }
                } else {
                    int i8 = i2;
                    for (int i9 = i2 - 1; i9 >= i3; i9--) {
                        if (this.h.get(i9).f18311a != GridViewData.GridType.TYPE_TIPS) {
                            if (this.h.get(i9).f18311a != GridViewData.GridType.TYPE_CAMERA) {
                                Collections.swap(this.h, i8, i9);
                                i8 = i9;
                            }
                        }
                    }
                }
                Log.e("CommonDeviceEditAdapter", "item moved from " + i2 + " to " + i3);
                notifyItemMoved(i2, i3);
                return true;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public void a() {
        this.o = false;
        if (this.h != null && !this.h.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < this.h.size(); i2++) {
                GridViewData gridViewData = this.h.get(i2);
                if (!(gridViewData == null || gridViewData.f18311a == GridViewData.GridType.TYPE_ADD_TO_COMMON || gridViewData.f18311a == GridViewData.GridType.TYPE_TIPS)) {
                    if (gridViewData.f18311a == GridViewData.GridType.TYPE_NORMAL && gridViewData.b != null) {
                        arrayList.add(gridViewData.b.did);
                    } else if (gridViewData.f18311a == GridViewData.GridType.TYPE_IR) {
                        MiioDeviceV2 miioDeviceV2 = new MiioDeviceV2();
                        miioDeviceV2.name = this.f.getString(R.string.group_type_phoneir);
                        miioDeviceV2.did = DeviceUtils.a();
                        miioDeviceV2.model = IRDeviceUtil.a();
                        arrayList.add(CommonUseDeviceDataManager.g);
                    }
                }
            }
            CommonUseDeviceDataManager.a().a((List<String>) arrayList, HomeManager.a().m());
            this.k = true;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x0098  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00c5  */
    @android.support.annotation.UiThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void c() {
        /*
            r7 = this;
            com.xiaomi.smarthome.homeroom.HomeManager r0 = com.xiaomi.smarthome.homeroom.HomeManager.a()
            java.util.List r0 = r0.F()
            com.xiaomi.smarthome.listcamera.CameraGroupManager r1 = com.xiaomi.smarthome.listcamera.CameraGroupManager.a()
            java.util.List r1 = r1.c()
            int r2 = r0.size()
            r7.m = r2
            java.lang.ref.WeakReference<com.xiaomi.smarthome.miui.AutoMaskLinearLayout> r2 = r7.g
            java.lang.Object r2 = r2.get()
            r3 = 0
            if (r2 == 0) goto L_0x003f
            java.lang.ref.WeakReference<com.xiaomi.smarthome.miui.AutoMaskLinearLayout> r2 = r7.g
            java.lang.Object r2 = r2.get()
            com.xiaomi.smarthome.miui.AutoMaskLinearLayout r2 = (com.xiaomi.smarthome.miui.AutoMaskLinearLayout) r2
            int r4 = r0.size()
            r2.setDeviceCount(r4)
            int r2 = r0.size()
            if (r2 <= 0) goto L_0x003f
            java.lang.ref.WeakReference<com.xiaomi.smarthome.miui.AutoMaskLinearLayout> r2 = r7.g
            java.lang.Object r2 = r2.get()
            com.xiaomi.smarthome.miui.AutoMaskLinearLayout r2 = (com.xiaomi.smarthome.miui.AutoMaskLinearLayout) r2
            r2.setShowLoading(r3)
        L_0x003f:
            boolean r2 = r7.l
            r4 = 1
            if (r2 == 0) goto L_0x0071
            int r2 = r0.size()
            if (r2 <= 0) goto L_0x0071
            android.content.Context r2 = com.xiaomi.smarthome.application.SHApplication.getAppContext()
            java.lang.String r5 = "pref_show_camera"
            android.content.SharedPreferences r2 = r2.getSharedPreferences(r5, r3)
            java.lang.String r5 = "key_show_camera"
            boolean r2 = com.xiaomi.smarthome.library.common.util.SharePrefsManager.b((android.content.SharedPreferences) r2, (java.lang.String) r5, (boolean) r4)
            if (r2 == 0) goto L_0x0067
            if (r1 == 0) goto L_0x0067
            int r2 = r1.size()
            if (r2 <= 0) goto L_0x0067
            r2 = 0
            r5 = 1
            goto L_0x0073
        L_0x0067:
            if (r1 == 0) goto L_0x0071
            int r2 = r1.size()
            if (r2 <= 0) goto L_0x0071
            r2 = 1
            goto L_0x0072
        L_0x0071:
            r2 = 0
        L_0x0072:
            r5 = 0
        L_0x0073:
            if (r1 == 0) goto L_0x008d
            int r6 = r1.size()
            if (r6 <= 0) goto L_0x008d
            com.xiaomi.smarthome.device.SmartHomeDeviceManager r6 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.a()
            java.lang.Object r1 = r1.get(r3)
            com.xiaomi.smarthome.listcamera.CameraGroupManager$GroupInfo r1 = (com.xiaomi.smarthome.listcamera.CameraGroupManager.GroupInfo) r1
            java.lang.String r1 = r1.f19240a
            com.xiaomi.smarthome.device.Device r1 = r6.b((java.lang.String) r1)
            r7.j = r1
        L_0x008d:
            com.xiaomi.smarthome.homeroom.model.GridViewData r1 = new com.xiaomi.smarthome.homeroom.model.GridViewData
            r1.<init>()
            com.xiaomi.smarthome.homeroom.model.GridViewData$GridType r6 = com.xiaomi.smarthome.homeroom.model.GridViewData.GridType.TYPE_TIPS
            r1.f18311a = r6
            if (r2 == 0) goto L_0x00c5
            int r2 = b
            f20092a = r2
            int r2 = r0.size()
            int r3 = f20092a
            if (r2 <= r3) goto L_0x00b7
            int r2 = f20092a
            r0.add(r2, r1)
            int r1 = f20092a
            int r1 = r1 + r4
            com.xiaomi.smarthome.homeroom.model.GridViewData r2 = new com.xiaomi.smarthome.homeroom.model.GridViewData
            com.xiaomi.smarthome.homeroom.model.GridViewData$GridType r3 = com.xiaomi.smarthome.homeroom.model.GridViewData.GridType.TYPE_CAMERA
            r2.<init>(r3)
            r0.add(r1, r2)
            goto L_0x00fb
        L_0x00b7:
            r0.add(r1)
            com.xiaomi.smarthome.homeroom.model.GridViewData r1 = new com.xiaomi.smarthome.homeroom.model.GridViewData
            com.xiaomi.smarthome.homeroom.model.GridViewData$GridType r2 = com.xiaomi.smarthome.homeroom.model.GridViewData.GridType.TYPE_CAMERA
            r1.<init>(r2)
            r0.add(r1)
            goto L_0x00fb
        L_0x00c5:
            if (r5 == 0) goto L_0x00e9
            int r2 = b
            int r2 = r2 + -2
            f20092a = r2
            int r2 = r0.size()
            int r4 = f20092a
            if (r2 <= r4) goto L_0x00db
            int r2 = f20092a
            r0.add(r2, r1)
            goto L_0x00de
        L_0x00db:
            r0.add(r1)
        L_0x00de:
            com.xiaomi.smarthome.homeroom.model.GridViewData r1 = new com.xiaomi.smarthome.homeroom.model.GridViewData
            com.xiaomi.smarthome.homeroom.model.GridViewData$GridType r2 = com.xiaomi.smarthome.homeroom.model.GridViewData.GridType.TYPE_CAMERA
            r1.<init>(r2)
            r0.add(r3, r1)
            goto L_0x00fb
        L_0x00e9:
            int r2 = b
            f20092a = r2
            int r2 = r7.m
            int r3 = f20092a
            if (r2 <= r3) goto L_0x00f6
            int r2 = f20092a
            goto L_0x00f8
        L_0x00f6:
            int r2 = r7.m
        L_0x00f8:
            r0.add(r2, r1)
        L_0x00fb:
            r7.h = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.miui.CommonDeviceEditAdapter.c():void");
    }

    public boolean d() {
        return this.o;
    }

    public void e() {
        LocalBroadcastManager.getInstance(this.f).unregisterReceiver(this.n);
    }

    public int b(int i2) {
        boolean z = false;
        int i3 = 0;
        boolean z2 = false;
        while (i3 < this.h.size()) {
            if (i3 != i2) {
                if (this.h.get(i3).f18311a == GridViewData.GridType.TYPE_CAMERA || this.h.get(i3).f18311a == GridViewData.GridType.TYPE_TIPS) {
                    z2 = !z2;
                }
                i3++;
            } else if (this.h.get(i3).f18311a == GridViewData.GridType.TYPE_CAMERA || this.h.get(i3).f18311a == GridViewData.GridType.TYPE_TIPS) {
                return 0;
            } else {
                if (i3 % 2 == 0) {
                    z = true;
                }
                if (z ^ z2) {
                    return 1;
                }
                return 2;
            }
        }
        return 0;
    }

    public boolean f() {
        return this.k;
    }

    private static int a(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 0; i6 < str.length(); i6++) {
            String valueOf = String.valueOf(str.charAt(i6));
            if (valueOf.matches("[一-龥]")) {
                i2++;
            } else if (valueOf.matches("[a-zA-Z]")) {
                i3++;
            } else if (valueOf.matches("[0-9]")) {
                i4++;
            } else {
                i5++;
            }
        }
        return (i2 * 2) + i3 + i4 + (i5 * 2);
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {

        /* renamed from: a  reason: collision with root package name */
        TextView f20101a;
        SimpleDraweeView b;
        ImageView c;
        View d;
        View e;
        RelativeLayout f;

        public MyViewHolder(View view) {
            super(view);
            this.e = view;
            this.f = (RelativeLayout) view.findViewById(R.id.rv_content);
            this.f20101a = (TextView) view.findViewById(R.id.tv_device_name);
            this.c = (ImageView) view.findViewById(R.id.iv_edit);
            this.d = view.findViewById(R.id.fl_edit);
            this.b = (SimpleDraweeView) view.findViewById(R.id.icon);
            Home m = HomeManager.a().m();
            if (m != null) {
                this.d.setVisibility(m.p() ? 0 : 8);
            }
        }

        public void a() {
            this.itemView.setSelected(true);
        }

        public void b() {
            this.itemView.setSelected(false);
        }
    }

    class MyTipsViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        View f20100a;

        public MyTipsViewHolder(View view) {
            super(view);
            this.f20100a = view;
        }
    }

    class CameraViewHolder extends RecyclerView.ViewHolder {
        /* access modifiers changed from: private */
        public View b;
        private ImageView c = ((ImageView) this.b.findViewById(R.id.camera_image_view));
        private ImageView d;
        /* access modifiers changed from: private */
        public TextView e = ((TextView) this.b.findViewById(R.id.device_name));
        private ImageView f = ((ImageView) this.b.findViewById(R.id.device_controller));
        /* access modifiers changed from: private */
        public TextView g = ((TextView) this.b.findViewById(R.id.all_camera_size));
        private ImageView h;
        private View i = this.b.findViewById(R.id.alert_container);

        public CameraViewHolder(View view) {
            super(view);
            this.b = view;
        }
    }
}
