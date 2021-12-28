package contacts;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactPerson extends Contact {
    public static final Scanner scanner = new Scanner(System.in);
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private DateTimeFormatter dateFormatter;
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


    /*
        Requirements for the user's phone.

       •	 A phone number can contain one group
       •	If it is divided into several groups, then there may be a different number of them.
       •	There may be spaces or dashes between groups, or both. For example +0 (123) 456-789-Abcd
       •	There may or may not be a plus sign in front of the first group.
       •	The first or second group can be enclosed in parentheses (123) or +0 (123).
            No more than one group is enclosed in brackets. Brackets are not required, they may not be.
       •	The group can contain numbers, uppercase and lowercase English letters.
            The group must consist of at least 2 characters.
            But the first group can consist of only one character.

    */
    private static final String PHONE_PATTERN = "^(\\+?[a-zA-Z0-9]*[ -]?\\(?[a-zA-z0-9]{2,}\\)?)" +
                                                "([ -]([a-zA-Z0-9]{2,}))*$" +
                                                "|^(\\+?([a-zA-Z0-9]{1,}))$";

    private static final Pattern namePattern = Pattern.compile(NAME_PATTERN);
    private static final Pattern phonePattern = Pattern.compile(PHONE_PATTERN);

    /*
         Какие поля должны быть доступны в классе Person:
         name;
         surname;
         gender;
         phoneNumber; класс super
         dateCreation; класс super
         dateEditing; класс super
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
                .setDateEditing()
                .setPersonFlag(true);
        return contactPerson;
    }

    public ContactPerson editContact(ContactPerson contactForEdition) {
        System.out.print("Select a field (name, surname, birth, gender, number): ");
        String field = scanner.nextLine();
        switch (field) {
            case "name":
                System.out.print("Enter name: ");
                contactForEdition.setName(scanner.nextLine());
                break;
            case "surname":
                System.out.print("Enter surname: ");
                contactForEdition.setSurname(scanner.nextLine());
                break;
            case "birth":
                System.out.print("Enter the birth date:");
                String removingLineBreak = scanner.nextLine();
                contactForEdition.setBirthday(scanner.nextLine());
                break;
            case "gender":
                System.out.print("Enter the gender (M, F): ");
                removingLineBreak = scanner.nextLine();
                contactForEdition.setGender(scanner.nextLine());
                break;
            case "number":
                System.out.print("Enter number: ");
                removingLineBreak = scanner.nextLine();
                contactForEdition.setPhoneNumber(scanner.nextLine());
                break;
            default:
                System.out.println("Bad parameters!");
                System.out.print("Input command: ");
        }
        contactForEdition.setDateEditing();

        return contactForEdition;

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

    public String printName() {
        return String.format(name + " " + surname);
    }
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
//        try {
//            this.dateFormatter.parse(birthday);
//        } catch (DateTimeParseException e) {
//            return false;
//        }
        return false;
    }


    boolean checkValidityPhone(final String phoneNumber) {
        Matcher matcher = phonePattern.matcher(phoneNumber);
        return matcher.matches();
    }
}
