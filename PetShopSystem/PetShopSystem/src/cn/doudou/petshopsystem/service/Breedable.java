package cn.xiyou.petshopsystem.service;

import cn.xiyou.petshopsystem.entity.Pet;

/**
 * Service���������﹦�ܽӿ�
 * 
 * @author doudou
 *
 */
public interface Breedable {

	/**
	 * �����̵������³���
	 * 
	 * @return ���ز���Ӱ�������
	 */
	int breed(Pet pet);

}
