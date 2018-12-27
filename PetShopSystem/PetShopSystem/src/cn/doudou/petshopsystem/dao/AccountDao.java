package cn.xiyou.petshopsystem.dao;

import java.util.List;

import cn.xiyou.petshopsystem.entity.Account;
import cn.xiyou.petshopsystem.entity.Pet;

/**
 * DAO����Ŀ��Ϣ���ܽӿ�
 * 
 * @author doudou
 *
 */
public interface AccountDao {

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
	int generateDealAccount(Pet pet, int buyerId, int sellerId, int dealType);

	/**
	 * ��ȡ�̵���Ŀ
	 * 
	 * @param storeId
	 *            �̵�id
	 * @return
	 */
	List<Account> getAccount(int storeId);

}
