# 🎬 Book My Show

A movie ticket booking backend application built using **Java and Spring Boot**. The application provides REST APIs for user registration, theater and hall management, movie show creation, seat availability, seat booking, and booking confirmation through email.

> 🚧 This project is currently under development. More features will be added in future.

---

## 📌 Project Overview

Book My Show is a backend application designed to simulate an online movie ticket booking system.

The application supports different users, including customers and theater owners. Theater owners can create theaters, halls, and movie shows, while customers can search for shows, check seat availability, and book seats.

After a successful seat booking, the application generates a booking confirmation email containing the customer's booking and show details.

---

## ✨ Features

### 👤 User Management

* Customer registration
* Theater owner registration
* User type management
* User information stored in PostgreSQL

### 🎭 Theater Management

* Create theaters
* Associate theaters with theater owners
* Manage theater information

### 🏢 Hall Management

* Create halls inside theaters
* Configure hall seat capacity
* Manage hall information

### 🎬 Show Management

* Create movie shows
* Configure movie name
* Configure show price
* Configure show start and end time
* Search shows by movie and city
* Validate show scheduling

### 💺 Seat Management

* Configure seats for halls
* Check seat availability for a show
* Book available seats
* Prevent booking of already booked seats
* Store booking information

### 🎟️ Ticket Booking

* Book a seat for a particular show
* Generate a unique booking ID
* Store seat booking details
* Store booking status
* Associate booked seats with a show

### 📧 Email Notification

After successful booking, a booking confirmation email is sent to the customer.

The email contains:

* Customer name
* Customer email
* Booking ID
* Seat ID
* Movie name
* Show price
* Show start time
* Show end time

The email content is generated using **Thymeleaf HTML templates**.

---

## 🛠️ Tech Stack

| Technology       | Usage                         |
| ---------------- | ----------------------------- |
| Java 17          | Programming Language          |
| Spring Boot      | Backend Framework             |
| Spring Web MVC   | REST APIs                     |
| Spring Data JPA  | Database Access               |
| Hibernate        | ORM                           |
| PostgreSQL       | Database                      |
| Maven            | Build & Dependency Management |
| Thymeleaf        | Email Template                |
| Spring Boot Mail | Email Notifications           |
| Lombok           | Boilerplate Code Reduction    |
| Git & GitHub     | Version Control               |
| Postman          | API Testing                   |
| IntelliJ IDEA    | Development Environment       |

---

## 🎟️ Seat Booking Flow

The current booking flow works approximately as follows:

```text
Customer
   ↓
Select Movie
   ↓
Search Shows
   ↓
Select Show
   ↓
Check Seat Availability
   ↓
Select Seat
   ↓
Book Seat
   ↓
Generate Booking ID
   ↓
Save Booking in PostgreSQL
   ↓
Send Confirmation Email
   ↓
Customer Receives Ticket Confirmation
```

---

## 📧 Booking Confirmation Email

The application uses **Thymeleaf** to generate an HTML booking confirmation email.

The email contains information such as:

```text
Customer Name : Hari
Email : hari@example.com
Booking ID : BMS001
Seat ID : A5
Movie : Avengers
Show Price : ₹250
Start Time : 07:00 PM
End Time : 10:00 PM
```

The email generation flow is:

```text
Booking
   ↓
EmailService
   ↓
Thymeleaf Context
   ↓
book-seat.html
   ↓
HTML Email
   ↓
NotificationService
   ↓
Customer Email
```

---

## 🗄️ Database

The application uses **PostgreSQL**.

Database name:

```text
bms
```

Configure the database in:

```text
src/main/resources/application.properties
```

Example:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/bms
spring.datasource.username=postgres
spring.datasource.password=YOUR_DATABASE_PASSWORD
```

---

## 📧 Email Configuration

Spring Boot Mail is used for sending booking confirmation emails.

Configure your email credentials in `application.properties`.

For Gmail, use an **App Password** rather than your normal Gmail password.

Do not commit email credentials or app passwords to GitHub.

---

## ▶️ How to Run

### 1. Clone the repository

```bash
git clone https://github.com/lingesh-sirpa/book-my-show.git
```

### 2. Open the project

Open the project in **IntelliJ IDEA**.

### 3. Create PostgreSQL Database

Create a database named:

```text
bms
```

### 4. Configure Database

Update:

```text
src/main/resources/application.properties
```

with your PostgreSQL credentials.

### 5. Configure Email

Add your email configuration and App Password if email notifications are enabled.

### 6. Run the Application

Run:

```text
BookMyShowApplication.java
```

The backend will start on:

```text
http://localhost:8080
```

---

## 🧪 API Testing

The REST APIs can be tested using **Postman**.

Recommended testing sequence:

```text
1. Register User
        ↓
2. Register Theater Owner
        ↓
3. Create Theater
        ↓
4. Create Hall
        ↓
5. Create Show
        ↓
6. Search Shows
        ↓
7. Check Seat Status
        ↓
8. Book Seat
        ↓
9. Verify Booking
        ↓
10. Check Confirmation Email
```

---

## 🚧 Future Improvements

The following features can be added to make the application more complete:

* [ ] User Login
* [ ] JWT Authentication
* [ ] Role-based Authorization
* [ ] Movie Management
* [ ] Multiple Seat Booking
* [ ] Booking History
* [ ] Ticket Cancellation
* [ ] Payment Gateway Integration
* [ ] Refund Management
* [ ] Redis-based Seat Locking
* [ ] Booking Expiration
* [ ] QR Code Ticket
* [ ] Improved Global Exception Handling
* [ ] Frontend integration with REST APIs
* [ ] Unit and Integration Testing
* [ ] Docker Support
* [ ] API Documentation using Swagger/OpenAPI

---

## 📈 Current Status

🚧 **Work In Progress**

The core backend functionality for user registration, theater/hall management, show management, seat availability, seat booking, and booking confirmation email has been implemented.

The project is being continuously improved with additional features and better architecture.

---

## 👨‍💻 Author

**Lingesh**

GitHub:
https://github.com/lingesh-sirpa

---