package com.xiaomi.smarthome.feedback;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.log.CoreLogUtilGrey;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.widget.ExpandGridView;
import com.xiaomi.smarthome.messagecenter.MessageCenter;
import com.xiaomi.smarthome.messagecenter.MessageCenterListener;
import java.util.ArrayList;
import java.util.List;

public class FeedBackMainActivity extends BaseActivity {
    public static final String RED_COUNT = "red_count";
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public FeedBackAdapter f15926a;
    /* access modifiers changed from: private */
    public FeedBackAdapter b;
    private ExpandGridView c;
    private ExpandGridView d;
    /* access modifiers changed from: private */
    public View e;
    /* access modifiers changed from: private */
    public int f = 0;
    private List<String> g = new ArrayList();
    private List<String> h = new ArrayList();
    private MessageCenterListener i = new MessageCenterListener() {
        public void onCheckNewMessageFinished(int i) {
        }

        public void onCheckSignNotifyFinished(boolean z, boolean z2) {
        }

        public void onCheckUpdateFinished(boolean z, boolean z2) {
        }

        public void onCheckNewFeedbackFinished(int i) {
            int unused = FeedBackMainActivity.this.f = i;
            if (FeedBackMainActivity.this.e != null) {
                FeedBackMainActivity.this.e.setVisibility(FeedBackMainActivity.this.f == 0 ? 8 : 0);
            }
        }
    };

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.feed_back_main_layout);
        this.f15926a = new FeedBackAdapter(this);
        this.b = new FeedBackAdapter(this);
        this.f = getIntent().getIntExtra(RED_COUNT, 0);
        a();
        b();
        c();
    }

    private void a() {
        List<Device> list;
        ArrayList<Device> arrayList = new ArrayList<>();
        Home m = HomeManager.a().m();
        if (m == null || m.p()) {
            list = SmartHomeDeviceManager.a().d();
        } else {
            list = HomeManager.a().o(HomeManager.a().l());
        }
        arrayList.addAll(list);
        for (Device device : arrayList) {
            if (!device.isSubDevice() && !device.isVirtualDevice() && !this.g.contains(device.model)) {
                this.g.add(device.model);
            }
        }
        this.h.add(FeedbackApi.COMMON_EXP);
        if (!CoreApi.a().D()) {
            this.h.add("shop");
        }
        this.h.add(FeedbackApi.AUTO_SCENE);
        this.h.add(FeedbackApi.BLE_GATEWAY);
        this.h.add("other");
    }

    private void b() {
        TextView textView = (TextView) findViewById(R.id.module_a_3_return_title);
        if (textView != null) {
            textView.setText(R.string.feedback_title);
        }
        View findViewById = findViewById(R.id.module_a_3_return_btn);
        if (findViewById != null) {
            findViewById.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    FeedBackMainActivity.this.finish();
                }
            });
        }
        View findViewById2 = findViewById(R.id.image_button);
        if (findViewById2 != null) {
            findViewById2.setVisibility(0);
            findViewById2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent();
                    int unused = FeedBackMainActivity.this.f = 0;
                    intent.setClass(FeedBackMainActivity.this.getContext(), FeedbackHistoryActivity.class);
                    FeedBackMainActivity.this.startActivity(intent);
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MessageCenter.a().a(MessageCenter.c, this.i);
        MessageCenter.a().c();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        MessageCenter.a().b(MessageCenter.c, this.i);
    }

    private void c() {
        this.c = (ExpandGridView) findViewById(R.id.feed_back_my_device_grid_view);
        this.d = (ExpandGridView) findViewById(R.id.feed_back_more_grid_view);
        this.c.setAdapter(this.f15926a);
        this.f15926a.a(this.g);
        this.e = findViewById(R.id.image_new_tag);
        this.c.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                String str = (String) FeedBackMainActivity.this.f15926a.getItem(i);
                if (str != null && !str.isEmpty()) {
                    Intent intent = new Intent();
                    intent.setClass(FeedBackMainActivity.this.getContext(), FeedbackCommonProblemActivity.class);
                    intent.putExtra("extra_model", str);
                    FeedBackMainActivity.this.startActivity(intent);
                }
            }
        });
        this.d.setAdapter(this.b);
        this.b.a(this.h);
        this.d.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                String str = (String) FeedBackMainActivity.this.b.getItem(i);
                if (str != null && !str.isEmpty()) {
                    Intent intent = new Intent();
                    intent.setClass(FeedBackMainActivity.this.getContext(), FeedbackCommonProblemActivity.class);
                    intent.putExtra("extra_model", str);
                    FeedBackMainActivity.this.startActivity(intent);
                }
            }
        });
        this.d.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (i != 0) {
                    return false;
                }
                FeedBackMainActivity.this.sendBroadcast(new Intent(CoreLogUtilGrey.f16120a).putExtra(CoreLogUtilGrey.b, true));
                ToastUtil.a((int) R.string.enable_greylog);
                return false;
            }
        });
    }

    /* access modifiers changed from: package-private */
    public boolean containSameSerialsModel(List<String> list, String str) {
        if (list == null || list.isEmpty()) {
            return false;
        }
        if (str == null || str.isEmpty()) {
            return true;
        }
        for (String i2 : list) {
            if (DeviceFactory.i(i2, str)) {
                return true;
            }
        }
        return false;
    }
}
