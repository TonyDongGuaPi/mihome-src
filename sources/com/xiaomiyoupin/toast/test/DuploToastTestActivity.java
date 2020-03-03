package com.xiaomiyoupin.toast.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.xiaomiyoupin.toast.R;
import com.xiaomiyoupin.toast.YPDToast;

public class DuploToastTestActivity extends Activity {
    /* access modifiers changed from: private */
    public int toastId;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.ypd_activity_duplo_toast_test);
        findViewById(R.id.ypd_btn_duplo_toast_success).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                YPDToast.getInstance().toast(DuploToastTestActivity.this, "成功样式！", 5, true, 2);
            }
        });
        findViewById(R.id.ypd_btn_duplo_toast_failure).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                YPDToast.getInstance().toast(DuploToastTestActivity.this, "失败样式！", 5, true, 3);
            }
        });
        findViewById(R.id.ypd_btn_duplo_toast_info).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                YPDToast.getInstance().toast(DuploToastTestActivity.this, "info样式！", 5, true, 1);
            }
        });
        findViewById(R.id.ypd_btn_duplo_toast_loading_dismiss_on_touch).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                YPDToast.getInstance().toast(DuploToastTestActivity.this, "可以直接touch关闭的loading！", 2, true);
            }
        });
        findViewById(R.id.ypd_btn_duplo_toast_loading_dismiss_in_5s).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int unused = DuploToastTestActivity.this.toastId = YPDToast.getInstance().toast(DuploToastTestActivity.this, "5s后自动关闭的loading", 2, false);
                view.postDelayed(new Runnable() {
                    public void run() {
                        YPDToast.getInstance().dismiss(DuploToastTestActivity.this.toastId);
                    }
                }, 5000);
            }
        });
        String str = isPlugin() ? "插件" : "APP主框架";
        ((TextView) findViewById(R.id.ypd_tv_plugin_hint)).setText("这是来自" + str + "的页面");
    }

    private boolean isPlugin() {
        return getClassLoader() != getApplicationContext().getClassLoader();
    }
}
