package com.mijia.player;

import android.content.Context;
import android.media.SoundPool;
import java.util.HashMap;

public class SoundUtil {

    /* renamed from: a  reason: collision with root package name */
    private static final String f8102a = "SoundUtil";
    private static SoundUtil d;
    /* access modifiers changed from: private */
    public SoundPool b = new SoundPool(3, 3, 0);
    /* access modifiers changed from: private */
    public HashMap<Integer, Integer> c = new HashMap<>();

    public static SoundUtil a() {
        if (d == null) {
            d = new SoundUtil();
        }
        return d;
    }

    private SoundUtil() {
    }

    public void a(Context context, final int i) {
        Integer num = this.c.get(Integer.valueOf(i));
        if (num == null) {
            try {
                Integer.valueOf(this.b.load(context, i, 1));
                this.b.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                    public void onLoadComplete(SoundPool soundPool, int i, int i2) {
                        SoundUtil.this.c.put(Integer.valueOf(i), Integer.valueOf(i));
                        SoundUtil.this.b.play(i, 1.0f, 1.0f, 0, 0, 1.0f);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            this.b.play(num.intValue(), 1.0f, 1.0f, 0, 0, 1.0f);
        }
    }
}
