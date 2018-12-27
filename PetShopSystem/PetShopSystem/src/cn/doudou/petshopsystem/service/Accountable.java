package cn.xiyou.petshopsystem.service;

import java.util.List;

import cn.xiyou.petshopsystem.entity.Account;

/**
 * Service层账目信息功能接口
 * 
 * @author XIAOHU
 *
 */
public interface Accountable {

	/**
	 * 获取商店账目
	 * 
	 * @param storeId
	 *            商店id
	 * @return
	 */
	List<Account> getAccount(int storeId);

}
