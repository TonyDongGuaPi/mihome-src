package com.adobe.xmp.impl;

import com.adobe.xmp.XMPConst;
import com.adobe.xmp.XMPException;
import com.adobe.xmp.options.PropertyOptions;
import com.taobao.weex.el.parse.Operators;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

class XMPNode implements Comparable {

    /* renamed from: a  reason: collision with root package name */
    static final /* synthetic */ boolean f695a = (!XMPNode.class.desiredAssertionStatus());
    private String b;
    private String c;
    private XMPNode d;
    private List e;
    private List f;
    private PropertyOptions g;
    private boolean h;
    private boolean i;
    private boolean j;
    private boolean k;

    public XMPNode(String str, PropertyOptions propertyOptions) {
        this(str, (String) null, propertyOptions);
    }

    public XMPNode(String str, String str2, PropertyOptions propertyOptions) {
        this.e = null;
        this.f = null;
        this.g = null;
        this.b = str;
        this.c = str2;
        this.g = propertyOptions;
    }

    private XMPNode a(List list, String str) {
        if (list == null) {
            return null;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            XMPNode xMPNode = (XMPNode) it.next();
            if (xMPNode.l().equals(str)) {
                return xMPNode;
            }
        }
        return null;
    }

    private void a(StringBuffer stringBuffer, boolean z, int i2, int i3) {
        int i4 = 0;
        for (int i5 = 0; i5 < i2; i5++) {
            stringBuffer.append(9);
        }
        if (this.d != null) {
            if (n().c()) {
                stringBuffer.append(Operators.CONDITION_IF);
            } else if (b().n().k()) {
                stringBuffer.append(Operators.ARRAY_START);
                stringBuffer.append(i3);
                stringBuffer.append(Operators.ARRAY_END);
            }
            stringBuffer.append(this.b);
        } else {
            stringBuffer.append("ROOT NODE");
            if (this.b != null && this.b.length() > 0) {
                stringBuffer.append(" (");
                stringBuffer.append(this.b);
                stringBuffer.append(Operators.BRACKET_END);
            }
        }
        if (this.c != null && this.c.length() > 0) {
            stringBuffer.append(" = \"");
            stringBuffer.append(this.c);
            stringBuffer.append('\"');
        }
        if (n().d(-1)) {
            stringBuffer.append("\t(");
            stringBuffer.append(n().toString());
            stringBuffer.append(" : ");
            stringBuffer.append(n().j());
            stringBuffer.append(Operators.BRACKET_END);
        }
        stringBuffer.append(10);
        if (z && j()) {
            XMPNode[] xMPNodeArr = (XMPNode[]) x().toArray(new XMPNode[f()]);
            int i6 = 0;
            while (xMPNodeArr.length > i6 && (XMPConst.ak.equals(xMPNodeArr[i6].l()) || XMPConst.al.equals(xMPNodeArr[i6].l()))) {
                i6++;
            }
            Arrays.sort(xMPNodeArr, i6, xMPNodeArr.length);
            int i7 = 0;
            while (i7 < xMPNodeArr.length) {
                i7++;
                xMPNodeArr[i7].a(stringBuffer, z, i2 + 2, i7);
            }
        }
        if (z && h()) {
            XMPNode[] xMPNodeArr2 = (XMPNode[]) w().toArray(new XMPNode[e()]);
            if (!n().k()) {
                Arrays.sort(xMPNodeArr2);
            }
            while (i4 < xMPNodeArr2.length) {
                i4++;
                xMPNodeArr2[i4].a(stringBuffer, z, i2 + 1, i4);
            }
        }
    }

    private void e(String str) throws XMPException {
        if (!XMPConst.ai.equals(str) && a(str) != null) {
            throw new XMPException("Duplicate property or field node '" + str + "'", 203);
        }
    }

    private void f(String str) throws XMPException {
        if (!XMPConst.ai.equals(str) && b(str) != null) {
            throw new XMPException("Duplicate '" + str + "' qualifier", 203);
        }
    }

    private boolean u() {
        return XMPConst.ak.equals(this.b);
    }

    private boolean v() {
        return XMPConst.al.equals(this.b);
    }

    private List w() {
        if (this.e == null) {
            this.e = new ArrayList(0);
        }
        return this.e;
    }

    private List x() {
        if (this.f == null) {
            this.f = new ArrayList(0);
        }
        return this.f;
    }

    public XMPNode a(int i2) {
        return (XMPNode) w().get(i2 - 1);
    }

    public XMPNode a(String str) {
        return a(w(), str);
    }

    public String a(boolean z) {
        StringBuffer stringBuffer = new StringBuffer(512);
        a(stringBuffer, z, 0, 0);
        return stringBuffer.toString();
    }

    public void a() {
        this.g = null;
        this.b = null;
        this.c = null;
        this.e = null;
        this.f = null;
    }

    public void a(int i2, XMPNode xMPNode) throws XMPException {
        e(xMPNode.l());
        xMPNode.f(this);
        w().add(i2 - 1, xMPNode);
    }

    public void a(XMPNode xMPNode) throws XMPException {
        e(xMPNode.l());
        xMPNode.f(this);
        w().add(xMPNode);
    }

    public void a(PropertyOptions propertyOptions) {
        this.g = propertyOptions;
    }

    public XMPNode b() {
        return this.d;
    }

    public XMPNode b(String str) {
        return a(this.f, str);
    }

    public void b(int i2) {
        w().remove(i2 - 1);
        c();
    }

    public void b(int i2, XMPNode xMPNode) {
        xMPNode.f(this);
        w().set(i2 - 1, xMPNode);
    }

    public void b(XMPNode xMPNode) {
        w().remove(xMPNode);
        c();
    }

    public void b(boolean z) {
        this.h = z;
    }

    public XMPNode c(int i2) {
        return (XMPNode) x().get(i2 - 1);
    }

    /* access modifiers changed from: protected */
    public void c() {
        if (this.e.isEmpty()) {
            this.e = null;
        }
    }

    public void c(XMPNode xMPNode) throws XMPException {
        List x;
        boolean d2;
        f(xMPNode.l());
        xMPNode.f(this);
        xMPNode.n().c(true);
        n().b(true);
        if (xMPNode.u()) {
            this.g.d(true);
            x = x();
            d2 = false;
        } else if (xMPNode.v()) {
            this.g.e(true);
            x = x();
            d2 = this.g.d();
        } else {
            x().add(xMPNode);
            return;
        }
        x.add(d2 ? 1 : 0, xMPNode);
    }

    public void c(String str) {
        this.b = str;
    }

    public void c(boolean z) {
        this.i = z;
    }

    public Object clone() {
        PropertyOptions propertyOptions;
        try {
            propertyOptions = new PropertyOptions(n().i());
        } catch (XMPException unused) {
            propertyOptions = new PropertyOptions();
        }
        XMPNode xMPNode = new XMPNode(this.b, this.c, propertyOptions);
        e(xMPNode);
        return xMPNode;
    }

    public int compareTo(Object obj) {
        String str;
        String l;
        if (n().o()) {
            str = this.c;
            l = ((XMPNode) obj).m();
        } else {
            str = this.b;
            l = ((XMPNode) obj).l();
        }
        return str.compareTo(l);
    }

    public void d() {
        this.e = null;
    }

    public void d(XMPNode xMPNode) {
        PropertyOptions n = n();
        if (xMPNode.u()) {
            n.d(false);
        } else if (xMPNode.v()) {
            n.e(false);
        }
        x().remove(xMPNode);
        if (this.f.isEmpty()) {
            n.b(false);
            this.f = null;
        }
    }

    public void d(String str) {
        this.c = str;
    }

    public void d(boolean z) {
        this.j = z;
    }

    public int e() {
        if (this.e != null) {
            return this.e.size();
        }
        return 0;
    }

    public void e(XMPNode xMPNode) {
        try {
            Iterator i2 = i();
            while (i2.hasNext()) {
                xMPNode.a((XMPNode) ((XMPNode) i2.next()).clone());
            }
            Iterator k2 = k();
            while (k2.hasNext()) {
                xMPNode.c((XMPNode) ((XMPNode) k2.next()).clone());
            }
        } catch (XMPException unused) {
            if (!f695a) {
                throw new AssertionError();
            }
        }
    }

    public void e(boolean z) {
        this.k = z;
    }

    public int f() {
        if (this.f != null) {
            return this.f.size();
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public void f(XMPNode xMPNode) {
        this.d = xMPNode;
    }

    public void g() {
        PropertyOptions n = n();
        n.b(false);
        n.d(false);
        n.e(false);
        this.f = null;
    }

    public boolean h() {
        return this.e != null && this.e.size() > 0;
    }

    public Iterator i() {
        return this.e != null ? w().iterator() : Collections.EMPTY_LIST.listIterator();
    }

    public boolean j() {
        return this.f != null && this.f.size() > 0;
    }

    public Iterator k() {
        if (this.f == null) {
            return Collections.EMPTY_LIST.iterator();
        }
        final Iterator it = x().iterator();
        return new Iterator() {
            public boolean hasNext() {
                return it.hasNext();
            }

            public Object next() {
                return it.next();
            }

            public void remove() {
                throw new UnsupportedOperationException("remove() is not allowed due to the internal contraints");
            }
        };
    }

    public String l() {
        return this.b;
    }

    public String m() {
        return this.c;
    }

    public PropertyOptions n() {
        if (this.g == null) {
            this.g = new PropertyOptions();
        }
        return this.g;
    }

    public boolean o() {
        return this.h;
    }

    public boolean p() {
        return this.i;
    }

    public boolean q() {
        return this.j;
    }

    public boolean r() {
        return this.k;
    }

    public void s() {
        if (j()) {
            XMPNode[] xMPNodeArr = (XMPNode[]) x().toArray(new XMPNode[f()]);
            int i2 = 0;
            while (xMPNodeArr.length > i2 && (XMPConst.ak.equals(xMPNodeArr[i2].l()) || XMPConst.al.equals(xMPNodeArr[i2].l()))) {
                xMPNodeArr[i2].s();
                i2++;
            }
            Arrays.sort(xMPNodeArr, i2, xMPNodeArr.length);
            ListIterator listIterator = this.f.listIterator();
            for (int i3 = 0; i3 < xMPNodeArr.length; i3++) {
                listIterator.next();
                listIterator.set(xMPNodeArr[i3]);
                xMPNodeArr[i3].s();
            }
        }
        if (h()) {
            if (!n().k()) {
                Collections.sort(this.e);
            }
            Iterator i4 = i();
            while (i4.hasNext()) {
                ((XMPNode) i4.next()).s();
            }
        }
    }

    public List t() {
        return Collections.unmodifiableList(new ArrayList(w()));
    }
}
