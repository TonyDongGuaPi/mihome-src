package com.xiaomi.smarthome.miio.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.tencent.smtt.sdk.TbsListener;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.utils.ClientRemarkInputView;
import com.xiaomi.smarthome.framework.log.MyLog;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.page.DeviceMoreActivity;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.widget.ColorBar;
import com.xiaomi.smarthome.library.common.widget.ColorCircleView;
import com.xiaomi.smarthome.miio.device.SmartBulbDevice;
import java.util.ArrayList;

public class YeeLinkBulbActivity extends BaseActivity implements ClientRemarkInputView.RenameInterface, SmartBulbDevice.UIListener {
    public static final int AUTO_CHANGE_TIME = 250;
    public static final int AUTO_DECREASE_LIGHT = 2;
    public static final int AUTO_INCREASE_LIGHT = 1;
    public static final String EXTRA_YEELINK_DEVICE = "yeelink_mac";
    public static final String YEELINK_MIIO_SCENE = "yeelink_miio_scene";

    /* renamed from: a  reason: collision with root package name */
    private static final int[] f11835a = {R.string.device_detail_option_rename};
    private static final int[] b = {R.string.yeelight_sun, R.string.yeelight_romantic, R.string.yeelight_beach, R.string.yeelight_warf, R.string.yeelight_passion};
    private static final int c = 5;
    private static final int[][] d = {new int[]{Color.red(-1), Color.green(-1), Color.blue(-1), 100}, new int[]{255, 128, 170, 50}, new int[]{TbsListener.ErrorCode.INSTALL_FROM_UNZIP, 207, 138, 90}, new int[]{43, 153, 217, 50}, new int[]{118, 0, 179, 30}};
    ColorCircleView mColorCircleView;
    TextView mColorName;
    RelativeLayout mControlBarLayout;
    ColorBar mCursorImageView;
    SmartBulbDevice mDevice;
    ImageView mHighImageView;
    TextView mLightTextView;
    ImageView mLowImageView;
    XQProgressDialog mPD;
    ImageView mPen;
    SharedPreferences mSharedPreferences;
    TextView mSwitchTextView;

    public void handleMessage(Message message) {
        super.handleMessage(message);
        switch (message.what) {
            case 1:
                this.mDevice.v();
                this.mHandler.sendEmptyMessageDelayed(1, 250);
                return;
            case 2:
                this.mDevice.w();
                this.mHandler.sendEmptyMessageDelayed(2, 250);
                return;
            default:
                return;
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        String stringExtra = getIntent().getStringExtra("yeelink_mac");
        if (stringExtra == null) {
            MyLog.e(" mac == null and return!");
            finish();
            return;
        }
        Device b2 = SmartHomeDeviceManager.a().b(stringExtra);
        if (b2 == null || !(b2 instanceof SmartBulbDevice)) {
            finish();
            return;
        }
        this.mDevice = (SmartBulbDevice) b2;
        this.mDevice.a((SmartBulbDevice.UIListener) this);
        this.mDevice.x();
        setContentView(R.layout.miio_page_lamp);
        a();
        c();
        this.mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.mSharedPreferences.getBoolean(YEELINK_MIIO_SCENE, true)) {
            SharedPreferences.Editor edit = this.mSharedPreferences.edit();
            edit.putBoolean(YEELINK_MIIO_SCENE, false);
            edit.commit();
            new MLAlertDialog.Builder(this).b((CharSequence) getString(R.string.yeelight_tips_forusage)).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            }).b().show();
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    private void a() {
        findViewById(R.id.module_a_4_return_more_black_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                YeeLinkBulbActivity.this.finish();
            }
        });
        findViewById(R.id.module_a_4_return_more_black_more_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                YeeLinkBulbActivity.this.gotoMorePage();
            }
        });
        if (this.mDevice.isShared()) {
            findViewById(R.id.module_a_4_return_more_black_more_btn).setVisibility(4);
        }
    }

    private void b() {
        ArrayList arrayList = new ArrayList();
        arrayList.add((TextView) findViewById(R.id.color_button1));
        arrayList.add((TextView) findViewById(R.id.color_button2));
        arrayList.add((TextView) findViewById(R.id.color_button3));
        arrayList.add((TextView) findViewById(R.id.color_button4));
        arrayList.add((TextView) findViewById(R.id.color_button5));
        for (int i = 0; i < arrayList.size(); i++) {
            final int rgb = Color.rgb(d[i][0], d[i][1], d[i][2]);
            final int i2 = d[i][3];
            ((TextView) arrayList.get(i)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    YeeLinkBulbActivity.this.setCircleColor(rgb);
                    YeeLinkBulbActivity.this.mCursorImageView.setColor(rgb);
                    YeeLinkBulbActivity.this.setCursorPosition(rgb);
                    YeeLinkBulbActivity.this.mDevice.a(Color.red(rgb), Color.green(rgb), Color.blue(rgb), i2);
                    YeeLinkBulbActivity.this.a(rgb, i2);
                    YeeLinkBulbActivity.this.mLightTextView.setText(String.valueOf(i2));
                }
            });
        }
    }

    private void c() {
        this.mColorName = (TextView) findViewById(R.id.color_name);
        this.mColorName.setVisibility(4);
        this.mPen = (ImageView) findViewById(R.id.light_pen_view);
        b();
        this.mSwitchTextView = (TextView) findViewById(R.id.switch_textview);
        this.mSwitchTextView.setEnabled(true);
        this.mSwitchTextView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (YeeLinkBulbActivity.this.mDevice.r()) {
                    YeeLinkBulbActivity.this.mDevice.t();
                    YeeLinkBulbActivity.this.mSwitchTextView.setBackgroundResource(R.drawable.miio_switch_white_on);
                    return;
                }
                YeeLinkBulbActivity.this.mDevice.u();
                YeeLinkBulbActivity.this.mLightTextView.setText(String.valueOf(0));
            }
        });
        this.mColorCircleView = (ColorCircleView) findViewById(R.id.color_view);
        this.mLightTextView = (TextView) findViewById(R.id.light_textview);
        this.mHighImageView = (ImageView) findViewById(R.id.high_imageview);
        this.mHighImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                YeeLinkBulbActivity.this.mDevice.v();
            }
        });
        this.mHighImageView.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                YeeLinkBulbActivity.this.mHandler.sendEmptyMessageDelayed(1, 250);
                return false;
            }
        });
        this.mHighImageView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() != 1) {
                    return false;
                }
                YeeLinkBulbActivity.this.mHandler.removeMessages(1);
                return false;
            }
        });
        this.mLowImageView = (ImageView) findViewById(R.id.low_imageview);
        this.mLowImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                YeeLinkBulbActivity.this.mDevice.w();
            }
        });
        this.mLowImageView.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                YeeLinkBulbActivity.this.mHandler.sendEmptyMessageDelayed(2, 250);
                return false;
            }
        });
        this.mLowImageView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() != 1) {
                    return false;
                }
                YeeLinkBulbActivity.this.mHandler.removeMessages(2);
                return false;
            }
        });
        this.mControlBarLayout = (RelativeLayout) findViewById(R.id.control_bar_layout);
        this.mCursorImageView = (ColorBar) findViewById(R.id.control_bar_tape);
        d();
        this.mControlBarLayout.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (YeeLinkBulbActivity.this.mDevice.s() <= 0) {
                    if (motionEvent.getAction() == 0) {
                        Toast.makeText(YeeLinkBulbActivity.this, R.string.light_off_noopt, 0).show();
                    }
                    return false;
                } else if (YeeLinkBulbActivity.this.mDevice.A()) {
                    return false;
                } else {
                    if (motionEvent.getAction() != 2 && motionEvent.getAction() != 0 && motionEvent.getAction() != 1) {
                        return false;
                    }
                    int x = (((int) motionEvent.getX()) - YeeLinkBulbActivity.this.mCursorImageView.getLeft()) - (YeeLinkBulbActivity.this.mCursorImageView.getCircleDim() / 2);
                    if (x > YeeLinkBulbActivity.this.mCursorImageView.getMeasuredPoint()) {
                        x = YeeLinkBulbActivity.this.mCursorImageView.getMeasuredPoint();
                    }
                    if (x < 0) {
                        x = 0;
                    }
                    YeeLinkBulbActivity.this.mCursorImageView.movePoint(x);
                    float f = (float) x;
                    if (f <= ((float) YeeLinkBulbActivity.this.mCursorImageView.getMeasuredPoint()) * 0.05f) {
                        YeeLinkBulbActivity.this.setCircleColor(-1);
                        YeeLinkBulbActivity.this.mCursorImageView.setColor(-1);
                        if (motionEvent.getAction() == 1) {
                            YeeLinkBulbActivity.this.mDevice.a(Color.red(-1), Color.green(-1), Color.blue(-1));
                            YeeLinkBulbActivity.this.a(-1, YeeLinkBulbActivity.this.mDevice.s());
                        }
                    } else {
                        float measuredPoint = 360.0f - (((f - (((float) YeeLinkBulbActivity.this.mCursorImageView.getMeasuredPoint()) * 0.05f)) / (((float) YeeLinkBulbActivity.this.mCursorImageView.getMeasuredPoint()) * 0.95f)) * 360.0f);
                        YeeLinkBulbActivity.this.setCircleColor(measuredPoint);
                        YeeLinkBulbActivity.this.mCursorImageView.setColor(YeeLinkBulbActivity.this.a(measuredPoint));
                        if (motionEvent.getAction() == 1) {
                            int access$100 = YeeLinkBulbActivity.this.a(measuredPoint);
                            YeeLinkBulbActivity.this.a(access$100, YeeLinkBulbActivity.this.mDevice.s());
                            YeeLinkBulbActivity.this.mDevice.a(Color.red(access$100), Color.green(access$100), Color.blue(access$100));
                        }
                    }
                    if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
                        YeeLinkBulbActivity.this.mCursorImageView.setPicker(false);
                    } else if (motionEvent.getAction() == 0 || motionEvent.getAction() == 2) {
                        YeeLinkBulbActivity.this.mCursorImageView.setPicker(true);
                    }
                    return true;
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void gotoMorePage() {
        Intent intent = new Intent(this, DeviceMoreActivity.class);
        intent.putExtra("did", this.mDevice.did);
        intent.putExtra(DeviceMoreActivity.ARGS_FIRMWARE_ENABLE, false);
        intent.putExtra(DeviceMoreActivity.ARGS_SHARE_EBABLE, false);
        intent.putExtra(DeviceMoreActivity.ARGS_UNBIND_ENABLE, false);
        intent.putExtra(DeviceMoreActivity.ARGS_DELETE_ENABLE, false);
        startActivityForResult(intent, 1);
        overridePendingTransition(0, 0);
    }

    /* access modifiers changed from: private */
    public int a(float f) {
        return Color.HSVToColor(new float[]{f, 1.0f, 1.0f});
    }

    /* access modifiers changed from: package-private */
    public void setCircleColor(int i) {
        this.mColorCircleView.setColor(i);
        this.mColorCircleView.invalidate();
    }

    /* access modifiers changed from: package-private */
    public void setCircleColor(float f) {
        this.mColorCircleView.setColor(a(f));
        this.mColorCircleView.invalidate();
    }

    /* access modifiers changed from: package-private */
    public void setCursorPosition(int i) {
        int red = Color.red(i);
        int green = Color.green(i);
        int blue = Color.blue(i);
        if (red <= 250 || green <= 250 || blue <= 250) {
            float[] fArr = {0.0f, 1.0f, 1.0f};
            Color.RGBToHSV(red, green, blue, fArr);
            this.mCursorImageView.movePointHSV(fArr[0]);
            return;
        }
        this.mCursorImageView.movePoint(0);
    }

    private void d() {
        if (TextUtils.isEmpty(this.mDevice.getName())) {
            ((TextView) findViewById(R.id.module_a_4_return_more_black_title)).setText(R.string.smart_bulb);
        } else {
            ((TextView) findViewById(R.id.module_a_4_return_more_black_title)).setText(this.mDevice.getName());
        }
        setCircleColor(this.mDevice.e());
        setCursorPosition(this.mDevice.e());
        this.mLightTextView.setText(String.valueOf(this.mDevice.s()));
        a(this.mDevice.e(), this.mDevice.s());
    }

    public void onGetBulbStatus() {
        d();
    }

    public void onLightChanged() {
        this.mLightTextView.setText(String.valueOf(this.mDevice.s()));
    }

    public void modifyBackName(String str) {
        this.mPD = new XQProgressDialog(this);
        this.mPD.setMessage(getString(R.string.changeing_back_name));
        this.mPD.setCancelable(false);
        this.mPD.show();
        this.mDevice.a(str);
    }

    /* access modifiers changed from: private */
    public void a(int i, int i2) {
        boolean z;
        int i3 = 0;
        while (true) {
            z = true;
            if (i3 >= 5) {
                z = false;
                break;
            }
            int red = Color.red(i);
            int green = Color.green(i);
            int blue = Color.blue(i);
            if (red == d[i3][0] && green == d[i3][1] && blue == d[i3][2] && i2 == d[i3][3]) {
                this.mColorName.setText(getResources().getString(b[i3]));
                this.mColorName.setVisibility(0);
                this.mPen.setVisibility(4);
                break;
            }
            i3++;
        }
        if (!z) {
            this.mColorName.setVisibility(4);
            this.mPen.setVisibility(0);
        }
    }

    private void e() {
        String o = this.mDevice.getName();
        final ClientRemarkInputView clientRemarkInputView = (ClientRemarkInputView) getLayoutInflater().inflate(R.layout.client_remark_input_view, (ViewGroup) null);
        clientRemarkInputView.initialize(this, new MLAlertDialog.Builder(this).a((int) R.string.change_back_name).b((View) clientRemarkInputView).d(false).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                clientRemarkInputView.onConfirm(dialogInterface);
            }
        }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ((MLAlertDialog) dialogInterface).setAudoDismiss(true);
            }
        }).d(), o);
    }

    public void onNameChangeSuccess() {
        this.mPD.dismiss();
        if (TextUtils.isEmpty(this.mDevice.getName())) {
            ((TextView) findViewById(R.id.module_a_4_return_more_black_title)).setText(R.string.smart_bulb);
        } else {
            ((TextView) findViewById(R.id.module_a_4_return_more_black_title)).setText(this.mDevice.getName());
        }
    }

    public void onNameCHangeFailed() {
        this.mPD.dismiss();
        Toast.makeText(this, R.string.change_back_name_fail, 0).show();
    }
}
