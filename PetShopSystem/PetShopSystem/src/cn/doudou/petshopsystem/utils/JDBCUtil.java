package cn.xiyou.petshopsystem.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * ʹ��JDBC�������ݿ�������رյĹ�����
 * 
 * @author doudou
 */
public class JDBCUtil {

	private static String driver;
	private static String url;
	private static String username;
	private static String password;

	static {
		init();

		try {
			// ����һ������
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	/**
	 * ��ʼ�����ݿ��������ݣ���resources�ļ����¶�ȡproperties�ļ��еļ�ֵ�����ݣ�
	 * ����resources����Դ�ļ��У�����Ŀ����֮��resources��������ݻ��Զ�����src�ļ��У�ֻ����һ���յ�resources�ļ���
	 */
	public static void init() {
		Properties prop = new Properties();
		InputStream is = JDBCUtil.class.getClassLoader().getResourceAsStream("db.properties");
		try {
			prop.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		driver = prop.getProperty("jdbc.driver");
		url = prop.getProperty("jdbc.url");
		username = prop.getProperty("jdbc.username");
		password = prop.getProperty("jdbc.password");
	}

	/**
	 * ��ȡ���ݿ�����
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * �ͷ�������Դ
	 * 
	 * @param rs
	 *            �����
	 * @param st
	 *            Statement����
	 * @param conn
	 *            ���ݿ�����
	 */
	public static void closeAll(ResultSet rs, Statement st, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
