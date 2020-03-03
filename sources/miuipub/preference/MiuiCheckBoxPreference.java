package miuipub.preference;

import android.content.Context;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class MiuiCheckBoxPreference extends CheckBoxPreference {
    public MiuiCheckBoxPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public View onCreateView(ViewGroup viewGroup) {
        MiuiPreferenceInjector.a((Preference) this, viewGroup);
        return super.onCreateView(viewGroup);
    }

    /* access modifiers changed from: protected */
    public void onBindView(View view) {
        MiuiPreferenceInjector.a((Preference) this, view);
        super.onBindView(view);
    }

    /* access modifiers changed from: protected */
    public void onClick() {
        super.onClick();
    }
}
