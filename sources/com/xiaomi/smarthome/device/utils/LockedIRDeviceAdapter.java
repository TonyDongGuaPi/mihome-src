package com.xiaomi.smarthome.device.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.exoplayer2.C;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.IRRemoteInfo;
import com.xiaomi.smarthome.framework.openapi.OpenApi;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.widget.AnimateFakeList;
import com.xiaomi.smarthome.miio.activity.ClientAllLockedActivity;
import java.util.ArrayList;
import java.util.List;

public class LockedIRDeviceAdapter extends LockedBaseAdapter {
    private static final String d = "LockedIRDeviceAdapter";

    /* renamed from: a  reason: collision with root package name */
    AnimateFakeList f15512a;
    List<IRRemoteInfo> b;
    final ClientAllLockedActivity c;

    public long getItemId(int i) {
        return (long) i;
    }

    public LockedIRDeviceAdapter(ClientAllLockedActivity clientAllLockedActivity, AnimateFakeList animateFakeList) {
        this.c = clientAllLockedActivity;
        this.f15512a = animateFakeList;
        a();
    }

    /* access modifiers changed from: protected */
    public void a() {
        this.b = IRDeviceUtil.e(this.c);
        if (this.b == null) {
            this.b = new ArrayList();
        }
    }

    public int getCount() {
        return this.b.size();
    }

    public Object getItem(int i) {
        if (i < this.b.size()) {
            return this.b.get(i);
        }
        return null;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (i >= this.b.size()) {
            return null;
        }
        if (view == null) {
            view = LayoutInflater.from(this.c).inflate(R.layout.client_all_item_v2_locked, viewGroup, false);
        }
        final IRRemoteInfo iRRemoteInfo = this.b.get(i);
        ImageView imageView = (ImageView) view.findViewById(R.id.image);
        TextView textView = (TextView) view.findViewById(R.id.name);
        TextView textView2 = (TextView) view.findViewById(R.id.name_status);
        String str = d;
        Log.d(str, "device_type=" + iRRemoteInfo.c);
        int i2 = iRRemoteInfo.c;
        int i3 = R.drawable.lock_list_box_normal;
        switch (i2) {
            case 1:
                i3 = R.drawable.lock_list_tv_normal;
                break;
            case 2:
                i3 = R.drawable.lock_list_dvd_normal;
                break;
            case 3:
                i3 = R.drawable.lock_list_infrared_air_conditioner_normal;
                break;
            case 4:
                i3 = R.drawable.lock_list_dvd2_normal;
                break;
            case 5:
                break;
            default:
                switch (i2) {
                    case 10000:
                        break;
                    case 10001:
                        i3 = R.drawable.lock_list_tv_mi_normal;
                        break;
                }
                i3 = R.drawable.lock_list_tv_normal;
                break;
        }
        imageView.setImageResource(i3);
        textView.setText(iRRemoteInfo.b);
        textView2.setText(R.string.lock_device_type_ir);
        view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(iRRemoteInfo.d);
                intent.putExtra("controller_id", iRRemoteInfo.f14856a);
                intent.addFlags(C.ENCODING_PCM_MU_LAW);
                LockedIRDeviceAdapter.this.c.startActivity(intent);
                LockedIRDeviceAdapter.this.c.finish();
                DisplayUtils.a((Context) LockedIRDeviceAdapter.this.c, 17432576, 17432577);
                OpenApi.a((Context) LockedIRDeviceAdapter.this.c);
            }
        });
        return view;
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        Log.d(d, "notifyDataSetChanged");
        a();
        if (this.f15512a != null) {
            this.f15512a.onDataChanged(false, this.c);
        }
    }
}
