package cn.xiyou.petshopsystem.view;

import java.util.List;
import java.util.Scanner;

import cn.xiyou.petshopsystem.entity.Account;
import cn.xiyou.petshopsystem.entity.Pet;
import cn.xiyou.petshopsystem.entity.PetOwner;
import cn.xiyou.petshopsystem.entity.PetStore;
import cn.xiyou.petshopsystem.service.Accountable;
import cn.xiyou.petshopsystem.service.Breedable;
import cn.xiyou.petshopsystem.service.PetOwnerService;
import cn.xiyou.petshopsystem.service.PetStoreService;
import cn.xiyou.petshopsystem.service.impl.PetOwnerServiceImpl;
import cn.xiyou.petshopsystem.service.impl.PetStoreServiceImpl;
import cn.xiyou.petshopsystem.utils.StringUtil;

/**
 * 宠物系统UI界面
 * 
 * @author doudou
 *
 */
public class PetShopSystemView {
	public static Scanner in = new Scanner(System.in);
	PetStoreService petStoreService = new PetStoreServiceImpl();
	PetOwnerService petOwnerService = new PetOwnerServiceImpl();
	Accountable accountable = new PetStoreServiceImpl();
	Breedable breedable = new PetStoreServiceImpl();


	public static void main(String[] args) {
		PetShopSystemView ps = new PetShopSystemView();
		ps.showAllPet();
		ps.showAllPetOwner();
		ps.showAllPetStore();

		System.out.println("\n请选择登录身份，输入1位宠物主人登录，输入2为宠物商店登录：");
		int identityNo = in.nextInt();
		switch (identityNo) {
		case 1:
			ps.petOwnerLogin();
			break;
		case 2:
			ps.petStoreLogin();
			break;
		default:
			ps.petOwnerLogin();
			break;
		}

	}

	/**
	 * 宠物商店登录
	 */
	private void petStoreLogin() {
		System.out.println("请输入宠物商店的名字：");
		String username = "北京宠物收售中心";
		System.out.println("请输入宠物商店的密码：");
		String password = "123";
		PetStore petStore = petStoreService.login(username, password);
		if (petStore == null) {
			System.out.println("抱歉，您的账户未注册或用户名或密码错误！");
			return;
		} else {
			System.out
					.println("恭喜【" + petStore.getName() + "】登录成功！您当前剩余元宝数为：" + petStore.getBalance() + ".0。你可选择以下功能：");
			System.out.println("1. 购买宠物");
			System.out.println("2. 卖出宠物");
			System.out.println("3. 培育宠物");
			System.out.println("4. 查询待售宠物");
			System.out.println("5. 查询商店结余");
			System.out.println("6. 查询商店账目");
			System.out.println("7. 开宠物商店");
			System.out.println("请选择：");
			int doNo = in.nextInt();
			switch (doNo) {
			case 1:
				// 购买宠物
				StoreBuy(petStore);
				break;
			case 2:
				// 卖出宠物
				StoreSell(petStore);
				break;
			case 3:
				// 培育宠物
				breedNewPet(petStore);
				break;
			case 4:
				// 查询待售宠物
				queryWaitingForSale(petStore);
				break;
			case 5:
				// 查询商店结余
				System.out.println("本店剩余元宝数为：" + petStore.getBalance() + ".0");
				break;
			case 6:
				// 查询商店账目
				queryAccount(petStore);
				break;
			case 7:
				// 开宠物商店
				openPetStore();
				break;
			default:
				System.out.println("请输入正确序号！");
				break;
			}
		}
	}

	/**
	 * 商店卖出宠物
	 * 
	 * @param petStore
	 */
	private void StoreSell(PetStore petStore) {
		System.out.println("======================可出售的宠物信息=====================");
		List<Pet> allPetsInCurStore = petStoreService.getAllPetsInCurStore();
		System.out.println("序号\t宠物名称\t宠物种类\t健康值\t亲密度\t出生日期\t\t价格\t库存(0)/新培育(1)");
		for (int i = 0; i < allPetsInCurStore.size(); i++) {
			Pet pet = allPetsInCurStore.get(i);
			System.out.println((i + 1) + "\t" + pet.getName() + "\t" + pet.getType_name() + "\t" + pet.getHealth()
					+ "\t" + pet.getLove() + "\t" + StringUtil.subDateToString(pet.getBirthday()) + "\t"
					+ pet.getPrice() + ".0\t" + pet.getBreedStatus());
		}
		System.out.println("请选择您要购买的宠物：");
		int petNo = in.nextInt();
		Pet pet = allPetsInCurStore.get(petNo - 1);// 获取要购买的宠物
		showAllPetOwner();// 展示所有宠物主人供选择
		System.out.println("请输入买主序号：");
		int OwnerNo = in.nextInt();// 这里主人序号即主人id（两个数值相等）
		int storeId = petStore.getId();// 获取商店id
		// 1.更新宠物信息：指定owner_id，将store_id变为null
		int rows1 = petStoreService.BuyAndUpdatePet(OwnerNo, pet);
		// 2.更新宠物主人信息：减少元宝数
		int rows2 = petOwnerService.updatePetOwner(OwnerNo, -pet.getPrice());
		// 3.更新宠物商店信息：增加元宝数
		int rows3 = petStoreService.updatePetStore(storeId, pet.getPrice());
		if (rows1 > 0 && rows2 > 0 && rows3 > 0) {
			// 4.交易类型（1：商店卖给宠物主人， 2：宠物主人卖给商店 ）
			int dealType = 1;
			// 5.生成台账信息：添加新账目
			int rows = petStoreService.generateDealAccount(pet, OwnerNo, storeId, dealType);
			if (rows > 0) {
				System.out.println("交易成功！台账正确插入一条信息。");
			}
		} else {
			System.out.println("交易失败！");
		}
	}

	/**
	 * 商店购买宠物
	 * 
	 * @param petStore
	 */
	private void StoreBuy(PetStore petStore) {
		// 说明：这里认为所有主人的所有宠物都是可以被购买的。
		System.out.println("======================可被购买的宠物信息=====================");
		List<Pet> petsWaitingForSaleInOwner = petStoreService.getPetsWaitingForSaleInOwner();
		System.out.println("序号\t宠物名称\t宠物种类\t健康值\t亲密度\t出生日期\t\t价格\t库存(0)/新培育(1)");
		for (int i = 0; i < petsWaitingForSaleInOwner.size(); i++) {
			Pet pet = petsWaitingForSaleInOwner.get(i);
			System.out.println((i + 1) + "\t" + pet.getName() + "\t" + pet.getType_name() + "\t" + pet.getHealth()
					+ "\t" + pet.getLove() + "\t" + StringUtil.subDateToString(pet.getBirthday()) + "\t"
					+ pet.getPrice() + ".0\t" + pet.getBreedStatus());
		}
		System.out.println("请选择您要购买的宠物：");
		int petNo = in.nextInt();
		Pet pet = petsWaitingForSaleInOwner.get(petNo - 1);// 获取要购买的宠物
		int storeId = petStore.getId();// 获取商店id
		int OwnerId = pet.getOwner_id();// 获取主人id
		int petId = pet.getId();// 获取宠物id
		// 1.更新宠物信息：指定owner_id，将store_id变为null
		int rows1 = petStoreService.SellAndUpdatePet(storeId, petId);
		// 2.更新宠物主人信息：增加元宝数
		int rows2 = petOwnerService.updatePetOwner(OwnerId, pet.getPrice());
		// 3.更新宠物商店信息：减少元宝数
		int rows3 = petStoreService.updatePetStore(storeId, -pet.getPrice());
		if (rows1 > 0 && rows2 > 0 && rows3 > 0) {
			// 4.交易类型（1：商店卖给宠物主人， 2：宠物主人卖给商店 ）
			int dealType = 2;
			// 5.生成台账信息：添加新账目
			int rows = petStoreService.generateDealAccount(pet, OwnerId, storeId, dealType);
			if (rows > 0) {
				System.out.println("交易成功！台账正确插入一条信息。");
			}
		} else {
			System.out.println("交易失败！");
		}
	}

	/**
	 * 宠物商店培育新宠物
	 * 
	 * @param petStore
	 */
	private void breedNewPet(PetStore petStore) {
		System.out.println("请输入新培育宠物名：");
		String name = in.next();
		System.out.println("请输入新培育宠物的品种：");
		String typeName = in.next();
		System.out.println("请输入新培育宠物的健康值（整型）：");
		int health = in.nextInt();
		System.out.println("请输入新培育宠物与主人能达到的最大亲密度（整型）：");
		int love = in.nextInt();
		System.out.println("请输入新培育宠物售价（整型）：");
		int price = in.nextInt();
		Pet pet = new Pet(petStore.getId(), name, typeName, health, love, price);
		int rows = breedable.breed(pet);
		if (rows > 0) {
			System.out.println("培育新宠物【" + name + "】成功！");
		} else {
			System.out.println("培育新宠物失败！");
		}
	}

	/**
	 * 查询商店账目
	 * 
	 * @param petStore
	 */
	private void queryAccount(PetStore petStore) {
		System.out.println("序号\t宠物编号\t卖家编号\t交易类型\t买家编号\t交易价格\t交易时间");
		List<Account> accountList = accountable.getAccount(petStore.getId());
		for (int i = 0; i < accountList.size(); i++) {
			Account account = accountList.get(i);
			System.out.println(
					(i + 1) + "\t" + account.getPet_id() + "\t" + account.getSeller_id() + "\t" + account.getDeal_type()
							+ "\t" + account.getBuyer_id() + "\t" + account.getPrice() + "\t" + account.getDeal_time());
		}
	}

	/**
	 * 宠物商店开新宠物商店
	 */
	private void openPetStore() {
		System.out.println("请输入要开的新宠物商店名：");
		String name = in.next();
		System.out.println("请输入新宠物商店的密码：");
		String pwd = in.next();
		System.out.println("请输入新宠物商店的备用资金（整数）：");
		Integer balance = in.nextInt();
		PetStore newPetStore = new PetStore(name, pwd, balance);
		int rows = petStoreService.openPetStore(newPetStore);
		if (rows > 0) {
			System.out.println("恭喜【" + name + "】开业！");
		} else {
			System.out.println("开新宠物商店失败！");
		}
	}

	/**
	 * 宠物商店查询待售宠物
	 * 
	 * @param petStore
	 */
	private void queryWaitingForSale(PetStore petStore) {
		List<Pet> petsWaitingForSale = petStoreService.getPetsWaitingForSale(petStore.getId());
		System.out.println("序号\t宠物名称\t宠物种类\t健康值\t亲密度\t出生日期\t\t售价\t库存(0)/新培育(1)");
		for (int i = 0; i < petsWaitingForSale.size(); i++) {
			Pet pet = petsWaitingForSale.get(i);
			System.out.println((i + 1) + "\t" + pet.getName() + "\t" + pet.getType_name() + "\t" + pet.getHealth()
					+ "\t" + pet.getLove() + "\t" + StringUtil.subDateToString(pet.getBirthday()) + "\t"
					+ pet.getPrice() + ".0\t" + pet.getBreedStatus());
		}
	}

	/**
	 * 宠物主人登录
	 */
	private void petOwnerLogin() {
		System.out.println("请输入宠物主人的名字：");
		String username = "zhang";
		System.out.println("请输入宠物主人的密码：");
		String password = "123";
		PetOwner petOwner = petOwnerService.login(username, password);
		if (petOwner == null) {
			System.out.println("抱歉，您的账户未注册或用户名或密码错误！");
			return;
		} else {
			System.out.println("恭喜【" + petOwner.getName() + "】登录成功！您当前剩余元宝数为：" + petOwner.getMoney() + ".0。你可选择以下功能：");
			System.out.println("1. 购买宠物");
			System.out.println("2. 卖出宠物");
			System.out.println("请选择：");
			int doNo = in.nextInt();
			switch (doNo) {
			case 1:
				petOwnerBuy(petOwner);
				break;
			case 2:
				petOwnerSell(petOwner);
				break;
			default:
				System.out.println("请输入正确的序号：");
				break;
			}
		}
	}

	/**
	 * 主人卖宠物
	 */
	private void petOwnerSell(PetOwner petOwner) {
		System.out.println("=============我的宠物列表============");
		System.out.println("序号\t宠物名称\t宠物类型\t元宝数");
		List<Pet> petsInOwner = petStoreService.getPetsInOwner(petOwner);
		for (int i = 0; i < petsInOwner.size(); i++) {
			Pet pet = petsInOwner.get(i);
			System.out.println(i + 1 + "\t" + pet.getName() + "\t" + pet.getType_name() + "\t" + pet.getPrice() + ".0");
		}
		System.out.println("请输入要出售宠物的序号：");
		int petNo = in.nextInt();
		Pet pet = petsInOwner.get(petNo - 1);// 获取主人出售的宠物
		// int storeId = pet.getStore_id();//先获取卖家id（这里指商店id）
		System.out.println("您要卖出名为【" + pet.getName() + "】的" + pet.getType_name() + "？确认输入Y或y，否则输入N或n：");
		String comfirmOrder = in.next();
		if (comfirmOrder.equals("Y") || comfirmOrder.equals("y")) {
			// 展示所有供选择的商店信息
			System.out.println("*********现有宠物商店**********");
			showAllPetStore();
			System.out.println("请选择要出售给商店的商店序号：");
			int petStoreNo = in.nextInt();// 在这里,petStoreNo（商店序号）与商店id相等,就不做获取了
			// 1.更新宠物信息：指定store_id，将owner_id变为null
			int rows1 = petOwnerService.SellAndUpdatePet(petStoreNo, pet.getId());
			// 2.更新宠物主人信息：增加元宝数
			int rows2 = petOwnerService.updatePetOwner(petOwner.getId(), pet.getPrice());
			// 3.更新宠物商店信息：减少元宝数
			int rows3 = petStoreService.updatePetStore(petStoreNo, -pet.getPrice());
			if (rows1 > 0 && rows2 > 0 && rows3 > 0) {
				// 4.交易类型（1：商店卖给宠物主人， 2：宠物主人卖给商店 ）
				int dealType = 2;
				// 5.生成台账信息：添加新账目
				int rows = petStoreService.generateDealAccount(pet, petStoreNo, petOwner.getId(), dealType);
				if (rows > 0) {
					System.out.println("交易成功！台账正确插入一条信息。");
				}
			} else {
				System.out.println("交易失败！");
			}
		} else {
			return;
		}
	}

	/**
	 * 主人买宠物
	 */
	private void petOwnerBuy(PetOwner petOwner) {
		System.out.println("您可购买的宠物范围：");
		System.out.println("1. 购买库存宠物");
		System.out.println("2. 购买新培育宠物");
		System.out.println("请选择：");
		int petRangeNo = in.nextInt();
		switch (petRangeNo) {
		case 1:
			petOwnerBuyPetInStock(petOwner);
			break;
		case 2:
			petOwnerBuyPetNewBreed(petOwner);
			break;
		default:
			System.out.println("请输入正确序号！");
			break;
		}
	}

	/**
	 * 主人购买新培育宠物
	 */
	private void petOwnerBuyPetNewBreed(PetOwner petOwner) {
		List<Pet> petBreed = petStoreService.getPetBreed();
		System.out.println("序号\t宠物名称\t宠物类型\t元宝数");
		for (int i = 0; i < petBreed.size(); i++) {
			Pet pet = petBreed.get(i);
			System.out.println(i + 1 + "\t" + pet.getName() + "\t" + pet.getType_name() + "\t" + pet.getPrice() + ".0");
		}
		System.out.println("请输入要购买宠物的序号：");
		int petNo = in.nextInt();
		Pet pet = petBreed.get(petNo - 1);// 获取主人购买的新培育宠物
		int storeId = pet.getStore_id();// 先获取卖家id（这里指商店id）
		// 1.更新宠物信息：指定owner_id，将store_id变为null
		int rows1 = petOwnerService.BuyAndUpdatePet(petOwner.getId(), pet);
		// 2.更新宠物主人信息：减少元宝数
		int rows2 = petOwnerService.updatePetOwner(petOwner.getId(), -pet.getPrice());
		// 3.更新宠物商店信息：增加元宝数
		int rows3 = petStoreService.updatePetStore(storeId, pet.getPrice());
		if (rows1 > 0 && rows2 > 0 && rows3 > 0) {
			// 4.交易类型（1：商店卖给宠物主人， 2：宠物主人卖给商店 ）
			int dealType = 1;
			// 5.生成台账信息：添加新账目
			int rows = petStoreService.generateDealAccount(pet, petOwner.getId(), storeId, dealType);
			if (rows > 0) {
				System.out.println("交易成功！台账正确插入一条信息。");
			}
		} else {
			System.out.println("交易失败！");
		}
	}

	/**
	 * 主人购买库存宠物
	 */
	private void petOwnerBuyPetInStock(PetOwner petOwner) {
		List<Pet> petsInStock = petStoreService.getPetsInStock();
		System.out.println("序号\t宠物名称\t宠物类型\t元宝数");
		for (int i = 0; i < petsInStock.size(); i++) {
			Pet pet = petsInStock.get(i);
			System.out.println(i + 1 + "\t" + pet.getName() + "\t" + pet.getType_name() + "\t" + pet.getPrice() + ".0");
		}
		System.out.println("请输入要购买宠物的序号：");
		int petNo = in.nextInt();
		Pet pet = petsInStock.get(petNo - 1);// 获取主人购买的宠物
		int storeId = pet.getStore_id();// 先获取卖家id（这里指商店id）
		// 1.更新宠物信息：指定owner_id，将store_id变为null
		int rows1 = petOwnerService.BuyAndUpdatePet(petOwner.getId(), pet);
		// 2.更新宠物主人信息：减少元宝数
		int rows2 = petOwnerService.updatePetOwner(petOwner.getId(), -pet.getPrice());
		// 3.更新宠物商店信息：增加元宝数
		int rows3 = petStoreService.updatePetStore(storeId, pet.getPrice());
		if (rows1 > 0 && rows2 > 0 && rows3 > 0) {
			// 5.交易类型（1：商店卖给宠物主人， 2：宠物主人卖给商店 ）
			int dealType = 1;
			// 6.生成台账信息：添加新账目
			int rows = petStoreService.generateDealAccount(pet, petOwner.getId(), storeId, dealType);
			if (rows > 0) {
				System.out.println("交易成功！台账正确插入一条信息。");
			}
		} else {
			System.out.println("交易失败！");
		}
	}

	/**
	 * 展示所有宠物
	 */
	private void showAllPet() {
		System.out.println("*************************【商店营业中】******************************");
		System.out.println("=========================所有商店中的宠物==========================");
		List<Pet> allPet = petStoreService.getAllPet();
		System.out.println("序号\t宠物名称");
		for (int i = 0; i < allPet.size(); i++) {
			Pet pet = allPet.get(i);
			System.out.println(i + 1 + "\t" + pet.getName());
		}
		System.out.println("==============================================================");
	}

	/**
	 * 展示所有宠物主人
	 */
	private void showAllPetOwner() {
		System.out.println("\n=======================所有宠物主人=============================");
		List<PetOwner> allPetOwner = petOwnerService.getAllPetOwner();
		System.out.println("序号\t宠物主人名");
		for (int i = 0; i < allPetOwner.size(); i++) {
			PetOwner petOwner = allPetOwner.get(i);
			System.out.println(i + 1 + "\t" + petOwner.getName());
		}
		System.out.println("==============================================================");
	}

	/**
	 * 展示所有宠物商店
	 */
	private List<PetStore> showAllPetStore() {
		System.out.println("\n=======================所有宠物商店=============================");
		List<PetStore> allPetStore = petStoreService.getAllPetStore();
		System.out.println("序号\t宠物商店名");
		for (int i = 0; i < allPetStore.size(); i++) {
			PetStore petStore = allPetStore.get(i);
			System.out.println(i + 1 + "\t" + petStore.getName());
		}
		System.out.println("==============================================================");
		return allPetStore;
	}

}
