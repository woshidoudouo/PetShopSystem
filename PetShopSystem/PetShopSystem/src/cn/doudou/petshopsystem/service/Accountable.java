package cn.xiyou.petshopsystem.service;

import java.util.List;

import cn.xiyou.petshopsystem.entity.Account;

/**
 * Service����Ŀ��Ϣ���ܽӿ�
 * 
 * @author XIAOHU
 *
 */
public interface Accountable {

	/**
	 * ��ȡ�̵���Ŀ
	 * 
	 * @param storeId
	 *            �̵�id
	 * @return
	 */
	List<Account> getAccount(int storeId);

}
