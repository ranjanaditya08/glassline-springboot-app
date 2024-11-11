
# Glassline E-Commerce Platform

An e-commerce platform backend built with Spring Boot, designed for seamless role-based access control, secure user authentication with JWT, and efficient management of products and cart items. The project aims to offer users a smooth shopping experience while providing admins with control over product listings.

---

## Features

### For Users
- **JWT Authentication**: Secure login and token-based session management.
- **Cart Management**: Add, edit, and delete items in the cart, with quantities maintained.

### For Admins
- **Product Management**: Add, edit, and delete products directly from the admin interface.
- **Role-Based Access Control**: Different functionalities for admins and users, with tailored access to specific actions.

### General
- **Role-Based Access Control**: Users and admins have separate permissions.
- **RESTful API**: Organized endpoints for interaction with the platform.
- **Error Handling**: User-friendly error messages and standardized responses.

## Technologies

- **Backend**: Spring Boot
- **Security**: Spring Security with JWT for authentication and authorization
- **Data Persistence**: MySQL (or any SQL database), Spring Data JPA

## Setup

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/ranjanaditya08/glassline-springboot-app.git
   cd glassline
