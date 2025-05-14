# Otus Lesson Web

A Java-based project for testing web applications, featuring a Selenium-driven test framework integrated with modern testing tools such as JUnit and Cucumber.

---

## Table of Contents

- [Overview](#overview)
- [Project Structure](#project-structure)
- [Technologies and Dependencies](#technologies-and-dependencies)
- [Installation and Setup](#installation-and-setup)
- [Running Tests](#running-tests)

---

## Overview

The **Otus Lesson Web** project is a web testing framework built using Java 21. It leverages modern tools such as Selenium WebDriver, JUnit 5, Cucumber, AssertJ, and Guice for efficient and scalable web test automation. The project is designed to support a wide range of web automation and testing use cases.

---

## Project Structure

```plaintext
otus-lesson-web/
├── src/
│   ├── main/         # Main application source code (if any)
│   ├── test/         # Test-related source code
├── pom.xml           # Maven Project Object Model
├── target/           # Compiled files and test reports
└── README.md         # Project documentation
```

The framework primarily focuses on automating web application testing using Selenium and integrating additional utilities for reporting, dependency injection, and assertions.

---

## Technologies and Dependencies

The project is built with the following technologies and dependencies:

### **Programming Language**

- **Java 21**

### **Key Dependencies**

- **Selenium**: Web automation library (`4.31.0`)
- **JUnit 5**: Testing framework (`5.12.1`)
- **Cucumber**: Behavior-driven development (`7.2.3`)
- **Guice**: Dependency injection library (`7.0.0`)
- **AssertJ**: Fluent assertions library (`3.27.3`)
- **Guava**: Utility libraries for Java (`32.1.2-jre`)
- **Jsoup**: HTML parsing (`1.18.3`)

### **Maven Plugins**

- **maven-surefire-plugin** (3.5.3): For executing automated tests.
- **maven-compiler-plugin** (3.14.0): For compiling Java source code.

---

## Installation and Setup

To set up and run the project:

### **Step 1: Prerequisites**
Make sure you have the following installed on your system:
- **Java 21 JDK**
- **Maven 3.8.x or higher**
- **Git** (optional for version control)

Ensure `JAVA_HOME` is set appropriately and added to your system's PATH.

### **Step 2: Clone the Repository**
```bash
git clone https://github.com/gbkocharyan/otus-lesson-web.git
cd otus-lesson-web
```

### **Step 3: Install Dependencies**
Use Maven to download and install all required dependencies:
```bash
mvn clean install
```

---

## Running Tests

### **Execute All Tests**
Run all tests in the project using the `maven-surefire-plugin`:
```bash
mvn test
```

### **Run Specific Tests (if applicable)**
To run specific tests, use:
```bash
mvn -Dtest=<TestClassName> test
```

### **Check Test Reports**
Test results are available in:
```plaintext
target/surefire-reports/
```

---

## Debugging Common Issues

1. **Error in Forked Process**:  
   If you experience errors related to the forked process, ensure JDK compatibility (Java 21) and verify your Maven dependencies.  
   Use:
   ```bash
   mvn dependency:tree
   ```
   to debug Maven dependency issues.

2. **Test Environment Issues**:  
   Ensure the test environment, like WebDriver binaries (via WebDriverManager), is set up correctly.
