package cn.xiyou.petshopsystem.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 宠物信息实体类
 * 
 * @author doudou
 *
 */
public class Pet implements Serializable {
	private static final long serialVersionUID = 6774064510061889064L;

	private Integer id;// 宠物编号
	private Integer owner_id;// 主人编号
	private Integer store_id;// 商店编号
	private String name;// 宠物名
	private String type_name;// 宠物类型名
	private Integer health;// 健康值
	private Integer love;// 和主人亲密度
	private Date birthday;// 出生日期
	private Integer price;// 宠物价格
	private Integer breedStatus;// 宠物状态（0：库存宠物，1：新培育宠物）

	public Pet() {

	}

	public Pet(Integer store_id, String name, String type_name, Integer health, Integer love, Integer price) {
		super();
		this.store_id = store_id;
		this.name = name;
		this.type_name = type_name;
		this.health = health;
		this.love = love;
		this.price = price;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOwner_id() {
		return owner_id;
	}

	public void setOwner_id(Integer owner_id) {
		this.owner_id = owner_id;
	}

	public Integer getStore_id() {
		return store_id;
	}

	public void setStore_id(Integer store_id) {
		this.store_id = store_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public Integer getHealth() {
		return health;
	}

	public void setHealth(Integer health) {
		this.health = health;
	}

	public Integer getLove() {
		return love;
	}

	public void setLove(Integer love) {
		this.love = love;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getBreedStatus() {
		return breedStatus;
	}

	public void setBreedStatus(Integer breedStatus) {
		this.breedStatus = breedStatus;
	}

	@Override
	public String toString() {
		return "Pet [id=" + id + ", owner_id=" + owner_id + ", store_id=" + store_id + ", name=" + name + ", type_name="
				+ type_name + ", health=" + health + ", love=" + love + ", birthday=" + birthday + ", price=" + price
				+ ", breedStatus=" + breedStatus + "]";
	}

}
