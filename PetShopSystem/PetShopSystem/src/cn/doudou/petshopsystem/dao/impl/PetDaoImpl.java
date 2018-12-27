package cn.xiyou.petshopsystem.dao.impl;

import java.util.List;

import cn.xiyou.petshopsystem.dao.BaseDao;
import cn.xiyou.petshopsystem.dao.PetDao;
import cn.xiyou.petshopsystem.entity.Pet;
import cn.xiyou.petshopsystem.entity.PetOwner;

/**
 * DAO����﹦��ʵ����
 * 
 * @author doudou
 *
 */
public class PetDaoImpl extends BaseDao<Pet> implements PetDao {

	/**
	 * ��ȡ���г���
	 * 
	 * @return
	 */
	@Override
	public List<Pet> getAllPet() {
		String sql = "select * from pet";
		return this.selectMany(sql, Pet.class);
	}

	/**
	 * ���³�����Ϣ����������� �� �̵������
	 * 
	 * @param OwnerId
	 *            ��������id
	 * @param pet
	 *            ��������Ǹ�����
	 * @return ���ز���Ӱ�������
	 */
	@Override
	public int BuyAndUpdatePet(int OwnerId, Pet pet) {
		String sql = "update pet set owner_id = ?,store_id = ? where id = ?";
		return this.executeUpdate(sql, OwnerId, null, pet.getId());
	}

	/**
	 * ���³�����Ϣ������������ �� �̵�����
	 * 
	 * @param storeId
	 *            ������������Ǹ��̵��id
	 * @param petId
	 *            �������ĳ���id
	 * @return ���ز���Ӱ�������
	 */
	@Override
	public int SellAndUpdatePet(int storeId, int petId) {
		String sql = "update pet set owner_id = ?,store_id = ? where id = ?";
		return this.executeUpdate(sql, null, storeId, petId);
	}

	/**
	 * ��ȡ��������г���
	 * 
	 * @return
	 */
	@Override
	public List<Pet> getPetsInStock() {
		String sql = "select * from pet where breedstatus = ? and owner_id is null";
		return this.selectMany(sql, Pet.class, 0);
	}

	/**
	 * ��ȡ����������
	 * 
	 * @return
	 */
	@Override
	public List<Pet> getPetBreed() {
		String sql = "select * from pet where breedstatus = ? and owner_id is null";
		return this.selectMany(sql, Pet.class, 1);
	}

	/**
	 * ��ȡ���˵ĳ����б�
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
	 * ��ȡָ���̵���۳���
	 * 
	 * @param storeId
	 *            �̵�id
	 * @return
	 */
	@Override
	public List<Pet> getPetsWaitingForSale(int storeId) {
		String sql = "select * from pet where store_id = ?";
		return this.selectMany(sql, Pet.class, storeId);
	}

	/**
	 * �����̵������³������״̬��0�������1�����������
	 * 
	 * @return ���ز���Ӱ�������
	 */
	@Override
	public int breed(Pet pet) {
		String sql = "insert into pet values(?,?,?,?,?,?,?,?,?,?)";
		Object[] args = { null, null, pet.getStore_id(), pet.getName(), pet.getType_name(), pet.getHealth(),
				pet.getLove(), null, pet.getPrice(), 1 };
		return this.executeUpdate(sql, args);
	}

	/**
	 * ��ȡ�������˵Ĵ��۳��˵����������Ϊ�������˵����г��ﶼ�ǿ��Ա�����ġ���
	 * 
	 * @return
	 */
	@Override
	public List<Pet> getPetsWaitingForSaleInOwner() {
		String sql = "select * from pet where store_id is null";
		return this.selectMany(sql, Pet.class);
	}

	/**
	 * ��ȡ��ǰ�̵������г���
	 * 
	 * @return
	 */
	@Override
	public List<Pet> getAllPetsInCurStore() {
		String sql = "select * from pet where store_id is not null";
		return this.selectMany(sql, Pet.class);
	}

}
