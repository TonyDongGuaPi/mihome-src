package com.xiaomi.smarthome.camera.lowpower.linkage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.activity.CameraBaseActivity;
import com.xiaomi.smarthome.camera.lowpower.LinkageManager;
import com.xiaomi.smarthome.camera.lowpower.entity.LinkageDeviceInfo;
import com.xiaomi.smarthome.camera.view.widget.CenterDrawableCheckBox;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.youpin.app_sdk.url_dispatch.UrlDispatchManger;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DeviceLinkSelectActivity extends CameraBaseActivity implements View.OnClickListener {
    public static final String DEVICE_LIST_COMPLETED = "device_list_request_completed";
    private static final int MAX_SELECTED = 3;
    /* access modifiers changed from: private */
    public MyAdapter adapter;
    /* access modifiers changed from: private */
    public BuyAdapter buyAdapter;
    private ImageView imgLoading;
    private boolean isMultiChoice;
    private ImageView mConfirmView;
    List<LinkageDeviceInfo> mLinkableDevices = new ArrayList();
    private int mMaxLength;
    private BroadcastReceiver mReceiver;
    /* access modifiers changed from: private */
    public RecyclerView rcBuyList;
    private TextView tvNoBleLock;
    private TextView tvTitle;
    private View vBuyGuide;
    private View vDeviceArea;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.linkage_screen_device_selector_layout);
        this.isMultiChoice = getIntent().getBooleanExtra("extra_multi_choice", true);
        this.mMaxLength = getIntent().getIntExtra("max_length", -1);
        this.tvTitle = (TextView) findViewById(R.id.module_a_3_return_title);
        this.mConfirmView = (ImageView) findViewById(R.id.module_a_3_right_iv_setting_btn);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                DeviceLinkSelectActivity.this.finish();
            }
        });
        initData();
    }

    private void initData() {
        if (!LinkageManager.getInstance().isRequesting()) {
            onDataCompleted();
        } else {
            registerListener();
        }
    }

    /* access modifiers changed from: private */
    public void onDataCompleted() {
        this.mLinkableDevices = LinkageManager.getInstance().getLinkageList();
        if (this.mLinkableDevices.size() > 0) {
            getLinkableDeviceInfo();
        } else {
            showBuyGuide();
        }
    }

    private void registerListener() {
        this.mReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                DeviceLinkSelectActivity.this.onDataCompleted();
            }
        };
        LocalBroadcastManager.getInstance(this).registerReceiver(this.mReceiver, new IntentFilter(DEVICE_LIST_COMPLETED));
    }

    private void showBuyGuide() {
        this.tvTitle.setText(R.string.link_device);
        if (this.vBuyGuide == null) {
            this.vBuyGuide = ((ViewStub) findViewById(R.id.vs_buy_guide)).inflate();
        }
        if (this.vDeviceArea != null) {
            this.vDeviceArea.setVisibility(8);
        }
        this.vBuyGuide.findViewById(R.id.common_white_empty_view).setBackgroundColor(Color.parseColor("#f7f7f7"));
        this.vBuyGuide.findViewById(R.id.common_white_empty_view).setVisibility(0);
        ((ImageView) this.vBuyGuide.findViewById(R.id.empty_icon)).setImageResource(R.drawable.ic_plug_rec_action_empty_1000);
        ((TextView) this.vBuyGuide.findViewById(R.id.common_white_empty_text)).setText(R.string.no_linkage_screen_device);
        this.rcBuyList = (RecyclerView) this.vBuyGuide.findViewById(R.id.buy_list);
        this.rcBuyList.setLayoutManager(new GridLayoutManager((Context) this, 3, 1, false));
        this.buyAdapter = new BuyAdapter();
        this.rcBuyList.setAdapter(this.buyAdapter);
        getBuyDeviceList();
    }

    private void getBuyDeviceList() {
        XmPluginHostApi.instance().getAppConfig("screen_device_recommend", "zh_CN", "2", new Callback<String>() {
            public void onFailure(int i, String str) {
            }

            public void onSuccess(String str) {
                try {
                    JSONArray jSONArray = new JSONArray(str);
                    final ArrayList arrayList = new ArrayList();
                    for (int i = 0; i < jSONArray.length(); i++) {
                        arrayList.add(jSONArray.getJSONObject(i));
                    }
                    DeviceLinkSelectActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            DeviceLinkSelectActivity.this.rcBuyList.setVisibility(0);
                            DeviceLinkSelectActivity.this.buyAdapter.setData(arrayList);
                            DeviceLinkSelectActivity.this.buyAdapter.notifyDataSetChanged();
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void getLinkableDeviceInfo() {
        this.tvTitle.setText(R.string.choose_screen_device);
        this.mConfirmView.setVisibility(0);
        this.mConfirmView.setImageResource(R.drawable.title_right_tick_drawable);
        this.mConfirmView.setOnClickListener(this);
        showLinkableDevices();
    }

    private void showLinkableDevices() {
        this.vDeviceArea = ((ViewStub) findViewById(R.id.vs_condition_list)).inflate();
        this.tvNoBleLock = (TextView) this.vDeviceArea.findViewById(R.id.tv_not_connect_ble);
        String string = getString(R.string.device_rec_condition_no_gateway);
        int indexOf = string.indexOf("#start#");
        int indexOf2 = string.indexOf("#end#") - "#start#".length();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append(string.replace("#start#", "").replace("#end#", ""));
        AnonymousClass3 r0 = new ClickableSpan() {
            public void onClick(View view) {
            }

            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setColor(DeviceLinkSelectActivity.this.getResources().getColor(R.color.class_text_33));
                textPaint.setUnderlineText(true);
            }
        };
        if (indexOf >= 0 && indexOf2 > 0) {
            try {
                spannableStringBuilder.setSpan(r0, indexOf, indexOf2, 33);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.tvNoBleLock.setText(spannableStringBuilder);
        this.tvNoBleLock.setMovementMethod(LinkMovementMethod.getInstance());
        if (this.mLinkableDevices.size() > 0) {
            this.tvNoBleLock.setVisibility(8);
        } else {
            this.tvNoBleLock.setVisibility(0);
        }
        this.imgLoading = (ImageView) this.vDeviceArea.findViewById(R.id.loading);
        RecyclerView recyclerView = (RecyclerView) this.vDeviceArea.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.adapter = new MyAdapter();
        recyclerView.setAdapter(this.adapter);
        if (this.vBuyGuide != null) {
            this.vBuyGuide.setVisibility(8);
        }
        this.imgLoading.setVisibility(8);
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.mReceiver != null) {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(this.mReceiver);
        }
    }

    public class ConditionViewHolder extends RecyclerView.ViewHolder {
        public CenterDrawableCheckBox checkBox;
        public TextView deviceName;
        public View divider;
        public View dividerLast;
        public View iconSelected;
        public SimpleDraweeView sdImg;
        public TextView tvRoomName;

        public ConditionViewHolder(View view) {
            super(view);
            this.deviceName = (TextView) view.findViewById(R.id.device_name);
            this.divider = view.findViewById(R.id.divider_x);
            this.dividerLast = view.findViewById(R.id.divider_last);
            this.iconSelected = view.findViewById(R.id.icon_selected);
            this.tvRoomName = (TextView) view.findViewById(R.id.room_name);
            this.sdImg = (SimpleDraweeView) view.findViewById(R.id.sd_device_img);
            this.checkBox = (CenterDrawableCheckBox) view.findViewById(R.id.select_checkbox);
        }

        public void fillWithCondition(LinkageDeviceInfo linkageDeviceInfo, final int i) {
            Device b = SmartHomeDeviceManager.a().b(linkageDeviceInfo.getDeviceStat().did);
            if (b == null) {
                b = DeviceFactory.k(linkageDeviceInfo.getDeviceStat().model);
            }
            StringBuilder sb = new StringBuilder();
            sb.append(TextUtils.isEmpty(linkageDeviceInfo.getDeviceStat().name) ? "" : linkageDeviceInfo.getDeviceStat().name);
            if (!b.isOnline) {
                sb.append(Operators.BRACKET_START_STR);
                sb.append(DeviceLinkSelectActivity.this.getString(R.string.offline_device));
                sb.append(Operators.BRACKET_END_STR);
            }
            this.deviceName.setText(sb.toString());
            String r = HomeManager.a().r(b.did);
            TextView textView = this.tvRoomName;
            if (TextUtils.isEmpty(r)) {
                r = "";
            }
            textView.setText(r);
            if (b != null) {
                DeviceFactory.b(b.model, this.sdImg);
            } else {
                DeviceFactory.b((String) null, this.sdImg);
            }
            this.checkBox.setChecked(linkageDeviceInfo.isSelected());
            this.checkBox.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    DeviceLinkSelectActivity.this.onCheckedChanged(ConditionViewHolder.this.checkBox.isChecked(), i);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void onCheckedChanged(boolean z, int i) {
        if (!this.isMultiChoice) {
            for (int i2 = 0; i2 < this.mLinkableDevices.size(); i2++) {
                LinkageDeviceInfo linkageDeviceInfo = this.mLinkableDevices.get(i2);
                if (i2 != i) {
                    linkageDeviceInfo.setSelected(false);
                } else {
                    linkageDeviceInfo.setSelected(z);
                }
            }
            this.mConfirmView.post(new Runnable() {
                public void run() {
                    DeviceLinkSelectActivity.this.adapter.notifyDataSetChanged();
                }
            });
            return;
        }
        int i3 = 0;
        for (LinkageDeviceInfo isSelected : this.mLinkableDevices) {
            if (isSelected.isSelected()) {
                i3++;
            }
        }
        if (i3 < this.mMaxLength || this.mLinkableDevices.get(i).isSelected()) {
            this.mLinkableDevices.get(i).setSelected(z);
        } else {
            ToastUtil.a((CharSequence) getString(R.string.max_devices_exceeded, new Object[]{Integer.valueOf(this.mMaxLength)}));
            this.mLinkableDevices.get(i).setSelected(false);
        }
        this.mConfirmView.post(new Runnable() {
            public void run() {
                DeviceLinkSelectActivity.this.adapter.notifyDataSetChanged();
            }
        });
    }

    public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        public long getItemId(int i) {
            return (long) i;
        }

        public int getItemViewType(int i) {
            return 0;
        }

        public MyAdapter() {
        }

        @NonNull
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new ConditionViewHolder(LayoutInflater.from(DeviceLinkSelectActivity.this).inflate(R.layout.linkage_device_select_item, (ViewGroup) null));
        }

        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            ((ConditionViewHolder) viewHolder).fillWithCondition(DeviceLinkSelectActivity.this.mLinkableDevices.get(i), i);
        }

        public int getItemCount() {
            return DeviceLinkSelectActivity.this.mLinkableDevices.size();
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.module_a_3_right_iv_setting_btn) {
            submiteInfo();
        }
    }

    private void submiteInfo() {
        LinkageManager.getInstance().submitSelectedInfo(this.mDeviceStat.model, this.mDeviceStat.did, new Callback<JSONObject>() {
            public void onSuccess(JSONObject jSONObject) {
                DeviceLinkSelectActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        ToastUtil.a((int) R.string.action_success);
                        DeviceLinkSelectActivity.this.updateLinkageDataSuccess();
                        DeviceLinkSelectActivity.this.finish();
                    }
                });
            }

            public void onFailure(int i, String str) {
                DeviceLinkSelectActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        ToastUtil.a((int) R.string.action_fail);
                        DeviceLinkSelectActivity.this.updateLinkageDataFail();
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public void updateLinkageDataFail() {
        for (LinkageDeviceInfo next : this.mLinkableDevices) {
            next.setSelected(next.isBinded());
        }
        this.adapter.notifyDataSetChanged();
    }

    /* access modifiers changed from: private */
    public void updateLinkageDataSuccess() {
        for (LinkageDeviceInfo next : this.mLinkableDevices) {
            next.setBinded(next.isSelected());
        }
        this.adapter.notifyDataSetChanged();
    }

    public static class BuyViewHolder extends RecyclerView.ViewHolder {
        public TextView modelName;
        public SimpleDraweeView sdImg;

        public BuyViewHolder(@NonNull View view) {
            super(view);
            this.modelName = (TextView) view.findViewById(R.id.model_name);
            this.sdImg = (SimpleDraweeView) view.findViewById(R.id.sd_model_img);
        }
    }

    public class BuyAdapter extends RecyclerView.Adapter<BuyViewHolder> {
        private List<JSONObject> buyData = new ArrayList();

        public long getItemId(int i) {
            return (long) i;
        }

        public BuyAdapter() {
        }

        @NonNull
        public BuyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new BuyViewHolder(LayoutInflater.from(DeviceLinkSelectActivity.this).inflate(R.layout.light_rec_scene_buy_item, (ViewGroup) null));
        }

        public void onBindViewHolder(@NonNull BuyViewHolder buyViewHolder, int i) {
            JSONObject jSONObject = this.buyData.get(i);
            String optString = jSONObject.optString("model");
            String optString2 = jSONObject.optString("deviceBuyLink");
            String optString3 = jSONObject.optString("deviceName");
            if (!TextUtils.isEmpty(optString)) {
                DeviceFactory.b(optString, buyViewHolder.sdImg);
                if (CoreApi.a().d(optString) != null) {
                    optString3 = CoreApi.a().d(optString).p();
                }
                buyViewHolder.modelName.setText(optString3);
                buyViewHolder.itemView.setOnClickListener(new View.OnClickListener(optString2) {
                    private final /* synthetic */ String f$0;

                    {
                        this.f$0 = r1;
                    }

                    public final void onClick(View view) {
                        UrlDispatchManger.a().c(this.f$0);
                    }
                });
            }
        }

        public int getItemCount() {
            if (this.buyData == null) {
                return 0;
            }
            return this.buyData.size();
        }

        /* access modifiers changed from: private */
        public void setData(List<JSONObject> list) {
            this.buyData.clear();
            this.buyData.addAll(list);
            if (list.size() <= 2) {
                DeviceLinkSelectActivity.this.rcBuyList.setLayoutManager(new GridLayoutManager((Context) DeviceLinkSelectActivity.this, 2, 1, false));
            }
            notifyDataSetChanged();
        }
    }

    public void finish() {
        super.finish();
        for (LinkageDeviceInfo next : this.mLinkableDevices) {
            if (next != null) {
                next.setSelected(next.isBinded());
            }
        }
    }
}
