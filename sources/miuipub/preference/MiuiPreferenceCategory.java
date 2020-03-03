package miuipub.preference;

import android.content.Context;
import android.preference.Preference;
import android.preference.PreferenceCategory;
import android.util.AttributeSet;
import android.view.View;
import miuipub.stateposition.StatePosition;

public class MiuiPreferenceCategory extends PreferenceCategory {
    public MiuiPreferenceCategory(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onBindView(View view) {
        MiuiPreferenceInjector.a((Preference) this, view);
        super.onBindView(view);
    }

    public boolean addPreference(Preference preference) {
        boolean addPreference = super.addPreference(preference);
        a(this);
        return addPreference;
    }

    public boolean removePreference(Preference preference) {
        boolean removePreference = super.removePreference(preference);
        a(this);
        return removePreference;
    }

    private void a(PreferenceCategory preferenceCategory) {
        int preferenceCount = preferenceCategory.getPreferenceCount();
        for (int i = 0; i < preferenceCount; i++) {
            Preference preference = preferenceCategory.getPreference(i);
            if (preference instanceof StatePosition) {
                StatePosition statePosition = (StatePosition) preference;
                if (preferenceCount == 1) {
                    statePosition.setPosition(3);
                } else if (i == 0) {
                    statePosition.setPosition(0);
                } else if (i == preferenceCount - 1) {
                    statePosition.setPosition(2);
                } else {
                    statePosition.setPosition(1);
                }
            }
        }
    }
}
