package com.example.com.callbackinterface;

import android.util.Base64;
import android.util.Log;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Cryptography {
	
	private static String CRYPTOGRAPHY_KEY="sil";
	
	public static String Decrypt(String text) throws Exception {
		String decryptedValue="";
		if(text!= null && !text.isEmpty())
		{
		
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); //this parameters should not be changed
		byte[] keyBytes = new byte[16];
		byte[] b = CRYPTOGRAPHY_KEY.getBytes("UTF-8");
		int len = b.length;
		if (len > keyBytes.length)
			len = keyBytes.length;
		System.arraycopy(b, 0, keyBytes, 0, len);
		SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
		IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
		cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
		byte[] results = new byte[text.length()];
//		BASE64Decoder decoder = new BASE64Decoder();
		//byte[] decodedBytes = null;
		try {
			byte[] decordedValue = Base64.decode(text.getBytes(), Base64.DEFAULT);
			results = cipher.doFinal(decordedValue);
//			results = cipher.doFinal(Base64.decode(text.getBytes());
			decryptedValue = new String(results);
//			String dec= new String(Base64.decode(decryptedValue, Base64.DEFAULT));
//			return decoded;
		} catch (Exception e) {
			Log.i("Erron in Decryption", e.toString());
		}
		Log.i("Data", new String(results, "UTF-8"));
		
		}
		return decryptedValue.trim(); // it returns the result as a String
	}

	public static String Encrypt(String text) throws Exception {
	String encrypted_string="";
	if(text!= null && !text.isEmpty())
		{
	
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		byte[] keyBytes = new byte[16];
		byte[] b = CRYPTOGRAPHY_KEY.getBytes("UTF-8");
		int len = b.length;
		if (len > keyBytes.length)
			len = keyBytes.length;
		System.arraycopy(b, 0, keyBytes, 0, len);
		SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
		IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
		cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

		byte[] results = cipher.doFinal(text.getBytes("UTF-8"));
//		BASE64Encoder encoder = new BASE64Encoder();
		encrypted_string= Base64.encodeToString(results, Base64.DEFAULT);
//		return Base64.encode(results); // it returns the result as a String
}


return encrypted_string.trim();


	}
	

}
