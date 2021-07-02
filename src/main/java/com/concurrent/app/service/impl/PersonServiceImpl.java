package com.concurrent.app.service.impl;

import com.concurrent.app.model.Employee;
import com.concurrent.app.model.Person;
import com.concurrent.app.model.Student;
import com.concurrent.app.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class PersonServiceImpl  implements PersonService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    @Async("asyncExecutor")
    public CompletableFuture<List<Employee>> getEmployeeDetails() {

        String url = "http://localhost:8089/app/employee-list" ;
        Employee[] response = restTemplate.getForObject(url, Employee[].class);

        return CompletableFuture.completedFuture(Arrays.asList(response));
    }

    @Override
    @Async("asyncExecutor")
    public CompletableFuture<List<Student>> getStudentDetails() {

        String url = "http://localhost:8085/api/student-list" ;
        Student[] response = restTemplate.getForObject(url, Student[].class);

        return CompletableFuture.completedFuture(Arrays.asList(response));
    }

    @Override
    public List<? super Person> findResponseTimeForSerialCall() {
        List<? super Person> personList = new ArrayList<>();
        String employeeUrl = "http://localhost:8089/app/employee-list" ;
        Employee[] employeeResponse = restTemplate.getForObject(employeeUrl, Employee[].class);
        List<Employee> employeeList = Arrays.asList(employeeResponse);
        personList.addAll(employeeList);
        String studentUrl = "http://localhost:8085/api/student-list" ;
        Student[] studentResponse = restTemplate.getForObject(studentUrl, Student[].class);
        List<Student> studentList = Arrays.asList(studentResponse);
        personList.addAll(studentList);
        return personList;
    }
}
