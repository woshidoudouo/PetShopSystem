package cn.xiyou.petshopsystem.dao.impl;

import java.util.List;

import cn.xiyou.petshopsystem.dao.AccountDao;
import cn.xiyou.petshopsystem.dao.BaseDao;
import cn.xiyou.petshopsystem.entity.Account;
import cn.xiyou.petshopsystem.entity.Pet;

/**
 * DAO层账目信息功能实现类
 * 
 * @author doudou
 *
 */
public class AccountDaoImpl extends BaseDao<Account> implements AccountDao {

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
	@Override
	public int generateDealAccount(Pet pet, int buyerId, int sellerId, int dealType) {
		String sql = "insert into account(id,pet_id,seller_id,deal_type,buyer_id,price,deal_time) values(?,?,?,?,?,?,?);";
		Object[] params = { null, pet.getId(), sellerId, dealType, buyerId, pet.getPrice(), null };
		return this.executeUpdate(sql, params);
	}

	/**
	 * 获取商店账目
	 * 
	 * @param storeId
	 *            商店id
	 * @return
	 */
	@Override
	public List<Account> getAccount(int storeId) {
		String sql = "SELECT * FROM account WHERE deal_type=1 AND seller_id = ? UNION SELECT * FROM account WHERE deal_type=2 AND buyer_id = ?";
		return this.selectMany(sql, Account.class, storeId, storeId);
	}

}
