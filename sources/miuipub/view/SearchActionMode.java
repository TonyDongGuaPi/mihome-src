package miuipub.view;

import android.view.ActionMode;
import android.view.View;
import android.widget.EditText;

public interface SearchActionMode {

    public interface Callback extends ActionMode.Callback {
    }

    EditText a();

    void a(View view);

    void a(ActionModeAnimationListener actionModeAnimationListener);

    void b(View view);

    void b(ActionModeAnimationListener actionModeAnimationListener);

    void c(View view);
}
