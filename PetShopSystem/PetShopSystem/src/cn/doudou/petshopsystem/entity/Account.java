package cn.xiyou.petshopsystem.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * ��Ŀ��Ϣʵ����
 * 
 * @author doudou
 *
 */
public class Account implements Serializable {
	private static final long serialVersionUID = -8614742579586077370L;

	private Integer id;// ��Ŀ���
	private Integer pet_id;// ������
	private Integer seller_id;// ���ұ��
	private Integer deal_type;// �������ͣ�1���̵������������ˣ� 2���������������̵� ��
	private Integer buyer_id;// ��ұ��
	private Integer price;// ���׼۸�
	private Date deal_time;// ����ʱ��

	public Account() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPet_id() {
		return pet_id;
	}

	public void setPet_id(Integer pet_id) {
		this.pet_id = pet_id;
	}

	public Integer getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(Integer seller_id) {
		this.seller_id = seller_id;
	}

	public Integer getDeal_type() {
		return deal_type;
	}

	public void setDeal_type(Integer deal_type) {
		this.deal_type = deal_type;
	}

	public Integer getBuyer_id() {
		return buyer_id;
	}

	public void setBuyer_id(Integer buyer_id) {
		this.buyer_id = buyer_id;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Date getDeal_time() {
		return deal_time;
	}

	public void setDeal_time(Date deal_time) {
		this.deal_time = deal_time;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", pet_id=" + pet_id + ", seller_id=" + seller_id + ", deal_type=" + deal_type
				+ ", buyer_id=" + buyer_id + ", price=" + price + ", deal_time=" + deal_time + "]";
	}

}
