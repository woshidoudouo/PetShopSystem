package cn.xiyou.petshopsystem.dao;

import java.util.List;

import cn.xiyou.petshopsystem.entity.Account;
import cn.xiyou.petshopsystem.entity.Pet;

/**
 * DAO层账目信息功能接口
 * 
 * @author doudou
 *
 */
public interface AccountDao {

	/**
	 * 生成交易账目信息
	 * 
	 * @param pet
	 *            被交易的那个宠物
	 * @param buyerId
	 *            买家id
	 * @param sellerId
	 *            卖家id
	 * @param dealType
	 *            交易类型（1：商店卖给宠物主人， 2：宠物主人卖给商店 ）
	 * @return
	 */
	int generateDealAccount(Pet pet, int buyerId, int sellerId, int dealType);

	/**
	 * 获取商店账目
	 * 
	 * @param storeId
	 *            商店id
	 * @return
	 */
	List<Account> getAccount(int storeId);

}
