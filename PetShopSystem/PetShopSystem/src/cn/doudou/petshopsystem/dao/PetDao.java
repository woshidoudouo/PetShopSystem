package cn.xiyou.petshopsystem.dao;

import java.util.List;

import cn.xiyou.petshopsystem.entity.Pet;
import cn.xiyou.petshopsystem.entity.PetOwner;

/**
 * DAO层宠物功能接口
 * 
 * @author doudou
 *
 */
public interface PetDao {

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
	 * 更新宠物信息（主人买宠物 或 商店卖宠物）
	 * 
	 * @param OwnerId
	 *            宠物买主id
	 * @param pet
	 *            被购买的那个宠物
	 * @return 返回操作影响的行数
	 */
	int BuyAndUpdatePet(int OwnerId, Pet pet);

	/**
	 * 更新宠物信息（主人卖宠物 或 商店买宠物）
	 * 
	 * @param storeId
	 *            将宠物买给的那个商店的id
	 * @param petId
	 *            被卖出的宠物id
	 * @return 返回操作影响的行数
	 */
	int SellAndUpdatePet(int storeId, int petId);

	/**
	 * 获取新培育宠物
	 * 
	 * @return
	 */
	List<Pet> getPetBreed();

	/**
	 * 获取主人的宠物列表
	 * 
	 * @param petOwner
	 * @return
	 */
	List<Pet> getPetsInOwner(PetOwner petOwner);

	/**
	 * 获取指定商店待售宠物
	 * 
	 * @param storeId
	 *            商店id
	 * @return
	 */
	List<Pet> getPetsWaitingForSale(int storeId);

	/**
	 * 宠物商店培育新宠物
	 * 
	 * @return 返回操作影响的行数
	 */
	int breed(Pet pet);

	/**
	 * 获取所有主人的待售宠物（说明：这里认为所有主人的所有宠物都是可以被购买的。）
	 * 
	 * @return
	 */
	List<Pet> getPetsWaitingForSaleInOwner();

}
