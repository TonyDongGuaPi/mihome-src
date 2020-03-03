package com.xiaomi.smarthome.newui.card;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.newui.card.Card;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class CardItemUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final Locale f20475a = Locale.ENGLISH;

    public static void a(ArrayList<? extends Card.CardType> arrayList, View view, int i) {
        int i2;
        int size = arrayList.size();
        int a2 = a(arrayList);
        int a3 = DisplayUtils.a(20.0f) / Math.max(a2, 1);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
            return;
        }
        if (a2 == size) {
            boolean e = ((Card.CardType) arrayList.get(i)).e();
            int i3 = 0;
            if (e) {
                i2 = 0;
            } else {
                i2 = a3 / 2;
            }
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            if (i != 0) {
                i3 = i2;
            } else if (!e || size <= 1) {
                i3 = a3;
            }
            marginLayoutParams.leftMargin = i3;
            if (i != size - 1) {
                a3 = i2;
            }
            marginLayoutParams.rightMargin = a3;
            return;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) layoutParams;
        marginLayoutParams2.leftMargin = a3;
        marginLayoutParams2.rightMargin = a3;
    }

    public static void a(Card card, TextView textView, TextView textView2, TextView textView3) {
        float f;
        if (card.b == 4) {
            f = 30.0f;
        } else {
            f = card.b == 2 ? 33.0f : 37.0f;
        }
        float f2 = f / 3.0f;
        if (textView2 != null) {
            textView2.setTextSize(1, f);
        }
        if (textView != null) {
            textView.setTextSize(1, f2);
        }
        if (textView3 != null) {
            textView3.setTextSize(1, f2);
        }
    }

    public static int a(ArrayList<? extends Card.CardType> arrayList) {
        int i = 0;
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            if (((Card.CardType) arrayList.get(i2)).b()) {
                i++;
            }
        }
        return i;
    }

    public static long a(Map<String, Long> map, String str) {
        Long l;
        if (map == null || (l = map.get(str)) == null) {
            return 0;
        }
        if (String.valueOf(l).length() > 10) {
            l = Long.valueOf(l.longValue() / 1000);
        }
        return l.longValue();
    }

    public static void a(int i, int i2, List<? extends Card.CardType> list, TextView textView, ImageView imageView) {
        int size = list.size();
        if (i2 == 0 && size == 1) {
            imageView.getLayoutParams().width = DisplayUtils.d(imageView.getContext(), 73.33f);
            imageView.getLayoutParams().height = DisplayUtils.d(imageView.getContext(), 73.33f);
            textView.setVisibility(8);
        } else if (i2 == 1 && size == 2 && i == 0) {
            imageView.getLayoutParams().width = DisplayUtils.d(imageView.getContext(), 43.33f);
            imageView.getLayoutParams().height = DisplayUtils.d(imageView.getContext(), 43.33f);
            textView.setVisibility(8);
        } else if (i2 == 6) {
            imageView.getLayoutParams().width = DisplayUtils.d(imageView.getContext(), 43.33f);
            imageView.getLayoutParams().height = DisplayUtils.d(imageView.getContext(), 43.33f);
            textView.setVisibility(8);
        } else if (i2 == 3 && size == 3) {
            Card.CardType cardType = (Card.CardType) list.get(2);
            if (i == 1 && cardType.d() && cardType.b != 4) {
                imageView.getLayoutParams().width = DisplayUtils.d(imageView.getContext(), 43.33f);
                imageView.getLayoutParams().height = DisplayUtils.d(imageView.getContext(), 43.33f);
                textView.setVisibility(8);
            }
        }
    }

    public static String a(String str) {
        return str == null ? "" : str.replaceAll("\\s", "").replace("@3x", "").toLowerCase(f20475a);
    }

    public static boolean a(long j) {
        return j != 0 && System.currentTimeMillis() / 1000 > j + 43200;
    }
}
