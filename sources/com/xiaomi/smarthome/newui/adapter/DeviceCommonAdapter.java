package com.xiaomi.smarthome.newui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.newui.card.ProductModel;
import java.util.ArrayList;
import java.util.List;

public class DeviceCommonAdapter extends RecyclerView.Adapter {

    /* renamed from: a  reason: collision with root package name */
    private static final String f20373a = "DeviceCommonAdapter";
    private static final String[] b = {"mijia.camera.v1", "yunyi.camera.v1", "chuangmi.camera.xiaobai", "chuangmi.camera.v2", "chuangmi.camera.v5", "isa.camera.isc5", "isa.camera.df3", "chuangmi.plug.m1", "chuangmi.plug.v1", "lumi.plug.v1", "zimi.powerstrip.v2", "qmi.powerstrip.v1", "zhimi.airpurifier.m1", "zhimi.airpurifier.v1", "zhimi.airpurifier.v3", "zhimi.airpurifier.v6", "philips.light.bulb", "philips.light.mono1", "philips.light.sread1", "philips.light.ceiling", ProductModel.r, ProductModel.j, ProductModel.l, ProductModel.m, ProductModel.d, ProductModel.f, ProductModel.b, ProductModel.h, "zhimi.fan.v2", "zhimi.fan.v3", "lumi.gateway.v1", "lumi.gateway.v2", "lumi.gateway.v3", "lumi.acpartner.v1", "lumi.acpartner.v2", "innolinks.plug.ap3200", "lumi.ctrl_86plug.v1", "lumi.ctrl_ln1.v1", "lumi.ctrl_ln2.v1", "lumi.ctrl_neutral1.v1", "lumi.ctrl_neutral2.v1", "rockrobo.vacuum.v1", "zhimi.aircondition.v1", "zhimi.aircondition.v2", "midea.aircondition.v1", "midea.aircondition.xa1", "aux.aircondition.hc1", "aux.aircondition.v1", "idelan.aircondition.g1", "idelan.aircondition.g2", "idelan.aircondition.v1", "idelan.aircondition.v2", "chuangmi.radio.v1", "chuangmi.radio.v2", "jiqid.mistory.v1", "zhimi.humidifier.v1", "lumi.curtain.v1", "yeelink.light.virtual", "philips.light.virtual", "zhimi.airpurifier.virtual", "isa.camera.virtual"};
    private String[] c;
    private List<List<Device>> d;
    private Context e;
    /* access modifiers changed from: private */
    public List<ItemData> f;

    public DeviceCommonAdapter(Context context, String[] strArr, List<List<Device>> list) {
        this.e = context;
        this.d = list;
        this.c = strArr;
        a();
    }

    public int getItemViewType(int i) {
        return this.f.get(i).c;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(this.e);
        switch (i) {
            case 1:
                return new TitleHolder(from.inflate(R.layout.item_cud_ctg, viewGroup, false));
            case 2:
                return new DeviceHolder(from.inflate(R.layout.item_add_common, viewGroup, false));
            default:
                Log.d("error", "viewholder is null");
                return null;
        }
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof TitleHolder) {
            ((TitleHolder) viewHolder).a(i);
        }
        if (viewHolder instanceof DeviceHolder) {
            ((DeviceHolder) viewHolder).a(i);
        }
    }

    public int getItemCount() {
        return this.f.size();
    }

    private void a() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.c.length; i++) {
            arrayList.add(new ItemData(1, (Device) null, this.c[i]));
            for (Device itemData : this.d.get(i)) {
                arrayList.add(new ItemData(2, itemData, (String) null));
            }
        }
        this.f = arrayList;
    }

    static class ItemData {

        /* renamed from: a  reason: collision with root package name */
        static final int f20375a = 1;
        static final int b = 2;
        int c;
        private Device d;
        private String e;

        ItemData(int i, Device device, String str) {
            this.c = i;
            this.d = device;
            this.e = str;
        }

        /* access modifiers changed from: package-private */
        public Device a() {
            return this.d;
        }

        /* access modifiers changed from: package-private */
        public String b() {
            return this.e;
        }
    }

    class TitleHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        TextView f20376a;

        public TitleHolder(View view) {
            super(view);
            this.f20376a = (TextView) view.findViewById(R.id.tv_category);
        }

        /* access modifiers changed from: package-private */
        public void a(int i) {
            this.f20376a.setText(((ItemData) DeviceCommonAdapter.this.f.get(i)).b());
        }
    }

    class DeviceHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView b;
        private ImageView c;
        private TextView d;

        public DeviceHolder(View view) {
            super(view);
            this.d = (TextView) view.findViewById(R.id.text);
            this.b = (SimpleDraweeView) view.findViewById(R.id.image);
            this.c = (ImageView) view.findViewById(R.id.check_image);
        }

        public void a(int i) {
            Device a2 = ((ItemData) DeviceCommonAdapter.this.f.get(i)).a();
            this.d.setText(a2.name);
            DeviceFactory.b(a2.model, this.b);
        }
    }
}
