/* 
 * James McKinnon
 * 
 * This file contains all of the data and methods that are common to all hourly employees
 * Including: payRate, hours, getHoursWorked(), setHoursWorked(), calcGrossPay(), 
 *            calcPay(), resetForMonth(), resetForYear() and printPayCheque().
 *            
 */

public class HourlyEmp extends Employee {

    private double payRate;
    protected double hours;

    /**
     * Receives values when the object is constructed, sends relevant information
     * to the Employee superclass and sets the payRate data field.
     * 
     * @param name
     * @param address
     * @param payRate
     * @param birthday
     * @param birthmonth
     * @param birthyear 
     */
    public HourlyEmp(String name, String address, double payRate, int birthday, int birthmonth, int birthyear) {
        super(name, address, birthday, birthmonth, birthyear);
        this.payRate = payRate;
    }

    /**
     * Default constructor so that default values are displayed if no parameters
     * are given when the HourlyEmp object is created.
     */
    public HourlyEmp() {
        this("Hourly Employee", "Earth, \nMilky Way Galaxy", 500, 12, 10, 1995);
    }

    public double getHoursWorked() {
        return hours;
    }

    public void setHoursWorked(double hours) {
        this.hours = hours;
    }

    /**
     * Calculates the gross pay that the worker has earned for the work they did
     * within a pay period and adds to the year to date amount for gross pay.
     * 
     * @return 
     */
    public double calcGrossPay() {
        // Normal pay rate
        
        if (hours <= 140) {
            grossPay = Math.round(((hours * payRate) * 100.0)) / 100.0;
            ytdGrossPay += grossPay;
        } else if (hours >= 147) { 
            // normal pay for 140 hours, 7 hr paid at time and a half, double time pay after that
            grossPay += Math.round((((hours - 147) * 2 * payRate) * 100.0)) / 100.0;
            grossPay += Math.round(((7 * 1.5 * payRate) * 100.0)) / 100.0;
            grossPay += Math.round(((140 * payRate) * 100.0)) / 100.0;
            ytdGrossPay += grossPay;
        } else if (hours > 140) { // runs if the hours are between 140 and 147
            grossPay += Math.round(((hours - 140) * 1.5 * payRate) * 100.0) / 100.0;
            grossPay += Math.round(((140 * payRate) * 100.0)) / 100.0;
            ytdGrossPay += grossPay;
        }
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
        calcNetPay();
    }

    /**
     * This sends information to the employee class that it would
     * otherwise not have access to.
     */
    public void printPayCheque() {
        printPayCheque(hours, payRate);
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
        ytdTax = 0;
        cpp = 0;
        ytdCpp = 0;
        ei = 0;
        ytdEi = 0;
        netPay = 0;
        ytdNetPay = 0;
    }
}
