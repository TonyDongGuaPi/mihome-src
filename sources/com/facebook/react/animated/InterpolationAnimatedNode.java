package com.facebook.react.animated;

import android.support.annotation.Nullable;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class InterpolationAnimatedNode extends ValueAnimatedNode {
    public static final String EXTRAPOLATE_TYPE_CLAMP = "clamp";
    public static final String EXTRAPOLATE_TYPE_EXTEND = "extend";
    public static final String EXTRAPOLATE_TYPE_IDENTITY = "identity";
    private static final Pattern fpPattern = Pattern.compile(fpRegex);
    private static final String fpRegex = "[+-]?(\\d+\\.?\\d*|\\.\\d+)([eE][+-]?\\d+)?";
    private final String mExtrapolateLeft;
    private final String mExtrapolateRight;
    private final boolean mHasStringOutput;
    private final double[] mInputRange;
    private int mNumVals;
    private final double[] mOutputRange;
    private double[][] mOutputs;
    @Nullable
    private ValueAnimatedNode mParent;
    private String mPattern;
    private final Matcher mSOutputMatcher;
    private boolean mShouldRound;

    private static double[] fromDoubleArray(ReadableArray readableArray) {
        double[] dArr = new double[readableArray.size()];
        for (int i = 0; i < dArr.length; i++) {
            dArr[i] = readableArray.getDouble(i);
        }
        return dArr;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x005b  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x005e A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0091  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00ad  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00b0 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static double interpolate(double r15, double r17, double r19, double r21, double r23, java.lang.String r25, java.lang.String r26) {
        /*
            r5 = r25
            r6 = r26
            int r7 = (r15 > r17 ? 1 : (r15 == r17 ? 0 : -1))
            r8 = 94742715(0x5a5a8bb, float:1.5578507E-35)
            r9 = -135761730(0xfffffffff7e870be, float:-9.428903E33)
            r10 = -1289044198(0xffffffffb32abf1a, float:-3.9755015E-8)
            r12 = -1
            if (r7 >= 0) goto L_0x005f
            int r7 = r25.hashCode()
            if (r7 == r10) goto L_0x0031
            if (r7 == r9) goto L_0x0027
            if (r7 == r8) goto L_0x001d
            goto L_0x003b
        L_0x001d:
            java.lang.String r7 = "clamp"
            boolean r7 = r5.equals(r7)
            if (r7 == 0) goto L_0x003b
            r7 = 1
            goto L_0x003c
        L_0x0027:
            java.lang.String r7 = "identity"
            boolean r7 = r5.equals(r7)
            if (r7 == 0) goto L_0x003b
            r7 = 0
            goto L_0x003c
        L_0x0031:
            java.lang.String r7 = "extend"
            boolean r7 = r5.equals(r7)
            if (r7 == 0) goto L_0x003b
            r7 = 2
            goto L_0x003c
        L_0x003b:
            r7 = -1
        L_0x003c:
            switch(r7) {
                case 0: goto L_0x005e;
                case 1: goto L_0x005b;
                case 2: goto L_0x005f;
                default: goto L_0x003f;
            }
        L_0x003f:
            com.facebook.react.bridge.JSApplicationIllegalArgumentException r0 = new com.facebook.react.bridge.JSApplicationIllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Invalid extrapolation type "
            r1.append(r2)
            r1.append(r5)
            java.lang.String r2 = "for left extrapolation"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x005b:
            r13 = r17
            goto L_0x0060
        L_0x005e:
            return r15
        L_0x005f:
            r13 = r15
        L_0x0060:
            int r5 = (r13 > r19 ? 1 : (r13 == r19 ? 0 : -1))
            if (r5 <= 0) goto L_0x00b1
            int r5 = r26.hashCode()
            if (r5 == r10) goto L_0x0083
            if (r5 == r9) goto L_0x0079
            if (r5 == r8) goto L_0x006f
            goto L_0x008d
        L_0x006f:
            java.lang.String r5 = "clamp"
            boolean r5 = r6.equals(r5)
            if (r5 == 0) goto L_0x008d
            r11 = 1
            goto L_0x008e
        L_0x0079:
            java.lang.String r5 = "identity"
            boolean r5 = r6.equals(r5)
            if (r5 == 0) goto L_0x008d
            r11 = 0
            goto L_0x008e
        L_0x0083:
            java.lang.String r5 = "extend"
            boolean r5 = r6.equals(r5)
            if (r5 == 0) goto L_0x008d
            r11 = 2
            goto L_0x008e
        L_0x008d:
            r11 = -1
        L_0x008e:
            switch(r11) {
                case 0: goto L_0x00b0;
                case 1: goto L_0x00ad;
                case 2: goto L_0x00b1;
                default: goto L_0x0091;
            }
        L_0x0091:
            com.facebook.react.bridge.JSApplicationIllegalArgumentException r0 = new com.facebook.react.bridge.JSApplicationIllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Invalid extrapolation type "
            r1.append(r2)
            r1.append(r6)
            java.lang.String r2 = "for right extrapolation"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x00ad:
            r13 = r19
            goto L_0x00b1
        L_0x00b0:
            return r13
        L_0x00b1:
            int r5 = (r21 > r23 ? 1 : (r21 == r23 ? 0 : -1))
            if (r5 != 0) goto L_0x00b6
            return r21
        L_0x00b6:
            int r5 = (r17 > r19 ? 1 : (r17 == r19 ? 0 : -1))
            if (r5 != 0) goto L_0x00c0
            int r4 = (r15 > r17 ? 1 : (r15 == r17 ? 0 : -1))
            if (r4 > 0) goto L_0x00bf
            return r21
        L_0x00bf:
            return r23
        L_0x00c0:
            r0 = 0
            double r0 = r23 - r21
            double r13 = r13 - r17
            double r0 = r0 * r13
            double r2 = r19 - r17
            double r0 = r0 / r2
            double r0 = r21 + r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.animated.InterpolationAnimatedNode.interpolate(double, double, double, double, double, java.lang.String, java.lang.String):double");
    }

    static double interpolate(double d, double[] dArr, double[] dArr2, String str, String str2) {
        int findRangeIndex = findRangeIndex(d, dArr);
        int i = findRangeIndex + 1;
        return interpolate(d, dArr[findRangeIndex], dArr[i], dArr2[findRangeIndex], dArr2[i], str, str2);
    }

    private static int findRangeIndex(double d, double[] dArr) {
        int i = 1;
        while (i < dArr.length - 1 && dArr[i] < d) {
            i++;
        }
        return i - 1;
    }

    public InterpolationAnimatedNode(ReadableMap readableMap) {
        this.mInputRange = fromDoubleArray(readableMap.getArray("inputRange"));
        ReadableArray array = readableMap.getArray("outputRange");
        this.mHasStringOutput = array.getType(0) == ReadableType.String;
        if (this.mHasStringOutput) {
            int size = array.size();
            this.mOutputRange = new double[size];
            this.mPattern = array.getString(0);
            this.mShouldRound = this.mPattern.startsWith("rgb");
            this.mSOutputMatcher = fpPattern.matcher(this.mPattern);
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < size; i++) {
                Matcher matcher = fpPattern.matcher(array.getString(i));
                ArrayList arrayList2 = new ArrayList();
                arrayList.add(arrayList2);
                while (matcher.find()) {
                    arrayList2.add(Double.valueOf(Double.parseDouble(matcher.group())));
                }
                this.mOutputRange[i] = ((Double) arrayList2.get(0)).doubleValue();
            }
            this.mNumVals = ((ArrayList) arrayList.get(0)).size();
            this.mOutputs = new double[this.mNumVals][];
            for (int i2 = 0; i2 < this.mNumVals; i2++) {
                double[] dArr = new double[size];
                this.mOutputs[i2] = dArr;
                for (int i3 = 0; i3 < size; i3++) {
                    dArr[i3] = ((Double) ((ArrayList) arrayList.get(i3)).get(i2)).doubleValue();
                }
            }
        } else {
            this.mOutputRange = fromDoubleArray(array);
            this.mSOutputMatcher = null;
        }
        this.mExtrapolateLeft = readableMap.getString("extrapolateLeft");
        this.mExtrapolateRight = readableMap.getString("extrapolateRight");
    }

    public void onAttachedToNode(AnimatedNode animatedNode) {
        if (this.mParent != null) {
            throw new IllegalStateException("Parent already attached");
        } else if (animatedNode instanceof ValueAnimatedNode) {
            this.mParent = (ValueAnimatedNode) animatedNode;
        } else {
            throw new IllegalArgumentException("Parent is of an invalid type");
        }
    }

    public void onDetachedFromNode(AnimatedNode animatedNode) {
        if (animatedNode == this.mParent) {
            this.mParent = null;
            return;
        }
        throw new IllegalArgumentException("Invalid parent node provided");
    }

    public void update() {
        String str;
        if (this.mParent != null) {
            double value = this.mParent.getValue();
            this.mValue = interpolate(value, this.mInputRange, this.mOutputRange, this.mExtrapolateLeft, this.mExtrapolateRight);
            if (!this.mHasStringOutput) {
                return;
            }
            if (this.mNumVals > 1) {
                StringBuffer stringBuffer = new StringBuffer(this.mPattern.length());
                this.mSOutputMatcher.reset();
                int i = 0;
                while (this.mSOutputMatcher.find()) {
                    int i2 = i + 1;
                    double interpolate = interpolate(value, this.mInputRange, this.mOutputs[i], this.mExtrapolateLeft, this.mExtrapolateRight);
                    if (this.mShouldRound) {
                        boolean z = i2 == 4;
                        if (z) {
                            interpolate *= 1000.0d;
                        }
                        int round = (int) Math.round(interpolate);
                        if (z) {
                            double d = (double) round;
                            Double.isNaN(d);
                            str = Double.toString(d / 1000.0d);
                        } else {
                            str = Integer.toString(round);
                        }
                        this.mSOutputMatcher.appendReplacement(stringBuffer, str);
                    } else {
                        int i3 = (int) interpolate;
                        this.mSOutputMatcher.appendReplacement(stringBuffer, ((double) i3) != interpolate ? Double.toString(interpolate) : Integer.toString(i3));
                    }
                    i = i2;
                }
                this.mSOutputMatcher.appendTail(stringBuffer);
                this.mAnimatedObject = stringBuffer.toString();
                return;
            }
            this.mAnimatedObject = this.mSOutputMatcher.replaceFirst(String.valueOf(this.mValue));
        }
    }
}
