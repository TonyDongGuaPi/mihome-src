package com.adobe.xmp.impl;

import com.adobe.xmp.XMPException;
import com.adobe.xmp.XMPIterator;
import com.adobe.xmp.XMPMetaFactory;
import com.adobe.xmp.impl.xpath.XMPPath;
import com.adobe.xmp.impl.xpath.XMPPathParser;
import com.adobe.xmp.options.IteratorOptions;
import com.adobe.xmp.options.PropertyOptions;
import com.adobe.xmp.properties.XMPPropertyInfo;
import com.taobao.weex.el.parse.Operators;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class XMPIteratorImpl implements XMPIterator {

    /* renamed from: a  reason: collision with root package name */
    protected boolean f689a = false;
    protected boolean b = false;
    private IteratorOptions c;
    private String d = null;
    private Iterator e = null;

    private class NodeIterator implements Iterator {

        /* renamed from: a  reason: collision with root package name */
        protected static final int f690a = 0;
        protected static final int b = 1;
        protected static final int c = 2;
        private int e = 0;
        private XMPNode f;
        private String g;
        private Iterator h = null;
        private int i = 0;
        private Iterator j = Collections.EMPTY_LIST.iterator();
        private XMPPropertyInfo k = null;

        public NodeIterator() {
        }

        public NodeIterator(XMPNode xMPNode, String str, int i2) {
            this.f = xMPNode;
            this.e = 0;
            if (xMPNode.n().o()) {
                XMPIteratorImpl.this.a(xMPNode.l());
            }
            this.g = a(xMPNode, str, i2);
        }

        private boolean b(Iterator it) {
            if (XMPIteratorImpl.this.f689a) {
                XMPIteratorImpl.this.f689a = false;
                this.j = Collections.EMPTY_LIST.iterator();
            }
            if (!this.j.hasNext() && it.hasNext()) {
                this.i++;
                this.j = new NodeIterator((XMPNode) it.next(), this.g, this.i);
            }
            if (!this.j.hasNext()) {
                return false;
            }
            this.k = (XMPPropertyInfo) this.j.next();
            return true;
        }

        /* access modifiers changed from: protected */
        public XMPPropertyInfo a(XMPNode xMPNode, String str, String str2) {
            final String m = xMPNode.n().o() ? null : xMPNode.m();
            final XMPNode xMPNode2 = xMPNode;
            final String str3 = str;
            final String str4 = str2;
            return new XMPPropertyInfo() {
                public String a() {
                    if (xMPNode2.n().o()) {
                        return str3;
                    }
                    return XMPMetaFactory.a().b(new QName(xMPNode2.l()).c());
                }

                public String b() {
                    return str4;
                }

                public String c() {
                    return m;
                }

                public PropertyOptions d() {
                    return xMPNode2.n();
                }

                public String e() {
                    return null;
                }
            };
        }

        /* access modifiers changed from: protected */
        public String a(XMPNode xMPNode, String str, int i2) {
            String str2;
            String str3;
            if (xMPNode.b() == null || xMPNode.n().o()) {
                return null;
            }
            if (xMPNode.b().n().k()) {
                str2 = "";
                str3 = Operators.ARRAY_START_STR + String.valueOf(i2) + Operators.ARRAY_END_STR;
            } else {
                str2 = "/";
                str3 = xMPNode.l();
            }
            if (str == null || str.length() == 0) {
                return str3;
            }
            if (XMPIteratorImpl.this.c().b()) {
                return !str3.startsWith("?") ? str3 : str3.substring(1);
            }
            return str + str2 + str3;
        }

        /* access modifiers changed from: protected */
        public void a(XMPPropertyInfo xMPPropertyInfo) {
            this.k = xMPPropertyInfo;
        }

        /* access modifiers changed from: protected */
        public void a(Iterator it) {
            this.h = it;
        }

        /* access modifiers changed from: protected */
        public boolean a() {
            this.e = 1;
            if (this.f.b() == null || (XMPIteratorImpl.this.c().c() && this.f.h())) {
                return hasNext();
            }
            this.k = a(this.f, XMPIteratorImpl.this.d(), this.g);
            return true;
        }

        /* access modifiers changed from: protected */
        public Iterator b() {
            return this.h;
        }

        /* access modifiers changed from: protected */
        public XMPPropertyInfo c() {
            return this.k;
        }

        public boolean hasNext() {
            if (this.k != null) {
                return true;
            }
            if (this.e == 0) {
                return a();
            }
            if (this.e == 1) {
                if (this.h == null) {
                    this.h = this.f.i();
                }
                boolean b2 = b(this.h);
                if (b2 || !this.f.j() || XMPIteratorImpl.this.c().d()) {
                    return b2;
                }
                this.e = 2;
                this.h = null;
                return hasNext();
            }
            if (this.h == null) {
                this.h = this.f.k();
            }
            return b(this.h);
        }

        public Object next() {
            if (hasNext()) {
                XMPPropertyInfo xMPPropertyInfo = this.k;
                this.k = null;
                return xMPPropertyInfo;
            }
            throw new NoSuchElementException("There are no more nodes to return");
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class NodeIteratorChildren extends NodeIterator {
        private String f;
        private Iterator g;
        private int h = 0;

        public NodeIteratorChildren(XMPNode xMPNode, String str) {
            super();
            if (xMPNode.n().o()) {
                XMPIteratorImpl.this.a(xMPNode.l());
            }
            this.f = a(xMPNode, str, 1);
            this.g = xMPNode.i();
        }

        public boolean hasNext() {
            if (c() != null) {
                return true;
            }
            if (XMPIteratorImpl.this.f689a || !this.g.hasNext()) {
                return false;
            }
            XMPNode xMPNode = (XMPNode) this.g.next();
            this.h++;
            String str = null;
            if (xMPNode.n().o()) {
                XMPIteratorImpl.this.a(xMPNode.l());
            } else if (xMPNode.b() != null) {
                str = a(xMPNode, this.f, this.h);
            }
            if (XMPIteratorImpl.this.c().c() && xMPNode.h()) {
                return hasNext();
            }
            a(a(xMPNode, XMPIteratorImpl.this.d(), str));
            return true;
        }
    }

    public XMPIteratorImpl(XMPMetaImpl xMPMetaImpl, String str, String str2, IteratorOptions iteratorOptions) throws XMPException {
        XMPNode xMPNode;
        String str3 = null;
        this.c = iteratorOptions == null ? new IteratorOptions() : iteratorOptions;
        boolean z = str != null && str.length() > 0;
        boolean z2 = str2 != null && str2.length() > 0;
        if (!z && !z2) {
            xMPNode = xMPMetaImpl.f();
        } else if (z && z2) {
            XMPPath a2 = XMPPathParser.a(str, str2);
            XMPPath xMPPath = new XMPPath();
            for (int i = 0; i < a2.a() - 1; i++) {
                xMPPath.a(a2.a(i));
            }
            xMPNode = XMPNodeUtils.a(xMPMetaImpl.f(), a2, false, (PropertyOptions) null);
            this.d = str;
            str3 = xMPPath.toString();
        } else if (!z || z2) {
            throw new XMPException("Schema namespace URI is required", 101);
        } else {
            xMPNode = XMPNodeUtils.a(xMPMetaImpl.f(), str, false);
        }
        if (xMPNode != null) {
            this.e = !this.c.a() ? new NodeIterator(xMPNode, str3, 1) : new NodeIteratorChildren(xMPNode, str3);
        } else {
            this.e = Collections.EMPTY_LIST.iterator();
        }
    }

    public void a() {
        this.b = true;
    }

    /* access modifiers changed from: protected */
    public void a(String str) {
        this.d = str;
    }

    public void b() {
        a();
        this.f689a = true;
    }

    /* access modifiers changed from: protected */
    public IteratorOptions c() {
        return this.c;
    }

    /* access modifiers changed from: protected */
    public String d() {
        return this.d;
    }

    public boolean hasNext() {
        return this.e.hasNext();
    }

    public Object next() {
        return this.e.next();
    }

    public void remove() {
        throw new UnsupportedOperationException("The XMPIterator does not support remove().");
    }
}
