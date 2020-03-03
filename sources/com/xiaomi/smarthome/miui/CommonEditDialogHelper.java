package com.xiaomi.smarthome.miui;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v4.view.GravityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.renderer.DeviceRenderer;
import com.xiaomi.smarthome.device.utils.IRDeviceUtil;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.statistic.StatHelper;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.CommonUtils;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import miui.app.AlertDialog;

public class CommonEditDialogHelper {
    public static void a(final Context context, final Device device) {
        if (device != null && context != null) {
            final AlertDialog show = new AlertDialog.Builder(context, 3).setAdapter(new ItemAdapter(context, device), (DialogInterface.OnClickListener) null).setTitle(device.getName()).setNegativeButton(R.string.cancel, (DialogInterface.OnClickListener) null).show();
            show.getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                    switch (i) {
                        case 0:
                            DeviceRenderer.a(context, device);
                            break;
                        case 1:
                            DeviceRenderer.b(context, device);
                            break;
                        case 2:
                            CommonEditDialogHelper.c(context, device);
                            break;
                    }
                    show.dismiss();
                }
            });
        }
    }

    static class ItemAdapter extends BaseAdapter {

        /* renamed from: a  reason: collision with root package name */
        private Context f20106a;
        private Device b;
        private String[] c = ((String[]) Arrays.copyOf(this.c, this.c.length - 1));

        public long getItemId(int i) {
            return (long) i;
        }

        public ItemAdapter(Context context, Device device) {
            this.f20106a = context;
            this.c = context.getResources().getStringArray(R.array.common_edit_item_name);
            this.b = device;
        }

        public int getCount() {
            return this.c.length;
        }

        /* renamed from: a */
        public String getItem(int i) {
            return this.c[i];
        }

        public boolean isEnabled(int i) {
            if (IRDeviceUtil.a(this.b.did)) {
                return false;
            }
            switch (i) {
                case 0:
                    return this.b.canRename();
                case 1:
                    return this.b.canBeShared();
                case 2:
                    return this.b.canBeDeleted();
                default:
                    return super.isEnabled(i);
            }
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            Holder holder;
            if (view == null) {
                view = LayoutInflater.from(this.f20106a).inflate(R.layout.common_device_edit_item_layout, viewGroup, false);
                holder = new Holder();
                holder.f20107a = (TextView) view.findViewById(R.id.tv_name);
                view.setTag(holder);
            } else {
                holder = (Holder) view.getTag();
            }
            holder.f20107a.setText(this.c[i]);
            holder.f20107a.setEnabled(isEnabled(i));
            return view;
        }

        class Holder {

            /* renamed from: a  reason: collision with root package name */
            TextView f20107a;

            Holder() {
            }
        }
    }

    /* access modifiers changed from: private */
    public static void c(final Context context, final Device device) {
        AlertDialog show = new AlertDialog.Builder(context, 3).setMessage(R.string.delete_common_device).setPositiveButton(R.string.tag_remove, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (CommonUtils.s()) {
                    ToastUtil.a(context, (int) R.string.smarthome_device_mijia_cant_delete);
                    return;
                }
                final XQProgressDialog xQProgressDialog = new XQProgressDialog(context);
                xQProgressDialog.setMessage(context.getString(R.string.smarthome_deleting));
                xQProgressDialog.setCancelable(false);
                xQProgressDialog.show();
                ArrayList arrayList = new ArrayList();
                arrayList.add(device);
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(device.did);
                SmartHomeDeviceManager.a().a((List<String>) arrayList2, context, (SmartHomeDeviceManager.IDelDeviceBatchCallback) new SmartHomeDeviceManager.IDelDeviceBatchCallback() {
                    public void a() {
                        xQProgressDialog.dismiss();
                        ToastUtil.a((CharSequence) context.getResources().getString(R.string.common_used_device_del, new Object[]{device.getName()}));
                    }

                    public void a(Error error) {
                        Device b2 = SmartHomeDeviceManager.a().b(error.b().substring(error.b().indexOf("did:") + 4));
                        if (b2 == null || !HomeManager.Q.contains(b2.model)) {
                            ToastUtil.a((int) R.string.bind_error);
                        } else {
                            ToastUtil.a((int) R.string.miband_remove_warning);
                        }
                        xQProgressDialog.dismiss();
                    }
                });
                StatHelper.b((List<Device>) arrayList);
                dialogInterface.dismiss();
                HomeManager.a().x(device.did);
            }
        }).setNegativeButton(R.string.cancel, (DialogInterface.OnClickListener) null).show();
        show.getButton(-1).setTextColor(context.getResources().getColor(R.color.std_dialog_button_red));
        final TextView messageView = show.getMessageView();
        messageView.setLineSpacing(0.0f, 1.1f);
        messageView.setTextColor(Color.parseColor("#333333"));
        messageView.setTextSize(15.0f);
        messageView.post(new Runnable() {
            public void run() {
                if (messageView.getLineCount() == 1) {
                    messageView.setGravity(17);
                } else {
                    messageView.setGravity(GravityCompat.START);
                }
            }
        });
    }
}
