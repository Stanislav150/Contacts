package contacts;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactOrganization extends Contact {

    public static final Scanner scanner = new Scanner(System.in);

    /*
        Organization Name Requirements

        The organization name consists of alphanumeric characters (a-za-Z0-9), lowercase or uppercase.
        The organization name is allowed with a period (.), underscore (_) and hyphen (-). The
        period (.), underscore (_) or hyphen (-) must not be the first or last character.
        The dot (.), underscore (_), or hyphen (-) are not displayed sequentially, for example, java.. regular expression
        The number of characters should be from 3 to 15.
    */
    private static final String ORGANIZATION_NAME_PATTERN =
            "^([a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){1,18}[a-zA-Z0-9])+$";

    /**
     * An approximate stub pattern
     */
    private static final String ADDRESS_PATTERN =
            "^([a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){1,18}[a-zA-Z0-9])+$";


    private static final Pattern organizationNamePattern = Pattern.compile(ORGANIZATION_NAME_PATTERN);
    private static final Pattern addressPattern = Pattern.compile(ADDRESS_PATTERN);

    /*
         Какие поля должны быть доступны в классе Organization:
         organizationName;
         address;
         phoneNumber; класс super
         dateCreation; класс super
         dateEditing; класс super
     */


    private String organizationName;
    private String address;

    ContactOrganization() {
    }

    ContactOrganization(String organizationName, String address) {
        this.organizationName = organizationName;
        this.address = address;
        this.phoneNumber = getPhoneNumber();
        this.dateCreation = getDateCreation();
        this.dateEditing = getDateEditing();
    }

    public ContactOrganization createContact() {
        ContactOrganization contactOrganization = new ContactOrganization();
        System.out.print("Enter the organization name:");
        String organizationName = scanner.nextLine();
        System.out.print("Enter the address: ");
        String address = scanner.nextLine();
        System.out.print("Enter the number: ");
        String number = scanner.nextLine();
        contactOrganization.setOrganizationName(organizationName)
                .setAddress(address)
                .setPhoneNumber(number)
                .setDateCreation()
                .setDateEditing()
                .setPersonFlag(false);
        return contactOrganization;
    }

    public ContactOrganization editContact(ContactOrganization contactForEdition) {
        System.out.print("Select a field (organizationName, address, number): ");
        String field = scanner.next();
        switch (field) {
            case "organizationName":
                System.out.print("Enter the organization name: ");
                String removingLineBreak = scanner.nextLine();
                contactForEdition.setOrganizationName(scanner.nextLine());
                break;
            case "address":
                System.out.print("Enter the address: ");
                removingLineBreak = scanner.nextLine();
                contactForEdition.setAddress(scanner.nextLine());
                break;
            case "number":
                System.out.print("Enter the number: ");
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

    public String getOrganizationName() {
        return organizationName;
    }

    public String getAddress() {
        return address;
    }

    public ContactOrganization setOrganizationName(String organizationName) {
        //if (checkValidityOrganizationName(organizationName))
            this.organizationName = organizationName;
        return this;
    }

    public ContactOrganization setAddress(String address) {
        //if (checkValidityAddress(address))
            this.address = address;
        return this;
    }

    public String printNameOrganization() {
        return String.format(organizationName);
    }

    public String toString() {
        return String.format("Organization name: %s%n"
                          + "Address: %s%n"
                          + "Number: %s%n"
                          + "Time created: %s%n"
                          + "Time last edit: %s%n ", organizationName, address, phoneNumber,
                printDataTime(dateCreation), printDataTime(dateEditing));
    }

    public String printDataTime(LocalDateTime localDateTime) {
        int hour = localDateTime.getHour();
        int minute = localDateTime.getMinute();
        return LocalDateTime.of(LocalDate.now(), LocalTime.of(hour, minute)).toString();

    }

    boolean checkValidityOrganizationName(final String organizationName) {
        Matcher matcher = organizationNamePattern.matcher(organizationName);
        return matcher.matches();
    }

    boolean checkValidityAddress(String address) {
        Matcher matcher = addressPattern.matcher(address);
        return matcher.matches();
    }
}