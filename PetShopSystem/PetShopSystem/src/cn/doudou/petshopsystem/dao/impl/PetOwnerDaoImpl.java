package cn.xiyou.petshopsystem.dao.impl;

import java.util.List;

import cn.xiyou.petshopsystem.dao.BaseDao;
import cn.xiyou.petshopsystem.dao.PetOwnerDao;
import cn.xiyou.petshopsystem.entity.PetOwner;

/**
 * DAO��������˹���ʵ����
 * 
 * @author doudou
 *
 */
public class PetOwnerDaoImpl extends BaseDao<PetOwner> implements PetOwnerDao {

	/**
	 * ��ȡ���г�������
	 * 
	 * @return
	 */
	@Override
	public List<PetOwner> getAllPetOwner() {
		String sql = "select * from petowner";
		return this.selectMany(sql, PetOwner.class);
	}

	/**
	 * �������˵�¼
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	@Override
	public PetOwner login(String username, String password) {
		String sql = "select * from petowner where name = ? and password = ?";
		return this.selectOne(sql, PetOwner.class, username, password);
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
		String sql = "update petowner set money = money+? where id = ?";
		return this.executeUpdate(sql, money, id);
	}

}
