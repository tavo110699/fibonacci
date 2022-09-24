package com.marlic.fibonacci.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Fibonacci {
    @Column(name = "s")
    private String s;

    public Fibonacci(String s) {
        this.s = s;
    }

    public String getS() {
        return s;
    }


}
