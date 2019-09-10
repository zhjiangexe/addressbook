package com.jiang.addressbook.service;

import com.jiang.addressbook.dao.CustomerDao;
import com.jiang.addressbook.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
  private CustomerDao customerDao;

  public CustomerService(CustomerDao customerDao) {
    this.customerDao = customerDao;
  }

  public List<Customer> fuzzySearchBySurname(String surname) {
    return customerDao.fuzzySearchBySurname(surname);
  }
}
