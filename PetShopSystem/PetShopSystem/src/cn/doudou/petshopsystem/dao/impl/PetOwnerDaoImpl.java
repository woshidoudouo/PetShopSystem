package cn.xiyou.petshopsystem.dao.impl;

import java.util.List;

import cn.xiyou.petshopsystem.dao.BaseDao;
import cn.xiyou.petshopsystem.dao.PetOwnerDao;
import cn.xiyou.petshopsystem.entity.PetOwner;

/**
 * DAO层宠物主人功能实现类
 * 
 * @author doudou
 *
 */
public class PetOwnerDaoImpl extends BaseDao<PetOwner> implements PetOwnerDao {

	/**
	 * 获取所有宠物主人
	 * 
	 * @return
	 */
	@Override
	public List<PetOwner> getAllPetOwner() {
		String sql = "select * from petowner";
		return this.selectMany(sql, PetOwner.class);
	}

	/**
	 * 宠物主人登录
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	@Override
	public PetOwner login(String username, String password) {
		String sql = "select * from petowner where name = ? and password = ?";
		return this.selectOne(sql, PetOwner.class, username, password);
	}

	/**
	 * 更新主人信息：余额
	 * 
	 * @param id
	 *            主人id
	 * @param money
	 *            宠物价格
	 * @return 返回操作影响的行数
	 */
	@Override
	public int updatePetOwner(int id, int money) {
		String sql = "update petowner set money = money+? where id = ?";
		return this.executeUpdate(sql, money, id);
	}

}
