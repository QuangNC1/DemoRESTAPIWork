package com.example.demo.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Work;

@Repository
public interface WorkService  extends JpaRepository<Work, String>{

}
