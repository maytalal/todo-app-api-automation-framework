#  Todo App – API Automation Framework

Automated API test suite for a **To-Do application**

Covers full user and todo flows with a clean, production-ready framework design.

---

##  Tech Stack

| Tool | Purpose |
|---|---|
| Java  | Programming language |
| REST Assured | API testing library |
| TestNG | Test runner |
| Maven | Build & dependency management |
| Allure Report | Test reporting |
| GitHub Actions | CI/CD pipeline |

---

##  Project Structure
```
.postman/
├── Todo_App_API.postman_collection.json
└── Production.postman_environment.json
src/test/java/com/todo/
├── apis/
│   ├── ToDoApi.java
│   └── UserApi.java
├── base/
│   └── Specs.java
├── models/
│   ├── ToDo.java
│   └── User.java
├── Route/
│   ├── ErrorMessage.java
│   └── Route.java
├── Steps/
│   └── UserSteps.java
└── TestCases/
    ├── TODOTestCases.java
    └── UserTestCases.java
```

---

##  Test Coverage

**User Flow**
-  Register (Positive & Negative)
-  Login (Positive & Negative)

**Todo Flow**
-  Add Todo (Positive & Negative)
-  Get Todo
-  Delete Todo

---

##  How to Run
```bash
# Run all tests
mvn test

# Run with specific environment
mvn test -Denv=PRODUCTION
```

##  Generate Allure Report
```bash
mvn allure:serve
```

##  Parallel Execution

Tests run in **10 threads** for faster execution.

---

##  CI/CD

Automated pipeline runs on every push to `main` via **GitHub Actions**.




