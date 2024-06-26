package com.example.transaction.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/*
* @Entity: This annotation specifies that the class is an entity and is mapped to a database table.
  @Table(name = "customer_order"): This annotation specifies the name of the database table to be used for mapping. In this case, it is customer_order.
  @Id: This annotation specifies the primary key of the entity.
  @GeneratedValue(strategy = GenerationType.IDENTITY): This annotation indicates that the primary key value is automatically generated by the database (using an auto-increment column).
  @ManyToOne: This annotation specifies a many-to-one relationship between the CustomerOrder entity and the Customer and Product entities.
* */
@Entity
@Table(name = "customer_order")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Product product;
}
