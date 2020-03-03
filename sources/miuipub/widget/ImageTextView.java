package miuipub.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import java.util.HashMap;

public class ImageTextView extends LinearLayout {
    private static final int[] b = {16842930, 16843256};
    private static final int c = 0;
    private static final int d = 1;

    /* renamed from: a  reason: collision with root package name */
    private final LinearLayout.LayoutParams f3085a;
    private HashMap<Character, Integer> e;
    private CharSequence f;

    public ImageTextView(Context context) {
        this(context, (AttributeSet) null);
    }

    public ImageTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ImageTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f3085a = new LinearLayout.LayoutParams(-2, -2);
        setOrientation(0);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, b, i, 0);
        CharSequence[] textArray = obtainStyledAttributes.getTextArray(0);
        Integer[] a2 = a(obtainStyledAttributes);
        if (!(textArray == null || a2 == null || textArray.length != a2.length)) {
            this.e = new HashMap<>();
            for (int i2 = 0; i2 < textArray.length; i2++) {
                this.e.put(Character.valueOf(textArray[i2].charAt(0)), a2[i2]);
            }
        }
        obtainStyledAttributes.recycle();
    }

    private Integer[] a(TypedArray typedArray) {
        TypedValue typedValue = new TypedValue();
        if (!typedArray.getValue(1, typedValue)) {
            return null;
        }
        TypedArray obtainTypedArray = getContext().getResources().obtainTypedArray(typedValue.resourceId);
        Integer[] numArr = new Integer[obtainTypedArray.length()];
        for (int i = 0; i < obtainTypedArray.length(); i++) {
            numArr[i] = Integer.valueOf(obtainTypedArray.peekValue(i).resourceId);
        }
        return numArr;
    }

    public void setCharMap(HashMap<Character, Integer> hashMap) {
        this.e = hashMap;
    }

    public CharSequence getText() {
        return this.f == null ? "" : this.f;
    }

    public void setText(CharSequence charSequence) {
        View view;
        if (this.e != null && charSequence != null && !charSequence.equals(this.f)) {
            this.f = charSequence;
            for (int i = 0; i < charSequence.length(); i++) {
                if (i < getChildCount()) {
                    view = getChildAt(i);
                } else {
                    view = new ImageView(getContext());
                    view.setLayoutParams(this.f3085a);
                    addView(view);
                }
                Integer num = this.e.get(Character.valueOf(charSequence.charAt(i)));
                if (num != null) {
                    view.setBackgroundDrawable(getResources().getDrawable(num.intValue()));
                }
            }
            for (int childCount = getChildCount(); childCount > charSequence.length(); childCount--) {
                removeViewAt(childCount - 1);
            }
        }
    }
}
