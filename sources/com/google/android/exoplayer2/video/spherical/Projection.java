package com.google.android.exoplayer2.video.spherical;

import com.google.android.exoplayer2.util.Assertions;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class Projection {
    public static final int DRAW_MODE_TRIANGLES = 0;
    public static final int DRAW_MODE_TRIANGLES_FAN = 2;
    public static final int DRAW_MODE_TRIANGLES_STRIP = 1;
    public static final int POSITION_COORDS_PER_VERTEX = 3;
    public static final int TEXTURE_COORDS_PER_VERTEX = 2;
    public final Mesh leftMesh;
    public final Mesh rightMesh;
    public final boolean singleMesh;
    public final int stereoMode;

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface DrawMode {
    }

    public static Projection createEquirectangular(int i) {
        return createEquirectangular(50.0f, 36, 72, 180.0f, 360.0f, i);
    }

    public static Projection createEquirectangular(float f, int i, int i2, float f2, float f3, int i3) {
        float f4;
        float f5;
        int i4;
        int i5;
        float f6 = f;
        int i6 = i;
        int i7 = i2;
        float f7 = f2;
        float f8 = f3;
        Assertions.checkArgument(f6 > 0.0f);
        Assertions.checkArgument(i6 >= 1);
        Assertions.checkArgument(i7 >= 1);
        Assertions.checkArgument(f7 > 0.0f && f7 <= 180.0f);
        Assertions.checkArgument(f8 > 0.0f && f8 <= 360.0f);
        float radians = (float) Math.toRadians((double) f7);
        float radians2 = (float) Math.toRadians((double) f8);
        float f9 = radians / ((float) i6);
        float f10 = radians2 / ((float) i7);
        int i8 = i7 + 1;
        int i9 = ((i8 * 2) + 2) * i6;
        float[] fArr = new float[(i9 * 3)];
        float[] fArr2 = new float[(i9 * 2)];
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        while (i10 < i6) {
            float f11 = radians / 2.0f;
            float f12 = (((float) i10) * f9) - f11;
            int i13 = i10 + 1;
            float f13 = (((float) i13) * f9) - f11;
            int i14 = i12;
            int i15 = i11;
            int i16 = 0;
            while (i16 < i8) {
                int i17 = i14;
                int i18 = 2;
                int i19 = i15;
                int i20 = 0;
                while (i20 < i18) {
                    if (i20 == 0) {
                        f5 = f12;
                        f4 = f5;
                    } else {
                        f4 = f12;
                        f5 = f13;
                    }
                    float f14 = ((float) i16) * f10;
                    float f15 = f10;
                    int i21 = i19 + 1;
                    int i22 = i13;
                    int i23 = i8;
                    double d = (double) f6;
                    float f16 = radians;
                    double d2 = (double) ((f14 + 3.1415927f) - (radians2 / 2.0f));
                    double sin = Math.sin(d2);
                    Double.isNaN(d);
                    double d3 = (double) f5;
                    float f17 = f9;
                    fArr[i19] = -((float) (sin * d * Math.cos(d3)));
                    int i24 = i21 + 1;
                    double sin2 = Math.sin(d3);
                    Double.isNaN(d);
                    int i25 = i10;
                    int i26 = i16;
                    fArr[i21] = (float) (d * sin2);
                    int i27 = i24 + 1;
                    double cos = Math.cos(d2);
                    Double.isNaN(d);
                    fArr[i24] = (float) (d * cos * Math.cos(d3));
                    int i28 = i17 + 1;
                    fArr2[i17] = f14 / radians2;
                    int i29 = i28 + 1;
                    fArr2[i28] = (((float) (i25 + i20)) * f17) / f16;
                    if (i26 == 0 && i20 == 0) {
                        i4 = i26;
                        i5 = i2;
                    } else {
                        i4 = i26;
                        i5 = i2;
                        if (!(i4 == i5 && i20 == 1)) {
                            i17 = i29;
                            i19 = i27;
                            i20++;
                            i16 = i4;
                            f12 = f4;
                            f10 = f15;
                            i8 = i23;
                            i13 = i22;
                            radians = f16;
                            f9 = f17;
                            i10 = i25;
                            i18 = 2;
                            i7 = i5;
                            f6 = f;
                        }
                    }
                    System.arraycopy(fArr, i27 - 3, fArr, i27, 3);
                    i27 += 3;
                    System.arraycopy(fArr2, i29 - 2, fArr2, i29, 2);
                    i29 += 2;
                    i17 = i29;
                    i19 = i27;
                    i20++;
                    i16 = i4;
                    f12 = f4;
                    f10 = f15;
                    i8 = i23;
                    i13 = i22;
                    radians = f16;
                    f9 = f17;
                    i10 = i25;
                    i18 = 2;
                    i7 = i5;
                    f6 = f;
                }
                float f18 = radians;
                float f19 = f10;
                float f20 = f12;
                int i30 = i13;
                int i31 = i8;
                int i32 = i10;
                i16++;
                i7 = i7;
                i15 = i19;
                i14 = i17;
                f9 = f9;
                f6 = f;
                int i33 = i;
            }
            i11 = i15;
            i12 = i14;
            i10 = i13;
            f6 = f;
            i6 = i;
        }
        return new Projection(new Mesh(new SubMesh(0, fArr, fArr2, 1)), i3);
    }

    public Projection(Mesh mesh, int i) {
        this(mesh, mesh, i);
    }

    public Projection(Mesh mesh, Mesh mesh2, int i) {
        this.leftMesh = mesh;
        this.rightMesh = mesh2;
        this.stereoMode = i;
        this.singleMesh = mesh == mesh2;
    }

    public static final class SubMesh {
        public static final int VIDEO_TEXTURE_ID = 0;
        public final int mode;
        public final float[] textureCoords;
        public final int textureId;
        public final float[] vertices;

        public SubMesh(int i, float[] fArr, float[] fArr2, int i2) {
            this.textureId = i;
            Assertions.checkArgument(((long) fArr.length) * 2 == ((long) fArr2.length) * 3);
            this.vertices = fArr;
            this.textureCoords = fArr2;
            this.mode = i2;
        }

        public int getVertexCount() {
            return this.vertices.length / 3;
        }
    }

    public static final class Mesh {
        private final SubMesh[] subMeshes;

        public Mesh(SubMesh... subMeshArr) {
            this.subMeshes = subMeshArr;
        }

        public int getSubMeshCount() {
            return this.subMeshes.length;
        }

        public SubMesh getSubMesh(int i) {
            return this.subMeshes[i];
        }
    }
}
