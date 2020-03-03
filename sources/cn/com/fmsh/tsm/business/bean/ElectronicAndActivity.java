package cn.com.fmsh.tsm.business.bean;

import java.util.ArrayList;
import java.util.List;

public class ElectronicAndActivity {
    private List<Object4Activity> activites = new ArrayList();
    private List<Object4Url> urls = new ArrayList();

    public class Object4Url {
        public byte[] url;
        public byte[] urlType;

        public Object4Url(byte[] bArr, byte[] bArr2) {
            this.url = bArr;
            this.urlType = bArr2;
        }
    }

    public class Object4Activity {
        public byte[] activity;
        public byte[] ticketType;

        public Object4Activity(byte[] bArr, byte[] bArr2) {
            this.ticketType = bArr;
            this.activity = bArr2;
        }
    }

    public Object4Url[] getUrls() {
        return (Object4Url[]) this.urls.toArray(new Object4Url[0]);
    }

    public void addUrl(byte[] bArr, byte[] bArr2) {
        this.urls.add(new Object4Url(bArr, bArr2));
    }

    public Object4Activity[] getActivity() {
        return (Object4Activity[]) this.activites.toArray(new Object4Activity[0]);
    }

    public void addActivity(byte[] bArr, byte[] bArr2) {
        this.activites.add(new Object4Activity(bArr, bArr2));
    }
}
