package cn.xiyou.petshopsystem.service;

/**
 * Service�����ҹ��ܽӿ�
 * 
 * @author doudou
 *
 */
public interface Sellable {

	/**
	 * ������
	 * 
	 * @param id
	 *            ����id
	 * @param petId
	 *            �������ĳ���id
	 */
	int SellAndUpdatePet(int id, int petId);

}
