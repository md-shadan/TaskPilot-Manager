# TaskPilot - A Secure Personalized Task Management System 🗂️

A full-stack Task Management Web App developed using **Spring Boot**, **Thymeleaf**, and **JWT Authentication**. It enables secure login-based access, personalized task operations, and provides a modern UI using Bootstrap. Users can manage their tasks seamlessly with CRUD functionality.

## 🚀 Features

- 🔐 JWT-based authentication (Login / Signup)
- 👤 User-specific task management
- ➕ Add new tasks  
- 📝 Update existing tasks  
- 🗑️ Delete tasks  
- ✅ Mark tasks as completed  
- 📌 Set task priority (Low, Medium, High)
- 🖥️ Responsive UI with Thymeleaf + Bootstrap

## 🛠️ Tech Stack

- Java 22  
- Spring Boot 3  
- Spring Security + JWT  
- Spring Data JPA  
- Thymeleaf (for UI)  
- MySQL Database  
- Hibernate  
- Maven  
- Bootstrap 5

## 📦 Setup Instructions

### Clone the Repository

```bash
git clone https://github.com/md-shadan/TaskPilot-Manager.git
cd TaskPilot-Manager
```

### Configure MySQL Database

Create a MySQL database named `taskmanager`.

Then update your `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/taskmanager
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# JWT settings
jwt.secret=your_jwt_secret
jwt.expiration=86400000
```

### Run the Application

```bash
./mvnw spring-boot:run
```


## 📂 API Endpoints

| Method | Endpoint             | Description              |
|--------|----------------------|--------------------------|
| POST   | `/auth/signup`       | Register new user        |
| POST   | `/auth/login`        | Login and get JWT token  |
| GET    | `/tasks`             | Get all tasks for user   |
| GET    | `/tasks/{id}`        | Get task by ID           |
| POST   | `/tasks`             | Create new task          |
| PUT    | `/tasks/{id}`        | Update existing task     |
| DELETE | `/tasks/{id}`        | Delete task              |
| PATCH  | `/tasks/{id}/done`   | Mark task as completed   |

## 🙋‍♂️ Author

**Md Shadan**  
GitHub: [@md-shadan](https://github.com/md-shadan)

## 📄 License

This project is open-source and available under the [MIT License](LICENSE).
