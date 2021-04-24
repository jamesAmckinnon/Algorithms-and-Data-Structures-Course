/* 
 * James McKinnon
 * 
 * This file contains all of the data and methods that are common to all people.
 *
 */

public abstract class Person {

    protected int idNumber;
    private static int lastIdNumber;
    protected String name;
    protected String address;
    protected int birthday;
    protected int birthmonth;
    protected int birthyear;

    /**
     * Constructor for the person class. Data that is common to all people is
     * passed to this class.
     *
     * @param name
     * @param address
     * @param birthday
     * @param birthmonth
     * @param birthyear
     */
    public Person(String name, String address, int birthday, int birthmonth, int birthyear) {
        this.name = name;
        this.address = address;
        this.birthday = birthday;
        this.birthmonth = birthmonth;
        this.birthyear = birthyear;

        idNumber = lastIdNumber + 3; // new id numbers will never be the same
        lastIdNumber = idNumber;
    }// Person constructor

    public int getIdNumber() {
        return idNumber;
    }// getIdNumber

    //prints information about the person
    public String toString() {
        return "ID: " + this.idNumber + "\n" + "Name: " + this.name + "\n"
                + "Birthday: " + this.birthmonth + "/" + this.birthday + "/" + this.birthyear;
    }// toString
}// Person
