package contacts;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contact {
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


    private String name;
    private String surname;
    private String phoneNamber;

    Contact() {
        this.name = "";
        this.surname = "";
        this.phoneNamber = "";
    }

    Contact(String name, String surname, String phoneNamber) {
        this.name = name;
        this.surname = surname;
        this.phoneNamber = phoneNamber;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhoneNamber() {
        return phoneNamber;
    }

    public Contact setName(String name) {
        //if (checkValidityName(name))
            this.name = name;
        return this;
    }

    public Contact setSurname(String surname) {
        //if (checkValidityName(name))
            this.surname = surname;
        return this;
    }

    public Contact setPhoneNamber(String phoneNamber) {
        if (checkValidityPhone(phoneNamber))
            this.phoneNamber = phoneNamber;
        else {
            System.out.println("Wrong number format!");
            this.phoneNamber = "[no number]";
        }
        return this;
    }

    public boolean hasNumber() {
        if (this.phoneNamber.equals("No number") || this.phoneNamber.equals("")) return false;
        else return true;
    }

    @Override
    public String toString() {
        return String.format("%s %s, %s", name, surname, phoneNamber);
    }

    boolean checkValidityName(final String username) {
        Matcher matcher = namePattern.matcher(username);
        return matcher.matches();
    }

    boolean checkValidityPhone(final String phoneNumber) {
        Matcher matcher = phonePattern.matcher(phoneNumber);
        return matcher.matches();
    }
}
