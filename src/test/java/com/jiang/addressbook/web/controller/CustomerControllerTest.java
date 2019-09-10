package com.jiang.addressbook.web.controller;

import com.jiang.addressbook.entity.Customer;
import com.jiang.addressbook.service.CustomerService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static com.jayway.jsonpath.matchers.JsonPathMatchers.hasJsonPath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.ArgumentMatchers.matches;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private CustomerService customerService;

  @Nested
  class query {
    final static String SURNAME = "surname";

    @Test
    void Given_BlankSurname_Then_Return400BadRequest() throws Exception {
      String json = mockMvc.perform(get("/customers").param(SURNAME, "   "))
          .andExpect(status().isBadRequest())
          .andReturn().getResponse().getContentAsString();

      assertThat(json, hasJsonPath("$.errors", hasItem("must not be blank")));

      System.out.println(json);
    }

    @ParameterizedTest(name = "Given {0} Then find Customers have surname Potter")
    @ValueSource(strings = {"Potter", "POTTER", "potter"})
    void Given_SpecificSurname_Then_ReturnCustomers(String surname) throws Exception {
      ArrayList<Customer> customers = new ArrayList<Customer>() {{
        add(new Customer(1L, "Potter", "Harry", "4 Privet Drive, Little Whinging, Surrey"));
        add(new Customer(2L, "Potter", "James", "Hogwarts School"));
      }};

      when(customerService.fuzzySearchBySurname(matches("(Potter|POTTER|potter)"))).thenReturn(customers);

      String json = mockMvc.perform(get("/customers").param(SURNAME, surname))
          .andExpect(status().isOk())
          .andReturn().getResponse().getContentAsString();

      assertThat(json, hasJsonPath("$.data[*].surname", hasItem("Potter")));

      System.out.println(json);
    }
  }
}
