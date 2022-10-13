package Project3.training.main;

import Project3.training.model.Department;
import Project3.training.model.Employee;
import Project3.training.services.DepartmentService;
import Project3.training.services.EmployeeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Management {
    private static List<Employee> listAllEmployee=new ArrayList<>();
    private static final List<Employee> listNewEmployees = new ArrayList<>(); //list all of em
    private static Department department;

//    private static List<Department> departments ;

    public static void main(String[] args) {
        String choice;
        String departmentName;
        List<Department> departments = new ArrayList<>();
        DepartmentService departmentService = new DepartmentService();
        EmployeeService employeeService = new EmployeeService();
        Scanner scanner;
        scanner = new Scanner(System.in);
        do {
            System.out.println("-----------------------------MENU-----------------------------");
            System.out.println("""
                    1.Create new Employee
                    2.Display non-department employee
                    3.Create new Department
                    4.Display list of Department
                    5.Add Employee to a Department
                    6.Display member of Department
                    7.Search Employee in a specific department
                    8.Search Employee
                    9.Display member of all Department
                    10.Exit""");
            System.out.println("Select: ");
            choice = scanner.nextLine();
            switch (choice) {
                case "1" -> {
                    String anotherChoice;
                    /*if (listNewEmployees != null) {
                        listNewEmployees.clear();
                    }*/
                    System.out.println("""
                            1.Create Salaried Employee
                            2.Create Hourly Employee
                            3.Exit""");
                    anotherChoice = scanner.nextLine();
                    switch (anotherChoice) {
                        case "1" -> {
                            listNewEmployees.addAll(employeeService.createSalariedEmployee(scanner));
                            System.out.println("Input done");
                            break;
                        }
                        case "2" -> {
                            listNewEmployees.addAll(employeeService.createHourlyEmployee(scanner));
                            System.out.println("Input done");
                            break;
                        }
                        default -> {
                            anotherChoice = "3";
                            break;
                        }
                    }
                    listAllEmployee.addAll(listNewEmployees);
                    break;
                }

                case "2" -> {
                    employeeService.displayNonDepEmployee(listNewEmployees);
                    break;
                }
                case "3" -> {
                    System.out.println("Enter name of department: ");
                    department = departmentService.createDep(scanner);
                    if (!DepartmentService.checkExistDepartment(departments, department)) {
                        break;
                    } else {
                        System.out.println("Create done");
                        departments.add(department);
                    }
                    break;
                }
                case "4" -> {
                    if (departments.isEmpty()) {
                        System.out.println("There is no department.");
                    } else {
                        departmentService.displayListDepartment(departments);
                    }
                    break;
                }
                case "5" -> {

                    if (departments.isEmpty()) {
                        System.out.println("There is no department.Please create some department");
                    } else {
                        System.out.println("Enter the name of department");
                        departmentName = scanner.nextLine();
                        Department department1;
                        department1 = departmentService.departmentSearch(departments, departmentName);
                        if (department1.getDepartmentName() == null) {
                            System.out.println("The company doesn't have this department");
                        } else {
                            System.out.println("1.Add a available list of employee\n2.Add new employee\n3.Exit");
                            System.out.println("Select: ");
                            String anotherChoice1 = scanner.nextLine();
                            switch (anotherChoice1) {
                                case "1": {
                                    System.out.println("1.Add list of employees\n2.Add an employee\n3.Exit\n");
                                    System.out.println("Select: ");
                                    String anotherChoice2 = scanner.nextLine();
                                    switch (anotherChoice2) {
                                        case "1": {
                                            departmentService.addNonListDepartmentEmployee(department1, listNewEmployees);
                                            listNewEmployees.clear();
                                            break;
                                        }
                                        case "2": {
                                            System.out.println("Enter the employee's serial number: ");
                                            int serial = Integer.parseInt(scanner.nextLine());
                                            departmentService.addNonDepartmentEmployee(department1, listNewEmployees, serial);
                                            listNewEmployees.remove(serial - 1);
                                            break;
                                        }
                                    }
                                    break;
                                }
                                case "2": {
                                    List<Employee> listEmployees = department1.getListOfEmployee();
                                    System.out.println("""
                                            1.Add Salaried Employee
                                            2.Add Hourly Employee
                                            3.Exit""");
                                    System.out.println("Select: ");
                                    String anotherChoice = scanner.nextLine();
                                    switch (anotherChoice) {
                                        case "1" -> {
                                            if (listEmployees == null) {
                                       /*List<Employee> listNewEmployees1 = employeeService.createSalariedEmployee(scanner);
                                        listEmployees.addAll(listNewEmployees1);*/
                                                listEmployees = employeeService.createSalariedEmployee(scanner);
                                            } else {
                                                List<Employee> listNewEmployees1 = employeeService.createSalariedEmployee(scanner);
                                                listEmployees.addAll(listNewEmployees1);
                                            }
                                            department1.setListOfEmployee(listEmployees);
                                            System.out.println("Input done!");
                                            break;
                                        }
                                        case "2" -> {
                                            if (listEmployees == null) {
                                                listEmployees = employeeService.createHourlyEmployee(scanner);
                                            } else {
                                                List<Employee> listNewEmployees1 = employeeService.createHourlyEmployee(scanner);
                                                listEmployees.addAll(listNewEmployees1);
                                            }
                                    /*List<Employee> listNewEmployees1 = employeeService.createHourlyEmployee(scanner);
                                    listEmployees.addAll(listNewEmployees1);*/
                                            department1.setListOfEmployee(listEmployees);
                                            listAllEmployee.addAll(listEmployees);
                                            System.out.println("Input done");
                                            break;
                                        }
                                        default -> {
                                            choice = "3";
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    break;
                }
                case "6" -> {
                    if (departments.isEmpty()) {
                        System.out.println("There is no department");
                    } else {
                        System.out.println("Enter name of department: ");
                        departmentName = scanner.nextLine();
                        Department department1;
                        department1 = departmentService.departmentSearch(departments, departmentName);
                        if (department1.getDepartmentName() == null) {
                            System.out.println("The company doesn't have this department");
                        } else {
                            if (department1.getListOfEmployee() == null) {
                                System.out.println("There is no employee in this department");
                            } else {
                                departmentService.display(department1);
                            }
                        }
                    }
                    break;
                }
                case "7" -> {
                    employeeService.searchEmployeeInDepartment(departments);
                }

                case "8" -> {
                    employeeService.searchEmployee(departments,listNewEmployees);
                }
                case "9" -> {
                    employeeService.displayAllEmployeeInADepartment(departments);
                    break;
                }
                case "10"->{
                    employeeService.displayAllEmployee(listAllEmployee);
                }
                case "11" -> {
                    choice = "11";
                    break;
                }
            }
        } while (!choice.equals("11"));
    }
}



