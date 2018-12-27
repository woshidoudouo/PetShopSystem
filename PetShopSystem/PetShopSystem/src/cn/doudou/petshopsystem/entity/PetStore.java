package cn.xiyou.petshopsystem.entity;

import java.io.Serializable;

/**
 * 宠物商店实体类
 * 
 * @author doudou
 *
 */
public class PetStore implements Serializable {
	private static final long serialVersionUID = 7347956400657011683L;

	private Integer id;// 商店编号
	private String name;// 宠物商店登录名（宠物商店名字）
	private String password;// 宠物商店密码
	private Integer balance;// 宠物商店结余元宝数

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
