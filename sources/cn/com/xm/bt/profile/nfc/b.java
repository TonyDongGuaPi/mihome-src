package cn.com.xm.bt.profile.nfc;

public class b {

    /* renamed from: a  reason: collision with root package name */
    private c f586a = null;

    public b(cn.com.xm.bt.c.b bVar) {
        this.f586a = new c(bVar);
    }

    public synchronized HMNFCStatus a(d dVar) {
        if (!this.f586a.a()) {
            return null;
        }
        this.f586a.a(dVar);
        return new HMNFCStatus(this.f586a.c());
    }

    public synchronized HMNFCStatus a() {
        int d;
        d = this.f586a.d();
        this.f586a.b();
        this.f586a.a((d) null);
        return new HMNFCStatus(d);
    }

    public synchronized ApduResponse a(byte[] bArr, int i, boolean z) {
        if (bArr != null) {
            if (bArr.length != 0) {
                return this.f586a.a(bArr, i, z);
            }
        }
        return null;
    }
}
