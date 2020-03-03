package com.xiaomi.smarthome.library.common.util;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.mi.global.bbs.utils.Constants;
import com.umeng.analytics.MobclickAgent;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.config.SHConfig;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.BleDevice;
import com.xiaomi.smarthome.device.BleMeshDevice;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.MiTVDevice;
import com.xiaomi.smarthome.device.MiioDeviceV2;
import com.xiaomi.smarthome.device.RouterDevice;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.DeviceApi;
import com.xiaomi.smarthome.device.bluetooth.BLEDeviceManager;
import com.xiaomi.smarthome.device.bluetooth.BleCacheUtils;
import com.xiaomi.smarthome.device.utils.ClientRemarkInputView;
import com.xiaomi.smarthome.device.utils.DeviceShortcutUtils;
import com.xiaomi.smarthome.family.api.RemoteFamilyApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.api.AsyncResponseCallback;
import com.xiaomi.smarthome.framework.api.RouterRemoteApi;
import com.xiaomi.smarthome.framework.statistic.StatUtil;
import com.xiaomi.smarthome.framework.update.api.UpdateApi;
import com.xiaomi.smarthome.framework.update.ui.MiioUpgradeActivity;
import com.xiaomi.smarthome.homeroom.CommonUseDeviceDataManager;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.MultipleChoiceDialogHelper;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.miio.MiioManager;
import com.xiaomi.smarthome.miio.camera.match.CameraDevice;
import java.util.ArrayList;
import org.json.JSONObject;

public abstract class SmartHomeTitleMoreMenuHelper {
    public static final int[] c = {R.string.smarthome_device_rename};
    public static int[] d = {R.string.smarthome_device_remove};
    public static int[] e = {R.string.smarthome_device_auth_release, R.string.smarthome_device_launcher};
    public static int[] f = {R.string.family_delete};
    public static int[] g = {R.string.smarthome_device_rename, R.string.smarthome_device_remove, R.string.smarthome_device_launcher};
    public static int[] h = {R.string.smarthome_device_rename, R.string.smarthome_device_remove, R.string.smarthome_device_launcher, R.string.invisible_in_main_device_list};
    public static int[] i = {R.string.smarthome_device_rename, R.string.smarthome_device_device, R.string.smarthome_device_auth_release, R.string.smarthome_device_launcher};
    public static int[] j = {R.string.smarthome_device_rename, R.string.smarthome_device_device, R.string.smarthome_device_virtual_delete, R.string.smarthome_device_launcher};
    public static int[] k = {R.string.smarthome_device_rename, R.string.smarthome_device_device, R.string.smarthome_device_auth_release, R.string.smarthome_device_launcher};
    public static int[] l = {R.string.smarthome_device_auth, R.string.smarthome_device_auth_friend};
    public static int[] m = {R.string.smarthome_device_request_unauth_mitv, R.string.smarthome_device_rename, R.string.smarthome_device_launcher};
    public static int[] n = {R.string.smarthome_device_rename, R.string.smarthome_device_auth, R.string.smarthome_device_auth_friend, R.string.smarthome_device_auth_release, R.string.smarthome_device_upgrade, R.string.smarthome_device_launcher};
    public static int[] o = {R.string.smarthome_device_rename, R.string.smarthome_device_auth, R.string.smarthome_device_auth_friend, R.string.smarthome_device_auth_release, R.string.smarthome_device_upgrade_new, R.string.smarthome_device_launcher};
    public static int[] p = {R.string.miio_sub_device_rename, R.string.miio_sub_device_study, R.string.miio_sub_device_remove, R.string.smarthome_device_share};
    int[] q = c;
    protected boolean r;
    protected String s = "";
    protected String t;
    protected String u;
    protected boolean v;
    protected MLAlertDialog w;

    public abstract TextView a();

    public boolean a(int i2) {
        return false;
    }

    public abstract Context b();

    public void b(int i2) {
    }

    public abstract Device c();

    public void d() {
    }

    public ImageView g() {
        return null;
    }

    public void e() {
        if (this.w != null) {
            this.w.dismiss();
            this.w = null;
        }
    }

    public void f() {
        if (this.w != null) {
            this.w.dismiss();
            this.w = null;
        }
    }

    public void a(int[] iArr) {
        this.q = iArr;
    }

    public void b(int[] iArr) {
        int[] iArr2 = new int[0];
        if (this.q != null) {
            iArr2 = this.q;
        }
        this.q = new int[(iArr2.length + iArr.length)];
        System.arraycopy(iArr2, 0, this.q, 0, iArr2.length);
        System.arraycopy(iArr, iArr2.length, this.q, iArr2.length, this.q.length - iArr2.length);
    }

    public void h() {
        if (CoreApi.a().q()) {
            Device c2 = c();
            if (c2 instanceof BleDevice) {
                a(d);
            } else if (c2.pid == Device.PID_VIRTUAL_DEVICE) {
                if (c2.isShared() || c2.isFamily()) {
                    a(k);
                } else {
                    a(j);
                }
            } else if ((c2 instanceof MiTVDevice) && c2.isOwner()) {
                a(m);
            } else if (c2 instanceof RouterDevice) {
                if (c2.isBinded()) {
                    a(e);
                } else {
                    this.q = null;
                }
            } else if (c2.isSubDevice()) {
                if (c2.parentModel.equalsIgnoreCase("lumi.gateway.v1") || c2.parentModel.equalsIgnoreCase("lumi.gateway.v2")) {
                    a(h);
                } else {
                    a(g);
                }
            } else if (c2.isOwner()) {
                a(i);
            } else if (c2.isShared() || c2.isFamily()) {
                a(d);
            } else {
                this.q = null;
            }
        } else {
            this.q = null;
            Toast.makeText(b(), R.string.loggin_first, 0).show();
        }
    }

    /* access modifiers changed from: private */
    public void d(int i2) {
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < this.q.length; i3++) {
            if (!(this.q[i3] == R.string.smarthome_device_rename || this.q[i3] == R.string.smarthome_device_device)) {
                arrayList.add(Integer.valueOf(this.q[i3]));
            }
        }
        c(((Integer) arrayList.get(i2)).intValue());
    }

    public void c(int i2) {
        if (!a(i2)) {
            if (i2 == R.string.smarthome_device_upgrade || i2 == R.string.smarthome_device_upgrade_new) {
                q();
            } else if (i2 == R.string.smarthome_device_rename) {
                o();
            } else if (i2 == R.string.smarthome_device_device) {
                p();
            } else if (i2 == R.string.smarthome_device_auth_release) {
                t();
            } else if (i2 == R.string.smarthome_device_virtual_delete) {
                r();
            } else if (i2 == R.string.smarthome_device_remove) {
                s();
            } else if (i2 == R.string.smarthome_device_launcher) {
                DeviceShortcutUtils.a(c(), (Intent) null);
            } else if (i2 == R.string.camera_show_top_snap) {
                Device c2 = c();
                if (c2 != null) {
                    String str = c2.did;
                    if (str.startsWith("yunyi.")) {
                        str.substring(6);
                    }
                    PluginRecord d2 = CoreApi.a().d(c2.model);
                    if (d2 != null && d2.l()) {
                        SHConfig.a().a("head_widget_did", c2.did);
                    }
                    e();
                }
            } else if (i2 == R.string.camera_not_show_top_snap) {
                Device c3 = c();
                if (c3 != null) {
                    String str2 = c3.did;
                    if (str2.startsWith("yunyi.")) {
                        str2.substring(6);
                    }
                    SHConfig.a().a("head_widget_did", "");
                    e();
                }
            } else if (i2 == R.string.smarthome_device_request_unauth || i2 == R.string.smarthome_device_request_unauth_mitv) {
                t();
            } else if (i2 == R.string.invisible_in_main_device_list) {
                SmartHomeDeviceManager.a().a(false, c().did, b(), (AsyncResponseCallback<Void>) null);
            }
        }
    }

    /* access modifiers changed from: private */
    public void e(int i2) {
        c(this.q[i2]);
    }

    public void i() {
        if (this.q != null && this.q.length != 0) {
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < this.q.length; i2++) {
                if (!(this.q[i2] == R.string.smarthome_device_rename || this.q[i2] == R.string.smarthome_device_device)) {
                    if (this.q[i2] == R.string.smarthome_device_upgrade_new) {
                        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(b().getString(this.q[i2]));
                        spannableStringBuilder.setSpan(new ForegroundColorSpan(-65536), 0, b().getString(this.q[i2]).length(), 33);
                        arrayList.add(spannableStringBuilder);
                    } else {
                        arrayList.add(b().getString(this.q[i2]));
                    }
                }
            }
            if (arrayList.size() > 0) {
                MLAlertDialog.Builder builder = new MLAlertDialog.Builder(b());
                builder.a((CharSequence) c().name);
                CharSequence[] charSequenceArr = new CharSequence[arrayList.size()];
                arrayList.toArray(charSequenceArr);
                builder.a(charSequenceArr, -1, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SmartHomeTitleMoreMenuHelper.this.d(i);
                    }
                });
                builder.a((DialogInterface.OnCancelListener) new DialogInterface.OnCancelListener() {
                    public void onCancel(DialogInterface dialogInterface) {
                        SmartHomeTitleMoreMenuHelper.this.d();
                    }
                });
                this.w = builder.d();
            }
        }
    }

    public void j() {
        if (this.q != null && this.q.length != 0) {
            CharSequence[] charSequenceArr = new CharSequence[this.q.length];
            for (int i2 = 0; i2 < this.q.length; i2++) {
                if (this.q[i2] == R.string.smarthome_device_upgrade_new) {
                    SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(b().getString(this.q[i2]));
                    spannableStringBuilder.setSpan(new ForegroundColorSpan(-65536), 0, b().getString(this.q[i2]).length(), 33);
                    charSequenceArr[i2] = spannableStringBuilder;
                } else {
                    charSequenceArr[i2] = b().getString(this.q[i2]);
                }
            }
            MLAlertDialog b = new MLAlertDialog.Builder(b()).a(charSequenceArr, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    SmartHomeTitleMoreMenuHelper.this.e(i);
                }
            }).a((DialogInterface.OnCancelListener) new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialogInterface) {
                    SmartHomeTitleMoreMenuHelper.this.d();
                }
            }).b();
            b.show();
            MultipleChoiceDialogHelper.a(b(), b);
        }
    }

    public void k() {
        if (this.q != null && this.q.length != 0) {
            String[] strArr = new String[this.q.length];
            for (int i2 = 0; i2 < this.q.length; i2++) {
                strArr[i2] = b().getString(this.q[i2]);
            }
            MLAlertDialog.Builder builder = new MLAlertDialog.Builder(b());
            builder.a((CharSequence[]) strArr, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    SmartHomeTitleMoreMenuHelper.this.e(i);
                }
            });
            builder.c((CharSequence) c().name);
            builder.e();
        }
    }

    private void o() {
        final ClientRemarkInputView clientRemarkInputView = (ClientRemarkInputView) LayoutInflater.from(b()).inflate(R.layout.client_remark_input_view, (ViewGroup) null);
        clientRemarkInputView.initialize((ClientRemarkInputView.RenameInterface) new ClientRemarkInputView.RenameInterface() {
            public void modifyBackName(final String str) {
                final XQProgressDialog xQProgressDialog = new XQProgressDialog(SmartHomeTitleMoreMenuHelper.this.b());
                xQProgressDialog.setMessage(SmartHomeTitleMoreMenuHelper.this.b().getString(R.string.changeing_back_name));
                xQProgressDialog.setCancelable(false);
                xQProgressDialog.show();
                final Device c = SmartHomeTitleMoreMenuHelper.this.c();
                if (c != null) {
                    if (c instanceof MiioDeviceV2) {
                        if (!(c instanceof MiTVDevice) || ((MiTVDevice) c).e()) {
                            MiioManager.a().a(c, str, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                                /* renamed from: a */
                                public void onSuccess(Void voidR) {
                                    xQProgressDialog.dismiss();
                                    c.name = str;
                                    if (SmartHomeTitleMoreMenuHelper.this.a() != null) {
                                        SmartHomeTitleMoreMenuHelper.this.a().setText(str);
                                    }
                                    SmartHomeTitleMoreMenuHelper.this.e();
                                    SmartHomeDeviceManager.a().p();
                                }

                                public void onFailure(Error error) {
                                    xQProgressDialog.dismiss();
                                    Toast.makeText(SmartHomeTitleMoreMenuHelper.this.b(), R.string.change_back_name_fail, 0).show();
                                }
                            });
                            return;
                        }
                        xQProgressDialog.dismiss();
                        Toast.makeText(SmartHomeTitleMoreMenuHelper.this.b(), R.string.change_back_name_mitv_notsupport, 0).show();
                    } else if (c instanceof CameraDevice) {
                        MiioManager.a().a(c, str, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                            /* renamed from: a */
                            public void onSuccess(Void voidR) {
                                xQProgressDialog.dismiss();
                                c.name = str;
                                if (SmartHomeTitleMoreMenuHelper.this.a() != null) {
                                    SmartHomeTitleMoreMenuHelper.this.a().setText(str);
                                }
                                SmartHomeTitleMoreMenuHelper.this.e();
                                SmartHomeDeviceManager.a().p();
                            }

                            public void onFailure(Error error) {
                                xQProgressDialog.dismiss();
                                Toast.makeText(SmartHomeTitleMoreMenuHelper.this.b(), R.string.change_back_name_fail, 0).show();
                            }
                        });
                    } else if (!(c instanceof BleDevice)) {
                    } else {
                        if (!TextUtils.isEmpty(c.did)) {
                            MiioManager.a().a(c, str, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                                /* renamed from: a */
                                public void onSuccess(Void voidR) {
                                    xQProgressDialog.dismiss();
                                    c.name = str;
                                    BleCacheUtils.a(c.mac, str);
                                    if (SmartHomeTitleMoreMenuHelper.this.a() != null) {
                                        SmartHomeTitleMoreMenuHelper.this.a().setText(str);
                                    }
                                    SmartHomeTitleMoreMenuHelper.this.e();
                                    SmartHomeDeviceManager.a().p();
                                }

                                public void onFailure(Error error) {
                                    xQProgressDialog.dismiss();
                                    Toast.makeText(SmartHomeTitleMoreMenuHelper.this.b(), R.string.change_back_name_fail, 0).show();
                                }
                            });
                            return;
                        }
                        xQProgressDialog.dismiss();
                        c.name = str;
                        BleCacheUtils.a(c.mac, str);
                        if (SmartHomeTitleMoreMenuHelper.this.a() != null) {
                            SmartHomeTitleMoreMenuHelper.this.a().setText(str);
                        }
                        SmartHomeTitleMoreMenuHelper.this.e();
                        SmartHomeDeviceManager.a().p();
                    }
                }
            }
        }, new MLAlertDialog.Builder(b()).a((int) R.string.change_back_name).b((View) clientRemarkInputView).d(false).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                clientRemarkInputView.onConfirm(dialogInterface);
            }
        }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ((MLAlertDialog) dialogInterface).setAudoDismiss(true);
            }
        }).d(), c(), 30);
    }

    private void p() {
        if (CoreApi.a().q()) {
            Intent intent = new Intent();
            intent.setClass(b(), DeviceShortcutUtils.a());
            Bundle bundle = new Bundle();
            Device c2 = c();
            bundle.putString("user_id", CoreApi.a().s());
            bundle.putString("did", c2.did);
            intent.putExtras(bundle);
            b().startActivity(intent);
            return;
        }
        Toast.makeText(b(), R.string.loggin_first, 0).show();
    }

    /* access modifiers changed from: private */
    public void q() {
        Device c2 = c();
        if (c2 != null) {
            Intent intent = new Intent(b(), MiioUpgradeActivity.class);
            intent.putExtra(MiioUpgradeActivity.MIIO_UPGRADE_DID, c2.did);
            intent.putExtra(MiioUpgradeActivity.MIIO_UPGRADE_PID, c2.pid);
            intent.putExtra(MiioUpgradeActivity.MIIO_UPGRADE_NAME, c2.name);
            b().startActivity(intent);
        }
    }

    private void r() {
        final String str = c().did;
        DeviceApi.getInstance().deleteModelGroup(b(), str, new AsyncCallback<String, Error>() {
            /* renamed from: a */
            public void onSuccess(String str) {
                CommonUseDeviceDataManager.a().b(str);
                SmartHomeTitleMoreMenuHelper.this.f();
                Toast.makeText(SmartHomeTitleMoreMenuHelper.this.b(), R.string.smarthome_device_delete_success, 0).show();
            }

            public void onFailure(Error error) {
                Toast.makeText(SmartHomeTitleMoreMenuHelper.this.b(), R.string.smarthome_device_delete_fail, 0).show();
            }
        });
    }

    private void s() {
        if (c().isSubDevice()) {
            new MLAlertDialog.Builder(b()).a((int) R.string.gateway_remove_subdevice_tips).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    MiioManager.a().a(SmartHomeTitleMoreMenuHelper.this.c().parentId, SmartHomeTitleMoreMenuHelper.this.c().pid, SmartHomeTitleMoreMenuHelper.this.c().did, new AsyncResponseCallback<Void>() {
                        public void a(Void voidR) {
                            SmartHomeTitleMoreMenuHelper.this.f();
                            Toast.makeText(SmartHomeTitleMoreMenuHelper.this.b(), R.string.smarthome_device_delete_success, 0).show();
                        }

                        public void a(int i) {
                            Toast.makeText(SmartHomeTitleMoreMenuHelper.this.b(), R.string.smarthome_device_delete_fail, 0).show();
                        }

                        public void a(int i, Object obj) {
                            Toast.makeText(SmartHomeTitleMoreMenuHelper.this.b(), R.string.smarthome_device_delete_fail, 0).show();
                        }
                    });
                }
            }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    ((MLAlertDialog) dialogInterface).setAudoDismiss(true);
                }
            }).d();
        } else if (c() instanceof BleDevice) {
            BLEDeviceManager.a((BleDevice) c());
            f();
        } else if (c() instanceof BleMeshDevice) {
            BLEDeviceManager.a(c());
            f();
        } else {
            RemoteFamilyApi.a().a(SHApplication.getAppContext(), c().did, c().pid, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    Toast.makeText(SmartHomeTitleMoreMenuHelper.this.b(), R.string.smarthome_device_delete_success, 0).show();
                    SmartHomeTitleMoreMenuHelper.this.f();
                }

                public void onFailure(Error error) {
                    Toast.makeText(SmartHomeTitleMoreMenuHelper.this.b(), R.string.smarthome_device_delete_fail, 0).show();
                }
            });
        }
    }

    private void t() {
        int i2;
        Device c2 = c();
        if (c2 instanceof CameraDevice) {
            i2 = R.string.smarthome_unbind_camera_tips;
        } else {
            i2 = c2 instanceof MiTVDevice ? R.string.smarthome_unbind_tips_mitv : R.string.smarthome_unbind_tips;
        }
        new MLAlertDialog.Builder(b()).b(i2).a((int) R.string.delete, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                final XQProgressDialog xQProgressDialog = new XQProgressDialog(SmartHomeTitleMoreMenuHelper.this.b());
                xQProgressDialog.setMessage(SmartHomeTitleMoreMenuHelper.this.b().getString(R.string.smarthome_unbinding));
                xQProgressDialog.setCancelable(false);
                xQProgressDialog.show();
                Device c = SmartHomeTitleMoreMenuHelper.this.c();
                if (c instanceof RouterDevice) {
                    MobclickAgent.a(SmartHomeTitleMoreMenuHelper.this.b(), StatUtil.c, "unbind_router");
                    final String str = SmartHomeTitleMoreMenuHelper.this.c().did;
                    if (str.startsWith("miwifi.")) {
                        str = str.substring(7);
                    }
                    if (SmartHomeTitleMoreMenuHelper.this.c().isOwner()) {
                        RouterRemoteApi.a().b(SHApplication.getAppContext(), str, new AsyncCallback<Void, Error>() {
                            /* renamed from: a */
                            public void onSuccess(Void voidR) {
                                SmartHomeTitleMoreMenuHelper.this.c().setOwner(false);
                                SmartHomeTitleMoreMenuHelper.this.e();
                                Toast.makeText(SmartHomeTitleMoreMenuHelper.this.b(), R.string.bind_success, 0).show();
                                SmartHomeTitleMoreMenuHelper.this.b((int) R.string.smarthome_device_auth_release);
                                xQProgressDialog.dismiss();
                                Intent intent = new Intent("com.xiaomi.router");
                                intent.putExtra(HomeManager.v, "delete");
                                intent.putExtra("userId", CoreApi.a().s());
                                intent.putExtra("id", str);
                                SHApplication.getAppContext().sendBroadcast(intent);
                            }

                            public void onFailure(Error error) {
                                SmartHomeTitleMoreMenuHelper.this.e();
                                Toast.makeText(SmartHomeTitleMoreMenuHelper.this.b(), R.string.bind_error, 0).show();
                                xQProgressDialog.dismiss();
                            }
                        });
                    } else if (SmartHomeTitleMoreMenuHelper.this.c().isShared() || SmartHomeTitleMoreMenuHelper.this.c().isFamily()) {
                        RouterRemoteApi.a().c(SHApplication.getAppContext(), str, new AsyncCallback<Void, Error>() {
                            /* renamed from: a */
                            public void onSuccess(Void voidR) {
                                if (SmartHomeTitleMoreMenuHelper.this.c() instanceof MiTVDevice) {
                                    SmartHomeDeviceManager.a().c(SmartHomeTitleMoreMenuHelper.this.c());
                                } else {
                                    SmartHomeTitleMoreMenuHelper.this.c().setShared(false);
                                }
                                SmartHomeTitleMoreMenuHelper.this.e();
                                Toast.makeText(SmartHomeTitleMoreMenuHelper.this.b(), R.string.bind_success, 0).show();
                                SmartHomeTitleMoreMenuHelper.this.b((int) R.string.smarthome_device_auth_release);
                                xQProgressDialog.dismiss();
                                Intent intent = new Intent("com.xiaomi.router");
                                intent.putExtra(HomeManager.v, "delete");
                                intent.putExtra("userId", CoreApi.a().s());
                                intent.putExtra("id", str);
                                SHApplication.getAppContext().sendBroadcast(intent);
                            }

                            public void onFailure(Error error) {
                                SmartHomeTitleMoreMenuHelper.this.e();
                                Toast.makeText(SmartHomeTitleMoreMenuHelper.this.b(), R.string.bind_error, 0).show();
                                xQProgressDialog.dismiss();
                            }
                        });
                    }
                } else {
                    MiioManager.a().a(c.did, c.pid, (AsyncResponseCallback<Void>) new AsyncResponseCallback<Void>() {
                        public void a(Void voidR) {
                            SmartHomeTitleMoreMenuHelper.this.e();
                            Toast.makeText(SmartHomeTitleMoreMenuHelper.this.b(), R.string.bind_success, 0).show();
                            SmartHomeTitleMoreMenuHelper.this.b((int) R.string.smarthome_device_auth_release);
                            xQProgressDialog.dismiss();
                        }

                        public void a(int i) {
                            SmartHomeTitleMoreMenuHelper.this.e();
                            Toast.makeText(SmartHomeTitleMoreMenuHelper.this.b(), R.string.bind_error, 0).show();
                            xQProgressDialog.dismiss();
                        }

                        public void a(int i, Object obj) {
                            SmartHomeTitleMoreMenuHelper.this.e();
                            Toast.makeText(SmartHomeTitleMoreMenuHelper.this.b(), R.string.bind_error, 0).show();
                            xQProgressDialog.dismiss();
                        }
                    });
                }
            }
        }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ((MLAlertDialog) dialogInterface).setAudoDismiss(true);
            }
        }).d();
    }

    public void l() {
        Device c2 = c();
        if (c2.isOwner()) {
            UpdateApi.a().f(SHApplication.getAppContext(), c2.did, c2.pid, new AsyncCallback<JSONObject, Error>() {
                public void onFailure(Error error) {
                }

                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    SmartHomeTitleMoreMenuHelper.this.r = !jSONObject.optBoolean("updating") && !jSONObject.optBoolean("isLatest");
                    SmartHomeTitleMoreMenuHelper.this.u = jSONObject.optString("curr");
                    SmartHomeTitleMoreMenuHelper.this.t = jSONObject.optString(Constants.PageFragment.PAGE_LATEST);
                    SmartHomeTitleMoreMenuHelper.this.s = jSONObject.optString("description");
                    SmartHomeTitleMoreMenuHelper.this.m();
                    if (SmartHomeTitleMoreMenuHelper.this.b() != null && SmartHomeTitleMoreMenuHelper.this.r && !SmartHomeTitleMoreMenuHelper.this.v) {
                        SmartHomeTitleMoreMenuHelper.this.v = true;
                        new MLAlertDialog.Builder(SmartHomeTitleMoreMenuHelper.this.b()).a((int) R.string.upgrade_new_version).a((DialogInterface.OnCancelListener) new DialogInterface.OnCancelListener() {
                            public void onCancel(DialogInterface dialogInterface) {
                                SmartHomeTitleMoreMenuHelper.this.v = false;
                            }
                        }).a((int) R.string.upgrade_new_version_upgrade, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                SmartHomeTitleMoreMenuHelper.this.q();
                                SmartHomeTitleMoreMenuHelper.this.v = false;
                            }
                        }).b((int) R.string.upgrade_new_version_cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ((MLAlertDialog) dialogInterface).setAudoDismiss(true);
                                SmartHomeTitleMoreMenuHelper.this.v = false;
                            }
                        }).d();
                    }
                }
            });
        }
    }

    public void m() {
        Device c2 = c();
        if (this.r) {
            if (c2.isOwner()) {
                a(o);
                if (g() != null) {
                    g().setVisibility(0);
                }
            }
        } else if (c2.isOwner()) {
            a(n);
            if (g() != null) {
                g().setVisibility(4);
            }
        }
    }

    public void n() {
        this.r = false;
    }
}
