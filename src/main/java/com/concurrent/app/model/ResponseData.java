package com.concurrent.app.model;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ResponseData {

    HttpStatus status;

    List<? super Person> personList;

    public ResponseData(HttpStatus status, List<? super Person> personList) {
        this.status = status;
        this.personList = personList;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public List<? super Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<? super Person> personList) {
        this.personList = personList;
    }
}
