package cn.xiyou.petshopsystem.entity;

import java.io.Serializable;

/**
 * 宠物主人实体类
 * 
 * @author doudou
 *
 */
public class PetOwner implements Serializable {
	private static final long serialVersionUID = 5205085983569223009L;

	private Integer id;// 宠物主人编号
	private String name;// 宠物主人名
	private String password;// 宠物主人密码
	private Integer money;// 宠物主人元宝数

	public PetOwner() {

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

	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	@Override
	public String toString() {
		return "PetOwner [id=" + id + ", name=" + name + ", password=" + password + ", money=" + money + "]";
	}

}
