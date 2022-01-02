package contacts;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Contact implements Serializable{
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

    private static final Pattern phonePattern = Pattern.compile(PHONE_PATTERN);
    private static final long serialVersionUID = 759894381010013822L;


    String phoneNumber;
    LocalDateTime dateCreation;
    LocalDateTime dateEditing;


    Contact() {}

    Contact(String phoneNumber, LocalDateTime dateCreation, LocalDateTime dateEditing) {
        this.phoneNumber = phoneNumber;
        this.dateCreation = dateCreation;
        this.dateEditing = dateEditing;

    }

    public abstract Contact createContact();

    public abstract void editContact() throws IOException;

    public abstract String printName();

    public abstract String toString();

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public LocalDateTime getDateEditing() {
        return dateEditing;
    }



    public Contact setPhoneNumber(String phoneNumber) {
        if (checkValidityPhone(phoneNumber))
            this.phoneNumber = phoneNumber;
        else {
            System.out.println("Wrong number format!");
            this.phoneNumber = "[no number]";
        }
        return this;
    }

    public Contact setDateCreation() {
        this.dateCreation = LocalDateTime.now();
        return this;
    }

    public Contact setDateEditing() {
        this.dateEditing = LocalDateTime.now();
        return this;
    }


    public String printDataTime(LocalDateTime localDateTime) {
        int hour = localDateTime.getHour();
        int minute = localDateTime.getMinute();
        return LocalDateTime.of(LocalDate.now(), LocalTime.of(hour, minute)).toString();

    }
    public boolean hasNumber() {
        return (this.phoneNumber.equals("No number") || this.phoneNumber.equals(""));
    }

    boolean checkValidityPhone(final String phoneNumber) {
        Matcher matcher = phonePattern.matcher(phoneNumber);
        return matcher.matches();
    }
}
