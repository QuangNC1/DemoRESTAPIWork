package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Work implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String starting_date;
    private String ending_date;
    private String status;
    @Id
    private String work_name;

    public Work(){
        work_name="";
    }
    public Work(String work_name, String starting_date, String ending_date, String status) {
        this.work_name =work_name;
        this.starting_date =starting_date;
        this.ending_date =ending_date;
        this.status =status;
    }
    public String getStarting_date() {
        return starting_date;
    }
    public void setStarting_date(String starting_date) {
        this.starting_date = starting_date;
    }
    public String getEnding_date() {
        return ending_date;
    }
    public void setEnding_date(String ending_date) {
        this.ending_date = ending_date;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getWork_name() {
        return work_name;
    }
    public void setWork_name(String work_name) {
        this.work_name = work_name;
    }
    
}
