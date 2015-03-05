package com.wangjs.cipher.onekey;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/**
 * 单项加密，有一个密钥，加密解密用同一个密钥
 * 不常用，不安全
 * 类似的算法: AES
 */
public class DesKeyTest {
	public static final String PLAIN_TEXT= "这个是明文测试字符串";
	
	public static void main(String[] args) {
		desByJdk();
	}
	
	public static void desByJdk(){
		try {
			KeyGenerator keyG = KeyGenerator.getInstance("DES");
			SecretKey key = keyG.generateKey();
			
			Cipher c = Cipher.getInstance("DES");
			c.init(Cipher.ENCRYPT_MODE, key);
			byte[] cipherBytes = c.doFinal(PLAIN_TEXT.getBytes());
			String cipherText = new String(cipherBytes);
			System.out.println("cipherText = "+cipherText);
			
			Cipher d = Cipher.getInstance("DES");
			d.init(Cipher.DECRYPT_MODE, key);
			String plainText = new String(d.doFinal(cipherBytes));
			System.out.println("plainText = "+plainText);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
	}
}
