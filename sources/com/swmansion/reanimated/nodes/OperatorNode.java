package com.swmansion.reanimated.nodes;

import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableMap;
import com.swmansion.reanimated.NodesManager;
import com.swmansion.reanimated.Utils;
import com.xiaomi.smarthome.feedback.FeedbackApi;
import com.xiaomi.stat.a.j;

public class OperatorNode extends Node {

    /* renamed from: a  reason: collision with root package name */
    private static final Operator f8927a = new ReduceOperator() {
        public double a(Double d, Double d2) {
            return d.doubleValue() + d2.doubleValue();
        }
    };
    private static final Operator b = new ReduceOperator() {
        public double a(Double d, Double d2) {
            return d.doubleValue() - d2.doubleValue();
        }
    };
    private static final Operator c = new ReduceOperator() {
        public double a(Double d, Double d2) {
            return d.doubleValue() * d2.doubleValue();
        }
    };
    private static final Operator d = new ReduceOperator() {
        public double a(Double d, Double d2) {
            return d.doubleValue() / d2.doubleValue();
        }
    };
    private static final Operator e = new ReduceOperator() {
        public double a(Double d, Double d2) {
            return Math.pow(d.doubleValue(), d2.doubleValue());
        }
    };
    private static final Operator f = new ReduceOperator() {
        public double a(Double d, Double d2) {
            return ((d.doubleValue() % d2.doubleValue()) + d2.doubleValue()) % d2.doubleValue();
        }
    };
    private static final Operator g = new SingleOperator() {
        public double a(Double d) {
            return Math.sqrt(d.doubleValue());
        }
    };
    private static final Operator h = new SingleOperator() {
        public double a(Double d) {
            return Math.log(d.doubleValue());
        }
    };
    private static final Operator i = new SingleOperator() {
        public double a(Double d) {
            return Math.sin(d.doubleValue());
        }
    };
    private static final Operator j = new SingleOperator() {
        public double a(Double d) {
            return Math.cos(d.doubleValue());
        }
    };
    private static final Operator k = new SingleOperator() {
        public double a(Double d) {
            return Math.tan(d.doubleValue());
        }
    };
    private static final Operator l = new SingleOperator() {
        public double a(Double d) {
            return Math.acos(d.doubleValue());
        }
    };
    private static final Operator m = new SingleOperator() {
        public double a(Double d) {
            return Math.asin(d.doubleValue());
        }
    };
    private static final Operator n = new SingleOperator() {
        public double a(Double d) {
            return Math.atan(d.doubleValue());
        }
    };
    private static final Operator o = new SingleOperator() {
        public double a(Double d) {
            return Math.exp(d.doubleValue());
        }
    };
    private static final Operator p = new SingleOperator() {
        public double a(Double d) {
            return (double) Math.round(d.doubleValue());
        }
    };
    private static final Operator q = new Operator() {
        public double a(Node[] nodeArr) {
            boolean a2 = OperatorNode.b(nodeArr[0].value());
            for (int i = 1; i < nodeArr.length && a2; i++) {
                a2 = a2 && OperatorNode.b(nodeArr[i].value());
            }
            return a2 ? 1.0d : 0.0d;
        }
    };
    private static final Operator r = new Operator() {
        public double a(Node[] nodeArr) {
            boolean a2 = OperatorNode.b(nodeArr[0].value());
            for (int i = 1; i < nodeArr.length && !a2; i++) {
                a2 = a2 || OperatorNode.b(nodeArr[i].value());
            }
            return a2 ? 1.0d : 0.0d;
        }
    };
    private static final Operator s = new Operator() {
        public double a(Node[] nodeArr) {
            return OperatorNode.b(nodeArr[0].value()) ? 0.0d : 1.0d;
        }
    };
    private static final Operator t = new Operator() {
        public double a(Node[] nodeArr) {
            Object value = nodeArr[0].value();
            return (value == null || ((value instanceof Double) && ((Double) value).isNaN())) ? 0.0d : 1.0d;
        }
    };
    private static final Operator u = new CompOperator() {
        public boolean a(Double d, Double d2) {
            return d.doubleValue() < d2.doubleValue();
        }
    };
    private static final Operator v = new CompOperator() {
        public boolean a(Double d, Double d2) {
            return d.equals(d2);
        }
    };
    private static final Operator w = new CompOperator() {
        public boolean a(Double d, Double d2) {
            return d.doubleValue() > d2.doubleValue();
        }
    };
    private static final Operator x = new CompOperator() {
        public boolean a(Double d, Double d2) {
            return d.doubleValue() <= d2.doubleValue();
        }
    };
    private static final Operator y = new CompOperator() {
        public boolean a(Double d, Double d2) {
            return d.doubleValue() >= d2.doubleValue();
        }
    };
    private static final Operator z = new CompOperator() {
        public boolean a(Double d, Double d2) {
            return !d.equals(d2);
        }
    };
    private final int[] A;
    private final Node[] B = new Node[this.A.length];
    private final Operator C;

    private interface Operator {
        double a(Node[] nodeArr);
    }

    /* access modifiers changed from: private */
    public static boolean b(Object obj) {
        return obj != null && !obj.equals(Double.valueOf(0.0d));
    }

    private static abstract class ReduceOperator implements Operator {
        public abstract double a(Double d, Double d2);

        private ReduceOperator() {
        }

        public double a(Node[] nodeArr) {
            double doubleValue = nodeArr[0].doubleValue().doubleValue();
            for (int i = 1; i < nodeArr.length; i++) {
                doubleValue = a(Double.valueOf(doubleValue), nodeArr[i].doubleValue());
            }
            return doubleValue;
        }
    }

    private static abstract class SingleOperator implements Operator {
        public abstract double a(Double d);

        private SingleOperator() {
        }

        public double a(Node[] nodeArr) {
            return a((Double) nodeArr[0].value());
        }
    }

    private static abstract class CompOperator implements Operator {
        public abstract boolean a(Double d, Double d2);

        private CompOperator() {
        }

        public double a(Node[] nodeArr) {
            return a((Double) nodeArr[0].value(), (Double) nodeArr[1].value()) ? 1.0d : 0.0d;
        }
    }

    public OperatorNode(int i2, ReadableMap readableMap, NodesManager nodesManager) {
        super(i2, readableMap, nodesManager);
        this.A = Utils.a(readableMap.getArray("input"));
        String string = readableMap.getString("op");
        if ("add".equals(string)) {
            this.C = f8927a;
        } else if (j.i.equals(string)) {
            this.C = b;
        } else if ("multiply".equals(string)) {
            this.C = c;
        } else if ("divide".equals(string)) {
            this.C = d;
        } else if ("pow".equals(string)) {
            this.C = e;
        } else if ("modulo".equals(string)) {
            this.C = f;
        } else if ("sqrt".equals(string)) {
            this.C = g;
        } else if ("log".equals(string)) {
            this.C = h;
        } else if ("sin".equals(string)) {
            this.C = i;
        } else if ("cos".equals(string)) {
            this.C = j;
        } else if ("tan".equals(string)) {
            this.C = k;
        } else if ("acos".equals(string)) {
            this.C = l;
        } else if ("asin".equals(string)) {
            this.C = m;
        } else if ("atan".equals(string)) {
            this.C = n;
        } else if (FeedbackApi.COMMON_EXP.equals(string)) {
            this.C = o;
        } else if ("round".equals(string)) {
            this.C = p;
        } else if ("and".equals(string)) {
            this.C = q;
        } else if ("or".equals(string)) {
            this.C = r;
        } else if ("not".equals(string)) {
            this.C = s;
        } else if ("defined".equals(string)) {
            this.C = t;
        } else if ("lessThan".equals(string)) {
            this.C = u;
        } else if ("eq".equals(string)) {
            this.C = v;
        } else if ("greaterThan".equals(string)) {
            this.C = w;
        } else if ("lessOrEq".equals(string)) {
            this.C = x;
        } else if ("greaterOrEq".equals(string)) {
            this.C = y;
        } else if ("neq".equals(string)) {
            this.C = z;
        } else {
            throw new JSApplicationIllegalArgumentException("Unrecognized operator " + string);
        }
    }

    /* access modifiers changed from: protected */
    public Object evaluate() {
        for (int i2 = 0; i2 < this.A.length; i2++) {
            this.B[i2] = this.mNodesManager.a(this.A[i2], Node.class);
        }
        return Double.valueOf(this.C.a(this.B));
    }
}
