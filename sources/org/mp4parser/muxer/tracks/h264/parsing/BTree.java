package org.mp4parser.muxer.tracks.h264.parsing;

public class BTree {

    /* renamed from: a  reason: collision with root package name */
    private BTree f4033a;
    private BTree b;
    private Object c;

    public void a(String str, Object obj) {
        BTree bTree;
        if (str.length() == 0) {
            this.c = obj;
            return;
        }
        if (str.charAt(0) == '0') {
            if (this.f4033a == null) {
                this.f4033a = new BTree();
            }
            bTree = this.f4033a;
        } else {
            if (this.b == null) {
                this.b = new BTree();
            }
            bTree = this.b;
        }
        bTree.a(str.substring(1), obj);
    }

    public BTree a(int i) {
        if (i == 0) {
            return this.f4033a;
        }
        return this.b;
    }

    public Object a() {
        return this.c;
    }
}
