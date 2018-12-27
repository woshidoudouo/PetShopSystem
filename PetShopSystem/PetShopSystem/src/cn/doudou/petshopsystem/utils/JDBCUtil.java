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
 * 使用JDBC进行数据库连接与关闭的工具类
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
			// 加载一次驱动
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 初始化数据库连接数据（从resources文件夹下读取properties文件中的键值对数据）
	 * 对于resources（资源文件夹），项目编译之后，resources里面的内容会自动移入src文件夹，只留下一个空的resources文件夹
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
	 * 获取数据库连接
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
	 * 释放所有资源
	 * 
	 * @param rs
	 *            结果集
	 * @param st
	 *            Statement对象
	 * @param conn
	 *            数据库连接
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
