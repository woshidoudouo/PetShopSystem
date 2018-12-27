package cn.xiyou.petshopsystem.service;

import cn.xiyou.petshopsystem.entity.Pet;

/**
 * Service层培育宠物功能接口
 * 
 * @author doudou
 *
 */
public interface Breedable {

	/**
	 * 宠物商店培育新宠物
	 * 
	 * @return 返回操作影响的行数
	 */
	int breed(Pet pet);

}
