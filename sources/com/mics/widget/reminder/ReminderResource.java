package com.mics.widget.reminder;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.drawable.Drawable;
import com.mics.R;

public class ReminderResource {

    /* renamed from: a  reason: collision with root package name */
    private static ReminderResource f7841a;
    private Drawable b;
    private Drawable c;
    private Drawable d;
    private AssetFileDescriptor e;

    private ReminderResource() {
    }

    private static ReminderResource e() {
        if (f7841a == null) {
            synchronized (ReminderResource.class) {
                if (f7841a == null) {
                    f7841a = new ReminderResource();
                }
            }
        }
        return f7841a;
    }

    public static void a(Context context) {
        if (context != null && e().b == null && e().c == null) {
            e().b = context.getResources().getDrawable(R.drawable.mics_reminder_shadow);
            e().c = context.getResources().getDrawable(R.drawable.mics_reminder_header_bg);
            e().d = context.getResources().getDrawable(R.drawable.mics_icon_cs_default);
            e().e = context.getResources().openRawResourceFd(R.raw.sound_new_message);
        }
    }

    static Drawable a() {
        return e().d;
    }

    static Drawable b() {
        return e().b;
    }

    static Drawable c() {
        return e().c;
    }

    static AssetFileDescriptor d() {
        return e().e;
    }
}
