package contacts;

import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactPerson extends Contact implements Serializable {

    public static final Scanner scanner = new Scanner(System.in);
    private static final long serialVersionUID = -6695556078576800841L;
    //transient BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    /*
        User Name Requirements

        The user name consists of alphanumeric characters (a-za-Z0-9), lowercase or uppercase.
        The user name is allowed with a period (.), underscore (_) and hyphen (-). The
        period (.), underscore (_) or hyphen (-) must not be the first or last character.
        The dot (.), underscore (_), or hyphen (-) are not displayed sequentially, for example, java.. regular expression
        The number of characters should be from 3 to 15.
    */
    private static final String NAME_PATTERN =
            "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){1,18}[a-zA-Z0-9]$";


    private static final Pattern namePattern = Pattern.compile(NAME_PATTERN);


    /*
         Which fields should be available in the Person class:
         name;
         surname;
         gender;
         phoneNumber; super class
         dateCreation; super class
         dataEditing; super class
     */


    private String name;
    private String surname;
    private String birthday;
    private String gender;


    ContactPerson() {
    }

    ContactPerson(String name, String surname, String birthday, String gender) {
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.gender = gender;
        this.phoneNumber = getPhoneNumber();
        this.dateCreation = getDateCreation();
        this.dateEditing = getDateEditing();
    }

    @Override
    public ContactPerson createContact() {
        ContactPerson contactPerson = new ContactPerson();
        System.out.print("Enter the name: ");
        String name = scanner.nextLine();
        System.out.print("Enter the surname: ");
        String surname = scanner.nextLine();
        System.out.print("Enter the birth date: ");
        String birthday = scanner.nextLine();
        System.out.print("Enter the gender (M, F):");
        String gender = scanner.nextLine();
        System.out.print("Enter the number: ");
        String number = scanner.nextLine();
        contactPerson.setName(name)
                .setSurname(surname)
                .setBirthday(birthday)
                .setGender(gender)
                .setPhoneNumber(number)
                .setDateCreation()
                .setDateEditing();
        return contactPerson;
    }


    @Override
    public void editContact() throws IOException {
        System.out.print("Select a field (name, surname, birth, gender, number): ");
        String field = scanner.next();
        switch (field) {
            case "name":
                System.out.print("Enter name: ");
                setName(Main.br.readLine());
                break;
            case "surname":
                System.out.print("Enter surname: ");
                setSurname(Main.br.readLine());
                break;
            case "birth":
                System.out.print("Enter the birth date:");
                setBirthday(Main.br.readLine());
                break;
            case "gender":
                System.out.print("Enter the gender (M, F): ");
                setGender(Main.br.readLine());
                break;
            case "number":
                System.out.print("Enter number: ");
                super.setPhoneNumber(Main.br.readLine());
                break;
            default:
                System.out.println("Bad parameters!");
                System.out.print("Input command: ");
        }
        super.setDateEditing();

    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getGender() {
        return gender;
    }

    public ContactPerson setName(String name) {
        //if (checkValidityName(name))
        this.name = name;
        return this;
    }

    public ContactPerson setSurname(String surname) {
        //if (checkValidityName(surname))
        this.surname = surname;
        return this;
    }

    /**
     * @param birthday YYYY-mm-dd
     * @return class object ContactPerson
     */
    public ContactPerson setBirthday(String birthday) {
        if (checkValidityBirthday(birthday))
            this.birthday = birthday;
        else {
            System.out.println("Bad birth date!");
            this.birthday = "[no data]";
        }
        return this;
    }

    public ContactPerson setGender(String gender) {
        if (gender.equals("M") || (gender.equals("F")))
            this.gender = gender;
        else {
            System.out.println("Bad gender!");
            this.gender = "[no data]";
        }
        return this;
    }

    @Override
    public String printName() {
        return name + " " + surname;
    }

    @Override
    public String toString() {
        return String.format("Name: %s%n"
                             + "Surname: %s%n"
                             + "Birth date: %s%n"
                             + "Gender: %s%n"
                             + "Number: %s%n"
                             + "Time created: %s%n"
                             + "Time last edit: %s%n ", name, surname, birthday, gender, phoneNumber,
                printDataTime(dateCreation), printDataTime(dateEditing));
    }

    boolean checkValidityName(final String username) {
        Matcher matcher = namePattern.matcher(username);
        return matcher.matches();
    }

    boolean checkValidityBirthday(String birthday) {
        //not completed
        return false;
    }
}
