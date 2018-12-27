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
 * Service��������˹���ʵ����
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
	 * ��ȡ���г�������
	 * 
	 * @return
	 */
	@Override
	public List<PetOwner> getAllPetOwner() {
		return petOwnerDao.getAllPetOwner();
	}

	/**
	 * ����������Ϣ�����
	 * 
	 * @param id
	 *            ����id
	 * @param money
	 *            ����۸�
	 * @return ���ز���Ӱ�������
	 */
	@Override
	public int updatePetOwner(int id, int money) {
		return petOwnerDao.updatePetOwner(id, money);
	}

	/**
	 * ���³�����Ϣ����������
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
	 * ���³�����Ϣ�����������
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

}
