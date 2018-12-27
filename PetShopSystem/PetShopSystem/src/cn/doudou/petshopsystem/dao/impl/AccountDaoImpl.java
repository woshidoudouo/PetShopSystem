package cn.xiyou.petshopsystem.dao.impl;

import java.util.List;

import cn.xiyou.petshopsystem.dao.AccountDao;
import cn.xiyou.petshopsystem.dao.BaseDao;
import cn.xiyou.petshopsystem.entity.Account;
import cn.xiyou.petshopsystem.entity.Pet;

/**
 * DAO����Ŀ��Ϣ����ʵ����
 * 
 * @author doudou
 *
 */
public class AccountDaoImpl extends BaseDao<Account> implements AccountDao {

	/**
	 * ���ɽ�����Ŀ��Ϣ
	 * 
	 * @param pet
	 *            �����׵��Ǹ�����
	 * @param buyerId
	 *            ���id
	 * @param sellerId
	 *            ����id
	 * @param dealType
	 *            �������ͣ�1���̵������������ˣ� 2���������������̵� ��
	 * @return
	 */
	@Override
	public int generateDealAccount(Pet pet, int buyerId, int sellerId, int dealType) {
		String sql = "insert into account(id,pet_id,seller_id,deal_type,buyer_id,price,deal_time) values(?,?,?,?,?,?,?);";
		Object[] params = { null, pet.getId(), sellerId, dealType, buyerId, pet.getPrice(), null };
		return this.executeUpdate(sql, params);
	}

	/**
	 * ��ȡ�̵���Ŀ
	 * 
	 * @param storeId
	 *            �̵�id
	 * @return
	 */
	@Override
	public List<Account> getAccount(int storeId) {
		String sql = "SELECT * FROM account WHERE deal_type=1 AND seller_id = ? UNION SELECT * FROM account WHERE deal_type=2 AND buyer_id = ?";
		return this.selectMany(sql, Account.class, storeId, storeId);
	}

}
