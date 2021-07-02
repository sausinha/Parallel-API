package com.concurrent.app.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class Student extends Person{

    public Student(){

    }
    public Student(int id, String name, String email, String address, int age){
        super(id, name, email, address, age);
    }
    private String degree;
    private int totalMarks;

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public int getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
    }
}
