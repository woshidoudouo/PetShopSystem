package cn.xiyou.petshopsystem.utils;

import java.util.Date;

/**
 * 字符串处理工具类
 * 
 * @author XIAOHU
 * @since 2018-04-11
 */
public class StringUtil {

	/**
	 * 将字符串首字母转为大写
	 * 
	 * @param str
	 *            要转换的字符串
	 * @return 返回转换后的首字母大写的字符串
	 */
	public static String toUpper(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	public static String subDateToString(Date date) {
		return date.toString().substring(0, 10);
	}

}
