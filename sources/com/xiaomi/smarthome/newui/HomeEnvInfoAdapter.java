package com.xiaomi.smarthome.newui;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemAdapter;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.homeroom.view.BaseGroupViewHolder;
import com.xiaomi.smarthome.homeroom.view.BaseViewHolder;
import com.xiaomi.smarthome.homeroom.view.CommonGroupTitleViewHolder;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.newui.HomeEnvInfoViewModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeEnvInfoAdapter extends AbstractExpandableItemAdapter<BaseGroupViewHolder, ChildViewHolder> {

    /* renamed from: a  reason: collision with root package name */
    public boolean f20279a = false;
    protected Context b;
    boolean c = false;
    /* access modifiers changed from: private */
    public Map<String, String> d = new HashMap();
    private View e;
    private final int f = 1;
    private List<HomeEnvInfoViewModel.GroupBean> g = new ArrayList();

    public boolean a(BaseGroupViewHolder baseGroupViewHolder, int i, int i2, int i3, boolean z) {
        return false;
    }

    public int d(int i, int i2) {
        return 0;
    }

    public HomeEnvInfoAdapter(Activity activity, boolean z) {
        this.b = activity;
        this.c = z;
        setHasStableIds(true);
    }

    public int a() {
        return this.g.size();
    }

    public int a(int i) {
        return this.g.get(i).c.size();
    }

    public long b(int i) {
        return (long) (this.g.get(i).f20296a.hashCode() + this.g.get(i).d);
    }

    public long c(int i, int i2) {
        HomeEnvInfoViewModel.GroupBean groupBean = this.g.get(i);
        if (TextUtils.isEmpty(groupBean.f20296a)) {
            return 0;
        }
        return (long) (groupBean.f20296a.hashCode() + (TextUtils.isEmpty(groupBean.c.get(i2).b) ? 0 : groupBean.c.get(i2).b.hashCode()));
    }

    public int c(int i) {
        return this.g.get(i).d;
    }

    /* renamed from: c */
    public BaseGroupViewHolder a(ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(this.b);
        if (i == 1) {
            return new BaseGroupViewHolder(this.e);
        }
        return new CommonGroupTitleViewHolder(from.inflate(R.layout.tag_group_item_common_5, viewGroup, false));
    }

    /* renamed from: d */
    public ChildViewHolder b(ViewGroup viewGroup, int i) {
        return new ChildViewHolder(LayoutInflater.from(this.b).inflate(R.layout.tag_child_home_env, viewGroup, false));
    }

    /* renamed from: a */
    public void b(BaseGroupViewHolder baseGroupViewHolder, int i, int i2) {
        if (i2 != 1) {
            baseGroupViewHolder.a((RecyclerView.Adapter) this, this.g.get(i).f20296a);
        }
    }

    /* renamed from: a */
    public void b(ChildViewHolder childViewHolder, int i, int i2, int i3) {
        childViewHolder.a(this.g.get(i).c.get(i2));
        boolean z = true;
        childViewHolder.e(i2 == this.g.get(i).c.size() - 1 ? 8 : 0);
        if (i2 != this.g.get(i).c.size() - 1) {
            z = false;
        }
        childViewHolder.a(z);
    }

    public void a(View view) {
        this.e = view;
    }

    public void a(String str, String str2, boolean z) {
        if (z) {
            this.d.put(str, str2);
        } else {
            this.d.remove(str);
        }
        notifyDataSetChanged();
    }

    public Map<String, String> b() {
        return this.d;
    }

    public void a(List<HomeEnvInfoViewModel.GroupBean> list) {
        this.g = list;
    }

    public void a(HashMap<String, String> hashMap) {
        if (hashMap != null && !hashMap.isEmpty()) {
            this.d = hashMap;
        }
    }

    class ChildViewHolder extends BaseViewHolder {
        private View c;
        private TextView d;
        private TextView e;
        private TextView f;
        private SimpleDraweeView g;
        private View h;
        private CheckBox i;
        private ImageView j;

        public ChildViewHolder(View view) {
            super(view);
            this.c = view.findViewById(R.id.root);
            this.d = (TextView) view.findViewById(R.id.title);
            this.e = (TextView) view.findViewById(R.id.desc);
            this.g = (SimpleDraweeView) view.findViewById(R.id.icon);
            this.h = view.findViewById(R.id.divider_item);
            this.i = (CheckBox) view.findViewById(R.id.checkbox);
            this.f = (TextView) view.findViewById(R.id.right_text);
            this.j = (ImageView) view.findViewById(R.id.sign);
        }

        /* JADX WARNING: Removed duplicated region for block: B:52:0x00f3  */
        /* JADX WARNING: Removed duplicated region for block: B:59:0x0113  */
        /* JADX WARNING: Removed duplicated region for block: B:60:0x0121  */
        /* JADX WARNING: Removed duplicated region for block: B:63:0x0132  */
        /* JADX WARNING: Removed duplicated region for block: B:64:0x013e  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a(com.xiaomi.smarthome.newui.topwidget.TopWidgetDataNew.Detail r8) {
            /*
                r7 = this;
                com.xiaomi.smarthome.device.SmartHomeDeviceManager r0 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.a()
                java.lang.String r1 = r8.b
                com.xiaomi.smarthome.device.Device r0 = r0.b((java.lang.String) r1)
                if (r0 == 0) goto L_0x01e2
                android.widget.TextView r1 = r7.e
                java.lang.String r2 = r0.name
                r1.setText(r2)
                android.widget.TextView r1 = r7.e
                boolean r2 = r0.isOnline
                r3 = 1056964608(0x3f000000, float:0.5)
                r4 = 1065353216(0x3f800000, float:1.0)
                if (r2 == 0) goto L_0x0020
                r2 = 1065353216(0x3f800000, float:1.0)
                goto L_0x0022
            L_0x0020:
                r2 = 1056964608(0x3f000000, float:0.5)
            L_0x0022:
                r1.setAlpha(r2)
                android.widget.TextView r1 = r7.d
                boolean r2 = r0.isOnline
                if (r2 == 0) goto L_0x002e
                r2 = 1065353216(0x3f800000, float:1.0)
                goto L_0x0030
            L_0x002e:
                r2 = 1056964608(0x3f000000, float:0.5)
            L_0x0030:
                r1.setAlpha(r2)
                com.facebook.drawee.view.SimpleDraweeView r1 = r7.g
                boolean r2 = r0.isOnline
                if (r2 == 0) goto L_0x003c
                r2 = 1065353216(0x3f800000, float:1.0)
                goto L_0x003e
            L_0x003c:
                r2 = 1056964608(0x3f000000, float:0.5)
            L_0x003e:
                r1.setAlpha(r2)
                android.widget.TextView r1 = r7.f
                boolean r2 = r0.isOnline
                if (r2 == 0) goto L_0x004a
                r2 = 1065353216(0x3f800000, float:1.0)
                goto L_0x004c
            L_0x004a:
                r2 = 1056964608(0x3f000000, float:0.5)
            L_0x004c:
                r1.setAlpha(r2)
                android.widget.ImageView r1 = r7.j
                boolean r2 = r0.isOnline
                if (r2 == 0) goto L_0x0057
                r3 = 1065353216(0x3f800000, float:1.0)
            L_0x0057:
                r1.setAlpha(r3)
                java.lang.String r1 = r0.model
                com.facebook.drawee.view.SimpleDraweeView r2 = r7.g
                com.xiaomi.smarthome.device.DeviceFactory.b((java.lang.String) r1, (com.facebook.drawee.view.SimpleDraweeView) r2)
                com.xiaomi.smarthome.newui.HomeEnvInfoAdapter r1 = com.xiaomi.smarthome.newui.HomeEnvInfoAdapter.this
                boolean r1 = r1.c
                r2 = 8
                r3 = 0
                if (r1 != 0) goto L_0x0150
                java.util.List<java.lang.String> r1 = com.xiaomi.smarthome.newui.HomeEnvInfoViewModel.e
                java.lang.String r4 = r8.g
                int r1 = r1.indexOf(r4)
                int r1 = java.lang.Math.abs(r1)
                java.util.List<java.lang.String> r4 = com.xiaomi.smarthome.newui.HomeEnvInfoViewModel.e
                int r4 = r4.size()
                int r1 = r1 % r4
                com.xiaomi.smarthome.newui.HomeEnvInfoAdapter r4 = com.xiaomi.smarthome.newui.HomeEnvInfoAdapter.this
                android.content.Context r4 = r4.b
                android.content.res.Resources r4 = r4.getResources()
                r5 = 2131689497(0x7f0f0019, float:1.9008011E38)
                java.lang.String[] r4 = r4.getStringArray(r5)
                int r5 = r4.length
                if (r1 >= r5) goto L_0x0099
                int r5 = r4.length
                if (r5 <= 0) goto L_0x0099
                android.widget.TextView r5 = r7.d
                r1 = r4[r1]
                r5.setText(r1)
            L_0x0099:
                java.lang.String r1 = r8.f
                boolean r1 = android.text.TextUtils.isEmpty(r1)
                if (r1 == 0) goto L_0x00a2
                return
            L_0x00a2:
                android.widget.TextView r1 = r7.f     // Catch:{ Exception -> 0x00aa }
                java.lang.String r4 = r8.f     // Catch:{ Exception -> 0x00aa }
                r1.setText(r4)     // Catch:{ Exception -> 0x00aa }
                goto L_0x00ae
            L_0x00aa:
                r1 = move-exception
                r1.printStackTrace()
            L_0x00ae:
                android.widget.TextView r1 = r7.f
                java.lang.String r4 = "#808C8E"
                int r4 = android.graphics.Color.parseColor(r4)
                r1.setTextColor(r4)
                java.lang.String r1 = r8.g
                r4 = -1
                int r5 = r1.hashCode()
                r6 = 103680(0x19500, float:1.45287E-40)
                if (r5 == r6) goto L_0x00e4
                r6 = 3442944(0x348900, float:4.824592E-39)
                if (r5 == r6) goto L_0x00da
                r6 = 3556308(0x3643d4, float:4.983449E-39)
                if (r5 == r6) goto L_0x00d0
                goto L_0x00ee
            L_0x00d0:
                java.lang.String r5 = "temp"
                boolean r1 = r1.equals(r5)
                if (r1 == 0) goto L_0x00ee
                r1 = 0
                goto L_0x00ef
            L_0x00da:
                java.lang.String r5 = "pm25"
                boolean r1 = r1.equals(r5)
                if (r1 == 0) goto L_0x00ee
                r1 = 2
                goto L_0x00ef
            L_0x00e4:
                java.lang.String r5 = "hum"
                boolean r1 = r1.equals(r5)
                if (r1 == 0) goto L_0x00ee
                r1 = 1
                goto L_0x00ef
            L_0x00ee:
                r1 = -1
            L_0x00ef:
                switch(r1) {
                    case 0: goto L_0x0121;
                    case 1: goto L_0x0113;
                    case 2: goto L_0x00f3;
                    default: goto L_0x00f2;
                }
            L_0x00f2:
                goto L_0x012e
            L_0x00f3:
                android.widget.ImageView r1 = r7.j
                r1.setVisibility(r2)
                java.lang.String r8 = r8.f     // Catch:{ Exception -> 0x010e }
                int r8 = java.lang.Integer.parseInt(r8)     // Catch:{ Exception -> 0x010e }
                r1 = 50
                if (r8 <= r1) goto L_0x012e
                android.widget.TextView r8 = r7.f     // Catch:{ Exception -> 0x010e }
                java.lang.String r1 = "#DFAD16"
                int r1 = android.graphics.Color.parseColor(r1)     // Catch:{ Exception -> 0x010e }
                r8.setTextColor(r1)     // Catch:{ Exception -> 0x010e }
                goto L_0x012e
            L_0x010e:
                r8 = move-exception
                r8.printStackTrace()
                goto L_0x012e
            L_0x0113:
                android.widget.ImageView r8 = r7.j
                r8.setVisibility(r3)
                android.widget.ImageView r8 = r7.j
                r1 = 2130840288(0x7f020ae0, float:1.728561E38)
                r8.setBackgroundResource(r1)
                goto L_0x012e
            L_0x0121:
                android.widget.ImageView r8 = r7.j
                r8.setVisibility(r3)
                android.widget.ImageView r8 = r7.j
                r1 = 2130840323(0x7f020b03, float:1.7285682E38)
                r8.setBackgroundResource(r1)
            L_0x012e:
                boolean r8 = r0.isOnline
                if (r8 != 0) goto L_0x013e
                android.widget.ImageView r8 = r7.j
                r8.setVisibility(r2)
                android.widget.TextView r8 = r7.f
                r8.setVisibility(r2)
                goto L_0x01e2
            L_0x013e:
                android.widget.TextView r8 = r7.f
                r8.setVisibility(r3)
                android.widget.ImageView r8 = r7.j
                android.widget.ImageView r0 = r7.j
                int r0 = r0.getVisibility()
                r8.setVisibility(r0)
                goto L_0x01e2
            L_0x0150:
                android.widget.TextView r0 = r7.f
                r1 = 4
                r0.setVisibility(r1)
                android.widget.ImageView r0 = r7.j
                r0.setVisibility(r2)
                android.widget.CheckBox r0 = r7.i
                r0.setVisibility(r3)
                android.widget.CheckBox r0 = r7.i
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = r8.g
                r1.append(r2)
                java.lang.String r2 = "_"
                r1.append(r2)
                java.lang.String r2 = r8.b
                r1.append(r2)
                java.lang.String r1 = r1.toString()
                r0.setTag(r1)
                com.xiaomi.smarthome.newui.HomeEnvInfoAdapter r0 = com.xiaomi.smarthome.newui.HomeEnvInfoAdapter.this
                java.util.Map r0 = r0.d
                if (r0 == 0) goto L_0x019e
                com.xiaomi.smarthome.newui.HomeEnvInfoAdapter r0 = com.xiaomi.smarthome.newui.HomeEnvInfoAdapter.this
                java.util.Map r0 = r0.d
                java.lang.String r1 = r8.g
                java.lang.Object r0 = r0.get(r1)
                java.lang.String r0 = (java.lang.String) r0
                android.widget.CheckBox r1 = r7.i
                java.lang.String r2 = r8.b
                boolean r0 = android.text.TextUtils.equals(r0, r2)
                r1.setChecked(r0)
            L_0x019e:
                android.widget.CheckBox r0 = r7.i
                com.xiaomi.smarthome.newui.-$$Lambda$HomeEnvInfoAdapter$ChildViewHolder$AH3bk_aQGbUpeiFqMVfLOLnY8ns r1 = new com.xiaomi.smarthome.newui.-$$Lambda$HomeEnvInfoAdapter$ChildViewHolder$AH3bk_aQGbUpeiFqMVfLOLnY8ns
                r1.<init>(r8)
                r0.setOnCheckedChangeListener(r1)
                android.view.View r0 = r7.c
                com.xiaomi.smarthome.newui.-$$Lambda$HomeEnvInfoAdapter$ChildViewHolder$8HwwbBVbYXs3Of7bq4SmrNkaHV0 r1 = new com.xiaomi.smarthome.newui.-$$Lambda$HomeEnvInfoAdapter$ChildViewHolder$8HwwbBVbYXs3Of7bq4SmrNkaHV0
                r1.<init>()
                r0.setOnClickListener(r1)
                com.xiaomi.smarthome.homeroom.HomeManager r0 = com.xiaomi.smarthome.homeroom.HomeManager.a()
                java.lang.String r1 = r8.h
                com.xiaomi.smarthome.homeroom.model.Room r0 = r0.i((java.lang.String) r1)
                if (r0 == 0) goto L_0x01c8
                android.widget.TextView r8 = r7.d
                java.lang.String r0 = r0.e()
                r8.setText(r0)
                goto L_0x01e2
            L_0x01c8:
                java.lang.String r8 = r8.h
                java.lang.String r0 = "mijia.roomid.default"
                boolean r8 = android.text.TextUtils.equals(r8, r0)
                if (r8 == 0) goto L_0x01e2
                android.widget.TextView r8 = r7.d
                com.xiaomi.smarthome.newui.HomeEnvInfoAdapter r0 = com.xiaomi.smarthome.newui.HomeEnvInfoAdapter.this
                android.content.Context r0 = r0.b
                r1 = 2131494437(0x7f0c0625, float:1.8612382E38)
                java.lang.String r0 = r0.getString(r1)
                r8.setText(r0)
            L_0x01e2:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.newui.HomeEnvInfoAdapter.ChildViewHolder.a(com.xiaomi.smarthome.newui.topwidget.TopWidgetDataNew$Detail):void");
        }

        /* access modifiers changed from: private */
        /* JADX WARNING: Removed duplicated region for block: B:14:0x0035  */
        /* JADX WARNING: Removed duplicated region for block: B:19:0x005d  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public /* synthetic */ void a(com.xiaomi.smarthome.newui.topwidget.TopWidgetDataNew.Detail r6, android.widget.CompoundButton r7, boolean r8) {
            /*
                r5 = this;
                android.widget.CheckBox r7 = r5.i
                java.lang.Object r7 = r7.getTag()
                java.lang.String r7 = (java.lang.String) r7
                java.lang.String r0 = ""
                java.lang.String r1 = ""
                boolean r2 = android.text.TextUtils.isEmpty(r7)
                r3 = 0
                r4 = 1
                if (r2 != 0) goto L_0x0033
                java.lang.String r2 = "_"
                int r2 = r7.indexOf(r2)     // Catch:{ Exception -> 0x002f }
                java.lang.String r2 = r7.substring(r3, r2)     // Catch:{ Exception -> 0x002f }
                java.lang.String r0 = "_"
                int r0 = r7.indexOf(r0)     // Catch:{ Exception -> 0x002c }
                int r0 = r0 + r4
                java.lang.String r7 = r7.substring(r0)     // Catch:{ Exception -> 0x002c }
                r1 = r7
                r0 = r2
                goto L_0x0033
            L_0x002c:
                r7 = move-exception
                r0 = r2
                goto L_0x0030
            L_0x002f:
                r7 = move-exception
            L_0x0030:
                r7.printStackTrace()
            L_0x0033:
                if (r8 == 0) goto L_0x005d
                com.xiaomi.smarthome.newui.HomeEnvInfoAdapter r7 = com.xiaomi.smarthome.newui.HomeEnvInfoAdapter.this
                java.util.Map r7 = r7.d
                boolean r7 = r7.containsKey(r0)
                if (r7 == 0) goto L_0x0053
                com.xiaomi.smarthome.newui.HomeEnvInfoAdapter r7 = com.xiaomi.smarthome.newui.HomeEnvInfoAdapter.this
                java.util.Map r7 = r7.d
                java.lang.Object r7 = r7.get(r0)
                java.lang.CharSequence r7 = (java.lang.CharSequence) r7
                boolean r7 = android.text.TextUtils.equals(r7, r1)
                if (r7 != 0) goto L_0x0084
            L_0x0053:
                com.xiaomi.smarthome.newui.HomeEnvInfoAdapter r7 = com.xiaomi.smarthome.newui.HomeEnvInfoAdapter.this
                java.lang.String r8 = r6.g
                java.lang.String r6 = r6.b
                r7.a((java.lang.String) r8, (java.lang.String) r6, (boolean) r4)
                goto L_0x0084
            L_0x005d:
                com.xiaomi.smarthome.newui.HomeEnvInfoAdapter r7 = com.xiaomi.smarthome.newui.HomeEnvInfoAdapter.this
                java.util.Map r7 = r7.d
                boolean r7 = r7.containsKey(r0)
                if (r7 == 0) goto L_0x0084
                com.xiaomi.smarthome.newui.HomeEnvInfoAdapter r7 = com.xiaomi.smarthome.newui.HomeEnvInfoAdapter.this
                java.util.Map r7 = r7.d
                java.lang.Object r7 = r7.get(r0)
                java.lang.CharSequence r7 = (java.lang.CharSequence) r7
                boolean r7 = android.text.TextUtils.equals(r7, r1)
                if (r7 == 0) goto L_0x0084
                com.xiaomi.smarthome.newui.HomeEnvInfoAdapter r7 = com.xiaomi.smarthome.newui.HomeEnvInfoAdapter.this
                java.lang.String r8 = r6.g
                java.lang.String r6 = r6.b
                r7.a((java.lang.String) r8, (java.lang.String) r6, (boolean) r3)
            L_0x0084:
                com.xiaomi.smarthome.newui.HomeEnvInfoAdapter r6 = com.xiaomi.smarthome.newui.HomeEnvInfoAdapter.this
                r6.f20279a = r4
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.newui.HomeEnvInfoAdapter.ChildViewHolder.a(com.xiaomi.smarthome.newui.topwidget.TopWidgetDataNew$Detail, android.widget.CompoundButton, boolean):void");
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(View view) {
            this.i.setChecked(!this.i.isChecked());
        }

        /* access modifiers changed from: package-private */
        public void e(int i2) {
            this.h.setVisibility(i2);
        }

        /* access modifiers changed from: package-private */
        public void a(boolean z) {
            float f2;
            this.c.setBackgroundResource(z ? R.drawable.main_grid_bottom_card_bg_normal : R.drawable.main_grid_center_card_bg_normal);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) this.c.getLayoutParams();
            if (z) {
                f2 = 70.0f;
            } else {
                f2 = 60.0f;
            }
            layoutParams.height = DisplayUtils.a(f2);
            this.c.setLayoutParams(layoutParams);
        }
    }
}
