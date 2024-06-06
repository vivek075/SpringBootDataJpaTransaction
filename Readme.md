# Spring Boot Transaction Management Example

This project demonstrates transaction management in a Spring Boot application. The application allows a customer to purchase a product, ensuring that the transaction is rolled back if the customer does not have sufficient funds.

**Table of Contents**
- Introduction
- Technologies Used
- Setup and Run
- Endpoints
- Detailed Explanation
- Troubleshooting

**Introduction :**

This project simulates a simple application where customers can purchase products. It uses Spring Boot's transaction management to ensure that if a customer does not have enough funds for a purchase, the transaction is rolled back and no changes are made to the database.

**Technologies Used :**
- Java 8
- Spring Boot 2.7.6
- Spring Data JPA
- H2 Database
- Maven

**Setup and Run**
- Prerequisites
- JDK 8 or later
- Maven 3.6.3 or later

**Steps**
- Clone the repository:

  `git clone https://github.com/vivek075/spring-boot-transaction.git`

  `cd spring-boot-transaction`

- Build the project:

  `mvn clean install`

- Run the application:

  `mvn spring-boot:run`

- Access the H2 Console (optional):

Open your browser and navigate to http://localhost:8080/h2-console

`JDBC URL: jdbc:h2:mem:testdb`

`User Name: sa`

`Password:` 

**Endpoints**

- Purchase Product
  `URL: /api/purchase/{customerId}/{productId}`

- Method: POST

  Description: Purchases a product for a customer. Rolls back the transaction if the customer has insufficient funds.

- Example Requests:

Successful purchase:

`curl -X POST http://localhost:8080/api/purchase/1/1`

Purchase with insufficient funds:

`curl -X POST http://localhost:8080/api/purchase/1/2`

**Detailed Explanation**
- _Entities_

  Customer.java

  Represents a customer with an ID, name, and balance.

  Product.java

  Represents a product with an ID, name, and price.

  CustomerOrder.java

  Represents an order with an ID, customer, and product.

- _Repositories_
  
  CustomerRepository.java

  Handles CRUD operations for Customer.

  ProductRepository.java

  Handles CRUD operations for Product.

  OrderRepository.java

  Handles CRUD operations for CustomerOrder.

- _Service_

  PurchaseService.java

  Contains the business logic for purchasing a product. Uses @Transactional to manage transactions.

- _Controller_

  PurchaseController.java

  Handles HTTP requests for purchasing products.

InsufficientFundsException.java

Custom exception for insufficient funds.

- _Configuration_

  application.properties

  Configures the H2 database and enables the H2 console.

  `spring.datasource.url=jdbc:h2:mem:testdb`
  
  `spring.datasource.driverClassName=org.h2.Driver`
  
  `spring.datasource.username=sa`

  `spring.datasource.password=`
  
  `spring.jpa.database-platform=org.hibernate.dialect.H2Dialect`

  `spring.h2.console.enabled=true`
 
  `spring.h2.console.path=/h2-console`

**Troubleshooting**

Ensure that you have the correct versions of JDK and Maven installed.

If you encounter any issues with the H2 database, verify the JDBC URL, username, and password in application.properties.

Use the H2 console to inspect the database and verify that data is being inserted and updated correctly.

