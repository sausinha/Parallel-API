package com.concurrent.app.controllers;

import com.concurrent.app.model.ResponseError;
import com.concurrent.app.model.*;
import com.concurrent.app.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(value = "/restApi")
public class ConcurrentController {

    @Autowired
    PersonService personService;

    @GetMapping(value = "person-List", produces = "application/json")
    public ResponseEntity collatedPersonInfo() {
        StopWatch watch = new StopWatch("My Stop Watch");

        watch.start("Parallel call");
        CompletableFuture<List<Employee>> employeeList = personService.getEmployeeDetails();
        CompletableFuture<List<Student>> studentList = personService.getStudentDetails();
        List<? super Person> personList = new ArrayList<>();
        List<CompletableFuture<? extends List<? extends Person>>> allFutures = Arrays.asList(employeeList, studentList);
        allFutures.forEach(future -> {
            try {
                future.join();
            } catch (CompletionException ex) {
                System.out.println(ex.getMessage());
            }
        });
        if (allFutures.stream().filter(CompletableFuture::isCompletedExceptionally).count() > 0) {
            return new ResponseEntity(new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR,
                    "One or more services failed", "500"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        try {
            personList.addAll(employeeList.get());
            personList.addAll(studentList.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        watch.stop();
        System.out.println("Time Elapsed for parallel call: " + watch.getTotalTimeSeconds() + " seconds");
        return new ResponseEntity(personList, HttpStatus.OK);
    }

    @GetMapping(value = "person-List-serial", produces = "application/json")
    public ResponseEntity collatedPersonInfoSerial() {
        StopWatch watch = new StopWatch("My Stop Watch");
        watch.start("Serial call");
        List<? super Person> personList = personService.findResponseTimeForSerialCall();
        watch.stop();
        System.out.println("Time Elapsed for serial call: " + watch.getTotalTimeSeconds() + " seconds");
        return new ResponseEntity(personList, HttpStatus.OK);
    }
}
