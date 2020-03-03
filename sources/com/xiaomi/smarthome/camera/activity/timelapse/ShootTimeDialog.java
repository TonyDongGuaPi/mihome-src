package com.xiaomi.smarthome.camera.activity.timelapse;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.amap.location.common.model.AmapLoc;
import com.mi.global.shop.model.Tags;
import com.unionpay.tsmservice.data.AppStatus;
import com.unionpay.tsmservice.data.Constant;
import com.wx.wheelview.widget.WheelView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.api.UserConfig;
import java.util.Arrays;

public abstract class ShootTimeDialog extends BottomBaseDialog {
    static final String TAG = "ShootTimeDialog";

    public interface CancelListener {
        void onCancel(ShootTimeDialog shootTimeDialog);
    }

    public interface ConfirmListener {
        void onConfirm(ShootTimeDialog shootTimeDialog, String str, String str2);
    }

    public ShootTimeDialog(Context context) {
        super(context);
    }

    public ShootTimeDialog(Context context, int i) {
        super(context, i);
    }

    public ShootTimeDialog(Context context, boolean z, DialogInterface.OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
    }

    public static class Builder {
        CancelListener cancelListener;
        ConfirmListener confirmListener;
        Context context;
        ShootTimeDialog dialog;
        String[] hour = null;
        String hourTime;
        boolean isCancelable;
        String[] min = null;
        String minTime;
        String startTimeHour;
        String startTimeMin;
        String title;

        public Builder(Context context2, boolean z, String str, String str2) {
            this.context = context2;
            this.isCancelable = z;
            this.startTimeHour = str;
            this.startTimeMin = str2;
        }

        public String getTitle() {
            return this.title;
        }

        public Builder setTitle(String str) {
            this.title = str;
            return this;
        }

        public Builder setConfirmListener(ConfirmListener confirmListener2) {
            this.confirmListener = confirmListener2;
            return this;
        }

        public Builder setCancelListener(CancelListener cancelListener2) {
            this.cancelListener = cancelListener2;
            return this;
        }

        public ShootTimeDialog build() {
            this.dialog = new ShootTimeDialog(this.context, this.isCancelable, (DialogInterface.OnCancelListener) null) {
                public View onCreateView() {
                    Builder builder = Builder.this;
                    int i = 0;
                    builder.hour = new String[]{"00", "01", "02", Constant.RECHARGE_MODE_BUSINESS_OFFICE, Constant.RECHARGE_MODE_DESIGNATED_AND_CACH, AppStatus.OPEN, AppStatus.APPLY, AppStatus.VIEW, "08", "09", "10", "11", "12", "13", AmapLoc.u, "15", Tags.Phone.M22S_PHONE, "17", "18", "19", UserConfig.g, "21", "22", "23"};
                    Builder builder2 = Builder.this;
                    builder2.min = new String[]{"00", "01", "02", Constant.RECHARGE_MODE_BUSINESS_OFFICE, Constant.RECHARGE_MODE_DESIGNATED_AND_CACH, AppStatus.OPEN, AppStatus.APPLY, AppStatus.VIEW, "08", "09", "10", "11", "12", "13", AmapLoc.u, "15", Tags.Phone.M22S_PHONE, "17", "18", "19", UserConfig.g, "21", "22", "23", AmapLoc.w, "25", "26", "27", "28", "29", "30", "31", Tags.Phone.M2A_PHONE, "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};
                    View inflate = LayoutInflater.from(Builder.this.context).inflate(R.layout.layout_shoot_time, (ViewGroup) null);
                    ((TextView) inflate.findViewById(R.id.tv_tips_title)).setText(Builder.this.title);
                    ((TextView) inflate.findViewById(R.id.tv_cancel)).setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            if (Builder.this.cancelListener != null) {
                                Builder.this.cancelListener.onCancel(this);
                            }
                            this.dismiss();
                        }
                    });
                    ((TextView) inflate.findViewById(R.id.tv_ok)).setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            if (Builder.this.confirmListener != null) {
                                Builder.this.confirmListener.onConfirm(this, Builder.this.hourTime, Builder.this.minTime);
                            }
                            this.dismiss();
                        }
                    });
                    WheelView wheelView = (WheelView) inflate.findViewById(R.id.wv_hour);
                    WheelView wheelView2 = (WheelView) inflate.findViewById(R.id.wv_min);
                    wheelView.setWheelAdapter(new TimeWheelAdapter(Builder.this.context));
                    wheelView2.setWheelAdapter(new TimeWheelAdapter(Builder.this.context));
                    wheelView.setWheelData(Arrays.asList(Builder.this.hour));
                    wheelView2.setWheelData(Arrays.asList(Builder.this.min));
                    wheelView.setWheelSize(5);
                    wheelView2.setWheelSize(5);
                    wheelView.setLoop(true);
                    wheelView2.setLoop(true);
                    wheelView.setSkin(WheelView.Skin.None);
                    wheelView2.setSkin(WheelView.Skin.None);
                    WheelView.WheelViewStyle wheelViewStyle = new WheelView.WheelViewStyle();
                    wheelViewStyle.d = Color.parseColor("#0bb58b");
                    wheelView.setStyle(wheelViewStyle);
                    wheelView2.setStyle(wheelViewStyle);
                    int i2 = 0;
                    while (true) {
                        if (i2 >= Builder.this.hour.length) {
                            i2 = 0;
                            break;
                        } else if (Builder.this.hour[i2].equals(Builder.this.startTimeHour)) {
                            break;
                        } else {
                            i2++;
                        }
                    }
                    int i3 = 0;
                    while (true) {
                        if (i3 >= Builder.this.min.length) {
                            break;
                        } else if (Builder.this.min[i3].equals(Builder.this.startTimeMin)) {
                            i = i3;
                            break;
                        } else {
                            i3++;
                        }
                    }
                    wheelView.setSelection(i2);
                    wheelView2.setSelection(i);
                    wheelView.setOnWheelItemSelectedListener(new WheelView.OnWheelItemSelectedListener() {
                        public void onItemSelected(int i, Object obj) {
                            Builder.this.hourTime = Builder.this.hour[i];
                            Log.i(ShootTimeDialog.TAG, "==========hour=========" + i + "=============hourTime=========" + Builder.this.hourTime);
                        }
                    });
                    wheelView2.setOnWheelItemSelectedListener(new WheelView.OnWheelItemSelectedListener() {
                        public void onItemSelected(int i, Object obj) {
                            Builder.this.minTime = Builder.this.min[i];
                            Log.i(ShootTimeDialog.TAG, "=============min==========" + i + "============minTime===" + Builder.this.minTime);
                        }
                    });
                    return inflate;
                }
            };
            return this.dialog;
        }
    }
}
