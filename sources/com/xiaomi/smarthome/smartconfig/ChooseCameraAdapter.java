package com.xiaomi.smarthome.smartconfig;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.smartconfig.ChooseCameraActivity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ChooseCameraAdapter extends BaseAdapter {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public ICameraChooser f22254a;
    private List<ChooseCameraActivity.CameraItem> b = new ArrayList();
    private Handler c = new Handler(Looper.getMainLooper());

    public interface ICameraChooser {
        Context getContext();

        void onCameraChoose(ChooseCameraActivity.CameraItem cameraItem);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public ChooseCameraAdapter(ICameraChooser iCameraChooser) {
        this.f22254a = iCameraChooser;
    }

    public void a(final Collection<ChooseCameraActivity.CameraItem> collection) {
        this.c.post(new Runnable() {
            public void run() {
                ChooseCameraAdapter.this.b(collection);
            }
        });
    }

    /* access modifiers changed from: private */
    public void b(Collection<ChooseCameraActivity.CameraItem> collection) {
        this.b.clear();
        this.b.addAll(collection);
        Collections.sort(this.b, new Comparator<ChooseCameraActivity.CameraItem>() {
            /* renamed from: a */
            public int compare(ChooseCameraActivity.CameraItem cameraItem, ChooseCameraActivity.CameraItem cameraItem2) {
                int i = 0;
                int i2 = cameraItem2.b != null ? cameraItem2.b.rssi : 0;
                if (cameraItem.b != null) {
                    i = cameraItem.b.rssi;
                }
                return i2 - i;
            }
        });
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.b.size();
    }

    /* renamed from: a */
    public ChooseCameraActivity.CameraItem getItem(int i) {
        return this.b.get(i);
    }

    private static class ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        SimpleDraweeView f22258a;
        TextView b;
        ImageView c;

        private ViewHolder() {
        }
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(this.f22254a.getContext()).inflate(R.layout.choose_camera_item, (ViewGroup) null);
            viewHolder = new ViewHolder();
            viewHolder.f22258a = (SimpleDraweeView) view.findViewById(R.id.image);
            viewHolder.b = (TextView) view.findViewById(R.id.name);
            viewHolder.c = (ImageView) view.findViewById(R.id.signal);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        final ChooseCameraActivity.CameraItem a2 = getItem(i);
        SimpleDraweeView simpleDraweeView = viewHolder.f22258a;
        simpleDraweeView.setHierarchy(new GenericDraweeHierarchyBuilder(simpleDraweeView.getResources()).setFadeDuration(200).setPlaceholderImage(simpleDraweeView.getResources().getDrawable(R.drawable.device_list_phone_no)).setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER).setPlaceholderImageScaleType(ScalingUtils.ScaleType.FIT_CENTER).build());
        DeviceFactory.a(DeviceFactory.a(a2.f22252a), simpleDraweeView, (int) R.drawable.device_list_phone_no);
        viewHolder.b.setText(DeviceFactory.i(a2.f22252a));
        int i2 = a2.b.rssi;
        if (i2 >= -30) {
            viewHolder.c.setImageResource(R.drawable.tag_ble_04);
        } else if (i2 >= -50) {
            viewHolder.c.setImageResource(R.drawable.tag_ble_03);
        } else if (i2 >= -70) {
            viewHolder.c.setImageResource(R.drawable.tag_ble_02);
        } else {
            viewHolder.c.setImageResource(R.drawable.tag_ble_01);
        }
        view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ChooseCameraAdapter.this.f22254a.onCameraChoose(a2);
            }
        });
        return view;
    }
}
