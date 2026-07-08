# 🎬 Book My Show Backend

A backend application for an online movie ticket booking system developed using Java and Spring Boot.

> 🚧 This project is currently under development.

---

## 📌 Project Description

The application allows theater owners to manage theaters, halls, and movie shows. Customers can register to use the platform. More features like movie booking, seat selection, and payment integration are planned.

---

## 🚀 Tech Stack

- Java
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven
- Hibernate
- REST API

---

## 📂 Project Structure

```
src
 ├── controller
 ├── service
 ├── repository
 ├── entity
 ├── dto
 ├── exception
 ├── enums
 └── config
```

---

## ✅ Features Implemented

- User Registration
- Theater Owner Registration
- Theater Management
- Hall Management
- Show Management
- Exception Handling
- DTO-based Request Handling
- Layered Architecture

---

## 🚧 Features Under Development

- User Login
- JWT Authentication
- Movie Management
- Seat Management
- Ticket Booking
- Booking History
- Ticket Cancellation
- Payment Integration
- Email Notifications

---

## 🛠 Database

PostgreSQL

Database Name:

```
bms
```

---

## ▶️ How to Run

### 1. Clone the repository

```bash
git clone https://github.com/lingesh-sirpa/book-my-show.git
```

### 2. Open the project in IntelliJ IDEA

### 3. Create PostgreSQL database

```
bms
```

### 4. Configure database credentials

Edit:

```
src/main/resources/application.properties
```

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/bms
spring.datasource.username=postgres
spring.datasource.password=YOUR_DATABASE_PASSWORD
```

### 5. Run

Execute:

```
BookMyShowApplication.java
```

The application will start on:

```
http://localhost:8080
```

---

## 📌 Current Status

🚧 Work In Progress

This project is actively being developed. New features and improvements are added regularly.

---

## 👨‍💻 Author

**Lingesh**

GitHub:
https://github.com/lingesh-sirpa