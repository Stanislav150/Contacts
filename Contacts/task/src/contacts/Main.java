package contacts;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static final Scanner scanner = new Scanner(System.in);
    public static boolean repeat = true;
    List<Contact> contacts = new ArrayList<>();
    Contact contact = new Contact();
    ContactPerson contactPerson = new ContactPerson();
    ContactOrganization contactOrganization = new ContactOrganization();


    public void menu() {
        System.out.print("Enter action (add, remove, edit, count, info, exit): ");
        String command = scanner.next();
        if (command.equals("add")) {
            System.out.print("Enter the type (person, organization): ");
            String type = scanner.next();
            if (type.equals("person")) contact.isPerson = true;
            else if (type.equals("organization")) contact.isPerson = false;
        }

        switch (command) {
            case "add": addContact();
                break;
            case "remove": removeContact();
                break;
            case "edit": edit();
                break;
            case "count": count();
                break;
            case "info": info();
                break;
            case "exit": exit();
                break;
            default:
                System.out.println("Bad parameters!");
                System.out.print("Input command: ");

        }

    }

    public void addContact() {
        if (contact.isPerson) contacts.add(contactPerson.createContact());
        else contacts.add(contactOrganization.createContact());
        System.out.println("The record added.\n");
    }

    /**
     * Очень интересное обращение к элементам списка. В нём хранятся два разных по структуре типа
     * элементов, обращаемся к ним, приводя их тип к нужному значению
     */
    public void info() {
        int count = 1;
        for (Contact c : contacts) {
            if (c.isPerson) System.out.printf("%d. %s%n", count, ((ContactPerson) c).printName());
            else System.out.printf("%d. %s%n", count, ((ContactOrganization) c).printNameOrganization());
            count++;
        }
        System.out.println("Enter index to show info: ");
        int record = scanner.nextInt() - 1;
        System.out.println(contacts.get(record).toString());
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
            info();
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
    public void edit() {
        if (contacts.size() == 0) System.out.println("No records to edit!");
        else {
            int count = 1;
            for (Contact c : contacts) {
                if (c.isPerson) System.out.printf("%d. %s%n", count, ((ContactPerson) c).printName());
                else System.out.printf("%d. %s%n", count, ((ContactOrganization) c).printNameOrganization());
                count++;
            }
            System.out.print("Select a record: ");
            int index = scanner.nextInt() - 1;
            Contact contactForEdition;
            if (contacts.get(index).isPerson) {
                contactForEdition = contactPerson
                        .editContact((ContactPerson) contacts.get(index));
            }
            else {
                contactForEdition = contactOrganization
                        .editContact((ContactOrganization) contacts.get(index));

            }
            contacts.set(index, contactForEdition);
            System.out.println("The record updated!\n");
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
