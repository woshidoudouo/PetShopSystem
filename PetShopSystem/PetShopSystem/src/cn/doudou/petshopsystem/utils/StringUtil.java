package cn.xiyou.petshopsystem.utils;

import java.util.Date;

/**
 * �ַ�����������
 * 
 * @author XIAOHU
 * @since 2018-04-11
 */
public class StringUtil {

	/**
	 * ���ַ�������ĸתΪ��д
	 * 
	 * @param str
	 *            Ҫת�����ַ���
	 * @return ����ת���������ĸ��д���ַ���
	 */
	public static String toUpper(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	public static String subDateToString(Date date) {
		return date.toString().substring(0, 10);
	}

}
