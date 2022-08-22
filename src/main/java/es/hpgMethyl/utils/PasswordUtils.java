package es.hpgMethyl.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordUtils {
	
	private static final String ENCRYPTION_ALGORITHM = "PBKDF2WithHmacSHA512";
	private static final int 	ENCRYPTION_ITERATIONS = 50000;
	private static final int 	ENCRYPTION_ITERATIONS_STRONG = 1000000;
	private static final int 	ENCRYPTION_KEY_LENGTH = 1024;
	private static final int 	SALT_BYTES_LENGTH = 20;
	
	private PasswordUtils() {};
	
	public static String makeSalt() {
	
		SecureRandom secureRandom = new SecureRandom();
		byte bytes[] = new byte[SALT_BYTES_LENGTH];
		secureRandom.nextBytes(bytes);
		String salt = Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
		
		return salt;		
	}
	
	public static String getHashWithSalt(String password, String salt, Boolean strong) throws NoSuchAlgorithmException, InvalidKeySpecException {
		
		char[] passwordChars = password.toCharArray();
	    byte[] saltBytes = salt.getBytes();
	    
	    int encryptionIterations = strong ? ENCRYPTION_ITERATIONS_STRONG : ENCRYPTION_ITERATIONS;   
	    
	    PBEKeySpec spec = new PBEKeySpec(passwordChars, saltBytes, encryptionIterations, ENCRYPTION_KEY_LENGTH);
	    
	    try {
	    	SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(ENCRYPTION_ALGORITHM);
	        byte[] passwordEncoded = secretKeyFactory.generateSecret(spec).getEncoded();
	        return Base64.getEncoder().encodeToString(passwordEncoded);
	    } finally {
	        spec.clearPassword();
	    }
	}
	
	public static Boolean checkPassword(String password, String hash, String salt, Boolean strong) {
		
		try {
			String candidateHash = getHashWithSalt(password, salt, strong);
			System.out.println(candidateHash);
			return candidateHash.equals(hash);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			return false;
		}
	}
}
