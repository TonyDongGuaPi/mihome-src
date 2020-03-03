package com.xiaomi.smarthome.international;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.SmartHomeMainActivity;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.bbs.BBSInitializer;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.server_compact.ServerBean;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.framework.corereceiver.CoreHostApiImpl;
import com.xiaomi.smarthome.framework.login.logic.LoginManager;
import com.xiaomi.smarthome.framework.navigate.SmartHomeLauncher;
import com.xiaomi.smarthome.framework.page.ActivityStack;
import com.xiaomi.smarthome.notishortcut.SmartNotiApi;
import com.xiaomi.smarthome.shopglobal.ShopGlobalHelper;

public class ServerLocationIncompatibleActivity extends Activity {
    public static final String SERVER_KEY = "server";
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public Context f18418a;
    Button mCancel;
    TextView mContextTextView;
    Button mOK;
    ImageView mRememberView;
    ServerBean mServerInfo;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT != 26) {
            setRequestedOrientation(1);
        }
        this.f18418a = this;
        setContentView(R.layout.server_location_incompatible_activity);
        this.mServerInfo = (ServerBean) getIntent().getParcelableExtra("server");
        if (this.mServerInfo == null) {
            a();
            return;
        }
        this.mContextTextView = (TextView) findViewById(R.id.content_tv);
        new AsyncTask<Void, Void, String>() {
            /* access modifiers changed from: protected */
            /* renamed from: a */
            public String doInBackground(Void... voidArr) {
                return ServerCompact.a((Context) ServerLocationIncompatibleActivity.this, ServerLocationIncompatibleActivity.this.mServerInfo.b);
            }

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public void onPostExecute(String str) {
                super.onPostExecute(str);
                ServerLocationIncompatibleActivity.this.mContextTextView.setText(String.format(ServerLocationIncompatibleActivity.this.getString(R.string.server_location_incompatible_content), new Object[]{str}));
            }
        }.execute(new Void[0]);
        this.mRememberView = (ImageView) findViewById(R.id.remember);
        findViewById(R.id.remember_container).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (ServerLocationIncompatibleActivity.this.mRememberView.isSelected()) {
                    ServerLocationIncompatibleActivity.this.mRememberView.setSelected(false);
                } else {
                    ServerLocationIncompatibleActivity.this.mRememberView.setSelected(true);
                }
            }
        });
        this.mRememberView.setSelected(true);
        this.mCancel = (Button) findViewById(R.id.cancel);
        this.mCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ServerLocationIncompatibleActivity.this.a();
            }
        });
        this.mOK = (Button) findViewById(R.id.ok);
        final ServerBean F = CoreApi.a().F();
        this.mOK.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (CoreApi.a().q()) {
                    LoginManager.a().logout(new AsyncCallback<Void, Error>() {
                        /* renamed from: a */
                        public void onSuccess(Void voidR) {
                            ServerLocationIncompatibleActivity.this.a(ServerLocationIncompatibleActivity.this.mServerInfo, new AsyncCallback<Void, Error>() {
                                public void onFailure(Error error) {
                                }

                                /* renamed from: a */
                                public void onSuccess(Void voidR) {
                                    ActivityStack.instance.doClearOnServerChanged();
                                    ShopGlobalHelper.b(F);
                                    BBSInitializer.b(F);
                                    Intent intent = new Intent(SHApplication.getAppContext(), SmartHomeLauncher.class);
                                    intent.setData(Uri.parse("https://home.mi.com/main/login"));
                                    ServerLocationIncompatibleActivity.this.startActivity(intent);
                                }
                            });
                        }

                        public void onFailure(Error error) {
                            Toast.makeText(ServerLocationIncompatibleActivity.this.f18418a, R.string.server_change_server_failure, 0).show();
                        }
                    });
                } else {
                    ServerLocationIncompatibleActivity.this.a(ServerLocationIncompatibleActivity.this.mServerInfo, new AsyncCallback<Void, Error>() {
                        public void onFailure(Error error) {
                        }

                        /* renamed from: a */
                        public void onSuccess(Void voidR) {
                            ActivityStack.instance.doClearOnServerChanged();
                            ShopGlobalHelper.b(F);
                            BBSInitializer.b(F);
                            ServerLocationIncompatibleActivity.this.startActivity(new Intent(ServerLocationIncompatibleActivity.this.f18418a, SmartHomeMainActivity.class));
                        }
                    });
                }
            }
        });
    }

    public void onBackPressed() {
        a();
        super.onBackPressed();
    }

    /* access modifiers changed from: private */
    public void a(final ServerBean serverBean, final AsyncCallback<Void, Error> asyncCallback) {
        if (serverBean == null) {
            a();
            return;
        }
        SmartNotiApi.a(SHApplication.getAppContext()).a(serverBean);
        if (CoreApi.a().l()) {
            CoreApi.a().a(serverBean, asyncCallback);
        } else {
            IntentFilter intentFilter = new IntentFilter(CoreHostApiImpl.e);
            LocalBroadcastManager.getInstance(SHApplication.getAppContext()).registerReceiver(new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    LocalBroadcastManager.getInstance(SHApplication.getAppContext()).unregisterReceiver(this);
                    CoreApi.a().a(serverBean, (AsyncCallback<Void, Error>) asyncCallback);
                }
            }, intentFilter);
        }
        if (this.mRememberView.isSelected()) {
            ServerHelper.a(true);
        } else {
            ServerHelper.a(false);
        }
        finish();
        overridePendingTransition(0, 0);
        LocalBroadcastManager instance = LocalBroadcastManager.getInstance(this.f18418a);
        Intent intent = new Intent(ServerHelper.b);
        intent.putExtra("param_key", 1);
        instance.sendBroadcast(intent);
    }

    /* access modifiers changed from: private */
    public void a() {
        if (this.mRememberView != null) {
            if (this.mRememberView.isSelected()) {
                ServerHelper.a(true);
            } else {
                ServerHelper.a(false);
            }
        }
        finish();
        overridePendingTransition(0, 0);
        LocalBroadcastManager instance = LocalBroadcastManager.getInstance(this.f18418a);
        Intent intent = new Intent(ServerHelper.b);
        intent.putExtra("param_key", 2);
        instance.sendBroadcast(intent);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this.f18418a).sendBroadcast(new Intent(ServerHelper.c));
    }
}
