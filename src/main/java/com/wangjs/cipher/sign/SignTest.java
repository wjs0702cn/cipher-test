package com.wangjs.cipher.sign;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * 数字签名及验证
 *
 */
public class SignTest {
	public static final String PLAIN_TEXT= "这个是明文测试字符串";
	
	public static void main(String[] args) {
		signByJdk();
	}
	
	public static void signByJdk(){
		try {
			KeyPairGenerator keygen = KeyPairGenerator.getInstance("RSA");
			KeyPair keyPair = keygen.generateKeyPair();
			RSAPrivateKey priKey = (RSAPrivateKey)keyPair.getPrivate();
			RSAPublicKey pubKey = (RSAPublicKey)keyPair.getPublic();
			
			Signature sign = Signature.getInstance("SHA1WithRSA");
			sign.initSign(priKey);
			sign.update(PLAIN_TEXT.getBytes());
			byte[] signData = sign.sign();
			System.out.println("signText = "+ new String(signData));
			
			Signature sign2 = Signature.getInstance("SHA1WithRSA");
			sign2.initVerify(pubKey);
			sign2.update(PLAIN_TEXT.getBytes());
			boolean isSame = sign2.verify(signData);
			System.out.println("isSame = "+isSame);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (SignatureException e) {
			e.printStackTrace();
		}
	}
}
