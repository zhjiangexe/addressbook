package com.jiang.addressbook.service;

import com.jiang.addressbook.dao.CustomerDao;
import com.jiang.addressbook.entity.Customer;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.matches;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
  @InjectMocks
  private CustomerService customerService;
  @Mock
  private CustomerDao customerDao;

  @ParameterizedTest(name = "Given {0} Then find Customers have surname Potter")
  @ValueSource(strings = {"Potter", "POTTER", "potter"})
  void fuzzySearchBySurname(String surname) {
    ArrayList<Customer> potters = new ArrayList<Customer>() {{
      add(new Customer(1L, "Potter", "Harry", "4 Privet Drive, Little Whinging, Surrey"));
      add(new Customer(2L, "Potter", "James", "Hogwarts School"));
    }};

    when(customerDao.fuzzySearchBySurname(matches("(Potter|POTTER|potter)"))).thenReturn(potters);

    List<Customer> customers = customerService.fuzzySearchBySurname(surname);

    assertThat(customers).extracting("surname").allMatch(e -> e.equals("Potter"));
  }
}