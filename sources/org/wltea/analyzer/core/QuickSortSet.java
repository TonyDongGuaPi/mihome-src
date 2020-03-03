package org.wltea.analyzer.core;

class QuickSortSet {

    /* renamed from: a  reason: collision with root package name */
    private Cell f4202a;
    private Cell b;
    private int c = 0;

    QuickSortSet() {
    }

    /* access modifiers changed from: package-private */
    public boolean d(Lexeme lexeme) {
        Cell cell = new Cell(lexeme);
        if (this.c == 0) {
            this.f4202a = cell;
            this.b = cell;
            this.c++;
            return true;
        } else if (this.b.compareTo(cell) == 0) {
            return false;
        } else {
            if (this.b.compareTo(cell) < 0) {
                this.b.c = cell;
                cell.b = this.b;
                this.b = cell;
                this.c++;
                return true;
            } else if (this.f4202a.compareTo(cell) > 0) {
                this.f4202a.b = cell;
                cell.c = this.f4202a;
                this.f4202a = cell;
                this.c++;
                return true;
            } else {
                Cell cell2 = this.b;
                while (cell2 != null && cell2.compareTo(cell) > 0) {
                    cell2 = cell2.b;
                }
                if (cell2.compareTo(cell) == 0 || cell2.compareTo(cell) >= 0) {
                    return false;
                }
                cell.b = cell2;
                cell.c = cell2.c;
                cell2.c.b = cell;
                cell2.c = cell;
                this.c++;
                return true;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public Lexeme i() {
        if (this.f4202a != null) {
            return this.f4202a.d;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public Lexeme j() {
        if (this.c == 1) {
            Lexeme d = this.f4202a.d;
            this.f4202a = null;
            this.b = null;
            this.c--;
            return d;
        } else if (this.c <= 1) {
            return null;
        } else {
            Lexeme d2 = this.f4202a.d;
            this.f4202a = this.f4202a.c;
            this.c--;
            return d2;
        }
    }

    /* access modifiers changed from: package-private */
    public Lexeme k() {
        if (this.b != null) {
            return this.b.d;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public Lexeme l() {
        if (this.c == 1) {
            Lexeme d = this.f4202a.d;
            this.f4202a = null;
            this.b = null;
            this.c--;
            return d;
        } else if (this.c <= 1) {
            return null;
        } else {
            Lexeme d2 = this.b.d;
            this.b = this.b.b;
            this.c--;
            return d2;
        }
    }

    /* access modifiers changed from: package-private */
    public int m() {
        return this.c;
    }

    /* access modifiers changed from: package-private */
    public boolean n() {
        return this.c == 0;
    }

    /* access modifiers changed from: package-private */
    public Cell o() {
        return this.f4202a;
    }

    class Cell implements Comparable<Cell> {
        /* access modifiers changed from: private */
        public Cell b;
        /* access modifiers changed from: private */
        public Cell c;
        /* access modifiers changed from: private */
        public Lexeme d;

        Cell(Lexeme lexeme) {
            if (lexeme != null) {
                this.d = lexeme;
                return;
            }
            throw new IllegalArgumentException("lexeme must not be null");
        }

        /* renamed from: a */
        public int compareTo(Cell cell) {
            return this.d.compareTo(cell.d);
        }

        public Cell a() {
            return this.b;
        }

        public Cell b() {
            return this.c;
        }

        public Lexeme c() {
            return this.d;
        }
    }
}
