package miuipub.hybrid;

public class PageContext {

    /* renamed from: a  reason: collision with root package name */
    private String f2949a;
    private String b;

    public String a() {
        return this.f2949a;
    }

    public void a(String str) {
        this.f2949a = str;
    }

    public String b() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }

    public int hashCode() {
        return 31 + (this.f2949a == null ? 0 : this.f2949a.hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PageContext pageContext = (PageContext) obj;
        if (this.f2949a == pageContext.f2949a) {
            return true;
        }
        return (this.f2949a == null || pageContext.f2949a == null || !this.f2949a.equals(pageContext.f2949a)) ? false : true;
    }
}
