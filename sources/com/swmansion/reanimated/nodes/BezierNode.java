package com.swmansion.reanimated.nodes;

import android.graphics.PointF;
import com.facebook.react.bridge.ReadableMap;
import com.swmansion.reanimated.NodesManager;

public class BezierNode extends Node {

    /* renamed from: a  reason: collision with root package name */
    private final int f8915a;
    private final CubicBezierInterpolator b;

    private static class CubicBezierInterpolator {

        /* renamed from: a  reason: collision with root package name */
        protected PointF f8916a;
        protected PointF b;
        protected PointF c;
        protected PointF d;
        protected PointF e;

        public CubicBezierInterpolator(PointF pointF, PointF pointF2) {
            this.c = new PointF();
            this.d = new PointF();
            this.e = new PointF();
            this.f8916a = pointF;
            this.b = pointF2;
        }

        public CubicBezierInterpolator(float f, float f2, float f3, float f4) {
            this(new PointF(f, f2), new PointF(f3, f4));
        }

        public float a(float f) {
            return b(c(f));
        }

        /* access modifiers changed from: protected */
        public float b(float f) {
            this.e.y = this.f8916a.y * 3.0f;
            this.d.y = ((this.b.y - this.f8916a.y) * 3.0f) - this.e.y;
            this.c.y = (1.0f - this.e.y) - this.d.y;
            return f * (this.e.y + ((this.d.y + (this.c.y * f)) * f));
        }

        /* access modifiers changed from: protected */
        public float c(float f) {
            float f2 = f;
            for (int i = 1; i < 14; i++) {
                float e2 = e(f2) - f;
                if (((double) Math.abs(e2)) < 0.001d) {
                    break;
                }
                f2 -= e2 / d(f2);
            }
            return f2;
        }

        private float d(float f) {
            return this.e.x + (f * ((this.d.x * 2.0f) + (this.c.x * 3.0f * f)));
        }

        private float e(float f) {
            this.e.x = this.f8916a.x * 3.0f;
            this.d.x = ((this.b.x - this.f8916a.x) * 3.0f) - this.e.x;
            this.c.x = (1.0f - this.e.x) - this.d.x;
            return f * (this.e.x + ((this.d.x + (this.c.x * f)) * f));
        }
    }

    public BezierNode(int i, ReadableMap readableMap, NodesManager nodesManager) {
        super(i, readableMap, nodesManager);
        this.f8915a = readableMap.getInt("input");
        this.b = new CubicBezierInterpolator((float) readableMap.getDouble("mX1"), (float) readableMap.getDouble("mY1"), (float) readableMap.getDouble("mX2"), (float) readableMap.getDouble("mY2"));
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Double evaluate() {
        return Double.valueOf((double) this.b.a(((Double) this.mNodesManager.a(this.f8915a)).floatValue()));
    }
}
