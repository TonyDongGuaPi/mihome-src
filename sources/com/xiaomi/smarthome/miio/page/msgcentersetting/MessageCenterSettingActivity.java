package com.xiaomi.smarthome.miio.page.msgcentersetting;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.xiaomi.mipush.sdk.Constants;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.family.api.RemoteFamilyApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.CorntabUtils;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.widget.ListItemView;
import com.xiaomi.smarthome.library.common.widget.SwitchButton;
import com.xiaomi.smarthome.library.common.widget.TimerPickerHourToHour;
import com.xiaomi.smarthome.miio.page.msgcentersetting.model.From;
import com.xiaomi.smarthome.miio.page.msgcentersetting.model.MsgCenterSettingData;
import com.xiaomi.smarthome.miio.page.msgcentersetting.model.NoInterruptTime;
import com.xiaomi.smarthome.miio.page.msgcentersetting.model.Param;
import com.xiaomi.smarthome.miio.page.msgcentersetting.model.To;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

public class MessageCenterSettingActivity extends BaseActivity {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public Disposable f19861a;
    /* access modifiers changed from: private */
    public MsgCenterSettingData b;
    /* access modifiers changed from: private */
    public MsgCenterSettingData c;
    @BindView(2131428836)
    ListItemView deviceShareItem;
    @BindView(2131428837)
    SwitchButton deviceShareItemBtn;
    @BindView(2131428838)
    TextView deviceShareItemTitle;
    @BindView(2131429182)
    SwitchButton familyInvitationBtn;
    @BindView(2131429183)
    ListItemView familyInvitationItem;
    @BindView(2131429184)
    TextView familyInvitationItemTitle;
    XQProgressDialog mProcessDialog;
    @BindView(2131430969)
    ImageView moduleA3ReturnBtn;
    @BindView(2131430971)
    ImageView moduleA3ReturnMoreMoreBtn;
    @BindView(2131430975)
    TextView moduleA3ReturnTitle;
    @BindView(2131431694)
    SwitchButton pushDeviceItemBtn;
    @BindView(2131431696)
    ListItemView pushHomeDeviceItem;
    @BindView(2131431701)
    ListItemView pushShareDeviceItem;
    @BindView(2131431703)
    ListItemView pushSilentItem;
    @BindView(2131431704)
    SwitchButton pushSilentItemBtn;
    @BindView(2131431705)
    TextView pushSilentItemTitle;
    @BindView(2131431706)
    ListItemView pushSilentTimeItem;
    @BindView(2131431707)
    TextView pushSilentTimeItemTitle;
    @BindView(2131431708)
    TextView pushSilentTimeTv;
    @BindView(2131432426)
    ListItemView shopItem;
    @BindView(2131432427)
    SwitchButton shopItemBtn;
    @BindView(2131432428)
    TextView shopItemTitle;
    @BindView(2131432919)
    FrameLayout titleBar;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_message_center_setting);
        ButterKnife.bind((Activity) this);
        d();
        a();
    }

    private void a() {
        this.moduleA3ReturnMoreMoreBtn.setVisibility(8);
        this.moduleA3ReturnTitle.setText(R.string.message_center_setting_title);
        this.deviceShareItemBtn.setOnTouchEnable(false);
        this.familyInvitationBtn.setOnTouchEnable(false);
        this.shopItemBtn.setOnTouchEnable(false);
        this.pushSilentItemBtn.setOnTouchEnable(false);
        this.pushDeviceItemBtn.setOnTouchEnable(false);
        findViewById(R.id.family_invitation_item).setVisibility(0);
        if (CoreApi.a().D()) {
            findViewById(R.id.shop_item).setVisibility(8);
        } else {
            findViewById(R.id.shop_item).setVisibility(0);
        }
    }

    @OnClick({2131430969, 2131431693, 2131428837, 2131428836, 2131429182, 2131429183, 2131432427, 2131432426, 2131431704, 2131431703, 2131431706, 2131431696, 2131431701, 2131431694})
    public void onClick(View view) {
        try {
            this.c = (MsgCenterSettingData) deepClone(this.b);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this.c != null || view.getId() == R.id.module_a_3_return_btn) {
            switch (view.getId()) {
                case R.id.device_share_item:
                    this.deviceShareItemBtn.setChecked(!this.deviceShareItemBtn.isChecked());
                    this.c.setDeviceShareSwitch(this.deviceShareItemBtn.isChecked() ? 1 : 0);
                    c();
                    return;
                case R.id.device_share_item_btn:
                    this.c.setDeviceShareSwitch(this.deviceShareItemBtn.isChecked() ? 1 : 0);
                    c();
                    return;
                case R.id.family_invitation_btn:
                    this.c.setFamilyInvitationSwitch(this.familyInvitationBtn.isChecked() ? 1 : 0);
                    c();
                    return;
                case R.id.family_invitation_item:
                    this.familyInvitationBtn.setChecked(!this.familyInvitationBtn.isChecked());
                    this.c.setFamilyInvitationSwitch(this.familyInvitationBtn.isChecked() ? 1 : 0);
                    c();
                    return;
                case R.id.module_a_3_return_btn:
                    finish();
                    return;
                case R.id.push_device_item:
                    this.pushDeviceItemBtn.setChecked(!this.pushDeviceItemBtn.isChecked());
                    this.c.setDevicePushSwitch(this.pushDeviceItemBtn.isChecked() ? 1 : 0);
                    c();
                    return;
                case R.id.push_home_device_item:
                    Intent intent = new Intent(this, DevicePushSettingActivity.class);
                    intent.putExtra(DevicePushSettingActivity.EXTRA_KEY, DevicePushSettingActivity.EXTRA_HOME_DEVICE);
                    startActivity(intent);
                    return;
                case R.id.push_share_device_item:
                    Intent intent2 = new Intent(this, DevicePushSettingActivity.class);
                    intent2.putExtra(DevicePushSettingActivity.EXTRA_KEY, DevicePushSettingActivity.EXTRA_SHARE_DEVICE);
                    startActivity(intent2);
                    return;
                case R.id.push_silent_item:
                    this.pushSilentItemBtn.setChecked(!this.pushSilentItemBtn.isChecked());
                    this.c.setNointerruptSwitch(this.pushSilentItemBtn.isChecked() ? 1 : 0);
                    c();
                    return;
                case R.id.push_silent_item_btn:
                    this.c.setNointerruptSwitch(this.pushSilentItemBtn.isChecked() ? 1 : 0);
                    c();
                    return;
                case R.id.push_silent_time_item:
                    NoInterruptTime noInterruptTime = this.b.getParam().getNoInterruptTime();
                    CorntabUtils.CorntabParam corntabParam = new CorntabUtils.CorntabParam();
                    corntabParam.c = noInterruptTime.getFrom().getHour().intValue();
                    corntabParam.b = noInterruptTime.getFrom().getMin().intValue();
                    CorntabUtils.CorntabParam b2 = CorntabUtils.b(TimeZone.getDefault(), TimeZone.getTimeZone("Asia/Shanghai"), corntabParam);
                    CorntabUtils.CorntabParam corntabParam2 = new CorntabUtils.CorntabParam();
                    corntabParam2.c = noInterruptTime.getTo().getHour().intValue();
                    corntabParam2.b = noInterruptTime.getTo().getMin().intValue();
                    CorntabUtils.CorntabParam b3 = CorntabUtils.b(TimeZone.getDefault(), TimeZone.getTimeZone("Asia/Shanghai"), corntabParam2);
                    Intent intent3 = new Intent(this, InterruptPushTimerSettingActiviy.class);
                    intent3.putExtra(InterruptPushTimerSettingActiviy.FROM_HOUR, b2.c);
                    intent3.putExtra(InterruptPushTimerSettingActiviy.FROM_MIN, b2.b);
                    intent3.putExtra(InterruptPushTimerSettingActiviy.TO_HOUR, b3.c);
                    intent3.putExtra(InterruptPushTimerSettingActiviy.TO_MIN, b3.b);
                    startActivityForResult(intent3, 200);
                    return;
                case R.id.shop_item:
                    this.shopItemBtn.setChecked(!this.shopItemBtn.isChecked());
                    this.c.setShopSwitch(this.shopItemBtn.isChecked() ? 1 : 0);
                    c();
                    return;
                case R.id.shop_item_btn:
                    this.c.setShopSwitch(this.shopItemBtn.isChecked() ? 1 : 0);
                    c();
                    return;
                default:
                    return;
            }
        }
    }

    public static Object deepClone(Object obj) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            new ObjectOutputStream(byteArrayOutputStream).writeObject(obj);
            return new ObjectInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray())).readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 != -1) {
            return;
        }
        if (i == 200 && intent != null) {
            int i3 = 0;
            int intExtra = intent.getIntExtra(InterruptPushTimerSettingActiviy.FROM_HOUR, this.c == null ? 0 : this.c.getParam().getNoInterruptTime().getFrom().getHour().intValue());
            int intExtra2 = intent.getIntExtra(InterruptPushTimerSettingActiviy.FROM_MIN, this.c == null ? 0 : this.c.getParam().getNoInterruptTime().getFrom().getMin().intValue());
            int intExtra3 = intent.getIntExtra(InterruptPushTimerSettingActiviy.TO_HOUR, this.c == null ? 0 : this.c.getParam().getNoInterruptTime().getTo().getHour().intValue());
            if (this.c != null) {
                i3 = this.c.getParam().getNoInterruptTime().getTo().getMin().intValue();
            }
            int intExtra4 = intent.getIntExtra(InterruptPushTimerSettingActiviy.TO_MIN, i3);
            if (this.c != null) {
                CorntabUtils.CorntabParam corntabParam = new CorntabUtils.CorntabParam();
                corntabParam.c = intExtra;
                corntabParam.b = intExtra2;
                CorntabUtils.CorntabParam a2 = CorntabUtils.a(TimeZone.getTimeZone("Asia/Shanghai"), corntabParam);
                this.c.getParam().getNoInterruptTime().getFrom().setHour(Integer.valueOf(a2.c));
                this.c.getParam().getNoInterruptTime().getFrom().setMin(Integer.valueOf(a2.b));
                CorntabUtils.CorntabParam corntabParam2 = new CorntabUtils.CorntabParam();
                corntabParam2.c = intExtra3;
                corntabParam2.b = intExtra4;
                CorntabUtils.CorntabParam a3 = CorntabUtils.a(TimeZone.getTimeZone("Asia/Shanghai"), corntabParam2);
                this.c.getParam().getNoInterruptTime().getTo().setHour(Integer.valueOf(a3.c));
                this.c.getParam().getNoInterruptTime().getTo().setMin(Integer.valueOf(a3.b));
            }
            c();
        } else if (i == 300 && intent.getExtras() != null) {
            try {
                MsgCenterSettingData msgCenterSettingData = (MsgCenterSettingData) intent.getExtras().get(DevicePushSettingActivity.EXTRA_KEY);
                if (msgCenterSettingData != null) {
                    this.b = msgCenterSettingData;
                    e();
                }
            } catch (Exception unused) {
            }
        }
    }

    private void b() {
        NoInterruptTime noInterruptTime = this.b.getParam().getNoInterruptTime();
        CorntabUtils.CorntabParam corntabParam = new CorntabUtils.CorntabParam();
        corntabParam.c = noInterruptTime.getFrom().getHour().intValue();
        corntabParam.b = noInterruptTime.getFrom().getMin().intValue();
        CorntabUtils.CorntabParam b2 = CorntabUtils.b(TimeZone.getDefault(), TimeZone.getTimeZone("Asia/Shanghai"), corntabParam);
        CorntabUtils.CorntabParam corntabParam2 = new CorntabUtils.CorntabParam();
        corntabParam2.c = noInterruptTime.getTo().getHour().intValue();
        corntabParam2.b = noInterruptTime.getTo().getMin().intValue();
        final TimerPickerHourToHour timerPickerHourToHour = new TimerPickerHourToHour((Context) this, b2.c, CorntabUtils.b(TimeZone.getDefault(), TimeZone.getTimeZone("Asia/Shanghai"), corntabParam2).c);
        new MLAlertDialog.Builder(this).b((View) timerPickerHourToHour).a((int) R.string.confirm_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                int[] hourFromTo = timerPickerHourToHour.getHourFromTo();
                if (hourFromTo != null && hourFromTo.length >= 2) {
                    try {
                        MsgCenterSettingData unused = MessageCenterSettingActivity.this.c = (MsgCenterSettingData) MessageCenterSettingActivity.deepClone(MessageCenterSettingActivity.this.b);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (MessageCenterSettingActivity.this.c != null) {
                        CorntabUtils.CorntabParam corntabParam = new CorntabUtils.CorntabParam();
                        corntabParam.c = hourFromTo[0];
                        corntabParam.b = 0;
                        CorntabUtils.CorntabParam a2 = CorntabUtils.a(TimeZone.getTimeZone("Asia/Shanghai"), corntabParam);
                        MessageCenterSettingActivity.this.c.getParam().getNoInterruptTime().getFrom().setHour(Integer.valueOf(a2.c));
                        MessageCenterSettingActivity.this.c.getParam().getNoInterruptTime().getFrom().setMin(Integer.valueOf(a2.b));
                        CorntabUtils.CorntabParam corntabParam2 = new CorntabUtils.CorntabParam();
                        corntabParam2.c = hourFromTo[1];
                        corntabParam2.b = 0;
                        CorntabUtils.CorntabParam a3 = CorntabUtils.a(TimeZone.getTimeZone("Asia/Shanghai"), corntabParam2);
                        MessageCenterSettingActivity.this.c.getParam().getNoInterruptTime().getTo().setHour(Integer.valueOf(a3.c));
                        MessageCenterSettingActivity.this.c.getParam().getNoInterruptTime().getTo().setMin(Integer.valueOf(a3.b));
                        MessageCenterSettingActivity.this.c();
                    }
                }
            }
        }).b().show();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        if (this.f19861a != null && !this.f19861a.isDisposed()) {
            this.f19861a.dispose();
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    /* access modifiers changed from: private */
    public void c() {
        f();
        this.f19861a = (Disposable) Observable.create(new ObservableOnSubscribe<Object>() {
            public void subscribe(final ObservableEmitter<Object> observableEmitter) throws Exception {
                RemoteFamilyApi.a().a((Context) MessageCenterSettingActivity.this, MessageCenterSettingActivity.this.c.toJSON(), (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                    /* renamed from: a */
                    public void onSuccess(Void voidR) {
                        if (!observableEmitter.isDisposed()) {
                            MsgCenterHelper.a(MessageCenterSettingActivity.this.c.toJSON().toString());
                            observableEmitter.onNext(new Object());
                            observableEmitter.onComplete();
                        }
                    }

                    public void onFailure(Error error) {
                        if (!observableEmitter.isDisposed()) {
                            observableEmitter.onError(new Throwable(error.b()));
                        }
                    }
                });
            }
        }).subscribeOn(Schedulers.io()).delay(500, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableObserver<Object>() {
            public void onNext(Object obj) {
            }

            public void onError(Throwable th) {
                if ((Build.VERSION.SDK_INT < 17 || !MessageCenterSettingActivity.this.isDestroyed()) && !MessageCenterSettingActivity.this.isFinishing()) {
                    MessageCenterSettingActivity.this.e();
                    MessageCenterSettingActivity.this.g();
                    Toast.makeText(MessageCenterSettingActivity.this, R.string.home_set_failed, 0).show();
                }
            }

            public void onComplete() {
                if ((Build.VERSION.SDK_INT < 17 || !MessageCenterSettingActivity.this.isDestroyed()) && !MessageCenterSettingActivity.this.isFinishing()) {
                    MsgCenterSettingData unused = MessageCenterSettingActivity.this.b = MessageCenterSettingActivity.this.c;
                    MessageCenterSettingActivity.this.e();
                    MessageCenterSettingActivity.this.g();
                }
            }
        });
    }

    private void d() {
        f();
        this.f19861a = (Disposable) Observable.create(new ObservableOnSubscribe<JSONObject>() {
            public void subscribe(final ObservableEmitter<JSONObject> observableEmitter) throws Exception {
                RemoteFamilyApi.a().e((Context) MessageCenterSettingActivity.this, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                    /* renamed from: a */
                    public void onSuccess(JSONObject jSONObject) {
                        if (!observableEmitter.isDisposed()) {
                            observableEmitter.onNext(jSONObject);
                            observableEmitter.onComplete();
                        }
                    }

                    public void onFailure(Error error) {
                        if (!observableEmitter.isDisposed()) {
                            observableEmitter.onError(new Exception(error == null ? "" : error.b()));
                        }
                    }
                });
            }
        }).map(new Function<JSONObject, MsgCenterSettingData>() {
            /* renamed from: a */
            public MsgCenterSettingData apply(JSONObject jSONObject) throws Exception {
                if (jSONObject == null) {
                    jSONObject = MsgCenterHelper.a();
                }
                if (jSONObject == null) {
                    return null;
                }
                return MsgCenterSettingData.parse(jSONObject);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableObserver<MsgCenterSettingData>() {
            public void onComplete() {
            }

            /* renamed from: a */
            public void onNext(MsgCenterSettingData msgCenterSettingData) {
                if (!MessageCenterSettingActivity.this.f19861a.isDisposed()) {
                    if ((Build.VERSION.SDK_INT < 17 || !MessageCenterSettingActivity.this.isDestroyed()) && !MessageCenterSettingActivity.this.isFinishing()) {
                        MessageCenterSettingActivity.this.g();
                        MsgCenterSettingData unused = MessageCenterSettingActivity.this.b = msgCenterSettingData;
                        MessageCenterSettingActivity.this.e();
                    }
                }
            }

            public void onError(Throwable th) {
                if (!MessageCenterSettingActivity.this.f19861a.isDisposed()) {
                    if ((Build.VERSION.SDK_INT < 17 || !MessageCenterSettingActivity.this.isDestroyed()) && !MessageCenterSettingActivity.this.isFinishing()) {
                        MessageCenterSettingActivity.this.g();
                        ToastUtil.a((CharSequence) MessageCenterSettingActivity.this.getString(R.string.message_center_setting_load_err));
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void e() {
        Param param;
        if (this.b != null && (param = this.b.getParam()) != null) {
            int i = 0;
            this.pushDeviceItemBtn.setChecked(param.getScene().intValue() == 1);
            this.deviceShareItemBtn.setChecked(param.getDeviceShare().intValue() == 1);
            this.familyInvitationBtn.setChecked(param.getFamilyShare().intValue() == 1);
            this.shopItemBtn.setChecked(param.getShop().intValue() == 1);
            this.pushSilentItemBtn.setChecked(param.getNoInterrupt().intValue() == 1);
            if (this.pushSilentItemBtn.isChecked()) {
                this.pushSilentTimeItemTitle.setTextColor(getResources().getColor(R.color.std_list_title));
                this.pushSilentTimeItemTitle.setEnabled(true);
                this.pushSilentTimeItem.setEnabled(true);
            } else {
                this.pushSilentTimeItemTitle.setTextColor(getResources().getColor(R.color.std_list_subtitle));
                this.pushSilentTimeItemTitle.setEnabled(false);
                this.pushSilentTimeItem.setEnabled(false);
            }
            NoInterruptTime noInterruptTime = param.getNoInterruptTime();
            if (noInterruptTime != null) {
                From from = noInterruptTime.getFrom();
                To to = noInterruptTime.getTo();
                if (!(from == null || to == null)) {
                    CorntabUtils.CorntabParam corntabParam = new CorntabUtils.CorntabParam();
                    corntabParam.c = from.getHour().intValue();
                    corntabParam.b = from.getMin().intValue();
                    CorntabUtils.CorntabParam b2 = CorntabUtils.b(TimeZone.getDefault(), TimeZone.getTimeZone("Asia/Shanghai"), corntabParam);
                    CorntabUtils.CorntabParam corntabParam2 = new CorntabUtils.CorntabParam();
                    corntabParam2.c = to.getHour().intValue();
                    corntabParam2.b = to.getMin().intValue();
                    CorntabUtils.CorntabParam b3 = CorntabUtils.b(TimeZone.getDefault(), TimeZone.getTimeZone("Asia/Shanghai"), corntabParam2);
                    String formatTime = InterruptPushTimerSettingActiviy.formatTime(b2.c, b2.b);
                    String formatTime2 = InterruptPushTimerSettingActiviy.formatTime(b3.c, b3.b);
                    TextView textView = this.pushSilentTimeTv;
                    textView.setText(formatTime + Constants.J + formatTime2);
                }
            }
            this.pushHomeDeviceItem.setVisibility(param.getScene().intValue() == 1 ? 0 : 8);
            ListItemView listItemView = this.pushShareDeviceItem;
            if (param.getScene().intValue() != 1) {
                i = 8;
            }
            listItemView.setVisibility(i);
        }
    }

    private void f() {
        this.mProcessDialog = new XQProgressDialog(this);
        this.mProcessDialog.setMessage(getResources().getString(R.string.loading_share_info));
        this.mProcessDialog.setCancelable(true);
        this.mProcessDialog.show();
        this.mProcessDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
            }
        });
    }

    /* access modifiers changed from: private */
    public void g() {
        if (this.mProcessDialog != null) {
            this.mProcessDialog.dismiss();
        }
    }
}
