package com.wangjs.cipher.messagedigest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

public class MD5Test {
	public static final String FILENAME = "doc/数字签名.pdf";
	
	public static void main(String[] args) {
		
		String md5digest = getMD5ByJdk(FILENAME);
		System.out.println("md5digest = " + md5digest);
		md5digest = getMD5ByCommons(FILENAME);
		System.out.println("md5digest = " + md5digest);
		
	}
	
	/**
	 * 使用JDK计算文件的MD5
	 * @param filename
	 * @return
	 */
	public static String getMD5ByJdk(String filename){
		FileInputStream file = null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			file = new FileInputStream(filename);
			byte[] buffer = new byte[1024];
			int length=-1;
			while((length=file.read(buffer))!=-1){
				md5.update(buffer, 0, length);
			}
			return new String(Hex.encodeHex(md5.digest()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if (file!=null) {
				try {
					file.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	/**
	 * 使用apache commons codec计算MD5
	 * @param filename
	 * @return
	 */
	public static String getMD5ByCommons(String filename){
		try(FileInputStream file = new FileInputStream(filename)){
			return DigestUtils.md5Hex(file);
		}catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
