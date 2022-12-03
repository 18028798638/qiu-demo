package com.example.testToken.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES加解密工具类
 */
public class AESUtil {

	private static final String CHARSET = "utf-8";
	private static final String KEY_ALGORITHM = "AES";
	private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/NoPadding";// 默认的加密算法
	
	/**
	 * AES 加密操作
	 *
	 * @param content  待加密内容
	 * @param password 加密密码
	 * @return 返回Base64转码后的加密数据
	 */
	public static String encrypt(String content, String password) {
		try {
			Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);// 创建密码器
			int blockSize = cipher.getBlockSize();

			byte[] byteContent = content.getBytes(CHARSET);

			int plaintextLength = byteContent.length;
			if (plaintextLength % blockSize != 0) {
				plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
			}
			
			byte[] plaintext = new byte[plaintextLength];
			System.arraycopy(byteContent, 0, plaintext, 0, byteContent.length);
			
			// 生成加密秘钥
			SecretKeySpec keySpec = getSecretKey(password);

			cipher.init(Cipher.ENCRYPT_MODE, keySpec);// 初始化为加密模式的密码器

			byte[] result = cipher.doFinal(plaintext);// 加密

			return Base64.encodeBase64String(result);// 通过Base64转码返回
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * AES 解密操作
	 *
	 * @param content
	 * @param password
	 * @return
	 */
	public static String decrypt(String content, String password) {

		try {
			// 实例化
			Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);

			// 生成加密秘钥
			SecretKeySpec keySpec = getSecretKey(password);

			cipher.init(Cipher.DECRYPT_MODE, keySpec);// 初始化为加密模式的密码器

			// 执行操作
			byte[] result = cipher.doFinal(Base64.decodeBase64(content));// 解密

			return new String(result, CHARSET);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	/**
	 * 生成加密秘钥
	 *
	 * @return
	 */
	private static SecretKeySpec getSecretKey(final String password) {

		int len = password.getBytes().length;
		if (len % 16 != 0) {
			len = len + (16 - (len % 16));
		}

		byte[] newpass = new byte[len];

		System.arraycopy(password.getBytes(), 0, newpass, 0, password.getBytes().length);

		SecretKeySpec keySpec = new SecretKeySpec(newpass, KEY_ALGORITHM);
		return keySpec;
	}

}
