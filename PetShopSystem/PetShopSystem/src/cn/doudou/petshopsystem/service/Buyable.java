package cn.xiyou.petshopsystem.service;

import cn.xiyou.petshopsystem.entity.Pet;

/**
 * Service����ҹ��ܽӿ�
 * 
 * @author doudou
 *
 */
public interface Buyable {

	/**
	 * �����
	 * 
	 * @param id
	 *            ����id
	 * @param pet
	 *            ��������Ǹ�����
	 * @return
	 */
	int BuyAndUpdatePet(int id, Pet pet);

}
