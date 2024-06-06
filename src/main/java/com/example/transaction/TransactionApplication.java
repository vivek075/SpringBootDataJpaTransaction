package com.example.transaction;

import com.example.transaction.entity.Customer;
import com.example.transaction.entity.Product;
import com.example.transaction.repository.CustomerRepository;
import com.example.transaction.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class TransactionApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransactionApplication.class, args);
    }

    @Bean
    public CommandLineRunner initDatabase(CustomerRepository customerRepository, ProductRepository productRepository) {
        return args -> {
            // Create an initial customer with a default balance
            Customer customer = new Customer();
            customer.setName("Vivek Singh");
            customer.setBalance(100);
            customerRepository.save(customer);

            List<Product> products = new ArrayList<>();
            Product p1 = new Product();
            p1.setName("Laptop");
            p1.setPrice(80.00);
            Product p2 = new Product();
            p2.setName("Phone");
            p2.setPrice(50.00);
            products.add(p1);
            products.add(p2);
            productRepository.saveAll(products);
        };
    }
}
