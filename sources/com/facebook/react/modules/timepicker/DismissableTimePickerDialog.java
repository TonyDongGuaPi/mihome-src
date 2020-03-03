package com.facebook.react.modules.timepicker;

import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;

public class DismissableTimePickerDialog extends TimePickerDialog {
    public DismissableTimePickerDialog(Context context, @Nullable TimePickerDialog.OnTimeSetListener onTimeSetListener, int i, int i2, boolean z) {
        super(context, onTimeSetListener, i, i2, z);
    }

    public DismissableTimePickerDialog(Context context, int i, @Nullable TimePickerDialog.OnTimeSetListener onTimeSetListener, int i2, int i3, boolean z) {
        super(context, i, onTimeSetListener, i2, i3, z);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        if (Build.VERSION.SDK_INT > 19) {
            super.onStop();
        }
    }
}
