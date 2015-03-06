package com.wangjs.cipher.keystore;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class KeyStoreTest {
	public static final String KEY_STORE = "keystore";
	public static final char[] KEY_STORE_PASS = "password".toCharArray();
	public static final String KEY_ALIAS = "mySecurityKey";
	public static final char[] MY_KEY_PASS = "myKeyPass".toCharArray();

	public static final String PLAIN_TEXT = "这个是明文测试字符串";
	
	public static void main(String[] args) throws UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		RSAPrivateKey priKey = getPrivateKey();
		RSAPublicKey pubKey = getPublicKey();
		
		System.out.println("private key = "+priKey);
		System.out.println("public key = "+pubKey);
	}

	private static KeyStore getKeyStore() throws KeyStoreException,
			NoSuchAlgorithmException, CertificateException, IOException {
		boolean ksExists = new File(".keystore").exists();
		if (ksExists) {
			return null;
		}
		KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
		FileInputStream fis = new FileInputStream(KEY_STORE);
		ks.load(fis, KEY_STORE_PASS);
		fis.close();
		return ks;
	}

	public static RSAPrivateKey getPrivateKey() throws KeyStoreException,
			NoSuchAlgorithmException, CertificateException, IOException,
			UnrecoverableKeyException {
		KeyStore ks = getKeyStore();
		return (RSAPrivateKey) ks.getKey(KEY_ALIAS, MY_KEY_PASS);
	}

	public static RSAPublicKey getPublicKey() throws KeyStoreException,
			NoSuchAlgorithmException, CertificateException, IOException {
		KeyStore ks = getKeyStore();
		return (RSAPublicKey) ks.getCertificate(KEY_ALIAS).getPublicKey();
	}
}
