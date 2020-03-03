package miuipub.preference;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.lang.reflect.Field;
import miuipub.util.AttributeResolver;
import miuipub.v6.R;
import miuipub.widget.SlidingButton;

public class MiuiPreferenceInjector {
    static void a(Preference preference, ViewGroup viewGroup) {
        preference.setLayoutResource(R.layout.v6_preference);
    }

    static void a(Preference preference, View view) {
        Rect rect = new Rect();
        if (preference instanceof MiuiPreferenceCategory) {
            if (!TextUtils.isEmpty(preference.getTitle())) {
                view.setBackgroundResource(R.drawable.v6_preference_category_background);
            } else if (preference.getOrder() == 0) {
                view.setBackgroundResource(R.drawable.v6_preference_category_background_first_no_title);
            } else {
                view.setBackgroundResource(R.drawable.v6_preference_category_background_no_title);
            }
            view.getBackground().getPadding(rect);
            view.setPadding(view.getPaddingLeft(), rect.top, view.getPaddingRight(), rect.bottom);
        } else {
            view.setBackgroundDrawable(AttributeResolver.b(preference.getContext(), R.attr.v6_preferenceBackground));
        }
        if (preference instanceof MiuiCheckBoxPreference) {
            a((MiuiCheckBoxPreference) preference, view);
        }
        View findViewById = view.findViewById(R.id.arrow_right);
        if (findViewById != null) {
            findViewById.setVisibility(preference.getWidgetLayoutResource() == 0 ? 0 : 8);
        }
        int dimensionPixelSize = view.getContext().getResources().getDimensionPixelSize(R.dimen.v6_preference_horizontal_extra_padding);
        Drawable background = view.getBackground();
        rect.setEmpty();
        if (background != null) {
            background.getPadding(rect);
        }
        view.setPadding(rect.left + dimensionPixelSize, view.getPaddingTop(), dimensionPixelSize + rect.right, view.getPaddingBottom());
    }

    private static void a(final MiuiCheckBoxPreference miuiCheckBoxPreference, final View view) {
        View findViewById = view.findViewById(16908289);
        if (findViewById != null && (findViewById instanceof SlidingButton)) {
            final SlidingButton slidingButton = (SlidingButton) findViewById;
            slidingButton.setOnPerformCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    if (view.getWindowVisibility() != 8) {
                        boolean unused = MiuiPreferenceInjector.b(MiuiPreferenceInjector.b(miuiCheckBoxPreference), miuiCheckBoxPreference, view);
                        if (z != miuiCheckBoxPreference.isChecked()) {
                            slidingButton.setChecked(!z);
                        }
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public static PreferenceScreen b(Preference preference) {
        try {
            Field declaredField = PreferenceManager.class.getDeclaredField("mPreferenceScreen");
            declaredField.setAccessible(true);
            return (PreferenceScreen) declaredField.get(preference.getPreferenceManager());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static boolean b(PreferenceScreen preferenceScreen, Preference preference, View view) {
        ListView listView;
        if (preferenceScreen == null) {
            return false;
        }
        ListAdapter rootAdapter = preferenceScreen.getRootAdapter();
        int i = 0;
        while (i < rootAdapter.getCount()) {
            Preference preference2 = (Preference) rootAdapter.getItem(i);
            if (preference2 == preference && (listView = (ListView) view.getParent()) != null) {
                preferenceScreen.onItemClick(listView, view, i + listView.getHeaderViewsCount(), rootAdapter.getItemId(i));
                return true;
            } else if ((preference2 instanceof PreferenceScreen) && b((PreferenceScreen) preference2, preference, view)) {
                return true;
            } else {
                i++;
            }
        }
        return false;
    }
}
