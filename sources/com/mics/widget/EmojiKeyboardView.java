package com.mics.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.media.AudioAttributes;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.drew.metadata.iptc.IptcDirectory;
import com.mics.R;
import com.mics.core.MiCS;
import com.xiaomi.smarthome.core.entity.Error;
import java.util.List;

public class EmojiKeyboardView extends RelativeLayout implements View.OnClickListener, View.OnTouchListener {
    private static final int g = 0;

    /* renamed from: a  reason: collision with root package name */
    private Context f7809a;
    private int b;
    private int c;
    private int d;
    private OnEmojiItemClick e;
    private List<Integer> f;
    private int h;

    public interface OnEmojiItemClick {
        void a(EmojiKeyboardView emojiKeyboardView, View view, int i, boolean z);
    }

    public EmojiKeyboardView(Context context) {
        this(context, (AttributeSet) null);
    }

    public EmojiKeyboardView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public EmojiKeyboardView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.h = 0;
        this.f7809a = context;
        this.d = (int) TypedValue.applyDimension(1, 11.0f, getResources().getDisplayMetrics());
        a(attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int b2 = b(i);
        int a2 = a(i2);
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(i, 1073741824), View.MeasureSpec.makeMeasureSpec(a2, 1073741824));
        setMeasuredDimension(b2, a2);
        a();
    }

    private int a(int i) {
        int mode = View.MeasureSpec.getMode(i);
        return (mode == Integer.MIN_VALUE || mode == 0) ? (int) TypedValue.applyDimension(1, 240.0f, getResources().getDisplayMetrics()) : View.MeasureSpec.getSize(i);
    }

    private int b(int i) {
        return View.MeasureSpec.getSize(i);
    }

    private void a(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.EmojiKeyboardView);
            this.b = obtainStyledAttributes.getInt(R.styleable.EmojiKeyboardView_row, 4);
            this.c = obtainStyledAttributes.getInt(R.styleable.EmojiKeyboardView_column, 7);
            obtainStyledAttributes.recycle();
        }
        set(this.b, this.c);
    }

    public void set(int i, int i2) {
        if (i <= 0) {
            i = 4;
        }
        this.b = i;
        if (i2 <= 0) {
            i2 = 6;
        }
        this.c = i2;
        int i3 = 9;
        if (this.c <= 9) {
            i3 = this.c;
        }
        this.c = i3;
        b();
    }

    private void a() {
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (measuredWidth != 0) {
            float f2 = (float) measuredWidth;
            this.d = (int) (((f2 - (TypedValue.applyDimension(1, 24.0f, getResources().getDisplayMetrics()) * ((float) this.c))) / ((float) (this.c + 1))) / 2.0f);
            int i = (int) ((f2 - (((float) this.d) * 2.0f)) / ((float) this.c));
            int i2 = (int) (((float) i) * 1.1f);
            setPadding(this.d, (int) (((float) (measuredHeight - (this.b * i2))) / 2.0f), this.d, getPaddingBottom());
            int childCount = getChildCount();
            if (childCount > 0) {
                for (int i3 = 0; i3 < childCount; i3++) {
                    ImageView imageView = (ImageView) getChildAt(i3);
                    imageView.setPadding(this.d, imageView.getPaddingTop(), this.d, imageView.getPaddingBottom());
                    imageView.getLayoutParams().width = i;
                    imageView.getLayoutParams().height = i2;
                }
            }
        }
    }

    private void b() {
        removeAllViews();
        for (int i = 0; i < this.b; i++) {
            for (int i2 = 0; i2 < this.c; i2++) {
                ImageView imageView = new ImageView(this.f7809a);
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                int i3 = (i * 10) + IptcDirectory.au;
                imageView.setId(i3 + i2);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(60, 60);
                if (i > 0) {
                    layoutParams.addRule(3, ((i - 1) * 10) + IptcDirectory.au + i2);
                }
                if (i2 > 0) {
                    layoutParams.addRule(1, i3 + (i2 - 1));
                }
                addView(imageView, layoutParams);
            }
        }
    }

    public void setResIds(List<Integer> list) {
        setResIds(list, 0);
    }

    public void setResIds(List<Integer> list, int i) {
        if (list.size() <= (this.b * this.c) - 1) {
            this.h = i;
            this.f = list;
            c();
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("设置的图素数量");
        sb.append(list.size());
        sb.append("已超过键盘容量");
        sb.append((this.b * this.c) - 1);
        sb.append("！");
        throw new ArrayIndexOutOfBoundsException(sb.toString());
    }

    private void c() {
        int childCount = getChildCount();
        if (this.f != null && childCount > 0) {
            int size = this.f.size();
            for (int i = 0; i < size; i++) {
                ImageView imageView = (ImageView) getChildAt(i);
                imageView.setImageResource(this.f.get(i).intValue());
                imageView.setOnTouchListener(this);
                imageView.setOnClickListener(this);
            }
        }
        if (this.h > 0 && childCount > 0) {
            ImageView imageView2 = (ImageView) getChildAt(childCount - 1);
            imageView2.setImageResource(this.h);
            imageView2.setOnTouchListener(this);
            imageView2.setOnClickListener(this);
        }
    }

    public void onClick(View view) {
        int id = view.getId() + Error.f;
        int i = ((id / 10) * this.c) + (id % 10);
        if (this.e != null) {
            OnEmojiItemClick onEmojiItemClick = this.e;
            boolean z = true;
            if (i != (this.b * this.c) - 1) {
                z = false;
            }
            onEmojiItemClick.a(this, view, i, z);
        }
    }

    public void setOnEmojiItemClick(OnEmojiItemClick onEmojiItemClick) {
        this.e = onEmojiItemClick;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() != 0 || !(view instanceof ImageView)) {
            return false;
        }
        a(6);
        return false;
    }

    private void a(long j) {
        Vibrator vibrator;
        if (MiCS.a().h() != null && MiCS.a().c() && (vibrator = (Vibrator) MiCS.a().h().getSystemService("vibrator")) != null && Build.VERSION.SDK_INT >= 26) {
            VibrationEffect createOneShot = VibrationEffect.createOneShot(j, 255);
            AudioAttributes.Builder builder = new AudioAttributes.Builder();
            builder.setUsage(5);
            builder.setContentType(4);
            vibrator.vibrate(createOneShot, builder.build());
        }
    }
}
