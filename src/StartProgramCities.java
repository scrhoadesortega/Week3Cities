
import java.util.List;
import java.util.Scanner;

/**
 * @author Sofia Rhoades Ortega - scrhoadesortega
 * CIS175 - Fall 2021
 * Feb 8, 2022
 */

public class StartProgramCities {
	static Scanner in = new Scanner(System.in);
	static CityHelper ch = new CityHelper();
	
	private static void addAnItem() {
		System.out.print("\nEnter a City Name: ");
		String name = in.nextLine();
		System.out.print("Enter its Zip Code: ");
		String zip = in.nextLine();
		System.out.print("Enter its Population: ");
		int pop = in.nextInt();
		City toAdd = new City(name, zip, pop);
		ch.insertItem(toAdd);
	}

	private static void deleteAnItem() {
		System.out.print("\nEnter a City Name to Delete: ");
		String name = in.nextLine();
		System.out.print("Enter its Zip Code to Delete: ");
		String zip = in.nextLine();
		System.out.print("Enter its Population to Delete: ");
		int pop = in.nextInt();
		City toDelete = new City(name, zip, pop);
		ch.deleteItem(toDelete);
	}

	private static void editAnItem() {
		System.out.println("\nHow would you like to search? ");
		System.out.println("1 : Search by City Name");
		System.out.println("2 : Search by Zip Code");
		System.out.println("2 : Search by Population");
		System.out.print("Your selection: ");
		int searchBy = in.nextInt();
		in.nextLine();
		List<City> foundItems;
		if (searchBy == 1) {
			System.out.print("Enter the City Name: ");
			String name = in.nextLine();
			foundItems = ch.searchForPopulationByName(name);
		} else if (searchBy == 2){
			System.out.print("Enter the Zip Code: ");
			String zip = in.nextLine();
			foundItems = ch.searchForPopulationByZip(zip);
		} else {
			System.out.print("Enter the Population: ");
			String pop = in.nextLine();
			foundItems = ch.searchForPopulationByPopulation(pop);
		}

		if (!foundItems.isEmpty()) {
			System.out.println("\nFound Results.");
			for (City c : foundItems) {
				System.out.println(c.getId() + " : " + c.returnCityInfo());
			}
			System.out.print("Which ID to edit: ");
			int idToEdit = in.nextInt();

			City toEdit = ch.searchForPopulationById(idToEdit);
			System.out.println("\nRetrieved " + toEdit.getPopulation() + " Residents From " + toEdit.getName() + "(" + toEdit.getZip() + ")");
			System.out.println("1 : Update City Name");
			System.out.println("2 : Update Zip Code");
			System.out.println("3 : Update Population");
			System.out.print("Your Selection: ");
			int update = in.nextInt();
			in.nextLine();

			if (update == 1) {
				System.out.print("New City Name: ");
				String newName = in.nextLine();
				toEdit.setName(newName);
			} else if (update == 2) {
				System.out.print("New Zip Code: ");
				String newZip = in.nextLine();
				toEdit.setZip(newZip);
			} else {
				System.out.print("New Population: ");
				int newPop = in.nextInt();
				toEdit.setPopulation(newPop);
			}
			ch.updateItem(toEdit);

		} else {
			System.out.println("---- No results found");
		}

	}

	public static void main(String[] args) {
		runMenu();

	}

	public static void runMenu() {
		boolean goAgain = true;
		System.out.println("--- City Population Database ---");
		while (goAgain) {
			System.out.println("\nSelect an item:");
			System.out.println("1 -- Add an item");
			System.out.println("2 -- Edit an item");
			System.out.println("3 -- Delete an item");
			System.out.println("4 -- View the list");
			System.out.println("5 -- Exit");
			System.out.print("Your selection: ");
			int selection = in.nextInt();
			in.nextLine();

			if (selection == 1) {
				addAnItem();
			} else if (selection == 2) {
				editAnItem();
			} else if (selection == 3) {
				deleteAnItem();
			} else if (selection == 4) {
				viewTheList();
			} else {
				ch.cleanUp();
				System.out.println("   Goodbye!   ");
				goAgain = false;
			}

		}

	}

	private static void viewTheList() {
		System.out.print("\n");
		List<City> allItems = ch.showAllItems();
		for(City singleItem : allItems){
			System.out.println(singleItem.returnCityInfo());
		}

	}
	
}
