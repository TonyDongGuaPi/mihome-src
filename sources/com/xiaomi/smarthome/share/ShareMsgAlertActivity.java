package com.xiaomi.smarthome.share;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.family.api.RemoteFamilyApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.widget.EllipsesProgressTextView;
import com.xiaomi.smarthome.messagecenter.HomeShareMessageManager;
import com.xiaomi.smarthome.messagecenter.IMessage;
import com.xiaomi.smarthome.messagecenter.ShareMessageManager;
import com.xiaomi.smarthome.miio.db.record.MessageRecord;
import com.xiaomi.smarthome.miio.page.MessageCenterListActivity;
import com.xiaomi.smarthome.newui.DropMenuAdapter;
import com.xiaomi.smarthome.newui.MultiHomeManagerActivity;
import com.xiaomi.smarthome.newui.widget.topnavi.PageBean;
import com.xiaomi.smarthome.stat.STAT;
import java.util.List;
import org.json.JSONObject;

public class ShareMsgAlertActivity extends BaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private Button f22115a;
    private Button b;
    private Button c;
    private View d;
    private View e;
    private EllipsesProgressTextView f;
    private DataModel g;
    private int h = Color.parseColor("#F46666");
    /* access modifiers changed from: private */
    public HomeUpdatedReceiver i;
    public int mMsgCount = 0;
    public List<IMessage> mMsgList;
    XQProgressDialog mProcessDialog;

    class HomeUpdatedReceiver extends BroadcastReceiver {

        /* renamed from: a  reason: collision with root package name */
        public String f22127a;

        HomeUpdatedReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            Home j = HomeManager.a().j(this.f22127a);
            if (j == null) {
                ShareMsgAlertActivity.this.c();
                return;
            }
            if (ShareMsgAlertActivity.this.mProcessDialog != null) {
                ShareMsgAlertActivity.this.mProcessDialog.dismiss();
            }
            ShareMsgAlertActivity.this.a(j);
            if (ShareMsgAlertActivity.this.i != null) {
                LocalBroadcastManager.getInstance(ShareMsgAlertActivity.this).unregisterReceiver(ShareMsgAlertActivity.this.i);
                HomeUpdatedReceiver unused = ShareMsgAlertActivity.this.i = null;
            }
            ShareMsgAlertActivity.this.mHandler.removeCallbacksAndMessages((Object) null);
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        int i2 = ShareAlertDialogHelper.c;
        List<IMessage> list = ShareAlertDialogHelper.d;
        if (i2 <= 0 || list == null || list.isEmpty()) {
            finish();
            return;
        }
        setContentView(R.layout.activity_share_msg_alert_layout);
        a();
        b();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        if (this.i != null) {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(this.i);
            this.i = null;
        }
    }

    private void a() {
        this.f22115a = (Button) findViewById(R.id.button2);
        this.b = (Button) findViewById(R.id.button3);
        this.b.setTextColor(getResources().getColor(R.color.black));
        this.c = (Button) findViewById(R.id.button1);
        this.e = findViewById(R.id.click_mask);
        this.e.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                ShareMsgAlertActivity.this.d(view);
            }
        });
        findViewById(R.id.topPanel).setVisibility(8);
        findViewById(R.id.contentPanel).setVisibility(8);
        findViewById(R.id.title_divider_line).setVisibility(8);
        d();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void d(View view) {
        c();
    }

    private void b() {
        this.mMsgCount = ShareAlertDialogHelper.c;
        this.mMsgList = ShareAlertDialogHelper.d;
        DataModel dataModel = new DataModel(0, this.mMsgCount, this.mMsgList);
        if (this.mMsgList.get(0) instanceof HomeShareMessageManager.HomeShareMessage) {
            a(dataModel);
        } else {
            b(dataModel);
        }
    }

    private void a(DataModel dataModel) {
        if (dataModel != null && dataModel.h != null && !dataModel.h.isEmpty()) {
            final IMessage iMessage = (IMessage) dataModel.h.get(0);
            TextView textView = (TextView) this.d.findViewById(R.id.share_tip);
            textView.setSingleLine(false);
            textView.setTextSize(15.0f);
            if (dataModel.g > 1) {
                textView.setText(R.string.multi_home_invite);
                this.c.setText(R.string.look_up);
                this.c.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Intent intent = new Intent(ShareMsgAlertActivity.this, MultiHomeManagerActivity.class);
                        intent.putExtra("from", 5);
                        ShareMsgAlertActivity.this.startActivity(intent);
                        ShareMsgAlertActivity.this.c();
                        STAT.d.aS();
                    }
                });
            } else {
                iMessage.a(textView);
                this.c.setText(R.string.family_accept);
                this.c.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        ShareMsgAlertActivity.this.mProcessDialog = new XQProgressDialog(ShareMsgAlertActivity.this);
                        ShareMsgAlertActivity.this.mProcessDialog.setCancelable(false);
                        ShareMsgAlertActivity.this.mProcessDialog.setMessage(ShareMsgAlertActivity.this.getResources().getString(R.string.loading_share_info));
                        ShareMsgAlertActivity.this.mProcessDialog.show();
                        long e = ((HomeShareMessageManager.HomeShareMessage) iMessage).e();
                        final long d = ((HomeShareMessageManager.HomeShareMessage) iMessage).d();
                        RemoteFamilyApi.a().a(e, d, 1, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                            /* renamed from: a */
                            public void onSuccess(JSONObject jSONObject) {
                                ShareMsgAlertActivity.this.findViewById(R.id.alert_dialog).setVisibility(8);
                                HomeUpdatedReceiver unused = ShareMsgAlertActivity.this.i = new HomeUpdatedReceiver();
                                ShareMsgAlertActivity.this.i.f22127a = String.valueOf(d);
                                LocalBroadcastManager.getInstance(ShareMsgAlertActivity.this).registerReceiver(ShareMsgAlertActivity.this.i, new IntentFilter(HomeManager.t));
                                HomeManager.a().b();
                                ShareMsgAlertActivity.this.mHandler.postDelayed(new Runnable() {
                                    public void run() {
                                        ShareMsgAlertActivity.this.c();
                                    }
                                }, 6000);
                            }

                            public void onFailure(Error error) {
                                ShareMsgAlertActivity.this.mProcessDialog.dismiss();
                                ShareMsgAlertActivity.this.c();
                            }
                        });
                        STAT.d.aS();
                    }
                });
            }
            this.b.setVisibility(8);
            this.d.findViewById(R.id.device_img).setVisibility(8);
            this.d.findViewById(R.id.tv_share_title).setVisibility(8);
            this.d.findViewById(R.id.device_name).setVisibility(8);
            this.f22115a.setText(R.string.cancel);
            this.f22115a.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    ShareMsgAlertActivity.this.c(view);
                }
            });
            STAT.c.p(((HomeShareMessageManager.HomeShareMessage) iMessage).d());
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void c(View view) {
        c();
        STAT.d.aT();
    }

    /* access modifiers changed from: private */
    public void a(final Home home) {
        if (home != null) {
            new MLAlertDialog.Builder(this).b((CharSequence) String.format(getString(R.string.home_share_response_success1), new Object[]{home.k()})).a((int) R.string.view_now, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    ShareMsgAlertActivity.this.c();
                    HomeManager.a().a(home.j(), (AsyncCallback) null);
                    STAT.d.a(1, 1);
                }
            }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    ShareMsgAlertActivity.this.c();
                    STAT.d.aK();
                }
            }).d();
            STAT.c.f(1);
        }
    }

    /* access modifiers changed from: private */
    public void c() {
        finish();
        overridePendingTransition(0, 0);
    }

    private void d() {
        this.d = getDialogContentView();
        this.f = (EllipsesProgressTextView) this.d.findViewById(R.id.progress_tip);
        ((FrameLayout) findViewById(R.id.custom)).addView(this.d);
    }

    public View getDialogContentView() {
        return LayoutInflater.from(getApplicationContext()).inflate(R.layout.share_notification_dialog_content_layout, (ViewGroup) null);
    }

    private void a(DataModel dataModel, View view) {
        Device d2;
        if (dataModel != null && view != null && dataModel.h != null && !dataModel.h.isEmpty()) {
            IMessage iMessage = (IMessage) dataModel.h.get(0);
            TextView textView = (TextView) view.findViewById(R.id.share_tip);
            if (dataModel.g == 1) {
                iMessage.a(textView, getResources().getString(R.string.share_notification_tips_user), 12);
            } else if (dataModel.g > 1) {
                textView.setText(R.string.share_notification_tips_multi_user);
            }
            if (this.g == null) {
                iMessage.a((SimpleDraweeView) view.findViewById(R.id.device_img));
            }
            TextView textView2 = (TextView) view.findViewById(R.id.device_name);
            textView2.setTextColor(getResources().getColor(R.color.grey_6));
            if (dataModel.f == 0) {
                if ((iMessage instanceof ShareMessageManager.ShareMessage) && (d2 = ((ShareMessageManager.ShareMessage) iMessage).d()) != null) {
                    PluginRecord d3 = CoreApi.a().d(d2.model);
                    if (d3 != null) {
                        textView2.setText(d3.p());
                    } else {
                        textView2.setText(d2.getName());
                    }
                }
            } else if (dataModel.f == 1 || dataModel.f == 2) {
                textView2.setText(R.string.updating_no_ellipse);
            } else if (dataModel.f == 3) {
                textView2.setText(R.string.share_request_accepted);
            } else if (dataModel.f == 4) {
                textView2.setText(R.string.share_request_accept_fail);
                textView2.setTextColor(this.h);
            }
        }
    }

    /* access modifiers changed from: private */
    public void b(DataModel dataModel) {
        if (dataModel != null) {
            a(dataModel, this.d);
            if (dataModel.g > 1) {
                this.f22115a.setVisibility(8);
                this.c.setVisibility(8);
                this.b.setVisibility(0);
                this.b.setText(R.string.look_up);
                this.b.setOnClickListener(new View.OnClickListener() {
                    public final void onClick(View view) {
                        ShareMsgAlertActivity.this.b(view);
                    }
                });
            } else {
                this.f22115a.setVisibility(0);
                this.c.setVisibility(0);
                this.b.setVisibility(8);
                this.f22115a.setText(R.string.cancel);
                this.f22115a.setOnClickListener(new View.OnClickListener() {
                    public final void onClick(View view) {
                        ShareMsgAlertActivity.this.a(view);
                    }
                });
                if (dataModel.h != null && !dataModel.h.isEmpty()) {
                    this.c.setOnClickListener(new View.OnClickListener((ShareMessageManager.ShareMessage) dataModel.h.get(0)) {
                        private final /* synthetic */ ShareMessageManager.ShareMessage f$1;

                        {
                            this.f$1 = r2;
                        }

                        public final void onClick(View view) {
                            ShareMsgAlertActivity.this.a(this.f$1, view);
                        }
                    });
                }
                this.c.setTextColor(getResources().getColor(R.color.std_dialog_button_green));
                if (dataModel.f == 1) {
                    this.c.setEnabled(false);
                    this.c.setTextColor(getResources().getColor(R.color.std_dialog_button_green_40));
                    this.c.setText(R.string.smarthome_share_accept);
                    if (!this.f.isAnimating()) {
                        this.f.startProgressAnim();
                    }
                    this.f.setVisibility(0);
                } else if (dataModel.f == 2) {
                    this.c.setEnabled(false);
                    this.c.setText(R.string.retry);
                    this.c.setTextColor(getResources().getColor(R.color.std_dialog_button_green_40));
                    if (!this.f.isAnimating()) {
                        this.f.startProgressAnim();
                    }
                    this.f.setVisibility(0);
                } else if (dataModel.f == 3) {
                    this.c.setEnabled(false);
                    this.c.setText(R.string.smarthome_share_accept);
                    this.c.setTextColor(getResources().getColor(R.color.std_dialog_button_green_40));
                    this.f.stopProgressAnim();
                    this.f.setVisibility(8);
                } else if (dataModel.f == 4) {
                    this.c.setEnabled(true);
                    this.c.setText(R.string.retry);
                    this.f.stopProgressAnim();
                    this.f.setVisibility(8);
                } else {
                    this.c.setEnabled(true);
                    this.c.setText(R.string.smarthome_share_accept);
                    this.f.stopProgressAnim();
                    this.f.setVisibility(8);
                }
            }
            this.g = dataModel;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(View view) {
        Intent intent = new Intent(this, MessageCenterListActivity.class);
        intent.putExtra("message_type", 1);
        intent.putExtra(MessageCenterListActivity.INTENT_KEY_TITLE, getResources().getString(R.string.miio_setting_share));
        startActivity(intent);
        c();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        c();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(ShareMessageManager.ShareMessage shareMessage, View view) {
        accept(shareMessage.e(), shareMessage.c(), new AsyncCallback() {
            public void onSuccess(Object obj) {
                if (ShareMsgAlertActivity.this.isValid()) {
                    ShareMsgAlertActivity.this.b(new DataModel(3, ShareMsgAlertActivity.this.mMsgCount, ShareMsgAlertActivity.this.mMsgList));
                    Intent intent = new Intent(DropMenuAdapter.f20253a);
                    intent.putExtra(DropMenuAdapter.c, PageBean.f20911a);
                    LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(intent);
                    ShareMsgAlertActivity.this.mHandler.postDelayed(new Runnable() {
                        public void run() {
                            if (ShareMsgAlertActivity.this.isValid()) {
                                ShareMsgAlertActivity.this.c();
                            }
                        }
                    }, 1000);
                }
            }

            public void onFailure(Error error) {
                if (ShareMsgAlertActivity.this.isValid()) {
                    ShareMsgAlertActivity.this.b(new DataModel(4, ShareMsgAlertActivity.this.mMsgCount, ShareMsgAlertActivity.this.mMsgList));
                }
            }
        });
        int i2 = 1;
        if (!(this.g == null || this.g.f == 0)) {
            i2 = 2;
        }
        b(new DataModel(i2, this.mMsgCount, this.mMsgList));
    }

    /* access modifiers changed from: package-private */
    public void accept(int i2, final String str, final AsyncCallback asyncCallback) {
        RemoteFamilyApi.a().a(SHApplication.getAppContext(), "accept", str, i2, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
            /* renamed from: a */
            public void onSuccess(Void voidR) {
                MessageRecord.delete(str);
                SmartHomeDeviceManager.a().p();
                if (ShareMsgAlertActivity.this.isValid() && asyncCallback != null) {
                    asyncCallback.onSuccess(voidR);
                }
            }

            public void onFailure(final Error error) {
                if (ShareMsgAlertActivity.this.isValid()) {
                    SHApplication.getGlobalHandler().postDelayed(new Runnable() {
                        public void run() {
                            if (ShareMsgAlertActivity.this.isValid()) {
                                if (error.a() == -6) {
                                    Toast.makeText(SHApplication.getAppContext(), R.string.smarthome_share_expired_toast, 0).show();
                                } else {
                                    Toast.makeText(SHApplication.getAppContext(), R.string.handle_error, 0).show();
                                }
                                if (asyncCallback != null) {
                                    asyncCallback.onFailure(error);
                                }
                            }
                        }
                    }, 3000);
                }
            }
        });
    }

    private static class DataModel {

        /* renamed from: a  reason: collision with root package name */
        public static final int f22126a = 0;
        public static final int b = 1;
        public static final int c = 2;
        public static final int d = 3;
        public static final int e = 4;
        /* access modifiers changed from: private */
        public final int f;
        /* access modifiers changed from: private */
        public final int g;
        /* access modifiers changed from: private */
        public final List<IMessage> h;

        private DataModel(int i, int i2, List<IMessage> list) {
            this.f = i;
            this.g = i2;
            this.h = list;
        }
    }
}
