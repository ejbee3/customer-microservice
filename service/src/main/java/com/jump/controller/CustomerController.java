package com.jump.controller;

import java.net.URI;
import java.util.List;

import com.jump.model.Customer;
import com.jump.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/")
public class CustomerController {

  @Autowired
  CustomerService customerService;

  @GetMapping("customers")
  public ResponseEntity<List<Customer>> getCustomers() {

    return ResponseEntity.ok(customerService.getCustomers());
  }

  @GetMapping("customers/{id}")
  public Customer getCustomer(@PathVariable("id") int id) {
    return customerService.getCustomerById(id);
  }

  @PostMapping("customers")
  public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {

    Customer result = customerService.addCustomer(customer);

    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(result.getId())
        .toUri();
    return ResponseEntity.created(location).body(result);

  }

  @PutMapping("customers/{customerId}")
  public Customer updateCustomer(@PathVariable Integer customerId, @RequestBody Customer customer) {

    Customer result = customerService.updateCustomer(customer);

    return result;
  }

  @DeleteMapping("customers/{customerId}")
  public ResponseEntity<?> deleteCustomer(@PathVariable Integer customerId) {
    customerService.deleteCustomer(customerId);
    return ResponseEntity.noContent().build();
  }

}
