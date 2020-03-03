package com.xiaomi.smarthome.miio.page;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import com.umeng.analytics.MobclickAgent;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceHelper;
import com.xiaomi.smarthome.device.utils.ClientRemarkInputView;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.StringUtil;
import com.xiaomi.smarthome.library.common.widget.FlowGroup;
import java.util.Set;

public class DeviceTagActivity extends BaseActivity implements View.OnClickListener, DeviceTagConstants {
    private View g;
    private View h;
    private FlowGroup i;
    private View j;
    private View k;
    private FlowGroup l;
    private View m;
    private View n;
    private FlowGroup o;
    private View p;
    private View q;
    private FlowGroup r;
    private View s;
    private View t;
    private FlowGroup u;
    private View v;
    private View w;
    private BroadcastReceiver x;
    private boolean y = false;
    private DisplayMetrics z;

    private void b() {
    }

    /* access modifiers changed from: private */
    public void b(Intent intent) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_device_tag);
        this.v = findViewById(R.id.title_bar);
        this.v.setAlpha(0.0f);
        this.v.setOnClickListener(this);
        this.w = findViewById(R.id.device_tag_bottom);
        this.w.setOnClickListener(this);
        this.g = findViewById(R.id.category_tag_title);
        this.h = findViewById(R.id.category_tag_sep);
        this.i = (FlowGroup) findViewById(R.id.category_tag_container);
        this.j = findViewById(R.id.brand_tag_title);
        this.k = findViewById(R.id.brand_tag_sep);
        this.l = (FlowGroup) findViewById(R.id.brand_tag_container);
        this.m = findViewById(R.id.router_tag_title);
        this.n = findViewById(R.id.router_tag_sep);
        this.o = (FlowGroup) findViewById(R.id.router_tag_container);
        this.p = findViewById(R.id.capability_tag_title);
        this.q = findViewById(R.id.capability_tag_sep);
        this.r = (FlowGroup) findViewById(R.id.capability_tag_container);
        this.s = findViewById(R.id.custom_tag_title);
        this.t = findViewById(R.id.custom_tag_sep);
        this.u = (FlowGroup) findViewById(R.id.custom_tag_container);
        b();
        a();
        View findViewById = findViewById(R.id.device_tag_container);
        findViewById.clearAnimation();
        findViewById.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_in_top));
        ObjectAnimator ofObject = ObjectAnimator.ofObject(findViewById(R.id.device_tag_container_parent), "backgroundColor", new ArgbEvaluator(), new Object[]{Integer.valueOf(getResources().getColor(R.color.black_00_transparent)), Integer.valueOf(getResources().getColor(R.color.black_30_transparent))});
        ofObject.setDuration(300);
        ofObject.start();
        ObjectAnimator ofObject2 = ObjectAnimator.ofObject(findViewById(R.id.device_tag_bottom), "backgroundColor", new ArgbEvaluator(), new Object[]{Integer.valueOf(getResources().getColor(R.color.black_00_transparent)), Integer.valueOf(getResources().getColor(R.color.black_30_transparent))});
        ofObject2.setDuration(300);
        ofObject2.start();
    }

    private void a() {
        if (this.x == null) {
            this.x = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    if ("device_tag_selected_action".equals(intent.getAction())) {
                        DeviceTagActivity.this.a(intent);
                    } else if (DeviceTagInterface.h.equals(intent.getAction())) {
                        DeviceTagActivity.this.b(intent);
                    }
                }
            };
        }
    }

    /* access modifiers changed from: private */
    public void a(Intent intent) {
        if (intent.getBooleanExtra("tag_selected_param", false)) {
            intent.getStringExtra("device_tag_param");
            intent.getStringExtra("router_bssid_param");
        }
        finish();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter("device_tag_selected_action");
        intentFilter.addAction(DeviceTagInterface.h);
        LocalBroadcastManager.getInstance(this).registerReceiver(this.x, intentFilter);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.x);
    }

    public void finish() {
        this.y = true;
        View findViewById = findViewById(R.id.device_tag_container);
        findViewById.clearAnimation();
        findViewById.startAnimation(AnimationUtils.loadAnimation(findViewById.getContext(), R.anim.slide_out_top));
        ObjectAnimator ofObject = ObjectAnimator.ofObject(findViewById(R.id.device_tag_container_parent), "backgroundColor", new ArgbEvaluator(), new Object[]{Integer.valueOf(findViewById.getContext().getResources().getColor(R.color.black_30_transparent)), Integer.valueOf(findViewById.getContext().getResources().getColor(R.color.black_00_transparent))});
        ofObject.setDuration(300);
        ofObject.start();
        ObjectAnimator ofObject2 = ObjectAnimator.ofObject(findViewById(R.id.device_tag_bottom), "backgroundColor", new ArgbEvaluator(), new Object[]{Integer.valueOf(findViewById.getContext().getResources().getColor(R.color.black_30_transparent)), Integer.valueOf(findViewById.getContext().getResources().getColor(R.color.black_00_transparent))});
        ofObject2.setDuration(300);
        ofObject2.start();
        ofObject2.addListener(new Animator.AnimatorListener() {
            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
            }

            public void onAnimationEnd(Animator animator) {
                DeviceTagActivity.this.c();
            }
        });
    }

    /* access modifiers changed from: private */
    public void c() {
        super.finish();
        overridePendingTransition(0, 0);
    }

    public boolean showTags(Activity activity, LayoutInflater layoutInflater, FlowGroup flowGroup, Set<String> set) {
        boolean z2 = false;
        if (flowGroup == null || set == null) {
            return false;
        }
        for (String a2 : set) {
            z2 |= a(activity, layoutInflater, flowGroup, a2);
        }
        return z2;
    }

    public boolean showRouterTags(Activity activity, LayoutInflater layoutInflater, FlowGroup flowGroup, Set<String> set) {
        boolean z2 = false;
        if (flowGroup == null || set == null) {
            return false;
        }
        flowGroup.removeAllViews();
        DeviceTagInterface<Device> b = SmartHomeDeviceHelper.a().b();
        synchronized (set) {
            for (String next : set) {
                z2 |= a(activity, layoutInflater, flowGroup, b.e(next), next);
            }
        }
        return z2;
    }

    private boolean a(final Activity activity, LayoutInflater layoutInflater, final FlowGroup flowGroup, final String str) {
        if (flowGroup == null || StringUtil.c(str)) {
            return false;
        }
        View inflate = layoutInflater.inflate(R.layout.item_device_tag, (ViewGroup) null);
        TextView textView = (TextView) inflate.findViewById(R.id.device_tag_text);
        textView.setText(a(textView.getPaint(), str, 0));
        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DeviceTagActivity.a((Context) activity, str, (String) null);
                DeviceTagActivity.this.a(flowGroup);
            }
        });
        flowGroup.addView(inflate);
        return true;
    }

    private String a(TextPaint textPaint, String str, int i2) {
        if (this.z == null) {
            this.z = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(this.z);
        }
        int i3 = (this.z.widthPixels - 198) - i2;
        if (str != null && str.length() > 0) {
            int length = str.length();
            float[] fArr = new float[length];
            textPaint.getTextWidths(str, fArr);
            int i4 = 0;
            for (int i5 = 0; i5 < length; i5++) {
                i4 += (int) Math.ceil((double) fArr[i5]);
                if (i4 > i3) {
                    return str.substring(0, i5 - 3) + getString(R.string.tag_ellipsis);
                }
            }
        }
        return str;
    }

    private boolean a(final Activity activity, LayoutInflater layoutInflater, FlowGroup flowGroup, String str, final String str2) {
        if (flowGroup == null || StringUtil.c(str)) {
            return false;
        }
        View inflate = layoutInflater.inflate(R.layout.item_device_tag, (ViewGroup) null);
        final TextView textView = (TextView) inflate.findViewById(R.id.device_tag_text);
        textView.setText(a(textView.getPaint(), str, 79));
        final Activity activity2 = activity;
        final String str3 = str;
        final String str4 = str2;
        final FlowGroup flowGroup2 = flowGroup;
        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DeviceTagActivity.a((Context) activity2, str3, str4);
                DeviceTagActivity.this.a(flowGroup2);
            }
        });
        View findViewById = inflate.findViewById(R.id.device_tag_edit);
        findViewById.setVisibility(0);
        findViewById.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                final DeviceTagInterface<Device> b2 = SmartHomeDeviceHelper.a().b();
                final ClientRemarkInputView clientRemarkInputView = (ClientRemarkInputView) activity.getLayoutInflater().inflate(R.layout.client_remark_input_view, (ViewGroup) null);
                MLAlertDialog d2 = new MLAlertDialog.Builder(activity).a((int) R.string.tag_edit_title).b((View) clientRemarkInputView).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        clientRemarkInputView.getEditText().getText().toString();
                        textView.setText(b2.e(str2));
                    }
                }).b((int) R.string.cancel, (DialogInterface.OnClickListener) null).d();
                String b3 = b2.b(str2);
                clientRemarkInputView.initialize((ClientRemarkInputView.RenameInterface) null, d2, b2.b(str2, b3), b3, false);
            }
        });
        flowGroup.addView(inflate);
        return true;
    }

    private void a(View view, View view2, View view3) {
        view.setVisibility(8);
        view2.setVisibility(8);
        view3.setVisibility(8);
    }

    public void onClick(View view) {
        if (view == this.v || view == this.w) {
            a((Context) this, (String) null, (String) null);
            finish();
        }
    }

    public void onBackPressed() {
        a((Context) this, (String) null, (String) null);
        super.onBackPressed();
    }

    /* access modifiers changed from: private */
    public static void a(Context context, String str, String str2) {
        Intent intent = new Intent("device_tag_selected_action");
        if (StringUtil.c(str)) {
            intent.putExtra("tag_selected_param", false);
        } else {
            intent.putExtra("tag_selected_param", true);
            intent.putExtra("device_tag_param", str);
            if (!StringUtil.c(str2)) {
                intent.putExtra("router_bssid_param", str2);
            }
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    /* access modifiers changed from: private */
    public void a(FlowGroup flowGroup) {
        String str;
        if (flowGroup == this.i) {
            str = "tag_category";
        } else if (flowGroup == this.l) {
            str = "tag_brand";
        } else if (flowGroup == this.o) {
            str = "tag_router";
        } else if (flowGroup == this.r) {
            str = "tag_capability";
        } else {
            str = flowGroup == this.u ? "tag_custom" : null;
        }
        if (!StringUtil.c(str)) {
            MobclickAgent.a((Context) this, "mihome", str);
        }
    }
}
