package cn.xiyou.petshopsystem.service;

import java.util.List;

import cn.xiyou.petshopsystem.entity.Pet;
import cn.xiyou.petshopsystem.entity.PetOwner;
import cn.xiyou.petshopsystem.entity.PetStore;

/**
 * Service������̵깦�ܽӿ�
 * 
 * @author doudou
 *
 */
public interface PetStoreService extends Buyable, Sellable, Breedable, Accountable {

	/**
	 * ��ȡ���г����̵�
	 * 
	 * @return
	 */
	List<PetStore> getAllPetStore();

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
	 * ��ȡ����������
	 * 
	 * @return
	 */
	List<Pet> getPetBreed();

	/**
	 * �����̵��¼
	 * 
	 * @param username
	 *            �̵���
	 * @param password
	 *            �̵�����
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
	 * ��ȡ���˵ĳ����б�
	 * 
	 * @param petOwner
	 * @return
	 */
	List<Pet> getPetsInOwner(PetOwner petOwner);

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
	 * ��ȡָ���̵���۳���
	 * 
	 * @param storeId
	 *            �̵�id
	 * @return
	 */
	List<Pet> getPetsWaitingForSale(int storeId);

	/**
	 * �¿������̵�
	 * 
	 * @param petStore
	 *            Ҫ���ĳ����̵���Ϣʵ��
	 * @return
	 */
	int openPetStore(PetStore petStore);

	/**
	 * ��ȡ�������˵Ĵ��۳��˵����������Ϊ�������˵����г��ﶼ�ǿ��Ա�����ġ���
	 * 
	 * @return
	 */
	List<Pet> getPetsWaitingForSaleInOwner();

}
