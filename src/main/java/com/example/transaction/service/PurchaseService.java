package com.example.transaction.service;

import com.example.transaction.entity.Customer;
import com.example.transaction.entity.CustomerOrder;
import com.example.transaction.entity.Product;
import com.example.transaction.repository.CustomerRepository;
import com.example.transaction.repository.OrderRepository;
import com.example.transaction.repository.ProductRepository;
import com.example.transaction.service.exception.InsufficientFundsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PurchaseService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public void purchaseProduct(Long customerId, Long productId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        if (customer.getBalance() < product.getPrice()) {
            throw new InsufficientFundsException("Insufficient funds for the purchase");
        }

        customer.setBalance(customer.getBalance() - product.getPrice());
        customerRepository.save(customer);

        CustomerOrder order = new CustomerOrder();
        order.setCustomer(customer);
        order.setProduct(product);
        orderRepository.save(order);
    }
}
