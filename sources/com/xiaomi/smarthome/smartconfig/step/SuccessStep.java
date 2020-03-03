package com.xiaomi.smarthome.smartconfig.step;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import com.google.android.exoplayer2.C;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.SmartHomeMainActivity;
import com.xiaomi.smarthome.auth.AuthManager;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceHelper;
import com.xiaomi.smarthome.device.utils.ClientRemarkInputView;
import com.xiaomi.smarthome.device.utils.DeviceLauncher2;
import com.xiaomi.smarthome.device.utils.DeviceShortcutUtils;
import com.xiaomi.smarthome.device.utils.DeviceTagManager;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.api.AsyncResponseCallback;
import com.xiaomi.smarthome.framework.openapi.ApiConst;
import com.xiaomi.smarthome.framework.statistic.StatHelper;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.library.common.ApiHelper;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.widget.FlowGroup;
import com.xiaomi.smarthome.newui.MoveRoomDialogHelper;
import com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider;
import com.xiaomi.smarthome.smartconfig.step.SmartConfigStep;
import com.xiaomi.smarthome.stat.STAT;
import com.xiaomi.smarthome.stat.StatClick;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SuccessStep extends SmartConfigStep {

    /* renamed from: a  reason: collision with root package name */
    boolean f22686a = false;
    List<String> b;
    HashSet<String> c = new HashSet<>();
    private bindCallback d;
    private XQProgressDialog e;
    @BindView(2131428308)
    CheckBox mCheckBox;
    @BindView(2131428336)
    View mChooseContainer;
    @BindView(2131432520)
    FlowGroup mFlowGroup;
    @BindView(2131432516)
    ImageView mIcon;
    @BindView(2131430408)
    TextView mLeftBtn;
    @BindView(2131431181)
    View mLeftRightBtn;
    @BindView(2131431178)
    Button mNextButton;
    @BindView(2131431897)
    TextView mRightBtn;
    @BindView(2131432519)
    ScrollView mScrollView;

    public void a(Message message) {
    }

    public void c() {
    }

    public void d() {
    }

    public SmartConfigStep.Step f() {
        return SmartConfigStep.Step.STEP_SUCCESS;
    }

    /* access modifiers changed from: package-private */
    public void b(final boolean z) {
        this.c.clear();
        Device device = (Device) SmartConfigDataProvider.a().a(SmartConfigDataProvider.w);
        for (int i = 0; i < this.mFlowGroup.getChildCount() - 1; i++) {
            if (this.mFlowGroup.getChildAt(i).isSelected()) {
                this.c.add((String) this.mFlowGroup.getChildAt(i).getTag());
            }
        }
        if (this.af != null) {
            this.e = XQProgressDialog.a(this.af, (CharSequence) null, this.af.getString(R.string.creating));
        }
        if (!this.mCheckBox.isChecked() || this.af == null) {
            c(z);
            return;
        }
        StatHelper.q();
        if (device == null) {
            c(z);
        } else {
            DeviceShortcutUtils.a(false, device, (Intent) null, "smart_config", (AsyncResponseCallback<Void>) new AsyncResponseCallback<Void>() {
                public void a(Void voidR) {
                    SuccessStep.this.c(z);
                }

                public void a(int i) {
                    if (i != -1) {
                        SuccessStep.this.c(z);
                    } else if (!(SuccessStep.this.af instanceof Activity) || Build.VERSION.SDK_INT < 23 || !((Activity) SuccessStep.this.af).shouldShowRequestPermissionRationale("com.android.launcher.permission.INSTALL_SHORTCUT")) {
                        ToastUtil.a((int) R.string.permission_tips_denied_msg);
                    } else {
                        ((Activity) SuccessStep.this.af).requestPermissions(new String[]{"com.android.launcher.permission.INSTALL_SHORTCUT"}, 1);
                    }
                }

                public void a(int i, Object obj) {
                    SuccessStep.this.c(z);
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void c(boolean z) {
        int i = 1;
        if (!z) {
            Device device = (Device) SmartConfigDataProvider.a().a(SmartConfigDataProvider.w);
            if (device != null) {
                Intent intent = new Intent(this.af, DeviceLauncher2.class);
                intent.setAction(ApiConst.f16684a);
                intent.putExtra("device_mac", device.mac);
                intent.putExtra(ApiConst.f, device.model);
                intent.putExtra("device_id", device.did);
                intent.putExtra(ApiConst.n, true);
                this.af.startActivity(intent);
            } else {
                Intent intent2 = new Intent();
                intent2.setClass(this.af, SmartHomeMainActivity.class);
                intent2.addFlags(C.ENCODING_PCM_MU_LAW);
                this.af.startActivity(intent2);
            }
        }
        StatClick statClick = STAT.d;
        String str = this.aj;
        String next = this.c.size() == 0 ? "null" : this.c.iterator().next();
        if (!this.mCheckBox.isChecked()) {
            i = 2;
        }
        statClick.a(str, next, i);
        h();
    }

    public void a(Context context) {
        a(context, R.layout.smart_config_success_ui);
        y();
        this.mIcon.setImageResource(R.drawable.kuailian_success_icon);
        this.mCheckBox.setChecked(false);
        if (!ApiHelper.i) {
            this.mCheckBox.setPadding(this.mCheckBox.getPaddingLeft() + DisplayUtils.a(18.0f), this.mCheckBox.getPaddingTop(), this.mCheckBox.getPaddingRight(), this.mCheckBox.getPaddingBottom());
        }
        if (((Integer) SmartConfigDataProvider.a().a(SmartConfigDataProvider.ab)).intValue() == 10) {
            this.mNextButton.setVisibility(8);
            this.mLeftRightBtn.setVisibility(0);
            TextView textView = this.mLeftBtn;
            textView.setText(this.af.getString(R.string.action_back) + AuthManager.h().a().a());
            this.mLeftBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    SuccessStep.this.b(true);
                }
            });
            this.mRightBtn.setText(R.string.auth_finish_dialog_to_mijia_plugin);
            this.mRightBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    SuccessStep.this.b(false);
                }
            });
        } else {
            this.mNextButton.setVisibility(0);
            this.mLeftRightBtn.setVisibility(8);
            this.mNextButton.setText(R.string.complete);
            this.mNextButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    SuccessStep.this.b(true);
                }
            });
        }
        Device device = (Device) SmartConfigDataProvider.a().a(SmartConfigDataProvider.w);
        if (device == null) {
            this.b = SmartHomeDeviceHelper.a().b().c();
        } else {
            this.b = SmartHomeDeviceHelper.a().b().a(device);
        }
        if (!CoreApi.a().D()) {
            b();
        } else {
            this.mChooseContainer.setVisibility(8);
        }
        SmartHomeDeviceHelper.a().b().s();
        StatHelper.c((String) SmartConfigDataProvider.a().a("device_model"));
    }

    private void h() {
        Device device = (Device) SmartConfigDataProvider.a().a(SmartConfigDataProvider.w);
        if (device == null) {
            d_(true);
            return;
        }
        Home m = HomeManager.a().m();
        if (m == null) {
            d_(true);
            return;
        }
        this.d = new bindCallback();
        if (this.c == null || this.c.size() <= 0) {
            HomeManager.a().a(m, (Room) null, device, (HomeManager.IHomeOperationCallback) this.d);
            return;
        }
        ((DeviceTagManager) SmartHomeDeviceHelper.a().b()).a((Set<String>) this.c, device.did, true, (HomeManager.IHomeOperationCallback) this.d);
        StatHelper.p();
    }

    /* access modifiers changed from: package-private */
    public void b() {
        this.mFlowGroup.removeAllViews();
        LayoutInflater from = LayoutInflater.from(this.af);
        int a2 = DisplayUtils.a((Activity) this.af).x - DisplayUtils.a(50.0f);
        for (int i = 0; i < this.b.size(); i++) {
            TextView textView = (TextView) from.inflate(R.layout.smart_config_tag_btn, (ViewGroup) null);
            textView.setMaxWidth(a2);
            textView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (view.isSelected()) {
                        view.setSelected(false);
                        return;
                    }
                    for (int i = 0; i < SuccessStep.this.mFlowGroup.getChildCount(); i++) {
                        SuccessStep.this.mFlowGroup.getChildAt(i).setSelected(false);
                    }
                    view.setSelected(true);
                }
            });
            textView.setText(this.b.get(i));
            textView.setTag(this.b.get(i));
            this.mFlowGroup.addView(textView);
        }
        ImageView imageView = new ImageView(this.af);
        imageView.setImageResource(R.drawable.choose_tag_more);
        this.mFlowGroup.addView(imageView);
        this.mFlowGroup.setMoreClickListener(new FlowGroup.TagMoreClickListener() {
            public void onClick(View view, boolean z) {
                if (!z) {
                    SuccessStep.this.mFlowGroup.setExpand(!z);
                    SuccessStep.this.U_().postDelayed(new Runnable() {
                        public void run() {
                            SuccessStep.this.mScrollView.fullScroll(130);
                        }
                    }, 50);
                    return;
                }
                MoveRoomDialogHelper.a(SuccessStep.this.af, (List<String>) new ArrayList(), (MoveRoomDialogHelper.addRoomListener) new MoveRoomDialogHelper.addRoomListener() {
                    public void onSuccess(String str) {
                        SuccessStep.this.b.add(0, str);
                        SuccessStep.this.b();
                        for (int i = 0; i < SuccessStep.this.mFlowGroup.getChildCount(); i++) {
                            SuccessStep.this.mFlowGroup.getChildAt(i).setSelected(false);
                        }
                        SuccessStep.this.mFlowGroup.getChildAt(0).setSelected(true);
                    }
                });
            }
        });
    }

    public void g() {
        final ClientRemarkInputView clientRemarkInputView = (ClientRemarkInputView) LayoutInflater.from(this.af).inflate(R.layout.client_remark_input_view_restrict_20, (ViewGroup) null);
        clientRemarkInputView.initialize((ClientRemarkInputView.RenameInterface) null, new MLAlertDialog.Builder(this.af).a((int) R.string.add_device_tag).b((View) clientRemarkInputView).d(false).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                String inputText = clientRemarkInputView.getInputText();
                for (String equals : SuccessStep.this.b) {
                    if (inputText.equals(equals)) {
                        Toast.makeText(SuccessStep.this.af, R.string.tag_exist_string, 0).show();
                        return;
                    }
                }
                SmartHomeDeviceHelper.a().b().a(inputText, (Set<String>) null);
                SuccessStep.this.b.add(0, inputText);
                dialogInterface.dismiss();
                SuccessStep.this.b();
                for (int i2 = 0; i2 < SuccessStep.this.mFlowGroup.getChildCount(); i2++) {
                    SuccessStep.this.mFlowGroup.getChildAt(i2).setSelected(false);
                }
                SuccessStep.this.mFlowGroup.getChildAt(0).setSelected(true);
            }
        }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ((MLAlertDialog) dialogInterface).setAudoDismiss(true);
            }
        }).d(), "");
    }

    public void e() {
        this.d = null;
        HomeManager.a().b((Device) SmartConfigDataProvider.a().a(SmartConfigDataProvider.w));
        if (this.e != null) {
            this.e.dismiss();
        }
    }

    class bindCallback implements HomeManager.IHomeOperationCallback {
        bindCallback() {
        }

        public void a() {
            SuccessStep.this.d_(true);
        }

        public void a(int i, Error error) {
            SuccessStep.this.d_(true);
        }
    }
}
