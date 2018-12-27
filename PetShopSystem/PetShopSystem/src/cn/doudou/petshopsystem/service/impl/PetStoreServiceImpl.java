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
 * Service层宠物商店功能实现类
 * 
 * @author doudou
 *
 */
public class PetStoreServiceImpl implements PetStoreService {
	PetDao petDao = new PetDaoImpl();
	PetStoreDao petStoreDao = new PetStoreDaoImpl();
	AccountDao accountDao = new AccountDaoImpl();

	/**
	 * 获取商店账目
	 * 
	 * @param storeId
	 *            商店id
	 * @return
	 */
	@Override
	public List<Account> getAccount(int storeId) {
		return accountDao.getAccount(storeId);
	}

	/**
	 * 获取库存中所有宠物
	 * 
	 * @return
	 */
	@Override
	public List<Pet> getPetsInStock() {
		return petDao.getPetsInStock();
	}

	/**
	 * 获取新培育宠物
	 * 
	 * @return
	 */
	@Override
	public List<Pet> getPetBreed() {
		return petDao.getPetBreed();
	}

	/**
	 * 宠物商店登录
	 * 
	 * @param username
	 * @param password
	 */
	@Override
	public PetStore login(String username, String password) {
		return petStoreDao.login(username, password);
	}

	/**
	 * 获取所有宠物
	 * 
	 * @return
	 */
	@Override
	public List<Pet> getAllPet() {
		return petDao.getAllPet();
	}

	/**
	 * 获取所有宠物商店
	 * 
	 * @return
	 */
	@Override
	public List<PetStore> getAllPetStore() {
		return petStoreDao.getAllPetStore();
	}

	/**
	 * 更新宠物信息（商店卖宠物）
	 * 
	 * @param OwnerId
	 *            宠物买主id
	 * @param pet
	 *            被购买的那个宠物
	 * @return
	 */
	@Override
	public int BuyAndUpdatePet(int OwnerId, Pet pet) {
		return petDao.BuyAndUpdatePet(OwnerId, pet);
	}

	/**
	 * 更新宠物信息（商店买宠物）
	 * 
	 * @param storeId
	 *            将宠物买给的那个商店的id
	 * @param petId
	 *            被卖出的宠物id
	 */
	@Override
	public int SellAndUpdatePet(int storeId, int petId) {
		return petDao.SellAndUpdatePet(storeId, petId);
	}

	/**
	 * 更新宠物商店信息：商店余额
	 * 
	 * @param storeId
	 *            商店ID
	 * @param price
	 *            宠物价格
	 * @return 返回操作影响的行数
	 */
	@Override
	public int updatePetStore(int storeId, int price) {
		return petStoreDao.updatePetStore(storeId, price);
	}

	/**
	 * 获取主人的宠物列表
	 * 
	 * @param petOwner
	 * @return
	 */
	@Override
	public List<Pet> getPetsInOwner(PetOwner petOwner) {
		return petDao.getPetsInOwner(petOwner);
	}

	/**
	 * 生成交易账目信息
	 * 
	 * @param pet
	 *            被交易的那个宠物
	 * @param buyerId
	 *            买家id
	 * @param sellerId
	 *            卖家id
	 * @param dealType
	 *            交易类型（1：商店卖给宠物主人， 2：宠物主人卖给商店 ）
	 * @return
	 */
	@Override
	public int generateDealAccount(Pet pet, int buyerId, int sellerId, int dealType) {
		return accountDao.generateDealAccount(pet, buyerId, sellerId, dealType);
	}

	/**
	 * 获取指定商店待售宠物
	 * 
	 * @param storeId
	 *            商店id
	 * @return
	 */
	@Override
	public List<Pet> getPetsWaitingForSale(int storeId) {
		return petDao.getPetsWaitingForSale(storeId);
	}

	/**
	 * 新开宠物商店
	 * 
	 * @param petStore
	 *            要开的宠物商店信息实体
	 * @return
	 */
	@Override
	public int openPetStore(PetStore petStore) {
		return petStoreDao.openPetStore(petStore);
	}

	/**
	 * 宠物商店培育新宠物
	 * 
	 * @return 返回操作影响的行数
	 */
	@Override
	public int breed(Pet pet) {
		return petDao.breed(pet);
	}

	/**
	 * 获取所有主人的待售宠物（说明：这里认为所有主人的所有宠物都是可以被购买的。）
	 * 
	 * @return
	 */
	@Override
	public List<Pet> getPetsWaitingForSaleInOwner() {
		return petDao.getPetsWaitingForSaleInOwner();
	}

	/**
	 * 获取当前商店中所有宠物
	 * 
	 * @return
	 */
	@Override
	public List<Pet> getAllPetsInCurStore() {
		return petDao.getAllPetsInCurStore();
	}

}
