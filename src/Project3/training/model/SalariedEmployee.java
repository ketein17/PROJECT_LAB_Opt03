package Project3.training.model;


public class SalariedEmployee extends Employee implements Payable{
    private double commissionRate;
    private double grossSales;
    private double basicSalary;

    public SalariedEmployee(){};

    public double getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(double commissionRate) {
        this.commissionRate = commissionRate;
    }

    public double getGrossSales() {
        return grossSales;
    }

    public void setGrossSales(double grossSales) {
        this.grossSales = grossSales;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }

    @Override
    public void display() {
        System.out.println(firstName+" "+lastName+"\t"+birthDate+"\t"+phone+"\t"+ email);
    }

    @Override
    public double getPaymentAmount() {
        return basicSalary*grossSales+commissionRate;
    }

}


