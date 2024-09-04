# TODO Systems Application 🚀

Welcome to the TODO Systems Application! 
This is a robust and scalable TODO application designed to help you manage your tasks efficiently.
With a sleek frontend and a powerful backend, this app is built to enhance your productivity.

## Overview 📚

The TODO Systems Application is a full-stack project comprising:

- **Backend**: Built with Java, Spring Boot, and PostgreSQL, using Mockito for testing.
- **Frontend**: Developed with React to offer a modern, interactive user experience.

### Features ✨

- **User Authentication**: Secure login and registration.
- **Task Management**: Add, update, and delete tasks.
- **Task Filtering**: Filter tasks by status and priority.
- **Responsive Design**: Accessible on both desktop and mobile devices.

## Project Structure 🏗️

### Backend

- **Framework**: Spring Boot
- **Language**: Java
- **Database**: PostgreSQL
- **Testing**: Mockito
- **APIs**: RESTful endpoints for CRUD operations

#### Key Components:

- **Entities**: Define data models like User and Task.
- **Repositories**: Interface with the PostgreSQL database.
- **Services**: Handle business logic.
- **Controllers**: Expose REST endpoints for the frontend.
- **Configuration**: Manage application settings and security.

### Frontend

- **Framework**: React
- **Language**: JavaScript/TypeScript
- **State Management**: Context API or Redux
- **UI Library**: Material-UI or Bootstrap

#### Key Components:

- **Components**: Reusable UI elements like buttons, forms, and lists.
- **Pages**: Main views like the Dashboard and Task Manager.
- **Services**: Handle API requests and state management.
- **Routing**: Manage navigation between different views.

### CRUD REST API Documentation

### User API

### 1. Create User

| **Operation**    | **Create User**                                                              |
|------------------|------------------------------------------------------------------------------|
| **Method**       | POST                                                                         |
| **URL**          | `/api/users`                                                                 |
| **Request Body** | `{ "username": "string", "password": "string", "email": "string" }`          |
| **Response**     | `201 Created`<br>{ "id": "number", "username": "string", "email": "string" } |
| **Description**  | Creates a new user in the system.                                            |

### 2. Get User

| **Operation**          | **Get User**                                                            |
|------------------------|-------------------------------------------------------------------------|
| **Method**             | GET                                                                     |
| **URL**                | `/api/users/{id}`                                                       |
| **Request Parameters** | `id` (path parameter, integer)                                          |
| **Response**           | `200 OK`<br>{ "id": "number", "username": "string", "email": "string" } |
| **Description**        | Retrieves user details by user ID.                                      |

### 3. Update User

| **Operation**          | **Update User**                                                         |
|------------------------|-------------------------------------------------------------------------|
| **Method**             | PUT                                                                     |
| **URL**                | `/api/users/{id}`                                                       |
| **Request Parameters** | `id` (path parameter, integer)                                          |
| **Request Body**       | `{ "username": "string", "email": "string" }`                           |
| **Response**           | `200 OK`<br>{ "id": "number", "username": "string", "email": "string" } |
| **Description**        | Updates user details by user ID.                                        |

### 4. Delete User

| **Operation**          | **Delete User**                |
|------------------------|--------------------------------|
| **Method**             | DELETE                         |
| **URL**                | `/api/users/{id}`              |
| **Request Parameters** | `id` (path parameter, integer) |
| **Response**           | `204 No Content`               |
| **Description**        | Deletes a user by user ID.     |

## Task API

### 1. Create Task

| **Operation**    | **Create Task**                                                                                                                                |
|------------------|------------------------------------------------------------------------------------------------------------------------------------------------|
| **Method**       | POST                                                                                                                                           |
| **URL**          | `/api/tasks`                                                                                                                                   |
| **Request Body** | `{ "title": "string", "description": "string", "status": "string", "priority": "string", "dueDate": "string" }`                                |
| **Response**     | `201 Created`<br>{ "id": "number", "title": "string", "description": "string", "status": "string", "priority": "string", "dueDate": "string" } |
| **Description**  | Creates a new task in the system.                                                                                                              |

### 2. Get Task

| **Operation**          | **Get Task**                                                                                                                              |
|------------------------|-------------------------------------------------------------------------------------------------------------------------------------------|
| **Method**             | GET                                                                                                                                       |
| **URL**                | `/api/tasks/{id}`                                                                                                                         |
| **Request Parameters** | `id` (path parameter, integer)                                                                                                            |
| **Response**           | `200 OK`<br>{ "id": "number", "title": "string", "description": "string", "status": "string", "priority": "string", "dueDate": "string" } |
| **Description**        | Retrieves task details by task ID.                                                                                                        |

### 3. Update Task

| **Operation**          | **Update Task**                                                                                                                           |
|------------------------|-------------------------------------------------------------------------------------------------------------------------------------------|
| **Method**             | PUT                                                                                                                                       |
| **URL**                | `/api/tasks/{id}`                                                                                                                         |
| **Request Parameters** | `id` (path parameter, integer)                                                                                                            |
| **Request Body**       | `{ "title": "string", "description": "string", "status": "string", "priority": "string", "dueDate": "string" }`                           |
| **Response**           | `200 OK`<br>{ "id": "number", "title": "string", "description": "string", "status": "string", "priority": "string", "dueDate": "string" } |
| **Description**        | Updates task details by task ID.                                                                                                          |

### 4. Delete Task

| **Operation**          | **Delete Task**                |
|------------------------|--------------------------------|
| **Method**             | DELETE                         |
| **URL**                | `/api/tasks/{id}`              |
| **Request Parameters** | `id` (path parameter, integer) |
| **Response**           | `204 No Content`               |
| **Description**        | Deletes a task by task ID.     |


## Getting Started 🚀

### Prerequisites 🛠️

- Java 17 or later
- PostgreSQL
- Node.js and npm (for frontend)
- Maven (for backend dependency management)

### Backend Setup 🔧

1. **Clone the repository:**
   ```bash
   git clone https://github.com/yourusername/todo-systems-app.git
   cd todo-systems-app/backend
   ```
2. **Configure the database:**
    - Create a PostgreSQL database.
    - Update src/main/resources/application.properties with your database credentials.
3. **Build and run the application:**
   ```bash
   ./mvnw spring-boot:run
   ```
4. **Run tests:**
   ```bash
   ./mvnw test
   ```

### Frontend Setup 🔧

1. **Navigate to the frontend directory:**
   ```bash
   cd todo-systems-app/frontend
   ```
2. **Install dependencies:**
   ```bash
   npm install
   ```
3. **Run the development server:**
   ```bash
   npm start
   ```

### Usage 🖥️

1. **Open the application:** in your browser (typically at http://localhost:3000 for the frontend).
2. **Create an account or log in.**
3. **Start managing your tasks using the intuitive UI.**
   
### Contributing 🤝

I welcome contributions to improve the **TODO Systems Application!** To get started:
1. **Fork the repository and clone it to your local machine.**
2. **Create a new branch for your feature or bug fix**
   ```bash
   git checkout -b feature/my-new-feature
   ```
3. **Make your changes and commit them**
   ```bash
    git commit -am 'Add new feature'
   ```
4. **Push your changes to your forked repository**
   ```bash
    git push origin feature/my-new-feature
   ```
5. **Open a Pull Request on GitHub.**

## License 📜

This project is licensed under the MIT License—see the [LICENSE](LICENSE) file for details.

## Contact 📬

For any questions or feedback, feel free to reach out:
- **Email**: [vanilson](mailto:vanilsonmuhongo@gmail.com)
- **GitHub Issues**: [GitHub Issues](https://github.com/edsonwade/todo-system-application/issues)
