package dev.lpa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

record Employee (String firstName, String lastName, String hireDate) {}

public class Main {

    public static void main(String[] args) {

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Renato", "Balmus", "14/06/2015"));
        employees.add(new Employee("Daniel", "Popescu", "22/02/2008"));
        employees.add(new Employee("Andrei", "Calmuc", "04/12/2019"));
        employees.add(new Employee("Catalin", "Calin", "11/04/2010"));
        employees.add(new Employee("Florin", "Zlate", "09/11/2004"));
        employees.add(new Employee("Mihai", "Mahailescu", "18/09/2010"));

        printOrderedList(employees,"yearsWorked");

    }

    public static void printOrderedList(List<Employee> eList, String sortField) {

        int currentYear = LocalDate.now().getYear();

        class MyEmployee {
            Employee containedEmployee;
            int yearsWorked;
            String fullName;

            public MyEmployee(Employee containedEmployee) {
                this.containedEmployee = containedEmployee;
                yearsWorked = currentYear - Integer.parseInt(containedEmployee.hireDate().split("/")[2]);
                fullName = containedEmployee.firstName() + " " + containedEmployee.lastName();
            }

            @Override
            public String toString() {
                return "%s has been an employee for %d years".formatted(fullName,yearsWorked);
            }
        }

        List<MyEmployee> list = new ArrayList<>();
        for (var employee: eList) {
            list.add(new MyEmployee(employee));
        }

        var comparator = new Comparator<MyEmployee>() {

            @Override
            public int compare(MyEmployee o1, MyEmployee o2) {
                if (sortField.equals("name")) {
                    return o1.fullName.compareTo(o2.fullName);
                }
                return o1.yearsWorked - o2.yearsWorked;
            }
        };

        list.sort(comparator);
        for (MyEmployee myEmployee : list) {
            System.out.println(myEmployee);
        }
    }
}
