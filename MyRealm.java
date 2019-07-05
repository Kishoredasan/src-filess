
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import org.apache.catalina.realm.GenericPrincipal;
import org.apache.catalina.realm.RealmBase;
import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Hex;
import file.TOTP;

public class MyRealm extends RealmBase {

private String username;

private String password;
public static String getRandomSecretKey() {
     
        String secretKey = "abcdefghijklmnopqrstuvwxyz";
		
        return secretKey.replace('1', 'I').replace('0', 'O');
    }
	public static String getTOTPCode(String secretKey) {
        String normalizedBase32Key = secretKey.replace(" ", "").toUpperCase();
        Base32 base32 = new Base32();
        byte[] bytes = base32.decode(normalizedBase32Key);
        String hexKey = Hex.encodeHexString(bytes);
        long time = (System.currentTimeMillis() / 1000) / 30;
        String hexTime = Long.toHexString(time);
        return TOTP.generateTOTP(hexKey, hexTime, "6");
    }
@Override
public Principal authenticate(String username, String credentials) {
 String secretKey = getRandomSecretKey();
this.username = username;
this.password = credentials;

String code = getTOTPCode(secretKey);
if (this.username.equals("kishore")) {
if (this.password.equals(code))
return getPrincipal(username);
else
return null;
} else {
return null;
}

}

@Override
protected Principal getPrincipal(String username) {
List<String> roles = new ArrayList<String>();
roles.add("user");
return new GenericPrincipal(username, password, roles);
}

@Override
protected String getPassword(String string) {
return null;
}
}