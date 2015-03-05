package com.wangjs.cipher.keyPair;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * 公私钥加密，有一对密钥，私钥自己保留，公钥给对方系统。
 * 使用私钥加密的东西，只能用公钥解密。使用公钥加密的东西，只能用私钥解密
 * 最大特点，身份验证。
 * 使用场景:
 * SSH/SFTP的密钥登录。(服务器保留公钥，用户保留私钥)
 * github.com/bitbucket.org
 * 数字签名/数字证书
 */
public class RsaKeyPairTest {
	public static final String PLAIN_TEXT= "这个是明文测试字符串";
	
	public static void main(String[] args) {
		rsaByJdk();
	}
	
	public static void rsaByJdk(){
		try {
			KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
			KeyPair keyPair = gen.generateKeyPair();
			RSAPrivateKey priKey = (RSAPrivateKey)keyPair.getPrivate();
			RSAPublicKey pubKey = (RSAPublicKey)keyPair.getPublic();
			
			Cipher c = Cipher.getInstance("RSA");
			c.init(Cipher.ENCRYPT_MODE, pubKey);
			byte[] cipherBytes = c.doFinal(PLAIN_TEXT.getBytes());
			System.out.println("cipherBytes ="+new String(cipherBytes));
			
			Cipher d = Cipher.getInstance("RSA");
			d.init(Cipher.DECRYPT_MODE, priKey);
			byte[] plainText = d.doFinal(cipherBytes);
			System.out.println("plainText = " + new String(plainText));
			
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
