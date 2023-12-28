# Grocery Store Backend 🛒


Welcome to the Grocery Store Backend project! This is a RESTful API that I built with Java and Spring Boot for a grocery store application. It allows users and admins to perform various operations on grocery items, such as browsing, booking, processing, adding, updating, and deleting. It also provides authentication and authorization features based on user roles.

## Table of Contents 📋

- [Installation](#installation)
- [Usage](#usage)
- [Technologies](#technologies)

## Installation 💻

To install and run this project, you need to have Java and Maven installed on your machine. You also need to have a MySQL database server running on your localhost.

Clone this repository using the following command:

```bash
git clone https://github.com/your-username/grocery-store-backend.git
```

Navigate to the project directory and edit the `application.properties` file to configure your database credentials and other settings.

Then, run the following command to build and run the project:

```bash
mvn spring-boot:run
```

The API will be available at `http://localhost:8080`.

## Usage 🛍️

This API provides endpoints for users and admins to perform various operations on grocery items. The following are the main endpoints and operations:

### User Endpoints 👤

- `GET /user/items` - This endpoint retrieves all available groceries in the store.
- `POST /user/item/{itemId}/book` - This endpoint books a grocery for a user and puts it into a tentative order.
- `POST /user/booking/{bookingId}/process` - This endpoint processes the order, indicating that the user has bought the item and the item should be removed from the store inventory.

### Admin Endpoints 🔑

- `POST /admin/item` - This endpoint adds a new grocery item.
- `GET /admin/inventory` - This endpoint lists all the grocery items saved in the system.
- `DELETE /admin/item/{itemId}` - This endpoint deletes an existing grocery item.
- `PUT /admin/item` - This endpoint updates an existing grocery item.
- `POST /user/register` - This endpoint is used to register a new user.

To test the above endpoints, you can import the `GroceryStore.postman_collection.json` file in Postman and use the provided requests.

### Entities 📄

This project uses the following entities to store and manipulate data:

- GroceryItem - This is the grocery item entity that has properties such as name, price, quantity, and category.
- Booking - This is the booking entity that stores the user's booked items and their status (pending or processed).
- User - This is the user entity that represents a user in the system, with properties such as username, password, and role (ADMIN or USER).

### Authentication & Authorization 🔐

- The system relies on basic authentication scheme to verify the user's identity and grant access to the endpoints.
- The user details are stored in the database using the user entity class.
- A default username `master` and password as `Master@1234!` is created with "ADMIN" role.
- Admin endpoints are only accessible to users with role as `ADMIN`.
- Only admin has the ability to create new user, and he can specify the role and password at the time of user creation.

## Technologies 🚀

This project is built with the following technologies and tools:

- Java - The programming language used to write the backend logic.
- Spring Boot - The framework used to create the RESTful API and handle the dependencies.
- Maven - The tool used to manage the project build and dependencies.
- Postgres - The database used to store and retrieve the data.
- Postman - The tool used to test and document the API endpoints.

## Not Implemented ❌
- Tests
- Pagination, and Sorting
- Order Processing. Grocery Items can only be booked.