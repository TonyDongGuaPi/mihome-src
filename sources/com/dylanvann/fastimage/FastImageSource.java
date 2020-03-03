package com.dylanvann.fastimage;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.text.TextUtils;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.Headers;
import com.facebook.react.views.imagehelper.ImageSource;
import javax.annotation.Nullable;

public class FastImageSource extends ImageSource {

    /* renamed from: a  reason: collision with root package name */
    private static final String f5286a = "data";
    private static final String b = "res";
    private static final String c = "android.resource";
    private static final String d = "content";
    private static final String e = "file";
    private Headers f;
    private Uri g;

    public static boolean a(Uri uri) {
        return "data".equals(uri.getScheme());
    }

    public static boolean b(Uri uri) {
        return "res".equals(uri.getScheme());
    }

    public static boolean c(Uri uri) {
        return "android.resource".equals(uri.getScheme());
    }

    public static boolean d(Uri uri) {
        return "content".equals(uri.getScheme());
    }

    public static boolean e(Uri uri) {
        return "file".equals(uri.getScheme());
    }

    public FastImageSource(Context context, String str) {
        this(context, str, (Headers) null);
    }

    public FastImageSource(Context context, String str, @Nullable Headers headers) {
        this(context, str, 0.0d, 0.0d, headers);
    }

    public FastImageSource(Context context, String str, double d2, double d3, @Nullable Headers headers) {
        super(context, str, d2, d3);
        this.f = headers == null ? Headers.b : headers;
        this.g = super.getUri();
        if (isResource() && TextUtils.isEmpty(this.g.toString())) {
            throw new Resources.NotFoundException("Local Resource Not Found. Resource: '" + getSource() + "'.");
        } else if (b(this.g)) {
            String uri = this.g.toString();
            this.g = Uri.parse(uri.replace("res:/", "android.resource://" + context.getPackageName() + "/"));
        }
    }

    public boolean a() {
        return this.g != null && a(this.g);
    }

    public boolean isResource() {
        return this.g != null && c(this.g);
    }

    public boolean b() {
        return this.g != null && e(this.g);
    }

    public boolean c() {
        return this.g != null && d(this.g);
    }

    public Object d() {
        if (c()) {
            return getSource();
        }
        if (a()) {
            return getSource();
        }
        if (isResource()) {
            return getUri();
        }
        if (b()) {
            return getUri().toString();
        }
        return f();
    }

    public Uri getUri() {
        return this.g;
    }

    public Headers e() {
        return this.f;
    }

    public GlideUrl f() {
        return new GlideUrl(getUri().toString(), e());
    }
}
