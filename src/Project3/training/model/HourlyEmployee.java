package Project3.training.model;

public class HourlyEmployee extends Employee implements Payable{
    private double wage;
    private double workingHours;

    public HourlyEmployee(){};

    public double getWage() {
        return wage;
    }

    public void setWage(double wage) {
        this.wage = wage;
    }

    public double getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(double workingHours) {
        this.workingHours = workingHours;
    }

    @Override
    public void display() {
        System.out.println(firstName+" "+lastName+"\t"+birthDate+"\t"+phone+"\t"+ email);
    }

    @Override
    public double getPaymentAmount() {
        return wage*workingHours;
    }
}
