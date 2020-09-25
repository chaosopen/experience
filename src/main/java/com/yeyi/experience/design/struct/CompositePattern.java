package com.yeyi.experience.design.struct;


import java.util.ArrayList;
import java.util.List;

/**
 * 组合模式
 * 又叫部分整体模式，是用于把一组相似的对象当作一个单一的对象
 * 按照层级关系包装到一个对象里
 *
 * 应用实例： 1、算术表达式包括操作数、操作符和另一个操作数，其中，另一个操作符也可以是操作数、操作符和另一个操作数。
 * 2、在 JAVA AWT 和 SWING 中，对于 Button 和 Checkbox 是树叶，Container（容器） 是树枝。
 */
public class CompositePattern {

    static class Employee {
        private String name;
        private String dept;
        private int salary;
        private List<Employee> subordinates;

        //构造函数
        public Employee(String name,String dept, int sal) {
            this.name = name;
            this.dept = dept;
            this.salary = sal;
            subordinates = new ArrayList<Employee>();
        }

        public void add(Employee e) {
            subordinates.add(e);
        }

        public void remove(Employee e) {
            subordinates.remove(e);
        }

        public List<Employee> getSubordinates(){
            return subordinates;
        }

        public String toString(){
            return ("Employee :[ Name : "+ name
                    +", dept : "+ dept + ", salary :"
                    + salary+" ]");
        }
    }

    public static void main(String[] args) {
        Employee CEO = new Employee("John","CEO", 30000);

        Employee headSales = new Employee("Robert","Head Sales", 20000);

        Employee headMarketing = new Employee("Michel","Head Marketing", 20000);

        Employee clerk1 = new Employee("Laura","Marketing", 10000);
        Employee clerk2 = new Employee("Bob","Marketing", 10000);

        Employee salesExecutive1 = new Employee("Richard","Sales", 10000);
        Employee salesExecutive2 = new Employee("Rob","Sales", 10000);

        CEO.add(headSales);
        CEO.add(headMarketing);

        headSales.add(salesExecutive1);
        headSales.add(salesExecutive2);

        headMarketing.add(clerk1);
        headMarketing.add(clerk2);

        //打印该组织的所有员工
        System.out.println(CEO);
        for (Employee headEmployee : CEO.getSubordinates()) {
            System.out.println(headEmployee);
            for (Employee employee : headEmployee.getSubordinates()) {
                System.out.println(employee);
            }
        }
    }
}
