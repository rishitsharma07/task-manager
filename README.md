# Task Manager Application

A full-stack task management web application built with Spring Boot and React.

## Features

- User authentication and authorization using JWT
- Create, update, and delete tasks
- Filter tasks by priority and status
- Responsive design for web and mobile screens
- Secure password hashing with BCrypt

## Tech Stack

**Backend**
- Java 17
- Spring Boot 3.5
- Spring Security 6
- Spring Data JPA + Hibernate
- H2 Database (development)
- JWT (jjwt 0.12.6)
- Maven

**Frontend**
- React 19 + Vite
- React Router DOM
- Axios
- CSS3

## Project Structure

```
task-manager/
├── src/                          # Spring Boot backend
│   └── main/java/com/taskflow/taskmanager/
│       ├── config/               # AppConfig, SecurityConfig, CorsConfig
│       ├── controller/           # AuthController, TaskController
│       ├── dto/                  # LoginRequest, RegisterRequest, TaskRequest
│       ├── entity/               # User, Task
│       ├── repository/           # UserRepository, TaskRepository
│       ├── security/             # JwtAuthFilter, UserDetailsServiceImpl
│       └── service/              # UserService, TaskService, JwtService
├── frontend/                     # React frontend
│   └── src/
│       ├── api/                  # Axios instance with JWT interceptor
│       ├── context/              # AuthContext
│       ├── pages/                # LoginPage, RegisterPage, DashboardPage
│       └── components/           # Reusable components
└── pom.xml
```

## API Endpoints

| Method | Endpoint | Auth | Description |
|--------|----------|------|-------------|
| POST | `/api/auth/register` | None | Register a new user |
| POST | `/api/auth/login` | None | Login and receive JWT token |
| GET | `/api/tasks` | JWT | Get all tasks for logged-in user |
| POST | `/api/tasks` | JWT | Create a new task |
| PUT | `/api/tasks/{id}` | JWT | Update an existing task |
| DELETE | `/api/tasks/{id}` | JWT | Delete a task |

## Getting Started

### Prerequisites

- Java 17
- Node.js 18+
- Maven

### Backend Setup

1. Clone the repository
   ```bash
   git clone https://github.com/rishitsharma07/task-manager.git
   cd task-manager
   ```

2. Set the environment variable for JWT secret
   ```bash
   # Windows
   set JWT_SECRET=your-secret-key-here-minimum-32-characters

   # Mac/Linux
   export JWT_SECRET=your-secret-key-here-minimum-32-characters
   ```

3. Run the backend
   ```bash
   ./mvnw spring-boot:run
   ```

   The backend will start on `http://localhost:8080`

### Frontend Setup

1. Navigate to the frontend folder
   ```bash
   cd frontend
   ```

2. Install dependencies
   ```bash
   npm install
   ```

3. Start the development server
   ```bash
   npm run dev
   ```

   The frontend will start on `http://localhost:5173`

## Usage

1. Open `http://localhost:5173` in your browser
2. Register a new account
3. Login with your credentials
4. Create, edit, and delete tasks from the dashboard

## Author

Rishit Sharma — [GitHub](https://github.com/rishitsharma07)