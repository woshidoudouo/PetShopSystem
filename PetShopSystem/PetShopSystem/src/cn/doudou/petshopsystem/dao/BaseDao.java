package cn.xiyou.petshopsystem.dao;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.xiyou.petshopsystem.utils.JDBCUtil;
import cn.xiyou.petshopsystem.utils.StringUtil;

/**
 * 封装JDBC的增删改查功能
 * 
 * @author doudou
 * 
 * 
 */
public class BaseDao<T> {

	/**
	 * 封装增、删、改功能
	 * 
	 * @param sql
	 *            需要执行的sql语句
	 * @param args
	 *            不定参数，是对sql语句中的占位符“？”传入的参数
	 * @return 返回操作所影响的行数
	 */
	public int executeUpdate(String sql, Object... args) {
		Connection conn = null;
		PreparedStatement pst = null;
		int rows = 0;
		try {
			conn = JDBCUtil.getConnection();
			pst = conn.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				pst.setObject(i + 1, args[i]);
			}
			rows = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeAll(null, pst, conn);
		}
		return rows;
	}

	/**
	 * 查询一条记录
	 * 
	 * @param sql
	 *            需要执行的sql语句
	 * @param cls
	 *            由此 Class对象建模的类的类型。如果将被建模的类未知，则使用 Class<?>。例如，String.class 的类型是
	 *            Class<String>。
	 * @param args
	 *            不定参数，是对sql语句中的占位符“？”传入的参数
	 * @return 返回操作所影响的行数
	 */
	public T selectOne(String sql, Class<T> cls, Object... args) {
		List<T> list = this.selectMany(sql, cls, args);
		return list.isEmpty() ? null : list.get(0);
	}

	/**
	 * 查询所有记录
	 * 
	 * @param sql
	 *            需要执行的sql语句
	 * @param cls
	 *            由此 Class对象建模的类的类型。如果将被建模的类未知，则使用 Class<?>。例如，String.class 的类型是
	 *            Class<String>。
	 * @param args
	 *            不定参数，是对sql语句中的占位符“？”传入的参数
	 * @return 返回操作所影响的行数
	 */
	public List<T> selectMany(String sql, Class<T> cls, Object... args) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<T> list = new ArrayList<T>();
		try {
			conn = JDBCUtil.getConnection();
			pst = conn.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				pst.setObject(i + 1, args[i]);
			}
			rs = pst.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();// 从结果集中获取数据库表的相关信息
			while (rs.next()) {
				T obj = cls.newInstance();// 创建cls实例
				for (int i = 1; i <= metaData.getColumnCount(); i++) {// metaData.getColumnCount()：获得数据库表中列数（字段数）
					String columnLabel = metaData.getColumnLabel(i);// 获取字段名
					String name = "set" + StringUtil.toUpper(columnLabel);// 动态拼接成该属性对应实体中的setter方法的方法名（=set字符串拼接首字母大写的属性名）。如setName(Stringname)的方法名为setName
					Field field = cls.getDeclaredField(columnLabel);// 获取实体中所有声明（私有+公有）的属性
					Method method = cls.getDeclaredMethod(name, field.getType());// 获取实体中所有声明（私有+公有）的形参为field.getType()类型，方法名为name的方法
					Object realParam = rs.getObject(columnLabel);// 通过结果集获取字段名为fieldName（与实体中的对应属性名完全相同）的值
					method.invoke(obj, realParam);// 执行obj对象中的method方法，传入的实参为realParam
				}
				list.add(obj);
			}
		} catch (SQLException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeAll(null, pst, conn);
		}
		return list;
	}

	/**
	 * 查询总记录数
	 * 
	 * @param sql
	 *            需要执行的sql语句
	 * @param args
	 *            需要对sql语句中的占位符“？”传入的参数数组
	 * @return 返回操作所影响的行数
	 */
	public int selectCount(String sql, Object... args) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn = JDBCUtil.getConnection();
			pst = conn.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				pst.setObject(i + 1, args[i]);
			}
			rs = pst.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeAll(null, pst, conn);
		}
		return count;
	}

}
