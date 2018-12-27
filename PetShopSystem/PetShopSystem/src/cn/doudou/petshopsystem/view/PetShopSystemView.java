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
 * ����ϵͳUI����
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

		System.out.println("\n��ѡ���¼��ݣ�����1λ�������˵�¼������2Ϊ�����̵��¼��");
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
	 * �����̵��¼
	 */
	private void petStoreLogin() {
		System.out.println("����������̵�����֣�");
		String username = "����������������";
		System.out.println("����������̵�����룺");
		String password = "123";
		PetStore petStore = petStoreService.login(username, password);
		if (petStore == null) {
			System.out.println("��Ǹ�������˻�δע����û������������");
			return;
		} else {
			System.out
					.println("��ϲ��" + petStore.getName() + "����¼�ɹ�������ǰʣ��Ԫ����Ϊ��" + petStore.getBalance() + ".0�����ѡ�����¹��ܣ�");
			System.out.println("1. �������");
			System.out.println("2. ��������");
			System.out.println("3. ��������");
			System.out.println("4. ��ѯ���۳���");
			System.out.println("5. ��ѯ�̵����");
			System.out.println("6. ��ѯ�̵���Ŀ");
			System.out.println("7. �������̵�");
			System.out.println("��ѡ��");
			int doNo = in.nextInt();
			switch (doNo) {
			case 1:
				// �������
				StoreBuy(petStore);
				break;
			case 2:
				// ��������
				StoreSell(petStore);
				break;
			case 3:
				// ��������
				breedNewPet(petStore);
				break;
			case 4:
				// ��ѯ���۳���
				queryWaitingForSale(petStore);
				break;
			case 5:
				// ��ѯ�̵����
				System.out.println("����ʣ��Ԫ����Ϊ��" + petStore.getBalance() + ".0");
				break;
			case 6:
				// ��ѯ�̵���Ŀ
				queryAccount(petStore);
				break;
			case 7:
				// �������̵�
				openPetStore();
				break;
			default:
				System.out.println("��������ȷ��ţ�");
				break;
			}
		}
	}

	/**
	 * �̵���������
	 * 
	 * @param petStore
	 */
	private void StoreSell(PetStore petStore) {
		System.out.println("======================�ɳ��۵ĳ�����Ϣ=====================");
		List<Pet> allPetsInCurStore = petStoreService.getAllPetsInCurStore();
		System.out.println("���\t��������\t��������\t����ֵ\t���ܶ�\t��������\t\t�۸�\t���(0)/������(1)");
		for (int i = 0; i < allPetsInCurStore.size(); i++) {
			Pet pet = allPetsInCurStore.get(i);
			System.out.println((i + 1) + "\t" + pet.getName() + "\t" + pet.getType_name() + "\t" + pet.getHealth()
					+ "\t" + pet.getLove() + "\t" + StringUtil.subDateToString(pet.getBirthday()) + "\t"
					+ pet.getPrice() + ".0\t" + pet.getBreedStatus());
		}
		System.out.println("��ѡ����Ҫ����ĳ��");
		int petNo = in.nextInt();
		Pet pet = allPetsInCurStore.get(petNo - 1);// ��ȡҪ����ĳ���
		showAllPetOwner();// չʾ���г������˹�ѡ��
		System.out.println("������������ţ�");
		int OwnerNo = in.nextInt();// ����������ż�����id��������ֵ��ȣ�
		int storeId = petStore.getId();// ��ȡ�̵�id
		// 1.���³�����Ϣ��ָ��owner_id����store_id��Ϊnull
		int rows1 = petStoreService.BuyAndUpdatePet(OwnerNo, pet);
		// 2.���³���������Ϣ������Ԫ����
		int rows2 = petOwnerService.updatePetOwner(OwnerNo, -pet.getPrice());
		// 3.���³����̵���Ϣ������Ԫ����
		int rows3 = petStoreService.updatePetStore(storeId, pet.getPrice());
		if (rows1 > 0 && rows2 > 0 && rows3 > 0) {
			// 4.�������ͣ�1���̵������������ˣ� 2���������������̵� ��
			int dealType = 1;
			// 5.����̨����Ϣ���������Ŀ
			int rows = petStoreService.generateDealAccount(pet, OwnerNo, storeId, dealType);
			if (rows > 0) {
				System.out.println("���׳ɹ���̨����ȷ����һ����Ϣ��");
			}
		} else {
			System.out.println("����ʧ�ܣ�");
		}
	}

	/**
	 * �̵깺�����
	 * 
	 * @param petStore
	 */
	private void StoreBuy(PetStore petStore) {
		// ˵����������Ϊ�������˵����г��ﶼ�ǿ��Ա�����ġ�
		System.out.println("======================�ɱ�����ĳ�����Ϣ=====================");
		List<Pet> petsWaitingForSaleInOwner = petStoreService.getPetsWaitingForSaleInOwner();
		System.out.println("���\t��������\t��������\t����ֵ\t���ܶ�\t��������\t\t�۸�\t���(0)/������(1)");
		for (int i = 0; i < petsWaitingForSaleInOwner.size(); i++) {
			Pet pet = petsWaitingForSaleInOwner.get(i);
			System.out.println((i + 1) + "\t" + pet.getName() + "\t" + pet.getType_name() + "\t" + pet.getHealth()
					+ "\t" + pet.getLove() + "\t" + StringUtil.subDateToString(pet.getBirthday()) + "\t"
					+ pet.getPrice() + ".0\t" + pet.getBreedStatus());
		}
		System.out.println("��ѡ����Ҫ����ĳ��");
		int petNo = in.nextInt();
		Pet pet = petsWaitingForSaleInOwner.get(petNo - 1);// ��ȡҪ����ĳ���
		int storeId = petStore.getId();// ��ȡ�̵�id
		int OwnerId = pet.getOwner_id();// ��ȡ����id
		int petId = pet.getId();// ��ȡ����id
		// 1.���³�����Ϣ��ָ��owner_id����store_id��Ϊnull
		int rows1 = petStoreService.SellAndUpdatePet(storeId, petId);
		// 2.���³���������Ϣ������Ԫ����
		int rows2 = petOwnerService.updatePetOwner(OwnerId, pet.getPrice());
		// 3.���³����̵���Ϣ������Ԫ����
		int rows3 = petStoreService.updatePetStore(storeId, -pet.getPrice());
		if (rows1 > 0 && rows2 > 0 && rows3 > 0) {
			// 4.�������ͣ�1���̵������������ˣ� 2���������������̵� ��
			int dealType = 2;
			// 5.����̨����Ϣ���������Ŀ
			int rows = petStoreService.generateDealAccount(pet, OwnerId, storeId, dealType);
			if (rows > 0) {
				System.out.println("���׳ɹ���̨����ȷ����һ����Ϣ��");
			}
		} else {
			System.out.println("����ʧ�ܣ�");
		}
	}

	/**
	 * �����̵������³���
	 * 
	 * @param petStore
	 */
	private void breedNewPet(PetStore petStore) {
		System.out.println("��������������������");
		String name = in.next();
		System.out.println("�����������������Ʒ�֣�");
		String typeName = in.next();
		System.out.println("����������������Ľ���ֵ�����ͣ���");
		int health = in.nextInt();
		System.out.println("�����������������������ܴﵽ��������ܶȣ����ͣ���");
		int love = in.nextInt();
		System.out.println("�����������������ۼۣ����ͣ���");
		int price = in.nextInt();
		Pet pet = new Pet(petStore.getId(), name, typeName, health, love, price);
		int rows = breedable.breed(pet);
		if (rows > 0) {
			System.out.println("�����³��" + name + "���ɹ���");
		} else {
			System.out.println("�����³���ʧ�ܣ�");
		}
	}

	/**
	 * ��ѯ�̵���Ŀ
	 * 
	 * @param petStore
	 */
	private void queryAccount(PetStore petStore) {
		System.out.println("���\t������\t���ұ��\t��������\t��ұ��\t���׼۸�\t����ʱ��");
		List<Account> accountList = accountable.getAccount(petStore.getId());
		for (int i = 0; i < accountList.size(); i++) {
			Account account = accountList.get(i);
			System.out.println(
					(i + 1) + "\t" + account.getPet_id() + "\t" + account.getSeller_id() + "\t" + account.getDeal_type()
							+ "\t" + account.getBuyer_id() + "\t" + account.getPrice() + "\t" + account.getDeal_time());
		}
	}

	/**
	 * �����̵꿪�³����̵�
	 */
	private void openPetStore() {
		System.out.println("������Ҫ�����³����̵�����");
		String name = in.next();
		System.out.println("�������³����̵�����룺");
		String pwd = in.next();
		System.out.println("�������³����̵�ı����ʽ���������");
		Integer balance = in.nextInt();
		PetStore newPetStore = new PetStore(name, pwd, balance);
		int rows = petStoreService.openPetStore(newPetStore);
		if (rows > 0) {
			System.out.println("��ϲ��" + name + "����ҵ��");
		} else {
			System.out.println("���³����̵�ʧ�ܣ�");
		}
	}

	/**
	 * �����̵��ѯ���۳���
	 * 
	 * @param petStore
	 */
	private void queryWaitingForSale(PetStore petStore) {
		List<Pet> petsWaitingForSale = petStoreService.getPetsWaitingForSale(petStore.getId());
		System.out.println("���\t��������\t��������\t����ֵ\t���ܶ�\t��������\t\t�ۼ�\t���(0)/������(1)");
		for (int i = 0; i < petsWaitingForSale.size(); i++) {
			Pet pet = petsWaitingForSale.get(i);
			System.out.println((i + 1) + "\t" + pet.getName() + "\t" + pet.getType_name() + "\t" + pet.getHealth()
					+ "\t" + pet.getLove() + "\t" + StringUtil.subDateToString(pet.getBirthday()) + "\t"
					+ pet.getPrice() + ".0\t" + pet.getBreedStatus());
		}
	}

	/**
	 * �������˵�¼
	 */
	private void petOwnerLogin() {
		System.out.println("������������˵����֣�");
		String username = "zhang";
		System.out.println("������������˵����룺");
		String password = "123";
		PetOwner petOwner = petOwnerService.login(username, password);
		if (petOwner == null) {
			System.out.println("��Ǹ�������˻�δע����û������������");
			return;
		} else {
			System.out.println("��ϲ��" + petOwner.getName() + "����¼�ɹ�������ǰʣ��Ԫ����Ϊ��" + petOwner.getMoney() + ".0�����ѡ�����¹��ܣ�");
			System.out.println("1. �������");
			System.out.println("2. ��������");
			System.out.println("��ѡ��");
			int doNo = in.nextInt();
			switch (doNo) {
			case 1:
				petOwnerBuy(petOwner);
				break;
			case 2:
				petOwnerSell(petOwner);
				break;
			default:
				System.out.println("��������ȷ����ţ�");
				break;
			}
		}
	}

	/**
	 * ����������
	 */
	private void petOwnerSell(PetOwner petOwner) {
		System.out.println("=============�ҵĳ����б�============");
		System.out.println("���\t��������\t��������\tԪ����");
		List<Pet> petsInOwner = petStoreService.getPetsInOwner(petOwner);
		for (int i = 0; i < petsInOwner.size(); i++) {
			Pet pet = petsInOwner.get(i);
			System.out.println(i + 1 + "\t" + pet.getName() + "\t" + pet.getType_name() + "\t" + pet.getPrice() + ".0");
		}
		System.out.println("������Ҫ���۳������ţ�");
		int petNo = in.nextInt();
		Pet pet = petsInOwner.get(petNo - 1);// ��ȡ���˳��۵ĳ���
		// int storeId = pet.getStore_id();//�Ȼ�ȡ����id������ָ�̵�id��
		System.out.println("��Ҫ������Ϊ��" + pet.getName() + "����" + pet.getType_name() + "��ȷ������Y��y����������N��n��");
		String comfirmOrder = in.next();
		if (comfirmOrder.equals("Y") || comfirmOrder.equals("y")) {
			// չʾ���й�ѡ����̵���Ϣ
			System.out.println("*********���г����̵�**********");
			showAllPetStore();
			System.out.println("��ѡ��Ҫ���۸��̵���̵���ţ�");
			int petStoreNo = in.nextInt();// ������,petStoreNo���̵���ţ����̵�id���,�Ͳ�����ȡ��
			// 1.���³�����Ϣ��ָ��store_id����owner_id��Ϊnull
			int rows1 = petOwnerService.SellAndUpdatePet(petStoreNo, pet.getId());
			// 2.���³���������Ϣ������Ԫ����
			int rows2 = petOwnerService.updatePetOwner(petOwner.getId(), pet.getPrice());
			// 3.���³����̵���Ϣ������Ԫ����
			int rows3 = petStoreService.updatePetStore(petStoreNo, -pet.getPrice());
			if (rows1 > 0 && rows2 > 0 && rows3 > 0) {
				// 4.�������ͣ�1���̵������������ˣ� 2���������������̵� ��
				int dealType = 2;
				// 5.����̨����Ϣ���������Ŀ
				int rows = petStoreService.generateDealAccount(pet, petStoreNo, petOwner.getId(), dealType);
				if (rows > 0) {
					System.out.println("���׳ɹ���̨����ȷ����һ����Ϣ��");
				}
			} else {
				System.out.println("����ʧ�ܣ�");
			}
		} else {
			return;
		}
	}

	/**
	 * ���������
	 */
	private void petOwnerBuy(PetOwner petOwner) {
		System.out.println("���ɹ���ĳ��ﷶΧ��");
		System.out.println("1. ���������");
		System.out.println("2. ��������������");
		System.out.println("��ѡ��");
		int petRangeNo = in.nextInt();
		switch (petRangeNo) {
		case 1:
			petOwnerBuyPetInStock(petOwner);
			break;
		case 2:
			petOwnerBuyPetNewBreed(petOwner);
			break;
		default:
			System.out.println("��������ȷ��ţ�");
			break;
		}
	}

	/**
	 * ���˹�������������
	 */
	private void petOwnerBuyPetNewBreed(PetOwner petOwner) {
		List<Pet> petBreed = petStoreService.getPetBreed();
		System.out.println("���\t��������\t��������\tԪ����");
		for (int i = 0; i < petBreed.size(); i++) {
			Pet pet = petBreed.get(i);
			System.out.println(i + 1 + "\t" + pet.getName() + "\t" + pet.getType_name() + "\t" + pet.getPrice() + ".0");
		}
		System.out.println("������Ҫ����������ţ�");
		int petNo = in.nextInt();
		Pet pet = petBreed.get(petNo - 1);// ��ȡ���˹��������������
		int storeId = pet.getStore_id();// �Ȼ�ȡ����id������ָ�̵�id��
		// 1.���³�����Ϣ��ָ��owner_id����store_id��Ϊnull
		int rows1 = petOwnerService.BuyAndUpdatePet(petOwner.getId(), pet);
		// 2.���³���������Ϣ������Ԫ����
		int rows2 = petOwnerService.updatePetOwner(petOwner.getId(), -pet.getPrice());
		// 3.���³����̵���Ϣ������Ԫ����
		int rows3 = petStoreService.updatePetStore(storeId, pet.getPrice());
		if (rows1 > 0 && rows2 > 0 && rows3 > 0) {
			// 4.�������ͣ�1���̵������������ˣ� 2���������������̵� ��
			int dealType = 1;
			// 5.����̨����Ϣ���������Ŀ
			int rows = petStoreService.generateDealAccount(pet, petOwner.getId(), storeId, dealType);
			if (rows > 0) {
				System.out.println("���׳ɹ���̨����ȷ����һ����Ϣ��");
			}
		} else {
			System.out.println("����ʧ�ܣ�");
		}
	}

	/**
	 * ���˹��������
	 */
	private void petOwnerBuyPetInStock(PetOwner petOwner) {
		List<Pet> petsInStock = petStoreService.getPetsInStock();
		System.out.println("���\t��������\t��������\tԪ����");
		for (int i = 0; i < petsInStock.size(); i++) {
			Pet pet = petsInStock.get(i);
			System.out.println(i + 1 + "\t" + pet.getName() + "\t" + pet.getType_name() + "\t" + pet.getPrice() + ".0");
		}
		System.out.println("������Ҫ����������ţ�");
		int petNo = in.nextInt();
		Pet pet = petsInStock.get(petNo - 1);// ��ȡ���˹���ĳ���
		int storeId = pet.getStore_id();// �Ȼ�ȡ����id������ָ�̵�id��
		// 1.���³�����Ϣ��ָ��owner_id����store_id��Ϊnull
		int rows1 = petOwnerService.BuyAndUpdatePet(petOwner.getId(), pet);
		// 2.���³���������Ϣ������Ԫ����
		int rows2 = petOwnerService.updatePetOwner(petOwner.getId(), -pet.getPrice());
		// 3.���³����̵���Ϣ������Ԫ����
		int rows3 = petStoreService.updatePetStore(storeId, pet.getPrice());
		if (rows1 > 0 && rows2 > 0 && rows3 > 0) {
			// 5.�������ͣ�1���̵������������ˣ� 2���������������̵� ��
			int dealType = 1;
			// 6.����̨����Ϣ���������Ŀ
			int rows = petStoreService.generateDealAccount(pet, petOwner.getId(), storeId, dealType);
			if (rows > 0) {
				System.out.println("���׳ɹ���̨����ȷ����һ����Ϣ��");
			}
		} else {
			System.out.println("����ʧ�ܣ�");
		}
	}

	/**
	 * չʾ���г���
	 */
	private void showAllPet() {
		System.out.println("*************************���̵�Ӫҵ�С�******************************");
		System.out.println("=========================�����̵��еĳ���==========================");
		List<Pet> allPet = petStoreService.getAllPet();
		System.out.println("���\t��������");
		for (int i = 0; i < allPet.size(); i++) {
			Pet pet = allPet.get(i);
			System.out.println(i + 1 + "\t" + pet.getName());
		}
		System.out.println("==============================================================");
	}

	/**
	 * չʾ���г�������
	 */
	private void showAllPetOwner() {
		System.out.println("\n=======================���г�������=============================");
		List<PetOwner> allPetOwner = petOwnerService.getAllPetOwner();
		System.out.println("���\t����������");
		for (int i = 0; i < allPetOwner.size(); i++) {
			PetOwner petOwner = allPetOwner.get(i);
			System.out.println(i + 1 + "\t" + petOwner.getName());
		}
		System.out.println("==============================================================");
	}

	/**
	 * չʾ���г����̵�
	 */
	private List<PetStore> showAllPetStore() {
		System.out.println("\n=======================���г����̵�=============================");
		List<PetStore> allPetStore = petStoreService.getAllPetStore();
		System.out.println("���\t�����̵���");
		for (int i = 0; i < allPetStore.size(); i++) {
			PetStore petStore = allPetStore.get(i);
			System.out.println(i + 1 + "\t" + petStore.getName());
		}
		System.out.println("==============================================================");
		return allPetStore;
	}

}
