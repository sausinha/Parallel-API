package com.concurrent.app.dtos;

import com.concurrent.app.model.Employee;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class EmployeeDto {

    private List<Employee> employeeList;

    public EmployeeDto() {
        employeeList = new ArrayList<>();
    }
}
