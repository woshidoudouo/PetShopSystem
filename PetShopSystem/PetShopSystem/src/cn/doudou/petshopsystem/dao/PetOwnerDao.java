package cn.xiyou.petshopsystem.dao;

import java.util.List;

import cn.xiyou.petshopsystem.entity.PetOwner;

/**
 * DAO层宠物主人功能接口
 * 
 * @author doudou
 *
 */
public interface PetOwnerDao {

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
	 *            宠物主人名
	 * @param password
	 *            宠物主人密码
	 * @return 返回登录的人信息实体
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
