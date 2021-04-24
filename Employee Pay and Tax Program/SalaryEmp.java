/* 
 * James McKinnon
 *
 * This file contains all of the data and methods that are common to all salary employees
 * Including: salary, hours, getHoursWorked(), calcGrossPay(), 
 *            calcPay(), resetForMonth(), resetForYear() and printPayCheque().
 */

public class SalaryEmp extends Employee {

    protected double salary;
    protected double hours = 189;

    /**
     * Receives values when the object is constructed, sends relevant information
     * to the Employee superclass and sets the salary data field.
     * 
     * @param name
     * @param address
     * @param salary
     * @param birthday
     * @param birthmonth
     * @param birthyear 
     */
    public SalaryEmp(String name, String address, double salary, int birthday, int birthmonth, int birthyear) {
        super(name, address, birthday, birthmonth, birthyear);
        this.salary = salary;
    }

    /**
     * Default constructor so that default values are displayed if no parameters
     * are given when the HourlyEmp object is created.
     */
    public SalaryEmp() {
        this("James", "Earth, Milky Way Galaxy", 500, 12, 10, 1995);
    }

    public double getHoursWorked() {
        return hours;
    }

    
    /**
     * Calculates the gross pay that the worker has earned for the work they did
     * within a pay period and adds to the year to date amount for gross pay.
     * 
     * @return 
     */
    public double calcGrossPay() {
        grossPay = salary / 12;
        ytdGrossPay += grossPay;
        return grossPay;
    }

    /**
     * calls all the methods that need to run in order to calculate the 
     * values on the pay cheque.
     */
    public void calcPay() {
        calcGrossPay();
        calcTax();
        calcCpp();
        calcEi();
    }

    /**
     * Sends information to the employee class that it would
     * otherwise not have access to.
     */
    public void printPayCheque() {
        printPayCheque(hours, salary);
    }

    public void resetForMonth() {
        grossPay = 0;
        tax = 0;
        cpp = 0;
        ei = 0;
        netPay = 0;
    }

    public void resetForYear() {
        grossPay = 0;
        ytdGrossPay = 0;
        tax = 0;
        cpp = 0;
        ytdCpp = 0;
        ei = 0;
        ytdEi = 0;
        netPay = 0;
        ytdNetPay = 0;
    }
}
