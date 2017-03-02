package com.billy.androidutils.utils.encrypt.rsa;

import java.util.logging.Logger;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;

public class Rsademo {
	public static void main(String[] args) {
		try {
			String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKB8GunT65dDzvV4" + "VD6UA9+lGEjIgEtyEVtrp3rEhBRmvOZ1sromkybrAF4ByodHh1BmgBLdImMqMzH2"
					+ "vgwc3ioOqiaODqHNPpqa/jeSrdNE/hJSKQqPXi+qVaIg6tOi84GnirHOrwkVxR45" + "kQgj4lH7qnIaMhooaIModIsDTGs7AgMBAAECgYEAg/Jlwlhtu9mRgDslsKnLoYZA"
					+ "uB65dM5dPPf/JC4MliV+LFEa2Hg8xmOy0pfQZ3dE5rLPnDLaQgQBQZQn3xehBE/N" + "2YdzLEH1Dpw1eOJY30Qf/Rp6jUaTwY5gQCxSDt24CXpDjzo09dvaR4uHhRNZX1KB"
					+ "XNco+PiM7ujFaSrhuBkCQQDQC3d2OhQB4vAaxaPzwqQv6lAFWCR8Osy5jyY/KlVF" + "kd/VzXp2uWACgFm6UXmUwyLfrSpFl013E5SjOsdgpXYNAkEAxXoqed1TYAHHS63N"
					+ "oIQlMz/ygHiMtkFeoD8HgKYw5TzYCpqlM++2O1VcbTLjQtnwctIe3B3xF7eOZ1Si" + "53KcZwJAdPaNYhWC3BCnJpYI9+ls/1c/R9HnKUSxhn05Zne5WxSJAB22hPrxRFa+"
					+ "m2Zk8ULH33LuehN3RMPoY+CO6QH9HQJBAK9+JrtP7iU2z2a42TEZ3nlSDe8PsnTR" + "WQdtm/w/NNqznIan8cJa+AZ4kH/WplIlneJcSuJwlW3vSNUZSQAIQWcCQHBsB41q"
					+ "WyyPcRBjMCR6YO4Iih/07kZJDAqHrdnhea+aNF+MPuShqIGlcDEbdeS1XxUp8gSm" + "diXEh5aJvpTfSEY=";

			String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCgfBrp0+uXQ871eFQ+lAPfpRhI" + "yIBLchFba6d6xIQUZrzmdbK6JpMm6wBeAcqHR4dQZoAS3SJjKjMx9r4MHN4qDqom"
					+ "jg6hzT6amv43kq3TRP4SUikKj14vqlWiIOrTovOBp4qxzq8JFcUeOZEII+JR+6py" + "GjIaKGiDKHSLA0xrOwIDAQAB";

			System.out.println("privateKey=" + privateKey);
			System.err.println("私钥加密——公钥解密");
			String source = "hyx_912_74571852356eb1dd4902f66c9c7ad103e8285d010d27488b";
			System.out.println("原文字：\r\n" + source);
			byte[] data = source.getBytes();
			// byte[] encodedData = RSACrypt.encryptByPrivateKey(data, privateKey);
			byte[] encodedData = RSACrypt.encryptByPublicKey(data, publicKey);
			String enBaseDate = RSACrypt.encode(encodedData);
			System.out.println("加密后：\r\n" + enBaseDate);
			// byte[] decryptByPublicKey = RSACrypt.decryptByPublicKey(encodedData, publicKey);
			byte[] decryptByPublicKey = RSACrypt.decryptByPrivateKey(encodedData, privateKey);
			System.out.println("解密后：\r\n" + new String(decryptByPublicKey));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
