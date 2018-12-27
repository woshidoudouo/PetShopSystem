package cn.xiyou.petshopsystem.service;

/**
 * Service层卖家功能接口
 * 
 * @author doudou
 *
 */
public interface Sellable {

	/**
	 * 卖宠物
	 * 
	 * @param id
	 *            卖主id
	 * @param petId
	 *            被卖出的宠物id
	 */
	int SellAndUpdatePet(int id, int petId);

}
