package net.qiujuer.genius.ui.drawable.effect;

public final class EffectFactory {

    /* renamed from: a  reason: collision with root package name */
    public static final int f3137a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;

    public static Effect a(int i) {
        if (i == 1) {
            return new AutoEffect();
        }
        if (i == 2) {
            return new EaseEffect();
        }
        if (i == 3) {
            return new PressEffect();
        }
        if (i == 4) {
            return new RippleEffect();
        }
        return null;
    }
}
