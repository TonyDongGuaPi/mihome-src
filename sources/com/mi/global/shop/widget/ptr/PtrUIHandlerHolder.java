package com.mi.global.shop.widget.ptr;

class PtrUIHandlerHolder implements PtrUIHandler {

    /* renamed from: a  reason: collision with root package name */
    private PtrUIHandler f7252a;
    private PtrUIHandlerHolder b;

    private boolean a(PtrUIHandler ptrUIHandler) {
        return this.f7252a != null && this.f7252a == ptrUIHandler;
    }

    private PtrUIHandlerHolder() {
    }

    public boolean a() {
        return this.f7252a != null;
    }

    private PtrUIHandler c() {
        return this.f7252a;
    }

    public static void a(PtrUIHandlerHolder ptrUIHandlerHolder, PtrUIHandler ptrUIHandler) {
        if (ptrUIHandler != null && ptrUIHandlerHolder != null) {
            if (ptrUIHandlerHolder.f7252a == null) {
                ptrUIHandlerHolder.f7252a = ptrUIHandler;
                return;
            }
            while (!ptrUIHandlerHolder.a(ptrUIHandler)) {
                if (ptrUIHandlerHolder.b == null) {
                    PtrUIHandlerHolder ptrUIHandlerHolder2 = new PtrUIHandlerHolder();
                    ptrUIHandlerHolder2.f7252a = ptrUIHandler;
                    ptrUIHandlerHolder.b = ptrUIHandlerHolder2;
                    return;
                }
                ptrUIHandlerHolder = ptrUIHandlerHolder.b;
            }
        }
    }

    public static PtrUIHandlerHolder b() {
        return new PtrUIHandlerHolder();
    }

    public static PtrUIHandlerHolder b(PtrUIHandlerHolder ptrUIHandlerHolder, PtrUIHandler ptrUIHandler) {
        if (ptrUIHandlerHolder == null || ptrUIHandler == null || ptrUIHandlerHolder.f7252a == null) {
            return ptrUIHandlerHolder;
        }
        PtrUIHandlerHolder ptrUIHandlerHolder2 = ptrUIHandlerHolder;
        PtrUIHandlerHolder ptrUIHandlerHolder3 = null;
        do {
            if (!ptrUIHandlerHolder.a(ptrUIHandler)) {
                ptrUIHandlerHolder3 = ptrUIHandlerHolder;
                ptrUIHandlerHolder = ptrUIHandlerHolder.b;
                continue;
            } else if (ptrUIHandlerHolder3 == null) {
                ptrUIHandlerHolder2 = ptrUIHandlerHolder.b;
                ptrUIHandlerHolder.b = null;
                ptrUIHandlerHolder = ptrUIHandlerHolder2;
                continue;
            } else {
                ptrUIHandlerHolder3.b = ptrUIHandlerHolder.b;
                ptrUIHandlerHolder.b = null;
                ptrUIHandlerHolder = ptrUIHandlerHolder3.b;
                continue;
            }
        } while (ptrUIHandlerHolder != null);
        return ptrUIHandlerHolder2 == null ? new PtrUIHandlerHolder() : ptrUIHandlerHolder2;
    }

    public void onUIReset(PtrFrameLayout ptrFrameLayout) {
        PtrUIHandlerHolder ptrUIHandlerHolder = this;
        do {
            PtrUIHandler c = ptrUIHandlerHolder.c();
            if (c != null) {
                c.onUIReset(ptrFrameLayout);
            }
            ptrUIHandlerHolder = ptrUIHandlerHolder.b;
        } while (ptrUIHandlerHolder != null);
    }

    public void onUIRefreshPrepare(PtrFrameLayout ptrFrameLayout) {
        PtrUIHandlerHolder ptrUIHandlerHolder = this;
        do {
            PtrUIHandler c = ptrUIHandlerHolder.c();
            if (c != null) {
                c.onUIRefreshPrepare(ptrFrameLayout);
            }
            ptrUIHandlerHolder = ptrUIHandlerHolder.b;
        } while (ptrUIHandlerHolder != null);
    }

    public void onUIRefreshBegin(PtrFrameLayout ptrFrameLayout) {
        PtrUIHandlerHolder ptrUIHandlerHolder = this;
        do {
            PtrUIHandler c = ptrUIHandlerHolder.c();
            if (c != null) {
                c.onUIRefreshBegin(ptrFrameLayout);
            }
            ptrUIHandlerHolder = ptrUIHandlerHolder.b;
        } while (ptrUIHandlerHolder != null);
    }

    public void onUIRefreshComplete(PtrFrameLayout ptrFrameLayout) {
        PtrUIHandlerHolder ptrUIHandlerHolder = this;
        do {
            PtrUIHandler c = ptrUIHandlerHolder.c();
            if (c != null) {
                c.onUIRefreshComplete(ptrFrameLayout);
            }
            ptrUIHandlerHolder = ptrUIHandlerHolder.b;
        } while (ptrUIHandlerHolder != null);
    }

    public void onUIPositionChange(PtrFrameLayout ptrFrameLayout, boolean z, byte b2, int i, int i2, float f, float f2) {
        PtrUIHandlerHolder ptrUIHandlerHolder = this;
        do {
            PtrUIHandler c = ptrUIHandlerHolder.c();
            if (c != null) {
                c.onUIPositionChange(ptrFrameLayout, z, b2, i, i2, f, f2);
            }
            ptrUIHandlerHolder = ptrUIHandlerHolder.b;
        } while (ptrUIHandlerHolder != null);
    }
}
