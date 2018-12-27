package cn.xiyou.petshopsystem.service;

import java.util.List;

import cn.xiyou.petshopsystem.entity.PetOwner;

/**
 * Service��������˹��ܽӿ�
 * 
 * @author doudou
 *
 */
public interface PetOwnerService extends Buyable, Sellable {

	/**
	 * ��ȡ���г�������
	 * 
	 * @return
	 */
	List<PetOwner> getAllPetOwner();

	/**
	 * �������˵�¼
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	PetOwner login(String username, String password);

	/**
	 * ����������Ϣ�����
	 * 
	 * @param id
	 *            ����id
	 * @param money
	 *            ����۸�
	 * @return ���ز���Ӱ�������
	 */
	int updatePetOwner(int id, int money);

}
