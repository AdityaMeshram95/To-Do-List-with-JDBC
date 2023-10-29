package project1;

import java.time.LocalDate;
import java.util.Scanner;

public class ToDoListMain {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		while (true) {

			System.out.println("To-Do List menue: ");
			System.out.println("1. Add the task");
			System.out.println("2. Display the task");
			System.out.println("3. Remove the task");
			System.out.println("4. Exit program");
			System.out.println("5. Create a To-Do List");
			System.out.println("The your choice...");

			int choice = sc.nextInt();
			sc.nextLine();

			switch (choice) {

			case 1:
				System.out.println("Enter the ID: ");
				int id = sc.nextInt();
				System.out.println("Enter the TASK: ");
				String desc = sc.next();

				ToDoList.addTask(id, desc);
				break;

			case 2:
				ToDoList.displayTask();
				break;

			case 3:
				System.out.println("Enter the task number to be removed: ");
				int index = sc.nextInt();
				ToDoList.removeTask(index);
				System.out.println("Task removed successfully...");
				break;

			case 4:
				sc.close();
				System.exit(0);
				break;

			case 5:
				ToDoList.initializeDatabase();
				break;

			default:
				System.out.println("Invalid choosen option...");
			}
		}
	}
}
