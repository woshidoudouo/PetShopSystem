package cn.xiyou.petshopsystem.dao;

import java.util.List;

import cn.xiyou.petshopsystem.entity.Pet;
import cn.xiyou.petshopsystem.entity.PetOwner;

/**
 * DAO����﹦�ܽӿ�
 * 
 * @author doudou
 *
 */
public interface PetDao {

	/**
	 * ��ȡ���г���
	 * 
	 * @return
	 */
	List<Pet> getAllPet();

	/**
	 * ��ȡ��������г���
	 * 
	 * @return
	 */
	List<Pet> getPetsInStock();

	/**
	 * ��ȡ��ǰ�̵������г���
	 * 
	 * @return
	 */
	List<Pet> getAllPetsInCurStore();

	/**
	 * ���³�����Ϣ����������� �� �̵������
	 * 
	 * @param OwnerId
	 *            ��������id
	 * @param pet
	 *            ��������Ǹ�����
	 * @return ���ز���Ӱ�������
	 */
	int BuyAndUpdatePet(int OwnerId, Pet pet);

	/**
	 * ���³�����Ϣ������������ �� �̵�����
	 * 
	 * @param storeId
	 *            ������������Ǹ��̵��id
	 * @param petId
	 *            �������ĳ���id
	 * @return ���ز���Ӱ�������
	 */
	int SellAndUpdatePet(int storeId, int petId);

	/**
	 * ��ȡ����������
	 * 
	 * @return
	 */
	List<Pet> getPetBreed();

	/**
	 * ��ȡ���˵ĳ����б�
	 * 
	 * @param petOwner
	 * @return
	 */
	List<Pet> getPetsInOwner(PetOwner petOwner);

	/**
	 * ��ȡָ���̵���۳���
	 * 
	 * @param storeId
	 *            �̵�id
	 * @return
	 */
	List<Pet> getPetsWaitingForSale(int storeId);

	/**
	 * �����̵������³���
	 * 
	 * @return ���ز���Ӱ�������
	 */
	int breed(Pet pet);

	/**
	 * ��ȡ�������˵Ĵ��۳��˵����������Ϊ�������˵����г��ﶼ�ǿ��Ա�����ġ���
	 * 
	 * @return
	 */
	List<Pet> getPetsWaitingForSaleInOwner();

}
