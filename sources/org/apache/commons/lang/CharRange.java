package org.apache.commons.lang;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.apache.commons.lang.text.StrBuilder;

public final class CharRange implements Serializable {
    private static final long serialVersionUID = 8270183163158333422L;

    /* renamed from: a  reason: collision with root package name */
    private transient String f3357a;
    private final char end;
    private final boolean negated;
    private final char start;

    /* renamed from: org.apache.commons.lang.CharRange$1  reason: invalid class name */
    class AnonymousClass1 {
    }

    static boolean access$100(CharRange charRange) {
        return charRange.negated;
    }

    static char access$200(CharRange charRange) {
        return charRange.start;
    }

    static char access$300(CharRange charRange) {
        return charRange.end;
    }

    public static CharRange is(char c) {
        return new CharRange(c, c, false);
    }

    public static CharRange isNot(char c) {
        return new CharRange(c, c, true);
    }

    public static CharRange isIn(char c, char c2) {
        return new CharRange(c, c2, false);
    }

    public static CharRange isNotIn(char c, char c2) {
        return new CharRange(c, c2, true);
    }

    public CharRange(char c) {
        this(c, c, false);
    }

    public CharRange(char c, boolean z) {
        this(c, c, z);
    }

    public CharRange(char c, char c2) {
        this(c, c2, false);
    }

    public CharRange(char c, char c2, boolean z) {
        if (c > c2) {
            char c3 = c2;
            c2 = c;
            c = c3;
        }
        this.start = c;
        this.end = c2;
        this.negated = z;
    }

    public char getStart() {
        return this.start;
    }

    public char getEnd() {
        return this.end;
    }

    public boolean isNegated() {
        return this.negated;
    }

    public boolean contains(char c) {
        return (c >= this.start && c <= this.end) != this.negated;
    }

    public boolean contains(CharRange charRange) {
        if (charRange == null) {
            throw new IllegalArgumentException("The Range must not be null");
        } else if (this.negated) {
            if (charRange.negated) {
                if (this.start < charRange.start || this.end > charRange.end) {
                    return false;
                }
                return true;
            } else if (charRange.end < this.start || charRange.start > this.end) {
                return true;
            } else {
                return false;
            }
        } else if (charRange.negated) {
            if (this.start == 0 && this.end == 65535) {
                return true;
            }
            return false;
        } else if (this.start > charRange.start || this.end < charRange.end) {
            return false;
        } else {
            return true;
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CharRange)) {
            return false;
        }
        CharRange charRange = (CharRange) obj;
        if (this.start == charRange.start && this.end == charRange.end && this.negated == charRange.negated) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.start + 'S' + (this.end * 7) + (this.negated ? 1 : 0);
    }

    public String toString() {
        if (this.f3357a == null) {
            StrBuilder strBuilder = new StrBuilder(4);
            if (isNegated()) {
                strBuilder.a('^');
            }
            strBuilder.a(this.start);
            if (this.start != this.end) {
                strBuilder.a('-');
                strBuilder.a(this.end);
            }
            this.f3357a = strBuilder.toString();
        }
        return this.f3357a;
    }

    public Iterator iterator() {
        return new CharacterIterator(this, (AnonymousClass1) null);
    }

    private static class CharacterIterator implements Iterator {

        /* renamed from: a  reason: collision with root package name */
        private char f3358a;
        private final CharRange b;
        private boolean c;

        CharacterIterator(CharRange charRange, AnonymousClass1 r2) {
            this(charRange);
        }

        private CharacterIterator(CharRange charRange) {
            this.b = charRange;
            this.c = true;
            if (!CharRange.access$100(this.b)) {
                this.f3358a = CharRange.access$200(this.b);
            } else if (CharRange.access$200(this.b) != 0) {
                this.f3358a = 0;
            } else if (CharRange.access$300(this.b) == 65535) {
                this.c = false;
            } else {
                this.f3358a = (char) (CharRange.access$300(this.b) + 1);
            }
        }

        private void a() {
            if (CharRange.access$100(this.b)) {
                if (this.f3358a == 65535) {
                    this.c = false;
                } else if (this.f3358a + 1 != CharRange.access$200(this.b)) {
                    this.f3358a = (char) (this.f3358a + 1);
                } else if (CharRange.access$300(this.b) == 65535) {
                    this.c = false;
                } else {
                    this.f3358a = (char) (CharRange.access$300(this.b) + 1);
                }
            } else if (this.f3358a < CharRange.access$300(this.b)) {
                this.f3358a = (char) (this.f3358a + 1);
            } else {
                this.c = false;
            }
        }

        public boolean hasNext() {
            return this.c;
        }

        public Object next() {
            if (this.c) {
                char c2 = this.f3358a;
                a();
                return new Character(c2);
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
