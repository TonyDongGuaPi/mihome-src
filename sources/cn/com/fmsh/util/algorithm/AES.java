package cn.com.fmsh.util.algorithm;

import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.Util4Java;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;
import com.coloros.mcssdk.c.a;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class AES {
    private static FMLog log = LogFactory.getInstance().getLog();

    public static byte[] encrypt4ECB(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr.length < 1 || bArr2 == null || bArr2.length < 1) {
            return null;
        }
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, a.b);
            Cipher instance = Cipher.getInstance("AES/ECB/NoPadding");
            instance.init(1, secretKeySpec);
            return instance.doFinal(bArr);
        } catch (NoSuchAlgorithmException e) {
            if (log != null) {
                log.error(DES.class.getName(), Util4Java.getExceptionInfo(e));
            }
            System.out.println(Util4Java.getExceptionInfo(e));
            return null;
        } catch (NoSuchPaddingException e2) {
            if (log != null) {
                log.error(DES.class.getName(), Util4Java.getExceptionInfo(e2));
            }
            System.out.println(Util4Java.getExceptionInfo(e2));
            return null;
        } catch (InvalidKeyException e3) {
            if (log != null) {
                log.error(DES.class.getName(), Util4Java.getExceptionInfo(e3));
            }
            System.out.println(Util4Java.getExceptionInfo(e3));
            return null;
        } catch (IllegalBlockSizeException e4) {
            if (log != null) {
                log.error(DES.class.getName(), Util4Java.getExceptionInfo(e4));
            }
            System.out.println(Util4Java.getExceptionInfo(e4));
            return null;
        } catch (BadPaddingException e5) {
            if (log != null) {
                log.error(DES.class.getName(), Util4Java.getExceptionInfo(e5));
            }
            System.out.println(Util4Java.getExceptionInfo(e5));
            return null;
        } catch (Exception e6) {
            if (log != null) {
                log.error(DES.class.getName(), Util4Java.getExceptionInfo(e6));
            }
            System.out.println(Util4Java.getExceptionInfo(e6));
            return null;
        }
    }

    public static byte[] decrypt4ECB(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr.length < 1 || bArr2 == null || bArr2.length < 1) {
            return null;
        }
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, a.b);
            Cipher instance = Cipher.getInstance("AES/ECB/NoPadding");
            instance.init(2, secretKeySpec);
            return instance.doFinal(bArr);
        } catch (NoSuchAlgorithmException e) {
            if (log != null) {
                log.error(DES.class.getName(), Util4Java.getExceptionInfo(e));
            }
            System.out.println(Util4Java.getExceptionInfo(e));
            return null;
        } catch (NoSuchPaddingException e2) {
            if (log != null) {
                log.error(DES.class.getName(), Util4Java.getExceptionInfo(e2));
            }
            System.out.println(Util4Java.getExceptionInfo(e2));
            return null;
        } catch (InvalidKeyException e3) {
            if (log != null) {
                log.error(DES.class.getName(), Util4Java.getExceptionInfo(e3));
            }
            System.out.println(Util4Java.getExceptionInfo(e3));
            return null;
        } catch (IllegalBlockSizeException e4) {
            if (log != null) {
                log.error(DES.class.getName(), Util4Java.getExceptionInfo(e4));
            }
            System.out.println(Util4Java.getExceptionInfo(e4));
            return null;
        } catch (BadPaddingException e5) {
            if (log != null) {
                log.error(DES.class.getName(), Util4Java.getExceptionInfo(e5));
            }
            System.out.println(Util4Java.getExceptionInfo(e5));
            return null;
        } catch (Exception e6) {
            if (log != null) {
                log.error(DES.class.getName(), Util4Java.getExceptionInfo(e6));
            }
            System.out.println(Util4Java.getExceptionInfo(e6));
            return null;
        }
    }

    public static void main(String[] strArr) {
        byte[] bArr;
        try {
            bArr = FM_Bytes.bytesToHexString(Digest.md5("vd42ga56a5ragerDm0Q79Z17MDcgeGpQ".getBytes("UTF-8"))).toLowerCase().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            bArr = null;
        }
        byte[] decrypt4ECB = decrypt4ECB(FM_Bytes.hexStringToBytes("3B9D9A9CD4766BB542F615A4B50694C73EE580A006B485880D9629D32537F2094B5E76E0FAFAAE91CC15A2AE16E4946D38A53E1165409D2CA9F77E0185A818877D188B86AFE70C88D75866D4F2900305851B6E809A27F41C05A5865022EC5641CD6C73C95A2815BEFAA7DF0C29215BB557A3824D3D6B96230C3697D5B7393E598FCFF4CF41AF1A686F38A75D620E5B6D327C1928F3B65886F96C5849EBFE380CA092F239CFD457ACC4412FBA755C47AB04E7668B74F73F45B53704BE1CDAED2B2F23FC929896DA98349F2EC12A83E0B1D151842F559DEFF326AA2370607CAC2A36EC2910B171EABA25988D35259FF37A1D913F359CAC68051633EFCB6E35753842C01C95EE356213B356DAFF3619FE2236A2CCF325CBA2A36ADAAAD2A0E0C66660BC395AE208794199CCBCF2B8AE83F4503DE56564A3BE9B7BD50DE5536A8C1F4D7E3CA827E72E67B0729C5A2F6A08CC7995460E457FEFBD0E1506E61BE228F92B061BDB336C007141E28B381584E9407D2D8CC7765CF7063FC51D7B424CE0830530BC6A95642AA826BEC9901AF83CDF4833681888A3F7C891FDD7C26DE5E2F93CBD8B45C11760D840D8ED848E3C6473147B5044B4D9BDEEA95D5A413EECEA801ECFB8FE35A40C29D82715D50F85F991F47A0D5FDC2B4FF6F16A58133A844400DA5577251B25B69217D89C389338050BA49C1D750FBFDCD022737981376298568DE6B4333FBAD5C28290E24EC81CABE299E89E20437247A002463A45F71F3DA0E358C98BE203B544F537C26403C7F340D07CE127454606CA6D27AB487FA904267FF81715D34DA0317EE3C53DF287EC1F9698FAC92141B9E1F48EC15D35B16E771790FB5D30AD6F2660F4D24E26DD5E7BB15DF20FA3EE78C36B8D9737A3D21C8308E6BBB225460DA170F3FC1131AA3198B2ED1164E1E467AF2416C721AD136B00A4E46FCC8B536D196B04491599A96B4A6B24421D637EDFF940D997ED36AF448ED985AD7A1E669EAB3E859CDBC716D5B51A052BC1788007479A419D67363F4FB6DA2101C9594EB3B2BE4F7CD36EDF244350137F74849CF746E79C524C4BD68B4B926C2A55CD4569D93D635AB851554B9A"), bArr);
        PrintStream printStream = System.out;
        printStream.println("ciphertext:" + FM_Bytes.bytesToHexString(decrypt4ECB));
        if (decrypt4ECB != null) {
            try {
                System.out.println(new String(decrypt4ECB, "UTF-8"));
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
            }
        } else {
            System.out.println("加密失败");
        }
    }
}
