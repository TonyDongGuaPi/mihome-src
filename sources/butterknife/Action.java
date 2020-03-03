package butterknife;

import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.view.View;

public interface Action<T extends View> {
    @UiThread
    void apply(@NonNull T t, int i);
}
