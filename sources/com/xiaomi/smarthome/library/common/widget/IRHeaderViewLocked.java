package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.core.entity.plugin.PluginError;
import com.xiaomi.smarthome.device.IRRemoteInfo;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.utils.IRDeviceUtil;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.openapi.OpenApi;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.miio.ir.IRV2ControllerMiActivity;
import java.util.List;

public class IRHeaderViewLocked extends FrameLayout {
    static final String TAG = "IRHeaderViewLocked";
    View mBtnIRSettings;
    View mBtnJumpRemote;
    Button mBtnRemote0;
    Button mBtnRemote1;
    Button mBtnRemote2;
    ImageView mCkDuoKan = null;
    ImageView mCkTianJia = null;
    List<IRRemoteInfo> mRemoteInfoList;
    LinearLayout mScDuoKan = null;
    LinearLayout mScTianJia = null;
    SwitchButton mSwCardMode = null;
    TextView mTvDuoKan = null;
    TextView mTvTianJia = null;

    public IRHeaderViewLocked(Context context) {
        super(context);
        init();
    }

    public IRHeaderViewLocked(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public IRHeaderViewLocked(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    /* access modifiers changed from: package-private */
    public void init() {
        inflate(getContext(), R.layout.ir_header_view_layout_locked, this);
        onFinishInflate();
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.mBtnIRSettings = findViewById(R.id.btn_ir_settings);
        this.mBtnIRSettings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!CoreApi.a().q()) {
                    Intent intent = new Intent();
                    intent.setData(Uri.parse("https://home.mi.com/main/login"));
                    intent.setPackage(IRHeaderViewLocked.this.getContext().getPackageName());
                    IRHeaderViewLocked.this.getContext().startActivity(intent);
                    DisplayUtils.a(IRHeaderViewLocked.this.getContext(), 17432576, 17432577);
                    OpenApi.a(IRHeaderViewLocked.this.getContext());
                    return;
                }
                String a2 = IRDeviceUtil.a();
                if (!TextUtils.isEmpty(a2)) {
                    if (!CoreApi.a().c(a2)) {
                        CoreApi.a().a(true, (CoreApi.UpdateConfigCallback) new CoreApi.UpdateConfigCallback() {
                            public void a(PluginError pluginError) {
                            }

                            public void a(boolean z, boolean z2) {
                                if (z) {
                                    SmartHomeDeviceManager.a().r();
                                }
                            }
                        });
                        Toast.makeText(IRHeaderViewLocked.this.getContext(), R.string.toast_failed_retry, 0).show();
                        return;
                    }
                    IRDeviceUtil.a(IRHeaderViewLocked.this.getContext(), (Intent) null);
                    DisplayUtils.a(IRHeaderViewLocked.this.getContext(), 17432576, 17432577);
                    OpenApi.a(IRHeaderViewLocked.this.getContext());
                }
            }
        });
        this.mBtnIRSettings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!CoreApi.a().q()) {
                    Intent intent = new Intent();
                    intent.setData(Uri.parse("https://home.mi.com/main/login"));
                    intent.setPackage(IRHeaderViewLocked.this.getContext().getPackageName());
                    IRHeaderViewLocked.this.getContext().startActivity(intent);
                    DisplayUtils.a(IRHeaderViewLocked.this.getContext(), 17432576, 17432577);
                    OpenApi.a(IRHeaderViewLocked.this.getContext());
                    return;
                }
                String a2 = IRDeviceUtil.a();
                if (!TextUtils.isEmpty(a2)) {
                    if (!CoreApi.a().c(a2)) {
                        CoreApi.a().a(true, (CoreApi.UpdateConfigCallback) new CoreApi.UpdateConfigCallback() {
                            public void a(PluginError pluginError) {
                            }

                            public void a(boolean z, boolean z2) {
                                if (z) {
                                    SmartHomeDeviceManager.a().r();
                                }
                            }
                        });
                        Toast.makeText(IRHeaderViewLocked.this.getContext(), R.string.toast_failed_retry, 0).show();
                        return;
                    }
                    IRDeviceUtil.a(IRHeaderViewLocked.this.getContext(), (Intent) null);
                    DisplayUtils.a(IRHeaderViewLocked.this.getContext(), 17432576, 17432577);
                    OpenApi.a(IRHeaderViewLocked.this.getContext());
                }
            }
        });
        this.mBtnRemote0 = (Button) findViewById(R.id.btn_remote0);
        this.mBtnRemote1 = (Button) findViewById(R.id.btn_remote1);
        this.mBtnRemote2 = (Button) findViewById(R.id.btn_remote2);
    }

    public void updateBottomView() {
        this.mRemoteInfoList = IRDeviceUtil.b(getContext());
        this.mBtnRemote1.setVisibility(0);
        if (!CoreApi.a().q()) {
            this.mBtnRemote0.setVisibility(0);
            a(this.mBtnRemote0, (IRRemoteInfo) null);
            this.mBtnRemote2.setVisibility(4);
            setRemoteAddBtn(this.mBtnRemote1);
        } else if (this.mRemoteInfoList == null || this.mRemoteInfoList.size() <= 0) {
            this.mBtnRemote0.setVisibility(4);
            this.mBtnRemote2.setVisibility(4);
            setRemoteAddBtn(this.mBtnRemote1);
        } else {
            this.mBtnRemote0.setVisibility(0);
            a(this.mBtnRemote0, this.mRemoteInfoList.get(0));
            int size = this.mRemoteInfoList.size();
            if (size == 1) {
                this.mBtnRemote2.setVisibility(4);
                setRemoteAddBtn(this.mBtnRemote1);
                return;
            }
            this.mBtnRemote2.setVisibility(0);
            if (size == 2) {
                a(this.mBtnRemote1, this.mRemoteInfoList.get(1));
                setRemoteAddBtn(this.mBtnRemote2);
            } else if (size >= 3) {
                a(this.mBtnRemote1, this.mRemoteInfoList.get(1));
                a(this.mBtnRemote2, this.mRemoteInfoList.get(2));
            }
        }
    }

    private void a(final Button button, final IRRemoteInfo iRRemoteInfo) {
        if (!CoreApi.a().q()) {
            button.setText(R.string.buildin_ir_name);
            button.setCompoundDrawables((Drawable) null, IRDeviceUtil.b(true), (Drawable) null, (Drawable) null);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    button.getContext().startActivity(new Intent(button.getContext(), IRV2ControllerMiActivity.class));
                    DisplayUtils.a(IRHeaderViewLocked.this.getContext(), 17432576, 17432577);
                    OpenApi.a(IRHeaderViewLocked.this.getContext());
                }
            });
            return;
        }
        button.setText(iRRemoteInfo.b);
        button.setCompoundDrawables((Drawable) null, IRDeviceUtil.c(iRRemoteInfo.c, true), (Drawable) null, (Drawable) null);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!CoreApi.a().q()) {
                    Intent intent = new Intent();
                    intent.setData(Uri.parse("https://home.mi.com/main/login"));
                    intent.setPackage(IRHeaderViewLocked.this.getContext().getPackageName());
                    IRHeaderViewLocked.this.getContext().startActivity(intent);
                    DisplayUtils.a(IRHeaderViewLocked.this.getContext(), 17432576, 17432577);
                    OpenApi.a(IRHeaderViewLocked.this.getContext());
                    return;
                }
                String a2 = IRDeviceUtil.a();
                if (!TextUtils.isEmpty(a2)) {
                    if (!CoreApi.a().c(a2)) {
                        CoreApi.a().a(true, (CoreApi.UpdateConfigCallback) new CoreApi.UpdateConfigCallback() {
                            public void a(PluginError pluginError) {
                            }

                            public void a(boolean z, boolean z2) {
                                if (z) {
                                    SmartHomeDeviceManager.a().r();
                                }
                            }
                        });
                        Toast.makeText(IRHeaderViewLocked.this.getContext(), R.string.toast_failed_retry, 0).show();
                        return;
                    }
                    Intent intent2 = new Intent();
                    intent2.putExtra("remote_id", iRRemoteInfo.f14856a);
                    IRDeviceUtil.a(IRHeaderViewLocked.this.getContext(), intent2);
                    DisplayUtils.a(IRHeaderViewLocked.this.getContext(), 17432576, 17432577);
                    OpenApi.a(IRHeaderViewLocked.this.getContext());
                }
            }
        });
    }

    private void setRemoteAddBtn(Button button) {
        button.setText(R.string.add_ir_remote);
        Drawable drawable = getContext().getResources().getDrawable(R.drawable.std_lock_list_infrared_icon_plus);
        int a2 = DisplayUtils.a(56.0f);
        drawable.setBounds(0, 0, a2, a2);
        button.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, drawable, (Drawable) null, (Drawable) null);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!CoreApi.a().q()) {
                    Intent intent = new Intent();
                    intent.setData(Uri.parse("https://home.mi.com/main/login"));
                    intent.setPackage(IRHeaderViewLocked.this.getContext().getPackageName());
                    IRHeaderViewLocked.this.getContext().startActivity(intent);
                    DisplayUtils.a(IRHeaderViewLocked.this.getContext(), 17432576, 17432577);
                    OpenApi.a(IRHeaderViewLocked.this.getContext());
                    return;
                }
                String a2 = IRDeviceUtil.a();
                if (!TextUtils.isEmpty(a2)) {
                    if (!CoreApi.a().c(a2)) {
                        CoreApi.a().a(true, (CoreApi.UpdateConfigCallback) new CoreApi.UpdateConfigCallback() {
                            public void a(PluginError pluginError) {
                            }

                            public void a(boolean z, boolean z2) {
                                if (z) {
                                    SmartHomeDeviceManager.a().r();
                                }
                            }
                        });
                        Toast.makeText(IRHeaderViewLocked.this.getContext(), R.string.toast_failed_retry, 0).show();
                        return;
                    }
                    Intent intent2 = new Intent();
                    intent2.putExtra("add_device", true);
                    IRDeviceUtil.a(IRHeaderViewLocked.this.getContext(), intent2);
                    DisplayUtils.a(IRHeaderViewLocked.this.getContext(), 17432576, 17432577);
                    OpenApi.a(IRHeaderViewLocked.this.getContext());
                }
            }
        });
    }
}
