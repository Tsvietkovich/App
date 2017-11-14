package com.tsvietkovich.TutorialsExamples.Employee;

import javax.persistence.*;

@Entity
@Table
@NamedQuery(name = "find employee by id",query = "Select e from Employee e where e.eid = :id")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int eid;
    private String ename;
    private double salary;
    private String deg;

    public Employee(String ename, double salary, String deg) {

        this.ename = ename;
        this.salary = salary;
        this.deg = deg;
    }

    public Employee( ) {
    }

    public int getEid( ) {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getEname( ) {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public double getSalary( ) {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDeg( ) {
        return deg;
    }

    public void setDeg(String deg) {
        this.deg = deg;
    }

    @Override
    public String toString() {
        return "Employee [eid=" + eid + ", ename=" + ename + ", salary=" + salary + ", deg=" + deg + "]";
    }
}
