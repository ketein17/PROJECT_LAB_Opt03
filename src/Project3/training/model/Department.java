package Project3.training.model;

import java.util.List;

public class Department {
    private String departmentName;
    private List<Employee> listOfEmployee;

    public Department() {
    }

    public Department(String departmentName) {
        this.departmentName = departmentName;
    }

    public Department(String departmentName, List<Employee> listOfEmployee) {
        this.departmentName = departmentName;
        this.listOfEmployee = listOfEmployee;
    }

    public List<Employee> getListOfEmployee() {
        return listOfEmployee;
    }

    public void setListOfEmployee(List<Employee> listOfEmployee) {
        this.listOfEmployee = listOfEmployee;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }


    public void display() {
        System.out.println();
    }
}
