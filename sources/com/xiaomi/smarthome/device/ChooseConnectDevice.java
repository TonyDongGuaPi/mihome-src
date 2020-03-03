package com.xiaomi.smarthome.device;

import android.app.Dialog;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.ChooseConnectDeviceAdapter;
import com.xiaomi.smarthome.device.DeviceScanManager;
import com.xiaomi.smarthome.device.choosedevice.ScanDeviceView;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.server_compact.ServerBean;
import com.xiaomi.smarthome.framework.page.BaseFragment;
import com.xiaomi.smarthome.framework.statistic.StatHelper;
import com.xiaomi.smarthome.framework.webview.WebShellActivity;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.smartconfig.NetworkDetector;
import com.xiaomi.smarthome.smartconfig.PushBindEntity;
import com.xiaomi.smarthome.stat.STAT;
import java.util.Collection;
import java.util.List;

public class ChooseConnectDevice extends BaseFragment implements DeviceScanManager.OnScanListener, ScanDeviceView.ScanViewListener {

    /* renamed from: a  reason: collision with root package name */
    public static final int f14766a = 1000;
    private static final int b = 300;
    /* access modifiers changed from: private */
    public ChooseConnectDeviceAdapter c;
    private View d;
    private Dialog e = null;
    private DeviceScanManager f;
    @BindView(2131429833)
    View inScanHint;
    @BindView(2131428160)
    TextView mHelpTips;
    @BindView(2131428804)
    ListView mListView;
    @BindView(2131431012)
    Button mMoreDevice;
    @BindView(2131431746)
    ImageView mRadarBgImg;
    @BindView(2131432136)
    ImageView mRadarImage;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.d == null) {
            this.d = layoutInflater.inflate(R.layout.fragment_choose_connect_device, viewGroup, false);
            ButterKnife.bind((Object) this, this.d);
            this.d.findViewById(R.id.scan_no_device_desc).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    String str;
                    STAT.d.K();
                    STAT.d.n(ChooseConnectDevice.this.c.getCount());
                    Intent intent = new Intent(ChooseConnectDevice.this.getActivity(), WebShellActivity.class);
                    Bundle bundle = new Bundle();
                    ServerBean F = CoreApi.a().F();
                    if (CoreApi.a().D()) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("https://");
                        if (F != null) {
                            str = F.f1530a + ".";
                        } else {
                            str = "";
                        }
                        sb.append(str);
                        sb.append("home.mi.com/views/faqDetail.html?question=");
                        sb.append(ChooseConnectDevice.this.getString(R.string.param_question_cannot_find_near_device));
                        bundle.putString("url", sb.toString());
                    } else {
                        bundle.putString("url", "https://home.mi.com/views/faqDetail.html?question=" + ChooseConnectDevice.this.getString(R.string.param_question_cannot_find_near_device));
                    }
                    intent.putExtras(bundle);
                    ChooseConnectDevice.this.startActivity(intent);
                }
            });
            this.c = new ChooseConnectDeviceAdapter(this.mContext, this, getActivity().getIntent(), R.layout.choose_device_nearby_item, false);
            NetworkDetector.a().a("plus");
            this.mHelpTips.setVisibility(0);
            this.mMoreDevice.setVisibility(8);
            this.mListView.setVisibility(0);
            this.mMoreDevice.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    STAT.d.L();
                    STAT.d.bc();
                    ChooseConnectDevice.this.mListView.setVisibility(0);
                }
            });
            this.mHelpTips.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    STAT.d.K();
                    STAT.d.n(ChooseConnectDevice.this.c.getCount());
                    ChooseConnectDevice.this.b();
                }
            });
            VirtualDeviceManager.a();
            this.mListView.setAdapter(this.c);
            this.mRadarImage.post(new Runnable() {
                public void run() {
                    ChooseConnectDevice.this.c();
                }
            });
            StatHelper.l();
        }
        if (this.f != null) {
            this.f.getScanCache(this);
        }
        return this.d;
    }

    public int a() {
        if (this.c != null) {
            return this.c.getCount();
        }
        return 0;
    }

    public void a(ChooseConnectDeviceAdapter.ScanDeviceType scanDeviceType, String str, ScanResult scanResult, BleDevice bleDevice, MiTVDevice miTVDevice, PushBindEntity pushBindEntity) {
        this.c.a(scanDeviceType, str, scanResult, bleDevice, miTVDevice, pushBindEntity);
    }

    /* access modifiers changed from: private */
    public void c() {
        if (isValid()) {
            int i = DisplayUtils.a(getContext()).x;
            int width = this.mRadarBgImg.getWidth();
            if (width != 0) {
                int height = (this.mRadarBgImg.getHeight() * i) / width;
                int a2 = (height / 2) - DisplayUtils.a(258.0f);
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mRadarBgImg.getLayoutParams();
                layoutParams.height = height;
                layoutParams.width = i;
                layoutParams.setMargins(0, -a2, 0, 0);
                this.mRadarBgImg.setLayoutParams(layoutParams);
            }
            int i2 = DisplayUtils.a(getContext()).y;
            int height2 = this.mRadarImage.getHeight();
            int width2 = this.mRadarImage.getWidth();
            if (height2 != 0 && width2 != 0) {
                double d2 = (double) i2;
                Double.isNaN(d2);
                int i3 = (int) (d2 * 0.9d);
                RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams((width2 * i3) / height2, i3);
                layoutParams2.setMargins(0, DisplayUtils.a(258.0f) - i3, 0, 0);
                this.mRadarImage.setLayoutParams(layoutParams2);
                this.mRadarImage.setVisibility(0);
                this.mRadarImage.postDelayed(new Runnable() {
                    public void run() {
                        ChooseConnectDevice.this.d();
                    }
                }, 200);
            }
        }
    }

    public void a(int i) {
        if (isValid()) {
            if (i < 7 || (i == 7 && this.c.getCount() == 7)) {
                this.mHelpTips.setVisibility(0);
            } else if (i == 7 && this.c.getCount() > 7) {
                this.mHelpTips.setVisibility(8);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void b() {
        String str;
        Intent intent = new Intent(getActivity(), WebShellActivity.class);
        Bundle bundle = new Bundle();
        ServerBean F = CoreApi.a().F();
        if (CoreApi.a().D()) {
            StringBuilder sb = new StringBuilder();
            sb.append("https://");
            if (F != null) {
                str = F.f1530a + ".";
            } else {
                str = "";
            }
            sb.append(str);
            sb.append("home.mi.com/views/faqDetail.html?question=");
            sb.append(getString(R.string.param_question_cannot_find_near_device));
            bundle.putString("url", sb.toString());
        } else {
            bundle.putString("url", "https://home.mi.com/views/faqDetail.html?question=" + getString(R.string.param_question_cannot_find_near_device));
        }
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /* access modifiers changed from: private */
    public void d() {
        if (isValid()) {
            RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 359.0f, 1, 0.5f, 1, 1.0f);
            rotateAnimation.setInterpolator(new LinearInterpolator());
            rotateAnimation.setDuration(5000);
            rotateAnimation.setRepeatCount(-1);
            rotateAnimation.setFillAfter(true);
            this.mRadarImage.setAnimation(rotateAnimation);
            this.mRadarImage.startAnimation(rotateAnimation);
        }
    }

    private void e() {
        this.mRadarImage.clearAnimation();
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (z) {
            try {
                if (this.c != null) {
                    STAT.c.h(this.c.getCount());
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void onDetach() {
        super.onDetach();
        e();
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.e != null && this.e.isShowing()) {
            this.e.dismiss();
            this.e = null;
        }
    }

    public void onScan(List<?> list) {
        int size = list.size();
        if (size == 0) {
            this.inScanHint.setVisibility(0);
            this.mListView.setVisibility(8);
        } else {
            this.mListView.setVisibility(0);
            this.inScanHint.setVisibility(8);
            if (this.c != null) {
                this.c.a((Collection<?>) list);
            }
        }
        a(size);
    }

    public void a(DeviceScanManager deviceScanManager) {
        this.f = deviceScanManager;
        if (this.c != null) {
            this.c.a(deviceScanManager);
        }
    }
}
