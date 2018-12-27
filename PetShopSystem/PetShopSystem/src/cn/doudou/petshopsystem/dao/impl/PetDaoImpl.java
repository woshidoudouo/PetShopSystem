package cn.xiyou.petshopsystem.dao.impl;

import java.util.List;

import cn.xiyou.petshopsystem.dao.BaseDao;
import cn.xiyou.petshopsystem.dao.PetDao;
import cn.xiyou.petshopsystem.entity.Pet;
import cn.xiyou.petshopsystem.entity.PetOwner;

/**
 * DAO层宠物功能实现类
 * 
 * @author doudou
 *
 */
public class PetDaoImpl extends BaseDao<Pet> implements PetDao {

	/**
	 * 获取所有宠物
	 * 
	 * @return
	 */
	@Override
	public List<Pet> getAllPet() {
		String sql = "select * from pet";
		return this.selectMany(sql, Pet.class);
	}

	/**
	 * 更新宠物信息（主人买宠物 或 商店卖宠物）
	 * 
	 * @param OwnerId
	 *            宠物买主id
	 * @param pet
	 *            被购买的那个宠物
	 * @return 返回操作影响的行数
	 */
	@Override
	public int BuyAndUpdatePet(int OwnerId, Pet pet) {
		String sql = "update pet set owner_id = ?,store_id = ? where id = ?";
		return this.executeUpdate(sql, OwnerId, null, pet.getId());
	}

	/**
	 * 更新宠物信息（主人卖宠物 或 商店买宠物）
	 * 
	 * @param storeId
	 *            将宠物买给的那个商店的id
	 * @param petId
	 *            被卖出的宠物id
	 * @return 返回操作影响的行数
	 */
	@Override
	public int SellAndUpdatePet(int storeId, int petId) {
		String sql = "update pet set owner_id = ?,store_id = ? where id = ?";
		return this.executeUpdate(sql, null, storeId, petId);
	}

	/**
	 * 获取库存中所有宠物
	 * 
	 * @return
	 */
	@Override
	public List<Pet> getPetsInStock() {
		String sql = "select * from pet where breedstatus = ? and owner_id is null";
		return this.selectMany(sql, Pet.class, 0);
	}

	/**
	 * 获取新培育宠物
	 * 
	 * @return
	 */
	@Override
	public List<Pet> getPetBreed() {
		String sql = "select * from pet where breedstatus = ? and owner_id is null";
		return this.selectMany(sql, Pet.class, 1);
	}

	/**
	 * 获取主人的宠物列表
	 * 
	 * @param petOwner
	 * @return
	 */
	@Override
	public List<Pet> getPetsInOwner(PetOwner petOwner) {
		String sql = "select * from pet where owner_id = ?";
		return this.selectMany(sql, Pet.class, petOwner.getId());
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
		String sql = "select * from pet where store_id = ?";
		return this.selectMany(sql, Pet.class, storeId);
	}

	/**
	 * 宠物商店培育新宠物：宠物状态（0：库存宠物，1：新培育宠物）
	 * 
	 * @return 返回操作影响的行数
	 */
	@Override
	public int breed(Pet pet) {
		String sql = "insert into pet values(?,?,?,?,?,?,?,?,?,?)";
		Object[] args = { null, null, pet.getStore_id(), pet.getName(), pet.getType_name(), pet.getHealth(),
				pet.getLove(), null, pet.getPrice(), 1 };
		return this.executeUpdate(sql, args);
	}

	/**
	 * 获取所有主人的待售宠物（说明：这里认为所有主人的所有宠物都是可以被购买的。）
	 * 
	 * @return
	 */
	@Override
	public List<Pet> getPetsWaitingForSaleInOwner() {
		String sql = "select * from pet where store_id is null";
		return this.selectMany(sql, Pet.class);
	}

	/**
	 * 获取当前商店中所有宠物
	 * 
	 * @return
	 */
	@Override
	public List<Pet> getAllPetsInCurStore() {
		String sql = "select * from pet where store_id is not null";
		return this.selectMany(sql, Pet.class);
	}

}
