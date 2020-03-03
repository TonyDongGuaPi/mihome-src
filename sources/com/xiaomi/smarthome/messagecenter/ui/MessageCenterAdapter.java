package com.xiaomi.smarthome.messagecenter.ui;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.exoplayer2.C;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.statistic.StatHelper;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.CalendarUtils;
import com.xiaomi.smarthome.messagecenter.CommonMessageManager;
import com.xiaomi.smarthome.messagecenter.DevicePushMessageManager;
import com.xiaomi.smarthome.messagecenter.HomeShareMessageManager;
import com.xiaomi.smarthome.messagecenter.IMessage;
import com.xiaomi.smarthome.messagecenter.ShareMessageManager;
import com.xiaomi.smarthome.messagecenter.WifiPwdChangedMessageManager;
import com.xiaomi.smarthome.miio.page.MessageCenterListActivity;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class MessageCenterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int c = 1;
    private static final int d = 2;
    private static final int e = 3;
    private static final int f = 4;
    private static final int g = 5;
    private static final int h = 6;

    /* renamed from: a  reason: collision with root package name */
    XQProgressDialog f10518a;
    private List<BaseViewModel> b = new ArrayList();
    /* access modifiers changed from: private */
    public final WeakReference<Activity> i;
    /* access modifiers changed from: private */
    public boolean j = false;
    /* access modifiers changed from: private */
    public SparseBooleanArray k = new SparseBooleanArray();
    /* access modifiers changed from: private */
    public List<IMessage> l = new ArrayList();

    /* access modifiers changed from: package-private */
    public void b() {
    }

    public MessageCenterAdapter(Activity activity) {
        this.i = new WeakReference<>(activity);
    }

    public void a(List<IMessage> list) {
        this.b = c(list);
        notifyDataSetChanged();
    }

    public void b(List<IMessage> list) {
        if (list != null || this.b.isEmpty()) {
            this.b = c(list);
            notifyDataSetChanged();
        }
    }

    public static List<BaseViewModel> c(List<IMessage> list) {
        HomeShareMessageManager.HomeShareMessage homeShareMessage;
        ShareMessageManager.ShareMessage shareMessage;
        if (list == null) {
            list = new ArrayList<>();
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ShareMessageManager.ShareMessage shareMessage2 = null;
        HomeShareMessageManager.HomeShareMessage homeShareMessage2 = null;
        for (int i2 = 0; i2 < list.size(); i2++) {
            IMessage iMessage = list.get(i2);
            if (iMessage != null) {
                if (iMessage instanceof ShareMessageManager.ShareMessage) {
                    if (shareMessage2 == null) {
                        shareMessage = (ShareMessageManager.ShareMessage) iMessage;
                    } else if (shareMessage2.a() < iMessage.a()) {
                        shareMessage = (ShareMessageManager.ShareMessage) iMessage;
                    }
                    shareMessage2 = shareMessage;
                } else if (iMessage instanceof HomeShareMessageManager.HomeShareMessage) {
                    if (homeShareMessage2 == null) {
                        homeShareMessage = (HomeShareMessageManager.HomeShareMessage) iMessage;
                    } else if (homeShareMessage2.a() < iMessage.a()) {
                        homeShareMessage = (HomeShareMessageManager.HomeShareMessage) iMessage;
                    }
                    homeShareMessage2 = homeShareMessage;
                } else if (iMessage instanceof DevicePushMessageManager.DevicePushMessage) {
                    DevicePushMessageManager.DevicePushMessage devicePushMessage = (DevicePushMessageManager.DevicePushMessage) iMessage;
                    if (SmartHomeDeviceManager.a().b(devicePushMessage.b) == null) {
                        Home j2 = HomeManager.a().j(String.valueOf(devicePushMessage.e()));
                        if (j2 != null) {
                            if (j2.p()) {
                            }
                        }
                    }
                    if (CoreApi.a().d(devicePushMessage.f10436a) != null) {
                        DeviceGroupViewModel deviceGroupViewModel = new DeviceGroupViewModel(3);
                        DevicePushMessageManager.DevicePushMessage unused = deviceGroupViewModel.c = devicePushMessage;
                        arrayList2.add(deviceGroupViewModel);
                    }
                } else if (iMessage instanceof WifiPwdChangedMessageManager.DevicePushMessage) {
                    WifiPwdChangedMsgViewModel wifiPwdChangedMsgViewModel = new WifiPwdChangedMsgViewModel(5);
                    IMessage unused2 = wifiPwdChangedMsgViewModel.c = iMessage;
                    arrayList3.add(wifiPwdChangedMsgViewModel);
                } else if (iMessage instanceof CommonMessageManager.CommonMessage) {
                    CommonMsgViewModel commonMsgViewModel = new CommonMsgViewModel(4);
                    IMessage unused3 = commonMsgViewModel.c = iMessage;
                    arrayList3.add(commonMsgViewModel);
                }
            }
        }
        DeviceShareViewModel deviceShareViewModel = new DeviceShareViewModel(1);
        ShareMessageManager.ShareMessage unused4 = deviceShareViewModel.c = shareMessage2;
        arrayList.add(deviceShareViewModel);
        HomeShareViewModel homeShareViewModel = new HomeShareViewModel(6);
        HomeShareMessageManager.HomeShareMessage unused5 = homeShareViewModel.c = homeShareMessage2;
        arrayList.add(homeShareViewModel);
        arrayList.addAll(arrayList2);
        arrayList.addAll(arrayList3);
        return arrayList;
    }

    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
        if (i2 == 1) {
            return new DeviceShareHolder(LayoutInflater.from(SHApplication.getAppContext()).inflate(R.layout.message_center_group_item, viewGroup, false));
        }
        if (i2 == 3) {
            return new DeviceGroupHolder(LayoutInflater.from(SHApplication.getAppContext()).inflate(R.layout.message_item, viewGroup, false));
        }
        if (i2 == 4) {
            return new CommonMsgHolder(LayoutInflater.from(SHApplication.getAppContext()).inflate(R.layout.message_item, viewGroup, false));
        }
        if (i2 == 5) {
            return new WifiPwdChangedMsgHolder(LayoutInflater.from(SHApplication.getAppContext()).inflate(R.layout.message_item, viewGroup, false));
        }
        if (i2 == 6) {
            return new HomeShareHolder(LayoutInflater.from(SHApplication.getAppContext()).inflate(R.layout.message_center_group_item, viewGroup, false));
        }
        return null;
    }

    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i2) {
        if (viewHolder instanceof DeviceShareHolder) {
            a((DeviceShareHolder) viewHolder, i2);
        } else if (viewHolder instanceof HomeShareHolder) {
            a((HomeShareHolder) viewHolder, i2);
        } else if (viewHolder instanceof DeviceGroupHolder) {
            a((DeviceGroupHolder) viewHolder, i2);
        } else if (viewHolder instanceof CommonMsgHolder) {
            a((CommonMsgHolder) viewHolder, i2);
        } else if (viewHolder instanceof WifiPwdChangedMsgHolder) {
            a((WifiPwdChangedMsgHolder) viewHolder, i2);
        }
    }

    private void a(DeviceShareHolder deviceShareHolder, int i2) {
        ShareMessageManager.ShareMessage a2;
        ((ImageView) deviceShareHolder.itemView.findViewById(R.id.device_icon)).setImageResource(R.drawable.msg_center_group_icon_device_share);
        ((TextView) deviceShareHolder.itemView.findViewById(R.id.device_item)).setText(R.string.miio_setting_share);
        TextView textView = (TextView) deviceShareHolder.itemView.findViewById(R.id.device_item_info);
        DeviceShareViewModel deviceShareViewModel = (DeviceShareViewModel) a(i2);
        if (deviceShareViewModel == null || deviceShareViewModel.c == null) {
            textView.setText(R.string.miio_no_message);
        } else {
            deviceShareViewModel.c.d(textView);
        }
        deviceShareHolder.itemView.findViewById(R.id.right_fl).setVisibility(8);
        deviceShareHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(SHApplication.getAppContext(), MessageCenterListActivity.class);
                intent.putExtra("message_type", 1);
                intent.putExtra(MessageCenterListActivity.INTENT_KEY_TITLE, SHApplication.getAppContext().getString(R.string.miio_setting_share));
                intent.addFlags(C.ENCODING_PCM_MU_LAW);
                SHApplication.getAppContext().startActivity(intent);
            }
        });
        a(0, deviceShareHolder.itemView.findViewById(R.id.number_icon));
        if (!(deviceShareViewModel == null || deviceShareViewModel.c == null || (a2 = deviceShareViewModel.c) == null || !a2.h())) {
            a(1, deviceShareHolder.itemView.findViewById(R.id.number_icon));
        }
        deviceShareHolder.itemView.findViewById(R.id.list_space).setVisibility(8);
    }

    public void a(int i2, View view) {
        if (view != null) {
            if (i2 == 0) {
                try {
                    view.setVisibility(4);
                } catch (Throwable unused) {
                }
            } else {
                TextView textView = (TextView) view;
                textView.setVisibility(0);
                if (i2 <= 99) {
                    textView.setText("");
                    return;
                }
                textView.setText("");
                textView.setBackgroundResource(R.drawable.red_point_num);
            }
        }
    }

    private BaseViewModel a(int i2) {
        List<BaseViewModel> list = this.b;
        if (i2 < 0 || i2 >= list.size()) {
            return null;
        }
        return list.get(i2);
    }

    private void a(HomeShareHolder homeShareHolder, int i2) {
        HomeShareMessageManager.HomeShareMessage a2;
        ((ImageView) homeShareHolder.itemView.findViewById(R.id.device_icon)).setImageResource(R.drawable.msg_center_group_icon_home_share);
        ((TextView) homeShareHolder.itemView.findViewById(R.id.device_item)).setText(R.string.home_share);
        TextView textView = (TextView) homeShareHolder.itemView.findViewById(R.id.device_item_info);
        HomeShareViewModel homeShareViewModel = (HomeShareViewModel) a(i2);
        if (homeShareViewModel == null || homeShareViewModel.c == null) {
            textView.setText(R.string.miio_no_message);
        } else {
            homeShareViewModel.c.d(textView);
        }
        homeShareHolder.itemView.findViewById(R.id.right_fl).setVisibility(8);
        homeShareHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(SHApplication.getAppContext(), MessageCenterListActivity.class);
                intent.putExtra("message_type", 8);
                intent.putExtra(MessageCenterListActivity.INTENT_KEY_TITLE, SHApplication.getAppContext().getString(R.string.home_share));
                intent.addFlags(C.ENCODING_PCM_MU_LAW);
                SHApplication.getAppContext().startActivity(intent);
            }
        });
        a(0, homeShareHolder.itemView.findViewById(R.id.number_icon));
        if (!(homeShareViewModel == null || homeShareViewModel.c == null || (a2 = homeShareViewModel.c) == null || !a2.h())) {
            a(1, homeShareHolder.itemView.findViewById(R.id.number_icon));
        }
        homeShareHolder.itemView.findViewById(R.id.list_space).setVisibility(0);
    }

    private void a(DeviceGroupHolder deviceGroupHolder, int i2) {
        BaseViewModel a2 = a(i2);
        if (a2 != null && (a2 instanceof DeviceGroupViewModel)) {
            DeviceGroupViewModel deviceGroupViewModel = (DeviceGroupViewModel) a2;
            if (deviceGroupViewModel.c != null) {
                deviceGroupHolder.a(deviceGroupViewModel.c);
            }
        }
    }

    private void a(CommonMsgHolder commonMsgHolder, int i2) {
        commonMsgHolder.a((CommonMsgViewModel) a(i2), i2);
    }

    private void a(WifiPwdChangedMsgHolder wifiPwdChangedMsgHolder, int i2) {
        wifiPwdChangedMsgHolder.a((WifiPwdChangedMsgViewModel) a(i2), i2);
    }

    public int getItemCount() {
        return this.b.size();
    }

    public int getItemViewType(int i2) {
        List<BaseViewModel> list = this.b;
        if (i2 < 0 || i2 >= list.size()) {
            return 0;
        }
        return list.get(i2).f10521a;
    }

    private class DeviceShareHolder extends RecyclerView.ViewHolder {
        public DeviceShareHolder(View view) {
            super(view);
        }
    }

    private class HomeShareHolder extends RecyclerView.ViewHolder {
        public HomeShareHolder(View view) {
            super(view);
        }
    }

    private class DeviceGroupHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        View f10529a;
        SimpleDraweeView b;
        TextView c;
        CheckBox d;
        TextView e;
        TextView f;
        View g;

        public DeviceGroupHolder(View view) {
            super(view);
            this.b = (SimpleDraweeView) view.findViewById(R.id.device_icon);
            this.b.setHierarchy(new GenericDraweeHierarchyBuilder(this.b.getResources()).setFadeDuration(200).setActualImageScaleType(ScalingUtils.ScaleType.CENTER_INSIDE).setPlaceholderImageScaleType(ScalingUtils.ScaleType.CENTER_INSIDE).build());
            this.c = (TextView) view.findViewById(R.id.right_tv);
            this.d = (CheckBox) view.findViewById(R.id.ratio_btn);
            this.e = (TextView) view.findViewById(R.id.device_item);
            this.f = (TextView) view.findViewById(R.id.device_item_info);
            this.f10529a = view.findViewById(R.id.right_fl);
            this.g = view.findViewById(R.id.new_message_tag);
        }

        public void a(IMessage iMessage) {
            if (iMessage instanceof DevicePushMessageManager.DevicePushMessage) {
                final DevicePushMessageManager.DevicePushMessage devicePushMessage = (DevicePushMessageManager.DevicePushMessage) iMessage;
                String str = devicePushMessage.f10436a;
                if (CoreApi.a().d(str) != null) {
                    DeviceFactory.b(str, this.b);
                    final CharSequence a2 = a(devicePushMessage, this.e);
                    devicePushMessage.a(this.f);
                    TextView textView = this.f;
                    textView.setText(CalendarUtils.b(devicePushMessage.f.receiveTime * 1000) + " " + this.f.getText());
                    this.d.setVisibility(8);
                    this.itemView.findViewById(R.id.arrow).setVisibility(0);
                    this.itemView.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            Intent intent = new Intent(SHApplication.getAppContext(), MessageCenterDeviceListActivityNew.class);
                            intent.putExtra("device_id", devicePushMessage.b);
                            intent.putExtra("title_name", a2);
                            intent.putExtra("device_model", devicePushMessage.f10436a);
                            intent.addFlags(C.ENCODING_PCM_MU_LAW);
                            SHApplication.getAppContext().startActivity(intent);
                        }
                    });
                    MessageCenterAdapter.this.a(0, this.itemView.findViewById(R.id.number_icon));
                    if (iMessage != null && iMessage.h()) {
                        MessageCenterAdapter.this.a(1, this.itemView.findViewById(R.id.number_icon));
                    }
                }
            }
        }

        private CharSequence a(DevicePushMessageManager.DevicePushMessage devicePushMessage, TextView textView) {
            PluginRecord d2 = CoreApi.a().d(devicePushMessage.f10436a);
            if (d2 == null) {
                return null;
            }
            Device b2 = SmartHomeDeviceManager.a().b(devicePushMessage.b);
            if (b2 == null || TextUtils.isEmpty(b2.getName())) {
                boolean z = true;
                if (devicePushMessage.f != null && !TextUtils.isEmpty(devicePushMessage.f.params)) {
                    try {
                        JSONObject jSONObject = new JSONObject(devicePushMessage.f.params);
                        if (!jSONObject.isNull("body")) {
                            JSONObject optJSONObject = jSONObject.optJSONObject("body");
                            if (!optJSONObject.isNull("name")) {
                                String optString = optJSONObject.optString("name");
                                if (!TextUtils.isEmpty(optString)) {
                                    textView.setText(optString);
                                    z = false;
                                }
                            }
                        }
                    } catch (Exception unused) {
                    }
                }
                if (z) {
                    textView.setText(d2.p());
                }
                return textView.getText();
            }
            textView.setText(b2.getName());
            return b2.getName();
        }
    }

    private class CommonMsgHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        View f10522a;
        SimpleDraweeView b;
        TextView c;
        CheckBox d;
        TextView e;
        TextView f;
        View g;

        public CommonMsgHolder(View view) {
            super(view);
            this.b = (SimpleDraweeView) view.findViewById(R.id.device_icon);
            this.b.setHierarchy(new GenericDraweeHierarchyBuilder(this.b.getResources()).setFadeDuration(200).setActualImageScaleType(ScalingUtils.ScaleType.CENTER_INSIDE).setPlaceholderImageScaleType(ScalingUtils.ScaleType.CENTER_INSIDE).build());
            this.c = (TextView) view.findViewById(R.id.right_tv);
            this.d = (CheckBox) view.findViewById(R.id.ratio_btn);
            this.e = (TextView) view.findViewById(R.id.device_item);
            this.f = (TextView) view.findViewById(R.id.device_item_info);
            this.f10522a = view.findViewById(R.id.right_fl);
            this.g = view.findViewById(R.id.new_message_tag);
        }

        public void a(final CommonMsgViewModel commonMsgViewModel, final int i) {
            final IMessage a2 = commonMsgViewModel.c;
            Context appContext = SHApplication.getAppContext();
            a2.a(this.e);
            a2.b(this.f);
            a2.a(this.b);
            if (a2.h()) {
                this.e.setTextColor(appContext.getResources().getColor(R.color.black_80_transparent));
                this.f.setTextColor(appContext.getResources().getColor(R.color.black_50_transparent));
            } else {
                this.e.setTextColor(appContext.getResources().getColor(R.color.class_D));
                this.f.setTextColor(appContext.getResources().getColor(R.color.class_D));
            }
            this.itemView.findViewById(R.id.arrow).setVisibility(8);
            this.d.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    boolean z = MessageCenterAdapter.this.k.get(i);
                    if (MessageCenterAdapter.this.i.get() != null && (MessageCenterAdapter.this.i.get() instanceof MessageCenterActivity)) {
                        ((MessageCenterActivity) MessageCenterAdapter.this.i.get()).setSelected(a2.c(), !z);
                    }
                    if (!z) {
                        MessageCenterAdapter.this.k.put(i, true);
                    } else {
                        MessageCenterAdapter.this.k.delete(i);
                    }
                }
            });
            if (MessageCenterAdapter.this.j) {
                this.f10522a.setVisibility(0);
                this.d.setVisibility(0);
                this.c.setVisibility(8);
                this.itemView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        CommonMsgHolder.this.d.performClick();
                    }
                });
                if (MessageCenterAdapter.this.k.get(i)) {
                    this.d.setChecked(true);
                } else {
                    this.d.setChecked(false);
                }
            } else {
                this.f10522a.setVisibility(0);
                this.d.setVisibility(8);
                this.c.setVisibility(0);
                if (!a2.b()) {
                    this.c.setTextColor(SHApplication.getAppContext().getResources().getColor(R.color.class_D));
                    float f2 = SHApplication.getAppContext().getResources().getDisplayMetrics().density;
                    int i2 = (int) (15.0f * f2);
                    int i3 = (int) (f2 * 7.0f);
                    this.c.setPadding(i2, i3, i2, i3);
                    this.c.setBackgroundResource(0);
                    a2.c(this.c);
                    if (this.c.getVisibility() == 8) {
                        this.f10522a.setVisibility(8);
                    }
                } else {
                    this.c.setTextColor(SHApplication.getAppContext().getResources().getColorStateList(R.color.selector_common_text));
                    this.c.setBackgroundResource(R.drawable.selector_common_btn);
                    float f3 = SHApplication.getAppContext().getResources().getDisplayMetrics().density;
                    int i4 = (int) (15.0f * f3);
                    int i5 = (int) (f3 * 7.0f);
                    this.c.setPadding(i4, i5, i4, i5);
                    this.c.setText(R.string.smarthome_share_accept);
                    this.c.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            MessageCenterAdapter.this.f10518a = new XQProgressDialog((Context) MessageCenterAdapter.this.i.get());
                            MessageCenterAdapter.this.f10518a.setCancelable(false);
                            MessageCenterAdapter.this.f10518a.setMessage(SHApplication.getAppContext().getResources().getString(R.string.loading_share_info));
                            MessageCenterAdapter.this.f10518a.show();
                            MessageCenterAdapter.this.f10518a.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                public void onDismiss(DialogInterface dialogInterface) {
                                    MessageCenterAdapter.this.b();
                                }
                            });
                            a2.a(MessageCenterAdapter.this.f10518a);
                        }
                    });
                }
                this.itemView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        List d = MessageCenterAdapter.this.l;
                        if (i < d.size()) {
                            ((IMessage) d.get(i)).a((Activity) MessageCenterAdapter.this.i.get());
                        }
                    }
                });
                this.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    public boolean onLongClick(View view) {
                        if (commonMsgViewModel.b && !MessageCenterAdapter.this.j) {
                            if (!MessageCenterAdapter.this.k.get(i)) {
                                CommonMsgHolder.this.d.performClick();
                            }
                            if (MessageCenterAdapter.this.i.get() != null && (MessageCenterAdapter.this.i.get() instanceof MessageCenterActivity)) {
                                ((MessageCenterActivity) MessageCenterAdapter.this.i.get()).showDeleteBars();
                            }
                            boolean unused = MessageCenterAdapter.this.j = true;
                            MessageCenterAdapter.this.notifyDataSetChanged();
                            StatHelper.D();
                        }
                        return true;
                    }
                });
            }
        }
    }

    private class WifiPwdChangedMsgHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        View f10533a;
        SimpleDraweeView b;
        TextView c;
        CheckBox d;
        TextView e;
        TextView f;
        View g;

        public WifiPwdChangedMsgHolder(View view) {
            super(view);
            this.b = (SimpleDraweeView) view.findViewById(R.id.device_icon);
            this.b.setHierarchy(new GenericDraweeHierarchyBuilder(this.b.getResources()).setFadeDuration(200).setActualImageScaleType(ScalingUtils.ScaleType.CENTER_INSIDE).setPlaceholderImageScaleType(ScalingUtils.ScaleType.CENTER_INSIDE).build());
            this.c = (TextView) view.findViewById(R.id.right_tv);
            this.d = (CheckBox) view.findViewById(R.id.ratio_btn);
            this.e = (TextView) view.findViewById(R.id.device_item);
            this.f = (TextView) view.findViewById(R.id.device_item_info);
            this.f10533a = view.findViewById(R.id.right_fl);
            this.g = view.findViewById(R.id.new_message_tag);
        }

        public void a(final WifiPwdChangedMsgViewModel wifiPwdChangedMsgViewModel, final int i) {
            final IMessage a2 = wifiPwdChangedMsgViewModel.c;
            Context appContext = SHApplication.getAppContext();
            a2.a(this.e);
            a2.b(this.f);
            a2.a(this.b);
            if (a2.h()) {
                this.e.setTextColor(appContext.getResources().getColor(R.color.black_80_transparent));
                this.f.setTextColor(appContext.getResources().getColor(R.color.black_50_transparent));
            } else {
                this.e.setTextColor(appContext.getResources().getColor(R.color.class_D));
                this.f.setTextColor(appContext.getResources().getColor(R.color.class_D));
            }
            this.itemView.findViewById(R.id.arrow).setVisibility(8);
            this.d.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    boolean z = MessageCenterAdapter.this.k.get(i);
                    if (MessageCenterAdapter.this.i.get() != null && (MessageCenterAdapter.this.i.get() instanceof MessageCenterActivity)) {
                        ((MessageCenterActivity) MessageCenterAdapter.this.i.get()).setSelected(a2.c(), !z);
                    }
                    if (!z) {
                        MessageCenterAdapter.this.k.put(i, true);
                    } else {
                        MessageCenterAdapter.this.k.delete(i);
                    }
                }
            });
            if (MessageCenterAdapter.this.j) {
                this.f10533a.setVisibility(0);
                this.d.setVisibility(0);
                this.c.setVisibility(8);
                this.itemView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        WifiPwdChangedMsgHolder.this.d.performClick();
                    }
                });
                if (MessageCenterAdapter.this.k.get(i)) {
                    this.d.setChecked(true);
                } else {
                    this.d.setChecked(false);
                }
            } else {
                this.f10533a.setVisibility(0);
                this.d.setVisibility(8);
                this.c.setVisibility(0);
                if (!a2.b()) {
                    this.c.setTextColor(SHApplication.getAppContext().getResources().getColor(R.color.class_D));
                    float f2 = SHApplication.getAppContext().getResources().getDisplayMetrics().density;
                    int i2 = (int) (15.0f * f2);
                    int i3 = (int) (f2 * 7.0f);
                    this.c.setPadding(i2, i3, i2, i3);
                    this.c.setBackgroundResource(0);
                    a2.c(this.c);
                    if (this.c.getVisibility() == 8) {
                        this.f10533a.setVisibility(8);
                    }
                } else {
                    this.c.setTextColor(SHApplication.getAppContext().getResources().getColorStateList(R.color.selector_common_text));
                    this.c.setBackgroundResource(R.drawable.selector_common_btn);
                    float f3 = SHApplication.getAppContext().getResources().getDisplayMetrics().density;
                    int i4 = (int) (15.0f * f3);
                    int i5 = (int) (f3 * 7.0f);
                    this.c.setPadding(i4, i5, i4, i5);
                    this.c.setText(R.string.smarthome_share_accept);
                    this.c.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            MessageCenterAdapter.this.f10518a = new XQProgressDialog((Context) MessageCenterAdapter.this.i.get());
                            MessageCenterAdapter.this.f10518a.setCancelable(false);
                            MessageCenterAdapter.this.f10518a.setMessage(SHApplication.getAppContext().getResources().getString(R.string.loading_share_info));
                            MessageCenterAdapter.this.f10518a.show();
                            MessageCenterAdapter.this.f10518a.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                public void onDismiss(DialogInterface dialogInterface) {
                                    MessageCenterAdapter.this.b();
                                }
                            });
                            a2.a(MessageCenterAdapter.this.f10518a);
                        }
                    });
                }
                this.itemView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        List d = MessageCenterAdapter.this.l;
                        if (i < d.size()) {
                            ((IMessage) d.get(i)).a((Activity) MessageCenterAdapter.this.i.get());
                        }
                    }
                });
                this.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    public boolean onLongClick(View view) {
                        if (wifiPwdChangedMsgViewModel.b && !MessageCenterAdapter.this.j) {
                            if (!MessageCenterAdapter.this.k.get(i)) {
                                WifiPwdChangedMsgHolder.this.d.performClick();
                            }
                            if (MessageCenterAdapter.this.i.get() != null && (MessageCenterAdapter.this.i.get() instanceof MessageCenterActivity)) {
                                ((MessageCenterActivity) MessageCenterAdapter.this.i.get()).showDeleteBars();
                            }
                            boolean unused = MessageCenterAdapter.this.j = true;
                            MessageCenterAdapter.this.notifyDataSetChanged();
                            StatHelper.D();
                        }
                        return true;
                    }
                });
            }
        }
    }

    public List<IMessage> a() {
        List<BaseViewModel> list = this.b;
        if (list == null || list.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < list.size(); i2++) {
            BaseViewModel baseViewModel = list.get(i2);
            if (baseViewModel != null && baseViewModel.b && (baseViewModel instanceof CommonMsgViewModel)) {
                arrayList.add(((CommonMsgViewModel) baseViewModel).c);
            }
        }
        return arrayList;
    }

    private static class BaseViewModel {

        /* renamed from: a  reason: collision with root package name */
        protected final int f10521a;
        protected boolean b;

        private BaseViewModel(int i) {
            this.b = false;
            this.f10521a = i;
        }
    }

    private static class DeviceShareViewModel extends BaseViewModel {
        /* access modifiers changed from: private */
        public ShareMessageManager.ShareMessage c;

        private DeviceShareViewModel(int i) {
            super(i);
        }
    }

    private static class DeviceGroupViewModel extends BaseViewModel {
        /* access modifiers changed from: private */
        public DevicePushMessageManager.DevicePushMessage c;

        private DeviceGroupViewModel(int i) {
            super(i);
        }
    }

    private static class HomeShareViewModel extends BaseViewModel {
        /* access modifiers changed from: private */
        public HomeShareMessageManager.HomeShareMessage c;

        private HomeShareViewModel(int i) {
            super(i);
        }
    }

    private static class CommonMsgViewModel extends BaseViewModel {
        /* access modifiers changed from: private */
        public IMessage c;

        private CommonMsgViewModel(int i) {
            super(i);
            this.b = true;
        }
    }

    private static class WifiPwdChangedMsgViewModel extends BaseViewModel {
        /* access modifiers changed from: private */
        public IMessage c;

        private WifiPwdChangedMsgViewModel(int i) {
            super(i);
            this.b = true;
        }
    }
}
