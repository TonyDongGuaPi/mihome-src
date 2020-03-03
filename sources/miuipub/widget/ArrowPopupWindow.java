package miuipub.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import com.miuipub.internal.widget.ArrowPopupView;
import miuipub.util.AttributeResolver;
import miuipub.v6.R;

public class ArrowPopupWindow extends PopupWindow {

    /* renamed from: a  reason: collision with root package name */
    public static final int f3067a = 1;
    public static final int b = 0;
    public static final int c = 3;
    public static final int d = 2;
    protected ArrowPopupView e;
    private Context f;
    private int g;

    /* access modifiers changed from: protected */
    public void c() {
    }

    public ArrowPopupWindow(Context context) {
        this(context, (AttributeSet) null);
    }

    public ArrowPopupWindow(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842870);
    }

    public ArrowPopupWindow(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public ArrowPopupWindow(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.f = context;
        h();
    }

    public Context a() {
        return this.f;
    }

    private void h() {
        this.g = this.f.getResources().getDimensionPixelOffset(R.dimen.v6_arrow_popup_window_list_max_height);
        this.e = (ArrowPopupView) b().inflate(R.layout.v6_arrow_popup_view, (ViewGroup) null, false);
        super.setContentView(this.e);
        super.setWidth(-1);
        super.setHeight(-1);
        setSoftInputMode(3);
        this.e.setArrowPopupWindow(this);
        super.setTouchInterceptor(g());
        c();
        update();
    }

    /* access modifiers changed from: protected */
    public LayoutInflater b() {
        return LayoutInflater.from(this.f);
    }

    public final void setContentView(View view) {
        this.e.setContentView(view);
    }

    public final void a(View view, ViewGroup.LayoutParams layoutParams) {
        this.e.setContentView(view, layoutParams);
    }

    public View getContentView() {
        return this.e.getContentView();
    }

    public final void a(int i) {
        this.e.setContentView(i);
    }

    public int d() {
        return this.e.getArrowMode();
    }

    public void b(int i) {
        this.e.setArrowMode(i);
    }

    public void a(View view, int i, int i2) {
        a(view);
        this.e.setAnchor(view);
        this.e.setOffset(i, i2);
        showAtLocation(view, 51, 0, 0);
        this.e.animateToShow();
    }

    private boolean i() {
        if (a().getResources().getInteger(R.integer.v6_window_translucent_status) == 0 || AttributeResolver.a(a(), R.attr.v6_windowTranslucentStatus, 0) == 0) {
            return false;
        }
        return true;
    }

    public void showAsDropDown(View view, int i, int i2) {
        a(view, i, i2);
    }

    private void a(View view) {
        View rootView = view.getRootView();
        if (rootView != null && (rootView.getLayoutParams() instanceof WindowManager.LayoutParams) && ((WindowManager.LayoutParams) rootView.getLayoutParams()).type != 1) {
            this.e.setBackgroundColor(0);
        }
    }

    public void a(boolean z) {
        if (z) {
            this.e.animateToDismiss();
        } else {
            dismiss();
        }
    }

    public void showAsDropDown(View view, int i, int i2, int i3) {
        a(view, i, i2);
    }

    public void update(int i, int i2, int i3, int i4, boolean z) {
        super.update(0, 0, -1, -1, z);
    }

    public void setTouchInterceptor(View.OnTouchListener onTouchListener) {
        this.e.setTouchInterceptor(onTouchListener);
    }

    public void a(CharSequence charSequence, View.OnClickListener onClickListener) {
        this.e.setPositiveButton(charSequence, onClickListener);
    }

    public int getWidth() {
        View contentView = getContentView();
        if (contentView != null) {
            return contentView.getWidth();
        }
        return 0;
    }

    public void setWidth(int i) {
        View contentView = getContentView();
        if (contentView != null) {
            ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
            layoutParams.width = i;
            contentView.setLayoutParams(layoutParams);
        }
    }

    public int getHeight() {
        View contentView = getContentView();
        if (contentView != null) {
            return contentView.getHeight();
        }
        return 0;
    }

    public void setHeight(int i) {
        View contentView = getContentView();
        if ((contentView instanceof ListView) && i > this.g) {
            i = this.g;
        }
        if (contentView != null) {
            ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
            layoutParams.height = i;
            contentView.setLayoutParams(layoutParams);
        }
    }

    public void a(int i, View.OnClickListener onClickListener) {
        a((CharSequence) this.f.getString(i), onClickListener);
    }

    public Button e() {
        return this.e.getPositiveButton();
    }

    public void b(CharSequence charSequence, View.OnClickListener onClickListener) {
        this.e.setNegativeButton(charSequence, onClickListener);
    }

    public void b(int i, View.OnClickListener onClickListener) {
        b((CharSequence) this.f.getString(i), onClickListener);
    }

    public Button f() {
        return this.e.getNegativeButton();
    }

    public void a(CharSequence charSequence) {
        this.e.setTitle(charSequence);
    }

    public void c(int i) {
        a((CharSequence) this.f.getString(i));
    }

    public View.OnTouchListener g() {
        return this.e;
    }
}
