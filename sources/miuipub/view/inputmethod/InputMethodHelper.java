package miuipub.view.inputmethod;

import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.miuipub.internal.util.PackageConstants;
import miuipub.util.SoftReferenceSingleton;

public class InputMethodHelper {

    /* renamed from: a  reason: collision with root package name */
    private static final SoftReferenceSingleton<InputMethodHelper> f3065a = new SoftReferenceSingleton<InputMethodHelper>() {
        /* access modifiers changed from: protected */
        /* renamed from: a */
        public InputMethodHelper createInstance() {
            return new InputMethodHelper();
        }
    };
    private InputMethodManager b;

    private InputMethodHelper() {
        this.b = (InputMethodManager) PackageConstants.a().getSystemService("input_method");
    }

    public static InputMethodHelper a() {
        return f3065a.get();
    }

    public InputMethodManager b() {
        return this.b;
    }

    public void a(EditText editText) {
        editText.requestFocus();
        this.b.viewClicked(editText);
        this.b.showSoftInput(editText, 0);
    }

    public void b(EditText editText) {
        this.b.hideSoftInputFromInputMethod(editText.getWindowToken(), 0);
    }
}
