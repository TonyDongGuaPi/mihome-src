package com.mi.global.shop.buy.payu;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.widget.DatePicker;
import com.mi.global.shop.R;
import com.mi.log.LogUtil;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Cards {

    /* renamed from: a  reason: collision with root package name */
    public static final String f6890a = "VISA";
    public static final String b = "LASER";
    public static final String c = "DISCOVER";
    public static final String d = "MAES";
    public static final String e = "MAST";
    public static final String f = "AMEX";
    public static final String g = "DINR";
    public static final String h = "JCB";
    public static final String i = "SMAE";
    public static final String j = "RUPAY";
    public static final String k = "BFL";
    public static HashMap<String, Drawable> l;
    public static Set<String> m = new HashSet();
    public static Set<String> n = new HashSet();

    public static void a(Resources resources) {
        l = new HashMap<>();
        l.put("VISA", resources.getDrawable(R.drawable.shop_visa));
        l.put("LASER", resources.getDrawable(R.drawable.shop_laser));
        l.put("DISCOVER", resources.getDrawable(R.drawable.shop_discover));
        l.put("MAES", resources.getDrawable(R.drawable.shop_maestro));
        l.put("MAST", resources.getDrawable(R.drawable.shop_master));
        l.put("AMEX", resources.getDrawable(R.drawable.shop_amex));
        l.put("DINR", resources.getDrawable(R.drawable.shop_diner));
        l.put("JCB", resources.getDrawable(R.drawable.shop_jcb));
        l.put("SMAE", resources.getDrawable(R.drawable.shop_maestro));
        l.put(j, resources.getDrawable(R.drawable.shop_rupay));
    }

    static {
        m.add("VISA");
        m.add("LASER");
        m.add("DISCOVER");
        m.add("MAES");
        m.add("MAST");
        m.add("AMEX");
        m.add("DINR");
        m.add("JCB");
        m.add("SMAE");
        m.add(j);
        m.add(k);
        n.add("504435");
        n.add("504645");
        n.add("504645");
        n.add("504775");
        n.add("504809");
        n.add("504993");
        n.add("600206");
        n.add("603845");
        n.add("622018");
    }

    public static Boolean a(String str) {
        if (str.length() < 12) {
            return false;
        }
        if (c(str).contentEquals(j) && str.length() == 16) {
            return b(str);
        }
        if (c(str).contentEquals("VISA") && str.length() == 16) {
            return b(str);
        }
        if (c(str).contentEquals("MAST") && str.length() == 16) {
            return b(str);
        }
        if ((c(str).contentEquals("MAES") || c(str).contentEquals("SMAE")) && str.length() >= 12 && str.length() <= 19) {
            return b(str);
        }
        if (c(str).contentEquals("DINR") && str.length() == 14) {
            return b(str);
        }
        if (c(str).contentEquals("AMEX") && str.length() == 15) {
            return b(str);
        }
        if (c(str).contentEquals("JCB") && str.length() == 16) {
            return b(str);
        }
        if (!c(str).contentEquals(k) || str.length() != 16) {
            return false;
        }
        return true;
    }

    public static Boolean b(String str) {
        boolean z = false;
        int i2 = 0;
        for (int length = str.length() - 1; length >= 0; length--) {
            int parseInt = Integer.parseInt(str.substring(length, length + 1));
            if (z && (parseInt = parseInt * 2) > 9) {
                parseInt = (parseInt % 10) + 1;
            }
            i2 += parseInt;
            z = !z;
        }
        if (i2 % 10 == 0) {
            return true;
        }
        return false;
    }

    public static DatePickerDialog a(Activity activity, DatePickerDialog.OnDateSetListener onDateSetListener, int i2, int i3, int i4) {
        int identifier;
        View findViewById;
        DatePickerDialog datePickerDialog = new DatePickerDialog(activity, onDateSetListener, i2, i3, i4);
        if (Build.VERSION.SDK_INT >= 11) {
            try {
                datePickerDialog.getDatePicker().getClass().getMethod("setCalendarViewShown", new Class[]{Boolean.TYPE}).invoke(datePickerDialog.getDatePicker(), new Object[]{false});
            } catch (Exception unused) {
            }
        }
        if (Build.VERSION.SDK_INT >= 11) {
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        }
        try {
            for (Field field : datePickerDialog.getClass().getDeclaredFields()) {
                if (field.getName().equals("mDatePicker")) {
                    field.setAccessible(true);
                    DatePicker datePicker = (DatePicker) field.get(datePickerDialog);
                    for (Field field2 : field.getType().getDeclaredFields()) {
                        if ("mDayPicker".equals(field2.getName()) || "mDaySpinner".equals(field2.getName()) || "DAY".equals(field.getName())) {
                            field2.setAccessible(true);
                            ((View) field2.get(datePicker)).setVisibility(8);
                        }
                    }
                }
            }
            if (!(Build.VERSION.SDK_INT < 21 || (identifier = Resources.getSystem().getIdentifier("mDayPicker", "id", "android")) == 0 || (findViewById = datePickerDialog.getDatePicker().findViewById(identifier)) == null)) {
                findViewById.setVisibility(8);
            }
            datePickerDialog.getDatePicker().setSpinnersShown(true);
            datePickerDialog.getDatePicker().setCalendarViewShown(false);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        datePickerDialog.setTitle((CharSequence) null);
        return datePickerDialog;
    }

    public static String c(String str) {
        if (str.startsWith("4")) {
            return "VISA";
        }
        if (str.length() <= 16 && str.matches("^508[5-9][0-9][0-9][\\d]+|60698[5-9][\\d]+|60699[0-9][\\d]+|607[0-8][0-9][0-9][\\d]+|6079[0-7][0-9][\\d]+|60798[0-4][\\d]+|(?!608000)608[0-4][0-9][0-9][\\d]+|608500[\\d]+|6521[5-9][0-9][\\d]+|652[2-9][0-9][0-9][\\d]+|6530[0-9][0-9][\\d]+|6531[0-4][0-9][\\d]+")) {
            LogUtil.b("RUPAY_MATCH", "match");
            return j;
        } else if (str.matches("^((6304)|(6706)|(6771)|(6709))[\\d]+") || str.matches("6(?:011|5[0-9]{2})[0-9]{12}[\\d]+")) {
            return "LASER";
        } else {
            if (str.matches("(5[06-8]|6\\d)\\d{14}(\\d{2,3})?[\\d]+") || str.matches("(5[06-8]|6\\d)[\\d]+") || str.matches("((504([435|645|774|775|809|993]))|(60([0206]|[3845]))|(622[018])\\d)[\\d]+")) {
                return (str.length() <= 6 || !n.contains(str.substring(0, 6))) ? "MAES" : "SMAE";
            }
            if (str.matches("^5[1-5][\\d]+")) {
                return "MAST";
            }
            if (str.matches("^3[47][\\d]+")) {
                return "AMEX";
            }
            if (str.startsWith("36") || str.matches("^30[0-5][\\d]+") || str.matches("2(014|149)[\\d]+")) {
                return "DINR";
            }
            if (str.matches("^35(2[89]|[3-8][0-9])[\\d]+")) {
                return "JCB";
            }
            return str.startsWith("203040") ? k : "";
        }
    }

    public static boolean a(String str, String str2) {
        String c2 = c(str);
        if (c2.contentEquals("SMAE") || c2.contentEquals(k)) {
            return true;
        }
        if (c2.contentEquals("AMEX") && str2.length() == 4) {
            return true;
        }
        if (c2.contentEquals("AMEX") || str2.length() != 3) {
            return false;
        }
        return true;
    }
}
