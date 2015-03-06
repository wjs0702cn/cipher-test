package com.wangjs.cipher.keyPair;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;

import com.wangjs.cipher.keystore.KeyStoreTest;

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
	
	public static void main(String[] args) throws Exception {
		rsaByJdk();
	}
	
	public static void rsaByJdk() throws UnrecoverableKeyException, KeyStoreException, CertificateException, IOException{
		try {
//			KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
//			KeyPair keyPair = gen.generateKeyPair();
//			RSAPrivateKey priKey = (RSAPrivateKey)keyPair.getPrivate();
//			RSAPublicKey pubKey = (RSAPublicKey)keyPair.getPublic();
			
			RSAPrivateKey priKey = KeyStoreTest.getPrivateKey();
			RSAPublicKey pubKey = KeyStoreTest.getPublicKey();

			
			Cipher c = Cipher.getInstance("RSA");
			c.init(Cipher.ENCRYPT_MODE, pubKey);
			byte[] cipherBytes = c.doFinal(PLAIN_TEXT.getBytes());
			System.out.println("cipherBytes ="+new String(cipherBytes));
			System.out.println("cipherString(Base64) = "+Base64.encodeBase64String(cipherBytes));
			
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
