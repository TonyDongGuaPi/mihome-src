package org.wltea.analyzer.dic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class DictSegment implements Comparable<DictSegment> {

    /* renamed from: a  reason: collision with root package name */
    private static final Map<Character, Character> f4204a = new HashMap(16, 0.95f);
    private static final int b = 3;
    private Map<Character, DictSegment> c;
    private DictSegment[] d;
    private Character e;
    private int f = 0;
    private int g = 0;

    DictSegment(Character ch) {
        if (ch != null) {
            this.e = ch;
            return;
        }
        throw new IllegalArgumentException("参数为空异常，字符不能为空");
    }

    /* access modifiers changed from: package-private */
    public Character a() {
        return this.e;
    }

    /* access modifiers changed from: package-private */
    public boolean b() {
        return this.f > 0;
    }

    /* access modifiers changed from: package-private */
    public Hit a(char[] cArr) {
        return a(cArr, 0, cArr.length, (Hit) null);
    }

    /* access modifiers changed from: package-private */
    public Hit a(char[] cArr, int i, int i2) {
        return a(cArr, i, i2, (Hit) null);
    }

    /* access modifiers changed from: package-private */
    public Hit a(char[] cArr, int i, int i2, Hit hit) {
        if (hit == null) {
            hit = new Hit();
            hit.a(i);
        } else {
            hit.f();
        }
        hit.b(i);
        Character ch = new Character(cArr[i]);
        DictSegment dictSegment = null;
        DictSegment[] dictSegmentArr = this.d;
        Map<Character, DictSegment> map = this.c;
        if (dictSegmentArr != null) {
            int binarySearch = Arrays.binarySearch(dictSegmentArr, 0, this.f, new DictSegment(ch));
            if (binarySearch >= 0) {
                dictSegment = dictSegmentArr[binarySearch];
            }
        } else if (map != null) {
            dictSegment = map.get(ch);
        }
        if (dictSegment != null) {
            if (i2 > 1) {
                return dictSegment.a(cArr, i + 1, i2 - 1, hit);
            }
            if (i2 == 1) {
                if (dictSegment.g == 1) {
                    hit.b();
                }
                if (dictSegment.b()) {
                    hit.d();
                    hit.a(dictSegment);
                }
                return hit;
            }
        }
        return hit;
    }

    /* access modifiers changed from: package-private */
    public void b(char[] cArr) {
        a(cArr, 0, cArr.length, 1);
    }

    /* access modifiers changed from: package-private */
    public void c(char[] cArr) {
        a(cArr, 0, cArr.length, 0);
    }

    private synchronized void a(char[] cArr, int i, int i2, int i3) {
        Character ch = new Character(cArr[i]);
        Character ch2 = f4204a.get(ch);
        if (ch2 == null) {
            f4204a.put(ch, ch);
        } else {
            ch = ch2;
        }
        DictSegment a2 = a(ch, i3);
        if (a2 != null) {
            if (i2 > 1) {
                a2.a(cArr, i + 1, i2 - 1, i3);
            } else if (i2 == 1) {
                a2.g = i3;
            }
        }
    }

    private DictSegment a(Character ch, int i) {
        if (this.f <= 3) {
            DictSegment[] c2 = c();
            DictSegment dictSegment = new DictSegment(ch);
            int binarySearch = Arrays.binarySearch(c2, 0, this.f, dictSegment);
            DictSegment dictSegment2 = binarySearch >= 0 ? c2[binarySearch] : null;
            if (dictSegment2 != null || i != 1) {
                return dictSegment2;
            }
            if (this.f < 3) {
                c2[this.f] = dictSegment;
                this.f++;
                Arrays.sort(c2, 0, this.f);
                return dictSegment;
            }
            Map<Character, DictSegment> d2 = d();
            a(c2, d2);
            d2.put(ch, dictSegment);
            this.f++;
            this.d = null;
            return dictSegment;
        }
        Map<Character, DictSegment> d3 = d();
        DictSegment dictSegment3 = d3.get(ch);
        if (dictSegment3 != null || i != 1) {
            return dictSegment3;
        }
        DictSegment dictSegment4 = new DictSegment(ch);
        d3.put(ch, dictSegment4);
        this.f++;
        return dictSegment4;
    }

    private DictSegment[] c() {
        if (this.d == null) {
            synchronized (this) {
                if (this.d == null) {
                    this.d = new DictSegment[3];
                }
            }
        }
        return this.d;
    }

    private Map<Character, DictSegment> d() {
        if (this.c == null) {
            synchronized (this) {
                if (this.c == null) {
                    this.c = new HashMap(6, 0.8f);
                }
            }
        }
        return this.c;
    }

    private void a(DictSegment[] dictSegmentArr, Map<Character, DictSegment> map) {
        for (DictSegment dictSegment : dictSegmentArr) {
            if (dictSegment != null) {
                map.put(dictSegment.e, dictSegment);
            }
        }
    }

    /* renamed from: a */
    public int compareTo(DictSegment dictSegment) {
        return this.e.compareTo(dictSegment.e);
    }
}
