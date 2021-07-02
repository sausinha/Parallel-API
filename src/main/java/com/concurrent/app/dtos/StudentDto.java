package com.concurrent.app.dtos;

import com.concurrent.app.model.Employee;
import com.concurrent.app.model.Student;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class StudentDto {

    private List<Student> studentList;

    public StudentDto() {
        studentList = new ArrayList<>();
    }

}
