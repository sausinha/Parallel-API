package com.concurrent.app.service;

import com.concurrent.app.model.Employee;
import com.concurrent.app.model.Person;
import com.concurrent.app.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface PersonService {

    CompletableFuture<List<Employee>> getEmployeeDetails();

    CompletableFuture<List<Student>> getStudentDetails();

    List<? super Person> findResponseTimeForSerialCall();
}
