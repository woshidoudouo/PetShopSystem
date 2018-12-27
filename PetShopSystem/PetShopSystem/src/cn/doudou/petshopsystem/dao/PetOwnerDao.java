package cn.xiyou.petshopsystem.dao;

import java.util.List;

import cn.xiyou.petshopsystem.entity.PetOwner;

/**
 * DAO��������˹��ܽӿ�
 * 
 * @author doudou
 *
 */
public interface PetOwnerDao {

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
	 *            ����������
	 * @param password
	 *            ������������
	 * @return ���ص�¼������Ϣʵ��
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
