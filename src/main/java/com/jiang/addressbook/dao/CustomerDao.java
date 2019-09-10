package com.jiang.addressbook.dao;

import com.jiang.addressbook.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerDao extends JpaRepository<Customer, Long> {
  @Query("SELECT C FROM Customer C WHERE LOWER(C.surname) LIKE LOWER(concat('%', :surname, '%'))")
  List<Customer> fuzzySearchBySurname(@Param("surname") String surname);
}
