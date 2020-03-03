package com.xiaomi.smarthome.family;

import android.content.Context;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.widget.EditText;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.miio.db.record.FamilyRecord;

public class FamilyUtils {
    public static String a(Context context, String[] strArr, FamilyRecord familyRecord) {
        String str;
        boolean z;
        if (!TextUtils.isEmpty(familyRecord.relationship)) {
            str = familyRecord.relationship;
            z = true;
        } else {
            int intValue = Integer.valueOf(familyRecord.relation_id).intValue();
            if (intValue > 7) {
                intValue = 7;
            }
            str = strArr[Math.abs(intValue) - 1];
            z = false;
        }
        if (str.startsWith("1000_")) {
            str = str.substring("1000_".length());
            z = false;
        }
        if (Integer.valueOf(familyRecord.relation_id).intValue() >= 0 || z) {
            return str;
        }
        return String.format(context.getString(R.string.family_default_accept_name), new Object[]{str});
    }

    public static void a(EditText editText, String str) {
        if (editText != null && str != null) {
            editText.setText(str);
            a(editText);
        }
    }

    public static void a(EditText editText) {
        Editable text;
        if (editText != null && (text = editText.getText()) != null) {
            Selection.setSelection(text, text.length());
        }
    }
}
