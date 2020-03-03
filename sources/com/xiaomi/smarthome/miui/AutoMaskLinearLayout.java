package com.xiaomi.smarthome.miui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.util.Arrays;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.GridViewData;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;

public class AutoMaskLinearLayout extends LinearLayout {

    /* renamed from: a  reason: collision with root package name */
    private static final int f20083a = 300;
    private static final AccelerateDecelerateInterpolator i = new AccelerateDecelerateInterpolator();
    private int b = 0;
    private RecyclerView c;
    private boolean d = false;
    private long e = 0;
    private int[] f = new int[2];
    private boolean g = false;
    private Rect h = null;
    int[] location = new int[2];
    boolean mAlreadyFindLocation = false;
    Paint paint = new Paint(1);
    boolean showLoading = true;
    Paint textPaint = new Paint(1);

    public AutoMaskLinearLayout(Context context) {
        super(context);
        this.paint.setColor(getResources().getColor(R.color.white_30_transparent));
        this.textPaint.setTextSize((float) DisplayUtils.a(getContext(), 11.0f));
        this.textPaint.setColor(getResources().getColor(R.color.white_80_transparent));
        setWillNotDraw(false);
    }

    public AutoMaskLinearLayout(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.paint.setColor(getResources().getColor(R.color.white_30_transparent));
        this.textPaint.setTextSize((float) DisplayUtils.a(getContext(), 11.0f));
        this.textPaint.setColor(getResources().getColor(R.color.white_80_transparent));
        setWillNotDraw(false);
    }

    public AutoMaskLinearLayout(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.paint.setColor(getResources().getColor(R.color.white_30_transparent));
        this.textPaint.setTextSize((float) DisplayUtils.a(getContext(), 11.0f));
        this.textPaint.setColor(getResources().getColor(R.color.white_80_transparent));
        setWillNotDraw(false);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.showLoading) {
            canvas.save();
            canvas.drawRect(0.0f, 0.0f, (float) getWidth(), (float) getHeight(), this.paint);
            String string = getResources().getString(R.string.common_device_loading);
            canvas.drawText(string, (((float) getWidth()) - this.textPaint.measureText(string)) / 2.0f, (float) (getHeight() / 2), this.textPaint);
            canvas.restore();
        } else if (this.mAlreadyFindLocation) {
            canvas.save();
            int i2 = this.b > CommonDeviceEditAdapter.f20092a ? CommonDeviceEditAdapter.f20092a : this.b;
            String quantityString = getResources().getQuantityString(R.plurals.drag_tips, i2, new Object[]{Integer.valueOf(i2)});
            Home m = HomeManager.a().m();
            if (m != null && !m.p()) {
                quantityString = getResources().getString(R.string.share_home_edit_device_tips);
            }
            float width = (((float) getWidth()) - this.textPaint.measureText(quantityString)) / 2.0f;
            if (this.d) {
                long currentTimeMillis = System.currentTimeMillis();
                if (this.e + 300 < currentTimeMillis) {
                    this.d = false;
                    canvas.drawRect(0.0f, (float) this.location[1], (float) getWidth(), (float) getHeight(), this.paint);
                    if (this.c != null && ((float) this.location[0]) > this.c.getY()) {
                        canvas.drawText(quantityString, width, ((float) this.location[0]) + this.textPaint.getFontSpacing(), this.textPaint);
                    }
                } else {
                    float interpolation = i.getInterpolation(((float) (currentTimeMillis - this.e)) / 300.0f);
                    canvas.drawRect(0.0f, (float) ((int) (((float) this.f[1]) + (((float) (this.location[1] - this.f[1])) * interpolation))), (float) getWidth(), (float) getHeight(), this.paint);
                    if (this.c != null && ((float) this.location[0]) > this.c.getY()) {
                        canvas.drawText(quantityString, width, ((float) ((int) (((float) this.f[0]) + (((float) (this.location[0] - this.f[0])) * interpolation)))) + this.textPaint.getFontSpacing(), this.textPaint);
                    }
                    postInvalidate();
                }
            } else {
                canvas.drawRect(0.0f, (float) this.location[1], (float) getWidth(), (float) getHeight(), this.paint);
                if (this.c != null && ((float) this.location[0]) > this.c.getY()) {
                    canvas.drawText(quantityString, width, ((float) this.location[0]) + this.textPaint.getFontSpacing(), this.textPaint);
                }
            }
            if (this.g) {
                Drawable drawable = getResources().getDrawable(R.drawable.common_item_camera_pos_bg);
                drawable.setBounds(this.h);
                drawable.draw(canvas);
            }
            canvas.restore();
        } else {
            canvas.save();
            canvas.drawRect(0.0f, (float) this.location[1], (float) getWidth(), (float) getHeight(), this.paint);
            canvas.restore();
            this.f = Arrays.c(this.location, 2);
            for (int i3 = 0; i3 < getChildCount(); i3++) {
                if (getChildAt(i3) instanceof RecyclerView) {
                    this.c = (RecyclerView) getChildAt(i3);
                    RecyclerView.LayoutManager layoutManager = this.c.getLayoutManager();
                    int i4 = 0;
                    while (i4 < layoutManager.getChildCount()) {
                        View childAt = layoutManager.getChildAt(i4);
                        if (childAt == null || layoutManager.getItemViewType(childAt) != GridViewData.GridType.TYPE_TIPS.ordinal()) {
                            i4++;
                        } else {
                            this.location[0] = ((int) (this.c.getY() + ((float) childAt.getTop()))) + DisplayUtils.d(getContext(), 8.0f);
                            this.location[1] = ((int) (this.c.getY() + ((float) childAt.getBottom()))) - DisplayUtils.d(getContext(), 7.0f);
                            if (this.d) {
                                this.e = System.currentTimeMillis();
                            }
                            invalidate();
                            this.mAlreadyFindLocation = true;
                            return;
                        }
                    }
                    return;
                }
            }
        }
    }

    public void setScrolledY(int i2) {
        int[] iArr = this.location;
        iArr[0] = iArr[0] + i2;
        int[] iArr2 = this.location;
        iArr2[1] = iArr2[1] + i2;
    }

    public void setDeviceCount(int i2) {
        if (this.b != i2) {
            this.mAlreadyFindLocation = false;
        }
        this.b = i2;
    }

    public void startDrawDash(Rect rect) {
        this.h = rect;
        this.g = true;
        postInvalidate();
    }

    public void stopDrawDash() {
        this.g = false;
        postInvalidate();
    }

    public void setRearrangeView() {
        this.mAlreadyFindLocation = false;
        this.d = true;
        postInvalidate();
    }

    public void setShowLoading(boolean z) {
        this.showLoading = z;
    }
}
