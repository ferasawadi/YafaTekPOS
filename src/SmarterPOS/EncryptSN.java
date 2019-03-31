package SmarterPOS;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class EncryptSN {
	private static String secretKey = "harvest.hinet-sy.com-hinet-smart@solutions#";
	private static String salt = "harvest.hinet-sy.com-hinet-smart@solutions#";
	private String DeviceSN = "";
	private String Secret = "";

	public EncryptSN() {
	}

	/**
	 * Constructor .
	 */
	public EncryptSN(String CustomerSN) {
		this.DeviceSN = CustomerSN;
		try {
			encrypt(EditSerialNumber(CustomerSN));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public String getDeviceSN() throws NoSuchAlgorithmException {
		return encrypt(DeviceSN);
	}

	public void setDeviceSN(String deviceSN) {
		DeviceSN = deviceSN;
	}

	/**
	 * Method to decrypt Device SN.
	 *
	 * @param strToEncrypt
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	private String encrypt(String strToEncrypt) throws NoSuchAlgorithmException {
		String encodedSN = "";
		String hyphenated = null;
		try {
			byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			IvParameterSpec ivspec = new IvParameterSpec(iv);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), 65536, 256);
			SecretKey tmp = factory.generateSecret(spec);
			SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
			encodedSN = Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
			StringBuilder str = new StringBuilder(encodedSN);
			int idx = str.length() - 4;
			while (idx > 0) {
				hyphenated = String.valueOf(str.insert(idx, "-"));
				idx = idx - 4;
			}
		} catch (Exception e) {
//            System.out.println("Error while encrypting: " + e.toString());
		}
		return hyphenated;
	}

	public String EditSerialNumber(String finalSN) {
		String SN = finalSN;
		String reverse = "";
		try {
			while (!finalSN.isEmpty()) {
				SN += SN;
				for (int i = SN.length() - 1; i >= 0; i--) {
					reverse = reverse + SN.charAt(i);
				}
				return reverse;
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return null;
	}
}
