# 🔌 REST API Automation Framework

![Java](https://img.shields.io/badge/Java-17-orange?style=flat-square)
![RestAssured](https://img.shields.io/badge/Rest_Assured-5.3-green?style=flat-square)
![Jackson](https://img.shields.io/badge/Jackson-Serialization-blue?style=flat-square)
![TestNG](https://img.shields.io/badge/TestNG-7.9-red?style=flat-square)
![Allure](https://img.shields.io/badge/Allure-Report-yellow?style=flat-square)

## 📋 Overview

This project is a scalable **API Testing Framework** designed to validate RESTful services using **Java 17** and **Rest Assured**. It implements advanced patterns like **POJO Serialization** (using Jackson) and centralized Request Specifications.

The framework runs against [JSONPlaceholder](https://jsonplaceholder.typicode.com/), a fake online REST API for testing and prototyping, covering the full **CRUD** (Create, Read, Update, Delete) lifecycle and edge cases.

## 🚀 Key Features

* **Advanced Serialization:** Uses Java Objects (POJOs) to dynamically build JSON payloads, avoiding hardcoded strings.
* **Robust Configuration:** Implements `RequestSpecBuilder` in a `BaseTest` class to manage Base URLs, Headers (User-Agent), and Logging filters globally.
* **Hamcrest Assertions:** powerful matchers for validating complex JSON structures (e.g., `everyItem`, `hasSize`, `greaterThan`).
* **Reporting:** Integrated with Allure for detailed graphical reports of API interactions.

## 🛠️ Tech Stack

* **Language:** Java 17
* **Client:** Rest Assured
* **Data Binding:** Jackson Databind (FasterXML)
* **Test Runner:** TestNG
* **Reporting:** Allure Reports
* **Build Tool:** Maven

## 🧪 Test Coverage

The suite covers **7 robust scenarios** across different API capabilities:

### 1. 📝 CRUD Operations (Lifecycle)
* **CREATE (POST):** Serializes a `UserRequest` Java object to JSON and validates the `201 Created` response.
* **READ (GET):** Validates list retrieval, specific ID fetching, and data integrity.
* **UPDATE (PUT):** Modifies an existing resource and asserts the returned updated data.
* **DELETE (DELETE):** Removes a resource and verifies the `200 OK` confirmation.

### 2. 🔍 Advanced Validations
* **Filtering (Query Params):** Validates that endpoints correctly filter data (e.g., `/posts?userId=1`) using `everyItem` assertions to ensure list consistency.
* **Negative Testing:** Verifies error handling by requesting non-existent resources (validating `404 Not Found`).

## 📦 How to Run

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/francisco9403/RestAssuredAutomation.git](https://github.com/francisco9403/RestAssuredAutomation.git)
    ```
2.  **Run tests using Maven:**
    ```bash
    mvn clean test
    ```
3.  **Generate & Open Allure Report:**
    ```bash
    mvn allure:serve
    ```
## 📊 Test Report
[View Latest Execution Report](https://francisco9403.github.io/RestAssuredAutomation/)


## 🐳 Run with Docker (Zero Setup)

You can execute the full test suite without installing Java or Maven locally. Just use Docker:

1.  **Run tests & Generate Report:**
    ```bash
    docker-compose up --build
    ```

2.  **View Results:**
    The Allure HTML report will be automatically generated in:
    `target/site/allure-maven-plugin/index.html`


## cw Project Structure

```text
src/
├── main/java/models/      # POJOs for JSON Serialization
│   └── UserRequest.java   # Object representation of a User
│
├── test/java/tests/       # Test Classes
│   ├── BaseTest.java      # Global Config (SpecBuilder, Filters)
│   ├── UsersTest.java     # GET & Negative Tests
│   ├── CreateUserTest.java# POST Tests (Serialization)
│   ├── LifecycleTest.java # PUT & DELETE Tests
│   └── PostsTest.java     # Filtering & List Validation
│
└── test/resources/        # Configuration
    └── allure.properties  # Report config
