package contacts;

import java.io.*;
import java.util.*;


public class Main implements Serializable {


    transient static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final long serialVersionUID = -8280650876184340946L;
    public static boolean repeat = true;
    private static String filename;
    static List<Contact> contacts = new ArrayList<>();
    List<Contact> searchResults = new ArrayList<>();
    Map<Integer, Integer> matchingMap = new LinkedHashMap<>();
    boolean isPerson = true;
    ContactPerson contactPerson = new ContactPerson();
    ContactOrganization contactOrganization = new ContactOrganization();


    private static boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void menu() throws IOException {
        System.out.print("Enter action (add, list, search, count, exit): ");
        String command = br.readLine();
        if (command.equals("add")) {
            System.out.print("Enter the type (person, organization): ");
            String type = br.readLine();
            if (type.equals("person")) isPerson = true;
            else if (type.equals("organization")) isPerson = false;
        }
        switch (command) {
            case "add":
                addContact();
                break;
            case "list":
                showList();
                break;
            case "search":
                search();
                break;
            case "count":
                count();
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
        if (isPerson) contacts.add(contactPerson.createContact());
        else contacts.add(contactOrganization.createContact());
        //Serializing
        try (FileOutputStream outputStream = new FileOutputStream(filename);
             ObjectOutputStream oos = new ObjectOutputStream(outputStream)) {
            oos.writeObject(contacts);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("The record added.\n");
    }

    public void showList() throws IOException {
        int count = 1;
        for (Contact c : contacts) {
            System.out.printf("%d. %s%n", count, c.printName());
            count++;
        }
        System.out.println();
        System.out.print("[list] Enter action ([number], back): ");
        String command = br.readLine();
        if (isDigit(command)) {
            int record = Integer.parseInt(command) - 1;
            System.out.println(contacts.get(record).toString());
            System.out.print("[record] Enter action (edit, delete, menu): ");
            String whatToDo = br.readLine();
            if (whatToDo.equals("edit")) edit(record);
            else if (whatToDo.equals("delete")) removeContact(record);
            else if (whatToDo.equals("menu")) menu();
        } else if (command.equals("back")) showList();
    }


    public void search() throws IOException {
        searchResults.clear();
        matchingMap.clear();
        System.out.print("Enter search query: ");
        String str = br.readLine();
        int count = 0;
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).toString().toLowerCase(Locale.ROOT)
                    .contains(str.toLowerCase(Locale.ROOT))) {
                searchResults.add(contacts.get(i));
                //A table of matches. key - number in searchResult, value - number in contacts
                matchingMap.put(count, i);
                count++;
            }
        }
        System.out.printf("Found %d results: %n", searchResults.size());
        count = 0;
        for (Contact contact : searchResults) {
            count++;
            System.out.printf("%d. %s%n", count, contact.printName());
        }

        System.out.print("\n[search] Enter action ([number], back, again): ");
        String command = br.readLine();
        if (isDigit(command)) {
            int record = Integer.parseInt(command) - 1;
            System.out.println(searchResults.get(record).toString());
            System.out.print("[record] Enter action (edit, delete, menu): ");
            String whatToDo = br.readLine();
            // Here we get the number in the list of matches, and edit it in the main list
            if (whatToDo.equals("edit")) edit(matchingMap.get(record));
            else if (whatToDo.equals("delete")) removeContact(matchingMap.get(record));
            else if (whatToDo.equals("menu")) menu();
        } else if (command.equals("back")) menu();
        else if (command.equals("again")) search();
        else System.out.println("Bad parameters!");
    }


    public void removeContact(int index) {
        if (contacts.size() == 0) System.out.println("No records to remove!");
        else {
            contacts.remove(index);
            try (FileOutputStream outputStream = new FileOutputStream(filename);
                 ObjectOutputStream oos = new ObjectOutputStream(outputStream)) {
                oos.writeObject(contacts);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("The record removed!");
            System.out.println();
        }
    }

    public void edit(int index) throws IOException {
        if (contacts.size() == 0) System.out.println("No records to edit!");
        else {
            contacts.get(index).editContact();
            contacts.set(index, contacts.get(index));
            try (FileOutputStream outputStream = new FileOutputStream(filename);
                 ObjectOutputStream oos = new ObjectOutputStream(outputStream)) {
                oos.writeObject(contacts);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Saved\n");
            System.out.print(contacts.get(index).toString());
            System.out.println();
        }
    }

    public void count() {
        System.out.printf("The Phone Book has %d records.%n", contacts.size());
        System.out.println();
    }

    public static void exit() {
        repeat = false;
    }

    private void writeObject(ObjectOutputStream oos) throws Exception {
        oos.defaultWriteObject();
    }

    private void readObject(ObjectInputStream ois) throws Exception {
        ois.defaultReadObject();
    }

    public boolean isFileEmpty(File file) {
        return file.length() != 0;
    }

    public static void main(String[] args) throws IOException {
        filename = "Contacts\\task\\src\\contacts\\phonebook.db";
        Main m = new Main();
        File file = new File(filename);

        if (m.isFileEmpty(file)) {
            //Deserialization
            try (FileInputStream fileInputStream = new FileInputStream(filename);
                 ObjectInputStream ois = new ObjectInputStream(fileInputStream)) {
                contacts = (List<Contact>) ois.readObject();
                System.out.println("open phonebook.db");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        while (repeat) m.menu();
    }
}
