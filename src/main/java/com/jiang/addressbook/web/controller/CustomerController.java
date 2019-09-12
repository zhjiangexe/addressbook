package com.jiang.addressbook.web.controller;

import com.jiang.addressbook.entity.Customer;
import com.jiang.addressbook.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Validated
@RestController
@RequestMapping("/customers")
@AllArgsConstructor
public class CustomerController {
  private final CustomerService customerService;

  /**
   * fuzzy search customers surname
   * @param surname
   * @return customers
   */
  @GetMapping("")
  public List<Customer> fuzzySearchBySurname(@NotBlank @RequestParam String surname) {
    return customerService.fuzzySearchBySurname(surname);
  }
}
