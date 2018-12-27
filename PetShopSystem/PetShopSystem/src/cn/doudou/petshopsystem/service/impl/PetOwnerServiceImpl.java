package cn.xiyou.petshopsystem.service.impl;

import java.util.List;

import cn.xiyou.petshopsystem.dao.PetDao;
import cn.xiyou.petshopsystem.dao.PetOwnerDao;
import cn.xiyou.petshopsystem.dao.impl.PetDaoImpl;
import cn.xiyou.petshopsystem.dao.impl.PetOwnerDaoImpl;
import cn.xiyou.petshopsystem.entity.Pet;
import cn.xiyou.petshopsystem.entity.PetOwner;
import cn.xiyou.petshopsystem.service.PetOwnerService;

/**
 * Service层宠物主人功能实现类
 * 
 * @author doudou
 *
 */
public class PetOwnerServiceImpl implements PetOwnerService {
	PetDao petDao = new PetDaoImpl();
	PetOwnerDao petOwnerDao = new PetOwnerDaoImpl();

	@Override
	public PetOwner login(String username, String password) {
		return petOwnerDao.login(username, password);
	}

	/**
	 * 获取所有宠物主人
	 * 
	 * @return
	 */
	@Override
	public List<PetOwner> getAllPetOwner() {
		return petOwnerDao.getAllPetOwner();
	}

	/**
	 * 更新主人信息：余额
	 * 
	 * @param id
	 *            主人id
	 * @param money
	 *            宠物价格
	 * @return 返回操作影响的行数
	 */
	@Override
	public int updatePetOwner(int id, int money) {
		return petOwnerDao.updatePetOwner(id, money);
	}

	/**
	 * 更新宠物信息（主人买宠物）
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
	 * 更新宠物信息（主人卖宠物）
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

}
