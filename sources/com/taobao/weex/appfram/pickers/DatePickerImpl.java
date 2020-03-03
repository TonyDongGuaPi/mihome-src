package com.taobao.weex.appfram.pickers;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import com.taobao.weex.common.WXThread;
import com.taobao.weex.ui.module.WXModalUIModule;
import com.taobao.weex.utils.WXLogUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class DatePickerImpl {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    private static final int DEFAULT_END_YEAR = 2100;
    private static final int DEFAULT_START_YEAR = 1900;
    private static SimpleDateFormat dateFormatter;
    private static SimpleDateFormat timeFormatter;

    public interface OnPickListener {
        void onPick(boolean z, @Nullable String str);
    }

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(2143262161311888433L, "com/taobao/weex/appfram/pickers/DatePickerImpl", 69);
        $jacocoData = a2;
        return a2;
    }

    public DatePickerImpl() {
        $jacocoInit()[0] = true;
    }

    public static void pickDate(@NonNull Context context, String str, String str2, String str3, @NonNull final OnPickListener onPickListener, @Nullable Map<String, Object> map) {
        Object obj;
        boolean[] $jacocoInit = $jacocoInit();
        Calendar instance = Calendar.getInstance();
        $jacocoInit[1] = true;
        instance.setTime(parseDate(str));
        $jacocoInit[2] = true;
        AnonymousClass1 r5 = new DatePickerDialog.OnDateSetListener() {
            private static transient /* synthetic */ boolean[] $jacocoData;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(5322970198513266270L, "com/taobao/weex/appfram/pickers/DatePickerImpl$1", 10);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                $jacocoInit[0] = true;
            }

            public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                String str;
                String str2;
                boolean[] $jacocoInit = $jacocoInit();
                int i4 = i2 + 1;
                $jacocoInit[1] = true;
                if (i4 < 10) {
                    str = "0" + i4;
                    $jacocoInit[2] = true;
                } else {
                    str = String.valueOf(i4);
                    $jacocoInit[3] = true;
                }
                $jacocoInit[4] = true;
                if (i3 < 10) {
                    str2 = "0" + i3;
                    $jacocoInit[5] = true;
                } else {
                    str2 = String.valueOf(i3);
                    $jacocoInit[6] = true;
                }
                $jacocoInit[7] = true;
                $jacocoInit[8] = true;
                onPickListener.onPick(true, i + "-" + str + "-" + str2);
                $jacocoInit[9] = true;
            }
        };
        $jacocoInit[3] = true;
        int i = instance.get(1);
        $jacocoInit[4] = true;
        int i2 = instance.get(2);
        $jacocoInit[5] = true;
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, r5, i, i2, instance.get(5));
        $jacocoInit[6] = true;
        DatePicker datePicker = datePickerDialog.getDatePicker();
        $jacocoInit[7] = true;
        Calendar instance2 = Calendar.getInstance(Locale.getDefault());
        $jacocoInit[8] = true;
        Calendar instance3 = Calendar.getInstance(Locale.getDefault());
        $jacocoInit[9] = true;
        instance2.set(1900, 0, 1);
        $jacocoInit[10] = true;
        instance3.set(2100, 11, 31);
        $jacocoInit[11] = true;
        if (TextUtils.isEmpty(str3)) {
            $jacocoInit[12] = true;
        } else {
            $jacocoInit[13] = true;
            long time = parseDate(str3).getTime();
            $jacocoInit[14] = true;
            if (datePicker.getMaxDate() >= time) {
                $jacocoInit[15] = true;
                datePicker.setMinDate(parseDate(str3).getTime());
                $jacocoInit[16] = true;
            } else {
                datePicker.setMinDate(instance2.getTimeInMillis());
                $jacocoInit[17] = true;
                datePicker.setMaxDate(instance3.getTimeInMillis());
                $jacocoInit[18] = true;
            }
        }
        if (TextUtils.isEmpty(str2)) {
            $jacocoInit[19] = true;
        } else {
            $jacocoInit[20] = true;
            long time2 = parseDate(str2).getTime();
            $jacocoInit[21] = true;
            if (datePicker.getMinDate() <= time2) {
                $jacocoInit[22] = true;
                datePicker.setMaxDate(parseDate(str2).getTime());
                $jacocoInit[23] = true;
            } else {
                datePicker.setMinDate(instance2.getTimeInMillis());
                $jacocoInit[24] = true;
                datePicker.setMaxDate(instance3.getTimeInMillis());
                $jacocoInit[25] = true;
            }
        }
        datePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            private static transient /* synthetic */ boolean[] $jacocoData;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(-1108678276812108629L, "com/taobao/weex/appfram/pickers/DatePickerImpl$2", 2);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                $jacocoInit[0] = true;
            }

            public void onCancel(DialogInterface dialogInterface) {
                boolean[] $jacocoInit = $jacocoInit();
                onPickListener.onPick(false, (String) null);
                $jacocoInit[1] = true;
            }
        });
        $jacocoInit[26] = true;
        Object obj2 = null;
        if (map != null) {
            obj = map.get(WXModalUIModule.CANCEL_TITLE);
            $jacocoInit[27] = true;
        } else {
            $jacocoInit[28] = true;
            obj = null;
        }
        setButtonText(datePickerDialog, -2, String.valueOf(obj));
        $jacocoInit[29] = true;
        if (map != null) {
            obj2 = map.get("confirmTitle");
            $jacocoInit[30] = true;
        } else {
            $jacocoInit[31] = true;
        }
        setButtonText(datePickerDialog, -1, String.valueOf(obj2));
        $jacocoInit[32] = true;
        datePickerDialog.show();
        $jacocoInit[33] = true;
    }

    public static void pickTime(@NonNull Context context, String str, @NonNull final OnPickListener onPickListener, @Nullable Map<String, Object> map) {
        Object obj;
        boolean[] $jacocoInit = $jacocoInit();
        Calendar instance = Calendar.getInstance();
        $jacocoInit[34] = true;
        instance.setTime(parseTime(str));
        $jacocoInit[35] = true;
        AnonymousClass3 r5 = new TimePickerDialog.OnTimeSetListener() {
            private static transient /* synthetic */ boolean[] $jacocoData;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(6726297179214090990L, "com/taobao/weex/appfram/pickers/DatePickerImpl$3", 9);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                $jacocoInit[0] = true;
            }

            public void onTimeSet(TimePicker timePicker, int i, int i2) {
                String str;
                String str2;
                boolean[] $jacocoInit = $jacocoInit();
                if (i < 10) {
                    str = "0" + i;
                    $jacocoInit[1] = true;
                } else {
                    str = String.valueOf(i);
                    $jacocoInit[2] = true;
                }
                $jacocoInit[3] = true;
                if (i2 < 10) {
                    str2 = "0" + i2;
                    $jacocoInit[4] = true;
                } else {
                    str2 = String.valueOf(i2);
                    $jacocoInit[5] = true;
                }
                $jacocoInit[6] = true;
                $jacocoInit[7] = true;
                onPickListener.onPick(true, str + ":" + str2);
                $jacocoInit[8] = true;
            }
        };
        $jacocoInit[36] = true;
        int i = instance.get(11);
        $jacocoInit[37] = true;
        TimePickerDialog timePickerDialog = new TimePickerDialog(context, r5, i, instance.get(12), false);
        $jacocoInit[38] = true;
        timePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            private static transient /* synthetic */ boolean[] $jacocoData;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(6410665932708661768L, "com/taobao/weex/appfram/pickers/DatePickerImpl$4", 2);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                $jacocoInit[0] = true;
            }

            public void onCancel(DialogInterface dialogInterface) {
                boolean[] $jacocoInit = $jacocoInit();
                onPickListener.onPick(false, (String) null);
                $jacocoInit[1] = true;
            }
        });
        $jacocoInit[39] = true;
        Object obj2 = null;
        if (map != null) {
            obj = map.get(WXModalUIModule.CANCEL_TITLE);
            $jacocoInit[40] = true;
        } else {
            $jacocoInit[41] = true;
            obj = null;
        }
        setButtonText(timePickerDialog, -2, String.valueOf(obj));
        $jacocoInit[42] = true;
        if (map != null) {
            obj2 = map.get("confirmTitle");
            $jacocoInit[43] = true;
        } else {
            $jacocoInit[44] = true;
        }
        setButtonText(timePickerDialog, -1, String.valueOf(obj2));
        $jacocoInit[45] = true;
        timePickerDialog.show();
        $jacocoInit[46] = true;
    }

    private static Date parseDate(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (dateFormatter != null) {
            $jacocoInit[47] = true;
        } else {
            $jacocoInit[48] = true;
            dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            try {
                $jacocoInit[49] = true;
            } catch (ParseException e) {
                $jacocoInit[51] = true;
                WXLogUtils.w("[DatePickerImpl] " + e.toString());
                $jacocoInit[52] = true;
                Date date = new Date();
                $jacocoInit[53] = true;
                return date;
            }
        }
        Date parse = dateFormatter.parse(str);
        $jacocoInit[50] = true;
        return parse;
    }

    private static Date parseTime(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (timeFormatter != null) {
            $jacocoInit[54] = true;
        } else {
            $jacocoInit[55] = true;
            timeFormatter = new SimpleDateFormat("HH:mm", Locale.getDefault());
            try {
                $jacocoInit[56] = true;
            } catch (ParseException e) {
                $jacocoInit[58] = true;
                WXLogUtils.w("[DatePickerImpl] " + e.toString());
                $jacocoInit[59] = true;
                Date date = new Date();
                $jacocoInit[60] = true;
                return date;
            }
        }
        Date parse = timeFormatter.parse(str);
        $jacocoInit[57] = true;
        return parse;
    }

    private static void setButtonText(final AlertDialog alertDialog, final int i, final CharSequence charSequence) {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(charSequence)) {
            $jacocoInit[61] = true;
        } else if (!"null".equals(charSequence)) {
            $jacocoInit[62] = true;
            try {
                alertDialog.getWindow().getDecorView().post(WXThread.secure((Runnable) new Runnable() {
                    private static transient /* synthetic */ boolean[] $jacocoData;

                    private static /* synthetic */ boolean[] $jacocoInit() {
                        boolean[] zArr = $jacocoData;
                        if (zArr != null) {
                            return zArr;
                        }
                        boolean[] a2 = Offline.a(8502092723257996013L, "com/taobao/weex/appfram/pickers/DatePickerImpl$5", 6);
                        $jacocoData = a2;
                        return a2;
                    }

                    {
                        boolean[] $jacocoInit = $jacocoInit();
                        $jacocoInit[0] = true;
                    }

                    public void run() {
                        boolean[] $jacocoInit = $jacocoInit();
                        Button button = alertDialog.getButton(i);
                        if (button == null) {
                            $jacocoInit[1] = true;
                        } else {
                            $jacocoInit[2] = true;
                            button.setAllCaps(false);
                            $jacocoInit[3] = true;
                            button.setText(charSequence);
                            $jacocoInit[4] = true;
                        }
                        $jacocoInit[5] = true;
                    }
                }));
                $jacocoInit[65] = true;
            } catch (Throwable th) {
                $jacocoInit[66] = true;
                th.printStackTrace();
                $jacocoInit[67] = true;
            }
            $jacocoInit[68] = true;
            return;
        } else {
            $jacocoInit[63] = true;
        }
        $jacocoInit[64] = true;
    }
}
