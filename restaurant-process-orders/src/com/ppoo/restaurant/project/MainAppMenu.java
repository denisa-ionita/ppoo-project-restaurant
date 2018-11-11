package com.ppoo.restaurant.project;

import com.ppoo.restaurant.project.domains.absstract.MenuItem;
import com.ppoo.restaurant.project.domains.users.Administrator;
import com.ppoo.restaurant.project.domains.users.Waiter;
import com.ppoo.restaurant.project.persistance.FileController;
import com.ppoo.restaurant.project.persistance.SystemInputController;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class MainAppMenu {

	FileController fileController;
	SystemInputController systemInputController;

	Waiter waiter;
	Administrator administrator;

	Scanner scanner = new Scanner(System.in);

	List<MenuItem> menuItemList;

	public MainAppMenu(FileController fileController){
		waiter = null;
		administrator = null;
		this.fileController = fileController;
		systemInputController = new SystemInputController(fileController);
	}

	private int readOption()
	{
		System.out.println("Optinuea: ");
		Scanner in = new Scanner(System.in);
		int choice = in.nextInt();
		return choice;
	}

	public void initializeLists(){
//		menuItemList = fileController.getMenu();
	}
	
	public void startMainMenu()
	{

		initializeLists();
		System.out.println("Bine ai venit!");
		boolean ok = true;
		while(ok)
		{
			System.out.println("1 Autentificare administrator");
			System.out.println("2 Autentificare ospatar");
			System.out.println("3 Iesire");
			int choice = readOption();
			switch (choice) {
			case 1:
				//admin

				if(systemInputController.verifyAdminInfo() == true){
					adminLoggedIn();
				} else{
					System.out.println("Administrator neidentificat! Nu puteti face modificari in aplicatie!");
				}

				break;
			case 2:
				//ospatar

				if(systemInputController.verifyWaiterInfo() == true){
					waiter = systemInputController.getWaiter();
					waiterLoggedIn(waiter);
				} else{
					System.out.println("Ospatar neidentificat! Nu puteti face modificari in aplicatie!");
				}
				break;
			case 3:
				//iesire
				ok = false;
				System.out.println("La revedere");
				try {
					System.in.read();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			default:
				System.out.println("Ati introdus ceva gresit.");
				break;
			}
		}
		
	}
	private void adminLoggedIn()
	{
		// adauga menu item/ modificat
		// scote rapoarte
		boolean ok = true;
		while(ok)
		{
			System.out.println("1 Adauga item in meniu");
			System.out.println("2 Modifica pret item in meniu");
			System.out.println("3 Scoate raport");
			System.out.println("4 Iesire");
			int choice = readOption();
			switch (choice) {
			case 1:
				// adauga item
				systemInputController.addNewMenuItem();
				break;
			case 2:
				// modifica item

				fileController.showMenu();

				System.out.print("Alegeti id-ul: ");

				Long id;
				id = scanner.nextLong();

				MenuItem menuItem = fileController.getMenuItemById(id);

				System.out.print("Pret nou: ");

				Double price;
				price = scanner.nextDouble();
				menuItem.setPrice(price);

				fileController.updateMenuItem(menuItem);
				fileController.rewriteMenuItemListToFile();

				break;
			case 3:
				// scoate raport

				// TO DO
				break;
			case 4:
				ok = false;
				break;
			default:
				System.out.println("Ati introdus ceva gresit.");
				
				break;
			}
		}
	}
	
	private void waiterLoggedIn(Waiter waiter)
	{
		//face comenzi
		//scoate comenzi
		// editeaza comanda
		boolean ok = true;
		while(ok)
		{
			System.out.println("1 Creaza comanda");
			System.out.println("2 Scoate comanda");
			System.out.println("3 Editeaza comanda");
			System.out.println("4 Iesire");
			int choice = readOption();
			switch (choice) {
			case 1:
				// Creaza comanda

//				fileController.insertOrder(waiter);
				systemInputController.addNewOrder(waiter);

				break;
			case 2:
				// scoate comanda
				break;
			case 3:
				// editeaza comanda
				break;
			case 4:
				ok = false;
				break;
			default:
				System.out.println("Ati introdus ceva gresit.");
				break;
			}
		}
	}
}
