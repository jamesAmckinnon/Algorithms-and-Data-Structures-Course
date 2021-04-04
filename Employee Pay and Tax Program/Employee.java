/* 
 * James McKinnon
 *
 * This file contains all of the data and methods that are common to all employees.
 * Including: getGrossPay(), getTax(), getCpp(), getEi(), getNetPay(), calcTax(),
 *            calcCpp(), calcEi(), calcNetPay() and printPayCheque().
 */

public abstract class Employee extends Person {

    protected double grossPay;
    protected double ytdGrossPay;
    protected double tax;
    protected double ytdTax;
    protected double cpp;
    protected double ytdCpp;
    protected double ei;
    protected double ytdEi;
    protected double netPay;
    protected double ytdNetPay;

    
    /**
     * Receives values from the subclasses HourlyEmp and SalaryEmp and also 
     * pushes values to the superclass Person.
     * 
     * @param name
     * @param address
     * @param birthday
     * @param birthmonth
     * @param birthyear 
     */
    public Employee(String name, String address, int birthday, int birthmonth, int birthyear) {
        super(name, address, birthday, birthmonth, birthyear); // only relavent data is sent to person
    }

    public double getGrossPay() {
        return grossPay;
    }

    public double getTax() {
        return tax;
    }

    public double getCpp() {
        return cpp;
    }

    public double getEi() {
        return ei;
    }

    public double getNetPay() {
        return calcNetPay(); //calculates the net pay at time of method call
    }

    /**
     * Finds out which tax bracket the employee is in and calculates the
     * amount of tax they will need to pay. Salary employees will always
     * have a fixed amount, hourly employees will have different amounts
     * based on the number of hours they worked in the month the tax is 
     * calculated for.
     * 
     * @return returns the amount of tax deducted from pay check
     */
    public double calcTax() {
        // Each if statement will account for a different tax bracket.
        // Only one will ever run when the calcTax method is called.
        if ((grossPay * 12) < 14000) { 
            tax = 0;
        } else if ((grossPay * 12) >= 14000 && (grossPay * 12) < 50000) {
            tax = Math.round((grossPay * .15) * 100.00) / 100.00;
            ytdTax += tax;
        } else if ((grossPay * 12) >= 50000 && (grossPay * 12) < 100000) {
            tax = Math.round((grossPay * .21) * 100.00) / 100.00;
            ytdTax += tax;
        } else if ((grossPay * 12) >= 100000 && (grossPay * 12) < 150000) {
            tax = Math.round((grossPay * .26) * 100.00) / 100.00;
            ytdTax += tax;
        } else {
            tax = Math.round((grossPay * .29) * 100.00) / 100.00;
            ytdTax += tax;
        }
        return tax;
    }

    /**
     * Calculates the cpp value for each entry to the system and also keeps
     * track of the year to date Cpp value so that it never goes above $3200.
     * 
     * @return returns the amount of cpp deducted from pay cheque
     */
    public double calcCpp() {
        // adding ytdCpp here confirms there is room to add the current months cpp contribution
        if (ytdCpp + (grossPay * .0545) < 3200) { 
            cpp = Math.round((grossPay * .0545) * 100.0) / 100.0;
            ytdCpp += cpp; 
        // when the cpp is not added as its full amount because it would go over 3200
        // only a portion of cpp is added and returned.
        } else if (ytdCpp + (grossPay * .0545) > 3200 && ytdCpp != 3200) {
            cpp = Math.round((3200 - ytdCpp) * 100.0) / 100.0;
            ytdCpp = 3200;
            
        } else if (ytdCpp == 3200) {
            cpp = 0;
        }
        return cpp;
    }

    /**
     * Calculates the amount of EI that is deducted from each employees paycheck
     * and keeps track of year to date EI deducted so that it doesn't go above $890.
     * 
     * @return returns the amount of cpp deducted from pay cheque
     */
    public double calcEi() {
        // adding ytdEi here confirms there is room to add the current months EI contribution
        if (ytdEi + (grossPay * .0158) < 890) {
            ei = Math.round((grossPay * .0158) * 100.0) / 100.0;
            ytdEi += ei;
        // when the ei is not added as its full amount because it would go over 890
        // only a portion of ei is added and returned.
        } else if (ytdEi + (grossPay * .0158) > 890 && ytdEi != 890) {
            ei = Math.round((890 - ytdEi) * 100.0) / 100.0;
            ytdEi = 890;
        } else if (ytdEi == 890) {
            ei = 0;
        }
        return ei;
    }

    /**
     * Calculates the net pay of an employee by deducting the tax, cpp and ei
     * contributions from grossPay.
     * 
     * @return returns the net pay value
     */
    public double calcNetPay() {
        netPay = grossPay - tax - cpp - ei;
        ytdNetPay += netPay;
        return netPay;
    }

    /**
     * Prints a pay cheque for an employee that includes useful information about
     * their pay, pay rate, hours, deductions, net pay and the year to date values
     * of this information.
     * 
     * @param hours this value is received from the subclass HourlyEmp or SalaryEmp
     * @param payRate this value is received from the subclass HourlyEmp or SalaryEmp
     */
    public void printPayCheque(double hours, double payRate) {
        System.out.printf("===============================================\r\n"
                + "%d \r\n"
                + "%s \r\n"
                + "%s \r\n"
                + "\r\n"
                + "--------------------------------------------\r\n"
                + "   Hours Worked: %.2f      Rate: %.2f\n"
                + "--------------------------------------------\r\n"
                + "%25s %12s\r\n"
                + "%25s %12s\r\n"
                + "   %-13s %8.2f %12.2f\r\n"
                + "   %-13s %8.2f %12.2f\r\n"
                + "   %-13s %8.2f %12.2f\r\n"
                + "   %-13s %8.2f %12.2f\r\n"
                + "\r\n"
                + "   %-13s %8.2f %12.2f\r\n"
                + "%40s%-6.2f\r\n"
                + "===============================================\r\n"
                + "\r\n",
                 idNumber, name, address, hours, payRate,
                "Current", "YTD",
                "-------", "---",
                "Gross Pay:", grossPay, ytdGrossPay,
                "Tax:", tax, ytdTax,
                "CPP:", cpp, ytdCpp,
                "EI:", ei, ytdEi,
                "Net Pay:", getNetPay(), ytdNetPay, "$",
                getNetPay());
    }
}
