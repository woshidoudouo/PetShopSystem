package cn.xiyou.petshopsystem.entity;

import java.io.Serializable;

/**
 * �����̵�ʵ����
 * 
 * @author doudou
 *
 */
public class PetStore implements Serializable {
	private static final long serialVersionUID = 7347956400657011683L;

	private Integer id;// �̵���
	private String name;// �����̵��¼���������̵����֣�
	private String password;// �����̵�����
	private Integer balance;// �����̵����Ԫ����

	public PetStore() {

	}

	public PetStore(String name, String password, Integer balance) {
		super();
		this.name = name;
		this.password = password;
		this.balance = balance;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "PetStore [id=" + id + ", name=" + name + ", password=" + password + ", balance=" + balance + "]";
	}

}
