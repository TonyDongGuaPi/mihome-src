package com.xiaomi.smarthome.newui;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.BleDevice;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.MiTVDevice;
import com.xiaomi.smarthome.device.RouterDevice;
import com.xiaomi.smarthome.device.WatchBandDevice;
import com.xiaomi.smarthome.device.utils.IRDeviceUtil;
import com.xiaomi.smarthome.homeroom.MultiHomeDeviceManager;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.miio.device.GeneralAPDevice;
import com.xiaomi.smarthome.newui.card.ProductModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class AddToCommonDialogHelper {

    /* renamed from: a  reason: collision with root package name */
    private static final String[] f20193a = {"chuangmi.plug.m1", "chuangmi.plug.v1", "lumi.plug.v1", "zimi.powerstrip.v2", "qmi.powerstrip.v1", "zhimi.airpurifier.m1", "zhimi.airpurifier.v1", "zhimi.airpurifier.v3", "zhimi.airpurifier.v6", "philips.light.mono1", "philips.light.sread1", "philips.light.ceiling", "philips.light.zyceiling", "philips.light.bulb", ProductModel.h, ProductModel.r, ProductModel.j, ProductModel.k, ProductModel.l, ProductModel.m, ProductModel.r, ProductModel.d, ProductModel.f, ProductModel.b, "lumi.acpartner.v1", "lumi.acpartner.v2", "innolinks.plug.ap3200", "lumi.ctrl_86plug.v1", "lumi.ctrl_ln1.v1", "lumi.ctrl_ln2.v1", "lumi.ctrl_neutral1.v1", "lumi.ctrl_neutral2.v1", "zhimi.aircondition.v1", "zhimi.aircondition.v2", "yeelink.light.virtual", "philips.light.virtual", "zhimi.airpurifier.virtual", "isa.camera.virtual"};
    private static final String[] b = {"mijia.camera.v1", "yunyi.camera.v1", "chuangmi.camera.xiaobai", "chuangmi.camera.v2", "chuangmi.camera.v5", "isa.camera.isc5", "isa.camera.df3", "chuangmi.plug.m1", "chuangmi.plug.v1", "lumi.plug.v1", "zimi.powerstrip.v2", "qmi.powerstrip.v1", "zhimi.airpurifier.m1", "zhimi.airpurifier.v1", "zhimi.airpurifier.v3", "zhimi.airpurifier.v6", "philips.light.bulb", "philips.light.mono1", "philips.light.sread1", "philips.light.ceiling", ProductModel.r, ProductModel.j, ProductModel.l, ProductModel.m, ProductModel.d, ProductModel.f, ProductModel.b, ProductModel.h, "zhimi.fan.v2", "zhimi.fan.v3", "lumi.gateway.v1", "lumi.gateway.v2", "lumi.gateway.v3", "lumi.acpartner.v1", "lumi.acpartner.v2", "innolinks.plug.ap3200", "lumi.ctrl_86plug.v1", "lumi.ctrl_ln1.v1", "lumi.ctrl_ln2.v1", "lumi.ctrl_neutral1.v1", "lumi.ctrl_neutral2.v1", "rockrobo.vacuum.v1", "zhimi.aircondition.v1", "zhimi.aircondition.v2", "midea.aircondition.v1", "midea.aircondition.xa1", "aux.aircondition.hc1", "aux.aircondition.v1", "idelan.aircondition.g1", "idelan.aircondition.g2", "idelan.aircondition.v1", "idelan.aircondition.v2", "chuangmi.radio.v1", "chuangmi.radio.v2", "jiqid.mistory.v1", "zhimi.humidifier.v1", "lumi.curtain.v1", "yeelink.light.virtual", "philips.light.virtual", "zhimi.airpurifier.virtual", "isa.camera.virtual"};
    private static final int c = 2130839148;
    private static final int d = 2130839147;
    private static final int e = 12;
    private static DeviceFilter t = new DeviceFilter() {
        public boolean a(Device device) {
            boolean z = !(device instanceof GeneralAPDevice);
            if (!device.isMiioBinded()) {
                z = false;
            }
            if (IRDeviceUtil.a(device.did)) {
                z = false;
            }
            if (device instanceof WatchBandDevice) {
                z = false;
            }
            if ((device instanceof MiTVDevice) && !device.isOwner()) {
                z = false;
            }
            if ((device instanceof RouterDevice) && !device.isOwner()) {
                z = false;
            }
            if ((device instanceof BleDevice) && ((BleDevice) device).k()) {
                z = false;
            }
            if (device.isVirtualDevice()) {
                z = false;
            }
            if (MultiHomeDeviceManager.a().d().contains(device) || MultiHomeDeviceManager.a().e().contains(device)) {
                return z;
            }
            return false;
        }
    };
    /* access modifiers changed from: private */
    public int f;
    private ViewPager g;
    /* access modifiers changed from: private */
    public List<Device> h = new ArrayList();
    /* access modifiers changed from: private */
    public List<List<Device>> i = new ArrayList();
    /* access modifiers changed from: private */
    public Context j;
    private MyPagerAdapter k;
    private ViewGroup l;
    /* access modifiers changed from: private */
    public ArrayList<ImageView> m = new ArrayList<>();
    /* access modifiers changed from: private */
    public int n = 0;
    /* access modifiers changed from: private */
    public Set<Integer> o = new HashSet();
    /* access modifiers changed from: private */
    public Set<Integer> p = new HashSet();
    /* access modifiers changed from: private */
    public OnConfirmListener q;
    /* access modifiers changed from: private */
    public View r;
    /* access modifiers changed from: private */
    public boolean s = true;
    private String[] u;

    interface DeviceFilter {
        boolean a(Device device);
    }

    public interface OnConfirmListener {
        void a();

        void a(List<Device> list);
    }

    public MLAlertDialog a(Context context, List<Device> list, OnConfirmListener onConfirmListener, List<String> list2, boolean z) {
        int i2;
        try {
            this.j = context;
            this.q = onConfirmListener;
            View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_add_common, (ViewGroup) null);
            this.s = z;
            this.g = (ViewPager) inflate.findViewById(R.id.pager);
            this.r = inflate.findViewById(R.id.advice);
            if (list.size() <= 10) {
                this.u = b;
            } else {
                this.u = f20193a;
            }
            ArrayList arrayList = new ArrayList(new HashSet(list));
            a((List<Device>) arrayList);
            if (arrayList.size() <= 0) {
                return null;
            }
            if (list2 == null || list2.size() <= 0) {
                c((List<Device>) arrayList);
            } else {
                a((List<Device>) arrayList, list2);
            }
            if (this.p.size() <= 0 || z) {
                this.r.setVisibility(4);
            }
            this.f = this.h.size() / 12;
            if (this.h.size() % 12 > 0) {
                this.f++;
            }
            this.l = (ViewGroup) inflate.findViewById(R.id.indicator);
            a();
            int i3 = this.f;
            int i4 = 0;
            while (i3 > 0) {
                ArrayList arrayList2 = new ArrayList();
                int i5 = 0;
                while (i4 < this.h.size() && i5 < 12) {
                    arrayList2.add(this.h.get(i4));
                    i5++;
                    i4++;
                }
                i3--;
                this.i.add(arrayList2);
            }
            this.k = new MyPagerAdapter();
            this.g.setAdapter(this.k);
            MLAlertDialog c2 = new MLAlertDialog.Builder(context).a((int) R.string.choose_to_add_device).b(inflate).a((DialogInterface.OnCancelListener) new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialogInterface) {
                    if (AddToCommonDialogHelper.this.q != null) {
                        AddToCommonDialogHelper.this.q.a();
                    }
                }
            }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (AddToCommonDialogHelper.this.q != null) {
                        AddToCommonDialogHelper.this.q.a();
                    }
                }
            }).a((int) R.string.confirm, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (AddToCommonDialogHelper.this.q != null) {
                        Integer[] numArr = (Integer[]) AddToCommonDialogHelper.this.o.toArray(new Integer[0]);
                        Arrays.sort(numArr);
                        ArrayList arrayList = new ArrayList();
                        for (Integer intValue : numArr) {
                            arrayList.add(AddToCommonDialogHelper.this.h.get(intValue.intValue()));
                        }
                        AddToCommonDialogHelper.this.q.a(arrayList);
                    }
                }
            }).c();
            c2.show();
            LinearLayout linearLayout = (LinearLayout) c2.getButton(-2).getParent();
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
            layoutParams.topMargin = 0;
            linearLayout.setLayoutParams(layoutParams);
            ViewGroup.LayoutParams layoutParams2 = ((ViewGroup) inflate.findViewById(R.id.pager)).getLayoutParams();
            int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.gird_item_height);
            if (this.h.size() > 12) {
                i2 = 3;
            } else {
                int size = this.h.size() / 4;
                i2 = this.h.size() % 4 > 0 ? size + 1 : size;
            }
            Log.i("dialog", "row:" + i2);
            layoutParams2.height = dimensionPixelSize * i2;
            return c2;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public void a(List<Device> list) {
        Iterator<Device> it = list.iterator();
        while (it.hasNext()) {
            if (!t.a(it.next())) {
                it.remove();
            }
        }
    }

    public static int b(List<Device> list) {
        if (list == null) {
            return 0;
        }
        int size = list.size();
        for (Device a2 : list) {
            if (!t.a(a2)) {
                size--;
            }
        }
        return size;
    }

    public static boolean a(Device device) {
        return device != null && !t.a(device);
    }

    private void a() {
        if (this.f >= 2) {
            for (int i2 = 0; i2 < this.f; i2++) {
                ImageView imageView = new ImageView(this.j);
                imageView.setPadding(5, 0, 5, 0);
                if (this.m.isEmpty()) {
                    imageView.setImageResource(R.drawable.common_dot_focused);
                } else {
                    imageView.setImageResource(R.drawable.common_dot_unfocus);
                }
                this.m.add(imageView);
                this.l.addView(imageView);
            }
            this.g.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                public void onPageScrollStateChanged(int i) {
                }

                public void onPageScrolled(int i, float f, int i2) {
                }

                public void onPageSelected(int i) {
                    for (int i2 = 0; i2 < AddToCommonDialogHelper.this.f; i2++) {
                        ImageView imageView = (ImageView) AddToCommonDialogHelper.this.m.get(i2);
                        if (i2 != i && AddToCommonDialogHelper.this.n == i2) {
                            imageView.setImageResource(R.drawable.common_dot_unfocus);
                        }
                        if (i == i2 && AddToCommonDialogHelper.this.n != i2) {
                            imageView.setImageResource(R.drawable.common_dot_focused);
                        }
                    }
                    int unused = AddToCommonDialogHelper.this.n = i;
                }
            });
        }
    }

    private void a(List<Device> list, List<String> list2) {
        ArrayList arrayList = new ArrayList(list);
        int i2 = 0;
        for (String str : new ArrayList(list2)) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                Device device = (Device) it.next();
                if (str.equals(device.did)) {
                    this.h.add(device);
                    it.remove();
                    this.o.add(Integer.valueOf(i2));
                    this.p.add(Integer.valueOf(i2));
                    i2++;
                }
            }
        }
        this.h.addAll(arrayList);
    }

    private void c(List<Device> list) {
        ArrayList arrayList = new ArrayList(list);
        int i2 = 0;
        for (String str : this.u) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                Device device = (Device) it.next();
                if (str.equals(device.model)) {
                    this.h.add(device);
                    it.remove();
                    this.o.add(Integer.valueOf(i2));
                    this.p.add(Integer.valueOf(i2));
                    i2++;
                }
            }
        }
        this.h.addAll(arrayList);
    }

    class MyPagerAdapter extends PagerAdapter {
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        MyPagerAdapter() {
        }

        public int getCount() {
            return AddToCommonDialogHelper.this.f;
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            View inflate = LayoutInflater.from(AddToCommonDialogHelper.this.j).inflate(R.layout.pager_item_add_common, (ViewGroup) null);
            ((GridView) inflate.findViewById(R.id.gridview)).setAdapter(new MyGridAdapter(AddToCommonDialogHelper.this.j, R.layout.item_add_common, (List) AddToCommonDialogHelper.this.i.get(i), i));
            viewGroup.addView(inflate);
            return inflate;
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView((View) obj);
        }
    }

    class MyGridAdapter extends ArrayAdapter<Device> {

        /* renamed from: a  reason: collision with root package name */
        List<Device> f20198a;
        Context b;
        int c;

        public long getItemId(int i) {
            return (long) i;
        }

        public MyGridAdapter(Context context, @NonNull int i, @LayoutRes List<Device> list, int i2) {
            super(context, i);
            this.f20198a = list;
            this.b = context;
            this.c = i2;
        }

        public int getCount() {
            return this.f20198a.size();
        }

        @Nullable
        /* renamed from: a */
        public Device getItem(int i) {
            return this.f20198a.get(i);
        }

        /* renamed from: a */
        public int getPosition(@Nullable Device device) {
            return super.getPosition(device);
        }

        @NonNull
        public View getView(int i, @Nullable View view, @NonNull ViewGroup viewGroup) {
            final ViewHolder viewHolder;
            if (view == null) {
                view = LayoutInflater.from(this.b).inflate(R.layout.item_add_common, viewGroup, false);
                viewHolder = new ViewHolder();
                viewHolder.d = view;
                viewHolder.c = (TextView) view.findViewById(R.id.text);
                viewHolder.b = (SimpleDraweeView) view.findViewById(R.id.image);
                viewHolder.f20201a = (ImageView) view.findViewById(R.id.check_image);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            Device device = this.f20198a.get(i);
            viewHolder.c.setText(device.name);
            DeviceFactory.b(device.model, viewHolder.b);
            final int i2 = (this.c * 12) + i;
            if (AddToCommonDialogHelper.this.o.contains(Integer.valueOf(i2))) {
                viewHolder.f20201a.setVisibility(0);
            } else {
                viewHolder.f20201a.setVisibility(4);
            }
            viewHolder.d.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (AddToCommonDialogHelper.this.o.contains(Integer.valueOf(i2))) {
                        viewHolder.f20201a.setVisibility(4);
                        AddToCommonDialogHelper.this.o.remove(Integer.valueOf(i2));
                    } else {
                        viewHolder.f20201a.setVisibility(0);
                        AddToCommonDialogHelper.this.o.add(Integer.valueOf(i2));
                    }
                    if (AddToCommonDialogHelper.this.p.size() != AddToCommonDialogHelper.this.o.size() || !AddToCommonDialogHelper.this.p.containsAll(AddToCommonDialogHelper.this.o) || AddToCommonDialogHelper.this.p.size() <= 0 || AddToCommonDialogHelper.this.s) {
                        AddToCommonDialogHelper.this.r.setVisibility(4);
                    } else {
                        AddToCommonDialogHelper.this.r.setVisibility(0);
                    }
                }
            });
            return view;
        }
    }

    class ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        ImageView f20201a;
        SimpleDraweeView b;
        TextView c;
        View d;

        ViewHolder() {
        }
    }
}
