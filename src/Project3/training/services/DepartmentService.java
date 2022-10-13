package Project3.training.services;

import Project3.training.model.Department;
import Project3.training.model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DepartmentService {

    public Department createDep(Scanner scanner) {
        Department department = new Department(scanner.nextLine());
        return department;
    }

    public static boolean checkExistDepartment(List<Department> departmentList, Department department) {
        String departmentName = department.getDepartmentName();
        boolean check = true;
        for (Department department1 : departmentList) {
            if (departmentName.equalsIgnoreCase(department1.getDepartmentName())) {
                System.out.println("Already exist");
                check = false;
            }
        }
        return check;
    }

    public Department createDep(List<Employee> employees, Scanner scanner) {
        Department dep = new Department();
        dep.setDepartmentName(scanner.nextLine());
        dep.setListOfEmployee(employees);
        return dep;
    }


    public void display(Department dep) {
        System.out.println(dep.getDepartmentName());
        EmployeeService.displaySalE(dep.getListOfEmployee());
        EmployeeService.displayHourE(dep.getListOfEmployee());
    }

    public Department addSalariedEmployee(Department department, Scanner scanner) {
        List<Employee> employees;
        EmployeeService employeeService = new EmployeeService();
        employees = department.getListOfEmployee();
        employees.addAll(employeeService.createSalariedEmployee(scanner));
        department.setListOfEmployee(employees);
        return department;
    }

    public void addEmployee(Department department,Employee employee ) {
        department.getListOfEmployee().add(employee);
    }

    public void displayListDepartment(List<Department> departments) {
        System.out.println("--------------List of Department--------------");
        for (Department department : departments) {
            System.out.print(department.getDepartmentName());
            if (department.getListOfEmployee() == null) {
                System.out.println(": 0 employee");
            } else {
                System.out.println(": " + department.getListOfEmployee().size() + " employee");
            }
        }
    }

    public Department departmentSearch(List<Department> departmentList, String departmentName) {
        Department department1 = new Department();
        for (Department department : departmentList) {
            if (departmentName.equals(department.getDepartmentName())) {
                department1 = department;
            }
        }
        return department1;
    }

    public Department addNonListDepartmentEmployee(Department department, List<Employee> employees) {
        if (employees.isEmpty()) {
            System.err.println("There is no non-department employee");
        } else {
            if (department.getListOfEmployee() == null) {
                department.setListOfEmployee(employees);
            } else {
                department.getListOfEmployee().addAll(employees);
            }
        }
        return department;
    }

    public Department addNonDepartmentEmployee(Department department, List<Employee> employees, int serial) {
        if (employees.isEmpty()) {
            System.err.println("There is no non-department employee");
        } else {
            if (department.getListOfEmployee() == null) {
                List<Employee> employeeList = new ArrayList<>();
                employeeList.add(employees.get(serial - 1));
                department.setListOfEmployee(employeeList);
            } else {
                if(serial-1>employees.size()){
                    System.out.println("No data");
                }else {
                    department.getListOfEmployee().add(employees.get(serial - 1));
                }
            }
        }
        return department;
    }
}


