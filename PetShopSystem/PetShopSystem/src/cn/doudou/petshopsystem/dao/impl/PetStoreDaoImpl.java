package cn.xiyou.petshopsystem.dao.impl;

import java.util.List;

import cn.xiyou.petshopsystem.dao.BaseDao;
import cn.xiyou.petshopsystem.entity.PetStore;

/**
 * DAO层宠物商店功能实现类
 * 
 * @author doudou
 *
 */
public class PetStoreDaoImpl extends BaseDao<PetStore> implements cn.xiyou.petshopsystem.dao.PetStoreDao {

	/**
	 * 获取所有宠物商店
	 * 
	 * @return
	 */
	@Override
	public List<PetStore> getAllPetStore() {
		String sql = "select * from petstore";
		return this.selectMany(sql, PetStore.class);
	}

	/**
	 * 宠物商店登录
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	@Override
	public PetStore login(String username, String password) {
		String sql = "select * from petstore where name = ? and password = ?";
		return this.selectOne(sql, PetStore.class, username, password);
	}

	/**
	 * 更新宠物商店信息：商店余额
	 * 
	 * @param storeId
	 *            商店ID
	 * @param price
	 *            宠物价格
	 * @return 返回操作影响的行数
	 */
	@Override
	public int updatePetStore(int storeId, int price) {
		String sql = "update petstore set balance = balance+? where id = ?";
		return this.executeUpdate(sql, price, storeId);
	}

	/**
	 * 新开宠物商店
	 * 
	 * @param petStore
	 *            要开的宠物商店信息实体
	 * @return
	 */
	@Override
	public int openPetStore(PetStore petStore) {
		String sql = "insert into petstore values(?,?,?,?)";
		return this.executeUpdate(sql, null, petStore.getName(), petStore.getPassword(), petStore.getBalance());
	}

}
