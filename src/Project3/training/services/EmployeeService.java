package Project3.training.services;

import Project3.training.model.Department;
import Project3.training.model.Employee;
import Project3.training.model.HourlyEmployee;
import Project3.training.model.SalariedEmployee;
import Project3.training.utils.DateFormatException;
import Project3.training.utils.EmailFormatException;
import Project3.training.utils.PhoneNumberFormatException;
import Project3.training.utils.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeService {

    public Employee createEmployee(Employee employee,Scanner scanner){
        String ssn, fname, lname, birthDate, phone, email;
        //employee = new SalariedEmployee();
        System.out.println("Enter ssn: ");
        ssn = scanner.nextLine();
        employee.setSsn(ssn);
        System.out.println("Enter first name: ");
        fname = scanner.nextLine();
        employee.setFirstName(fname);
        System.out.println("Enter last name: ");
        lname = scanner.nextLine();
        employee.setLastName(lname);
        do {
            System.out.println("Enter birthday: ");
            birthDate = scanner.nextLine();
            try {
                employee.setBirthDate(birthDate);
            } catch (DateFormatException e) {
                System.err.println("Please enter birthday again! " + e.getMessage());
                continue;
            }
            break;
        } while (true);

        do {
            System.out.println("Enter phone number: ");
            phone = scanner.nextLine();
            try {
                employee.setPhone(phone);
            } catch (PhoneNumberFormatException e) {
                System.err.println("Please enter phone number again! " + e.getMessage());
                continue;
            }
            break;
        } while (true);

        do {
            System.out.println("Enter email: ");
            email = scanner.nextLine();
            try {
                employee.setEmail(email);
            } catch (EmailFormatException e) {
                System.err.println("Please enter email again! " + e.getMessage());
                continue;
            }
            break;
        } while (true);
        return  employee;
    }
    public List<Employee> createSalariedEmployee(Scanner scanner) {
        String loop;
        String ssn, fname, lname, birthDate, phone, email;
        String commissionRate;
        String grossSales;
        String basicSalary;
        double doCR, doGS, doBS;
        SalariedEmployee sEmployee;
        EmployeeService employeeService=new EmployeeService();
        List<Employee> employees = new ArrayList<>();
        do {
            sEmployee = new SalariedEmployee();
            employeeService.createEmployee(sEmployee,scanner);

            do {
                System.out.println("Enter commission rate: ");
                commissionRate = scanner.nextLine();
                try {
                    doCR = Validator.parseDoubleNumber(commissionRate);
                } catch (NumberFormatException e) {
                    continue;
                }
                break;
            } while (true);


            do {
                System.out.println("Enter gross sales: ");
                grossSales = scanner.nextLine();
                try {
                    doGS = Validator.parseDoubleNumber(grossSales);
                } catch (NumberFormatException e) {
                    continue;
                }
                break;
            } while (true);


            do {
                System.out.println("Enter basic salary: ");
                basicSalary = scanner.nextLine();
                try {
                    doBS = Validator.parseDoubleNumber(basicSalary);
                } catch (NumberFormatException e) {
                    continue;
                }
                break;
            } while (true);


            sEmployee.setCommissionRate(doCR);
            sEmployee.setGrossSales(doGS);
            sEmployee.setBasicSalary(doBS);
            employees.add(sEmployee);
            System.out.println("Do you want to continue to input Salaried Employee(Y/N)?:");
            loop = scanner.nextLine();

        } while (loop.charAt(0) == 'Y' | loop.charAt(0) == 'y');
        return employees;
    }


    public List<Employee> createHourlyEmployee(Scanner scanner) {
        String loop;
        //String ssn, fname, lname, birthDate, phone, email;
        String wage, workingHours;
        double doW, doWH;
        HourlyEmployee hEmployee;
        List<Employee> employees = new ArrayList<>();
        EmployeeService employeeService=new EmployeeService();
        do {
            hEmployee = new HourlyEmployee();
            employeeService.createEmployee(hEmployee,scanner);
            do {
                System.out.println("Enter wage: ");
                wage = scanner.nextLine();
                try {
                    hEmployee.setWage(Validator.parseDoubleNumber(wage));

                } catch (NumberFormatException e) {
                    System.out.println();
                    continue;
                }
                break;
            } while (true);


            do {
                System.out.println("Enter working hours: ");
                workingHours = scanner.nextLine();
                try {
                    hEmployee.setWorkingHours(Validator.parseDoubleNumber(workingHours));
                } catch (NumberFormatException e) {
                    continue;
                }
                break;
            } while (true);

            employees.add(hEmployee);
            System.out.println("Do you want to continue to input Hourly Employee(Y/N)?:");
            loop = scanner.nextLine();
        } while (loop.charAt(0) == 'Y' | loop.charAt(0) == 'y');
        return employees;
    }

    public void displayAllEmployee(List<Employee> employees) {
        if (employees.isEmpty()) {
            System.err.println("The company doesn't have employee");
        }
        for (Employee employee : employees) {
            if (employee instanceof SalariedEmployee) {
                System.out.print("Salaried Employee:");
                employee.display();
            }
            if (employee instanceof HourlyEmployee) {
                System.out.print("Hourly Employee ");
                employee.display();
            }
        }
    }

    public static void displaySalE(List<Employee> employees) {
        if (employees == null) {
            return;
        } else {
            System.out.println("-----------------Salaried Employee-----------------");
            for (Employee e : employees) {
                if (e instanceof SalariedEmployee) {
                    e.display();
                }
            }
        }
    }

    public static void displayHourE(List<Employee> employees) {
        if (employees == null) {
            return;
        } else {
            System.out.println("-----------------Hourly Employee-----------------");
            for (Employee e : employees) {
                if (e instanceof HourlyEmployee) {
                    e.display();
                }
            }
        }
    }

    public void searchEmployeeInDepartment(List<Department> departmentList) {
        String employeeName;
        List<Employee> employeeList = new ArrayList<>();
        DepartmentService departmentService = new DepartmentService();
        Scanner scanner = new Scanner(System.in);
        String departmentName;
        System.out.println("Enter department's name: ");
        departmentName = scanner.nextLine();
        Department department = departmentService.departmentSearch(departmentList, departmentName);
        if (department.getDepartmentName() == null) {
            System.out.println("There is no department like that");
        } else {
            if (department.getListOfEmployee() == null) {
                System.out.println("There is no employee in this department");
            } else {
                departmentService.display(department);
                System.out.println("Enter employee's name: ");
                employeeName = scanner.nextLine();
                for (Employee employee1 : department.getListOfEmployee()) {
                    if (employeeName.equalsIgnoreCase((employee1.getFirstName() + " " + employee1.getLastName()))) {
                        employeeList.add(employee1);
                    } else if (employeeName.equalsIgnoreCase(employee1.getFirstName())) {
                        employeeList.add(employee1);
                    } else if (employeeName.equalsIgnoreCase(employee1.getLastName())) {
                        employeeList.add(employee1);
                    } else {
                        System.out.println("No data");
                    }
                }
                EmployeeService.displaySalE(employeeList);
                EmployeeService.displayHourE(employeeList);
            }
        }
    }

    public void displayAllEmployeeInADepartment(List<Department> departmentList) {
        DepartmentService departmentService = new DepartmentService();
        if (departmentList == null) {
            System.out.println("There is no department");
        } else {
            for (Department department : departmentList) {
                departmentService.display(department);
            }
        }
    }

    public void searchEmployee(List<Department> departmentList, List<Employee> nonEmployee) {
        Department department1 = new Department("Non Department Employee", nonEmployee);
        departmentList.add(0, department1);
        String employeeName;

        Scanner scanner = new Scanner(System.in);
        if (departmentList.size() == 1 && nonEmployee.isEmpty()) {
            System.err.println("There is no department!");
        } else {
            System.out.println("Enter employee's name: ");
            employeeName = scanner.nextLine();
            for (Department department : departmentList) {
                List<Employee> employeeList = new ArrayList<>();

                if (department.getListOfEmployee() == null) {
                    System.out.print("");
                } else {
                    for (Employee employee1 : department.getListOfEmployee()) {
                        if (employeeName.equalsIgnoreCase((employee1.getFirstName() + " " + employee1.getLastName()))

                        ) {
                            employeeList.add(employee1);
                        } else if (employeeName.equalsIgnoreCase(employee1.getFirstName())) {
                            employeeList.add(employee1);
                        } else if (employeeName.equalsIgnoreCase(employee1.getLastName())) {
                            employeeList.add(employee1);
                        } else {
                            System.out.println("No data");
                        }
                    }
                    if (employeeList.isEmpty()) {
                        System.out.println("");
                    } else {
                        System.out.println("-------------" + department.getDepartmentName() + "-------------");
                        EmployeeService.displaySalE(employeeList);
                        EmployeeService.displayHourE(employeeList);
                    }
                }

                //}
            }
        }
        departmentList.remove(0);
    }


    public void displayNonDepEmployee(List<Employee> employees) {
        int i = 0;
        if (employees == null) {
            System.out.println("No data!");
        } else {
            for (Employee employee : employees) {
                i += 1;
                System.out.print(i + ": ");
                employee.display();
            }
        }
    }
}

