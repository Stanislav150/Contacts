package contacts;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static final Scanner scanner = new Scanner(System.in);
    public static boolean repeat = true;
    List<ContactPerson> contacts = new ArrayList<>();

    public void menu() {
        System.out.print("Enter action (add, remove, edit, count, list, exit): ");
        String command = scanner.nextLine();
        switch (command) {
            case "add":
                addContact();
                break;
            case "remove":
                removeContact();
                break;
            case "edit":
                editContact();
                break;
            case "count":
                count();
                break;
            case "list":
                showList();
                break;
            case "exit":
                exit();
                break;
            default:
                System.out.println("Bad parameters!");
                System.out.print("Input command: ");

        }
    }

    public void addContact() {
        System.out.print("Enter the name: ");
        String name = scanner.nextLine();
        System.out.print("Enter the surname: ");
        String surname = scanner.nextLine();
        System.out.print("Enter the number: ");
        String number = scanner.nextLine();
        ContactPerson contact = new ContactPerson().setName(name)
                .setSurname(surname)
                .setPhoneNamber(number);
        contacts.add(contact);
        System.out.println("The record added.");
    }

    /**
     *
     */
    public void showList() {
        int count = 1;
        for (ContactPerson c : contacts) {
            System.out.printf("%d. %s%n", count, c.toString());
            count++;
        }
    }

    /**
     * Enter action (add, remove, edit, count, list, exit): > remove
     * 1. John Smith, +0 (123) 456-789-ABcd
     * 2. Adam Jones, (123) 234 345-456
     * Select a record: > 1
     * The record removed!
     */
    public void removeContact() {
        if (contacts.size() == 0) System.out.println("No records to remove!");
        else {
            showList();
            System.out.println("Select a record: ");
            int index = scanner.nextInt() - 1;
            contacts.remove(index);
            System.out.println("The record removed!");
        }
    }

    /**
     * Enter action (add, remove, edit, count, list, exit): > edit
     * 1. John Smith, +0 (123) 456-789-ABcd
     * 2. Adam Jones, [no number]
     * Select a record: > 2
     * Select a field (name, surname, number): > number
     * Enter number: > (123) 234 345-456
     * The record updated!
     */
    public void editContact() {
        if (contacts.size() == 0) System.out.println("No records to edit!");
        else {
            showList();
            System.out.print("Select a record: ");
            int index = scanner.nextInt() - 1;
            ContactPerson contactForEdition = contacts.get(index);
            System.out.print("Select a field (name, surname, number): ");
            String field = scanner.next();
            switch (field) {
                case "name":
                    System.out.print("Enter name: ");
                    contactForEdition.setName(scanner.next());
                    break;
                case "surname":
                    System.out.print("Enter surname: ");
                    contactForEdition.setSurname(scanner.next());
                    break;
                case "number":
                    System.out.print("Enter number: ");
                    String removingLineBreak = scanner.nextLine();
                    contactForEdition.setPhoneNamber(scanner.nextLine());
                    break;
                default:
                    System.out.println("Bad parameters!");
                    System.out.print("Input command: ");
            }
            contacts.set(index, contactForEdition);
            System.out.println("The record updated!");
        }
    }

    public void count() {
        System.out.printf("The Phone Book has %d records.%n", contacts.size());
    }


    public static void exit() {
        repeat = false;
    }

    public static void main(String[] args) {
        Main m = new Main();
        while (repeat) m.menu();
    }
}
