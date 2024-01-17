package com.example.demo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private int age;
    private String name;
    private int salary;
    private String department;
    private String gender;
    private int yearOfJoining;

    public static void main(String[] args) {
        List<Employee> empList = Arrays.asList(
                new Employee(28, "John", 50000, "HR", "Male", 2014),
                new Employee(26, "Roma", 51000, "HR", "Female", 2015),
                new Employee(31, "Mathew", 60000, "HR", "Male", 2017),
                new Employee(26, "Michel", 95000, "Tech", "Male", 2013),
                new Employee(37, "Ram", 48000, "Tech", "Male", 2013),
                new Employee(29, "Agnes", 73000, "Ops", "Female", 2013),
                new Employee(30, "Jessica", 65000, "Ops", "Female", 2013),
                new Employee(31, "Julie", 56000, "Ops", "Female", 2013),
                new Employee(21, "Xiaodong", 50600, "Ops", "Male", 2019),
                new Employee(32, "Liping", 50300, "Tech", "Female", 2014),
                new Employee(23, "Garry", 40000, "Tech", "Male", 2014),
                new Employee(40, "Harry", 170708, "Tech", "Female", 2017));
        // Q1. get the detail of higest paid employee
        method1(empList);
        // Q2. List of employee who join after 2014
        method2(empList);
        // Q3. No of male and femaile employee in company
        method3(empList);
        // Q4. Average salary of organisation employee
        method4(empList);
        // Q5. Name of all department
        method5(empList);
        // Q6. average salary of male and female employee
        method6(empList);
        // Q7. who has the max exprience of the company
        method7(empList);
        // Q8. Details of youngest female employee in HR department
        method8(empList);
        // Q9. Average age of employee in company
        method9(empList);
        // Q10. Find details of employee who has 3rd higest salary
        method10(empList);
    }

    private static void method10(List<Employee> empList) {
        Optional<String> first = empList.stream().sorted((a, b) -> b.getSalary() - a.getSalary()).map(Employee::getName)
                .skip(2).findFirst();

        System.out.println(first);
    }

    private static void method9(List<Employee> empList) {
        double average = empList.stream().collect(Collectors.summarizingInt(Employee::getAge)).getAverage();

        System.out.println(average);
    }

    private static void method8(List<Employee> empList) {
        Optional<Employee> result = empList.stream()
                .filter(dt -> dt.getDepartment().equals("HR") && dt.getGender().equals("Female"))
                .min(Comparator.comparing(Employee::getAge));

        result.ifPresent(employee -> System.out.println(employee));
    }

    private static void method7(List<Employee> empList) {
        String string = empList.stream().sorted((a, b) -> a.getYearOfJoining() - b.getYearOfJoining())
                .map(Employee::getName).findFirst().get();
        System.out.println(string);
        // List<Employee> collect =
        // empList.stream().min(Comparator.comparing(Employee::getYearOfJoining)).stream()
        // .collect(Collectors.toList());
        // System.out.println(collect);
    }

    private static void method6(List<Employee> empList) {
        Map<String, Double> collect = empList.stream()
                .collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingDouble(Employee::getSalary)));
        System.out.println(collect);
    }

    private static void method5(List<Employee> empList) {
        // List<String> collect = empList.stream()
        // .collect(Collectors.groupingBy(Employee::getDepartment)).entrySet().stream().map(Map.Entry::getKey)
        // .collect(Collectors.toList());
        // System.out.println(collect);
        List<String> collect = empList.stream().map(Employee::getDepartment).distinct().collect(Collectors.toList());
        System.out.println(collect);
    }

    private static void method4(List<Employee> empList) {
        DoubleSummaryStatistics collect = empList.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(collect.getAverage());
    }

    private static void method3(List<Employee> empList) {
        Map<String, Long> collect = empList.stream()
                .collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
        System.out.println(collect);
    }

    private static void method2(List<Employee> empList) {
        empList.stream().filter(dt -> dt.getYearOfJoining() > 2014).collect(Collectors.toList())
                .forEach(System.out::println);
    }

    private static void method1(List<Employee> empList) {
        String str = empList.stream().max(Comparator.comparing(Employee::getSalary)).get().getName();
        System.out.println(str);
        System.out.println("=".repeat(50));
        String name2 = empList.stream().collect(Collectors.maxBy(Comparator.comparing(Employee::getSalary))).get()
                .getName();
        System.out.println(name2);
    }
}
