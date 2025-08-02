# 🧠 Microservices-Based Quiz Application

This project is a microservices-based quiz application built using Spring Boot. It demonstrates how individual services can work together to create a scalable, modular system for quiz creation, question management, and score evaluation.

---

## 📌 Project Goal

To build a quiz system using **microservice architecture** that allows:
- Creating and managing quizzes
- Generating and submitting quiz questions
- Calculating quiz scores

---

## 🧱 Microservices Architecture

### 1. **question-service**
- Performs CRUD operations on quiz questions.
- Generates questions for a quiz based on ID list.
- Evaluates submitted quiz answers and calculates score.

### 2. **quiz-service**
- Creates quizzes.
- Fetches questions from `question-service`.
- Sends submitted answers back to `question-service` for scoring.

### 3. **eureka-server**
- Acts as the **service registry** using **Netflix Eureka**.
- Helps services discover each other dynamically without hardcoded URLs.

### 4. **api-gateway**
- Acts as a single entry point to the system.
- Forwards all external requests to the appropriate microservice.
- Helps avoid exposing multiple service ports.

---

## 🔧 Tech Stack

- **Backend Framework:** Spring Boot (Java)
- **Service Registry:** Netflix Eureka
- **API Gateway:** Spring Cloud Gateway
- **Database:** MySQL
- **Build Tool:** Maven
- **API Testing:** Postman

---

## 🚀 Getting Started

### ✅ Prerequisites

- Java 17+ installed
- MySQL server running (with schema configured)
- Maven installed
- Postman (optional, for testing)

### 📂 Clone the Repository

```bash
git clone https://github.com/yashank-mittal/Microservices.git
cd Microservices
