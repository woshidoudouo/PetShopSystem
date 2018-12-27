package cn.xiyou.petshopsystem.dao.impl;

import java.util.List;

import cn.xiyou.petshopsystem.dao.BaseDao;
import cn.xiyou.petshopsystem.entity.PetStore;

/**
 * DAO������̵깦��ʵ����
 * 
 * @author doudou
 *
 */
public class PetStoreDaoImpl extends BaseDao<PetStore> implements cn.xiyou.petshopsystem.dao.PetStoreDao {

	/**
	 * ��ȡ���г����̵�
	 * 
	 * @return
	 */
	@Override
	public List<PetStore> getAllPetStore() {
		String sql = "select * from petstore";
		return this.selectMany(sql, PetStore.class);
	}

	/**
	 * �����̵��¼
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	@Override
	public PetStore login(String username, String password) {
		String sql = "select * from petstore where name = ? and password = ?";
		return this.selectOne(sql, PetStore.class, username, password);
	}

	/**
	 * ���³����̵���Ϣ���̵����
	 * 
	 * @param storeId
	 *            �̵�ID
	 * @param price
	 *            ����۸�
	 * @return ���ز���Ӱ�������
	 */
	@Override
	public int updatePetStore(int storeId, int price) {
		String sql = "update petstore set balance = balance+? where id = ?";
		return this.executeUpdate(sql, price, storeId);
	}

	/**
	 * �¿������̵�
	 * 
	 * @param petStore
	 *            Ҫ���ĳ����̵���Ϣʵ��
	 * @return
	 */
	@Override
	public int openPetStore(PetStore petStore) {
		String sql = "insert into petstore values(?,?,?,?)";
		return this.executeUpdate(sql, null, petStore.getName(), petStore.getPassword(), petStore.getBalance());
	}

}
