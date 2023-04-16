package authenticator;

import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.GeneralSecurityException;
import java.time.Instant;


public class TOTPb extends Authentication {
	
	private static final SecureRandom secureRandom = new SecureRandom();
	private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";
	private static final int CODE_DIGITS = 6;
	private static final int TIME_STEP_SECONDS = 30;


    public static String generateSecretKey(int length) {
        byte[] key = new byte[length];
        secureRandom.nextBytes(key);
        System.out.println(key);
        return Base64.getEncoder().encodeToString(key);
    }
	
    public static String generateTOTP(String secretKey) throws GeneralSecurityException {
        byte[] decodedKey = Base64.getDecoder().decode(secretKey);
        long time = Instant.now().getEpochSecond() / TIME_STEP_SECONDS;

        byte[] data = new byte[8];
        for (int i = 7; i >= 0; i--) {
            data[i] = (byte) (time & 0xff);
            time >>= 8;
        }

        SecretKeySpec signingKey = new SecretKeySpec(decodedKey, HMAC_SHA1_ALGORITHM);
        Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
        mac.init(signingKey);
        byte[] hash = mac.doFinal(data);

        int offset = hash[hash.length - 1] & 0xf;
        int binary =
                ((hash[offset] & 0x7f) << 24) |
                ((hash[offset + 1] & 0xff) << 16) |
                ((hash[offset + 2] & 0xff) << 8) |
                (hash[offset + 3] & 0xff);
        int code = binary % (int) Math.pow(10, CODE_DIGITS);

        return String.format("%06d", code);
    }
    
    // algoritmo recuperado de: https://www.rfc-editor.org/rfc/rfc6238
    /* 
     * nuevo algoritmo:
     * https://github.com/taimos/totp/blob/master/src/main/java/de/taimos/totp/TOTP.java
     */

	@Override
	public boolean verify(User usuario) {
		// TODO Auto-generated method stub
		return false;
	}
	
	//desde aqui:
	
	//https://www.geeksforgeeks.org/bitwise-shift-operators-in-java/
	// 
	// https://docs.oracle.com/javase/7/docs/api/java/lang/Long.html
	// https://docs.oracle.com/javase/7/docs/api/javax/crypto/Mac.html
	/* importante :
	 * https://docs.oracle.com/javase/7/docs/api/javax/crypto/package-summary.html
	 */
	
	
	

}
