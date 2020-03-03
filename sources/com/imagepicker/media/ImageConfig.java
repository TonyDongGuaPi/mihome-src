package com.imagepicker.media;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ViewProps;
import com.taobao.weex.common.Constants;
import java.io.File;

public class ImageConfig {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final File f6065a;
    @Nullable
    public final File b;
    public final int c;
    public final int d;
    public final int e;
    public final int f;
    public final boolean g;

    public ImageConfig(@Nullable File file, @Nullable File file2, int i, int i2, int i3, int i4, boolean z) {
        this.f6065a = file;
        this.b = file2;
        this.c = i;
        this.d = i2;
        this.e = i3;
        this.f = i4;
        this.g = z;
    }

    @NonNull
    public ImageConfig a(int i) {
        return new ImageConfig(this.f6065a, this.b, i, this.d, this.e, this.f, this.g);
    }

    @NonNull
    public ImageConfig b(int i) {
        return new ImageConfig(this.f6065a, this.b, this.c, i, this.e, this.f, this.g);
    }

    @NonNull
    public ImageConfig c(int i) {
        return new ImageConfig(this.f6065a, this.b, this.c, this.d, i, this.f, this.g);
    }

    @NonNull
    public ImageConfig d(int i) {
        return new ImageConfig(this.f6065a, this.b, this.c, this.d, this.e, i, this.g);
    }

    @NonNull
    public ImageConfig a(@Nullable File file) {
        return new ImageConfig(file, this.b, this.c, this.d, this.e, this.f, this.g);
    }

    @NonNull
    public ImageConfig b(@Nullable File file) {
        return new ImageConfig(this.f6065a, file, this.c, this.d, this.e, this.f, this.g);
    }

    @NonNull
    public ImageConfig a(@Nullable boolean z) {
        return new ImageConfig(this.f6065a, this.b, this.c, this.d, this.e, this.f, z);
    }

    @NonNull
    public ImageConfig a(@NonNull ReadableMap readableMap) {
        boolean z;
        int i = readableMap.hasKey("maxWidth") ? readableMap.getInt("maxWidth") : 0;
        int i2 = readableMap.hasKey("maxHeight") ? readableMap.getInt("maxHeight") : 0;
        int i3 = readableMap.hasKey(Constants.Name.QUALITY) ? (int) (readableMap.getDouble(Constants.Name.QUALITY) * 100.0d) : 100;
        int i4 = readableMap.hasKey(ViewProps.ROTATION) ? readableMap.getInt(ViewProps.ROTATION) : 0;
        if (readableMap.hasKey("storageOptions")) {
            ReadableMap map = readableMap.getMap("storageOptions");
            if (map.hasKey("cameraRoll")) {
                z = map.getBoolean("cameraRoll");
                return new ImageConfig(this.f6065a, this.b, i, i2, i3, i4, z);
            }
        }
        z = false;
        return new ImageConfig(this.f6065a, this.b, i, i2, i3, i4, z);
    }

    public boolean a(int i, int i2, int i3) {
        return ((i < this.c && this.c > 0) || this.c == 0) && ((i2 < this.d && this.d > 0) || this.d == 0) && this.e == 100 && (this.f == 0 || i3 == this.f);
    }

    public File a() {
        return this.b != null ? this.b : this.f6065a;
    }
}
