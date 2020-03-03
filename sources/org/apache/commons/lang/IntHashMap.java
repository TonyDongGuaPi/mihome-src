package org.apache.commons.lang;

class IntHashMap {

    /* renamed from: a  reason: collision with root package name */
    private transient Entry[] f3366a;
    private transient int b;
    private int c;
    private final float d;

    private static class Entry {

        /* renamed from: a  reason: collision with root package name */
        final int f3367a;
        final int b;
        Object c;
        Entry d;

        protected Entry(int i, int i2, Object obj, Entry entry) {
            this.f3367a = i;
            this.b = i2;
            this.c = obj;
            this.d = entry;
        }
    }

    public IntHashMap() {
        this(20, 0.75f);
    }

    public IntHashMap(int i) {
        this(i, 0.75f);
    }

    public IntHashMap(int i, float f) {
        if (i < 0) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Illegal Capacity: ");
            stringBuffer.append(i);
            throw new IllegalArgumentException(stringBuffer.toString());
        } else if (f > 0.0f) {
            i = i == 0 ? 1 : i;
            this.d = f;
            this.f3366a = new Entry[i];
            this.c = (int) (((float) i) * f);
        } else {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Illegal Load: ");
            stringBuffer2.append(f);
            throw new IllegalArgumentException(stringBuffer2.toString());
        }
    }

    public int a() {
        return this.b;
    }

    public boolean b() {
        return this.b == 0;
    }

    public boolean a(Object obj) {
        if (obj != null) {
            Entry[] entryArr = this.f3366a;
            int length = entryArr.length;
            while (true) {
                int i = length - 1;
                if (length <= 0) {
                    return false;
                }
                for (Entry entry = entryArr[i]; entry != null; entry = entry.d) {
                    if (entry.c.equals(obj)) {
                        return true;
                    }
                }
                length = i;
            }
        } else {
            throw new NullPointerException();
        }
    }

    public boolean b(Object obj) {
        return a(obj);
    }

    public boolean a(int i) {
        Entry[] entryArr = this.f3366a;
        for (Entry entry = entryArr[(Integer.MAX_VALUE & i) % entryArr.length]; entry != null; entry = entry.d) {
            if (entry.f3367a == i) {
                return true;
            }
        }
        return false;
    }

    public Object b(int i) {
        Entry[] entryArr = this.f3366a;
        for (Entry entry = entryArr[(Integer.MAX_VALUE & i) % entryArr.length]; entry != null; entry = entry.d) {
            if (entry.f3367a == i) {
                return entry.c;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void c() {
        int length = this.f3366a.length;
        Entry[] entryArr = this.f3366a;
        int i = (length * 2) + 1;
        Entry[] entryArr2 = new Entry[i];
        this.c = (int) (((float) i) * this.d);
        this.f3366a = entryArr2;
        while (true) {
            int i2 = length - 1;
            if (length > 0) {
                Entry entry = entryArr[i2];
                while (entry != null) {
                    Entry entry2 = entry.d;
                    int i3 = (entry.f3367a & Integer.MAX_VALUE) % i;
                    entry.d = entryArr2[i3];
                    entryArr2[i3] = entry;
                    entry = entry2;
                }
                length = i2;
            } else {
                return;
            }
        }
    }

    public Object a(int i, Object obj) {
        Entry[] entryArr = this.f3366a;
        int i2 = Integer.MAX_VALUE & i;
        int length = i2 % entryArr.length;
        for (Entry entry = entryArr[length]; entry != null; entry = entry.d) {
            if (entry.f3367a == i) {
                Object obj2 = entry.c;
                entry.c = obj;
                return obj2;
            }
        }
        if (this.b >= this.c) {
            c();
            entryArr = this.f3366a;
            length = i2 % entryArr.length;
        }
        entryArr[length] = new Entry(i, i, obj, entryArr[length]);
        this.b++;
        return null;
    }

    public Object c(int i) {
        Entry[] entryArr = this.f3366a;
        int length = (Integer.MAX_VALUE & i) % entryArr.length;
        Entry entry = null;
        for (Entry entry2 = entryArr[length]; entry2 != null; entry2 = entry2.d) {
            if (entry2.f3367a == i) {
                if (entry != null) {
                    entry.d = entry2.d;
                } else {
                    entryArr[length] = entry2.d;
                }
                this.b--;
                Object obj = entry2.c;
                entry2.c = null;
                return obj;
            }
            entry = entry2;
        }
        return null;
    }

    public synchronized void d() {
        Entry[] entryArr = this.f3366a;
        int length = entryArr.length;
        while (true) {
            length--;
            if (length >= 0) {
                entryArr[length] = null;
            } else {
                this.b = 0;
            }
        }
    }
}
