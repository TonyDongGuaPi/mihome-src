package butterknife;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.view.View;

public interface Setter<T extends View, V> {
    @UiThread
    void set(@NonNull T t, @Nullable V v, int i);
}
