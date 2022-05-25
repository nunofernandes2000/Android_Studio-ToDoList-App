package com.estgp.todolist.Classes;

import java.io.Serializable;
import java.time.LocalDate;

public class task implements Serializable {

    private String name;
    private LocalDate limitDate;
    private String description;

    public task() {
    }

    public task(String name, LocalDate limitDate, String description) {
        this.name = name;
        this.limitDate = limitDate;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public  void setName(String name) {
        this.name = name;
    }

    public LocalDate getLimitDate() {
        return limitDate;
    }

    public  void setLimitDate(LocalDate limitDate) {
        this.limitDate = limitDate;
    }

    public String getDescription() {
        return description;
    }

    public  void setDescription(String description) {
        this.description = description;
    }



}


