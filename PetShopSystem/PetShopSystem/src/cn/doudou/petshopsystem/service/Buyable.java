package cn.xiyou.petshopsystem.service;

import cn.xiyou.petshopsystem.entity.Pet;

/**
 * Service层买家功能接口
 * 
 * @author doudou
 *
 */
public interface Buyable {

	/**
	 * 买宠物
	 * 
	 * @param id
	 *            买主id
	 * @param pet
	 *            被购买的那个宠物
	 * @return
	 */
	int BuyAndUpdatePet(int id, Pet pet);

}
