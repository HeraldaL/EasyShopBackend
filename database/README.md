## Project Name: EasyShopBackend
## Hello
The objective of the EasyShop Application is to develop an e-commerce platform for EasyShop, a company that specializes in online retail. The project involves utilizing a Spring Boot API as the backend server and a MySQL database for efficient data storage. The primary goal is to address any existing issues and introduce enhancements for an updated version of the EasyShop website.

## Usage
1-Access the application through the defined base URL (e,g., http://localhost:8080)

2-Use Postman to interact with available endpoints.

##  Endpoints
GET /categories: Retrieve all categories.

GET /categories/{id}: Retrieve a specific category by its ID.

GET /categories/{categoryId}/products: Retrieve all products under a specific category.

POST /categories: Create a new category.

PUT /categories/{id}: Update an existing category.

DELETE /categories/{id}: Delete a category.

## Technology Stack
Spring Boot

MySQL

JdbcTemplate

## Error Handling
Only users with the "ROLE_ADMIN" role can perform POST, PUT, and DELETE operations.

If a user without the required role tries to access these endpoints, a 403 status code will be returned.


## Postman Test view
![Postman Test.PNG](..%2F..%2F..%2F..%2F..%2FUsers%2FStudent%2FPictures%2FSaved%20Pictures%2FPostman%20Test.PNG)

## Website View
![website.PNG](..%2F..%2F..%2F..%2F..%2FUsers%2FStudent%2FPictures%2FSaved%20Pictures%2Fwebsite.PNG)