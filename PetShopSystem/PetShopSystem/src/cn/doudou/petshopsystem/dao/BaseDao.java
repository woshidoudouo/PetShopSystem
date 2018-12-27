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
 * ��װJDBC����ɾ�Ĳ鹦��
 * 
 * @author doudou
 * 
 * 
 */
public class BaseDao<T> {

	/**
	 * ��װ����ɾ���Ĺ���
	 * 
	 * @param sql
	 *            ��Ҫִ�е�sql���
	 * @param args
	 *            �����������Ƕ�sql����е�ռλ������������Ĳ���
	 * @return ���ز�����Ӱ�������
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
	 * ��ѯһ����¼
	 * 
	 * @param sql
	 *            ��Ҫִ�е�sql���
	 * @param cls
	 *            �ɴ� Class����ģ��������͡����������ģ����δ֪����ʹ�� Class<?>�����磬String.class ��������
	 *            Class<String>��
	 * @param args
	 *            �����������Ƕ�sql����е�ռλ������������Ĳ���
	 * @return ���ز�����Ӱ�������
	 */
	public T selectOne(String sql, Class<T> cls, Object... args) {
		List<T> list = this.selectMany(sql, cls, args);
		return list.isEmpty() ? null : list.get(0);
	}

	/**
	 * ��ѯ���м�¼
	 * 
	 * @param sql
	 *            ��Ҫִ�е�sql���
	 * @param cls
	 *            �ɴ� Class����ģ��������͡����������ģ����δ֪����ʹ�� Class<?>�����磬String.class ��������
	 *            Class<String>��
	 * @param args
	 *            �����������Ƕ�sql����е�ռλ������������Ĳ���
	 * @return ���ز�����Ӱ�������
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
			ResultSetMetaData metaData = rs.getMetaData();// �ӽ�����л�ȡ���ݿ��������Ϣ
			while (rs.next()) {
				T obj = cls.newInstance();// ����clsʵ��
				for (int i = 1; i <= metaData.getColumnCount(); i++) {// metaData.getColumnCount()��������ݿ�����������ֶ�����
					String columnLabel = metaData.getColumnLabel(i);// ��ȡ�ֶ���
					String name = "set" + StringUtil.toUpper(columnLabel);// ��̬ƴ�ӳɸ����Զ�Ӧʵ���е�setter�����ķ�������=set�ַ���ƴ������ĸ��д��������������setName(Stringname)�ķ�����ΪsetName
					Field field = cls.getDeclaredField(columnLabel);// ��ȡʵ��������������˽��+���У�������
					Method method = cls.getDeclaredMethod(name, field.getType());// ��ȡʵ��������������˽��+���У����β�Ϊfield.getType()���ͣ�������Ϊname�ķ���
					Object realParam = rs.getObject(columnLabel);// ͨ���������ȡ�ֶ���ΪfieldName����ʵ���еĶ�Ӧ��������ȫ��ͬ����ֵ
					method.invoke(obj, realParam);// ִ��obj�����е�method�����������ʵ��ΪrealParam
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
	 * ��ѯ�ܼ�¼��
	 * 
	 * @param sql
	 *            ��Ҫִ�е�sql���
	 * @param args
	 *            ��Ҫ��sql����е�ռλ������������Ĳ�������
	 * @return ���ز�����Ӱ�������
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
