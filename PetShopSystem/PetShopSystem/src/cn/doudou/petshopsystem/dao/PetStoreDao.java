package cn.xiyou.petshopsystem.dao;

import java.util.List;

import cn.xiyou.petshopsystem.entity.PetStore;

/**
 * DAO层宠物商店功能接口
 * 
 * @author doudou
 *
 */
public interface PetStoreDao {

	/**
	 * 获取所有宠物商店
	 * 
	 * @return
	 */
	List<PetStore> getAllPetStore();

	/**
	 * 宠物商店登录
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	PetStore login(String username, String password);

	/**
	 * 更新宠物商店信息：商店余额
	 * 
	 * @param storeId
	 *            商店ID
	 * @param price
	 *            宠物价格
	 * @return 返回操作影响的行数
	 */
	int updatePetStore(int storeId, int price);

	/**
	 * 新开宠物商店
	 * 
	 * @param petStore
	 *            要开的宠物商店信息实体
	 * @return
	 */
	int openPetStore(PetStore petStore);

}
