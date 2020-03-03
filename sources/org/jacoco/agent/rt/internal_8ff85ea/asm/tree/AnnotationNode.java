package org.jacoco.agent.rt.internal_8ff85ea.asm.tree;

import java.util.ArrayList;
import java.util.List;
import org.jacoco.agent.rt.internal_8ff85ea.asm.AnnotationVisitor;

public class AnnotationNode extends AnnotationVisitor {
    public String c;
    public List<Object> d;

    public void a() {
    }

    public void a(int i) {
    }

    public AnnotationNode(String str) {
        this(327680, str);
        if (getClass() != AnnotationNode.class) {
            throw new IllegalStateException();
        }
    }

    public AnnotationNode(int i, String str) {
        super(i);
        this.c = str;
    }

    AnnotationNode(List<Object> list) {
        super(327680);
        this.d = list;
    }

    public void a(String str, Object obj) {
        if (this.d == null) {
            this.d = new ArrayList(this.c != null ? 2 : 1);
        }
        if (this.c != null) {
            this.d.add(str);
        }
        int i = 0;
        if (obj instanceof byte[]) {
            byte[] bArr = (byte[]) obj;
            ArrayList arrayList = new ArrayList(bArr.length);
            int length = bArr.length;
            while (i < length) {
                arrayList.add(Byte.valueOf(bArr[i]));
                i++;
            }
            this.d.add(arrayList);
        } else if (obj instanceof boolean[]) {
            boolean[] zArr = (boolean[]) obj;
            ArrayList arrayList2 = new ArrayList(zArr.length);
            int length2 = zArr.length;
            while (i < length2) {
                arrayList2.add(Boolean.valueOf(zArr[i]));
                i++;
            }
            this.d.add(arrayList2);
        } else if (obj instanceof short[]) {
            short[] sArr = (short[]) obj;
            ArrayList arrayList3 = new ArrayList(sArr.length);
            int length3 = sArr.length;
            while (i < length3) {
                arrayList3.add(Short.valueOf(sArr[i]));
                i++;
            }
            this.d.add(arrayList3);
        } else if (obj instanceof char[]) {
            char[] cArr = (char[]) obj;
            ArrayList arrayList4 = new ArrayList(cArr.length);
            int length4 = cArr.length;
            while (i < length4) {
                arrayList4.add(Character.valueOf(cArr[i]));
                i++;
            }
            this.d.add(arrayList4);
        } else if (obj instanceof int[]) {
            int[] iArr = (int[]) obj;
            ArrayList arrayList5 = new ArrayList(iArr.length);
            int length5 = iArr.length;
            while (i < length5) {
                arrayList5.add(Integer.valueOf(iArr[i]));
                i++;
            }
            this.d.add(arrayList5);
        } else if (obj instanceof long[]) {
            long[] jArr = (long[]) obj;
            ArrayList arrayList6 = new ArrayList(jArr.length);
            int length6 = jArr.length;
            while (i < length6) {
                arrayList6.add(Long.valueOf(jArr[i]));
                i++;
            }
            this.d.add(arrayList6);
        } else if (obj instanceof float[]) {
            float[] fArr = (float[]) obj;
            ArrayList arrayList7 = new ArrayList(fArr.length);
            int length7 = fArr.length;
            while (i < length7) {
                arrayList7.add(Float.valueOf(fArr[i]));
                i++;
            }
            this.d.add(arrayList7);
        } else if (obj instanceof double[]) {
            double[] dArr = (double[]) obj;
            ArrayList arrayList8 = new ArrayList(dArr.length);
            int length8 = dArr.length;
            while (i < length8) {
                arrayList8.add(Double.valueOf(dArr[i]));
                i++;
            }
            this.d.add(arrayList8);
        } else {
            this.d.add(obj);
        }
    }

    public void a(String str, String str2, String str3) {
        if (this.d == null) {
            this.d = new ArrayList(this.c != null ? 2 : 1);
        }
        if (this.c != null) {
            this.d.add(str);
        }
        this.d.add(new String[]{str2, str3});
    }

    public AnnotationVisitor a(String str, String str2) {
        if (this.d == null) {
            this.d = new ArrayList(this.c != null ? 2 : 1);
        }
        if (this.c != null) {
            this.d.add(str);
        }
        AnnotationNode annotationNode = new AnnotationNode(str2);
        this.d.add(annotationNode);
        return annotationNode;
    }

    public AnnotationVisitor a(String str) {
        if (this.d == null) {
            this.d = new ArrayList(this.c != null ? 2 : 1);
        }
        if (this.c != null) {
            this.d.add(str);
        }
        ArrayList arrayList = new ArrayList();
        this.d.add(arrayList);
        return new AnnotationNode((List<Object>) arrayList);
    }

    public void a(AnnotationVisitor annotationVisitor) {
        if (annotationVisitor != null) {
            if (this.d != null) {
                for (int i = 0; i < this.d.size(); i += 2) {
                    a(annotationVisitor, (String) this.d.get(i), this.d.get(i + 1));
                }
            }
            annotationVisitor.a();
        }
    }

    static void a(AnnotationVisitor annotationVisitor, String str, Object obj) {
        if (annotationVisitor != null) {
            if (obj instanceof String[]) {
                String[] strArr = (String[]) obj;
                annotationVisitor.a(str, strArr[0], strArr[1]);
            } else if (obj instanceof AnnotationNode) {
                AnnotationNode annotationNode = (AnnotationNode) obj;
                annotationNode.a(annotationVisitor.a(str, annotationNode.c));
            } else if (obj instanceof List) {
                AnnotationVisitor a2 = annotationVisitor.a(str);
                if (a2 != null) {
                    List list = (List) obj;
                    for (int i = 0; i < list.size(); i++) {
                        a(a2, (String) null, list.get(i));
                    }
                    a2.a();
                }
            } else {
                annotationVisitor.a(str, obj);
            }
        }
    }
}
