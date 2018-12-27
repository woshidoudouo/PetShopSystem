package cn.xiyou.petshopsystem.service;

import java.util.List;

import cn.xiyou.petshopsystem.entity.PetOwner;

/**
 * Service层宠物主人功能接口
 * 
 * @author doudou
 *
 */
public interface PetOwnerService extends Buyable, Sellable {

	/**
	 * 获取所有宠物主人
	 * 
	 * @return
	 */
	List<PetOwner> getAllPetOwner();

	/**
	 * 宠物主人登录
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	PetOwner login(String username, String password);

	/**
	 * 更新主人信息：余额
	 * 
	 * @param id
	 *            主人id
	 * @param money
	 *            宠物价格
	 * @return 返回操作影响的行数
	 */
	int updatePetOwner(int id, int money);

}
