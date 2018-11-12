package com.ppoo.restaurant.project;

import com.ppoo.restaurant.project.domains.absstract.MenuItem;
import com.ppoo.restaurant.project.domains.exceptions.InvalidInputException;
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

	Scanner scanner;

	public MainAppMenu(FileController fileController){
		waiter = null;
		scanner = new Scanner(System.in);
		administrator = null;
		this.fileController = fileController;
		systemInputController = new SystemInputController(fileController);
	}

	private int readOption()
	{
		System.out.println("Optiunea: ");
		Scanner in = new Scanner(System.in);
		int choice = in.nextInt();
		return choice;
	}

	public void startMainMenu()
	{

		System.out.println("Bine ai venit!");
		boolean ok = true;
		while(ok)
		{
			System.out.println("1 Autentificare administrator");
			System.out.println("2 Autentificare ospatar");
			System.out.println("3 Iesire");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			int choice = readOption();
			switch (choice) {
				case 1:
					//admin

					try{
						if(systemInputController.verifyAdminInfo() == true){
							adminLoggedIn();
						} else
							throw new InvalidInputException("Administrator neidentificat! Nu puteti face modificari in aplicatie!");

					} catch (InvalidInputException e){
						System.out.println(e.toString());
					}

					break;
				case 2:
					//ospatar

					try{
						if(systemInputController.verifyWaiterInfo() == true){
							waiter = systemInputController.getWaiter();
							waiterLoggedIn(waiter);
						}
						else
							throw new InvalidInputException("Ospatar neidentificat! Nu puteti face modificari in aplicatie!");
					} catch (InvalidInputException e){
						System.out.println(e.toString());
					}
					break;
				case 3:
					//iesire

					fileController.exitAppWithFileUpdate();
					ok = false;
					System.out.println("La revedere!");
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					System.out.println("INFO: Toate datele dumneavoastra au fost salvate in fisiere!");
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					try {
						System.in.read();
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				default:
					System.out.println("ERROR: Operatie invalida!");
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					break;
			}
		}

	}
	private void adminLoggedIn()
	{
		boolean ok = true;
		while(ok)
		{
			System.out.println("1 Adauga item in meniu");
			System.out.println("2 Modifica pret item in meniu");
			System.out.println("3 Scoate raport");
			System.out.println("4 Sterge item din meniu");
			System.out.println("5 Adauga utilizator");
			System.out.println("6 Iesire");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			int choice = readOption();
			switch (choice) {
				case 1:
					// adauga item
					systemInputController.addNewMenuItem();
					System.out.println("INFO: MenuItem adaugat cu succes!");
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					break;
				case 2:
					// modifica item
					fileController.showMenu();

					systemInputController.modifyMenuItem();
					System.out.println("INFO: MenuItem modificat cu succes!");
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					break;
				case 3:
					// rapoarte
					reportsMenu();
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					break;
				case 4:
					fileController.showMenu();

					systemInputController.deleteMenuItem();
					System.out.println("INFO: MenuItem sters cu succes!");
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					break;
				case 5:

					systemInputController.insertNewRestaurantEmployee();
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					System.out.println("INFO: Angajat inregistrat cu succes!");
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					break;
				case 6:
					ok = false;
					break;
				default:
					System.out.println("ERROR: Operatie invalida!");
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					break;
			}
		}
	}

	private void waiterLoggedIn(Waiter waiter)
	{
		boolean ok = true;
		while(ok)
		{
			System.out.println("1 Creaza comanda");
			System.out.println("2 Printeaza comanda");
			System.out.println("3 Editeaza comanda");
			System.out.println("4 Iesire");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			int choice = readOption();
			switch (choice) {
				case 1:
					// Creaza comanda
					systemInputController.addNewOrder(waiter);
					System.out.println("INFO: Comanda creata cu succes!");
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					break;
				case 2:
					// Print comanda
					systemInputController.printOrder();
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					break;
				case 3:
					// Edit comanda
					systemInputController.editOrder();
					System.out.println("INFO: Comanda modificata cu succes!");
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					break;
				case 4:
					ok = false;
					break;
				default:
					System.out.println("ERROR: Operatie invalida!");
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					break;
			}
		}
	}

	private void reportsMenu(){

		boolean ok = true;
		while(ok){
			System.out.println("1 Valori comenzi zilnice");
			System.out.println("2 Cele mai vandute produse");
			System.out.println("3 Iesire");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			int choice = readOption();

			switch (choice){
				case 1:
					System.out.println("Alegeti una din categoriile urmatoare: ");
					System.out.println("	1. Valorile tuturor comenzilor grupate pe data");
					System.out.println("	2. Valorile comenzilor cu data specificata");

					Integer option;
					option = scanner.nextInt();

					String dateString;

					if(option == 1){
						systemInputController.valueOfAllOrders();
					} else if(option == 2){
						System.out.print("Introduceti data dorita (dd-MM-YYYY): ");
						dateString = scanner.next();
						systemInputController.valueOfOrdersByDate(dateString);
					}
					break;
				case 2:
					systemInputController.popularMenuItems();
					break;
				case 3:
					ok = false;
					break;
				default:
					System.out.println("ERROR: Operatie invalida!");
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					break;
			}
		}

	}

}
