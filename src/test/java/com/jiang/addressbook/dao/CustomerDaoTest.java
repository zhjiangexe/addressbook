package com.jiang.addressbook.dao;

import com.jiang.addressbook.entity.Customer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:db/changelog/changelog-master.xml")
class CustomerDaoTest {
  @Autowired
  private CustomerDao customerDao;

  @Nested
  class fuzzySearchBySurname {
    @ParameterizedTest(name = "{index}. Given {0} Then find Customers have surname Potter")
    @ValueSource(strings = {"potter", "POTTER", "Potter"})
    void Given_DifferentCaseWord_Then_FindAllCustomerNoExactCaseMatch(String surname) {

      List<Customer> customers = customerDao.fuzzySearchBySurname(surname);

      assertThat(customers).extracting("surname").allMatch(e -> e.equals("Potter"));
    }

    @ParameterizedTest(name = "{index}. Given fuzzy word [{0}] Then find {1} customers")
    @CsvSource({"ot, 3", "er, 3", "po, 3"})
    void Given_FuzzyWord_Then_FindAllMatchCustomer(String surnameLike, int expectCustomerQty) {

      List<Customer> customers = customerDao.fuzzySearchBySurname(surnameLike);

      assertThat(customers).hasSize(expectCustomerQty);
    }

  }
}