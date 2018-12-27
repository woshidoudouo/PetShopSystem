package cn.xiyou.petshopsystem.dao;

import java.util.List;

import cn.xiyou.petshopsystem.entity.PetStore;

/**
 * DAO������̵깦�ܽӿ�
 * 
 * @author doudou
 *
 */
public interface PetStoreDao {

	/**
	 * ��ȡ���г����̵�
	 * 
	 * @return
	 */
	List<PetStore> getAllPetStore();

	/**
	 * �����̵��¼
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	PetStore login(String username, String password);

	/**
	 * ���³����̵���Ϣ���̵����
	 * 
	 * @param storeId
	 *            �̵�ID
	 * @param price
	 *            ����۸�
	 * @return ���ز���Ӱ�������
	 */
	int updatePetStore(int storeId, int price);

	/**
	 * �¿������̵�
	 * 
	 * @param petStore
	 *            Ҫ���ĳ����̵���Ϣʵ��
	 * @return
	 */
	int openPetStore(PetStore petStore);

}
