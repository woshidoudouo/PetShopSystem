package cn.xiyou.petshopsystem.service.impl;

import java.util.List;

import cn.xiyou.petshopsystem.dao.AccountDao;
import cn.xiyou.petshopsystem.dao.PetDao;
import cn.xiyou.petshopsystem.dao.PetStoreDao;
import cn.xiyou.petshopsystem.dao.impl.AccountDaoImpl;
import cn.xiyou.petshopsystem.dao.impl.PetDaoImpl;
import cn.xiyou.petshopsystem.dao.impl.PetStoreDaoImpl;
import cn.xiyou.petshopsystem.entity.Account;
import cn.xiyou.petshopsystem.entity.Pet;
import cn.xiyou.petshopsystem.entity.PetOwner;
import cn.xiyou.petshopsystem.entity.PetStore;
import cn.xiyou.petshopsystem.service.PetStoreService;

/**
 * Service������̵깦��ʵ����
 * 
 * @author doudou
 *
 */
public class PetStoreServiceImpl implements PetStoreService {
	PetDao petDao = new PetDaoImpl();
	PetStoreDao petStoreDao = new PetStoreDaoImpl();
	AccountDao accountDao = new AccountDaoImpl();

	/**
	 * ��ȡ�̵���Ŀ
	 * 
	 * @param storeId
	 *            �̵�id
	 * @return
	 */
	@Override
	public List<Account> getAccount(int storeId) {
		return accountDao.getAccount(storeId);
	}

	/**
	 * ��ȡ��������г���
	 * 
	 * @return
	 */
	@Override
	public List<Pet> getPetsInStock() {
		return petDao.getPetsInStock();
	}

	/**
	 * ��ȡ����������
	 * 
	 * @return
	 */
	@Override
	public List<Pet> getPetBreed() {
		return petDao.getPetBreed();
	}

	/**
	 * �����̵��¼
	 * 
	 * @param username
	 * @param password
	 */
	@Override
	public PetStore login(String username, String password) {
		return petStoreDao.login(username, password);
	}

	/**
	 * ��ȡ���г���
	 * 
	 * @return
	 */
	@Override
	public List<Pet> getAllPet() {
		return petDao.getAllPet();
	}

	/**
	 * ��ȡ���г����̵�
	 * 
	 * @return
	 */
	@Override
	public List<PetStore> getAllPetStore() {
		return petStoreDao.getAllPetStore();
	}

	/**
	 * ���³�����Ϣ���̵������
	 * 
	 * @param OwnerId
	 *            ��������id
	 * @param pet
	 *            ��������Ǹ�����
	 * @return
	 */
	@Override
	public int BuyAndUpdatePet(int OwnerId, Pet pet) {
		return petDao.BuyAndUpdatePet(OwnerId, pet);
	}

	/**
	 * ���³�����Ϣ���̵�����
	 * 
	 * @param storeId
	 *            ������������Ǹ��̵��id
	 * @param petId
	 *            �������ĳ���id
	 */
	@Override
	public int SellAndUpdatePet(int storeId, int petId) {
		return petDao.SellAndUpdatePet(storeId, petId);
	}

	/**
	 * ���³����̵���Ϣ���̵����
	 * 
	 * @param storeId
	 *            �̵�ID
	 * @param price
	 *            ����۸�
	 * @return ���ز���Ӱ�������
	 */
	@Override
	public int updatePetStore(int storeId, int price) {
		return petStoreDao.updatePetStore(storeId, price);
	}

	/**
	 * ��ȡ���˵ĳ����б�
	 * 
	 * @param petOwner
	 * @return
	 */
	@Override
	public List<Pet> getPetsInOwner(PetOwner petOwner) {
		return petDao.getPetsInOwner(petOwner);
	}

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
		return accountDao.generateDealAccount(pet, buyerId, sellerId, dealType);
	}

	/**
	 * ��ȡָ���̵���۳���
	 * 
	 * @param storeId
	 *            �̵�id
	 * @return
	 */
	@Override
	public List<Pet> getPetsWaitingForSale(int storeId) {
		return petDao.getPetsWaitingForSale(storeId);
	}

	/**
	 * �¿������̵�
	 * 
	 * @param petStore
	 *            Ҫ���ĳ����̵���Ϣʵ��
	 * @return
	 */
	@Override
	public int openPetStore(PetStore petStore) {
		return petStoreDao.openPetStore(petStore);
	}

	/**
	 * �����̵������³���
	 * 
	 * @return ���ز���Ӱ�������
	 */
	@Override
	public int breed(Pet pet) {
		return petDao.breed(pet);
	}

	/**
	 * ��ȡ�������˵Ĵ��۳��˵����������Ϊ�������˵����г��ﶼ�ǿ��Ա�����ġ���
	 * 
	 * @return
	 */
	@Override
	public List<Pet> getPetsWaitingForSaleInOwner() {
		return petDao.getPetsWaitingForSaleInOwner();
	}

	/**
	 * ��ȡ��ǰ�̵������г���
	 * 
	 * @return
	 */
	@Override
	public List<Pet> getAllPetsInCurStore() {
		return petDao.getAllPetsInCurStore();
	}

}
