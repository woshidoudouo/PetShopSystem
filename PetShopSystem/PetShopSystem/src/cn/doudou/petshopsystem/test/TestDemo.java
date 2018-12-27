package cn.xiyou.petshopsystem.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import cn.xiyou.petshopsystem.utils.JDBCUtil;

/**
 *
 * 
 * @author doudou
 *
 */
public class TestDemo {

	/**
	 * ≤‚ ‘JDBCUtilπ§æﬂ¿‡
	 */
	@Test
	public void testJDBCUtil() {

		Connection conn = JDBCUtil.getConnection();
		String sql = "select * from pet";
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				System.out.println(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
