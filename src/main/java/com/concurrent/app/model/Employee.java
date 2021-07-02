package com.concurrent.app.model;


public class Employee extends Person {

    public Employee(){

    }
    public Employee(int id, String name, String email, String address, int age){
        super(id, name, email, address, age);
    }
    private String salary;
    private String department;

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
