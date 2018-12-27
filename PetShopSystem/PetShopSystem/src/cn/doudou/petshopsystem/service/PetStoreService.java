package cn.xiyou.petshopsystem.service;

import java.util.List;

import cn.xiyou.petshopsystem.entity.Pet;
import cn.xiyou.petshopsystem.entity.PetOwner;
import cn.xiyou.petshopsystem.entity.PetStore;

/**
 * Service层宠物商店功能接口
 * 
 * @author doudou
 *
 */
public interface PetStoreService extends Buyable, Sellable, Breedable, Accountable {

	/**
	 * 获取所有宠物商店
	 * 
	 * @return
	 */
	List<PetStore> getAllPetStore();

	/**
	 * 获取所有宠物
	 * 
	 * @return
	 */
	List<Pet> getAllPet();

	/**
	 * 获取库存中所有宠物
	 * 
	 * @return
	 */
	List<Pet> getPetsInStock();

	/**
	 * 获取当前商店中所有宠物
	 * 
	 * @return
	 */
	List<Pet> getAllPetsInCurStore();

	/**
	 * 获取新培育宠物
	 * 
	 * @return
	 */
	List<Pet> getPetBreed();

	/**
	 * 宠物商店登录
	 * 
	 * @param username
	 *            商店名
	 * @param password
	 *            商店密码
	 * @return
	 */
	PetStore login(String username, String password);

	/**
	 * 更新宠物商店信息：商店余额
	 * 
	 * @param storeId
	 *            商店ID
	 * @param price
	 *            宠物价格
	 * @return 返回操作影响的行数
	 */
	int updatePetStore(int storeId, int price);

	/**
	 * 获取主人的宠物列表
	 * 
	 * @param petOwner
	 * @return
	 */
	List<Pet> getPetsInOwner(PetOwner petOwner);

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
	int generateDealAccount(Pet pet, int buyerId, int sellerId, int dealType);

	/**
	 * 获取指定商店待售宠物
	 * 
	 * @param storeId
	 *            商店id
	 * @return
	 */
	List<Pet> getPetsWaitingForSale(int storeId);

	/**
	 * 新开宠物商店
	 * 
	 * @param petStore
	 *            要开的宠物商店信息实体
	 * @return
	 */
	int openPetStore(PetStore petStore);

	/**
	 * 获取所有主人的待售宠物（说明：这里认为所有主人的所有宠物都是可以被购买的。）
	 * 
	 * @return
	 */
	List<Pet> getPetsWaitingForSaleInOwner();

}
