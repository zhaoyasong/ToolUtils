package com.billy.androidutils.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sourceforge.pinyin4j.PinyinHelper;

public class StringUtils {
	protected static final String TAG = StringUtils.class.getSimpleName();

	/**
	 * 增加空白
	 */
	public static String addBlank(int size) {
		return String.format("%" + size + "s", "");
	}

	/**
	 * 判断字符串是否为null或者""
	 */
	public static boolean isEmptyOrNull(String content) {
		if (content == null || content.equals("")) {
			return true;
		}
		return false;
	}

	/**
	 *  判断字符串是否为IP地址
	 */
	public static boolean isIPAddress(String ipString) {
		if (ipString != null) {
			String[] singleArray = ipString.split("\\.");
			if (singleArray == null) {
				return false;
			}
			for (String numString : singleArray) {
				if (isEmptyOrNull(numString.trim())) {
					return false;
				}
				try {
					int num = Integer.parseInt(numString.trim());
					if (num < 0 || num > 255) {
						return false;
					}
				} catch (NumberFormatException e) {
					return false;
				}

			}
			return true;
		}
		return false;
	}

	/**
	 * 是否是email地址
	 */
	public static boolean isEmailAddress(String emailString) {
		String format = "\\p{Alpha}\\w{2,15}[@][a-z0-9]{3,}[.]\\p{Lower}{2,}";
		return isMatch(format, emailString);
	}

	/**
	 * 是否为数字
	 */
	public static boolean isDigit(String digitString) {
		if (!isEmptyOrNull(digitString)) {
			String regex = "[0-9]*";
			return isMatch(regex, digitString);
		}
		return false;
	}

	/**
	 *  字符串正则校验
	 */
	public static boolean isMatch(String regex, String string) {

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(string);
		return matcher.matches();
	}

	/**
	 *是否为URL地址
	 */
	public static boolean isUrl(String strIp) {
		String strPattern = "^((https?)|(ftp))://(?:(\\s+?)(?::(\\s+?))?@)?([a-zA-Z0-9\\-.]+)"
				+ "(?::(\\d+))?((?:/[a-zA-Z0-9\\-._?,'+\\&%$=~*!():@\\\\]*)+)?$";
		return isMatch(strPattern, strIp);
	}

	/**
	 *  String 转换成Unicode
	 * @param string
	 *            传入汉字
	 * @return
	 */
	public static String string2Unicode(String string) {
		if (!isEmptyOrNull(string)) {
			char[] charArray = string.toCharArray();
			StringBuffer buffer = new StringBuffer();
			for (char ch : charArray) {
				int code = (int) ch;
				buffer.append(code);
			}
			return buffer.toString();
		}
		return null;
	}

	/**
	 *  Unicode转换成String
	 * @param string
	 * @return
	 */
	public static String unicode2String(String string) {
		if (!isEmptyOrNull(string)) {
			int end = 0;
			String noSpace = string.trim();
			int count = noSpace.length() / 5;
			StringBuffer buffer = new StringBuffer();

			for (int j = 0; j < count; j++) {
				end += 5;
				int uCode = Integer.valueOf(noSpace.substring(j * 5, end));
				buffer.append((char) uCode);

			}
			return buffer.toString();
		}
		return null;
	}

	/**
	 *  得到首字母
	 */
	public static String getPinYinFirstLetter(String string) {
		if (!isEmptyOrNull(string)) {
			char[] cs = string.toCharArray();
			for (int i = 0; i < cs.length; i++) {
				String[] pinyins = null;
				if (cs[i] > 128) {
					pinyins = PinyinHelper.toHanyuPinyinStringArray(cs[i]);
				}
				if (pinyins != null && pinyins.length > 0) {
					return pinyins[0].substring(0, 1);
				}
			}
		}
		return null;
	}

	/**
	 * 获取url参数
	 */
	public static String getParamValueOfUrl(String url, String paramName) {
		try {
			String urls[] = url.split("[?]");
			if (urls.length > 1) {
				String param = urls[1];
				String params[] = param.split("[&]");
				for (String string : params) {
					String keyAndValue[] = string.split("[=]");
					if (keyAndValue.length > 1) {
						String key = keyAndValue[0];
						String value = keyAndValue[1];
						if (key.equalsIgnoreCase(paramName)) {
							return value;
						}
					}
				}
			}
		} catch (Exception e) {
			return "";
		}
		return "";
	}

	/**
	 * 半角转换为全角
	 * @param input
	 * @return
	*/
	public static String ToDBC(String input) {
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 12288) {
				c[i] = (char) 32;
				continue;
			}
			if (c[i] > 65280 && c[i] < 65375)
				c[i] = (char) (c[i] - 65248);
		}
		return new String(c);
	}

	/**
	 * 去除特殊字符或将所有中文标号替换为英文标号
	 * @param str
	 * @return
	*/
	public static String stringFilter(String str) {
		str = str.replaceAll("【", "[").replaceAll("】", "]").replaceAll("！", "!").replaceAll("：", ":");// 替换中文标号
		String regEx = "[『』]"; // 清除掉特殊字符
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}

	/**
	 * 根据Unicode编码完美的判断中文汉字和符号
	 * @param c
	 * @return
	 */

	private static boolean isChinese(char c) {

		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);

		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS

		|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS

		|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A

		|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B

		|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION

		|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS

		|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {

			return true;

		}

		return false;

	}

	/**
	 * 完整的判断中文汉字和符号
	 * @param strName
	 * @return
	 */

	public static boolean isChinese(String strName) {

		char[] ch = strName.toCharArray();

		for (int i = 0; i < ch.length; i++) {

			char c = ch[i];

			if (isChinese(c)) {

				return true;

			}

		}

		return false;

	}
}
